<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
<!--
.unnamed1 {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #000000;
	border-right-color: #000000;
	border-bottom-color: #000000;
	border-left-color: #000000;
}
-->
</style>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>查看商家信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">

<!-- 图片弹出样式 -->
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />


<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<!-- viewType：viewSellerPending 商家待审核查看-->
	<input type = "hidden" id ="viewType" value = "${param.viewType}">
	<input type="hidden" id="statuschage" value="${selleridList.status}">
	<input type="hidden" id="signdateId" value="<fmt:formatDate value="${selleridList.signdate}" pattern="yyyy-MM-dd"/>">
	<input type="hidden" id="isonlineId" value="${selleridList.isonline}">
	<input type="hidden" id="sellerLandmarkLidId" value="${sellerLandmarkList.lid}">
	<dir></dir>
	<div id="main" style="min-height: 903px;">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="sellerForm" role="form">
						<input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
						<table class="table" style="text-align: center;">
							<tr>
								<td style="width:10%;">
										<h5>商家名称:</h5> 
								</td>
								<td style="width:23.3%;">
									 <input disabled="disabled" type="text" name = "sellername" class="form-control"  value = "${selleridList.sellername}"> 
								</td>
								<td style="width:10%;">
										<h5>商家地址:</h5> 
								</td>
								<td colspan="3">
									  <input disabled="disabled" type="text" class="form-control" name="address"  value="${selleridList.address}">
								</td>
							</tr>
							
							<tr>
								<td style="width:80px;">
										<h5>所在区域:</h5> 
								</td>
								<td >
									<div class="input-group" style="width:100%;" id="areaSelect" initValue="${selleridList.area}"></div>
								</td>
								
								<td style="width:10%;">
									<h5>所属商圈:</h5> 
								</td>
								<td style="width:23.3%;">
									<select class="form-control" disabled id="zoneid" name="zoneid" initValue="${selleridList.zoneid}">
										<option value="">暂无数据</option>
									</select>
								</td>
								
								<td style="width:10%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属合作商：</h5></td>
								<td style="width:23.3%;" style="text-align: left">
								    <select class="form-control" id="jointid" name="jointid" style="width:100%;" initValue="${selleridList.jointid}" disabled="disabled">
								    	<option value="">请先选择区域</option>
								    </select>
								</td>
							</tr>
							
							<tr>
							   <td class="sellerTitleCss">
									<h5>所属寻蜜客:</font></h5>
							   </td>
							   <td class="xmerContentCss">
							 		<select class="form-control" id="uid" name="uid" initValue="${selleridList.uid}" style="width:100%;" disabled="disabled"></select>
							   </td> 
							   <td>
										<h5>&nbsp;&nbsp;联盟店:</h5> 
							   </td>
							   <td style="text-align: left">
								       <select class="form-control" id="allianceId" name ="allianceId" styl ="width:100%;" initValue="${selleridList.allianceId}" disabled="disabled">
								       </select>
							   </td>
								
							   <td >
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;连锁店：</h5> 
							   </td>
							   <td style="text-align: left">
								       <select class="form-control" id="fatherid" name ="fatherid" styl ="width:100%;" initValue="${selleridList.fatherid}" disabled="disabled">
								       </select>
								       <input type="hidden" class="form-control" name="lssellername" value="${selleridList.lssellername}">
							    </td>
							</tr>
							
							<tr>
								<td style="width:80px;"><h5>经营行业：</h5></td>
								<td style="width:120px;">
									<input type="hidden" name="typename" value="${selleridList.typename}"/>
									<input type="hidden" name="tradename" value="${selleridList.tradename}"/>
									
									<div class="input-group" id="tradeSelect" style="width : 100%" initValue="${selleridList.genre}"></div>
									<%-- <div class="form-group" id="includeTrade">
										<label class="col-md-2 control-label"></label>
										<div class="col-md-5" style="width : 100%;">
											<c:if test="${!empty selleridList.traderRefs}">
												<c:forEach items="${selleridList.traderRefs}" var="trade">
													<div class="input-group">
														<div class="includeTradeSelect" style="width : 100%;" initvalue="${trade.genre}"></div>
														<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer"></i></span> 
														<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer"></i></span>
													</div>
												</c:forEach>
											</c:if>
										</div>
									</div>	
									 --%>
								</td>
								
								<td style="width:80px;">
										<h5>营业执照:</h5> 
								</td>
								<td style="width:120px;">
									   <input disabled="disabled" type="text" class="form-control" name="licenseid"  value="${selleridList.licenseid}">
								</td>
								<td><h5>是否是美食星势力商户:</h5></td> 
		 						<td style="text-align: left;">
		 							<input  name="isforce" value="1" type="radio" ${selleridList.isforce==1?'checked':''} disabled="disabled" ><span style="font-size: 12px;width: 60px;">是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input name="isforce" value="0" type="radio" ${selleridList.isforce==0?'checked':''} disabled="disabled"><span style="font-size: 12px;">否</span>
		 						</td>
							</tr>
							
							<tr>
								<td style="width:10%;">
									<h5>联系电话：</h5> 
								</td>
								<td style="width:23.3%;" >
									 <input disabled="disabled" type="text" class="form-control" name="tel"  placeholder="联系电话" value="${selleridList.tel}">
								</td>
							
								<td style="width:10%;">
										<h5>联系人号码:</h5> 
								</td>
								<td style="width:23.3%;">
									 <input disabled="disabled" type="text" class="form-control" name="phoneid"  value="${selleridList.phoneid}">
								</td>
								
								<td style="width:10%;">
										<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法人姓名:</h5> 
								</td>
								<td style="width:23.3%;">
									  <input disabled="disabled" type="text" class="form-control" name="fullname"  value="${selleridList.fullname}">
								</td>
							</tr>
							<tr>
								<td><h5>营业时间：</h5></td>
							    <td>
							    	<input type="text" class="form-control form-time" name="sdate1" style="width:45%;float:left;" value="${selleridList.sdate1}" disabled/>
							    	<span style="float:left;width:10%;">&nbsp;~&nbsp;</span>
							    	<input type="text" class="form-control form-time" name="sdate2" style="width:45%;float:left;" value="${selleridList.sdate2}" disabled/>
							    </td>
							
								<td>
										<h5>有效开始日期:</h5> 
								</td>
								<td>								 
									   <input disabled="disabled" type="text" class="form-control form-datetime" title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd"/>" name="svalidity"  value="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>">
								</td>
								<td>
									  <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有效结束日期:</h5> 
								</td>
								<td>								 
									  <input disabled="disabled" type="text" class="form-control form-datetime" title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd"/>" name="evalidity"  value="<fmt:formatDate value="${selleridList.evalidity}" pattern="yyyy-MM-dd HH:mm"/>">
								</td>
							</tr>
							
							<tr>
							    <td><h5>&nbsp;&nbsp;是否折上折:</h5></td> 
		 						<td style="text-align: left;">
		 							<h5>
			 							<input disabled="disabled" name="agioAgio" value="1"type="radio" ${selleridList.agioAgio==1?'checked':''}><span style="font-size: 12px;" >是</span>
			 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 							<input disabled="disabled" name="agioAgio" value="0" type="radio" ${selleridList.agioAgio==0?'checked':''}><span style="font-size: 12px;">否</span>
		 							</h5>
		 							<div id="agioInstructionDiv">
										<c:if test="${selleridList.agioAgio==1}">
											<input placeholder="全单折扣" class="form-control" name="agioAgioNum" value="${selleridList.agioAgioNum}" disabled="disabled">
											<textarea placeholder="折上折说明……" class="form-control"
												name="agioInstruction" disabled="disabled">${selleridList.agioInstruction}</textarea>
										</c:if>
									</div>
								</td>
		 												   
								<td><h5>总部帮忙签约:</h5></td> <%-- type="radio" <c:if test="${selleridList.give==1}">checked</c:if> --%>
		 						<td style="text-align: left;">
		 							<input disabled="disabled" name="give" value="1"type="radio" ${selleridList.give==1?'checked':''} ><span style="font-size: 12px;width: 60px;">是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input disabled="disabled" name="give" value="0" type="radio" ${selleridList.give==0?'checked':''} ><span style="font-size: 12px;">否</span>
		 						</td>
		 						
								<td><h5>&nbsp;&nbsp;是否扣取支付手续费：</h5></td> 
		 						<td style="text-align: left;">
		 							<input  name="isfees" value="0" type="radio" ${selleridList.isfees==0?'checked':''} disabled="disabled" ><span style="font-size: 12px;width: 60px;">是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input name="isfees" value="1" type="radio" ${selleridList.isfees==1?'checked':''} disabled="disabled"><span style="font-size: 12px;">否</span>
		 						</td>
							</tr>
							<tr>
