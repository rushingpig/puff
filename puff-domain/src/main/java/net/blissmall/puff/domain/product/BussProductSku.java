package net.blissmall.puff.domain.product;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buss_product_sku")
public class BussProductSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * 规格
     */
    private String size;

    /**
     * 网站来源
     */
    private String website;

    /**
     * 行政区域ID
     */
    @Column(name = "regionalism_id")
    private Integer regionalismId;

    /**
     * 产品的价格（单位：分）
     */
    private Integer price;

    /**
     * 产品原价（单位：分）
     */
    @Column(name = "original_price")
    private Integer originalPrice;

    /**
     * 第一预约时间
     */
    @Column(name = "book_time")
    private Double bookTime;

    /**
     * 预售上架开始时间
     */
    @Column(name = "presell_start")
    private Date presellStart;

    /**
     * 预售上架结束时间
     */
    @Column(name = "presell_end")
    private Date presellEnd;

    /**
     * 预售发货开始时间
     */
    @Column(name = "send_start")
    private Date sendStart;

    /**
     * 预售发货结束时间
     */
    @Column(name = "send_end")
    private Date sendEnd;

    /**
     * 活动价格
     */
    @Column(name = "activity_price")
    private Integer activityPrice;

    /**
     * 活动开始时间
     */
    @Column(name = "activity_start")
    private Date activityStart;

    /**
     * 活动结束时间
     */
    @Column(name = "activity_end")
    private Date activityEnd;

    private Integer sort;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_time")
    private Date updatedTime;

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
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取规格
     *
     * @return size - 规格
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置规格
     *
     * @param size 规格
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 获取网站来源
     *
     * @return website - 网站来源
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置网站来源
     *
     * @param website 网站来源
     */
    public void setWebsite(String website) {
        this.website = website;
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
     * 获取产品的价格（单位：分）
     *
     * @return price - 产品的价格（单位：分）
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置产品的价格（单位：分）
     *
     * @param price 产品的价格（单位：分）
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取产品原价（单位：分）
     *
     * @return original_price - 产品原价（单位：分）
     */
    public Integer getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置产品原价（单位：分）
     *
     * @param originalPrice 产品原价（单位：分）
     */
    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 获取第一预约时间
     *
     * @return book_time - 第一预约时间
     */
    public Double getBookTime() {
        return bookTime;
    }

    /**
     * 设置第一预约时间
     *
     * @param bookTime 第一预约时间
     */
    public void setBookTime(Double bookTime) {
        this.bookTime = bookTime;
    }

    /**
     * 获取预售上架开始时间
     *
     * @return presell_start - 预售上架开始时间
     */
    public Date getPresellStart() {
        return presellStart;
    }

    /**
     * 设置预售上架开始时间
     *
     * @param presellStart 预售上架开始时间
     */
    public void setPresellStart(Date presellStart) {
        this.presellStart = presellStart;
    }

    /**
     * 获取预售上架结束时间
     *
     * @return presell_end - 预售上架结束时间
     */
    public Date getPresellEnd() {
        return presellEnd;
    }

    /**
     * 设置预售上架结束时间
     *
     * @param presellEnd 预售上架结束时间
     */
    public void setPresellEnd(Date presellEnd) {
        this.presellEnd = presellEnd;
    }

    /**
     * 获取预售发货开始时间
     *
     * @return send_start - 预售发货开始时间
     */
    public Date getSendStart() {
        return sendStart;
    }

    /**
     * 设置预售发货开始时间
     *
     * @param sendStart 预售发货开始时间
     */
    public void setSendStart(Date sendStart) {
        this.sendStart = sendStart;
    }

    /**
     * 获取预售发货结束时间
     *
     * @return send_end - 预售发货结束时间
     */
    public Date getSendEnd() {
        return sendEnd;
    }

    /**
     * 设置预售发货结束时间
     *
     * @param sendEnd 预售发货结束时间
     */
    public void setSendEnd(Date sendEnd) {
        this.sendEnd = sendEnd;
    }

    /**
     * 获取活动价格
     *
     * @return activity_price - 活动价格
     */
    public Integer getActivityPrice() {
        return activityPrice;
    }

    /**
     * 设置活动价格
     *
     * @param activityPrice 活动价格
     */
    public void setActivityPrice(Integer activityPrice) {
        this.activityPrice = activityPrice;
    }

    /**
     * 获取活动开始时间
     *
     * @return activity_start - 活动开始时间
     */
    public Date getActivityStart() {
        return activityStart;
    }

    /**
     * 设置活动开始时间
     *
     * @param activityStart 活动开始时间
     */
    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    /**
     * 获取活动结束时间
     *
     * @return activity_end - 活动结束时间
     */
    public Date getActivityEnd() {
        return activityEnd;
    }

    /**
     * 设置活动结束时间
     *
     * @param activityEnd 活动结束时间
     */
    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return created_by
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    /**
     * @return del_flag
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}