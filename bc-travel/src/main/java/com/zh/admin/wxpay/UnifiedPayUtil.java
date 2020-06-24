package com.zh.admin.wxpay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfigImpl;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.zh.admin.utils.GodzSUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.github.wxpay.sdk.WXPayConstants.MD5;

public class UnifiedPayUtil {

    public static PayVo unifiedPay(String openid) throws Exception {
        String nonce_str = WXPayUtil.generateNonceStr();
        String tradeNum = GodzSUtils.getCharAndNum(25);

        HashMap<String, String> params = new HashMap<>();
        params.put("appid","wxa9e6f1fa364a24ab");
        params.put("mch_id","1370838302");
        params.put("nonce_str",nonce_str);//随机串
        params.put("body","最美中国年费会员充值");
        params.put("out_trade_no",tradeNum);//订单号
        params.put("spbill_create_ip","127.0.0.1");
        params.put("notify_url","127.0.0.1");
        params.put("trade_type","JSAPI");
        params.put("total_fee","1");
        params.put("openid",openid);
        params.put("sign_type","MD5");

        WXPay wxPay = new WXPay(new WXPayConfigImpl());
        Map<String, String> resultMap = wxPay.unifiedOrder(wxPay.fillRequestData(params));

        String timeStamp=WXPayUtil.getCurrentTimestamp()+"";
        String prepay_id = resultMap.get("prepay_id");
        String signature=getSign(prepay_id,timeStamp,nonce_str);

        PayVo payVo = new PayVo();
        payVo.setNonceStr(nonce_str);
        payVo.setTimeStamp(timeStamp);
        payVo.setPackageZ(prepay_id);
        payVo.setPaySign(signature);

        return payVo;
    }
    public static String getSign(String packageZ,String timeStamp,String nonce_str) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid","wxa9e6f1fa364a24ab");//小写的i！！！操腾讯死妈！！！！！！！！！
        map.put("timeStamp",timeStamp);
        map.put("nonceStr",nonce_str);
        map.put("package","prepay_id="+packageZ);
        map.put("signType", MD5);

        System.out.println(map);

        String nigger =  WXPayUtil.generateSignature(map, "Q1ew9re8ret7i8pgj2mn1b23bg5f7ik5", WXPayConstants.SignType.MD5);
        System.out.println(nigger);
        return nigger;
    }
}
