<@p.header title="水印管理"/>
<div class="layout">
  <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">商家中心</a> <span>></span>
                <a href="#?act=store_album&op=album_cate"/>
                图片空间                </a><span>></span>水印管理              </div>
    </div>
    <div class="main">
      
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
  <li class="normal"><a  href="${base}/other/albumcate">我的相册</a></li><li class="active"><a  href="${base}/other/storewatermark">水印管理</a></li></ul>
  </div>
  <div class="ncu-form-style">
    <form method="post" enctype="multipart/form-data" action="#?act=store_album&op=store_watermark" id="wm_form">
      <div class="setup">
        <dl>
          <dt>水印图片：</dt>
          <dd>
                        <p class="picture"  style="display:none;"><span class="thumb"><i></i><img id="imgView" src="" onload="javascript:DrawImage(this,100,100);"/></span></p>
            <p>
              <input type="file" maxlength="0" hidefocus="true" nc_type="logo" name="image" id="image"/>
            </p>
            <p class="hint">请选择水印图片</p>
                      </dd>
        </dl>
        <dl>
          <dt>图片质量：</dt>
          <dd>
            <p>
              <input type="text" name="image_quality" class="text" style="width:25px" value="90"/>
              % </p>
            <p class="hint">0 - 100</p>
          </dd>
        </dl>
        <dl>
          <dt>图片位置：</dt>
          <dd>
            <ul class="wm_pos" id="wm_image_pos">
              <h3 class="field_notice">选择水印图片放置位置</h3>
              <li>
                <input type="radio" name="image_pos" value="1" checked/>
                <label>左上</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="2"/>
                <label>正上</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="3"/>
                <label>右上</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="4"/>
                <label>左中</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="5"/>
                <label>中间</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="6"/>
                <label>右中</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="7"/>
                <label>左下</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="8"/>
                <label>中下</label>
              </li>
              <li>
                <input type="radio" name="image_pos" value="9"/>
                <label>右下</label>
              </li>
            </ul>
          </dd>
        </dl>
        <dl>
          <dt>融合度：</dt>
          <dd>
            <p>
              <input type="text" class="text"  name="image_transition" value="20"/>
            </p>
            <p class="hint">水印图片与原图片的融合度</p>
          </dd>
        </dl>
        <dl>
          <dt>水印文字：</dt>
          <dd>
            <p>
              <textarea name="wm_text" rows="3" ></textarea>
            </p>
            <p class="hint">水印文字</p>
          </dd>
        </dl>
        <dl>
          <dt>文字大小：</dt>
          <dd>
            <p>
              <input id="wm_text_size" class="text"  type="text" name="wm_text_size" value="20"/>
              px </p>
            <p class="hint">设置水印文字大小</p>
          </dd>
        </dl>
        <dl>
          <dt>文字角度：</dt>
          <dd>
            <p>
              <select name="wm_text_angle" class="" style="width:50px;">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4" selected="selected">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
              </select>
            </p>
            <p class="hint">水印文字角度,尽量不要更改</p>
          </dd>
        </dl>
        <dl>
          <dt>文字位置：</dt>
          <dd>
            <ul class="wm_pos" id="wm_text_pos">
              <h3 class="field_notice">选择水印文字放置位置</h3>
              <li>
                <input type="radio" name="wm_text_pos" value="1"/>
                <label>左上</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="2"/>
                <label>正上</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="3" checked/>
                <label>右上</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="4"/>
                <label>左中</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="5"/>
                <label>中间</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="6"/>
                <label>右中</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="7"/>
                <label>左下</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="8"/>
                <label>中下</label>
              </li>
              <li>
                <input type="radio" name="wm_text_pos" value="9"/>
                <label>右下 </label>
              </li>
            </ul>
          </dd>
        </dl>
        <dl>
          <dt>文字字体：</dt>
          <dd>
            <p>
              <select name="wm_text_font" class="">
                <br />
<b>Warning</b>:  Invalid argument supplied for foreach() in <b>E:\APMServ5.2.6\www\htdocs\templates\default\member\store_watermark.form.php</b> on line <b>185</b><br />
              </select>
            </p>
            <p class="hint">水印文字的字体</p>
          </dd>
        </dl>
        <dl>
          <dt>文字颜色：</dt>
          <dd>
            <p>
              <input id="wm_text_color"  class="text"  type="text"  name="wm_text_color" value="#CCCCCC"/>
            </p>
            <p class="hint">水印字体的颜色值</p>
            <div id="colorpanel" style="display:none;width:253px;height:177px;"></div>
          </dd>
        </dl>
        <dl class="bottom">
          <dt>&nbsp;</dt>
          <dd>
            <input type="hidden" name="form_submit" value="ok" />
            <input type="submit" class="submit" value="提交" />
          </dd>
        </dl>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script> 
