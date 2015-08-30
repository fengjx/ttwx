/**
 * 关键字回复管理
 */
 
var viewDialog;
var $table;

$(function(){

	//消息预览dialog
	viewDialog = $('#viewDialog').modal({
		keyboard : true,
		show : false
	});

	$table = $('#data-table').bootstrapTable({
		method: 'post',
		toolbar: "#toolbar",
		contentType: "application/x-www-form-urlencoded",
		url: adminPath + '/wechat/action/pageList',
		queryParamsType: "limit",
		queryParams: queryParams,
		cache: false,
		height: 'auto',
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
		},{
			field: 'key_word',
			title: '关键字',
			align: 'left',
			valign: 'middle'
		},{
			field: 'dict_name',
			title: '动作响应类型',
			align: 'left',
			width: '20%',
			valign: 'middle',
			formatter: function (value,row,index) {
				return value;
			}
		},{
			field: 'in_time',
			title: '编辑时间',
			align: 'left',
			width: '15%',
			valign: 'middle'
		},{
			field: 'op',
			title: '操作',
			width: '12%',
			align: 'center',
			valign: 'middle',
			formatter : function(value, row, index) {
				var html = '<a class="btn btn-xs btn-success" onclick="view(\''+index+'\');" href="javascript:void(0);"><i class="glyphicon glyphicon-eye-open"></i></a>';
				html += '<a class="btn btn-xs btn-info" href="'+adminPath+'/wechat/action/keywordAdd?id='+row.id+'"><i class="glyphicon glyphicon-edit"></i></a>';
				html += '<a class="btn btn-xs btn-danger" onclick="deleteMsgAction(\''+row.id+'\',\''+row.key_word+'\');" href="javascript:void(0);"><i class="glyphicon glyphicon-remove"></i></a>';
				return html;
			}
		}]
	});

	function queryParams(params) {
		var start_time = $('#toolbar input[name="start_time"]').val();
		var end_time = $('#toolbar input[name="end_time"]').val();
		params = $.extend(params,{
			"req_type": "text",
			"key_word": $('#toolbar input[name="qry_key_word"]').val(),
			"start_time": start_time,
			"end_time": end_time
		});
		return params;
	}

});

function searchDatagrid() {
	$table.bootstrapTable('refresh');
}

function clearDatagrid() {
	$('#toolbar input').val('');
	$table.bootstrapTable('refresh');
}

function deleteMsgAction(id, keywords){
	app.confirmModal("你要删除关键字【"+keywords+"】的响应规则吗？", function () {
		$.ajax({
			url :  adminPath + '/wechat/action/delete',
			data : 'id='+id,
			cache : false,
			dataType : "json",
			success : function(res) {
				if(res && '1' === res.code){
					app.alertModal('刪除成功');
					$table.bootstrapTable('refresh');
				}else{
					app.alertModal(res.msg?res.msg:'刪除失败');
				}
			}
		});
	});
}


/**
 * 预览
 * @param {} id
 * @return {Boolean}
 */
function view (index){
	$table.bootstrapTable('uncheckAll');
	$table.bootstrapTable('check', index);
	var row = $table.bootstrapTable('getSelections')[0];

	var viewHtml = "";		//预览效果HTML
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
			viewHtml = "配置的接口已经失效，请重新配置";
		}
	}
	app.alertModal(viewHtml,{
		title:"用户发送文字消息【"+row.key_word+"】将收到以下内容",
		height:"auto",
		width:300
	});
}
