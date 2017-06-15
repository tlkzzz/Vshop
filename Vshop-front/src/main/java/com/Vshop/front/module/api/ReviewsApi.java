package com.Vshop.front.module.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.Vshop.core.common.Constants;
import com.Vshop.core.common.FileUtils;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.base.Member;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.user.service.EvaluateService;
import com.Vshop.service.utils.CommonConstants;

/**
 * 课程评论api
 * @author cgl
 * @date 2015年08月25日10:42:16
 */
@Controller
@RequestMapping("/reviews/api")
public class ReviewsApi extends BaseApi {
	
	@Resource
	private EvaluateService evaluateService;
	
	@Resource
    private MemberService memberService;
	
	/**
	 * 保存课程评价
	 * @param orderSn 订单编号
	 * @param recId 课程订单id
	 * @param sevalDesccredit  学院描述相符评分
	 * @param sevalServicecredit 学院服务态度评分
	 * @param sevalDeliverycredit 学院发货速度评分
	 * @param gevalScore 课程评分1-5分
	 * @param gevalContent 课程信誉评价内容
	 * @param gevalIsAnonymous 课程是否匿名评价 1表示是匿名评价,否则0
	 * @param memberId 会员id
	 * @param image 晒单图片
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/saveReviews")
    public JSONObject saveReviews(
    		HttpServletRequest request
    		){
		jsonObj = new JSONObject();
        String orderSn = ParamsUtils.getString(request.getParameter("orderSn"));
        int recId = ParamsUtils.getInt(request.getParameter("recId"));
        String sevalDesccredit = ParamsUtils.getString(request.getParameter("sevalDesccredit"));
        String sevalServicecredit = ParamsUtils.getString(request.getParameter("sevalServicecredit"));
        String sevalDeliverycredit = ParamsUtils.getString(request.getParameter("sevalDeliverycredit"));
        int gevalScore = ParamsUtils.getInt(request.getParameter("gevalScore"));
        String gevalContent = ParamsUtils.getString(request.getParameter("gevalContent"));
        int gevalIsAnonymous = ParamsUtils.getInt(request.getParameter("gevalIsAnonymous"));
        int memberId = ParamsUtils.getInt(request.getParameter("memberId"));
        Member m = memberService.findById(memberId);
        try{
        	
        	EvaluateStore evaluateStore = new EvaluateStore();
        	evaluateStore.setSevalDesccredit(Double.valueOf(sevalDesccredit));
        	evaluateStore.setSevalServicecredit(Double.valueOf(sevalServicecredit));
        	evaluateStore.setSevalDeliverycredit(Double.valueOf(sevalDeliverycredit));
        	
        	EvaluateGoods evaluateGoods = new EvaluateGoods();
        	evaluateGoods.setGevalScore(gevalScore);
        	evaluateGoods.setGevalContent(gevalContent);
        	evaluateGoods.setGevalIsAnonymous(gevalIsAnonymous);
        	
        	if(ServletFileUpload.isMultipartContent(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
				// 得到上传的图片数据
				List<MultipartFile> portrait = multipartRequest.getFiles("images");
				if(portrait.size() > 0){
					MultipartFile[] list = portrait.toArray(new MultipartFile[portrait.size()]);
					Map<String, Object> map = FileUtils.fileUpload(list, CommonConstants.FILE_BASEPATH, Constants.MEMBER_UPLOAD_URL, request,"images",1);
					if("true".equals(map.get("success") + "")){
						evaluateGoods.setGevalImage(map.get("result") + "");
					}
				}
			}
        	
            Map<String,Object> map = evaluateService.saveEvaluate(Long.valueOf(orderSn),recId,evaluateStore,evaluateGoods, memberId, m.getMemberName());
            
			if((Boolean)map.get("success") == true){
	            jsonObj.put("result", 1);
	            jsonObj.put("msg", "评论成功");
			}else{
				jsonObj.put("result", 0);
	            jsonObj.put("msg", "评论失败");
			}
        }catch (Exception e){
        	e.printStackTrace();
			jsonObj.put("result", 0);
            jsonObj.put("msg", "评论失败");
        }
        return jsonObj;
    }
	
}
