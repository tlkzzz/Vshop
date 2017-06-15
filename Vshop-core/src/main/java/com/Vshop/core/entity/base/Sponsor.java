package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;

@Data
public class Sponsor {
	private Integer id;
	private String name;
	private String shortName;
	private String address;
	private String contacter;
	private String mobile;
	private Integer deleted;
	private Date createTime;
	private Date updateTime;
}
