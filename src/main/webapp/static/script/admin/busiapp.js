/**
 * 扩展接口 2014-11-16
 */
var datagrid;
var appDialog;
var appForm;


$(function() {

    //实例化form表单
    appForm = $("#appForm").form();
    //实例化窗口
    appDialog = $('#appDialog').show().dialog({
        top : 50,
        width : 500,
        modal : true,
        title : '添加接口',
        buttons : [{
            text : '确定',
            handler : function() {
                submitApp();
            }
        }]
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
        columns : [[{
            title : 'id',
            field : 'id',
            width : 50,
            checkbox : true
        }, {
            field : 'name',
            title : '接口名称',
            width : 75
        },{
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
            width : 60
        }, {
            field : 'OP',
            title : '查看详细',
            width : 70,
            formatter : function(value, rowData, rowIndex) {
                var html = '<a class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="view(\''+rowData.id+'\');" href="javascript:void(0);">查看</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateApp(\''+rowData.id+'\');" href="javascript:void(0);">修改</a>';
                html += '<a class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="deleteApp(\''+rowData.id+'\',\''+rowData.name+'\');" href="javascript:void(0);">刪除</a>';
                return html;
            }
        }]],
        onLoadSuccess : function(){
            $.parser.parse("td[field='OP']");
        }
    });

    loadMsgType();
    loadEventType();
    loadAppType();
    //事件消息类型的checkbox绑定事件
    $("input[group='reqTypes'][value='event']").live('click',function(){
        if(!$(this).attr("checked")){   //如果取消选中事件消息
            $("input[group='eventTypes']").attr("checked",false);
        }
    });
    //事件类型的checkbox绑定事件
    $("input[group='eventTypes']").live('click',function(){
        var length = $("input[group='eventTypes']:checked").length;
        //length == 0没有被选中的事件类型
        $("input[value='event'][group='reqTypes']").attr("checked", length != 0);
    });

});

function searchDatagrid() {
    var start_time = $('#toolbar input[name="start_time"]').val();
    var end_time = $('#toolbar input[name="end_time"]').val();
    var qry_app_type = $("#qry_app_type").combobox("getValue");

    datagrid.datagrid('load', {
        "start_time" : start_time,
        "end_time" : end_time,
        "name" : $('#qry_app_name').val(),
        "app_type" : qry_app_type
    });
    $('#qry_app_type').combobox('setValue', qry_app_type);
}

function clearDatagrid() {
    $('#toolbar input').val('');
    datagrid.datagrid('load', {});
}

