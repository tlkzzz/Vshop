package com.Vshop.front.module.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.entity.apibean.ApiFloorVo;
import com.Vshop.core.entity.apibean.WebCodeApiBean;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.core.entity.base.WebCode;
import com.Vshop.front.sms.C123SendSmsUtil;
import com.Vshop.service.module.member.common.PointsLogType;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.module.website.service.WebCodeService;


/**
 * 首页楼层api
 * @author kviuff
 * @date 2015-07-20
 */
@Slf4j
@Controller
@RequestMapping("/floor/api")
public class IndexApi extends BaseApi {

	@Resource
	private WebCodeService webCodeService;
	
	@Resource
    private MemberService memberService;
	
	@Resource
	private SettingService settingService;
	
	@Resource
	private ShopPointsLogService shopPointsLogService;
	
	/**
	 * 楼层列表
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("floorList")
	@ResponseBody
	public JSONObject index(@RequestParam(required =false , value="pageSize" , defaultValue="")String pageSize ,
			@RequestParam(required = false ,value="pageNo" , defaultValue="")String pageNo){
		jsonObj = new JSONObject();
		try {
			List<WebCode> list = webCodeService.queryAllByType("floor_list");
			WebCode code = null;
			
			WebCodeApiBean api = null;
			String codeInfo= "";
			List<WebCodeApiBean> apiList = new ArrayList<WebCodeApiBean>();
			for(int i=0 ;i<list.size() ;i++){
				code = list.get(i);
				codeInfo = code.getCodeInfo();
				if(StringUtils.isNotEmpty(codeInfo)){
					api = new WebCodeApiBean();
					JSONObject json = new JSONObject().fromObject(codeInfo);
					api.setBannerImg(json.getString("bannerImg"));
					api.setBannerUrl(json.getString("bannerUrl"));
					api.setFloorColor(json.getString("floorColor"));
					api.setFloorImg(json.getString("floorImg"));
					api.setFloorName(json.getString("floorName"));
					api.setSubName(json.getString("floorName"));
//					api.setSubName(json.getString("subName"));//副标题
//					api.setGcId(gcId);
					int gcId = StringUtils.isEmpty(json.getString("gcId")) ? 0 : Integer.valueOf(json.getString("gcId"));
					api.setGcId(gcId);
					int isShow = StringUtils.isEmpty(json.getString("isShow")) ? 0 : Integer.valueOf(json.getString("isShow"));
					api.setIsShow(isShow);
					int sort = StringUtils.isEmpty(json.getString("sort")) ? 0 : Integer.valueOf(json.getString("sort"));
					api.setSort(sort);
					apiList.add(api);
				}
			}
//			System.out.println(apiList.toString());
//			WebCode code = list.get(0);
//			list.remove(0);
//			WebCodeApiBean bean = new WebCodeApiBean();
//			bean.setWebcode(code);
//			bean.setWebCodeList(list);
			jsonObj.put("result", 1);
			jsonObj.put("msg", "获取成功");
	        jsonObj.put("data", JSONArray.fromObject(apiList));
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("出错了",e);
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
	        jsonObj.put("data", "无楼层列表");
		}
		return jsonObj;
	}
	
	
	
	/**
	 * 楼层列表
	 * @return
	 */
	@RequestMapping("indexList")
	@ResponseBody
	public JSONObject indexList(@RequestParam(required =false , value="pageSize" , defaultValue="")String pageSize ,
			@RequestParam(required = false ,value="pageNo" , defaultValue="")String pageNo){
		jsonObj = new JSONObject();
		try {
			List<WebCode> list = webCodeService.queryAllByType("floor_list");
			
			List<ApiFloorVo> floorList = new ArrayList<ApiFloorVo>();
			for(int i=0 ;i<list.size() ;i++){
				ApiFloorVo floor = JsonUtils.fromJson(list.get(i).getCodeInfo(),ApiFloorVo.class);
				if(i==0){
					floor.setFloorType("1*3");
				}else{
					floor.setFloorType("2*2");
				}
				floorList.add(floor);
			}
			//System.out.println("floorList=="+floorList.toString());
			jsonObj.put("result", 1);
			jsonObj.put("msg", "获取成功");
	        jsonObj.put("data", JSONArray.fromObject(floorList));
		} catch (Exception e) {
			log.error("出错了",e);
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
	        jsonObj.put("data", "无楼层列表");
		}
		return jsonObj;
	}
	
