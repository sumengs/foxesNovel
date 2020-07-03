(function () {
    window.User_Shelf = {
        data: {
            tabIndex: 2,
            loading: [false, false, false],
            bookCase: [{}, {}, {}],
            pageSize: 20,
            ready: false,
            tabReady: [false, false, false],
            flag: false
        }, watch: {topdataloading: "initData"}, methods: {
            initData: function () {
                if (!this.ready && this.topdataloading[1]) {
                    this.ready = true;
                    this.getMyBookCase()
                }
            }, setTabIndex: function (e) {
                if (this.tabIndex == e) return;
                this.tabIndex = e;
                if (!this.tabReady[e]) {
                    Vue.set(this.tabReady, e, true);
                    this.getMyBookCase()
                }
            }, setPageIndex: function (e) {
                var s = this.tabIndex;
                if (this.bookCase[s].pageIndex == e) return;
                this.bookCase[s].pageIndex = e;
                this.bookCase[s].allSel = false;
                this.bookCase[s].selItems = [];
                if (!this.bookCase[s].pages[e].load) {
                    this.getMyBookCase()
                }
            }, goPageNum: function () {
                var e = this.tabIndex;
                var s = this.bookCase[e].goPageNo;
                if (isNaN(s) || s < 1 || s > this.bookCase[e].pageCount || s - 1 == this.bookCase[e].pageIndex) return;
                this.bookCase[e].goPageNo = s;
                this.setPageIndex(s - 1)
            }, selAll: function () {
                var e = this.tabIndex;
                var s = this.bookCase[e];
                if (s.allSel) {
                    var o = [];
                    for (var a = 0; a < s.pages[s.pageIndex].books.length; a++) {
                        if (s.pages[s.pageIndex].books[a].HasDelete == 0) o.push(s.pages[s.pageIndex].books[a].BookId)
                    }
                    s.selItems = o
                } else {
                    s.selItems = []
                }
            }, delSingleBook: function (e) {
                var s = this.tabIndex;
                var o = this.bookCase[s].pageIndex;
                var a = this.bookCase[s].pages[o].books[e];
                if (a.HasDelete == 1) return;
                this.delBookCase([a.BookId])
            }, delSomeBook: function () {
                this.delBookCase(this.bookCase[this.tabIndex].selItems)
            }, delBookCase: function (e) {
                var s = this.tabIndex;
                if (this.loading[s] || e.length == 0) return;
                Vue.set(this.loading, s, true);
                var o = this;
                ajaxService("/Service/DeleteBookCase", {bookid: e.join(",")}, function (a, t) {
                    Vue.set(o.loading, s, false);
                    if (t == 1) {
                        Util.showMsgBox("网络连接异常，请稍后再试", 1e3);
                        return
                    }
                    if (t == 2) {
                        Util.showMsgBox("系统错误，请稍后再试", 1e3);
                        return
                    }
                    if (a.Code == 0) {
                        Util.showMsgBox("删除成功", 1e3);
                        o.setDeleteStatus(e)
                    } else {
                        if (a.Code == 99) {
                            location.href = "/UserValidator";
                            return
                        }
                        Util.showMsgBox(a.Message, 1e3)
                    }
                })
            }, setDeleteStatus: function (e) {
                var s = [];
                for (var o in e) {
                    s[o] = e[o]
                }
                var a = false;
                for (var t = 0; t < s.length; t++) {
                    a = false;
                    for (var i = 0; i < this.bookCase[0].pages.length; i++) {
                        if (a) break;
                        if (this.bookCase[0].pages[i] && this.bookCase[0].pages[i].books.length > 0) {
                            for (var n = 0; n < this.bookCase[0].pages[i].books.length; n++) {
                                if (this.bookCase[0].pages[i].books[n].BookId == s[t]) {
                                    this.bookCase[0].pages[i].books[n].HasDelete = 1;
                                    a = true;
                                    break
                                }
                            }
                        }
                    }
                    if (this.bookCase[0].selItems.length > 0) {
                        var l = this.bookCase[0].selItems.indexOf(s[t]);
                        if (l >= 0) {
                            this.bookCase[0].selItems.splice(l, 1)
                        }
                    }
                    if (!this.bookCase[1].pages) continue;
                    a = false;
                    for (var i = 0; i < this.bookCase[1].pages.length; i++) {
                        if (a) break;
                        if (this.bookCase[1].pages[i] && this.bookCase[1].pages[i].books.length > 0) {
                            for (var n = 0; n < this.bookCase[1].pages[i].books.length; n++) {
                                if (this.bookCase[1].pages[i].books[n].BookId == s[t]) {
                                    this.bookCase[1].pages[i].books[n].HasDelete = 1;
                                    a = true;
                                    break
                                }
                            }
                        }
                    }
                    if (this.bookCase[1].selItems.length > 0) {
                        var l = this.bookCase[1].selItems.indexOf(s[t]);
                        if (l >= 0) {
                            this.bookCase[1].selItems.splice(l, 1)
                        }
                    }
                }
                this.topaccountinfo.bookCaseCount = this.topaccountinfo.bookCaseCount - s.length
            }, getMyBookCase: function () {
                var e = this.tabIndex;
                if (this.loading[e]) return;
                var s = this.bookCase[e].pageIndex | 0;
                Vue.set(this.loading, e, true);
                var o = this;
                ajaxService("/user/getMyBookCase", {
                    type: this.tabIndex,
                    index: s,
                    size: this.pageSize
                }, function (a, t) {
                    Vue.set(o.loading, e, false);
                    if (t == 1) {
                        Util.showMsgBox("网络连接异常，请稍后再试", 1e3);
                        return
                    }
                    if (t == 2) {
                        Util.showMsgBox("系统错误，请稍后再试", 1e3);
                        return
                    }
                    if (a != "" && a.List.length > 0) {
                        var i = Math.ceil(a.Total / o.pageSize);
                        if (s == 0) {
                            Vue.set(o.bookCase, o.tabIndex, o.initPage(a.List, i))
                        } else {
                            var n = {load: true, books: a.List};
                            Vue.set(o.bookCase[o.tabIndex].pages, s, n)
                        }
                    }
                })

            }, initPage: function (e, s) {
                var o = {pageIndex: 0, pageCount: s, allSel: false, selItems: [], pages: new Array(s), goPageNo: 1};
                for (var a = 0; a < s; a++) {
                    o.pages[a] = {load: false, books: []}
                }
                o.pages[0].load = true;
                o.pages[0].books = e;
                return o
            },showMyBookshelf:function () {
                alert("展示我的书架")
            }
        }
    }
})();