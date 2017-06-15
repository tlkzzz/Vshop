/**
 * 
 */
package com.Vshop.service.module.feedback.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Feedback;
import com.Vshop.service.module.feedback.dao.FeedbackDao;
import com.Vshop.service.module.feedback.dao.mapper.FeedbackMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: feedbackDaoImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
	
	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public void save(Feedback feedback) {
		feedbackMapper.save(feedback);
	}

	@Override
	public void delete(int id) {
		feedbackMapper.delete(id);
	}

	@Override
	public void update(Feedback feedback) {
		feedbackMapper.update(feedback);
	}

	@Override
	public Feedback findbyId(int id) {
		return feedbackMapper.findbyId(id);
	}

	@Override
	public List<Feedback> findBylist() {
		return feedbackMapper.findBylist();
	}

	@Override
	public List<Feedback> findBylist(Pager pager) {
		return feedbackMapper.findPageList(pager);
	}

	@Override
	public int findCount(Feedback feedback) {
		return feedbackMapper.findCount(feedback);
	}

	@Override
	public void saveContact(Feedback feedback) {
		feedbackMapper.saveContact(feedback);
	}

}
