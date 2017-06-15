package com.Vshop.core.entity.apibean;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebCodeApiBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4137751683850533632L;

	/**
	 * 分类id
	 */
	private Integer gcId;
	
	/**
     * 楼层标题
     */
    private String floorName;
    
    /**
     * 楼层副标题
     */
    private String subName;

	/**
	 * 楼层横幅
	 */
    private String bannerImg;

    /**
     * 楼层url
     */
    private String bannerUrl;

    /**
     * 楼层主图片
     */
    private String floorImg;

    /**
     * 楼层地址
     */
    private String floorUrl;

    /**
     * 楼层主题
     */
    private String floorColor;
    
    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 排序
     */
    private Integer sort;
    
}
