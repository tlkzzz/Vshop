package com.Vshop.front.module.user.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.PredepositRecharge;
import com.Vshop.front.module.alipay.service.AlipayService;
import com.Vshop.front.module.unionpay.service.UnionpayService;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.trade.service.PredepositService;

/**
 * 我的余额
 * @author liukai
 */
@Controller
@RequestMapping("/predeposit")
@Slf4j
public class MyPredepositAction {
	
	@Resource
	private PredepositService predepositService;
	
	@Resource
    private MemberService memberService;
	
	@Resource 
	private UnionpayService unionpayService;
    
    @Resource 
   	private AlipayService alipayService;
	
	/**
	 * 余额信息页面
	 * @return
	 */
	@RequestMapping("/predepositIndex")
	public ModelAndView predepositIndex(){
		try {
			ModelAndView model = new ModelAndView("/user/predeposit/predeposit-index");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("member",member);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("余额充值页加载失败！");
			throw new RuntimeException("加载失败!");
		}
	}
	
	/**
	 * 余额信息页面
	 * @return
	 */
	@RequestMapping("/rechargeIndex")
	public ModelAndView rechargeIndex(){
		try {
			ModelAndView model = new ModelAndView("/user/predeposit/recharge-index");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			model.addObject("member",member);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("余额充值页加载失败！");
			throw new RuntimeException("加载失败!");
		}
	}
	
	/**
	 * 余额信息页面
	 * @return
	 */
	@RequestMapping("/recharge")
	public ModelAndView recharge(
							@RequestParam(value="amount") Double amount
							){
		try {
			ModelAndView model = new ModelAndView("/user/predeposit/recharge");
			Member member =  memberService.findMemberById(CacheUtils.getCacheUser().getMember().getMemberId());
			PredepositRecharge predepositRecharge =  predepositService.addRechargePredeposit(member.getMemberId(), BigDecimal.valueOf(amount));
			model.addObject("predepositRecharge", predepositRecharge);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("余额充值页加载失败！");
			throw new RuntimeException("加载失败!");
		}
	}
	
	 /**
     * 去付款
     * @param @return 设定文件
     * @return ModelAndView    返回类型
     * @throws
     * @Title: gotopay
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    @RequestMapping("/gotopay")
    public void orderpay(@RequestParam(value = "pdrSn") String pdrSn,
    					@RequestParam("paymentCode") String paymentCode,
    					@RequestParam("paymentId") Integer paymentId,
    					HttpServletRequest request ,HttpServletResponse response) {
        try {
        	String sHtmlText = "";
 			if(StringUtils.isNotEmpty(pdrSn)&&paymentCode.equals("ZFB")){
 				//修改预付款充值表信息
 				predepositService.updateRechargePredeposit(pdrSn, paymentId);
 				sHtmlText=alipayService.toPay(pdrSn);
 			}else if(StringUtils.isNotEmpty(pdrSn)&&paymentCode.equals("YL")){
 				//修改预付款充值表信息
 				predepositService.updateRechargePredeposit(pdrSn, paymentId);
 				sHtmlText = unionpayService.toUnionpay(pdrSn);//构造提交银联的表单
 			}else{
 				
 			}
 			response.setCharacterEncoding("UTF-8");
 			response.getWriter().write(sHtmlText);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("提交付款页加载失败！");
            throw new RuntimeException("导航失败!");
        }
    }
}
