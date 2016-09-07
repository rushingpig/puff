package net.blissmall.puff.validation.group.user;

import net.blissmall.puff.validation.group.FirstGroup;
import net.blissmall.puff.validation.group.SecondGroup;

import javax.validation.GroupSequence;

/**
 * @Author : zhuzhenglin
 * @Date : 16/8/10 00:27
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
@GroupSequence({FirstGroup.class,SecondGroup.class,RegistryGroup.class})
public interface RegistrySequeceGroup {
}
