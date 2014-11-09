/**
 * 用户管理 2014-03-19
 */

// 用户列表数据
var datagrid;
// 分组表单Dialog
var groupDialog;
// 添加/修改分组表单
var groupForm;
// 用户分组数据
var groupData;
// 当前选择的用户分组
var curGroupId;

$(function() {
	// 加载用户分组
	loadGroupList();
	datagrid = $('#datagrid').datagrid({
				url : domain + '/wechat/app/busiapp_pageBusiApp.action',
				toolbar : '#toolbar',
				iconCls : 'icon-save',
				pagination : true,
				pageSize : 10,
				pageList : [10, 15, 20],
				fit : true,
				singleSelect : true,
				fitColumns : true,
				nowrap : false,
				border : false,
				idField : 'id',
				frozenColumns : [[{
							title : 'id',
							field : 'id',
							hidden : true
						}, {
							field : 'name',
							title : 'name',
							width : 150
						}, {
							field : 'beanName',
							title : 'beanName',
							width : 150
						}]],
				columns : [[{
							field : 'methodName',
							title : 'methodName',
							width : 50
						}, {
							field : 'app_url',
							title : 'app_url',
							width : 150
						}, {
							field : 'headimgurl',
							title : '頭像',
							width : 150,
							formatter : function(value, rowData, rowIndex) {
								return parseReqxml2html(value, rowData.in_time);
							}
						}, {
							field : 'subscribe_time',
							title : '關注時間',
							width : 130,
							formatter : function(value, rowData, rowIndex) {
								return formattime(value);
							}
						}, {
							field : 'group_id',
							title : '分組',
							width : 150,
							formatter : function(value, rowData, rowIndex) {
								return createGroupCombobox(value, rowData.id);
							}
						}, {
							field : 'unsubscribe_time',
							title : '取消關注時間',
							width : 130,
							formatter : function(value, rowData, rowIndex) {
								return formattime(value);
							}
						}, {
							field : 'support_req_type',
							title : 'support_req_type',
							width : 90
						}]]
			});
	groupDialog = $('#groupDialog').show().dialog({
		modal : true,
		buttons : [{
			text : '確定',
			handler : function() {
				groupForm.form('submit', {
							url : domain + '/wechat/app/user_saveOrUpdateGroup.action',
							success : function(data) {
								var res = $.evalJSON(data);
								groupDialog.dialog('close');
								if (res && 'success' == res.status) {
									fjx.showMsg('操作成功！');
								} else {
									$.messager.alert('提示', res
													? res.msg
													: '操作失敗', 'error');
								}
								loadGroupList();
							},
							onSubmit : function() {
								$.messager.progress({
											text : '數據提交中....',
											interval : 100
										});
							}
						});
			}
		}]
	}).dialog('close');

	// 实例化表单
	groupForm = $('#groupForm').form();

	$(".inner_menu_link").live('click', function() {
				var groupId = $(this).attr("data-id");
				$(".inner_menu_item").removeClass("selected");
				$(this).parent().addClass("selected");
				curGroupId = groupId;
				searchDatagrid();
			});

	$(".edit_gray").live('click', function() {
				groupForm.form('load', {
							"paramMap.id" : $(this).attr("data-gid"),
							"paramMap.name" : $(this).attr("data-gname")
						});
				groupDialog.dialog('open').dialog({
							title : '修改分组'
						});
			});
});

// 条件搜索
function searchDatagrid() {
	var start_time = $('#toolbar input[comboname="paramMap.start_time"]')
			.datetimebox('getValue');
	var end_time = $('#toolbar input[comboname="paramMap.end_time"]')
			.datetimebox('getValue');
	datagrid.datagrid('load', {
				"paramMap.key_word" : $('#toolbar input[name="paramMap.key_word"]')
						.val(),
				"paramMap.start_time" : start_time,
				"paramMap.end_time" : end_time,
				"paramMap.group_id" : curGroupId
			});
	$('#toolbar input[comboname="paramMap.start_time"]').datetimebox(
			'setValue', start_time);
	$('#toolbar input[comboname="paramMap.end_time"]').datetimebox('setValue',
			end_time);
}

function clearDatagrid() {
	$('#toolbar input').val('');
	datagrid.datagrid('load', {});
}

function loadGroupList() {
	$.ajax({
		url : domain + '/wechat/app/user_groupList.action',
		cache : false,
		dataType : "json",
		success : function(data) {
			if (data) {
				groupData = data;
				$(".custom").remove();
				var html = "";
				$.each(data, function(i, row) {
					html += '<dd class="inner_menu_item custom">'
							+ '<a href="javascript:void(0);" data-id="'
							+ row.id
							+ '" class="inner_menu_link">'
							+ '<strong>'
							+ row.name
							+ '</strong><em class="num"></em>'
							+ '</a>'
							+ '<span class="menu_opr">'
							+ '<a data-gid="'
							+ row.id
							+ '" data-gname="'
							+ row.name
							+ '" href="javascript:void(0);" class="icon16_common edit_gray no_extra js_iconEdit">编辑</a>'
							+ '<a data-gid="'
							+ row.id
							+ '" data-gname="'
							+ row.name
							+ '" href="javascript:void(0);" class="icon16_common del_gray js_iconDel">删除</a>'
							+ '</span>' + '</dd>';
				});
				$(".inner_menu").append(html);
			} else {
				$.messager.alert('提示', '用户分组加载失败！', 'error');
			}
		}
	});
}

/**
 * 添加分组
 */
function appGroup() {
	groupForm.form('clear');
	groupDialog.dialog('open').dialog({
				title : '添加分组'
			});
}

/**
 * 生成用户分组combobox
 */
function createGroupCombobox(group_id, user_id) {
	var html = '<select class="easyui-combobox group-combobox" id="com'
			+ user_id + '" style="width:150px;"'
			+ 'data-options="onSelect:groupSelect">' + '<option value=",'
			+ user_id + '" >未分组</option>';
	$.each(groupData, function(i, value) {
				if (value.id == group_id) {
					html += '<option value="' + value.id + ',' + user_id
							+ '" selected="selected" >' + value.name
							+ '</option> ';
				} else {
					html += '<option value="' + value.id + ',' + user_id + '">'
							+ value.name + '</option> ';
				}
			});
	html += '</select>';
	return html;
}
// 更改分组
function groupSelect(record) {
	var id = record.value.split(',')[1];
	var group_id = record.value.split(',')[0];
	var data = {
		"paramMap.id" : id,
		"paramMap.group_id" : group_id
	};
	$.ajax({
		url : domain + '/wechat/app/user_updateUser.action',
		cache : false,
		data : data,
		dataType : "json",
		success : function(res) {
			if (res && res.status === 'success') {
				fjx.showMsg('修改分组成功');
				// $("com"+id).combobox("select",record.value);
				datagrid.datagrid("reload");
			} else {
				$.messager.alert('提示', res ? res.msg : '修改分組失敗！',
						'error');
			}
		}
	});
}