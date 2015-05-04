/**
 * 消息回复管理 2014-03-19
 */

var viewDialog;
var datagrid;
var resTypeCombobox;
var eventTypeCombobox;

$(function() {

	// 消息预览dialog
	viewDialog = $("#viewDialog").show().dialog({
		modal : true
	}).dialog('close');

	// 加载消息记录数据
	datagrid = $('#datagrid').datagrid({
		url : domain + '/admin/msglog/pageList',
		queryParams : {
			"from_user_name" : $("#openid").val()
		},
		toolbar : '#toolbar',
		pagination : true,
		pageSize : 10,
		pageList : [10, 15, 20],
		fit : true,
		fitColumns : true,
		nowrap : false,
		border : false,
		idField : 'id',
		frozenColumns : [[{
					title : 'id',
					field : 'id',
					hidden : true
				}, {
					field : 'create_time',
					hidden : true
				}, {
					field : 'msg_id',
					hidden : true
				}, {
					field : 'to_user_name',
					hidden : true
				}, {
					field : 'from_user_name',
					title : '用戶OPENID',
					width : 200
				}]],
		columns : [[{
					// field : 'req_type',
					field : 'req_name',
					title : '消息类型',
					width : 50,
					formatter : function(value, rowData, rowIndex) {
						return formattime(value);
					}
				}, {
					// field : 'event_type',
					field : 'event_name',
					title : '事件类型',
					width : 50,
					formatter : function(value, rowData, rowIndex) {
						return formattime(value);
					}
				}, {
					field : 'req_xml',
					title : '发送内容',
					width : 150,
					formatter : function(value, rowData, rowIndex) {
						return parseReqxml2html(value, rowData.str_in_time);
					}
				}, {
					field : 'str_in_time',
					title : '发送时间',
					width : 150
				}, {
					field : 'resp_xml',
					title : '响应内容',
					width : 250,
					formatter : function(value, rowData, rowIndex) {
						if (!value || value == '') {
							return "无";
						}
						var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''
								+ rowData.id
								+ '\');" href="javascript:void(0);">查看</a>';
						return html;
					}
				}, {
					field : 'str_resp_time',
					title : '响应时间',
					width : 150
				}]],
		onLoadSuccess : function(){
			$.parser.parse();
		}
	});
	// 消息类型
	resTypeCombobox = $("#resType").combobox({
		valueField : 'dict_value',
		textField : 'dict_name',
		url : domain + '/dict/list?group_code=req_type'
	});
	// 事件类型
	eventTypeCombobox = $("#eventType").combobox({
		valueField : 'dict_value',
		textField : 'dict_name',
		url : domain + '/dict/list?group_code=event_type'
	});
});

function searchDatagrid() {
	// var start_time = $('#toolbar
	// input[comboname="paramMap.start_time"]').datetimebox('getValue');
	// var end_time = $('#toolbar
	// input[comboname="paramMap.end_time"]').datetimebox('getValue');
	var start_time = $('#toolbar input[name="start_time"]').val();
	var end_time = $('#toolbar input[name="end_time"]').val();
	datagrid.datagrid('load', {
				"key_word" : $('#toolbar input[name="paramMap.key_word"]')
						.val(),
				"start_time" : start_time,
				"end_time" : end_time,
				"req_type" : resTypeCombobox.combobox("getValue"),
				"event_type" : eventTypeCombobox.combobox("getValue"),
				"from_user_name" : $("#openid").val()
			});
}

function clearDatagrid() {
	$('#toolbar input').val('');
	datagrid.datagrid('load', {});
}

function view(id) {
	var row = undefined;
	if (!id || id == '') { // id为空，则编辑当前选中的行
		var rows = datagrid.datagrid('getSelections');
		if (!rows || rows.length < 1) {
			$.messager.show({
						msg : '請選擇一個要查看的關鍵字',
						title : '提示'
					});
			return false;
		} else if (rows.length > 1) {
			var keywords = [];
			for (var i = 0; i < rows.length; i++) {
				keywords.push(rows[i].key_word);
			}
			$.messager.show({
						msg : '只能選擇一個關鍵字查看回复消息,您已經選擇了【' + keywords.join(',')
								+ '】' + rows.length + '個關鍵字',
						title : '提示'
					});
			return false;
		} else if (rows.length == 1) {
			row = rows[0];
		}
	}
	if (!row) {
		datagrid.datagrid('unselectAll');
		row = datagrid.datagrid('selectRecord', id).datagrid("getSelected");
	}
	// 预览效果HTML
	var viewHtml = parseRespxml2html(row.resp_xml, row.str_resp_time);
	$("#viewDiv").html(viewHtml);

	viewDialog.dialog({
        title : "用戶收到的消息"
    }).dialog("open");

}