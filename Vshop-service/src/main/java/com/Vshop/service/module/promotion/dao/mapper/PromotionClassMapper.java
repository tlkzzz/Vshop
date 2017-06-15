/**
 * 
 */
package com.Vshop.service.module.promotion.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.PromotionClass;
import com.Vshop.core.entity.vo.PromotionClassVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: PromotionClassMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@SqlMapper
public interface PromotionClassMapper {
	
	/**
	 * 保存
	 * @param promotionClass
	 */
	public void save(PromotionClass promotionClass);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 更新
	 * @param promotionClass
	 */
	public void update(PromotionClass promotionClass);
	
	/**
	 * 获取列表
	 * @param pager
	 * @return
	 */
	public List<PromotionClass> findList(Pager pager);
	
	/**
	 * 获取总条数
	 * @param pager
	 * @return
	 */
	public int findCount(Pager pager);
	
	/**
	 * 获取单条数据
	 * @param pcId
	 * @return
	 */
	public PromotionClass findById(Integer pcId);
	
	/**
	 * 获取优惠超类集合
	 * @return
	 */
	public PromotionClassVo findVoByUse();

}
