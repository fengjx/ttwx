/**
 * 多图文本
 * 2015-5-30
 * fengjx
 */
//图文总数
var total = 2;
//图文编号
var newsNum;
//当前编辑的消息编号
var curDataId;
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

    //默认编辑第一条消息
    curDataId = 1;	//当前编辑的图文
    newsNum = 2;	//图文个数，最小2条

    $('#news_title').keyup(function () {
        $("#appmsg_title" + curDataId).find("a").html($(this).val());
    });

    ////选择自定义URL
    $(".frm_checkbox_label").click(function () {
        if ($(".frm_checkbox").attr("checked")) {//如果是选中
            $(this).removeClass("selected");
            $(".frm_checkbox").attr("checked", false);
            $("#busiapp_url").attr("readonly", true);
            $("#span_url").hide();
            editor.setEnabled();
            $("#appmsgUrl" + curDataId).attr("select", "0");
        } else {
            $(this).addClass("selected");
            $(".frm_checkbox").attr("checked", true);
            $("#busiapp_url").attr("readonly", false);
            $("#span_url").show();
            editor.setDisabled('fullscreen');
            $("#appmsgUrl" + curDataId).attr("select", "1");
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

    //实例化图片上传组件
    uploader = UE.getEditor('upload_container', {});
    uploader.ready(function () {
        //设置编辑器不可用
        uploader.setDisabled();
        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
        uploader.hide();
        //侦听图片上传
        uploader.addListener('beforeInsertImage', function (t, arg) {
            //图片预览
            $("#appmsg_thumb" + curDataId).attr("src", arg[0].src);
            $("#appmsg_thumb" + curDataId).parent().addClass("has_thumb");
            $("#preview").attr("src", arg[0].src);
            $(".upload_preview").show();
        })
    });

    //实例化编辑器
    editor = UE.getEditor('container', {
        initialFrameHeight: 600,
        initialFrameWidth: 'auto'
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
                    var items = json.Articles.item;
                    $.each(items, function (j, item) {
                        var _url = item.Url;
                        if (j == 0) {		//第一个图文
                            $("#appmsg_title1").find("a").html(item.Title);
                            $("#news_title").val(item.Title);
                            $("#appmsg_thumb1").attr("src", item.PicUrl);
                            $("#appmsg_thumb1").parent().addClass("has_thumb");
                            $("#preview").attr("src", item.PicUrl);
                            $(".upload_preview").show();
                            if (isLocalUrl(_url)) {
                                editor.setContent(loadContent(_url));
                            } else {
                                $(".frm_checkbox_label").addClass("selected");
                                $(".frm_checkbox").attr("checked", true);
                                $("#busiapp_url").attr("readonly", false);
                                $("#appmsgUrl1").attr("select", "1");
                                $("#appmsgUrl1").val(_url);
                                $("#busiapp_url").val(_url);
                                $("#span_url").show();
                                editor.setDisabled('fullscreen');
                            }
                        } else if (j == 1) {	//第二个图文
                            $("#appmsg_title2").find("a").html(item.Title);
                            $("#appmsg_thumb2").attr("src", item.PicUrl);
                            $("#appmsg_thumb2").parent().addClass("has_thumb");
                            if (isLocalUrl(_url)) {
                                $("#appmsgContent2").val(loadContent(_url));
                            } else {
                                $("#appmsgUrl2").attr("select", "1");
                                $("#appmsgUrl2").val(_url);
                            }
                        } else {
                            addAppmsg(item);
                        }
                    });
                }
            });
        }
    });
    //监听编辑器输入事件
    editor.addListener('aftergetcontent', function (type) {
        $("#appmsgContent" + curDataId).val(this.body.innerHTML);	//将当前编辑器内容缓存
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
    //删除图片预览
    $("#appmsg_thumb" + curDataId).attr("src", "");
    $("#appmsg_thumb" + curDataId).parent().removeClass("has_thumb");
    $("#preview").attr("src", "");
    $(".upload_preview").hide();
}
/**
 * 添加一条图文
 */
function addAppmsg(item) {
    if (total > 7) {	//限制最多10条
        app.alertModal("你最多可以加入8条图文消息");
        return false;
    }
    //图文总数加1
    total = total + 1;
    newsNum = newsNum + 1;
    var html = '<div class="appmsg_item js_appmsg_item" data-id="' + newsNum + '" data-fileid="" id="appmsgItem' + newsNum + '">' +
        '<input type="hidden" class="appmsgContent" id="appmsgContent' + newsNum + '" data-id="' + newsNum + '" value=""/>' +
        '<input type="hidden" class="appmsgUrl" id="appmsgUrl' + newsNum + '" data-id="' + newsNum + '" value="" select=""/>' +
        '<img id="appmsg_thumb' + newsNum + '" src="" class="js_appmsg_thumb appmsg_thumb">' +
        '<i class="appmsg_thumb default">缩略图</i>' +
        '<h4 class="appmsg_title" id="appmsg_title' + newsNum + '">' +
        '<a target="_blank" href="javascript:void(0);" onclick="return false;">标题</a>' +
        '</h4>' +
        '<div class="appmsg_edit_mask">' +
        '<a href="javascript:void(0);" onclick="editAppmsg(' + newsNum + ');" data-id="' + newsNum + '" class="icon18_common edit_gray js_edit">编辑</a>' +
        '<a href="javascript:void(0);" onclick="deleteAppmsg(' + newsNum + ');" data-id="' + newsNum + '" class="icon18_common del_gray js_del">删除</a>' +
        '</div>' +
        '</div>';
    $("#js_appmsg_preview").append(html);
    if (item) {
        var _url = item.Url;
        $("#appmsg_title" + newsNum).find("a").html(item.Title);
        $("#appmsg_thumb" + newsNum).attr("src", item.PicUrl);
        $("#appmsg_thumb" + newsNum).parent().addClass("has_thumb");
        if (isLocalUrl(_url)) {
            $("#appmsgContent" + newsNum).val(loadContent(_url));
        } else {
            $("#appmsgUrl" + newsNum).attr("select", "1");
            $("#appmsgUrl" + newsNum).val(_url);
        }
    }
}

/**
 * 根据图文索引删除一条图文
 * @param {} index
 */
function deleteAppmsg(dataId) {
    if (total < 3) {	//限制最少2条
        app.alertModal("无法删除，多图文消息至少需要有2条");
        return false;
    }
    total = total - 1;
    $("#appmsgItem" + dataId).remove();
}

/**
 * 编辑图文
 * @param {} index
 */
function editAppmsg(dataId) {
    if ($(".frm_checkbox").attr("checked")) {		//如果是选中，自定义URL
        var app_url = $("#busiapp_url").val();
        $("#appmsgUrl" + curDataId).val(app_url);
        $("#appmsgUrl" + curDataId).attr("select", "1");
    } else {
        $("#appmsgUrl" + curDataId).val('http://');
        $("#appmsgUrl" + curDataId).attr("select", "0");
    }
    curDataId = dataId;		//更新当前编辑图文索引
    showEditCss(dataId);	//调整编辑器样式
    settingInner(dataId);	//为编辑内容赋值
}
/**
 * 调整编辑区域css
 */
function showEditCss(index) {
    var _index = $("#appmsgItem" + index).index();	//返回同级DIV中的索引（从0开始）
    if (_index == 0) {		//第一个消息
        $(".appmsg_editor").css("margin-top", "0px");
        $(".arrow_out").css("margin-top", "0px");
        $(".arrow_in").css("margin-top", "0px");
    } else if (_index < 4) {
        var top = 288;
        top = top + 103 * (_index - 2);
        $(".appmsg_editor").css("margin-top", top + "px");
        $(".arrow_out").css("margin-top", "0px");
        $(".arrow_in").css("margin-top", "0px");
    } else {
        var top = 15.75;
        top = top + 103 * (_index - 4);
        $(".appmsg_editor").css("margin-top", top + "px");
        $(".arrow_out").css("margin-top", "477px");
        $(".arrow_in").css("margin-top", "477px");
    }
}

//为inner赋值
function settingInner(dataId) {
    var imgSrc = $("#appmsg_thumb" + dataId).attr("src");
    var title = $("#appmsg_title" + dataId).find("a").text();
    var content = $("#appmsgContent" + dataId).val();
    var appmsgUrl = $("#appmsgUrl" + dataId).val();
    var select = $("#appmsgUrl" + dataId).attr("select");

    $("#news_title").val(title);
    if (!imgSrc || imgSrc == '') {
        $("#preview").attr("src", "");
        $(".upload_preview").hide();
    } else {
        $("#preview").attr("src", imgSrc);
        $(".upload_preview").show();
    }
    $("#busiapp_url").val(appmsgUrl);
    editor.setContent(content);
    if (select && select == '1') {	//选择自定义URL
        $(".frm_checkbox_label").addClass("selected");
        $(".frm_checkbox").attr("checked", true);
        $("#span_url").show();
        editor.setDisabled('fullscreen');
    } else {
        $(".frm_checkbox_label").removeClass("selected");
        $(".frm_checkbox").attr("checked", false);
        $("#span_url").hide();
        editor.setEnabled();
    }
}

/**
 * 提交保存
 */
function submitNewsForm(msgFlag) {
    if (validForm()) {
        $("#msgFlag").val(msgFlag);
        $("#news_form").submit();
    }
}

function validForm() {
    editAppmsg(curDataId);
    var flag = true;
    //图文json数据数组
    var newsJson = [];
    var xml_data = "<xml>" +
        "<ToUserName><![CDATA[]]></ToUserName>" +
        "<FromUserName><![CDATA[]]></FromUserName>" +
        "<CreateTime><![CDATA[1]]></CreateTime>" +
        "<MsgType><![CDATA[news]]></MsgType>" +
        "<ArticleCount>" + total + "</ArticleCount>" +
        "<Articles>";
    //遍历图文消息
    var c_num = 0;		//计数，记录内容编辑的个数
    $(".js_appmsg_item").each(function (i) {
        var item = $(this);
        var title = item.find(".appmsg_title > a").text();
        var picUrl = item.find(".js_appmsg_thumb").attr("src");
        var select = item.find(".appmsgUrl").attr("select");
        if (!title || title == '' || title == '标题') {
            app.alertModal('请输入标题[图文' + (i + 1) + ']');
            flag = false;
            return false;
        }
        //if(!picUrl || picUrl == ''){
        //	$.messager.alert('提示', '请上传图片[图文'+(i+1)+']', 'warning');
        //	flag = false;
        //	return false;
        //}
        var json = {};
        xml_data += "<item>" +
            "<Title><![CDATA[" + title + "]]></Title>" +
            "<Description><![CDATA[]]></Description>" +
            "<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>";
        if (select == "1") {	//选择自定义URl
            var app_url = item.find(".appmsgUrl").val();
            if (!app_url || '' == app_url || 'http://' == app_url) {
                app.alertModal('请填写URL或选择应用[图文' + (i + 1) + ']');
                flag = false;
                return false;
            }
            xml_data += "<Url><![CDATA[" + app_url + "]]></Url>" + "</item>";
        } else {
            var content = item.find(".appmsgContent").val();
            if (!content || '' == $.trim(content)) {
                app.alertModal('请编辑页面内容，或者填写链接地址[图文' + (i + 1) + ']');
                flag = false;
                return false;
            }
            json = {'title': title, 'content': content};
            newsJson.push(json);
            xml_data += "<Url_" + c_num + "><![CDATA[##url##]]></Url_" + c_num + ">" + "</item>"; //后台通过<Url_"+i+">来替换
            c_num = c_num + 1;
        }
    });
    if (!flag) {		//停止继续往下执行
        return false;
    }
    xml_data += "</Articles></xml>";
    $("#xml_data").val(xml_data);
    if (newsJson.length > 0) {
        $("#contentsJson").val($.toJSON(newsJson));
    }
    return true;
}

/**
 * 读取素材内容
 */
function loadContent(url) {
    var content;
    $.ajax({
        url: adminPath + '/wechat/material/getContent',
        type: 'post',
        data: {url: url},
        dataType: "text",
        cache: false,
        async: false,
        success: function (_content) {
            content = _content
        }
    });
    return content;
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
