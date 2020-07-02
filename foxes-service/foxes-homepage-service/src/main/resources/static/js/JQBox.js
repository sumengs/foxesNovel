(function ($) {
    JQBox = {};
    var my = JQBox;
    var imgPath = "js/";
    var zindex = 10000;
    //创建窗体底层元素
    var _options;
    var isIE6 = false;

    my._createBox = function (options, callback) {
        options = $.extend({
            title: "操作窗口",                     //窗体默认标题名
            width: 450,                            //窗体默认宽度
            height: 320,                           //窗体默认高度
            content: "",                           //主元素
            eventObject: null,                     //如果不为null窗体在展示的时候已此目标为起点开始往中间移动（窗体本身有小到大）
            target: "body",
            mask: true,
            cover: false,
            parentbox: null,
            scroll: "hidden",
            overflowy:"hidden",
            speed: 200,
            buttons: { show: false, ok: false, cancle: false, close: false, outhtml: "" },
            clickOK: null,
            okCallBack: null,
            cancleCallBack: null,
            closeCallBack: null,
            boxid: ""
        }, options);

        if ($.browser.msie)
            if ($.browser.version == '6.0') isIE6 = true;

        var BoxID = my._guid();
        options.boxid = BoxID;
        _options = options;
        zindex = zindex + 5;

        var _posX = options.eventObject !== null ? $(options.eventObject).offset().left : (parseInt(($(window).width() - options.width) * 0.5));
        //var _posY = options.eventObject !== null ? $(options.eventObject).offset().top : (($(window).height() - options.height) * 0.5 + $(window).scrollTop() - options.height);
        var _posY = options.eventObject !== null ? $(options.eventObject).offset().top : parseInt(($(window).height() - options.height) * 0.5 + $(window).scrollTop() - 80);
        var _X = options.eventObject !== null ? 0 : options.width - 5;
        var _Y = options.eventObject !== null ? 0 : options.height;
        if (_posY < 0) _posY = 0;


        if (options.cover == false) {
            $('div[id^=jqbox_container_]').remove();
        }
        $(options.target).append('<div id="jqbox_container_' + BoxID + '"><div id="jqbox_mask_' + BoxID + '" style="z-index:' + (zindex - 1) + ';"></div></div>');
        if (options.mask) {
            if (options.cover) {
                $('#jqbox_container_' + BoxID + ' #jqbox_mask_' + BoxID).addClass('x-box-mask-cover');
            } else {
                $('#jqbox_container_' + BoxID + ' #jqbox_mask_' + BoxID).addClass('x-box-mask');
            }
        } else {
            $('#jqbox_container_' + BoxID + ' #jqbox_mask_' + BoxID).addClass('x-box-mask-blank');
        }

        var _boxHtml = [
            '<div id="' + BoxID + '" class="x-box" style="width:' + _X + 'px;height:' + _Y + 'px;top:' + _posY + 'px;left:' + _posX + 'px;z-index:' + (zindex) + '"> ',
            '   <div class="x-box-main x-rs5" style="width:' + (options.width) + 'px;"> ',
            '       <iframe class="x-box-i"></iframe> ',
            '       <div id="' + BoxID + '_title" class="x-box-title" style="width:' + (options.width) + 'px;">',
            '           <span class="x-box-title-msg">' + options.title + '</span>',
            '           <span class="x-box-title-boxs"><a title="关闭" id="' + BoxID + '_close" class="close0" href="javascript:;"></a> </span> ',
            '       </div> ',
            '       <div id="' + BoxID + '_content" class="x-box-content" style="width:' + (options.width) + 'px;height:' + (options.height) + 'px;overflow-y:' + options.overflowy + ';">' + options.content + '</div>',
            '       <div class="x-box-button" id="' + BoxID + '_button"  style="width:' + (options.width) + 'px;"><div class="outhtml"></div><div class="buttondiv">',
            '       </div></div>',
            '   </div>',
            '</div>'
            ].join("");
        $(_boxHtml).appendTo($('#jqbox_container_' + BoxID));

        my.AddItemData(options);


        if (callback) callback(options);
    };
    my.removeAllBox = function (options) {
        $('div[id^=jqbox_container_]').fadeOut(200, function () { $(this).remove(); });
    };
    my._guid = function () {
        //创建一个20位字符的窗体句柄，以确保每次新建的窗体ID是唯一的
        var _str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        var chars = [];
        for (var i = 20; i--; ) { chars.push(_str.charAt(Math.random() * _str.length)); }
        return chars.join("");
    };
    my.resetHtml = function (strhtml) {
        if (strhtml.length > 1)
            $('#' + BoxID + '_content').html(strhtml);
    };

    //添加窗口
    my.AddItemData = function (options) {

        var Aheight = 41;
        if (options.buttons.show) {
            Aheight = 76;

            $('#' + options.boxid + '_button').show().find(".buttondiv").html(function () {
                var s = [];
                if (options.buttons.ok) {
                    s.push('<input type=button name="ok_btn" value="确定" />');
                }
                if (options.buttons.cancle) {
                    s.push('<input type=button name="cancle_btn" value="取消" />');
                }
                if (options.buttons.close) {
                    s.push('<input type=button name="close_btn" value="关闭" />');
                }
                return s.join('');
            });

            options.buttons.outhtml ? $('#' + options.boxid + '_button').find(".outhtml").html(options.buttons.outhtml) : "";

            if (options.buttons.ok) {
                $('#' + options.boxid + '_button').find(':button[name=ok_btn]').click(function () {
                    var r = true;
                    if (options.clickOK) {
                        r = options.clickOK();
                    };
                    if (r == true) {
                        my.removeBox(options, function () {
                            if (options.okCallBack) {
                                options.okCallBack();
                            }
                        });
                    }
                });
            }
            if (options.buttons.cancle) {
                $('#' + options.boxid + '_button').find(':button[name=cancle_btn]').click(function () {
                    my.removeBox(options, function () {
                        if (options.cancleCallBack) {
                            options.cancleCallBack();
                        }
                    });
                });
            }
            if (options.buttons.close) {
                $('#' + options.boxid + '_button').find(':button[name=close_btn]').click(function () {
                    my.removeBox(options);
                });
            }
        }

        

        $('#' + options.boxid).animate({
            width: options.width + 5,
            height: options.height + Aheight,
            top: parseInt(($(window).height() - options.height - Aheight) * 0.5) + $(window).scrollTop(),
            left: parseInt(($(document).width() - options.width) * 0.5),
            opacity: 'show'
        }, options.speed);
       

        if (isIE6) {
            //$('#' + options.boxid).css('top', 'expression(eval(document.documentElement.scrollTop||document.body.scrollTop)+' + parseInt(($(window).height() - options.height - Aheight) * 0.5) + ')');
        }else{
            my.resize(options, Aheight, true);
            $(window).bind('resize', function () { my.resize(options, Aheight, true); });
            $(window).bind('scroll', function () { my.resize(options, Aheight, false); });
        }
        //绑定各种事件
        $('#' + options.boxid + '_close').click(function () {
            my.removeBox(options);
        });
        my._drag(options);
    };
    my.setOutHtml = function (options, outhtml, callback) {
        $('#' + options.boxid + '_button').find(".outhtml").html(outhtml)
    };

    my.getOutHtmlBoxObj = function (options) {
        return $('#' + options.boxid + '_button').find(".outhtml");
    };
    my.resize = function (options, Aheight, moving) {
        if (document.getElementById(options.boxid)) {
            $('#jqbox_mask_' + options.boxid).css({ width: $(window).width() + 'px', height: $(document).height() + 'px', opacity: 0.5 }).fadeIn(options.speed);
            var _top = parseInt(($(window).height() - options.height - Aheight) * 0.5) + $(window).scrollTop();
            if (moving) {
                var _left = parseInt(($(document).width() - options.width) * 0.5);
                $('#' + options.boxid).animate({
                    top: _top,
                    left: _left
                }, options.speed);
            } else {
                $('#' + options.boxid).css({ top: _top });
            }
        }
    };
    //删除层
    my.removeBox = function (options, callback) {
        try {
            if (options.mask) {
                $('#jqbox_container_' + options.boxid + ' #jqbox_mask_' + options.boxid).fadeOut(options.speed, function () { $(this).remove() });
            }
            var _top = parseInt(($(window).height() - options.height) * 0.5 + $(document).scrollTop()) - 80;
            if (_top < 0) _top = 0;
            $('#' + options.boxid).animate({
                top: _top,
                opacity: 'hide'
            }, options.speed, function () {
                $('#jqbox_container_' + options.boxid).remove();
                zindex -= 5;
                if (callback) callback();
                if (options.closeCallBack) options.closeCallBack(options.boxid);
            });
            $(window).unbind("resize scroll");
            // $(document).unbind("click");
        } catch (e) { }
    };

    my.removeBoxForse = function (callback) {
        my.removeBox(_options, callback);
    };

    //元素拖动事件绑定
    my._drag = function (options) {
        var eventObject = document.getElementById(options.boxid + "_title");
        var moveObject = document.getElementById(options.boxid);
        $(eventObject).mousedown(function (e) {
            e = e || event;
            var offsetX = e.clientX - parseInt($(moveObject).css("left")) + $(window).scrollLeft();
            var offsetY = e.clientY - parseInt($(moveObject).css("top")); // + $(window).scrollTop();
            $(document).mousemove(function (e) {
                if (eventObject.setCapture) eventObject.setCapture();
                else if (window.captureEvents) window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);

                e = e || event;
                var left = e.clientX - offsetX;
                var top = e.clientY - offsetY;
                $(moveObject).css({ left: left + "px", top: top + "px" });
                return false;
            });
            $(document).mouseup(function () {
                if (eventObject.releaseCapture) eventObject.releaseCapture();
                $(document).unbind("mousemove mouseup");
            });
            return false;
        });
    };

    //新建HTML窗口
    my.HtmlBox = function (options, callback) {
        my._createBox(options, function (option) {
            if (typeof callback !== "undefined") callback(option);
        });
    };
    //alert 窗口
    my.alert = function (options, callback) {
        options = $.extend({
            height: 80,
            title: "系统提示",
            icon: "confirm",
            speed: 200,
            buttons: { show: true, ok: true }
        }, options);

        var s = [];
        s.push('<table class="x-alert" style="height:' + options.height + 'px;width:' + options.width + 'px;">');
        s.push('<tr><td style="height:' + options.height + 'px;width:52px;"><span class="' + options.icon + '"></span></td><td><span class="msg">' + options.msg + '</span></td></tr>');
        s.push('</table>');
        options.content = s.join('');

        my._createBox(options, function (option) {
            if (typeof callback !== 'undefined') callback(option);
        });
    };

    //confirm 窗口
    my.confirm = function (options, callback) {
        options = $.extend({
            height: 80,
            title: "操作确认",
            icon: "confirm",
            buttons: { show: true, ok: true, cancle: true }
        }, options);

        my.alert(options, function (option) { if (callback) callback(option); });

    };
    ///iframe窗口
    my.FrameBox = function (options, callback) {
        options = $.extend({
            uri: "",
            scroll: 'no'
        }, options);

        var html = [];
        html.push('<div class="x-box-loading" style="width:100%; height:100%; line-height:' + options.height * 0.9 + 'px">正在加载请稍后........</div>');
        html.push('<iframe _id="_content" src=""  style="width:100%; height:' + (options.height-5) + 'px;" scrolling="' + options.scroll + '" frameborder="0" marginheight="0" marginwidth="0"></iframe>');
        options.content = html.join("");

        my._createBox(options, function (option) {
            setTimeout(function () {
                $('#' + option.boxid + '_content').find("iframe[_id=_content]").attr({ src: options.uri }).load(
                function () {
                    $('#' + option.boxid + '_content').find(".x-box-loading").hide();
                    if (typeof callback != 'undefined') callback(option);
                });
            }, 200);
        });

    };

})(jQuery);

