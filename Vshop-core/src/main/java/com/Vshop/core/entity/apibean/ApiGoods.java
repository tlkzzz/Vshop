package com.Vshop.core.entity.apibean;

import lombok.Data;
import lombok.ToString;

/**
 *    
 * 项目名称：Vshop-entity   
 * 类名称：Goods   
 * 类描述：   
 * 创建人：cgl
 * 创建时间：2015年06月10日16:20:41
 * 修改人：cgl   
 * 修改时间：2015年06月10日16:20:41 
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class ApiGoods {
	/**
	 * 主键ID
	 */
	private Integer goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	 /**
	  * 商品默认封面图片
	  */
	 private String goodsImage;
	 
}
