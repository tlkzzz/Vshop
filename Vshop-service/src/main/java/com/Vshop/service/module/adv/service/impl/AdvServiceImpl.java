/**
 * 
 */
package com.Vshop.service.module.adv.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Adv;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.service.module.adv.dao.AdvDao;
import com.Vshop.service.module.adv.service.AdvService;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Service
public class AdvServiceImpl implements AdvService {
	
	@Resource
	private AdvDao advDao;

	@Override
	public void save(Adv adv) {

		advDao.save(adv);
	}

	@Override
	public void update(Adv adv) {

		advDao.update(adv);
	}

	@Override
	public void delete(Integer id) {

		advDao.delete(id);
	}

	@Override
	public int findAdvCount(Adv adv) {
		return advDao.findAdvCount(adv);
	}

	@Override
	public List<Adv> findAllAdv(Adv adv) {
		return advDao.findAllAdv(adv);
	}

	@Override
	public List<Adv> findAdvPagerList(Pager pager) {
		return advDao.findAdvPagerList(pager);
	}

	@Override
	public Adv findAdvById(Integer id) {
		return advDao.findAdvById(id);
	}

	@Override
	public AdvPosition findByAdvPositionId(Integer apId) {
		
		return advDao.findByAdvPositionId(apId);
	}

}
