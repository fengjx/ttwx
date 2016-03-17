/**
 * 消息记录
 */
var $table;

$(function () {

    $table = $('#data-table').bootstrapTable({
        method: 'post',
        toolbar: "#qry-toolbar",
        contentType: "application/x-www-form-urlencoded",
        url: adminPath + '/wechat/msglog/pageList',
        queryParamsType: "limit",
        queryParams: queryParams,
        cache: false,
        striped: true,
        sidePagination: "server",
        pagination: true,
        pageSize: 10,
        pageList: [10, 15, 20],
        showColumns: true,
        minimumCountColumns: 2,
        clickToSelect: true,
        idField:"id",
        columns: [{
            field: 'id',
            valign: 'middle',
            radio:true
        }, {
            field: 'from_user_name',
            title: 'openid',
            align: 'left',
            valign: 'middle'
        }, {
            field: 'req_type',
            title: '消息类型',
            align: 'center',
            valign: 'middle',
            formatter: function (value,row,index) {
                return convertMsgType(value);
            }
        }, {
            field: 'event_type',
            title: '事件类型',
            align: 'left',
            valign: 'middle',
            formatter: function (value,row,index) {
                return convertEventType(value);
            }
        }, {
            field: 'req_xml',
            title: '发送内容',
            align: 'left',
            valign: 'middle',
            formatter: function (value,row,index) {
                return parseReqxml2html(value, row.in_time);
            }
        }, {
            field: 'create_time',
            title: '发送时间',
            align: 'left',
            valign: 'middle'
        }, {
            field: 'resp_xml',
            title: '响应内容',
            align: 'left',
            valign: 'middle',
            formatter : function(value, row, index) {
                if (!value || value == '') {
                    return "无";
                }
                var html = '<a class="btn btn-info btn-xs" onclick="view(' + index + ');" href="javascript:void(0);">查看</a>';
                return html;
            }
        }, {
            field: 'resp_time',
            title: '响应时间',
            align: 'center',
            valign: 'middle',
            clickToSelect: false
        }]
    });

    function queryParams(params) {
        var start_time = $('#qry-toolbar input[name="start_time"]').val();
        var end_time = $('#qry-toolbar input[name="end_time"]').val();
        params = $.extend(params,{
            "key_word": $('#qry-toolbar input[name="qry_key_word"]').val(),
            "start_time": start_time,
            "end_time": end_time,
            "req_type": $("#qry_req_type").val(),
            "event_type": $("#qry_event_type").val(),
            "from_user_name": $("#qry-toolbar input[name='qry_openid']").val()
        });
        return params;
    }

});

function searchDatagrid() {
    $table.bootstrapTable('refresh');
}

function clearDatagrid() {
    $('#qry-toolbar input').val('');
    document.getElementById("qry_req_type").options[0].selected = true;
    document.getElementById("qry_event_type").options[0].selected = true;
    $table.bootstrapTable('refresh');
}

function view(index) {
    $table.bootstrapTable('uncheckAll');
    $table.bootstrapTable('check', index);
    var row = $table.bootstrapTable('getSelections')[0];
    // 预览效果HTML
    var viewHtml = parseRespxml2html(row.resp_xml, row.resp_time);
    app.alertModal(viewHtml,{
        title:"用户收到的消息",
        height:"auto",
        width:300
    });
}