package wusc.edu.pay.api.merchant;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpException;
import wusc.edu.pay.common.utils.httpclient.Context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class Pay {

    public String merchantNo = "";

    public String merchantKey = "";

    public Pay(String merchantNo, String merchantKey) {
        this.merchantNo = merchantNo;
        this.merchantKey = merchantKey;
    }

    /**
     * 生成支付url,Post方式
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws HttpException
     */
    public String buildPayUrlPost(Map<String, String> map) throws UnsupportedEncodingException {
        String p1_MerchantNo = map.get("p1_MerchantNo");
        String p2_OrderNo = map.get("p2_OrderNo");
        String p3_Amount = map.get("p3_Amount");
        String p4_Cur = map.get("p4_Cur");
        String p5_ProductName = map.get("p5_ProductName");
        String p6_Mp = map.get("p6_Mp");
        String p7_ReturnUrl = map.get("p7_ReturnUrl");
        String p8_NotifyUrl = map.get("p8_NotifyUrl");
        String p9_FrpCode = map.get("p9_FrpCode");
        String pa_OrderPeriod = map.get("pa_OrderPeriod");
        String pb_PayerLoginName = map.get("pb_PayerLoginName");
        String pay_url = map.get("pay_url");

        StringBuilder html = new StringBuilder();
        // 支持b2c和b2b
        html.append("<form name='toPay' action='").append(pay_url).append("' method='POST'>\r");
        // 直接把地址传过来
        // html.append("<form name='toPay' action='").append(Context.PAY_URL).append("' method='POST'>\r");
        html.append("<input type='hidden' name='p1_MerchantNo' value='").append(p1_MerchantNo).append("'>\r");
        html.append("<input type='hidden' name='p2_OrderNo' value='").append(p2_OrderNo).append("'>\r");
        html.append("<input type='hidden' name='p3_Amount' value='").append(p3_Amount).append("'>\r");
        html.append("<input type='hidden' name='p4_Cur' value='").append(p4_Cur).append("'>\r");
        html.append("<input type='hidden' name='p5_ProductName' value='").append(URLEncoder.encode(p5_ProductName, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p6_Mp' value='").append(URLEncoder.encode(p6_Mp, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p7_ReturnUrl' value='").append(URLEncoder.encode(p7_ReturnUrl, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p8_NotifyUrl' value='").append(URLEncoder.encode(p8_NotifyUrl, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='p9_FrpCode' value='").append(p9_FrpCode).append("'>\r");
        html.append("<input type='hidden' name='pa_OrderPeriod' value='").append(pa_OrderPeriod).append("'>\r");
        html.append("<input type='hidden' name='pb_PayerLoginName' value='").append(URLEncoder.encode(pb_PayerLoginName, "utf-8")).append("'>\r");
        html.append("<input type='hidden' name='hmac' value='").append(hmacRequest(map)).append("'>\r");
        html.append("</form><script>document.forms['toPay'].submit();</script>");
        return html.toString();
    }

    /**
     * 生成支付url,Get方式
     *
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    public String buildPayUrlGet(Map<String, String> map) throws UnsupportedEncodingException {
        String p1_MerchantNo = map.get("p1_MerchantNo");
        String p2_OrderNo = map.get("p2_OrderNo");
        String p3_Amount = map.get("p3_Amount");
        String p4_Cur = map.get("p4_Cur");
        String p5_ProductName = map.get("p5_ProductName");
        String p6_Mp = map.get("p6_Mp");
        String p7_ReturnUrl = map.get("p7_ReturnUrl");
        String p8_NotifyUrl = map.get("p8_NotifyUrl");
        String p9_FrpCode = map.get("p9_FrpCode");
        String pa_OrderPeriod = map.get("pa_OrderPeriod");
        String pb_PayerLoginName = map.get("pb_PayerLoginName");

        StringBuilder url = new StringBuilder(Context.PAY_URL);
        url.append("?p1_MerchantNo=").append(p1_MerchantNo);
        url.append("&p2_OrderNo=").append(p2_OrderNo);
        url.append("&p3_Amount=").append(p3_Amount);
        url.append("&p4_Cur=").append(p4_Cur);
        url.append("&p5_ProductName=").append(URLEncoder.encode(p5_ProductName, "utf-8"));
        url.append("&p6_Mp=").append(URLEncoder.encode(p6_Mp, "utf-8"));
        url.append("&p7_ReturnUrl=").append(URLEncoder.encode(p7_ReturnUrl, "utf-8"));
        url.append("&p8_NotifyUrl=").append(URLEncoder.encode(p8_NotifyUrl, "utf-8"));
        url.append("&p9_FrpCode=").append(p9_FrpCode);
        url.append("&pa_OrderPeriod=").append(pa_OrderPeriod);
        url.append("&pb_PayerLoginName=").append(URLEncoder.encode(pb_PayerLoginName, "utf-8"));
        url.append("&hmac=").append(hmacRequest(map));
        return url.toString();
    }

    /**
     * 生成hmac
     *
     * @param map
     * @return
     */
    public String hmacRequest(Map<String, String> map) {
        String p1_MerchantNo = map.get("p1_MerchantNo");
        String p2_OrderNo = map.get("p2_OrderNo");
        String p3_Amount = map.get("p3_Amount");
        String p4_Cur = map.get("p4_Cur");
        String p5_ProductName = map.get("p5_ProductName");
        String p6_Mp = map.get("p6_Mp");
        String p7_ReturnUrl = map.get("p7_ReturnUrl");
        String p8_NotifyUrl = map.get("p8_NotifyUrl");
        String p9_FrpCode = map.get("p9_FrpCode");
        String pa_OrderPeriod = map.get("pa_OrderPeriod");
        String pb_PayerLoginName = map.get("pb_PayerLoginName");

        StringBuilder signStr = new StringBuilder();
        signStr.append("p1_MerchantNo=").append(p1_MerchantNo);
        signStr.append("&p2_OrderNo=").append(p2_OrderNo);
        signStr.append("&p3_Amount=").append(p3_Amount);
        signStr.append("&p4_Cur=").append(p4_Cur);
        signStr.append("&p5_ProductName=").append(p5_ProductName);
        signStr.append("&p6_Mp=").append(p6_Mp);
        signStr.append("&p7_ReturnUrl=").append(p7_ReturnUrl);
        signStr.append("&p8_NotifyUrl=").append(p8_NotifyUrl);
        signStr.append("&p9_FrpCode=").append(p9_FrpCode);
        signStr.append("&pa_OrderPeriod=").append(pa_OrderPeriod);
        signStr.append("&pb_PayerLoginName=").append(pb_PayerLoginName);
        return DigestUtils.md5Hex(signStr.toString() + merchantKey);
    }

    /**
     * 生成hmac
     *
     * @param map
     * @return
     */
    public String hmacResponse(Map<String, String> map) {
        String r1_MerchantNo = map.get("r1_MerchantNo");
        String r2_OrderNo = map.get("r2_OrderNo");
        String r3_Amount = map.get("r3_Amount");
        String r4_Cur = map.get("r4_Cur");
        String r5_Mp = map.get("r5_Mp");
        String r6_Status = map.get("r6_Status");
        String r7_TrxNo = map.get("r7_TrxNo");
        String r8_BankOrderNo = map.get("r8_BankOrderNo");
        String r9_BankTrxNo = map.get("r9_BankTrxNo");
        String ra_PayTime = map.get("ra_PayTime");
        String rb_DealTime = map.get("rb_DealTime");
        String rc_BankCode = map.get("rc_BankCode");

        StringBuilder signStr = new StringBuilder();
        signStr.append("r1_MerchantNo=").append(r1_MerchantNo);
        signStr.append("&r2_OrderNo=").append(r2_OrderNo);
        signStr.append("&r3_Amount=").append(r3_Amount);
        signStr.append("&r4_Cur=").append(r4_Cur);
        signStr.append("&r5_Mp=").append(r5_Mp);
        signStr.append("&r6_Status=").append(r6_Status);
        signStr.append("&r7_TrxNo=").append(r7_TrxNo);
        signStr.append("&r8_BankOrderNo=").append(r8_BankOrderNo);
        signStr.append("&r9_BankTrxNo=").append(r9_BankTrxNo);
        signStr.append("&ra_PayTime=").append(ra_PayTime);
        signStr.append("&rb_DealTime=").append(rb_DealTime);
        signStr.append("&rc_BankCode=").append(rc_BankCode);
        return DigestUtils.md5Hex(signStr.toString() + merchantKey);
    }

    /**
     * 验证签名
     *
     * @param map
     * @return
     */
    public Boolean verifyHmacRequest(Map<String, String> map) {
        String vhmac = hmacRequest(map).toUpperCase();
        String hmac = map.get("hmac").toUpperCase();
        return hmac.equals(vhmac);
    }

    /**
     * 验证签名
     *
     * @param map
     * @return
     */
    public Boolean verifyHmacResponse(Map<String, String> map) {
        String vhmac = hmacResponse(map).toUpperCase();
        String hmac = map.get("hmac").toUpperCase();
        return hmac.equals(vhmac);
    }

}
