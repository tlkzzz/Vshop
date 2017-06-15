package com.Vshop.seller.utils.freemarker.directive;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.Vshop.seller.utils.sessionKey.CacheUtils;

/**
 * 在页面中加入绝对路径
 */
public class StoreFreeMarkerView extends FreeMarkerView {

    private static final String STORE_ID = "storeid";
    private static final String CONTEXT_PATH = "base";
    @Override
    protected void exposeHelpers(Map<String, Object> model,
                                 HttpServletRequest request) throws Exception {
    	Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
          if(CacheUtils.getCacheUser().getStore()!=null){
        	Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
        	model.put(STORE_ID, storeId);
         }	
        }
        model.put(CONTEXT_PATH, request.getContextPath());
        super.exposeHelpers(model, request);
    }
}
