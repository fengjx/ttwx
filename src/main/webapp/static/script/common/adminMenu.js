/**
 * 后台管理菜单
 * 2016-1-19
 */

$(function () {
    $("#mainFrame").load(function () {
        cmainFrame();
    });

    $(window).resize(function(){
        cmainFrame();
    });

    $(".first-menu").click(function () {
        $(".first-menu").css("color", "");
        $(this).css("color", "#f89406");
        loadSecondMenu($(this).attr("data-id"));
    });

    $("#side-menu").on('click', '#sidebar-collapse', function () {
        var isOpen = $("#side-menu").hasClass("menu-min");
        sidebar(isOpen ? 1 : 0);
    });

    $("#side-menu").on('click', 'a', function () {
        var $this = $(this);
        var href = $(this).attr("data-href");
        var next = $(this).attr("data-next");
        if ('true' == next || '' == href) {
            var isOpen = $this.parent().hasClass("open");
            if(isOpen){
                $this.parent().removeClass("open");
                $this.nextAll("ul").css("display","none");
            }else{
                $this.parent().addClass("open");
                $this.nextAll("ul").css("display","block");
            }
        }else{
            $("#mainFrame").attr("src", href);
            $("#side-menu").find("li").removeClass("active");
            var parent = $this.parent();
            $(parent).addClass("active");
            if (parent.parent().hasClass("submenu")) {
                parent.parent().parent().addClass("open");
                parent.parent().css("display", "block");
            }
        }
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


    loadSecondMenu($(".first-menu:eq(0)").css("color", "#f89406").attr("data-id"));
});

function loadSecondMenu(pid) {
    if (!pid) {
        return false;
    }
    $.ajax({
        url: adminPath + '/leftMenu',
        data: {
            pid: pid
        },
        cache: false,
        dataType: "html"
    }).done(function (res) {
        $("#side-menu").html(res);
    }).fail(function () {
    });
}

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

function cmainFrame() {
    var ifm = $("#mainFrame");
    var bheight = document.documentElement.clientHeight;
    ifm.width("100%");
    ifm.height((bheight - 50) + 'px');
}