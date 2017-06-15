package com.Vshop.supplier.module.goods.controller;

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
import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsType;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.GoodsTypeService;


@Controller
@RequestMapping("/goods/class")
public class GoodsClassAction {
	@Resource
    private GoodsClassService goodsClassService;
    @Resource
    private GoodsTypeService goodsTypeService;
    String message = "success";
    /**
     * 列表
     * @param goodsClass
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list(@ModelAttribute GoodsClass goodsClass,Model model,
                       @RequestParam(required=false, value="div",defaultValue="")String div) {

        List<GoodsClass> list = goodsClassService.findList(0);
        model.addAttribute("list", list);//结果集
        return "/goods/class/list";
    }

    /**
     * 查询子列表
     * @param id 父id
     * @return json
     */
    @RequestMapping("child")
    public @ResponseBody  List<GoodsClass> child(@RequestParam int id,@RequestParam int level){
        //存入deep，配合ajax
        List<GoodsClass> classList = goodsClassService.findList(id);
        for(GoodsClass vo : classList){
            vo.setDeep(level);
        }
        return classList;
    }

    /**
     *跳转方法
     * @return
     */
    @RequestMapping("forward")
    public String forward(@ModelAttribute GoodsClass goodsClass,Model model,@ModelAttribute GoodsType goodsType){
        //拼装类型和类别
        model.addAttribute("classList",goodsClassService.findAll());
        model.addAttribute("typeList",goodsTypeService.findList());
        if(goodsClass.getGcId() != 0){
            GoodsClass gc=goodsClassService.findById(goodsClass.getGcId());
            model.addAttribute("gc",gc);
            return "/goods/class/edit";
        }else{
            model.addAttribute("flag",goodsClass.getGcParentId());
            return "goods/class/save";
        }
    }

    /**
     *编辑或修改
     * @param goodsClass
     * @return
     */
//    @RequestMapping("edit")
//    public String edit(@ModelAttribute GoodsClass goodsClass,HttpServletRequest request,Model model){
//        String referer = request.getHeader("Referer");
//        if (goodsClass.getGcId() == 0) {
//            //新增
//            goodsClassService.save(goodsClass);
//            model.addAttribute("referer", referer);
//            model.addAttribute("msg", "新增成功");
//        } else {
//            //修改
//            goodsClassService.update(goodsClass);
//            model.addAttribute("referer", referer);
//            model.addAttribute("msg", "修改成功");
//        }
//        return Constants.MSG_URL;
//    }
    
    /**
     *添加或修改
     * @param goodsClass
     * @return
     */
   @RequestMapping("edit")
    public String edit(@ModelAttribute GoodsClass goodsClass,HttpServletRequest request,Model model){
        String referer = request.getHeader("Referer");
        if (goodsClass.getGcId() == 0) {
            //新增
         	goodsClass.setGcTitle("123");
         	goodsClass.setGcKeywords("哈哈哈");
         	goodsClass.setGcDescription("恩恩");
            goodsClassService.save(goodsClass);
            //查找父级id
            if(goodsClass.getGcParentId()==0){
            	goodsClass.setGcIdpath(goodsClass.getGcId()+",");
            }else{
            	GoodsClass goodscla=goodsClassService.findById(goodsClass.getGcParentId());
            	if(goodscla!=null){
            		goodsClass.setGcIdpath(goodscla.getGcIdpath()+goodsClass.getGcId()+",");
            	}
            }
            //将idpath存进数据库
            goodsClassService.update(goodsClass);
            model.addAttribute("referer", referer);
            model.addAttribute("msg", "新增成功");
        } else {
            //修改
            goodsClassService.update(goodsClass);
            model.addAttribute("referer", referer);
            model.addAttribute("msg", "修改成功");
        }
        return "redirect:/goods/class/list";
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
            goodsClassService.delete(id);
        }
        model.addAttribute("referer", referer);
        model.addAttribute("msg", "删除成功");
        return Constants.MSG_URL;
    }

    /**
     * 修改排序
     * @return
     */
    @RequestMapping("/modifySort")
    public @ResponseBody Boolean modifySort(@RequestParam int id,@RequestParam Integer value){

        GoodsClass goodsClass = new GoodsClass();
        goodsClass.setGcId(id);
        goodsClass.setGcSort(value);
        goodsClassService.update(goodsClass);
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

        GoodsClass goodsClass = new GoodsClass();
        goodsClass.setGcId(id);
        goodsClass.setGcName(value);
        //判断是否有重复名称
        if(goodsClassService.findCount(goodsClass) > 0){
            return false;
        }else{
            //执行修改操作
            goodsClassService.update(goodsClass);
            return true;
        }
    }

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@ModelAttribute GoodsClass goodsClass){

        //校验重复
        if(goodsClassService.findCount(goodsClass) > 0){
            return "false";
        }else{
            return "true";
        }

    }
    
    /**
     * 校验菜单下是否有子菜单
     * @return
     */
    @RequestMapping("/validateparentid")
    public @ResponseBody Boolean validateparentid(@RequestParam int id){
        //校验重复
    	List<GoodsClass> goodsclaslist=goodsClassService.findList(id);
        if(goodsclaslist.size()> 0){
            return false;
        }else{
            return true;
        }
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
 		goodsClassService.delete(Integer.valueOf(classid));
 		map.put("result", "删除成功");
 		map.put(message, "true");
 		return map;
 	}
}
