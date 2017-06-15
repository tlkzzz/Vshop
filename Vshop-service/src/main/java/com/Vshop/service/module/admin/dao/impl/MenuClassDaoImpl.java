package com.Vshop.service.module.admin.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.vo.MenuClassVo;
import com.Vshop.service.module.admin.dao.MenuClassDao;
import com.Vshop.service.module.admin.dao.mapper.MenuClassMapper;

@Repository
public class MenuClassDaoImpl implements MenuClassDao{

    @Resource
    private MenuClassMapper menuClassMapper;

    /**
     * 查询下级分类
     *
     * @param id
     * @return
     */
    @Override
    public List<MenuClass> findChild(int id) {
        return menuClassMapper.findChild(id);
    }

    
    /**
     * 保存分类
     * @param menuClass
     */
	@Override
	public void save(MenuClass menuClass) {
		menuClassMapper.save(menuClass);
	}
    

    /**
     * 修改分类
     * @param menuClass
     */
	@Override
	public void update(MenuClass menuClass) {
		menuClassMapper.update(menuClass);
	}

	@Override
	public int findCount(MenuClass menuClass) {
		
		return menuClassMapper.findCount(menuClass);
	}
	 /**
     * 删除
     * @param id
     */
	@Override
	public void delete(Integer id) {
		menuClassMapper.delete(id);
	}
    
	/**
     * 通过id查询分类
     * @param gcId
     * @return
     */
    @Override
    public MenuClass findById(Integer gcId) {
        return menuClassMapper.findById(gcId);
    }
    
    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<MenuClassVo> findPageList() {
        return menuClassMapper.findPageList();
    }

    /**
     * 查询出所有级别的分类
     * @return
     */
    @Override
    public List<MenuClass> findList() {
        return menuClassMapper.findList();
    }

  

    /**
     * 查询子列表
     * @param id
     * @return
     */
    @Override
    public List<MenuClassVo> findChildListmap(int id,int roleid) {
    	Map<String,Object> params = new HashMap<String, Object>(); 
    	params.put("mparentid", id);
    	params.put("roleid", roleid);
        return menuClassMapper.findChildListmap(params);
    }

    /**
     * 修改子类分类
     *
     * @param menuClass
     */
    @Override
    public void updateChildType(MenuClass menuClass) {
    	menuClassMapper.updateChildType(menuClass);
    }

    /**
     * 递归查询所有
     *
     * @return
     */
    @Override
    public List<MenuClass> findAll() {
        return menuClassMapper.findAll();
    }


	@Override
	public int findbyparentid(int mparentid) {
		return menuClassMapper.findbyparentid(mparentid);
	}


	@Override
	public int findparentidCount(int mid) {
		return menuClassMapper.findparentidCount(mid);
	}


	@Override
	public List<MenuClassVo> findChildList(int id) {
		// TODO Auto-generated method stub
		return menuClassMapper.findChildList(id);
	}


}
