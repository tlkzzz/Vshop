<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:500px;}
	</style>
	<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
	<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=6te2AY8PQrW6uyBXz2AGRdCS"></script>
	<title>单击获取点击的经纬度</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
    $(function(){
       add_control();//加载缩放比例尺
    });
	
	var Longitude="116.4035";//设置经度的初始值
	var Atitude="39.915";//设置纬度的初始值
	if(parent.$('#store_Longitude').val()!=''){
	     Longitude=parent.$('#store_Longitude').val();
	     Atitude=parent.$('#store_Atitude').val();
	}
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(Longitude,Atitude);
	
	map.centerAndZoom(point,15);
	var json_data = [[Longitude,Atitude]];
	var pointArray = new Array();
	for(var i=0;i<json_data.length;i++){
		var marker = new BMap.Marker(point); // 创建点
		map.addOverlay(marker);    //增加点
		//pointArray[i] = new BMap.Point(json_data[i][0], json_data[i][1]);
	}
	//让所有点在视野范围内
	//map.setViewport(pointArray);
	
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		alert(e.point.lng + "," + e.point.lat);
		parent.$('#store_Longitude').val(e.point.lng);////经度
		parent.$('#store_Atitude').val(e.point.lat);//纬度
		/* 
		var maps = new BMap.Map("allmap");
		var points = new maps.Point(e.point.lng,e.point.lat);//手动更改定位位置
	    maps.centerAndZoom(points,15); */
        //parent.layer.closeAll();//关闭父窗口
	});
	
	
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	/*缩放控件type有四种类型:
	BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/
	
	//添加控件和比例尺
	function add_control(){
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		map.addControl(top_right_navigation);    
	}
	//移除控件和比例尺
	function delete_control(){
		map.removeControl(top_left_control);     
		map.removeControl(top_left_navigation);  
		map.removeControl(top_right_navigation); 
	}
</script>