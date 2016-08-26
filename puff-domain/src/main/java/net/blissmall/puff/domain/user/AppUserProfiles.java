package net.blissmall.puff.domain.user;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_user_profiles")
public class AppUserProfiles {
    /**
     * 用户唯一ID标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uuid;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户手机号
     */
    @Column(name = "mobile_phone")
    private Integer mobilePhone;

    /**
     * 用户头像链接地址
     */
    private String avatar;

    /**
     * 用户最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 用户最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 性别(0:保密，1：男，2：女)
     */
    private String sex;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 省份ID
     */
    @Column(name = "province_id")
    private Integer provinceId;

    /**
     * 城市ID
     */
    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 行政区域ID
     */
    @Column(name = "regionalism_id")
    private Integer regionalismId;

    /**
     * 用户注册时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 用户个人信息所在地址
     */
    private String address;

    /**
     * 获取用户唯一ID标识
     *
     * @return uuid - 用户唯一ID标识
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置用户唯一ID标识
     *
     * @param uuid 用户唯一ID标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户手机号
     *
     * @return mobile_phone - 用户手机号
     */
    public Integer getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置用户手机号
     *
     * @param mobilePhone 用户手机号
     */
    public void setMobilePhone(Integer mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取用户头像链接地址
     *
     * @return avatar - 用户头像链接地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像链接地址
     *
     * @param avatar 用户头像链接地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取用户最后登录时间
     *
     * @return last_login_time - 用户最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置用户最后登录时间
     *
     * @param lastLoginTime 用户最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取用户最后登录IP
     *
     * @return last_login_ip - 用户最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置用户最后登录IP
     *
     * @param lastLoginIp 用户最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取性别(0:保密，1：男，2：女)
     *
     * @return sex - 性别(0:保密，1：男，2：女)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别(0:保密，1：男，2：女)
     *
     * @param sex 性别(0:保密，1：男，2：女)
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取用户生日
     *
     * @return birthday - 用户生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置用户生日
     *
     * @param birthday 用户生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取省份ID
     *
     * @return province_id - 省份ID
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省份ID
     *
     * @param provinceId 省份ID
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取城市ID
     *
     * @return city_id - 城市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市ID
     *
     * @param cityId 城市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取行政区域ID
     *
     * @return regionalism_id - 行政区域ID
     */
    public Integer getRegionalismId() {
        return regionalismId;
    }

    /**
     * 设置行政区域ID
     *
     * @param regionalismId 行政区域ID
     */
    public void setRegionalismId(Integer regionalismId) {
        this.regionalismId = regionalismId;
    }

    /**
     * 获取用户注册时间
     *
     * @return created_time - 用户注册时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置用户注册时间
     *
     * @param createdTime 用户注册时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取用户个人信息所在地址
     *
     * @return address - 用户个人信息所在地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置用户个人信息所在地址
     *
     * @param address 用户个人信息所在地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
}