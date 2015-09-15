<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>选择应用</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</head>
<body class="no-skin">
	<div class="main-container">
		<div class="main-content">
			<div id="context" class="main-content-inner">
				<div id="toolbar">
					<div class="form-inline" role="form">
						<fieldset>
							<div class="form-group">
								<div class="control-group">
									<label class="control-label">应用名称：</label>
									<input name="qry_name" class="form-control" type="text" placeholder="模糊匹配">
									<label class="control-label">入库时间：</label>
									<div class="input-group">
										<input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="start_time" type="text" style="width: 120px;" />
										<div class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</div>
									</div>
									<label class="control-label">-</label>
									<div class="input-group">
										<input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="end_time" type="text" style="width: 120px;" />
										<div class="input-group-addon">
											<i class="glyphicon glyphicon-calendar"></i>
										</div>
									</div>
						<span class="columns-right pull-right">
							<button id="extapp-btn-qry" type="button" class="btn btn-white btn-primary">
								<i class="icon-search"></i>
								查询
							</button>
							<button id="extapp-btn-reset" type="button" class="btn btn-white">
								<i class="icon-circle-blank"></i>
								重置
							</button>
						</span>
								</div>
							</div>
						</fieldset>
					</div>
				</div>
				<table id="data-table"></table>
				<div id="tablePager"></div>
				<div style="margin-bottom: 100px;"></div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(function () {
		var _app = top.app;
		var $table;
		var appType = '${appType}';

		$(".btn-select").on("#context", function () {
			var id = $(this).attr("data-id");
			var row = $table.jqGrid('getRowData', id);
			var res;
			if('web' == appType){
				$("#label-${id}").val(row.app_url);
				res = "web:"+row.app_url
			}else if('api' == appType){
				$("#label-${id}").val(row.name);
				res = "api:"+row.app_url
			}
			dialog.close(res);  // 关闭（隐藏）对话框
			dialog.remove();	// 主动销毁对话框
			return false;
		});

		$table = $('#data-table').jqGrid({
			url: adminPath + '/sys/ext/pageList',
			colModel: [{
				name: 'id',
				hidden: true,
				key: true
			}, {
				name: 'name',
				label: '应用名称',
				sortable: false,
				align: 'left'
			}, {
				name: 'app_type',
				label: '应用类型',
				sortable: false,
				align: 'left',
				formatter: function (value, opt, row) {
					return app.getDictName("app_type", value);
				},
				unformat: function (cellValue, options, cellObject) {
					return app.getDictVal("app_type", cellValue);
				}
			}, {
				name: 'description',
				label: '接口描述',
				sortable: false,
				width: 183,
				align: 'left'
			}, {
				name: 'order_no',
				label: '排序',
				sortable: false,
				width: 40,
				align: 'left'
			}, {
				name: 'is_valid',
				label: '是否启用',
				sortable: false,
				align: 'left',
				width: 80,
				formatter: function (value, opt, row) {
					return app.getDictName("yesNo", value);
				},
				unformat: function (cellValue, options, cellObject) {
					return app.getDictVal("yesNo", cellValue);
				}
			}, {
				name: 'in_time',
				label: '入库时间',
				align: 'left'
			}, {
				name: 'op',
				label: '操作',
				align: 'center',
				sortable: false,
				formatter: function (value, opt, row) {
					var html = '<a data-id="'+row.id+'" class="btn-select btn btn-minier btn-success" href="javascript:void(0);"><i class="ace-icon glyphicon glyphicon-ok"></i></a>';
					return html;
				}
			}, {
				name: 'msg_types',
				align: 'left',
				hidden: true
			}, {
				name: 'event_types',
				align: 'left',
				hidden: true
			}, {
				name: 'bean_name',
				align: 'left',
				hidden: true
			}, {
				name: 'app_url',
				align: 'left',
				hidden: true
			}, {
				name: 'restful_url',
				align: 'left',
				hidden: true
			}],
			serializeGridData: function (postData) {
				var start_time = $('#toolbar input[name="extapp_start_time"]').val();
				var end_time = $('#toolbar input[name="extapp_end_time"]').val();
				postData = $.extend(postData, {
					"name": $('#toolbar input[name="qry_name"]').val(),
					"app_type": appType,
					"msg_type": '${msgType}',
					"event_type": '${eventType}',
					"is_valid": $('#extapp_is_valid').val(),
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
		});

		$("#extapp-btn-qry").click(function () {
			$table.setGridParam({page: 1}).trigger("reloadGrid");
		});

		$("#extapp-btn-reset").click(function () {
			$('#toolbar input').val('');
			$('#qry_is_valid').val('');
			$('#qry_app_type').val('');
			$table.trigger("reloadGrid");
		});

	});

</script>
</body>
</html>
