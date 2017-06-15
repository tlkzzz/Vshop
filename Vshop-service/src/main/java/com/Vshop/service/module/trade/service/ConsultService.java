package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.Consult;
import com.Vshop.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
public interface ConsultService {

    /**
     * 查询条数 指定店铺
     * @param pager
     * @return
     */
    int findCount(Consult consult);

    /**
     * 分页列表 指定店铺
     * @param pager
     * @return
     */
    List<Consult> findList(Pager pager);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    
    void updateReply(Consult consult);

    Consult findById(int id);
    /**
     * 保存
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
    
    /**
     * 根据id获取对象
     * @param consultId
     * @return
     */
    Consult findById(Integer consultId);
}
