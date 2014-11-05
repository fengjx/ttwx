<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用戶管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west'" style="width:200px" title="用户分组">
        	<div id="groupDialog" style="display: none;overflow: hidden;width:250px;">
				<form id="groupForm" method="post">
					<table class="tableForm">
						<input type="hidden" name="id" value=""/>
						<tr>
							<th>分组名称</th>
							<td><input name="name" class="easyui-validatebox" required="true" /></td>
						</tr>
					</table>
				</form>
			</div>
        	
        
			<div class="group_list">
				<div class="inner_menu_box" id="groupsList">
					<dl class="inner_menu">
						<dt class="inner_menu_item">
							<a href="javascript:void(0);"	class="inner_menu_link"> 
								<strong>全部用户</strong>
								<em class="num"></em> 
							</a>
						</dt>
						<dd class="inner_menu_item ">
							<a href="javascript:void(0);" class="inner_menu_link" data-id=""> 
								<strong>未分組</strong>
								<em class="num"></em>
							</a>
						</dd>
						
					</dl>
					<div class="inner_menu_item extra" id="js_groupAdd">
						<a href="javascript:void(0);" onclick="appGroup();" class=""> 
							<i class="icon14_common add_gray"></i> <strong>新建分組</strong>
						</a>
					</div>
					<%--<dl class="inner_menu no_extra">
						<dt class="inner_menu_item selected">
							<a href="" class="inner_menu_link"> 
								<strong>黑名单</strong><em class="num">(0)</em>
							</a>
						</dt>
					</dl>--%>
				</div>
			</div>
		</div>
        
        <div id="user_list" data-options="region:'center'" title="用户列表">
        	
        	<div id="toolbar" class="datagrid-toolbar" style="height: auto;display: none;">
				<fieldset>
					<table class="tableForm">
						<tr>
							<th>openid</th>
							<td colspan="2">
								<input name="openid" style="width: 305px;" />
							</td>
						</tr>
						<tr>
							<th>关注时间</th>
							<td colspan="2">
								<!-- <input name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />-<input name="paramMap.end_time" class="easyui-datetimebox" editable="false" style="width: 150px;" /> -->
								<input class="Wdate" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="start_time" type="text" style="width: 148px;" />
								-
								<input class="Wdate" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="end_time" type="text" style="width: 148px;" />
							</td>
							<td>
								<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">清空</a>
							</td>
						</tr>
					</table>
				</fieldset>
				<div>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('reload');" plain="true" href="javascript:void(0);">刷新</a> 
					<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消选中</a>
				</div>
			</div>
			<table id="datagrid">
				<!-- js加载 -->
			</table>
		</div>
		
    </div>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/css/user.css?v=2014031901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/script/admin/user.js?v=2014051801" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/plugin/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
</body>
</html> 