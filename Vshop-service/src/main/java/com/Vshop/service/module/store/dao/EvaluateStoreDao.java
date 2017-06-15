package com.Vshop.service.module.store.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * @项目名称：Vshop-seller   
 * @类名称：StoreBindClassDao  
 * @类描述：   
 * @创建人：sangyuchen   
 * @创建时间：2014年12月18日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
@Repository
public interface EvaluateStoreDao {

	public EvaluateStore findEvaluateStore(Integer id);
	
	/**
     * 分页列表
     * @param pager
     * @return
     */
    List<EvaluateStore> findlist(Pager pager);

    /**
     * 总条数
     * @param evaluateStore
     * @return
     */
    int findCount(EvaluateStore evaluateStore);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    
    /**
     * 保存
     * @param evaluateStore
     */
    public void save(EvaluateStore evaluateStore);
	
}