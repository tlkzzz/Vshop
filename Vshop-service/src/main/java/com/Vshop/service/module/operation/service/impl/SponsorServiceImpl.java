package com.Vshop.service.module.operation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.service.module.operation.dao.SponsorDao;
import com.Vshop.service.module.operation.service.SponsorService;
import com.Vshop.service.utils.page.Pager;

@Service
public class SponsorServiceImpl implements SponsorService {

	@Autowired
	private SponsorDao sponsorDao;
	
	@Override
	public void save(Sponsor sponsor) {
		sponsorDao.save(sponsor);
	}

	@Override
	public void delete(int id) {
		sponsorDao.delete(id);
	}

	@Override
	public void update(Sponsor sponsor) {
		sponsorDao.update(sponsor);
	}

	@Override
	public Sponsor findById(long id) {
		return sponsorDao.findById(id);
	}

	@Override
	public List<Sponsor> findPageList(Pager pager) {
		return sponsorDao.findPageList(pager);
	}

	@Override
	public List<Sponsor> findList() {
		return sponsorDao.findList();
	}

	@Override
	public int countSponsor(Sponsor sponsor) {
		return sponsorDao.countSponsor(sponsor);
	}

}
