/**
 * 
 */
package com.Vshop.service.module.adv.dao;

import java.util.List;

import com.Vshop.core.entity.base.Adv;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.core.entity.vo.AdvPositionVo;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvPositionDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
public interface AdvPositionDao {
	
	/**
	 * 保存
	 * @param advPosition
	 */
	public void save(AdvPosition advPosition);
	
	/**
	 * 更新
	 * @param advPosition
	 */
	public void update(AdvPosition advPosition);
	
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
	public int findAdvPositionCount(AdvPosition advPosition);
	
	/**
	 * 查询集合
	 * @param advPosition
	 * @return
	 */
	public List<AdvPosition> findAllAdvPosition(AdvPosition advPosition);
	
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	public List<AdvPosition> findAdvPositionPagerList(Pager pager);
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	public AdvPosition findAdvPositionById(Integer id);
	
	/**
	 * 根据advPosition 获取广告
	 * @param advPosition
	 * @return List<AdvPosition>
	 */
	public AdvPositionVo findAdvPositionVoList(AdvPosition advPosition);
	
	
	/**
	 * 根据advPosition 获取广告
	 * @param apId
	 * @return AdvPosition 
	 */
//	public AdvPositionVo findAdvPositionVoList(Integer apId);

}
