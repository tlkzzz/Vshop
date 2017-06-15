package com.Vshop.core.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArticleClassVo {
    private Integer acId;
    private String acCode;
    private String acName;
    private Integer acParentId;
    private Integer acSort;
    private Integer articleId;
    private String articleUrl;
    private String articleTitle;
    private String articleContent;
    private int acStatus;
    private int hasChildren;
    private List<ArticleClassVo> classVoList;
    private int deep;
}
