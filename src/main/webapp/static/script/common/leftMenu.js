/**
 * 后台管理左侧菜单
 * 2015-8-20
 */

window.onload = function () {
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
        var $this = $(this);
        if (isOpen) {
            $("#side-menu").removeClass("menu-min");
            $this.find("i").addClass("icon-arrow-left");
            $this.find("i").removeClass("icon-arrow-right");
        } else {
            $("#side-menu").addClass("menu-min");
            $this.find("i").removeClass("icon-arrow-left");
            $this.find("i").addClass("icon-arrow-right");
        }
    });

};