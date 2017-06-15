package com.Vshop.front.module.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.front.utils.CommonConstants;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.store.service.EvaluateStoreService;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.module.trade.service.OrderGoodsService;
import com.Vshop.service.module.user.service.EvaluateService;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.front.module.user.controller
 * @Description:
 * @date 2015/3/11 15:54
 */
@Slf4j
@Controller
@RequestMapping("/myReviews")
public class MyReviewsAction {

    @Resource
    private EvaluateGoodsService evaluateGoodsService;

    @Resource
    private EvaluateStoreService evaluateStoreService;

    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private EvaluateService evaluateService;

    @Resource
    private MemberService memberService;
    
    /**
     * 来自学员的评价
     * @param model
     * @param reviewsType
     * @return
     */
    @RequestMapping("/reviewsBuyerList")
    public String reviewsList(
    		Model model,
			@RequestParam(required=false, value="pageNo",defaultValue="1")String pageNoStr,
			@RequestParam(required=false, value="gevalscore",defaultValue="")String gevalscore,
			@RequestParam(required=false, value="havecontent",defaultValue="")String havecontent){
    	Pager pager = new Pager();
		EvaluateGoods evaluateGoods = new EvaluateGoods();
    	int memberId = CacheUtils.getCacheUser().getMember().getMemberId();
    	evaluateGoods.setGevalFrommemberid(memberId);
    	//设置评分等级条件
		if(StringUtils.isNotEmpty(gevalscore) && !"0".equals(gevalscore)){
			evaluateGoods.setGevalScore(Integer.parseInt(gevalscore));
		}
		//设置有无评分内容
		
		//有评分内容
		if(StringUtils.isNotEmpty(havecontent) && "1".equals(havecontent)){
			evaluateGoods.setHaveContent(Integer.parseInt(havecontent));
		}
		//无评分内容
		if(StringUtils.isNotEmpty(havecontent) && "2".equals(havecontent)){
			evaluateGoods.setHaveContent(Integer.parseInt(havecontent));
		}
    	pager.setCondition(evaluateGoods);//实体加载在pager中
    	pager.setPageNo(Integer.parseInt(pageNoStr));
    	pager.setPageSize(5);
		List<EvaluateGoods> results = evaluateGoodsService.findPageList(pager);// 结果集
    	model.addAttribute("datas", results);
    	model.addAttribute("recordCount", pager.getTotalRows());// 总数
    	model.addAttribute("pageCount", pager.getTotalRows());// 总数
    	model.addAttribute("toUrl","/myReviews/reviewsBuyerList");
    	model.addAttribute("pageSize", pager.getPageSize());
    	model.addAttribute("pageNo", pager.getPageNo());
    	model.addAttribute("havecontent", havecontent);
    	model.addAttribute("gevalscore", gevalscore);
		return "/user/reviews/my-buyer-reviews";
    }
    
    /**
     * 来自卖家的评价
     * @param model
     * @param reviewsType
     * @return
     */
    @RequestMapping("/reviewsSellerList")
    public ModelAndView reviewsStoreList(){
    	ModelAndView model = new ModelAndView("/user/reviews/my-seller-reviews");
    	Member member = memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
    	model.addObject("member", member);
		return model;
    }
    
    /**
     * 给他人的评价
     * @param model
     * @param reviewsType
     * @return
     */
    @RequestMapping("/reviewsOtherList")
    public ModelAndView reviewsOtherList(){
    	ModelAndView model = new ModelAndView("/user/reviews/my-orther-reviews");
    	Member member = memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
    	model.addObject("member", member);
		return model;
    }
    
