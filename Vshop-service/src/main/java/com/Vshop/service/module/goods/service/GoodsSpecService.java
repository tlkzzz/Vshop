package com.Vshop.service.module.goods.service;

import java.util.List;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.service.utils.page.Pager;

/**
 * 商品规格
 * @author chen
 *2015年08月14日14:48:56
 */
public interface GoodsSpecService {
	
	/**
	 * 保存goodsspec
	 * @param goodsSpec
	 */
	public void saveGoodsSpec(GoodsSpec goodsSpec);
	
	/**
	 * 根据goodsid批量删除
	 * @param goodsId
	 */
	public void deleteGoodsSpecByGoodsId(Integer goodsId);

	/**
	 * 通过goodsId查找
	 * @param spId
	 * @return
	 */
	public List<GoodsSpec> findListByGoodsId(Integer goodsId);
	
	/**
	 * 通过主键查找
	 * @param goodsSpecId
	 * @return
	 */
	public GoodsSpec findByGoodsSpecId(Integer goodsSpecId);

	/**
	 * 查找所有的
	 */
    public List<GoodsSpec> findAllList();

    /**
     * 分页并且条件查询
     * @param pager
     * @return
     */
    public List<GoodsSpec> findPageList(Pager pager);
    
    /**
     * 查询条数
     */
    public Integer findPageListCount(Pager pager);
    
    /**
     * 修改库存
     */
    public void updateGoodsSpecStorage(GoodsSpec goodsSpec);
    
	/**
	 * 通过已选择的规格值id(以逗号分隔)得到对应的规格商品
	 * @return String
	 */
    public GoodsSpec getGoodsSpecBySpecValueId(String specValuesStr, Goods goods);
    
}
