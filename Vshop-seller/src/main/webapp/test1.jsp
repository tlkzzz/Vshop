<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	
	var spec_group_checked = ['',''];
	for (var i=0; i<2; i++){
		 if(i==0){
			 spec_group_checked[i] = ['',''];
		 }else{
			 spec_group_checked[i] = [''];
		 }
	}
	 
	var strHtml = "";
	function reveation(len,sign){
		if(len<sign){
			alert(spec_group_checked[len].length);
			for(window["i_"+len] = 0 ; window["i_"+len] < spec_group_checked[len].length; window["i_"+len] ++){
				len++;
				reveation(len,sign);
			}
		}else{
			strHtml +="测试"+len;
			alert("123");
		}
		alert(strHtml);
	}
	

	
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    
    <a href ="javascript:void(0);" onclick="reveation(0,2);">测试按钮</a>
  </body>
</html>
