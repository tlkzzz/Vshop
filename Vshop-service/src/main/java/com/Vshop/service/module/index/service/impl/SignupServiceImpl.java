package com.Vshop.service.module.index.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.MailService;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.index.service.SignupService;
import com.Vshop.service.module.member.dao.MemberDao;
import com.Vshop.service.utils.CommonConstants;

/**
 * Created by rabook on 2015/3/27.
 */
@Service
public class SignupServiceImpl implements SignupService{

    @Resource
    private MemberDao memberDao;

    @Resource
    private MailService mailService;

    @Override
    public void saveSendEmail(Member member) {
	    //发送邮件
		String code = DateUtils.getRandomString(8);
	    Map<String,Object> map = Maps.newHashMap();
	    map.put("username", member.getMemberName());
	    String url = CommonConstants.FRONT_SERVER+"/signResult?userName="+member.getMemberName()+"&code="+code;
	    map.put("url", url);
	    mailService.sendMailHtml(member.getMemberEmail(),"注册激活",map,"register.ftl");
    }

    @Override
    public String updateSign(String userName, String code) {

        Member member = memberDao.findMemberByName(userName);
        member.setMemberState(1);
        member.setSignCodeState(1);
        memberDao.updateMember(member);
        if(code.equals(member.getSignCode()) && member.getSignCodeState() == 0){
            return "恭喜您注册成功";
        }else{
            return "您好！你的验证码已经过期！";
        }
    }
}
