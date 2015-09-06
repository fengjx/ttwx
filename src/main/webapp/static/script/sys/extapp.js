/**
 * 扩展接口管理
 */
var editModal;
var $table;

$(function () {

    editModal = $('#editModal').modal({
        show: false
    });

    $table = $('#data-table').jqGrid({
        url: adminPath + '/sys/ext/pageList',
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
            formatter: function (value, opt, row) {
                return app.getDictName("yesNo", value);
            }
        }, {
            name: 'in_time',
            label: '入库时间',
            align: 'left'
        }, {
            name: 'op',
            label: '操作',
            align: 'center',
            sortable: false,
            formatter: function (value, opt, row) {
                var html = '<a class="btn btn-minier btn-success" onclick="append(\'' + row.id + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-plus"></i></a>';
                html += '<a class="btn btn-minier btn-info" onclick="updateData(\'' + row.id + '\')" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
                html += '<a class="btn btn-minier btn-danger" onclick="deleteData(\'' + row.id + '\',\'' + row.dict_name + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-remove"></i></a>';
                return html;
            }
        }],
        serializeGridData: function (postData) {
            var start_time = $('#toolbar input[name="start_time"]').val();
            var end_time = $('#toolbar input[name="end_time"]').val();
            postData = $.extend(postData, {
                "group_code": "text",
                "app_name": $('#toolbar input[name="qry_key_word"]').val(),
                "start_time": start_time,
                "end_time": end_time
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
            buttonicon: "glyphicon glyphicon-edit",
            title: "edit",
            caption: "",
            position: "first",
            onClickButton: function () {
                updateData();
            }
        }
    ).jqGrid('navButtonAdd', '#tablePager',
        {
            buttonicon: "glyphicon glyphicon-plus",
            title: "add",
            caption: "",
            position: "first",
            onClickButton: function () {
                append();
            }
        }
    );

    $("#app_type").change(function () {
        var appType = $(this).val();
        $("[name='web'],[name='api'],[name='restful']").addClass("hide");
        $("[name='" + appType + "']").removeClass("hide");
    });

    //事件消息类型的checkbox绑定事件
    $("input[group='reqTypes'][value='event']").click(function () {
        if (!$(this).attr("checked")) {   //如果取消选中事件消息
            $("input[group='eventTypes']").attr("checked", false);
        }
    });
    //事件类型的checkbox绑定事件
    $("input[group='eventTypes']").click(function () {
        var length = $("input[group='eventTypes']:checked").length;
        //length == 0没有被选中的事件类型
        $("input[value='event'][group='reqTypes']").attr("checked", length != 0);
    });

});

function searchDatagrid() {
    $table.trigger("reloadGrid");
}

function clearDatagrid() {
    $('#toolbar input').val('');
    $table.trigger("reloadGrid");
}

/**
 * 编辑（添加/修改）
 * @param data
 */
function edit(data) {
    $("#form-data").find("#id").val('');
    $("#form-data").clearForm();
    $("#app_type").val("web");
    if (data) {
        $("#form-data").autofill(data);
    }
    editModal.modal('show');
}

/**
 * 添加
 * @param id
 */
function append(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
    }
    if (!id) {
        edit();
    } else {
        var row = $table.jqGrid('getRowData', id);
        edit({
            group_code: row.group_code,
            group_name: row.group_name
        });
    }
}

/**
 * 修改
 * @param id
 * @returns {boolean}
 */
function updateData(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要编辑的数据");
            return false;
        }
    }
    var row = $table.jqGrid('getRowData', id);
    edit(row);
}

/**
 * 删除
 * @param id
 * @param name
 * @returns {boolean}
 */
function deleteData(id, name) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要删除的数据");
            return false;
        }
    }
    var row = $table.jqGrid('getRowData', id);
    app.confirmModal("你要删除应用【" + row.name + "】吗？", function () {
        $.ajax({
            url: adminPath + '/sys/ext/delete',
            data: {
                id: id,
                app_type: row.app_type
            },
            cache: false,
            dataType: "json",
            success: function (res) {
                if (res && '1' === res.code) {
                    app.ok('刪除成功');
                    $table.trigger("reloadGrid");
                } else {
                    app.error(res.msg ? res.msg : '刪除失败');
                }
            }
        });
    });
}

/**
 * 提交表单
 */
function submitData() {
    var app_url;
    var restful_url;
    var description;
    var bean_name;
    var reqTypes = "";
    var eventTypes = "";


    var app_type = $("#app_type").val();
    if (app_type == '') {
        app.alert("请选择应用类型！");
        return false;
    } else if (app_type == 'web') {
        app_url = $("#app_url").val();
        if (app_url == '') {
            app.alert("请输入链接！");
            return false;
        }
    } else if (app_type == 'restful') {
        restful_url = $("#restful_url").val();
        if (restful_url == '') {
            app.alert("请输入restful_url！");
            return false;
        }
    } else if (app_type == 'api') {
        bean_name = $.trim($("input[name='bean_name']").val());
        if (bean_name === '') {
            app.alert("请输入spring id！");
            return false;
        }
        var $reqTypes = $("input[group='check-req_type']:checked");
        if ($reqTypes.length == 0) {
            app.alert("请选择消息类型！");
            return false;
        }
        $reqTypes.each(function (i) {
            var $this = $(this);
            if (i == 0) {
                reqTypes += $this.val();
            } else {
                reqTypes += "," + $this.val();
            }
        });
        if (reqTypes.search("event") !== -1) {
            var $eventTypes = $("input[group='check-event_type']:checked");
            if ($eventTypes.length == 0) {
                app.alert("请选择事件类型！");
                return false;
            }
            $eventTypes.each(function (i) {
                var $this = $(this);
                if (i == 0) {
                    eventTypes += $this.val();
                } else {
                    eventTypes += "," + $this.val();
                }
            });
        }
    }
    var app_name = $("#name").val();
    if (app_name == '') {
        app.alert("请输入应用名称！");
        return false;
    }

    app_url = $("#app_url").val();
    restful_url = $("#restful_url").val();
    description = $("#description").val();

    var data = {
        id: $("#id").val(),
        app_type: app_type,
        name: app_name,
        app_url: app_url,
        restful_url: restful_url,
        bean_name: bean_name,
        description: description,
        eventTypes: eventTypes,
        reqTypes: reqTypes,
        order_no: $("#order_no").val(),
        is_valid: $("#is_valid").attr("checked") ? 1 : 0
    };

    $.ajax({
        url: adminPath + '/sys/ext/save',
        type: 'post',
        data: data,
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if (data && '1' == data.code) {
                editModal.modal('hide');
                app.ok(data.msg ? data.msg : '操作成功！');
                $table.trigger("reloadGrid");
            } else {
                app.error(data ? data.msg : '操作失败');
            }
        }
    });
}