/**
 * 后台管理左侧菜单
 * 2015-8-20
 */

window.onload = function () {
    var curUrl = window.location.href;
    var ul = document.getElementById('side-menu');
    var lis = ul.getElementsByTagName("li");
    for(var i = 0; i < lis.length; i++){
        var href = lis.item(i).getElementsByTagName("a")[0].getAttribute("href");
        if(-1 !== curUrl.indexOf(href)){
            lis.item(i).setAttribute("class", "active");
            break;
        }
    }
};