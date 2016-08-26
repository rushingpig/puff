package net.blissmall.puff.domain.region;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "dict_regionalism")
public class DictRegionalism implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "level_type")
    private Boolean levelType;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "merger_name")
    private String mergerName;

    private String lng;

    private String lat;

    private String pinying;

    /**
     * 是否激活（0：false，1：true）
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_time")
    private Date updatedTime;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return short_name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return level_type
     */
    public Boolean getLevelType() {
        return levelType;
    }

    /**
     * @param levelType
     */
    public void setLevelType(Boolean levelType) {
        this.levelType = levelType;
    }

    /**
     * @return city_code
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return zip_code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return merger_name
     */
    public String getMergerName() {
        return mergerName;
    }

    /**
     * @param mergerName
     */
    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    /**
     * @return lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * @return lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return pinying
     */
    public String getPinying() {
        return pinying;
    }

    /**
     * @param pinying
     */
    public void setPinying(String pinying) {
        this.pinying = pinying;
    }

    /**
     * 获取是否激活（0：false，1：true）
     *
     * @return del_flag - 是否激活（0：false，1：true）
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置是否激活（0：false，1：true）
     *
     * @param delFlag 是否激活（0：false，1：true）
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return updated_by
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
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
}