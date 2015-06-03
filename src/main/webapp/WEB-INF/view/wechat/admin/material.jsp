<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>素材管理</title>
	<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp">
			<jsp:param name="index" value="4"/>
		</jsp:include>
		<div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
				<li><a href="<%=domain %>/admin">">后台管理</a></li>
				<li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
				<li class="active">素材管理</li>
			</ol>
			<div class="main_bd" style="width:99.5%">
				<jsp:include page="/WEB-INF/view/wechat/admin/inc_material_list.jsp">
					<jsp:param name="showType" value="edit"/>
				</jsp:include>
			</div>
		</div>
	</div>
</div>
<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
<link href="<%=resourceUrl%>/css/common.css?v=2014030901" rel="stylesheet" type="text/css"/>
<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/jQuery-Paging/jquery.paging.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/jQuery-Paging/jquery.bootstrap-paging.js?v=2015052801" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
