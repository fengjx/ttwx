<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="元素id"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="元素name"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="元素class"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description=""%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="是否允许编辑"%>
<%@ attribute name="appType" type="java.lang.String" required="true" description="应用类型"%>
<%@ attribute name="msgType" type="java.lang.String" required="false" description="应用支持的消息类型"%>
<%@ attribute name="eventType" type="java.lang.String" required="false" description="应用支持的事件类型"%>
<c:set var="current" value="${fns:currentTimeMillis()}"/>

<input id="${id}" name="${name}" type="hidden" value=""/>
<input id="label${id}" name="label${name}" ${allowInput?'':'readonly="readonly"'} type="text" value="" class="${cssClass}" style="${cssStyle}"/>
<a id="btn-search-app${current}" name="btn-search-app" href="javascript:" class="btn btn-xs btn-default">
    &nbsp;<i class="icon-search"></i>&nbsp;
</a>
<div id="modal${current}" class="modal" tabindex="-1" role="dialog" >
    <div class="modal-dialog" style="width: 850px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">选中应用</h4>
            </div>
            <div class="modal-body">
                <div id="toolbar${current}">
                    <div class="form-inline" role="form">
                        <fieldset>
                            <div class="form-group">
                                <div class="control-group">
                                    <label class="control-label">应用名称：</label>
                                    <input name="qry_name" class="form-control" type="text" placeholder="模糊匹配">
                                    <label class="control-label">入库时间：</label>
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
                                        <button id="btn-qry${current}" type="button" class="btn btn-white btn-primary">
                                            <i class="icon-search"></i>
                                            查询
                                        </button>
                                        <button id="btn-reset${current}" type="button" class="btn btn-white">
                                            <i class="icon-circle-blank"></i>
                                            重置
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <table id="table${current}"></table>
                <div id="pager${current}"></div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取消</button>
                <button id="btn-ok${current}" type="button" class="btn btn-primary btn-sm" >确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $(function () {
        var $modal${current};
        var $table${current};
        var appType = '${appType}';

        $modal${current} = $('#modal${current}').modal({
            show: false
        });

        $("#btn-qry${current}").click(function () {
            $table${current}.setGridParam({page: 1}).trigger("reloadGrid");
        });

        $("#btn-reset${current}").click(function () {
            $('#toolbar${current} input').val('');
            $${id}Table.trigger("reloadGrid");
        });

        $("#btn-ok${current}").click(function () {
            var id = $table${current}.jqGrid('getGridParam', "selrow");
            if (!id) {
                app.alertModal("请选选中应用");
                return false;
            }
            var row = $table${current}.jqGrid('getRowData', id);
            if('web' == appType){
                $("#label${id}").val(row.app_url);
                $("#${id}").val(row.app_url);
            }else if('api' == appType){
                $("#label${id}").val(row.name);
                $("#${id}").val(row.id);
            }
            $modal${current}.modal('hide');
        });
        <c:if test="${allowInput}">
        $("#label${id}").blur(function () {
            $("#${id}").val($(this).val());
        });
        </c:if>
        $("#btn-search-app${current}").click(function () {
            if(!$table${current}){
                $table${current} = $('#table${current}').jqGrid({
                    url: adminPath + '/wechat/ext/pageList',
                    colModel: [{
                        name: 'id',
                        hidden: true,
                        key: true
                    }, {
                        name: 'name',
                        label: '应用名称',
                        sortable: false,
                        align: 'left'
                    }, {
                        name: 'app_type',
                        label: '应用类型',
                        sortable: false,
                        align: 'left',
                        formatter: function (value, opt, row) {
                            return app.getDictName("app_type", value);
                        },
                        unformat: function (cellValue, options, cellObject) {
                            return app.getDictVal("app_type", cellValue);
                        }
                    }, {
                        name: 'description',
                        label: '接口描述',
                        sortable: false,
                        width: 183,
                        align: 'left'
                    }, {
                        name: 'order_no',
                        label: '排序',
                        sortable: false,
                        width: 40,
                        align: 'left'
                    }, {
                        name: 'is_valid',
                        label: '是否启用',
                        sortable: false,
                        align: 'left',
                        width: 80,
                        formatter: function (value, opt, row) {
                            return app.getDictName("yesNo", value);
                        },
                        unformat: function (cellValue, options, cellObject) {
                            return app.getDictVal("yesNo", cellValue);
                        }
                    }, {
                        name: 'in_time',
                        label: '入库时间',
                        align: 'left'
                    }, {
                        name: 'msg_types',
                        align: 'left',
                        hidden: true
                    }, {
                        name: 'event_types',
                        align: 'left',
                        hidden: true
                    }, {
                        name: 'bean_name',
                        align: 'left',
                        hidden: true
                    }, {
                        name: 'app_url',
                        align: 'left',
                        hidden: true
                    }, {
                        name: 'restful_url',
                        align: 'left',
                        hidden: true
                    }],
                    serializeGridData: function (postData) {
                        var start_time = $('#toolbar${current} input[name="start_time"]').val();
                        var end_time = $('#toolbar${current} input[name="end_time"]').val();
                        postData = $.extend(postData, {
                            "name": $('#toolbar input[name="qry_name"]').val(),
                            "app_type": '${appType}',
                            "msg_type": '${msgType}',
                            "event_type": '${eventType}',
                            "start_time": start_time,
                            "end_time": end_time
                        });
                        return postData;
                    }
                });

                $table${current}.jqGrid('navGrid', '#pager${current}', {
                    edit: false,
                    add: false,
                    del: false,
                    search: false,
                    refresh: true,
                    view: false,
                    position: "left",
                    cloneToTop: true
                });
            }
            $modal${current}.modal('show');
        });

    });

</script>