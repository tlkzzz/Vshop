package com.Vshop.service.module.store.service.impl;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.service.module.store.dao.EvaluateStoreDao;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.store.util.Util;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * @项目名称：Vshop-seller   
 * @类名称：StoreBindClassServiceImpl   
 * @类描述：   
 * @创建人：shining   
 * @创建时间：2014年12月3日 上午11:56:42   
 * @修改人：shining   
 * @修改时间：2014年12月3日 上午11:56:42   
 * @修改备注：   
 * @version    
 *
 */
@Service
public class EvaluateStoreServiceImpl implements EvaluateStoreService {
	
	@Autowired
	private EvaluateStoreDao evaluateStoreDao;

	@Override
	public EvaluateStore findEvaluateStore(Integer id) {
		return evaluateStoreDao.findEvaluateStore(id);
	}

	@Override
	public List<EvaluateGoods> findPageList(Pager pager,
			EvaluateGoods evaluateGoods) {
		return null;
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(EvaluateGoods evaluateGoods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findCount(EvaluateStore evaluateStore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EvaluateStore> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * 根据店铺id查询店铺评分信息
     * @param storeId
     * @return
     */
    public EvaluateStore findEvaluateStoreByStoreId(Integer storeId){
    	EvaluateStore evaluateStore=new EvaluateStore();
		evaluateStore=evaluateStoreDao.findEvaluateStore(storeId);
		//保留一位小数
		NumberFormat numberFormat= NumberFormat.getNumberInstance() ; 
		numberFormat.setMaximumFractionDigits(1);
		if(evaluateStore!=null){
			//发货速度评分
			if(evaluateStore.getSevalDeliverycredit()!=null){
				evaluateStore.setSevalDeliverycredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDeliverycredit())));
			}else{
				evaluateStore.setSevalDeliverycredit(Double.valueOf("3.0"));
			}
			//描述相符评分
			if(evaluateStore.getSevalDesccredit()!=null){
				evaluateStore.setSevalDesccredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDesccredit())));
			}else{
				evaluateStore.setSevalDesccredit(Double.valueOf("3.0"));
			}
			//服务态度评分
			if(evaluateStore.getSevalServicecredit()!=null){
				evaluateStore.setSevalServicecredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalServicecredit())));
			}else{
				evaluateStore.setSevalServicecredit(Double.valueOf("3.0"));
			}
		}
		if(!evaluateStore.getCount().equals("0")){
			evaluateStore.setAverageCredit(Util.getAverageCreditFormat(evaluateStore));
		}
		return evaluateStore;
    }
}