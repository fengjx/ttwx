$(document).ready(function () {
    var isclient = true;
    var ue = UE.getEditor('container');

    $(".element-item").bind("click", function () {
        $(".element-item.selected").removeClass("selected");
        $(this).addClass("selected");
        var html = $(".content", this).html()
        ue.setContent(html, true);
    });

});