<%--							
									<td><h5>&nbsp;&nbsp;订单基数：</h5></td>
									<td>
										<input type="text" class="form-control" name="order"
										placeholder="订单基数" value="${selleridList.order}" disabled="disabled">
									</td>
	
									<td><h5>&nbsp;&nbsp;节省钱基数：</h5></td>
									<td>
										<input type="text" class="form-control" name="saveMoney"
										placeholder="节省钱基数" value="${selleridList.saveMoney}" disabled="disabled">
									</td>
 --%>	
 								<td><h5>商家标签:</h5></td>
								<td>
									<select class="form-control" name="label" disabled="disabled">
										<option value="0"
											<c:if test="${selleridList.label == 0}">selected</c:if>>无</option>
										<option value="1"
											<c:if test="${selleridList.label == 1}">selected</c:if>>推荐商家</option>
										<option value="2"
											<c:if test="${selleridList.label == 2}">selected</c:if>>省钱实惠</option>
										<option value="3"
											<c:if test="${selleridList.label == 3}">selected</c:if>>热门商家</option>
									</select>
								</td>
								<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否为KA商户：</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="isKa" value="1" type="radio" ${selleridList.isKa==1?'checked':'' } disabled="disabled"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isKa" value="0" type="radio" ${selleridList.isKa==0?'checked':'' } disabled="disabled"><span
											style="font-size: 12px;">否</span>
									</h5>
								</td>
							</tr>
							<tr>
								<td><h5>每日商家提现限额:</h5></td>
								<td>
								<input disabled="disabled"  type="text" name = "dailyLimitWithdraw" class="form-control"  value = "${selleridList.dailyLimitWithdraw}"> 
								</td>
								<td><h5>SAAS交易总额:</h5></td>
								<td>
								<input disabled="disabled"  type="text" name = "totalLimitTurnover" class="form-control"  value = "${selleridList.totalLimitTurnover}"> 
								</td>
								<td><h5>每日交易限额:</h5></td>
								<td>
								<input disabled="disabled"  type="text" name = "dailyLimitTurnover" class="form-control"  value = "${selleridList.dailyLimitTurnover}"> 
								</td>
							</tr>
							<tr>
								<td><h5>是否公开商户:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "isPublic" value = "1" ${selleridList.isPublic==1?'checked':''} disabled><span style="font-size: 12px;">是</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input  type="radio" name = "isPublic" value = "0" ${selleridList.isPublic==0?'checked':''} disabled><span style="font-size: 12px;">否</span> 
								</td>
								<td><h5>是否参与分红:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "joinDividend" value = "1" ${selleridList.joinDividend==1?'checked':''} disabled><span style="font-size: 12px;">是</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input  type="radio" name = "joinDividend" value = "0" ${selleridList.joinDividend==0?'checked':''} disabled><span style="font-size: 12px;">否</span> 
								</td>
								<td><h5>是否设为付费商家:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "isPaid" value = "1" ${selleridList.isPaid==1?'checked':''} disabled><span style="font-size: 12px;">是</span> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input  type="radio" name = "isPaid" value = "0" ${selleridList.isPaid==0?'checked':''} disabled><span style="font-size: 12px;">否</span> 
								</td>
							</tr>
							<tr>
								<td><h5>营业执照名称：</h5></td>
								<td><input disabled="disabled" type="text" class="form-control" name="businessLicenseName"
										   placeholder="营业执照名称" value="${selleridList.businessLicenseName}"></td>
								<td>
							</tr>
							<!-- 折扣信息=================================================================================================== -->
				</table>
				<hr>
				<table class="table" style="text-align: center;">	
							<tr>
								<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分账模式:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="ledgerMode" value="0" type="radio" ${selleridList.ledgerMode==0?'checked':'' } disabled="disabled"><span
											style="font-size: 12px;">正常分账模式</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="ledgerMode" value="2" type="radio" ${selleridList.ledgerMode==2?'checked':'' } disabled="disabled"><span
											style="font-size: 12px;">仅商家参与分账模式</span>
									</h5>
								</td>
								<td style="width:10%;">
										<h5>折扣:</h5> 
							    </td>
								<td style="width:23.3%">
									    <input type="hidden" class="form-control" id="sellerAgioId"  name="aid"  value="${sellerAgio.aid}">
									   <input type="text" class="form-control" readonly="readonly" placeholder="折扣数为0.01-0.99之间的小数" name="baseagio"  value="${sellerAgio.baseagio}">
							    </td> 
							    
							     <%--<td style="width:10%;">--%>
										<%--<h5>平台占比:</h5> --%>
							    <%--</td>--%>
								<%--<td style="width:23.3%;">--%>
									   <%--<input type="text" class="form-control" readonly="readonly" id="pledger" name="pledger"  value="${sellerAgio.pledger}">--%>
							    <%--</td> --%>
							    <%----%>
							    <%--<td style="width:10%;">--%>
										<%--<h5>用户占比:</h5> --%>
							    <%--</td>--%>
								<%--<td style="width:23.3%;">--%>
									   <%--<input type="text" class="form-control" readonly="readonly" name="yledger"  value="${sellerAgio.yledger}">--%>
							    <%--</td> --%>
							</tr>
						
							<%--<tr>--%>
							    <%--<td>--%>
										<%--<h5>营业收入:</h5> --%>
							    <%--</td>--%>
								<%--<td>--%>
									   <%--<input type="text" class="form-control"  readonly="readonly" name="income"  value="${sellerAgio.income}">--%>
							    <%--</td> --%>
							    <%----%>
							    <%--<td>--%>
										<%--<h5>商户占比:</h5> --%>
							    <%--</td>--%>
								<%--<td>--%>
									   <%--<input type="text" class="form-control" readonly="readonly" name="sledger"  value="${sellerAgio.sledger}">--%>
							    <%--</td>--%>
								<%--<td >--%>
										<%--<h5>平台补贴（%）:</h5> --%>
							    <%--</td>--%>
								<%--<td >--%>
									   <%--<input type="text" class="form-control" readonly="readonly" name="flatAgio"  value="${sellerAgio.flatAgio}">--%>
							    <%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
							    <%--<td>--%>
										<%--<h5>佣金补贴比例（%）:</h5> --%>
							    <%--</td>--%>
								<%--<td>--%>
									   <%--<input type="text" class="form-control"  readonly="readonly" name="ratio"  value="${selleridList.ratioStr}">--%>
							    <%--</td> --%>
							    <%--<td class="sellerTitleCss">--%>
									<%--<h5>平台扣款比例（%）:</h5>--%>
								<%--</td>--%>
								<%--<td class="sellerContentCss"><input type="text"--%>
									<%--class="form-control" readonly="readonly" name="debit"--%>
									<%--value="${selleridList.debit}"></td>--%>
							<%--</tr>--%>
							<!-- 折扣信息=================================================================================================== -->
				</table>
				<hr>
				<!-- 直播分账设置=================================================================================================== -->
				<table class="table" style="text-align: center;" width="100%;">
					<tr>
						<input type="hidden" name="liveLedger.id" value="${selleridList.liveLedger.id}">
						<td class="sellerTitleCss"><h5>直播分账开关:</h5></td>
						<td style="text-align: left;">
							<h5>
								<input name="liveLedgerOperating" value="1" type="radio" ${selleridList.liveLedgerOperating==1?'checked':'' } disabled="disabled"><span
									style="font-size: 12px;">开启</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="liveLedgerOperating" value="0" type="radio" ${selleridList.liveLedgerOperating==0?'checked':'' } disabled="disabled"><span
									style="font-size: 12px;">不开启</span>
							</h5>
						</td>
					</tr>
				</table>
				<table class="table liveLedgerTable" style="text-align: center;" width="100%;" hidden>
					<tr>
						<td class="width: 10%"><h5>直播分账操作:</h5></td>
						<td style="text-align: left;" class="width: 25%">
							<h5>
								<input name="liveLedger.ledgerStyle" value="0" type="radio" ${selleridList.liveLedger.ledgerStyle!=1?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">自动分账</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="liveLedger.ledgerStyle" value="1" type="radio" ${selleridList.liveLedger.ledgerStyle==1?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">手动分账</span>
							</h5>
						</td>
						<td class="width: 10%"><h5>直播分账模式:</h5></td>
						<td style="text-align: left;" class="width: 25%">
							<h5>
								<input name="liveLedger.ledgerMode" value="1" type="radio" ${selleridList.liveLedger.ledgerMode!=2?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">全部分账模式</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="liveLedger.ledgerMode" value="2" type="radio" ${selleridList.liveLedger.ledgerMode==2?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">仅针对粉丝券分账</span>
							</h5>
						</td>
					</tr>
					<tr>
						<td class="width: 10%">
							<h5>分账比例（%）:</h5>
						</td>
						<td class="width: 25%"><input type="text"
							class="form-control" placeholder="请输入0-100之间的数" name="liveLedger.ledgerRatio"
							value="${selleridList.liveLedger.ledgerRatio}" disabled="disabled"></td>
							
						<td class="width: 10%">
							<h5>有效时间:</h5>
						</td>
						<td class="width: 25%">
							<input type="text" class="form-control form-datetime"
								name="liveLedger.startDate" readonly style="width:45%;float:left;"
								value="<fmt:formatDate value="${selleridList.liveLedger.startDate}" pattern="yyyy-MM-dd HH:mm:SS"/>" disabled="disabled"/> 
							<span style="float:left;width:10%;">&nbsp;~&nbsp;</span> 
							<input
								type="text" class="form-control form-datetime" name="liveLedger.endDate"
								readonly style="width:45%;float:left;"
								value="<fmt:formatDate value="${selleridList.liveLedger.endDate}" pattern="yyyy-MM-dd HH:mm:SS"/>" disabled="disabled"/>
						</td>
						<td></td><td></td>
					</tr>
				</table>
				<hr>
				<table class="table" style="text-align: center;" width="100%;">
					<tr>
						<td><h5>鸟币支付开关:</h5></td>
						<td style="text-align: left;">
							<h5>
								<input name="liveCoinPay" value="1" type="radio" ${selleridList.liveCoinPay==1?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">开启</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="liveCoinPay" value="0" type="radio" ${selleridList.liveCoinPay==0?'checked':''} disabled="disabled"><span
									style="font-size: 12px;">关闭</span>
							</h5>
						</td>
						<td><h5>(开启鸟币支付后，仅消费商家可获得营收分账，其他角色将不参加分账)</h5></td>
					</tr>
				</table>
				<hr>
				<table class="table" style="text-align: center;">							
							<tr>
							    <td style="width:10%;">
										<h5>审批说明:</h5> 
								</td>
								<td colspan = "10">
										<textarea disabled="disabled" name="examineinfo" rows="3" class="form-control">${selleridList.examineinfo}</textarea>
								</td>
							</tr>
							
							<tr>
							    <td style="width:10%;" >
										<h5>备注:</h5>
								</td>
								<td colspan = "10">
										<textarea disabled="disabled" name="remarks" rows="3" class="form-control" >${selleridList.remarks}</textarea>
								</td>
							</tr>
				</table>
				<hr>
				<table class="table" style="text-align: center;">
					<tr>	
						<td style="width:10%;"><h5>商家LOGO:</h5> </td>							
						<td style="text-align: left;width:15%;">
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"  rel="slide" title="商家LOGO" class="fancybox"  id="logo">
								<input type="hidden" value="${selleridList.url}" id="sellerUrlId">
								<img alt="商家LOGO"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"   style="width: 100px;height: 100px;">			
							</a>
						</td> 
									
						<td style="width:10%;"><h5>营业执照正本附件:</h5> </td>							
						<td style="text-align: left;width:15%;">
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licenseurl}"  rel="slide" title="营业执照正本附件" class="fancybox"  id="yyzzzm">
								<img alt="营业执照正本附件"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licenseurl}"   style="width: 100px;height: 100px;">			
							</a>
						</td> 
					
					    <td style="width:10%;"><h5>营业执照副本附件:</h5> </td>
						<td style="text-align: left;width:15%;">
						   <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" rel="slide" title="营业执照副本附件"  class="fancybox"  id="yyzzfm">
						  	  <img alt="营业执照副本附件" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" style="width: 100px;height: 100px;">
						   </a>
						</td>





					</tr>
					
					<tr>

						<td style="width:10%;">
							<h5>卫生许可证:</h5>
						</td>
						<td style="text-align: left;width:15%;">
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.hygienicLicenseImg}"  rel="slide" rel="slide" title="卫生许可证" class="fancybox"  id="hygienicLicenseImg">
								<img alt="卫生许可证"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.hygienicLicenseImg}"   style="width: 100px;height: 100px;">
							</a>
						</td>


						<td style="width:10%;">
							<h5>商家图片:</h5>
						</td>
						<td style="text-align: left">
							<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.picUrl}"  rel="slide" rel="slide" title="商家图片" class="fancybox"  id="logo">
								<input type="hidden" id="sellerPicUrlId" value="${selleridList.picUrl}"/>
								<img alt="商家图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.picUrl}"   style="width: 100px;height: 100px;">
							</a>
						</td>
					</tr>

					<tr>
						<td style="width:10%;"><h5>身份证附件正面:</h5> 
						</td>
						<td style="text-align: left;width:23.3%;">
							<a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityzurl}" rel="slide" title="身份证附件正面"  class="fancybox" id="sfzzm" >	 
								 <img alt="身份证附件正面" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityzurl}" style="width: 100px;height: 100px;">							
						   </a>
						</td>
						
						<td style="width:10%;">
							<h5>身份证附件反面:</h5> 
						</td>
						<td style="text-align: left;width:23.3%;">
							<a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityfurl}" rel="slide" title="身份证附件反面" class="fancybox" id="sfzfm" >	 
							    <img alt="身份证附件反面" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityfurl}" style="width: 100px;height: 100px;">													 
						    </a>
						</td> 
						
						<td style="width:10%;">
							<h5>手持身份证正面照:</h5> 
						</td>
						<td style="text-align: left;width:23.3%;">
							<a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identitynurl}" rel="slide" title="手持身份证正面照" class="fancybox" id="scsfzzmz" >	 
							    <img alt="手持身份证正面照" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identitynurl}" style="width: 100px;height: 100px;">													 
						    </a>
						</td> 
					</tr>
					
					<tr>
						<td style="width:10%;">
							<h5>商户合同:</h5> 
						</td>
						<td style="text-align: left;" colspan="3">
							<a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement}" rel="slide" title="商户合同（一）" class="fancybox col-md-3" id="ht1" >	 
							    <img alt="商户合同（一）" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement}" style="width: 100px;height: 100px;">													 
						    </a>
						    <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement2}" rel="slide" title="商户合同（二）" class="fancybox col-md-3" id="ht2" >	 
							    <img alt="商户合同（二）" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement2}" style="width: 100px;height: 100px;">													 
						    </a>
						    <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement3}" rel="slide" title="商户合同（三）" class="fancybox col-md-3" id="ht3" >	 
							    <img alt="商户合同（三）" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement3}" style="width: 100px;height: 100px;">													 
						    </a>
						    <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement4}" rel="slide" title="商户合同（四）" class="fancybox col-md-3" id="ht4" >	 
							    <img alt="商户合同（四）" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement4}" style="width: 100px;height: 100px;">													 
						    </a>
							</td>
				  </tr>
				</table>
					</form>
				</div>
			</div>
		</div>
				
		<!-- 经纬度信息 -->
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">商家经纬度信息</div>
				<div class="panel-body">
					<form action="" id="sellerLandmarkFromId">
						<input type="hidden" name="sellerLandmarkToken"
							value="${sellerLandmarkToken}"> <input type="hidden"
							name="sellerid" value="${selleridList.sellerid}" /> <input
							type="hidden" name="lid" id="lid"
							value="${sellerLandmarkList.lid}" />
						<table class="table" style="text-align: center;">
							<tr>
								<td>
									<table class="table" style="text-align: center;">
										<tr>
											<td class="sellerTitleCss">
												<h5>经度:</h5>
											</td>
											<td class="sellerContentCss"><input type="text"
												name="longitude" class="form-control" disabled="disabled" 
												value="${sellerLandmarkList.longitude}"></td>
											<td width="45%"><span><h5 style="color: red;">
														<font>请使用高德地图经度（范围 : 73.240&lt;经度&lt;135.150）</font>
													</h5></span></td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>纬度:</h5>
											</td>
											<td class="sellerContentCss"><input type="text"
												name="latitude" class="form-control" disabled="disabled" 
												value="${sellerLandmarkList.latitude}"></td>
											<td width="45%"><span><h5 style="color: red;">
														<font>请使用高德地图纬度（范围 : 3.3120&lt;纬度&lt;53.1980）</font>
													</h5></span></td>
										</tr>

									</table>
								</td>
								<td rowspan="2"
									style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
									<br>
									<button class="btn btn-danger" type="submit">
										<i class="icon-save"></i>&nbsp;保存
									</button>&nbsp; <!-- <button class="btn btn-warning" type="reset" id="backlongitudeId"><i class="icon-reply"></i>&nbsp;返回</button>		 -->
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<!-- 详细信息 -->
		<div class="example" >
		<div class="panel panel-primary">
		<div class="panel-heading">详细信息</div>
		<div class="panel-body">
		   <form id="sellerDetailedForm">
		    <input type="hidden" name="sellerDetailedToken" value="${sellerDetailedToken}">
			<table class="table" style="text-align: center;">
				<tr>
					<td>
					<table class="table" style="text-align: center;">
						<tr>
							<td class = "sellerTitleCss">
									<h5>WIFI:</h5> 
							</td>
							<td class="width: 18%">
								<select class="form-control" name="sellerDetailed.iswifi" disabled="disabled" value="${detailed.sellerDetailed.iswifi}">
									<option value="">请选择</option>
					                <option value="0" <c:if test="${detailed.sellerDetailed.iswifi==0}">selected</c:if>>没有</option>
					                <option value="1" <c:if test="${detailed.sellerDetailed.iswifi==1}">selected</c:if>>免费提供</option>
					                <option value="2" <c:if test="${detailed.sellerDetailed.iswifi==2}">selected</c:if>>有偿提供</option>
				                </select>
							</td>
							<td class = "sellerTitleCss">
									<h5>停车位:</h5> 
							</td>
							<td class="width: 18%">
								<select class="form-control" disabled="disabled" name="sellerDetailed.isparking" value="${detailed.sellerDetailed.isparking}">
									<option value="">请选择</option>
					                <option value="0" <c:if test="${detailed.sellerDetailed.isparking==0}">selected</c:if>>没有</option>
					                <option value="1" <c:if test="${detailed.sellerDetailed.isparking==1}">selected</c:if>>免费提供</option>
					                <option value="2" <c:if test="${detailed.sellerDetailed.isparking==2}">selected</c:if>>有偿提供</option>
				                </select>
							</td>
							<td class = "sellerTitleCss">
									<h5>独立包间:</h5> 
							</td>
							<td class="width: 18%">
								<select class="form-control" disabled="disabled" name="sellerDetailed.isroom" value="${detailed.sellerDetailed.isroom}">
									<option value="">请选择</option>
					                <option value="0" <c:if test="${detailed.sellerDetailed.isroom==null || detailed.sellerDetailed.isroom==0}">selected</c:if>>未知</option>
					                <option value="1" <c:if test="${detailed.sellerDetailed.isroom==1}">selected</c:if>>有</option>
					                <option value="2" <c:if test="${detailed.sellerDetailed.isroom==2}">selected</c:if>>没有</option>
				                </select>
							</td>
							 <%-- <td class="sellerTitleCss">
												<h5>是否开启首次:</h5>
											</td>
											<td class="sellerContentCss"><select
												class="form-control" disabled="disabled" name="sellerDetailed.isFirst"
												value="${detailed.sellerDetailed.isFirst}">
													<option value="0"
														<c:if test="${detailed.sellerDetailed.isFirst==0}">selected</c:if>>否</option>
													<option value="1"
														<c:if test="${detailed.sellerDetailed.isFirst==1}">selected</c:if>>是</option>
													
											</select></td> --%>
							<%--<td class="sellerTitleCss">
							<h5>是否开启打赏功能:</h5> 
							</td>
							<td class="sellerContentCss">
								 <select class="form-control" disabled="disabled" name="sellerDetailed.isPay" value="${detailed.sellerDetailed.isPay}">
			                           <option value="1" <c:if test="${detailed.sellerDetailed.isPay==1}">selected</c:if>>是</option>
			                           <option value="0" <c:if test="${detailed.sellerDetailed.isPay==0}">selected</c:if>>否</option>
		                         </select>  
							</td> --%>
							<td class="sellerTitleCss">
								<h5>参考地标:</h5>
							</td>
							<td><input type="text" class="form-control"
								disabled="disabled" placeholder="参考地标"
								name="sellerDetailed.landmark"
								value="${detailed.sellerDetailed.landmark}"></td>
						</tr>
						<tr>
						<td class="sellerTitleCss">
									<h5>商家wifi名:</h5> 
							</td>
							<td class="width: 18%">
							      <input type="hidden" id="selleridid" name="sellerDetailed.sellerid" value="${selleridList.sellerid}"/>
								  <input type="text" class="form-control" disabled="disabled" placeholder="wifi名" name="sellerDetailed.WIFIName" value="${detailed.sellerDetailed.WIFIName}">
								  
							</td>
							<td class="sellerTitleCss">
									<h5>wifi密码:</h5> 
							</td>
							<td class="width: 18%">
								  <input type="text" class="form-control" disabled="disabled" placeholder="wifi密码" name="sellerDetailed.WIFIPassword" value="${detailed.sellerDetailed.WIFIPassword}">
								  
							</td>
							<td class="sellerTitleCss">
								<h5>单次提现限额:</h5>
							</td>
							<td><input type="text" class="form-control"
								name="sellerDetailed.amountLimit" placeholder="50000.0元"
								value="${detailed.sellerDetailed.amountLimit}" disabled="disabled"></td>
						</tr>
