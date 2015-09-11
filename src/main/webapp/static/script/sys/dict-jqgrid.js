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
        url: adminPath + '/sys/dict/pageList',
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'dict_value',
            label: '字典值',
            sortable: false,
            align: 'left'
        }, {
            name: 'dict_name',
            label: '字典名称',
            sortable: false,
            align: 'left'
        }, {
            name: 'dict_desc',
            label: '字典描述',
            sortable: false,
            align: 'left'
        }, {
            name: 'group_code',
            label: '字典组',
            sortable: false,
            align: 'left'
        }, {
            name: 'group_name',
            label: '组名称',
            sortable: false,
            align: 'left',
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
            width: 70,
            align: 'left',
            formatter: function (value, opt, row) {
                return app.getDictName("yesNo", value);
            },
            unformat: function (cellValue, options, cellObject) {
                return app.getDictVal("yesNo", cellValue);
            }
        }, {
            name: 'in_time',
            label: '更新时间',
            align: 'left'
        }, {
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
            postData = $.extend(postData, {
                "group_code": $('#toolbar input[name="qry_group_code"]').val(),
                "dict_desc": $('#toolbar input[name="qry_dict_desc"]').val(),
                "is_valid": $('#qry_is_valid').val()
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