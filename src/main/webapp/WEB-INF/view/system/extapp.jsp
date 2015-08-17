<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>授权设置</title>
	<link href="<%=resourceUrl%>/css/setting.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
	var valid_state = '${wechatAccount.valid_state}';
</script>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="/WEB-INF/view/system/inc_menu.jsp">
			<jsp:param name="index" value="0"/>
		</jsp:include>
		<div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
				<li><a href="<%=domain %>/admin">后台管理</a></li>
				<li><a href="<%=domain %>/admin/system">系统管理</a></li>
				<li class="active">接口配置</li>
			</ol>

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
								<label class="control-label">--------------</label>
								<div class="input-group">
									<input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="end_time" type="text" style="width: 120px;" />
									<div class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</div>
								</div>
								<span class="columns-right pull-right">
									<button onclick="searchDatagrid();" type="button" class="btn">
										<i class="glyphicon glyphicon-search"></i>
										查看
									</button>
									<button onclick="clearDatagrid();" type="button" class="btn">
										<i class="glyphicon glyphicon-transfer"></i>
										重置
									</button>
								</span>
							</div>
						</div>
					</fieldset>
					<div>
						<a class="btn btn-default" href="<%=domain%>/admin/wechat/action/keywordAdd">
							<i class="glyphicon glyphicon-plus"></i>
							添加
						</a>
						<a class="btn btn-default" onclick="searchDatagrid();" href="javascript:void(0);">
							<i class="glyphicon glyphicon-refresh"></i>
							刷新
						</a>
						<a class="btn btn-default" onclick="$table.bootstrapTable('uncheckAll');" href="javascript:void(0);">
							<i class="glyphicon glyphicon-check"></i>
							取消选中
						</a>
					</div>
				</div>
			</div>
			<table id="data-table"></table>


		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
<script src="<%=resourceUrl %>/script/system/extapp.js?v=2015072601" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
</body>
</html>





