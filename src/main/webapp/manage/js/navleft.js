/*分页JS*/
var rsss = false;
$(function () {
    $(".leftNav_side").css("min-height", $(".leftNav_side").height());
    $(window).resize(function () {
        $(".leftNav_side").height($(window).height());
    }).trigger("resize");//左侧菜单高度自适应,但是保留内容最小高度

    //切换左导航一级菜单 
    $(".Nav_lvl dt").click(function () {
        $(this).parent().siblings().find("dd").hide();
        $(this).siblings().slideDown(300);

    });
    //切换左导航二级菜单  
    $(".Nav_lvl dd").click(function () {
        $(".Nav_lvl dd").removeClass();
        $(this).addClass("Nav_lvl_dd_on");

    });

    //切换顶部导航
    $(".topNav_ul li").click(function () {
        $(this).addClass("topNav_li_on").siblings().removeClass();
    });
    
    if(Number($("[name='totalCount']").val()) > 0){
	    var pages = [],
	    totalPage = Number($("[name='totalPage']").val()),
	    totalCount = Number($("[name='totalCount']").val()),
	    currentPage = Number($("[name='pageNum']").val())==0 ? 1 :Number($("[name='pageNum']").val());
	    pages[pages.length] = ' <th colspan="100"><i>当前第'+currentPage+'页/共'+totalPage+'页</i><i>共'+totalCount+'条记录</i>';
	    if (currentPage == 1) {
	    	pages[pages.length] = ' <span>首页</span><span>上一页</span>';
	    }
	    else {
	    	pages[pages.length] = ' <a class="first" href="#">首页</a><a class="prev" href="#">上一页</a>';
	    }
	    if (currentPage < 5) {
	        for (var i = 1; i <= (totalPage > 10 ? 10 : totalPage); i++) {
	            if (currentPage == i)
	            	pages[pages.length] = '<span  class="sel">' + i + '</span>';
	            else
	            	pages[pages.length] = '<a href="#">' + i + '</a>';
	        }
	    }
	    else if (currentPage >= totalPage - 5)
	    	for (var i = totalPage - 9; i <= totalPage; i++) {
	            if (currentPage == i)
	            	pages[pages.length] = '<span class="sel">' + i + '</span>';
	            else
	            	pages[pages.length] = '<a href="#">' + i + '</a>';
	        }
	    else {
	        for (var i = currentPage - 5; i <= currentPage + 4; i++) {
	            if (currentPage == i)
	            	pages[pages.length] = '<span class="sel">' + i + '</span>';
	            else
	            	pages[pages.length] = '<a href="#">' + i + '</a>';
	        }
	    }
	
	    if (currentPage < totalPage) {
	    	pages[pages.length] = '<a class="next" href="#">下一页</a><a class="last" href="#">尾页</a>';
	    }
	    else {
	    	pages[pages.length] = '<span>下一页</span><span>尾页</span>';
	    }
	    pages[pages.length] = '<input type="text" name="page" value="'+currentPage+'"/>';
	    pages[pages.length] = '<input type="button" value="跳转" class="btn_violet" />';
	    $(".pager").html(pages.join("")).find("a:not(.next):not(.prev)").click(function(){
	    	$("[name='currentPage']").val($(this).text());
	    	$("#pagerForm").submit();
	    });
	    $(".pager").find("a.first").click(function(){
	    	num = 1;
	    	$("[name='currentPage']").val(num);
	    	$("#pagerForm").submit();
	    });
	    $(".pager").find("a.prev").click(function(){
	    	num = Number($("[name='currentPage']").val()) - 1 < 0 ? 0 :Number($("[name='currentPage']").val()) - 1;
	    	$("[name='currentPage']").val(num);
	    	$("#pagerForm").submit();
	    });
	    $(".pager").find("a.next").click(function(){
	    	$("[name='currentPage']").val(Number($("[name='currentPage']").val()) + 1);
	    	$("#pagerForm").submit();
	    });
	    $(".pager").find("a.last").click(function(){
	    	num = Number($("[name='totalPage']").val());
	    	$("[name='currentPage']").val(num);
	    	$("#pagerForm").submit();
	    });
	    $(".pager").find("input.btn_violet").click(function(){
	    	num = Number($("[name='page']").val());
	    	if(num > totalPage){
	    		num = totalPage;
	    	} else if(num < 1){
	    		num = 1;
	    	}
    		$("[name='currentPage']").val(num);
	    	$("#pagerForm").submit();
	    });
    }
});
