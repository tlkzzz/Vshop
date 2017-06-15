package com.Vshop.service.module.member.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.member.dao.MemberDao;
import com.Vshop.service.module.member.dao.mapper.MemberMapper;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：MemberDaoImpl   
 * 类描述：   DAO 实现类
 * 修改备注：   
 * @version    
 */
@Repository
public class MemberDaoImpl extends BaseDao implements MemberDao {
	
    @Autowired
    private MemberMapper memberMapper;
   

    /**
     * 获取结果集
     */
    public List<Member> findMemberList(Pager pager) {
        return memberMapper.findMemberList(pager);
    }

	public void save(Member member) {
		memberMapper.save(member);
	}

	public void update(Member member) {
		memberMapper.updateMember(member);
	}

	public void delete(Long id) {
		memberMapper.delete(id);
	}

    /**
     * 查询
     * @param pager
     */
//    public int findCount(Pager pager) {
//        return memberMapper.findCount(pager);
//    }
    
    public Member findById(Integer memberId) {
        return memberMapper.findById(memberId);
    }

    
    /**
     * 根据Member修改信息
     * @param member
     */
    public void updateMember(Member member){
    	 memberMapper.updateMember(member);
    }

	public Member findMemberById(Integer id) {
		return memberMapper.findById(id);
	}

	public Member findMemberByEmail(String memberEmail) {
		return memberMapper.findMemberByEmail(memberEmail);
	}

	public Member findMemberByName(String memberName) {
		return memberMapper.findMemberByName(memberName);
	}

	public Member findMemberByMobile(String memberMobile) {
		return memberMapper.findMemberByMobile(memberMobile);
	}
    

	public int findMemberCount(Member member){
		return memberMapper.findMemberCount(member);
	}
    
	  /**
     * 根据会员信息查找
     * @param
     * @return
     */
	@Override
	public Member findMember(Member member) {
		return memberMapper.findMember(member);
	}
	

}
