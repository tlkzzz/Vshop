package com.Vshop.front.module.easemob.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.httpclient.api.EasemobMessages;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.store.service.StoreService;


/**
 * 项目名称：Vshop-front
 * 类名称：SendMsgController
 * 类描述：环信通讯
 * 创建人：KVIUFF
 * 创建时间：2015年9月17日 下午5:34:55
 */
@Controller
@RequestMapping("/easemob")
@Slf4j
public class EasemobController {
	
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	@Resource
    private StoreService storeService;
	
	/**
     * 加载会话界面
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: main
     */
    @RequestMapping(value = "/msgview")
    public String msgview(
    		Model model,
    		@RequestParam(value = "touser") String storeId
    		) {
    	boolean isLogin = CacheUtils.isLogin();
		model.addAttribute("isLogin", isLogin);
		if(isLogin){
			Member m = CacheUtils.getCacheUser().getMember();
			model.addAttribute("curUserId", m.getMemberName());
			model.addAttribute("password", m.getMemberPasswd());
		}
		Store store = storeService.findById(Integer.parseInt(storeId));
    	model.addAttribute("curChatUserId", storeId);
    	model.addAttribute("curChatUserName", store.getMemberName());
    	model.addAttribute("appkey", Constants.APPKEY);
        return "/easemob/msgview";
    }
	
	/**
	 * 发送消息-给用户发一条文本消息
	 * @param touser  发送给的用户
	 * @param msg     发送的消息
	 * @return
	 */
	@RequestMapping("sendOneTextMsg")
	@ResponseBody
	public Map<String, Object> sendOneTextMsg(
			@RequestParam(value = "touser") String touser,
			@RequestParam(value = "msg") String msg
			){
		Map<String,Object> map = new HashMap<String, Object>();
		ObjectNode ext = factory.objectNode();
		ArrayNode targetusers = factory.arrayNode();
        targetusers.add(touser);
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msg);
        txtmsg.put("type","txt");
        Member m = CacheUtils.getCacheUser().getMember();
		ObjectNode sendTxtMessageusernode = EasemobMessages.sendMessages("users", targetusers, txtmsg, m.getMemberName(), ext);
		if (null != sendTxtMessageusernode) {
			log.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
			map.put("success", true);
			map.put("msg", "发送成功");
        }
		return map;
	}
	
	
	public Map<String, Object> sendOneImgMsg(
			@RequestParam(value = "touser") String touser,
			@RequestParam(value = "msg") String msg
			){
		Map<String,Object> map = new HashMap<String, Object>();
		ObjectNode ext = factory.objectNode();
		ArrayNode targetusers = factory.arrayNode();
        targetusers.add(touser);
        ObjectNode txtmsg = factory.objectNode();
        txtmsg.put("msg", msg);
        txtmsg.put("type","txt");
        Member m = CacheUtils.getCacheUser().getMember();
		ObjectNode sendTxtMessageusernode = EasemobMessages.sendMessages("users", targetusers, txtmsg, m.getMemberName(), ext);
		if (null != sendTxtMessageusernode) {
			log.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
			map.put("success", true);
			map.put("msg", "发送成功");
        }
		return map;
	}
	
	
}
