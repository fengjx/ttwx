/**
 * 单图文本编辑
 * 2015-05-29
 */
//文本编辑器
var editor;
//图片上传
var uploader;
var $templateModal;

$(function () {

    $templateModal = $('#templateModal').modal({
        show: false
    });

    $("#btn-template").click(function () {
        $templateModal.modal('show');
    });

    $('#news_title').keyup(function () {
        $(".appmsg_title").find("a").html($(this).val())
    });

    $('.frm_textarea').keyup(function () {
        $(".appmsg_desc").html($(this).val())
    });
    //选择自定义URL
    $(".frm_checkbox_label").click(function () {
        if ($(".frm_checkbox").attr("checked")) {//如果是选中
            $(this).removeClass("selected");
            $(".frm_checkbox").attr("checked", false);
            $("#busiapp_url").attr("readonly", true);
            $("#span_url").hide();
            editor.setEnabled();
        } else {
            $(this).addClass("selected");
            $(".frm_checkbox").attr("checked", true);
            $("#busiapp_url").attr("readonly", false);
            $("#span_url").show();
            editor.setDisabled('fullscreen');
        }
    });

    $("#news_form").submit(function () {
        var msgFlag = $("#msgFlag").val();
        if (msgFlag == '1') {
            app.prompt("请输入预览userId", function (userId) {
                if (userId == '') {
                    app.alert("userId不能为空");
                }
                else {
                    $("#wxUserId").val(userId);
                    toSubmit();
                }
            });
        } else {
            toSubmit();
        }


        return false;
    });

    //实例化编辑器
    editor = UE.getEditor('container', {
        initialFrameHeight: 600,
        initialFrameWidth: 'auto'
    });
    uploader = UE.getEditor('upload_container', {});

    uploader.ready(function () {
        //设置编辑器不可用
        uploader.setDisabled();
        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
        uploader.hide();
        //侦听图片上传
        uploader.addListener('beforeInsertImage', function (t, arg) {
            //将地址赋值给相应的input,只取第一张图片的路径
            //$("#news_pic_url").val("value", arg[0].src);
            //图片预览
            $(".js_appmsg_thumb").attr("src", arg[0].src);
            $(".js_appmsg_thumb").show();
            $("#preview").attr("src", arg[0].src);
            $(".upload_preview").show();
        })
    });

    editor.ready(function () {
        if (material_id && material_id != '') {
            $.ajax({
                url: adminPath + '/wechat/material/load',
                data: "id=" + material_id,
                cache: false,
                dataType: "json",
                success: function (data) {
                    var json = $.xml2json(data.xml_data);
                    $(".appmsg_title").find("a").html(json.Articles.item.Title);
                    //标题
                    $("#news_title").val(json.Articles.item.Title);
                    $(".appmsg_desc").html(json.Articles.item.Title);
                    //摘要
                    $(".appmsg_desc").html(json.Articles.item.Description);
                    $(".frm_textarea").html(json.Articles.item.Description);
                    //图片
                    var pic_url = json.Articles.item.PicUrl;
                    if (json.Articles.item.PicUrl && json.Articles.item.PicUrl != '') {
                        //$("#news_pic_url").val("value", pic_url);
                        //图片预览
                        $(".js_appmsg_thumb").attr("src", pic_url);
                        $(".js_appmsg_thumb").show();
                        $("#preview").attr("src", pic_url);
                        $(".upload_preview").show();
                    }
                    var _url = json.Articles.item.Url;
                    if (isLocalUrl(_url)) {
                        $.ajax({
                            url: adminPath + '/wechat/material/getContent',
                            type: 'post',
                            data: {url: _url},
                            dataType: "html",
                            cache: false,
                            async: false,
                            success: function (content) {
                                editor.setContent(content);
                            }
                        });
                    } else {
                        $(".frm_checkbox_label").addClass("selected");
                        $(".frm_checkbox").attr("checked", true);
                        $("#busiapp_url").attr("readonly", false);
                        $("#busiapp_url").val(_url);
                        $("#span_url").show();
                        editor.setDisabled('fullscreen');
                    }
                },
                beforeSend: function (XMLHttpRequest) {
                    app.loadingModal();
                },
                complete: function () {
                    app.closeDialog();
                }
            });
        }
    });
});

