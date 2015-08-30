/**
 * 微信用户管理
 */
// 用户列表数据
var datagrid;
var $table;
// 分组表单Dialog
var groupDialog;
// 用户分组数据
var groupData;
// 当前选择的用户分组
var curGroupId;
$(function() {
	// 加载用户分组
	loadGroupList();

	$table = $('#data-table').bootstrapTable({
		method: 'post',
		toolbar: "#toolbar",
		contentType: "application/x-www-form-urlencoded",
		url: adminPath + '/wechat/user/userPageList',
		queryParamsType: "limit",
		queryParams: queryParams,
		cache: false,
		striped: true,
		sidePagination: "server",
		pagination: true,
		pageSize: 10,
		pageList: [10, 15, 20],
		minimumCountColumns: 2,
		clickToSelect: true,
		idField:"id",
		columns: [{
			field: 'id',
			valign: 'middle',
			radio:true
		}, {
			field: 'openid',
			title: 'openid',
			align: 'left',
			valign: 'middle'
		}, {
			field: 'nickname',
			title: '昵称',
			align: 'left',
			valign: 'middle'
		}, {
			field: 'sex',
			title: '性别',
			align: 'left',
			valign: 'middle'
		}, {
			field: 'headimgurl',
			title: '头像',
			align: 'left',
			valign: 'middle'
		}, {
			field: 'group_id',
			title: '分组',
			align: 'left',
			valign: 'middle',
			formatter: function (value,row,index) {
				return createGroupCombobox(value);
			}
		}, {
			field: 'unsubscribe_time',
			title: '取消关注时间',
			align: 'left',
			valign: 'middle'
		}, {
			field: 'op',
			title: '操作',
			align: 'center',
			valign: 'middle',
			formatter: function (value,row,index) {
				///admin/wechat/msglog?openid
				var html = '<a class="btn btn-info btn-xs" href="'+adminPath+'/wechat/msglog?openid='+row.openid+'">消息记录</a>';
				return html;
			}
		}]
	});

	function queryParams(params) {
		var start_time = $('#qry-toolbar input[name="start_time"]').val();
		var end_time = $('#qry-toolbar input[name="end_time"]').val();
		params = $.extend(params,{
			"key_word": $('#qry-toolbar input[name="qry_key_word"]').val(),
			"start_time": start_time,
			"end_time": end_time,
			"req_type": $("#qry_req_type").val(),
			"event_type": $("#qry_event_type").val(),
			"from_user_name": $("#qry-toolbar input[name='qry_openid']").val()
		});
		return params;
	}

	//$(".inner_menu_link").live('click', function() {
	//	var groupId = $(this).attr("data-id");
	//	$(".inner_menu_item").removeClass("selected");
	//	$(this).parent().addClass("selected");
	//	curGroupId = groupId;
	//	searchDatagrid();
	//});
    //

	// 修改分组名称
	$(".inner_menu").on('click',".edit_gray", function() {
		editGroup($(this).attr("data-gid"),$(this).attr("data-gname"));
	});
    //
	// 刪除分組
	$(".inner_menu").on("click", ".del_gray", function() {
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
		url : adminPath + '/wechat/user/groupList.action',
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
				app.alertModal("用户分组加载失败");
			}
		}
	});
}

/**
 * 删除分组
 * @param groupId
 * @param groupName
 */
function deleteGroup(groupId, groupName) {
	app.confirmModal('确认要删除分组【' + groupName + '】吗', function () {
		$.ajax({
			url : adminPath + '/wechat/user/deleteGroup',
			data : 'id=' + groupId,
			dataType : "json",
			success : function(data) {
				if (data && '1' == data.code) {
					app.ok("删除成功");
					loadGroupList();
				} else {
					app.error(res.msg ? res.msg : '刪除失败');
				}
			}
		});
	});
}

/**
 * 添加分组
 */
function editGroup(id,name) {
	var title = "添加分組";
	if(id){
		title = "修改分组";
	}
	app.promptModal(title, function (val) {
		if (!val) {
			alert("请输入分组名称");
			return false;
		}
		$.ajax({
			url: adminPath + '/wechat/user/saveGroup',
			type: 'post',
			data: {
				id: id,
				name: val
			},
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				if (data && '1' == data.code) {
					loadGroupList();
					app.closeDialog();
				} else {
					alert(data.msg ? data.msg : "保存失败");
				}
			}
		});
		return false;
	},{defaultVal:name});
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
		url : adminPath + '/user/updateUser.action',
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
	app.loadingModal();
	var href = adminPath + '/wechat/msglog?openid='+openid;
	parent.addTab({
		src : href,
		title : '消息管理'
	});
}