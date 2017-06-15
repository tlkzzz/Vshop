<@p.userHeader title="余额"/>
<script type="text/javascript" src="${base}/res/js/smilies/smilies_data.js" charset="utf-8"></script>  
<script type="text/javascript" src="${base}/res/js/smilies/smilies.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jquery.caretInsert.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jcarousel/jquery.jcarousel.min.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jquery.charCount.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="余额"/>
    	<div class="right-content">
      		<div class="main">
        		<link type="text/css" href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" >
				<style type="text/css">
				.path { display: none;}
				.fd-media .goodsinfo dt { width: 300px;}
				</style>
				<!--内容-->
				<div class="wrap">
  					<div class="layout-l" style="width: 100%">
   						<div class="member-intro">
      						<dl>
      							<dt>可用余额:</dt>
        						<dd>
        							<span style="color:red;font-size: 16px;">
	        							<script type="text/javascript">
			              					var available = number_format(${member.availablePredeposit},2);
			              					document.write("&yen;" + available);
			              				</script>
		              				</span>
        						</dd>
                				<dt>锁定余额:</dt>
                				<dd>
                					<span style="color:red;font-size: 16px;">
	        							<script type="text/javascript">
			              					var available = number_format(${member.freezePredeposit},2);
			              					document.write("&yen;" + available);
			              				</script>
		              				</span>
                				</dd>
                				<dt>
                					<input type="button" class="submit" value="充值" onClick="recharge();"/>
                				</dt>
              				</dl>
    					</div>
  					</div>
  					<div class="clear"></div>
				</div>
			</div>
    	</div>
	</div>
</div>
<script type="text/javascript">
	var APP_BASE = '${base}';
	function recharge(){
		location.href = APP_BASE+"/predeposit/rechargeIndex";
	}
</script>
<@p.userfooter/>