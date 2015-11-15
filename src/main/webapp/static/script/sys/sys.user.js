/**
 * 字典管理
 */

var sysUser;

function init() {

    sysUser = {
        dataForm: $("#dataForm")

    };

    sysUser.editModal = $('#editModal').modal({
        show: false
    });

}


$(function () {
    init();

    sysUser.$table = $('#data-table').jqGrid({
        url: adminPath + '/sys/user/pageList',
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'username',
            label: '用户名',
            sortable: false,
            align: 'left'
        }, {
            name: 'email',
            label: '邮箱',
            sortable: false,
            align: 'left'
        }, {
            name: 'phone',
            label: '电话',
            sortable: false,
            align: 'left'
        }, {
            name: 'score',
            label: '积分',
            sortable: false,
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
            align: 'left',
            formatter: function (value, opt, row) {
                var html = '<a class="btn btn-minier btn-info" onclick="updateData(\'' + row.id + '\')" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
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

    sysUser.$table.jqGrid('navGrid', '#tablePager', {
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


    $("#form-data").validate({
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                url: adminPath + "/sys/user/save",
                dataType: 'json',
                success: function (res) {
                    if (res && '1' == res.code) {
                        sysUser.$table.trigger("reloadGrid");
                        sysUser.editModal.modal('hide');
                        app.ok(res.msg ? res.msg : "保存成功！");
                    } else {
                        app.alertModal(res.msg ? res.msg : "保存失败！");
                    }
                }
            });
        }
    });


});

function searchDatagrid() {
    sysUser.$table.setGridParam({page: 1}).trigger("reloadGrid");
}

function clearDatagrid() {
    $('#toolbar input').val('');
    $('#qry_is_valid').val('');
    sysUser.$table.trigger("reloadGrid");
}

/**
 * 添加
 * @param id
 */
function append(id) {
    if (!id) {
        id = sysUser.$table.jqGrid('getGridParam', "selrow");
    }
    if (!id) {
        edit();
    } else {
        var row = sysUser.$table.jqGrid('getRowData', id);
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
    sysUser.dataForm.find("#id").val('');
    sysUser.dataForm.clearForm();
    if (data) {
        $("#form-data").autofill(data);
        if (data.is_valid == '1') {
            $("input[name='is_valid']").prop("checked", true);
        }
    }
    sysUser.editModal.modal('show');
}

/**
 * 修改
 * @param id
 * @returns {boolean}
 */
function updateData(id) {
    if (!id) {
        id = sysUser.$table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要编辑的数据");
            return false;
        }
    }
    var row = sysUser.$table.jqGrid('getRowData', id);
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
        id = sysUser.$table.jqGrid('getGridParam', "selrow");
        if (!id) {
            app.alertModal("请选中要删除的数据");
            return false;
        }
    }
    var row = sysUser.$table.jqGrid('getRowData', id);
    app.confirmModal("你要删除字典【" + row.dict_name + "】吗？", function () {
        $.ajax({
            url: adminPath + '/sys/user/delete',
            data: 'id=' + id,
            cache: false,
            dataType: "json",
            success: function (res) {
                if (res && '1' === res.code) {
                    sysUser.$table.trigger("reloadGrid");
                    app.ok(res.msg ? res.msg : "保存成功！");
                } else {
                    app.error(res.msg ? res.msg : '刪除失败');
                }
            }
        });
    });
}