<%--						<tr>
							<c:if test="${selleridList.category == 1}">
								<td class="sellerTitleCss">
									<h5>是否开通点菜:</h5>
								</td>
								<td class="sellerContentCss"><select
									class="form-control" disabled="disabled"
									name="sellerDetailed.isOpenBooking"
									value="${detailed.sellerDetailed.isOpenBooking}">
										<option value=""
											<c:if test="${ empty detailed.sellerDetailed.isOpenBooking }">selected</c:if>>请选择</option>
										<option value="1"
											<c:if test="${detailed.sellerDetailed.isOpenBooking==1}">selected</c:if>>是</option>
										<option value="0"
											<c:if test="${detailed.sellerDetailed.isOpenBooking == 0}">selected</c:if>>否</option>
								</select></td>
							</c:if>
						</tr>
 --%>						
						<tr>
						
						<tr>
							<td class = "sellerTitleCss" >
									<h5>商家推荐:</h5> 
									<h5><font color="red">（限定10个,多个用";"号隔开）</font></h5> 
							</td>
							<td   colspan = "9">
									<textarea name="sellerDetailed.dishes" disabled="disabled" rows="3" class="form-control" placeholder="限定10个,多个用';'号隔开">${detailed.sellerDetailed.dishes}</textarea>
							</td>
						</tr>
						<tr>
							<td class="sellerTitleCss">
									<h5>商家环境图片:</h5> 
									<!-- <h5><font color="red">（限定10个,多个用";"号隔开）</font></h5>  -->
							</td>
							<td colspan="9" align="left">
								<input type="hidden" id="sellerPicsNum" value="${fn:length(detailed.sellerPics)}">
								<button class="btn btn-danger" type="button" id="addPic" style="float : left"><i class="icon-plus"></i></button><br/>
								<div id="sellerPicTemp" style="display: none;float : left;text-align: center;">
									<button class="btn btn-warning removebtn" type="button" ><i class="icon-remove"></i></button>
									<input name="picid" type="hidden" />
									<input name="picurl" type="hidden" />
									<input name="bewrite" type="text"  class="form-control" placeholder="图片描述" style="width:100px"/>
									<div class="pic"></div>
								</div>
								<div id="sellerPics" style="float : left;">
								<c:forEach items="${detailed.sellerPics}" varStatus="status" var="pic">
									<div style="float : left;text-align: center;">
										<button class="btn btn-warning removebtn" type="button" ><i class="icon-remove"></i></button>
										<input name="sellerPics[${status.index }].picid" type="hidden" value="${pic.picid}"/>
										<input name="sellerPics[${status.index }].picurl" type="hidden" value="${pic.picurl}" id="sellerPicUrl${status.index}"/>
										<input name="sellerPics[${status.index }].bewrite" type="text" value="${pic.bewrite}" class="form-control" placeholder="图片描述" style="width:100px"/>
										<div style="position:relative;top:0px; left: 0px; width: 100px; height: 100px; overflow: hidden; bottom: auto; right: auto;">
										<img class="img-thumbnail" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${pic.picurl}" >
										</div>
									</div>
								</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td class = "sellerTitleCss" >
									<h5>商家介绍:</h5> 
									
									<h5><font color="red">（商家介绍最多300字）</font></h5> 
							</td>
							<td colspan = "9">
									<textarea name="sellerDetailed.introduce" disabled="disabled" rows="10" class="form-control" placeholder="商家介绍最多300字">${detailed.sellerDetailed.introduce}</textarea>
							</td>
						</tr>
						
						<tr>
						<td colspan="10" align="left"><spn><h5>推广设置信息</h5></spn><hr></td>
						</tr>
						
						<tr>
						<td colspan="10" >
						<div>
							<table>
								<tr>
									<td><h5>摇一摇：</h5>
									<td>
									<td><h5>推广时间：</h5>
									<td>
									<td colspan="1"><input type="hidden" id="selleridid0" name="extensionSet.sellerId0"	value="${selleridList.sellerid}" /> 
									<input	type="hidden" id="extensionType0"	name="extensionSet.extensionType0" value="1" /> 
									<input	type="hidden" id="id0" name="extensionSet.id0"	value="${detailed.extensionSets.yaoyiyao.id}" /> 
									<input	type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSet.dateStart0" placeholder="开始日期"	style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.yaoyiyao.dateStart}" pattern="yyyy-MM-dd"/>">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input	type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd" name="extensionSet.dateEnd0" placeholder="结束日期"	style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.yaoyiyao.dateEnd}" pattern="yyyy-MM-dd"/>">
									<td>
									<td><h5>排序：</h5>
									<td>
									<td class="sellerContentCss"><input type="text"	disabled="disabled" class="form-control" placeholder="排序级别(数字越大越靠前)"	name="extensionSet.sort0"	value="${detailed.extensionSets.yaoyiyao.sort}"></td>
									<td><h5>添加到营销列表：</h5>
									<td>
									<td style="text-align: left;">
										<h5>
											<label><input	disabled="disabled" name="extensionSet.isAddMarketingList0" value="1"	type="radio"	${detailed.extensionSets.yaoyiyao.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;
											<span	style="font-size: 12px;">是</span></label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label>
											<input disabled="disabled"	name="extensionSet.isAddMarketingList0" value="0"	type="radio" ${detailed.extensionSets.yaoyiyao.isAddMarketingList==0?'checked':''}>&nbsp;&nbsp;
											<span style="font-size: 12px;">否</span></label>
										</h5>
									</td>
								</tr>
								<tr>
									<td><h5>订单推广：</h5>
									<td>
									<td><h5>推广时间：</h5>
									<td>
									<td colspan="1">
									<input type="hidden" id="extensionType1" name="extensionSet.extensionType1"	value="2" /> 
									<input type="hidden" id="id1"	name="extensionSet.id1"	value="${detailed.extensionSets.orderPromotion.id}" /> 
									<input type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSet.dateStart1" placeholder="开始日期"	style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.orderPromotion.dateStart}" pattern="yyyy-MM-dd"/>">
									<label style="float: left;">&nbsp;--&nbsp;</label> 
									<input	type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"
										name="extensionSet.dateEnd1" placeholder="结束日期"
										style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.orderPromotion.dateEnd}" pattern="yyyy-MM-dd"/>">
									<td>
									<td><h5>排序：</h5>
									<td>
									<td class="sellerContentCss"><input disabled="disabled" type="text" class="form-control" placeholder="排序级别(数字越大越靠前)" name="extensionSet.sort1" value="${detailed.extensionSets.orderPromotion.sort}"></td>
									<td><h5>添加到营销列表：</h5>
									<td>
									<td style="text-align: left;">
										<h5>
											<label><input disabled="disabled"
												name="extensionSet.isAddMarketingList1" value="1"
												type="radio"
												${detailed.extensionSets.orderPromotion.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;<span
												style="font-size: 12px;">是</span></label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label><input disabled="disabled"
												name="extensionSet.isAddMarketingList1" value="0"
												type="radio"
												${detailed.extensionSets.orderPromotion.isAddMarketingList==0?'checked':''}>&nbsp;&nbsp;<span
												style="font-size: 12px;">否</span></label>
										</h5>
									</td>
								</tr>
								<tr>
									<td><h5>列表推广：</h5>
									<td>
									<td><h5>推广时间：</h5>
									<td>
									<td colspan="1"><input type="hidden" id="extensionType2" name="extensionSet.extensionType2"	value="3" /> 
									<input type="hidden" id="id2"	name="extensionSet.id2"	value="${detailed.extensionSets.listPromotion.id}" /> 
									<input	type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSet.dateStart2" placeholder="开始日期"	style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.listPromotion.dateStart}" pattern="yyyy-MM-dd"/>">
									<label style="float: left;">&nbsp;--&nbsp;</label> 
									<input	type="text" disabled="disabled" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSet.dateEnd2" placeholder="结束日期"	style="width:46%;float:left"
										value="<fmt:formatDate value="${detailed.extensionSets.listPromotion.dateEnd}" pattern="yyyy-MM-dd"/>">
									<td>
									<td><h5>排序：</h5>
									<td>
									<td class="sellerContentCss"><input disabled="disabled" type="text"	class="form-control" placeholder="排序级别(数字越大越靠前)" name="extensionSet.sort2"	value="${detailed.extensionSets.listPromotion.sort}"></td>
									<td><h5>添加到营销列表：</h5>
									<td>
									<td style="text-align: left;">
										<h5>
											<label><input	disabled="disabled" name="extensionSet.isAddMarketingList2" value="1"	type="radio"	${detailed.extensionSets.listPromotion.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;
											<span	style="font-size: 12px;">是</span></label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label>
											<input	disabled="disabled" name="extensionSet.isAddMarketingList2" value="0"	type="radio" ${detailed.extensionSets.listPromotion.isAddMarketingList==0?'checked':''}>&nbsp;&nbsp;
											<span	style="font-size: 12px;">否</span></label>
										</h5>
									</td>
								</tr>
							</table>
						</div>
						</td>
						</tr>
						<tr>
						<td colspan="12" align="left"><hr></td>
						</tr>
						   <tr>
							<td class = "sellerTitleCss" >
									<h5>商家提示:</h5> 
									<h5><font color="red">（商家提示介绍最多300字）</font></h5> 
							</td>
							<td colspan = "9">
							        <textarea name="sellerDetailed.tips" rows="10" disabled="disabled" class="form-control">${detailed.sellerDetailed.tips}</textarea>
							</td>
						</tr>
												 <tr>
							<td class = "sellerTitleCss" >
									<h5>商家优惠:</h5> 
									<h5><font color="red">（商家优惠介绍最多300字）</font></h5> 
							</td>
							<td colspan = "9">
							        <textarea name="sellerDetailed.rule" rows="10" disabled="disabled" class="form-control">${detailed.sellerDetailed.rule}</textarea>
							</td>
						</tr>
						<tr>
						<td colspan="3" align="left"><span style="position:relative;top:49px;"><h5>商家等级信息：</h5></span></td>
						<td colspan="6" align="right">
                        </td>
                       
                        </tr>
                        <tr height="1px"><td colspan="12"><hr></td></tr>
						<tr>
						<td colspan="12">
						    <table>
						       <tr>
									<td class="sellerTitleCss">
									<h5>人均消费<font color="red">(元)</font>:</h5> 
							        </td>
							        <td class="sellerContentCss">
								   <input type="text" class="form-control"  disabled="disabled" placeholder="人均消费" name="sellerDetailed.consume" 
								    value="<fmt:formatNumber value='${detailed.sellerDetailed.consume}' pattern='00.00'/>"> 
							       </td>
							        <td class = "sellerTitleCss">
										<h5>是否连锁:</h5> 
								   </td>
								   <td class="sellerContentCss">
									        <select class="form-control" id="isChain" disabled="disabled" name="sellerDetailed.isChain" value="${detailed.sellerDetailed.isChain}">
									           <option value="">请选择</option>
					                           <option value="1" <c:if test="${detailed.sellerDetailed.isChain==1}">selected</c:if>>是</option>
					                           <option value="0" <c:if test="${detailed.sellerDetailed.isChain==0}">selected</c:if>>否</option>
				                            </select>
									</td>
									<td class="sellerTitleCss">
										<h5>商家等级:</h5>
									</td>
									<td class="sellerContentCss">
									<select class="form-control"  name ="sellerDetailed.sellerGrade" id="sellerGrade" disabled="disabled" value="${detailed.sellerDetailed.sellerGrade}">
						                <option value = "1" <c:if test="${detailed.sellerDetailed.sellerGrade==1}">selected</c:if>>A&nbsp;级</option> 
						                <option value = "3" <c:if test="${detailed.sellerDetailed.sellerGrade==3}">selected</c:if>>B&nbsp;级</option>
						                <option value = "5" <c:if test="${detailed.sellerDetailed.sellerGrade==5}">selected</c:if>>C&nbsp;级</option>
						             </select>
									</td>
						       </tr>
						       <tr>
						       		<td class = "sellerTitleCss">
						       			<h5>级别调整原因:</h5>
						       		</td>
						       		<td class="sellerContentCss" colspan="5">
						       			<textarea  name="sellerDetailed.adjustReason" disabled="disabled" class="form-control" >${detailed.sellerDetailed.adjustReason}</textarea>
						       		</td>
						       </tr>
						   </table> 
						</td>
						</tr>
						</table>
						</td>
						</tr>
						</table>
					</form>
				</div>
			</div>
           <c:if test="${empty param.viewType}">
				<div align="center" id="sellerButton" style="display: none;">
					 <input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
					 <input type="hidden" name="updateStatusToken" id="updateStatusToken" value="${updateStatusToken}">
					 <c:if test="${btnAu['businessman/seller/updateSellerStatus']}">
					 	<button class="btn btn-success" type="button" id="auditYes" style="display: none;"><i class="icon-ok"></i>&nbsp;审核通过</button>
					 </c:if>
					 <c:if test="${btnAu['businessman/seller/updateSellerStatus']}">
					 	&nbsp;
					 	<button class="btn btn-success" type="button" id="recover" style="display: none;"><i class="icon-ok"></i>&nbsp;恢复合作</button>	
					 </c:if>
					 <c:if test="${btnAu['businessman/seller/updateSellerStatus']}">
						 &nbsp;
						 <button class="btn btn-danger" type="button" id="auditNo" style="display: none;"><i class="icon-remove-sign"></i>&nbsp;审核不通过</button>	
					 </c:if>
					 <c:if test="${btnAu['businessman/seller/updateSellerStatus']}">
						 &nbsp;	 
					 	 <button class="btn btn-danger" type="button" id="stop" style="display: none;"><i class="icon-remove-sign"></i>&nbsp;暂停合作</button>
					 </c:if>
					 <c:if test="${btnAu['businessman/seller/updateSellerStatus']}">
						 &nbsp;	 
					 	 <button class="btn btn-danger" type="button" id="revoke" style="display: none;"><i class="icon-remove-sign"></i>&nbsp;注销下架</button>
					 </c:if>	
				</div>
				</c:if>
				 <c:if test="${!empty param.viewType}">
				<div align="center" id="sellerPendingButton" style="display: none;">
					 <input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
					 <input type="hidden" name="updateStatusToken" id="updateStatusToken" value="${updateStatusToken}">
					 <c:if test="${btnAu['businessman/sellerPending/updateSellerStatus']}">
					 	<button class="btn btn-success" type="button" id="auditYes"><i class="icon-ok"></i>&nbsp;审核通过</button>
					 </c:if>
					 <c:if test="${btnAu['businessman/sellerPending/updateSellerStatus']}">
						 &nbsp;
						 <button class="btn btn-danger" type="button" id="auditNo"><i class="icon-remove-sign"></i>&nbsp;审核不通过</button>	
					 </c:if>
				 </div>
			</c:if>
		</div>
	</div>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
    <script src="<%=path%>/resources/upload/upload.js"></script>
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
 
 <!--图片弹出  -->
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.js?v=2.1.5"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712//source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/lib/jquery.mousewheel-3.0.6.pack.js"></script> 
		
	<!-- 图片缩放 -->   
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/shiftzoom.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/geodata.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/cvi_tip_lib.js"></script>
	<%-- <script type="text/javascript">shiftzoom.defaultCurpath='<%=path%>/ux/shiftzoom/images/cursors/';</script>  --%>

    <script type="text/javascript">
 	
   $(function(){
	/* 	$(".image_gall").popImage({"tagName":"src"}); */
	   	$('.fancybox').fancybox();
	
	//图片为空时隐藏
	 	var licenseurls='${selleridList.licenseurl}';//正面执照电子版URL
		if( licenseurls==""){
			$("#yyzzzm").hide();				
		}else{
			$("#yyzzzm").show();		
		} 
		var licensefurls='${selleridList.licensefurl}';// 营业执照电子版反面URL
		if( licensefurls==""){
			$("#yyzzfm").hide();				
		}else{
			$("#yyzzfm").show();		
		} 
		
		var identityzurls='${selleridList.identityzurl}';// 身份证附件正面
		if( identityzurls==""){
			$("#sfzzm").hide();				
		}else{
			$("#sfzzm").show();		
		} 
		
		var identityfurls='${selleridList.identityfurl}';// 身份证附件反面
		if( identityfurls==""){
			$("#sfzfm").hide();				
		}else{
			$("#sfzfm").show();		
		} 
		
		var identitynurl='${selleridList.identitynurl}';// 手持身份证图
		if( identitynurl==""){
			$("#scsfzzmz").hide();				
		}else{
			$("#scsfzzmz").show();		
		} 
		
		var sellerLogoId='${selleridList.url}';//正面执照电子版URL
		if( sellerLogoId==""){
			$("#logo").hide();				
		}else{
			$("#logo").show();		
		} 
		
		var agreementurls='${selleridList.agreement}';//商家合同(一) URL
		if( agreementurls==""){
			$("#ht1").hide();				
		}else{
			$("#ht1").show();		
		} 
		var agreement2urls='${selleridList.agreement2}';//商家合同(二) URL
		if( agreement2urls==""){
			$("#ht2").hide();				
		}else{
			$("#ht2").show();		
		} 
		var agreement3urls='${selleridList.agreement3}';//商家合同(三) URL
		if( agreement3urls==""){
			$("#ht3").hide();				
		}else{
			$("#ht3").show();		
		} 
		var agreement4urls='${selleridList.agreement4}';//商家合同(四) URL
		if( agreement4urls==""){
			$("#ht4").hide();				
		}else{
			$("#ht4").show();		
		} 
	});    
  </script>
  <script src="<%=path%>/js/businessman/viewSeller.js"></script> 
</body>
</html>
