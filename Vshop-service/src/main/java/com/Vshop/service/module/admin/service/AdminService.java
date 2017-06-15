package com.Vshop.service.module.admin.service;


import java.util.List;

import com.Vshop.core.entity.base.Admin;
import com.Vshop.core.entity.vo.AdminVo;
import com.Vshop.service.utils.page.Pager;


public interface AdminService {

	Admin findByAdminName(String adminName);

	int findAdminCount(Admin admin);

	/**
	 * 
	 * @Title: findAdminList
	 * @Description: TODO (分页获取集合)
	 * @param @param pager
	 * @return List<Admin>   
	 * @throws
	 */
	List<AdminVo> findAdminList(Pager pager);
	
	
	/**
	 * 
	 * @Title: findAdminById
	 * @Description: TODO (根据id获取admin)
	 * @param @param pager
	 * @return List<Admin>    
	 * @throws
	 */
	Admin findAdminById(Integer id);
	
	/**
	 * 
	 * @Title: save
	 * @Description: TODO (保存)
	 * @param @param Admin
	 * @throws
	 */
	void save(Admin admin);
	
	/**
	 * 
	 * @Title: update
	 * @Description: TODO (更新)
	 * @param @param Admin
	 * @return void 
	 * @throws
	 */
	void update(Admin admin);

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO 删除
	 * @param @param id    ID
	 * @throws
	 */
	void delete(Integer id);

}
