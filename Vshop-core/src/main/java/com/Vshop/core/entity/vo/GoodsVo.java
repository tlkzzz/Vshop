package com.Vshop.core.entity.vo;

import java.math.BigDecimal;


/**
 * Created by rabook on 2015/3/27.
 */

public class GoodsVo implements java.io.Serializable{

    private static final long serialVersionUID = -565846846274255933L;

    private Integer goodsId;
    private String goodsName;
    private Double goodsPrice;
    private Short goodsNum;
    private String goodsImages;
    private int storeId;
    private String storeName;
    private BigDecimal goodsFreight;
    private Integer specId;

	@Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object instanceof GoodsVo) {
            GoodsVo goodsVo = (GoodsVo)object;
            if(this.goodsId == goodsVo.goodsId){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.goodsId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Short getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Short goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

	public BigDecimal getGoodsFreight() {
		return goodsFreight;
	}

	public void setGoodsFreight(BigDecimal goodsFreight) {
		this.goodsFreight = goodsFreight;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
    
}
