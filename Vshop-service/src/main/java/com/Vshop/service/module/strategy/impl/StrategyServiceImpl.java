/**
 * 
 */
package com.Vshop.service.module.strategy.impl;

import org.springframework.stereotype.Service;

import com.Vshop.service.module.strategy.StrategyService;
import com.Vshop.service.module.strategy.common.StrategyCondition;
import com.Vshop.service.module.strategy.common.StrategyTypes;
import com.Vshop.service.module.strategy.context.StrategyContext;


/**
 * <p>Title: StrategyServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月23日
 * @version 1.0
 */
@Service
public class StrategyServiceImpl implements StrategyService {
	
	
	@Override
	public double realPrice(Integer strategyType, double consumPrice ,StrategyCondition condition) {
		
		StrategyContext context = new StrategyContext();//实例化促销上下文
			switch (strategyType) {
				case StrategyTypes.PROMOTIONAL_STRATEGY:
					context.setStrategy(new PromotionalStrategy());
					break ;
				case StrategyTypes.REDUCE_STRATEGY:
					context.setStrategy(new ReduceStrategy());
					break ;
				case StrategyTypes.REBATE_STRATEGY:
					context.setStrategy(new RebateStrategy());
					break;
					
//				case 3:
//					context.setStrategy(new Other());
//					break;
//					可以继续加其他的活动促销 或者组合的项目
				default :
					return consumPrice + condition.getOrderFreight();	
			}
		return context.cul(consumPrice, condition);
	}

}
