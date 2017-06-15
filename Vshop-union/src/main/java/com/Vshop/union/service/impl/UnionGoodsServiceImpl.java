package com.Vshop.union.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Admin;
import com.Vshop.service.utils.page.Pager;
import com.Vshop.service.utils.sessionkey.CacheUser;
import com.Vshop.service.utils.sessionkey.CacheUtil;
import com.Vshop.union.dao.UnionGoodsDao;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.entity.UnionGoodsClass;
import com.Vshop.union.service.UnionGoodsClassService;
import com.Vshop.union.service.UnionGoodsService;

/**
 * 联盟商品serviceimpl
 * 
 * @author liuzhen
 * @version 2015-9-23
 */
@Service
public class UnionGoodsServiceImpl implements UnionGoodsService {

	/** 联盟商品dao */
	@Resource
	private UnionGoodsDao unionGoodsDao;
	/** 联盟商品分类service */
	@Resource
	private UnionGoodsClassService unionGoodsClassService;

	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UnionGoods> findPagerList(Pager pager) {
		return unionGoodsDao.findPagerList(pager);
	}

	/**
	 * 通过id获取联盟商品
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UnionGoods findById(int id) {
		return unionGoodsDao.findById(id);
	}

	/**
	 * 通过id删除联盟商品
	 * 
	 * @param id
	 */
	@Override
	public void deleteById(int id) {
		unionGoodsDao.deleteById(id);
	}

	/**
	 * 保存联盟商品
	 * 
	 * @param goods
	 */
	@Override
	public void save(UnionGoods goods) {
		goods.setCreateDate(System.currentTimeMillis());

		CacheUser user = CacheUtil.getCacheUser();
		Admin admin = user.getAdmin();

		goods.setCreateBy(admin.getAdminId());
		goods.setDelFlag(0);

		int classid = goods.getClassId();
		if (classid != 0) {
			UnionGoodsClass goodsClass = unionGoodsClassService
					.findById(classid);

			if (goodsClass != null) {
				String idpaths = goodsClass.getIdpaths();

				goods.setClassIds(idpaths);
				goods.setClassName(goodsClass.getName());

				goods.setClassNames(unionGoodsClassService
						.findListByIds(idpaths));

			}
		}

		unionGoodsDao.save(goods);
	}

	/**
	 * 修改联盟商品
	 * 
	 * @param goods
	 */
	@Override
	public void update(UnionGoods goods) {
		goods.setUpdateDate(System.currentTimeMillis());

		CacheUser user = CacheUtil.getCacheUser();
		Admin admin = user.getAdmin();

		goods.setUpdateBy(admin.getAdminId());

		UnionGoods oldGoods = findById(goods.getId());

		int classid = goods.getClassId();
		if (classid != 0 && oldGoods.getClassId() != classid) {
			UnionGoodsClass goodsClass = unionGoodsClassService
					.findById(classid);

			if (goodsClass != null) {
				String idpaths = goodsClass.getIdpaths();

				goods.setClassIds(idpaths);
				goods.setClassName(goodsClass.getName());

				goods.setClassNames(unionGoodsClassService
						.findListByIds(idpaths));
			}
		}

		unionGoodsDao.update(goods);
	}

	/**
	 * 获取所有联盟商品
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoods> findAll() {
		return unionGoodsDao.findAll();
	}

	/**
	 * 获取符合满就送条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	@Override
	public List<UnionGoods> findmjs(int count) {
		return unionGoodsDao.findByCommisionRatioWl(49, 0, count);
	}

	/**
	 * 获取符合精选特卖条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	@Override
	public List<UnionGoods> findjxtm(int count) {
		return unionGoodsDao.findByCommisionRatioWl(19, 50, count);
	}

	/**
	 * 获取符合热销条件的推广商品
	 * 
	 * @param count
	 *            获取总条数 ,如果值<=0,返回默认分页数量
	 * @return
	 */
	@Override
	public List<UnionGoods> findrx(int count) {
		return unionGoodsDao.findByCommisionRatioWl(0, 20, count);
	}

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
	@Override
	public List<UnionGoods> findGoodsList(int classId, String type, int pageNo,
			String keyword) {
		type = type.trim();

		UnionGoods goods = new UnionGoods();
		goods.setClassId(classId);
		goods.setGoodsName(keyword.trim());

		if (StringUtils.isNotBlank(type)) {
			if ("mjs".equals(type)) {
				goods.setMinCommisionRatio(49);
				goods.setMaxCommisionRatio(0);
			}
			if ("jxtm".equals(type)) {
				goods.setMinCommisionRatio(19);
				goods.setMaxCommisionRatio(50);
			}
			if ("rx".equals(type)) {
				goods.setMinCommisionRatio(0);
				goods.setMaxCommisionRatio(20);
			}
		}

		Pager pager = new Pager();
		pager.setPageNo(pageNo);
		pager.setCondition(goods);

		return unionGoodsDao.findFrontPagerList(pager);
	}

	/**
	 * 通过商品分类删除关联的商品
	 * 
	 * @param classId
	 *            商品分类id
	 */
	@Override
	public void deleteByClassId(int classId) {
		unionGoodsDao.deleteByClassId(classId);
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
		unionGoodsDao.updateClassIds(classId, classIds,classNames);
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
		unionGoodsDao.updateClassNames(classId, oldName, newName);
	}
}
