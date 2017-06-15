package com.Vshop.union.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Admin;
import com.Vshop.service.utils.page.Pager;
import com.Vshop.service.utils.sessionkey.CacheUser;
import com.Vshop.service.utils.sessionkey.CacheUtil;
import com.Vshop.union.dao.UnionGoodsClassDao;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.entity.UnionGoodsClass;
import com.Vshop.union.service.UnionGoodsClassService;
import com.Vshop.union.service.UnionGoodsService;

/**
 * 联盟商品分类serviceimpl
 * 
 * @author liuzhen
 * @version 2015-9-21
 */
@Service
public class UnionGoodsClassServiceImpl implements UnionGoodsClassService {

	/** 联盟商品分类dao */
	@Resource
	private UnionGoodsClassDao unionGoodsClassDao;
	/** 联盟商品service */
	@Resource
	private UnionGoodsService unionGoodsService;

	/**
	 * 分页集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findPagerList(Pager pager) {
		return unionGoodsClassDao.findPagerList(pager);
	}

	/**
	 * 通过id获取联盟商品分类
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UnionGoodsClass findById(int id) {
		return unionGoodsClassDao.findById(id);
	}

	/**
	 * 通过id删除联盟商品分类
	 * 
	 * @param id
	 */
	@Override
	public void deleteById(int id) {

		unionGoodsService.deleteByClassId(id);// 删除分类同时删除分类下商品

		List<UnionGoodsClass> goodsClassChild = findListByPid(id);
		if (goodsClassChild != null && goodsClassChild.size() > 0) {
			for (int i = 0; i < goodsClassChild.size(); i++) {
				deleteById(goodsClassChild.get(i).getId());
			}
		}

		unionGoodsClassDao.deleteById(id);
	}

	/**
	 * 保存联盟商品分类
	 * 
	 * @param goodsClass
	 */
	@Override
	public void save(UnionGoodsClass goodsClass) {
		goodsClass.setCreateDate(System.currentTimeMillis());

		CacheUser user = CacheUtil.getCacheUser();
		Admin admin = user.getAdmin();

		goodsClass.setCreateBy(admin.getAdminId());
		goodsClass.setDelFlag(0);

		int pid = goodsClass.getPid();

		UnionGoodsClass pClass = null;

		// 设置层级当前
		if (pid == 0) {
			goodsClass.setLevels(1);
		} else {
			pClass = unionGoodsClassDao.findById(pid);
			goodsClass.setLevels(pClass.getLevels() + 1);
		}

		unionGoodsClassDao.save(goodsClass);

		// 保存成功后修改idpaths
		if (pClass != null) {
			goodsClass.setIdpaths(pClass.getIdpaths() + goodsClass.getId()
					+ ",");
		} else {
			goodsClass.setIdpaths(goodsClass.getId() + ",");
		}
		unionGoodsClassDao.update(goodsClass);

	}

	/**
	 * 修改联盟商品分类
	 * 
	 * @param goodsClass
	 */
	@Override
	public void update(UnionGoodsClass goodsClass) {
		long currentTime = System.currentTimeMillis();
		goodsClass.setUpdateDate(System.currentTimeMillis());

		CacheUser user = CacheUtil.getCacheUser();
		Admin admin = user.getAdmin();

		goodsClass.setUpdateBy(admin.getAdminId());

		UnionGoodsClass oldClass = findById(goodsClass.getId());

		if (!oldClass.getName().equals(goodsClass.getName())) {
			updateChildNames(goodsClass.getId(), oldClass.getName(),
					goodsClass.getName());
		}

		if (oldClass.getPid() != goodsClass.getPid()) {

			UnionGoodsClass pClass = null;
			// 重置levels和idpaths字段
			if (goodsClass.getPid() == 0) {
				goodsClass.setLevels(1);

				goodsClass.setIdpaths(goodsClass.getId() + ",");
			} else {
				pClass = unionGoodsClassDao.findById(goodsClass.getPid());
				goodsClass.setLevels(pClass.getLevels() + 1);

				goodsClass.setIdpaths(pClass.getIdpaths() + goodsClass.getId()
						+ ",");
			}

			unionGoodsService.updateClassIds(goodsClass.getId(),
					goodsClass.getIdpaths(),
					findListByIds(goodsClass.getIdpaths()));

		}

		unionGoodsClassDao.update(goodsClass);

		if (oldClass.getPid() != goodsClass.getPid()) {
			updateChildLevelAndPath(goodsClass);
		}
		System.out.println("本次更新分类总用时："
				+ (System.currentTimeMillis() - currentTime) + "毫秒");
	}

	/**
	 * 更新分类名称
	 * 
	 * @param classId
	 *            分类id
	 * @param oldName
	 *            旧分类名称
	 * @param newName
	 *            新分类名称
	 */
	private void updateChildNames(int classId, String oldName, String newName) {
		unionGoodsService.updateClassNames(classId, oldName, newName);
		List<UnionGoodsClass> goodsClasss = findListByPid(classId);
		if (goodsClasss != null && goodsClasss.size() > 0) {
			for (int i = 0; i < goodsClasss.size(); i++) {
				updateChildNames(goodsClasss.get(i).getId(), oldName, newName);
			}
		}
	}

	/**
	 * 更新子分类levels和idpaths字段
	 * 
	 * @param goodsClass
	 */
	private void updateChildLevelAndPath(UnionGoodsClass goodsClass) {
		List<UnionGoodsClass> goodsClasss = findListByPid(goodsClass.getId());
		if (goodsClasss != null && goodsClasss.size() > 0) {
			for (int i = 0; i < goodsClasss.size(); i++) {
				UnionGoodsClass childClass = goodsClasss.get(i);

				childClass.setLevels(goodsClass.getLevels() + 1);

				childClass.setIdpaths(goodsClass.getIdpaths()
						+ childClass.getId() + ",");

				unionGoodsService.updateClassIds(childClass.getId(),
						childClass.getIdpaths(),
						findListByIds(goodsClass.getIdpaths()));

				unionGoodsClassDao.update(childClass);

				updateChildLevelAndPath(childClass);
			}
		}
	}

	/**
	 * 获取所有联盟商品分类
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findAll() {
		return unionGoodsClassDao.findAll();
	}

	/**
	 * 获取所有允许展示的联盟商品分类
	 * 
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findAllIsShow() {
		return unionGoodsClassDao.findAllIsShow();
	}

	/**
	 * 根据父id查询分类列表
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findListByPid(int pid) {
		return unionGoodsClassDao.findListByPid(pid);
	}

	/**
	 * 根据父id查询用于前端显示的分类列表
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public List<UnionGoodsClass> findFrontListByPid(int pid) {
		return unionGoodsClassDao.findFrontListByPid(pid);
	}

	/**
	 * 通过多个分类id获取分类名称集合,返回以“/”分割的字符串
	 * 
	 * @param ids
	 *            多个分类id，用“，”逗号分隔
	 * @return
	 */
	@Override
	public String findListByIds(String ids) {
		if (StringUtils.isBlank(ids)) {
			return "";
		}

		String[] idArray = ids.split(",");

		if (idArray == null || idArray.length == 0) {
			return "";
		}

		List<String> names = unionGoodsClassDao.findListByIds(idArray);
		if (names != null && names.size() > 0) {
			return StringUtils.join(names.toArray(), "/");
		}
		return "";
	}
}
