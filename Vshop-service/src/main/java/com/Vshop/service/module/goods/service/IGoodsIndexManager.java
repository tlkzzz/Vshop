package com.Vshop.service.module.goods.service;

import java.util.Map;

public interface IGoodsIndexManager {

	void addIndex(Map goods);
	
	void updateIndex(Map goods);
	
	void deleteIndex(Map goods);
	
	void addAallIndex();
}
