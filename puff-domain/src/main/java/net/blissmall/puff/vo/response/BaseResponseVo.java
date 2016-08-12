package net.blissmall.puff.vo.response;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/9 14:43
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class BaseResponseVo {

    public BaseResponseVo(String code, String msg, Object data, Object err) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.err = err;
    }

    public BaseResponseVo() {
        this.code = code;
    }

    private String code;
    private String msg;
    private Object data;
    private Object err;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }
}
