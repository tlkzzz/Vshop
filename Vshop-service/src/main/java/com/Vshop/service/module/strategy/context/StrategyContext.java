/**
 * 
 */
package com.Vshop.service.module.strategy.context;

import com.Vshop.core.common.NumberUtils;
import com.Vshop.service.module.strategy.IStrategy;
import com.Vshop.service.module.strategy.common.StrategyCondition;

/**
 * <p>Title: StrategyContext.java</p>
 * <p>Description: 优惠策略上下文</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public class StrategyContext {
	
	private IStrategy strategy;
	
	
	public void setStrategy(IStrategy strategy){
		this.strategy  = strategy;
	}
	
	public double cul(double consumPrice , StrategyCondition condition ){
		
		double realPrice = this.strategy.realPrice(consumPrice, condition);
		realPrice = NumberUtils.round(realPrice, 2);//四舍五入 保留两位小数
		
		return realPrice;
	}

}
