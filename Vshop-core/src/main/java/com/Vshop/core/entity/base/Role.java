package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;



/**
 * Created by yansheng on 2014/7/2.
 */
@Data
@ToString
public class Role extends BaseEntity{
	
    private static final long serialVersionUID = -8426339414665997935L;
    
    private long id;
    
    private String roleName;
    /**
     * 0:未删除;1.已删除
     */
//    private int isDel;
//    private Long createdTime;
//    private Long updatedTime;
}
