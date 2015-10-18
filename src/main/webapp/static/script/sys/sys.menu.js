/**
 * 字典管理
 */

var editModal;
var formValid;
var dataForm;
var $table;

$(function () {

    editModal = $('#editModal').modal({
        show: false
    });

    $table = $('#data-table').jqGrid({
        url: adminPath + '/sys/menu/tree',
        viewrecords : true,
        responsive : true,
        styleUI : 'Bootstrap',
        treeGrid: true,
        treeGridModel: 'adjacency', //决定树形表格可使用的方法，可用值有nested 或者adjacency.
        scroll: "true",
        datatype: 'json',
        pager: "#tablePager",
        mtype: "POST",
        width : "auto",
        autowidth : true,
        height: "auto",    // 设为具体数值则会根据实际记录数出现垂直滚动条
        rowNum : "-1",     // 显示全部记录
        shrinkToFit:false,  // 控制水平滚动条
        jsonReader: {
            root: "dataRows",
            repeatitems : false
        },
        treeReader : {
            level_field: "level",
            parent_id_field: "parent_id",
            leaf_field: "isLeaf",
            expanded_field: "expanded"
        },
        colModel: [
            { label: 'id', index: 'id', hidden: true,key: true },
            { label: '图标', index: 'icon'},
            { label: '名称', index: 'name'},
            { label: '链接', index: 'url', width: 200},
            { label: '权限', index: 'permission'},
            { label: '级别', index: 'level'},
            { label: '是否显示', index: 'is_show',
                formatter: function (value, opt, row) {
                    return app.getDictName("yesNo", value);
                },
                unformat: function (cellValue, options, cellObject) {
                    return app.getDictVal("yesNo", cellValue);
                }
            },
            { label: '排序', index: 'order_no'},
            { label: '更新时间', index: 'update_time'},
            {
            name: 'op',
            label: '操作',
            sortable: false,
            align: 'center',
            formatter: function (value, opt, row) {
                var html = '<a class="btn btn-minier btn-success" onclick="append(\'' + row.id + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-plus"></i></a>';
                html += '<a class="btn btn-minier btn-info" onclick="updateData(\'' + row.id + '\')" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
                html += '<a class="btn btn-minier btn-danger" onclick="deleteData(\'' + row.id + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-remove"></i></a>';
                return html;
            }
        }],
        serializeGridData: function (postData) {
            //postData = $.extend(postData, {
            //    "group_code": $('#toolbar input[name="qry_group_code"]').val(),
            //    "dict_desc": $('#toolbar input[name="qry_dict_desc"]').val(),
            //    "is_valid": $('#qry_is_valid').val()
            //});
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
    }).jqGrid('navButtonAdd', '#toolbar',
        {
            buttonicon: "glyphicon glyphicon-remove",
            title: "remove",
            caption: "",
            position: "first",
            onClickButton: function () {
                deleteData();
            }
        }
    ).jqGrid('navButtonAdd', '#toolbar',
        {
            buttonicon: "glyphicon glyphicon-edit",
            title: "edit",
            caption: "",
            position: "first",
            onClickButton: function () {
                updateData();
            }
        }
    ).jqGrid('navButtonAdd', '#toolbar',
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

    $("#btn-add").click(function () {
        append();
    });


    formValid = $.scojs_valid('#form-data', {
        rules: {
            dict_value: ['not_empty', {'max_length': 20}],
            dict_name: ['not_empty'],
            group_code: ['not_empty']
        },
        messages: {
            dict_value: {
                not_empty: "请输入字典值",
                max_length: "字典值不能超过20个字符"
            },
            dict_name: {
                not_empty: "请输入字典名称"
            },
            group_code: {
                not_empty: "请输入字典组标识"
            }
        },
        wrapper: '.control-group'
    });

    dataForm = $("#form-data").submit(function () {
        $(this).ajaxSubmit({
            url: adminPath + "/sys/dict/save",
            dataType: 'json',
            beforeSubmit: function () {
                return formValid.validate();
            },
            success: function (res) {
                if (res && "1" == res.code) {
                    editModal.modal('hide');
                    app.ok(res.msg);
                    $table.trigger("reloadGrid");
                } else {
                    app.error(res.msg);
                }
            }
        });
        return false;
    });
});

function searchDatagrid() {
    $table.setGridParam({page: 1}).trigger("reloadGrid");
}

function clearDatagrid() {
    $('#toolbar input').val('');
    $('#qry_is_valid').val('');
    $table.trigger("reloadGrid");
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
 * 编辑（添加/修改）
 * @param data
 */
function edit(data) {
    dataForm.find("#id").val('');
    dataForm.clearForm();
    if (data) {
        $("#form-data").autofill(data);
        if (data.is_valid == '1') {
            $("input[name='is_valid']").prop("checked", true);
        }
    }
    editModal.modal('show');
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
function deleteData(id) {
    if (!id) {
        id = $table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要删除的数据");
            return false;
        }
    }
    var row = $table.jqGrid('getRowData', id);
    app.confirmModal("你要删除字典【" + row.dict_name + "】吗？", function () {
        $.ajax({
            url: adminPath + '/sys/dict/delete',
            data: 'id=' + id,
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