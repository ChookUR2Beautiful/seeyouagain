/*提示组件*/
function tipsComponent(c) {
    this.i = 1;
}

tipsComponent.prototype.show = function(content) {
    $('body').append('<div class="tipsmodle"><p>' + content + '</p></div>');
    var twidth = $('.tipsmodle').css('width');
    $('.tipsmodle').css({ 'marginLeft': -(parseInt(twidth) + 20) / 2 + "px", left: "50%" });
    this.showAction();
}

tipsComponent.prototype.showAction = function() {
    var _this = this;
    $('.tipsmodle').animate({ opacity: "0.9", bottom: "25%" }, 100, "linear", function() {
        var tips_index = this;
        setTimeout(function() {
            _this.destoryTips(tips_index);
        }, 1000);
    });
}

tipsComponent.prototype.destoryTips = function(_this) {

    $(_this).animate({ opacity: "0", bottom: "30%" }, 100, "linear", function() {
        $(_this).remove();
    });

}

var Tips = new tipsComponent();
/*提示组件END*/

/*加载组件*/
var loading = {
    show : function(){
        var loadingHtml = '<div class="index-showok-model"></div><div class="index-showok"><i class="loadingimg"></i><p class="loadingp">请稍候</p></div>';
        if($('.index-showok-model').length == 0 ){
            $('body').append(loadingHtml);
            $('.index-showok').animate({opacity : "0.6" },500,"linear");
        }
    },
    hide : function(){
        $('.index-showok').animate({opacity : "0" },500,"linear",function(){
            $('.index-showok-model').remove();
            $('.index-showok').remove();
        });
    }
}

/*加载组件END*/
/*对话框*/
function dialog(options) {
    if (!options) throw new Error('缺少配置项');
    this.title = options.title || '标题';
    this.buttonNum = options.buttonNum || 2;
    this.firstBtnText = options.firstBtnText || '取消';
    this.firstBtnEvent = options.firstBtnEvent;
    this.secondBtnText = options.secondBtnText || '确定';
    this.secondBtnEvent = options.secondBtnEvent;
    this.content = options.content;
    this.init();
}


dialog.prototype.init = function() {
    var dialogHtml = '<div class="dialog-modal"></div>';
    dialogHtml += '<div class="dialog-box">';
    dialogHtml += '<div class="dialog-content">';
    if(this.title!=null&&this.title!=""&&this.title!='标题'){
        dialogHtml += '<p class="dialog-content-title">' + this.title + '</p>';
    }
    dialogHtml += '<p class="dialog-content-text">'+this.content+'</p>';
    dialogHtml += '</div>';

    if (this.buttonNum == 1) {
        dialogHtml += '<div class="dialog-btn dialog-singlebtn">';
        dialogHtml += '<a href="javascript:;" class="dialog-btn-cancel">' + this.firstBtnText + '</a>';
    } else {
        dialogHtml += '<div class="dialog-btn">';
        dialogHtml += '<a href="javascript:;" class="dialog-btn-cancel">' + this.firstBtnText + '</a>';
        dialogHtml += '<a href="javascript:;" class="dialog-btn-confirm">' + this.secondBtnText + '</a>';
    }

    dialogHtml += '</div></div>';
    $('body').append(dialogHtml);
    this.addEvent();
}

dialog.prototype.addEvent = function() {
    var _this = this;
    $('.dialog-btn .dialog-btn-cancel').bind('click', function() {
        _this.destoryDialog();
        if (typeof(_this.firstBtnEvent) == 'function') _this.firstBtnEvent();
    });
    $('.dialog-btn .dialog-btn-confirm').bind('click', function() {
        _this.destoryDialog();
        if (typeof(_this.secondBtnEvent) == 'function') _this.secondBtnEvent();
    });
}

dialog.prototype.destoryDialog = function() {
    $('.dialog-modal').remove();
    $('.dialog-box').remove();
}

var model = {
    pay: payModal,
    datePickerModel: datePickerModel,
    timePickerModel: timePickerModel,
    selectorModel: selectorModel
}

function getmodel(m){
    var r=/\/\*([\S\s]*?)\*\//m,
    m=r.exec(model[m].toString());
    return m&&m[1]||m;
}

/*支付*/
function payPassword(options){
    this.keyIndex = 0;
    if(typeof(options) != 'object') throw new Error('配置错误');
    this.success = options.success;
    this.init();
}

payPassword.prototype.init = function(){
    $('body').append(getmodel('pay'));
    this.addEvent();
}

