package com.Vshop.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  
 */
@Data
@ToString
public class MenuClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2601980222449814295L;

	
	

	/**
     * 索引id
     */
    private Integer mid;

    /**
     * 菜单名
     */
    private String mname;

    /**
     * 菜单路径
     */
    private String murl;

    /**
     * 父ID
     */
    private Integer mparentid;

    /**
     * 排序
     */
    private Integer msort;
    /**
     * 等级
     */
    private Integer mlevel;
    /**
     * 菜单路径
     */
    private String mpath;
    /**
     * 菜单描述
     */
    private String mdescription;
    
    private List<MenuClass> menuClassList;

}
