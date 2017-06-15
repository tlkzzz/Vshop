package com.Vshop.service.module.goods.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ConsumerCodeLog;
import com.Vshop.core.entity.base.ConsumerCodeSend;
import com.Vshop.service.module.goods.dao.ConsumerCodeSendDao;
import com.Vshop.service.module.goods.service.ConsumerCodeLogService;
import com.Vshop.service.module.goods.service.ConsumerCodeSendService;
import com.Vshop.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerCodeSendServiceImpl implements ConsumerCodeSendService {

	@Autowired
	ConsumerCodeSendDao consumerCodeSendDao;
	
	@Autowired
	ConsumerCodeLogService consumerCodeLogService;
	
	public static int BUNCH_DIGIT = 8;
	
	@Override
	public ConsumerCodeSend sendCode(ConsumerCodeSend consumerCodeSend) throws Exception {
		
		if (consumerCodeSend != null) {
			if (consumerCodeSend.getSendUser() == null || consumerCodeSend.getSendUser() <= 0) {
				throw new Exception("发码人不能为空！--ConsumerCodeSend.SendUser");
			} else if (consumerCodeSend.getOrderId() == null || consumerCodeSend.getOrderId() <= 0) {
				throw new Exception("需要关联订单Id！--ConsumerCodeSend.OrderId");
			} else if (consumerCodeSend.getGoodsId() == null || consumerCodeSend.getGoodsId() <= 0) {
				throw new Exception("需要关联课程Id！--ConsumerCodeSend.GoodsId");
			} else if (consumerCodeSend.getOrderSn() == null || consumerCodeSend.getOrderSn().length() <= 0) {
				throw new Exception("需要关联订单号！--ConsumerCodeSend.OrderSn");
			}
			
			ConsumerCodeSend ccs = new ConsumerCodeSend();
			ccs.setSendUser(consumerCodeSend.getSendUser());
			ccs.setOrderId(consumerCodeSend.getOrderId());
			ccs.setGoodsId(consumerCodeSend.getGoodsId());

			Pager pager = new Pager();
			pager.setCondition(ccs);

			/** 发码的时候，是否需要根据订单及商品查询判断是否已经发过码，防止重复发码**/
			List<ConsumerCodeSend> ccs_list = findPageList(pager);
			if (ccs_list != null && ccs_list.size() > 0 && ccs_list.get(0) != null) {
				ConsumerCodeSend ccs2 = ccs_list.get(0);
				throw new Exception("此订单关联的商品已经发布，请勿重复发码[" + ccs2.getConsumerCodeBunch() + "]");
			}
			
			// 赋值课程码 开始
			// 目前暂定8位消费码
			String consumerCodeBunch = makeConsumerCodeBunch(BUNCH_DIGIT);
			int i = consumerCodeSendDao.countByCode(consumerCodeBunch);
			int times = 1;
			while (i > 0) { // 已存在
				// 生成课程码
				consumerCodeBunch = makeConsumerCodeBunch(BUNCH_DIGIT);
				// 查询课程码是否已经存在
				i = consumerCodeSendDao.countByCode(consumerCodeBunch);
				times ++;
			}

			if (times > 5) {
				log.error("生成1个" + BUNCH_DIGIT + "位消费码，重复次数为" + (times - 1) + ", 已经大于5次，请考虑换算法");
				log.info("生成1个" + BUNCH_DIGIT + "位消费码，重复次数为" + (times - 1) + ", 已经大于5次，请考虑换算法");
				log.debug("生成1个" + BUNCH_DIGIT + "位消费码，重复次数为" + (times - 1) + ", 已经大于5次，请考虑换算法");
			}
			consumerCodeSend.setConsumerCodeBunch(consumerCodeBunch);
			// 赋值课程码 结束
			
			// 记录发码时间
			if (consumerCodeSend.getSendTime() == null || consumerCodeSend.getSendTime() <= 0) {
				consumerCodeSend.setSendTime(System.currentTimeMillis());
			}

			if (consumerCodeSend.getValidityStatus() == null) {
				consumerCodeSend.setValidityStatus(1);
			}
			
			// 0,有有效期；1,永久有效，设置有效期时间止
			if (new Integer(0).equals(consumerCodeSend.getValidityStatus())) {
				if (consumerCodeSend.getValidityTime() != null && consumerCodeSend.getValidityTime() > 0) {
					consumerCodeSend.setValidityEndTime(
							getValidityEndTime(consumerCodeSend.getValidityTime(), Calendar.DAY_OF_MONTH)
									.getTimeInMillis());
				}
			}

			consumerCodeSend.setCodeStatus(1); // 已发
			Integer pkid = consumerCodeSendDao.save(consumerCodeSend);
			
			consumerCodeLogService.save(consumerCodeSend, "发码");
			
			log.info("consumerCodeSend.getConsumerCodeId():" + consumerCodeSend.getConsumerCodeId());
			log.info("consumerCodeSendDao.save(consumerCodeSend) result = " + pkid);
		} else {
			throw new Exception("发码初始化参数对象ConsumerCodeSend不能为空，系统错误，请联系系统管理员！");
		}
		
		return consumerCodeSend;
	}

	@Override
	public ConsumerCodeSend receiveCode(ConsumerCodeSend consumerCodeSend) throws Exception {
		if (consumerCodeSend == null) {
			throw new Exception("收码初始化参数对象ConsumerCodeSend不能为空，系统错误，请联系系统管理员！");
		}

		if (consumerCodeSend.getConsumerCodeBunch() == null) {
			throw new Exception("课程码为空！");
		} else if (consumerCodeSend.getConsumerCodeBunch().length() != BUNCH_DIGIT) {
			throw new Exception("课程码为" + BUNCH_DIGIT + "位字符串！");
		}

		String consumerCodeBunch = consumerCodeSend.getConsumerCodeBunch().toUpperCase();
		ConsumerCodeSend sendCode = findByCode(consumerCodeBunch);

		if (sendCode == null) {
			throw new Exception("课程码" + consumerCodeBunch + "在发码库中不存在，收码失败！");
		} else {
			if (sendCode.getCodeStatus() == 2) {
				throw new Exception("课程码" + consumerCodeBunch + "已经被收码，收码失败！");
			} else if (sendCode.getCodeStatus() == 3) {
				throw new Exception("课程码" + consumerCodeBunch + "已经过期作废，收码失败！");
			} else if (sendCode.getCodeStatus() == 4) {
				throw new Exception("课程码" + consumerCodeBunch + "已经撤回作废，收码失败！");
			}

			if (consumerCodeSend.getGoodsId() != null && !consumerCodeSend.getGoodsId().equals(sendCode.getGoodsId())) {
				throw new Exception("对应的课程Id不一致，收码失败！");
			} else if (consumerCodeSend.getOrderId() != null && !consumerCodeSend.getOrderId().equals(sendCode.getOrderId())) {
				throw new Exception("对应的订单Id不一致，收码失败！");
			} else if (consumerCodeSend.getOrderSn() != null && !consumerCodeSend.getOrderSn().equals(sendCode.getOrderSn())) {
				throw new Exception("对应的订单号不一致，收码失败！");
			}
			
			// 记录修改时间
			if (consumerCodeSend.getReceiveTime() == null || consumerCodeSend.getReceiveTime() <= 0) {
				consumerCodeSend.setReceiveTime(System.currentTimeMillis());
			}

			if (sendCode.getValidityStatus() != null && sendCode.getValidityStatus() == 0) { // 有效期判断
				long validityEndTime = System.currentTimeMillis(); // 有效期至
				if (sendCode.getValidityEndTime() == null || sendCode.getValidityEndTime() <= 0) {
					if (sendCode.getValidityTime() == null || sendCode.getValidityTime() <= 0) {
						throw new Exception("课程码" + consumerCodeBunch + "有效期天数为空或小于等于0，发码存在问题，收码失败！");
					} else {
						validityEndTime = getValidityEndTime(sendCode.getSendTime(), sendCode.getValidityTime(),
								Calendar.DAY_OF_MONTH).getTimeInMillis();
					}
				} else {
					validityEndTime = sendCode.getValidityEndTime();
				}
				
				if (System.currentTimeMillis() > validityEndTime) {
					consumerCodeSend.setCodeStatus(3); // 过期
					consumerCodeSendDao.updateByCode(consumerCodeSend);
					consumerCodeLogService.save(consumerCodeSend, "过期，系统自动修改状态");
					log.info("updateByCode(" + consumerCodeSend.getConsumerCodeBunch() + ")");
					throw new Exception("课程码" + consumerCodeBunch + "已经过了有效期，收码失败！");
				}
			}
			
			consumerCodeSend.setCodeStatus(2);
			consumerCodeSendDao.updateByCode(consumerCodeSend);
			
			consumerCodeLogService.save(consumerCodeSend, "收码");
			log.info("updateByCode(" + consumerCodeSend.getConsumerCodeBunch() + ")");
		}
		
		return consumerCodeSend;
	}
	
	/**
	 * 撤回作废码
	 * @param consumerCodeSend
	 * @return
	 * @throws Exception
	 */
	public ConsumerCodeSend recallCode(ConsumerCodeSend consumerCodeSend) throws Exception {
		if (consumerCodeSend == null) {
			throw new Exception("收码初始化参数对象ConsumerCodeSend不能为空，系统错误，请联系系统管理员！");
		}
		
		if (consumerCodeSend.getConsumerCodeBunch() == null) {
			throw new Exception("课程码为空！");
		} else if (consumerCodeSend.getConsumerCodeBunch().length() != BUNCH_DIGIT) {
			throw new Exception("课程码为" + BUNCH_DIGIT + "位字符串！");
		}
		
		String consumerCodeBunch = consumerCodeSend.getConsumerCodeBunch().toUpperCase();
		ConsumerCodeSend sendCode = findByCode(consumerCodeBunch);
		
		if (sendCode == null) {
			throw new Exception("课程码" + consumerCodeBunch + "在发码库中不存在，操作失败！");
		} else {
			if (sendCode.getCodeStatus() == 2) {
				throw new Exception("课程码" + consumerCodeBunch + "已经被收码，操作失败！");
			} else if (sendCode.getCodeStatus() == 3) {
				throw new Exception("课程码" + consumerCodeBunch + "已经过期作废，操作失败！");
			} else if (sendCode.getCodeStatus() == 4) {
				throw new Exception("课程码" + consumerCodeBunch + "已经撤回作废，操作失败！");
			}

			// 记录修改时间
			if (consumerCodeSend.getReceiveTime() == null || consumerCodeSend.getReceiveTime() <= 0) {
				consumerCodeSend.setReceiveTime(System.currentTimeMillis());
			}

			if (sendCode.getValidityStatus() != null && sendCode.getValidityStatus() == 0) { // 有效期判断
				long validityEndTime = System.currentTimeMillis(); // 有效期至
				if (sendCode.getValidityEndTime() == null || sendCode.getValidityEndTime() <= 0) {
					if (sendCode.getValidityTime() == null || sendCode.getValidityTime() <= 0) {
						throw new Exception("课程码" + consumerCodeBunch + "有效期天数为空或小于等于0，发码存在问题，操作失败！");
					} else {
						validityEndTime = getValidityEndTime(sendCode.getSendTime(), sendCode.getValidityTime(),
								Calendar.DAY_OF_MONTH).getTimeInMillis();
					}
				} else {
					validityEndTime = sendCode.getValidityEndTime();
				}
				
				if (System.currentTimeMillis() > validityEndTime) {
					consumerCodeSend.setCodeStatus(3); // 过期
					consumerCodeSendDao.updateByCode(consumerCodeSend);
					consumerCodeLogService.save(consumerCodeSend, "过期，系统自动修改状态");
					log.info("updateByCode(" + consumerCodeSend.getConsumerCodeBunch() + ")");
					throw new Exception("课程码" + consumerCodeBunch + "已经过了有效期，操作失败！");
				}
			}
			
			consumerCodeSend.setCodeStatus(4); // 撤回作废
			consumerCodeSendDao.updateByCode(consumerCodeSend);
			
			consumerCodeLogService.save(consumerCodeSend, "撤回作废");
			log.info("updateByCode(" + consumerCodeSend.getConsumerCodeBunch() + ")");
		}
		
		return consumerCodeSend;
	}
	
	@Override
	public boolean updateById(ConsumerCodeSend consumerCodeSend) {
		// id存在有效，并且更新状态不能逆改(2/3/4->1) （1:已发,2:已收,3:过期作废,4撤回作废）
		if (consumerCodeSend != null && consumerCodeSend.getCodeStatus() != null && consumerCodeSend.getCodeStatus() > 1
				&& consumerCodeSend.getConsumerCodeId() != null && consumerCodeSend.getConsumerCodeId() > 0) {

			// 记录修改时间
			if (consumerCodeSend.getReceiveTime() == null || consumerCodeSend.getReceiveTime() <= 0) {
				consumerCodeSend.setReceiveTime(System.currentTimeMillis());
			}

			consumerCodeSendDao.updateById(consumerCodeSend);
			log.info("updateById(" + consumerCodeSend.getConsumerCodeId() + ")");
			
			ConsumerCodeLog consumerCodeLog = new ConsumerCodeLog();
			consumerCodeLog.setConsumerCodeBunch(String.valueOf(consumerCodeSend.getConsumerCodeId()));
			consumerCodeLog.setCodeStatus(consumerCodeSend.getCodeStatus());
			consumerCodeLog.setLogTime(System.currentTimeMillis());
			// consumerCodeLog.setLogFlag(1);
			// consumerCodeLog.setLogIp("");
			consumerCodeLog.setLogUser(consumerCodeSend.getSendUser());
			consumerCodeLog.setLogResult("updateById(ConsumerCodeSend consumerCodeSend)");

			consumerCodeLogService.save(consumerCodeLog);
//			consumerCodeLogService.save(consumerCodeSend, "updateById(ConsumerCodeSend consumerCodeSend)");
			
			return true;
		}

		return false;
	}

	@Override
	public boolean updateByCode(ConsumerCodeSend consumerCodeSend) {
		// id存在有效，并且更新状态不能逆改(2/3/4->1) （1:已发,2:已收,3:过期作废,4撤回作废）
		if (consumerCodeSend != null && consumerCodeSend.getCodeStatus() != null && consumerCodeSend.getCodeStatus() > 1
				&& consumerCodeSend.getConsumerCodeBunch() != null
				&& consumerCodeSend.getConsumerCodeBunch().length() == 8) {

			// 记录修改时间
			if (consumerCodeSend.getReceiveTime() == null || consumerCodeSend.getReceiveTime() <= 0) {
				consumerCodeSend.setReceiveTime(System.currentTimeMillis());
			}
			consumerCodeSendDao.updateByCode(consumerCodeSend);
			log.info("updateByCode(" + consumerCodeSend.getConsumerCodeBunch() + ")");
			
			consumerCodeLogService.save(consumerCodeSend, "updateByCode(ConsumerCodeSend consumerCodeSend)");
			
			return true;
		}

		return false;
	}

	@Override
	public ConsumerCodeSend findById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeSendDao.findById(consumerCodeId);
	}

	@Override
	public ConsumerCodeSend findByCode(String consumerCodeBunch) {
		// TODO Auto-generated method stub
		return consumerCodeSendDao.findByCode(consumerCodeBunch);
	}

	@Override
	public List<ConsumerCodeSend> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeSendDao.findPageList(pager);
	}
	
	public List<ConsumerCodeSend> findList(ConsumerCodeSend consumerCodeSend) {
		// TODO Auto-generated method stub
		
		Pager pager = new Pager();
		pager.setCondition(consumerCodeSend);
		pager.setPageSize(1000);
		
		return consumerCodeSendDao.findPageList(pager);
	}

	@Override
	public Integer countById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeSendDao.countById(consumerCodeId);
	}

	@Override
	public Integer countByCode(String consumerCodeBunch) {
		// TODO Auto-generated method stub
		return consumerCodeSendDao.countByCode(consumerCodeBunch);
	}

	/**
	 * 商品消费码组成数组
	 */
	static String[] CONSUMER_CODE_MEMBERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
			"F", "G", "H", // "I",
			"J", "K", "L", "M", "N", // "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	
	/**
	 * 生成商品消费码
	 * @param digit
	 * @return
	 */
	protected String makeConsumerCodeBunch(int digit) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			double random_digit = Math.random() * CONSUMER_CODE_MEMBERS.length;
			result.append(CONSUMER_CODE_MEMBERS[(int) Math.floor(random_digit)]);
		}
		return result.toString();
	}

	/**
	 * 计算有效期截止日期
	 * @param validity_time  有效期，单位(天)
	 * @param calendar_field 时间精度，参考DAY_OF_MONTH、HOUR_OF_DAY、MINUTE
	 * @return
	 */
	protected static Calendar getValidityEndTime(int validity_time, int calendar_field) {
		Calendar cal = Calendar.getInstance();

		if (Calendar.DAY_OF_MONTH == calendar_field) {  // 精确到天
			cal.set(Calendar.HOUR_OF_DAY, 0);	// 小时置为0
			cal.set(Calendar.MINUTE, 0);		// 分钟置为0 
			cal.set(Calendar.SECOND, 0);		// 秒钟置为0
			
			cal.add(Calendar.DAY_OF_MONTH, 1);		// 如果精确到天，需要补充1天
		} else if (Calendar.HOUR_OF_DAY == calendar_field) {  // 精确到小时
			cal.set(Calendar.MINUTE, 0);		// 分钟置为0
			cal.set(Calendar.SECOND, 0);		// 秒钟置为0
			
			cal.add(Calendar.HOUR_OF_DAY, 1);		// 如果精确到小时，需要补充1小时
		} else if (Calendar.MINUTE == calendar_field) {		// 精确到分钟
			cal.set(Calendar.SECOND, 0);
			
			cal.add(Calendar.MINUTE, 1);		// 如果精确到分钟，需要补充1分钟，保证不让用户吃亏，^_^
		}
		
		cal.add(Calendar.DAY_OF_MONTH, validity_time);
		
		return cal;
	}

	/**
	 * 计算有效期截止日期
	 * @param validity_time  有效期，单位(天)
	 * @param calendar_field 时间精度，参考DAY_OF_MONTH、HOUR_OF_DAY、MINUTE
	 * @return
	 */
	protected static Calendar getValidityEndTime(long send_time, int validity_time, int calendar_field) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(send_time);
		
		if (Calendar.DAY_OF_MONTH == calendar_field) {  // 精确到天
			cal.set(Calendar.HOUR_OF_DAY, 0);	// 小时置为0
			cal.set(Calendar.MINUTE, 0);		// 分钟置为0 
			cal.set(Calendar.SECOND, 0);		// 秒钟置为0
			
			cal.add(Calendar.DAY_OF_MONTH, 1);		// 如果精确到天，需要补充1天
		} else if (Calendar.HOUR_OF_DAY == calendar_field) {  // 精确到小时
			cal.set(Calendar.MINUTE, 0);		// 分钟置为0
			cal.set(Calendar.SECOND, 0);		// 秒钟置为0
			
			cal.add(Calendar.HOUR_OF_DAY, 1);		// 如果精确到小时，需要补充1小时
		} else if (Calendar.MINUTE == calendar_field) {		// 精确到分钟
			cal.set(Calendar.SECOND, 0);
			
			cal.add(Calendar.MINUTE, 1);		// 如果精确到分钟，需要补充1分钟，保证不让用户吃亏，^_^
		}
		
		cal.add(Calendar.DAY_OF_MONTH, validity_time);
		
		return cal;
	}
}
