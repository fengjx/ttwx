
/**
 * 默认消息回复
 * 2014年5月20日 fengjx
 */
var msgAction;

$(function(){
	loadDefaultMsg();
});

function loadDefaultMsg(){
	$.ajax({
		url :  domain + '/admin/action/load',
		data : {
			"ext_type" : "wechat_default_msg"
		},
		dataType : "json",
		success : function(res) {
			msgAction = res;
			if(res){
				$("#set-tip").hide();
				thowSetting();
			}
		}
	});
}

function thowSetting(){
	//清除数据
	if(!msgAction){		//没有設置回复信息
		showActionContent('action_index');
		return false;
	}
	var viewHtml = "";		//预览效果HTML
	if(msgAction.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(msgAction.material.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			viewHtml = json.Content;
		}else if(msgType == "news"){	//图文消息
			viewHtml = xml2NewsHtml(msgAction.material.xml_data,msgAction.material.str_in_time,msgAction.material.id);
		}
	}else if(msgAction.action_type == 'api'){
		if(!msgAction.extApp){
			viewHtml = "插件已经被删除，请重新配置！";
		}else{
			viewHtml = "从接口【"+msgAction.extApp.name+"】中获得数据";
		}
	}
	$("#viewDiv").html(viewHtml);
	$.parser.parse('#viewDiv');
	$("#resp-setting").hide();
	$("#view").show();
}



function updateMsgView(){
	clearData();
	$("#editType").val("edit");
	$("#msgActionId").val(msgAction.id);
	var tabIndex;
	
	if(msgAction.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(msgAction.material.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			tabIndex = 0;
			$("#replyText").val(json.Content);
		}else if(msgType == "news"){	//图文消息
			tabIndex = 4;
			newsId = msgAction.material_id;
			viewHtml = xml2NewsHtml(msgAction.material.xml_data,msgAction.material.str_in_time,msgAction.material.id);
			$("#preview_news").html(viewHtml);				
		}
	}else if(msgAction.action_type == 'api'){
		tabIndex = 5;
		if (msgAction.extApp) {
			busiapi_combobox.combobox("select",msgAction.extApp.id);
		}
	}
	$("#edit_tabs").tabs("select",tabIndex);
	$("#view").hide();
	$("#resp-setting").show();
}

/**
 * 提交响应规则設置
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType){
	msgActionForm.form('submit', {
		url : domain + '/admin/action/save',
		success : function(data) {
			fjx.closeProgress();
			var res = $.evalJSON(data);
			if(res && '1' == res.code){
//				clearData();
				parent.fjx.showMsg('設置成功');
				window.location.reload();
			}else{
				$.messager.alert('提示',	res?res.msg:'设置失败！','error');
			}
		},
		onSubmit : function(){
			$("#extType").val("wechat_default_msg");		//默认消息
			if(respType === 'text'){
				$("#msgActionType").val("material");		//响应消息类型
				var txtContent = $.trim($("#replyText").val());
				if(!txtContent || "" == txtContent){
					$.messager.alert('提示', '请输入要回复的内容', 'warning');
					return false;
				}
				$("#txtContent").val(txtContent);
			}
			if(respType === 'news'){
				$("#msgActionType").val("material");
				var newsId = $("#newsId").val();
				if(!newsId || newsId == ''){
					$.messager.alert('提示', '请选择素材', 'warning');
					return false;
				}
				$("#msgMaterialId").val(newsId);
			}
			if(respType === 'api'){
				$("#msgActionType").val("api");
				var app_id = busiapi_combobox.combobox("getValue");
				if(!app_id){
					$.messager.alert('提示', '请选择业务接口', 'warning');
					return false;
				}
				$("#msgExtAppId").val(app_id);
			}
			fjx.progress();
		}
	});
}