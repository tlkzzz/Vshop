package com.Vshop.admin.module.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.MenuClass;
import com.Vshop.core.entity.base.RoleMenu;
import com.Vshop.core.entity.vo.MenuClassVo;
import com.Vshop.service.module.admin.service.AdminService;
import com.Vshop.service.module.admin.service.MenuClassService;
import com.Vshop.service.module.admin.service.RoleMenuService;

/**
 * 权限菜单Action
 */

@Slf4j
@Controller
@RequestMapping("/menu/class")
public class MenuClassAction{

    @Resource
    private MenuClassService menuClassService;
    @Resource
	private AdminService adminService;
    @Resource
	private RoleMenuService roleMenuService;
    @Autowired  
    private  HttpServletRequest request;  
    
    String message = "success";
    
    /** 导航至主操作页面 */
	@RequestMapping("/index")
	public String index() {
		try {
			log.debug("操作成功！");
			return "/admin/menu/list";
		} catch (Exception e) {
			log.error("导航失败!",e);
			throw new RuntimeException("导航失败!");
		}
	}
    /**
     * 列表
     * @param
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(@ModelAttribute MenuClass menuClass,Model model,
                       @RequestParam(required=false, value="div",defaultValue="")String div) {

        List<MenuClassVo> list = menuClassService.findListForPage();
        model.addAttribute("list", list);//结果集
        return "/admin/menu/list";
    }
    
    /**
     * 列表
     * @param
     * @param model
     * @return
     */
    @RequestMapping("roleandmenulist")
    public String roleandmenulist(@ModelAttribute MenuClass menuClass,Model model,
                       @RequestParam(required=false, value="div",defaultValue="")String div) {
        String roleid=request.getParameter("roleid");
        List<MenuClassVo> list = menuClassService.findListForPage();
		List<RoleMenu> rolemenulist = roleMenuService.findList(Integer .valueOf(roleid));
		String roleclassidstr="";
	    for(MenuClassVo menuvo:list){
        	roleclassidstr+=menuvo.getMid()+",";
        }
		model.addAttribute("list", list);//结果集
	    model.addAttribute("roleid",roleid);//角色id
        model.addAttribute("rolemenulist", rolemenulist);
        model.addAttribute("roleclassidstr", roleclassidstr.substring(0,roleclassidstr.lastIndexOf(",")));
        return "/admin/menu/roleandmenulist";
    }
    
    /**
     * 查询子列表
     * @param id 父id
     * @return json
     */
    @RequestMapping("child")
    public @ResponseBody  List<MenuClassVo> child(@RequestParam int id,@RequestParam int level){

        //存入deep，配合ajax
        List<MenuClassVo> classList = menuClassService.findChildList(id);
        String roleid=request.getParameter("roleid");
        if(roleid!=null&&!"".equals(roleid)){
        	List<RoleMenu> rolemenulist = roleMenuService.findList(Integer
    				.valueOf(roleid));
            for(MenuClassVo vo : classList){
                vo.setDeep(level);
                for(RoleMenu rolemenu:rolemenulist){
                	if(rolemenu.getMenuId()!=null&&vo.getMid()==rolemenu.getMenuId()){
                		vo.setIschange(1);
                	}
                }
            }
        }else{
        	 for(MenuClassVo vo : classList){
                 vo.setDeep(level);
             }
        }
        return classList;
    }
    
    /**
    /**
     *跳转方法
     * @return
     */
    @RequestMapping("forward")
    public String forward(@ModelAttribute MenuClass menuClass,Model model){
        //拼装类型和类别
        model.addAttribute("classList",menuClassService.findAll());
        model.addAttribute("flag",menuClass.getMparentid());
        if(menuClass.getMid() != 0){
        	MenuClass gc=menuClassService.findById(menuClass.getMid());
            model.addAttribute("gc",gc);
            return "/admin/menu/edit";
        }else{
            return "/admin/menu/save";
        }
    }

