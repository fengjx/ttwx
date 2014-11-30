<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>扩展接口配置</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<script src="<%=resourceUrl %>/script/admin/busiapp.js?v=20141117" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
	<style>
		input[type=text]{
			width: 150px;
		}
	</style>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" border="false" style="overflow: hidden;">
		<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
			<fieldset>
				<table class="tableForm">
					<tr>
						<td>插件类型</td>
						<td>
							<input type="text" id="qry_app_type" name="qry_app_type">
						</td>
						<td>接口名称</td>
						<td>
							<input type="text" id="qry_app_name" name="qry_app_name" value="" />
						</td>
						<td>接入时间</td>
						<td colspan="2">
							<!-- <input name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />-<input name="paramMap.end_time" class="easyui-datetimebox" editable="false" style="width: 150px;" /> -->
							<input class="Wdate" onClick="WdatePicker()" name="start_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" style="width: 148px;" />
							-
							<input class="Wdate" onClick="WdatePicker()" name="end_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" style="width: 148px;" />
							
						</td>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a>
							<a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">重置</a>
						</td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="appApp();" plain="true" href="javascript:void(0);">添加</a>
				<%--<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteApp();" plain="true" href="javascript:void(0);">刪除</a>--%>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('load');" plain="true" href="javascript:void(0);">刷新</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid">
			<!-- js加载 -->
		</table>
	</div>

	<div id="appDialog" style="display: none;overflow: hidden;width:450px;">
		<form id="appForm" method="post">
			<table class="tableForm">
				<input type="hidden" id="id" name="id" value=""/>
				<tr>
					<td>应用类型：</td>
					<td>
						<input type="text" id="app_type" name="app_type">
					</td>
					<td>应用名称：</td>
					<td>
						<input id="name" name="name" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr name="web" style="display: none;">
					<td>URL：</td>
					<td colspan="3">
						<input id="app_url" name="app_url" class="easyui-validatebox" required="true" style="width: 350px;" />
					</td>
				</tr>
				<tr name="restful" style="display: none;">
					<td>resful url：</td>
					<td colspan="3">
						<input id="restful_url" name="restful_url" class="easyui-validatebox" required="true" style="width: 350px;" />
					</td>
				</tr>

				<tr name="api" style="display: none;">
					<td>spring id：</td>
					<td>
						<input name="bean_name" class="easyui-validatebox" required="true" />
					</td>
					<td>method name</td>
					<td>
						<input name="method_name" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr name="api" style="display: none;">
					<td>消息类型：</td>
					<td id="msgType">
					</td>
					<td>事件类型：</td>
					<td id="eventType">
					</td>
				</tr>
				<tr>
					<td>应用说明：</td>
					<td>
						<textarea id="description" name="description"></textarea>
					</td>
					<td>是否启用</td>
					<td>
						<input id="is_valid" name="is_valid" type="checkbox" value="1">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html> 