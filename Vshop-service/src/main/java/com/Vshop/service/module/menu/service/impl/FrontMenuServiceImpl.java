package com.Vshop.service.module.menu.service.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.FrontMenu;
import com.Vshop.service.module.menu.dao.FrontMenuDao;
import com.Vshop.service.module.menu.service.FrontMenuService;

@Service
public class FrontMenuServiceImpl implements FrontMenuService{
	
	@Autowired
	private FrontMenuDao frontMenuDao; 
	
	@Override
	public String frontMenu() {
		List<FrontMenu> fmList = frontMenuDao.findParentMenu();
    	
    	JSONArray jArray =new JSONArray();
    	for(FrontMenu fm:fmList){
    		JSONObject jObject = new JSONObject();
    		jObject.put("id", fm.getId());
    		jObject.put("name", fm.getName());
    		jObject.put("icon", fm.getICon());
    		JSONArray jsArray = new JSONArray();
    		if(fm.getHasChild()!=0){
    			List<FrontMenu> childFmList = frontMenuDao.findChildMenu(fm.getId());
    			for(FrontMenu fmu:childFmList){
    				JSONObject newObject = new JSONObject();
    				newObject.put("id", fmu.getId());
        			newObject.put("name", fmu.getName());
        			newObject.put("url", fmu.getUrl());
        			jsArray.put(newObject);
    			}
    		}
    		jObject.put("subMenu", jsArray);
    		jArray.put(jObject);
    	}
    	return jArray.toString();
	}
	
	@Override
	public List<FrontMenu> selectAllFm() {
		return frontMenuDao.selectAllFm();
	}

	@Override
	public void save(FrontMenu frontMenu) {
		frontMenuDao.save(frontMenu);
	}

	@Override
	public void delete(int id) {
		frontMenuDao.delete(id);	
	}

	@Override
	public void update(FrontMenu frontMenu) {
		frontMenuDao.update(frontMenu);
	}

	@Override
	public List<FrontMenu> selectParentFrontMenu() {
		return frontMenuDao.selectParentFrontMenu();
	}

	@Override
	public List<FrontMenu> selectChildrenFrontMenu(FrontMenu frontMenu) {
		return frontMenuDao.selectChildrenFrontMenu(frontMenu);
	}

	@Override
	public int findParentIdCount(Integer id) {
		return frontMenuDao.findParentIdCount(id);
	}

}
