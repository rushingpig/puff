package net.blissmall.puff.domain.user;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_user_delivery_address")
public class AppUserDeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @NotBlank(message = "{NotBlank.AppUserDeliveryAddress.uuid}")
    @Column(name = "uuid")
    private String uuid;

    /**
     * 收货人姓名
     */
    @NotBlank(message = "{NotBlank.AppUserDeliveryAddress.receipientName}")
    @Column(name = "receipient_name")
    private String receipientName;

    /**
     * 收货人手机
     */
    @Column(name = "receipient_mobile")
    private String receipientMobile;

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
     * 详细地址
     */
    private String address;

    /**
     * 是否设为默认地址 0：否    1：是
     */
    @Column(name = "is_default")
    private Integer isDefault;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 0：无效   1：有效
     */
    @Column(name = "del_flag")
    private Boolean delFlag;



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
     * 获取收货人姓名
     *
     * @return receipient_name - 收货人姓名
     */
    public String getReceipientName() {
        return receipientName;
    }

    /**
     * 设置收货人姓名
     *
     * @param receipientName 收货人姓名
     */
    public void setReceipientName(String receipientName) {
        this.receipientName = receipientName;
    }

    /**
     * 获取收货人手机
     *
     * @return receipient_mobile - 收货人手机
     */
    public String getReceipientMobile() {
        return receipientMobile;
    }

    /**
     * 设置收货人手机
     *
     * @param receipientMobile 收货人手机
     */
    public void setReceipientMobile(String receipientMobile) {
        this.receipientMobile = receipientMobile;
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
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取是否设为默认地址 0：否    1：是
     *
     * @return is_default - 是否设为默认地址 0：否    1：是
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否设为默认地址 0：否    1：是
     *
     * @param isDefault 是否设为默认地址 0：否    1：是
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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

    /**
     * @return updated_time
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * @param updatedTime
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取0：无效   1：有效
     *
     * @return del_flag - 0：无效   1：有效
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置0：无效   1：有效
     *
     * @param delFlag 0：无效   1：有效
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

}