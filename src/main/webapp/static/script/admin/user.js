/**
 * 微信用户管理
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
		url : domain + '/admin/user/userPageList.action',
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
				field : 'group_name',
				hidden : true
			}, {
				field : 'openid',
				title : 'openid',
				width : 150
			}]],
		columns : [[{
				field : 'nickname',
				title : '昵称',
				width : 50
			}, {
				field : 'sex',
				title : '性別',
				width : 50
			}, {
				field : 'headimgurl',
				title : '头像',
				width : 150
			}, {
				field : 'str_subscribe_time',
				title : '关注时间',
				width : 130
			}, {
				field : 'group_id',
				title : '分组',
				width : 150,
				formatter : function(value, rowData, rowIndex) {
					return createGroupCombobox(value, rowData.id);
				}
			}, {
				field : 'str_unsubscribe_time',
				title : '取消关注时间',
				width : 130
			}, {
				field : 'op',
				title : '操作',
				width : 90,
				formatter : function(value, rowData, rowIndex) {
					var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="viewLog(\''+ rowData.openid + '\');" href="javascript:void(-1);">消息记录</a>';
					return html;
				}
			}]],
		onLoadSuccess : function(){
			$.parser.parse();
		}
	});
	groupDialog = $('#groupDialog').show().dialog({
		modal : true,
		buttons : [{
			text : '確定',
			handler : function() {
				groupForm.form('submit', {
					url : domain + '/admin/user/saveGroup.action',
					success : function(res) {
						fjx.closeProgress();
						var data = $.evalJSON(res);
						groupDialog.dialog('close');
						if (data && '1' == data.code) {
							fjx.showMsg('操作成功！');
						} else {
							$.messager.alert('提示', data? data.msg:'操作失败', 'error');
						}
						loadGroupList();
					},
					onSubmit : function() {
						$.messager.progress({
							text : '数据提交中....',
							interval : 100
						});
					},
					onLoadError:function(){
						fjx.closeProgress();
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
			"id" : $(this).attr("data-gid"),
			"name" : $(this).attr("data-gname")
		});
		groupDialog.dialog('open').dialog({
			title : '修改分组'
		});
	});

	// 刪除分組
	$(".del_gray").live("click", function() {
		var groupId = $(this).attr("data-gid");
		var groupName = $(this).attr("data-gname");
		deleteGroup(groupId, groupName);
	});

});

// 条件搜索
function searchDatagrid() {
	var start_time = $('#toolbar input[name="start_time"]').val();
	var end_time = $('#toolbar input[name="end_time"]').val();
	datagrid.datagrid('load', {
		"openid" : $('#toolbar input[name="openid"]').val(),
		"start_time" : start_time,
		"end_time" : end_time,
		"group_id" : curGroupId
	});
}

function clearDatagrid() {
	$('#toolbar input').val('');
	datagrid.datagrid('load', {});
}

function loadGroupList() {
	$.ajax({
		url : domain + '/admin/user/groupList.action',
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

function deleteGroup(groupId, groupName) {
	if (!groupId) {
		$.messager.alert('提示', '请选择要删除的分组！', 'info');
		return false;
	}
	$.messager.confirm('请确认', '确认要删除分组【' + groupName + '】吗', function(r) {
		if (r) {
			$.ajax({
				url : domain + '/admin/user/deleteGroup',
				data : 'id=' + groupId,
				dataType : "json",
				success : function(data) {
					if (data && '1' == data.code) {
						fjx.showMsg('刪除成功');
					} else {
						$.messager.alert('提示', data ? data.msg : '删除失败', 'error');
					}
					loadGroupList();
				}
			});
		}
	});
}

/**
 * 添加分组
 */
function appGroup() {
	groupForm.form('clear');
	groupDialog.dialog('open').dialog({
		title : '添加分組'
	});
}

/**
 * 生成用户分组combobox
 */
function createGroupCombobox(group_id, user_id) {
	var html = '<select class="easyui-combobox group-combobox" id="com'	+ user_id + '" style="width:150px;"'
			+ 'data-options="onSelect:groupSelect">' 
			+ 	'<option value=",'+ user_id + '" >未分組</option>';
	$.each(groupData, function(i, value) {
		if (value.id == group_id) {
			html += '<option value="' + value.id + ',' + user_id + '" selected="selected" >' + value.name + '</option> ';
		} else {
			html += '<option value="' + value.id + ',' + user_id + '">'	+ value.name + '</option> ';
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
		"user_id" : id,
		"group_id" : group_id
	};
	$.ajax({
		url : domain + '/admin/user/updateUser.action',
		cache : false,
		data : data,
		dataType : "json",
		success : function(res) {
			if (res && "1" === res.code) {
				fjx.showMsg('修改分组成功');
				// $("com"+id).combobox("select",record.value);
				datagrid.datagrid("reload");
			} else {
				$.messager.alert('提示', res ? res.msg : '修改分组失败！','error');
			}
		}
	});
}
/**
 * 打开消息管理界面
 * @param {} openid
 */
function viewLog(openid){
	parent.app.loadingModal();
	var href = domain+'/admin/msglog?openid='+openid;
	parent.addTab({
		src : href,
		title : '消息管理'
	});
}