package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;



/**
 * 店铺等级
 *    
 * 项目名称：Vshop-entity   
 * 类名称：StoreGrade   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月12日 上午11:24:32   
 * 修改人：yanghui   
 * 修改时间：2014年11月12日 上午11:24:32   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class StoreGrade extends BaseEntity{

	/**
	 * 主键ID
	 */
	private Integer sgId;

	/**
	 * 等级名称
	 */
	private String sgName;
	
	
	private Integer sgGoodsLimit;
	private Integer sgAlbumLimit;
	private Integer sgSpaceLimit;
	private Integer sgTemplateNumber;
	private String sgTemplate;
	private String sgPrice;
	private Integer sgConfirm;
	private String sgDescription;
	private String sgFunction;
	
	/**
	 * 排序
	 */
	private Integer sgSort;
	
	 /**
     * 0:未删除;1.已删除
     */
//    private Integer isDel;
//    /**
//     * 创建时间
//     */
//    private Long createdTime;
//    /**
//     * 更新时间
//     */
//    private Long updatedTime;
	
	
}
