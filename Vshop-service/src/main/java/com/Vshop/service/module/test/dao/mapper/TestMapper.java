package com.Vshop.service.module.test.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Account;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 * @author LH
 *
 */
@SqlMapper
public interface TestMapper {
    public int findAcctCount(Pager pager);
    public List<Account> findAcctList(Pager pager);
    
    public Account findAcctById(@Param("id") Long id);
    
    public void save(Account account);
    
    public void update(Account account);
    
    public void delete(@Param("id") Long id);
    
}
