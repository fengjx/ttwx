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
                url: adminPath + '/leftMenu',
                data: {
                    pid: pid
                },
                cache: false,
                dataType: "html"
            }).done(function (res) {
                $("#left-menu").html(res);
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

    $("#left-menu").on('click','.first-menu', function () {
        $(".first-menu").css("color", "");
        $(this).css("color", "#f89406");
        app.admin.menu.loadSecondMenu($(this).attr("data-id"));
    });

    $("#left-menu").on('click','#sidebar-collapse', function () {
        var isOpen = $("#side-menu").hasClass("menu-min");
        app.admin.sidebar(isOpen ? 1 : 0);
    });

});