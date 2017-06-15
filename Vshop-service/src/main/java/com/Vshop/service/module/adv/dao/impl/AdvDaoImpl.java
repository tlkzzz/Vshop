/**
 * 
 */
package com.Vshop.service.module.adv.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Adv;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.service.module.adv.dao.AdvDao;
import com.Vshop.service.module.adv.dao.mapper.AdvMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Repository
public class AdvDaoImpl implements AdvDao {
	
	@Resource
	private AdvMapper advMapper;

	@Override
	public void save(Adv adv) {
		advMapper.save(adv);		
	}

	@Override
	public void update(Adv adv) {

		advMapper.update(adv);
	}

	@Override
	public void delete(Integer id) {

		advMapper.delete(id);
	}

	@Override
	public int findAdvCount(Adv adv) {
		
		return advMapper.findAdvCount(adv);
	}

	@Override
	public List<Adv> findAllAdv(Adv adv) {
		 
		return advMapper.findAllAdv(adv);
	}

	@Override
	public List<Adv> findAdvPagerList(Pager pager) {
		return advMapper.findAdvPagerList(pager);
	}

	@Override
	public Adv findAdvById(Integer id) {
		
		return advMapper.findAdvById(id);
	}

	@Override
	public AdvPosition findByAdvPositionId(Integer apId) {
		
		return advMapper.findByAdvPositionId(apId);
	}

}
