package com.Vshop.service.module.menu.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.FrontMenu;
import com.Vshop.service.module.menu.dao.FrontMenuDao;
import com.Vshop.service.module.menu.dao.mapper.FrontMenuMapper;

@Repository
public class FrontMenuDaoImpl implements FrontMenuDao{
	
	@Autowired
	private FrontMenuMapper frontMenuMapper;
	
	@Override
	public FrontMenu findFrontMenuById(Integer id) {
		return frontMenuMapper.findFrontMenuById(id);
	}

	@Override
	public List<FrontMenu> findParentMenu() {
		return frontMenuMapper.findParentMenu();
	}

	@Override
	public List<FrontMenu> findChildMenu(Integer id) {
		return frontMenuMapper.findChildMenu(id);
	}
	
	@Override
	public List<FrontMenu> selectAllFm() {
		return frontMenuMapper.selectAllFm();
	}

	@Override
	public void save(FrontMenu frontMenu) {
		frontMenuMapper.save(frontMenu);
	}

	@Override
	public void delete(int id) {
		frontMenuMapper.delete(id);
	}

	@Override
	public void update(FrontMenu frontMenu) {
		frontMenuMapper.update(frontMenu);
	}

	@Override
	public List<FrontMenu> selectParentFrontMenu() {
		return frontMenuMapper.selectParentFrontMenu();
	}

	@Override
	public List<FrontMenu> selectChildrenFrontMenu(FrontMenu frontMenu) {
		return frontMenuMapper.selectChildrenFrontMenu(frontMenu);
	}

	@Override
	public int findParentIdCount(Integer id) {
		return frontMenuMapper.findParentIdCount(id);
	}
	
}

