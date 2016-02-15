<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>粉丝管理</title>
	<link href="${resourceUrl}/css/user.css?v=2014031901" rel="stylesheet" type="text/css"/>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>微信管理</li>
			<li class="active">粉丝管理</li>
		</ol>
	</div>
	<div class="page-content" >
		<div class="row" style="border:1px solid #ddd;min-height: 620px;">
			<div class="col-md-2 nopadding" style="border-right:1px solid #ddd;min-height: 620px;">
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
							<a href="javascript:void(0);" onclick="editGroup();" >
								<i class="icon14_common add_gray"></i> <strong>新建分組</strong>
							</a>
						</div>

						<div class="inner_menu_item extra">
							<a href="javascript:void(0);" onclick="editGroup();" >
								<i class="glyphicon glyphicon-refresh"></i> <strong>同步分組</strong>
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
			<div class="col-md-10 nopadding">

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
				<table id="data-table">
					<!-- js加载 -->
				</table>

			</div>
		</div>
	</div>


<script src="${resourceUrl}/script/wechat/admin/user-jqgrid.js?v=2015090201" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
