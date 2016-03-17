<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>留言反馈</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>门户</li>
			<li class="active">留言反馈</li>
		</ol>
	</div>
	<div class="page-content">
		<div id="toolbar">
			<div class="form-inline" role="form">
				<fieldset>
					<div class="form-group">
						<div class="control-group">
							<label class="control-label">姓名：</label>
							<input name="qry_name" class="form-control" type="text" placeholder="模糊查询">
							<label class="control-label">邮箱：</label>
							<input name="qry_email" class="form-control" type="text" placeholder="模糊查询">
							<label class="control-label">QQ：</label>
							<input name="qry_qq" class="form-control" type="text" placeholder="模糊查询">
							<label class="control-label">电话：</label>
							<input name="qry_phone" class="form-control" type="text" placeholder="模糊查询">
							<span class="columns-right pull-right">
								<button id="btn-qry" type="button" class="btn btn-white btn-primary">
									<i class="icon-search"></i>
									查询
								</button>
								<button id="btn-reset" type="button" class="btn btn-white">
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

	</div>

<script src="${resourceUrl}/script/portal/admin/guestbook.js?v=2015090602" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
