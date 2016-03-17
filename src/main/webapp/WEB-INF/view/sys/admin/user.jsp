<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>用户管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li>系统管理</li>
			<li class="active">用户管理</li>
		</ol>
	</div>

	<div class="page-content">
		<div id="toolbar">
			<div class="form-inline" role="form">
				<fieldset>
					<div class="form-group">
						<div class="control-group">
							<label class="control-label">用户名：</label>
							<input name="qry_username" class="form-control" type="text" placeholder="模糊匹配">
							<label class="control-label">邮箱：</label>
							<input name="qry_email" class="form-control" type="text" placeholder="模糊匹配">
							<label class="control-label">启用：</label>
							<select name="qry_is_valid" id="qry_is_valid">
								<option value="">全部</option>
								<myform:options items="${fns:getDictList('yesNo')}" itemValue="value" itemLabel="label"></myform:options>
							</select>
							<label class="control-label">更新时间：</label>
							<div class="input-group">
								<input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="start_time" type="text" style="width: 120px;" />
								<div class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</div>
							</div>
							<label class="control-label">-</label>
							<div class="input-group">
								<input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="end_time" type="text" style="width: 120px;" />
								<div class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i>
								</div>
							</div>
							<span class="columns-right pull-right">
								<button onclick="searchDatagrid();" type="button" class="btn btn-white btn-primary">
									<i class="icon-search"></i>
									查询
								</button>
								<button onclick="clearDatagrid();" type="button" class="btn btn-white">
									<i class="icon-circle-blank"></i>
									重置
								</button>
							</span>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
		<table id="data-table"></table>
		<div id="tablePager"></div>

		<div id="editModal" class="modal" tabindex="-1" role="dialog" >
			<div class="modal-dialog" style="width: 600px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">用户编辑</h4>
					</div>
					<form class="form-horizontal" id="form-data" method="POST" role="form">
						<div class="modal-body">
							<input type="hidden" id="id" name="id" value="" />
							<div class="control-group">
								<label class="control-label" for="username">用户名：</label>
								<div class="controls">
									<input type="text" id="username" name="username" class="span4 form-control"/>
								</div>
							</div>
							<div name="web" class="control-group">
								<label class="control-label" for="email">邮箱：</label>
								<div class="controls">
									<input id="email" name="email" type="text" class="span4 form-control"/>
								</div>
							</div>
							<div name="api" class="control-group">
								<label class="control-label" for="phone_no">电话：</label>
								<div class="controls">
									<input name="phone_no" id="phone_no" type="text" class="span4 form-control"/>
								</div>
							</div>
                            <div name="api" class="control-group">
                                <label class="control-label" for="score">积分：</label>
                                <div class="controls">
                                    <input name="score" id="score" type="text" class="span4 form-control"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">是否启用:</label>
                                <div class="controls">
                                    <myform:radios name="is_valid" values="${fns:getDictList('yesNo')}" checked="${is_valid}"/>
                                </div>
                            </div>
							<div class="control-group">
								<label class="control-label" for="remarks">备注:</label>
								<div class="controls">
									<textarea id="remarks" name="remarks" class="span4" rows="3">${remarks}</textarea>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary btn-sm" data-loading-text="正在提交...">保存</button>
						</div>
					</form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div>

<script src="${resourceUrl}/script/sys/sys.user.js?v=201511901" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
