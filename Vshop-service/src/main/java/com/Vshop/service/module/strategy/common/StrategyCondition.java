/**
 * 
 */
package com.Vshop.service.module.strategy.common;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: Condition.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Data
@ToString
public class StrategyCondition {

	/**
	 * 开始值
	 */
	private double startValue;
	
	/**
	 *优惠值 
	 */
	private double promoteValue;
	
	/**
	 * 其他条件容器
	 */
	private Object Obj;
	
	/**
	 * 订单运费
	 */
	private double orderFreight;
}
