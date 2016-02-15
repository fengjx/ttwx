<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>角色管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>系统管理</li>
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
			<div class="modal-dialog" style="width: 600px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">角色分配 - <span id="editRole"></span></h4>
					</div>
					<form class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" name="roleId" value=""/>
							<div class="form-group">
								<div class="col-sm-12">
									<select multiple="multiple" size="10" name="user-list" id="user-list">
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
							<button id="btn-submit" type="button" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
						</div>
					</form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->

	</div>

<script src="${resourceUrl}/script/sys/role.js?v=2015103101" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
