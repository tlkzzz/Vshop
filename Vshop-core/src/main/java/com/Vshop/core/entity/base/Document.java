package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.core.entity.website
 * @Description:
 * @date 2014/11/11 11:10
 */
@Data
@ToString
public class Document extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 6101583018576478694L;

    /**
     * id
     */
    private Integer docId;

    /**
     * 调用标识码
     */
    private String docCode;

    /**
     * 标题
     */
    private String docTitle;

    /**
     * 内容
     */
    private String docContent;

    /**
     * 添加时间/修改时间
     */
    private Date docTime;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    //private Long createdTime;
    /**
     * 更新时间
     */
    //private Long updatedTime;
}
