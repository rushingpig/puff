package net.blissmall.puff.vo.user;

import net.blissmall.puff.domain.user.AppUserAuths;
import net.blissmall.puff.domain.user.AppUserProfiles;

import java.io.Serializable;

/**
 * @Author : zhuzhenglin
 * @Date : 16/9/8 14:51
 * @Email : zhenglin.zhu@xfxb.net
 * @Since : v1.0
 */
public class UserInfoVo implements Serializable{

    private AppUserAuths appUserAuths;
    private AppUserProfiles appUserProfiles;

    public UserInfoVo(AppUserAuths appUserAuths, AppUserProfiles appUserProfiles) {
        this.appUserAuths = appUserAuths;
        this.appUserProfiles = appUserProfiles;
    }

    public AppUserAuths getAppUserAuths() {
        return appUserAuths;
    }

    public void setAppUserAuths(AppUserAuths appUserAuths) {
        this.appUserAuths = appUserAuths;
    }

    public AppUserProfiles getAppUserProfiles() {
        return appUserProfiles;
    }

    public void setAppUserProfiles(AppUserProfiles appUserProfiles) {
        this.appUserProfiles = appUserProfiles;
    }
}