payPassword.prototype.addEvent = function(){
    var _this = this;
    $('.pay-key .pay-key-header .pay-key-header-back').click(function(){
        _this.removePay();
    });

    $('.pay-key .pay-key-keybord td a').click(function(){
        var key = $(this).attr('key');
        if(key != 'backspace' && _this.keyIndex < 6){
            $('.pay-key-box .pay-key-box-psw').eq(_this.keyIndex).val(key);
            _this.keyIndex++;
        }

        if(key == 'backspace'){
            _this.keyIndex--;
            $('.pay-key-box .pay-key-box-psw').eq(_this.keyIndex).val('');
        }

        if(_this.keyIndex == 6){
            var psw = '';
            $('.pay-key-box .pay-key-box-psw').each(function(){
                psw += $(this).val();
            });
            if(typeof(_this.success) == 'function') _this.success(psw,_this);
        }
    });
}

payPassword.prototype.removePay = function(){
    $('.pay-modal').remove();
    $('.pay-key').remove();
}

payPassword.prototype.showTips = function(content){
    $('.pay-key-tips-success').html(content);
}


function payModal(){
    /*
    <div class="pay-modal"></div>
    <div class="pay-key">
        <div class="pay-key-header">
            <a href="javascript:;" class="pay-key-header-back"></a>
            <p class="pay-key-header-text">输入余额支付密码</p>
        </div>
        <div class="pay-key-box">
            <input type="password" readonly class="pay-key-box-psw">
            <input type="password" readonly class="pay-key-box-psw">
            <input type="password" readonly class="pay-key-box-psw">
            <input type="password" readonly class="pay-key-box-psw">
            <input type="password" readonly class="pay-key-box-psw">
            <input type="password" readonly class="pay-key-box-psw">
        </div>
        <div class="pay-key-tips">
            <p class="pay-key-tips-success"></p>
        </div>
        <table class="pay-key-keybord" cellspacing="0px" cellpadding="0px">
            <tr>
                <td><a href="javascript:;" key="1">1</a></td>
                <td><a href="javascript:;" key="2">2</a></td>
                <td><a href="javascript:;" key="3">3</a></td>
            </tr>
            <tr>
                <td><a href="javascript:;" key="4">4</a></td>
                <td><a href="javascript:;" key="5">5</a></td>
                <td><a href="javascript:;" key="6">6</a></td>
            </tr>
            <tr>
                <td><a href="javascript:;" key="7">7</a></td>
                <td><a href="javascript:;" key="8">8</a></td>
                <td><a href="javascript:;" key="9">9</a></td>
            </tr>
            <tr>
                <td class="pay-key-keybord-nobg"></td>
                <td><a href="javascript:;" key="0">0</a></td>
                <td class="pay-key-keybord-nobg"><a href="javascript:;" key="backspace"><i class="pay-key-keybord-backspace"></i></a></td>
            </tr>
        </table>
    </div>
    */
}

/*日期*/

function datePickerModel(){
    /*
    <div class="bottom-madal-s"></div> 
    <div class="bottom-madal timepacker">
        <div class="packer_title">
            <a href="javascript:;" class="packer_cancel_btn" id="packer_cancel_btn">取消</a>
            <p class="title">请选择日期</p>
            <a href="javascript:;" class="packer_confirm_btn" id="packer_confirm_btn">确定</a>
        </div>
        <div class="packer_content">
            <ul class="packer_year_time" id="packer_year_time" style="width:40%;">
                <div class="bd"></div>
            </ul>
            <ul class="packer_month_time" id="packer_month_time" style="width:20%;">
                <div class="bd"></div>
            </ul>
            <ul class="packer_day_time" id="packer_day_time" style="width:20%;">
                <div class="bd"></div>
            </ul>
            <span class="packer_line"></span>
        </div>
    </div>
    */
}

/*时间*/

function timePickerModel(){
    /*
    <div class="bottom-madal-s"></div> 
    <div class="bottom-madal timepacker">
        <div class="packer_title">
            <a href="javascript:;" class="packer_cancel_btn" id="packer_cancel_btn">取消</a>
            <p class="title">请选择时间</p>
            <a href="javascript:;" class="packer_confirm_btn" id="packer_confirm_btn">确定</a>
        </div>
        <div class="packer_content">
            <p class="packer_year_p" style="width:30%;"></p>
            <ul class="packer_year_time" id="packer_hour_time" style="width:20%;">
                <div class="bd"></div>
            </ul>
            <p class="packer_year_p">:</p>
            <ul class="packer_month_time" id="packer_min_time" style="width:20%;">
                <div class="bd"></div>
            </ul>
            <span class="packer_line"></span>
        </div>
    </div>
    */
}

