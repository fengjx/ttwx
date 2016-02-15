//选择素材弹出框
var materialModal;

$(function () {

    materialModal = $('#materialModal').modal({
        keyboard: true,
        show: false
    });

    //选中图文素材效果
    $("#appmsgList").on('click', ".appmsg", function () {
        $(".appmsg").removeClass('selected');
        $(this).addClass("selected");
    });

});

//清除数据
function clearData() {
    $('#msgActionForm input').val('');
    $("#preview_news").html("");
    $(".field").val('');
}

/**
 * 选择素材
 */
function openMaterialDialog() {
    materialModal.modal('show');
}

/**
 * 选择素材
 * @returns {boolean}
 */
function selectedMaterial() {
    var selectedView = $("div[class='appmsg selected']");
    var newsId = selectedView.attr("id");
    if (!selectedView || !newsId || newsId == '') {
        alert("请选择素材");
        return false;
    }
    $("#newsId").val(newsId);
    var preview_news = selectedView.removeClass("selected").html();
    $("#preview_news").html(preview_news);
    materialModal.modal('hide');
}

/**
 * 删除规则
 * @param id
 * @param okCallback
 */
function deleteById(id, okCallback) {
    app.confirmModal("确定要删除回复内容吗？", function () {
        $.ajax({
            url: adminPath + '/wechat/action/delete',
            data: 'id=' + id,
            cache: false,
            dataType: "json",
            success: function (res) {
                if (okCallback) {
                    okCallback(res);
                } else {
                    if (res && '1' === res.code) {
                        clearData();
                        app.alertModal('刪除成功', function () {
                            window.location.reload();
                        });
                    } else {
                        app.alertModal(res.msg ? res.msg : '刪除失败');
                    }
                }
            }
        });
    });
}

