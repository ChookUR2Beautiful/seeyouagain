<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <c:choose>
    	<c:when test="${initmap.response.zhiboType == 1 }">
   			<title>${initmap.response.nname}正在直播</title>
    	</c:when>
    	<c:when test="${initmap.response.zhiboType == 3 }">
    		<title>${initmap.response.nname}的回放</title>
    	</c:when>
    	<c:otherwise>
    		<title>${initmap.response.nname}的直播</title>
    	</c:otherwise>
    </c:choose>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">

    <link rel="stylesheet" href="<%=basePath%>/css/normalize.css">
    <link rel="stylesheet" href="<%=basePath%>/css/showtipsv2.css">
    <link rel="stylesheet" href="<%=basePath%>/css/play.css?v=3">
    
</head>
<body>
    <div class="play-wrap">
         
        <div class="play-header">
        
            <div class="play-header-logo">
                <img src="<%=basePath%>/images/xmn_logo.png">
            </div>
            <a href="javascript:appLoad();" class="play-download-btn">
            </a>
        </div>
        <div class="anchor-box">
           <!--主播信息-->
           <div class="play-anchor" >
               <span class="play-anchor-img" style="background-image: url(${initmap.response.avatar});"></span>
               <div class="play-anchor-info">
                   <p class="play-anchor-name">${initmap.response.nname}</p>
                   <p class="play-anchor-audience">${initmap.response.view_count}人</p>
               </div>
               <a class="play-anchor-focus" href="javascript:;">关注</a>
           </div>

           <!--主播位置-->
           <div class="live-anchor-location" >
               <p class="live-anchor-locationbox">
                   <i class="location-icon"></i>
                   <span class="live-anchor-locationcontent">${initmap.response.sellername}</span>

               </p>
               <p class="live-anchor-distance" id="distanceStr"></p>
               <!--<p class="live-anchor-id">ID:1234567</p>-->
               <!--<p class="live-anchor-id">寻蜜鸟</p>-->
           </div>
       </div>
       <c:if test="${initmap.response.isSell == 1 }">
	       <div class="coupons-box">
	           <span>${initmap.response.title }</span>
	           <span class="money">${initmap.response.ticketMoney }</span>
	       </div>
       </c:if>
        <%-- <div class="live-header">
        	<!--主播信息-->
        <div class="play-anchor" >
            <span class="play-anchor-img" style="background-image: url(${initmap.response.avatar});"></span>
            <div class="play-anchor-info">
                <p class="play-anchor-name">${initmap.response.nname}</p>
                <p class="play-anchor-audience">${initmap.response.view_count}人</p>
            </div>
            <a class="play-anchor-focus" href="javascript:appLoad();">关注</a>
        </div>
        <!--主播位置-->
	        <div class="live-anchor-location" >
	            <p class="live-anchor-locationbox">
	                <i class="location-icon"></i>
	                <span class="live-anchor-locationcontent">${initmap.response.sellername} 
	                <label id="distanceTxt"></label>
	                </span>
	                <span class="live-anchor-distance" id="distanceStr"></span>
	            </p>
	            <p class="live-anchor-id">ID:${initmap.response.anchor_room_no}</p>
	            <p class="live-anchor-id">寻蜜鸟 ID:${initmap.response.anchor_room_no}</p>
	        </div>
            <div class="live-anchor-img" style="background-image: url(${initmap.response.avatar});"></div>
            <p class="live-anchor-name">${initmap.response.nname}</p>
            <p class="live-anchor-locshop"><i></i><span>${initmap.response.sellername}</span></p>
            <div class="live-focus">
                <p class="live-seenum">${initmap.response.view_count}</p>
                <span class="live-seetext">在看</span>
                <!--已关注
                <a href="javascript:;" class="live-onfocus"></a>
                -->
                <!--未关注-->
                <a href="javascript:appLoad();" class="live-offfocus"></a>
            </div>
        </div> --%>
    
        <!--结束时显示-->
        <%-- <div class="play-end" <c:if test="${initmap.response.zhiboType==4||initmap.response.zhiboType==5 }">style='display: block;'</c:if> >
                    <div class="playend-text">
                <p class="playend-text-title">直播结束</p>
                <p class="playend-text-totalp">${initmap.response.view_count}</p>
                <p class="playend-text-totalptext">总观看人数</p>
                <p class="playend-text-liveid">ID:${initmap.response.anchor_room_no}</p>
            </div>
            <div class="playend-footer">
                <a href="javascript:;" class="playend-footer-closebtn"></a>
                <div class="playend-footer-text">
                    <p class="playend-footer-downtext">下载寻蜜鸟APP</p>
                    <p class="playend-footer-descript">吃饭看直播互动更精彩</p>
                </div>
                <a href="javascript:appLoad();" class="playend-footer-downbtn">下载</a>
            </div>
        </div> --%>
        
         <!--结束时显示-->
        <div class="play-end" <c:if test="${initmap.response.zhiboType==4||initmap.response.zhiboType==5 }">style='display: block;'</c:if>>
            <div class="playend-text">
                <p class="playend-text-title">直播已结束</p>
                
                <p class="playend-text-totalp">${initmap.response.view_count}</p>
                <p class="playend-text-totalptext">人看过 </p>
                <!--<p class="playend-text-liveid">ID:1234567</p>-->
                <div class="attention-box">
                    <p class="hint">关注主播，下次直播不再错过</p>
                    <div class="attention-content">
                        <img src="${initmap.response.avatar}" alt="" />
                        <p class="name">${initmap.response.nname}</p>
                        <p>ID:${initmap.response.anchor_room_no}</p>
                        <a href="javascript:;" class="attention-btn">关注</a>
                    </div>
                </div>
            </div>
            </div>
        <div class="live-playfornt" <c:if test="${initmap.response.zhiboType==4||initmap.response.zhiboType==5 }">style='display: none;'</c:if>>
            <img src="${initmap.response.liveCoverUrl}">
            <a href="javascript:;" class="live-play-btn"></a>
            <div class="play-content" id="id_video_container"></div>
            <div class="backModel"></div>
            <!--聊天室-->
            <div class="live-chat" >
                <div class="live-chat-content">
                    <div class="live-chat-slide">
                    </div>
                </div>
            </div>
			<a href="#" class="share-btn"></a>
        </div>
        <!--底部图标-->
	        <div class="live-bottom" >
	            <a class="live-bottom-icon live-bottom-shuoshuo" href="javascript:appLoad();"></a>
	            <a class="live-bottom-icon live-bottom-message" href="javascript:appLoad();"></a>
	            <a class="live-bottom-icon live-bottom-share" href="javascript:appLoad();"></a>
	        </div>
	        <!--底部互动提示-->
            <a class="live-tips" href="javascript:appLoad();">
                <i class="live-tips-bg"></i>
                <p><span>在寻蜜鸟中打开  互动更轻松</span><i></i></p>
            </a>
            <div class="cb"></div>
        <!--大家都在看-->
        
        <div class="live-other-anchor">
            <p class="oh-anchor">大家都在看</p>
            <ul class="oh-anchor-list">
                <div class="clearfloat"></div>
            </ul>
        </div>
        
    </div>
    <!-- <script src="http://qzonestyle.gtimg.cn/open/qcloud/video/live/h5/live_connect.js" charset="utf-8"></script> -->

    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/showv2.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/xmnplayer.js?v=1.4"></script>
    <!-- <script src="https://content.jwplatform.com/libraries/u9zTJ18Z.js"></script> -->
    <script>
        ;(function(){


        	var loopNum = 0;
            var isOver = 0;
            var isRecode = ${initmap.response.zhiboType }==3?1:0;
            
            if(!isIos()) $('.live-play-btn').show();
            /*
            new dialogWithbtn({
                content:'您正在使用网页观看直播，使用寻蜜鸟APP看直播，画质更清晰，互动更便捷',
                confirmFun:function(){
                	appLoad();
                }
            });
			*/
            $('.playend-footer-closebtn').bind('click',function(){
                $(this).parent().remove();
            });

			//点击关注按钮
            $(".attention-btn").on("click",function () {
                $(this).addClass("active");
            });
            var uagent = navigator.platform;
			
            
            
            $('.live-play-btn').bind('click',function(){
            	if((uagent.indexOf('Win') == 0 || uagent.indexOf('Mac') == 0 ) && !isRecode){
            		new dialogWithbtn({
                        content:'请使用手机浏览器或者寻蜜鸟APP打开链接！',
                        confirmFun:function(){
                        	appLoad();
                        }
                    });
    			}else{
    				
    			
	                $('.live-header').remove();
	                var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;
	                var clientWidth = document.body.clientWidth || document.documentElement.clientWidth || window.innerWidth;
	                $(this).parent().css({width: clientWidth+"px",height: parseInt(clientHeight) - 60 + 'px'}).find('img').remove();
	                if($('#id_video_container').find('video').length == 0){
	                    xmnplayer.setup({
	                        pid: 'id_video_container',
	                        file: '${initmap.response.vedioUrl}',
	                        onPause:function(){
	                            stopPlay();
	                            // startPlay();
	                        },
	                        onPlay: function(){
	                            // alert('asg');
	                            startPlay();
	                        },
	                        onError: function(){
	                        	if(uagent.indexOf('Win') == 0 || uagent.indexOf('Mac') == 0){
	                        	}else{
	                        		$('.play-end').show();
	                                $('.live-playfornt').hide();
	                        	}
	                        }
	                    });
	                }
	                $(this).hide();
	
	                xmnplayer.play();
	                /*显示主播信息*/
	                $('.play-anchor').show();
	                /*显示主播位置*/
	                $('.live-anchor-location').show();
	                /*显示底部图标*/
	                $('.live-bottom').show();
	                /*显示互动图标*/
	                $('.live-tips').show();
	                if (${initmap.response.isSell } == 1) {
		                /*显示粉丝券  */
	                	$('.share-btn').show();
					}
	                /*隐藏预售信息  */
                	$('.coupons-box').hide();
    			}
            });


            var s = 0;
            var inTertimer = null;
            var isPlay = 0;
            var isEnd = 0;
            //startPlay();
            function startPlay()
            {
            	
            	
            	
                if(!isEnd){
                    $('.backModel').show();
                    inTertimer = setTimeout(setMessage,1000);
                }else{
                    xmnplayer.stopPlay();
                }
                
            }

            function stopPlay()
            {
                // isPlay = 0;
                clearTimeout(inTertimer);
            }
            
            if("${initmap.state}"!=100 && "${initmap.state}"!=""){
            	alert("${initmap.info}")
            }
            
            getLocation();
            function getLocation(){
                if (navigator.geolocation){
                    navigator.geolocation.getCurrentPosition(showPosition);
                }else{
                    x.innerHTML="Geolocation is not supported by this browser.";
                }
            }
            function showPosition(position){
            	var long1="${initmap.response.long1}";
            	var lat1="${initmap.response.lat1}";
            	if(long1!=null&&long1!=""&&long1!="0.0" && lat1!=null&&long1!=""&&lat1!="0.0"){
	            	var long2=position.coords.latitude;
	            	var lat2=position.coords.longitude;
	            	var distanceStr=Distance(long1,lat1,long2,lat2);
	            	document.getElementById("distanceStr").innerHTML=distanceStr;
	            	
            	}
            }
            
            //计算距离
            function Distance(long1,lat1,long2,lat2){
            	 var a, b, R;  
            		    R = 6378137; // 地球半径  
            		    lat1 = lat1 * Math.PI / 180.0;  
            		    lat2 = lat2 * Math.PI / 180.0;  
            		    a = lat1 - lat2;  
            		    b = (long1 - long2) * Math.PI / 180.0;  
            		    var d;  
            		    var sa2, sb2;  
            		    sa2 = Math.sin(a / 2.0);  
            		    sb2 = Math.sin(b / 2.0);  
            		    d = 2  
            		    		* R  
            		            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
            		                    * Math.cos(lat2) * sb2 * sb2)); 
            			if(d>1000){
            			return Math.round(d/1000)+"km";
            			}
            		    return Math.round(d)+"m";  
            			}
            
            function setMessage()
            {
            	loopNum++;
                /*20161019修改*/
                if(loopNum > 30){
                    stopPlay();
                    $('.play-btn').hide();
                    xmnplayer.stopPlay();
                    isEnd = 1;
                    new confirmDiag({
                        content: '请使用手机浏览器或者寻蜜鸟APP打开链接！',
                        confirmFun:function(){
                        	appLoad();
                        }
                    })
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "${initmap.response.barrageUrl}",
                    data: {},
                    dataType: "json",
                    success: function(data){
                        var _html = '';
                        if(data==null || data==""){
                        	return;
                        }
                        for(var i = 0 ; i < data.length ; i++){
                        	var ob=data[i];
                            var string = '';
                            var random =ob.rank_no;
                            	//parseInt(Math.random() * 200);
                            for(var l = 0 ; l < random ; l++){
                                // string += '宝';
                            }
                            /*取等级*/
                            var lvl = getLevel(random);
                            _html += '<div class="chat-box">';
                            _html += '<span class="chat-box-level '+ lvl.icon +' '+ lvl.bg +'"><i></i><em>'+random+'</em></span>';
                            _html += '<p class="chat-box-content">';
                            _html += '<span class="chat-box-sayp">'+ob.nname+'</span>';
                            _html += '<span class="chat-box-maincontent">'+ob.messager_txt+'</span>';
                            _html += '</p></div>';
                        }

                        $('.live-chat-slide').append(_html);
                        s++;
                        slideTop();
                        if(isPlay == 1){
                            inTertimer = setTimeout(setMessage,1000);
                        }
                    }
                });
            }

            function isIos()
            {
                var ua = navigator.userAgent.toLowerCase(); 
                if (/iphone|ipad|ipod/.test(ua))  return true;
                else return false;
            }

            

            function getLevel(lvl)
            {
                var lvIcon = '',lvbg='';
                if(lvl >= 1 && lvl <= 30){
                    lvIcon = 'level-star';
                }else if(lvl >= 31 && lvl <= 60){
                    lvIcon = 'level-moon';
                }else if(lvl >= 61 && lvl <= 90){
                    lvIcon = 'level-sun';
                }else if(lvl >= 91 && lvl <= 120){
                    lvIcon = 'level-normal-crown';
                }else if(lvl >= 121 && lvl <= 150){
                    lvIcon = 'level-medium-crown';
                }else if(lvl >= 151 && lvl <= 180){
                    lvIcon = 'level-senior-crown';
                }else if(lvl >= 181 && lvl <= 200){
                    lvIcon = 'level-extreme-crown';
                }

                var s = Math.ceil(lvl/5) % 6;
                if(s == 1){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-green';
                    else lvbg = 'level-bg-blue';
                }else if(s == 2){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-blue';
                    else lvbg = 'level-bg-navyblue';
                }else if(s == 3){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-navyblue';
                    else lvbg = 'level-bg-pink';
                }else if(s == 4){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-pink';
                    else if(lvl >= 91 && lvl <= 180) lvbg = 'level-bg-back';
                    else lvbg = 'level-bg-orange';
                }else if(s == 5){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-orange';
                    else lvbg = 'level-bg-darkyellow';
                }else if(s == 0){
                    if(lvl >= 1 && lvl <= 90) lvbg = 'level-bg-darkyellow';
                    else lvbg = 'level-bg-darkgrey';
                }

                return {
                    'icon': lvIcon,
                    'bg': lvbg
                }
                
            }

            /*滚动到最上面*/
            function slideTop(){
                var boxHeight = $('.live-chat-slide').css('height') || $('.live-chat-slide').height();
                var chatBoxHeight = 130;
                var dis = chatBoxHeight - parseInt(boxHeight) - 15;
                $('.live-chat-slide')
                .css('transition','transform .5s')
                .css('-webkit-transition','transform .5s')
                .css('transform','translateY('+dis+'px)')
                .css('-webkit-transform','translateY('+dis+'px)');
            }
            

            getOtherAnchor();

            function getOtherAnchor()
            {
                var loadingHtml = '<div class="live-loadingdata"></div>';
                $('.live-other-anchor .oh-anchor-list').prepend(loadingHtml);
                $.ajax({
                    type: "GET",
                    url: "<%=basePath%>/live/getReferList",
                    data: {},
                    success: function(data){
                        $('.live-loadingdata').remove();
                        if (data.state == 100) {
	                       var _html = '';
	                       $.each(data.response.referList, function(index, item) {
	                           _html += '<li>';
	                           if (item.zhiboType == 0) {
	                           	var annunciateInfoUrl = item.annunciateInfoUrl + "?liveid=" + item.zhiboRecordId;
	                           	_html += "<a href=\"javascript:advancedetail('" + annunciateInfoUrl + "');\">";
							}else if (item.zhiboType == 1 || item.zhiboType == 3) {
	                           	_html += '<a href="javascript:shareInit(' + item.zhiboRecordId + ');">';
							}
	                           
	                           _html += '<div class="oh-anchor-img" style="background-image: url(' + item.cover + ');">';
	                           
	                           if (item.zhiboType == 0) {
	                           	_html += '<div class="oh-anchor-time">';
							}else if (item.zhiboType == 1) {
	                            _html += '<div class="oh-anchor-time oh-anchor-living">';
							}else if (item.zhiboType == 3) {
	                            _html += '<div class="oh-anchor-time oh-anchor-recode">';
							}
	                           _html += '<i></i>';
	                           _html += '<p>' + item.liveTypeMark + '</p></div></div>';
	                           _html += '<div class="oh-anchor-info">';
	                           
	                           if (item.sex == 2) {
	                           _html += '<p class="oh-anchor-name girl">' + item.anchorNname + '</p>';
							}else {
	                           _html += '<p class="oh-anchor-name boy">' + item.anchorNname + '</p>';
								
							}
	                           _html += '<div class="oh-anchor-location">';
	                           _html += '<i></i><span></span>';
	                           _html += '</div></div></a></li>';
	                       });
	                       
	                       $('.live-other-anchor .oh-anchor-list').prepend(_html);
						}
                    }
                });

            }

        })();
      //跳转下载app客户端
		function appLoad(){
			window.location.href="http://a.app.qq.com/o/simple.jsp?pkgname=com.xmn.consumer";
      }
      //跳转正在直播或者回放
		function shareInit(zhiboRecordId){
			window.location.href="<%=basePath%>/live/shareInit?zhiboRecordId=" + zhiboRecordId;
      }
      //跳转预售详情页面
		function advancedetail(url){
			window.location.href=url;
      }
    </script>

</body>
</html>