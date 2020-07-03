$(function(){
	
	$('.tab-con .tab-hd h3').hover(function () {
	    var thisIndex = $(this).index();
	    $(this).addClass("active").siblings().removeClass("active");
	    $(this).parents('.tab-con').find('.tab-bd .bd-main').eq(thisIndex).show().siblings().hide();
	})

    //tab
    $('.tab .hd h3').hover(function(){
    	var thisIndex = $(this).index();
    	$(this).addClass("active").siblings().removeClass("active");
    	$(this).parents('.tab').find('.bd .ranking-list').eq(thisIndex).show().siblings('.ranking-list').hide();
    })
    $('.tab .hd span').hover(function(){
    	var thisIndex = $(this).index();
    	$(this).addClass("current").siblings().removeClass("current");
    	$(this).parents('.tab').find('.bd .long-list').eq(thisIndex).show().siblings().hide();
    })

    
})