/*选项*/
function selectorModel(){
    /*
    <div class="bottom-madal-s"></div> 
    <div class="bottom-madal timepacker">
        <div class="packer_title">
            <a href="javascript:;" class="packer_cancel_btn" id="packer_cancel_btn">取消</a>
            <p class="title">选择项</p>
            <a href="javascript:;" class="packer_confirm_btn" id="packer_confirm_btn">确定</a>
        </div>
        <div class="packer_content">
            <p class="packer_year_p" style="width:25%;"></p>
            <ul class="packer_year_time" id="packer_options" style="width:50%;">
                <div class="bd"></div>
            </ul>
            <span class="packer_line"></span>
        </div>
    </div>
    */
}



var mySlide = function(a){
    a = a||{};
    var opts = {
        slideCell:a.slideCell || "#touchSlide", 
        titCell:a.titCell || ".hd li", 
        mainCell:a.mainCell || ".bd", 
        effect:a.effect || "left",
        autoPlay:a.autoPlay || false, 
        delayTime:a.delayTime || 200, // 效果持续时间
        defaultIndex:a.defaultIndex ||0, // 默认的当前位置索引。0是第一个； defaultIndex:1 时，相当于从第2个开始执行
        pageStateCell:a.pageStateCell ||".pageState", // 分页状态对象，用于显示分页状态，例如：2/3
        year:a.year || null,
        initYear: a.initYear,
        initMonth: a.initMonth,
        initDay: a.initDay,
        initHour: a.initHour,
        initMin: a.initMin,
        options: a.options,
        initValue: a.initValue,
        width : a.width || 10,
        type : a.type
    }

    var slideCell = document.getElementById(opts.slideCell.replace("#",""));
    if( !slideCell ) return false;
    if(typeof(opts.init) == "function") opts.init();

    //简单模拟jquery选择器
    var obj = function(str,parEle){ 
        str = str.split(" ");
        var par = [];
        parEle = parEle||document;
        var retn = [ parEle ] ;
        for( var i in str ){ if(str[i].length!=0) par.push(str[i]) } //去掉重复空格
        for( var i in par ){ 
            if( retn.length==0 ) return false;
            var _retn = [];
            for ( var r in retn )
            {
                if( par[i][0] =="#" ) _retn.push( document.getElementById( par[i].replace("#","") ) );
                else if( par[i][0] =="." ){
                    var tag = retn[r].getElementsByTagName('*');
                    for( var j=0; j<tag.length; j++ ){
                        var cln = tag[j].className;
                        if( cln && cln.search(new RegExp("\\b" + par[i].replace(".","") + "\\b"))!=-1 ){ _retn.push( tag[j] ); }
                    }
                }
                else { var tag = retn[r].getElementsByTagName( par[i] ); for( var j=0; j<tag.length; j++ ){ _retn.push( tag[j] ) } }
            }
            retn =_retn;
        }
        
        return retn.length==0 || retn[0] == parEle ? false:retn;
    }// obj E

    // 创建包裹层
    var wrap = function(el, v){
            var tmp = document.createElement('div');
            tmp.innerHTML = v;
            tmp = tmp.children[0];
            var _el = el.cloneNode(true);
            tmp.appendChild(_el);
            el.parentNode.replaceChild(tmp, el);
            conBox = _el; // 重置conBox
            return tmp;
    };



    // 获取样色数值
    var getStyleVal =function(el, attr){ var v=0; if(el.currentStyle){ v= el.currentStyle[attr] } else { v= getComputedStyle(el,false)[attr]; } return parseInt(v.replace("px","")) } 

    // class处理
    var addClass =function(ele, className){
         if (!ele || !className || (ele.className && ele.className.search(new RegExp("\\b" + className + "\\b")) != -1)) return;
         ele.className += (ele.className ? " " : "") + className;
    }

    var removeClass = function(ele, className){
         if (!ele || !className || (ele.className && ele.className.search(new RegExp("\\b" + className + "\\b")) == -1)) return;
         ele.className = ele.className.replace(new RegExp("\\s*\\b" + className + "\\b", "g"), "");
    }

    var hasClass = function(elem, cls){
        cls = cls || '';
        if(cls.replace(/\s/g, '').length == 0) return false;
        return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
    }

    //全局对象
    var effect = opts.effect;
    
    var pageState = obj( opts.pageStateCell )[0];

    var conBox = obj( opts.mainCell,slideCell )[0];//内容元素父层对象

    var startTime = new Date(opts.startTime);
    if(startTime == 'Invalid Date') startTime = new Date();

    var endTime = new Date(opts.endTime);
    if(endTime == 'Invalid Date'){
        endyear = startTime.getFullYear();
        endMonth = startTime.getMonth()+1;
        endDay = startTime.getDate();
        if(endMonth+1 > 12){
            endyear += 1;
            endMonth = 1;
        }
        endTime = new Date(endyear+"/"+endMonth+"/"+endDay);
    }

    var mylist = document.createElement("li");
    var pss = document.createElement("p");
    mylist.appendChild(pss);
    conBox.appendChild(mylist);
    var mylist = document.createElement("li");
    var pss = document.createElement("p");
    mylist.appendChild(pss);
    conBox.appendChild(mylist);



    if(opts.type == 'year'){
        if(opts.year){
            for(var i = 0; i < opts.year.length ; i++){
                var mylist = document.createElement("li");
                var pss = document.createElement("p");

                if(opts.initYear == opts.year[i]){
                    mylist.className="on";
                    opts.defaultIndex = i;
                }

                pss.innerText = opts.year[i] + '年';
                mylist.appendChild(pss);
                conBox.appendChild(mylist);
            }
        }
    }

    if(opts.type == 'month'){
        
        for(var i = 1 ; i < 13 ; i++){
            var mylist = document.createElement("li");
            var pss = document.createElement("p");
            if(i == opts.initMonth){
                mylist.className="on";
                opts.defaultIndex = (i - 1);
            }
            pss.innerText = i + '月';
            mylist.appendChild(pss);
            conBox.appendChild(mylist);
        }
    }
    

    if(opts.type == 'day'){
        for(var i = 1 ; i < 32 ; i++){
            var mylist = document.createElement("li");
            var pss = document.createElement("p");
            if(i == opts.initDay){
                mylist.className="on";
                opts.defaultIndex = (i - 1);
            }
            pss.innerText = i + '日';
            mylist.appendChild(pss);
            conBox.appendChild(mylist);
        }
    }


    if(opts.type == 'hour'){
        for(var i = 0 ; i < 24 ; i++){
            var mylist = document.createElement("li");
            var pss = document.createElement("p");
            if(i == opts.initHour){
                mylist.className="on";
                opts.defaultIndex = i;
            }

            var optsHour = i;
            if(optsHour < 10){
                optsHour = '0' + optsHour;
            }
            pss.innerText = optsHour;
            mylist.appendChild(pss);
            conBox.appendChild(mylist);
        }
    }

    if(opts.type == 'min'){
        for(var i = 0 ; i < 60 ; i++){
            var mylist = document.createElement("li");
            var pss = document.createElement("p");
            if(i == opts.initMin){
                mylist.className="on";  
                opts.defaultIndex = i;
            }
            var optsMin = i;
            if(optsMin < 10){
                optsMin = '0' + optsMin;
            }
            pss.innerText = optsMin;
            mylist.appendChild(pss);
            conBox.appendChild(mylist);
        }
    }

    if(opts.type == 'array'){
        var setDefaultIndex = 0;
        for(var i = 0 ; i < opts.options.length ; i++){
            var mylist = document.createElement("li");
            var pss = document.createElement("p");
            if(opts.options[i].value == opts.initValue){
                mylist.className="on";  
                opts.defaultIndex = i ;
                setDefaultIndex = 1;
            }
            pss.setAttribute('value',opts.options[i].value);
            pss.innerText = opts.options[i].text;
            mylist.appendChild(pss);
            conBox.appendChild(mylist);
        }

        if(!setDefaultIndex) {
            opts.defaultIndex = 0;
            $('#packer_options').find('.bd li:eq(2)').addClass('on');
        }
    }
    
    
    //必须的，空格部分
    for(var i = 0 ; i < 3 ; i++){
        var mylist = document.createElement("li");
        var pss = document.createElement("p");
        mylist.appendChild(pss);
        conBox.appendChild(mylist);
    }
    // _html += '<li><p></p></li><li><p></p></li><li><p></p></li><li><p></p></li><li><p></p></li><li><p></p></li>';




    
    // console.log(opts.mainCell);
    if( !conBox ) return false;

    var conBoxSize= conBox.children.length;
    var conBoxChild = conBox.children;
    var navObj = obj( opts.titCell,slideCell );//导航子元素结合
    var navObjSize = navObj?navObj.length:conBoxSize;
    var sLoad=opts.switchLoad;

    /*字符串转换*/
    var index=parseInt(opts.defaultIndex);
    var delayTime=parseInt(opts.delayTime);
    var interTime=parseInt(opts.interTime);
    var autoPlay = (opts.autoPlay=="false"||opts.autoPlay==false)?false:true;
    var autoPage = (opts.autoPage=="false"||opts.autoPage==false)?false:true;
    var loop = (opts.pnLoop=="false"||opts.pnLoop==false)?false:true;
    var oldIndex = index;
    var inter=null;// autoPlay的setInterval
    var timeout = null; // leftLoop的setTimeout
    var endTimeout = null;  //translate的setTimeout
    
    var startX = 0;
    var startY = 0;
    var distX = 0;
    var distY = 0;
    var lastDistY = 0;
    var dist = 0; //手指滑动距离
    var isTouchPad = (/hp-tablet/gi).test(navigator.appVersion);
    var hasTouch = 'ontouchstart' in window && !isTouchPad;
    var touchStart = hasTouch ? 'touchstart' : 'mousedown';
    //var touchMove = hasTouch ? 'touchmove' : 'mousemove';
    var touchMove = hasTouch ? 'touchmove' : '';
    var touchEnd = hasTouch ? 'touchend' : 'mouseup';
    var slideH=0;
    var slideW= 40;// mainCell滑动距离
    var twCell;
    var scrollY ;
    var tempSize = conBoxSize;
    
    //处理分页
    if( navObjSize==0 ) navObjSize=conBoxSize;

    


    twCell = wrap(conBox,'<div class="tempWrap" style="overflow:hidden; position:relative;"></div>');
    // conBox.style.cssText="width:"+tempSize*slideW+"px;"+"position:relative;overflow:hidden;padding:0;margin:0;";
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
    var clientWidth = document.documentElement.clientWidth || document.body.clientWidth;
    var curHeight = parseInt(clientHeight) - 50 - 110;
    var widthPercent = parseInt(opts.width) / 100;
    for ( var i =0; i<tempSize; i++ ){  
        conBox.children[i].children[0].style.cssText="height:"+slideW+"px;width:"+(clientWidth * widthPercent)+"px;";
        conBox.children[i].style.cssText="height:"+slideW+"px;width:"+(clientWidth * widthPercent)+"px" ;
    }
    
    var doStartFun=function(){ if ( typeof opts.startFun =='function' ){ opts.startFun( index,navObjSize ) } }
    var doEndFun=function(){ if (  typeof opts.endFun =='function' ){ opts.endFun( index,navObjSize ) } }
    
    //滑动效果
    var translate = function( dist, speed, ele ) {
        // console.log(dist);
        if( !!ele ){ ele=ele.style; }else{ ele=conBox.style; }
        ele.webkitTransitionDuration =  ele.MozTransitionDuration = ele.msTransitionDuration = ele.OTransitionDuration = ele.transitionDuration =  speed + 'ms';
        ele.webkitTransform = 'translate(0,' + dist + 'px)' + 'translateZ(0)';
        ele.msTransform = ele.MozTransform = ele.OTransform = 'translateY(' + dist + 'px)';     
    }
    
    

    //效果函数
    var doPlay=function(isTouch,isM){
        
        switch (effect)
        {
            case "left": 

                if ( index >= navObjSize) { 
                    index = isTouch?index-1:0; 

                } else if( index < 0) { 
                    index = isTouch?0:navObjSize-6; 
                } 
                if( sLoad!=null ){ doSwitchLoad(0) } 
                else {
                    translate(  (-index*slideW),delayTime ); 

                }
                oldIndex=index; 
                break;
                

        }//switch end
        doStartFun();
        endTimeout= setTimeout( function(){ doEndFun() }, delayTime );

        //设置className
        for ( var i=0; i<navObjSize; i++ )
        {
            removeClass(navObj[i],opts.titOnClassName);
            if( i == index ){ addClass(navObj[i],opts.titOnClassName) }
        }

        if( loop==false ){ //loop控制是否继续循环
            removeClass( nextBtn,"nextStop" );removeClass( prevBtn,"prevStop" );
            if (index==0 ){ addClass( prevBtn,"prevStop" ) }
            else if (index==navObjSize-1 ){ addClass( nextBtn,"nextStop" ) }
        }
        if(pageState){ pageState.innerHTML= "<span>"+(index+1)+"</span>/"+navObjSize; }

    };// doPlay end

    //初始化执行
    doPlay(0,1);
    

    //触摸开始函数
    var tStart = function(e){
        clearTimeout( timeout );clearTimeout( endTimeout );
        scrollY = undefined;
        distX = 0;
        var point = hasTouch ? e.touches[0] : e;
        startX =  point.pageX;
        startY =  point.pageY;
        
        //添加“触摸移动”事件监听
        conBox. addEventListener(touchMove, tMove,false);

        //添加“触摸结束”事件监听
        conBox.addEventListener(touchEnd, tEnd ,false);
    }

    //触摸移动函数
    var tMove = function(e){

        if( hasTouch ){ if ( e.touches.length > 1 || e.scale && e.scale !== 1) return }; //多点或缩放
        console.log(e.touches);
        var point = hasTouch ? e.touches[0] : e;
        distX = point.pageX-startX;
        distY = point.pageY-startY;
        console.log(distY);
        var mylistIndex = Math.ceil(-distY/slideW);
        var curIndex = index + mylistIndex;
        if ( curIndex >= navObjSize - 6) { 
            curIndex = navObjSize - 6; 
        } else if( curIndex < 0) { 
            curIndex = 0; 
        }
        curIndex += 2;
        if(!hasClass(conBox.children[curIndex],'on')){
            for ( var i=0; i<navObjSize; i++ ){
                removeClass(conBox.children[i],'on');
            }
            addClass(conBox.children[curIndex],'on');
        }
        if ( typeof scrollY == 'undefined') { scrollY = !!( scrollY || Math.abs(distY) < Math.abs(distX) ); }
        // console.log(!scrollY);
        if( !scrollY ){ 
            e.preventDefault(); if(autoPlay){clearInterval(inter) }
            switch (effect){
                case "left":
                    if( (index==0 && distY>0) || (index>=navObjSize-1&&distY<0 )){ distY=distY*1 }
                        // console.log(-index*slideW+distY);
                    translate( -index*slideW+distY ,0 );
                break;
            }
            
            if(  sLoad!=null && Math.abs(distY)>slideW/3 ){ 
                doSwitchLoad( distY>-0?-1:1 ) 
            }
        }
    }

    //触摸结束函数
    var tEnd = function(e){
        console.log(distY);
        if(distY == 0 || lastDistY == distY) return;
        e.preventDefault(); 
        if( !scrollY )
        {
            var distIndex= Math.ceil(-distY/slideW);
            if( Math.abs(distY) > slideW/10  ){ 
                if(distY > 0){
                    index +=distIndex;
                }else{
                    index +=distIndex;
                } 
                if ( index >= navObjSize - 6) { 
                    index = navObjSize - 6; 
                } else if( index < 0) { 
                    index = 0; 
                } 
            }
            doPlay( true );
        }
        lastDistY = distY;
        conBox.removeEventListener(touchMove, tMove, false);
        conBox.removeEventListener(touchEnd, tEnd, false);
    }

    


    //添加“触摸开始”事件监听
    conBox.addEventListener(touchStart, tStart ,false);


}