function loadMsgType () {
    $.ajax({
        url : domain + '/dict/list?group_code=req_type',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if(data){
                var html = '';
                $.each(data, function(i,row){
                    html += '<label><input group="reqTypes" type="checkbox" value="'+row.dict_value+'"></label>'+row.dict_name+'<p>';
                });
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
                var html = '';
                $.each(data, function (i, row) {
                    html += '<label><input group="eventTypes" type="checkbox" value="' + row.dict_value + '"></label>' + row.dict_name + '<p>';
                });
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

function appApp () {
    appForm.form('clear');
    appDialog.dialog('open');
}

function updateApp (id) {
    datagrid.datagrid('unselectAll');
    var row = datagrid.datagrid('selectRecord',id).datagrid("getSelected");
    appForm.form('clear');

    var app_type = row.app_type;
    $("[name='web'],[name='api'],[name='restful']").hide();
    $("[name='"+app_type+"']").show();

    $('#app_type').combobox('setValue', app_type);
    appForm.form('load', {
        "id" : row.id,
        "name" : row.name,
        "description" : row.description,
        "restful_url" : row.restful_url,
        "app_url" : row.app_url,
        "bean_name" : row.bean_name,
        "method_name" : row.method_name
    });
    var msgTypes = row.supportMsgTypesStr;
    if(msgTypes !== ''){
        var msgTypesArr = msgTypes.split(",");
        for(var i=0; i<msgTypesArr.length; i++){
            $("input[group='reqTypes'][value='"+msgTypesArr[i]+"']").prop("checked",true);
        }
    }
    var eventTypes = row.supportEventTypesStr;
    if(eventTypes !== ''){
        var eventTypesArr = eventTypes.split(",");
        for(var i=0; i<eventTypesArr.length; i++){
            $("input[group='eventTypes'][value='"+eventTypesArr[i]+"']").prop("checked",true);
        }
    }
    if (row.is_valid === '1') {
        $("#is_valid").prop("checked",true);
    }
    appDialog.dialog('open');
}

/**
 * 提交
 */
function submitApp () {
    var app_url;
    var restful_url;
    var description;
    var bean_name;
    var method_name;
    var reqTypes = "";
    var eventTypes = "";


    var app_type = $("#app_type").combobox("getValue");
    if(app_type == ''){
        $.messager.alert('提示', '请选择应用类型！', 'warning');
        return false;
    }else if (app_type == 'web') {
        app_url = $("#app_url").val();
        if (app_url == '') {
            $.messager.alert('提示', '请输入URL！', 'warning');
            return false;
        }
    }else if (app_type == 'restful') {
        restful_url = $("#restful_url").val();
        if (restful_url == '') {
            $.messager.alert('提示', '请输入restful_url！', 'warning');
            return false;
        }
    }else if (app_type == 'api') {
        bean_name = $.trim($("input[name='bean_name']").val());
        if(bean_name === ''){
            $.messager.alert('提示', '请输入spring id！', 'warning');
            return false;
        }
        method_name = $.trim($("input[name='method_name']").val());
        if(method_name === ''){
            $.messager.alert('提示', '请输入method name！', 'warning');
            return false;
        }
        var $reqTypes = $("input[group='reqTypes']:checked");
        if ($reqTypes.length == 0) {
            $.messager.alert('提示', '请选择消息类型！', 'warning');
            return false;
        }
        $reqTypes.each(function(i){
            var $this = $(this);
            if (i == 0) {
                reqTypes += $this.val();
            }else{
                reqTypes += ","+$this.val();
            }
        });
        if (reqTypes.search("event") !== -1) {
            var $eventTypes = $("input[group='eventTypes']:checked");
             if ($eventTypes.length == 0) {
                 $.messager.alert('提示', '请选择事件类型！', 'warning');
                 return false;
             }
            $eventTypes.each(function(i){
                var $this = $(this);
                if (i == 0) {
                    eventTypes += $this.val();
                }else{
                    eventTypes += "," + $this.val();
                }
            });
        }
    }
    var app_name = $("#name").val();
    if(app_name == ''){
        $.messager.alert('提示', '请输入应用名称！', 'warning');
        return false;
    }

    app_url = $("#app_url").val();
    restful_url = $("#restful_url").val();
    description = $("#description").val();

    var data = {
        id : $("#id").val(),
        app_type : app_type,
        name : app_name,
        app_url : app_url,
        restful_url : restful_url,
        bean_name : bean_name,
        method_name : method_name,
        description : description,
        eventTypes : eventTypes,
        reqTypes : reqTypes,
        is_valid: $("#is_valid").attr("checked")?1:0
    };

    $.ajax({
        url: domain + '/admin/extapp/save',
        type: 'post',
        data: data,
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            appDialog.dialog('close');
            if (data && '1' == data.code) {
                fjx.showMsg(data.msg?data.msg:'操作成功！');
                datagrid.datagrid("myReload");
            } else {
                $.messager.alert('提示', data? data.msg:'操作失败', 'error');
            }
        }
    });
}

function deleteApp (id,app_name) {
    $.messager.confirm('请确认', '你要删除应用【'+app_name+'】吗？', function(r) {
        if (r) {
            $.ajax({
                url :  domain + '/admin/extapp/delete',
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

function loadAppType() {
    $.ajax({
        url : domain + '/dict/list?group_code=app_type',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            $("#qry_app_type").combobox({
                valueField:'dict_value',
                textField:'dict_name',
                data:data
            });
            $("#app_type").combobox({
                valueField:'dict_value',
                textField:'dict_name',
                data:data,
                onSelect : appTypeSelect
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

function appTypeSelect() {
    var appType = $("#app_type").combobox("getValue");
    $("[name='web'],[name='api'],[name='restful']").hide();
    $("[name='"+appType+"']").show();
}

