package com.xxx.common.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



@Controller
@RequestMapping("alipay")
public class Callback {

    @Autowired
    AliPayConfig aliPayConfig;

    @ResponseBody
    @RequestMapping("paymentOrder")
    public void paymentOrder(String money, String month, HttpServletResponse httpResponse)
            throws IOException, AlipayApiException {

        /**
         * 实例化客户端
         * 	参数：网关地址、商户APP_ID、商户私钥、格式、编码、支付宝公钥、加密类型
         */
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(), aliPayConfig.getAppid(), aliPayConfig.getMerchantPrivateKey(), aliPayConfig.getFormate(),
                aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType());

        String outTradeNo = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
                + System.currentTimeMillis() + "";// 生成商户订单号
        AlipayTradePagePayModel payment = new AlipayTradePagePayModel();
        payment.setOutTradeNo(outTradeNo);// 订单号
        payment.setProductCode("FAST_INSTANT_TRADE_PAY");// 销售产品码（不可更改）
        payment.setTotalAmount(money);// 金额
        payment.setSubject("充值");// 标题
        payment.setBody("充值");// 描述
        payment.setTimeoutExpress("30m");// 该笔订单允许的最晚付款时间（30分钟）
        payment.setPassbackParams(month);

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());// 在公共参数中设置回跳和通知地址
        alipayRequest.setBizModel(payment);// 分装参数
        String form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单

        httpResponse.setContentType("text/html;charset=" + aliPayConfig.getCharset());
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();// 关闭

    }


    @ResponseBody
    @RequestMapping("alipayNotify")
    public String alipayNotify(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = new HashMap<String, String>();
        // 从支付宝回调的request域中取值
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        String tradeStatus = request.getParameter("trade_status"); // 交易状态
        boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType());// 调用SDK验证签名
        // 4.对验签进行处理
        if (signVerified) { // 验签通过
            if (tradeStatus.equals("TRADE_SUCCESS")) { // 只处理支付成功的订单: 修改交易表状态,支付成功
				/*BigDecimal total_amount = BigDecimal.valueOf(Double.valueOf(params.get("total_amount").toString()));// 充值金额
				User user = (User) request.getSession().getAttribute("user_login");
				String userTelephone = user.getUser_telephone();// 当前用户手机号
				String passback_params = params.get("passback_params").toString();
				chargerecordService.insertChargerecord(total_amount, userTelephone, passback_params);// 充值记录添加
				BigDecimal sumUserMoney = userService.sumUserMoney(userTelephone);// 使用手机号统计金额
				System.out.println(sumUserMoney);
				BigDecimal totalSum = sumUserMoney.add(total_amount);// 现用户总金额加充值金额
				userService.updateUserMoney(totalSum, userTelephone);// 使用手机号修改金额*/
				return "success";
            } else {
                return "fail";
            }
        } else { // 验签不通过
            return "fail";
        }
    }

}
