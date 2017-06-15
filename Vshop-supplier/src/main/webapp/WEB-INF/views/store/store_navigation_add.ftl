<@p.header title="信息设置"/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<div class="layout">
    <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">商家中心</a> <span>></span>
                <a href="#"/>
                导航管理                </a><span>></span>新增导航              </div>
    </div>
        <div class="main">
      <div class="wrap">
<div class="tabmenu"><ul class="tab pngFix">
  <li class="normal"><a  href="${base}/store/storenavigation">导航列表</a></li><li class="active"><a  href="#">新增导航</a></li></ul>
</div>
  <div class="ncu-form-style">
    <form method="post" action="#?act=store&op=store_navigation" target="_parent" name="store_navigation_form" id="store_navigation_form" enctype="multipart/form-data">
      <input type="hidden" name="form_submit" value="ok"/>
      <input type="hidden" name="sn_id" value=""/>
      <dl>
        <dt class="required"><em class="pngFix"></em>导航名称：</dt>
        <dd>
          <input type="text" class="w150 text" name="sn_title" value="" />
        </dd>
      </dl>
      <dl>
        <dt>是否显示：</dt>
        <dd>
          <input type="radio" name="sn_if_show" value="1" checked="checked"/>
          是               <input type="radio" name="sn_if_show" value="0"/>
          否       </dd>
      </dl>
      <dl>
        <dt>排序：</dt>
        <dd>
          <input type="text" class="w50 text" name="sn_sort" value="255"/>
        </dd>
      </dl>
      <dl>
        <dt>内容：</dt>
        <dd>
          <textarea id="sn_content" name="sn_content" style="width:600px;height:300px;"></textarea>
          <script src="${base}/res/js/kindeditor/kindeditor-min.js" charset="utf-8"></script>
          <script src="${base}/res/js/kindeditor/lang/zh_CN.js" charset="utf-8"></script>
<script>
	var KE;
  KindEditor.ready(function(K) {
        KE = K.create("textarea[name='sn_content']", {
						items : ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', '|', 'selectall', 'clearhtml','quickformat','|',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'table', 'hr', 'emoticons', 'link', 'unlink', '|', 'about'],
						cssPath : "http://${base}/resource/kindeditor/themes/default/default.css",
						allowImageUpload : false,
						allowFlashUpload : false,
						allowMediaUpload : false,
						allowFileManager : false,
						syncType:"form",
						afterCreate : function() {
							var self = this;
							self.sync();
						},
						afterChange : function() {
							var self = this;
							self.sync();
						},
						afterBlur : function() {
							var self = this;
							self.sync();
						}
        });
			KE.appendHtml = function(id,val) {
				this.html(this.html() + val);
				if (this.isCreated) {
					var cmd = this.cmd;
					cmd.range.selectNodeContents(cmd.doc.body).collapse(false);
					cmd.select();
				}
				return this;
			}
	});
</script>
	        </dd>
      </dl>
      <dl>
        <dt>导航外链URL：</dt>
        <dd>
          <p>
            <input type="text" class="w300 text" name="sn_url" />
          </p>
          <p class="hint">请填写包含http://的完整URL地址,如果填写此项则点击该导航会跳转到外链</p>
          </td>
      </dl>
      <dl>
        <dt>新窗口打开：</dt>
        <dd>
	          <input type="radio" name="sn_new_open" value="1" checked="checked">
	                             是&nbsp;&nbsp;&nbsp;&nbsp;
	          <input type="radio" name="sn_new_open" value="0">
                       否</dd>
      </dl>
      <dl class="bottom">
        <dt>&nbsp;</dt>
        <dd>
          <input type="submit" class="submit" value="提交" />
        </dd>
      </dl>
    </form>
  </div>
</div>
<script>
$(document).ready(function(){
	$('#store_navigation_form').validate({
        rules : {
            sn_title : {
                required   : true
            }
        },
        messages : {
            sn_title : {
                required   : '导航名称不能为空'
            }
        }
    });
});
</script>   
   </div>
     </div>
    <div class="clear"></div>
    </div>

<script type="text/javascript">
	// 收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function() {
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires : 7,
					path : '/'
				});
			});
		});
	});
</script>
</body>
</html>
<@p.footer/>