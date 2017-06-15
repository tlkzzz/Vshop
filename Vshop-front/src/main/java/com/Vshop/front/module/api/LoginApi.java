package com.Vshop.front.module.api;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easemob.server.example.httpclient.api.EasemobIMUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.Vshop.core.common.Digests;
import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.common.PinYinUtil;
import com.Vshop.core.entity.apibean.MemberApiBean;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.member.service.MemberService;

/**
 * 用户有登录接口
 * @author lkang
 * @date 2015-07-16
 */
@Controller
@RequestMapping("/loginapi")
public class LoginApi extends BaseApi {
	
	@Resource
	private MemberService memberService;
	
	/**
	 * 登录api
	 * @author kviuff
	 * @param username
	 * @param password
	 * @param captcha
	 * @param remember_me
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public JSONObject loginIn(
			@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "remember_me", required = false) String remember_me){
		jsonObj = new JSONObject();
        try {
        	/*Subject subject = SecurityUtils.getSubject();
        	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if ("1".equals(remember_me)) {
                token.setRememberMe(true);
            }
            subject.login(token);*/
            Member m = memberService.findMemberByName(username);
            if(null == m){
            	jsonObj.put("result",0);
            	jsonObj.put("msg", "用户名错误");
            }else{
            	boolean isValidate = Digests.validatePassword(password,m.getMemberPasswd());
            	if(isValidate){
            		MemberApiBean bean = new MemberApiBean();
            		MyBeanUtils.copyBeanNotNull2Bean(m, bean);
            		bean.setMemberNameCode(PinYinUtil.getPingYin(bean.getMemberName()));
            		memberService.updateweiMember(m.getMemberId());//修改登陆者的登陆次数，登陆时间
            		jsonObj.put("result", 1);
            		jsonObj.put("msg", "成功登录");
            		jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
            		ObjectNode getIMUsersByUserNameNode = EasemobIMUsers.getIMUsersByUserName(username);
            		ObjectMapper mapper = new ObjectMapper();  
                    try {
        				JsonNode rootNode = mapper.readTree(getIMUsersByUserNameNode.toString());
        				String status = rootNode.path("statusCode").toString();
        				if("404".equals(status)){
        					ObjectNode node = EasemobIMUsers.createNewIMUserSingle(username, "lmshopb2b2c");
        					if (null != node) {
        			            System.out.println("EASEMOBIMUSERS-注册IM用户[单个]: " + node.toString());
        			        }else{
        			        	System.out.println("EASEMOBIMUSERS-注册IM用户[单个]:失败");
        			        }
        				}
        			} catch (JsonProcessingException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			} 
            	}else{
            		jsonObj.put("result",0);
            		jsonObj.put("msg", "密码错误");
            	}
            }
        } catch (UnknownAccountException e) {
        	jsonObj.put("result",0);
        	jsonObj.put("msg", "用户名/密码错误");
        } catch (IncorrectCredentialsException e) {
        	jsonObj.put("result",0);
        	jsonObj.put("msg", "用户名/密码错误");
        } catch (Exception e) {
        	e.printStackTrace();
            jsonObj.put("result",0);
            jsonObj.put("msg", "服务器导常");
        }
		
		return jsonObj;
	}
	
	/**
	 * 用户登出操作
	 * @author kviuff
	 * @return
	 */
	@RequestMapping("loginout")
	@ResponseBody
	public JSONObject loginOut(){
		jsonObj = new JSONObject();
		try {
			Subject subject = SecurityUtils.getSubject();
	        subject.logout();
	        jsonObj.put("result",1);
            jsonObj.put("msg", "成功登出");
		} catch (Exception e) {
			jsonObj.put("result",0);
            jsonObj.put("msg", "服务器导常");
		}
		
		return jsonObj;
	}
}
