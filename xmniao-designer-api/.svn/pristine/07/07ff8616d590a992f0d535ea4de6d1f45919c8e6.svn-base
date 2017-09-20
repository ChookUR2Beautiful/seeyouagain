<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>活动参与明细</title>
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
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/openlive-20160902.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
</head>
<body class="padd-fill-tb bg-color-01">
	<div class="container-wrap">
		<div class="datadetails-info-module">
			<div class="datadetails-info-wrap">
				<div class="getdetails-con">
					<span class="getdetails-coll"><strong>${activity.joinNumber}<em>个</em></strong>
						<font>参与总数</font></span> <span class="getdetails-coll bag-size"><strong>${activity.residue}<em>个</em></strong>
						<font>剩余数</font></span>
				</div>
			</div>
		</div>
		<div class="list-default-module fill-list-block">
			<div id="freetryRecordList"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript">
	/*当前页数*/
	var currentPage = 1;
	/*内容状态,没有内容后状态为0*/
	var currentPageStatus = 1;
	/*每页显示条数*/
	var pageSize = 10;

	$(function() {
		loadData();

		//变量:  1.当前页  2.每页显示数 
		//获取当前页面是进行中还是已经结束,判断加载显示数是否等于每页显示数,是则加载下一页
		//调用触发方法,异步加载分页数据

		$(window).scroll(function() {
			// 当滚动到最底部以上100像素时， 加载新内容
			if ($(document).height() - $(this).scrollTop() - $(this).height() < 1) {
				//进行中
				if (currentPageStatus) {
					currentPageStatus = 0;
					loadData();
				//$("#menberbag-underway").append("<div class="end">已经到底了</div>");
				//已到底最底部
				}
			}
		});

		function loadData() {
			var param = {
				"activityId" : "${activity.id}",
				"activityType" : "${activity.activityType}",
				"pageSize" : pageSize,
				"pageIndex" : currentPage
			};
			$.post("${ctx}/h5/activity/record/list_record", param, function(data, status) {
				if (status == "success") {
					if (data.state == 0) {
						$.each(data.result, function(i, item) {
							var a = $("<a>").attr("class", "list-item item-icon-left").attr("href", "${ctx}/h5/activity/record/detail_record?recordId=" + item.id + "&activityId=${activity.id}&activityType=${activity.activityType}");
							var img=$("<img>").attr("src",item.head);
							if(img.attr("src")){
								$("<i>").attr("class", "icon-wrap icon-consumer").append(img).appendTo(a);
							}else{
								$("<i>").attr("class", "icon-wrap icon-consumer").append($("<img>").attr("src","${ctx}/imgs/redpacket/Consumer-members@2x.png")).appendTo(a);
							}
							var span = $("<span>").attr("class", "item-coll");
							$("<strong>").html(item.usrName ? item.usrName : '匿名').appendTo(span);
							var getTime = new Date(item.getTime);
							$("<font>").html(formatDate(getTime, "yyyy-MM-dd HH:mm:ss")).appendTo(span);
							span.appendTo(a);
							var span1 = $("<span>").attr("class", "item-coll");
							$("<strong>").html(item.vipName).appendTo(span1);
							$("<font>").html("获得奖品：" + item.awardName).appendTo(span1);
							span1.appendTo(a);
							a.appendTo("#freetryRecordList");
						});
						if ((currentPage == 1 && data.result.length == 0) || data.result == null) {

							var html = '<div class="emptydata">';
							html += '<i></i>';
							html += '<p>您当前没相关数据</p>';
							html += '</div>';
							$("body").append(html);
							currentPageStatus = 0;
						}
						else {
							currentPage++;
							currentPageStatus = 1;
							if (data.result.length < 10) {
								$(".container-wrap").append('<div class="end">已经到底了</div>');
								currentPageStatus = 0;
							}
						}
					}
				}
			}, "json");
		}
	});
</script>

</html>