package com.Vshop.service.module.store.dao;

import java.util.List;

import com.Vshop.core.entity.StoreGoodsClass;
import com.Vshop.core.entity.vo.StoreGoodsClassVo;

public interface StoreGoodsClassDao {
    public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    public void deleteByPrimaryKey(Integer id);

    public List<StoreGoodsClass> findParentList(int id);

    public void insertSelective(StoreGoodsClass storeGoodsClass);

    public void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass);

    public StoreGoodsClass selectByPrimaryKey(Integer stcId);

    public List<StoreGoodsClass> findAll(int id);

    List<StoreGoodsClass> findChild(int id);

    void updateState(StoreGoodsClass storeGoodsClass);
    
    public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    public StoreGoodsClass findbystcName(String stcName);
    
    /**
     * 查询父子关联通过显示状态
     * @param id
     */
    public List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass);
}
