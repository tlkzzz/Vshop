package com.Vshop.admin.module.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.Constants;
import com.Vshop.core.entity.GoodsType;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.service.module.goods.service.AttributeService;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.goods.service.GoodsService;
import com.Vshop.service.module.goods.service.GoodsTypeService;
import com.Vshop.service.module.goods.service.SpecService;
import com.Vshop.service.module.goods.vo.GoodsTypeVo;
import com.Vshop.service.utils.page.Pager;

@Controller
@RequestMapping("/goods/type")
public class GoodsTypeAction {
	@Resource
    private GoodsTypeService goodsTypeService;
    @Resource
    private SpecService specService;
    @Resource
    private BrandService brandService;
    @Resource
    private GoodsClassService goodsClassService;
    @Resource
    private AttributeService attributeService;
    @Resource
    private GoodsService goodsService;

    /**
     * 查询列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo){

        Pager pager = new Pager();
        //int total  = goodsTypeService.findCount(pager);//获取总条数
        if(!StringUtils.isEmpty(pageNo)){
            pager.setPageNo(Integer.parseInt(pageNo));
        }
        List<GoodsType> list=goodsTypeService.findPageList(pager);
        pager.setResult(list);
        //pager.setTotalRows(total);
        model.addAttribute("pager", pager);//总数
        return "goods/type/list";
    }

    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam int[] ids,HttpServletRequest request,Model model){

        String referer = request.getHeader("Referer");
        List<Goods> goodsList = goodsService.findGoodsByTypeId(ids);
        if(null!=goodsList && goodsList.size()>0){
        	model.addAttribute("referer", referer);
            model.addAttribute("msg", "删除失败，改类型中包含产品，请先解绑产品。");
        }else{
            for(int id : ids){
        		goodsTypeService.delete(id);
        	}
        	model.addAttribute("referer", referer);
            model.addAttribute("msg", "删除成功");
        }
        return Constants.MSG_URL;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/forward")
    public String forward(@RequestParam int id,Model model,
                          @ModelAttribute SpecVo specVo){
        model.addAttribute("specList",specService.findAllList(specVo));
        model.addAttribute("brandList",brandService.findBrandGroupByClassId());
        //model.addAttribute("classList",goodsClassService.findList());
        if( id == 0){
            return "goods/type/save";
        }else{
            model.addAttribute("type",goodsTypeService.selectTypeFetchOther(id));
            return "goods/type/edit";
        }
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("/save")
    public String save(@ModelAttribute GoodsTypeVo vo,Model model,HttpServletRequest request){

        //String referer = request.getHeader("Referer");
        model.addAttribute("referer", "list");
        if( vo.getGoodsType().getTypeId() != null){
            goodsTypeService.updateGoodsType(vo);
            model.addAttribute("msg", "修改成功");
        }else{
            goodsTypeService.saveGoodsType(vo);
            model.addAttribute("msg", "新增成功");
        }
        return Constants.MSG_URL;
    }

    /**
     * 修改排序
     * @return
     */
    @RequestMapping("/modifySort")
    public @ResponseBody Boolean modifySort(@RequestParam int id,@RequestParam Integer value){

        GoodsType type = new GoodsType();
        type.setTypeId(id);
        type.setTypeSort(value);
        goodsTypeService.updateType(type);
        return true;
    }
    
    /**
     * 根据属性id删除属性和属性值
     * @param attrId
     * @return
     */
    @RequestMapping("/deleteAttr")
    @ResponseBody
    public Map<String,Object> deleteAttr(@RequestParam int attrId){
    	Map<String,Object> map = new HashMap<String, Object>();
    	try{
    		attributeService.deleteAttrById(attrId);
    		map.put("success", true);
    	}catch (Exception e) {
    		map.put("success", false);
    		e.printStackTrace();
    	}
		return map;
    }
}
