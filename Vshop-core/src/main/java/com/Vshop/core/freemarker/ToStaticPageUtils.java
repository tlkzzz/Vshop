package com.Vshop.core.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;

import lombok.extern.slf4j.Slf4j;

import com.Vshop.core.common.FileUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 将ftl页面静态化
 * @author cgl
 * 2015年08月07日17:17:52
 */
@Slf4j
public class ToStaticPageUtils {

	/**
	 * 
	 * @param data
	 * @param modelPath ftl模板的路径
	 * @param staticPagePath 生成的静态页面路径(名字以及类型)
	 * @throws IOException
	 * @throws TemplateException
	 * @throws ServletException
	 */
    public static void createHTML(Map<String, Object> data, String modelBasePath, String modelPath, String staticPagePath) throws IOException, TemplateException, ServletException {    
    	log.info("开始生成静态页＝＝＝＝" + staticPagePath);
		Configuration conf = new Configuration();
		conf.setLocale(Locale.CHINA);
		conf.setSetting("auto_import", "/commons/header.ftl as p");
		conf.setSetting("object_wrapper", "freemarker.ext.beans.BeansWrapper");
	    conf.setDefaultEncoding("utf-8");// 设置编码
	    conf.setClassicCompatible(true);// 允许有空值
	    TemplateLoader templateLoader =new FileTemplateLoader(new File(modelBasePath));
	    conf.setTemplateLoader(templateLoader);
	    Template temp = conf.getTemplate(modelPath, "utf-8");// 获得模板对象
        File htmlFile = new File(staticPagePath);
        if(!htmlFile.exists()){
            FileUtils.createFile(htmlFile, staticPagePath);
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
        //处理模版 
        temp.process(data, out);
        out.flush();
        out.close();
        log.info("结束生成静态页＝＝＝＝" + staticPagePath);
    }
	
}
