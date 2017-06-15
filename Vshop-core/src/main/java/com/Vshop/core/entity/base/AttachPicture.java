package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 图片空间
 * @author liuk
 */
@Data
@ToString
public class AttachPicture {
	
	private Integer id;
	/**
	 * 图片原名
	 */
	private String realName;
	/**
	 * 上传后的名字
	 */
	private String localName;
	/**
	 * 缩略图路径
	 */
	private String thumbnailPath;
	/**
	 * 原图路径
	 */
	private String localPath;
	/**
	 * 创建时间
	 */
	private Long createDate;
	/**
	 * 店铺id
	 */
	private Integer storeid;
}
