package com.Vshop.service.module.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easemob.server.example.httpclient.api.EasemobIMUsers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.Digests;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.MemberGrade;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.member.dao.MemberDao;
import com.Vshop.service.module.member.service.MemberGradeService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：MemberServiceImpl   
 * 类描述：service实现类
 * 修改备注：   
 * @version    
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
    
	@Resource
	private MemberGradeService memberGradeService;
	
	@Resource
	private SettingService settingService;
	

	/**
	 * 
	 * @Title: findMemberList
	 * @Description: TODO (查询所有的会员信息，用分页显示)
	 * @param @param pager
	 * @param @return    设定文件
	 * @return List<Account>    返回类型
	 * @throws
	 */
	public List<Member> findMemberList(Pager pager) {
		return memberDao.findMemberList(pager);
	}

	/**
     * 保存member信息
     * @param member
     */
	@Override
	public void save(Member member) {
		String code = DateUtils.getRandomString(8);
		member.setSignCode(code);
        member.setSignCodeState(0);
        member.setMemberState(1);//会员的开启状态 1为开启 0为关闭
        //member.setMemberTruename("");//会员真实姓名
        if(StringUtils.isEmpty(member.getMemberAvatar())){
           member.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
        }
        member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));
        member.setCreateTime(System.currentTimeMillis());//会员创建时间
        
        //获取成功注册等级积分
		String rankSettingPoints = settingService.findByNameAndCode("points", "register_rank");
		//获取成功注册消费积分
		String consSettingPoints = settingService.findByNameAndCode("points", "register_cons");
		if(StringUtils.isNotBlank(rankSettingPoints)){
			member.setMemberRankPoints(Integer.valueOf(rankSettingPoints));
		}else{
			member.setMemberRankPoints(0);
		}
		if(StringUtils.isNotBlank(consSettingPoints)){
			member.setMemberConsumePoints(Integer.valueOf(consSettingPoints));
		}else{
			member.setMemberConsumePoints(0);
		}
		
        MemberGrade memberGrade=memberGradeService.findDefaultGrade();
        if(memberGrade!=null){
        	 member.setMemberGradeId(memberGrade.getGradeId());//默认会员等级
        }
		memberDao.save(member);
		
//		ObjectNode node = EasemobIMUsers.createNewIMUserSingle(member.getMemberName(), "lmshopb2b2c");
//		if (null != node) {
//            System.out.println("EASEMOBIMUSERS-注册IM用户[单个]: " + node.toString());
//        }else{
//        	System.out.println("EASEMOBIMUSERS-注册IM用户[单个]:失败");
//        }
	}
	/**
     * 修改member信息
     * @param member
     */
	@Override
	public void update(Member member) {
		memberDao.update(member);
	}
    
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO (删除)
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void delete(Long id) {
		memberDao.delete(id);
	}

    /**
     * 根据会员id获取会员信息
     * @param memberId
     * @return
     */
	@Override
	public Member findById(Integer memberId) {
		return memberDao.findById(memberId);
	}
    
	/**
     * 根据会员名查询会员信息
     * @param memberName
     * @return
     */
	@Override
	public Member findMemberByName(String memberName) {
		return memberDao.findMemberByName(memberName);
	}
    
	/**
    * 根据Member修改信息
    * @param member
    */
   public void updateMember(Member member){
	   if(StringUtils.isNotEmpty(member.getMemberPasswd())){
		   member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));//修改密码  
	   }
	   memberDao.updateMember(member);
   }

	@Override
	public Member findMemberById(Integer id) {
		return memberDao.findMemberById(id);
	}


	@Override
	public int updatePass(String newPasswd, Integer memberId) {
		Member member =  memberDao.findMemberById(memberId);
		try {
			 member.setMemberPasswd(Digests.entryptPassword(newPasswd));
			 memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			System.out.println("更新密码失败"+e.getMessage());
			return 0;
		}
	}

	@Override
	public int updateMember(String data) {
		int result = 0 ;
		try {
			Member member = JsonUtils.fromJson(data, Member.class);
			if(member.getMemberId()!=null){
				memberDao.updateMember(member);
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("保存失败"+e.getMessage());
		}
		return result;
	}
	
   /**
    * 根据memberId修改密码
    * @param newPasswd
    * @param memberId
    */
	@Override
	public int updatePass(String memberPasswd,String newPasswd,Integer memberId){
		 Member member =  memberDao.findMemberById(memberId);
		 if(!Digests.validatePassword(memberPasswd, member.getMemberPasswd())){
			 return 2;
		 }else{
			 try {
				 member.setMemberPasswd(new Md5Hash(newPasswd).toString());
				 memberDao.updateMember(member);
				return 1;
			} catch (Exception e) {
				System.out.println("更新密码失败"+e.getMessage());
				return 0;
			}
		 }
	 }
    
  /**
    * 修改头像
    * @param path
    * @param memberId
    * @return
    */
	@Override
	public int updateFace(String path, Integer memberId) {
		//Member member =  memberDao.findMemberById(memberId);
		Member member=new Member();
		member.setMemberId(memberId);
		try {
			member.setMemberAvatar(path);
			memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("更新密码失败"+e.getMessage());
			System.out.println("更新头像失败"+e.getMessage());
			return 0;
		}
	}
    
   /**
    * 根据email查询会员
    * @param email
    * @return
    */
	@Override
	public Member findMemberByEmail(String email) {
		return memberDao.findMemberByEmail(email);
	}
    
  /**
    * 根据memberMobile查询member表
    * @param memberMobile
    * @return
    */
	@Override
	public Member findMemberByMobile(String memberMobile) {
		return memberDao.findMemberByMobile(memberMobile);
	}
    
   /**
    * 仅仅修改当前登陆人的
    * @param memberMobile
    * @return
    */
	@Override
	public void updateweiMember(Integer memberId) {
		Member member=memberDao.findById(memberId);
		Member member9=new Member();
		if(member!=null){
			member9.setMemberId(member.getMemberId());
			member9.setMemberOldLoginTime(member.getMemberLoginTime());//上次登陆时间
			member9.setMemberLoginTime(System.currentTimeMillis());//最后登陆时间
			member9.setMemberLoginNum(1);//登陆次数
			memberDao.updateMember(member9);
		}
	}

	
	/**
	 * 获取总记录数
	 * @param member
	 * @return
	 */
	public int findMemberCount(Member member) {
		return memberDao.findMemberCount(member);
	}
	/**
     * 根据会员信息查找
     * @param
     * @return
     */
	@Override
	public Member findMember(Member member) {
		return memberDao.findMember(member);
	}
}
