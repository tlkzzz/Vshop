package com.Vshop.service.module.operation.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Sponsor;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface SponsorMapper {
	void save(Sponsor sponsor);
	
	void delete(int id);
	
	void update(Sponsor sponsor);
	
	Sponsor findById(long id);
	
	List<Sponsor> findPageList(Pager pager);
	
	List<Sponsor> findList();

	int countSponsor(Sponsor sponsor);
}
