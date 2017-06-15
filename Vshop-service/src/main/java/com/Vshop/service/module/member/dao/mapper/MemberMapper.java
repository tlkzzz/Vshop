package com.Vshop.service.module.member.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.utils.page.Pager;


/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：MemberMapper   
 * 类描述：MemberMapper    
 * 创建人：sangyuchen   
 * 创建时间：2014年11月10日 上午9:38:26   
 * 修改人：sangyuchen   
 * 修改时间：2014年11月10日 上午9:38:26   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface MemberMapper {
    
    
    public List<Member> findMemberList(Pager pager);
    
    //public Member findMemberById(Long id);
    
    public void save(Member member);
    
    public void update(Member member);
    
    public void delete(Long id);

    /**
     * 查询
     * @param pager
     */
//    int findCount(Pager pager);
    
    Member findById(Integer memberId);

    /**
     * 根据会员id修改密码
     * @param newPasswd
     * @param memberId
     */
    public void updatePass(String newPasswd, Integer memberId);
    
    void updateMember(Member member);
    
    /**
     * 根据公司id查询用户
     * @return
     */
    List<Member> findByCompId(Integer companyId);
    
    
    /**
     * 删除用户信息
     * @param memberId
     */
    void delete(Integer memberId);
    
    /**
     * 用户移除角色
     * @param id
     */
    void deleteShopRoleMemberVo(Integer memberId,Integer isSorF);
    /**
     * 根据用户id删除用户与角色绑定 
     * @param id
     */
    public void deleteShopRoleMemberVoByMemberId(Integer id);
    
    /**
     * 根据membername查询member表
     * @param memberName
     * @return
     */
    public List<Member> findByMemberName(String memberName);
    
    /**
     * 根据memberphone查询member表
     * @param memberPhone
     * @return
     */
    Member findMemberByMobile(@Param("memberMobile") String memberMobile);
    /**
     * 根据ID修改密码
     * @param memberId
     * @param memberPasswd
     */
    void updatePassById(Member member);
    /**
     * 修改用户登录
     * @param memberName 用户名
     * @param password 密码
     */
    void updateLoginNum(String memberName);
    
    Member findMemberByEmail(@Param("memberEmail") String memberEmail);
    
    /**
     * 查询会员信息
     * @param memberName
     * @return
     */
    Member findMemberByName(@Param("memberName") String memberName);
    
    /**
   	 * 获取总记录数
   	 * @param member
   	 * @return
   	 */
   	public int findMemberCount(Member member);
    
    /**
     * 根据会员信息查找
     * @param
     * @return
     */
    Member findMember(Member member);
}
