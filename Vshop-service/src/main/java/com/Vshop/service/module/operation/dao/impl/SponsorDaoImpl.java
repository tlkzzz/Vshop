package com.Vshop.service.module.operation.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.service.module.operation.dao.SponsorDao;
import com.Vshop.service.module.operation.dao.mapper.SponsorMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class SponsorDaoImpl implements SponsorDao {
	@Autowired
	private SponsorMapper sponsorMapper;
	
	@Override
	public void save(Sponsor sponsor) {
		sponsor.setCreateTime(new Date());
		sponsorMapper.save(sponsor);
	}

	@Override
	public void delete(int id) {
		sponsorMapper.delete(id);
	}

	@Override
	public void update(Sponsor sponsor) {
		sponsor.setUpdateTime(new Date());
		sponsorMapper.update(sponsor);
	}

	@Override
	public Sponsor findById(long id) {
		return sponsorMapper.findById(id);
	}

	@Override
	public List<Sponsor> findPageList(Pager pager) {
		return sponsorMapper.findPageList(pager);
	}

	@Override
	public List<Sponsor> findList() {
		return sponsorMapper.findList();
	}

	@Override
	public int countSponsor(Sponsor sponsor) {
		return sponsorMapper.countSponsor(sponsor);
	}

}