function datePicker(options){
    if(typeof(options) != 'object') throw new Error('配置错误');
    this.success = options.success;
    this.cancel = options.cancel;
    this.initTime = options.initTime;
    this.compareTime = options.compareTime;
    this.successDestory = !!options.successDestory;
    this.operation = options.operation;
    this.initYear = '';
    this.initMonth = '';
    this.initDay = '';
    this.targetEle = options.targetEle;
    this.init();
}

datePicker.prototype.init = function(){
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight || window.innerHeight;
    $('body').append(getmodel('datePickerModel')).addClass('bodyoverflow').css({'overflow':'hidden','height':parseInt(clientHeight) + 'px'})
    if(!this.initTime) this.initTime = '';
    var yearArr = this.initTime.split('年');
    var monthArr;
    var initTime = new Date();
    if(yearArr.length == 2){
        this.initYear = yearArr[0];
        monthArr = yearArr[1].split('月');
        if(monthArr.length == 2){
            this.initMonth = monthArr[0];
            this.initDay = parseInt(monthArr[1]);
        }else{
            this.initMonth = initTime.getMonth() + 1;
            this.initDay = initTime.getDate();
        }
    }else{
        this.initYear = initTime.getFullYear();
        this.initMonth = initTime.getMonth() + 1;
        this.initDay = initTime.getDate();
    }
    this.addContent();
    document.addEventListener('touchmove', preventDefault, false); 
    setTimeout(function(){$('.timepacker').addClass('bottom-madal-moveup');},1);
}

