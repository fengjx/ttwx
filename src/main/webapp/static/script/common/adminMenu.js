/**
 * 后台管理菜单
 * 2016-1-19
 */

app.admin = {
    menu:{
        sidebar: function (isOpen) {
            var $sidebar = $("#sidebar-collapse");
            if (1 == isOpen) {
                $("#side-menu").removeClass("menu-min");
                $sidebar.find("i").addClass("icon-arrow-left");
                $sidebar.find("i").removeClass("icon-arrow-right");
                $.cookie("isOpen", 1, {expires: 7, path: '/'});
            } else {
                $("#side-menu").addClass("menu-min");
                $sidebar.find("i").removeClass("icon-arrow-left");
                $sidebar.find("i").addClass("icon-arrow-right");
                $.cookie("isOpen", 0, {expires: 7, path: '/'});
            }
        },
        loadSecondMenu: function (pid) {
            //second-menu
            $.ajax({
                url: adminPath + '/sys/menu/userMenu',
                data: {
                    pid: pid
                },
                cache: false,
                dataType: "json"
            }).done(function (res) {
                if(data){
                    var html = "";
                    $.each(res, function (i, d) {
                        if(d.is_show){

                        }
                    });
                }else{
                    app.alert("菜单加载失败");
                }
            }).fail(function () {

            });
        }
    }
};

$(function () {
    $(".first-menu").click(function () {
        $(".first-menu").css("color", "");
        $(this).css("color", "#f89406");
        app.admin.menu.loadSecondMenu($(this).attr("data-id"));
    });
});