<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="sys"/>
	<title>角色管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="${adminPath}">后台管理</a></li>
			<li><a href="${adminPath}/sys">系统管理</a></li>
			<li class="active">角色管理</li>
		</ol>
	</div>
	<div class="page-content">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${adminPath}/sys/role/">角色列表</a></li>
			<li><a href="${adminPath}/sys/role/form">角色添加</a></li>
		</ul>
		<table id="data-table"></table>
		<div id="tablePager"></div>



		<div id="assignModal" class="modal" tabindex="-1" role="dialog" >
			<div class="modal-dialog" style="width: 600px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">角色分配</h4>
					</div>
					<form action="${adminPath}/sys/user/save" class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="" />
							<div class="control-group">
								<label class="control-label" for="name">用户名：</label>
								<div class="controls">
									<input type="text" id="name" name="name" class="span4 form-control"/>
								</div>
							</div>
							<div name="web" class="control-group">
								<label class="control-label" for="email">邮箱：</label>
								<div class="controls">
									<input id="email" name="mail" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="api" class="control-group">
								<label class="control-label" for="phone">电话：</label>
								<div class="controls">
									<input name="phone" id="phone" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="api" class="control-group">
								<label class="control-label" for="score">积分：</label>
								<div class="controls">
									<input name="score" id="score" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">是否启用:</label>
								<div class="controls">
									<myform:radios name="is_valid" values="${fns:getDictList('yesNo')}" checked="${is_valid}"/>
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

<script src="${resourceUrl}/script/sys/role.js?v=2015103101" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
