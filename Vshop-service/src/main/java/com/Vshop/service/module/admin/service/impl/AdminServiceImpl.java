package com.Vshop.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Admin;
import com.Vshop.core.entity.vo.AdminVo;
import com.Vshop.service.module.admin.dao.AdminDao;
import com.Vshop.service.module.admin.service.AdminService;
import com.Vshop.service.utils.page.Pager;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminDao adminDao;
	
	public Admin findByAdminName(String adminName){

		return adminDao.findByAdminName(adminName);
	}

	public int findAdminCount(Admin admin) {
		return adminDao.findAdminCount(admin);
	}

	public List<AdminVo> findAdminList(Pager pager) {
		return adminDao.findAdminList(pager);
	}


	@Override
	public void update(Admin admin) {
		adminDao.update(admin);
	}
	
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.save(admin);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		adminDao.delete(id);
	}

	@Override
	public Admin findAdminById(Integer id) {
		// TODO Auto-generated method stub
		return adminDao.findAdminById(id);
	}

}
