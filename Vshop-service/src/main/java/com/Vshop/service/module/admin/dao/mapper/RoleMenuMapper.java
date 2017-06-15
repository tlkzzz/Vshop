package com.Vshop.service.module.admin.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.RoleMenu;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface RoleMenuMapper {
	/**
	 * 查询角色
	 * @param pager
	 * @return
	 */
	Integer countRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 获取角色权限列表
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
	 * @param roleMenu
	 * @return
	 */
	int saveRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 更新角色关系表
	 * @param roleMenu
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
