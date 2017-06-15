<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=720,user-scalable=no" />  
		<meta charset="utf-8" />
		<title>发红包</title>
		<link href="${base}/res/fhb/css/reset.css" rel="stylesheet" type="text/css" />
	    <link href="${base}/res/fhb/css/global.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="zp">
			<div id="content">
				<ul>
					<li>产品名称:<span>${g.name}</span></li>
					<li>生产日期:<span>${g.scsj}</span></li>
					<li>保质期:<span>${g.xsqy}</span></li>
					<li>生产产地:<span>${g.scs}</span></li>
				</ul>
				<!--<p>生产厂家：<span>${g.scs}</span></p>-->
			</div>
			<div id="footer">
				<div class="f-l">
			

<#if (key==1)>
<h4 class="size36">恭喜您!获得<em>${g.je}</em>元红包哟!</h4>
<#elseif (key==0)>
<h4 class="size34">感谢您的支持,请再接再厉！</h4>
<#elseif (key==2)>
	<h4 class="size24">非常遗憾，该二维码已经被扫描了<em>${g.nb}</em>次</h4>
					<p>首次扫描人：<i>${g.smr}</i></p>
					<p>扫码时间：<i>${g.smsj}</i></p>
<#else>
<h4>活动已结束！谢谢支持！</h4>
 </#if>


			
					<span>无法参与优彼“支持正品 扫码有奖”活动。<br />
					如果您确认这是开袋后首次扫码，请前往购买出反映，或关注“优彼食品”微信公众号发聩。
					</span>
				</div>
				<div class="f-r">
					<img src="${base}/res/fhb/img/e.jpg"/>
					<div class="f-r-p">
						<p>长按二维码识别</p>
						<p>快速关注“优彼食品”微信公众号</p>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
