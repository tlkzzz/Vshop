package com.Vshop.service.module.trade.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.Payment;
import com.Vshop.core.entity.base.PredepositLog;
import com.Vshop.core.entity.base.PredepositRecharge;
import com.Vshop.core.entity.base.ShopPointsLog;
import com.Vshop.service.module.member.common.PointsLogType;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.member.service.ShopPointsLogService;
import com.Vshop.service.module.setting.service.PaymentService;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.module.trade.service.PredepositLogService;
import com.Vshop.service.module.trade.service.PredepositRechargeService;
import com.Vshop.service.module.trade.service.PredepositService;

/**
 * 预付款相关
 * @author liukai
 */
@Service
public class PredepositServiceImpl implements PredepositService{
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private PredepositLogService predepositLogService;
	
	@Resource
	private PredepositRechargeService predepositRechargeService;
	
	@Resource
	private PaymentService paymentService;
	
	@Resource
	private ShopPointsLogService shopPointsLogService;
	
	@Resource
	private SettingService settingService;
	
	/**
	 * 预付款充值
	 * @param memberId 用户id
	 * @param pdrAmount 充值金额
	 */
	public PredepositRecharge addRechargePredeposit(Integer memberId,BigDecimal pdrAmount){
		try{
			Member member = memberService.findById(memberId);
			//创建一个新的预付款充值编号
			String pdrSn = "R"+DateUtils.getDateStr("yyyyMMddHHmmssSSS");
			//新建一个预付款充值实体
			PredepositRecharge predepositRecharge = new PredepositRecharge();
			predepositRecharge.setPdrSn(pdrSn); //记录唯一标示
			predepositRecharge.setPdrMemberId(member.getMemberId()); //会员编号
			predepositRecharge.setPdrMemberName(member.getMemberName()); //会员名称
			predepositRecharge.setPdrAmount(pdrAmount); //充值金额
			predepositRecharge.setPdrPaymentState("0"); //支付状态:未支付
			//保存预付款充值表
			predepositRechargeService.savePdr(predepositRecharge);
			
			//创建一个新的变更日志实体
			PredepositLog predepositLog = new PredepositLog();
			predepositLog.setLgMemberId(member.getMemberId()); //会员编号
			predepositLog.setLgMemberName(member.getMemberName()); //会员名称
			predepositLog.setLgType("recharge"); //操作类型:充值
			predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); //可用金额变更0表示未变更
			predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); //冻结金额变更0表示未变更
			predepositLog.setLgDesc("生成预付款充值信息"); //描述
			predepositLog.setCreateTime(System.currentTimeMillis()); //添加时间
			//保存预存款变更日志表
			predepositLogService.savePdl(predepositLog);
			
			return predepositRecharge;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 充值成功
	 * @param pdrSn 充值表编号
	 * @param memberId 用户id
	 */
	public void updateFinishRecharge(String pdrSn){
		try{
			PredepositRecharge predepositRecharge = predepositRechargeService.findByPdrSn(pdrSn);
			//判断是否存在支付表信息
			if(predepositRecharge!=null){ 
				//判断是否已支付
				if(predepositRecharge.getPdrPaymentState().equals("0")){ //未支付
					predepositRecharge.setPdrPaymentState("1"); //支付状态:已支付
					predepositRecharge.setPdrPaymentTime(System.currentTimeMillis()); //支付时间
					//修改预存款充值表
					predepositRechargeService.updatePdr(predepositRecharge);
					
					//创建一个新的变更日志实体
					PredepositLog predepositLog = new PredepositLog();
					predepositLog.setLgMemberId(predepositRecharge.getPdrMemberId()); //会员编号
					predepositLog.setLgMemberName(predepositRecharge.getPdrMemberName()); //会员名称
					predepositLog.setLgType("recharge"); //操作类型:充值
					predepositLog.setLgAvAmount(predepositRecharge.getPdrAmount()); //可用金额变更0表示未变更
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); //冻结金额变更0表示未变更
					predepositLog.setLgDesc("充值成功"); //描述
					predepositLog.setCreateTime(System.currentTimeMillis()); //添加时间
					//保存预存款变更日志表
					predepositLogService.savePdl(predepositLog);
					
					/**
					 * 修改用户余额信息
					 */
					Member member = memberService.findById(predepositRecharge.getPdrMemberId());
					//预存款可用金额 = 原有金额 + 充值金额
					double available = member.getAvailablePredeposit().doubleValue() + predepositRecharge.getPdrAmount().doubleValue();
					member.setAvailablePredeposit(BigDecimal.valueOf(available)); //预存款可用金额
					memberService.update(member);
					
					/**
					 * 订单积分修改,订单积分日志保存
					 */
					Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(predepositRecharge.getPdrAmount()));;
					Integer rankPoint = member.getMemberRankPoints();
					Integer consPoint = member.getMemberConsumePoints();
					if(rankPoint==null) rankPoint = 0;
					if(consPoint==null) consPoint = 0;
					//获取积分设置余额充值(一元等于多少积分)等级积分
					String rankSettingPoints = settingService.findByNameAndCode("points", "recharge_rank");
					//获取积分设置余额充值(一元等于多少积分)消费积分
					String consSettingPoints = settingService.findByNameAndCode("points", "recharge_cons");
					if(StringUtils.isNotBlank(rankSettingPoints)){
						rankPoint += Integer.valueOf(rankSettingPoints)*points;
					}else{ //若没设置余额充值等级积分,则按(一元等于一积分计算)
						rankPoint += points;
					}
					if(StringUtils.isNotBlank(consSettingPoints)){
						consPoint += Integer.valueOf(consSettingPoints)*points;
					}else{ //若没设置余额充值消费积分,则按(一元等于一积分计算)
						consPoint += points;
					}
					//修改用户等级积分
					member.setMemberRankPoints(rankPoint);
					//修改用户消费积分
					member.setMemberConsumePoints(consPoint);
					//修改用户积分
					memberService.update(member);
					
					ShopPointsLog shopPointsLog = new ShopPointsLog();
					shopPointsLog.setMemberid(member.getMemberId());
					shopPointsLog.setMembername(member.getMemberName());
					shopPointsLog.setAdminid(1);
					shopPointsLog.setAdminname("admin");
					shopPointsLog.setPoints(consPoint);
					shopPointsLog.setCreateTime(System.currentTimeMillis());
					shopPointsLog.setType(PointsLogType.POINTS_TYPE_RECHARGE); //积分操作类型
					shopPointsLog.setDesc("充值完成");
					shopPointsLog.setStage("充值成功,增加会员积分");
					//保存会员积分日志表
					shopPointsLogService.save(shopPointsLog);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改预付款充值表支付信息
	 * @param pdrSn
	 * @param paymentId
	 */
	public void updateRechargePredeposit(String pdrSn,Integer paymentId){
		try{
			Payment payment = paymentService.findById(paymentId.longValue());
			PredepositRecharge predepositRecharge = predepositRechargeService.findByPdrSn(pdrSn);
			predepositRecharge.setPdrPaymentCode(payment.getPaymentCode());  //支付方式编号
			predepositRecharge.setPdrPaymentName(payment.getPaymentName());  //支付方式名称
			//修改预存款充值表
			predepositRechargeService.updatePdr(predepositRecharge);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
