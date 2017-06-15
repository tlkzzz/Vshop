package com.Vshop.service.module.admin.service;

import java.util.List;

import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.vo.MenuClassVo;

public interface MenuClassService {

    /**
     * 保存分类
     * @param menuClass
     */
    void save(MenuClass menuClass);
    /**
     * 修改分类
     * @param menuClass
     */
    void update(MenuClass menuClass);
    /**
     * 删除
     * @param mid
     */
    void delete(Integer mid);
    /**
     * 通过id查询分类
     * @param gcId
     * @return
     */
    MenuClass findById(Integer gcId);

    /**
     * 查询一级分类
     * @return
     */
    List<MenuClassVo> findListForPage();

    /**
     * 查询子列表
     * @param id
     * @return
     */
    List<MenuClassVo> findChildList(int id);

    /**
     * 查询出所有级别的分类
     * @return
     */
    List<MenuClass> findList();
    /**
     * 根据不同条件查询条数，页面验证用
     * @param menuClass
     * @return
     */
    int findCount(MenuClass menuClass);

    /**
     * 递归查询所有
     * @return
     */
    List<MenuClass> findAll();
    
   /**
    * 根据mparentid查询
    * @param id
    * @return
    */
   int findbyparentid(int mparentid);
   /**
    * 根据mid查询
    * @param menuClass
    * @return
    */
   int findparentidCount(int mid);
   
   List<MenuClassVo> findChildListmap(int id, int roleid);
}
