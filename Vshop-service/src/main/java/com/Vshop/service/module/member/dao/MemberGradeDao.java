package com.Vshop.service.module.member.dao;

import java.util.List;

import com.Vshop.core.entity.base.MemberGrade;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @author cgl
 * 2015年08月24日15:50:03
 * 会员等级
 */
public interface MemberGradeDao {
	
	/**
	 * 查询默认的会员等级
	 */
	public MemberGrade findDefaultGrade();

	/**
	 * 分页查询count
	 */
	public Integer findMemberGradeCount(MemberGrade memberGrade);
	
	/**
	 * 分页list
	 */
	public List<MemberGrade> findMemberGradePageList(Pager pager);
	
	/**
	 * 根据ID查询实体
	 */
	public MemberGrade findMembeGraderById(Integer gradeId);
	
	/**
	 * 保存
	 */
	public void save(MemberGrade memberGrade);
	
	/**
	 * 修改
	 */
	public void update(MemberGrade memberGrade);
	
	/**
	 * 修改所有的默认值为0
	 */
	public void updateDefault(Integer gradeId);
	
	/**
	 * 删除
	 */
	public void delete(Integer gradeId);
}
