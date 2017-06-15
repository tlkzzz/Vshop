package com.Vshop.service.module.admin.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Admin;
import com.Vshop.core.entity.vo.AdminVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;


@SqlMapper
public interface AdminMapper {

	Admin findByAdminName(String adminName);
	
	int findAdminCount(Admin admin);
    
    List<AdminVo> findAdminList(Pager pager);
    
    Admin findAdminById(@Param("id") Integer id);
    
    void save(Admin admin);
    
    void update(Admin admin);
    
    void delete(Integer id);
}
