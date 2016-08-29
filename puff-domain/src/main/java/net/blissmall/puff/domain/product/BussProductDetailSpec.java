package net.blissmall.puff.domain.product;

import javax.persistence.*;
import java.util.Date;

@Table(name = "buss_product_detail_spec")
public class BussProductDetailSpec {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品详情id
     */
    @Column(name = "detail_id")
    private Integer detailId;

    /**
     * 说明key
     */
    @Column(name = "`key`")
    private String key;

    /**
     * 说明value
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 创建人id
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 记录更新操作者id
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 记录更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 软删除标志
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取产品详情id
     *
     * @return detail_id - 产品详情id
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * 设置产品详情id
     *
     * @param detailId 产品详情id
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * 获取说明key
     *
     * @return key - 说明key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置说明key
     *
     * @param key 说明key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取说明value
     *
     * @return value - 说明value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置说明value
     *
     * @param value 说明value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取创建人id
     *
     * @return created_by - 创建人id
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人id
     *
     * @param createdBy 创建人id
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
     * 获取记录更新操作者id
     *
     * @return updated_by - 记录更新操作者id
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置记录更新操作者id
     *
     * @param updatedBy 记录更新操作者id
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取记录更新时间
     *
     * @return updated_time - 记录更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置记录更新时间
     *
     * @param updatedTime 记录更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取软删除标志
     *
     * @return del_flag - 软删除标志
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置软删除标志
     *
     * @param delFlag 软删除标志
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}