<script type="text/javascript">
var SITE_URL = "http://192.168.1.230";
$(function(){
	$('#del_image').click(function (){
		var result = confirm('确定删除水印图片?');
		if (result)
		{
			$('#image').css('display','none');
			$('#del_image').css('display','none');
			$('#is_del_image').val('ok');
			$('#wm_form').submit();
		}
	});
	$('#wm_form').validate({
    	submitHandler:function(form){
    		ajaxpost('wm_form', '', '', 'onerror') 
    	},
        rules : {
            image_quality : {
                required   : true,
				number : true,
				min : 0,
				max : 100
            },
            image_transition : {
                required   : true,
				number : true,
				min : 0,
				max : 100
            },
			wm_text_size : {
				required : true,
				number : true
			},
			wm_text_color : {
				required : true,
				maxlength : 7
			}
        },
        messages : {
            image_quality : {
                required   : '水印图片质量不能为空',
				number : '水印图片质量必须为数字',
				min : '水印图片质量在 0-100 之间',
				max : '水印图片质量在 0-100 之间'
            },
            image_transition : {
                required   : '水印图片融合度不能为空',
				number : '水印图片融合度必须为数字',
				min : '水印图片融合度在 0-100 之间',
				max : '水印图片融合度在 0-100 之间'
            },
			wm_text_size : {
				required : '水印文字大小不能为空',
				number : '水印文字大小必须为数字'
			},
			wm_text_color : {
				required : '水印字体颜色不能为空',
				maxlength : '字体颜色值格式不正确'
			}
        }
    });
});

$(document).ready( function () { 
     $("div").cssRadio(); 
     $("div").cssCheckBox(); 
}); 
jQuery.fn.cssRadio = function () { 
    $(":input[type=radio] + label").each( function(){ 
            if ( $(this).prev()[0].checked ) 
                $(this).addClass("checked"); 
            }) 
        .hover( 
            function() { $(this).addClass("over"); }, 
            function() { $(this).removeClass("over"); } 
            ) 
        .click( function() { 
             var contents = $(this).parent().parent(); /*多组控制 关键*/ 
            $(":input[type=radio] + label", contents).each( function() { 
                $(this).prev()[0].checked=false; 
                $(this).removeClass("checked");    
            }); 
            $(this).prev()[0].checked=true; 
             $(this).addClass("checked"); 
            }).prev().hide(); 
}; 

jQuery.fn.cssCheckBox = function () { 
    $(":input[type=checkbox] + label").each( function(){ 
            if ( $(this).prev()[0].checked ) 
                {$(this).addClass("checked");}            
            }) 
        .hover( 
            function() { $(this).addClass("over"); }, 
            function() { $(this).removeClass("over"); } 
            ) 
        .toggle( function()  /*不能click，不然checked无法回到unchecked*/ 
            {                
                $(this).prev()[0].checked=true; 
                 $(this).addClass("checked"); 
            }, 
            function() 
            { 
                $(this).prev()[0].checked=false; 
                 $(this).removeClass("checked"); 
            }).prev().hide();           
} 

$(document).ready(function() {
    ShowColorControl("wm_text_color");
});

