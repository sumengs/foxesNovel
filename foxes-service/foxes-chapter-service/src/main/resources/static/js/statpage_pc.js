(function(){window.statPcPage={api:"https://c.xxsy.net/clickdaily/get",eventApi:"https://c.xxsy.net/eventclick/get",userSignCookieName:"pcstatpageusersign",userIdCookieName:"userid",ispc:0,domain:"xxsy.net",urlparten:{recharge:/recharge.xxsy.net/i,domain:/xxsy.net/i},constId:{recharge:135,home:39,channel:156,info:118,chapter:122,subcribe:153,samebook:168},types:[{id:156,name:"分类",parten:/category/i},{id:118,name:"详情",parten:/info/i},{id:127,name:"改编频道",parten:/ip/i},{id:130,name:"搜索",parten:/search/i},{id:126,name:"专题详情",parten:/special\/detail/i},{id:125,name:"专题",parten:/special/i},{id:133,name:"书单详情页",parten:/bookorderdetail/i},{id:132,name:"书单页",parten:/bookorder/i},{id:134,name:"包月",parten:/baoyue/i},{id:93,name:"个人中心",parten:/control/i},{id:122,name:"阅读",parten:/chapter/i},{id:124,name:"作者主页",parten:/authorcenter/i},{id:158,name:"作者红包广场",parten:/authorbonus/i},{id:138,name:"注册",parten:/reg/i},{id:137,name:"登录",parten:/loginnew/i},{id:113,name:"排行榜",parten:/rank/i},{id:192,name:"忘记密码",parten:/findpwd/i}],uploadPageInfo:function(){if(!this.urlparten.domain.test(location.href))return;var e=this.getLastPageId();if(this.isRecharge(location.href)){this.upload(this.getParams(e,this.constId.recharge,""))}else{var t=location.pathname;var i=this.getPageId(t,true);if(i==192){this.uploadStaticPageInfo(192,true,"",new Date,null);return}if(i>0)this.upload(this.getParams(e,i,t))}},uploadStaticPageInfo:function(e,t,i,a,r){if(!this.urlparten.domain.test(location.href)||e<=0)return;var n=this.getLastPageId();var o=this.getParams(n,e,"");o.iserr=t?0:1;o.msg=i;o.qt=this.timeFormat(a);if(r){o.rt=this.timeFormat(r);o.lt=o.qt.getTime()-o.rt.getTime()}else{o.lt=0}this.upload(o,true)},getParams:function(e,t,i){var a=window.performance.timing;var r=a?a.domComplete-a.navigationStart:0;var n={ispc:this.ispc,rid:e,pid:t,uid:this.getUserId(),udid:this.getUserSign(),sid:0,bid:0,cid:0,lt:r};if(e==this.constId.info&&/samebook/i.test(location.search)){n.rid=this.constId.samebook}if(t==this.constId.channel){n.sid=i.match(/\d+/)[0]}else if(t==this.constId.info){n.bid=i.match(/\d+/)[0]}else if(t==this.constId.chapter||t==this.constId.subcribe){n.bid=$("#page_bookid").val();n.cid=i.match(/\d+/)[0]}return n},getLastPageId:function(){var e=document.referrer;if(e!=""&&this.urlparten.domain.test(e)){if(this.isRecharge(e))return this.constId.recharge;var t=e.split(this.domain)[1].split("?")[0];return this.getPageId(t,false)}else{return 0}},isRecharge:function(e){return this.urlparten.recharge.test(e)},getPageId:function(e,t){if(e=="")return 0;if(e=="/")return this.constId.home;for(var i=0;i<this.types.length;i++){if(this.types[i].parten.test(e)){if(this.types[i].id==this.constId.chapter&&t&&$(".popbox_chapter").length>0){return this.constId.subcribe}else{return this.types[i].id}}}return 0},upload:function(e,t){var i=t?this.eventApi:this.api;$.ajax({type:"get",url:i,data:e,dataType:"jsonp"})},getUserId:function(){return this.getCookie(this.userIdCookieName)||0},getUserSign:function(){var e=this.getCookie(this.userSignCookieName);if(e==""){e=this.getRandomStr(32);this.setCookie(this.userSignCookieName,e,999)}return e},getRandomStr:function(e){var t="0123456789abcdefghijklmnopqrstuvwxyz";var i=t.length;var a="";for(var r=0;r<e;++r){a+=t.charAt(Math.floor(Math.random()*i))}return a},getCookie:function(e,t){return!t?this.cookieOperate(e):this.cookieOperate(e).Val(t)},cookieOperate:function(e){var t="";if(document.cookie&&document.cookie!=""){var i=document.cookie.split(";");for(var a=0;a<i.length;a++){cookie=i[a].replace(/^\s+|\s+$/g,"");if(cookie.substring(0,e.length+1)==e+"="){t=unescape(cookie.substring(e.length+1));break}}}return t},setCookie:function(e,t,i){if(i){var a=new Date;a.setTime(a.getTime()+i*24*60*60*1e3);document.cookie=e+"="+escape(t)+";expires="+a.toUTCString()+";path=/;domain=."+this.domain+";"}else{document.cookie=e+"="+escape(t)+";path=/;domain=."+this.domain+";"}},timeFormat:function(e){if(!e)return"";var t="yyyy-MM-dd HH:mm:ss";var i=t.replace("yyyy",e.getFullYear()).replace("MM",(e.getMonth()+1).toString().fill(2,"0")).replace("dd",e.getDate().toString().fill(2,"0")).replace("HH",e.getHours().toString().fill(2,"0")).replace("hh",e.getHours()>12?(e.getHours()-12).toString().fill(2,"0"):e.getHours().toString().fill(2,"0")).replace("mm",e.getMinutes().toString().fill(2,"0")).replace("ss",e.getSeconds().toString().fill(2,"0"));return i}};setTimeout(function(){try{statPcPage.uploadPageInfo()}catch(e){}},300)})();