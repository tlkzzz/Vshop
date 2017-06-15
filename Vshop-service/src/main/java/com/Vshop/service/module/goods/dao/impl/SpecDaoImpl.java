package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.service.module.goods.dao.SpecDao;
import com.Vshop.service.module.goods.dao.mapper.SpecMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class SpecDaoImpl implements SpecDao {

    @Resource
    private SpecMapper specMapper;
    
    /**
     * 保存
     * @param goodSpec
     */
    @Override
    public void save(Spec spec) {
    	specMapper.save(spec);
    }

    /**
     * 修改
     * @param goodsSpec
     */
    @Override
    public void update(Spec spec) {
    	specMapper.update(spec);
    }
    
	@Override
	public Spec findById(Integer spId) {
		// TODO Auto-generated method stub
		return specMapper.findById(spId);
	}

	@Override
	public List<Spec> findAllList(Spec spec) {
		// TODO Auto-generated method stub
		return specMapper.findAllList(spec);
	}

	@Override
	public List<Spec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return specMapper.findPageList(pager);
	}
	
	@Override
    public Integer findPageListCount(Pager pager){
		
		return specMapper.findPageListCount(pager);
    }

	@Override
	public List<Spec> findListBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return specMapper.findListBySpId(spId);
	}
 
	@Override
	public List<SpecVo> findSpecListBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return specMapper.findSpecListBySpId(spId);
	}
	
	@Override
	public List<SpecVo> findListByType(Integer typeId) {
		// TODO Auto-generated method stub
		return specMapper.findListByType(typeId);
	}
	
	@Override
    public void deleteSpecBySpId(Integer spId){
		// TODO Auto-generated method stub
		specMapper.deleteSpecBySpId(spId);
		specMapper.deleteSpecValueBySpId(spId);
    }

}
