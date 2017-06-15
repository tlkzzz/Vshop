package com.Vshop.service.module.website.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ArticleClassVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710515311807510784L;

	private int acId;

	private  String acCode;

    private String acName;

    private int acSort;

    private int acStatus;

    private int hasChildren;

    private List<ArticleClassVo> classVoList;



    private int deep;
}
