package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * Created by yansheng on 2014/7/3.
 */
@Data
@ToString
public class Permission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -8426339414567997935L;
    private long id;
    private String permName;
    private int type;
    private String url;
    private long parentId;
    private String parentIds;
    private String resource;
    /**
     * 0:未删除;1.已删除
     */
//    private int isDel;
//    private Long createdTime;
//    private Long updatedTime;
}
