package com.Vshop.front.module.api;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.common.MyBeanUtils;
import com.Vshop.core.entity.apibean.PredepositRechargeApiBean;
import com.Vshop.core.entity.base.PredepositRecharge;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.trade.service.PredepositService;

/**
 * 余额api接口
 * @author liukai
 */
@Slf4j
@Controller
@RequestMapping("/predepositApi")
public class PredepositApi extends BaseApi {
	
	@Resource
	private PredepositService predepositService;
	
	/**
	 * 余额充值
	 * @param amount 充值金额
	 * @param memberId 用户id
	 * @param request
	 * @return
	 */
	@RequestMapping("recharge")
	@ResponseBody
	public JSONObject saveOrder(
			@RequestParam(value="amount") Double amount,
			@RequestParam(value="memberId") Integer memberId,
			HttpServletRequest request
			){
		jsonObj = new JSONObject();
		try {
			PredepositRecharge predepositRecharge =  predepositService.addRechargePredeposit(memberId, BigDecimal.valueOf(amount));
			PredepositRechargeApiBean rechargeApiBean = new PredepositRechargeApiBean();
			MyBeanUtils.copyBeanNotNull2Bean(predepositRecharge, rechargeApiBean);
			jsonObj.put("data", JSONArray.fromObject(rechargeApiBean, JsonUtils.getJsonConfig()));
			jsonObj.put("result", 1);
            jsonObj.put("msg", "保存成功");
		} catch (Exception e) {
			log.error("订单保存API出错", e);
			jsonObj.put("result", 0);
            jsonObj.put("msg", "保存失败");
		}
		return jsonObj;
	}

}
