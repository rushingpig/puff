package net.blissmall.puff.service.exception;

import net.blissmall.puff.service.constant.ErrorStatus;

/**
 * 基本的业务异常类
 * <pre>
 *     统一业务异常类,便于在controller层捕获统一处理
 * </pre>
 * @Author : zhuzhenglin
 * @Date : 16/8/10 18:35
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class BussException extends RuntimeException {

    private ErrorStatus errorStatus;

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public BussException(String msg) {
        super(msg);
    }

    public BussException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BussException(ErrorStatus errorStatus){
        super(errorStatus.getMsg());
        this.errorStatus = errorStatus;
    }
}
