/**
 * 
 */
package com.Vshop.service.module.adv.dao;

import java.util.List;

import com.Vshop.core.entity.base.Adv;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
public interface AdvDao {
	
	/**
	 * 保存
	 * @param adv
	 */
	public void save(Adv adv);
	
	/**
	 * 更新
	 * @param adv
	 */
	public void update(Adv adv);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 总条数
	 * @param pager
	 * @return int
	 */
	public int findAdvCount(Adv adv);
	
	/**
	 * 查询集合
	 * @param adv
	 * @return
	 */
	public List<Adv> findAllAdv(Adv adv);
	
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	public List<Adv> findAdvPagerList(Pager pager);
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	public Adv findAdvById(Integer id);
	
	/**
	 * 根据apId 查询
	 * @param apId
	 * @return
	 */
	public AdvPosition findByAdvPositionId(Integer apId);

}
