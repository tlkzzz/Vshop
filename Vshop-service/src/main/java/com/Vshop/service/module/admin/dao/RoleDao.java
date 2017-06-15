package com.Vshop.service.module.admin.dao;

import java.util.List;

import com.Vshop.core.entity.base.Roles;
import com.Vshop.service.utils.page.Pager;

public interface RoleDao {
	/**
	 * 查询角色
	 * @param pager
	 * @return
	 */
	Integer countShopRole(Roles shopRole);
	
	/**
	 * 获取全部服装
	 * @return
	 */
	List<Roles> findShopRoleList(Pager pager);
	
	/**
	 * 删除Role数据
	 * @param id 对应Test id
	 * @return
	 */
	int deleteShopRole(Integer id);
	
	/**
	 * 保存
	 * @param test1
	 * @return
	 */
	int saveShopRole(Roles shopRole);
	
	/**
	 * 更新存在状态
	 * @param id
	 */
	void updateState(Roles shopRole);
	
	/**
	 * 插件一条数据信息
	 */
	Roles findShopRoleById(Integer id);
	
    /**
     * 校验查询
     *
     * @param pager
     */
    public int findCount(Roles shopRole);
    
    /**
     * 查询出所有级角色
     * @return
     */
    List<Roles> findList();
}
