package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.Classs;
import com.Vshop.service.module.store.dao.ClasssDao;
import com.Vshop.service.module.store.dao.mapper.ClasssMapper;
import com.Vshop.service.module.store.vo.ClasssVo;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @author LH
 *
 */
@Repository
public class ClasssDaoImpl implements ClasssDao {
    @Resource
    private ClasssMapper classsMapper;

    @Override
	public List<ClasssVo> queryClasssList(Pager pager) {
		// TODO Auto-generated method stub
		return classsMapper.queryClasssList(pager);
	}


	@Override
	public List<Classs> queryClasssParentList() {
		// TODO Auto-generated method stub
		return classsMapper.queryClasssParentList();
	}
	@Override
	public void save(Classs classs){
		classsMapper.save(classs);
	}
	@Override
	public void delete(Long id){
		classsMapper.delete(id);
	}
	@Override
	public Classs queryById(int id){
		return classsMapper.queryById(id);
	}


	@Override
	public void update(Classs classs) {
		classsMapper.update(classs);
		
	}

    /**
     * 查询子节点
     *
     * @param id
     * @return
     */
    @Override
    public List<Classs> queryClasssChildrenList(Classs classs) {
        return classsMapper.queryClasssChildrenList(classs);
    }

    /**
     * 去重
     *
     * @param classs
     * @return
     */
    @Override
    public int findCount(Classs classs) {
        return classsMapper.findCount(classs);
    }
	@Override
	public List<Classs> findList(int parentid) {
		return classsMapper.findList(parentid);
	}
}
