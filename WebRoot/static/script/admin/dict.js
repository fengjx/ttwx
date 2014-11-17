/**
 * 扩展接口 2014-11-16
 */
var datagrid;
var apiDialog;
var apiForm;
var dictGroupCombobox;

$(function() {
    //实例化form表单
    apiForm = $("#apiForm").form();
    //实例化窗口
    apiDialog = $('#apiDialog').show().dialog({
        width : 500,
        modal : true,
        title : '添加接口'
    }).dialog('close');

    // 加载消息记录数据
    datagrid = $('#datagrid').datagrid({
        url : domain + '/dict/pageList',
        queryParams : {
            group_code:$("#group_code").val()
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
        },{
            field : 'dict_name',
            title : '字典名称',
            width : 100
        }, {
            field : 'dict_value',
            title : '字典值',
            width : 100
        }]],
        columns : [[{
            field : 'dict_desc',
            title : '字典描述',
            width : 50
        }, {
            field : 'group_name',
            title : '字典分组',
            width : 50
        }, {
            field : 'group_code',
            title : '分组编码',
            width : 50
        }, {
            field : 'is_valid',
            title : '是否启用',
            width : 22
        }, {
            field : 'order_num',
            title : '排序',
            width : 22
        }, {
            field : 'in_time',
            title : '编辑时间',
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


    dictGroupCombobox = $("#dictGroup").combobox({
        valueField : 'group_code',
        textField : 'group_code',
        url : domain + '/dict/group'
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
    if(!msgTypeData || !eventTypeData){

    }
    $('#toolbar input').val('');
    datagrid.datagrid('load', {});
}

function loadMsgType () {
    $.ajax({
        url : domain + '/dict/list?group_code=event_type',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if(data){
                var html = '<label><input type="checkbox" value=""></label>全选<p>';
                $.each(data, function(i,row){
                    html += '<label><input type="checkbox" value="'+row.dict_name+'"></label>'+row.dict_name+'<p>';
                });
                alert(html);
                $("#msgType").html(html);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        beforeSend: function (XMLHttpRequest) {
        },
        complete: function () {
        }
    });
}

function loadEventType () {
    $.ajax({
        url: domain + '/dict/list?group_code=event_type',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if (data) {
                var html = '<label><input type="checkbox" value=""></label>全选<p>';
                $.each(data, function (i, row) {
                    html += '<label><input type="checkbox" value="' + row.dict_name + '"></label>' + row.dict_name + '<p>';
                });
                alert(html);
                $("#eventType").html(html);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        beforeSend: function (XMLHttpRequest) {
        },
        complete: function () {
        }
    });
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
