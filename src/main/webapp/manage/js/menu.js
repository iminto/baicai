//leftMenu
var initScrollt = 0, inScroll = false;

function initScroll() { //初始化滚动条
    var _over = $(".overview"), _view = $(".viewport");
    $(".leftMenu_main").tinyscrollbar_update("relative");

    if (_over.height() > _view.height() && inScroll) {
        $(".scrollbar .thumb").fadeIn();
    }
    else {
        $(".scrollbar .thumb").fadeOut();
    }
}
document.onselectstart = function () { return false; }
$(function () {
    var leftMenu = $(".leftMenu_main");
    if (leftMenu.length != 0) {
        var wrap_url = $("body").attr("re");
        $("body").append(["<form id='re_form' action='", wrap_url, "' target='mainFrame' method='post'></form>"].join(""));
        $("#re_form").submit();

        var clickTimer;

        $(".nav_header").click(function () {
            var _menu = $(this).next("ul");
            if (!_menu.is(":animated")) {
                if (_menu.is(":hidden")) {
                    clickTimer = setTimeout(function () { _menu.slideDown(300, function () { initScroll(); }); }, 200);
                }
                else
                    _menu.slideUp(300, function () { initScroll(); });
            }
        }).dblclick(function () {//双击全部展开或全部收起
            clearTimeout(clickTimer);
            if ($(".nav_list:visible").length != 0)
                $(".nav_list").slideUp(300, function () { initScroll(); });
            else
                $(".nav_list").slideDown(300, function () { initScroll(); });
        });
        //点击底部块全部闪开或全部收起
        $(".nav_shadow").click(function () {
            if ($(".nav_list:visible").length != 0)
                $(".nav_list").slideUp(300, function () { initScroll(); });
            else
                $(".nav_list").slideDown(300, function () { initScroll(); });
        })

        $(".nav_list li").click(function () {
            $(".nav_list li").removeClass("sel");
            $(this).addClass("sel").parent("ul").prev(".nav_header").addClass("sel").siblings(".nav_header").removeClass("sel");
        })
        $(".nav_list li:last-child").css("border-bottom", "none");

        leftMenu.tinyscrollbar({ wheel: 20 });

        $(".scrollbar .thumb").hide();
        $(window).resize(function () {
            $(".viewport").height($(window).height())
            initScroll();
        }).trigger("resize");

        leftMenu.hover(function () {
            if ($(".scrollbar .thumb").height() == $(window).height())
                return;
            inScroll = true;
            $(".scrollbar .thumb").fadeIn();
        }, function () {
            inScroll = false;
            $(".scrollbar .thumb").fadeOut();
        })
    }
})

//topMenu
$(function () {
    $(".top_nav li:not(.btn)").click(function () {
        $(this).addClass("sel").siblings(":not(.btn)").removeClass();
    })

    //点击左侧块收起/展开顶部菜单(没什么意义!)
    $(".top_nav li.btn").toggle(function () {
        $(".top_nav li:not(.btn)").animate({ "margin-left": -200 });
        $(this).addClass("sel")
    }, function () {
        $(".top_nav li:not(.btn)").animate({ "margin-left": 0 });
        $(this).removeClass("sel")
    })
})

//arrow
$(function () {
    var f_width = 240, interval, arrow = $(".arrow");
    arrow.toggle(function () {
        interval = setInterval(function () {
            window.top.document.getElementById('center').cols = f_width + ",10,*";
            if (f_width == 0) {
                clearInterval(interval);
                arrow.removeClass("close").addClass("open");
            }
            f_width = f_width - 10;
        }, 10);
    }, function () {
        interval = setInterval(function () {
            window.top.document.getElementById('center').cols = f_width + ",10,*";
            if (f_width == 240) {
                clearInterval(interval);
                arrow.removeClass("open").addClass("close");
            }
            f_width = f_width + 10;
        }, 10);
    })
})