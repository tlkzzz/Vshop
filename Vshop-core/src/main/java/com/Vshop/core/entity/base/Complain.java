package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2014/12/20.
 */
@Data
@ToString
public class Complain extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 4426093333608136247L;

    private Integer complainId;

    private Integer orderId;

    private Integer accuserId;

    private String accuserName;

    private Integer accusedId;

    private String accusedName;

    private String complainSubjectContent;

    private Integer complainSubjectId;

    private String complainContent;

    private String complainPic1;

    private String complainPic2;

    private String complainPic3;

    private Date complainDatetime;

    private Date complainHandleDatetime;

    private Integer complainHandleMemberId;

    private String appealMessage;

    private Date appealDatetime;

    private String appealPic1;

    private String appealPic2;

    private String appealPic3;

    private String finalHandleMessage;

    private Date finalHandleDatetime;

    private Integer finalHandleMemberId;

    private Integer complainState;

    private Integer complainActive;

}
