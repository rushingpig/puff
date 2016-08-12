package net.blissmall.puff.vo.sms;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求发送短信接口时的vo类
 * @Author : zhuzhenglin
 * @Date : 16/8/10 15:34
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class RequestForSmsVo implements Serializable{

    private String method = "verification.registry";
    private String phone;
    private Date timestamp = new Date();
    private Params params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    /**
     * 根据要发送短信的类型来获取相应的method,对应不同的短信模板
     * @param smsType
     * @return
     */
    public static String getRequestSmsMethod(SmsVo.SmsType smsType){
        switch (smsType){
            case REGISTRY:
                return "verification.registry";
            case QUIDK_LOGIN:
                return "verification.login";
            default:
                throw new IllegalArgumentException();
        }
    }

    public class Params{
        private String code;
        private String product = "幸福商城";

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }
    }
}
