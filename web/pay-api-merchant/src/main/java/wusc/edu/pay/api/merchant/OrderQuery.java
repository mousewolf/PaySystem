package wusc.edu.pay.api.merchant;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import wusc.edu.pay.common.utils.httpclient.Context;
import wusc.edu.pay.common.utils.httpclient.SimpleHttpParam;
import wusc.edu.pay.common.utils.httpclient.SimpleHttpResult;
import wusc.edu.pay.common.utils.httpclient.SimpleHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class OrderQuery {

    public String merchantNo = "";

    public String merchantKey = "";

    public OrderQuery(String merchantNo, String merchantKey) {
        this.merchantNo = merchantNo;
        this.merchantKey = merchantKey;
    }

    /**
     * 订单查询
     *
     * @param map
     * @return
     */
    public String doQuery(Map<String, String> map) {
        String p1_MerchantNo = map.get("p1_MerchantNo");
        String p2_OrderNo = map.get("p2_OrderNo");

        String param = "?p1_MerchantNo=" + p1_MerchantNo;
        param += "&p2_OrderNo=" + p2_OrderNo;
        param += "&hmac=" + hmacRequest(map);
        SimpleHttpParam httpParam = new SimpleHttpParam(Context.ORDERQUERY_URL + param);
        httpParam.setConnectTimeout(5000);
        httpParam.setMethod("POST");
        SimpleHttpResult response = SimpleHttpUtils.httpRequest(httpParam);

        if (response != null && response.getStatusCode() == 200) {
            return response.getContent();
        } else {
            Map<String, String> returnMap = new HashMap<>();
            returnMap.put("r1_MerchantNo", p1_MerchantNo);
            returnMap.put("r2_OrderNo", p2_OrderNo);
            returnMap.put("r3_Amount", "");
            returnMap.put("r4_ProductName", "");
            returnMap.put("r5_TrxNo", "");
            returnMap.put("ra_Status", "");
            returnMap.put("rb_Code", "101");
            returnMap.put("rc_CodeMsg", "请求服务器失败");
            returnMap.put("hmac", hmacResponse(returnMap));
            return JSONObject.toJSONString(returnMap);
        }
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

        StringBuilder signStr = new StringBuilder();
        signStr.append("p1_MerchantNo=").append(p1_MerchantNo);
        signStr.append("&p2_OrderNo=").append(p2_OrderNo);

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
        String r4_ProductName = map.get("r4_ProductName");
        String r5_TrxNo = map.get("r5_TrxNo");
        String ra_Status = map.get("ra_Status");
        String rb_Code = map.get("rb_Code");
        String rc_CodeMsg = map.get("rc_CodeMsg");

        StringBuilder signStr = new StringBuilder();
        signStr.append("r1_MerchantNo=").append(r1_MerchantNo);
        signStr.append("&r2_OrderNo=").append(r2_OrderNo);
        signStr.append("&r3_Amount=").append(r3_Amount);
        signStr.append("&r4_ProductName=").append(r4_ProductName);
        signStr.append("&r5_TrxNo=").append(r5_TrxNo);
        signStr.append("&ra_Status=").append(ra_Status);
        signStr.append("&rb_Code=").append(rb_Code);
        signStr.append("&rc_CodeMsg=").append(rc_CodeMsg);
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
