package com.Vshop.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.Vshop.core.entity.Type;
import com.Vshop.service.module.store.dao.TypeDao;
import com.Vshop.service.module.store.service.TypeService;
import com.Vshop.service.module.store.vo.TypesVo;
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
public class TypeServiceImpl implements TypeService {

	@Resource
	private TypeDao typeDao;

	@Override
	public List<TypesVo> queryClasssList(Pager pager) {

		// 对数据进行整理
		List<TypesVo> results = typeDao.queryClasssList(pager);
		List<TypesVo> returnResults = Lists.newArrayList();
		Integer pId = 0;
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getCId() == null) {
				returnResults.add(results.get(i));
			} else {
				if (pId == results.get(i).getPId()) {
					TypesVo cv = new TypesVo();
					cv.setCId(results.get(i).getCId());
					cv.setCName(results.get(i).getCName());
					cv.setCSort(results.get(i).getCSort());
					cv.setPId(results.get(i).getPId());
					returnResults.add(cv);
				} else {
					TypesVo cv = new TypesVo();
					cv.setPId(results.get(i).getPId());
					cv.setPName(results.get(i).getPName());
					cv.setPSort(results.get(i).getPSort());
					returnResults.add(cv);
					cv = new TypesVo();
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
	public List<Type> queryClasssParentList() {
		// TODO Auto-generated method stub
		return typeDao.queryClasssParentList();
	}

	public void save(Type classs) {
		typeDao.save(classs);
	}

	public void delete(Long id) {
		typeDao.delete(id);
	}

	public Type queryById(int id) {
		return typeDao.queryById(id);
	}

	@Override
	public void update(Type classs) {
		typeDao.update(classs);

	}

    /**
     * 查询子节点
     * @return
     */
    @Override
    public List<Type> queryClasssChildrenList(Type classs) {
        return typeDao.queryClasssChildrenList(classs);
    }

    /**
     * 去重
     *
     * @param classs
     * @return
     */
    @Override
    public int findCount(Type classs) {
        return typeDao.findCount(classs);
    }

	@Override
	public List<Type> findList(int parentid) {
		return typeDao.findList(parentid);
	}

	@Override
	public List<Type> findAllList() {
		return typeDao.findAllList();
	}

}
