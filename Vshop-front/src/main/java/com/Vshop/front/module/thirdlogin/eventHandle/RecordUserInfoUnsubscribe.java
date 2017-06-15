package com.Vshop.front.module.thirdlogin.eventHandle;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.EventType;
import com.Vshop.core.common.Digests;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.service.MemberService;


@Component("recordUserInfoUnsubscribe")
public class RecordUserInfoUnsubscribe extends BaseEventHandle implements EventHandle{
private final static Logger log= Logger.getLogger(RecordUserInfoUnsubscribe.class);
	
	

@Resource
private MemberService memberService;

	@Override
	public BaseMsg handle(BaseEvent event) {
		 
		HashMap<String,Object> param = new HashMap<String,Object>();
		
		
		Member m = new Member();
	                    
		
		m.setMemberOpenid(event.getFromUserName());   
		
		Member newm= memberService.findMember(m);
		if(newm!=null){
			newm.setIsDel(1);
			memberService.updateMember(newm);
		}
		
		
		//不返回结果进入下一个Handle
		return null;
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		/**
		 * 以后可以考虑做一个时效性缓存 避免每次都需要处理这个事件handle
		 */
		return event.getEvent().equalsIgnoreCase(EventType.UNSUBSCRIBE)?true:false;
	}

}
