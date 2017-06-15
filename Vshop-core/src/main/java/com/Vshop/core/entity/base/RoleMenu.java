package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
/**
 * 
 * @author guanqf
 *
 */
@Data
@ToString
public class RoleMenu implements Serializable{
	
	private static final long serialVersionUID = -1910424503447503876L;

	private Integer roleId;//角色ID
	
	private Integer menuId;//菜单ID

}
