package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 敏感词库文件
 * @author zhaorh
 * @date 2015-10-14 11:16:00
 */
@Data
@ToString
public class Sensitive extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 4028365179770564469L;
	
	
	/**
	 * 敏感词文件上传时间
	 *
	 */
	private String upTime;
	
	/**
	 * 敏感词文件名称
	 */
	private String fileName;
	
	/**
	 * 敏感词文件的全路径
	 */
	private String filePath;
	
	
}