    /**
     *编辑或修改
     * @param
     * @return
     */
    @RequestMapping("edit")
    public String edit(@ModelAttribute MenuClass menuClass,Model model,HttpServletRequest request){
        String referer = request.getHeader("Referer");
        String mpath="";
        String[] str;
        if(menuClass.getMparentid()!=0&&!"".equals(menuClass.getMparentid()+"")){
    		String mpaths="";
    		mpath=getparents(menuClass.getMparentid(),mpaths);
    		menuClass.setMpath(mpath);
    		str=mpath.split(",");
    		log.debug("str长度:"+str.length);
    		menuClass.setMlevel(str.length);
    	}
    	if(menuClass.getMparentid()==0){
    		menuClass.setMpath("0,");
    		menuClass.setMlevel(1);
    	}
        if (menuClass.getMid() == 0) {
            //新增
        	menuClassService.save(menuClass);
            model.addAttribute("referer", referer);
            model.addAttribute("msg", "新增成功");
//            adminLogService.save("新增后台菜单", request);
        } else {
            //修改
        	menuClassService.update(menuClass);
            model.addAttribute("referer", referer);
            model.addAttribute("msg", "修改成功");
//            adminLogService.save("修改后台菜单", request);
        }
        return Constants.MSG_URL;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public String  delete(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        for(int id : ids){
        	menuClassService.delete(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
//        adminLogService.save("删除后台菜单", request);
        return Constants.MSG_URL;
    }
    
    @RequestMapping(value = "/deleteid", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> deleteid(@RequestParam(value = "classid") String classid,
			Model model) {
		Map<String, String> map = Maps.newHashMap();
		if (StringUtils.isBlank(classid)) {
			model.addAttribute("result", "ID为空");
			map.put("result", "ID为空");
			map.put(message, "true");
			return map;
		}
		menuClassService.delete(Integer.valueOf(classid));
		map.put("result", "删除成功");
		map.put(message, "true");
//	    adminLogService.save("删除后台菜单", request);
		return map;
	}
    
    /**
     * 修改排序
     * @return
     */
    @RequestMapping("/modifySort")
    public @ResponseBody Boolean modifySort(@RequestParam int id,@RequestParam Integer value){
        MenuClass menuClass = new MenuClass();
        menuClass.setMid(id);
        menuClass.setMsort(value);
        menuClassService.update(menuClass);
//        adminLogService.save("修改排序", request);
        return true;
    }

    /**
     * 修改分类名称
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/modifyName")
    public @ResponseBody Boolean modifyname(@RequestParam int id,@RequestParam String value){

    	MenuClass menuClass = new MenuClass();
    	menuClass.setMid(id);
    	menuClass.setMname(value);
        //判断是否有重复名称
        if(menuClassService.findCount(menuClass) > 0){
            return false;
        }else{
            //执行修改操作
        	menuClassService.update(menuClass);
            return true;
        }
    }

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@ModelAttribute MenuClass menuClass){
        //校验重复
        if(menuClassService.findCount(menuClass) > 0){
            return "false";
        }else{
            return "true";
        }
    }
   
  
    
    public  String getparents(int mparentid, String parpath) {//递归方法，循环调用 	
    	Integer parentid=menuClassService.findbyparentid(mparentid);
        if (parentid ==0) {
        	return "0,"+String.valueOf(mparentid)+","+parpath;        	 
        } else {
        	parpath=String.valueOf(mparentid)+","+parpath;
        	return (getparents(parentid,parpath));             
        } 
    }  
    
    /**
     * 校验菜单下是否有子菜单
     * @return
     */
    @RequestMapping("/validateparentid")
    public @ResponseBody Boolean validateparentid(@RequestParam int id){
        //校验重复
    	Integer pidcount=menuClassService.findparentidCount(id);
        if(pidcount> 0){
            return false;
        }else{
            return true;
        }
    }
}
