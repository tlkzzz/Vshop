/**
 * 
 */
package com.Vshop.service.module.promotion.dao;

import java.util.List;

import com.Vshop.core.entity.base.Promotion;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: PromotionDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public interface PromotionDao {
	
	/**
	 * 保存
	 * @param promotion
	 */
	public void save(Promotion promotion);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 更新
	 * @param promotion
	 */
	public void update(Promotion promotion);
	
	/**
	 * 获取列表
	 * @param pager
	 * @return
	 */
	public List<Promotion> findList(Pager pager);
	
	/**
	 * 获取总条数
	 * @param promotion
	 * @return
	 */
	public int findCount(Promotion promotion);
	
	/**
	 * 单条纪录
	 * @param pcId
	 * @return
	 */
	public Promotion findById(Integer pcId);

}
