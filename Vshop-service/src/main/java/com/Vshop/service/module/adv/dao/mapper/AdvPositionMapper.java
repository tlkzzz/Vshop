/**
 * 
 */
package com.Vshop.service.module.adv.dao.mapper;

import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.core.entity.vo.AdvPositionVo;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvPostionMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@SqlMapper
public interface AdvPositionMapper {

	/**
	 * 保存
	 * @param advPosition
	 */
	 void save(AdvPosition advPosition);
	
	/**
	 * 更新
	 * @param advPosition
	 */
	 void update(AdvPosition advPosition);
	
	/**
	 * 删除
	 * @param id
	 */
	 void delete(Integer id);
	
	/**
	 * 总条数
	 * @param pager
	 * @return int
	 */
	 int findAdvPositionCount(AdvPosition advPosition);
	
	/**
	 * 查询集合
	 * @param advPosition
	 * @return
	 */
	 List<AdvPosition> findAllAdvPosition(AdvPosition advPosition);
	
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	 List<AdvPosition> findAdvPositionPagerList(Pager pager);
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	 AdvPosition findAdvPositionById(Integer id);
	 
	 /**
	 * 根据advPosition 获取广告
	 * @param advPosition
	 * @return AdvPositionVo 
	 */
	public AdvPositionVo findAdvPositionVoList(AdvPosition advPosition);
	
	
	/**
	 * 根据apkey 获取广告
	 * @param apkey
	 * @return AdvPositionVo 
	 */
//	public AdvPositionVo findAdvPositionVoByKey(String apkey);
	
//	/**
//	 * 根据advPosition 获取广告
//	 * @param apId
//	 * @return AdvPositionVo
//	 */
//	public AdvPositionVo findAdvPositionVoList(Integer apId);
	
}
