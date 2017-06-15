package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsumerCode extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4682143195171560793L;

	/**
	 * 原始码库自增ID
	 */
	private Integer consumerCodeId; // int(11)只是主键
	
	/**
	 * 商品消费码
	 */
	private String consumerCodeBunch; // varchar(20)
	
	/**
	 * 商品id
	 */
	private Integer goodsId; // int(11)
	
	/**
	 * 码来源，1:自身，3：第三方直接提供
	 */
	private Integer codeSource; // int(1)
	
	/**
	 * 会员id
	 */
	private Integer memberId; // int(11)会员id
	
	/**
	 * 创建时间
	 */
	private Long codeCreateTime; // bigint(13)
	
	/**
	 * 创建时间
	 */
	private Timestamp codeCreateTimeStr;
	
	/**
	 * 码状态：0:未激活,1:激活,2:被用,3:失效
	 */
	private Integer codeStatus; // int(1)
	

	public Long getCodeCreateTime() {
		return codeCreateTime;
	}
	
	public void setCodeCreateTime(Long codeCreateTime) {
		this.codeCreateTime = codeCreateTime;
		codeCreateTimeStr = DateUtils.getTimestampByLong(codeCreateTime);
	}
	
	public Timestamp getCodeCreateTimeStr() {
		return codeCreateTimeStr;
	}
	
	public void setCodeCreateTimeStr(Timestamp codeCreateTimeStr) {
		this.codeCreateTimeStr = DateUtils.getTimestampByLong(codeCreateTime);
	}
}
