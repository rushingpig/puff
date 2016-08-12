package net.blissmall.puff.domain.user;

import net.blissmall.puff.common.utils.JSONUtils;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "app_user_auths")
public class AppUserAuths implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;

    @Column(name = "auth_id")
    private String authId;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "auth_expired")
    private String authExpired;

    @Column(name = "auth_type")
    private String authType;

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return auth_id
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * @return auth_token
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return auth_expired
     */
    public String getAuthExpired() {
        return authExpired;
    }

    /**
     * @param authExpired
     */
    public void setAuthExpired(String authExpired) {
        this.authExpired = authExpired;
    }

    /**
     * @return auth_type
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    @Override
    public String toString() {
        return JSONUtils.toJsonString(this);
    }

    public enum AuthType{
        QQ,WEIXIN,WEIBO,MOBILEPHONE;
    }
}