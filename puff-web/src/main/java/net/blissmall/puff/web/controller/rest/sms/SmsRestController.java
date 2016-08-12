package net.blissmall.puff.web.controller.rest.sms;

import net.blissmall.puff.api.sms.MultiEnvSmsService;
import net.blissmall.puff.api.sms.SmsService;
import net.blissmall.puff.core.validation.group.SendSmsGroup;
import net.blissmall.puff.vo.response.BaseResponseVo;
import net.blissmall.puff.vo.sms.SmsVo;
import net.blissmall.puff.web.controller.BaseRestController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 14:14
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@RestController
@RequestMapping("/sms")
public class SmsRestController extends BaseRestController {

    @Resource
    private SmsService smsService;
    @Resource
    private MultiEnvSmsService multiEnvSmsService;

    @PostMapping("")
    public BaseResponseVo restSendSms(@Validated(SendSmsGroup.class) @RequestBody SmsVo smsVo, BindingResult bindingResult, HttpServletResponse response){
        if(multiEnvSmsService.sendSms(smsVo)){
            return ok();
        }else {
            return fail();
        }
    }
}
