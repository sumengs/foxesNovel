(function(){$("body").append('<div class="popbox line-box history-box" id="bonusrecord">'+'<span class="closeBoxBtn" onclick="authorBonus.closeBonusRecord()">关闭</span>'+'<div class="line-box-main" id="bonusrecordlist"><p class="userdataloaidng">正在加载 . . .</p></div></div>');$("body").append('<div id="fans2017wrap" class="overWrap" style="display:none;"></div>');window.loginCallBack=function(){location.reload()};window.authorBonus={dom:{body:$("body"),bonusrecord:$("#bonusrecord"),bonusrecordlist:$("#bonusrecordlist"),wrap:$("#fans2017wrap"),bonusCount:$("#bonuscount"),coinCount:$("#coincount"),bonusRule:$(".hongbao-rule")},loading:[],getBonusLoading:false,getBonusRecordLoading:false,getBonusList:function(o,n){if(authorBonus.loading[n])return;authorBonus.loading[n]=true;$.get("/AuthorBonus/GetBonusList",$.param({type:n,index:o}),function(o){authorBonus.loading[n]=false;$("#bonuslist"+n).html(o)})},getRecord:function(o,n,u){if(u){$("#bonusbox").remove();authorBonus.dom.bonusrecord.show()}if(authorBonus.getBonusRecordLoading)return;authorBonus.getBonusRecordLoading=true;$.get("/AuthorBonus/Record",$.param({id:n,index:o}),function(o){authorBonus.getBonusRecordLoading=false;authorBonus.dom.bonusrecordlist.html(o)})},closeBonusRecord:function(){authorBonus.dom.wrap.hide();authorBonus.dom.bonusrecord.hide();authorBonus.dom.bonusrecordlist.html('<p class="userdataloaidng">正在加载 . . .</p>');authorBonus.getBonusRecordLoading=false},getBonus:function(o,n,u,s){if(!window.hasLogin){window.login();return}if(!o||authorBonus.getBonusLoading)return;authorBonus.getBonusLoading=true;$.get("/AuthorBonus/GetBonus",$.param({bonusId:o,bonusTitle:n,bookid:u,bookName:s}),function(o){authorBonus.getBonusLoading=false;if(o=="未登录"){window.login();return}authorBonus.dom.body.append(o);authorBonus.dom.wrap.show();var n=$("#getcoincount");if(n.length>0){authorBonus.dom.bonusCount.html(parseInt(authorBonus.dom.bonusCount.html())+1);authorBonus.dom.coinCount.html(parseInt(authorBonus.dom.coinCount.html())+parseInt(n.val()))}})},closeBonusBox:function(){authorBonus.dom.wrap.hide();$("#bonusbox").remove();authorBonus.getBonusLoading=false},goAction:function(o){if(location.pathname!="/info/"+o+".html"){setTimeout(function(){window.open("/info/"+o+".html")},0)}else{authorBonus.closeBonusBox()}},showBonusRule:function(o){if(o){authorBonus.closeBonusBox();authorBonus.dom.wrap.show();authorBonus.dom.bonusRule.show()}else{authorBonus.dom.wrap.hide();authorBonus.dom.bonusRule.hide()}}}})();