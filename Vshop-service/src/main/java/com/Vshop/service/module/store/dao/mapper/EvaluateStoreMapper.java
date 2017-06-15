package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：EvaluateStoreMapper
 * @类描述：
 * @创建人：sangyuchen
 * @创建时间：2014年12月18日 上午11:58:05
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface EvaluateStoreMapper {
   
	public EvaluateStore findEvaluateStore(@Param("id") Integer id);
	
	List<EvaluateStore> findPageList(Pager pager);

    int findCount(EvaluateStore evaluateStore);

    void delete(int id);
    
    /**
     * 保存店铺评论
     * @param evaluateStore
     */
    public void save(EvaluateStore evaluateStore);
	
}