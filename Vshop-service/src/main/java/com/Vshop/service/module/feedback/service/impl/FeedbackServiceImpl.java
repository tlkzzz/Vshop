/**
 * 
 */
package com.Vshop.service.module.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Feedback;
import com.Vshop.service.module.feedback.dao.FeedbackDao;
import com.Vshop.service.module.feedback.service.FeedbackService;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: FeedbackServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年8月25日
 * @version 1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void save(Feedback feedback) {
		feedbackDao.save(feedback);
	}

	@Override
	public void delete(int id) {
		feedbackDao.delete(id);
		
	}

	@Override
	public void update(Feedback feedback) {
		feedbackDao.update(feedback);
	}

	@Override
	public Feedback findbyId(int id) {
		return feedbackDao.findbyId(id);
	}

	@Override
	public List<Feedback> findBylist() {
		return feedbackDao.findBylist();
	}

	@Override
	public List<Feedback> findBylist(Pager pager) {
		return feedbackDao.findBylist(pager);
	}

	@Override
	public int findCount(Feedback feedback) {
		return feedbackDao.findCount(feedback);
	}

	@Override
	public void saveContact(Feedback feedback) {
		feedbackDao.saveContact(feedback);
	}

}
