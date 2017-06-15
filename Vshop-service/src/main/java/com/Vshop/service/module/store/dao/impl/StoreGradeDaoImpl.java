package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.StoreGrade;
import com.Vshop.service.module.store.dao.StoreGradeDao;
import com.Vshop.service.module.store.dao.mapper.StoreGradeMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：StoreGradeDaoImpl   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月13日 下午3:23:55   
 * 修改人：yanghui   
 * 修改时间：2014年11月13日 下午3:23:55   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class StoreGradeDaoImpl implements StoreGradeDao {

    @Resource
    private StoreGradeMapper storeGradeMapper;

    @Override
    public List<StoreGrade> queryStoreGradeList(Pager pager) {
		// TODO Auto-generated method stub
		return storeGradeMapper.queryStoreGradeList(pager);
	}
    @Override
	public void save(StoreGrade grade){
    	storeGradeMapper.save(grade);
	}
	@Override
	public void delete(Long id){
		storeGradeMapper.delete(id);
	}
	@Override
	public StoreGrade queryById(Long id){
		return storeGradeMapper.queryById(id);
	}

	@Override
	public void update(StoreGrade grade) {
		storeGradeMapper.update(grade);
		
	}

    /**
     * 校验查重
     *
     * @param storeGrade
     * @return
     */
    @Override
    public int queryCount(StoreGrade storeGrade) {
        return storeGradeMapper.queryCount(storeGrade);
    }


}
