package com.Vshop.supplier.module.index.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.captcha.RandCodeImageUtils;
import com.Vshop.core.common.SpringContextUtil;
import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Supplier;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.supplier.test.Group;
import com.Vshop.supplier.test.ReadArea;
import com.Vshop.supplier.utils.sessionKey.CacheUser;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
import com.Vshop.supplier.utils.twodimencode.TwoDimensionCode;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.SupplierService;
import com.Vshop.service.module.index.service.SignupService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.store.service.StoreGradeService;
import com.Vshop.service.module.store.service.StoreService;
import com.Vshop.service.module.trade.service.ConsultService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.module.website.service.ArticleClassService;



/**
 * 首页
 * 项目名称：Vshop-supplier   
 * 类名称：IndexAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月25日 下午9:07:10   
 * 修改人：liuhao   
 * 修改时间：2014年11月25日 下午9:07:10   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexAction {

	String message = "success";

	@Resource
    private EvaluateStoreService evaluateStoreService;
    @Resource
    private ArticleClassService articleClassService;
    @Resource
    private StoreService storeService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private ConsultService consultService;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private StoreGradeService storeGradeService;//店铺等级
    @Resource
    private MemberService memberService;
    @Resource
    private SignupService signupService;
    /**
     * 导航主页面跳转
     * @param model
     * @return
     */
	@RequestMapping("")
	public String  index(Model model){
        //跳转地址
        String str="";
//        SupplierVo supplier=null;
        Supplier supplier = null;
        Supplier supplierbymember = supplierService.findByMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
        if(supplierbymember!=null){
        	//暂时不需要供应商等级设置
//        	store=storeService.findVoById(storebymember.getStoreId());
        	supplier=supplierbymember;
        }
		try{
            if(supplier != null && supplier.getSupplierState().intValue() == 1){
            	CacheUser cacheUser = CacheUtils.getCacheUser();
            	if(null == cacheUser.getSupplier()) cacheUser.setSupplier(supplier);
            	
                model.addAttribute("supplier",supplier);
                //取得店铺评价
//                model.addAttribute("evaluateStore",evaluateStoreService.findEvaluateStore(store.getStoreId()));
                
                Goods goods = new Goods();
//                goods.setStoreId(store.getStoreId());
                goods.setSupplierId(supplier.getId());
                /**
                 * 仓库、下架、定时上架的商品
                 * goods_state 0
                 * goods_show  0
                 */
        		goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
        		goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
                model.addAttribute("publishCount", goodsService.countGoods(goods));
                
                /**
                 * 上架的商品
                 * goods_state 0
                 * goods_show  1
                 */
        		goods.setGoodsState(GoodsState.GOODS_OPEN_STATE);
        		goods.setGoodsShow(GoodsState.GOODS_ON_SHOW);
                model.addAttribute("onlineCount",goodsService.countGoods(goods));
                
                /**
                 * 下架的商品
                 * goods_state 1
                 * goods_show  0
                 */
                goods.setGoodsState(GoodsState.GOODS_CLOSE_STATE);
        		goods.setGoodsShow(GoodsState.GOODS_OFF_SHOW);
                model.addAttribute("lockupCount",goodsService.countGoods(goods));
                
                //优惠券要从教培角度查
//                Consult consult = new Consult();
//                consult.setStoreId(store.getStoreId());
//                consult.setReplyStatus(1);
//                model.addAttribute("consultCount",consultService.findCount(consult));
                
                Order order = new Order();
                
                /**
                 * 店铺中所有订单
                 */
//                order.setStoreId(store.getStoreId());
                order.setSupplierId(supplier.getId());
                model.addAttribute("allorderCount", orderService.findOrderCount(order));
                /**
                 * 待付款
                 */
               // order.setStoreId(CacheUtils.getCacheUser().getStore().getStoreId());
                order.setOrderState(10);
                model.addAttribute("noPayment", orderService.findOrderCount(order));
                
                /**
                 * 待发货
                 */
                order.setOrderState(20);
                model.addAttribute("noDelivery",orderService.findOrderCount(order));
                
                /**
                 * 已发货
                 */
                order.setOrderState(30);
                model.addAttribute("waitDelivery",orderService.findOrderCount(order));
                /**
                 * 待评价
                 */
                order.setEvaluationStatus(0);//待评价
                order.setOrderState(40);
                model.addAttribute("waitEvaluate",orderService.findOrderCount(order));
              
                
                /**
                 * 已完成
                 */
                order.setOrderState(40);
                model.addAttribute("finshlivery",orderService.findOrderCount(order));
                
                //不知道干什么的，先注释
//                List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService.findTitleList();
                
                /*
                //评分
                EvaluateStore evaluateStore=null;
        		if(store.getStoreId()!=0){
        			evaluateStore=evaluateStoreService.findEvaluateStore(store.getStoreId());
        			//保留一位小数
        			NumberFormat numberFormat= NumberFormat.getNumberInstance() ; 
        			numberFormat.setMaximumFractionDigits(1);
        			if(evaluateStore!=null){
        				//发货速度评分
        				if(evaluateStore.getSevalDeliverycredit()!=null){
        					evaluateStore.setSevalDeliverycredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDeliverycredit())));
        				}
        				//描述相符评分
        				if(evaluateStore.getSevalDesccredit()!=null){
        					evaluateStore.setSevalDesccredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalDesccredit())));
        				}
        				//服务态度评分
        				if(evaluateStore.getSevalServicecredit()!=null){
        					evaluateStore.setSevalServicecredit(Double.valueOf(numberFormat.format(evaluateStore.getSevalServicecredit())));
        				}
        			}
        			 if(!evaluateStore.getCount().equals("0")){
        			    evaluateStore.setAverageCredit(Util.getAverageCreditFormat(evaluateStore));
        			 }
        		}
        		*/
//        		StoreGrade storeGrade=storeGradeService.queryById(Long.valueOf(store.getGradeId()));
//        		model.addAttribute("evaluateStore", evaluateStore);
//                model.addAttribute("listArticleClassTitleVo", listArticleClassTitleVo);
                model.addAttribute("supplier",supplier);
                model.addAttribute("member", CacheUtils.getCacheUser().getMember());
//                model.addAttribute("storeGrade", storeGrade);
                str="/index/index";
                
            }else{
            	/*model.addAttribute("supplier",supplier);
            	str="/index/step1";*/
            	model.addAttribute("id", null != supplier ? supplier.getId() : "0");
            	str = "redirect:/joinIn/step2";//null != supplier ? String.format("redirect:/joinIn/step2?id=%s", supplier.getId()) : "redirect:/joinIn/step2";
            }
			return str;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	
	/**
	 * 默认加载的中心页面
	 * @Title: main 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "content")
	public ModelAndView content(){
		ModelAndView model = new ModelAndView("/index/index_content");
		return model;
	}

	/**
	 * 跳转至登陆页面
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {

            return "redirect:/";
        }
        return "/index/login";
	}

	/**
	 * 生成验证码图片io流
	 */
	@RequestMapping(value = "generateImage", method = RequestMethod.GET)
	public void generateImage(HttpServletResponse response,HttpServletRequest request)
			throws ServletException, IOException {
        /*Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String captcha =  PatchcaUtils.generateImage(response);
        session.setAttribute("captcha", captcha);*/
        RandCodeImageUtils.generateImage(response, request);
	}

	@RequestMapping(value = "twodimencodeImage", method = RequestMethod.GET)
	public void twodimencodeImage(@RequestParam(value="content") String content, HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		TwoDimensionCode handler = new TwoDimensionCode();  
		handler.encoderQRCode(content, response.getOutputStream(), "png", 7);
//		RandCodeImageUtils.generateImage(response, request);
	}
	
	/**
	 * 用户登录操作
	 * @return 登录后的地址
	 */
    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> loginCheck(@RequestParam(value="username") String username,
						@RequestParam(value="password") String password,
						@RequestParam(value="captcha") String captcha,
						@RequestParam(value ="remember_me",required = false) String remember_me,
                        Model model, HttpServletRequest request) {
        @SuppressWarnings("unused")
		String error = null;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionCaptcha = (String) session.getAttribute("captcha");
		Map<String,Object> map = Maps.newHashMap();
		if(StringUtils.isNotEmpty(username)){//判断店铺是否关闭
			Supplier supplier=oncheck(username);
			if(supplier!=null&&supplier.getSupplierState()==0){
				map.put("success",false);
				map.put("message", "该教培机构已关闭!");
				return map;
			}
		}
		if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
			map.put("success",false);
			map.put("message", "验证码错误");
			return map;
		}
		boolean isrmemberName = "1".equals(remember_me) ? true : false;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), isrmemberName, "", captcha, false);
		try {
			if ("1".equals(remember_me)) {
				token.setRememberMe(true);
			}
			subject.login(token);
            CacheUtils.initCacheUser(subject.getPrincipal().toString());
            if(CacheUtils.getCacheUser().getSupplier()!=null&&CacheUtils.getCacheUser().getSupplier().getSupplierState()==0){//判断店铺是否关闭
				map.put("success",false);
				map.put("message", "该教培机构已关闭!");
				return map;
			}
//            String ip = IpUtil.getIpAddr(request);
        } catch (UnknownAccountException e) {
			//error = "用户名/密码错误";
        	map.put("success",false);
			map.put("message", "用户名/密码错误");
			return map;
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
			map.put("success",false);
			map.put("message", "用户名/密码错误");
			return map;
		} catch (Exception e) {
			//其他错误，比如锁定，如果想单独处理请单独catch 处理
			map.put("success",false);
			map.put("message", "其他错误:"+e.getMessage());
			error = "其他错误：" + e.getMessage();
			return map;
		}
		 if(CacheUtils.getCacheUser().getSupplier()!=null&&CacheUtils.getCacheUser().getSupplier().getSupplierState()==0){//判断店铺是否关闭
				map.put("success",false);
				map.put("message", "该教培机构已关闭!");
				return map;
		}
		if(CacheUtils.getCacheUser().getSupplier()!=null){
				//修改店铺的上次登陆时间
			supplierService.updateSupplier(CacheUtils.getCacheUser().getSupplier().getId());
		}
		map.put("success",true);
		map.put("message","登陆成功");
		return map;
		//return "redirect:/";
	}

    @RequestMapping("sign")
    public @ResponseBody Map<String, Object> sign(@ModelAttribute Member member, @RequestParam(value = "captcha") String captcha, HttpSession session) {
        Map<String, Object> map = Maps.newHashMap();
        
        String password = member.getMemberPasswd();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
        	map.putAll(ImmutableMap.of("success", false, "message", "验证码错误"));
        } else {
            try {
            	Member membervali = memberService.findMemberByName(member.getMemberName());
            	if(membervali == null){
            		savemember(member);//保存会员信息
            		
            		readyLogin(member.getMemberName(), password, captcha);
            		map.putAll(ImmutableMap.of("success", true, "message", "恭喜您注册成功！"));
            	}else{
            		readyLogin(member.getMemberName(), password, captcha);
            		map.putAll(ImmutableMap.of("success", true, "message", "恭喜您注册成功！"));
            	}
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户注册失败", e.getMessage());
                map.putAll(ImmutableMap.of("success", false, "message", "注册失败"));
            }
        }
        
        return map;
    }
    
	/**
	 * 用户登出操作
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
        CacheUtils.cleanCacheUser();
		return "redirect:/";
	}

	@RequestMapping(value = "createArea")
	public String createArea(Model model) throws IOException {
		List<Group> groups= ReadArea.read();
		model.addAttribute("groups", groups);
		System.out.println("===================");
		System.out.println(JsonUtils.toJsonStr(groups));
		return "index/test/area";
	}
	
	
	/**
	 * 没有权限
	 * @return
	 */
	@RequestMapping("/joinIn/unauthor")
	public String unauthor() {
		
		return "/index/unauthor";
	}
	
	public Supplier oncheck(String membername){
		MemberService memberService = SpringContextUtil.getBean(MemberService.class);
		SupplierService supplierService = SpringContextUtil.getBean(SupplierService.class);
		Member member = memberService.findMemberByName(membername);
		Supplier supplier=null;
		if(member!=null){
			supplier = supplierService.findByMemberId(member.getMemberId());
		}
		return supplier;
	}
	
	@RequestMapping("signUp")
    public String signUp() {
        return "index/signup";
    }
	
	/*@RequestMapping("sign")
    public
    @ResponseBody
    Map<String, Object> sign(@ModelAttribute Member member, @RequestParam(value = "captcha") String captcha
            , HttpSession session) {

        Map<String, Object> map = Maps.newHashMap();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
            map.put("success", false);
            map.put("msg", "验证码错误");
        } else {
            try {
            	//后台验证用户名是否重复注册
            	Member membervali=memberService.findMemberByName(member.getMemberName());
            	if(membervali==null){
            		savemember(member);//保存会员信息
	                //signupService.saveSendEmail(member);发邮件方法
	                map.put("success", true);
//	                map.put("msg", "邮件已发送，请到邮箱激活");
	                map.put("msg", "恭喜您注册成功！");
            	}
            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户注册失败", e.getMessage());
                map.put("success", false);
                map.put("msg", "注册失败");
            }
        }
        return map;
    }*/

    @RequestMapping("signResult")
    public String signResult(@RequestParam String userName, @RequestParam String code, Model model) {

        model.addAttribute("msg", signupService.updateSign(userName, code));
        return "index/signResult";
    }
	
	@RequestMapping("checkMemeber")
    public
    @ResponseBody
    boolean checkMemeber(@RequestParam String name) {
       // Map<String, Object> map = Maps.newHashMap();
        if (memberService.findMemberByName(name) != null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping("checkEmail")
    public
    @ResponseBody
    boolean checkEmail(@RequestParam String email) {
        if (memberService.findMemberByEmail(email) != null) {
            return false;
        } else {
            return true;
        }

    }
    @RequestMapping("checkMobile")
    public
    @ResponseBody
    boolean checkMobile(@RequestParam String mobile) {
        if (memberService.findMemberByMobile(mobile) != null) {
            return false;
        } else {
            return true;
        }

    }
    @RequestMapping("checkCaptcha")
    public
    @ResponseBody
    boolean checkCaptcha(@RequestParam String captcha) {
    	Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
        	return false;
        }else{
        	return true;
        }
    }
    
    /**
     * 保存用户信息
     *
     */
    public void savemember(Member member){
    	 member.setMemberType("wzzc");//默认会员类型为网站注册
    	 member.setMemberTruename(member.getMemberName());//会员真实姓名默认为会员账号
    	 if(member.getMemberAvatar()==null||"".equals(member.getMemberAvatar())){
    		 member.setMemberAvatar("/upload/img/avatar/01.jpg");//会员头像
    	 }
    	 memberService.save(member);
    }
    
    private void readyLogin(String userName, String password, String captcha) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken(userName, password.toCharArray(), false, "", captcha, false));
        CacheUtils.initCacheUser(subject.getPrincipal().toString());
    }
}