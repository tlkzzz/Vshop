package com.Vshop.admin.module.admin.controller;

import java.util.Map;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.Vshop.service.sms.sender.Sender;

@Controller
@RequestMapping("/send")
@Slf4j
public class SendAction {
	@RequestMapping(value = "/code", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody
	String sendCode(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "message") String message, Model model) {
		Map<String, Object> resutl = Maps.newHashMap();
		try {
			log.info("send code start.....");
			
			return JSON.toJSONString(Sender.send(mobile, message));
		} catch (Exception e) {
			resutl.putAll(ImmutableMap.of("status", 1, "success", false, "message", e.getMessage()));
			e.printStackTrace();
		}
		return JSON.toJSONString(resutl);
	}
}
