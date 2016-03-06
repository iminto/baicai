//简单焦点轮播
(function ($) {
  $.fn.zBanner=function(options) {
    var dft={
      interval: 5000,
      obj1:'.banner-con',
      obj2:'.banner-nav',
      addnav:null,
      adjnav:false,
      trigger:'c',
      animation:'slideX',
      speed:400,      //运动速度
      arrow:null,     //是否添前进后退按钮
      extraNav:null
    };

    var ops = $.extend(dft,options);
    var box = $(this);
    var conBox=box.find(ops.obj1),
    cWidth=conBox.width(),
    cHeight=conBox.height(),
    con=conBox.children(),
    num=con.length,
    timer=null,
    target=0,
    cur=0,
    isInit=false;
    //
    init();

    if (ops.addnav) {
      //添加导航点，默认不添加
      var nav=box.find(ops.obj2);
      var navBtn='';
      for (var i = 0; i < num; i++) {
        navBtn+='<a>'+i+'</a>';
      };
      nav.append(navBtn);
      nav.find('a').eq(0).addClass('current');
      //设置导航居中
      var fmargin=nav.width()/2;
      nav.css({'margin-left':-fmargin});
      ops.trigger=='c'?ops.trigger='click':ops.trigger='mouseenter';
      nav.on(ops.trigger,'a',function () {
       target=$(this).index();
       userMove(target);
     });
    }

    if (ops.arrow) {
      box.on('click','.prev',function () {
        userMove(-1)
      });
      box.on('click','.next',function () {
        userMove();
      });
    }

    if (ops.extraNav) {
      var extraNav=box.find(ops.extraNav);
      extraNav.find('li').eq(0).addClass('current');
      extraNav.on('mouseenter','li',function () {
       target=$(this).index();
       userMove(target);
     });
    }

    function init () {
      con.eq(0).css({'z-index':2}).addClass('current');
      timer=setInterval(slide,ops.interval);
    }

    function slide (act) {
      if (con.is(":animated")) {return false;}
      cur=getcur(conBox);
      if (act>=0) {
        target=act;
        target<cur?act=-1:act=1;
      }
      else if (act==-1) {
        target-=1;
        if (target<0) { target=num-1; }
      }
      else {
        act=1;
        target+=1;
        if (target==num) { target=0; }
      }
      if (cur==target) {return false;}
      if (ops.addnav) {
       nav.find('a').removeClass('current').eq(target).addClass('current');
     }
     if (ops.extraNav) {
       extraNav.find('li').removeClass('current').eq(target).addClass('current');
     }
     if (ops.animation) {
      switch (ops.animation) {
        case 'slideX':
        cWidth=conBox.width();
        con.eq(cur)
        .removeClass('current')
        .animate({left:-act*cWidth},{duration:ops.speed,queue:false,complete:function () {
          $(this).css({'z-index':1});
        }}).end().eq(target)
        .css({'left':act*cWidth,'z-index':2})
        .animate({left:0},{duration:ops.speed,queue:false})
        .addClass('current');
        break;
        case 'slideY':
        con.eq(cur)
        .removeClass('current')
        .animate({top:-act*cHeight},{duration:ops.speed,queue:false,complete:function () {
          $(this).css({'z-index':1});
        }}).end().eq(target)
        .css({'top':act*cHeight,'z-index':2})
        .animate({top:0},{duration:ops.speed,queue:false})
        .addClass('current');
        break;
        case 'fade':
        if (!isInit) {
          con.eq(cur).siblings().css({'opacity':0});
          isInit=true;
        }
        con.eq(cur).animate({'opacity':0},{duration:600,complete:function () {
            $(this).css({'z-index':1}).removeClass('current');
        }});
        con.eq(target).css({'opacity':0,'z-index':1}).animate({'opacity':1},{duration:600,complete:function () {
            $(this).css({'z-index':2}).addClass('current');
        }});
        break;
        default:
        con.hide().removeClass('current').eq(target).show().addClass('current');
      }
    }
  }

  function userMove (act) {
    clearTimeout(timer);
    slide(act);
    timer=setInterval(slide,ops.interval);
  }

  function getcur(obj) {
    return obj.find('.current').index();
  }

} 
})(jQuery);     

