package net.blissmall.puff.core.validation.group.user;

import net.blissmall.puff.core.validation.group.FirstGroup;
import net.blissmall.puff.core.validation.group.SecondGroup;

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
