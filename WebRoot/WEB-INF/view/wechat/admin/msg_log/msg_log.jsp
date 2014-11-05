<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>微信消息管理</title>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
			<fieldset>
				<table class="tableForm">
					<tr>
						<th>关键字</th>
						<td>
							<input type="text" name="paramMap.key_word" style="width: 150px;" />
						</td>
						<td>消息类型</td>
						<td width="152px">
							<input id="resType" name="resType" style="width: 150px;"/>
						</td>
						<td>事件类型</td>
						<td width="152px">
							<input id="eventType" name="eventType" style="width: 150px;"/>
						</td>
					</tr>
					<tr>
						<th>OPENID</th>
						<td>
							<input type="text" id="openid" name="from_user_name" style="width: 150px;" value="${openid}" />
						</td>
						<td>发送时间</td>
						<td >
							<!-- <input type="text" name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" /> -->
							<input class="Wdate" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="start_time" type="text" style="width: 148px;" />
						</td>
						<th>---------</th>
						<td >
							<!-- <input type="text" name="paramMap.end_time" class="easyui-datetimebox" editable="false" editable="false" style="width: 150px;" /> -->
							<input class="Wdate" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="end_time" type="text" style="width: 148px;" />
						</td>
						<td colspan="2" align="right">
							<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">清空</a>
						</td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('reload');" plain="true" href="javascript:void(-1);">刷新</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(-1);">取消選中</a>
			</div>
		</div>
		<table id="datagrid"></table>
	</div>

	<div id="viewDialog" style="display: none;overflow: hidden;top:50px; min-height:200px; width: 400px;">
		<div id="viewDiv">
			<!-- js加载预览效果 -->
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
    <script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
    <script src="<%=resourceUrl%>/script/admin/msg_log.js?v=2014110102" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
</body>
</html> 