package com.Vshop.service.module.admin.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.vo.MenuClassVo;
import com.Vshop.core.orm.mybatis.SqlMapper;

/**
 * Created by rabook on 2014/11/4.
 */

@SqlMapper
public interface MenuClassMapper {

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
    void delete(@Param("mid") Integer mid);

    /**
     * 通过id查询分类
     * @param mid
     * @return
     */
    MenuClass findById(@Param("mid") Integer mid);

    /**
     * 查询一级分类
     * @return
     */
    List<MenuClassVo> findPageList();

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
     * 查询子列表
     * @param id
     * @return
     */
    List<MenuClassVo> findChildList(@Param("mparentid") int mid);
    
    List<MenuClassVo> findChildListmap(Map parmap);

    /**
     * 修改子类分类
     * @param goodsClass
     */
    void updateChildType(MenuClass menuClass);

    /**
     * 递归查询所有
     * @return
     */
    List<MenuClass> findAll();

    /**
     * 查询下级分类
     * @param id
     * @return
     */
    List<MenuClass> findChild(@Param("mparentid") int mid);
    
    /**
     * 根据mparentid查询
     * @param menuClass
     * @return
     */
    int findbyparentid(int mparentid);
    /**
     * 根据mid查询
     * @param menuClass
     * @return
     */
    int findparentidCount(int mid);

}
