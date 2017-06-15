<@p.header title="图片空间 "/>
<div class="layout">
  <div class="sidebar">
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">供应商中心</a> <span>></span>
                <a href="#?act=store_album&op=album_cate"/>
                图片空间                </a><span>></span>我的相册              </div>
    </div>
        <div class="main">
      <script type="text/javascript" src="http://192.168.1.230/resource/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="http://192.168.1.230/resource/js/swfupload/swfupload.js"></script>
<script type="text/javascript" charset="utf-8" src="http://192.168.1.230/resource/js/swfupload/js/handlers.js"></script>
<script type="text/javascript">
var GOODS_SWFU;
$(function(){
    GOODS_SWFU = new SWFUpload({
        upload_url: "index.php?act=swfupload2&op=swfupload&instance=goods_image",
        flash_url: "http://192.168.1.230/resource/js/swfupload/swfupload.swf",
        post_params: {
            'sid': 2,
          	"PHPSESSID": "6be8eb341ae851f48b3c907aa6c7584a",
            "HTTP_USER_AGENT":"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.4) Gecko/20100611 Firefox/3.6.4 GTB7.1",
            'category_id': 1        },
        file_size_limit: "2 MB",
        file_types: "*.gif;*.jpg;*.jpeg;*.png",
        custom_settings: {
            upload_target: "goods_upload_progress",
            if_multirow: 1
        },

        // Button Settings
        button_image_url: "http://192.168.1.230/resource/js/swfupload/images/SmallSpyGlassWithTransperancy_17x18.png",
        button_width: 86,
        button_height: 18,
        button_text: '<span class="button">批量上传</span>',
        button_text_style: '.button {font-family: Helvetica, Arial, sans-serif; font-size: 12pt; font-weight: bold; color: #3F3D3E;} .buttonSmall {font-size: 10pt;}',
        button_text_top_padding: 0,
        button_text_left_padding: 18,
        button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
        button_cursor: SWFUpload.CURSOR.HAND,

        // The event handler functions are defined in handlers.js
        file_queue_error_handler: fileQueueError,
        file_dialog_complete_handler: fileDialogComplete,
        upload_progress_handler: uploadProgress,
        upload_error_handler: uploadError,
        upload_success_handler: uploadSuccess,
        upload_complete_handler: uploadComplete,
        button_placeholder_id: "spanButtonPlaceholder",
        file_queued_handler : fileQueued
    });
});
$(function(){
	$('#sel').change(function(){
		GOODS_SWFU.setPostParams({'category_id':$("#sel").val(),'sid': 2});
	});
});
</script>
<script type="text/javascript">
var SITE_URL = "http://192.168.1.230";
var num=0;
function add_uploadedfile(file_data)
{
	file_data = jQuery.parseJSON(file_data);
	if(file_data.state == 'true') {
		num++;
		if(GOODS_SWFU.getStats().files_queued == 0){
            window.setTimeout(function(){
                $('#uploader').hide();
                $('#open_uploader').find('.show').attr('class','hide');
                history.go(0);
            },4000);
        }
		return false;
	}
}
function upload_complete(){
	if (num == 0){
		alert('请检查您上传图片是否达到上限，如果是，可通过升级供应商解决此问题');
	}else{
		alert('成功上传'+num+'张图片');
	}
}
</script>
<link type="text/css" rel="stylesheet" href="${base}/res/js/kindeditor/themes/default.css"/>
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
  <li class="active"><a  href="${base}/other/albumcate">我的相册</a></li><li class="normal"><a  href="${base}/other/storewatermark">水印管理</a></li></ul>
  </div>
  <div id="pictureIndex">
    <div class="picture-control">
      <div class="newalbum"><a uri="#?act=store_album&op=album_add" nc_type="dialog" dialog_title="创建相册" class="btn" >创建相册</a></div>
      <div class="sortord">
        <form name="select_sort" id="select_sort">
          <input type="hidden" name="act" value="store_album" />
          <input type="hidden" name="op" value="album_cate" />
          排序:
          <select  name="sort" id="img_sort">
            <option value="4" selected  >按排序从大到小</option>
            <option value="5"  >按排序从小到大</option>
            <option value="0"  >按创建时间从晚到早</option>
            <option value="1"  >按创建时间从早到晚</option>
            <option value="2"  >按相册名降序</option>
            <option value="3"  >按相册名升序</option>
          </select>
        </form>
      </div>
            <div id="open_uploader" class="upload"><a href="JavaScript:void(0);" class="hide">上传图片</a></div>
      <div class="upload-con" id="uploader" style=" display: none;">
        <div class="upload-con-top"></div>
        <div class="upload-wrap"> 选择图片相册：          <select id="sel" style="width:100px;">
                        <option value='1' style="width:80px;">默认相册</option>
                      </select>
        </div>
        <div class="upload-wrap">
          <ul>
            <li class="btn1">
              <div id="divSwfuploadContainer">
                <div id="divButtonContainer"> <span id="spanButtonPlaceholder"></span> </div>
              </div>
            </li>
          </ul>
          <div id="remote" class="upload_file" style="display:none">
            <iframe src="#?act=store_goods&op=image_swupload&id=0&belong=2&instance=goods_image&upload_type=remote_image" width="208" height="39" scrolling="no" frameborder="0"></iframe>
          </div>
          <div id="goods_upload_progress"></div>
          <div class="upload-txt"><span>支持Jpg、Gif、Png格式，大小不超过1024KB的图片上传；浏览文件时可以按住ctrl或shift键多选。</span> </div>
        </div>
        <div class="upload-con-bottom"></div>
      </div>
          </div>
    <div class="clear" style=" border-bottom: solid 1px #E7E7E7;">&nbsp;</div>
        <ul class="album">
            <li class="hidden">
        <dl>
          <dt>
            <div class="covers"><a href="#?act=store_album&op=album_pic_list&id=1"> <span class="thumb size150"><i></i>
                            <img src="${base}/res/images/member/default_image.png" onload="javascript:DrawImage(this,150,150);">
                            </span></a></div>
            <h3 class="title"><a href="#?act=store_album&op=album_pic_list&id=1">默认相册</a></h3>
            <h5 class="quantity">共174张</h5>
          </dt>
          <dd class='table'> <span  nc_type="dialog" dialog_title="编辑相册" dialog_id='album_1' dialog_width="480" uri="#?act=store_album&op=album_edit&id=1"><a href="JavaScript:void(0);" class="edit2"  >编辑</a></span>
                      </dd>
        </dl>
      </li>
          </ul>
    <div class="clear"></div>
    <div class="pagination"></div>
    <div class="clear"></div>
        <script type="text/javascript">
		/*鼠标触及li显示dd内的控制按钮*/
			$(document).ready(function(){
				$('.album').children('li').bind('mouseenter',function(){
					$('.album').children('li').attr('class','hidden');
						$(this).attr('class','show');
				});
			$('.album').children('li').bind('mouseleave',function(){
				$('.album').children('li').attr('class','hidden');
				});
			})
        </script> 
    <script type="text/javascript">
		$(function(){
			$("#img_sort").change(function(){
				$('#select_sort').submit();
			}); 
		});
		</script> 
  </div>
</div>
    </div>
  </div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
    //收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function(){
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires :7,
					path : '/'
				});
			});
		});
	});
</script> 
</body>
</html>
<@p.footer/>