package com.zh.core.wxpay;

import java.io.*;

public class WXPayConfigImpl extends WXPayConfig {
    @Override
    String getAppID() {
        return "wxa9e6f1fa364a24ab";
    }

    @Override
    String getMchID() {
        return "1370838302";
    }

    @Override
    String getKey() {
        return "Q1ew9re8ret7i8pgj2mn1b23bg5f7ik5";
    }

    @Override
    InputStream getCertStream() throws FileNotFoundException {
        File file = new File("/apiclient_cert.p12");
        FileInputStream fileInputStream = new FileInputStream(file);
        return new BufferedInputStream(fileInputStream);
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API,true);
            }
        };
    }
}
