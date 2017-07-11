<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="gray">
<head>
    <meta charset="UTF-8">
    <title>流水列表</title>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/main.css"/><link>
</head>
<body>
	<ul class="list-nav">
		<li class="list-nav-active">
			<a href="javascript:;" class="list-change-btn">粉丝</a>
		</li>
		<li>
			<a href="javascript:;" class="list-change-btn">营收</a>
		</li>
	</ul>
    <div class="total-box">
        <p class="money">￥<span id="totalmoney"></span></p>
        <p class="num">全部流水<span id="totalcount"></span>笔</p>
    </div>
    <div class="title-box">
        <span class="gray">收支明细</span>
    </div>
    <div class="list-box">
    	
<%--     <c:forEach items="${requestScope.data}" var="redpacketIng"> --%>
    
        
        
<%--         </c:forEach> --%>
       
    </div>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/pullupload.js"></script>
    <script>
        $(function () {
        	var page=0;
            //$(window).scroll(function () {
//            	$(window).on("touchend",function () {
//            		var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;
//                 if($(this).scrollTop()==$("body").height()-clientHeight){
//                     page+=1;
//                     console.log(page);
//                 }
//             });
			var sellerid = "${sellerid}";
			var imgServer = "${ctx}";
			
			
			

			var nav1page = 1;
            var pullUp = new pullUpload({
                url: '${ctx}/api/v1/common/list',
                initdata:true,
                beforeSend:function(){
                	$('.list-box').append('<div class="loadingimg"></div>');
                },
                params: {
                    page: nav1page,
                    sellerid:  sellerid,
                    type:  1,
                    rows:'5'
                },
                success: function(pullUp,data){
                    pullUp.params.page++;
                    var res = data.state;
                    $('.loadingimg').remove();
                    if(!res){
                    	var resData = data.result;
                    	var dataList = resData.dataList;
                    	var _html = '';
                    	for(var i in dataList){
                    		var time = dataList[i].countDate;
                    		var totalCount = dataList[i].countSum;
                    		_html += '<p class="title">'+time+'<span>共计：￥'+ totalCount +'</span></p>';
                    		var innerDataList = dataList[i].subList;
                    		_html += '<ul>';
                    		for(var s in innerDataList){
                    			_html += '<li>';
                    			var payIcon = '';
                    			var payText = '';
//                     			if(innerDataList[s].payType == "1000001"){
//                     				payIcon = imgServer + '/imgs/login/Alipay@2x.png';
//                     				payText = '支付宝支付';
//                     			}else if(innerDataList[s].payType == "1000003"){
//                     				payIcon = imgServer + '/imgs/login/wechat@2x.png';
//                     				payText = '微信支付';
//                     			}else{
                    				
//                     			}

								if(pullUp.params.type == 1){
									payIcon = imgServer + '/imgs/login/Receivables@2x.png';
                    				payText = '粉丝券  <span class="fanstips">(' + innerDataList[s].uid +')</span>';
								}else{
									if(innerDataList[s].payType == "1000001"){
	                     				payIcon = imgServer + '/imgs/login/Alipay@2x.png';
	                     				payText = '支付宝支付 <span class="fanstips">(' + innerDataList[s].uid + ')</span>';
	                     			}else if(innerDataList[s].payType == "1000003"){
	                     				payIcon = imgServer + '/imgs/login/wechat@2x.png';
	                     				payText = '微信支付 <span class="fanstips">(' + innerDataList[s].uid + ')</span>';
	                     			}else{
	                     				payIcon = imgServer + '/imgs/login/Receivables@2x.png';
	                     				payText = '其他支付 <span class="fanstips">(' + innerDataList[s].uid + ')</span>';
	                     			}
								}
                    			
                    			_html += '<img src="'+ payIcon +'" />';
                    			_html += '<p class="list-title">';
                    			_html += '<span>'+payText+'<em></em></span>';
                    			var fansCoupon = '';
                    			if(innerDataList[s].couponType){
                    				fansCoupon = '<span class="usefanscoupon">(使用粉丝券)</span>';
                    			}
                    			var singleCdenom = 0;
                    			if(pullUp.params.type == 1){
                    				singleCdenom = parseFloat(innerDataList[s].cdenom).toFixed(2);
                    			}else{
                    				if(innerDataList[s].cdenom){
                    					fansCoupon = '<span class="usefanscoupon">(使用粉丝券)</span>';
                    					singleCdenom = parseFloat(innerDataList[s].cdenom).toFixed(2);
                    				}else{
                    					singleCdenom = parseFloat(innerDataList[s].amount).toFixed(2);
                    				}
                    				
                    			}
                    			_html += '<span class="money">'+fansCoupon+'￥'+ singleCdenom +'</span></p>';
                    			_html += '<p class="list-time">交易时间：'+ innerDataList[s].zdate +'</p>';
                    			_html += '</li>';
                    		}
                    		_html += '</ul>';
                    	}
                    	
                    	/*总数*/
                    	var totalAmount = resData.totalAmount;
                    	var totalCount = resData.count;
                    	$('#totalmoney').html(totalAmount);
                    	$('#totalcount').html(totalCount);
                    	
                    	if(dataList.length == 0){
                    		pullUp.end = 1;
                    		$('.list-box').append('<div class="listend">已经到底了</div>');
                    	}
                    	$('.list-box').append(_html);
                    	
                    }else{
                    	alert('服务器获取失败')
                    }
                    
                }
            });
	
            $('.list-nav li a').click(function(){
				var _index = $(this).parent().index();
				if(!$(this).parent().hasClass('list-nav-active')){
					$('.list-nav li').removeClass('list-nav-active');
					$(this).parent().addClass('list-nav-active');
					$('.list-box').empty();
					pullUp.params.page = 1;
					pullUp.end = 0;
					if(_index == 0){
						/*粉丝*/
						pullUp.params.type = 1;
					}else{
						/*营收*/
						pullUp.params.type = 2;
					}
					
					pullUp.getData();
				}
			});
            

        })
    </script>
</body>
</html>