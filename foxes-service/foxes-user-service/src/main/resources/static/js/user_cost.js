(function(){window.User_Cost={data:{tabIndex:0,chilrenTabIndex:[0,0,0],costData:[[{},{},{},{},{},{}],[{}],[{}]],loading:[[false,false,false,false,false,false],[false],[false]],ready:[false,false,false],chilrenReady:[true,false,false,false,false,false],apiUrl:[["GetMyChapterSubcribeTodayRecord","GetMyGiftRecord","GetMySpecialOffRecord","GetMyBuyMonthRecord","GetMyYuanbaoCostRecord","GetMyChapterSubcribeHistoryRecord"],["GetMyPayRecord"],["GetMyRewardRecord"]],pageSize:10,isHistory:0},watch:{topdataloading:"initData",isHistory:function(e,t){if(e==1&&!this.chilrenReady[5]){Vue.set(this.chilrenReady,5,true);this.getData(0)}}},methods:{initData:function(){if(!this.ready[0]&&this.topdataloading[4]){Vue.set(this.ready,0,true);this.getData(0)}},getChilrenTabIndex:function(){if(this.tabIndex==0&&this.chilrenTabIndex[0]==0&&this.isHistory==1)return 5;return this.chilrenTabIndex[this.tabIndex]},setTabIndex:function(e){if(this.tabIndex==e)return;this.tabIndex=e;if(!this.ready[e]){Vue.set(this.ready,e,true);this.getData(0)}},setCostTabIndex:function(e){if(this.chilrenTabIndex[0]==e)return;Vue.set(this.chilrenTabIndex,0,e);if(!this.chilrenReady[e]){Vue.set(this.chilrenReady,e,true);this.getData(0)}},setPageIndex:function(e){var t=this.tabIndex;var a=this.getChilrenTabIndex();if(this.costData[t][a].pageIndex==e)return;this.costData[t][a].pageIndex=e;if(!this.costData[t][a].pages[e]){this.getData(e)}},goPageNum:function(){var e=this.tabIndex;var t=this.getChilrenTabIndex();var a=this.costData[e][t].goPageNo;if(isNaN(a)||a<1||a>this.costData[e][t].pageCount||a-1==this.costData[e][t].pageIndex)return;this.costData[e][t].goPageNo=a;this.costData[e][t].pageIndex=a-1;if(!this.costData[e][t].pages[a-1]){this.getData(a-1)}},getData:function(e){var t=this.tabIndex;var a=this.getChilrenTabIndex();if(this.loading[t][a])return;var i="/partview/"+this.apiUrl[t][a];Vue.set(this.loading[t],a,true);var s=this;ajaxService(i,{index:e,size:s.pageSize},function(i,n){Vue.set(s.loading[t],a,false);if(n==1){Util.showMsgBox("网络连接异常，请稍后再试",1e3);return}if(n==2){Util.showMsgBox("系统错误，请稍后再试",1e3);return}if(i!=""&&i.List.length>0){for(var r=0;r<i.List.length;r++){i.List[r].ShowAll=false}if(e==0){var h=t==0&&a==3;Vue.set(s.costData[t],a,s.setPage(i.PageCount,i.List,h))}else{s.costData[t][a].pages[e]=i.List}}})},setPage:function(e,t,a){var i={pageIndex:0,pageCount:e,pages:[],goPageNo:1};if(a){var s=[];for(var n=0;n<t.length;n++){if(s.length==this.pageSize){i.pages.push(s);s=[]}t[n].HasDelete=false;s.push(t[n])}i.pages.push(s)}else{i.pages=new Array(e);i.pages[0]=t}return i}}}})();