package com.Vshop.core.entity.base;

import java.io.Serializable;


import lombok.Data;
import lombok.ToString;

/**
 * 会员积分日志
 * Created by gyh on 2015/7/24.
 */
@Data
@ToString
public class ShopPointsLog extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -5107874425347340380L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 会员编号
	 */
	private Integer memberid;
	/**
	 * 会员名称
	 */
	private String membername;
	/**
	 * 管理员编号
	 */
	private Integer adminid;
	/**
	 * 管理员名称
	 */
	private String adminname;
	/**
	 * 积分数负数表示扣除
	 */
	private Integer points;
	/**
	 * 操作描述
	 */
	private String desc;
	/**
	 * 操作阶段
	 */
	private String stage;
	/**
	 * 积分操作类型
	 */
	private Integer type;
}
