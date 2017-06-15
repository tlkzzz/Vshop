package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.entity
 * @Description:相册分类
 * @date 2014/12/11 16:08
 */
@Data
@ToString
public class AlbumClass extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -8432046201162898530L;

    private Integer aclassId;

    private String aclassName;

    private Integer storeId;

    private String aclassDes;

    private Integer aclassSort;

    private String aclassCover;

    private Date uploadTime;

    private Boolean isDefault;
}
