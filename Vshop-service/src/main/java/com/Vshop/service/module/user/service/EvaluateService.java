package com.Vshop.service.module.user.service;

import java.util.Map;

import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;

/**
 * @author llf
 * @Description:
 * @date 2015/3/16 14:15
 */
public interface  EvaluateService {

	public Map<String,Object> saveEvaluate(long orderSn, int recId,
	           EvaluateStore evaluateStore, EvaluateGoods evaluateGoods,
	           int memberId,String memberName,String specInfo);
	
	public Map<String,Object> saveEvaluate(long orderSn, int recId,
								           EvaluateStore evaluateStore, EvaluateGoods evaluateGoods,
								           int memberId,String memberName);
}
