<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>参与明细</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/marketingRank.css" />
<link rel="stylesheet" href="${ctx}/css/component.css">

<style type="text/css">
.end {
	font-size: 12px;
	text-align: center;
	padding: 2px 0;
	margin: 3px 0;
}
.page-loading{
		width: 100%;
		height: 30px;
		background-size: 37%;
		background-image: url(${ctx}/imgs/loadingimg.gif);
		background-repeat: no-repeat;
		background-position: center;
	}
	.page-end{
	    text-align: center;
	    font-size: 12px;
	    padding: 5px 0;
	    color: #999;
	}
</style>
</head>
<body class="bg-color-01 padd-fill-tb">
	<div class="container-wrap container-wrap-padding">
		<div class="slide-tab-wrap slide-tab-fixed">
			<span class="active"><a href="javascript:;">已集赞</a></span> <span><a
				href="javascript:;">已兑换</a></span>
		</div>
		<div class="swiper-container" id="bag-list-module">
			<div class="swiper-wrapper">
				<div class="swiper-slide" id="menberbag-underway">
					

				</div>
				<div class="swiper-slide" id="menberbag-end">
					

				</div>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
 <script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript">
	var activityId="${fcouspoints.id}";
	var recordMap;
	var converMap;
	/*当前页数*/
	var currentPage = 1;
	/*内容状态,没有内容后状态为0*/
	var currentPageStatus = 1;
	/*每页显示条数*/
	var pageSize = 10;
	
	/*当前页数*/
	var currentPage2 = 1;
	/*内容状态,没有内容后状态为0*/
	var currentPageStatus2 = 1;
	/*每页显示条数*/
	var pageSize2 = 10;
	$(function() {
		var tabIndex = '';
		$(".slide-tab-wrap span").each(function(index, ele) {
			if ($(this).hasClass("active")) {
				tabIndex = $(this).index();
				$(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
				$(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).show()
				return false;
			}
		})
		$(".slide-tab-wrap span").click(function() {
			var conIndex = $(this).index();
			$(this).addClass("active").siblings().removeClass("active");
			$(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
			$(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).show();
		})
		
	  $(this).ajaxStart(function(){
        if ($(".active").find("a").html() == '已集赞') {
					$("#menberbag-underway").append('<div class="page-loading"></div>');
				}else if($(".active").find("a").html() == '已兑换'){
					$("#menberbag-end").append('<div class="page-loading"></div>');
				}
      });
      $(this).ajaxSuccess(function(){
        if ($(".active").find("a").html() == '已集赞') {
					$("#menberbag-underway").find(".page-loading").remove();
				}else if($(".active").find("a").html() == '已兑换'){
					$("#menberbag-end").find(".page-loading").remove();
				}
      });
		
		$.post(basePath+"/h5/activity/fcouspoints/list_record_count","id="+activityId,function(data, status){
			 if (status == "success") {
					if (data.state == 0) {
						recordMap=data;
					}
			}
		});
		
		$.post(basePath+"/h5/activity/fcouspoints/list_conver_count","id="+activityId,function(data, status){
			 if (status == "success") {
					if (data.state == 0) {
						converMap=data;
					}
			}
		});
		
		loadData();
		loadData2();
		$(window).scroll(function() {
			// 当滚动到最底部以上100像素时， 加载新内容
			if ($(document).height() - $(this).scrollTop() - $(this).height() < 1) {
				if ($(".active").find("a").html() == '已集赞') {
					if (currentPageStatus) {
						currentPageStatus = 0;
						loadData();
					}
				}else if($(".active").find("a").html() == '已兑换'){
					if (currentPageStatus2) {
						currentPageStatus2 = 0;
						loadData2();
					}
				}
			}
		});

		function loadData() {
			var param = {
				"id" : "${fcouspoints.id}",
				"pageSize" : pageSize,
				"pageIndex" : currentPage
			};
			$.post("${ctx}/h5/activity/fcouspoints/list_record", param, function(data, status) {
				if (status == "success") {
					if (data.state == 0) {
						$.each(data.result, function(i, item) {
							//迭代操作
							var dateFam=formatDate(new Date(item.getTime),'yyyy-MM-dd');
							var record=$("#record"+dateFam);
							if(record.length>0){
								html='<a class="list-item item-icon-left list-item-jizan" href="${ctx}/h5/activity/fcouspoints/detail_revenue?id='+item.id+'">';
								if(item.head){
									html+='<i class="icon-wrap icon-consumer"><img src="'+item.head+'"></i> <span class="item-coll">';
								}else{
									html+='<i class="icon-wrap icon-consumer"><img src="${ctx}/imgs/redpacket/Consumer-members@2x.png"></i> <span class="item-coll">';
								}
								html+='	<strong>'+item.usrName+'<em class="sub-strong">（'+item.payType+'）</em></strong> <font>交易时间'+formatDate(new Date(item.getTime),'HH:mm:ss')+'</font>';
								html+='</span> <span class="item-coll"> <strong>￥'+item.payment+'</strong> <font class="sub-font">'+item.givePoints+'点</font>';
								html+='</span>';
								html+='</a>';
								record.append(html);
							}else{
							   var titleHtml=getDateTitle(new Date(item.getTime),0);
							   $("#menberbag-underway").append(titleHtml);
								html='<div class="content-slide-wrap list-default-module fill-list-block" id="record'+dateFam+'">';
								html+='<a class="list-item item-icon-left list-item-jizan" href="${ctx}/h5/activity/fcouspoints/detail_revenue?id='+item.id+'">';
								if(item.head){
									html+='<i class="icon-wrap icon-consumer"><img src="'+item.head+'"></i> <span class="item-coll">';
								}else{
									html+='<i class="icon-wrap icon-consumer"><img src="${ctx}/imgs/redpacket/Consumer-members@2x.png"></i> <span class="item-coll">';
								}	
								html+='	<strong>'+item.usrName+'<em class="sub-strong">（'+item.payType+'）</em></strong> <font>交易时间'+formatDate(new Date(item.getTime),'HH:mm:ss')+'</font>';
								html+='</span> <span class="item-coll"> <strong>￥'+item.payment+'</strong> <font class="sub-font">'+item.givePoints+'点</font>';
								html+='</span>';
								html+='</a>';
								html+='</div>';
								$("#menberbag-underway").append(html);
							}
						});
						if ((currentPage == 1 && data.result.length == 0) || data.result == null) {
							var html = '<div class="emptydata">';
							html += '<i></i>';
							html += '<p>您当前没相关数据</p>';
							html += '</div>';
							$("#menberbag-underway").append(html);
							currentPageStatus = 0;
						}
						else {
							currentPage++;
							currentPageStatus = 1;
							if (data.result.length < 10) {
								$("#menberbag-underway").append('<div class="end">已经到底了</div>');
								currentPageStatus = 0;
							}
						}
					}
				}
			}, "json");
		}
		
		function loadData2() {
			var param = {
				"id" : "${fcouspoints.id}",
				"pageSize" : pageSize2,
				"pageIndex" : currentPage2
			};
			$.post("${ctx}/h5/activity/fcouspoints/list_conver", param, function(data, status) {
				if (status == "success") {
					if (data.state == 0) {
						$.each(data.result, function(i, item) {
							//迭代操作
							var dateFam=formatDate(new Date(item.getTime),'yyyy-MM-dd');
							var record=$("#conver"+dateFam);
							if(record.length>0){
								html='<a class="list-item item-icon-left list-item-jizan" href="${ctx}/h5/activity/fcouspoints/detail_conver?id='+item.id+'">';
								if(item.head){
									html+='<i class="icon-wrap icon-consumer"><img src="'+item.head+'"></i> <span class="item-coll">';
								}else{
									html+='<i class="icon-wrap icon-consumer"><img src="${ctx}/imgs/redpacket/Consumer-members@2x.png"></i> <span class="item-coll">';
								}
								html+='	<strong>'+item.usrName+'</strong> <font>兑换时间'+formatDate(new Date(item.getTime),'HH:mm:ss')+'</font>';
								html+='</span> <span class="item-coll"> <strong>'+item.awardName+'</strong> <font>'+(item.degree==1?'首':item.degree)+'次兑换</font>';
								html+='</span>';
								html+='</a>';
								record.append(html);
							}else{
							   var titleHtml=getDateTitle(new Date(item.getTime),1);
							   $("#menberbag-end").append(titleHtml);
								html='<div class="content-slide-wrap list-default-module fill-list-block" id="conver'+dateFam+'">';
								html+='<a class="list-item item-icon-left list-item-jizan" href="${ctx}/h5/activity/fcouspoints/detail_conver?id='+item.id+'">';
								if(item.head){
									html+='<i class="icon-wrap icon-consumer"><img src="'+item.head+'"></i> <span class="item-coll">';
								}else{
									html+='<i class="icon-wrap icon-consumer"><img src="${ctx}/imgs/redpacket/Consumer-members@2x.png"></i> <span class="item-coll">';
								}	
								html+='	<strong>'+item.usrName+'</strong><font>兑换时间'+formatDate(new Date(item.getTime),'HH:mm:ss')+'</font>';
								html+='</span> <span class="item-coll"> <strong>'+item.awardName+'</strong> <font>'+(item.degree==1?'首':item.degree)+'次兑换</font>';
								html+='</span>';
								html+='</a>';
								html+='</div>';
								$("#menberbag-end").append(html);
							}
						});
						if ((currentPage2 == 1 && data.result.length == 0) || data.result == null) {

							var html = '<div class="emptydata">';
							html += '<i></i>';
							html += '<p>您当前没相关数据</p>';
							html += '</div>';
							$("#menberbag-end").append(html);
							currentPageStatus2 = 0;
						}
						else {
							currentPage2++;
							currentPageStatus2 = 1;
							if (data.result.length < 10) {
								$("#menberbag-end").append('<div class="end">已经到底了</div>');
								currentPageStatus2 = 0;
							}
						}
					}
				}
			}, "json");
		}
		
		
		function getDateTitle(date,type){
		var html='<div class="slide-headv">';
			if(type==0){	//集点
				var dateFormat=formatDate(date,'yyyy-MM-dd');
				$.each(recordMap.result,function(i,item){
					if(dateFormat==item.getTime){
						html+='<span class="slide-deadv-r">共计：'+item.givePoints+'点</span>';
						return;
					}
				});
				
			}else if(type==1){	//兑换
				var dateFormat=formatDate(date,'yyyy-MM-dd');
				$.each(converMap.result,function(i,item){
					if(dateFormat==item.getTime){
						html+='<span class="slide-deadv-r">共计：'+item.converCount+'张</span>';
						return;
					}
				});
			}
			html+='<span class="slide-deadv-l">'+date.getFullYear() + '.' + (parseInt(date.getMonth())+parseInt(1)) + '.' + date.getDate()+' '+getWeek(date.getDay())+'</span></div>';
			if(type==0){
				html+='<div class="content-slide-wrap list-default-module fill-list-block" id="record'+dateFormat+'"></div>';
			}else if(type==1){
				html+='<div class="content-slide-wrap list-default-module fill-list-block" id="conver'+dateFormat+'"></div>';
			}
			return html;
		}
		
		function getWeek(i){
			var weekday=new Array(7)
			weekday[0]="周天"
			weekday[1]="周一"
			weekday[2]="周二"
			weekday[3]="周三"
			weekday[4]="周四"
			weekday[5]="周五"
			weekday[6]="周六"
			return weekday[i];
		}
		
		function detailConver(id){
			window.location.href="${ctx}/h5/activity/fcouspoints/detail_conver?id="+id;
		}
		
	})
</script>
</html>