<#assign couponMemberTag = newTag("couponMemberTag")/>
<#assign couponMap = couponMemberTag("{'couponIsUser':'${couponIsUser}','currentTime':'${time}'}") />
<#assign couponlist = couponMap.couponMemberList >
<table class="ncu-table-style">
    <thead>
		<tr nc_type="table_header">
			<th colspan="8"> </th>
		</tr>
		<!-- <tr>
			<td class="tc"><input type="checkbox" id="all" class="checkall"/></td>
			<td colspan="20">
				<label for="all">全选</label>
				<a href="javascript:void(0);" class="ncu-btn1" uri="" name="fav_id" confirm="您确定要删除吗?" nc_type="batchbuttons">
					<span>删除</span>
				</a> 
			</td>
		</tr> -->
	</thead>
	<tbody>
	<#if couponlist?size gt 0>
		<#list couponlist as couponMember>
		<tr class="bd-line">
			<!-- <td style="vertical-align: middle;">
				<input type="checkbox"  class="checkitem"  value="${favoriteGoods.favId}"/>
			</td> -->
			<td>
				<div class="goods-pic-small">
					<span class="thumb size60"><i></i>
							<img src="${imgServer}${couponMember.couponPic}" onload="javascript:DrawImage(this,60,60);" />
					</span>
				</div>
			</td>
			<td>
				<dl class="goods-name">
					<dt>${couponMember.couponTitle}</dt>
					<dd class="share-sale">
						<span>开始时间：<em>${couponMember.startTimeStr?string("yyyy-MM-dd")}</em></span>
						<span>截止时间：<em>${couponMember.endTimeStr?string("yyyy-MM-dd")}</em></span>
					</dd>
				</dl>
			</td>
			<td></td>
			<td class="tl">
				<p> 
					<a href="${base}/store/shop?storeId=${couponMember.storeId}" target="_blank">${couponMember.storeName}</a>
						<span class="goods-favorite" title="该商家已收藏"><i class="have">&nbsp;</i></span>
					</p>
			</td>
			<td class="goods-price"><strong>优惠金额：${couponMember.couponPrice}</strong></td>
			<td class="goods-time">限制金额：${couponMember.couponLimit}</td>
			<!-- <td><p><a href="javascript:void(0)" onclick="deletefavotitegoods('${favoriteGoods.favId}','${favoriteGoods.favType}');" class="ncu-btn2 mt5">删&nbsp;除</a></p></td> -->
		</tr>
		</#list>
	</tbody>
	<#else>
	<tbody>
 	<tr>
 		<td colspan="9">
 			暂无符合要求的优惠券
 		</td>
 	</tr>
 	</tbody>
	</#if>
</table>