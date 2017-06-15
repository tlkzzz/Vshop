//package com.Vshop.front.module.weChatpay.service.impl;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Service;
//
//import com.Vshop.front.wechat.menu.MenuManager;
//
//@Service
//public class StartWeChatServiceImpl  implements ApplicationListener<ContextRefreshedEvent>{
//	 //服务启动时初始化公众号菜单数据
//		@Override
//		public void onApplicationEvent(ContextRefreshedEvent event) {
//			MenuManager menu=new MenuManager();
//			menu.onstart();
//		}
//
//}
