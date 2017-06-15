package com.Vshop.core.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import com.Vshop.core.entity.MenuClass;

/**
 * Created by rabook on 2014/11/4.
 */
@Data
public class MenuClassVo implements Serializable{


    private static final long serialVersionUID = 979008948230666647L;

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
    /**
     *是否有子类
     */
    private Integer hasChild;
    
    /**
     * 深度
     */
    private Integer deep;
    /**
     * 是否选中
     */
    private Integer ischange;
    private List<MenuClass> povermenuClassList;
}
