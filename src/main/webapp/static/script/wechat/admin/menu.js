/**
 * 菜单管理
 */
var msgActionForm;
// 菜单数据
var menuData;
//当前选择的节点
var selectNode;
// 是否正在排序
var isSort = false;

var msg = {
    a: "你可以先添加一个菜单，然后开始为其设置响应动作",
    b: "发布成功，如没能查看到更新效果，请重新关注微信查看",
    c: "已有子菜单，无法设置动作",
    d: "订阅者点击该菜单会收到一下消息",
    e: "订阅者点击该菜单会跳转到以下链接",
    f: "通过拖拽左边的菜单进行排序"
}

$(function () {
    loadMenu();

    $("#menu_tree").on("click", ".dd-handle", function () {
        if(isSort){
            return false;
        }
        var $this = $(this);
        var $parent = $(this).parent();
        $(".dd-handle").removeClass("btn-yellow");
        $this.addClass("btn-yellow");
        var level = $parent.attr("data-level");
        var index = $parent.index();
        if ('1' === level) {
            selectNode = menuData[index];
        } else {
            var pid = $parent.attr("data-pid");
            var pindex = $("#menu" + pid).index();
            selectNode = menuData[pindex].children[index];
        }
        thowSetting(selectNode);
    });

    $("#menu_tree").on("click", ".dd-handle > a", function () {
        var plist = $(this).parent().parent();
        var pid = plist.attr("data-id");
        append(2, pid);
    });

    msgActionForm = $("#msgActionForm").submit(function () {
        $(this).ajaxSubmit({
            url: adminPath + "/wechat/action/save",
            dataType: 'json',
            beforeSubmit: function () {
                app.loadingModal("数据提交中....");
            },
            success: function (res) {
                if (res && '1' === res.code) {
                    clearData();
                    app.ok("设置成功");
                    loadMenu();
                } else {
                    app.error(res.msg ? res.msg : '设置失败');
                }
            },
            complete : function(){
                app.closeDialog();
            }
        });
        return false;
    });

});

function loadMenu() {

    $.ajax({
        url: adminPath + '/wechat/menu/load',
        type: 'post',
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            $("#menu_tree").html("");
            if (data && data.length > 0) {
                menuData = data;
                $("#menu_tree").html(createMenuTree(data));
                if (selectNode) {
                    var nodeId = selectNode.id;
                    // 触发点击事件，选中之前点击的菜单
                    $('#menu' + nodeId + ' > .dd-handle').trigger("click");
                }
            }
        }
    });
}


function createMenuTree(data) {
    var html = "";
    $.each(data, function (i, row) {
        html += '<li id="menu' + row.id + '" class="dd-item" data-id="' + row.id + '" data-level="1">' +
            '<div class="dd-handle">' +
            row.name +
            '<a href="javascript:void(0);" title="添加" class="sorted">' +
            '<i class="pull-right glyphicon glyphicon-plus"></i>' +
            '</a>' +
            '</div>';
        if (row.children) {
            html += '<ol class="dd-list">';
            $.each(row.children, function (j, cRow) {
                html += '<li id="menu' + cRow.id + '" class="dd-item" data-id="' + cRow.id + '" data-pid="' + row.id + '" data-level="2">' +
                    '<div class="dd-handle">' + cRow.name + '</div>' +
                    '</li>';
            });
            html += '</ol>';
        }
        html += '</li>';

    });
    return html;
}

/**
 * 添加菜单
 * @param level
 * @param pid
 */
function append(level, pid) {
    var title;
    var order_no;
    if (level === 1) {
        title = "输入一级菜单名称";
        var l = $("#menu_tree > li").length;
        if (l >= 3) {
            app.alertModal("最多可创建3个一級菜单");
            return false;
        }
        order_no = l + 1;
    } else {
        title = "输入二级菜单名称";
        var l = $("#menu" + pid + " ol li").length;
        if (l >= 5) {
            app.alertModal("最多可创建5个二級菜单");
            return false;
        }
        order_no = l + 1;
    }
    app.promptModal(title, function (val) {
        if (!val) {
            alert("请输入菜单名称");
            return false;
        }
        $.ajax({
            url: adminPath + '/wechat/menu/save',
            type: 'post',
            data: {
                parent_id: pid,
                menu_level: level,
                order_no: order_no,
                name: val
            },
            dataType: "json",
            cache: false,
            async: true,
            success: function (data) {
                if (data && '1' == data.code) {
                    loadMenu();
                    app.closeDialog();
                } else {
                    alert(data.msg ? data.msg : "保存失败");
                }
            }
        });
        return false;
    });
}


