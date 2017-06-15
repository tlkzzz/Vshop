/**
 * 
 */
package com.Vshop.service.module.strategy.impl;

import com.Vshop.service.module.strategy.IStrategy;
import com.Vshop.service.module.strategy.common.StrategyCondition;


/**
 * <p>Title: RebateStrategy.java</p>
 * <p>Description:满xxxx打xx折 </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public class RebateStrategy implements IStrategy {

	@Override
	public double realPrice(double consumPrice, StrategyCondition condition) {
		if(consumPrice > condition.getStartValue() && condition.getPromoteValue() != 0){
			return consumPrice * condition.getPromoteValue() + condition.getOrderFreight();
		}else{
			return consumPrice + condition.getOrderFreight();
		}
	}


}
