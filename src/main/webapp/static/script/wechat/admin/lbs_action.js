
/**
 * 默认消息回复
 * 2014年5月20日 fengjx
 */
var msgAction;
var msgActionForm;

$(function(){

	loadDefaultMsg();

	msgActionForm = $("#msgActionForm").submit(function () {
		$(this).ajaxSubmit({
			url: adminPath + "/wechat/action/save",
			dataType: 'json',
			beforeSubmit: function () {
				app.loadingModal("数据提交中....");
			},
			success: function (res) {
				app.closeDialog();
				if (res && '1' == res.code) {
					app.alertModal("保存成功！", function () {
						window.location.reload();
					});
				} else {
					app.alertModal(res.msg ? res.msg : "保存失败！", function () {
						app.closeDialog();
					});
				}
			}
		});
		return false;
	});

});

function loadDefaultMsg(){
	$.ajax({
		url :  adminPath + '/wechat/action/load',
		data : {
			"req_type" : "location"
		},
		dataType : "json",
		success : function(res) {
			msgAction = res;
			// 如果有数据，表示已经设置过了
			if(res&&res.id){
				$("#set-tip").hide();
				thowSetting();
			}
		}
	});
}

function thowSetting(){
	if(!msgAction){		//没有设置回复信息
		showActionContent('action_index');
		return false;
	}
	var viewHtml = "";		//预览效果HTML
	if(msgAction.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(msgAction.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			viewHtml = json.Content;
		}else if(msgType == "news"){	//图文消息
			viewHtml = xml2NewsHtml(msgAction.xml_data,msgAction.in_time,msgAction.material_id);
		}
	}else if(msgAction.action_type == 'api'){
		if(!msgAction.app_id){
			viewHtml = "配置的接口已经失效，请重新配置";
		}else{
			viewHtml = "从接口【"+msgAction.app_name+"】中获得响应数据";
		}
	}
	$("#viewDiv").html(viewHtml);
	$("#resp-setting").hide();
	$("#view").show();
}

function updateMsgView(){
	clearData();
	$("#editType").val("edit");
	$("#msgActionId").val(msgAction.id);
	var tabIndex;
	if(msgAction.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(msgAction.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			tabIndex = 0;
			$("#replyText").val(json.Content);
		}else if(msgType == "news"){	//图文消息
			tabIndex = 4;
			var viewHtml = xml2NewsHtml(msgAction.xml_data,msgAction.in_time,msgAction.material_id);
			$("#preview_news").html(viewHtml);				
		}
	}else if(msgAction.action_type == 'api'){
		tabIndex = 5;
		if (msgAction.app_id) {
			$("#busiapp_id").val(msgAction.app_id)
		}
	}
	$('#edit_tabs a:eq('+tabIndex+')').tab('show');
	$("#view").hide();
	$("#resp-setting").show();
}

/**
 * 提交响应规则設置
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType){
	$("#msgReqType").val(req_type);
	$("#eventType").val(event_type);
	if(respType === 'text'){
		$("#msgActionType").val("material");		//响应消息类型
		var txtContent = $.trim($("#replyText").val());
		if(!txtContent || "" == txtContent){
			app.alertModal("请输入要回复的内容");
			return false;
		}
		$("#txtContent").val(txtContent);
	}
	if(respType === 'news'){
		$("#msgActionType").val("material");
		var newsId = $("#newsId").val();
		if(!newsId || newsId == ''){
			app.alertModal("请选择素材");
			return false;
		}
		$("#msgMaterialId").val(newsId);
	}
	if(respType === 'api'){
		$("#msgActionType").val("api");
		var app_id = $.trim($("#busiapp_id").val());
		if (!app_id) {
			app.alertModal("请选择扩展应用");
			return false;
		}
		$("#msgExtAppId").val(app_id);

	}
	msgActionForm.submit();
}
