package com.Vshop.admin.module.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Vshop.service.utils.CommonConstants;

@Slf4j
@Controller
@RequestMapping("/img")
public class ImgAction
{
	@RequestMapping("/index")
    public void readImg(@RequestParam String imgfile, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
		
        File file = new File(CommonConstants.FILE_BASEPATH + imgfile);
        if (file.isFile())
        {
        	log.info("start......return image");
        	
        	response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache, no-store");
            response.setHeader("Pragma", "no-cache");
            long time = System.currentTimeMillis();
            response.setDateHeader("Last-Modified", time);
            response.setDateHeader("Date", time);
            response.setDateHeader("Expires", time);
            
            ImageIO.write(ImageIO.read(FileUtils.openInputStream(file)), "png", response.getOutputStream());
        }
    }

}