package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Consult;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */

@SqlMapper
public interface ConsultMapper {

    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Consult consult);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<Consult> findList(Pager pager);

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") int id);
    
    void updateReply(Consult consult);

    Consult findById(@Param("id")int id);
    
    /**
     *  保存
     * @param consult
     */
    public void save(Consult consult);
    
    
    /**
     * 查询条数
     * @param pager
     * @return
     */
    int findMemberCount(Consult consult);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<Consult> findMemberList(Pager pager);
}
