/**
 * 
 */
package com.Vshop.service.module.strategy;

import com.Vshop.service.module.strategy.common.StrategyCondition;


/**
 * <p>Title: IStrategy.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public interface IStrategy {
	
	/**
	 * 计算实际价格
	 * @param consumPrice 消费金额
	 * @param condition 条件
	 * @return
	 */
	public double realPrice(double consumPrice ,StrategyCondition condition);

}
