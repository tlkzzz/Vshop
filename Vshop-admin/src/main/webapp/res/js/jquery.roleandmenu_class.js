$(document).ready(function(){
	//列表下拉
	$('img[nc_type="flex"]').click(function(){
		var status = $(this).attr('status');
		var roleid=$("#roleid").val();
		if(status == 'open'){
			var pr = $(this).parent('td').parent('tr');
			var mid = $(this).attr('fieldid');
            var level = $(this).attr('level');
			var obj = $(this);
			$(this).attr('status','none');
			//ajax
			$.ajax({
				url: APP_BASE+'/menu/class/child?id='+mid+'&level='+(Number(level)+1)+'&roleid='+roleid,
				dataType: 'json',
				success: function(data){
					var src='';
					for(var i = 0; i < data.length; i++){
						var tmp_vertline = "<img class='preimg' src='"+APP_BASE+"/res/images/vertline.gif'/>";
						src += "<tr class='"+pr.attr('class')+" row"+mid+"'>";
						if(data[i].ischange == 1){
						  src += "<td class='w36'><input type='checkbox' name='ids' value='"+data[i].mid+"' class='checkitem' checked='true'>";
						}else{
						  src += "<td class='w36'><input type='checkbox' name='ids' value='"+data[i].mid+"' class='checkitem'>";
						}
						//图片
						if(data[i].hasChild != 0){
							src += " <img fieldid='"+data[i].mid+"' status='open' level="+(data[i].deep)+" nc_type='flex' src='"+APP_BASE+"/res/images/tv-expandable.gif' />";
						}else{
							src += " <img fieldid='"+data[i].mid+"' status='none' nc_type='flex' src='"+APP_BASE+"/res/images/tv-item.gif' />";
						}
						src += "</td><td class='w48 sort'>";						
						//排序
						src += " <span title='可编辑下级分类排序' ajax_branch='goods_class_sort' datatype='number' fieldid='"+data[i].mid+"' fieldname='gc_sort' nc_type='inline_edit' class='editable tooltip'>"+data[i].msort+"</span></td>";
						//名称
						src += "<td class='w50pre name'>";
						
						
						for(var tmp_i=1; tmp_i < (data[i].deep-1); tmp_i++){  
							src += tmp_vertline;
						}
						if(data[i].hasChild != 0){
							src += " <img fieldid='"+data[i].mid+"' status='open' nc_type='flex' level="+(data[i].deep)+" src='"+APP_BASE+"/res/images/tv-item1.gif' />";
						}else{
							src += " <img fieldid='"+data[i].mid+"' status='none' nc_type='flex' src='"+APP_BASE+"/res/images/tv-expandable1.gif' />";
						}
						src += " <span title='可编辑下级分类名称' required='1' fieldid='"+data[i].mid+"' ajax_branch='goods_class_name' fieldname='gc_name' nc_type='inline_edit' class='editable tooltip'>"+data[i].mname+"</span>";
						//新增下级
						
						src += "</td>";
						//类型
						src += "<td>"+data[i].murl+"</td>";
						//操作
						src += "</tr>";
					}
					//插入
					pr.after(src);
					obj.attr('status','close');
					obj.attr('src',obj.attr('src').replace("tv-expandable","tv-collapsable"));
					$('img[nc_type="flex"]').unbind('click');
					$('span[nc_type="inline_edit"]').unbind('click');
					//重现初始化页面
                    $.getScript(APP_BASE+"/res/js/jquery.edit.js");
					$.getScript(APP_BASE+"/res/js/jquery.roleandmenu_class.js");
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