(function () {
    var t;
    var e = 0;
    var a;
    var i = [];
    var n = 0;
    var c = 0;
    var r = 0;
    var o;
    var s;
    var u = "";
    var l;
    var h = "";
    var d = "";
    var b = false;
    var f = 2.5;
    var p = $("#chapterlist");
    var v = $("#chapterllisttab");
    var m = 100;
    var t = {};
    var x = false;
    window.initChapterList = function (i, n, c, r, d, b, p) {
        e = n;
        a = i;
        x = d;
        $.get("/partview/GetChapterList", $.param({
            bookid: n,
            noNeedBuy: c ? 1 : 0,
            special: r,
            maxFreeChapterId: b,
            isMonthly: p
        }), function (e) {
            $("#chapterlist").html(e);
            if (i && !c) {
                o = $("#subchaptercount");
                s = $("#subchapterbtn");
                u = $("#subaccounttext");
                l = $("#subneedtext");
                t.account = parseInt($("#subaccount").val());
                t.yuanbao = parseInt($("#subyuanbao").val());
                t.tiyanbi = parseInt($("#subtiyanbi").val());
                f = parseFloat($("#subpricerate").val());
                t.updateAccount = function () {
                    h = "余额<em>" + t.account + "</em>潇湘币";
                    if (t.yuanbao > 0) {
                        h += "，<em>" + t.yuanbao + "</em>元宝"
                    }
                    if (t.tiyanbi > 0) {
                        h += "，<em>" + t.tiyanbi + "</em>体验币"
                    }
                    u.html(h)
                };
                t.updateAccount()
            }
            m = parseInt($("#limitdiscount").val());
            var a = new Date($("#limitdiscounttime").val());
            if (m < 100 && m > 0) {
                C(m, a)
            }
        })
    };
    window.initChapterListNoSub = function (t, a, i) {
        e = t;
        $.get("/partview/GetChapterListNoSub", $.param({bookid: t, isvip: a, maxFreeChapterId: i}), function (t) {
            $("#chapterlist").html(t)
        })
    };
    $("#chapterlist").on("click", ".catalog-main dt .iconfont", function () {
        var t = $(this).parents("dt").next("dd");
        if (t.is(":visible")) {
            $(this).parents("dt").next("dd").slideUp();
            $(this).html("&#xe601;")
        } else {
            $(this).parents("dt").next("dd").slideDown();
            $(this).html("&#xe7cd;")
        }
    });
    $("#chapterlist").on("click", "#selectAllChapter", function () {
        $(this).is(":checked") ? $("#chapter").find(":checkbox").attr("checked", "true") : $("#chapter").find(":checkbox").removeAttr("checked");
        k()
    });
    window.subVolume = function (t) {
        var e = $(":checkbox[name=volume" + t + "]:not(:checked)");
        if (e.length > 0) {
            e.attr("checked", "true")
        } else {
            $(":checkbox[name=volume" + t + "]").removeAttr("checked")
        }
        k()
    };
    $("#chapterlist").on("click", "li :checkbox", function () {
        k()
    });

    function k() {
        i = [];
        n = 0;
        r = 0;
        c = 0;
        _objsubneed = "";
        $("#chapter :checkbox:checked").each(function () {
            if ($(this).is(":checked")) {
                i.push(parseInt($(this).attr("data-chapterid")));
                var t = parseInt($(this).attr("data-price")) * f;
                if (t > 0 && m > 0 && m < 100) {
                    t = Math.floor(t * m / 100);
                    if (t == 0) t = 1
                }
                n += t
            }
        });
        y();
        w()
    }

    function w() {
        o.html(i.length);
        g();
        if (i.length == 0) {
            s.addClass("forbid")
        } else if (s.hasClass("forbid")) {
            s.removeClass("forbid")
        }
    }

    function g() {
        if (c + r >= 0 && i.length > 0) {
            d = m > 0 && m < 100 ? "本次<em>折后</em>订阅需消耗" : "本次订阅需消耗";
            d += "<em>" + n + "</em>点";
            l.html(d)
        } else {
            l.html("")
        }
    }

    function y() {
    }

    function C(t, e) {
        var a = $("#discountinfo");
        var i = setInterval(function () {
            var n = Util.leftTimer(e.getFullYear(), e.getMonth() + 1, e.getDate(), e.getHours(), e.getMinutes(), e.getSeconds());
            if (n.less) {
                var c = "限时" + parseInt(t / 10) + "折中，剩余" + parseInt(24 * n.days + n.hours) + "小时" + n.minutes + "分" + n.seconds + "秒";
                a.html(c)
            } else {
                clearInterval(i);
                a.html("")
            }
        }, 1e3)
    }

    window.goSubscribe = function () {
        if (i.length == 0 || b) return;
        var a = n <= t.yuanbao + t.account || n <= t.tiyanbi;
        var c = "";
        if (x) c = '<p class="bonus-subcribenotice">作者红包发放中，订阅任意金额即有机会领取</p>';
        var r = {
            title: "订阅",
            text: c + d + "<br><span>" + h + "</span>",
            btnText: a ? "确认订阅" : "去充值",
            showCancel: false,
            callback: a ? function (t) {
                subcribe(e, i.join(","), -1, t)
            } : function () {
                window.open("http://recharge.xxsy.net/")
            }
        };
        commonBox.confirmBox(r)
    };
    window.subcribe = function (a, i, n, c) {
        b = true;
        ajaxPostService("/Service/subcribechapter", {bookid: e, arrchapterid: i, auto: n}, function (e, a) {
            b = false;
            if (a == 1) {
                Util.showMsgBox("网络连接异常，请稍后再试", 1e3);
                return
            }
            if (a == 2) {
                Util.showMsgBox("系统错误，请稍后再试", 1e3);
                return
            }
            if (e.Code == 99) {
                location.href = "/UserValidator";
                return
            }
            if (e.Code == 0) {
                var i = e.Data;
                var n = "订阅成功";
                if (i.Mon > 0) {
                    n += ",本次订阅获取月票" + i.Mon + "张"
                }
                if (i.Ass > 0) {
                    n += ",本次订阅获取评价票1张"
                }
                Util.showMsgBox(n, 2e3, true);
                t.account = i.UserAccountBalance;
                t.yuanbao = e.Yuanbao;
                t.tiyanbi = e.Tiyanbi;
                t.updateAccount();
                I();
                c();
                return
            }
            Util.showMsgBox(e.Message, 2e3)
        })
    };

    function I() {
        $("#chapter :checkbox:checked").each(function () {
            $(this).siblings("i").remove();
            $(this).remove()
        });
        $("#selectAllChapter").removeAttr("checked");
        k()
    }

    window.buySpecial = function () {
        JQBox.FrameBox({
            title: "订阅特价书",
            uri: "/payments?q=specialbook&bookid=" + e + "&rnd=" + Math.random(),
            width: 510,
            height: 450
        })
    }
})();