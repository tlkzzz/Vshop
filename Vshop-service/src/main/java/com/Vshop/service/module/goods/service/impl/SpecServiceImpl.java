package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.base.SpecValue;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.goods.dao.SpecDao;
import com.Vshop.service.module.goods.dao.SpecValueDao;
import com.Vshop.service.module.goods.service.SpecService;
import com.Vshop.service.utils.page.Pager;

@Service
public class SpecServiceImpl implements SpecService{

    @Resource
    private SpecDao specDao;
    
    @Resource
    private SpecValueDao specValueDao;
    
    /**
     * 保存
     * @param goodsSpec
     */
    @Override
    public void save(Spec spec, String specValues) {
        specDao.save(spec);
        //得到sp_id
        Integer spId = spec.getSpId();
        //删除spId下面所有的规格值
        specValueDao.deleteBySpId(spId);
        //存入规格值
        //首先得到规格值得list
        List<Object> list = JsonUtils.readJsonList(specValues, SpecValue.class);
        //循环存入shop_spec_value表中
        for(int i = 0; i < list.size(); i++){
        	//得到规格值实体
        	SpecValue specValue = (SpecValue) list.get(i);
        	//设置spId
        	specValue.setSpId(spId);
        	//存储
        	specValueDao.save(specValue);
        }
    }
    
    /**
     * 修改
     * @param goodsSpec
     */
    @Override
    public void update(Spec spec, String specValues) {
        specDao.update(spec);
        //得到sp_id
        Integer spId = spec.getSpId();
        //删除spId下面所有的规格值
        specValueDao.deleteBySpId(spId);
        //存入规格值
        //首先得到规格值得list
        List<Object> list = JsonUtils.readJsonList(specValues, SpecValue.class);
        //循环存入shop_spec_value表中
        for(int i = 0; i < list.size(); i++){
        	//得到规格值实体
        	SpecValue specValue = (SpecValue) list.get(i);
        	//设置spId
        	specValue.setSpId(spId);
        	//存储
        	specValueDao.save(specValue);
        }
    }
    
    /**
     * 修改
     * @param goodsSpec
     */
    @Override
    public void update(Spec spec) {
    	specDao.update(spec);
    }

	@Override
	public Spec findById(Integer spId) {
		// TODO Auto-generated method stub
		return specDao.findById(spId);
	}

	@Override
	public List<Spec> findAllList(Spec spec) {
		// TODO Auto-generated method stub
		return specDao.findAllList(spec);
	}

	@Override
	public List<Spec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return specDao.findPageList(pager);
	}
	
	@Override
    public Integer findPageListCount(Pager pager){
		// TODO Auto-generated method stub
		return specDao.findPageListCount(pager);
    }

	@Override
	public List<Spec> findListBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return specDao.findListBySpId(spId);
	}
 
	@Override
	public List<SpecVo> findSpecListBySpId(Integer spId) {
		// TODO Auto-generated method stub
		return specDao.findSpecListBySpId(spId);
	}
	
	@Override
	public List<SpecVo> findListByType(Integer typeId) {
		// TODO Auto-generated method stub
		return specDao.findListByType(typeId);
	}

	@Override
	public void deleteSpecBySpId(Integer spId) {
		specDao.deleteSpecBySpId(spId);
	}

}
