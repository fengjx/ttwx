<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="admin"/>
    <title>消息记录</title>
    <link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li>微信管理</li>
            <li class="active">消息记录</li>
        </ol>
    </div>
    <div class="page-content">
        <div id="qry-toolbar">
            <div class="form-inline" role="form">
                <fieldset>
                    <div class="form-group">
                        <div class="control-group">
                            <label class="control-label">关 键 字：</label>
                            <input name="qry_key_word" class="form-control" type="text" placeholder="用户发送的文字">
                            <label class="control-label">消息类型：</label>
                            <select name="qry_req_type" id="qry_req_type">
                                <option value="">全部</option>
                                <myform:options items="${fns:getDictList('req_type')}" itemValue="value" itemLabel="label"></myform:options>
                            </select>
                            <label class="control-label">事件类型：</label>
                            <select name="qry_event_type" id="qry_event_type">
                                <option value="">全部</option>
                                <myform:options items="${fns:getDictList('event_type')}" itemValue="value" itemLabel="label"></myform:options>
                            </select>
                        </div>
                        <div class="control-group">
                            <label class="control-label">openid：</label>
                            <input name="qry_openid" class="form-control" type="text" placeholder="用户发送的文字">
                            <label class="control-label">发送时间：</label>
                            <div class="input-group">
                                <input class="form-control" onClick="WdatePicker()" data-options="dateFmt:'yyyy-M-d H:m:s',readOnly:true,skin:'twoer'" name="start_time" type="text" style="width: 120px;" />
                                <div class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </div>
                            </div>
                            <label class="control-label">--------------</label>
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
    </div>

<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=201552001" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/msg-log-jqgrid.js?v=2014110102" type="text/javascript" charset="UTF-8"></script>
</body>
</html>