package com.Vshop.admin.module.store.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableList;
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.Type;
import com.Vshop.service.module.store.service.TypeService;

@Controller
@RequestMapping("/store/type")
public class TypeAction {
	String message = "success";

    @Resource
    private TypeService classsService;

    /**
     * @return String    返回类型
     * @throws
     * @Title: addIndex
     * @Description: TODO(进入增加的方法)
     */
    @RequestMapping("/addIndex")
    public String addIndex(@ModelAttribute Type classs, Model model) {

        model.addAttribute("parentId",classs.getParentId());
        classs.setParentId(0);
        List<Type> results = classsService.queryClasssChildrenList(classs);
        model.addAttribute("ParentList", results);//父节点结果集
        return "/store/type/save";
    }
    
    @RequestMapping("/addChildIndex")
    public String addChildIndex(@ModelAttribute Type classs, Model model) {
        model.addAttribute("parentId",classs.getParentId());
        List<Type> results = ImmutableList.of(classsService.queryById(classs.getId()));
        model.addAttribute("ParentList", results);//父节点
        return "/store/type/save";
    }

    /**
     * @param @return /store/calss/edit.flt
     * @return String    返回类型
     * @throws
     * @Title: updateIndex
     * @Description: TODO(进入增加的方法)
     */
    @RequestMapping("/updateIndex")
    public String updateIndex(@ModelAttribute Type classs, Model model) {

        model.addAttribute("classsResult", classsService.queryById(classs.getId()));
        return "/store/type/edit";
    }

    /**
     * 加载数据页面
     * 不带分页
     */
    @RequestMapping(value = "/list")
    public String list(Model model) {

        List<Type> results = classsService.queryClasssParentList();//结果集
        model.addAttribute("list", results);//结果集
        //转发请求到FTL页面
        return "/store/type/list";

    }


    /**
     * 编辑或修改用户
     *
     * @param classs
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute Type classs, Model model, HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        if (classs.getId() == null) {
        	classs.setCreateTime(new Date().getTime());
            classsService.save(classs);
            model.addAttribute("msg", "新增成功");
        } else {
        	classs.setUpdateTime(new Date().getTime());
            classsService.update(classs);
            model.addAttribute("msg", "修改成功");
        }
        //转发请求到FTL页面
        return Constants.MSG_URL;
    }


    /**
     * 删除用户
     *
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") long[] ids, Model model,HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        for (long id : ids) {
            classsService.delete(id);
        }
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }

    /**
     * 查询子列表
     *
     * @param id 父id
     * @return json
     */
    @RequestMapping("child")
    public @ResponseBody List<Type> child(@RequestParam int id, @RequestParam int level) {
    	Type classs = new Type();
        classs.setParentId(id);
        //存入deep，配合ajax
        List<Type> classList = classsService.queryClasssChildrenList(classs);
        for (Type c : classList) {
            c.setDeep(level);
        }
        return classList;
    }

    /**
     * 修改排序
     *
     * @return
     */
    @RequestMapping("/modifySort")
    public
    @ResponseBody
    Boolean modifySort(@RequestParam int id, @RequestParam Integer value) {

    	Type c = new Type();
        c.setId(id);
        c.setSort(value);
        classsService.update(c);
        return true;
    }

    /**
     * 修改分类名称
     *
     * @param id
     * @param value
     * @return
     */
    @RequestMapping("/modifyName")
    public
    @ResponseBody
    Boolean modifyName(@RequestParam int id, @RequestParam String value) {

    	Type c = new Type();
        c.setId(id);
        c.setName(value);
        //判断是否有重复名称
        if (classsService.findCount(c) > 0) {
            return false;
        } else {
            //执行修改操作
            classsService.update(c);
            return true;
        }
    }

    /**
     * 校验重复
     *
     * @return
     */
    @RequestMapping("/validate")
    public
    @ResponseBody
    boolean validate(@ModelAttribute Type classs) {

        if (classsService.findCount(classs) > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 校验菜单下是否有子菜单
     * @return
     */
    @RequestMapping("/validateparentid")
    public @ResponseBody Boolean validateparentid(@RequestParam int id){
        //校验重复
    	List<Type> claslist=classsService.findList(id);
        if(claslist.size()> 0){
            return false;
        }else{
            return true;
        }
    }
    
    @RequestMapping(value = "/deleteid", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> deleteid(@RequestParam(value = "classid") String classid,
 			Model model) {
 		Map<String, String> map = Maps.newHashMap();
 		if (StringUtils.isBlank(classid)) {
 			model.addAttribute("result", "ID为空");
 			map.put("result", "ID为空");
 			map.put(message, "true");
 			return map;
 		}
 		classsService.delete(Long.valueOf(classid));
 		map.put("result", "删除成功");
 		map.put(message, "true");
 		return map;
 	}
}
