<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>编辑菜单</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>系统管理</li>
			<li class="active">${not empty id?'修改':'添加'}角色</li>
		</ol>
	</div>
	<div class="page-content">
		<ul class="nav nav-tabs">
			<li><a href="${adminPath}/sys/role">角色列表</a></li>
			<li class="active"><a href="${adminPath}/sys/role/form">${not empty id?'修改':'添加'}角色</a></li>
		</ul>
		<form class="form-horizontal" id="data-form" method="POST" role="form">
			<div class="modal-body">
				<input type="hidden" id="id" name="id" value="${id}" />
				<div class="control-group" >
					<label class="control-label" for="name">角色名称:</label>
					<div class="controls">
						<input id="oldName" name="oldName" value="${name}" type="hidden"/>
						<input id="name" name="name" value="${name}" type="text" required class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="role_code">角色标示:</label>
					<div class="controls">
						<input id="oldRoleCode" name="oldRoleCode" value="${role_code}" type="hidden"/>
						<input id="role_code" name="role_code" value="${role_code}" required type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">系统角色:</label>
					<div class="controls">
						<myform:radios name="is_sys" values="${fns:getDictList('yesNo')}" checked="${is_sys}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否启用:</label>
					<div class="controls">
						<myform:radios name="is_valid" values="${fns:getDictList('yesNo')}" checked="${is_valid}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">角色授权:</label>
					<div class="controls">
						<input type="hidden" id="menuIds" name="menuIds" value="${menuIds}">
						<div id="menuTree" class="ztree"></div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remarks">备注:</label>
					<div class="controls">
						<textarea id="remarks" name="remarks" class="span4 form-control" rows="3">${remarks}</textarea>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
<script src="${resourceUrl}/script/sys/sys.role.form.js?v=2015101701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
