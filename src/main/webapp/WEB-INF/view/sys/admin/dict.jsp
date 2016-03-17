<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>字典管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>系统管理</li>
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
							<input name="qry_group_code" class="form-control" type="text" placeholder="group_code">
							<label class="control-label">字典描述：</label>
							<input name="qry_dict_desc" class="form-control" type="text" placeholder="字典描述">
							<label class="control-label">启用：</label>
							<select name="qry_is_valid" id="qry_is_valid">
								<option value="">全部</option>
								<myform:options items="${fns:getDictList('yesNo')}" itemValue="value" itemLabel="label"></myform:options>
							</select>
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

		<div id="editModal" class="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">字典编辑</h4>
					</div>
					<form action="${adminPath}/sys/dict/save" class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="" />
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
							<button type="submit" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
						</div>
					</form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div>

<script src="${resourceUrl}/script/sys/dict-jqgrid.js?v=2015090602" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