//tab
(function ($) {
  $.fn.zTab=function(options) {
   var dft={
      obj1:'.tabnav',          //导航按钮元素
      obj2:'.tabcon',          //被切换元素
      trigger:'mouseenter', //触发方式，默认点击触发
      curName:'current', //给高亮设置类名
      cur:0,                //初始高亮的顺序，默认第一个
      delay:0,              //触发延时
      auto:null,           //是否自动改变
      animation:null,
      callback : null ,      //回调
      load:null             //首次加载时执行
    };

    var ops=$.extend(dft,options);
    return this.each(function () {
      var self=$(this),       //obj1,obj2的父元素
      nav=self.find(ops.obj1),
      con=self.find(ops.obj2),
      navBtn=nav.children(),
      num=navBtn.length,
      timer=null,
      timer2=null,
      isInit=false;

      //初始化执行
      init();

      navBtn.on(ops.trigger,function () {
        ops.cur=$(this).index();
        clearTimeout(timer);
        clearTimeout(timer2);
        timer=setTimeout(run,ops.delay); 
        return false;
      });

      navBtn.on('mouseleave',function () {
        clearTimeout(timer);
        if (ops.auto) {
          timer2=setInterval(auto,ops.auto.interval);
        }
      });
      //
      function init () {
        ops.trigger=='c'?ops.trigger='click':ops.trigger='mouseenter'; //导航触发方式判定
        run();
        if (ops.auto) {
          timer2=setInterval(auto,ops.auto.interval);
        }
        else {
          run(); 
        }

        if(ops.load){
          ops.load(self,ops.cur,num);
        }
        
        isInit=true;        
      }
      //
      function run () {
        navBtn.removeClass(ops.curName).eq(ops.cur).addClass(ops.curName); //
        if (ops.animation) {
         switch(ops.animation.type) {
            // 结构必须符合要求，目前还不完善
            case 'slideY':
            var conH=con.outerHeight();
            if (isInit) {
              con.parent().animate({marginTop:-conH*ops.cur},{duration:400,queue:false})
            }
            else {
              con.parent().height(conH*num);
            }
            break;
            case 'slideX' :
            var conW=con.outerWidth();
            if (isInit) {
              con.parent().animate({marginLeft:-conW*ops.cur},{duration:400,queue:false})
            }
            else {
              con.parent().width(conW*num);
            } 
            break;               
          }
        }
        else {
           con.hide().eq(ops.cur).show(); //
         } 
         if(ops.callback&&isInit){
          ops.callback(self,ops.cur,num);
        }
      }
      //
      function auto () {
        ops.cur+=1;
        if (ops.cur==num) {ops.cur=0;}
        run();
      }

    });
}   
})(jQuery); 

