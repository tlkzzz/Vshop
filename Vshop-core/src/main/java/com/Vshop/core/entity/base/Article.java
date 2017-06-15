package com.Vshop.core.entity.base;


import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.ArticleClass;

/**
 * Created by rabook on 2014/11/9.
 */
@Data
@ToString
public class Article extends BaseEntity implements  Serializable{

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 分类id
     */
    private Integer acId;

    /**
     * 跳转链接
     */
    private String articleUrl;

    /**
     * 是否显示，0为否，1为是，默认为1
     */
    private int articleShow;

    /**
     * 排序
     */
    private int articleSort;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 发布时间
     */
    private Long articleTime;

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

    private ArticleClass articleClass;

}
