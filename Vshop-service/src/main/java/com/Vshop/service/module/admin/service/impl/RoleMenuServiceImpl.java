package com.Vshop.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.RoleMenu;
import com.Vshop.service.module.admin.dao.RoleMenuDao;
import com.Vshop.service.module.admin.service.RoleMenuService;
import com.Vshop.service.utils.page.Pager;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{
	
	@Resource
	private RoleMenuDao roleMenuDao;

	@Override
	public Integer countRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.countRoleMenu(roleMenu);
	}

	@Override
	public List<RoleMenu> findRoleMenuList(Pager pager) {
		// TODO Auto-generated method stub
		return roleMenuDao.findRoleMenuList(pager);
	}

	@Override
	public int deleteRoleMenu(Integer id) {
		// TODO Auto-generated method stub
		return roleMenuDao.deleteRoleMenu(id);
	}

	@Override
	public int saveRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.saveRoleMenu(roleMenu);
	}

	@Override
	public void updateState(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		roleMenuDao.updateState(roleMenu);
		
	}

	@Override
	public RoleMenu findRoleMenuById(Integer id) {
		// TODO Auto-generated method stub
		return roleMenuDao.findRoleMenuById(id);
	}

	@Override
	public int findCount(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.findCount(roleMenu);
	}

	@Override
	public List<RoleMenu> findList(Integer roleid) {
		return roleMenuDao.findList(roleid);
	}

}
