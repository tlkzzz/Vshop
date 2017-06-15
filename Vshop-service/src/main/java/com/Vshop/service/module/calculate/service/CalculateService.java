/**
 * 
 */
package com.Vshop.service.module.calculate.service;

import com.Vshop.service.module.strategy.common.StrategyCondition;


/**
 * <p>Title: CalculateService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月27日
 * @version 1.0
 */
public interface CalculateService {
	
	/**
	 * 计算价钱
	 * @param consumPrice
	 * @param condition
	 * @return
	 */
	public double Calculate(double consumPrice ,StrategyCondition condition) ;
	

}
