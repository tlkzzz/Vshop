package com.Vshop.service.module.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Account;
import com.Vshop.service.module.test.dao.TestDao;
import com.Vshop.service.module.test.service.TestService;
import com.Vshop.service.utils.page.Pager;

/**
 * LH
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    
    /**
     * 获取总记录数
     */
    public int findAcctCount(Pager pager) {
        return testDao.findAcctCount(pager);
    }

    /**
     * 查询所有的用户，用分页显示
     */
    public List<Account> findAcctList(Pager pager) {
        return testDao.findAcctList(pager);
    }

	@Override
	public Account findAcctById(Long id) {
		// TODO Auto-generated method stub
		return testDao.findAcctById(id);
	}

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		testDao.save(account);
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		testDao.update(account);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		testDao.delete(id);
	}
   
}
