/**
 * 关键字回复管理
 */
 
var keywordDialog;
var viewDialog;
var msgActionForm;
var datagrid;

$(function(){
	
	init();
	
});


/**
 * 初始化
 */
function init(){
	//消息动作规则表单
	msgActionForm = $('#msgActionForm').form();
	
	//添加关键字dialog
	keywordDialog = $('#keywordDialog').show().dialog({
		modal : true,
		title : '添加关键字回复'
	}).dialog('close');
	
	//消息预览dialog
	viewDialog = $("#viewDialog").show().dialog({
		modal : true
	}).dialog('close');
	
	datagrid = $('#datagrid').datagrid({
		url : domain + '/admin/action/pageList?req_type=text',
		toolbar : '#toolbar',
		iconCls : 'icon-save',
		pagination : true,
		pageSize : 10,
		pageList : [10,15,20],
		fit : true,
		fitColumns : true,
		nowrap : false,
		border : false,
		idField : 'id',
		frozenColumns : [ [ {
			title : 'id',
			field : 'id',
			width : 50,
			checkbox : true
		}, {
			field : 'req_type',
			hidden : true
		},{
			field : 'material_id',
			hidden : true
		},{
			field : 'app_id',
			hidden : true
		},{
			field : 'action_type',
			hidden : true
		},{
			field : 'resp_type',
			hidden : true
		},{
			field : 'key_word',
			title : '关键字',
			width : 200
		} ] ],
		columns : [[ {
			field : 'dict_name',
			title : '动作响应类型',
			width : 150
		}, {
			field : 'str_in_time',
			title : '编辑时间',
			width : 150
		},{
			field : 'op',
			title : '操作',
			width : 150,
			formatter : function(value, rowData, rowIndex) {
				var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''+rowData.id+'\');" href="javascript:void(0);">查看</a>';
				html += '<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMsgAction(\''+rowData.id+'\');" href="javascript:void(0);">修改</a>';
				html += '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteMsgAction(\''+rowData.id+'\',\''+rowData.key_word+'\');" href="javascript:void(0);">刪除</a>';
				return html;
			}
		} ] ],
		onLoadSuccess : function(){
			$.parser.parse();
		}
	});
}


function searchDatagrid(){
	var start_time = $('#toolbar input[name="start_time"]').val();
	var end_time = $('#toolbar input[name="end_time"]').val();
	datagrid.datagrid('load', {
		"key_word" : $('#toolbar input[name="key_word"]').val(),
		"start_time" : start_time,
		"end_time" : end_time
	});
}

function clearDatagrid() {
	$('#toolbar input').val('');
	datagrid.datagrid('load', {});
}

/**
 * 打开添加关键字dialog
 */
function append() {
	clearData();
	$("#msgKeyWord").removeAttr("readonly")
	keywordDialog.dialog('open');
}

