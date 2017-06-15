<#assign couponMemberTag = newTag("couponMemberTag")/>
<#assign couponMap = couponMemberTag("{'couponIsUser':'${couponIsUser}','currentTime':'${time}'}") />
<#assign couponlist = couponMap.couponMemberList >
<#if couponlist?size gt 0>
<#list couponlist as couponMember>
<div class="container-fluid top25">
	<div class="row re">
		<div class="col-xs-12 col-sm-12 text-center">
			<img class="img-responsive" src="${base}/res/html5/images/juan_grey.png" />
		</div>
		<div class="abs">
			<div class="col-xs-5 col-sm-5 white padding10">
				<p>
					<span class="pull-left re left10">
						<font class="f32 bottomer">￥</font>
						<font class="f76 impact">${couponMember.couponPrice}</font>
				    	<font class="f30 bottomer1 left10">优惠券</font>
				    </span>
			    </p>
			</div>
			
			<div class="col-xs-7 col-sm-7 padding10">
				<p>
			    	<span class="f32 white text-center heiti left10 padding5">${couponMember.storeName}</span>
			    </p>
			    <div class="clear"></div>
				<p class="f24 top5 white text-left heiti">过期时间：${couponMember.endTimeStr?string("yyyy-MM-dd")}</p>
			</div>
			<#if couponIsUser==2>
				<div class="juan"><img src="${base}/res/html5/images/juan1.png" class="img-responsive" /></div>
			</#if>
		</div>
    </div>
</div>
</#list>
<#else>
	暂无符合要求的优惠券
</#if>

