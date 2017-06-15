package com.Vshop.service.module.member.service;

import java.util.List;

import com.Vshop.core.entity.base.Member;
import com.Vshop.service.utils.page.Pager;

/**
 * 项目名称：Vshop-admin   
 * 类名称：MemberService   
 * 类描述：   
 * 修改备注：   
 * @version    
 */
public interface MemberService {


	/**
	 * @Title: findMemberList
	 * @Description: TODO (查询所有的会员信息，用分页显示)
	 * @param @param pager
	 * @param @return    设定文件
	 * @return List<Account>    返回类型
	 * @throws
	 */
	public List<Member> findMemberList(Pager pager);

    /**
     * 保存member信息
     * @param member
     */
	public void save(Member member);
    
	/**
     * 修改member信息
     * @param member
     */
	public void update(Member member);

	/**
	 * @Title: delete
	 * @Description: TODO (删除)
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void delete(Long id);

    
    /**
     * 根据会员id获取会员信息
     * @param memberId
     * @return
     */
    Member findById(Integer memberId);

    /**
     * 根据会员名查询会员信息
     * @param memberName
     * @return
     */
   public Member findMemberByName(String memberName);
   
   /**
    * 根据Member修改信息
    * @param member
    */
   public void updateMember(Member member);
   
   /**
    * 根据id获得会员
    * @param id
    * @return
    */
   public Member findMemberById(Integer id);
   
   /**
    * 根据memberId修改密码
    * @param newPasswd
    * @param memberId
    */
   public int updatePass(String newPasswd,Integer memberId);
   
   /**
    * 修改会员
    * @param data
    * @return
    */
	public int updateMember(String data); 
	
	/**
    * 根据memberId修改密码
    * @param newPasswd
    * @param memberId
    */
   public int updatePass(String memberPasswd,String newPasswd,Integer memberId);
   
   /**
    * 修改头像
    * @param path
    * @param memberId
    * @return
    */
   public int updateFace(String path,Integer memberId);
   
   /**
    * 根据email查询会员
    * @param email
    * @return
    */
   public Member findMemberByEmail(String email);
   
   /**
    * 根据memberMobile查询member表
    * @param memberMobile
    * @return
    */
   public Member findMemberByMobile(String memberMobile);
   
   /**
    * 仅仅修改当前登陆人的
    * @param memberMobile
    * @return
    */
   public void updateweiMember(Integer memberId);
   
	
	 /**
   	 * 获取总记录数
   	 * @param member
   	 * @return int
   	 */
	public int findMemberCount(Member member);
   
	/**
     * 根据会员信息查找
     * @param
     * @return
     */
    Member findMember(Member member);
}
