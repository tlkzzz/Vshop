package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

@SqlMapper
public interface GoodsSpecMapper {
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
}
