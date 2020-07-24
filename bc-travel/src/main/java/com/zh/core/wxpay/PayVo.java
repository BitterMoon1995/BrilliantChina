package com.zh.core.wxpay;

import lombok.Data;

@Data
public class PayVo {
    String paySign;
    String timeStamp;
    String nonceStr;
    String packageZ;
}
