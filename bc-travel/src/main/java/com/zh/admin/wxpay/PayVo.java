package com.zh.admin.wxpay;

import lombok.Data;

@Data
public class PayVo {
    String paySign;
    String timeStamp;
    String nonceStr;
    String packageZ;
}
