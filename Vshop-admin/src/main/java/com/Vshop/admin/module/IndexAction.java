package com.Vshop.admin.module;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.auth.shiro.UsernamePasswordToken;
import com.Vshop.core.captcha.RandCodeImageUtils;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.base.Admin;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.RoleMenu;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.vo.MenuClassVo;
import com.Vshop.service.module.admin.service.AdminService;
import com.Vshop.service.module.admin.service.MenuClassService;
import com.Vshop.service.module.admin.service.RoleMenuService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.store.service.StoreService;

/**
 * Created by yansheng on 2014/6/29.
 */
@Slf4j
@Controller
@RequestMapping("")
public class IndexAction {

    @Resource
    private AdminService adminService;
    @Resource
    private MemberService memberService;
//    @Resource
//    private StoreDetailService storeDetailService;
    @Resource
    private StoreService storeService;
    @Resource
    private BrandService brandService;
    @Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuClassService menuClassService;
	
	@Resource
	private GoodsService goodsService;

    /**
     * 跳转至登陆页面
     * @return
     */
    @RequestMapping(value = "/login")
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
    @RequestMapping(value = "/generateImage", method = RequestMethod.GET)
    public void generateImage(HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        String captcha =  PatchcaUtils.generateImage(response);
//        session.setAttribute("captcha", captcha);
        RandCodeImageUtils.generateImage(response, request);
    }

