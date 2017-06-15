package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 会员等级
 * cgl
 * 2015年08月24日15:40:22
 */
@Data
@ToString
public class MemberGrade implements Serializable {
	
    private static final long serialVersionUID = -8426339414555997935L;
    
    /**
     * 索引id
     */
    public Integer gradeId;
    
    /**
     * 等级名称
     */
    public String gradeName;
    
    /**
     * 所需积分
     */
    public Integer integration;
    
    /**
     * 等级所对应的图片
     */
    public String gradeImg;
    
    /**
     * 优惠百分比
     */
    public Integer preferential;
    
    /**
     * 是否是默认的
     */
    public Integer isDefault;
}
