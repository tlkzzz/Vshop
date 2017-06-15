package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransportExtend {
	private Integer id;
	
	/**
	 * 平邮py 快递kd EMS es
	 */
	private String type;
	
	private String areaId;
	private String topAreaId;
	private String areaName;
	private Integer snum;
	private Double sprice;
	private Integer xnum;
	private Double xprice;
	private Integer transportId;
	private String transportTitle;

	/**
	 * 0:未删除;1.已删除
	 */
	private int isDel;

	/**
	 * 创建时间
	 */
	private Long createdTime;
	/**
	 * 更新时间
	 */
	private Long updatedTime;
}
