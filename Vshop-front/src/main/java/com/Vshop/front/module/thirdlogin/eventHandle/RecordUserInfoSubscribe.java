package  com.Vshop.front.module.thirdlogin.eventHandle;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.EventType;
import com.Vshop.core.common.Digests;
import com.Vshop.core.common.IpUtil;
import com.Vshop.core.entity.base.Member;
import com.Vshop.service.module.member.service.MemberService;

//import com.heyi.utils.EmojiFilter;

/**
 * 记录用户信息到数据库
 * @author coolzlay
 *
 */
@Component("recordUserInfoSubscribe")
public class RecordUserInfoSubscribe extends BaseEventHandle implements EventHandle{
	private final static Logger log= Logger.getLogger(RecordUserInfoSubscribe.class);
	
	
	@Resource
	private MemberService memberService;
	
	@Override
	public BaseMsg handle(BaseEvent event) {
		log.debug("RecordUserInfoSubscribe.handle()");
		log.debug("getAppID():"+getAppID());
		log.debug("getAppSecret():"+getAppSecret());
		
		
		//1 .从微信接口获取 用户详细信息
		ApiConfig config = new ApiConfig(getAppID(), getAppSecret());
		UserAPI userAPI = new UserAPI(config);
		GetUserInfoResponse userInfo = userAPI.getUserInfo(event
				.getFromUserName());
		
		System.out.println("===========nickname========="+userInfo.getNickname());
		
         Member m = new Member();
	                    
		
		m.setMemberOpenid(event.getFromUserName());   
		
		Member newm= memberService.findMember(m);
		
		if(newm!=null){
			newm.setIsDel(0);
			newm.setMemberPasswd(userInfo.getOpenid());   
			memberService.updateMember(newm);
		}else {
			m.setMemberName(userInfo.getNickname());                         // 用户名
			m.setMemberTruename(userInfo.getNickname());                 // 昵称
			m.setMemberSex(userInfo.getSex());                             // 性别
			m.setCreateTime(System.currentTimeMillis());  // 注册时间
			m.setMemberType("weixin");                    // 用户登录类型
			m.setMemberPasswd(userInfo.getOpenid());                        
			
			m.setMemberOpenid(userInfo.getOpenid());                        // 用户的的唯一标识
			if(StringUtils.isEmpty(userInfo.getHeadimgurl())){
				m.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
			}else{
				m.setMemberAvatar(userInfo.getHeadimgurl());
			}
			memberService.save(m);
		}
		
		
		
		
		//不返回结果进入下一个Handle
		return null;
	}

	@Override
	public boolean beforeHandle(BaseEvent event) {
		/**
		 * 以后可以考虑做一个时效性缓存 避免每次都需要处理这个事件handle
		 */
		return event.getEvent().equalsIgnoreCase(EventType.SUBSCRIBE)?true:false;
	}

}
