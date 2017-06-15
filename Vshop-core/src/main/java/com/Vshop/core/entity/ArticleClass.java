package com.Vshop.core.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * Created by ss on 2014/11/4.
 */
@Data
@ToString
public class ArticleClass extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 4723947788270319576L;

    /**
     * 索引id
     */
    private Integer acId;

    /**
     * 分类标识码
     */
    private String acCode;

    /**
     * 分类名称
     */
    private String acName;

    /**
     * 父ID
     */
    private int acParentId;

    /**
     * 排序
     */
    private int acSort;

    /**
     * 是否启用0:启用;1:停用
     */
    private int acStatus;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    //private Long createdTime;
    /**
     * 更新时间
     */
    //private Long updatedTime;

    private List<ArticleClass> articleClassList;

}
