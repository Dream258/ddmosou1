package com.xxx.common.alipay;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AliPayConfig {

    /** 支付宝gatewayUrl */
    private String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    /** 商户应用id */
    private String appid = "2018120362377822";
    /** RSA私钥，用于对商户请求报文加签 */
    private String merchantPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCqUIlDRVPsdP9AQYBQg6qT8IQefGLUIlYrXUqcep8oCqDBDM7Tt/0kdsEuZNHOA6orylLL7FDVV7qA8lC0+axmsFsxWGOnV8jtrSZwves1PxJ27NDqnyXfIzEgkF52pjvVhQZ/6FbG9z++HtVxnc+g+l2uGKeDifSbPMmsiTidiuyAzYl/xlnod0A5k1MGWfUSqdpo1jkT1h7bZ/H/m3zGl5MkSse63p0GbR5ufoREXvZSiHmyQ5sDDUT2HE+wCb1TAyvX9rviRz2eCTRlr+4bL3zcb7VuDb9x/5t+WakEPTE8U48FbL0++I4s2ETiY1YoSWnuz+sF+mXmLv3Jd6VXAgMBAAECggEBAJwoFYKZeTZPm42VwQmRf3//QZQWJiMsjGdcNUG96Fm1TOlUTfqvMqoRPFTGWduTXp6h9kJFHWRO+JCsHDWiymCpCCP5T/2qLm9Fl/49FACRIOPrhC3q17V8tdmOpR4Skr/WJTx3kcECB+7QpJF0yV9mzygGMOkZPy5qkNriQgenR4Lh2ZYKlzI177qzj5YVRYaa31pprunegF26CuOGEfO26sQFj6r6hopd4/foVy9QJ98zHLBva2HJugNOZ1OFnCq2FMZyS+cB8WKWnQypCq+4g8i+5STd3cDm3z97J+29/Yl0IgZXqlkVCNftPYSSgorV4l/xCTd74EkCnFqjqyECgYEA/nAnd4W+idCLoNRWQIp49GUUCF6FmmZLacCJE938nRcfnH41oMB1Eb+FPC9qA63anMGm2ImT5ZQMJeG4Uwz153bYEFx1xTYlouhMot/oRuczJ9q1HjYu8PoXvE/ZT0zHH+W8piBdit8ShzNMSVGOXXy368q/chK/5pHaQZWwzIcCgYEAq1wu4XkcldaavX36J1ksu2NoGT4Esd5QAMwuIyngmJtRVk1l2UwFzYe0LttZKnmb7QPZ5cCgfdXPc2vuL4GBl37mWg94NhlhSDXVHA4cUUo4NYaj6dCKMpiWpcwqpK+4cm8VaQKIpL+7waNHV6MMjj2xMW4H3sHlxdjgBpHe5LECgYEAnnZbHiU9tbccPt3/mdKj4hHRmCTGMJLlBMfUl3r5iRyNo3k3DRbP0m3lHMaqz//G1zoEH3gPlDvQFQevNIot3Co4BniPIHPInNzwXb/1ebMkOP4NLvOv6ITZo35XaPv4R/VcuFoP6B1l/K4GJOOWNsMmCMQM14611XOg6pe/tKECgYA2G9eZzrpd/fPCngLdZeUgDpWSUuj8mSw99DslU1XeGj0muS3N3Zl/erhEi3KuXNQFlU+6ooaK9XaaBZyiV4xqH+Hw0i2tN3pmaMkwIKyEZqE1FZywee8JZvE6zSsx8WKWj8j87ZXkH77TuBEQ9SVDzOskpo/JoQRC4uZqbKkrIQKBgQDRJF1LUinG/x7aIwK3sRgTyGw35vDLXYKsUmJogbBnVk5SS3KXjEec7dA1fdwJb/Eex3kvBsbHK6ptdBeNpOSDQycHBF4L6B2RNpzKaolH4F55lMjwEwvpBYBtrYbvJEunRlmb7NJ5X/jKu8EkKs2ac5Kea9q59JH+AOLZhn5BAA==";
    /** 支付宝RSA公钥，用于验签支付宝应答 */
    private String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgHB0CKKGHKVlBVZ3wUeerhBX3E5FV/hmdiy/aicujYGP+a8kQPswPqmlvJTiPO5G15IMRyF/cStCYQXNAX1o6r2o/VCx/2ZU9Jigia6bXak0FAmH2AqAtXI0y1Q+LF2fI2sQ1yV/oCTXO9EuXFmy8Ii+WYT8hgeXePLsbCKOzNm2q8lhA6iS1Q0tMUzX6nqWv5aFqt+9qKMkBUtWCyJkpO8jpVKap9OsXjHbJY73Y7G8XFL4rCTUkz/v+cihSkqbbN4OW//x/bCkHHVGiTrvv6fX8uNlCg9XOMm6zJXwXmFgGbSn+kd6V2MqM+d7xAivRvZAwYR+adoKm1yspGMQzQIDAQAB";
    /** 签名类型 */
    private String signType = "RSA2";
    /** 格式 */
    private String formate = "json";
    /** 编码 */
    private String charset = "UTF-8";
    /** 同步地址 */
    private String returnUrl = "http://";
    /** 异步地址 */
    private String notifyUrl;

}
