package com.Vshop.service.module.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.service.utils.page.Pager;

@Repository
public interface SpecDao {
	
    /**
     * 保存
     * @param goods
     */
    public void save(Spec spec);
    
    /**
     * 修改
     * @param goodsSpec
     */
    public void update(Spec spec);

	public Spec findById(Integer spId);
	
    public List<Spec> findAllList(Spec spec);

    public List<Spec> findPageList(Pager pager);
    
    public Integer findPageListCount(Pager pager);
    
    public List<Spec> findListBySpId(Integer spId);
    
    public List<SpecVo> findSpecListBySpId(Integer spId);
    
    public List<SpecVo> findListByType(Integer typeId);
    
    public void deleteSpecBySpId(Integer spId);
}
