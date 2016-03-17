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

	$table = $('#data-table').jqGrid({
		url: adminPath + '/wechat/action/pageList?req_type=text',
		colModel: [{
			name: 'id',
			hidden: true,
			key: true
		}, {
			name: 'key_word',
			label: '关键字',
			align: 'left',
			sortable: false
		}, {
			name: 'fuzzy',
			label: '匹配方式',
			sortable: false,
			align: 'left',
			width:60,
			formatter: function (value, opt, row) {
				return app.getDictName("keywordFuzzy", value);
			},
			unformat: function (cellValue, options, cellObject) {
				return app.getDictVal("keywordFuzzy", cellValue);
			}
		}, {
			name: 'action_type',
			label: '动作响应类型',
			sortable: false,
			align: 'left',
			formatter: function (value, opt, row) {
				return app.getDictName("action_type", value);
			},
			unformat: function (cellValue, options, cellObject) {
				return app.getDictVal("action_type", cellValue);
			}
		}, {
			name: 'order_no',
			label: '优先级',
			align: 'left',
			width:40,
			sortable: false
		}, {
			name: 'in_time',
			label: '编辑时间',
			align: 'left',
			sortable: false
		}, {
			name: 'op',
			label: '操作',
			align: 'center',
			sortable: false,
			formatter: function (value, opt, row) {
				var html = '<a class="btn btn-minier btn-success" onclick="view(\''+row.id+'\');" href="javascript:void(0);"><i class="glyphicon glyphicon-eye-open"></i></a>';
				html += '<a class="btn btn-minier btn-info" href="'+adminPath+'/wechat/action/keywordAdd?id='+row.id+'"><i class="glyphicon glyphicon-edit"></i></a>';
				html += '<a class="btn btn-minier btn-danger" onclick="deleteData(\''+row.id+'\',\''+row.key_word+'\');" href="javascript:void(0);"><i class="glyphicon glyphicon-remove"></i></a>';
				return html;
			}
		},{
			name: 'action_type',
			hidden: true
		},{
			name: 'material_id',
			hidden: true
		},{
			name: 'app_name',
			hidden: true
		},{
			name: 'xml_data',
			hidden: true,
			formatter: function (value, opt, row) {
				return $.jgrid.htmlEncode(value);
			},
		}],
		serializeGridData: function (postData) {
			var start_time = $('#toolbar input[name="start_time"]').val();
			var end_time = $('#toolbar input[name="end_time"]').val();
			postData = $.extend(postData,{
				"req_type": "text",
				"key_word": $('#toolbar input[name="qry_key_word"]').val(),
				"start_time": start_time,
				"end_time": end_time
			});
			return postData;
		}
	});

	$table.jqGrid('navGrid', '#tablePager', {
		edit: false,
		add: false,
		del: false,
		search: false,
		refresh: true,
		view: false,
		position: "left",
		cloneToTop: true
	}).jqGrid('navButtonAdd', '#tablePager',
		{
			buttonicon: "glyphicon glyphicon-remove",
			title: "remove",
			caption: "",
			position: "first",
			onClickButton: function () {
				deleteData();
			}
		}
	).jqGrid('navButtonAdd', '#tablePager',
		{
			buttonicon: "glyphicon glyphicon-eye-open",
			title: "view",
			caption: "",
			position: "first",
			onClickButton: function () {
				view();
			}
		}
	).jqGrid('navButtonAdd', '#tablePager',
		{
			buttonicon: "glyphicon glyphicon-plus",
			title: "add",
			caption: "",
			position: "first",
			onClickButton: function () {
				window.location.href = adminPath + "/wechat/action/keywordAdd";
			}
		}
	);

});

function searchDatagrid() {
	$table.trigger("reloadGrid");
}

function clearDatagrid() {
	$('#toolbar input').val('');
	$table.trigger("reloadGrid");
}

function deleteData(id, keyword){
	if (!id) {
		id = $table.jqGrid('getGridParam', "selrow");
		if (!id) {
			app.alertModal("请选中要删除的数据");
			return false;
		}
		keyword = $table.jqGrid('getRowData', id).key_word;
	}
	app.confirmModal("你要删除关键字【"+keyword+"】的响应规则吗？", function () {
		$.ajax({
			url :  adminPath + '/wechat/action/delete',
			data : 'id='+id,
			cache : false,
			dataType : "json",
			success : function(res) {
				if(res && '1' === res.code){
					app.ok('刪除成功');
					$table.trigger("reloadGrid");
				}else{
					app.error(res.msg?res.msg:'刪除失败');
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
function view (id){
	if (!id) {
		id = $table.jqGrid('getGridParam', "selrow");
		if (!id) {
			app.alertModal("请选中要预览的数据");
			return false;
		}
	}
	var row = $table.jqGrid('getRowData', id);
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
