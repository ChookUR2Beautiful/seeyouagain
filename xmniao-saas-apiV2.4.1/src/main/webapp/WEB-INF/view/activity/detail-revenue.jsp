<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>营收交易详情</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
</head>
<body class="padd-fill-tb bg-color-01">
    <div class="container-wrap">
        <div class="detailts-divhead-module" id="bid_status">
           
        </div>
        <div class="data-details-module">
            <div class="data-details-content">
                 <div class="data-datails-money" id="income">￥00.00</div>
                 <div class="data-datails-text">实际营收</div>
            </div>
            <div class="data-details-date">
                <div class="data-details-tate-wrap" id="sdate">
                    
                </div>
            </div>
        </div>
        <div class="line-desc-divhead">
            <i class="divhead-line"></i>
            <span class="divhea-line-text line-width-04">交易明细</span>
        </div>
        <div class="fill-list-module" >
            <div class="list-wrap">
                <div class="list-item item-icon-left custom-fill-coll">
                    <i class="icon-wrap icon-wrap-img"><img src="${ctx}/imgs/revenue/Consumer_members1@2x.png" alt="" id="head"/></i>
                   <span class="item-coll direc-l">
                       <strong id="nname"></strong>
                       <font id="userType"></font>
                   </span>
                   <span class="item-coll direc-r item-row-2" id="times">
                       <font class="sub-font">${record.givePoints}点</font>
                   </span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item">
                    <span class="item-input-wrap" id="money">￥0.00</span>
                    <span class="item-name">消费金额</span>
                </div>
            </div>
            <div class="list-wrap">
                <div class="list-item">
                    <span class="item-input-wrap" id="percent">0%</span>
                    <span class="item-name">签约折扣</span>
                </div>
            </div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap" id="couponsMoney"><em class="fon-color">（优惠券）</em>-￥0.00</span><span class="item-name">优惠减免</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap" id="income1">￥0.00</span><span class="item-name">实际支付</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap" id="rebate">￥0.00</span><span class="item-name">总分张金额</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap" >${record.payType}</span><span class="item-name">支付方式</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap" id="codeid"></span><span class="item-name">消费验证号</span></div></div>
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript">
	var bid="${record.bid}";
	var bfirst="${record.bfirst}";
	var sessionToken="${sessionToken}"
	var obj={"bid":bid,"sessionToken":sessionToken,"appVersion":"1.0.0",searchType:1}
	$.post("${ctx}/api/v1/bill/detail",obj,function(data, status){
		if (status == "success") {
			if (data.state == 0) {
				var result=data.result; 
				if(result.status==1){
					var html='';
					html+='<div class="detailts-divhead-module-wrap">';
                	html+='<div class="detailts-divhead-wrap-name direc-l">订单编号：'+result.bid+'</div>';
               		html+='<div class="detailts-divhead-wrap-desc direc-r mark-desc">';
                    html+='<span class="sub-font">已分账</span>';
               		html+=' </div>';
           			html+=' </div>';
           			$("#bid_status").html(html);
				}else{
					var html='';
					html+='<div class="detailts-divhead-module-wrap">';
                	html+='<div class="detailts-divhead-wrap-name direc-l">订单编号：'+result.bid+'</div>';
               		html+='<div class="detailts-divhead-wrap-desc direc-r">';
                    html+='<span class="sub-font">未分账</span>';
               		html+=' </div>';
           			html+=' </div>';
           			$("#bid_status").html(html);
				}
				$("#income").text("￥"+result.income);
				$("#sdate").text('交易时间：'+result.sdate);
				var head=result.avatar;
				if(head){
				   $("#head").attr("src","${imageHost}/"+head);
				}
				if(result.nname){
					$("#nname").text(result.nname);
				}else{
					$("#nname").text('匿名');
				}
				if(result.userType=="1"){
					$("#userType").text("普通会员");
				}else{
					$("#userType").text("绑定会员");
				}
				if(bfirst=="1"){
					$("#times").html('<i class="icon-rectangle">首次<br>消费</i>');
				}
				$("#money").text("￥"+result.money);
				$("#percent").text(result.percent+"%");
				$("#couponsMoney").html('<em class="fon-color">（优惠券）</em>-￥'+result.couponsMoney);
				$("#income1").text("￥"+result.income);
				$("#codeid").text(result.codeid);
			}
		}
	})
</script>
</html>