function ShowColorControl(controlId)
{
    $("#" + controlId).bind("click", OnDocumentClick);
}
var ColorHex = new Array('00', '33', '66', '99', 'CC', 'FF')
var SpColorHex = new Array('FF0000', '00FF00', '0000FF', 'FFFF00', '00FFFF', 'FF00FF')
var current = null
function initPanel() {
    var colorTable = ''
    for (i = 0; i < 2; i++) {
        for (j = 0; j < 6; j++) {
            colorTable = colorTable + '<tr style="height:12px;">'
            colorTable = colorTable + '<td  style="width=11px;height:12px;background-color:#000000">'

            if (i == 0) {
                colorTable = colorTable + '<td style="width=11px;height:12px;background-color:#' + ColorHex[j] + ColorHex[j] + ColorHex[j] + '">'
            }
            else {
                colorTable = colorTable + '<td style="width=11px;height:12px;background-color:#' + SpColorHex[j] + '">'
            }


            colorTable = colorTable + '<td style="width=11px;height:12px;background-color:#000000">'
            for (k = 0; k < 3; k++) {
                for (l = 0; l < 6; l++) {
                    colorTable = colorTable + '<td style="width=11px;height:12px;background-color:#' + ColorHex[k + i * 3] + ColorHex[l] + ColorHex[j] + '">'
                }
            }
        }
    }
    colorTable = '<table width=253 border="0" cellspacing="0" cellpadding="0" style="border:1px #000000 solid;border-bottom:none;border-collapse: collapse" bordercolor="000000">'
           + '<tr height="30px"><td colspan=21 bgcolor=#cccccc>'
           + '<table cellpadding="0" cellspacing="1" border="0" style="border-collapse: collapse">'
           + '<tr><td width="3"><td><input type="text" id="DisColor" size="6" disabled style="border:solid 1px #000000;background-color:#ffff00"></td>'
           + '<td width="3"><td><input type="text" id="HexColor" size="7" style="border:inset 1px;font-family:Arial;" value="#000000"></td><td align="right" width="100%"><span id="spnClose" style="cursor:hand;">X</span>&nbsp;</td></tr></table></td></table>'
           + '<table  width=253  id="tblColor" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000"  style="cursor:hand;">'
           + colorTable + '</table>';
    $("#colorpanel").html(colorTable);
    $("#tblColor").bind("mouseover", doOver);
    $("#tblColor").bind("mouseout", doOut);
    $("#tblColor").bind("click", doclick);
    $("#spnClose").bind("click", function() { $("#colorpanel").css("display","none"); });
}
//鼠标覆盖事件
function doOver(event) {

    var e = $.event.fix(event);
    var element = e.target;
    if ((element.tagName == "TD") && (current != element)) {

        if (current != null) { current.style.backgroundColor = current.style.background; }
        element.style.background = element.style.backgroundColor;
        $("#DisColor").css("backgroundColor", element.style.backgroundColor);
        var clr = element.style.backgroundColor.toUpperCase();
        if (clr.indexOf('RGB') > -1) {
            clr = clr.substring(clr.length - 18);
            clr = rgb2hex(clr);
        }
        $("#HexColor").val(clr);
        //element.style.backgroundColor = "white";
        current = element;
    }
}
//鼠标移开事件
function doOut(event) {
    if (current != null) current.style.backgroundColor = current.style.background.toUpperCase();
}
//鼠标点击事件
function doclick(event) {
    var e = $.event.fix(event);
    if (e.target.tagName == "TD") {
        var clr = e.target.style.background;
        clr = clr.toUpperCase();
        if (targetElement) {
            if (clr.indexOf('RGB') > -1) {
                clr = clr.substring(clr.length - 18);
                clr = rgb2hex(clr);
            }
            targetElement.value = clr; 
        }
        DisplayClrDlg(false, e);
        return clr;
    }
}

var targetElement = null;

function OnDocumentClick(event) {

    var e = $.event.fix(event);
    var srcElement = e.target;
//    if (srcElement.alt == "clrDlg") {
        targetElement = srcElement;
        DisplayClrDlg(true, e);
//    }
//    else {

//        while (srcElement && srcElement.id != "colorpanel") {
//            srcElement = srcElement.parentElement;
//        }
//        if (!srcElement) {
//            DisplayClrDlg(false, e);
//        }
//    }
}

//显示颜色对话框
//display 决定显示还是隐藏
//自动确定显示位置
function DisplayClrDlg(display, event) {

    var clrPanel = $("#colorpanel");
    if (display) {
        var left = document.body.scrollLeft + event.clientX;
        var top = document.body.scrollTop + event.clientY;
        if (event.clientX + clrPanel.width > document.body.clientWidth) {
            //对话框显示在鼠标右方时，会出现遮挡，将其显示在鼠标左方
            left -= clrPanel.width;
        }
        if (event.clientY + clrPanel.height > document.body.clientHeight) {
            //对话框显示在鼠标下方时，会出现遮挡，将其显示在鼠标上方
            top -= clrPanel.height;
        }

        clrPanel.css("left", left);
        clrPanel.css("top", top);
        clrPanel.css("display", "block");
    }
    else {
        clrPanel.css("display", "none");
    }
}

$(document).ready(function() {
    initPanel();
});

//RGB转十六进制颜色值
function zero_fill_hex(num, digits) {
    var s = num.toString(16);
    while (s.length < digits)
        s = "0" + s;
    return s;
}

function rgb2hex(rgb) {
    if (rgb.charAt(0) == '#')
        return rgb;
    var n = Number(rgb);
    var ds = rgb.split(/\D+/);
    var decimal = Number(ds[1]) * 65536 + Number(ds[2]) * 256 + Number(ds[3]);
    return "#" + zero_fill_hex(decimal, 6);
}
</script>    </div>
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