(function($) {

    $["fn"]["easyPaging"] = function(num, o) {

        if (!$["fn"]["paging"]) {
            return this;
        }

        // Normal Paging config
        var opts = {
            "perpage": 10,
            "elements": 0,
            "page": 1,
            "format": "",
            "lapping": 0,
            "onSelect": function() {
            }
        };

        $["extend"](opts, o || {});

        var $li = $("li", this);

        var masks = {};

        $li.each(function(i) {
            if(0 === i){
                masks.first = this.innerHTML;
                opts.format += "[";
            }else if (1 === i) {
                masks.prev = this.innerHTML;
                opts.format += "<";
            }else if(i === $li.length - 2){
                masks.next = this.innerHTML;
                opts.format += ">";
            } else if (i + 1 === $li.length) {
                masks.last = this.innerHTML;
                opts.format += "]";
            } else {
                masks[i] = this.innerHTML.replace(/#[nc]/, function(str) {
                    opts["format"] += str.replace("#", "");
                    return "([...])";
                });
            }
        });
        //alert(opts["format"]);
        opts["onFormat"] = function(type) {

            var value = "";
            switch (type) {
                case 'block':
                    //value = masks[this["pos"]].replace("([...])", this["value"]);
                    value = this["value"];
                    if (!this['active'])
                        return '<li>' + value + '</li>';
                    if (this["page"] !== this["value"])
                        return '<li><a href="#' + this["value"] + '">' + value + '</a></li>';
                    //return '<li class="am-active">' + value + '</li>';
                    return '<li class="active"><a href="#">' + value + '</a></li>';

                case 'next':
                case 'prev':
                    if (!this['active']){
                        return '<li class="disabled"><a href="#" >' + masks[type] + '</a></li>';
                    }
                    return '<li><a href="#' + this["value"] + '">' + masks[type] + '</a></li>';
                case 'first':
                    if (this.active) {
                        return '<li><a href="#' + this.value + '">'+ masks[type] +'</a></li>';
                    }
                    return '<li class="disabled"><a href="#' + this.value + '">'+ masks[type] +'</a></li>';
                case 'last':
                    if (this.active) {
                        return '<li><a href="#' + this.value + '">'+ masks[type] +'</a></li>';
                    }
                    return '<li class="disabled"><a href="#' + this.value + '">'+ masks[type] +'</a></li>';
            }
        };

        $(this)["paging"](num, opts);

        return this;
    };

}(jQuery));