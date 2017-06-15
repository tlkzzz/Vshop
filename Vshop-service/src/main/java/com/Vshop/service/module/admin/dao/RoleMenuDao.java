package com.Vshop.service.module.admin.dao;

import java.util.List;

import com.Vshop.core.entity.base.RoleMenu;
import com.Vshop.service.utils.page.Pager;

public interface RoleMenuDao {
	/**
	 * 查询角色
	 * @param pager
	 * @return
	 */
	Integer countRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 获取全部服装
	 * @return
	 */
	List<RoleMenu> findRoleMenuList(Pager pager);
	
	/**
	 * 删除Role数据
	 * @param id 对应Test id
	 * @return
	 */
	int deleteRoleMenu(Integer id);
	
	/**
	 * 保存
	 * @param test1
	 * @return
	 */
	int saveRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 更新存在状态
	 * @param id
	 */
	void updateState(RoleMenu roleMenu);
	
	/**
	 * 插件一条数据信息
	 */
	RoleMenu findRoleMenuById(Integer id);
	
    /**
     * 校验查询
     *
     * @param pager
     */
    public int findCount(RoleMenu roleMenu);
    /**
     * 查询出所有
     * @return
     */
    List<RoleMenu> findList(Integer roleid);
}
