/**
 * 字典管理
 */

var dataForm;

$(function () {

    $("#btn-add").click(function () {
        append();
    });

    dataForm = $("#data-form").validate({
        submitHandler: function(form) {
            var parent_level = $("#parent_id").attr("data-level");
            $(form).ajaxSubmit({
                url: adminPath + "/sys/menu/save",
                dataType: 'json',
                data:{
                    level: parent_level?parseInt(parent_level)+1:1
                },
                success: function (res) {
                    app.closeDialog();
                    if (res && '1' == res.code) {
                        app.alertModal(res.msg ? res.msg : "保存成功！", {
                            ok: function () {
                                window.location.href = adminPath + "/sys/menu";
                            }
                        });
                    } else {
                        app.alertModal(res.msg ? res.msg : "保存失败！", {
                            ok: function () {
                                app.closeDialog();
                            }
                        });
                    }
                }
            });
        }
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
        dataForm.autofill(data);
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