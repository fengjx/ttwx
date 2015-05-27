<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理</title>
    <link href="<%=resourceUrl%>/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=resourceUrl%>/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp">
            <jsp:param name="index" value="3"/>
        </jsp:include>
        <div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">后台管理</a></li>
                <li><a href="#">平台管理</a></li>
                <li class="active">消息记录</li>
            </ol>

            <div id="qry-toolbar">
                <div class="form-inline" role="form" style="width: 1000px;">
                    <fieldset>
                        <div class="form-group">
                            <div class="control-group">
                                <label class="control-label">关 键 字：</label>
                                <input name="qry_key_word" class="form-control" type="text" placeholder="用户发送的文字">
                                <label class="control-label">消息类型：</label>
                                <jsp:include page="/dict/selecter">
                                    <jsp:param name="showAll" value="1"/>
                                    <jsp:param name="id" value="qry_req_type"/>
                                    <jsp:param name="name" value="qry_req_type"/>
                                    <jsp:param name="groupCode" value="req_type"/>
                                </jsp:include>
                                <label class="control-label">事件类型：</label>
                                <jsp:include page="/dict/selecter">
                                    <jsp:param name="showAll" value="1"/>
                                    <jsp:param name="id" value="qry_event_type"/>
                                    <jsp:param name="name" value="qry_event_type"/>
                                    <jsp:param name="groupCode" value="event_type"/>
                                </jsp:include>
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

                                <span class="columns-right pull-right" style="margin-right: -396px;">
                                    <button onclick="searchDatagrid();" type="button" class="bottom-margin btn glyphicon glyphicon-search ">
                                        <i class="fa fa-check"></i>
                                    </button>
                                    <button onclick="clearDatagrid();" type="button" class="btn glyphicon glyphicon-transfer">
                                        <i class="fa fa-check"></i>
                                    </button>
                                </span>
                            </div>
                    </fieldset>
                </div>
            </div>
            <table id="data-table"></table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/bootstrap-table/bootstrap-table.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/bootstrap-table/bootstrap-table-option.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/My97DatePicker/WdatePicker.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/material_util.js?v=201552001" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/msg_log.js?v=2014110102" type="text/javascript" charset="UTF-8"></script>
</body>
</html>