<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>${title==null?'数据详情':title}</title>
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
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
<link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/css/marketingRank.css">
</head>
<body class="padd-fill-tb bg-color-01">
	<input hidden="hidden" id="activityId" />
	<input hidden="hidden" id="activityType" />
	<div class="container-wrap">
		<div class="datadetails-info-module">
			<div class="datadetails-info-wrap">
				<div class="datadetails-ht">
					<strong id="consumeAmount"><em>￥</em><fmt:formatNumber type="number" value="${activity.consumeAmount}" pattern="0.00" maxFractionDigits="2"/></strong> <span>刺激消费</span>
				</div>
				<div class="datadetails-hb con-col-3">
					<span><strong id="joinNumber">${activity.giveAwardCount}</strong><font>获取次数</font></span>
					<span><strong id="newuserNum">${activity.newuserNum}</strong><font>新会员数</font></span>
					<span><strong id="views">${activity.views}</strong><font>曝光次数</font></span>
				</div>
			</div>
		</div>
		<div class="activity-module">
			<div class="activity-item" id="dateSpace">
				<i class="icon-wrap icon-zeng" id="iocn"></i>
			</div>
		</div>
		<div class="list-default-module">
			<a class="list-item item-icon-right" id="detailActvity" href="${ctx}/h5/activity/activity/detail1?id=${activity.id}">尝新活动明细
				<i class="icon-wrap icon-arrow-right"></i>
			</a> <a class="list-item item-icon-right"
				href="${ctx}/h5/activity/activity_record/input?activityId=${activity.id}" id="detailRecord">参与明细 <i
				class="icon-wrap icon-arrow-right"></i></a>
		</div>
	</div>
    <div class="floor-module" id="activtyStatus">
        <div class="floor-links-col-2">
        <a href="javascript:;" class="floor-item btn-white" id="endActivity">终止活动</a>
        <a class="floor-item btn-blue" href="javascript:;" id="download_material">下载宣传物料</a>
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript">
loading.show();
	$(function() {
		$.post("${ctx}/h5/activity/record/detail","activityId=${activityId}&activityType=${activityType}",function(data,status){
			if(status=="success"){
				var consumeAmount = $("#consumeAmount");  //刺激消费
				var joinNumber = $("#joinNumber");  //参与次数
				var newuserNum = $("#newuserNum");   //新会员数
				var views = $("#views");  //曝光次数
				var detailActvity = $("#detailActvity"); //活动明细地址
				var detailRecord = $("#detailRecord"); //参与明细地址
				var data=data.result;
				var iocn = $("#iocn");
				if(data.activityType==1){	//免尝
					joinNumber.text(data.giveAwardCount);
					newuserNum.text(data.newuserNum);
					consumeAmount.text("￥"+data.consumeAmount.toFixed(2));
					views.text(data.views);
					detailActvity.attr("href","${ctx}/h5/activity/freetry/detail_activity?id="+data.id).text("免尝活动明细").append('<i class="icon-wrap icon-arrow-right"></i>');
					detailRecord.attr("href","${ctx}/h5/activity/record/list_record_init?activityId="+data.id+"&activityType="+data.activityType);
					iocn.attr("class","icon-wrap icon-chang")
					$("#download_material").attr("href","${ctx}/api/v1/common/download_material?title="+data.name+"&id="+data.id+"&type=11");
				}else if(data.activityType==2){  //大转盘
					joinNumber.text(data.giveAwardCount);
					newuserNum.text(data.newuserNum);
					views.text(data.views);
					consumeAmount.text("￥"+data.consumeAmount.toFixed(2));
					detailActvity.attr("href","${ctx}/h5/activity/roullete/detail_activity?id="+data.id).text("大转盘活动明细").append('<i class="icon-wrap icon-arrow-right"></i>');
					detailRecord.attr("href","${ctx}/h5/activity/record/list_record_init?activityId="+data.id+"&activityType="+data.activityType);
					iocn.attr("class","icon-wrap icon-chou");
					$("#download_material").attr("href","${ctx}/api/v1/common/download_material?title="+data.name+"&id="+data.id+"&type=10");
				}else if(data.activityType==3){  //秒杀
					joinNumber.text(data.giveAwardCount);
					newuserNum.text(data.newuserNum);
					views.text(data.views);
					consumeAmount.text("￥"+data.consumeAmount.toFixed(2));
					detailActvity.attr("href","${ctx}/h5/activity/kill/detail_activity?id="+data.id).text("秒杀活动明细").append('<i class="icon-wrap icon-arrow-right"></i>');
					detailRecord.attr("href","${ctx}/h5/activity/record/list_record_init?activityId="+data.id+"&activityType="+data.activityType);
					iocn.attr("class","icon-wrap icon-xian-3");
					$("#download_material").attr("href","${ctx}/api/v1/common/download_material?title="+data.name+"&id="+data.id+"&type=9");
				}else if(data.activityType==4){
					$(".datadetails-ht").hide();
					joinNumber.text(data.joinNumber);
					joinNumber.parent().find("font").text('参与人数');
					newuserNum.text(data.countPoints);
					newuserNum.parent().find("font").text('已集赞');
					views.text(data.conversionNumber);
					views.parent().find("font").text('已兑换');
					iocn.attr("class","icon-direction-l icon-wrap icon-jiyou");
					consumeAmount.text("￥"+data.consumeAmount.toFixed(2));
					detailActvity.attr("href","${ctx}/h5/activity/fcouspoints/detail_activity?id="+data.id).text("集点活动明细").append('<i class="icon-wrap icon-arrow-right"></i>');
					detailRecord.attr("href","${ctx}/h5/activity/fcouspoints/list_fcouspoints_init?id="+data.id);
					$("#download_material").attr("href","${ctx}/api/v1/common/download_material?title=集点活动"+"&id="+data.id+"&type=12");
				}	
				$("#activityId").val(data.id);
				$("#activityType").val(data.activityType);
				var beingDate = new Date(data.beginDate);
				var endDate = new Date(data.endDate);
				var startingDay = parseInt((beingDate.getTime() - new Date().getTime())/ (1000 * 60 * 60 * 24));
				var endDay = parseInt((new Date().getTime()-endDate.getTime())/ (1000 * 60 * 60 * 24));
				if(data.status==1){
					$("#dateSpace").append("活动已终止~");
					$("#activtyStatus").hide();
				}else if(new Date().getTime()-endDate.getTime()>0){
					$("#dateSpace").append("活动已结束"+endDay+"天~");
					$("#activtyStatus").hide();
				}else if(beingDate.getTime() - new Date().getTime()>0){
					$("#dateSpace").append("活动还有"+startingDay+"天开始~");
				}else{	
					endDay=Math.abs(endDay)+1;
					$("#dateSpace").append("活动已进行"+Math.abs(startingDay)+"天，距结束"+endDay+"天~");
				}
				loading.hide()
			}
			
		});
		
		$("#endActivity").on("click",function(){
				var dialogObject = {};
				dialogObject.title='提示';
		        dialogObject.content = '是否停止活动';
		        dialogObject.secondBtnEvent=function(){
						var activityId=$("#activityId").val();
						var activityType=$("#activityType").val();
						var param = {"activityId":activityId,activityType:activityType};
						$.ajax({
				        	type:"post",
				        	url: '${ctx}/h5/activity/record/end_activity',
				        	data:param,
				        	dataType:"json",
				        	success:function(data,status){
				        	if(status=="success"){
								if(data.state==0){
									Tips.show("终止成功");
									window.location.href="${ctx}/h5/activity/record/list?sessionToken="+data.result;
								}
							}
				        	}
				        });
		       }
		       var dialogWrap = new dialog(dialogObject)
		});

	});
</script>

</html>