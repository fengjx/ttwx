<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="sys"/>
	<title>字典管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="${adminPath}">后台管理</a></li>
			<li><a href="${adminPath}/sys">系统管理</a></li>
			<li class="active">字典管理</li>
		</ol>
	</div>
	<div class="page-content">
		<div id="toolbar">
			<div class="form-inline" role="form">
				<fieldset>
					<div class="form-group">
						<div class="control-group">
							<label class="control-label">字典组：</label>
							<input name="qry_openid" class="form-control" type="text">
							<label class="control-label">字典描述：</label>
							<input name="desc" class="form-control" type="text" placeholder="字典描述">
							<span class="columns-right pull-right">
								<button onclick="searchDatagrid();" type="button" class="btn btn-white btn-primary">
									<i class="glyphicon glyphicon-search"></i>
									查询
								</button>
								<button onclick="clearDatagrid();" type="button" class="btn btn-white">
									<i class="glyphicon glyphicon-transfer"></i>
									重置
								</button>
							</span>
						</div>
					</div>
				</fieldset>
				<a onclick="edit();" class="btn btn-info btn-xs" href="javascript:void(-1);">
					<i class="glyphicon glyphicon-plus"></i>
					添加
				</a>
				<a class="btn btn-warning btn-xs" onclick="searchDatagrid();" href="javascript:void(0);">
					<i class="glyphicon glyphicon-refresh"></i>
					刷新
				</a>
				<a class="btn btn-default btn-xs" onclick="$table.bootstrapTable('uncheckAll');" href="javascript:void(0);">
					<i class="glyphicon glyphicon-ban-circle"></i>
					取消选中
				</a>

			</div>
		</div>
		<table id="data-table"></table>
		<div id="tablePager"></div>

		<div id="editModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">字典编辑</h4>
					</div>
					<form action="${adminPath}/sys/dict/save" class="form-horizontal" id="form-dict" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="${wechatAccount.id }" />
							<div class="control-group" >
								<label class="control-label" for="dict_value">字典值:</label>
								<div class="controls">
									<input id="dict_value" name="dict_value" value="" type="text" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="dict_name">字典名称:</label>
								<div class="controls">
									<input id="dict_name" name="dict_name" value="" type="text" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="dict_desc">字典描述:</label>
								<div class="controls">
									<input id="dict_desc" name="dict_desc" value="" type="text" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="group_code">字典组标识:</label>
								<div class="controls">
									<input id="group_code" name="group_code" value="" type="text" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="group_name">字典组名称:</label>
								<div class="controls">
									<input id="group_name" name="group_name" value="" type="text" class="span4 form-control">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="order_num">排序:</label>
								<div class="controls">
									<input id="order_num" name="order_num" value="" type="number" class="span4 form-control">
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
							<button type="submit" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
						</div>
					</form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div>

<script src="${resourceUrl}/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.formautofill.min.js" type="text/javascript" charset="UTF-8"></script>
<c:choose>
	<c:when test="${'JqGridPage' eq adapterPageName}">
		<link href="${resourceUrl}/jqGrid/css/ui.jqgrid-bootstrap.css" rel="stylesheet" type="text/css"/>
		<script src="${resourceUrl}/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${resourceUrl}/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${resourceUrl}/script/sys/dict-jqgrid.js?v=2015072601" type="text/javascript" charset="UTF-8"></script>
	</c:when>
	<c:otherwise>
		<link href="${resourceUrl}/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
		<script src="${resourceUrl}/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${resourceUrl}/bootstrap-table/bootstrap-table-option.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${resourceUrl}/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${resourceUrl}/script/sys/dict.js?v=2015072601" type="text/javascript" charset="UTF-8"></script>
	</c:otherwise>
</c:choose>
</body>
</html>