/**
 * 发布菜单，同步菜单数据到微信
 */
function release() {
    $.ajax({
        url: domain + '/admin/wechat/menu/release',
        cache: false,
        dataType: "json",
        success: function (res) {
            if (res && '1' == res.code) {
                app.ok("菜单发布成功");
            } else {
                app.error(res.msg ? res.msg : '发布失败');
            }
        },
        beforeSend: function () {
            app.loadingModal("正在发布菜单...");
        },
        complete : function(){
            app.closeDialog();
        }
    });
}

/**
 * 修改菜单名称
 */
function updatedMenu() {
    if (selectNode) {
        app.promptModal("修改菜单", function (val) {
            if (!val) {
                alert("请输入菜单名称");
                return false;
            }
            $.ajax({
                url: domain + '/admin/wechat/menu/save',
                type: 'post',
                data: {
                    'id': selectNode.id,
                    name: val
                },
                dataType: "json",
                cache: false,
                async: true,
                success: function (data) {
                    if (data && '1' == data.code) {
                        loadMenu();
                        app.closeDialog();
                    } else {
                        alert(data.msg ? data.msg : "保存失败");
                    }
                }
            });
            return false;
        },{defaultVal:selectNode.name});
    } else {
        app.alertModal("请选择要编辑的菜单");
    }
}
/**
 * 删除菜单
 */
function removeMenu() {
    if (selectNode) {
        app.confirmModal('您要刪除删除【' + selectNode.name + '】？', function () {
            $.ajax({
                url: domain + '/admin/wechat/menu/delete',
                data: 'id=' + selectNode.id,
                cache: false,
                dataType: "json",
                success: function (res) {
                    if (res && '1' == res.code) {
                        app.ok("删除成功");
                        $("#action_none").find("p").html(msg.a);
                        showActionContent('action_none');
                        selectNode = undefined;
                        loadMenu();
                    } else {
                        app.error(res.msg ? res.msg : '刪除失败');
                    }
                }
            });
        });
    } else {
        app.alertModal("请选择要删除的菜单");
    }
}

/**
 * 显示菜单设置
 */
function thowSetting(node) {
    //清除数据
    clearData();
    if (node.children) {//有子菜单
        $("#action_none").find("p").html(msg.c);
        showActionContent('action_none');
        return false;
    }
    if (!node.type) {//没有设置菜单动作
        showActionContent('action_index');
        return false;
    }
    var tips = "";
    var viewHtml = "";		//预览效果HTML
    if (node.type == 'click') {//菜单动作为点击类型
        tips = msg.d;
        if (node.action_type == 'material') {//数据源从素材读取
            var json = $.xml2json(node.xml_data);
            var msgType = json.MsgType;
            if (msgType == "text") {		//文本消息
                viewHtml = json.Content;
            } else if (msgType == "news") {	//图文消息
                viewHtml = xml2NewsHtml(node.xml_data, node.action_time, node.material_id);
            }
        } else if (node.action_type == 'api') {
            if (!node.app_name) {
                viewHtml = "插件已经被删除，请重新配置";
            } else {
                viewHtml = "从接口【" + node.app_name + "】中获得数据";
            }
        }
    } else if (node.type == 'view') {//菜单动作为点击链接
        tips = msg.e;
        viewHtml = node.url + '<a target="_blamk" class="btn btn-sm btn-white" href="' + node.url + '" role="button">查看</a>';
    }
    $("#view").find(".action_tips").html(tips);
    $("#viewDiv").html(viewHtml);
    showActionContent('view');
}

/**
 * 显示内容
 */
function showActionContent(id) {
    $(".action_content").hide();
    $("#" + id).show();
}


/**
 * 提交菜单响应规则
 * @param {} respType 消息响应类型
 */
