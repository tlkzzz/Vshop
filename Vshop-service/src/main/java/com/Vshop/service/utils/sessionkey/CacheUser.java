package com.Vshop.service.utils.sessionkey;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.Admin;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：CacheUser
 * 类描述：用户信息
 * 创建人：lkang   
 * 创建时间：2015年5月04日 03:00:00   
 */
@Data
@ToString
public class CacheUser  implements Serializable{
	
	private static final long serialVersionUID = 1990175011668690412L;

	private Admin admin;
}
