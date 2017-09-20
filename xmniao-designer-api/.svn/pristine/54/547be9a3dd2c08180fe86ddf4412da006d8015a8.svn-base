/*!
 * TouchSlide v1.1
 * javascript触屏滑动特效插件，移动端滑动特效，触屏焦点图，触屏Tab切换，触屏多图切换等
 * 详尽信息请看官网：http://www.SuperSlide2.com/TouchSlide/
 *
 * Copyright 2013 大话主席
 *
 * 请尊重原创，保留头部版权
 * 在保留版权的前提下可应用于个人或商业用途

 * 1.1 宽度自适应（修复安卓横屏时滑动范围不变的bug）
 */



var mySlide = function(a){
	a = a||{};
	var opts = {
		slideCell:a.slideCell || "#touchSlide", //运行效果主对象，必须用id！，例如 slideCell:"#touchSlide"
		titCell:a.titCell || ".hd li", // 导航对象，当自动分页设为true时为“导航对象包裹层”
		mainCell:a.mainCell || ".bd", // 切换对象包裹层
		effect:a.effect || "left", // 效果，支持 left、leftLoop
		autoPlay:a.autoPlay || false, // 自动播放
		delayTime:a.delayTime || 200, // 效果持续时间
		defaultIndex:a.defaultIndex ||0, // 默认的当前位置索引。0是第一个； defaultIndex:1 时，相当于从第2个开始执行
		pageStateCell:a.pageStateCell ||".pageState", // 分页状态对象，用于显示分页状态，例如：2/3
		startTime:a.startTime,
		endTime : a.endTime

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


	for(var y = startTime.getFullYear() ; y <= endTime.getFullYear() ; y++){
		var year = y;
		var startMonth = 1;
		if(y == startTime.getFullYear()){
			startMonth = startTime.getMonth()+1;
		}

		var endMonth = 12;
		if(y == endTime.getFullYear()){
			endMonth = endTime.getMonth()+1;
		}

		for(var m = startMonth ; m <= endMonth ; m++){
			var startDay = 1;
			var month = m;
			var endDay = 1;

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

            

			if(m == startMonth){
				startDay = startTime.getDate();
			}

			if(m == endMonth){
				endDay = endTime.getDate();
			}

			for(var d = startDay ; d <= endDay ; d++){
				month = m;
				if(month < 10){
					month = '0'+month;
				}

				if(d < 10){
					d = '0'+d;
				}

				var mylist = document.createElement("li");
				if(year == startTime.getFullYear() && month == startTime.getMonth()+1 && d == startTime.getDate()){
					mylist.className="on";
				}
				var pss = document.createElement("p");
				pss.innerText = year+"-"+month+"-"+d;
				mylist.appendChild(pss);
				conBox.appendChild(mylist);

			}

		}
	}


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
	for ( var i =0; i<tempSize; i++ ){  
		conBox.children[i].children[0].style.cssText="width:180px;";
		conBox.children[i].style.cssText="display:table-row;height:"+slideW+"px;width:"+(clientWidth/2)+"px" ;

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
		conBox.	addEventListener(touchMove, tMove,false);

		//添加“触摸结束”事件监听
		conBox.addEventListener(touchEnd, tEnd ,false);
	}

	//触摸移动函数
	var tMove = function(e){

		if( hasTouch ){ if ( e.touches.length > 1 || e.scale && e.scale !== 1) return }; //多点或缩放

		var point = hasTouch ? e.touches[0] : e;
		distX = point.pageX-startX;
		distY = point.pageY-startY;


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
		if(distY==0) return;
		e.preventDefault(); 
		if( !scrollY )
		{
			var distIndex= Math.ceil(-distY/slideW);

			// console.log(distIndex);
			if( Math.abs(distY) > slideW/10  ){ 
				if(distY > 0){

					index +=distIndex;

				}else{
					index +=distIndex;
				} 

				// console.log(index);
				if ( index >= navObjSize - 6) { 
					index = navObjSize - 6; 
				} else if( index < 0) { 
					index = 0; 
				} 
			}
			doPlay( true );  
		}
		
		conBox.removeEventListener(touchMove, tMove, false);
		conBox.removeEventListener(touchEnd, tEnd, false);
	}

	


	//添加“触摸开始”事件监听
	conBox.addEventListener(touchStart, tStart ,false);


}// TouchSlide E

function timePacker(options)
	{
		mySlide({ 
			slideCell:options.firstPackerEle,
			startTime:options.firstStartTime,
			endTime:options.firstEndTime
		}); 

		mySlide({ 
			slideCell:options.secondPackerEle,
			startTime:options.secondStartTime,
			endTime:options.secondEndTime
		});

		d3.select('.packer_confirm_btn').on('click',function(){
			var firstTime = d3.select(options.firstPackerEle).select('.bd').select('.on')[0][0].textContent;
			var secondTime = d3.select(options.secondPackerEle).select('.bd').select('.on')[0][0].textContent;
			if(typeof(options.finshEvent) == "function") options.finshEvent(firstTime,secondTime);
		});

		d3.select('.packer_cancel_btn').on('click',function(){
			if(typeof(options.finshEvent) == "function") options.cancelEvent();
		});

	}