package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsumerCodeLog extends BaseEntity implements Serializable {

	
	private static final long serialVersionUID = -110660178821957970L;
	
	private Integer logId; // int(11) 日志Id
	private Integer logUser; // int(11) 操作人
	private Long logTime; // bigint(13) 操作时间
	private String logIp; // varchar(20) 操作人Ip
	private String consumerCodeBunch; // varchar(20) 课程码
	private Integer logFlag; // int(1) 操作结果：1，成功；2：失败
	private String logResult; // text 操作内容
	private Integer codeStatus; // int(1) 课程码状态

}
