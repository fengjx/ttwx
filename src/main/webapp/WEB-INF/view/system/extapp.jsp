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

		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
<script src="<%=resourceUrl %>/script/wechat/admin/setting.js?v=20141005" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
</body>
</html>





