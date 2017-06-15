package com.Vshop.front.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.Vshop.core.cache.jedis.JedisUtils;

/**
 * 这种方法的测试类   几乎相当于重启项目 因为要扫描注解  然后注入容器
 * @TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true) 
 * transactionManager的默认取值是"transactionManager"，
 * defaultRollback的默认取值是true，当然，你也可以改成false。
 * true表示测试不会对数据库造成污染,false的话当然就会改动到数据库中了。
 * 在方法名上添加@Rollback(false)表示这个测试用例不需要回滚。
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/applicationContext*.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SpringTxTestCase {

	//your service Autowired
//	@Autowired
//	private MemberService memberService;
//
//	@Test
//	public void search(){
	
//	}
	
	@Test
	public void redis(){
		String value=JedisUtils.get("linjm");
		System.out.println("value===="+value);
		
	}
}
