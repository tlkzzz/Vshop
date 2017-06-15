package com.Vshop.front.module.controller.fileupload;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.service.utils.CommonConstants;

/**
 * 项目名称：Vshop-front
 * 类名称：Fileupload
 * 类描述：
 * 创建人：gyh
 * 创建时间：2015年7月22日 下午5:05:34
 * 修改备注：
 */
@Controller
@RequestMapping("/fileupload")
@Slf4j
public class Fileupload {
	 @RequestMapping(value = "/fileUpload")
	    public String fileUpload(@RequestParam MultipartFile[] files,
	                             HttpServletRequest request, HttpServletResponse response) throws IOException {
	        //可以在上传文件的同时接收其它参数
	        Map<String, Object> map = Maps.newHashMap();
	        try {
	            OSSConfigure ossConfigure=new OSSConfigure();
				 map =OSSManageUtil.uploadFile(ossConfigure, files, Constants.MEMBER_UPLOAD_URL,"images",request);
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error("上传文件失败", e.toString());
	        }
	        String json = JsonUtils.toJsonStr(map);
	        response.setContentType("text/html");
	        response.getWriter().write(json);
	        return null;
	    }
}
