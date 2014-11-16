/**
 * 扩展接口 2014-11-16
 */
var datagrid;
var apiDialog;
var apiForm;

$(function() {
    //实例化form表单
    apiForm = $("#apiForm").form();
    //实例化窗口
    apiDialog = $('#apiDialog').show().dialog({
        modal : true,
        title : '添加接口'
    }).dialog('close');

    // 加载消息记录数据
    datagrid = $('#datagrid').datagrid({
        url : domain + '/admin/extapp/pageList',
        queryParams : {
        },
        toolbar : '#toolbar',
        pagination : true,
        pageSize : 10,
        pageList : [10, 15, 20],
        fit : true,
        fitColumns : true,
        nowrap : false,
        border : false,
        idField : 'id',
        frozenColumns : [[{
            title : 'id',
            field : 'id',
            width : 50,
            checkbox : true
        }, {
            field : 'name',
            title : '接口名称',
            width : 100
        }]],
        columns : [[{
            field : 'description',
            title : '接口描述',
            width : 200
        }, {
            field : 'app_type',
            title : '接口类型',
            width : 30
        }, {
            field : 'is_valid',
            title : '是否启用',
            width : 22
        }, {
            field : 'str_in_time',
            title : '接入时间',
            width : 49
        }, {
            field : 'OP',
            title : '查看详细',
            width : 65,
            formatter : function(value, rowData, rowIndex) {
                var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''+rowData.id+'\');" href="javascript:void(0);">查看</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMsgAction(\''+rowData.id+'\');" href="javascript:void(0);">修改</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteMsgAction(\''+rowData.id+'\',\''+rowData.key_word+'\');" href="javascript:void(0);">刪除</a>';
                return html;
            }
        }]],
        onLoadSuccess : function(){
            $.parser.parse();
        }
    });
});

function searchDatagrid() {

    var start_time = $('#toolbar input[name="start_time"]').val();
    var end_time = $('#toolbar input[name="end_time"]').val();
    datagrid.datagrid('load', {
        "key_word" : $('#toolbar input[name="paramMap.key_word"]')
            .val(),
        "start_time" : start_time,
        "end_time" : end_time,
        "req_type" : resTypeCombobox.combobox("getValue"),
        "event_type" : eventTypeCombobox.combobox("getValue"),
        "from_user_name" : $("#openid").val()
    });
}

function clearDatagrid() {
    $('#toolbar input').val('');
    datagrid.datagrid('load', {});
}

function appApi () {
    apiDialog.dialog('open');
}

function updateApi () {

}

function submitApi () {

}

function deleteApi (id) {

}
