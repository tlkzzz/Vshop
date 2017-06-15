package com.Vshop.front.utils;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.Vshop.core.freemarker.ExtFreeMarkerView;
import com.Vshop.front.MemContents;
import com.Vshop.front.module.alipayinterna.RateFileload;

public class FrontFreemarkerView extends ExtFreeMarkerView {
	private static final String RATE_PATH = "rate";
    @Override
    protected void exposeHelpers(Map<String, Object> model,
                                 HttpServletRequest request) throws Exception {
    	RateFileload rdf=new RateFileload();
    	if("".equals(RateFileload.ratevalue)||RateFileload.ratevalue==null){
    		File file=new File(MemContents.aplipayratefiledir+MemContents.aplipayratefilename+".txt"); 
    		if(file.exists()){
    			rdf.readrate();//读取汇率
    		}
    	}
        model.put(RATE_PATH, RateFileload.ratevalue);
        super.exposeHelpers(model, request);
    }
}
