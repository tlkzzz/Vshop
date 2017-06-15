package com.Vshop.service.module.store.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.Type;
import com.Vshop.service.module.store.dao.TypeDao;
import com.Vshop.service.module.store.dao.mapper.TypeMapper;
import com.Vshop.service.module.store.vo.TypesVo;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @author LH
 *
 */
@Repository
public class TypeDaoImpl implements TypeDao {
    @Resource
    private TypeMapper typeMapper;

    @Override
	public List<TypesVo> queryClasssList(Pager pager) {
		// TODO Auto-generated method stub
		return typeMapper.queryClasssList(pager);
	}


	@Override
	public List<Type> queryClasssParentList() {
		// TODO Auto-generated method stub
		return typeMapper.queryClasssParentList();
	}
	@Override
	public void save(Type classs){
		typeMapper.save(classs);
	}
	@Override
	public void delete(Long id){
		typeMapper.delete(id);
	}
	@Override
	public Type queryById(int id){
		return typeMapper.queryById(id);
	}


	@Override
	public void update(Type classs) {
		typeMapper.update(classs);
		
	}

    /**
     * 查询子节点
     *
     * @param id
     * @return
     */
    @Override
    public List<Type> queryClasssChildrenList(Type classs) {
        return typeMapper.queryClasssChildrenList(classs);
    }

    /**
     * 去重
     *
     * @param classs
     * @return
     */
    @Override
    public int findCount(Type classs) {
        return typeMapper.findCount(classs);
    }
	@Override
	public List<Type> findList(int parentid) {
		return typeMapper.findList(parentid);
	}


	@Override
	public List<Type> findAllList() {
		return typeMapper.findAllList();
	}
}