function submitMsgActionForm(respType) {

    var menuId = selectNode.id;
    $("#menuId").val(menuId);
    //更新菜单才可能有action
    if (selectNode.action_id) {
        $("#msgActionId").val(selectNode.action_id);
    }
    $("#menuType").val("click");
    $("#materiaMsgType").val(respType);		//响应消息类型
    $("#msgReqType").val(req_type);
    $("#eventType").val(event_type);
    if (respType === 'text') {
        $("#msgActionType").val("material");
        var txtContent = $.trim($("#replyText").val());
        if (!txtContent || "" == txtContent) {
            app.alertModal("请输入要回复的內容");
            return false;
        }
        $("#txtContent").val(txtContent);
    }
    if (respType === 'news') {
        $("#msgActionType").val("material");
        var newsId = $("#newsId").val();
        if (!newsId || newsId == '') {
            app.alertModal("请选择素材");
            return false;
        }
        $("#msgMaterialId").val(newsId);
    }
    if (respType === 'api') {
        $("#msgActionType").val("api");
        var app_id = $.trim($("#busiapp_id").val());
        if (!app_id) {
            app.alertModal("请选择扩展应用");
            return false;
        }
        $("#msgExtAppId").val(app_id);

    }
    if (respType === 'url') {
        $("#menuType").val("view");
        var app_url = $.trim($("#busiapp_url").val());
        if (!app_url || '' == app_url) {
            app.alertModal("请填写URL或选择应用");
            return false;
        }
        $("#menuUrl").val(app_url);
    }
    msgActionForm.submit();
}

function updateMsgView() {
    clearData();
    $("#editType").val("edit");
    $("#msgActionId").val(selectNode.action_id);
    if (selectNode.type == 'click') {//菜单动作为点击类型
        var tabIndex;
        if (selectNode.action_type == 'material') {//数据源从素材读取
            var json = $.xml2json(selectNode.xml_data);
            var msgType = json.MsgType;
            if (msgType == "text") {		//文本消息
                tabIndex = 0;
                $("#replyText").val(json.Content);
            } else if (msgType == "news") {	//图文消息
                tabIndex = 4;
                var viewHtml = xml2NewsHtml(selectNode.xml_data, selectNode.action_time, selectNode.material_id);
                $("#preview_news").html(viewHtml);
            }
        } else if (selectNode.action_type == 'api') {
            tabIndex = 5;
            if (selectNode.app_id) {
                $("#busiapp_id").val(selectNode.app_id);
                $("#label-busiapp_id").val(selectNode.app_name);
            }
        }
        $('#edit_tabs a:eq(' + tabIndex + ')').tab('show');
        showActionContent("action_edit");

    } else if (selectNode.type == 'view') {//菜单动作为点击链接
        $("#busiapp_url").val(selectNode.url);
        showActionContent("action_url");
    }
}
// 排序
function sort() {
    isSort = true;
    $(".dd-handle").removeClass("btn-yellow");
    $("ol").sortable();
    $(".sorted").addClass("hide");
    $(".sort").removeClass("hide");
    $("#action_none").find("p").html(msg.f);
    showActionContent('action_none');
}

// 取消排序
function cancelSort() {
    isSort = false;
    $("#action_none").find("p").html(msg.a);
    $("ol").sortable("destroy");
    $(".sort").addClass("hide");
    $(".sorted").removeClass("hide");
    loadMenu();
}

// 保存排序
function saveSort() {
    var $tree = $("#menu_tree > li");
    if($tree && $tree.length > 0){
        var sortJson = [];
        $.each($tree, function (i,dom) {
            var $dom = $(dom);
            sortJson.push({
                id: $dom.attr("data-id"),
                order_no: i + 1
            });
            var $ctree = $dom.find("li");
            if($ctree && $ctree.length > 0){
                $.each($ctree, function (j,cdom) {
                    sortJson.push({
                        id: $(cdom).attr("data-id"),
                        order_no: j + 1
                    });
                });
            }
        });
        $.ajax({
            url: domain + '/admin/wechat/menu/sort',
            type: 'post',
            data: {
                sortStr: $.toJSON(sortJson)
            },
            dataType: "json",
            cache: false,
            async: true,
            success: function (res) {
                if (res && '1' == res.code) {
                    app.ok("排序完成");
                } else {
                    app.error(res.msg ? res.msg : '排序失败');
                }
            },
            complete: function () {
                cancelSort();
            }
        });
    }else{
        cancelSort();
    }
}
