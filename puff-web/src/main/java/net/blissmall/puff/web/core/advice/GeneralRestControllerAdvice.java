package net.blissmall.puff.web.core.advice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 所有RestController的切面
 * @Author : pigo
 * @Date : 5/13/16 16:33
 * @E-mail : zhenglin.zhu@xfxb.net
 */
@ControllerAdvice(annotations = {Controller.class})
public class GeneralRestControllerAdvice {

}
