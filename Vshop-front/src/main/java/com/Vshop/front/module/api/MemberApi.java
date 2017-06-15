package com.Vshop.front.module.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.Digests;
import com.Vshop.core.common.FileUtils;
import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.common.NumberUtils;
import com.Vshop.core.common.PinYinUtil;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.apibean.FavoritesGoodsApiBean;
import com.Vshop.core.entity.apibean.FavoritesStoreApiBean;
import com.Vshop.core.entity.apibean.MemberApiBean;
import com.Vshop.core.entity.apibean.StoreApiBean;
import com.Vshop.core.entity.base.Favorites;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsDetailBean;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.sms.C123SendSmsUtil;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.trade.common.OrderState;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

/**
 * 会员中心接口
 * @author kviuff
 * @date 2015-07-21 17:50:00
 */
@Slf4j
@Controller
@RequestMapping("/memberapi")
public class MemberApi extends BaseApi {

	@Resource
    private MemberService memberService;
	
	@Resource
	private FavoritesService favoritesService;
	
	@Resource
	private EvaluateGoodsService evaluateGoodsService;
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 会员注册
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public JSONObject memberRegister(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password,
			@RequestParam(required=false ,value="mobile" , defaultValue="" ) String mobile){
		jsonObj = new JSONObject();
		try {
			
			Member m = new Member();
			m.setMemberName(name);
			int count = memberService.findMemberCount(m);
			if(count > 0){
				jsonObj.put("result", 0);
	            jsonObj.put("msg", "用户名已存在");
			}else{
				Member member = new Member();
				member.setMemberName(name);
				
				member.setMemberMobile(mobile);
				if(com.Vshop.core.common.StringUtils.isMobile(name))
				member.setMemberMobile(name);//app端用户名限制是手机号
				
				member.setMemberPasswd(password);
				memberService.save(member);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "注册成功");
			}
		} catch (Exception e) {
			log.error("订单列表API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "注册失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 获取会员信息
	 * @param memberId
	 * @return
	 */
	@RequestMapping("memberDetail")
	@ResponseBody
	public JSONObject memberDetail(
			@RequestParam(value = "memberId") Integer memberId
			){
		jsonObj = new JSONObject();
		try {
			Member member = new Member();
			member = memberService.findById(memberId);
			if(null == member){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "用户不存在");
				jsonObj.put("data", "[]");
			}else{
				
				MemberApiBean bean = new MemberApiBean();
				MyBeanUtils.copyBeanNotNull2Bean(member, bean);
				
				//判断若用户余额为空则设为0
				if(bean.getAvailablePredeposit()==null){
					bean.setAvailablePredeposit("0.00");
				}
				
				//判断会员消费积分为空设为0
				if(bean.getMemberConsumePoints()==null){
					bean.setMemberConsumePoints("0.00");
				}
				
				// 待付款订单
				Order order = new Order();
				order.setBuyerId(memberId);
				order.setPaymentState(OrderState.PAYMENT_STATE_NO);
				order.setOrderState(OrderState.ORDER_STATE_NO_PATMENT);
				int payment_no = orderService.findOrderCountByOrder(order);
				bean.setNoPayOrder(payment_no);
				
				// 待收货订单
				order.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING);
				int receiving_no = orderService.findOrderCountByOrder(order);
				bean.setNoReceiveOrder(receiving_no);
				
				// 待发货订单
				order.setOrderState(OrderState.ORDER_STATE_UNFILLED);
				int unfilled_No = orderService.findOrderCountByOrder(order);
				bean.setNoFilledOrder(unfilled_No);
				
				// 已完成订单
				order.setOrderState(OrderState.ORDER_STATE_FINISH);
				int finish_No = orderService.findOrderCountByOrder(order);
				bean.setFinishOrder(finish_No);
				
				// 获取会员收藏的课程的数量
				Favorites favorites = new Favorites();
				favorites.setFavType("goods");
				favorites.setMemberId(memberId);
				int favGoodsCount = favoritesService.FavoriteGoodsCount(favorites);
				bean.setFavGoodsCount(favGoodsCount);
				
				// 获取会员收藏的学院的数量
				Favorites favorites1 = new Favorites();
				favorites1.setFavType("store");
				favorites1.setMemberId(memberId);
				int favStoreCount = favoritesService.FavoriteStoreCount(favorites1);
				bean.setFavStoreCount(favStoreCount);
				
				bean.setMemberNameCode(PinYinUtil.getPingYin(bean.getMemberName()));
				
				jsonObj.put("result", 1);
				jsonObj.put("msg", "获取成功");
				jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
			}
			
		} catch (Exception e) {
			log.error("会员信息API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	/**
	 * 修改会员密码
	 * @param memberid 会员id
	 * @param password 旧密码
	 * @param newpassword 新密码
	 * @return
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public JSONObject updateMember(
			@RequestParam(value = "memberid") Integer memberId,
			@RequestParam(value = "password" ,required=false ,defaultValue="") String password,
			@RequestParam(value = "newpassword") String newPasswd
			){
		jsonObj = new JSONObject();
		try {
			Member m = memberService.findById(memberId);
			if(StringUtils.isNotEmpty(password) && !Digests.validatePassword(password, m.getMemberPasswd())){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "原密码不正确");
			}else{
				memberService.updatePass(newPasswd, memberId);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "修改成功");
			}
		} catch (Exception e) {
			log.error("修改密码API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "修改失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 会员收藏
	 * @param memberId 会员id
	 * @param pageNo   页码
	 * @param pageSize 页数
	 * @param type     类型：1-收藏课程，2-收藏学院
	 * @return
	 */
	@RequestMapping("memberfavotites")
	@ResponseBody
	public JSONObject favotitesGoods(
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "pageno", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "type") String type
			
			){
		jsonObj = new JSONObject();
		try {
			Favorites favorites = new Favorites();
			favorites.setMemberId(memberId);
			
			Pager pager = new Pager();
			pager.setPageNo(pageNo);
			pager.setPageSize(pageSize);
			
			List<Favorites> list = new ArrayList<Favorites>();
			if("1".equals(type)){
				List<FavoritesGoodsApiBean> beanList = new ArrayList<FavoritesGoodsApiBean>();
				favorites.setFavType("goods");
				pager.setCondition(favorites);
				list = favoritesService.findFavoriteGoodsList(pager);
				for (Favorites fav : list) {
					Goods goods = fav.getGoods();
					GoodsDetailBean goodsBean = new GoodsDetailBean();
					MyBeanUtils.copyBeanNotNull2Bean(goods, goodsBean);
					BigDecimal evaluate = evaluateGoodsService.getAverageScoreByGooodsId(goods.getGoodsId());
					evaluate = evaluate == null ? new BigDecimal(3) : evaluate;
					goodsBean.setEvaluate(evaluate);
					FavoritesGoodsApiBean bean = new FavoritesGoodsApiBean();
					bean.setGoods(goodsBean);
					bean.setFavTime(fav.getFavTime());
					beanList.add(bean);
				}
				jsonObj.put("result", 1);
				jsonObj.put("data", JSONArray.fromObject(beanList, JsonUtils.getJsonConfig()));
			}else{
				List<FavoritesStoreApiBean> breanList = new ArrayList<FavoritesStoreApiBean>();
				favorites.setFavType("store");
				pager.setCondition(favorites);
				list = favoritesService.findFavoriteStoreList(pager);
				for (Favorites fav : list) {
					Store store = fav.getStore();
					StoreApiBean bean = new StoreApiBean();
					MyBeanUtils.copyBeanNotNull2Bean(store, bean);
					FavoritesStoreApiBean storeBean = new FavoritesStoreApiBean();
					storeBean.setStore(bean);
					storeBean.setFavTime(fav.getFavTime());
					breanList.add(storeBean);
				}
				jsonObj.put("result", 1);
				jsonObj.put("data", JSONArray.fromObject(breanList, JsonUtils.getJsonConfig()));
			}
			
			
			
		} catch (Exception e) {
			log.error("会员收藏API出错", e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "无信息");
		}
		return jsonObj;
	}
	
	/**
	 * 修改会员信息
	 * @param memberId 会员ID
	 * @param memberBirthday 会员生日 
	 * @param memberTruename 昵称
	 * @param memberSex 性别
	 * @param memberAreainfo 会员地址
	 * @param memberAvatar 会员头像
	 * @return
	 */
	@RequestMapping("updateMember")
	@ResponseBody
	public JSONObject update(HttpServletRequest request){
		jsonObj = new JSONObject();
		try {
			Integer memberId = ParamsUtils.getInt(request.getParameter("memberId"));
			String memberBirthday = ParamsUtils.getString(request.getParameter("birthday"));
			String memberTruename = ParamsUtils.getString(request.getParameter("nichen"));
			Integer memberSex = ParamsUtils.getInt(request.getParameter("sex"));
			String memberAreainfo = ParamsUtils.getString(request.getParameter("areaInfo"));
			Member m = memberService.findById(memberId);
			if(null == m){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "会员不存在");
			}else{
				m.setMemberId(memberId);
				m.setMemberBirthday(DateUtils.strToLong(memberBirthday + " 00:00:00"));
				m.setMemberTruename(memberTruename);
				m.setMemberSex(memberSex);
				m.setMemberAreainfo(memberAreainfo);
				if(ServletFileUpload.isMultipartContent(request)) {
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
					// 得到上传的图片数据
					MultipartFile portrait = multipartRequest.getFile("face");
					if(null != portrait){
						Map<String, Object> map = FileUtils.fileUpload(portrait, CommonConstants.FILE_BASEPATH, Constants.MEMBER_UPLOAD_URL, request,"images",1);
						if("true".equals(map.get("success") + "")){
							m.setMemberAvatar(map.get("result") + "");
						}
					}
				}
				memberService.update(m);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "修改成功");
			}
		} catch (Exception e) {
			log.error("修改会员信息API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "服务器异常");
		}
		
		return jsonObj;
	}
	
	/**
	 * 手机验证码
	 * @param mobile
	 * @param codeType 验证码类型
	 * @return
	 */
	@RequestMapping("/findCode")
	@ResponseBody
	public JSONObject verify(@RequestParam String mobile,
			@RequestParam(required=false ,value="codeType" , defaultValue="" ) String codeType){
		jsonObj = new JSONObject();
		try {
			if(com.Vshop.core.common.StringUtils.isMobile(mobile) && 11 == mobile.length()){
				Member member = memberService.findMemberByMobile(mobile);
				if(member==null){
					jsonObj.put("result", 0);
					jsonObj.put("msg", "用户不存在");
					jsonObj.put("data", "[]");
					return jsonObj;
				}
				Integer valid = NumberUtils.getRandomInt(99999);
				C123SendSmsUtil sms = new C123SendSmsUtil();
				String result = sms.sendSms(mobile, valid + "");
				if("1".equals(result)){
					jsonObj.put("result", 1);
					jsonObj.put("msg", "获取成功");
					jsonObj.put("data", "{'verifyCode':'"+valid+"','memberId':'"+member.getMemberId()+"'}");
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
	
	/**
	 * 第三方登录
	 * @param userName 用户名
	 * @param sex      性别：1男，0女
	 * @param type     类型：qq,weixin,sina
	 * @param openId   唯一标识
	 * @param avatar   头像
	 * @return
	 */
	@RequestMapping("thirdLogin")
	@ResponseBody
	public JSONObject thirdLogin(HttpServletRequest request){
		jsonObj = new JSONObject();
		try {
			String name = request.getParameter("userName");
			int sex = Integer.parseInt(request.getParameter("sex"));
			String loginType = request.getParameter("type");
			String openID = request.getParameter("openId");
			String face = request.getParameter("avatar");
			
			MemberApiBean bean = new MemberApiBean();
			Member mem = new Member();
			mem.setMemberOpenid(openID);
			Member member = memberService.findMember(mem);
			if(null == member){
				Member m = new Member();
				m.setMemberName(name);     // 用户名
				m.setMemberTruename(name); // 昵称
				m.setMemberSex(sex);       // 性别
				m.setCreateTime(System.currentTimeMillis());; // 注册时间
				m.setMemberType(loginType);
				m.setMemberPasswd(openID); 
				m.setMemberOpenid(openID); // QQ用户的的唯一标识
				if(StringUtils.isNotEmpty(face)){
					m.setMemberAvatar(face);
				}
				memberService.save(m);
				memberService.updateweiMember(m.getMemberId());
				
				m = memberService.findMember(mem);
				MyBeanUtils.copyBeanNotNull2Bean(m, bean);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "登录成功");
				jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
			}else{
				member.setMemberName(name);     // 用户名
				member.setMemberTruename(name); // 昵称
				member.setMemberSex(sex);       // 性别
				if(StringUtils.isNotEmpty(face)){
					member.setMemberAvatar(face);// 头像
				}
				member.setMemberType(loginType);
				memberService.updateweiMember(member.getMemberId());
				memberService.updateMember(member);
				MyBeanUtils.copyBeanNotNull2Bean(member, bean);
				jsonObj.put("result", 1);
				jsonObj.put("msg", "登录成功");
				jsonObj.put("data", JSONArray.fromObject(bean, JsonUtils.getJsonConfig()));
			}
			
		} catch (Exception e) {
			log.error("第三方登录出错了",e);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
	
	/**
	 * 获取会员余额
	 * @param memberId
	 * @return
	 */
	@RequestMapping("memberAvailable")
	@ResponseBody
	public JSONObject memberAvailable(
			@RequestParam(value = "memberId") Integer memberId
			){
		jsonObj = new JSONObject();
		try {
			Map<String,String> map = new HashMap<String, String>();
			Member member = memberService.findById(memberId);
			String availablePredeposit = member.getAvailablePredeposit().toString();
			map.put("availablePredeposit", availablePredeposit);
			jsonObj.put("result", 1);
			jsonObj.put("data", JSONArray.fromObject(map, JsonUtils.getJsonConfig()));
		}catch (Exception e) {
			jsonObj.put("result", 0);
			jsonObj.put("msg", "服务器异常");
		}
		return jsonObj;
	}
}
