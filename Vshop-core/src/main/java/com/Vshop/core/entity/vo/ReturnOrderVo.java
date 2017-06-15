package com.Vshop.core.entity.vo;

import java.util.List;

import lombok.Data;

import com.Vshop.core.entity.base.ReturnGoods;
import com.Vshop.core.entity.base.ReturnOrder;

/**
 * 退货表超类 
 * @author liukai
 */
@Data
public class ReturnOrderVo extends ReturnOrder{
	
	//退货商品集合
	private List<ReturnGoods> returnGoodsList;
}
