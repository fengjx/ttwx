
/**
 * 单图文本编辑
 */
 
 //文本编辑器
 var editor;
 //图片上传
 var uploader;
 //表单
 var news_form;
 //扩展web应用
var busiweb_combobox;

$(function(){
	//设置dwr同步执行
	dwr.engine.setAsync(false);
 	init();
 	//实例化form组建
 	news_form = $('#news_form').form();
});

/**
 * 初始化
 */
function init (){
	
	//实例化编辑器
	editor = UE.getEditor('container',{
 		initialFrameHeight:400,
 		initialFrameWidth:755
 	});
	uploader = UE.getEditor('upload_container',{
 	});
 	
 	uploader.ready(function () {
        //设置编辑器不可用
        uploader.setDisabled();
        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
        uploader.hide();
        //侦听图片上传
        uploader.addListener('beforeInsertImage', function (t, arg) {
            //将地址赋值给相应的input,只去第一张图片的路径
            $("#news_pic_url").val("value", arg[0].src);
            //图片预览
            $(".js_appmsg_thumb").attr("src", arg[0].src);
            $(".js_appmsg_thumb").show();
            $("#preview").attr("src", arg[0].src);
            $(".upload_preview").show();
        })
    });
	
	
	$('#news_title').keyup(function() {
		$(".appmsg_title").find("a").html($(this).val())
	});
	
	$('.frm_textarea').keyup(function(){
		$(".appmsg_desc").html($(this).val())
	});
	//选择自定义URL
	$(".frm_checkbox_label").click(function(){
		if($(".frm_checkbox").attr("checked")){//如果是选中
			$(this).removeClass("selected");
			$(".frm_checkbox").attr("checked",false);
			$("#busiapp_url").attr("readonly",true);
			$("#span_url").hide();
			editor.setEnabled();
		}else{
			$(this).addClass("selected");
			$(".frm_checkbox").attr("checked",true);
			$("#busiapp_url").attr("readonly",false);
			$("#span_url").show();
			editor.setDisabled('fullscreen');
		}
	});
	
	//加载web应用列表
	busiweb_combobox = $('#busiapp_url').combobox({   
		url: domain+'/admin/extapp/list?app_type=web',
        method: 'get',
        valueField:'app_url',
        textField:'name',
        groupField:'group_name'
	}); 
	
	if(material_id && material_id != ''){
		$.ajax({
			url :  domain + '/admin/material/load',
			data : "id="+material_id,
			cache : false,
			dataType : "json",
			success : function(data) {
				var json = $.xml2json(data.xml_data);
				$(".appmsg_title").find("a").html(json.Articles.item.Title);
				//标题
				$("#news_title").val(json.Articles.item.Title);
				$(".appmsg_desc").html(json.Articles.item.Title);
				//摘要
				$(".appmsg_desc").html(json.Articles.item.Description);
				$(".frm_textarea").html(json.Articles.item.Description);
				//图片
	            $("#news_pic_url").val("value", json.Articles.item.PicUrl);
	            //图片预览
	            $(".js_appmsg_thumb").attr("src", json.Articles.item.PicUrl);
	            $(".js_appmsg_thumb").show();
	            $("#preview").attr("src", json.Articles.item.PicUrl);
	            
	            var _url = json.Articles.item.Url;
	            if(isLocalUrl(_url)){
	            	var tmpContent = loadContent(_url);
	            	editor.addListener("ready", function () {
	        	        this.setContent(tmpContent);
	        		});
	            }else{
	            	$(".frm_checkbox_label").addClass("selected");
	    			$(".frm_checkbox").attr("checked",true);
	    			$("#busiapp_url").attr("readonly",false);
	    			busiweb_combobox.combobox("setValue",_url);
	    			$("#span_url").show();
	    			editor.addListener("ready", function () {
	    				editor.setDisabled('fullscreen');
	        		});
	            }
	            $(".upload_preview").show();
			}
		});
	}
	
}

//弹出图片上传的对话框
function upImage() {
	var myImage = uploader.getDialog("insertimage",{
		maxNum : 1
	});
	myImage.open();
}

