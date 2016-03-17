/**
 * 角色编辑
 */

var roleEdit;

function init() {
    roleEdit = {
        setting: {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parent_id",
                    rootPId: null
                },
                key: {
                    url: "xUrl"
                }
            },
            check: {
                enable: true,
                chkStyle: "checkbox"
            }
        },
        $menuTree: {},
        loadMenuTree: function () {
            $.ajax({
                url: adminPath + '/sys/menu/treeNode',
                cache: false,
                dataType: "json"
            }).done(function (zNodes) {
                roleEdit.$menuTree = $.fn.zTree.init($("#menuTree"), roleEdit.setting, zNodes);
                // 选中已授权菜单
                roleEdit.selectCheckNode($("#menuIds").val());
                // 默认展开全部节点
                roleEdit.$menuTree.expandAll(true);
                app.closeDialog();
            }).fail(function () {
                app.error("菜单加载失败");
            });
        },
        selectCheckNode: function (menuIds) {
            if (menuIds && menuIds !== '') {
                var ids = menuIds.split(',');
                for (var i = 0; i < ids.length; i++) {
                    var node = roleEdit.$menuTree.getNodeByParam("id", ids[i]);
                    try {
                        roleEdit.$menuTree.checkNode(node, true, false);
                    } catch (e) {
                    }
                }
            }
        }
    }

}

$(function () {

    init();

    roleEdit.loadMenuTree();

    $("#btn-add").click(function () {
        append();
    });

    $("#data-form").validate({
        submitHandler: function (form) {
            var ids = [], nodes = roleEdit.$menuTree.getCheckedNodes(true);
            for (var i = 0; i < nodes.length; i++) {
                ids.push(nodes[i].id);
            }
            $("#menuIds").val(ids);
            $(form).ajaxSubmit({
                url: adminPath + "/sys/role/save",
                dataType: 'json',
                data: {},
                success: function (res) {
                    if (res && '1' == res.code) {
                        app.alertModal(res.msg ? res.msg : "保存成功！", function () {
                            window.location.href = adminPath + "/sys/role";
                        });
                    } else {
                        app.alertModal(res.msg ? res.msg : "保存失败！");
                    }
                }
            });
        }
    });
});
