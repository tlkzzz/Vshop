package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Goods;

/**
 * 商品交易
 *    
 * 项目名称：Vshop-front   
 * 类名称：CartVo   
 * 类描述：   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class GoodsTradeVo extends Goods implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6870926648086503120L;
	
	private String tradeGoodsCount;//交易商品数量
	
	private String tradeGoodsPrice;//交易商品价格
	
}