//slide滑动块
(function ($) {
  $.fn.zslide=function(options) {
    var dft={
      obj:'.slide-con',
      prev:'.prev',
      next:'.next',
      speed:600,
      //默认每次运动，隐藏掉的元素个数 
      per:1,
      //1为判断开始和结束2为无限滚动          
      type:1,         
      direc:'x',
      auto:null
    };
    var ops = $.extend(dft,options);
    var box = $(this);
    var con=box.find(ops.obj),
    nextBtn=box.find(ops.next),
    prevBtn=box.find(ops.prev);
    var conAtom=con.children();
        var tarPos=0,//坐标
        num=conAtom.length,
        conAtomS,
        conSize,
        canMove=false,
        showSize,
        e=0,//可以移动的次数
        cur=0,
        act='';
        //
        init();
        //
        function init () {
         if (ops.direc=='x') {
          conAtomS=conAtom.outerWidth(true);
          showSize=con.parent().width();
          conSize=conAtomS*num;
        }
        else {
          conAtomS=conAtom.outerHeight(true);
          showSize=con.parent().height();
          conSize=conAtomS*num;
        }

        if ((conSize-showSize)>=0) { 
          canMove=true;
        }
        
        if (ops.type==1) {
          if (canMove) {
            e=Math.ceil((conSize-showSize)/(ops.per*conAtomS));
          }
          judge();
        }
        else if (ops.type==2) {
          if (canMove) {
           conAtom.each(function () {
             $(this).clone().appendTo(con);
           });
           conSize*=2;
         }
       }

       if (ops.direc=='x') {
        con.width(conSize);
      }
      else {
        con.height(conSize);
      }
      
    }

    if (canMove) {
      nextBtn.click(function () {
        act='next';
        slide();
      });

      prevBtn.click(function () {
        act='prev';
        slide();
      });

      if (ops.auto) {
        var timer=null;
        timer=setInterval(auto,ops.auto.interval);
      }
    }
    function slide () {
      if (ops.type==1) {
        if (!judge()) { return false; }
        tarPos=-cur*ops.per*conAtomS;
        if (ops.direc=='x') {
          con.animate({'marginLeft':tarPos},{ duration: ops.speed,queue: false});
        }
        else {
          con.animate({'marginTop':tarPos},{ duration: ops.speed,queue: false});
        }
      }
      else if (ops.type==2) {
            if (con.is(":animated")) {return false; }
            /**/
            if (act=='next') {
              cur+=ops.per;
              tarPos=-conAtomS*cur;
                if (ops.direc=='x') {
                  con.animate({'marginLeft':tarPos},{duration:ops.speed,queue:false,complete: function() {
                       for (var i = 0; i <ops.per; i++) {
                            con.children().eq(0).appendTo(con);
                         }
                      $(this).css({'margin-left':0});   
                  }});
                }
                else {
                  con.animate({'marginTop':tarPos},{duration:ops.speed,queue:false,complete: function() {
                       for (var i = 0; i <ops.per; i++) {
                            con.children().eq(0).appendTo(con);
                         }
                      $(this).css({'margin-top':0});   
                  }});
                }
                cur=0;
            }
            else if (act=="prev") {
              cur-=ops.per;
              tarPos=-conAtomS*ops.per;
              if (ops.direc=='x') {
                  for (var i = 0; i <ops.per; i++) {
                      con.children().eq((2*num-1)).insertBefore(con.children().eq(0));
                   }
                  con.css({'margin-left':tarPos}); 
                  con.animate({'marginLeft':0},{duration:400,queue:false});
               }   
                else {
                  for (var i = 0; i <ops.per; i++) {
                      con.children().eq((2*num-1)).insertBefore(con.children().eq(0));
                   }
                 con.css({'margin-top':tarPos}); 
                  con.animate({'marginTop':0},{duration:400,queue:false});
               }
               cur=0;
             }    
           }
        }

      //
      function judge () {
        if (act=='next') {
          if (e==0||cur==e) { 

            return false; 
          }
          cur+=1;
        }
        else if (act=='prev') {
          if (cur==0) { return false; }
          cur-=1;
        }
        else {

        }
          //
          if(cur==0) {
            prevBtn.addClass('none');
          }
          else {
            prevBtn.removeClass('none');
          }

          if (cur==e) {
            nextBtn.addClass('none');
          }
          else {
            nextBtn.removeClass('none');
          }
          return true;
        }
        //
        function auto () {
          act='next';
          slide();
        }
      }  
    })(jQuery);

//页面加载
$(function () {
//我要投资
$(document).on('mouseenter mouseleave','.inv-mue-filter a',function (e) {
  if (e.type=='mouseenter') {
    $(this).addClass('btn-bbc1');
  }
  else if (e.type=='mouseleave') {
    $(this).removeClass('btn-bbc1');
  }
});
//积分商城
  $('.shop-list').on('mouseenter mouseleave','li',function (e) {
    var aniFigure=$(this).find('.figure');
    if (e.type=='mouseenter') {
      aniFigure.stop().animate({'right':0},300);
    }
    else if (e.type=='mouseleave') {
      aniFigure.stop().animate({'right':-20},300);
    }
  });

});
/**/

/*渲染交易进度条*/
function percentbar (obj) {
  obj.each(function () {
    barInner=$(this).find('.bar-inner');
    barInner.each(function () {
      var barWidth=$(this).attr('data-width');
      $(this).animate({'width':barWidth},400);
    });
  });
}
