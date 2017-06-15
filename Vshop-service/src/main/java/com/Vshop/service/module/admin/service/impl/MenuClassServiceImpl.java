package com.Vshop.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.vo.MenuClassVo;
import com.Vshop.service.module.admin.dao.MenuClassDao;
import com.Vshop.service.module.admin.service.MenuClassService;

@Service
public class MenuClassServiceImpl implements MenuClassService{

    @Resource
    private MenuClassDao menuClassDao;

    /**
     * 保存分类
     * @param menuClass
     */
    @Override
    public void save(MenuClass menuClass) {
    	menuClassDao.save(menuClass);
    }

    /**
     * 修改分类
     * @param menuClass
     */
    @Override
    public void update(MenuClass menuClass) {
    	menuClassDao.update(menuClass);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
    	menuClassDao.delete(id);
    }

    /**
     * 通过id查询分类
     * @param id
     * @return
     */
    @Override
    public MenuClass findById(Integer id) {
        return menuClassDao.findById(id);
    }

    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<MenuClassVo> findListForPage() {
        return menuClassDao.findPageList();
    }

    /**
     * 查询子列表
     * @param id
     * @return
     */
    @Override
    public List<MenuClassVo> findChildList(int id) {
        return menuClassDao.findChildList(id);
    }

    /**
     * 查询出所有级别的分类
     * @return
     */
    @Override
    public List<MenuClass> findList() {
        return menuClassDao.findList();
    }

    /**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
    @Override
    public int findCount(MenuClass menuClass) {
        return menuClassDao.findCount(menuClass);
    }

    /**
     * 递归查询所有
     *
     * @return
     */
    @Override
    public List<MenuClass> findAll() {

        List<MenuClass> meunClassList = menuClassDao.findList();
        for(MenuClass menuClass : meunClassList){
        	menuClass.setMenuClassList(menuClassDao.findChild(menuClass.getMid()));
        }
        return meunClassList;
    }
    
   

    
	@Override
	public int findbyparentid(int mparentid) {
		return menuClassDao.findbyparentid(mparentid);
	}

	@Override
	public int findparentidCount(int mid) {
		return menuClassDao.findparentidCount(mid);
	}

	@Override
	public List<MenuClassVo> findChildListmap(int id, int roleid) {
		return menuClassDao.findChildListmap(id, roleid);
	}

//	@Override
//	public void update(MenuClass menuClass) {
//		// TODO Auto-generated method stub
//		
//	}


}
