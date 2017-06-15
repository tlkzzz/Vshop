package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * @author llf
 * @Package com.Vshop.entity
 * @Description:
 * @date 2014/12/8 14:32
 */
@Data
@ToString
public class OffPayArea extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 196408823669167428L;

    /**
     * 商家ID
     */
    private Integer storeId;

    /**
     * 县ID组合
     */
    private String areaId;
}
