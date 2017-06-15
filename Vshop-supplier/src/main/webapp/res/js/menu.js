
var menus = [
   {
	   id : "product", //id不会对菜单造成影响
	   name : "课程管理",
	   subMenu : [
	      {
	    	  id : "pro_sell", //id不会对菜单造成影响
	    	  name : "课程发布",
	    	  url : "/pro/sell"
	      },
	      {
	    	  id : "pro_sale",
	    	  name : "出售中的课程",
	    	  url : "/pro/sale"
	      },
	      {
	    	  id : "pro_store",
	    	  name : "仓库中的课程",
	    	  url : "/pro/store"
	      }
//	      ,
//	      {
//	    	  id : "pro_brand",
//	    	  name : "品牌列表",
//	    	  url : "/storeBrand/index"
//	      },
//	      {
//	    	  id : "pro_goods_class",
//	    	  name : "教培机构分类",
//	    	  url : "/storeGoodsClass/index"
//	      }
	   ]
   },
   {
	   id : "order",
	   name : "交易管理",
	   subMenu : [
	       {
	    	   id : "order",
	    	   name : "订单管理",
	    	   url : "/trade/orderList"
	       },
	       {
	    	   id : "shipments",
	    	   name : "发货",
	    	   url : "/transport/shipments"
	       },
	       {
	    	   id : "receivecode",
	    	   name : "收码",
	    	   url : "/transport/receivecode"
	       },
	       {
	    	   id : "shipments_setting",
	    	   name : "发货设置",
	    	   url : "/transport/shipments_setting"
	       }
//	       ,
//	       {
//	    	   id : "payment",
//	    	   name : "支付方式",
//	    	   url : "/trade/payment"
//	       }
	       ,
	       {
	    	   id : "tradegoods",
	    	   name : "课程交易",
	    	   url : "/tradegoods/list"
	       },
	       {
	    	   id : "tradetool",
	    	   name : "物流工具",
	    	   url : "/transport/index"
	       }
//	       ,
//	       {
//	    	   id : "review",
//	    	   name : "评价管理",
//	    	   url : "/trade/review"
//	       }
	   ]
   },
   /*
   {
	   id : "chuxiao",
	   name : "促销管理",
	   subMenu : [
//	      {
//	    	  id : "store_groupbuy",
//	    	  name : "团购管理",
//	    	  url : "#?act=store_groupbuy"
//	      },
//	      {
//	    	  id : "store_promotion_xianshi",
//	    	  name : "限时折扣",
//	    	  url : "#?act=store_promotion_xianshi&op=xianshi_list"
//	      },
//	      {
//	    	  id : "store_promotion_mansong",
//	    	  name : "满即送",
//	    	  url : "#?act=store_promotion_mansong&op=mansong_list"
//	      },
//	      {
//	    	  id : "store_promotion_bundling",
//	    	  name : "组合销售",
//	    	  url : "#?act=store_promotion_bundling&op=bundling_list"
//	      },
	      {
	    	  id : "store_coupon",
	    	  name : "优惠券管理",
	    	  url : "/coupon/list"
	      }
//	    		  ,
//	      {
//	    	  id : "store_voucher",
//	    	  name : "代金券管理",
//	    	  url : "#?act=store_voucher"
//	      },
//	      {
//	    	  id : "store_activity",
//	    	  name : "活动管理",
//	    	  url : "#?act=store&op=store_activity"
//	      },
//	      {
//	    	  id : "store_ztc",
//	    	  name : "直通车管理",
//	    	  url : "#?act=store_ztc&op=ztc_list"
//	      }
	   ]
   },
   {
	   id : "store",
	   name : "教培机构设置",
	   subMenu : [
	      {
	    	  id : "show_store",
	    	  name : "我的教培机构",
	    	  url : "/store/shop"
	      },
	      {
	    	  id : "storeseting",
	    	  name : "教培机构设置",
	    	  url : "/storeSetting/storeseting"
	      },
	      {
	    	  id : "storethem",
	    	  name : "主题设置",
	    	  url : "/store/storethem"
	      }
//	      ,
//	      {
//	    	  id : "storenavigation",
//	    	  name : "导航管理",
//	    	  url : "/store/storenavigation"
//	      },
//	      {
//	    	  id : "storepartner",
//	    	  name : "合作伙伴管理",
//	    	  url : "/store/storepartner"
//	      }
	   ]
   },
   */
   {
	   id : "consult",
   	   name : "客服管理",
   	   subMenu : [
   	           /*
   	      {
   	    	  id : "index",
   	    	  name : "咨询管理",
   	    	  url : "/consult/index"
   	      },
   	   */
   	      {
   	    	  id : "cusrefundrecord",
   	    	  name : "退款记录",
   	    	  url : "/trade/refundOrderList"
   	      },
   	      {
   	    	  id : "cusreprorecord",
   	    	  name : "退货记录",
   	    	  url : "/trade/returnOrderList"
   	      }
//   	      ,
//   	      {
//   	    	  id : "complainaccuserlist",
//   	    	  name : "投诉管理",
//   	    	  url : "/consult/complainaccuserlist"
//   	      },
//   	      {
//   	    	  id : "storeinform",
//   	    	  name : "被举报禁售",
//   	    	  url : "/consult/storeinform"
//   	      }
   	   ]
   },
//   {
//	   id : "other",
//	   name : "其他设置",
//	   subMenu : [
//	      {
//	    	  id : "storegbuy",
//	    	  name : "金币管理",
//	    	  url : "/other/storegbuy"
//	      },
//	      {
//	    	  id : "adv_manage",
//	    	  name : "广告管理",
//	    	  url : "#?act=store_adv&op=adv_manage"
//	      },
//	      {
//	    	  id : "albumcate",
//	    	  name : "图片空间",
//	    	  url : "/other/albumcate"
//	      }
//	   ]
//   },
   {
	   id : "statistics",
	   name : "教培机构统计",
	   subMenu : [
	      {
	    	  id : "flow_statistics",
	    	  name : "流量统计",
	    	  url : "/report/clickIndex"
	      },
	      {
	    	  id : "sale_statistics",
	    	  name : "订单统计",
	    	  url : "/report/orderIndex"
	      },
	      {
	    	  id : "probability_statistics",
	    	  name : "购买率统计",
	    	  url : "/report/purchaseRateIndex"
	      },
	      {
	    	  id : "balance_statistics",
	    	  name : "结算统计",
	    	  url : "/report/balanceIndex"
	      }
	   ]
   }
];

