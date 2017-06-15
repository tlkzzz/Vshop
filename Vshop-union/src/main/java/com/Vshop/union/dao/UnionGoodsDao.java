package com.Vshop.union.dao;

import java.util.List;

import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.UnionGoods;

/**
 * 联盟商品dao
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
public interface UnionGoodsDao {
	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	public List<UnionGoods> findPagerList(Pager pager);

	/**
	 * 通过id获取联盟商品
	 * 
	 * @param id
	 * @return
	 */
	public UnionGoods findById(int id);

	/**
	 * 通过id删除联盟商品
	 * 
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * 修改联盟商品
	 * 
	 * @param goodsClass
	 */
	public void update(UnionGoods goods);

	/**
	 * 保存联盟商品
	 * 
	 * @param goodsClass
	 */
	public void save(UnionGoods goods);

	/**
	 * 获取所有联盟商品
	 * 
	 * @return
	 */
	public List<UnionGoods> findAll();

	/**
	 * 通过无线佣金率获取商品列表
	 * 
	 * @param minCommisionRatio
	 *            最小佣金率，如果值<=0,参数将忽略
	 * @param maxCommisionRatio
	 *            最大佣金率，如果值<=0,参数将忽略
	 * @param count
	 *            获取总条数,如果值<=0,返回默认分页数量
	 * @return
	 */
	public List<UnionGoods> findByCommisionRatioWl(int minCommisionRatio,
			int maxCommisionRatio, int count);
	
	/**
	 * 获取符合前端显示的分页集合
	 * 
	 * @param pager
	 * @return
	 */
	public List<UnionGoods> findFrontPagerList(Pager pager);
	
	/**
	 * 通过商品分类删除关联的商品
	 * 
	 * @param classId
	 *            商品分类id
	 */
	public void deleteByClassId(int classId);
	
	/**
	 * 更新商品分类
	 * 
	 * @param classId
	 *            分类id
	 * @param classIds
	 *            所有所属的商品分类
	 * @param classNames
	 *            所有所属的商品分类名称
	 */
	public void updateClassIds(int classId, String classIds,String classNames);
	
	/**
	 * 修改
	 * 
	 * @param classId
	 *            分类id
	 * @param oldName
	 *            旧分类名称
	 * @param newName
	 *            新分类说明
	 */
	public void updateClassNames(int classId, String oldName, String newName);
}
