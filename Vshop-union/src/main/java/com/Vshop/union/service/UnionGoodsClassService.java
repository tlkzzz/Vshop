package com.Vshop.union.service;

import java.util.List;

import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.UnionGoodsClass;

/**
 * 联盟商品分类service
 * 
 * @author liuzhen
 * @version 2015-9-21
 */
public interface UnionGoodsClassService {
	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	public List<UnionGoodsClass> findPagerList(Pager pager);

	/**
	 * 通过id获取联盟商品分类
	 * 
	 * @param id
	 * @return
	 */
	public UnionGoodsClass findById(int id);

	/**
	 * 通过id删除联盟商品分类
	 * 
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * 保存联盟商品分类
	 * 
	 * @param goodsClass
	 */
	public void save(UnionGoodsClass goodsClass);

	/**
	 * 修改联盟商品分类
	 * 
	 * @param goodsClass
	 */
	public void update(UnionGoodsClass goodsClass);

	/**
	 * 获取所有联盟商品分类
	 * 
	 * @return
	 */
	public List<UnionGoodsClass> findAll();

	/**
	 * 获取所有允许展示的联盟商品分类
	 * 
	 * @return
	 */
	public List<UnionGoodsClass> findAllIsShow();

	/**
	 * 根据父id查询分类列表
	 * 
	 * @param pid
	 * @return
	 */
	public List<UnionGoodsClass> findListByPid(int pid);

	/**
	 * 根据父id查询用于前端显示的分类列表
	 * 
	 * @param pid
	 * @return
	 */
	public List<UnionGoodsClass> findFrontListByPid(int pid);

	/**
	 * 通过多个分类id获取分类名称集合,返回以“/”分割的字符串
	 * 
	 * @param ids
	 *            多个分类id，用“，”逗号分隔
	 * @return
	 */
	public String findListByIds(String ids);
}
