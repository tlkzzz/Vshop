package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @项目名称：Vshop-entity
 * @类名称：Express
 * @类描述： express 快递公司表
 * @修改备注：
 * @version
 */
@Data
@ToString
public class Express extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -3800698370292904345L;

	private Integer id;

	private String seName;

	private Integer estate;

	private String eCode;

	private String eLetter;

	private Integer eorder;

	private String eUrl;
	
}