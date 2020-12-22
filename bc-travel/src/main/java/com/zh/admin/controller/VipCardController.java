package com.zh.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.admin.entity.VipCard;
import com.zh.admin.entity.Withdraw;
import com.zh.admin.service.IVipCardService;
import com.zh.admin.service.IWithdrawService;
import com.zh.common.iResult;
import com.zh.core.utils.DateUtils;
import com.zh.core.utils.GodzSUtils;
import com.zh.core.utils.HttpsUtils;
import com.zh.core.wxpay.Openid;
import com.zh.core.wxpay.PayVo;
import com.zh.core.wxpay.UnifiedPayUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author God周周神
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/vip/vip-card")
public class VipCardController {
    @Autowired
    IVipCardService service;
    @Autowired
    IWithdrawService withdrawService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    //获取openID
    @GetMapping("/login")
    public Object login(@RequestParam String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid","wxa9e6f1fa364a24ab");
        params.put("secret","7db719b2cead5dab3af40032612f71d0");
        params.put("js_code",code);
        params.put("grant_type","authorization_code");

        String s = HttpsUtils.get(url, params, null);
        //用户在微信系的信息，openid、session_key等
        Map<String,String> wxInfo = JSON.parseObject(s, Map.class);
        String openid = wxInfo.get("openid");
        short vipStatus = service.checkVipStatus(openid);

        wxInfo.put("vipStatus",String.valueOf(vipStatus));
        return wxInfo;
    }

    //新用户注册
    @PostMapping("/save")
    public iResult saveVipInfo(@RequestBody VipCard vipCard){
        //生成推荐码
        String name = vipCard.getRealName();
        String firstPinYin = GodzSUtils.getFirstPinYin(name);

        String randomS = GodzSUtils.getCharAndNum(8-firstPinYin.length());
        vipCard.setPromoCode(firstPinYin+randomS);

        //生成生日
        String idNum = vipCard.getIdNum();
        if (idNum.length()!=18)
            return iResult.illegalParam;

        Calendar calendar = Calendar.getInstance();
        String year = idNum.substring(6, 10);
        String month = idNum.substring(11, 12);
        String day = idNum.substring(13, 14);
        calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
        vipCard.setBirthday(calendar.getTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1989, Calendar.JUNE,4);
        vipCard.setExpirationTime(calendar2.getTime());

        if (service.save(vipCard))
            return iResult.success;
        else return iResult.serverDown;
    }
    @PostMapping("/edit")
    public Integer edit(@RequestParam String phone,@RequestParam String address,
                        @RequestParam String photoSrc,@RequestParam String openid){
        VipCard vipCard = getByOpenid(openid);
        Date editTime = vipCard.getEditTime();
        if (editTime!=null){
            Long editStamp = new Timestamp(editTime.getTime()).getTime();
            Long currentStamp = new Timestamp(new Date().getTime()).getTime();
            long dif = (currentStamp - editStamp) / 86400000 ;
            if (dif < 1) return 401; //修改间隔小于一天
        }

        if (!phone.isEmpty()) vipCard.setPhone(phone);
        if (!address.isEmpty()) vipCard.setAddress(address);
        if (!photoSrc.isEmpty()) vipCard.setPhotoSrc(photoSrc);
        vipCard.setEditTime(new Date());

        boolean b = service.updateById(vipCard);
        if (b) return 200;  //完结撒花  ✿✿ヽ(°▽°)ノ✿
        else return 500; //服务器错误
    }

    //异步校验推荐码
    @PostMapping("/verifyPmCode")
    public Integer verifyPmCode(@RequestParam String openid,@RequestParam String pmCode){
        System.out.println(openid+"nigger"+pmCode);
        if (StringUtils.isAnyBlank(openid,pmCode)) return 403;
        VipCard i = getByOpenid(openid);
        VipCard she = getByPmCode(pmCode);
        System.out.println(i+"nigger"+she);
        //此推荐码无匹配会员
        if (!ObjectUtils.allNotNull(i, she)) return 400;
        //不能推荐自己
        if (she.getOpenid().equals(openid)) return 415;
        //推荐人没充值
        if (DateUtils.checkExpire(she.getExpirationTime())!=6)
            return 416;
        return 200;
    }

    //支付
    @PostMapping("/pay")
    public PayVo pay(@RequestParam String openid) throws Exception {
        System.out.println(openid);
        //统一下单
        PayVo vo = UnifiedPayUtil.unifiedPay(openid);
        //Redis设置 openID-此次下单随机串 键值对
        //会员时长充值安全性设计：
        // 1.外部调用charge方法充值会员时长时，必须携带统一下单生成的字符串
        // 2.charge方法调用成功后，Redis中该键值对即刻销毁，永远保证给一次钱<=>充一次值
        String nonceStr = vo.getNonceStr();
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        op.set(openid,nonceStr,60, TimeUnit.SECONDS);
        return vo;
    }

