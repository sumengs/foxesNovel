(function(){window.User_LotteryTicket={data:{loading:false,showResultBox:false,result:false,successInfo:[],failMessage:"",ticketNo:"",btnText:"确认兑换"},methods:{closeResultBox:function(){this.showResultBox=false;this.successInfo=[];this.failMessage=""},useTicket:function(){if(this.loading)return;if(this.ticketNo==""){Util.showMsgBox("请输入礼品兑换码");return}this.loading=true;this.btnText="正在兑换";var s=this;ajaxPostService("/service/UseLotteryTicket",{ticketNo:s.ticketNo},function(e,t){s.loading=false;s.btnText="确认兑换";if(t==1){Util.showMsgBox("网络连接异常，请稍后再试");return}if(t==2){Util.showMsgBox("系统错误，请稍后再试");return}if(e.Code==0){s.result=true;s.successInfo=e.List}else{s.result=false;s.failMessage=e.Message}s.showResultBox=true})}}}})();