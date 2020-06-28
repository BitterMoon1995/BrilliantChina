package com.zh.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfigImpl;
import com.mysql.cj.protocol.x.Notice;
import com.zh.admin.entity.VipCard;
import com.zh.admin.service.IVipCardService;
import com.zh.admin.utils.GodzSUtils;
import com.zh.admin.utils.HttpsUtils;
import com.zh.admin.wxpay.Openid;
import com.zh.admin.wxpay.PayVo;
import com.zh.admin.wxpay.UnifiedPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    RedisTemplate<String,String> redisTemplate;

    @GetMapping("/login")
    public Object login(@RequestParam String code) throws Exception {
        RestTemplate template = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String, String> params = new HashMap<String, String>();
        params.put("appid","wxa9e6f1fa364a24ab");
        params.put("secret","7db719b2cead5dab3af40032612f71d0");
        params.put("js_code",code);
        params.put("grant_type","authorization_code");

        String s = HttpsUtils.get(url, params, null);
        Object zxtsima = JSONObject.parse(s);
        return zxtsima;
    }


    @PostMapping("/save")
    public void saveVipInfo(@RequestBody VipCard vipCard){
        //新会员注册
        if (vipCard.getId()==null){
            //生成推荐码
            String name = vipCard.getRealName();
            String firstPinYin = GodzSUtils.getFirstPinYin(name);

            String randomS = GodzSUtils.getCharAndNum(6-firstPinYin.length());

            vipCard.setPromoCode(firstPinYin+randomS);

            //生成生日
            Calendar calendar = Calendar.getInstance();
            String idNum = vipCard.getIdNum();
            String year = idNum.substring(6, 10);
            String month = idNum.substring(11, 12);
            String day = idNum.substring(13, 14);
            calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
            vipCard.setBirthday(calendar.getTime());

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(1989,5,4);
            vipCard.setExpirationTime(calendar2.getTime());

            service.save(vipCard);
        }
    }

    @PostMapping("/pay")
    public PayVo pay(@RequestBody Openid openid, HttpServletRequest request) throws Exception {
        String theShy = openid.getOpenid();
        PayVo vo = UnifiedPayUtil.unifiedPay(theShy);
        String nonceStr = vo.getNonceStr();
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        op.set(openid.getOpenid(),nonceStr,60, TimeUnit.SECONDS);
        return vo;
    }

    @PostMapping("/charge")
    public void charge(@RequestParam String openid,@RequestParam String nonceStr){
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        String myStr = op.get(openid);
        assert myStr != null;
        if (myStr.equals(nonceStr)){
            QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
            wrapper.eq("openid",openid);
            VipCard vipCard = service.getOne(wrapper);

            Date date = vipCard.getExpirationTime();
            Calendar expTime = Calendar.getInstance();
            expTime.setTime(date);

            //没充过，当前时间续一年
            if (expTime.get(Calendar.YEAR)==1989){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
                vipCard.setExpirationTime(calendar.getTime());
            }
            //充过，原有到期时间续一年
            else {
                expTime.set(Calendar.YEAR,expTime.get(Calendar.YEAR)+1);
                vipCard.setExpirationTime(expTime.getTime());
            }
            service.updateById(vipCard);
        }
    }

    @GetMapping("/getVipInfo")
    public VipCard getVipInfo(String openid){
        QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return service.getOne(wrapper);
    }
}