    //★★★支付回调
    @Transactional
    @PostMapping("/charge")
    public synchronized iResult charge(@RequestParam String openid,
                       @RequestParam String nonceStr,
                       @RequestParam(required = false) String promoCode){

        if (StringUtils.isAnyBlank(openid,nonceStr)) return iResult.illegalParam;
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        String myStr = op.get(openid);
        if (myStr == null || !myStr.equals(nonceStr)) return iResult.hostileAttack;
        //校验通过，合法充值
        QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        VipCard me = service.getOne(wrapper);

        Date date = me.getExpirationTime();
        Calendar expTime = Calendar.getInstance();
        expTime.setTime(date);

        //没充过，当前时间续一年
        if (expTime.get(Calendar.YEAR)==1989){
            /*  这段get-set-set-set操作，非原子    */
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            /*  且calendar本身就是线程不安全    */

            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
            me.setExpirationTime(calendar.getTime());

            /*  必出事，必死    */

            //设置为已充值过のBOY
            me.setCharged(true);
            //推荐人获取收益条件1：得有推荐码
            if (!promoCode.isEmpty()){
                //获取上级
                wrapper.clear();
                wrapper.eq("promo_code",promoCode);
                VipCard upOneLevel = service.getOne(wrapper);
                //上级没充值？摘了
                short sta = DateUtils.checkExpire(upOneLevel.getExpirationTime());
                //2.推荐人是未过期会员
                if (sta == 6) {//设置上级ID
                    me.setSuperiorId(upOneLevel.getId());

                    //上级收米
                    Integer profit = upOneLevel.getProfit();
                    if (profit == null) upOneLevel.setProfit(20);
                    else upOneLevel.setProfit(profit + 20);
                    service.updateById(upOneLevel);

                    //尝试获取上上级
                    String upTwoLevelId = upOneLevel.getSuperiorId();
                    if (StringUtils.isNotBlank(upTwoLevelId)) {
                        wrapper.clear();
                        wrapper.eq("id", upTwoLevelId);
                        VipCard upTwoLevel = service.getOne(wrapper);
                        //上上级收米
                        Integer profit2 = upTwoLevel.getProfit();
                        if (profit2 == null) upTwoLevel.setProfit(2);
                        else upTwoLevel.setProfit(profit2 + 2);
                        service.updateById(upTwoLevel);
                    }
                }
            }
        }
        //还要判断是否已过期
        //没过期直接续一年，已过期当前时间充一年啊！！！！！！！！！！！！！！！！！！！！！！！！
        else {
            Date now = new Date();
            //如果是过期了的，怎么可能在过期时间上续？肯定当前时间啊
            if (expTime.getTime().before(now)) {
                expTime.setTime(now);
            }
            expTime.set(Calendar.YEAR,expTime.get(Calendar.YEAR)+1);
            me.setExpirationTime(expTime.getTime());
        }
        //安全性设计，见L-128
        redisTemplate.delete(openid);
        service.updateById(me);
        return iResult.success;
    }

    //提现
    @GetMapping("/withdraw")
    public Integer withdraw(@RequestParam String wechatId,@RequestParam String openid){
        VipCard vipCard = getByOpenid(openid);
        vipCard.setWechatId(wechatId);

        Withdraw withdraw = new Withdraw();
        withdraw.setAmount(vipCard.getProfit());
        withdraw.setCreateTime(LocalDateTime.now());
        withdraw.setWechatId(wechatId);
        withdraw.setState("未处理");

        if (withdrawService.save(withdraw)){
            vipCard.setProfit(0);
            service.updateById(vipCard);
            return 200;
        }
        return 500;
    }

    @GetMapping("/getVipInfo")
    public VipCard getVipInfo(String openid){
        return getByOpenid(openid);
    }

    public VipCard getByOpenid(String openid){
        QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        VipCard vipCard = service.getOne(wrapper);

        if (vipCard==null)
            return null;

        short vipStatus = DateUtils.checkExpire(vipCard.getExpirationTime());
        vipCard.setVipStatus(vipStatus);
        return vipCard;
    }

    public VipCard getByPmCode(String pmCode){
        QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
        wrapper.eq("promo_code",pmCode);
        return service.getOne(wrapper);
    }
}