datePicker.prototype.addContent = function(){
    var _this = this;
    mySlide({ 
        slideCell:'#packer_year_time',
        year:[2016,2017],
        initYear: _this.initYear,
        width : 40,
        type : 'year'
    });

    mySlide({ 
        slideCell:'#packer_month_time',
        width : 25,
        initMonth: _this.initMonth,
        type : 'month'
    }); 

    mySlide({ 
        slideCell:'#packer_day_time',
        initDay: _this.initDay,
        width : 25,
        type : 'day'
    }); 

    this.addEvent();
}

datePicker.prototype.addEvent = function(){
    var _this = this;
    $('#packer_confirm_btn').bind('click',function(){
        var year = parseInt($('#packer_year_time').find('.bd .on p').html());
        var month = parseInt($('#packer_month_time').find('.bd .on p').html());
        var day = parseInt($('#packer_day_time').find('.bd .on p').html());

        var selDate = new Date(year+"/"+month);
        
        if((year%4==0 && year%100!=0)||(year%100==0 && year%400==0)){
            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                endDay = 31;
            }else if(month == 4 || month == 6 || month == 9 || month == 11){
                endDay = 30;
            }else if(month == 2){
                endDay = 29;
            }
        }else{
            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                endDay = 31;
            }else if(month == 4 || month == 6 || month == 9 || month == 11){
                endDay = 30;
            }else if(month == 2){
                endDay = 28;
            }
        }
        var dateFormat = year+"年"+month+"月"+day+"日";


        
        if(parseInt(day) > endDay){
            //超出范围的
            Tips.show(year+'年'+month+"月"+" 超过"+endDay+'日');
        }else{
            var localDate = new Date().getTime();
            var selDate = new Date(year + '/' + month + '/' + day).getTime();
            if(selDate + (24 * 60 * 60 * 1000) < localDate){
                Tips.show('当前活动时间不可用');
                return;
            }

            var compareStatus;
            if(_this.compareTime && typeof(_this.compareTime) == 'string'){
                var yearArr = _this.compareTime.split('年');
                if(yearArr.length == 2){
                    var compareYear = yearArr[0];
                    var monthArr = yearArr[1].split('月');
                    if(monthArr.length == 2){
                        var compareMonth = monthArr[0];
                        var compareDay = parseInt(monthArr[1]);
                        var compareTimestamp = new Date(compareYear + '/' + compareMonth + '/' + compareDay).getTime();
                        var timeTimestamp = new Date(year + '/' + month + '/' + day).getTime();
                        var calStatus;
                        if(timeTimestamp > compareTimestamp){
                            calStatus = '>';
                        }
                        else if(timeTimestamp < compareTimestamp) {
                            calStatus = '<';
                        }else{
                            calStatus = '=';
                        }

                        if(_this.operation.length == 2){
                            compareStatus = _this.operation.indexOf(calStatus) > -1 ? true:false;
                        }else{
                            compareStatus = calStatus == _this.operation ? true:false;
                        }
                        
                    }
                }
            }else{
                compareStatus = 'noCompare';
            }

            $(_this.targetEle).html(dateFormat);
            $.isFunction(_this.success) && _this.success(year,month,day,_this,compareStatus);
            _this.successDestory && _this.destoryDatePicker();
        }
        
    });

    $('#packer_cancel_btn').bind('click',function(){
        $.isFunction(_this.cancel) && _this.cancel();
        _this.destoryDatePicker();
    });
}

