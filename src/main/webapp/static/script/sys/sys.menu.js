/**
 * 字典管理
 */
$(function () {

    editModal = $('#editModal').modal({
        show: false
    });

    $('#tree-table').treegrid();

    $("a[name='btn-remove']").click(function () {
        var id = $(this).attr("data-id");
        var name = $(this).attr("data-name");
        app.confirmModal("确定要删除该菜单吗<p>[" + name + "]", function () {
            $.ajax({
                url: adminPath + '/sys/menu/delete',
                data: {id:id},
                cache: false,
                dataType: "json"
            }).fail(function () {
                app.alertModal("系统错误，操作失败！");
            });
        })
    });


});
