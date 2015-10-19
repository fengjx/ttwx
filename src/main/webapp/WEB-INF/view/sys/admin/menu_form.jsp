<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="sys"/>
	<title>编辑菜单</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="${adminPath}">后台管理</a></li>
			<li><a href="${adminPath}/sys">系统管理</a></li>
			<li class="active">编辑菜单</li>
		</ol>
	</div>
	<div class="page-content">
		<form class="form-horizontal" id="data-form" method="POST" role="form">
			<div class="modal-body">
				<input type="hidden" id="id" name="id" value="" />
				<div class="control-group" >
					<label class="control-label" for="parent_id">父级菜单:</label>
					<div class="controls">
						<sys:treeselect id="parent_id" name="parent_id" value="" url="${adminPath}/sys/menu/treeNode" allowClear="true" />
					</div>
				</div>
				<div class="control-group" >
					<label class="control-label" for="name">菜单名称:</label>
					<div class="controls">
						<input id="name" name="name" value="" type="text" required class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="url">链接:</label>
					<div class="controls">
						<input id="url" name="url" value="" type="text" required class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="permission">权限标示:</label>
					<div class="controls">
						<input id="permission" name="permission" value="" type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="icon">图标:</label>
					<div class="controls">
						<input id="icon" name="icon" value="" type="text" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="order_no">排序:</label>
					<div class="controls">
						<input id="order_no" name="order_no" value="" required type="number" class="span4 form-control">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否启用:</label>
					<div class="controls">
						<div class="radio">
							<label>
								<input name="is_show" value="1" checked="checked" type="radio" class="ace" />
								<span class="lbl">显示</span>
							</label>
							<label>
								<input name="is_show" value="0" type="radio" class="ace" />
								<span class="lbl">隐藏</span>
							</label>
						</div>


				</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="remarks">备注:</label>
					<div class="controls">
						<textarea id="remarks" name="remarks" class="span4 form-control" rows="3"></textarea>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
<script src="${resourceUrl}/script/sys/sys.menu.form.js?v=2015101701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