datePicker.prototype.destoryDatePicker = function(){
    $('.bottom-madal').removeClass('bottom-madal-moveup');
    document.removeEventListener('touchmove', preventDefault, false);
    setTimeout(function(){
        $('.bottom-madal').remove();
        $('.bottom-madal-s').remove();
        $('body').removeClass('bodyoverflow').css({'overflow':'auto','height':'auto'})
    },400);
}

/*时间*/
function timePicker(options){
    if(typeof(options) != 'object') throw new Error('配置错误');
    this.success = options.success;
    this.cancel = options.cancel;
    this.targetEle = options.targetEle;
    this.initTime = options.initTime;
    this.successDestory = options.successDestory;
    this.compareTime = options.compareTime;
    this.operation = options.operation;
    this.initHour = '';
    this.initMin = '';
    this.init();
}

timePicker.prototype.init = function(){
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight || window.innerHeight;

    $('body').append(getmodel('timePickerModel')).addClass('bodyoverflow').css({'overflow':'hidden','height':parseInt(clientHeight) + 'px'});

    if(!this.initTime) this.initTime = '';
    var timeArr = this.initTime.split(':');
    if(timeArr.length == 2 && timeArr[0] >= 0 && timeArr[0] < 24 && timeArr[1] >= 0 && timeArr[1] < 60){
        this.initHour = timeArr[0];
        this.initMin = timeArr[1];
    }else{
        this.initHour = 0;
        this.initMin = 0;
    }

    this.addContent();
    document.addEventListener('touchmove', preventDefault, false);
    setTimeout(function(){$('.timepacker').addClass('bottom-madal-moveup');},1);
}

