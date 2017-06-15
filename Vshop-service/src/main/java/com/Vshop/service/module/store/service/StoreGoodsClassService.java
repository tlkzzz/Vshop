package com.Vshop.service.module.store.service;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.StoreGoodsClass;
import com.Vshop.core.entity.vo.StoreGoodsClassVo;

public interface StoreGoodsClassService {
    public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    public void deleteByPrimaryKey(Integer id);

    public List<StoreGoodsClass> findParentList(int id);

    public void save(StoreGoodsClass storeGoodsClass);

    public void update(StoreGoodsClass storeGoodsClass);

    public StoreGoodsClass selectByPrimaryKey(Integer stcId);


    public Map<String, List<StoreGoodsClass>> queryStoreClass(int id);

    List<StoreGoodsClass> findChild(int id);
    
    public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    public StoreGoodsClass findbystcName(String stcName);
    
    /**
     * 查询父子关联通过显示状态
     * @param id
     */
    public List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass);
}
