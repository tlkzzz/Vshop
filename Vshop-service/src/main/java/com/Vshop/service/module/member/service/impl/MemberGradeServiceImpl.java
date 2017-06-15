package com.Vshop.service.module.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.MemberGrade;
import com.Vshop.service.module.member.dao.MemberGradeDao;
import com.Vshop.service.module.member.service.MemberGradeService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @author cgl
 * 2015年08月24日15:50:03
 * 会员等级
 */
@Service()
public class MemberGradeServiceImpl implements MemberGradeService {
	
	@Autowired
	private MemberGradeDao memberGradeDao;
	
	/**
	 * 查询默认的会员等级
	 */
	public MemberGrade findDefaultGrade(){
		return memberGradeDao.findDefaultGrade();
	}

	/**
	 * 分页查询count
	 */
	public Integer findMemberGradeCount(MemberGrade memberGrade){
		return memberGradeDao.findMemberGradeCount(memberGrade);
	}
	
	/**
	 * 分页list
	 */
	public List<MemberGrade> findMemberGradePageList(Pager pager){
		return memberGradeDao.findMemberGradePageList(pager);
	}
	
	/**
	 * 根据ID查询实体
	 */
	public MemberGrade findMembeGraderById(Integer gradeId){
		return memberGradeDao.findMembeGraderById(gradeId);
	}
	
	/**
	 * 保存
	 */
	public void save(MemberGrade memberGrade){
		memberGradeDao.save(memberGrade);
		/**判断是否设置为默认*/
		if(memberGrade.getIsDefault() == 1){
			updateDefault(memberGrade.getGradeId());
		}
		
	}
	
	/**
	 * 修改
	 */
	public void update(MemberGrade memberGrade){
		memberGradeDao.update(memberGrade);
		/**判断是否设置为默认*/
		if(memberGrade.getIsDefault() == 1){
			updateDefault(memberGrade.getGradeId());
		}
	}
	
	
	/**
	 * 修改指定id的会员等级为默认的等级
	 */
	public void updateDefault(Integer gradeId){
		
		/**将所有的等级设为不是默认的0*/
		memberGradeDao.updateDefault(gradeId);
		
		/**设置当前id的等级为默认的值*/
//		MemberGrade memberGrade = new MemberGrade();
//		
//		memberGrade.setGradeId(gradeId);
//		memberGrade.setIsDefault(1);
//		
//		memberGradeDao.update(memberGrade);
	}
	
	/**
	 * 删除
	 */
	public void delete(Integer gradeId){
		memberGradeDao.delete(gradeId);
	}
}
