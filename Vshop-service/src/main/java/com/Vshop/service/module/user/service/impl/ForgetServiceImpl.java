package com.Vshop.service.module.user.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.MailService;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.dao.MemberDao;
import com.Vshop.service.module.user.service.ForgetService;
import com.Vshop.service.utils.CommonConstants;

@Service
public class ForgetServiceImpl implements ForgetService{

	@Resource
    private MemberDao memberDao;
	
	@Autowired
    private MailService mailService;
	
	/**
	 * 发送邮件
	 * @param username
	 * @param email
	 * @throws Exception
	 */
	public int saveSendEmail(String memberName, String email) throws Exception {
		
		if(!DateUtils.paramLength(memberName) && !DateUtils.paramLength(email)) {
			Member member = memberDao.findMemberByName(memberName);
			if(member.getMemberEmail().equals(email)){
				String getNewPassword =  DateUtils.getRandomString(8);
				member.setMemberPasswd(getNewPassword);
				memberDao.updateMember(member);

				//发送邮件
				Map<String,Object> map = Maps.newHashMap();
	            map.put("username", member.getMemberName());
	            String url = CommonConstants.FRONT_SERVER+"/forget/activate?username="+member.getMemberName()+"&code="+getNewPassword;
	            map.put("url", url);
	            mailService.sendMailHtml(email,"找回密码",map,"findPassword.ftl");
	            return 1;
			}else{
				return 2;
			}
			
		}else{
			return 0;
		}
	}
}
