/**
 * 设置授权
 */
$(function () {

    $("#form-auth").submit(function () {
        $(this).ajaxSubmit({
            url: adminPath + "/wechat/setting/save",
            dataType: 'json',
            beforeSubmit: validForm,
            success: function (res) {
                if (res && "1" == res.code) {
                    app.alertModal("更新成功！", function () {
                        window.location.href = adminPath + '/wechat/setting';
                    });
                } else {
                    app.alert(res.msg ? res.msg : '授权失败');
                }
            }
        });
        return false;
    });

    $("#btn-reset").click(function () {
        app.confirmModal("重置后，之前的配置将失效，需要重新配置微信接口", function () {
            $.ajax({
                url: adminPath + "/wechat/setting/reset",
                type: 'post',
                data: {
                    id: $("#id").val()
                },
                dataType: "json",
                success: function (data) {
                    if (data && "1" == data.code) {
                        app.alertModal("重置成功，请登录公众平台重新配置！", function () {
                            window.location.href = adminPath + '/wechat/setting';
                        });
                    } else {
                        app.alert(data.msg ? data.msg : '重置失败');
                    }
                }
            });
        });
    });
    //改变当前状态
    changeState(valid_state);
});

/**
 * 未完成，应该写入参数校验逻辑
 * @returns {boolean}
 */
function validForm() {

    return true;
}

function changeState(valid_state) {
    if (valid_state == '0') {
        $("#step1").addClass("text-danger");
        $("#step2").removeClass("text-danger");
        $("#step3").removeClass("text-danger");
    } else if (valid_state == '1') {
        $("#step2").addClass("text-danger");
        $("#step1").removeClass("text-danger");
        $("#step3").removeClass("text-danger");
    } else if (valid_state == '2') {
        $("#step1").removeClass("text-danger");
        $("#step2").removeClass("text-danger");
        $("#step3").addClass("text-danger");
    } else {
        $("#step1").removeClass("text-danger");
        $("#step2").removeClass("text-danger");
        $("#step3").removeClass("text-danger");
    }
}