/**
 *
 * @type {{id: string, name: string, subMenu: {id: string, name: string, url: string}[]}[]}
 * id:生成横向主菜单的ID
 * name:菜单名称
 * subMenu:该菜单对应的子菜单
 *      --id:子菜单对应的ID
 *      --name:子菜单名称
 *      ---url:子菜单点击时加载的URL
 */

var menus = [
    {
        id: "index_menu",
        name: "首页",
        desc: "常用操作",
        subMenu: [
            {
                id: "index_welcome",
                name: "欢迎页",
                url: "/welcome"
            },
            {
                id: "index_about",
                name: "关于我们",
                url: "/about"
            }
        ]
    }
    ,{
    	id: "menu_adminlog",
    	name: "设置",
    	desc: "设置相关",
    	subMenu: [{
    		id: "adminlog_list",
    		name: "操作日志",
    		url: "/adminlog/index"
    	},
    	{
            id: "area_list",
            name: "配送地区",
            url: "/area/index"
        },{
            id: "express_list",
            name: "快递公司",
            url: "/setting/express/index"
        },{
            id: "payment_list",
            name: "支付方式",
            url: "/setting/payment/index"
        }]
    },
    {
        id: "goods_menu",
        name: "课程",
        desc: "课程相关",
        subMenu: [
            {
                id: "goods_type_list",
                name: "属性管理",
                url: "/goods/type/index"
            },
            {
                id: "goods_class_list",
                name: "分类管理",
                url: "/goods/class/index"
            },
            {
                id: "brand_list",
                name: "品牌管理",
                url: "/goods/brand/index"
            },
            {
                id: "goods_list",
                name: "课程管理",
                url: "/goods/goodsCommon/index"
            }
        ]
    },
    {
    	id: "store_meun",
    	name: "店铺",
    	desc: "店铺相关",
    	subMenu: [
    	          {
    	        	  id: "store_class",
    	        	  name: "店铺分类",
    	        	  url: "/store/classs/index"
    	          },
    	          {
    	        	  id: "store_list",
    	        	  name: "店铺管理",
    	        	  url: "/storeDetail/index"
    	          },
    	            {
    	                id: "store_level",
    	                name: "店铺等级(暂无)",
    	                url: ""
    	            },
    	            
    	            {
    	                id: "store_setting",
    	                name: "二级域名",
    	                url: "/store/setting/index"
    	            },
    	            {
    	                id: "store_tracelog",
    	                name: "店铺动态",
    	                url: "/tracelog/index"
    	            }
    	          ]
    },
    {
        id: "member_menu",
        name: "会员",
        desc: "会员相关",
        subMenu: [
            {
                id: "member_list",
                name: "会员管理",
                url: "/member/index"
            },
            {
                id: "member_notice",
                name: "会员通知",
                url: ""
            },
            {
                id: "score_list",
                name: "积分管理",
                url: ""
            },
            {
                id: "pre_deposit",
                name: "预存款",
                url: ""
            },
            {
                id: "binding_share",
                name: "分享绑定",
                url: ""
            },
            {
                id: "member_photo",
                name: "会员相册",
                url: ""
            },
            {
                id: "buyer_dynamic",
                name: "买家动态",
                url: ""
            },
            {
                id: "member_tags",
                name: "会员标签",
                url: ""
            }
        ]
    },
    {
        id: "trade_menu",
        name: "交易",
        desc: "交易相关",
        subMenu: [
            {
                id: "order_list",
                name: "订单管理",
                url: "/orders/index"
            },
            {
                id: "refund_list",
                name: "退款管理",
                url: "/refundReturn/moneyIndex"
            },
            {
                id: "return_list",
                name: "退课管理",
                url: "/refundReturn/index"
            },
            {
                id: "tousu_list",
                name: "投诉管理(暂无)",
                url: ""
            },
            {
                id: "evalGoods_list",
                name: "评价管理",
                url: "/trade/evalGoods/index"
            }
        ]
    },
    {
        id: "website_menu",
        name: "网站",
        desc: "网站相关",
        subMenu: [
            {
                id: "ac_list",
                name: "文章分类",
                url: "/website/class/index"
            },
            {
                id: "article_list",
                name: "文章管理",
                url: "/website/article/index"
            },
            {
                id: "systemArticle_list",
                name: "系统文章",
                url: "/website/document/index"
            },
            {
                id: "pageNav_list",
                name: "页面导航",
                url: "/website/navigation/index"
            }
        ]
    },
    {
        id: "operation_menu",
        name: "运营",
        desc: "运营相关",
        subMenu: [
            {
                id: "orderstatis_list",
                name: "结算管理",
                url: "/orderstatis/index"
            }
        ]
    }
    ,{
    	id: "stat_tj",
    	name: "统计",
    	desc: "统计相关",
    	subMenu: [
    	{
            id: "statmember_list",
            name: "会员统计",
            url: "/stat/member/index"
        }
        ,{
                id: "statStore_list",
                name: "店铺统计",
                url: "/stat/newStore/index"
        },
        {
                id: "tradeGoods_list",
                name: "销量分析",
                url: "/stat/tradeGoods/index"
        }
        ]
    }
];