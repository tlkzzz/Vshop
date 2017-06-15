/**
 * 
 */
package com.Vshop.front.utils.ehcache;

import java.util.ArrayList;
import java.util.List;

import com.Vshop.core.entity.GoodsClass;

/**
 * <p>Title: Test.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
public class Test {

	public static void main(String args[]){
		List<GoodsClass> list = new ArrayList<GoodsClass>();
		
		GoodsClass b =new GoodsClass();
		b.setGcId(1);
		b.setGcName("1Name");
		b.setGcParentId(0);
		list.add(b);
		
		GoodsClass b1 =new GoodsClass();
		b1.setGcId(2);
		b1.setGcName("2Name");
		b1.setGcParentId(2);
		list.add(b);
		
		GoodsClass b2 =new GoodsClass();
		b2.setGcId(3);
		b2.setGcName("3Name");
		b2.setGcParentId(1);
		list.add(b2);
		
		GoodsClass b3 = new GoodsClass();
		b3.setGcId(4);
		b3.setGcName("4Name");
		b3.setGcParentId(1);
		b3.setClassList(list);
		list.add(b3);
		
		ICache<List<GoodsClass>> cache = CacheFactory.getCache("GoodsClassCache");
		long beginTime = System.currentTimeMillis();
		cache.put("tree", list);
		long endTime = System.currentTimeMillis();
		System.out.printf("放入执行时间是:%d ms %n", endTime - beginTime);
		System.out.println("放入完成");
		
		beginTime = System.currentTimeMillis();
		List<GoodsClass> listss = new ArrayList<GoodsClass>();
		listss =  cache.get("tree");
		for (GoodsClass gct : listss) {
			System.out.println("===="+gct.getGcName());
//			for (GoodsClass goodsClass : gct.getClassList()) {
//				System.out.println("GcName===="+goodsClass.getGcName());
//			}
		}
		endTime = System.currentTimeMillis();
		System.out.printf("取出执行时间是:%d ms %n", endTime - beginTime);
	}
}
