
//left 默认加载的URL
function getToUrl(id){
	var toUrl="";
	//交易管理
	if(id=="myorder"){//订单管理
	
		toUrl = "/trade/order";
	
	}else if(id=="logis"){//物流工具
	
		toUrl = "/transport/list";
	
	}else if(id=="myeva"){//评价管理
		toUrl = "/trade/review";
	}
	//课程管理
	else if(id=="prosell"){//发布课程
		
		toUrl = "/pro/sell";
		
	}else if(id=="prosale"){//出售中的课程
		
		toUrl = "/pro/sale";
	
	}else if(id=="prostore"){//仓库中的课程
	
		toUrl = "/pro/store";
	
	}else if(id=="prorelation"){//关联板式
	
		toUrl = "/pro/selldetail?catrid=1&catename=";
	
	}else if(id=="imgspace"){//账户设置
	
		toUrl = "";
	
	}
	//账户设置
	else if(id=="accinfo"){//账户设置
		toUrl = "/account/accinfo";
	}else if(id=="accsafety"){//账户绑定
		toUrl = "/account/accsafety";
	}else if(id=="accaddress"){//收货地址
		toUrl = "/account/accaddress";
	}else if(id=="cusemail"){//站内信
		toUrl = "/sms/email";
	}
	
	else if(id=="storeinfo"){//学院基本信息
		toUrl = "/store/info";
	}else if(id=="storebrand"){//学院品牌申请
		toUrl = "/storeBrand/index";
	}else if(id=="brandApply"){//学院品牌申请
        toUrl = "/storeBrand/index";
    }else if(id=="classif"){//学院分类导航
		toUrl = "/storeGoodsClass/index";
	}else if(id=="shopset"){//学院设置
		toUrl = "/storeSetting";
	}else if(id=="service"){//客服服务
        toUrl = "/cusSetting/index";
    }else if(id=="consultreply"){//客服服务
        toUrl = "/consult/index";
    }
	return toUrl;
}





//默认加载方法
function initdata(apm){
	//先清除样式
	$(".user-menu a").each(function(i){
		$(this).removeAttr("class");
	});
	if(apm =="index"){//首页默认访问页面
		$("#dataListDiv").ShopTable({
            url:'/index/content'
		});
	}
	if(apm=='trade'){//交易管理默认访问页面
		$("#myorder").attr("class", "cur"); 
		$("#dataListDiv").ShopTable({
            url:'/trade/order'
		});
	}
	if(apm=='account'){//账户设置默认访问页面
		$("#accinfo").attr("class", "cur"); 
		$("#dataListDiv").ShopTable({
            url:'/account/accinfo'
		});
	}
	if(apm=='sms'){//站内信默认访问页面
		$("#cusemail").attr("class", "cur"); 
		$("#dataListDiv").ShopTable({
            url:'/sms/email'
		});
	}
}



//获取到left选项的时候加载样式，跳转路径
function changeLeft(id){
	$(".user-menu>dd>a").each(function(i){
		$(this).removeAttr("class");
	});
	$("#"+id).addClass("cur");
	
	var toUrl = getToUrl(id);
	//if(id =="prosell"){
	window.location.href=APP_BASE+""+toUrl
	//}else{
		//$("#dataListDiv").ShopTable({
	      //  url:toUrl
		//});
	//}
	//
	
}

