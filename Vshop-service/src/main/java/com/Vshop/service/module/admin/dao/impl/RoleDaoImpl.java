package com.Vshop.service.module.admin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Roles;
import com.Vshop.service.module.admin.dao.RoleDao;
import com.Vshop.service.module.admin.dao.mapper.RoleMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public Integer countShopRole(Roles shopRole) {
		return roleMapper.countShopRole(shopRole);
	}

	@Override
	public List<Roles> findShopRoleList(Pager pager) {
		List<Roles> list = roleMapper.findShopRoleList(pager);
		return list;
	}

	@Override
	public int deleteShopRole(Integer id) {
		return roleMapper.deleteShopRole(id);
	}

	@Override
	public int saveShopRole(Roles shopRole) {
		return roleMapper.saveShopRole(shopRole);
	}

	@Override
	public void updateState(Roles shopRole) {
		roleMapper.updateState(shopRole);
	}

	@Override
	public Roles findShopRoleById(Integer id) {
		return roleMapper.findShopRoleById(id);
	}

	@Override
	public int findCount(Roles shopRole) {
		return roleMapper.findCount(shopRole);
	}

	@Override
	public List<Roles> findList() {
		return roleMapper.findList();
	}

}
