package com.Vshop.service.module.product.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * 
 * @项目名称：Vshop-front
 * @类名称：EvaluateGoodsVo
 * @类描述： 商品评价的vo
 * @创建人：shining
 * @创建时间：2014年12月27日 下午4:17:20
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class EvaluateGoodsVo {
	// 总评价数
	private Integer pingJiaNum;
	// 好评数
	private Integer haoPingNum;
	// 中评数
	private Integer zhongPingNum;
	// 差评数
	private Integer chaPingNum;
	// 好评百分比
	private Double haoPingPercent;
	// 中评百分比
	private Double zhongPingPercent;
	// 差评百分比
	private Double chaPingPercent;

	// 好评百分比
	public Double getHaoPingPercent() {
		haoPingPercent = (double) haoPingNum / pingJiaNum * 100;
		if (haoPingPercent.isNaN()) {
			haoPingPercent = new Double(100);
		}
		return haoPingPercent;
	}

	// 中评百分比
	public Double getZhongPingPercent() {
		zhongPingPercent = (double) zhongPingNum / pingJiaNum * 100;
		if (zhongPingPercent.isNaN()) {
			zhongPingPercent = new Double(0);
		}

		return zhongPingPercent;
	}

	// 差评百分比
	public Double getChaPingPercent() {
		chaPingPercent = (double) chaPingNum / pingJiaNum * 100;
		if (chaPingPercent.isNaN()) {
			chaPingPercent = new Double(0);
		}
		return chaPingPercent;
	}
}
