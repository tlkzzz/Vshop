package com.Vshop.admin.module.setting.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.common.FileUtils;
import com.Vshop.core.entity.base.Sensitive;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.utils.CommonConstants;






/**
 * 
 * 
 * @项目名称：Vshop-admin
 * @类名称：FlieUploadAction
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Controller
@RequestMapping("/setting/sensitive")
@Slf4j
public class SensitiveAction {
    /**
     * 敏感词文件上传页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/add")
	public String add(Model model){
    	File[] files = FileUtils.getFileListByFilePath(CommonConstants.FILE_BASEPATH+Constants.SENSITIVE_UPLOAD_URL);
    	ArrayList<Sensitive> sensitiveList = new ArrayList<Sensitive>();
		for (File file : files) {
			Sensitive sensitive = new Sensitive();
			sensitive.setFileName(file.getName());
			sensitive.setFilePath(file.getPath());
			sensitive.setUpTime(DateUtils.formatLongToStr(file.lastModified(), "yyyy-MM-dd HH:mm:ss"));
			sensitiveList.add(sensitive);
		}
		model.addAttribute("sensitiveList", sensitiveList);
        return "setting/sensitive/add";
    }
    /**
     * 敏感词库文件上传
     * @param files
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam MultipartFile[] files,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        //可以在上传文件的同时接收其它参数
        Map<String, Object> map = Maps.newHashMap();
        try {
            map = FileUtils.fileUpload(files, CommonConstants.FILE_BASEPATH, Constants.SENSITIVE_UPLOAD_URL, request,"sensitive",0);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件失败", e.toString());
        }
        String json = JsonUtils.toJsonStr(map);
        response.setContentType("text/html");
        response.getWriter().write(json);

        return null;
    }

}