function toSubmit() {
    $("#news_form").ajaxSubmit({
        url: adminPath + "/wechat/material/save",
        dataType: 'json',
        beforeSubmit: function () {
            app.loadingModal("数据提交中....");
        },
        success: function (res) {
            app.closeDialog();
            var msgFlag = $("#msgFlag").val();
            if (res && '1' == res.code) {
                if (msgFlag == '1') {
                    app.alert("预览成功，请留意微信消息！");
                } else {
                    var msg = msgFlag == '2' ? "群发成功!" : "保存成功!";
                    app.alertModal(msg, function () {
                        window.location.href = adminPath + '/wechat/material';
                    });
                }
            } else {
                app.closeDialog();
                app.alertModal(res.msg ? res.msg : "保存失败！");
            }
        }
    });
}

//弹出图片上传的对话框
function upImage() {
    var myImage = uploader.getDialog("insertimage", {
        maxNum: 1
    });
    myImage.open();
}

function deleteImage() {
    //将地址赋值给相应的input,只取第一张图片的路径
    //$("#news_pic_url").val("value", "");
    //图片预览
    $(".js_appmsg_thumb").attr("src", "");
    $(".js_appmsg_thumb").hide();
    $("#preview").attr("src", "");
    $(".upload_preview").hide();
}

function validForm() {
    var title = $("#news_title").val();
    var picUrl = $("#preview").attr("src");
    var description = $(".frm_textarea").val();
    if (!title || title == '') {
        app.alertModal("请输入标题");
        return false;
    }
    //if(!picUrl || picUrl == ''){
    //	$.messager.alert('提示', '请上传图片', 'warning');
    //	return false;
    //}
    if (!description || description == '') {
        app.alertModal("请添加摘要");
        return false;
    }
    var xml_data = "<xml>" +
        "<ToUserName><![CDATA[]]></ToUserName>" +
        "<FromUserName><![CDATA[]]></FromUserName>" +
        "<CreateTime><![CDATA[1]]></CreateTime>" +
        "<MsgType><![CDATA[news]]></MsgType>" +
        "<ArticleCount>1</ArticleCount>" +
        "<Articles>" +
        "<item>" +
        "<Title><![CDATA[" + title + "]]></Title>" +
        "<Description><![CDATA[" + description + "]]></Description>" +
        "<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>" +
        "<Url_0><![CDATA[##url##]]></Url_0>" +
        "</item>" +
        "</Articles>" +
        "</xml>";
    if ($(".frm_checkbox").attr("checked")) {	//选择自定义URL
        $("#contentsJson").remove();
        var app_url = $.trim($("#busiapp_url").val());
        if (!app_url || '' == $.trim(app_url)) {
            app.alertModal("请填写URL或选择应用");
            return false;
        }
        xml_data = xml_data.replace("<Url_0><![CDATA[##url##]]></Url_0>", "<Url><![CDATA[" + app_url + "]]></Url>");
    } else {
        var content = editor.getContent();
        if (!content || '' == $.trim(content)) {
            app.alertModal("请编辑页面内容內容，或填写链接地址");
            flag = false;
            return false;
        }
        var contentsJson = "[{'content' : '" + content + "','title' : '" + title + "'}]";
        $("#contentsJson").val(contentsJson);
    }
    $("#xml_data").val(xml_data);
    return true;
}


/**
 * 提交表单
 */
function submitNewsForm(flag) {
    if (validForm()) {
        $("#msgFlag").val(flag);
        $("#news_form").submit();
    }
}

//添加摘要
function addDesc() {
    $(".js_desc_area").show();
    $(".js_addDesc").hide();
}

/**
 * 判断链接是否是本项目链接
 * @param url
 * @returns {Boolean} 是本项目链接返回true
 */
function isLocalUrl(url) {
    if (url.search(staticDomain + '/upload/html/material') === -1) {
        return false;
    }
    return true;
}
