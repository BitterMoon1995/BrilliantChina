package com.zh.admin.controller;


import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(zxtsima);
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

            service.save(vipCard);
        }
    }

    @PostMapping("/pay")
    public PayVo pay(@RequestBody Openid openid, HttpServletRequest request) throws Exception {
        String theShy = openid.getOpenid();
        PayVo vo = UnifiedPayUtil.unifiedPay(theShy);
        return vo;
    }
}