timePicker.prototype.addContent = function(){
    var _this = this;
    mySlide({ 
        slideCell:'#packer_hour_time',
        width : 20,
        initHour: _this.initHour,
        type : 'hour'
    });
    mySlide({ 
        slideCell:'#packer_min_time',
        width : 20,
        initMin: _this.initMin,
        type : 'min'
    });

    this.addEvent();
}

timePicker.prototype.addEvent = function(){

    var _this = this;
    // console.log(_this.compareTime);
    $('#packer_confirm_btn').bind('click',function(){
        var hour = $('#packer_hour_time').find('.bd .on p').html();
        var min = $('#packer_min_time').find('.bd .on p').html();

        var eleTime = '2016/09/27';
        var compareStatus;
        if(_this.compareTime){
            var compareTimestamp = new Date(eleTime+" "+_this.compareTime).getTime();
            var timestamp = new Date(eleTime+" "+hour+":"+min).getTime();
            var calStatus;
            if(compareTimestamp < timestamp) calStatus = '<';
            else if(compareTimestamp > timestamp) calStatus = '>';
            else calStatus = '=';

            if(_this.operation.length == 2){
                compareStatus = _this.operation.indexOf(calStatus) > -1 ? true:false;
            }else{
                compareStatus = calStatus == _this.operation ? true:false;
            }

        }else{
            compareStatus = 'noCompare';
        }
        var dateFormat = hour + ':' + min;
        $(_this.targetEle).html(dateFormat);
        $.isFunction(_this.success) && _this.success(hour,min,_this,compareStatus);
        _this.successDestory && _this.destoryTimePicker();
    });

    $('#packer_cancel_btn').bind('click',function(){
        $.isFunction(_this.cancel) && _this.cancel();
        _this.destoryTimePicker();
    });
}

