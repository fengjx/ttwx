/**
 * 后台管理左侧菜单
 * 2015-8-20
 */
$(function () {
    sidebar($.cookie("isOpen"));
    var curUrl = window.location.href;
    var $ul = $("#side-menu");
    var $lis = $ul.find("li");
    $lis.each(function (i, li) {
        var href = $(li).find("a").attr("href");
        if (curUrl == href) {
            $(li).addClass("active");
            if($(li).parent().hasClass("submenu")){
                $(li).parent().parent().addClass("open");
                $(li).parent().css("display","block");
            }
            return;
        }
    });

    $("#sidebar-collapse").click(function () {
        var isOpen = $("#side-menu").hasClass("menu-min");
        sidebar(isOpen ? 1 : 0);
    });

    $("li > a").has("b").click(function () {
        var $this = $(this);
        var isOpen = $this.parent().hasClass("open");
        if(isOpen){
            $this.parent().removeClass("open");
            $this.nextAll("ul").css("display","none");
        }else{
            $this.parent().addClass("open");
            $this.nextAll("ul").css("display","block");
        }
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
