package com.Vshop.service.module.operation.service;

import java.util.List;

import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.service.utils.page.Pager;

public interface SponsorService {
	
	void save(Sponsor sponsor);
	
	void delete(int id);
	
	void update(Sponsor sponsor);
	
	Sponsor findById(long id);
	
	List<Sponsor> findPageList(Pager pager);
	
	List<Sponsor> findList();

	int countSponsor(Sponsor sponsor);
}
