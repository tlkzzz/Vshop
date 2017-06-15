package com.Vshop.service.module.test.service;


import java.util.List;

import com.Vshop.core.entity.base.Account;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @author LH
 *
 */
public interface TestService {

	/**
	 * 获取总记录数
	 * @param pager
	 * @return
	 */
    public int findAcctCount(Pager pager);

    /**
     * 查询所有的用户信息，用分页显示
     * @return
     * @Adder by Administrator 2013-2-19 上午11:49:22
     */
    public List<Account> findAcctList(Pager pager);
    
    
    public Account findAcctById(Long id); 
    
    public void save(Account account);
    
    public void update(Account account);
    
    public void delete(Long id);
    
}
