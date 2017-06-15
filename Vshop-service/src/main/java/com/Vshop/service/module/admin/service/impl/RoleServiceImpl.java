package com.Vshop.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Roles;
import com.Vshop.service.module.admin.dao.RoleDao;
import com.Vshop.service.module.admin.service.RoleService;
import com.Vshop.service.utils.page.Pager;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private RoleDao roleDao;

	@Override
	public Integer countShopRole(Roles shopRole) {
		// TODO Auto-generated method stub
		return roleDao.countShopRole(shopRole);
	}

	@Override
	public List<Roles> findShopRoleList(Pager pager) {
		// TODO Auto-generated method stub
		return roleDao.findShopRoleList(pager);
	}

	@Override
	public int deleteShopRole(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.deleteShopRole(id);
	}

	@Override
	public int saveShopRole(Roles shopRole) {
		// TODO Auto-generated method stub
		shopRole.setCreateTime(System.currentTimeMillis());
		return roleDao.saveShopRole(shopRole);
	}

	@Override
	public void updateState(Roles shopRole) {
		roleDao.updateState(shopRole);	
	}

	@Override
	public Roles findShopRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.findShopRoleById(id);
	}

	@Override
	public int findCount(Roles shopRole) {
		// TODO Auto-generated method stub
		return roleDao.findCount(shopRole);
	}

	@Override
	public List<Roles> findList() {
		return roleDao.findList();
	}
}
