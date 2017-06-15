$(document).ready(function(){
	//列表下拉
	$('img[nc_type="flexType"]').click(function(){
		var status = $(this).attr('status');
		if(status == 'open'){
			var pr = $(this).parent('td').parent('tr');
			var id = $(this).attr('fieldid');
			var obj = $(this);
            var level = $(this).attr('level');
			$(this).attr('status','none');
			//ajax
			$.ajax({
				url: APP_BASE+'/store/type/child?id='+id+'&level='+(Number(level)+1),
				dataType: 'json',
				success: function(data){
					var src='';
					for(var i = 0; i < data.length; i++){
						var tmp_vertline = level == 2 ? "<img class='preimg' src='' width='24px' height='0'/>" : "<img class='preimg' src='templates/images/vertline.gif'/>";
						src += "<tr class='"+pr.attr('class')+" row"+id+"'>";
						src += "<td class='w36'><input type='checkbox' name='ids' value='"+data[i].id+"' class='checkitem' />";
						if(data[i].hasChild != 0 ){
							src += "<img fieldid='"+data[i].id+"' status='open' nc_type='flexType' level='" + (Number(level)+1) + "' src='"+APP_BASE+"/res/images/tv-expandable.gif' />";
						}else{
							src += "<img fieldid='"+data[i].id+"' status='none' nc_type='flexType' src='"+APP_BASE+"/res/images/tv-item.gif' />";
						}
						//图片
						src += "</td><td class='w48 sort'>";
						//排序
						src += "<span title='可编辑' datatype='number' fieldid='"+data[i].id+"' fieldname='sort' modUrl='"+APP_BASE+"/store/type/modifySort' nc_type='inline_edit' class='editable'>"+data[i].sort+"</span>";
						//名称
						src += "<td class='name'>";
						for(var tmp_i=1; tmp_i < (data[i].deep-1); tmp_i++){
							src += tmp_vertline;
						}
						if(data[i].have_child != 0){
							src += " <img fieldid='"+data[i].id+"' status='open' nc_type='flexType' src='"+APP_BASE+"/res/images/tv-item1.gif' />";
						}else{
							src += " <img fieldid='"+data[i].id+"' status='none' nc_type='flexType' src='"+APP_BASE+"/res/images/tv-expandable1.gif' />";
						}
						src += "<span title='可编辑' required='1' fieldid='"+data[i].id+"' modUrl='"+APP_BASE+"/store/type/modifyName' fieldname='name' nc_type='inline_edit' class='node_name editable'>"+data[i].name+"</span>";
						src += level == 1 ? _.format('<a class="btn-add-nofloat marginleft" href="{0}/store/type/addChildIndex?id={1}&parentId={2}">新增下级</a>', APP_BASE, data[i].id, data[i].id) : '';
						//src += '<a class="btn-add-nofloat marginleft" href="${base}/store/type/addIndex?id=${c.id}&parentId=${c.id}">新增下级</a>'
						src += "</td>";
						
						//操作
						src += "<td class='w84'>";
						src += "<span><a href='"+APP_BASE+"/store/type/updateIndex?id="+data[i].id+"'>编辑</a>";
						src += " | <a href=\"JavaScript:void(0);\" onclick=\"delClassid('"+data[i].id+"');\">删除</a>";
						src += "</td>";
						src += "</tr>";
					}
					//插入
					pr.after(src);
					obj.attr('status','close');
					obj.attr('src',obj.attr('src').replace("tv-expandable","tv-collapsable"));
					$('img[nc_type="flexType"]').unbind('click');
					$('span[nc_type="inline_edit"]').unbind('click');
					//重现初始化页面
                    $.getScript(APP_BASE+"/res/js/jquery.edit.js");
					$.getScript(APP_BASE+"/res/js/store/jquery.store_type.js");
					$.getScript(APP_BASE+"/res/js/admincp.js");
				},
				error: function(){
					alert('获取信息失败');
				}
			});
		}
		if(status == 'close'){
			$(".row"+$(this).attr('fieldid')).remove();
			$(this).attr('src',$(this).attr('src').replace("tv-collapsable","tv-expandable"));
			$(this).attr('status','open');
		}
	})
});