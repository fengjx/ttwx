<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>粉丝管理</title>
	<link href="<%=resourceUrl%>/css/user.css?v=2014031901" rel="stylesheet" type="text/css"/>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp">
			<jsp:param name="index" value="1"/>
		</jsp:include>
		<div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
				<li><a href="<%=domain %>/admin">后台管理</a></li>
				<li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
				<li class="active">粉丝管理</li>
			</ol>

			<div class="container-fluid" >
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

		</div>
	</div>
</div>
<script src="<%=resourceUrl%>/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/bootstrap-table/bootstrap-table-option.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/script/wechat/admin/user.js?v=2014051801" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