function deleteImage(){
	//将地址赋值给相应的input,只去第一张图片的路径
    $("#news_pic_url").val("value", "");
    //图片预览
    $(".js_appmsg_thumb").attr("src", "");
    $(".js_appmsg_thumb").hide();
    $("#preview").attr("src","");
    $(".upload_preview").hide();
}

/**
 * 提交表单
 */
function submitNewsForm(){
	
	news_form.form('submit', {
		url : curUrl + '/admin/material/save',
		success : function(data) {
			fjx.closeProgress();
			var res = $.evalJSON(data);
			if(res && '1' == res.code){
				//fjx.showMsg('保存成功！');
				alert('保存成功！');
				//window.location.href = domain + '/admin/material/view';
				window.location.reload();
			}else{
				$.messager.alert('提示',	res?res.msg:'保存失败！','error');
			}
		},
		onSubmit : function(){ 
			var title = $("#news_title").val();
			var picUrl = $("#preview").attr("src");
			var description = $(".frm_textarea").val();
			if(!title || title == ''){
				$.messager.alert('提示', '请输入标题', 'warning');
				return false;
			}
			//if(!picUrl || picUrl == ''){
			//	$.messager.alert('提示', '请上传图片', 'warning');
			//	return false;
			//}
			if(!description || description == ''){
				$.messager.alert('提示', '请添加摘要', 'warning');
				return false;
			}
			var xml_data = "<xml>" +
							"<ToUserName><![CDATA[]]></ToUserName>" +
							"<FromUserName><![CDATA[]]></FromUserName>" +
							"<CreateTime><![CDATA[]]></CreateTime>" +
							"<MsgType><![CDATA[news]]></MsgType>" +
							"<ArticleCount>1</ArticleCount>" +
							"<Articles>" +
								"<item>" +
								"<Title><![CDATA["+title+"]]></Title>" +
								"<Description><![CDATA["+description+"]]></Description>" +
								"<PicUrl><![CDATA["+picUrl+"]]></PicUrl>" +
								"<Url_0><![CDATA[##url##]]></Url_0>" +
								"</item>" +
							"</Articles>" +
						   "</xml>";
			if($(".frm_checkbox").attr("checked")){	//选择自定义URL
				$("#contentsJson").remove();
				var app_url = busiweb_combobox.combobox("getValue");
				//如果app_url为空，表示用户没有选择应用的链接地址
				if(!app_url){
					//读取用户输入的URL
					app_url = busiweb_combobox.combobox("getText");
				}
				if(!app_url || '' == $.trim(app_url)){
					$.messager.alert('提示', '请填写URL或选择应用', 'warning');
					return false;
				}
				xml_data = xml_data.replace("<Url_0><![CDATA[##url##]]></Url_0>","<Url><![CDATA["+app_url+"]]></Url>");
			}else{
				var content = editor.getContent();
				if(!content || '' == $.trim(content)){
					$.messager.alert('提示', '请编辑页面内容內容，或填写链接地址', 'warning');
					flag = false;
					return false;
				}
				var contentsJson = "[{'content' : '"+content+"','title' : '"+title+"'}]";	
				$("#contentsJson").val(contentsJson);
			}
			$("#xml_data").val(xml_data);
			$.messager.progress({
				text : '数据提交中....',
				interval : 100
			});
		}
	});
}

//添加摘要
function addDesc(){
	$(".js_desc_area").show();
	$(".js_addDesc").hide();
}


/**
 * 读取素材内容
 */
function loadContent(url){
	if(!isLocalUrl(url)){
		return "";
	}
	var path = url.split(staticDomain)[1];
	var content = "";
	
	materialService.loadMaterialContentByUrl(path,{
		callback:function(data){
			content = data;
		},
        errorHandler:function(errorString, exception){
        	alert("正文內容获取失败");
        },
        exceptionHandler:function(exceptionString, exception){
        	alert("正文內容获取失败");
        }
	});
	return content;
}

/**
 * 判断链接是否是本项目链接
 * @param url
 * @returns {Boolean} 是本项目链接返回true
 */
function isLocalUrl(url){
	if(url.search(staticDomain + '/upload/html/material') === -1){
		return false;
	}
	return true;
}

