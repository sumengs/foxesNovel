(function(){window.User_BookOrder={data:{type:-1,hasInitData:false,loading:false,draftDetail:{},detailInfo:{orderId:0,title:"",intro:""},saveLoading:false,draftConfirmBoxShow:false,saveConfirmBoxShow:false,deleteConfirmBoxShow:false,goTabAfterLeaveEdit:3,bookShow:false,bookTab:0,bookSelectList:[],bookInit:[false,false],bookList:[[],[]],bookSearchPageSize:8,bookSearchKeyword:"",bookSearchLoading:[false,false],bookSearchApi:["/partview/GetMyBookCaseSimpleList","/search/GetSearchSimpleList"],bookSearchPageIndex:[-1,-1],bookSearchPageCount:[0,0],deleteBookOrderId:0},mounted:function(){var e=this;bookOrder.draftFunction=function(t){e.getDraft(t)};var e=this;bookOrder.deleteBookOrder=function(t){e.deleteBookOrderId=t;e.deleteConfirmBoxShow=true}},watch:{topdataloading:function(){if(!this.hasInitData&&this.topdataloading[9]){this.hasInitData=true;this.setType(2)}}},methods:{setType:function(e){if(e==0&&this.topviplevel<2){Util.showMsgBox("VIP等级≥2才可以创建书单");return}if(this.type==e)return;if(this.type==0&&this.checkHasDraft()){this.draftConfirmBoxShow=true;this.goTabAfterLeaveEdit=e;return}this.type=e;if(e==2||e==3){bookOrder.setType(e)}},checkHasDraft:function(){return this.detailInfo.title.replace(/\s/g,"")!=""||this.detailInfo.intro.replace(/\s|[\r\n]/g,"")!=""||this.bookSelectList.length>0},setBookSelectBoxShow:function(e){this.bookShow=e;if(e&&!this.bookInit[0]&&!this.bookInit[1])this.setBookSearchTab(0)},setBookSearchTab:function(e){this.bookTab=e;if(!this.bookInit[e]){Vue.set(this.bookInit,e,true);this.searchBook(0)}},selectBook:function(e){var t=this.bookTab;var o=this.bookList[t][e].bookId;var i=!this.bookList[t][e].checked;var s=this.bookList[1-t];if(i){if(this.bookSelectList.length==15){Util.showMsgBox("最多选择15本书");return}this.bookSelectList.push(this.bookList[t][e])}else{for(var r=0;r<this.bookSelectList.length;r++){if(this.bookSelectList[r].bookId==o){this.bookSelectList.splice(r,1);break}}}for(var r=0;r<s.length;r++){if(s[r].bookId==o){s[r].checked=i;break}}this.bookList[t][e].checked=i},cancelSaveDraft:function(){this.initEditBookOrder();this.draftConfirmBoxShow=false;this.setType(this.goTabAfterLeaveEdit)},initEditBookOrder:function(){this.detailInfo={orderId:0,title:"",intro:""},this.bookSelectList=[];this.setTabBookListSelect()},deleteBookListItem:function(e){this.bookSelectList.splice(e,1);this.setTabBookListSelect()},setTabBookListSelect:function(){for(var e=0;e<this.bookList.length;e++){for(var t=0;t<this.bookList[e].length;t++){var o=false;for(var i=0;i<this.bookSelectList.length;i++){if(this.bookSelectList[i].bookId==this.bookList[e][t].bookId){o=true;break}}this.bookList[e][t].checked=o}}},checkBookOrder:function(){var e={message:"",title:"",intro:"",bookList:[]};var t=this.detailInfo.title.replace(/\s/g,"");if(t.length<4||t.length>20){e.message="书单名称在4-20字之间";return e}var o=/((http|https|rtsp|www)|[a-z0-9\-\_]{1,4}[\.]{1,}([a-z0-9\s]{2,5}))*$/gi;var i=this.detailInfo.intro.replace(o,"");var s=i.replace(/\s|[\r\n]/g,"").length;if(s<10||s>1e3){e.message="书单简介在10-1000字之间";return e}if(this.bookSelectList.length<4||this.bookSelectList.length>15){e.message="作品数量在4-15本之间";return e}var r=[];for(var a=0;a<this.bookSelectList.length;a++){var h=this.bookSelectList[a].intro.replace(o,"");var n=h.replace(/\s|[\r\n]/g,"").length;if(n<10||n>500){e.message="作品推荐语在10-500字之间";return e}var l={bookId:this.bookSelectList[a].bookId,intro:h};r.push(l)}e.title=t;e.intro=i;e.bookList=r;return e},beforeSaveBookOrder:function(){var e=this.checkBookOrder();if(e.message!=""){Util.showMsgBox(e.message);return}this.saveConfirmBoxShow=true},saveBookOrder:function(e){if(this.saveLoading)return;var t=this.checkBookOrder();if(t.message!=""){Util.showMsgBox(t.message);return}var o={isDraft:e?0:1,orderId:this.detailInfo.orderId,title:t.title,intro:t.intro,bookList:JSON.stringify(t.bookList)};this.saveLoading=true;var i=this;ajaxPostService("/service/savebookorder",o,function(t,o){i.saveLoading=false;if(o==1){Util.showMsgBox("网络连接异常，请稍后再试");return}if(o==2){Util.showMsgBox("系统错误，请稍后再试");return}if(t.Code==0){Util.showMsgBox("书单保存成功！");e?i.saveConfirmBoxShow=false:i.draftConfirmBoxShow=false;i.initEditBookOrder();i.type=3;bookOrder.initTab(3)}else{Util.showMsgBox(t.Message)}})},getDraft:function(e){if(this.loading)return;this.initEditBookOrder();this.setType(0);this.loading=true;var t=this;ajaxService("/bookorder/GetBookOrderDraftInfo",{id:e},function(e,o){t.loading=false;if(o==1){Util.showMsgBox("网络连接异常，请稍后再试");return}if(o==2){Util.showMsgBox("系统错误，请稍后再试");return}if(e!=""&&e.Info.ID>0){t.detailInfo={orderId:e.Info.ID,title:e.Info.Title,intro:e.Info.Intro.replace("<p>","　　").replace(/<p>/gi,"\n　　")};for(var i=0;i<e.List.length;i++){var s={bookId:e.List[i].BookID,bookName:e.List[i].Title,authorName:e.List[i].AuthorName,select:true,intro:e.List[i].Intro.replace("<p>","　　").replace(/<p>/gi,"\n　　")};t.bookSelectList.push(s)}t.setTabBookListSelect()}})},searchBook:function(e,t){var o=this.bookTab;if(this.bookSearchLoading[o])return;Vue.set(this.bookSearchPageIndex,o,e);Vue.set(this.bookSearchLoading,o,true);Vue.set(this.bookList,o,[]);if(t)Vue.set(this.bookSearchPageCount,o,0);var i=this;var s=o==0?{index:e,size:this.bookSearchPageSize}:{index:e,size:this.bookSearchPageSize,keyword:this.bookSearchKeyword};ajaxService(this.bookSearchApi[o],s,function(e,t){Vue.set(i.bookSearchLoading,o,false);if(t==1){Util.showMsgBox("网络连接异常，请稍后再试");return}if(t==2){Util.showMsgBox("系统错误，请稍后再试");return}if(e!=""){Vue.set(i.bookSearchPageCount,o,e.PageCount);var s=[];for(var r=0;r<e.List.length;r++){var a={bookId:o==0?e.List[r].BookID:e.List[r].ID,bookName:o==0?e.List[r].Title:e.List[r].Title,authorName:o==0?e.List[r].AuthorName:e.List[r].AuthorName,select:false,intro:""};for(var h=0;h<i.bookSelectList.length;h++){if(i.bookSelectList[h].bookId==a.bookId){a.checked=true;break}}s.push(a)}Vue.set(i.bookList,o,s)}})},deleteBookOrder:function(){if(this.loading)return;this.loading=true;var e=this;ajaxService("/service/DeleteBookOrder",{id:e.deleteBookOrderId},function(t,o){e.loading=false;if(o==1){Util.showMsgBox("网络连接异常，请稍后再试");return}if(o==2){Util.showMsgBox("系统错误，请稍后再试");return}if(t.Code==0){Util.showMsgBox("删除成功");$(".bookorderitem"+e.deleteBookOrderId).remove();e.deleteBookOrderId=0;e.deleteConfirmBoxShow=false}else{Util.showMsgBox(t.Message)}})}}}})();