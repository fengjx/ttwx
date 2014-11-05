<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>业务配置</title>
	<%@include file="/web/common/inc/admin.jsp"%>
	<link href="<%=curUrl%>/web/wechat/admin/material/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=curUrl %>/web/wechat/admin/busi/js/busiapp.js?v=20140416" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west'" style="width:200px" title="用户分组">
        	
        	<div id="groupDialog" style="display: none;overflow: hidden;width:250px;">
				<form id="groupForm" method="post">
					<table class="tableForm">
						<input type="hidden" name="paramMap.id" value=""/>
						<tr>
							<th>分組名稱</th>
							<td><input name="paramMap.name" class="easyui-validatebox" required="true" /></td>
						</tr>
					</table>
				</form>
			</div>
        	
        
			<div class="group_list">
				<div class="inner_menu_box" id="groupsList">
					<dl class="inner_menu">
						<dt class="inner_menu_item">
							<a href="javascript:void(0);"	class="inner_menu_link"> 
								<strong>全部用戶</strong>
								<em class="num"></em> 
							</a>
						</dt>
						<dd class="inner_menu_item ">
							<a href="javascript:void(0);" class="inner_menu_link" data-id=""> 
								<strong>為分組</strong>
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
								<input name="paramMap.key_word" style="width: 305px;" />
							</td>
						</tr>
						<tr>
							<th>關注時間</th>
							<td colspan="2">
								<input name="paramMap.start_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />-<input name="paramMap.end_time" class="easyui-datetimebox" editable="false" style="width: 150px;" />
							</td>
							<td>
								<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchDatagrid();" href="javascript:void(0);">查找</a><a class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="clearDatagrid();" href="javascript:void(0);">清空</a>
							</td>
						</tr>
					</table>
				</fieldset>
				<div>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="datagrid.datagrid('reload');" plain="true" href="javascript:void(0);">刷新</a> 
					<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="datagrid.datagrid('unselectAll');" plain="true" href="javascript:void(0);">取消選中</a>
				</div>
			</div>
			
			<table id="datagrid">
				<!-- js加载 -->
			</table>
		</div>
		
    </div>
	
</body>
</html> 