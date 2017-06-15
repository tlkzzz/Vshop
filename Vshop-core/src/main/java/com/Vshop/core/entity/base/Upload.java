package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2015/3/7.
 */
@Data
@ToString
public class Upload extends BaseEntity implements java.io.Serializable{

    private static final long serialVersionUID = -1686709080098168269L;

    private Integer uploadId;

    private String fileName;

    private String fileThumb;

    private String fileWm;

    private Integer fileSize;

    private Integer storeId;

    private Integer uploadType;

    private Date uploadTime;

    private Integer itemId;

    private String imgUrl;
}
