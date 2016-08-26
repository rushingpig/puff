package net.blissmall.puff.domain.product;

import java.util.Date;
import javax.persistence.*;

@Table(name = "buss_product_detail")
public class BussProductDetail {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 区域id
     */
    @Column(name = "regionalism_id")
    private Integer regionalismId;

    /**
     * 列表页图片
     */
    @Column(name = "list_img")
    private String listImg;

    /**
     * 列表页文案
     */
    @Column(name = "list_copy")
    private String listCopy;

    /**
     * 详情页顶部商品描述
     */
    @Column(name = "detail_top_copy")
    private String detailTopCopy;

    /**
     * 详情页模板上方的包装文案
     */
    @Column(name = "detail_template_copy")
    private String detailTemplateCopy;

    /**
     * 详情页模板上方的结束标题
     */
    @Column(name = "detail_template_copy_end")
    private String detailTemplateCopyEnd;

    /**
     * 缩略展示图1
     */
    @Column(name = "detail_img_1")
    private String detailImg1;

    /**
     * 缩略展示图2
     */
    @Column(name = "detail_img_2")
    private String detailImg2;

    /**
     * 缩略展示图3
     */
    @Column(name = "detail_img_3")
    private String detailImg3;

    /**
     * 缩略展示图4
     */
    @Column(name = "detail_img_4")
    private String detailImg4;

    /**
     * 是否全部一致
     */
    private Integer consistency;

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
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取区域id
     *
     * @return regionalism_id - 区域id
     */
    public Integer getRegionalismId() {
        return regionalismId;
    }

    /**
     * 设置区域id
     *
     * @param regionalismId 区域id
     */
    public void setRegionalismId(Integer regionalismId) {
        this.regionalismId = regionalismId;
    }

    /**
     * 获取列表页图片
     *
     * @return list_img - 列表页图片
     */
    public String getListImg() {
        return listImg;
    }

    /**
     * 设置列表页图片
     *
     * @param listImg 列表页图片
     */
    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    /**
     * 获取列表页文案
     *
     * @return list_copy - 列表页文案
     */
    public String getListCopy() {
        return listCopy;
    }

    /**
     * 设置列表页文案
     *
     * @param listCopy 列表页文案
     */
    public void setListCopy(String listCopy) {
        this.listCopy = listCopy;
    }

    /**
     * 获取详情页顶部商品描述
     *
     * @return detail_top_copy - 详情页顶部商品描述
     */
    public String getDetailTopCopy() {
        return detailTopCopy;
    }

    /**
     * 设置详情页顶部商品描述
     *
     * @param detailTopCopy 详情页顶部商品描述
     */
    public void setDetailTopCopy(String detailTopCopy) {
        this.detailTopCopy = detailTopCopy;
    }

    /**
     * 获取详情页模板上方的包装文案
     *
     * @return detail_template_copy - 详情页模板上方的包装文案
     */
    public String getDetailTemplateCopy() {
        return detailTemplateCopy;
    }

    /**
     * 设置详情页模板上方的包装文案
     *
     * @param detailTemplateCopy 详情页模板上方的包装文案
     */
    public void setDetailTemplateCopy(String detailTemplateCopy) {
        this.detailTemplateCopy = detailTemplateCopy;
    }

    /**
     * 获取详情页模板上方的结束标题
     *
     * @return detail_template_copy_end - 详情页模板上方的结束标题
     */
    public String getDetailTemplateCopyEnd() {
        return detailTemplateCopyEnd;
    }

    /**
     * 设置详情页模板上方的结束标题
     *
     * @param detailTemplateCopyEnd 详情页模板上方的结束标题
     */
    public void setDetailTemplateCopyEnd(String detailTemplateCopyEnd) {
        this.detailTemplateCopyEnd = detailTemplateCopyEnd;
    }

    /**
     * 获取缩略展示图1
     *
     * @return detail_img_1 - 缩略展示图1
     */
    public String getDetailImg1() {
        return detailImg1;
    }

    /**
     * 设置缩略展示图1
     *
     * @param detailImg1 缩略展示图1
     */
    public void setDetailImg1(String detailImg1) {
        this.detailImg1 = detailImg1;
    }

    /**
     * 获取缩略展示图2
     *
     * @return detail_img_2 - 缩略展示图2
     */
    public String getDetailImg2() {
        return detailImg2;
    }

    /**
     * 设置缩略展示图2
     *
     * @param detailImg2 缩略展示图2
     */
    public void setDetailImg2(String detailImg2) {
        this.detailImg2 = detailImg2;
    }

    /**
     * 获取缩略展示图3
     *
     * @return detail_img_3 - 缩略展示图3
     */
    public String getDetailImg3() {
        return detailImg3;
    }

    /**
     * 设置缩略展示图3
     *
     * @param detailImg3 缩略展示图3
     */
    public void setDetailImg3(String detailImg3) {
        this.detailImg3 = detailImg3;
    }

    /**
     * 获取缩略展示图4
     *
     * @return detail_img_4 - 缩略展示图4
     */
    public String getDetailImg4() {
        return detailImg4;
    }

    /**
     * 设置缩略展示图4
     *
     * @param detailImg4 缩略展示图4
     */
    public void setDetailImg4(String detailImg4) {
        this.detailImg4 = detailImg4;
    }

    /**
     * 获取是否全部一致
     *
     * @return consistency - 是否全部一致
     */
    public Integer getConsistency() {
        return consistency;
    }

    /**
     * 设置是否全部一致
     *
     * @param consistency 是否全部一致
     */
    public void setConsistency(Integer consistency) {
        this.consistency = consistency;
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