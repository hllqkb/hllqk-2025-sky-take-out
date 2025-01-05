package com.sky.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sky.wechat")
@Slf4j
public class WechatProperties {
    private String appId;// 小程序 appId
    private String secret;// 小程序 appSecret
    private String mchid;// 商户号
    private String mchSerialNo;// 商户证书序列号
    private String privateKeyFilePath;// 商户证书私钥文件路径
    private String apiV3Key;// 微信支付 API v3 密钥
    private String notifyUrl;// 微信支付异步通知地址
    private String weChatPayCertFilePath;// 微信支付证书文件路径
    private String refundNotifyUrl;// 微信退款异步通知地址
}
