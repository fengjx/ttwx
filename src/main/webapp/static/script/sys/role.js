/**
 * 角色管理
 */

var roleAdmin;

function init() {
    roleAdmin = {
        $table: {},
        deleteData: function deleteData(id) {
            if (!id) {
                id = roleAdmin.$table.jqGrid('getGridParam', "selrow");
                if (!id) {
                    app.alertModal("请选中要删除的数据");
                    return false;
                }
            }
            var row = roleAdmin.$table.jqGrid('getRowData', id);
            app.confirmModal("你要删除角色【" + row.name + "】吗？", function () {
                $.ajax({
                    url: adminPath + '/sys/role/delete',
                    data: 'id=' + id,
                    cache: false,
                    dataType: "json"
                }).done(function (res) {
                    if (res && '1' === res.code) {
                        app.ok(res.msg ? res.msg : '刪除成功');
                        roleAdmin.$table.trigger("reloadGrid");
                    } else {
                        app.error(res.msg ? res.msg : '刪除失败');
                    }
                }).fail(function () {
                    app.alert("系统错误，删除失败！");
                });
            });
        },
        assignUser: function (roleId, roleName) {
            $.ajax({
                url: adminPath + '/sys/role/roleUsers',
                data: {roleId: roleId},
                cache: false,
                dataType: "json"
            }).done(function (data) {
                try {
                    roleAdmin.userSelect.empty();
                    var option;
                    for (var i = 0, len = data.length; i < len; i++) {
                        option = '<option value="' + data[i].userId + '"';
                        if (roleId === data[i].roleId) {
                            option += 'selected="selected"';
                        }
                        option += '>' + data[i].username + '</option>';
                        roleAdmin.userSelect.append(option);
                    }
                    roleAdmin.userSelect.bootstrapDualListbox('refresh');
                    app.closeDialog();
                } catch (e) {
                    app.error(data.msg ? data.msg : "读取用户数据失败");
                }
            }).fail(function () {
                app.error("读取用户数据失败");
            });
            $("#form-data").find("input[name='roleId']").val(roleId);
            $("#editRole").html(roleName);
            roleAdmin.assignModal.modal('show');
        },
        assignModal: $('#assignModal').modal({
            height: 500,
            show: false
        }),
        userSelect: $('select[name="user-list"]').bootstrapDualListbox({
            infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>',
            selectorMinimalHeight: 300,
            selectedListLabel: '<div class="alert alert-success" role="alert">已授权</div>',
            nonSelectedListLabel: '<div class="alert alert-warning" role="alert">未授权</div>'
        })
    };

    roleAdmin.userSelect
        .bootstrapDualListbox('getContainer')
        .find('.btn')
        .addClass('btn-white btn-info btn-bold');

}

$(function () {

    init();

    roleAdmin.$table = $('#data-table').jqGrid({
        url: adminPath + '/sys/role/list',
        pager: false,
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'name',
            label: '角色名称',
            sortable: false,
            align: 'left'
        }, {
            name: 'role_code',
            label: '角色标识',
            sortable: false,
            align: 'left'
        }, {
            name: 'is_sys',
            label: '系统角色',
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
            name: 'update_time',
            label: '更新时间',
            sortable: false,
            align: 'left'
        }, {
            name: 'op',
            label: '操作',
            sortable: false,
            align: 'left',
            formatter: function (value, opt, row) {
                var html = '<a title="分配角色" name="btn-role-user" data-id="' + row.id + '" data-name="' + row.name + '"  class="btn btn-minier btn-success"><i class="ace-icon fa glyphicon glyphicon-user"></i></a>';
                html += '<a title="编辑" class="btn btn-minier btn-info" href="' + adminPath + '/sys/role/form?id=' + row.id + '"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
                html += '<a title="删除" name="btn-delete" data-id="' + row.id + '" class="btn btn-minier btn-danger" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-remove"></i></a>';
                return html;
            }
        }]
    });

    roleAdmin.$table.jqGrid('navGrid', '#tablePager', {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true,
        view: false,
        position: "left",
        cloneToTop: true
    });

    $("#data-table").on("click", "a[name='btn-delete']", function () {
        var id = $(this).attr("data-id");
        roleAdmin.deleteData(id);
    });

    $("#data-table").on("click", "a[name='btn-role-user']", function () {
        var roleId = $(this).attr("data-id");
        var roleName = $(this).attr("data-name");
        roleAdmin.assignUser(roleId, roleName);
    });

    // 提交角色授权
    $("#btn-submit").click(function () {
        var selectIds = roleAdmin.userSelect;
        var userIds = selectIds.val() ? selectIds.val().join(",") : ""
        $.ajax({
            url: adminPath + '/sys/role/saveRoleUsers',
            data: {
                roleId: $("#form-data").find("input[name='roleId']").val(),
                userIds: userIds
            },
            cache: false,
            dataType: "json"
        }).done(function (res) {
            if (res && '1' == res.code) {
                roleAdmin.$table.trigger("reloadGrid");
                roleAdmin.assignModal.modal('hide');
                app.ok(res.msg ? res.msg : "授权成功！");
            } else {
                app.alertModal(res.msg ? res.msg : "授权失败！");
            }
        });

    });
});
