<script type="text/javascript" src="${base}/res/js/member/member.js"></script>
<#assign favoriteStoreListTag = newTag("favoriteGoodsListTag")/>
<#assign favoriteStoreList =favoriteStoreListTag("{'tagDataType':'2','pageSize':'${pageSize}','pageNo':'${pageNo}','flags':'store'}")>
<table class="ncu-table-style">
	<thead>
		  <tr>
				<td class="tc"><input type="checkbox" id="all" class="checkall"/></td>
				<td colspan="20">
				<label for="all">
					<span class="all">全选</span>
				</label>
				<a href="javascript:void(0);" class="ncu-btn1" uri="" name="fav_id" nc_type="batchbuttons">
					<span>删除</span>
				</a>
				</td>
			</tr>
	</thead>
	<thead>
		<tr>
			<th class="w30"></th>
			<th class="w70"></th>
			<th class="tl">商家</th>
			<!-- <th class="w120">店内商品</th> -->
			<th class="w120">收藏时间</th>
			<th class="w150">收藏人气</th>
			<th class="w90">操作</th>
		</tr>
	</thead>
	<tbody>
	<#if favoriteStoreList?exists && favoriteStoreList?size gt 0>
	<#list favoriteStoreList as favoriteStore>
	<tr class="bd-line">
		<td class="tc"><input id="all" type="checkbox" class="checkitem"  value="${favoriteStore.favId}"/></td>
		<td>
			<div class="goods-pic-small"> 
				<span class="thumb size60">
					<i></i>
					<a href="${base}/store/shop?storeId=${favoriteStore.favId}" target="_blank"><img src="${imgServer}${favoriteStore.store.storeImage}" onload="javascript:DrawImage(this,60,60);"/></a>
				</span>
			</div>
		</td>
		<td class="tl">
			<dl class="goods-name">
			<dt>
				<a href="${base}/store/shop?storeId=${favoriteStore.favId}" target="_blank">
					${favoriteStore.store.storeName}
				</a>
				<p>${favoriteStore.store.areaInfo}</p>
			</dt>
			<dd>
				${favoriteStore.store.memberName}
				<!-- <a target="_blank" href="#" class="email" title="站内消息"></a> -->
			</dd>
			</dl>
		</td>
		<!-- <td><a href="javascript:get_store_goods('${favoriteStore.favId}');" class="favorites-new-goods">新上架商品(0)<i id="store-arrow-2" class="arrow-down">&nbsp;</i></a></td> -->
		<td class="goods-time">
		   <#if favoriteStore.favTimestr??>
		        ${favoriteStore.favTimestr?string("yyyy-MM-dd  HH:mm:ss")}
		   </#if>
		</td>
		<td>${favoriteStore.store.storeCollect}</td>
		<td>
			<a href="javascript:void(0)" onclick="deletefavotiteStore('${favoriteStore.favId}','${favoriteStore.favType}');" class="ncu-btn2 mt5">删&nbsp;除</a>
		</td>
	</tr>
	<tr id="store-goods-2" class="shop-new-goods" style="display:none;">
		<td colspan="20" style=" padding-top: 0;" >
			<div class="fr pr">
				<div class="arrow"></div>
				<ul class="jcarousel-skin-tango"></ul>
			</div>
		</td>
	</tr>
	</#list>
	<#else>
 	<tr>
 		<td colspan="9">
 			暂无收藏商家
 		</td>
 	</tr>
	</#if>
	</tbody>
	
	<tfoot>
		<tr>
			<td class="tc"><input type="checkbox" id="all2" class="checkall"/></td>
			<td colspan="7">
				<label for="all2">全选</label>
				<a href="javascript:void(0);" class="ncu-btn1"  name="fav_id"  nc_type="batchbuttons">
					<span>删除</span>
				</a>
				<div class="pagination">
				<#assign recordCount = favoriteStoreListTag("{'pageSize':'${pageSize}','tagDataType':'5','flags':'store'}")>
				<#import "/commons/tagpage.ftl" as q> <#--引入分页-->
				<#if recordCount??>
    				<@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				</#if>
				</div>
			</td>
		</tr>
	</tfoot>
</table>