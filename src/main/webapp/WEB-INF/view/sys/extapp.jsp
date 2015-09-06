<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="sys"/>
	<title>接口管理</title>
	<link href="${resourceUrl}/jqGrid/css/ui.jqgrid-bootstrap.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="${adminPath}">后台管理</a></li>
			<li><a href="${adminPath}/sys">系统管理</a></li>
			<li class="active">接口配置</li>
		</ol>
	</div>

	<div class="page-content">
		<div id="toolbar">
			<div class="form-inline" role="form" style="width: 1000px;">
				<fieldset>
					<div class="form-group">
						<div class="control-group">
							<label class="control-label">关键字：</label>
							<input name="qry_key_word" class="form-control" type="text" placeholder="用户发送的文字">
							<label class="control-label">发送时间：</label>
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
								<button onclick="searchDatagrid();" type="button" class="btn btn-white btn-primary">
									<i class="icon-search"></i>
									查询
								</button>
								<button onclick="clearDatagrid();" type="button" class="btn btn-white">
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

		<div id="editModal" class="modal" tabindex="-1" role="dialog" >
			<div class="modal-dialog" style="width: 800px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">应用编辑</h4>
					</div>
					<form action="${adminPath}/sys/dict/save" class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="" />
							<div class="control-group" >
								<label class="control-label">应用类型：</label>
								<div class="controls">
									<select data-type="dict" data-group="app_type" id="app_type" data-default="web" name="app_type" class="app-element"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="name">应用名称：</label>
								<div class="controls">
									<input type="text" id="name" name="name" class="span4 form-control"/>
								</div>
							</div>
							<div name="web" class="control-group">
								<label class="control-label" for="app_url">链接：</label>
								<div class="controls">
									<input id="app_url" name="app_url" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="restful" class="control-group hide">
								<label class="control-label">resful url：</label>
								<div class="controls">
									<input id="restful_url" name="restful_url" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="api" class="control-group hide">
								<label class="control-label">spring id：</label>
								<div class="controls">
									<input name="bean_name" id="bean_name" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="api" class="control-group hide">
								<label class="control-label">消息类型：</label>
								<div id="msgType" class="controls">
									<input type="checkbox" data-type="dict" data-group="req_type" class="app-element"/>
								</div>
							</div>
							<div name="api" class="control-group hide">
								<label class="control-label">事件类型：</label>
								<div id="eventType" class="controls">
									<input type="checkbox" data-type="dict" data-group="event_type" class="app-element"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">应用说明：</label>
								<div class="controls">
									<textarea id="description" name="description"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="order_no">排序:</label>
								<div class="controls">
									<input id="order_no" name="order_no" value="" type="number" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="is_valid">是否启用:</label>
								<div class="controls">
									<input id="is_valid" name="is_valid" value="1" type="checkbox">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
							<button type="button" class="btn btn-primary btn-sm" data-loading-text="正在提交..." onclick="submitData();">保存</button>
						</div>
					</form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div>

<script src="${resourceUrl}/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/jqGrid/grid-opt.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.formautofill.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/sys/extapp.js?v=2015090501" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
