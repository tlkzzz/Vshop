package com.Vshop.union.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.entity.UnionGoods;

/**
 * 联盟商品ampper
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
@SqlMapper
public interface UnionGoodsMapper {
	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	public List<UnionGoods> findPagerList(Pager pager);

	/**
	 * 获取符合前端显示的分页集合
	 * 
	 * @param pager
	 * @return
	 */
	public List<UnionGoods> findFrontPagerList(Pager pager);

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
	 * 更新商品分类
	 * 
	 * @param classId
	 *            分类id
	 * @param classIds
	 *            所有所属的商品分类
	 * @param classNames
	 *            所有所属的商品分类名称
	 */
	public void updateClassIds(@Param("classId") int classId,
			@Param("classIds") String classIds,
			@Param("classNames") String classNames);

	/**
	 * 通过商品分类删除关联的商品
	 * 
	 * @param classId
	 *            商品分类id
	 */
	public void deleteByClassId(int classId);

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
	public void updateClassNames(@Param("classId") int classId,
			@Param("oldName") String oldName, @Param("newName") String newName);

}
