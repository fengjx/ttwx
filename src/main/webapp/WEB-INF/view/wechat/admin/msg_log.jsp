<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="wechat"/>
    <title>消息记录</title>
    <link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li><a href="${adminPath}">后台管理</a></li>
            <li><a href="${adminPath}/wechat">平台管理</a></li>
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
                            <select data-type="dict" data-group="req_type" name="qry_req_type" id="qry_req_type" class="app-element">
                                <option value="">全部</option>
                            </select>
                            <label class="control-label">事件类型：</label>
                            <select data-type="dict" data-group="event_type" name="qry_event_type" id="qry_event_type" class="app-element">
                                <option value="">全部</option>
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
    </div>

<script src="${resourceUrl}/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=201552001" type="text/javascript" charset="UTF-8"></script>
<c:choose>
    <c:when test="${'JqGridPage' eq adapterPageName}">
        <link href="${resourceUrl}/jqGrid/css/ui.jqgrid-bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="${resourceUrl}/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/jqGrid/grid-opt.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/script/wechat/admin/msg-log-jqgrid.js?v=2014110102" type="text/javascript" charset="UTF-8"></script>
    </c:when>
    <c:otherwise>
        <link href="${resourceUrl}/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
        <script src="${resourceUrl}/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/bootstrap-table/bootstrap-table-option.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="UTF-8"></script>
        <script src="${resourceUrl}/script/wechat/admin/msg_log.js?v=2014110102" type="text/javascript" charset="UTF-8"></script>
    </c:otherwise>
</c:choose>
</body>
</html>