package com.Vshop.front.module.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.Area;
import com.Vshop.core.entity.base.Address;
import com.Vshop.core.entity.base.Member;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-front   
 * 类名称：MyFavAction   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2015年3月3日 下午10:15:28   
 * 修改人：liuhao   
 * 修改时间：2015年3月3日 下午10:15:28   
 * 修改备注：   
 * @version    
 *
 */
@Controller
@RequestMapping("/myaddress")
@Slf4j
public class MyAddressAction {
	@Resource
	private AddressService addressService;
	
	@Resource
	private AreaService areaService;
    @Resource
    private MemberService memberService;
	
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		try {
			ModelAndView model = new ModelAndView("/user/address/my-address");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			// 尾部菜单
//			List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService.findTitleList();
//
//			model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
			model.addObject("titleName", "收货地址");
			model.addObject("apm", "setting");
			model.addObject("cur", "address");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}

	/**
	 * 查询页面
	 * 
	 * @Title: list
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/list")
	public ModelAndView List(
			@RequestParam(required=false, value="pageNo",defaultValue="")String pageNo
			){
		try {
			ModelAndView model = new ModelAndView("/user/address/my-address-list");
			Address address = new Address();
			address.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
			//构造pager
			Pager pager = new Pager();
			if(StringUtils.isNotBlank(pageNo)){
				pager.setPageNo(Integer.valueOf(pageNo));
			}
			pager.setCondition(address);


			List<Address> lists = addressService.findList(pager);// 结果集
			// 尾部菜单
//			List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService.findTitleList();
//			model.addObject("listArticleClassTitleVo", listArticleClassTitleVo);
			model.addObject("toUrl", "/myaddress/list");//提供回调的url
			model.addObject("pager", pager);
			model.addObject("pageNo", pager.getPageNo());//当前页
			model.addObject("pageSize", pager.getPageSize());//每页显示条数
			model.addObject("recordCount", pager.getTotalRows());//总数
			model.addObject("lists", lists);
			model.addObject("apm", "index");//高亮参数
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 
	 * @Title: deleteAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param ids
	 * @param @param model
	 * @param @return    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> deleteAddress(@RequestParam(value = "id") String id,
			Model model) {
		Map<String, String> map = Maps.newHashMap();
		addressService.deleteAddress(id);
		map.put("result", "删除成功");
		map.put("success", "true");
		return map;
	}
	
	
	
	
	
	
	/**
	 * 导航主页面跳转
	 * 
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param apm 加载的
	 * @param @return 设定文件
	 * @return ModelAndView 返回类型
	 * @throws RuntimeException
	 */
	@RequestMapping("/addAddress")
	public ModelAndView addAddress() {
		try {
			ModelAndView model = new ModelAndView("/user/address/my-address-add");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			
			List<Area> areas = areaService.queryAll();
			
			model.addObject("areas",areas);
			model.addObject("memberId",CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("titleName", "收货地址");
			model.addObject("cur", "address");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	
	/**
	 * 
	 * @Title: updateAddress 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return ModelAndView    返回类型 
	 * @throws
	 */
	@RequestMapping("/updateAddress")
	public ModelAndView updateAddress(@RequestParam(value = "id") String addressId) {
		try {
			ModelAndView model = new ModelAndView("/user/address/my-address-update");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
	        model.addObject("member",member);
			Address address = addressService.queryById(Integer.valueOf(addressId));
			
			Area area = areaService.queryParentList(address.getCityId());
			
			List<Area> areas = areaService.queryAll();
			
			model.addObject("area",area);
			model.addObject("address",address);
			model.addObject("areas",areas);
			model.addObject("memberId",CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("titleName", "收货地址");
			model.addObject("cur", "address");
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("卖家中心首页加载失败！");
			throw new RuntimeException("导航失败!");
		}
	}
	

	/**
	 * 
	 * @Title: updateAds
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/updateAds" ,method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateAddress(@RequestParam(value = "data") String jsondata,Model model) throws Exception {
		Map<String, String> map = Maps.newHashMap();
		int result = addressService.updateAddress(jsondata);
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		return map;
	}


	/**
	 *
	 * @Title: saveAddress
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param jsondata
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Map<String,Object>    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/saveAddress", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> saveAddress(@RequestParam(value = "data") String jsondata,Model model) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		map = addressService.saveAddress(jsondata,CacheUtils.getCacheUser().getMember().getMemberId());
		return map;
	}
	
	/**
	 * 
	 * @Title: updateDef 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param addressId
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return Map<String,String>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/updateDef", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> updateDef(@RequestParam(value = "addressId") String addressId) throws Exception {

		Map<String, String> map = Maps.newHashMap();
		int result = addressService.updateDef(addressId, CacheUtils.getCacheUser().getMember().getMemberId().toString());
		if(result == 1){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		return map;
	}
	
}
