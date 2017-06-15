package com.Vshop.union.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.dao.UnionGoodsDao;
import com.Vshop.union.dao.mapper.UnionGoodsMapper;
import com.Vshop.union.entity.UnionGoods;

/**
 * 联盟商品daoimpl
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
@Repository
public class UnionGoodsDaoImpl implements UnionGoodsDao {

	/** 联盟商品mapper */
	@Resource
	private UnionGoodsMapper unionGoodsMapper;

	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UnionGoods> findPagerList(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}

		if (pager.getCondition() == null) {
			pager.setCondition(new UnionGoods());
		}
		return unionGoodsMapper.findPagerList(pager);
	}

	/**
	 * 通过id获取联盟商品
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UnionGoods findById(int id) {
		return unionGoodsMapper.findById(id);
	}

	/**
	 * 通过id删除联盟商品
	 * 
	 * @param id
	 */
	@Override
	public void deleteById(int id) {
		unionGoodsMapper.deleteById(id);
	}

	/**
	 * 修改联盟商品
	 * 
	 * @param goods
	 */
	@Override
	public void update(UnionGoods goods) {
		unionGoodsMapper.update(goods);
	}

	/**
	 * 保存联盟商品
	 * 
	 * @param goods
	 */
	@Override
	public void save(UnionGoods goods) {
		unionGoodsMapper.save(goods);
	}

	/**
	 * 获取所有联盟商品
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoods> findAll() {
		return unionGoodsMapper.findAll();
	}

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
	@Override
	public List<UnionGoods> findByCommisionRatioWl(int minCommisionRatio,
			int maxCommisionRatio, int count) {

		Pager pager = new Pager();

		if (count > 0) {
			pager.setPageSize(count);
		}

		UnionGoods goods = new UnionGoods();

		if (minCommisionRatio > 0) {
			goods.setMinCommisionRatio(minCommisionRatio);
		}
		if (maxCommisionRatio > 0) {
			goods.setMaxCommisionRatio(maxCommisionRatio);
		}

		pager.setCondition(goods);

		return findFrontPagerList(pager);
	}

	/**
	 * 获取符合前端显示的分页集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UnionGoods> findFrontPagerList(Pager pager) {
		return unionGoodsMapper.findFrontPagerList(pager);
	}

	/**
	 * 通过商品分类删除关联的商品
	 * 
	 * @param classId
	 *            商品分类id
	 */
	@Override
	public void deleteByClassId(int classId) {
		unionGoodsMapper.deleteByClassId(classId);
	}

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
	@Override
	public void updateClassIds(int classId, String classIds,String classNames) {
		unionGoodsMapper.updateClassIds(classId, classIds,classNames);
	}

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
	@Override
	public void updateClassNames(int classId, String oldName, String newName) {
		unionGoodsMapper.updateClassNames(classId, oldName, newName);
	}
}
