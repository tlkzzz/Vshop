/**
 * 
 */
package com.Vshop.service.module.strategy;

import java.util.Random;

import com.Vshop.service.module.strategy.common.StrategyCondition;
import com.Vshop.service.module.strategy.context.StrategyContext;
import com.Vshop.service.module.strategy.impl.PromotionalStrategy;
import com.Vshop.service.module.strategy.impl.RebateStrategy;
import com.Vshop.service.module.strategy.impl.ReduceStrategy;


/**
 * <p>Title: TestPromote.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月20日
 * @version 1.0
 */
public class TestPromote {
	
	
	public static void main(String args[]){
		
		StrategyContext context =  new StrategyContext();
		
		Random random = new Random();
		for(int i =0; i<10 ;i++){
			int x = random.nextInt(3);
//			System.out.println("x=="+x);
			double consumePrice = 0;
			while((consumePrice = random.nextInt(2000)) == 0){
			}
				switch (x) {
					case 0:
						context.setStrategy(new PromotionalStrategy());
						break ;
					case 1:
						context.setStrategy(new ReduceStrategy());
						break ;
					case 2:
						context.setStrategy(new RebateStrategy());
						break;
				}
			
			StrategyCondition condition = new StrategyCondition();
			switch (x) {
				case 0:
//					context.setStrateryContext(new PromotionalStratery());
					condition.setStartValue(1000);
					condition.setPromoteValue(10);
					break ;
				case 1:
//					context.setStrateryContext(new ReduceStratery());
					condition.setStartValue(1000);
					condition.setPromoteValue(100);
					break ;
				case 2:
//					context.setStrateryContext(new RebateStratery());
					condition.setStartValue(1000);
					condition.setPromoteValue(0.8);
					break;
			}
			System.out.print(x== 0 ? "满"+condition.getStartValue()+"免邮费"+condition.getPromoteValue() :
				(x==1 ? "满"+condition.getStartValue()+"减"+condition.getPromoteValue() :
					(x==2 ? "满"+condition.getStartValue()+"打折"+condition.getPromoteValue() : "")));
			System.out.println("  原价："+ consumePrice +  "优惠后的价格" + context.cul(consumePrice, condition));
		}
		
	}

}
