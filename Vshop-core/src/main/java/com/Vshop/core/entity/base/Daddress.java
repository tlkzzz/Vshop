package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 发货地址
 * @author Administrator
 */
@Data
@ToString
public class Daddress  extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 4157379805967603322L;
    
    /**
     * 发货地址id
     */
    private Integer addressId;
    
    /**
     * 店铺id
     */
    private Integer storeId;
    
    /**
     * 联系人
     */
    private String sellerName;
    
    /**
     * 地区id
     */
    private Integer areaId;
    
    /**
     * 市级id
     */
    private Integer cityId;
    
    /**
     * 省级id
     */
    private Integer provinceId;
    
    /**
     * 地区内容
     */
    private String areaInfo;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 座机电话
     */
    private String telPhone;
    
    /**
     * 手机电话
     */
    private String mobPhone;
    
    /**
     * 公司
     */
    private String company;
    
    /**
     * 是否为默认
     */
    private String isDefault;
    
    /**
     * 邮编
     */
    private Integer zipCode;
    
    /**
     * 备注
     */
    private String content;
    
    private Integer supplierId;
    
}