/**
 * 提交响应规则設置
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType){
	msgActionForm.form('submit', {
		url : domain + '/admin/action/save',
		ajax : true,
		success : function(data) {
			fjx.closeProgress();
			var res = $.evalJSON(data);
			if(res && '1' == res.code){
				clearData();
				keywordDialog.dialog("close");
				fjx.showMsg('设置成功');
				datagrid.datagrid("myReload");
			}else{
				$.messager.alert('提示',	res?res.msg:'设置失败！','error');
			}
		},
		onSubmit : function(){
			var keyWord = $("#msgKeyWord").val();
			if(!keyWord || '' == $.trim(keyWord)){
				$.messager.alert('提示', '请输入关键字', 'warning');
				return false;
			}
			$("#materiaMsgType").val(respType);		//响应消息类型
			$("#msgReqType").val(req_type);
			if(respType === 'text'){
				$("#msgActionType").val("material");
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
					$.messager.alert('提示', '请选择业务功能', 'warning');
					return false;
				}
				$("#msgExtAppId").val(app_id);
			}
			$.messager.progress({
				text : '数据提交中....',
				interval : 100
			});
		}
	});
}


function deleteMsgAction(ids, keywords){
	if(!ids || ids == ''){	//id为空，则从当前选中的行进行删除
		var rows = datagrid.datagrid('getSelections');
		if(!rows || rows.length<1){
			$.messager.show({
				msg : '请选择要删除的关键字',
				title : '提示'
			});
			return false;
		}else{
			ids = [];
			keywords = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id)
				keywords.push(rows[i].key_word);
			}
			ids = ids.join(',');
			keywords = keywords.join(',');
		}
	}
	$.messager.confirm('请确认', '你要删除关键字【'+keywords+'】的消息响应规则吗', function(r) {
		if (r) {
			$.ajax({
				url :  domain + '/admin/action/delete',
				data : 'ids='+ids,
				cache : false,
				dataType : "json",
				success : function(res) {
					if(res && '1' === res.code){
						fjx.showMsg('刪除成功');
						datagrid.datagrid("myReload");
					}else{
						$.messager.alert('提示',res?res.msg:'刪除失败','error');
					}
				}
			});
		}
	});
}

function editMsgAction(id){
	var row = undefined;
	if(!id || id == ''){	//id为空，则编辑当前选中的行
		var rows = datagrid.datagrid('getSelections');
		if(!rows || rows.length<1){
			fjx.showMsg('请选择要编辑的关键字');
			return false;
		}else if (rows.length > 1 ) {
			var keywords = [];
			for ( var i = 0; i < rows.length; i++) {
				keywords.push(rows[i].key_word);
			}
			fjx.showMsg('只能选择一个关键字进行编辑！你已经选择了【' + keywords.join(',') + '】' + rows.length + '个关键字');
			return false;
		} else if (rows.length == 1) {
			row = rows[0];
		}	
	}
	if(!row){
		datagrid.datagrid('unselectAll');
		row = datagrid.datagrid('selectRecord',id).datagrid("getSelected");
	}
	msgActionForm.form("clear");
	msgActionForm.form('load', {
		"editType" : "edit",
		"id" : row.id,
		"key_word" : row.key_word
	});
	var tabIndex;
	if(row.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(row.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			tabIndex = 0;
			$("#replyText").val(json.Content);
		}else if(msgType == "news"){	//图文消息
			tabIndex = 4;
		}
		
	}else if(row.action_type == 'api'){
		tabIndex = 5;
		if (row.app_id) {
			busiapi_combobox.combobox("select",row.app_id);
		}
	}
	$("#edit_tabs").tabs("select",tabIndex);
	$("#msgKeyWord").attr("readonly","readonly");
	keywordDialog.dialog('open');
}
/**
 * 预览
 * @param {} id
 * @return {Boolean}
 */
function view (id){
	var row = undefined;
	if(!id || id == ''){	//id为空，则预览当前选中的行
		var rows = datagrid.datagrid('getSelections');
		if(!rows || rows.length<1){
			$.messager.show({
				msg : '请选择要查看的关键字',
				title : '提示'
			});
			return false;
		}else if (rows.length > 1 ) {
			var keywords = [];
			for ( var i = 0; i < rows.length; i++) {
				keywords.push(rows[i].key_word);
			}
			$.messager.show({
				msg : '一次只能查看一个关键字的回复消息！你已经选择了【' + keywords.join(',') + '】' + rows.length + '个关键字',
				title : '提示'
			});
			return false;
		} else if (rows.length == 1) {
			row = rows[0];
		}
	}
	if(!row){
		datagrid.datagrid('unselectAll');
		row = datagrid.datagrid('selectRecord',id).datagrid("getSelected");
	}
	var viewHtml = "";		//预览效果HTML
	var title = "";
	if(row.action_type == 'material'){//数据源从素材读取
		var json = $.xml2json(row.xml_data);
		var msgType = json.MsgType;
		if(msgType == "text"){		//文本消息
			viewHtml = json.Content;
		}else if(msgType == "news"){	//图文消息
			viewHtml = xml2NewsHtml(row.xml_data,row.in_time,row.material_id);	//(wechat.js)
		}
	}else if(row.action_type == 'api'){
		if (row.app_name) {
			viewHtml = "从接口【"+row.app_name+"】中获得响应数据";
		}else{
			viewHtml = "插件已经被删除，请重新配置";
		}
	}
	$("#viewDiv").html(viewHtml);
	
	viewDialog.dialog({
		title:"用户发送文字消息【"+row.key_word+"】将收到以下回复"		
	}).dialog("open");
	
}