<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>菜单管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>系统管理</li>
			<li class="active">菜单管理</li>
		</ol>
	</div>
	<div class="page-content">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${adminPath}/sys/menu/">菜单列表</a></li>
			<li><a href="${adminPath}/sys/menu/form">菜单添加</a></li>
		</ul>
		</br>
		<table id="tree-table" class="table table-bordered table-striped table-condensed">
			<colgroup>
				<col width="15%"/>
				<col width="4%"/>
				<col width="30%"/>
				<col width="10%"/>
				<col width="5%"/>
				<col width="5%"/>
				<col width="15%"/>
				<col width="6%"/>
				<col width="10%"/>
			</colgroup>
			<thead>
				<tr>
					<th>菜单名称</th>
					<th>图标</th>
					<th>链接</th>
					<th>权限标识</th>
					<th>显示</th>
					<th>排序</th>
					<th>更新时间</th>
					<th>是否启用</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${treeTable}" var="item">
				<tr class="treegrid-${item.id} <c:if test="${item.parent_id ne null}">treegrid-parent-${item.parent_id}</c:if> ">
					<td>${item.name}</td>
					<td><i class="${item.icon}"></i></td>
					<td>${item.url}</td>
					<td>${fns:abbr(item.permission, 30)}</td>
					<td>${fns:getDictLabel(item.is_show, "yesNo")}</td>
					<td>${item.order_no}</td>
					<td>${item.update_time}</td>
					<td>${fns:getDictLabel(item.is_valid, "yesNo")}</td>
					<td>
						<a class="btn btn-info btn-minier" href="${adminPath}/sys/menu/form?id=${item.id}">
							<i class="glyphicon glyphicon-edit"></i>
						</a>
						<a name="btn-remove" class="btn btn-danger btn-minier" href="javascript:void(-1);" data-id="${item.id}" data-name="${item.name}">
							<i class="glyphicon glyphicon-remove"></i>
						</a>
						<a class="btn btn-success btn-minier" href="${adminPath}/sys/menu/form?parentId=${item.id}">
							<i class="glyphicon glyphicon-plus"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

		<div id="editModal" class="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">编辑菜单</h4>
					</div>
					<form action="${adminPath}/sys/dict/save" class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="" />
							<div class="control-group" >
								<label class="control-label" for="name">菜单名称:</label>
								<div class="controls">
									<sys:treeselect id="parent_id" name="parent_id" value="" url="${adminPath}/sys/menu/treeNode" />
								</div>
							</div>
							<div class="control-group" >
								<label class="control-label" for="name">菜单名称:</label>
								<div class="controls">
									<input id="name" name="name" value="" type="text" class="span4 form-control">
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

<script src="${resourceUrl}/script/sys/sys.menu.js?v=2015101701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
