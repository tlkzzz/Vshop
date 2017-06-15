package com.Vshop.core.entity.base;
import java.io.Serializable;


import lombok.Data;
import lombok.ToString;

/**
 * 较色表实体类
 * @author GUANQF
 */
@Data
@ToString
public class Roles extends BaseEntity implements Serializable{
	   
	private static final long serialVersionUID = -1910424503447503876L;
	//角色ID
	private Integer id;
	//角色名称
    private String name;
    //创建时间
    //private Long createtime;
    //角色描述
    private String description;
    
    /**
     * 角色别名 用于shiro 权限控制
     */
    private String roleAlias;
}
