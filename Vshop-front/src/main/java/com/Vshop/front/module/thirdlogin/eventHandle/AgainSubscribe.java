package com.Vshop.front.module.thirdlogin.eventHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.EventType;


/**
 * 用户再次关注
 * @author coolzlay
 *
 */
@Component("againSubscribe")
public class AgainSubscribe  extends BaseEventHandle implements EventHandle{
	private final static Logger log= Logger.getLogger(AgainSubscribe.class);
	
	//@Autowired(required = false)
	//private ActivityFirstSubscribeUserService activityFirstSubscribeUserService; //首次关注用户表
	@Override
	public BaseMsg handle(BaseEvent event) {
		
		//再次关注发送图文信息
		Article articles = new Article();
		articles.setDescription("");
		articles.setTitle("感谢您再次关注合意购");
		articles.setUrl("http://mp.weixin.qq.com/s?__biz=MzAwNzUyNjA4Mg==&mid=209460738&idx=1&sn=efdc998f2d1d3af546bca9390539bd1d");
		articles.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/Viakac0ujbUBOIURg4Ro6mfcWISDuXHJlxAnbf8f22uK5dSUk4MLGSmsK5t0oYkKgia6vYM5fo3Fg9VOk2qdkRVg/0?wx_fmt=jpeg");
		
		List<Article> list = new ArrayList<Article>();
		list.add(articles);
		NewsMsg newmsg = new NewsMsg();
		newmsg.setArticles(list);
		
		return newmsg;
//		return new TextMsg("感谢您再次关注合意购，您的满意是我们努力的动力！");
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		//1 判断是否订阅消息
		if(!event.getEvent().equalsIgnoreCase(EventType.SUBSCRIBE)){
			return false;
		}
		
		//2 判断是否是第一次关注用户
		//int queryCountByOpendID = activityFirstSubscribeUserService.queryCountByOpendID(event.getFromUserName());
		
		/*
		if(queryCountByOpendID>0){ //从来没有关注过
			return true;
		}*/
		return false;
	}

}
