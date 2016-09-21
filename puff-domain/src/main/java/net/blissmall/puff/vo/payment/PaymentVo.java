package net.blissmall.puff.vo.payment;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 订单支付时的vo类
 * @Author : zhuzhenglin
 * @Date : 16/9/20 15:52
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class PaymentVo implements Serializable {

    @NotBlank(message = "{NotBlank.PaymentVo.channel}")
    private String channel;
    @NotNull(message = "{NotNull.PaymentVo.amount}")
    private Integer amount;
    private String body;
    private String client_ip;
    private Map<String,Object> extra;
    private String order_no;
    private String subject;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
