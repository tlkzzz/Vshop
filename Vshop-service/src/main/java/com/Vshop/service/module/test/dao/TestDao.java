package com.Vshop.service.module.test.dao;


import java.util.List;

import com.Vshop.core.entity.base.Account;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 */
public interface TestDao {
    public int findAcctCount(Pager pager);
    public List<Account> findAcctList(Pager pager);
    
    public Account findAcctById(Long id);
    
    public void save(Account account);
    
    public void update(Account account);
    
    public void delete(Long id);
    
}