    /**
     * 用户登录操作
     * @return 登录后的地址
     */
    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public String loginCheck(@RequestParam(value="username") String username,
                        @RequestParam(value="password") String password,
                        @RequestParam(value="captcha") String captcha,
                        @RequestParam(value ="remember_me",required = false) String remember_me,
                        Model model) {
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (!sessionCaptcha.equals(captcha.trim().toUpperCase())) {
            error = "验证码错误";
            model.addAttribute("error", error);
            return "/index/login";
        }
        
        boolean isrmemberName = "1".equals(remember_me) ? true : false;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray(), isrmemberName, "", captcha, false);
        try {
            if ("1".equals(remember_me)) {
                token.setRememberMe(true);
            }
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            log.debug("其他错误",e);
            //其他错误，比如锁定，如果想单独处理请单独catch 处理
            error = "其他错误：" + e.getMessage();
        }
        if (error != null) {//出错了，返回登录页面
            model.addAttribute("error", error);
            return "/index/login";
        } else {//登录成功
            return "redirect:/";
        }
    }

    /**
     * 用户登出操作
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    @RequestMapping("/index")
    public String index() {
        return "index/index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "index/welcome";
    }

    @RequestMapping("/about")
    public String about(){
        return "index/about";
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.GET)
    public String changePwd() {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        Admin admin = adminService.findByAdminName(username);
        //log.debug(JsonUtils.toJsonStr(admin));
        return "/index/changePwd";
    }

    @RequestMapping(value = "/changePwdSave",method = RequestMethod.POST)
    public String changePwdSave(
            @RequestParam(required = false, value = "old_pw", defaultValue = "") String oldPw,
            @RequestParam(required = false, value = "new_pw", defaultValue = "") String newPw,
            Model model,HttpServletRequest request
                                ) {
        String referer = request.getHeader("Referer");

        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        String msg = "";
        Admin admin = adminService.findByAdminName(username);
        if (admin.getAdminPassword().equals(new Md5Hash(oldPw).toString())) {
            if (StringUtils.isNotBlank(newPw)) {
                admin.setAdminPassword(new Md5Hash(newPw).toString());
                adminService.update(admin);
                msg = "密码修改成功";
            } else {
                msg = "新密码不能为空";
            }
        } else {
            msg = "旧密码不匹配";
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", msg);
        return Constants.MSG_URL;
    }

    /**
     * 首页统计
     * @return
     */
    @RequestMapping("/statistics")
    public @ResponseBody Map<String,Integer> statistics(){
        //一周开始和结束时间
        String endTime = new DateTime().dayOfWeek().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss");
        String startTime = new DateTime().dayOfWeek().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss");
        Map<String,Integer> map = Maps.newHashMap();
        
        // 会员总数
        map.put("member",memberService.findMemberCount(new Member()));
        // 新增会员数
        Member member = new Member();
        member.setStartTime(DateUtils.strToLong(startTime));
        member.setEndTime(DateUtils.strToLong(endTime));
        map.put("week_add_member",memberService.findMemberCount(member));
        Store storeDetail = new Store();
        // 店铺总数
        map.put("store",storeService.queryCount(storeDetail));
        // 店铺申请数
        // 即将到期
        storeDetail.setFlag("expire");
        storeDetail.setEndTime(DateUtils.strToLong(startTime));
        storeDetail.setCreateTime(System.currentTimeMillis());
        map.put("store_expire",storeService.queryCount(storeDetail));
        // 已经到期
        storeDetail.setFlag("expired");
        storeDetail.setCreateTime(System.currentTimeMillis());
        map.put("store_expired",storeService.queryCount(storeDetail));
        // 商品总数
        Goods goods = new Goods();
        map.put("goods",goodsService.countGoods(goods));
        // 新增商品数
        goods.setStartTime(DateUtils.strToLong(startTime));
        goods.setEndTime(DateUtils.strToLong(endTime));
        map.put("week_add_product", goodsService.countGoods(goods));
        goods = new Goods();
        goods.setGoodsState(1);//下架状态
        goods.setGoodsShow(0);;//下架状态
//        goods.setIsDel(0);//没有删除的
        //违规下架的 暂时没有商品审核
        map.put("product_down", goodsService.countGoods(goods));
        Brand brand = new Brand();
        brand.setBrandApply(0);
        map.put("brand_apply", brandService.countBrand(brand));
        return map;
    }
    
    @RequestMapping("/getMenuJSON")
	private @ResponseBody String getMenuJSON() {
		// 拼JSON的业务逻辑
		Subject subject = SecurityUtils.getSubject();
		String username = subject.getPrincipal().toString();
		Admin admin = adminService.findByAdminName(username);
		String menuStr="{ \"id\": \"index_menu\", \"name\": \"首页\", \"desc\": \"常用操作\", \"subMenu\": [ { \"id\": \"index_welcome\", \"name\": \"欢迎页\", \"url\": \"/welcome\" }, { \"id\": \"index_about\", \"name\": \"关于我们\", \"url\": \"/about\" } ] }";
		if(admin.getRoleid()!=null&&!"".equals(admin.getRoleid())){
		String[] roleids = admin.getRoleid().split(",");
		JSONObject menujson = new JSONObject();
		String menujsons="";
		
		int mi=0;
		for (int i = 0; i < roleids.length; i++) {
			List<RoleMenu> rolemenulist = roleMenuService.findList(Integer
					.valueOf(roleids[i]));
			if (rolemenulist != null) {
				for (RoleMenu rolemenu : rolemenulist) {
					MenuClass menuclass = menuClassService.findById(rolemenu
							.getMenuId());
					if (menuclass != null) {
						if (menuclass.getMparentid() == 0) {//判断是否是父节点
							JSONArray menuarray = new JSONArray();
							menujson.put("id", menuclass.getMid());//
							menujson.put("name", menuclass.getMname());
							menujson.put("desc", menuclass.getMdescription());
							List<MenuClassVo> menulists = menuClassService
									.findChildListmap(menuclass.getMid(),Integer.valueOf(roleids[i]));
							for (int j = 0; j < menulists.size(); j++) {//将父节点下的菜单内容放入json中
								MenuClassVo menulassvo = menulists.get(j);
								JSONObject menuvojson = new JSONObject();
								menuvojson.put("id", menulassvo.getMid());
								menuvojson.put("name", menulassvo.getMname());
								menuvojson.put("url", menulassvo.getMurl());
								menuarray.put(j, menuvojson);
							}
							// menujson.put("subMenu","");
							++mi;
							menujson.put("subMenu", menuarray);
							if(mi>0){
								menujsons+=","+menujson.toString();
							}
						} 
					}
				}
				menuStr = menuStr + menujsons ;
			}else{
				
			}
		}
		log.debug(menuStr);
		return "[" +menuStr+ "]";
	  }else{
		  return "[" +menuStr+ "]";
	  }
	}
}