	/**
	 * 签到
	 * @param memberId
	 * @return
	 */
	@RequestMapping("sign")
	@ResponseBody
	public JSONObject sign(
			@RequestParam int memberId
			){
		jsonObj = new JSONObject();
		try {
			Member m = memberService.findById(memberId);
			if(null == m){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "会员不存在");
			}else{
				ShopPointsLog shopPointsLog = new ShopPointsLog();
				shopPointsLog.setMemberid(m.getMemberId());
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_SIGN); //积分操作类型
				shopPointsLog.setStartTime(DateUtils.getStartTime()); //查询当天开始时间
				shopPointsLog.setEndTime(DateUtils.getEndTime()); //查询当天结束时间
				//查询当天签到的积分记录
				int signCount = shopPointsLogService.findCount(shopPointsLog);
				//若记录不大于0条(未签到)
				if(signCount<=0){ //签到
					Integer rankPoint = m.getMemberRankPoints();
					Integer consPoint = m.getMemberConsumePoints();
					if(rankPoint==null) rankPoint = 0;
					if(consPoint==null) consPoint = 0;
					//获取积分设置登录增加等级积分
					String rankSettingPoints = settingService.findByNameAndCode("points", "sign_rank");
					//获取积分设置登录增加消费积分
					String consSettingPoints = settingService.findByNameAndCode("points", "sign_cons");
					if(StringUtils.isNotBlank(rankSettingPoints)){
						rankPoint += Integer.valueOf(rankSettingPoints);
					}
					if(StringUtils.isNotBlank(consSettingPoints)){
						consPoint += Integer.valueOf(consSettingPoints);
					}
					if(rankPoint!=null){
						m.setMemberRankPoints(rankPoint);
					}else{
						m.setMemberRankPoints(0);
					}
					if(consPoint!=null){
						m.setMemberConsumePoints(consPoint);
					}else{
						m.setMemberConsumePoints(0);
					}
					memberService.updateMember(m);
					
					shopPointsLog.setMembername(m.getMemberName());
					shopPointsLog.setAdminid(1);
					shopPointsLog.setAdminname("admin");
					shopPointsLog.setPoints(consPoint);
					shopPointsLog.setCreateTime(System.currentTimeMillis());
					shopPointsLog.setDesc("签到完成");
					shopPointsLog.setStage("签到成功,增加会员积分");
					//保存会员积分日志表
					shopPointsLogService.save(shopPointsLog);
					
					jsonObj.put("result", 1);
					jsonObj.put("msg", "签到成功");
				}else{
					jsonObj.put("result", 0);
					jsonObj.put("msg", "您今天已签到");
				}
			}
		} catch (Exception e) {
			log.error("出错了",e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		
		return jsonObj;
	}
	
	/**
	 * 手机验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/verifyCode")
	@ResponseBody
	public JSONObject verify(
			@RequestParam String mobile
			){
		jsonObj = new JSONObject();
		try {
			if(11 == mobile.length()){
				Integer valid = NumberUtils.getRandomInt(99999);
				C123SendSmsUtil sms = new C123SendSmsUtil();
				String result = sms.sendSms(mobile, valid + "");
				if("1".equals(result)){
					jsonObj.put("result", 1);
					jsonObj.put("msg", "获取成功");
					jsonObj.put("data", "{'verifyCode':'"+valid+"'}");
				}else{
					jsonObj.put("result", 0);
					jsonObj.put("msg", "短信发送失败");
					jsonObj.put("data", "[]");
				}
			}else{
				jsonObj.put("result", 0);
				jsonObj.put("msg", "手机号格式不正确");
				jsonObj.put("data", "[]");
			}
		} catch (Exception e) {
			log.error("出错了",e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		
		return jsonObj;
	}
	
}
