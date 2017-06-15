package com.Vshop.union.service;

import java.util.List;

import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.UnionGoods;

/**
 * 联盟商品service
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
public interface UnionGoodsService {
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
	 * 获取符合满就送条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	public List<UnionGoods> findmjs(int count);

	/**
	 * 获取符合精选特卖条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	public List<UnionGoods> findjxtm(int count);

	/**
	 * 获取符合热销条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	public List<UnionGoods> findrx(int count);

	/**
	 * 获取商品列表
	 * 
	 * @param classId
	 *            商品分裂id
	 * @param type
	 *            类型(mjs：满就送、jxtm：精选特卖、rx：热销商品)
	 * @param pageNo
	 *            页码
	 * @param keyword
	 *            关键字
	 * @return
	 */
	public List<UnionGoods> findGoodsList(int classId, String type, int pageNo,
			String keyword);

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
	public void updateClassIds(int classId, String classIds, String classNames);

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
