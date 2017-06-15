package com.Vshop.core.entity.base;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

/**
 * 退货商品表
 * @author liukai
 */
@Data
@ToString
public class ReturnGoods {
	
	//退货商品记录ID
	private Integer logId;
	
	//退货记录ID
	private Integer returnId;
	
	//订单ID
	private Integer orderId;
	
	//商品ID
	private Integer goodsId;
	
	//商品名称
	private String goodsName;
	
	//规格ID
	private Integer specId;
	
	//规格描述
	private String specInfo;
	
	//商品价格
	private BigDecimal goodsPrice;
	
	//商品数量
	private Integer goodsNum;
	
	//退货数量
	private Integer goodsReturnNum;
	
	//商品图片
	private String goodsImage;
}
