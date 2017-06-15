<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
	<script type="text/javascript" src="${base}/res/js/zeroclipboard/ZeroClipboard.js" charset="utf-8"></script>
	<script type="text/javascript">
    $(document).ready(function() {
    	var str = "";
    <#if ewmType==1&&storeId!=null>
    	var urlurl = "${frontServer}/weixin/author?storeId=${storeId}";
		str = '<table style="width: 99%; vertical-align: middle; text-align: center;"><tr><td style="width: 140px;">'
				+ '<img id="twodimencodeImage" src="${base}/twodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=1" />'
				+ '</td><td style="text-align: center;">'
				+ '<a href="downloadTwodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=1" target="_blank"><button>保存</button></a>'
				+ '<input type="hidden" id="schoolurl" name="schoolurl" value="' + urlurl + '" />'
				+ '<span id="clip_container"><button id="clip_button" style="position: absolute; right: 70px; top: 120px;"><nobr>复制地址</nobr></button></span>'
				+ '</td></tr>'
				+ '</table>';
	<#else>
		var urlurl = "${frontServer}/weixin/detail?goodsId=${goodsId}&storeId=${storeId}";
		str = '<table style="width: 99%; vertical-align: middle; text-align: center;"><tr><td style="width: 140px;">'
				+ '<img id="twodimencodeImage" src="${base}/twodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=2" />'
				+ '</td><td>'
				+ '<a href="${base}/downloadTwodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=2" target="_blank"><button>保存</button></a>'
				+ '<input type="hidden" id="schoolurl" name="schoolurl" value="' + urlurl + '" />'
				+ '<span id="clip_container"><button id="clip_button" style="position: absolute; right: 70px; top: 120px;"><nobr>复制地址</nobr></button></span>'
				+ '</td></tr>'
				+ '</table>';
	</#if>
    
    	var obj = document.getElementById("twodimencode_div");
		if(obj != null) {
			obj.innerHTML = str;
		}	
		
		myZeroClipboard();
	});
	
		function myZeroClipboard() {
			ZeroClipboard.setMoviePath("${base}/res/js/zeroclipboard/ZeroClipboard.swf");
		  	var clip = new ZeroClipboard.Client();
		  	clip.setHandCursor(true);  	
		  	clip.addEventListener('mouseOver', function (client){    
		    	clip.setText($('#schoolurl').val());
		  	});
		  	clip.addEventListener('complete', function (client, text) {   
		    	layer.msg('复制成功', {icon: 1});
		  	});
		  	clip.glue('clip_button', 'clip_container');
		}
</script>
<style>
	.showDIV {
		width: 350px;
		height: 180px;
		border-style:none;
	}
	
	.showDIV img {
		margin-top: 25px;
		margin-left: 25px;
	}
	
	.showDIV button {
		width: 70px;
		height: 30px;
		color: #fff;
		background: #f90;
		border: none;
		right: 70px;
		top: 30px;
		border-radius: 8px;
	}
</style>
</head>

<body>
	<div id="twodimencode_div" class="showDIV"></div>
</body>
</html>