package com.Vshop.service.module.goods.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.ConsumerCode;
import com.Vshop.service.module.goods.dao.ConsumerCodeDao;
import com.Vshop.service.module.goods.service.ConsumerCodeService;
import com.Vshop.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerCodeServiceImpl implements ConsumerCodeService {

	@Autowired
	ConsumerCodeDao consumerCodeDao;
	
	@Override
	public void save(ConsumerCode consumerCode) {
		if (consumerCode != null) {
			// 目前暂定8位消费码
			String consumerCodeBunch = makeConsumerCodeBunch(8);
			int i = consumerCodeDao.findByBunch(consumerCodeBunch);
			int times = 1;
			while (i > 0) { // 已存在
				consumerCodeBunch = makeConsumerCodeBunch(8);
				i = consumerCodeDao.findByBunch(consumerCodeBunch);
				times++;
			}

			if (times > 5) {
				log.error("生成8位消费码，重复次数为" + (times - 1) + ", 已经大于5次，请考虑换算法");
			}

			consumerCode.setConsumerCodeBunch(consumerCodeBunch);
			if (consumerCode.getCodeCreateTime() == null || consumerCode.getCodeCreateTime() <= 0) {
				consumerCode.setCodeCreateTime(System.currentTimeMillis());
			}

			consumerCodeDao.save(consumerCode);
		}
	}
	
	@Override
	public void directSave(ConsumerCode consumerCode) {
		if (consumerCode != null) {
			if (consumerCode.getCodeCreateTime() == null || consumerCode.getCodeCreateTime() <= 0) {
				consumerCode.setCodeCreateTime(System.currentTimeMillis());
			}
			consumerCodeDao.save(consumerCode);
		}
	}

	@Override
	public void update(ConsumerCode consumerCode) {
		// TODO Auto-generated method stub
		consumerCodeDao.update(consumerCode);
	}

	@Override
	public List<ConsumerCode> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeDao.findPageList(pager);
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return consumerCodeDao.findPageListCount(pager);
	}

	@Override
	public ConsumerCode findById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		return consumerCodeDao.findById(consumerCodeId);
	}

	@Override
	public void deleteById(Integer consumerCodeId) {
		// TODO Auto-generated method stub
		consumerCodeDao.deleteById(consumerCodeId);
	}

	@Override
	public List<ConsumerCode> findListByMemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return consumerCodeDao.findListByMemberId(memberId);
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
	private String makeConsumerCodeBunch(int digit) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			double random_digit = Math.random() * CONSUMER_CODE_MEMBERS.length;
			result.append(CONSUMER_CODE_MEMBERS[(int) Math.floor(random_digit)]);
		}
		return result.toString();
	}

	public static String makeConsumerCodeBunch1() {
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replace("-", "");
		System.out.println(uuids);
		System.out.println(Long.parseLong(uuids.substring(0, 10), 36));
		return uuid.toString();
	}
	
	public static String makeConsumerCodeBunch2() {
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replace("-", "");
		char[] uids = uuids.toUpperCase().toCharArray();
		System.out.println(uuids.toUpperCase().toCharArray());
		BigDecimal deci = new BigDecimal(0);
		int length = uids.length;
		for (int len = length - 1; len >= 0; len--) {
			int x = (int) uids[len];
			if (x >= 48 && x < 58) {
				deci = deci.add(new BigDecimal(x - 48).multiply(new BigDecimal(16).pow(length - len)));
			} else if (x >= 65 && x < 91) {
				deci = deci.add(new BigDecimal(x - 55).multiply(new BigDecimal(16).pow(length - len)));
			} else {
				// 丢弃
			}
			// System.out.println(deci.toString());
		}
		System.out.println(deci.toString());
		// System.out.println(deci.divide(new BigDecimal(65535)).toString());
		// 79 O 73 I
		// System.out.println(Long.parseLong(uuids.substring(0, 10), 36));
		return uuid.toString();
	}
	
	
}
