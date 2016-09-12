package net.blissmall.puff.domain.user;

import javax.persistence.*;
import java.util.Date;

@Table(name = "app_user_favorites")
public class AppUserFavorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    private String uuid;

    /**
     * 收藏的对应的产品ID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 删除标志
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    /**
     * 产品名
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品SKU所对应的价格（单位：分）
     */
    @Column(name = "product_sku_price")
    private Integer productSkuPrice;

    /**
     * 产品SKU所对应的规格
     */
    @Column(name = "product_sku_size")
    private String productSkuSize;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Transient
    private Integer pageNo;

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
     * 获取收藏的对应的产品ID
     *
     * @return sku_id - 收藏的对应的产品ID
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置收藏的对应的产品ID
     *
     * @param skuId 收藏的对应的产品ID
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取删除标志
     *
     * @return del_flag - 删除标志
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标志
     *
     * @param delFlag 删除标志
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取产品名
     *
     * @return product_name - 产品名
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名
     *
     * @param productName 产品名
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取产品SKU所对应的价格（单位：分）
     *
     * @return product_sku_price - 产品SKU所对应的价格（单位：分）
     */
    public Integer getProductSkuPrice() {
        return productSkuPrice;
    }

    /**
     * 设置产品SKU所对应的价格（单位：分）
     *
     * @param productSkuPrice 产品SKU所对应的价格（单位：分）
     */
    public void setProductSkuPrice(Integer productSkuPrice) {
        this.productSkuPrice = productSkuPrice;
    }

    /**
     * 获取产品SKU所对应的规格
     *
     * @return product_sku_size - 产品SKU所对应的规格
     */
    public String getProductSkuSize() {
        return productSkuSize;
    }

    /**
     * 设置产品SKU所对应的规格
     *
     * @param productSkuSize 产品SKU所对应的规格
     */
    public void setProductSkuSize(String productSkuSize) {
        this.productSkuSize = productSkuSize;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Integer getPageNo() {
        if(pageNo == null){
            pageNo = 0;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}