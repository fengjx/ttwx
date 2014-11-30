<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>系统字典配置</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/script/admin/dict.js?v=20141117" type="text/javascript" charset="UTF-8"></script>
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
						<td>字典分组</td>
						<td width="152px">
							<input type="text" id="dictGroup" name="dictGroup"/>
						</td>
						<td>字典值</td>
						<td colspan="2">
							<input name="dict_value" style="width: 155px;" value="" />
						</td>
						<td>接入时间</td>
						<td colspan="2">
							<!-- <input name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />-<input name="paramMap.end_time" class="easyui-datetimebox" editable="false" style="width: 150px;" /> -->
							<input class="Wdate" onClick="WdatePicker()" name="start_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" />
							-
							<input class="Wdate" onClick="WdatePicker()" name="end_time" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" type="text" />
						</td>
						<td>
							<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a>
							<a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">重置</a>
						</td>
					</tr>
				</table>
			</fieldset>
			<div>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="appDict();" plain="true" href="javascript:void(0);">添加</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('load');" plain="true" href="javascript:void(0);">刷新</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
			</div>
		</div>
		<table id="datagrid">
			<!-- js加载 -->
		</table>
	</div>

	<div id="dictDialog" style="display: none;overflow: hidden;width:450px;">
		<form id="dictForm" method="post">
			<table class="tableForm">
				<input type="hidden" name="id" value=""/>
				<tr>
					<td>字典分组：</td>
					<td>
						<input type="text" id="group_name" name="group_name" value=""/>
					</td>
					<td>分组代码：</td>
					<td>
						<input type="text" id="group_code" name="group_code" class="easyui-validatebox" required="true" >
					</td>
				</tr>
				<tr>
					<td>字典值</td>
					<td>
						<input type="text" id="dict_value" name="dict_value" class="easyui-validatebox" required="true" />
					</td>
					<td>字典名称</td>
					<td>
						<input type="text" id="dict_name" name="dict_name" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<td>排序</td>
					<td>
						<input type="text" id="order_num" name="order_num"></input>
					</td>
					<td>是否启用</td>
					<td>
						<input name="is_valid" type="checkbox" value="1">
					</td>
				</tr>
				<tr>
					<td>字典描述</td>
					<td>
						<textarea id="dict_desc" name="dict_desc"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html> 