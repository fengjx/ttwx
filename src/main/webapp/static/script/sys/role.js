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
        }
    }
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
            align: 'left',
        }, {
            name: 'op',
            label: '操作',
            sortable: false,
            align: 'center',
            formatter: function (value, opt, row) {
                var html = '<a class="btn btn-minier btn-success" onclick="append(\'' + row.id + '\');" href="javascript:void(0);"><i class="ace-icon fa glyphicon glyphicon-plus"></i></a>';
                html += '<a class="btn btn-minier btn-info" href="' + adminPath + '/sys/role/form?id=' + row.id + '"><i class="ace-icon fa glyphicon glyphicon-edit"></i></a>';
                html += '<a name="btn-delete" data-id="' + row.id + '" class="btn btn-minier btn-danger" href="javascript:void(-1);"><i class="ace-icon fa glyphicon glyphicon-remove"></i></a>';
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

});
