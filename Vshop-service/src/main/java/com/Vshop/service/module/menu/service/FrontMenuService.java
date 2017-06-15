package com.Vshop.service.module.menu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.FrontMenu;

public interface FrontMenuService {
	
	/**
	 * 查询所有前台菜单列表并以json格式返回
	 * @return
	 */
	public String frontMenu();
	
	/**
	 *查询所有菜单 
	 * @return
	 */
	public List<FrontMenu> selectAllFm();
	
	/**
	 * 添加菜单
	 * @param frontMenu
	 */
	public void save(FrontMenu frontMenu);
	
	/**
	 * 删除菜单
	 * @param id
	 */
	public void delete(@Param("id") int id);
	
	/**
	 * 修改菜单
	 * @param frontMenu
	 */
	public void update(FrontMenu frontMenu);
	
	/**
	 * 查询所有一级父节点
	 * @return
	 */
	public List<FrontMenu> selectParentFrontMenu();
	
	/**
	 * 查询父节点下的子节点
	 * @param frontMenu
	 * @return
	 */
	public List<FrontMenu> selectChildrenFrontMenu(FrontMenu frontMenu);
	
	/**
	 * 根据父级id查询所有父级id下的数量
	 * @param id
	 * @return
	 */
	public int findParentIdCount(Integer id);
}