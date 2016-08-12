package net.blissmall.puff.service.constant;

import net.blissmall.puff.common.utils.PropertiesUtils;

/**
 * restful返回的错误状态
 * @Author : zhuzhenglin
 * @Date : 16/8/9 17:01
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class ErrorStatus {


//    EVERYTHIND_IS_OK("0000","everything goes well -> enjoy yourself (●'◡'●)ﾉ♥ "),
//    SERVER_ERROR("9999","泡芙服务开小差了,喝杯茶再试 ʅ（◞‸◟）ʃ "),
//    NOT_FOUND("404","迷路了,换条道吧 ✪ω✪"),
//    INVALID_PARAMS("9998","非法请求参数 ( ´◔ ‸◔`) ");

    public static ErrorStatus EVERYTHIND_IS_OK;
    public static ErrorStatus SERVER_ERROR;
    public static ErrorStatus NOT_FOUND;
    public static ErrorStatus INVALID_PARAMS;
    public static ErrorStatus USERNAME_ALREADY_EXIST;
    public static ErrorStatus SMS_VALIDATE_CODE_EXPIRE;
    public static ErrorStatus ERROR_SMS_VALIDATE_CODE;
    public static ErrorStatus USER_NOT_EXIST;
    public static ErrorStatus ERROR_USERNAME_PASSWORD;
    public static ErrorStatus RESET_PASSOWRD_FORBIDDEN;

    static {
        PropertiesUtils p = new PropertiesUtils("classpath:/properties/i18n/messages.properties");
        EVERYTHIND_IS_OK = new ErrorStatus(p.getProperty("EVERYTHIND_IS_OK_CODE"),p.getProperty("EVERYTHIND_IS_OK_MSG"));
        SERVER_ERROR = new ErrorStatus(p.getProperty("SERVER_ERROR_CODE"),p.getProperty("SERVER_ERROR_MSG"));
        NOT_FOUND = new ErrorStatus(p.getProperty("NOT_FOUND_CODE"),p.getProperty("NOT_FOUND_MSG"));
        INVALID_PARAMS = new ErrorStatus(p.getProperty("INVALID_PARAMS_CODE"),p.getProperty("INVALID_PARAMS_MSG"));
        USERNAME_ALREADY_EXIST = new ErrorStatus(p.getProperty("USERNAME_ALREADY_EXIST_CODE"),p.getProperty("USERNAME_ALREADY_EXIST_MSG"));
        SMS_VALIDATE_CODE_EXPIRE = new ErrorStatus(p.getProperty("SMS_VALIDATE_CODE_EXPIRE_CODE"),p.getProperty("SMS_VALIDATE_CODE_EXPIRE_MSG"));
        ERROR_SMS_VALIDATE_CODE = new ErrorStatus(p.getProperty("ERROR_SMS_VALIDATE_CODE"),p.getProperty("ERROR_SMS_VALIDATE_MSG"));
        USER_NOT_EXIST = new ErrorStatus(p.getProperty("USER_NOT_EXIST_CODE"),p.getProperty("USER_NOT_EXIST_MSG"));
        ERROR_USERNAME_PASSWORD = new ErrorStatus(p.getProperty("ERROR_USERNAME_PASSWORD_CODE"),p.getProperty("ERROR_USERNAME_PASSWORD_MSG"));
        RESET_PASSOWRD_FORBIDDEN = new ErrorStatus(p.getProperty("RESET_PASSOWRD_FORBIDDEN_CODE"),p.getProperty("RESET_PASSOWRD_FORBIDDEN_MSG"));

    }


    private String code;
    private String msg;

    private ErrorStatus(String code,String msg){
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
