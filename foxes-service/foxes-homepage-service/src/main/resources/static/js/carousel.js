!
    function (i) {
        var e = function (e, t) {
            var n = i.extend({},
                i.fn.carousel.defaults, t),
                r = this,
                e = i(e),
                o = e.children(".slides");
            o.children("div").addClass("slideItem");
            var a = o.children(".slideItem"),
                d = a.find("img"),
                h = 0,
                c = 0,
                s = a.length,
                l = !1,
                f = !0;
            this.current = h,
                this.length = s,
                this.init = function () {
                    var i = n;
                    q(),
                    1 == i.directionNav && P(),
                    "none" != i.buttonNav && Y(),
                    1 == i.reflection && W(),
                    1 == i.shadow && B(),
                    1 == i.description && R(),
                    1 == i.autoplay && U(),
                        V()
                };
            var u = function (i) {
                    var e = n,
                        t = s,
                        r = e.frontWidth,
                        o = e.frontHeight;
                    if (0 != i) if ("center" == e.hAlign) if (i > 0 && i <= Math.ceil((t - 1) / 2)) {
                        var a = u(i - 1);
                        r = e.backZoom * a.width,
                            o = e.backZoom * a.height
                    } else {
                        var d = u(t - i);
                        r = d.width,
                            o = d.height
                    } else if (i == t - 1) r = e.frontWidth / e.backZoom,
                        o = e.frontHeight / e.backZoom;
                    else {
                        var a = u(i - 1);
                        r = e.backZoom * a.width,
                            o = e.backZoom * a.height
                    }
                    return {
                        width: r,
                        height: o
                    }
                },
                v = function (i) {
                    var e = n,
                        t = s,
                        r = e.frontWidth,
                        o = e.frontHeight + T(i) + X(i);
                    if (0 != i) if ("center" == e.hAlign) if (i > 0 && i <= Math.ceil((t - 1) / 2)) {
                        var a = u(i - 1);
                        r = e.backZoom * a.width,
                            o = e.backZoom * a.height + T(i) + X(i)
                    } else {
                        var d = v(t - i);
                        r = d.width,
                            o = d.height
                    } else if (i == t - 1) r = e.frontWidth / e.backZoom,
                        o = e.frontHeight / e.backZoom + T(i) + X(i);
                    else {
                        var a = u(i - 1);
                        r = e.backZoom * a.width,
                            o = e.backZoom * a.height + T(i) + X(i)
                    }
                    return {
                        width: r,
                        height: o
                    }
                },
                g = function (i) {
                    var e, t, r = n,
                        o = u(i);
                    return e = o.height * r.vMargin,
                        t = o.width * r.hMargin,
                        {
                            vMargin: e,
                            hMargin: t
                        }
                },
                p = function (i) {
                    var e = n,
                        t = m(i - 1) + (u(i - 1).height - u(i).height) / 2;
                    return "center" != e.hAlign && i == s - 1 && (t = e.top - (u(i).height - u(0).height) / 2),
                        t
                },
                m = function (i) {
                    var e = n,
                        t = e.top,
                        r = g(i).vMargin;
                    return "bottom" == e.vAlign && (t = e.bottom),
                    0 != i && (t = "center" == e.hAlign ? i > 0 && i <= Math.ceil((s - 1) / 2) ? "center" == e.vAlign ? p(i) : p(i) + r : m(s - i) : i == s - 1 ? "center" == e.vAlign ? p(i) : p(i) - r : "center" == e.vAlign ? p(i) : p(i) + r),
                        t
                },
                b = function (i) {
                    var e, t = n,
                        r = s,
                        o = r % 2,
                        a = r / 2,
                        d = g(i).hMargin;
                    return 0 == i ? "center" == t.hAlign ? e = (t.carouselWidth - t.frontWidth) / 2 : (e = t.left, "right" == t.hAlign && (e = t.right)) : "center" == t.hAlign ? i > 0 && i <= Math.ceil((r - 1) / 2) ? (e = b(i - 1) - d, 0 == o && i == a && (e = (t.carouselWidth - v(i).width) / 2)) : e = t.carouselWidth - b(r - i) - v(i).width : e = i == r - 1 ? b(0) - (v(i).width - v(0).width) / 2 - d : b(i - 1) + (v(i - 1).width - v(i).width) / 2 + d,
                        e
                },
                w = function (i) {
                    var e = n,
                        t = s,
                        r = 1,
                        o = t - e.slidesPerScroll;
                    if (o < 2 && (o = 2), "center" == e.hAlign) {
                        var a = (t - 1) / 2,
                            d = o / 2,
                            h = a + 1 - d,
                            c = a + d;
                        0 == i ? r = 1 : (r = e.backOpacity, i >= h && i <= c && (r = 0))
                    } else 0 == i ? r = 1 : (r = e.backOpacity, i < t - o || (r = 0));
                    return r
                },
                y = function (i) {
                    for (var e = new Array,
                             t = n,
                             r = s,
                             o = 0; o < r; o++) {
                        var a = v(o);
                        "left" == t.hAlign ? (e[o] = {
                            width: a.width,
                            height: a.height,
                            top: m(o),
                            left: b(o),
                            opacity: w(o)
                        },
                        "bottom" == t.vAlign && (e[o] = {
                            width: a.width,
                            height: a.height,
                            bottom: m(o),
                            left: b(o),
                            opacity: w(o)
                        })) : (e[o] = {
                            width: a.width,
                            height: a.height,
                            top: m(o),
                            right: b(o),
                            opacity: w(o)
                        },
                        "bottom" == t.vAlign && (e[o] = {
                            width: a.width,
                            height: a.height,
                            bottom: m(o),
                            right: b(o),
                            opacity: w(o)
                        }))
                    }
                    return e[i]
                },
                k = function (i) {
                    var e = h,
                        t = i - e;
                    return i < e && (t += s),
                        t
                },
                x = function (i) {
                    var e = s,
                        t = n.hAlign;
                    return "left" == t || "right" == t ? i == e - 1 ? e - 1 : e - (2 + i) : i >= 0 && i <= (e - 1) / 2 ? e - 1 - i : i - 1
                },
                A = function (i) {
                    var e = n;
                    1 == e.autoplay && 1 == e.pauseOnHover && $()
                },
                M = function (i) {
                    var e = n;
                    1 == e.autoplay && 1 == e.pauseOnHover && 1 == f && U()
                },
                q = function () {
                    var e = n,
                        t = s,
                        r = d;
                    o.css({
                        "width": e.carouselWidth + "px",
                        "height": e.carouselHeight + "px"
                    }).bind("mouseover", A).bind("mouseout", M),
                        i(".description").bind("mouseover", A).bind("mouseout", M);
                    for (var h = 0; h < t; h++) {
                        var c = a.eq(h);
                        c.css(y(k(h))).bind("click", Z),
                            a.eq(k(h)).css({
                                "z-index": x(h)
                            }),
                            r.eq(h).css(u(k(h)));
                        0 == c.css("opacity") ? c.hide() : c.show()
                    }
                },
                O = function (i) {
                    0 == i.css("opacity") && i.hide()
                },
                C = function (i, e, t) {
                    if (1 != l) {
                        var o = n,
                            a = s;
                        1 == e && $(),
                            c = i,
                        c == a && (c = 0),
                        -1 == c && (c = a - 1),
                            o.before(r),
                            I(),
                            f = t
                    }
                },
                I = function () {
                    var i = s;
                    if (1 != l) {
                        if (h == c) return void (l = !1);
                        if (l = !0, S(h), h > c) var e = i - h + c,
                            t = h - c;
                        else var e = c - h,
                            t = h + i - c;
                        dir = e > t ? -1 : 1,
                            h += dir,
                        h == i && (h = 0),
                        -1 == h && (h = i - 1),
                            F(),
                            L(),
                            j(h);
                        for (var n = 0; n < i; n++) H(n)
                    }
                },
                H = function (e) {
                    var t = n,
                        o = a.eq(e),
                        f = k(e);
                    o.show(),
                        o.animate(y(f), t.speed, "linear",
                            function () {
                                O(i(this)),
                                e == s - 1 && (l = !1, h != c ? I() : (r.current = h, E(h), t.after(r)))
                            }),
                        o.css({
                            "z-index": x(f)
                        }),
                        d.eq(e).animate(u(f), t.speed, "linear"),
                    1 == t.reflection && N(t, o, e),
                    1 == t.shadow && D(t, o, e)
                },
                Z = function (e) {
                    var t = i(this);
                    if (t.index() != h) return C(t.index(), !0, !1),
                        !1
                },
                T = function (i) {
                    var e = 0,
                        t = n;
                    return 1 == t.reflection && (e = t.reflectionHeight * u(i).height),
                        e
                },
                W = function () {
                    var e = n,
                        t = a,
                        r = d,
                        o = s,
                        h = e.reflectionOpacity,
                        c = "rgba(" + e.reflectionColor + "," + h + ")",
                        l = "rgba(" + e.reflectionColor + ",1)",
                        f = '<style type="text/css">';
                    f += ".slideItem .gradient {",
                        f += "position:absolute; left:0; top:0; margin:0; padding:0; border:none; width:100%; height:100%; ",
                        f += "background: -moz-linear-gradient(" + c + "," + l + "); ",
                        f += "background: -o-linear-gradient(" + c + "," + l + "); ",
                        f += "background: -webkit-linear-gradient(" + c + "," + l + "); ",
                        f += "background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(" + c + "), to(" + l + ")); ",
                        f += "background: linear-gradient(" + c + "," + l + "); ",
                        f += "} ",
                        f += ".slideItem .reflection {",
                        f += "filter: progid:DXImageTransform.Microsoft.Alpha(style=1,opacity=" + 100 * h + ",finishOpacity=0,startX=0,finishX=0,startY=0,finishY=100)",
                        f += "-ms-filter: progid:DXImageTransform.Microsoft.Alpha(style=1,opacity=" + 100 * h + ",finishOpacity=0,startX=0,finishX=0,startY=0,finishY=100)",
                        f += "}",
                        f += "</style>",
                        i(f).appendTo("head");
                    for (var v = 0; v < o; v++) {
                        var g = r.eq(v).attr("src"),
                            p = u(v);
                        i('<div class="reflection"></div>').css({
                            "position": "absolute",
                            "margin": "0",
                            "padding": "0",
                            "border": "none",
                            "overflow": "hidden",
                            "left": "0",
                            "top": u(v).height + "px",
                            "width": "100%",
                            "height": T(v)
                        }).appendTo(t.eq(v)).append(i('<img src="' + g + '" />').css({
                            "width": p.width + "px",
                            "height": p.height + "px",
                            "left": "0",
                            "margin": "0",
                            "padding": "0",
                            "border": "none",
                            "-moz-transform": "rotate(180deg) scale(-1,1)",
                            "-webkit-transform": "rotate(180deg) scale(-1,1)",
                            "-o-transform": "rotate(180deg) scale(-1,1)",
                            "transform": "rotate(180deg) scale(-1,1)",
                            "filter": "progid:DXImageTransform.Microsoft.BasicImage(rotation=2, mirror=1)",
                            "-ms-filter": "progid:DXImageTransform.Microsoft.BasicImage(rotation=2, mirror=1)"
                        })).append('<div class="gradient"></div>')
                    }
                },
                N = function (i, e, t) {
                    var n = e.children(".reflection"),
                        r = i.speed,
                        o = u(k(t));
                    n.animate({
                            "top": o.height + "px",
                            "height": T(k(t))
                        },
                        r, "linear"),
                        n.children("img").animate(o, r, "linear")
                },
                X = function (i) {
                    var e = 0;
                    return 1 == n.shadow && (e = .1 * u(i).height),
                        e
                },
                z = function (i) {
                    var e = a.eq(i).find(".shadow"),
                        t = e.children(".shadowLeft"),
                        n = e.children(".shadowRight");
                    e.children(".shadowMiddle");
                    return u(i).width - (t.width() + n.width())
                },
                B = function () {
                    var e = a,
                        t = s,
                        r = u(0).width;
                    "center" != n.hAlign && (r = u(t - 1).width);
                    for (var o = 0; o < t; o++) {
                        var d = e.eq(o);
                        i('<div class="shadow"></div>').css({
                            "width": r + "px",
                            "z-index": "-1",
                            "position": "absolute",
                            "margin": "0",
                            "padding": "0",
                            "border": "none",
                            "overflow": "hidden",
                            "left": "0",
                            "bottom": "0"
                        }).append('<div class="shadowLeft"></div><div class="shadowMiddle"></div><div class="shadowRight"></div>').appendTo(d).children("div").css({
                            "position": "relative",
                            "float": "left"
                        }),
                            d.find(".shadow").children(".shadowMiddle").width(z(o))
                    }
                },
                D = function (i, e, t) {
                    e.find(".shadow").children(".shadowMiddle").animate({
                            "width": z(k(t)) + "px"
                        },
                        i.speed, "linear")
                },
                P = function () {
                    var i = e;
                    i.append('<div class="nextButton">></div><div class="prevButton"><</div>'),
                        i.children(".nextButton").bind("click",
                            function (i) {
                                C(h + 1, !0, !1)
                            }),
                        i.children(".prevButton").bind("click",
                            function (i) {
                                C(h - 1, !0, !1)
                            })
                },
                Y = function () {
                    var t = e,
                        r = s,
                        o = "bullet",
                        a = "bulletActive";
                    "numbers" == n.buttonNav && (o = "numbers", a = "numberActive"),
                        t.append('<div class="buttonNav"></div>');
                    for (var d = t.children(".buttonNav"), h = 0; h < r; h++) {
                        var c = "";
                        "numbers" == o && (c = h + 1),
                            i('<div class="' + o + '">' + c + "</div>").css({
                                "text-align": "center"
                            }).bind("click",
                                function (e) {
                                    C(i(this).index(), !0, !1)
                                }).appendTo(d)
                    }
                    var l = d.children("." + o);
                    l.eq(0).addClass(a),
                        d.css({
                            "width": s * l.outerWidth(!0),
                            "height": l.outerHeight(!0)
                        })
                },
                L = function () {
                    var i = n,
                        t = e.children(".buttonNav");
                    if ("numbers" == i.buttonNav) {
                        var r = t.children(".numbers");
                        r.removeClass("numberActive"),
                            r.eq(h).addClass("numberActive")
                    } else {
                        var o = t.children(".bullet");
                        o.removeClass("bulletActive"),
                            o.eq(h).addClass("bulletActive")
                    }
                },
                R = function () {
                    for (var e = i(n.descriptionContainer), t = (e.width(), e.height(), e.children("div")), r = t.length, o = 0; o < r; o++) t.eq(o).hide().css({
                        "position": "absolute",
                        "top": "0",
                        "left": "0"
                    });
                    t.eq(0).show()
                },
                S = function (e) {
                    var t = n;
                    if (1 == t.description) {
                        i(t.descriptionContainer).children("div").eq(e).hide()
                    }
                },
                j = function (e) {
                    var t = n;
                    if (1 == t.description) {
                        i(t.descriptionContainer).children("div").eq(e).show()
                    }
                },
                Q = function () {
                    var e = u(0);
                    i('<div class="spinner"></div>').hide().css(y(0)).css({
                        "width": e.width + "px",
                        "height": e.height + "px",
                        "z-index": s + 3,
                        "position": "absolute",
                        "cursor": "pointer",
                        "overflow": "hidden",
                        "padding": "0",
                        "margin": "0",
                        "border": "none"
                    }).appendTo(o)
                },
                V = function () {
                    Q();
                    var e = u(0);
                    i('<div class="videoOverlay"></div>').hide().css(y(0)).css({
                        "width": e.width + "px",
                        "height": e.height + "px",
                        "z-index": s + 2,
                        "position": "absolute",
                        "cursor": "pointer",
                        "overflow": "hidden",
                        "padding": "0",
                        "margin": "0",
                        "border": "none"
                    }).bind("click", J).appendTo(o),
                        E(h)
                },
                E = function (i) {
                    a.eq(i).children("a").hasClass("video") && o.children(".videoOverlay").show()
                },
                F = function () {
                    var i = o;
                    i.children(".videoOverlay").hide().children().remove(),
                        i.children(".spinner").hide()
                },
                G = function () {
                    var e = o.children(".videoOverlay"),
                        t = a.eq(h).children("a").attr("href"),
                        n = getVideo(t);
                    i("<iframe></iframe>").attr({
                        "width": e.width() + "px",
                        "height": e.height() + "px",
                        "src": n,
                        "frameborder": "0"
                    }).bind("load", K).appendTo(e)
                },
                J = function (e) {
                    G(),
                        o.children(".spinner").show(),
                        i(this).hide(),
                    1 == n.autoplay && ($(), f = !1)
                },
                K = function (i) {
                    var e = o;
                    e.children(".videoOverlay").show(),
                        e.children(".spinner").hide()
                },
                U = function () {
                    intervalProcess = setInterval(function () {
                            C(h + 1, !1, !0)
                        },
                        n.autoplayInterval)
                },
                $ = function () {
                    if (1 == n.autoplay) return void clearInterval(intervalProcess)
                };
            this.prev = function () {
                C(h - 1, !0, !1)
            },
                this.next = function () {
                    C(h + 1, !0, !1)
                },
                this.goTo = function (i) {
                    C(i, !0, !1)
                },
                this.pause = function () {
                    $(),
                        f = !1
                },
                this.resume = function () {
                    1 == n.autoplay && U()
                }
        };
        i.fn.carousel = function (i) {
            for (var t = [], n = 0; n < this.length; n++) this[n].carousel || (this[n].carousel = new e(this[n], i), this[n].carousel.init()),
                t.push(this[n].carousel);
            return t.length > 1 ? t : t[0]
        },
            i.fn.carousel.defaults = {
                hAlign: "center",
                vAlign: "center",
                hMargin: .4,
                vMargin: .2,
                frontWidth: 400,
                frontHeight: 300,
                carouselWidth: 1e3,
                carouselHeight: 360,
                left: 0,
                right: 0,
                top: 0,
                bottom: 0,
                backZoom: .8,
                slidesPerScroll: 5,
                speed: 500,
                buttonNav: "none",
                directionNav: !1,
                autoplay: !0,
                autoplayInterval: 5e3,
                pauseOnHover: !0,
                mouse: !0,
                shadow: !1,
                reflection: !1,
                reflectionHeight: .2,
                reflectionOpacity: .5,
                reflectionColor: "255,255,255",
                description: !1,
                descriptionContainer: ".description",
                backOpacity: 1,
                before: function (i) {
                },
                after: function (i) {
                }
            }
    }(jQuery);
$(function () {
    if ($("#carousel").find(".pic").length > 0) {
        $("#carousel").carousel({
            hAlign: "center",
            hMargin: 0.4,
            frontWidth: 125,
            frontHeight: 171,
            carouselWidth: 125,
            carouselHeight: 171,
            directionNav: !0,
            buttonNav: "bullets",
            autoplay: !0,
            autoplayInterval: 1000 * 3,
            pauseOnHover: !0,
            shadow: !0,
            description: !0
        })
    }
});