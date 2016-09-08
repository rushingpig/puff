package net.blissmall.puff.domain.category;

import javax.persistence.*;
import java.util.Date;

@Table(name = "buss_product_category")
public class BussProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父级分类ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 产品分类名称
     */
    private String name;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 是否为附加商品，1表示是，0表示否
     */
    @Column(name = "isAddition")
    private Boolean isaddition;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新者
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

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
     * 获取父级分类ID
     *
     * @return parent_id - 父级分类ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级分类ID
     *
     * @param parentId 父级分类ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取产品分类名称
     *
     * @return name - 产品分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品分类名称
     *
     * @param name 产品分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取是否为附加商品，1表示是，0表示否
     *
     * @return isAddition - 是否为附加商品，1表示是，0表示否
     */
    public Boolean getIsaddition() {
        return isaddition;
    }

    /**
     * 设置是否为附加商品，1表示是，0表示否
     *
     * @param isaddition 是否为附加商品，1表示是，0表示否
     */
    public void setIsaddition(Boolean isaddition) {
        this.isaddition = isaddition;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
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
     * 获取更新者
     *
     * @return updated_by - 更新者
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新者
     *
     * @param updatedBy 更新者
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
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
}