package com.Vshop.service.module.goods.dao.mapper;


import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsStoreCls;

@SqlMapper
public interface GoodsClassMapper {

	/**
     * 保存分类
     * @param goodsClass
     */
    void save(GoodsClass goodsClass);
    /**
     * 修改分类
     * @param goodsClass
     */
    void update(GoodsClass goodsClass);
    /**
     * 删除
     * @param id
     */
    void delete(Integer id);
    
    /**
     * 通过id查询分类
     * @param gcId
     * @return
     */
    GoodsClass findById(Integer gcId);
    
    /**
     * 通过id查询分类
     * @param gcId
     * @return
     */
    List<GoodsClass> findByList(List<Integer> gcIdList);
    
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<GoodsClass> findList(int parentid);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<GoodsClass> findAll();
    
    /**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
    int findCount(GoodsClass goodsClass);
    
    /**
     * 根据父goodsClass查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<GoodsClass> findListbyishow(GoodsClass goodsClass);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<GoodsClass> findAllbyisshow(GoodsClass goodsClass);
    
    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    List<GoodsClass> findChild(Integer gcParentId);
    
    /**
     * 通过父id及学院id查询一级分类
     * @param gsc
     * @return
     */
	List<GoodsClass> findListByStoreId(GoodsStoreCls gsc);
	
	/**
	 * 通过学院id查询叶子分类
	 * @param gsc
	 * @return
	 */
	List<GoodsClass> findLeafListByStoreId(GoodsStoreCls gsc);
}
