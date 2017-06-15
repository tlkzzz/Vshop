package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.StoreGrade;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;
/**
 * 店铺等级
 *    
 * 项目名称：Vshop-admin   
 * 类名称：StoreGradeMapper   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月12日 上午11:36:31   
 * 修改人：yanghui   
 * 修改时间：2014年11月12日 上午11:36:31   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface StoreGradeMapper{
	 
	public List<StoreGrade> queryStoreGradeList(Pager pager);
    public void save(StoreGrade grade);
    public void delete(Long id);
    public StoreGrade queryById(Long id);
    public void update(StoreGrade grade);

    /**
     * 校验查重
     * @param storeGrade
     * @return
     */
    public int queryCount(StoreGrade storeGrade);
}
