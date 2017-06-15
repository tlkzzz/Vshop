/**
 * 
 */
package com.Vshop.service.module.strategy;

import com.Vshop.service.module.strategy.common.StrategyCondition;


/**
 * <p>Title: StrategyService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月23日
 * @version 1.0
 */
public interface StrategyService {
	
	/**
	 * 获取优惠后的实际价格
	 * @param strategyType
	 * @param consumPrice
	 * @param condition 优惠条件
	 * @return
	 */
	public double realPrice(Integer strategyType, double consumPrice ,StrategyCondition condition);
	

}
