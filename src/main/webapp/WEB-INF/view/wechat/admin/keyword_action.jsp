<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>关键字回复</title>
	<link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<ol class="breadcrumb">
			<li>微信管理</li>
			<li class="active">关键字回复</li>
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
	</div>

<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/keyword-action-jqgrid.js?v=2015060701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>

