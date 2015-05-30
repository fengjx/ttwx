/**
 * by fengjx
 * 2015-5-28
 */
(function( $ ){

    $.fn.iscrollPage = function(options) {

        var opts = $.extend($.fn.iscrollPage.defaults, options);
        var target = opts.scrollTarget;
        if (target == null){
            target = obj;
        }
        opts.scrollTarget = target;

        return this.each(function() {
            $.fn.iscrollPage.init($(this), opts);
        });

    };

    $.fn.stopiscrollPage = function(){
        return this.each(function() {
            $(this).attr('iscrollPage', 'disabled');
        });

    };

    $.fn.iscrollPage.loadContent = function(obj, opts){
        var target = opts.scrollTarget;
        var mayLoadContent = $(target).scrollTop()+opts.heightOffset >= $(document).height() - $(target).height();
        if (mayLoadContent){
            if (opts.beforeLoad != null){
                opts.beforeLoad();
            }
            $(obj).children().attr('rel', 'loaded');
            $.ajax({
                type: 'POST',
                url: opts.contentPage,
                data: opts.contentData,
                success: function(data){
                    $(obj).append(data);
                    var objectsRendered = $(obj).children('[rel!=loaded]');

                    if (opts.afterLoad != null){
                        opts.afterLoad(objectsRendered);
                    }
                },
                dataType: 'html'
            });
        }

    };

    $.fn.iscrollPage.init = function(obj, opts){
        var target = opts.scrollTarget;
        $(obj).attr('iscrollPage', 'enabled');

        $(target).scroll(function(event){
            if ($(obj).attr('iscrollPage') == 'enabled'){
                $.fn.iscrollPage.loadContent(obj, opts);
            }
            else {
                event.stopPropagation();
            }
        });

        $.fn.iscrollPage.loadContent(obj, opts);

    };

    $.fn.iscrollPage.defaults = {
        'contentPage' : null,
        'contentData' : {},
        'beforeLoad': null,
        'afterLoad': null	,
        'scrollTarget': null,
        'heightOffset': 0
    };
})( jQuery );