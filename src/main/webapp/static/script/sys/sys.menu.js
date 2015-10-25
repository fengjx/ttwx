/**
 * 字典管理
 */

var $table;

$(function () {

    editModal = $('#editModal').modal({
        show: false
    });

    $table = $('#data-table').jqGrid({
        url: adminPath + '/sys/menu/tree',
        "datatype":"json",
        "width":"780",
        "hoverrows":false,
        "viewrecords":false,
        "gridview":true,
        "height":"auto",
        "sortname":"name",
        "scrollrows":true,
        "treeGrid":true,
        "ExpandColumn":"name",
        "treedatatype":"json",
        "treeGridModel":"adjacency",
        "loadonce":true,
        "rowNum":100,
        page:false,
        "treeReader":{
            "parent_id_field":"parent_id",
            "level_field":"level",
            "leaf_field":"isLeaf",
            "expanded_field":"expanded",
            "icon_field":"icon"
        },
        colModel: [
            { label: 'id', index: 'id', name: 'id', hidden: true, key: true },
            { label: '图标', index: 'icon', name: 'icon'},
            { label: '名称', index: 'name', name: 'name'},
            { label: '链接', index: 'url',name: 'url'},
            { label: '权限', index: 'permission',name: 'permission'},
            { label: '级别', index: 'level', name: 'level'},
            { label: '是否显示', index: 'is_show', name: 'is_show',
                formatter: function (value, opt, row) {
                    return app.getDictName("yesNo", value);
                },
                unformat: function (cellValue, options, cellObject) {
                    return app.getDictVal("yesNo", cellValue);
                }
            },
            { label: '排序', index: 'order_no', name: 'order_no'},
            { label: '更新时间', index: 'update_time', name: 'update_time'},
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
            }
        ],
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