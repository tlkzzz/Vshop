package com.Vshop.core.entity.apibean;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author llf
 * @Description:
 * @date 2014/12/16 9:47
 */
@Data
@ToString
public class ApiFloorVo implements Serializable{

    
	private static final long serialVersionUID = -864221852629659918L;

	/**
     * 分类id
     */
    private Integer gcId;

    /**
     * 楼层导航图片链接
     */
    private String bannerUrl;

    /**
     * 楼层名称
     */
    private String floorName;

    /**
     * 楼层类型
     */
    private String floorType;

    /**
     * 商品
     */
    private List<ApiGoods> goodsList;

}
