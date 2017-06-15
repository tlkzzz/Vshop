package com.Vshop.union.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.service.utils.page.Pager;
import com.Vshop.union.dao.UnionGoodsClassDao;
import com.Vshop.union.dao.mapper.UnionGoodsClassMapper;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.entity.UnionGoodsClass;

/**
 * 联盟商品分类daoimpl
 * 
 * @author liuzhen
 * @version 2015-9-21
 */
@Repository
public class UnionGoodsClassDaoImpl implements UnionGoodsClassDao {

	/** 联盟商品分类mapper */
	@Resource
	private UnionGoodsClassMapper unionGoodsClassMapper;

	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findPagerList(Pager pager) {
		if (pager == null) {
			pager = new Pager();
		}

		if (pager.getCondition() == null) {
			pager.setCondition(new UnionGoods());
		}
		return unionGoodsClassMapper.findPagerList(pager);
	}

	/**
	 * 通过id获取联盟商品分类
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UnionGoodsClass findById(int id) {
		return unionGoodsClassMapper.findById(id);
	}

	/**
	 * 通过id删除联盟商品分类
	 * 
	 * @param id
	 */
	@Override
	public void deleteById(int id) {
		unionGoodsClassMapper.deleteById(id);
	}

	/**
	 * 修改联盟商品分类
	 * 
	 * @param goodsClass
	 */
	@Override
	public void update(UnionGoodsClass goodsClass) {
		unionGoodsClassMapper.update(goodsClass);
	}

	/**
	 * 保存联盟商品分类
	 * 
	 * @param goodsClass
	 */
	@Override
	public void save(UnionGoodsClass goodsClass) {
		unionGoodsClassMapper.save(goodsClass);
	}

	/**
	 * 获取所有联盟商品分类
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findAll() {
		return unionGoodsClassMapper.findAll();
	}

	/**
	 * 获取所有允许展示的联盟商品分类
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findAllIsShow() {
		return unionGoodsClassMapper.findAllIsShow();
	}

	/**
	 * 根据父id查询分类列表
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findListByPid(int pid) {
		return unionGoodsClassMapper.findListByPid(pid);
	}

	/**
	 * 根据父id查询用于前端显示的分类列表
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findFrontListByPid(int pid) {
		return unionGoodsClassMapper.findFrontListByPid(pid);
	}

	/**
	 * 通过多个分类id获取分类名称集合,返回以“/”分割的字符串
	 * 
	 * @param ids
	 *            多个分类id
	 * @return
	 */
	@Override
	public List<String> findListByIds(String[] ids) {
		return unionGoodsClassMapper.findListByIds(ids);
	}

}
