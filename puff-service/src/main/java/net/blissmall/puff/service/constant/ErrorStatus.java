package net.blissmall.puff.service.constant;

/**
 * restful返回的错误状态
 *
 * @Author : zhuzhenglin
 * @Date : 16/8/9 17:01
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public enum ErrorStatus {


//    EVERYTHIND_IS_OK("0000","everything goes well -> enjoy yourself (●'◡'●)ﾉ♥ "),
//    SERVER_ERROR("9999","泡芙服务开小差了,喝杯茶再试 ʅ（◞‸◟）ʃ "),
//    NOT_FOUND("404","迷路了,换条道吧 ✪ω✪"),
//    INVALID_PARAMS("9998","非法请求参数 ( ´◔ ‸◔`) ");


    EVERYTHIND_IS_OK("EVERYTHIND_IS_OK_CODE", "EVERYTHIND_IS_OK_MSG"),
    SERVER_ERROR("SERVER_ERROR_CODE", "SERVER_ERROR_MSG"),
    NOT_FOUND("NOT_FOUND_CODE", "NOT_FOUND_MSG"),
    INVALID_PARAMS("INVALID_PARAMS_CODE", "INVALID_PARAMS_MSG"),
    USERNAME_ALREADY_EXIST("USERNAME_ALREADY_EXIST_CODE", "USERNAME_ALREADY_EXIST_MSG"),
    SMS_VALIDATE_CODE_EXPIRE("SMS_VALIDATE_CODE_EXPIRE_CODE", "SMS_VALIDATE_CODE_EXPIRE_MSG"),
    ERROR_SMS_VALIDATE_CODE("ERROR_SMS_VALIDATE_CODE", "ERROR_SMS_VALIDATE_MSG"),
    USER_NOT_EXIST("USER_NOT_EXIST_CODE", "USER_NOT_EXIST_MSG"),
    ERROR_USERNAME_PASSWORD("ERROR_USERNAME_PASSWORD_CODE", "ERROR_USERNAME_PASSWORD_MSG"),
    RESET_PASSOWRD_FORBIDDEN("RESET_PASSOWRD_FORBIDDEN_CODE", "RESET_PASSOWRD_FORBIDDEN_MSG"),
    NO_VALID_UPDATED("NO_VALID_UPDATED_CODE", "NO_VALID_UPDATED_MSG"),
    OVER_DELIVERY_ADDRESS_LIMIT("OVER_DELIVERY_ADDRESS_LIMIT_CODE", "OVER_DELIVERY_ADDRESS_LIMIT_MSG"),
    NO_MORE_RESULT("NO_MORE_RESULT_CODE", "NO_MORE_RESULT_MSG"),
    NO_AUTHORIZED_REQUEST("NO_AUTHORIZED_REQUEST_CODE", "NO_AUTHORIZED_REQUEST_MSG"),
    FREQUENTLY_REQUEST_SMS("FREQUENTLY_REQUEST_SMS_CODE", "FREQUENTLY_REQUEST_SMS_MSG");

    private String code;
    private String msg;

    ErrorStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
