<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>${data.response.title}</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath%>/css/activitypage.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/component.css"/>
</head>
<body class="bg-color-01">
	<input type="hidden" name="id" value="${data.response.id}" id="activityId">
    <div class="activity-banner">
        <img src="${data.response.image}" alt="活动广告"/>
    </div>
    <dl class="activity-rule">
        <dt><!-- <i class="g-icon icon-bottom-array"></i> -->活动规则</dt>
        <dd>${data.response.isDate}</dd>
        <dd>${data.response.remark}</dd>
    </dl>
    <div class="activity-list-module">
    <ul class="activity-picimg clearfix">
    	<c:forEach items="${data.response.products}" var="product">
	        <li class="picimg-item" id="${product.codeId}" name="${product.stock}">
	        	<c:if test="${product.stock == 0}">
		        	<img src="<%=basePath%>/img/loot_all.png" class="hint-img" />
	        	</c:if>
	            <div class="picimg-links" href="#">
	                <img src="${product.breviary}" alt=""/>
	                <div class="picimg-info-tit"><em>精选</em>${product.goodsName}</div>
	                <div class="picimg-info-price">
	                    <div class="picimg-maxprice"><span>${product.salePrice}</span></div>
	                    <del class="picimg-del-price">${product.price}</del>
	                    <i class="g-icon icon-addbtn-01"></i>
	                </div>
	            </div>
	        </li>
    	</c:forEach>
    </ul>
    <div class="page-end" style="display: none;">已经到底了</div>
    </div>
	<!-- <p id="isStock" style="display:none;position: fixed;z-index:99;top: 50%;width: 100%;left: 0;text-align: center;">该商品库存不足</p>   -->  
</body>

<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/component.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/baseUtil.js"></script>
<script type="text/javascript">
    $(function(){
    	var id = $('#activityId').val();
    	//点击
    	$(".activity-picimg").on("click","li",function(e){
    		e.preventDefault();
    		e.stopPropagation();
    		var stock = $(this).attr("name");
    		if(stock == 0){
    			/* $("#isStock").show();
    			setTimeout(function(){
    				$("#isStock").hide();
    			},2000) */
    			Tips.show("该商品库存不足");
    			return; 
    		}
    		var codeid=$(this).attr("id");
	    		if(isIOS()){
	    			setupWebViewJavascriptBridge(function(bridge) {
						bridge.callHandler('openGoodsDetailCallback', {"codeid":codeid,"id":id}, function(response) {
						})
					})
	    		}else{
					//安卓    			
	    			market.goToGoodsDetail(codeid,id);
	    		}
    	})
        var num=2;
        //返回角度
        function GetSlideAngle(dx, dy) {
            return Math.atan2(dy, dx) * 180 / Math.PI;
        }

//根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
        function GetSlideDirection(startX, startY, endX, endY) {
            var dy = startY - endY;
            var dx = endX - startX;
            var result = 0;

            //如果滑动距离太短
            if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
                return result;
            }

            var angle = GetSlideAngle(dx, dy);
            if (angle >= -45 && angle < 45) {
                result = 4;
            } else if (angle >= 45 && angle < 135) {
                result = 1;
            } else if (angle >= -135 && angle < -45) {
                result = 2;
            }
            else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
                result = 3;
            }

            return result;
        }

//滑动处理
        var startX, startY;
        document.addEventListener('touchstart', function (ev) {
            startX = ev.touches[0].pageX;
            startY = ev.touches[0].pageY;
        }, false);
        document.addEventListener('touchend', function (ev) {
            var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
            var endX, endY;
            endX = ev.changedTouches[0].pageX;
            endY = ev.changedTouches[0].pageY;
            var direction = GetSlideDirection(startX, startY, endX, endY);
            switch (direction) {
                case 0:

                    break;
                case 1:

                    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
                    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
                    if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 50){
                        /*有滑动条情况*/
                        download(id,num);
                        num++;
                    }
                    //alert("向上");
                    break;
                case 2:
                    //alert("向下");
                    if(scrollTop==0){
                        download(0);
                        num=1;
                    }
                    break;
                case 3:
                    //alert("向左");
                    break;
                case 4:
                    //alert("向右");
                    break;
                default:
            }
        }, false);
        // 初始化页面
        //download(0);
    })
    function download(id,num){
        $('.activity-list-module').append('<div class="page-loading"></div>');
        $.post("<%=basePath%>api/v1/market/pay/other/acivity/list",
                {
        			id:id,
        			page: num
                },
                function(data){
                    $('.page-loading').remove();
                    if(data.state == 100){
                        /*成功*/
                        var products = data.result;
                        var _html = '';
                        var totalCount = data.totalCounts;
                        if(totalCount){
                            for(var product in products){
                            	var url = "<%=basePath%>";
                            	var p = products[product];
                                _html += '<li class="picimg-item" id="'+p.codeId+'" name="'+p.stock+'">';
                                _html += '<div class="picimg-links" href="#">';
                               	if(p.stock == 0){
                               		_html += '<img src="'+url+'/img/loot_all.png" class="hint-img" />'
                               	}
                                _html += '<img src="'+p.breviary+'" alt=""/>';
                                _html += '<div class="picimg-info-tit"><em>精选</em>'+p.goodsName+'</div>';
                                _html += '<div class="picimg-info-price">';
                                _html += '<div class="picimg-maxprice"><span>'+p.salePrice+'</span></div>';
                                _html += '<del class="picimg-del-price">'+p.price+'</del>';
                                _html += '</div>';
                                _html += '<i class="g-icon icon-addbtn-01"></i>';
                                _html += '</div>';
                                _html += '</li>';
                            }

                            if(data.totalCounts<4){
                                //_html += '<div class="page-end">已经到底了</div>';
                                $('.activity-picimg .page-end').show();

                            }else{
                                $('.activity-picimg').append(_html);
                            }
                        }else{
//                            pullupload.end = true;
							//$('#list-box-1').append('<div class="emptydata"><i></i><p>您当前没相关数据</p></div>');
							Tips.show('对不起，没有更多的信息了');
                        }

                    }else{
                        Tips.show('服务器错误，请刷新重试');
                    }

                }
        );
    }
</script>
</html>