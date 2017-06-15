package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

/**
 * 前台菜单表
 * @author liuk
 */
@Data
@ToString
public class FrontMenu {
	private int id; //菜单id
	private String name; //菜单名
	private String url; //url路径
	private int parentId; //父级id
	private int sort; //排序
	private int level; //菜单等级
	private String path; //菜单id路径
	private String description; //描述
	private int hasChild; //是否拥有子节点
	private String iCon; //一级分类的图标
}
