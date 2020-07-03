(function () {
    String.prototype.Val = function (e) {
        var o = this;
        var n = "";
        var i = new RegExp("(^|&)" + e + "=([^&]*)(&|$)", "i");
        var a = o.substr(1).match(i);
        if (a != null) {
            n = unescape(a[2])
        }
        return n
    };
    window.myhead = {
        getCookie: function (e, o) {
            return !o ? myhead.cookieOperate(e) : myhead.cookieOperate(e).Val(o)
        }, cookieOperate: function (e) {
            var o = "";
            if (document.cookie && document.cookie != "") {
                var n = document.cookie.split(";");
                for (var i = 0; i < n.length; i++) {
                    cookie = n[i].replace(/^\s+|\s+$/g, "");
                    if (cookie.substring(0, e.length + 1) == e + "=") {
                        o = unescape(cookie.substring(e.length + 1));
                        break
                    }
                }
            }
            return o
        }, setCookie: function (e, o, n) {
            if (n) {
                var i = new Date;
                i.setTime(i.getTime() + n * 24 * 60 * 60 * 1e3);
                document.cookie = e + "=" + escape(o) + ";expires=" + i.toUTCString() + ";path=/;domain=.xxsy.net;"
            } else {
                document.cookie = e + "=" + escape(o) + ";path=/;domain=.xxsy.net;"
            }
        }, setSecondCookie: function (e, o, n) {
            if (n) {
                var i = new Date;
                i.setTime(i.getTime() + n * 1e3);
                document.cookie = e + "=" + escape(o) + ";expires=" + i.toUTCString() + ";path=/;domain=.xxsy.net;"
            } else {
                document.cookie = e + "=" + escape(o) + ";path=/;domain=.xxsy.net;"
            }
        }, init: function (e) {
            if (e) {
                if (e == "closebox") {
                    try {
                        JQBox.removeBoxForse(function () {
                            loginCallBack()
                        })
                    } catch (e) {
                    }
                } else {
                    loginCallBack()
                }
            }
        }, logout: function () {
            var e = encodeURI("/login/logout?callback=myhead.logoutCallBack&rnd=" + Math.random());
            $.getScript(e)
        }, logoutCallBack: function () {
            loginCallBack()
        }
    };
    window.menuBookCase = {
        _getBookCase: false,
        _getBookMark: false,
        _bookCaseTab: 1,
        _delBaseIndex: -1,
        _loading: false,
        setBookCaseTab: function (e) {
            if (menuBookCase._bookCaseTab == e) return;
            menuBookCase._bookCaseTab = e;
            if (e == 2 && !menuBookCase._getBookMark) {
                menuBookCase.getBookCase(2)
            }
            $("#head_select_" + e).attr("class", "on").siblings(".on").attr("class", "off");
            $("#mybcasecnt" + e).show().siblings(".menumybclist").hide()
        },
        getBookCase: function (o) {
            var n = "";
            if (o == 1) {
                menuBookCase._getBookCase = true;
                n = "GetMyBookCase"
            } else {
                menuBookCase._getBookMark = true;
                n = "GetMyBookMark"
            }
            ajaxService("/partview/" + n, {index: 0, size: 10, type: 1}, function (n) {
                var i = o == 1 ? n.List : n;
                var a = [];
                if (i && i.length > 0) {
                    $(i).each(function (n, i) {
                        a.push('<li class="n0 bookcaseindex' + n + '">' + (n + 1) + '</li><li class="n1 bookcaseindex' + n + '"><a href="/info/' + i.BookId + '.html" id="b_' + i.BookId + '">' + i.BookName + '</span></a></li><li class="n2 bookcaseindex' + n + '">');
                        if (i.LastChapterIsVip == 1) {
                            a.push('<a href="/chapter/' + i.LastChapterId + '.html"><font color=red>[Ｖ]</font>' + i.LastChapterName + "</a>")
                        } else {
                            a.push('<a href="/chapter/' + i.LastChapterId + '.html">&nbsp;' + i.LastChapterName + "</a>")
                        }
                        a.push('&nbsp;<font color="#666666">[' + (o == 1 ? i.UpdateTime : i.InsertTime) + "]</font>");
                        a.push('</li><li class="n3 bookcaseindex' + n + '">' + i.Author + '</li><li class="n4 bookcaseindex' + n + '"><a href="javascript:void(0)"');
                        a.push('onclick="menuBookCase.confirmBox(' + e + "," + i.BookId + "," + o + "," + n + ')" target=_self>' + (o == 1 ? "下架" : "撤销") + "</a></li>")
                    });
                    a.push("<li class=newline><span class=s1> </span><span class=s2>");
                    if (o == 1) {
                        a.push('<a href="/control?t=1" target="_blank">管理我的书架>></a>')
                    } else {
                        a.push('<a href="/control?t=2" target="_blank">管理我的书签>></a>')
                    }
                    a.push('&nbsp;&nbsp;&nbsp;&nbsp;<a target=_self href="javascript:void(0)" onclick="menuBookCase.menuCloseBookCase(2)" title="关闭窗口">关闭x</a></span></li></ul>')
                }
                $("#mybcasecnt" + o).html(a.join(""))
            })
        },
        menuCloseBookCase: function () {
            $("#menucnt2").hide()
        },
        confirmBox: function (e, o, n, a) {
            if (menuBookCase._loading) return;
            var s = "下架";
            var t = "确定下架";
            if (n == 2) {
                s = "的书签撤销"
            }
            var r = '<div class=cfbox-mask id=cfbox-mask></div><div class=cfbox id=cfbox><div class=divbd><div class=cnt id=_divcnt><img src="' + i + '/pic/icon_alert.gif"/></span>确定要将 <font color=#ff0000>《' + $("#b_" + o).text() + "》</font> " + s + '吗？<p><input type=button value="' + t + '" onclick="menuBookCase.delBookCase(' + e + "," + o + "," + n + "," + a + ')" style="' + menuBookCase.btnstyle() + '" /><input type=button value="  取 消 " style="' + menuBookCase.btnstyle() + ';margin-left:20px;" onclick="menuBookCase.closeBox(' + n + ')" /><p></div></div></div>';
            $("#menucnt2").html($("#menucnt2").html() + r)
        },
        closeBox: function () {
            $("#cfbox-mask").remove();
            $("#cfbox").remove()
        },
        delBookCase: function (e, o, n, i) {
            if (e == 0) {
                login();
                return false
            }
            menuBookCase._delBaseIndex = i;
            menuBookCase._loading = true;
            var a = n == 1 ? "DeleteBookCase" : "DeletebookMark";
            ajaxService("/service/" + a, {bookid: o}, function (e, o) {
                menuBookCase._loading = false;
                menuBookCase.closeBox();
                if (o == 1) {
                    Util.showMsgBox("网络连接异常，请稍后再试");
                    return
                }
                if (o == 2) {
                    Util.showMsgBox("系统错误，请稍后再试");
                    return
                }
                if (e.Code == 0) {
                    Util.showMsgBox("删除成功");
                    $("#mybcasecnt" + n).find(".bookcaseindex" + menuBookCase._delBaseIndex).remove()
                } else {
                    Util.showMsgBox(e.Message)
                }
            })
        },
        btnstyle: function () {
            if ($.browser.msie) {
                return "border:solid #FFFFFF 1px;background-image:url(" + i + "/pic/line_1_27.gif);width:auto;height:26px;margin:0 0 0 30px;padding:5px 0 0 0 ;color:#085271; cursor: pointer;font-size:14px;"
            } else {
                return "border:solid #cdcdcd 1px; background-image:url(" + i + "/css/1/btn_line.gif);width:auto;height:24px; color:#085271; cursor: pointer;font-size:14px;padding:3px 10px;"
            }
        },
        addBookCase: function (e, o) {
            if (menuBookCase._loading) return;
            menuBookCase._loading = true;
            ajaxService("/service/AddCollection", {bookid: e}, function (e, n) {
                menuBookCase._loading = false;
                if (n == 1) {
                    Util.showMsgBox("网络连接异常，请稍后再试", 1e3);
                    return
                }
                if (n == 2) {
                    Util.showMsgBox("系统错误，请稍后再试", 1e3);
                    return
                }
                if (e.Code != 0) {
                    Util.showMsgBox(e.Message, 1e3);
                    return
                }
                Util.showMsgBox("加入书架成功", 1e3);
                if (typeof o == "function") o()
            })
        }
    };
    var e = 0;
    var o = "";
    var n = false;
    var i = "http://images.xxsy.net";
    document.domain = "xxsy.net";
    window.hasLogin = false;

    function a() {
        e = myhead.getCookie("xxyc", "userid") || myhead.getCookie("userid");
        o = myhead.getCookie("name4js");
        $("#username").html(o);
        if (e > 0) {
            $("#loginaction").hide();
            $("#userinfo").show();
            window.hasLogin = true
        } else {
            $("#loginaction").show();
            $("#userinfo").hide();
            window.hasLogin = false
        }
    }

    window.loginCallBack = function () {
        location.reload()
    };
    window.login = function (e) {
        if (n) return;
        if ($("#loginbox").length > 0) {
            var o = {isBox: true, showValCode: parseInt($("#isshowvalcode").val()) == 1};
            userLogin.init(o);
            $("#loginbox").show();
            return
        }
        n = true;
        $.get("/loginnew/box", $.param({r: Math.random()}), function (o) {
            n = false;
            $("body").append(o);
            if (e) {
                $.getScript("//images.xxsy.net/js/pc/website/login.js?v=20191118", function () {
                    var e = {
                        isBox: true,
                        showValCode: parseInt($("#isshowvalcode").val()) == 1,
                        loginByQrcodeFirst: parseInt($("#loginbyqrcodefirst").val()) == 1
                    };
                    userLogin.init(e)
                })
            } else {
                $.getScript("//images.xxsy.net/js/pc/website/qqlogin.js", function () {
                    $.getScript("//images.xxsy.net/js/pc/website/login.js?v=20191118", function () {
                        var e = {
                            isBox: true,
                            showValCode: parseInt($("#isshowvalcode").val()) == 1,
                            loginByQrcodeFirst: parseInt($("#loginbyqrcodefirst").val()) == 1
                        };
                        userLogin.init(e)
                    })
                })
            }
        })
    };
    $("#logout").click(function () {
        myhead.logout()
    });
    $("#mybookcase").click(function () {
        if (e == 0) {
            login()
        } else {
            window.open("/control?t=1")
        }
    });
    $("#menubookcase").hover(function () {
        if (e == 0) return;
        $(this).addClass("active");
        $("#menucnt2").show();
        if (!menuBookCase._getBookCase) {
            menuBookCase.getBookCase(1)
        }
    }, function () {
        $("#menucnt2").hide();
        $(this).removeClass("active")
    });
    $("#shenmi").click(function () {
        Util.showMsgBox("敬请期待", 2e3)
    });
    $("#gosearch").click(function () {
        var e = $("#searchkeyword").val();
        var o = "";
        if (e == "") {
            o = $("#searchkeyword").attr("placeholder");
            $("#searchkeyword").val(o)
        }
        setTimeout(function () {
            if (o != "") {
                $("#searchkeyword").val("")
            }
        }, 100)
    });
    $("#headerLogin").click(function () {
        login()
    });
    $(".menu-style .more").hover(function () {
        $(this).addClass("active");
        $(this).find(".sub-menu").show()
    }, function () {
        $(this).removeClass("active");
        $(this).find(".sub-menu").hide()
    });

    function s() {
        try {
            if (!window.hasLogin) return;
            var e = myhead.getCookie("update_ing");
            if (e != "") return;
            var o = myhead.getCookie("alupdate_userinfo");
            if (o != null && o != "") return;
            myhead.setCookie("update_ing", 1, 60);
            ajaxService("/service/UpdateUserLoginState", {}, function (e, o) {
            })
        } catch (e) {
            console.log(e.message, e.name, e.stack)
        }
    }

    a();
    s()
})();