package com.Vshop.core.freemarker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;


/**
 * FREEMARKER标签基类
 * @author LKANG
 * 2015-05-11下午9:10:23
 */
@SuppressWarnings("deprecation")
public abstract class BaseFreeMarkerTag implements TemplateMethodModel {
	
//	private int pageSize =10;
	
	@SuppressWarnings("rawtypes")
	public Object exec(List jsonParam) throws TemplateModelException {
		
		if(jsonParam!=null && !jsonParam.isEmpty()){
			String param = (String)jsonParam.get(0);
			if(param!=null){
				 if(!param.startsWith("{")){
					 param="{"+param+"}";
				 }
				JSONObject jsonObject  =JSONObject.fromObject(param);
//				Integer pageSizeNum =(Integer)jsonObject.get("pageSize");
//				if(pageSizeNum!=null){
//					this.pageSize= pageSizeNum;
//				}
				return this.exec(jsonObject);
			}else{
				return this.exec(new HashMap());
			}
		}else{
			return this.exec(new HashMap());
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract Object exec(Map params) throws TemplateModelException;

//	protected int getPageSize() {
//		return pageSize;
//	}

	
}
