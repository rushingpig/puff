package net.blissmall.puff.domain.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "logs_user_login")
public class LogsUserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    private String uuid;

    /**
     * 用户名
     */
    @Column(name = "auth_id")
    private String authId;

    /**
     * 登录IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

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

    @Column(name = "created_time")
    private Date createdTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return uuid - 用户ID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置用户ID
     *
     * @param uuid 用户ID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户名
     *
     * @return auth_id - 用户名
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * 设置用户名
     *
     * @param authId 用户名
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    /**
     * 获取登录IP
     *
     * @return login_ip - 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
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
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}