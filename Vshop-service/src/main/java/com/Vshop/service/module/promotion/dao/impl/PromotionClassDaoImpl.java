/**
 * 
 */
package com.Vshop.service.module.promotion.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.PromotionClass;
import com.Vshop.core.entity.vo.PromotionClassVo;
import com.Vshop.service.module.promotion.dao.PromotionClassDao;
import com.Vshop.service.module.promotion.dao.mapper.PromotionClassMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: PromotionClassDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Repository
public class PromotionClassDaoImpl implements PromotionClassDao {
	
	@Resource
	private PromotionClassMapper promotionClassMapper;

	@Override
	public void save(PromotionClass promotionClass) {
		promotionClassMapper.save(promotionClass);
		
	}

	@Override
	public void delete(Integer id) {
		promotionClassMapper.delete(id);
		
	}

	@Override
	public void update(PromotionClass promotionClass) {
		promotionClassMapper.update(promotionClass);
		
	}

	@Override
	public List<PromotionClass> findList(Pager pager) {
		List<PromotionClass> l = promotionClassMapper.findList(pager);
		System.out.println("222"+l);
		return l;
	}

	@Override
	public int findCount(Pager pager) {
		
		return promotionClassMapper.findCount(pager);
	}

	@Override
	public PromotionClass findById(Integer pcId) {
		return promotionClassMapper.findById(pcId);
	}

	@Override
	public PromotionClassVo findVoByUse() {
		
		return promotionClassMapper.findVoByUse();
	}

}
