/**
 * 后台管理左侧菜单
 * 2015-8-20
 */
$(function () {
    sidebar($.cookie("isOpen"));
    var curUrl = window.location.href;
    var ul = document.getElementById('side-menu');
    var lis = ul.getElementsByTagName("li");
    for (var i = 0; i < lis.length; i++) {
        var href = lis.item(i).getElementsByTagName("a")[0].getAttribute("href");
        if (-1 !== curUrl.indexOf(href)) {
            lis.item(i).setAttribute("class", "active");
            break;
        }
    }
    $("#sidebar-collapse").click(function () {
        var isOpen = $("#side-menu").hasClass("menu-min");
        sidebar(isOpen ? 1 : 0);
    });

});

function sidebar(isOpen) {
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
}
