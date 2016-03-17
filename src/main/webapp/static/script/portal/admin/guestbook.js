/**
 * 字典管理
 */

$(function () {

    var $table;

    $table = $('#data-table').jqGrid({
        url: adminPath + '/portal/guestbook/pageList',
        colModel: [{
            name: 'id',
            hidden: true,
            key: true
        }, {
            name: 'name',
            label: '姓名',
            sortable: false,
            align: 'left'
        }, {
            name: 'email',
            label: '邮箱',
            sortable: false,
            align: 'left'
        }, {
            name: 'qq',
            label: 'QQ',
            sortable: false,
            align: 'left'
        }, {
            name: 'phone',
            label: '电话',
            sortable: false,
            align: 'left'
        }, {
            name: 'msg',
            label: '内容',
            sortable: false,
            align: 'left',
            width:200
        }, {
            name: 'in_time',
            label: '更新时间',
            align: 'left'
        }],
        serializeGridData: function (postData) {
            postData = $.extend(postData, {
                "name": $('#toolbar input[name="qry_name"]').val(),
                "email": $('#toolbar input[name="qry_email"]').val(),
                "qq": $('#toolbar input[name="qry_qq"]').val(),
                "phone": $('#toolbar input[name="qry_phone"]').val()
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
        view: true,
        position: "left",
        cloneToTop: true
    });

    $("#btn-qry").click(function () {
        $table.setGridParam({page: 1}).trigger("reloadGrid");
    });

    $("#btn-reset").click(function () {
        $('#toolbar input').val('');
        $table.trigger("reloadGrid");
    });
});

