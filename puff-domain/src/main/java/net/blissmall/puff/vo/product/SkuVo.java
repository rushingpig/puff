package net.blissmall.puff.vo.product;

import java.io.Serializable;

public class SkuVo implements Serializable{

    private Integer skuId;
    private Integer amount;

    public SkuVo() {
    }

    public SkuVo(Integer skuId, Integer amount) {
        this.skuId = skuId;
        this.amount = amount;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SkuVo) && this.skuId == ((SkuVo)obj).skuId;
    }
}
