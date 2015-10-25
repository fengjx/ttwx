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
