/* 全局对象 */
var app = $.extend({}, app);
var dictjs = domain + '/dict/js';
document.write('<script src="' + dictjs + '" type="text/javascript" charset="UTF-8"></script>');
(function ($) {
    /**
     * 将json字符串转成json对象
     *
     * @param {}
     *            jsonStr
     * @return {}
     */
    app.serialiJson = function (jsonStr) {
        return eval('(' + jsonStr + ')');
    };

    /**
     * 得到url路径 /开头标识本项目路径，其他表示外部系统路径（直接返回）
     *
     * @param {}
     *            targetUrl
     * @param {}
     *            domain
     * @return {}
     */
    app.getUrl = function (targetUrl, domain) {
        if (!targetUrl || targetUrl == '') {
            return '';
        }
        if (/^\//.test(targetUrl)) { // 如果是/开头
            return domain + targetUrl;
        }
        return targetUrl;
    };

    // 获取URL地址参数
    app.getQueryString = function (name, url) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        if (!url || url == ""){
            url = window.location.search;
        }else{
            url = url.substring(url.indexOf("?"));
        }
        r = url.substr(1).match(reg)
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };

    // 验证手机号码合法性
    app.validateMobile = function (value) {
        var reg1 = /^13\d{9}$/;
        var reg2 = /^15[0-35-9]\d{8}$/;
        var reg3 = /^18[05-9]\d{8}$/;
        if (reg1.test(value) || reg2.test(value) || reg3.test(value)) {
            return true;
        }
        return false;
    };

    // 验证邮箱合法性
    app.validateMail = function (value) {
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (reg.test(value)) {
            return true;
        }
        return false;
    };

    // 验证固话合法性
    app.validateTel = function (value) {
        var reg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
        if (reg.test(value)) {
            return true;
        }
        return false;
    };

    // 验证邮编合法性
    app.validatePostal = function (value) {
        var reg = /^[a-zA-Z0-9 ]{3,12}$/;
        if (reg.test(value)) {
            return true;
        }
        return false;
    };

    /**
     * 时间戳转换时间
     *
     * @param {}
     *            tm
     * @return {} 如：2011-3-16 16:50:43 格式
     */
    app.getLocalTime = function (tm) {
        var tt;
        if (tm.toString().length == 13) {
            tt = new Date(parseInt(tm)).toLocaleString().replace(/年|月/g, "-")
                .replace(/日/g, " ").replace(/\//g, "-");

        } else {
            tt = new Date(parseInt(tm) * 1000).replace(/年|月/g, "-").replace(/日/g,
                " ").replace(/\//g, "-");
        }
        return tt;
    };

    /**
     * 时间戳转换时间
     *
     * @param {}
     *            tm
     * @return {} 如：2011年3月16日 16:50:43 格式
     */
    app.getLocalTimeCN = function (tm) {
        var tt
        if (tm.toString().length == 13) {
            tt = new Date(parseInt(tm)).toLocaleString().replace(/\//g, "-");
        } else {
            tt = new Date(parseInt(tm) * 1000).toLocaleString().replace(/\//g, "-");
        }
        return tt;
    };

    app.art = undefined;
    app.artZindex = 10240;
    app.loadingModal = function (title, opt) {
        app.closeDialog();
        var _title = title || "正在请求...";
        var _opt = $.extend({
            zIndex: app.artZindex,
            "title": _title,
            "width": "200"
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
    };

    app.loading = function (title, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            "title": "正在请求...",
            "width": "200"
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
    };

    app.alert = function (msg, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: '提示',
            width: "200",
            content: msg,
            fixed: true,
            zIndex: 10240,
            cancel: false,
            ok: function () {
                if (okCallBack && $.isFunction(okCallBack)) {
                    okCallBack();
                }
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
    };

    app.alertModal = function (msg, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: '提示',
            width: "200",
            content: msg,
            fixed: true,
            cancel: false,
            ok: function () {
                if (okCallBack && $.isFunction(okCallBack)) {
                    okCallBack();
                }
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
    };

    app.confirm = function (msg, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: '请确认',
            width: "200",
            content: msg,
            fixed: true,
            okValue: '确定',
            cancelValue: '取消',
            ok: function () {
                if (okCallBack && $.isFunction(okCallBack)) {
                    okCallBack();
                }
            },
            cancel: function () {
                app.closeDialog;
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
    };

    app.confirmModal = function (msg, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: '请确认',
            width: "200",
            content: msg,
            fixed: true,
            okValue: '确定',
            cancelValue: '取消',
            ok: function () {
                if (okCallBack && $.isFunction(okCallBack)) {
                    okCallBack();
                }
            },
            cancel: function () {
                app.closeDialog;
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
    };

    app.prompt = function (title, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: title,
            width: "200",
            content: "<input id='art-content' style='width:172px;' type='text' autofocus />",
            fixed: true,
            okValue: '确定',
            cancelValue: '取消',
            ok: function () {
                return okCallBack($("#art-content").val());
            },
            cancel: function () {
                app.closeDialog;
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
        if (opt && opt.defaultVal) {
            $("#art-content").val(opt.defaultVal);
        }
    };

    app.promptModal = function (title, okCallBack, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            title: title,
            width: "200",
            content: "<input id='art-content' style='width:172px;' type='text' autofocus />",
            fixed: true,
            okValue: '确定',
            cancelValue: '取消',
            ok: function () {
                return okCallBack($("#art-content").val());
            },
            cancel: function () {
                app.closeDialog;
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
        if (opt && opt.defaultVal) {
            $("#art-content").val(opt.defaultVal);
        }
    };

    app.tusiModal = function (msg, time, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            "content": msg,
            fixed: true,
            "width": "200"
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
        if (time) {
            setTimeout(function () {
                app.closeDialog();
            }, time * 1000)
        }
    };

    /**
     * 自动消失的提示框
     *
     * @param msg
     * @param time
     * @param opt
     */
    app.tusiAlert = function (msg, time, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            "content": msg,
            fixed: true,
            "width": "200"
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
        if (time) {
            setTimeout(function () {
                app.closeDialog();
            }, time * 1000)
        }
    };

    /**
     * 自动消失的提示框
     *
     * @param id
     * @param msg
     * @param time
     * @param opt
     */
    app.tip = function (id, msg, time, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            quickClose: true,// 点击空白处快速关闭
            content: msg,
            onclose: function () {
                $('#' + id).focus();
            },
            follow: $('#' + id)[0]
        }, opt || {});
        app.art = dialog(_opt);
        app.art.show();
        if (time) {
            setTimeout(function () {
                app.closeDialog();
            }, time * 1000);
        }
    };

    app.winModal = function (title, url, onclose, opt) {
        app.closeDialog();
        var _opt = $.extend({
            zIndex: app.artZindex,
            fixed: true,
            title: title,
            url: url,
            onclose: function () {
                // 处理返回值
                if (this.returnValue && onclose) {
                    onclose(this.returnValue);
                }
            },
            ok: function () {
            }
        }, opt || {});
        app.art = dialog(_opt);
        app.art.showModal();
    };


    app.closeDialog = function () {
        if (app.art) {
            app.art.close().remove();
            app.art = undefined;
        }
    };

    app.ok = function (msg) {
        app.closeDialog();
        $.scojs_message(msg, $.scojs_message.TYPE_OK);
    };

    app.error = function (msg) {
        app.closeDialog();
        $.scojs_message(msg, $.scojs_message.TYPE_ERROR);
    };

    /**
     * 获得字典值
     * @param groupCode
     * @param dictName
     * @returns {string}
     */
    app.getDictVal = function (groupCode, dictName) {
        var val = "";
        var dictList = app.getDict(groupCode);
        if (dictList) {
            var dict;
            for (var i = 0; i < dictList.length; i++) {
                dict = dictList[i];
                if (dict.dict_name == dictName) {
                    val = dict["dict_value"];
                    break;
                }
            }
        }
        return val;
    };

    app.getDictName = function (groupCode, dictVal) {
        var val = "";
        var dictList = app.getDict(groupCode);
        if (dictList) {
            var dict;
            for (var i = 0; i < dictList.length; i++) {
                dict = dictList[i];
                if (dict.dict_value == dictVal) {
                    val = dict["dict_name"];
                    break;
                }
            }
        }
        return val;
    };

    app.getDict = function (groupCode) {
        return app.dict[groupCode];
    };

    // 自定义渲染组件
    app.parser = {
        auto: true,
        parse: function ($selector) {
            if ($selector) {
                render($($selector));
            } else {
                var $elements = $(".app-element");
                $.each($elements, function (i, dom) {
                    app.parser.render($(dom));
                });
            }
        },
        render: function ($dom) {
            var type = $dom.attr("data-type");
            if (type == 'dict') {
                var groupCode = $dom.attr("data-group");
                var dictArr = app.getDict(groupCode);
                if (!dictArr) {
                    return false;
                }
                if ($dom.is("select")) {
                    var options = '';
                    for (var i = 0; i < dictArr.length; i++) {
                        options += "<option value='" + dictArr[i].dict_value + "'>" + dictArr[i].dict_name + "</option>";
                    }
                    $dom.append(options);
                    if ($dom.attr("data-default")) {
                        $dom.val($dom.attr("data-default"));
                    }
                } else if ($dom.is("input")) {
                    if ($dom.attr("type").toLowerCase() == 'checkbox') {
                        var ckecks = '';
                        for (var i = 0; i < dictArr.length; i++) {
                            ckecks += '<label><input group="check-' + groupCode + '" type="checkbox" value="' + dictArr[i].dict_value + '"></label>' + dictArr[i].dict_name + '&nbsp;';
                        }
                        $dom.after(ckecks);
                        $dom.remove();
                    }
                }
            }
        }
    };
})(jQuery);