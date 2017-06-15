package com.Vshop.service.module.goods.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Spec;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;
@SqlMapper
public interface SpecMapper {
    /**
     * 保存
     * @param goodsSpec
     */
    void save(Spec spec);
    
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
    
    public void deleteSpecValueBySpId(Integer spId);
}
