package com.Vshop.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.Vshop.core.entity.Classs;
import com.Vshop.service.module.store.dao.ClasssDao;
import com.Vshop.service.module.store.service.ClasssService;
import com.Vshop.service.module.store.vo.ClasssVo;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 项目名称：Vshop-admin 类名称：ClasssServiceImpl 类描述： 创建人：weiyue 创建时间：2014年11月5日
 * 下午10:43:18 修改人：weiyue 修改时间：2014年11月5日 下午10:43:18 修改备注：
 * 
 * @version
 * 
 */
@Service
public class ClasssServiceImpl implements ClasssService {

	@Resource
	private ClasssDao classsDao;

	@Override
	public List<ClasssVo> queryClasssList(Pager pager) {

		// 对数据进行整理
		List<ClasssVo> results = classsDao.queryClasssList(pager);
		List<ClasssVo> returnResults = Lists.newArrayList();
		Integer pId = 0;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getCId() == null) {
				returnResults.add(results.get(i));
			} else {
				if (pId == results.get(i).getPId()) {
					ClasssVo cv = new ClasssVo();
					cv.setCId(results.get(i).getCId());
					cv.setCName(results.get(i).getCName());
					cv.setCSort(results.get(i).getCSort());
					cv.setPId(results.get(i).getPId());
					returnResults.add(cv);
				} else {
					ClasssVo cv = new ClasssVo();
					cv.setPId(results.get(i).getPId());
					cv.setPName(results.get(i).getPName());
					cv.setPSort(results.get(i).getPSort());
					returnResults.add(cv);
					cv = new ClasssVo();
					cv.setCId(results.get(i).getCId());
					cv.setCName(results.get(i).getCName());
					cv.setCSort(results.get(i).getCSort());
					cv.setPId(results.get(i).getPId());
					returnResults.add(cv);
				}
				pId = results.get(i).getPId();

			}

		}

		return returnResults;
	}

	@Override
	public List<Classs> queryClasssParentList() {
		// TODO Auto-generated method stub
		return classsDao.queryClasssParentList();
	}

	public void save(Classs classs) {
		classsDao.save(classs);
	}

	public void delete(Long id) {
		classsDao.delete(id);
	}

	public Classs queryById(int id) {
		return classsDao.queryById(id);
	}

	@Override
	public void update(Classs classs) {
		classsDao.update(classs);

	}

    /**
     * 查询子节点
     * @return
     */
    @Override
    public List<Classs> queryClasssChildrenList(Classs classs) {
        return classsDao.queryClasssChildrenList(classs);
    }

    /**
     * 去重
     *
     * @param classs
     * @return
     */
    @Override
    public int findCount(Classs classs) {
        return classsDao.findCount(classs);
    }

	@Override
	public List<Classs> findList(int parentid) {
		return classsDao.findList(parentid);
	}

}
