/**
 * 扩展接口 2014-11-16
 */
var datagrid;
var dictDialog;
var dictForm;

var dictGroupData;
var dictGroupCombobox;

$(function() {

    //实例化form表单
    dictForm = $("#dictForm").form();
    //实例化窗口
    dictDialog = $('#dictDialog').show().dialog({
        title : '添加字典',
        top : 50,
        width : 500,
        modal : true,
        buttons : [{
            text : '确定',
            handler : function() {
               submitDict()
            }
        }]
    }).dialog('close');

    // 加载消息记录数据
    datagrid = $('#datagrid').datagrid({
        url : domain + '/dict/pageList',
        queryParams : {
            group_code:$("#group_code").val()
        },
        toolbar : '#toolbar',
        pagination : true,
        singleSelect:true,
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
            hidden:true
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
            width : 80
        }, {
            field : 'group_name',
            title : '字典分组',
            width : 30
        }, {
            field : 'group_code',
            title : '分组编码',
            width : 30
        }, {
            field : 'is_valid',
            title : '是否启用',
            width : 22
        }, {
            field : 'order_num',
            title : '排序',
            width : 10
        }, {
            field : 'str_in_time',
            title : '编辑时间',
            width : 40
        }, {
            field : 'OP',
            title : '查看详细',
            width : 65,
            formatter : function(value, rowData, rowIndex) {
                var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''+rowData.id+'\');" href="javascript:void(0);">查看</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateDict(\''+rowData.id+'\');" href="javascript:void(0);">修改</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteDict(\''+rowData.id+'\',\''+rowData.dict_name+'\');" href="javascript:void(0);">刪除</a>';
                return html;
            }
        }]],
        onLoadSuccess : function(){
            $.parser.parse();
        }
    });

    loadDictGroup();
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

/**
 * 加载字典分组
 */
function loadDictGroup () {
    $.ajax({
        url : domain + '/dict/group',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            dictGroupData = data;
            dictGroupCombobox = $("#dictGroup").combobox({
                valueField : 'group_code',
                textField : 'group_name',
                data:dictGroupData
            });
            $("#group_name").combobox({
                valueField : 'group_name',
                textField : 'group_name',
                data:dictGroupData
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        },
        beforeSend: function (XMLHttpRequest) {
        },
        complete: function () {
        }
    });
}


function appDict () {
    dictDialog.dialog('open');
}

function updateDict (id) {
    datagrid.datagrid('unselectAll');
    datagrid.datagrid('selectRecord',id).datagrid("getSelected");
    var selectNode = datagrid.datagrid("getSelected");
    if (selectNode) {
        dictForm.form('clear');
        dictForm.form('load', {
            'id' : selectNode.id,
            'group_code' : selectNode.group_code,
            'group_name' : selectNode.group_name,
            'dict_value' : selectNode.dict_value,
            'dict_name' : selectNode.dict_name,
            'dict_desc' : selectNode.dict_desc,
            'is_valid' : selectNode.is_valid,
            'order_num' : selectNode.order_num
        });
        dictDialog.dialog('open').dialog({
            title : '修改字典'
        });
    }else{
        $.messager.alert('提示', '请选择要修改的字典！', 'warning');
    }
}

function submitDict () {
    dictForm.form('submit', {
        url : domain + '/admin/dict/save',
        success : function(res) {
            fjx.closeProgress();
            var data = $.evalJSON(res);
            dictDialog.dialog('close');
            if (data && '1' == data.code) {
                fjx.showMsg(data.msg?data.msg:'操作成功！');
                datagrid.datagrid("myReload");
            } else {
                $.messager.alert('提示', data? data.msg:'操作失败', 'error');
            }
        },
        onSubmit : function() {
            $.messager.progress({
                text : '数据提交中....',
                interval : 100
            });
        },
        onLoadError:function(){
            fjx.closeProgress();
        }
    });
}

function deleteDict (id,dict_name) {
    $.messager.confirm('请确认', '你要删除字典【'+dict_name+'】吗？', function(r) {
        if (r) {
            $.ajax({
                url :  domain + '/admin/dict/delete',
                data : 'id='+id,
                success : function (res) {
                    if(res && '1' === res.code){
                        fjx.showMsg(res.msg?res.msg:'操作成功！');
                        datagrid.datagrid("myReload");
                    }else{
                        $.messager.alert('提示',res?res.msg:'操作失败！','error');
                    }
                }
            });
        }
    });
}
