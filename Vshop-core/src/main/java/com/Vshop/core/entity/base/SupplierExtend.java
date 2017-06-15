package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * @author llf
 * @Package com.Vshop.entity
 * @Description:
 * @date 2014/12/11 16:30
 */
@Data
@ToString
public class SupplierExtend extends BaseEntity implements Serializable{
	
	/**
	 * 店铺id
	 */
    private Integer supplierId;
    
    /**
     * 快递公司ID的组合
     */
    private String express;
}