timePicker.prototype.destoryTimePicker = function(){
    $('.bottom-madal').removeClass('bottom-madal-moveup');
    document.removeEventListener('touchmove', preventDefault, false);
    setTimeout(function(){
        $('.bottom-madal').remove();
        $('.bottom-madal-s').remove();

        $('body').removeClass('bodyoverflow');
    },400);
}

/*选项选择器*/
function selector(options){
    if(typeof(options) != 'object') throw new Error('配置错误');
    this.initValue = options.initValue;
    this.options = options.options;
    if(typeof(this.options) != 'Array' && this.options.length == 0){
        alert('选项的数量必须大于1');
        return;
    }
    this.success = options.success;
    this.cancel = options.cancel;

    this.successDestory = options.successDestory;
    if(typeof(this.successDestory) == "undefined") this.successDestory = true;
    this.init();
}

selector.prototype.init = function(){
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight || window.innerHeight;

    $('body').append(getmodel('selectorModel')).addClass('bodyoverflow').css({'overflow':'hidden','height':parseInt(clientHeight) + 'px'});
    this.addContent();
    document.addEventListener('touchmove', preventDefault, false);
    setTimeout(function(){$('.timepacker').addClass('bottom-madal-moveup');},1);
}

selector.prototype.addContent = function(){
    var _this = this;
    mySlide({ 
        slideCell:'#packer_options',
        width : 50,
        initValue: _this.initValue,
        options: _this.options,
        type : 'array'
    });
    this.addEvent();
}
selector.prototype.addEvent = function(){
    var _this = this;
    $('#packer_confirm_btn').bind('click',function(){
        var selText = $('#packer_options').find('.bd .on p').html();
        var selValue = $('#packer_options').find('.bd .on p').attr('value');
        
        var res = {
            text: selText,
            value: selValue
        }

        
        $.isFunction(_this.success) && _this.success(res,_this);
        _this.successDestory && _this.destorySelector();
    });

    $('#packer_cancel_btn').bind('click',function(){
        $.isFunction(_this.cancel) && _this.cancel();
        _this.destorySelector();
    });

}

selector.prototype.destorySelector = function(){
    $('.bottom-madal').removeClass('bottom-madal-moveup');
    document.removeEventListener('touchmove', preventDefault, false);
    setTimeout(function(){
        $('.bottom-madal').remove();
        $('.bottom-madal-s').remove();
        $('body').removeClass('bodyoverflow');
    },400);
}


function sassSwitch(options){
    if(typeof(options) != 'object') throw new Error('配置错误');
    this.ele = options.ele;
    if(typeof(this.ele) == 'undefined') throw new Error('目标元素必须');
    this.open = options.open;
    if(typeof(this.open) == 'undefined') this.open = false;
    this.init();
}

sassSwitch.prototype.init = function(){
    var _this = this;
    var initStatus = '';
    if(this.open) initStatus = 'sass-switch-on';
    $(this.ele).append('<div class="sass-switch-box '+ initStatus +'"><span class="sass-switch-opera"></span></div>');
    $(this.ele).find('.sass-switch-opera').bind('click',function(){
        var _par = $(this).parent();
        if(_par.hasClass('sass-switch-on')) _par.removeClass('sass-switch-on');
        else _par.addClass('sass-switch-on');
        $(_this.ele).trigger('switchchange',_this);
    });
}

sassSwitch.prototype.getSwitchStatus = function(){
    return $(this.ele).find('.sass-switch-box').hasClass('sass-switch-on') ? true : false;
}

function preventDefault(e) { e.preventDefault(); }; 