    /**
     * 原有评价主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(){
    	
    	ModelAndView model = new ModelAndView("/user/reviews/my-reviews");
    	Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        model.addObject("member",member);
    	model.addObject("titleName", "我的评价");
		model.addObject("cur", "myreviews");
		
        return model;
    }
    
    /**
     * 原有评价管理列表查询
     * @param div
     * @param pageNo
     * @param addTime
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(required=false, value="div",defaultValue="")String div,
                             @RequestParam(required=false, value="pageNo",defaultValue="")String pageNo,
                             @RequestParam(required=false, value="addTime",defaultValue="")String addTime){
        try {
            Pager pager = new Pager();
            EvaluateGoods evaluateGoods = new EvaluateGoods();
            evaluateGoods.setGevalFrommemberid(CacheUtils.getCacheUser().getMember().getMemberId());
            if(StringUtils.isNotBlank(pageNo)){
                pager.setPageNo(Integer.parseInt(pageNo));
            }
            if("2".equals(addTime)){
                evaluateGoods.setStartTime(DateUtils.strToLong(DateUtils.getMonth(
                        DateUtils.getDateStr(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", -12)));
                evaluateGoods.setEndTime(DateUtils.strToLong(DateUtils.getMonth(
                        DateUtils.getDateStr(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", -3)));
            }else{
                evaluateGoods.setStartTime(DateUtils.strToLong(DateUtils.getMonth(
                        DateUtils.getDateStr(new Date()), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss", -3)));
                evaluateGoods.setEndTime(DateUtils.strToLong(DateUtils.getDateStr(new Date())));
            }
            ModelAndView model = new ModelAndView("/user/reviews/my-reviews-list");
            pager.setCondition(evaluateGoods);
            List<EvaluateGoods> lists = evaluateGoodsService.findPageList(pager);// 结果集
            model.addObject("data", lists);// 结果集
            model.addObject("pageNo", pager.getPageNo());// 当前页
            model.addObject("pageSize", pager.getPageSize());// 每页显示条数
            model.addObject("recordCount", pager.getTotalRows());// 总数
            model.addObject("toUrl", "/myReviews/list");
            model.addObject("div", div);// 显示的DIV数据区域
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("我的评价加载失败！",e.toString());
            throw new RuntimeException("导航失败!");
        }
    }

    @RequestMapping("/reviews")
    public String reviews(Model model,@RequestParam long orderSn,@RequestParam int recId){

        OrderGoods orderGoods = orderGoodsService.findById(recId);
        EvaluateStore evaluateStore = evaluateStoreService.findEvaluateStore(orderGoods.getStoreId());
        model.addAttribute("evaluateStore",evaluateStore);
        model.addAttribute("orderSn",orderSn);
        model.addAttribute("recId",recId);
        model.addAttribute("orderGoods",orderGoods);
        model.addAttribute("specInfo", orderGoods.getSpecInfo());//添加
        Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
        model.addAttribute("member",member);
        
        return "user/reviews/pro-reviews";
    }

    @RequestMapping("/saveReviews")
    public @ResponseBody Map<String,Object> saveReviews(@RequestParam long orderSn,
    													@RequestParam int recId,
    													@RequestParam String specInfo,
    													@RequestParam(value="goodsImageMore",required=false,defaultValue="") String goodsImageMore,
    													@ModelAttribute EvaluateStore evaluateStore,
    													@ModelAttribute EvaluateGoods evaluateGoods){

        Map<String,Object> map = Maps.newHashMap();
        Member member = CacheUtils.getCacheUser().getMember();
        try{
        	if(StringUtils.isNotBlank(goodsImageMore)){
        		evaluateGoods.setGevalImage(goodsImageMore);
        	}
            map = evaluateService.saveEvaluate(orderSn,recId,evaluateStore,evaluateGoods, member.getMemberId(), member.getMemberName(),specInfo);
        }catch (Exception e){
        	e.printStackTrace();
            log.error("评价失败",e.toString());
            map.put("success",false);
            map.put("msg","评论失败");
        }
        return map;
    }
    
    @RequestMapping(value="/imageFileUpload")
    public String imageFileUpload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response){
    	response.setContentType("text/plain; charset=UTF-8");
    	response.setContentType("text/html");
    	//可以在上传文件的同时接收其它参数
        Map<String, Object> map = Maps.newHashMap();
        //已上传评论图片数量
        String imgNo = request.getParameter("imgNo");
        //判断评论上传图片不能大于三张
    	if(myfiles.length+Integer.valueOf(imgNo)>3){
    		map.put("success",false);
    		map.put("result", "最多上传三张图片");
    		String json = JsonUtils.toJsonStr(map);
    		try {
				response.getWriter().write(json);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
		String type = request.getParameter("type");
		
        
        String originalFilename = null;
        
        StringBuffer photoSrc = new StringBuffer();//StringBuffer用来存放上传文件的所有地址
        StringBuffer photoNewName = new StringBuffer();//用来存放图片的新名字
        StringBuffer oldName = new StringBuffer();//原来的名字
        for(MultipartFile myfile : myfiles){
            if(myfile.isEmpty()){
                map.put("result", "请选择文件后上传");
                map.put("success", false);
            }else{
                originalFilename = String.valueOf(new DateTime().getMillis())+ myfile.getOriginalFilename().substring( myfile.getOriginalFilename().indexOf("."));
               try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					log.error("",e1);
				}                
                try {
            		String realPath = CommonConstants.FILE_BASEPATH + Constants.EVALUATE_UPLOAD_URL + "/" ;
            		//这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
            		//此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
            		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
            		//上传成功的时候将图片的地址给已经准备好的Stringbuffer
            		photoSrc.append(CommonConstants.FILE_BASEPATH + Constants.EVALUATE_UPLOAD_URL+"/" + originalFilename + ",");
            		//上传成功的时候将图片的新名字给StringBuffer
            		photoNewName.append(originalFilename +  ",");
                } catch (IOException e) {
                	if("attach"==type){
                		log.error("文件[" + myfile.getOriginalFilename() + "]上传失败,堆栈轨迹如下");
                	}else{
                		log.error("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
                	}
                    map.put("result", "文件上传失败，请重试！！");
                    map.put("success", false);
                    
                }
            }
        }
        if("attach".equals(type)){
        	 map.put("oldName", oldName.toString());
        }
        map.put("imgPath",Constants.EVALUATE_UPLOAD_URL+ "/");
        map.put("photoNewName", photoNewName.toString());
        map.put("photoSrc", photoSrc.toString());
        map.put("filename", originalFilename);
		map.put("success", true);
		map.put("listimgSize", myfiles.length+"");
		String json = JsonUtils.toJsonStr(map);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
    }
}
