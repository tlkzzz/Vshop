package com.Vshop.front.module.thirdlogin.eventHandle;

 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.service.MemberService;




/**
 * 首次关注送礼 活动
 * 用户第一次关注微信号的时候 送30M流量给用户
 * @author coolzlay
 *
 */
@Component("firestSubscribe")
public class FirestSubscribe  extends BaseEventHandle implements EventHandle{
	private final static Logger log= Logger.getLogger(FirestSubscribe.class);
	
	
	@Resource
	private MemberService memberService;
	@Override
	public BaseMsg handle(BaseEvent event) {
		
		try {
			//为了事务,另外封一个子方法调用, 接口实现 不能往外抛出异常
			processHandle(event);
		} catch (Exception e) {
			log.error("FirestSubscribe.handle", e);
			
			return null;
		}
		 
		//首次关注发送图文信息
		Article articles = new Article();
		articles.setDescription("");
		articles.setTitle("感谢您关注课程汇");
		articles.setUrl("http://mp.weixin.qq.com/s?__biz=MzIwMjE3NTI3OQ==&mid=402255854&idx=1&sn=64a479513eabc83be975790d2f679b9d#rd");
		articles.setPicUrl("http://mmbiz.qpic.cn/mmbiz/VPbsTHj8XpxQx2c91CjNc2E6tPWicEu1dAClhO637YO97kRXRwpsEQmN3zjpRczyKPn7XvXzBmqQZ2agjScEsxw/640?wx_fmt=jpeg&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1");
		
		List<Article> list = new ArrayList<Article>();
		list.add(articles);
		NewsMsg newmsg = new NewsMsg();
		newmsg.setArticles(list);
		
		return newmsg;
		
		
//		return new TextMsg("感谢您关注合意购，首次关注送您30M流量哦！"
//				+"您还可以到商城流量钱包中查看使用规则哦！");
	}
	
	private void processHandle(BaseEvent event) throws Exception{
		//0 获取用户基础信息
		System.out.println("============="+event.getFromUserName());
		
		//3  更新财富明细信息
		/*SiteUserWealthLogBean siteUserWealthLogBean = new SiteUserWealthLogBean();
		siteUserWealthLogBean.setUserid(Long.valueOf(siteUserBean.getId()));
		siteUserWealthLogBean.setWealthChange(1);
		siteUserWealthLogBean.setWealthType(1);
		siteUserWealthLogBean.setWealthValue(30); //30M   注意这个 才是日志中的赠送量
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		siteUserWealthLogBean.setChangeDate(date); //给索引用的
		siteUserWealthLogBean.setChangeReason("微信首次关注赠送");
		siteUserWealthLogService.add(siteUserWealthLogBean);*/
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		
		//1 判断是否订阅消息
		if(!event.getEvent().equalsIgnoreCase(EventType.SUBSCRIBE)){
			return false;
		}
		
		Member m = new Member();
	                    
		
		//m.setMemberOpenid(event.getFromUserName());   
		
		//if(memberService.findMember(m)==null){ //从来没有关注过
		 //  return true;
	    //}
		
		//2 判断是否是第一次关注用户
		//int queryCountByOpendID = activityFirstSubscribeUserService.queryCountByOpendID(event.getFromUserName());
		
//		if(queryCountByOpendID==0){ //从来没有关注过
//			return true;
//		}
		return true;
	}

}

