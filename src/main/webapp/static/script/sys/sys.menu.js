/**
 * 菜单管理
 */
$(function () {

    $('#tree-table').treegrid();

    $("a[name='btn-remove']").click(function () {
        var id = $(this).attr("data-id");
        var name = $(this).attr("data-name");
        app.confirmModal("确定要删除该菜单吗<p>[" + name + "]", function () {
            $.ajax({
                url: adminPath + '/sys/menu/delete',
                data: {id: id},
                cache: false,
                dataType: "json"
            }).done(function (res) {
                if (res && res.code == '1') {
                    app.alertModal(res.msg ? res.msg : "提交成功", function () {
                        window.location.reload();
                    });
                } else {
                    app.alertModal(res.msg ? res.msg : "提交失败");
                }
            }).fail(function () {
                app.alertModal("系统错误，操作失败！");
            });
        })
    });


});
