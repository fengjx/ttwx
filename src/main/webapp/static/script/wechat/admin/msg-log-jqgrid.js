/**
 * 消息记录
 */
var $table;

$(function () {


    $table = $('#data-table').jqGrid({
        url: adminPath + '/wechat/msglog/pageList',
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'from_user_name',
            label: 'openid',
            sortable: false
        }, {
            name: 'req_type',
            label: '消息类型',
            sortable: false,
            formatter: function (value, opt, row) {
                return convertMsgType(value);
            }
        }, {
            name: 'event_type',
            label: '事件类型',
            sortable: false,
            formatter: function (value, opt, row) {
                return convertEventType(value);
            }
        }, {
            name: 'req_xml',
            label: '发送内容',
            sortable: false,
            formatter: function (value, opt, row) {
                return parseReqxml2html(value, row.in_time);
            }
        }, {
            name: 'create_time',
            label: '发送时间',
            sortable: false
        }, {
            name: 'resp_xml',
            label: '响应内容',
            sortable: false,
            formatter: function (value, opt, row) {
                if (!value || value == '') {
                    return "无";
                }
                var html = '<a class="btn btn-info btn-minier" onclick="view(\'' + row.id + '\');" href="javascript:void(0);">查看</a>';
                return html;
            }
        },{
            name: 'resp_time',
            label: '响应时间',
            sortable: false

        }, {
            name: 'resp_xml',
            hidden: true,
            formatter: function (value, opt, row) {
                return $.jgrid.htmlEncode(value);
            }
        }],

        serializeGridData: function (postData) {
            var start_time = $('#qry-toolbar input[name="start_time"]').val();
            var end_time = $('#qry-toolbar input[name="end_time"]').val();
            postData = $.extend(postData,{
                "key_word": $('#qry-toolbar input[name="qry_key_word"]').val(),
                "start_time": start_time,
                "end_time": end_time,
                "req_type": $("#qry_req_type").val(),
                "event_type": $("#qry_event_type").val(),
                "from_user_name": $("#qry-toolbar input[name='qry_openid']").val()
            });
            return postData;
        }
    });

    $table.jqGrid('navGrid', '#tablePager', {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true,
        view: false,
        position: "left",
        cloneToTop: true
    }).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-remove",
            title: "remove",
            caption: "",
            position: "first",
            onClickButton: function () {
                deleteData();
            }
        }
    ).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-eye-open",
            title: "view",
            caption: "",
            position: "first",
            onClickButton: function () {
                view();
            }
        }
    ).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-plus",
            title: "add",
            caption: "",
            position: "first",
            onClickButton: function () {
                window.location.href = adminPath + "/wechat/action/keywordAdd";
            }
        }
    );


});

function searchDatagrid() {
    $table.trigger("reloadGrid");
}

function clearDatagrid() {
    $('#qry-toolbar input').val('');
    document.getElementById("qry_req_type").options[0].selected = true;
    document.getElementById("qry_event_type").options[0].selected = true;
    $table.trigger("reloadGrid");
}

function view(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要查看的数据");
            return false;
        }
    }
    var row = $table.jqGrid('getRowData', id);
    // 预览效果HTML
    var viewHtml = parseRespxml2html(row.resp_xml, row.resp_time);
    app.alertModal(viewHtml,{
        title:"用户收到的消息",
        height:"auto",
        width:300
    });
}