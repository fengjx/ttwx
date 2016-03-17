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
			<li class="active">${not empty id?'修改':'添加'}菜单</li>
		</ol>
	</div>
	<div class="page-content">
		<ul class="nav nav-tabs">
			<li><a href="${adminPath}/sys/menu/">菜单列表</a></li>
			<li class="active"><a href="${adminPath}/sys/menu/form">${not empty id?'修改':'添加'}菜单</a></li>
		</ul>
		<form class="form-horizontal" id="data-form" method="POST" role="form">
			<input type="hidden" id="id" name="id" value="${id}" />
			<div class="modal-body">
				<div class="control-group" >
					<label class="control-label" for="parent_id">父级菜单:</label>
					<div class="controls">
						<sys:treebox id="parent_id" name="parent_id" value="${parent_id}" parentIds="${parent_ids}" labelValue="${parent_name}" treeLevel="${parent_level}" url="${adminPath}/sys/menu/treeNode" allowClear="true" />
					</div>
				</div>
				<div class="control-group" >
					<label class="control-label" for="name">菜单名称:</label>
					<div class="controls">
						<input id="name" name="name" value="${name}" type="text" required class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="url">链接:</label>
					<div class="controls">
						<input id="url" name="url" value="${url}" type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="permission">权限标示:</label>
					<div class="controls">
						<input id="permission" name="permission" value="${permission}" type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="icon">图标:</label>
					<div class="controls">
						<input id="icon" name="icon" value="${icon}" type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="order_no">排序:</label>
					<div class="controls">
						<input id="order_no" name="order_no" value="${order_no}" required type="number" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否显示:</label>
					<div class="controls">
						<myform:radios name="is_show" values="${fns:getDictList('yesNo')}" checked="${is_show}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否启用:</label>
					<div class="controls">
						<myform:radios name="is_valid" values="${fns:getDictList('yesNo')}" checked="${is_valid}"/>
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
<script src="${resourceUrl}/script/sys/sys.menu.form.js?v=2015101701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
