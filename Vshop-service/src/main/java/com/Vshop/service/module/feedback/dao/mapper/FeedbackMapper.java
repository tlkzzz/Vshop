/**
 * 
 */
package com.Vshop.service.module.feedback.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.base.Feedback;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: FeedbackMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */

@SqlMapper
public interface FeedbackMapper {
	
	/**
	 * 保存
	 */
	void save(Feedback feedback);
	/**
	 * 删除
	 * @param id
	 */
	void delete(int id);
	/**
	 * 修改
	 * @param feedback
	 */
	void update(Feedback feedback);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	Feedback findbyId(int id);
	/**
	 *  获取集合
	 * @return
	 */
	List<Feedback> findBylist();
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	List<Feedback> findPageList(Pager pager);
	/**
	 * 总条数
	 * @param feedback
	 * @return
	 */
	int findCount(Feedback feedback);
	/**
	 * 保存联系我们内容
	 */
	void saveContact(Feedback feedback);

}
