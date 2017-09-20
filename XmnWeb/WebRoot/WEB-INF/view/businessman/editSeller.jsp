<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>编辑商家信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<!-- viewType：editSellerPending 商家待审核查看-->
	<input type="hidden" id="viewType" value="${param.viewType}">
	<div id="main" style="min-height: 903px;">
		<!-- 基本信息 -->
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="sellerForm" role="form">
						<input type="hidden" name="sellerToken" value="${sellerToken}">
						<input type="hidden" id="selleridid" name="sellerid"
							value="${selleridList.sellerid}" />
						<input type="hidden" id="isType" value="${isType}">
						<table class="table" style="text-align: center;" width="100%">
							<tr>
								<td>
									<table class="table" style="text-align: center;">
										<tr>
											<td class="sellerTitleCss">
												<h5>商家名称:</h5>
											</td>
											<td class="sellerContentCss"><input type="text"
												class="form-control" name="sellername" placeholder="商家名称"
												value="${selleridList.sellername}"></td>
											<td>
												<h5>商家地址:</h5>
											</td>
											<td colspan="4"><input type="text" class="form-control"
												name="address" placeholder="地址"
												value="${selleridList.address}"></td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>所在区域:</h5>
											</td>
											<td class="sellerContentCss">
												<div class="input-group" style="width:100%;" id="areaSelect"
													initValue="${selleridList.area}"></div>
											</td>
											<td class="sellerTitleCss">
												<h5>所属商圈:</h5>
											</td>
											<td class="sellerContentCss"><select
												class="form-control" id="zoneid" name="zoneid"
												initValue="${selleridList.zoneid}">
													<option value="">请先选择区域再选择商圈</option>
											</select></td>

											<td class="sellerTitleCss"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属合作商：</h5></td>
											<td class="sellerContentCss" style="text-align: left"><select
												class="form-control" id="jointid" name="jointid"
												style="width:100%;" initValue="${selleridList.jointid}">
													<option value="">请先选择区域</option>
											</select></td>
										</tr>
										<tr>
											<%-- <td><h5>所属业务员:</h5></td>
											<td style="text-align: left"><select
												class="form-control" id="staffid" name="staffid"
												style="width:100%;" initValue="${selleridList.staffid}">
													<option value="">请先选择合作商</option>
											</select></td> --%>
											<td class="sellerTitleCss">
												<h5>所属寻蜜客：</h5>
											</td>
										 	<td class="xmerContentCss">
												<label id="checkids"></label>
										 		<select class="form-control" id="uid" name="uid" initValue="${selleridList.uid}" style="width:100%;" disabled="disabled"></select>
											</td>
											<td>
												<h5>&nbsp;&nbsp;&nbsp;联盟店:</h5>
											</td>
											<td style="text-align: left"><select
												class="form-control" id="allianceId" name="allianceId"
												styl="width:100%;" initValue="${selleridList.allianceId}">
											</select></td>
											<td>
												<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;连锁店：</h5>
											</td>
											<td style="text-align: left"><select
												class="form-control" id="fatherid" name="fatherid"
												style="width:96.5%;" initValue="${selleridList.fatherid==0?null:selleridList.fatherid}">
											</select> <input type="hidden" class="form-control"
												name="lssellername" value="${selleridList.lssellername}">
											</td>
										</tr>

										<tr>
											<td class="sellerTitleCss"><h5>经营行业：</h5></td>
											<td class="sellerContentCss"><input type="hidden"
												name="typename" value="${selleridList.typename}" /> <input
												type="hidden" name="tradename"
												value="${selleridList.tradename}" />
												
												<div class="input-group" id="tradeSelect"
													style="width : 100%" initValue="${selleridList.genre}">
												</div>
												<%-- 
												<div class="form-group" id="includeTrade">
													<label class="col-md-2 control-label"></label>
													<div class="col-md-5" style="width : 100%;">
														<c:if test="${!empty selleridList.traderRefs}">
															<c:forEach items="${selleridList.traderRefs}" var="trade">
																<div class="input-group">
																	<div class="includeTradeSelect" style="width : 100%;" initvalue="${trade.genre}"></div><div id="checkMsg"></div>
																	<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> 
																	<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
																</div>
															</c:forEach>
														</c:if>
														<c:if test="${empty selleridList.traderRefs}">
															<div class="input-group">
																<div class="includeTradeSelect" style="width : 100%"></div><div id="checkMsg"></div>
																<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> 
																<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
															</div>
														</c:if>
													</div>
												</div>	
												<div class="includeTradeSelectTemp hidden ">
													<div class="input-group">
														<div class="includeTradeSelect" style="width : 100%"></div>
														<span class="input-group-addon"><i class="icon icon-plus"
															style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> <span
															class="input-group-addon"><i class="icon icon-minus"
															style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
													</div>
												</div>
											--%>												
											</td>

											<td>
												<h5>营业执照:</h5>
											</td>
											<td><input type="text" class="form-control"
												name="licenseid" placeholder="营业执照"
												value="${selleridList.licenseid}"></td>
											<td nowrap="nowrap"><h5>美食星势力商户：</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="isforce" value="1" type="radio"
														${selleridList.isforce==1?'checked':''}><span
														style="font-size: 12px;">是</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="isforce" value="0" type="radio"
														${selleridList.isforce==0?'checked':''}><span
														style="font-size: 12px;">否</span>
												</h5>
											</td>
										</tr>

										<tr>
											<td>
												<h5>联系电话:</h5>
											</td>
											<td><input type="text" class="form-control" name="tel"
												placeholder="联系电话" value="${selleridList.tel}"></td>
												
											<td>
												<h5>联系人手机:</h5>
											</td>
											<td><input type="hidden" class="form-control"
												name="oldPhoneid" placeholder="联系人手机"
												value="${selleridList.phoneid}"> <input type="text"
												class="form-control" name="phoneid" placeholder="联系人手机"
												value="${selleridList.phoneid}"></td>

											<td style="width:100px;">
												<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法人姓名：</h5>
											</td>
											<td colspan="2"><input type="text" class="form-control"
												name="fullname" placeholder="法人姓名"
												value="${selleridList.fullname}"></td>
										</tr>

										<tr>
											<td><h5>营业时间：</h5></td>
											<td><input type="text" class="form-control form-time"
												readonly="readonly" name="sdate1"
												style="width:45%;float:left;" value="${selleridList.sdate1}" />
												<span style="float:left;width:10%;">&nbsp;~&nbsp;</span> <input
												type="text" class="form-control form-time"
												readonly="readonly" name="sdate2"
												style="width:45%;float:left;" value="${selleridList.sdate2}" />
											</td>
											<td>
												<h5>有效日期开始:</h5>
											</td>
											<td><input type="text" readonly="readonly"
												class="form-control form-datetime"
												title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd"/>"
												name="svalidity" id="sjaddsvalidity" placeholder="开始日期"
												value="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>">
											</td>

											<td>
												<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有效日期结束：</h5>
											</td>
											<td><input type="text"  readonly="readonly"
												class="form-control form-datetime" name="evalidity"
												id="sjaddevalidity" placeholder="结束日期"
												value="<fmt:formatDate value="${selleridList.evalidity}" pattern="yyyy-MM-dd"/>">
											</td>
										</tr>

										<tr>
											<td><h5>是否折上折:</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="agioAgio" value="1" type="radio"
														${selleridList.agioAgio==1?'checked':''}><span
														style="font-size: 12px;">是</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="agioAgio" value="0" type="radio"
														${selleridList.agioAgio==0?'checked':''}><span
														style="font-size: 12px;">否</span>
												</h5>
												<div id="agioInstructionDiv">
												<c:if test="${selleridList.agioAgio==1}">
													<input placeholder="全单折扣" class="form-control" name="agioAgioNum" value="${selleridList.agioAgioNum}">
													<textarea placeholder="折上折说明……" class="form-control" name="agioInstruction">${selleridList.agioInstruction}</textarea>
												</c:if>
												</div>
											</td>

											<td nowrap="nowrap"><h5>总部帮忙签约：</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="give" value="1" type="radio"
														${selleridList.give==1?'checked':''}><span
														style="font-size: 12px;">是</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="give" value="0" type="radio"
														${selleridList.give==0?'checked':''}><span
														style="font-size: 12px;">否</span>
												</h5>
											</td>

											<td nowrap="nowrap"><h5>是否扣取支付手续费:</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="isfees" value="0" type="radio"
														${selleridList.isfees==0?'checked':''}><span
														style="font-size: 12px;">是</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="isfees" value="1" type="radio"
														${selleridList.isfees==1?'checked':''}><span
														style="font-size: 12px;">否</span>
												</h5>
											</td>
										</tr>
										<tr>
<%--										
											<td><h5>订单基数：</h5></td>
											<td>
												<input type="text" class="form-control" name="order"
												placeholder="订单基数" value="${selleridList.order}">
											</td>
			
											<td><h5>节省钱基数：</h5></td>
											<td>
												<input type="text" class="form-control" name="saveMoney"
												placeholder="节省钱基数" value="${selleridList.saveMoney}">
											</td>
 --%>			
	 										<td><h5>商家标签:</h5></td>
											<td>
												<select class="form-control" name="label">
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
											<td nowrap="nowrap"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否为KA商户：</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="isKa" value="1" type="radio" ${selleridList.isKa==1?'checked':'' }><span
														style="font-size: 12px;">是</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="isKa" value="0" type="radio" ${selleridList.isKa==0?'checked':'' }><span
														style="font-size: 12px;">否</span>
												</h5>
											</td>
									</tr>
									<tr>
										<td><h5>每日商家提现限额:</h5></td>
										<td>
										<input  type="text" name = "dailyLimitWithdraw" class="form-control"  value = "${selleridList.dailyLimitWithdraw}"> 
										</td>
										<td><h5>SAAS交易总额:</h5></td>
										<td>
										<input  type="text" name = "totalLimitTurnover" class="form-control"  value = "${selleridList.totalLimitTurnover}"> 
										</td>
										<td><h5>每日交易限额:</h5></td>
										<td>
										<input  type="text" name = "dailyLimitTurnover" class="form-control"  value = "${selleridList.dailyLimitTurnover}"> 
										</td>
									</tr>
									<tr>
										<td><h5>是否公开商户:</h5></td>
										<td style="text-align: left;">
										<input  type="radio" name = "isPublic" value = "1" ${selleridList.isPublic==1?'checked':''}><span style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<input  type="radio" name = "isPublic" value = "0" ${selleridList.isPublic==0?'checked':''}><span style="font-size: 12px;">否</span> 
										</td>
										<td><h5>是否参与分红:</h5></td>
										<td style="text-align: left;">
										<input  type="radio" name = "joinDividend" value = "1" ${selleridList.joinDividend==1?'checked':''}><span style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<input  type="radio" name = "joinDividend" value = "0" ${selleridList.joinDividend==0?'checked':''}><span style="font-size: 12px;">否</span> 
										</td>
										<td><h5>是否设为付费商家:</h5></td>
										<td style="text-align: left;">
										<input  type="radio" name = "isPaid" value = "1" ${selleridList.isPaid==1?'checked':''}><span style="font-size: 12px;">是</span> 
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input  type="radio" name = "isPaid" value = "0" ${selleridList.isPaid==0?'checked':''}><span style="font-size: 12px;">否</span> 
										</td>
									</tr>

										<tr>
											<td><h5>营业执照名称：</h5></td>
											<td><input type="text" class="form-control" name="businessLicenseName"
													   placeholder="营业执照名称" value="${selleridList.businessLicenseName}"></td>
											<td>
										</tr>
									</table> <!-- 折扣信息=================================================================================================== -->
									<hr>
									<table class="table" style="text-align: center;width: 100%;">
										<tr>
											<td><h5>分账模式:</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="ledgerMode" value="0" type="radio" ${selleridList.ledgerMode==0?'checked':'' }><span
														style="font-size: 12px;">正常分账模式</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="ledgerMode" value="2" type="radio" ${selleridList.ledgerMode==2?'checked':'' }><span
														style="font-size: 12px;">仅商家参与分账模式</span>
												</h5>
											</td>
											<td class="sellerTitleCss">
												<h5>折扣（%）:</h5>
											</td>
											<td class="sellerContentCss"><input type="text"
												class="form-control" placeholder="请输入0-98之间的数"
												name="baseagio" value="${sellerAgio.baseagio}"></td>
											<td class="sellerTitleCss">
												<%--<h5>用户占比（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" readonly="readonly" name="yledger"--%>
												<%--value="${sellerAgio.yledger}"></td>--%>
										</tr>
										<%--<tr>--%>
											<%--<td class="sellerTitleCss">--%>
												<%--<h5>营业收入（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" readonly="readonly" name="income"--%>
												<%--value="${sellerAgio.income}"></td>--%>

											<%--<td class="sellerTitleCss">--%>
												<%--<h5>商户占比（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" readonly="readonly" name="sledger"--%>
												<%--value="${sellerAgio.sledger}"></td>--%>
											<%--<td class="sellerTitleCss">--%>
												<%--<h5>平台占比（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" readonly="readonly" name="pledger"--%>
												<%--value="${sellerAgio.pledger}"></td>--%>
										<%--</tr>--%>
										<%--<tr>--%>
											<%--<td class="sellerTitleCss">--%>
												<%--<h5>佣金补贴比例（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" name="ratio"--%>
												<%--value="${selleridList.ratioStr}" readonly="readonly" >--%>
											<%--</td>--%>
											<%--<td class="sellerTitleCss">--%>
												<%--<h5>平台补贴（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" name="flatAgio"--%>
												<%--value="${sellerAgio.flatAgio}" readonly="readonly" >--%>
											<%--</td>--%>
											<%--<td class="sellerTitleCss">--%>
												<%--<h5>平台扣款比例（%）:</h5>--%>
											<%--</td>--%>
											<%--<td class="sellerContentCss"><input type="text"--%>
												<%--class="form-control" &lt;%&ndash; <c:if test="${sellerAgio.baseagio != 100}">readonly="readonly"</c:if>  &ndash;%&gt;name="debit"--%>
												<%--value="${selleridList.debit}" placeholder="不输入，默认为0"></td>--%>
										<%--</tr>--%>
										<!-- ===================================================================================================== -->
									</table>
									<hr>
									<!-- 直播分账设置=================================================================================================== -->
									<table class="table" style="text-align: center;" width="100%;">
										<tr>
										<input type="hidden" name="liveLedger.id" value="${selleridList.liveLedger.id}">
											<td class="sellerTitleCss"><h5>直播分账开关:</h5></td>
											<td style="text-align: left;">
												<h5>
													<input name="liveLedgerOperating" value="1" type="radio" ${selleridList.liveLedgerOperating==1?'checked':'' }><span
														style="font-size: 12px;">开启</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="liveLedgerOperating" value="0" type="radio" ${selleridList.liveLedgerOperating==0?'checked':'' }><span
														style="font-size: 12px;">不开启</span>
												</h5>
											</td>
											<td class="sellerTitleCss"></td>
											<td style="text-align: left;">
											</td>
										</tr>
									</table>
									<table class="table liveLedgerTable" style="text-align: center;" width="100%;" hidden>
										<tr>
											<td class="width: 10%"><h5>直播分账操作:</h5></td>
											<td style="text-align: left;" class="width: 25%">
												<h5>
													<input name="liveLedger.ledgerStyle" value="0" type="radio" ${selleridList.liveLedger.ledgerStyle!=1?'checked':''}><span
														style="font-size: 12px;">自动分账</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="liveLedger.ledgerStyle" value="1" type="radio" ${selleridList.liveLedger.ledgerStyle==1?'checked':''}><span
														style="font-size: 12px;">手动分账</span>
												</h5>
											</td>
											<td class=class="width: 10%"><h5>直播分账模式:</h5></td>
											<td style="text-align: left;" class="width: 25%">
												<h5>
													<input name="liveLedger.ledgerMode" value="1" type="radio" ${selleridList.liveLedger.ledgerMode!=2?'checked':''}><span
														style="font-size: 12px;">全部分账模式</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="liveLedger.ledgerMode" value="2" type="radio" ${selleridList.liveLedger.ledgerMode==2?'checked':''}><span
														style="font-size: 12px;">仅针对粉丝券分账</span>
												</h5>
											</td>
										</tr>
										<tr>
											<td class=class="width: 10%">
												<h5>分账比例（%）:</h5>
											</td>
											<td class=class="width: 25%"><input type="text"
												class="form-control" placeholder="请输入0-100之间的数" name="liveLedger.ledgerRatio"
												value="${selleridList.liveLedger.ledgerRatio}"></td>
												
											<td class=class="width: 10%">
												<h5>有效时间:</h5>
											</td>
											<td class=class="width: 25%">
												<input type="text" class="form-control form-datetime"
													name="liveLedger.startDate" readonly style="width:45%;float:left;"
													value="<fmt:formatDate value="${selleridList.liveLedger.startDate}" pattern="yyyy-MM-dd HH:mm:SS"/>" /> 
												<span style="float:left;width:10%;">&nbsp;~&nbsp;</span> 
												<input
													type="text" class="form-control form-datetime" name="liveLedger.endDate"
													readonly style="width:45%;float:left;"
													value="<fmt:formatDate value="${selleridList.liveLedger.endDate}" pattern="yyyy-MM-dd HH:mm:SS"/>" />
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
													<input name="liveCoinPay" value="1" type="radio" ${selleridList.liveCoinPay==1?'checked':''}><span
														style="font-size: 12px;">开启</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="liveCoinPay" value="0" type="radio" ${selleridList.liveCoinPay==0?'checked':''}><span
														style="font-size: 12px;">关闭</span>
												</h5>
											</td>
											<td><h5>(开启鸟币支付后，仅消费商家可获得营收分账，其他角色将不参加分账)</h5></td>
										</tr>
									</table>
									<hr>
									<table class="table" style="text-align: center;">
										<tr>
											<td class="sellerTitleCss">
												<h5>备注:</h5>
											</td>
											<td colspan="10"><textarea name="remarks" rows="3"
													class="form-control">${selleridList.remarks}</textarea></td>
										</tr>
									</table>
									<hr>
									<table class="table" style="text-align: center;">
										<tr>
											<td>
												<h5>商家LOGO:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='sellerLogoId' ImgValidate="true"></div>
													<input type="hidden" id="url" name="url"
														value="${selleridList.url}" />
												</div>
											</td>

											<td>
												<h5>营业执照正本附件:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='sellerHead2'></div>
													<input type="hidden" id="licenseurlid" name="licenseurl"
														value="${selleridList.licenseurl}" />
												</div>
											</td>

											<td>
												<h5>营业执照副本附件:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='sellerHead3'></div>
													<input type="hidden" id="licensefurlid" name="licensefurl"
														value="${selleridList.licensefurl}" />
												</div>
											</td>

											<td>
												<h5>卫生许可证:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='sellerHead4'></div>
													<input type="hidden" id="hygienicLicenseImg" name="hygienicLicenseImg"
														   value="${selleridList.hygienicLicenseImg}" />
												</div>
											</td>

										</tr>

										<tr>
											<td>
												<h5>商家图片:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='sellerPicId' ImgValidate="true"></div>
													<input type="hidden" id="picUrl" name="picUrl"
														value="${selleridList.picUrl}" />
												</div>
											</td>

											<td>
												<h5>身份证附件正面:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='identityzurldivid'></div>
													<input type="hidden" id="identityzurleditid"
														name="identityzurl" value="${selleridList.identityzurl}" />
												</div>
											</td>

											<td>
												<h5>身份证附件反面:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='licensefurldivid'></div>
													<input type="hidden" id="licensefurleditid"
														name="identityfurl" value="${selleridList.identityfurl}" />
												</div>
											</td>
											
											<td>
												<h5>手持身份证正面照:</h5>
											</td>
											<td>
												<div class="col-md-9">
													<div id='identitynurldivid'></div>
													<input type="hidden" id="identitynurleditid"
														name="identitynurl" value="${selleridList.identitynurl}" />
												</div>
											</td>
										</tr>

										<tr>
											<td>
												<h5>商户合同:</h5>
											</td>
											<td colspan="3">
												<div class="col-md-3">
													<div id='agreementid'></div>
													<input type="hidden" id="agreement" name="agreement"
														value="${selleridList.agreement}"/>
												</div>
												<div class="col-md-3">
													<div id='agreement2id'></div>
													<input type="hidden" id="agreement2" name="agreement2"
														value="${selleridList.agreement2}" />
												</div>
												<div class="col-md-3">
													<div id='agreement3id'></div>
													<input type="hidden" id="agreement3" name="agreement3"
														value="${selleridList.agreement3}" />
												</div>
												<div class="col-md-3">
													<div id='agreement4id'></div>
													<input type="hidden" id="agreement4" name="agreement4"
														value="${selleridList.agreement4}" />
												</div>
											</td>
										</tr>
									</table>
								</td>
								<td rowspan="4"
									style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br>
									<button class="btn btn-danger" type="submit"
										id="UpdateSavaSeller">
										<i class="icon-save"></i>&nbsp;保存
									</button>&nbsp; <!-- <button class="btn btn-warning" type="reset" id="backSellerId"><i class="icon-reply"></i>&nbsp;返回</button> -->
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
												name="longitude" class="form-control"
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
												name="latitude" class="form-control"
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
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">详细信息</div>
				<div class="panel-body">
					<form id="sellerDetailedForm">
						<input type="hidden" name="sellerDetailedToken"
							value="${sellerDetailedToken}">
						<table class="table" style="text-align: center;">
							<tr>
								<td>
									<table class="table" style="text-align: center;">
										<tr>
											<td class="sellerTitleCss">
												<h5>WIFI:</h5>
											</td>
											<td class="width: 18%"><select
												class="form-control" name="sellerDetailed.iswifi"
												value="${detailed.sellerDetailed.iswifi}">
													<option value="">请选择</option>
													<option value="0"
														<c:if test="${detailed.sellerDetailed.iswifi==0}">selected</c:if>>没有</option>
													<option value="1"
														<c:if test="${detailed.sellerDetailed.iswifi==1}">selected</c:if>>免费提供</option>
													<option value="2"
														<c:if test="${detailed.sellerDetailed.iswifi==2}">selected</c:if>>有偿提供</option>
											</select></td>
											<td class="sellerTitleCss">
												<h5>停车位:</h5>
											</td>
											<td class="width: 18%"><select
												class="form-control" name="sellerDetailed.isparking"
												value="${detailed.sellerDetailed.isparking}">
													<option value="">请选择</option>
													<option value="0"
														<c:if test="${detailed.sellerDetailed.isparking==0}">selected</c:if>>没有</option>
													<option value="1"
														<c:if test="${detailed.sellerDetailed.isparking==1}">selected</c:if>>免费提供</option>
													<option value="2"
														<c:if test="${detailed.sellerDetailed.isparking==2}">selected</c:if>>有偿提供</option>
											</select></td>
                                          <%--  <td class="sellerTitleCss">
												<h5>是否开启首次:</h5>
											</td>
											<td class="sellerContentCss"><select
												class="form-control" name="sellerDetailed.isFirst"
												value="${detailed.sellerDetailed.isFirst}">
													<option value="0"
														<c:if test="${detailed.sellerDetailed.isFirst==0}">selected</c:if>>否</option>
													<option value="1"
														<c:if test="${detailed.sellerDetailed.isFirst==1}">selected</c:if>>是</option>
													
											</select></td>
											<td class="sellerTitleCss">
												<h5>是否开启打赏功能:</h5>
											</td>
											<td class="sellerContentCss"><select
												class="form-control" name="sellerDetailed.isPay"
												value="${detailed.sellerDetailed.isPay}">
													<option value="1"
														<c:if test="${detailed.sellerDetailed.isPay==1}">selected</c:if>>是</option>
													<option value="0"
														<c:if test="${detailed.sellerDetailed.isPay==0}">selected</c:if>>否</option>
											</select></td>
											 --%>
											 <td class="sellerTitleCss">
												<h5>单独包间:</h5>
											</td>
											<td class="width: 18%"><select
												class="form-control" name="sellerDetailed.isroom"
												value="${detailed.sellerDetailed.isroom}">
													<option value="">请选择</option>
													<option value="1"
														<c:if test="${detailed.sellerDetailed.isroom==1}">selected</c:if>>有</option>
													<option value="2"
														<c:if test="${detailed.sellerDetailed.isroom==2}">selected</c:if>>没有</option>
											</select></td>
											 <td class="sellerTitleCss">
												<h5>参考地标:</h5>
											</td>
											<td><input type="text" class="form-control"
												placeholder="参考地标" name="sellerDetailed.landmark"
												value="${detailed.sellerDetailed.landmark}"></td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>商家wifi名:</h5>
											</td>
											<td class="width: 18%"><input type="hidden"
												id="selleridid" name="sellerDetailed.sellerid"
												value="${selleridList.sellerid}" /> <input type="text"
												class="form-control" placeholder="wifi名"
												name="sellerDetailed.WIFIName"
												value="${detailed.sellerDetailed.WIFIName}"></td>
											<td class="sellerTitleCss">
												<h5>wifi密码:</h5>
											</td>
											<td class="width: 18%" id="wifiCTd"><input type="text"
												class="form-control" placeholder="wifi密码"
												name="sellerDetailed.WIFIPassword"
												value="${detailed.sellerDetailed.WIFIPassword}"></td>
											<td class="sellerTitleCss">
												<h5>单次提现限额:</h5>
											</td>
											<td><input type="text" class="form-control"
												name="sellerDetailed.amountLimit" placeholder="50000.0"
												value="${detailed.sellerDetailed.amountLimit}"></td>
										</tr>
<%--										<tr>
										<c:if test="${selleridList.category == 1}">
											<td class="sellerTitleCss" id="isOpenBookTTd">
												<h5>是否开通点菜:</h5>
											</td>
											<td class="sellerContentCss" id="isOpenBookCTd"><select
												class="form-control" name="sellerDetailed.isOpenBooking"
												value="${detailed.sellerDetailed.isOpenBooking}">
													<option value="">请选择</option>
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
											<td class="sellerTitleCss">
												<h5>商家推荐:</h5>
												<h5>
													<font color="red">（限定10个,多个用";"号隔开）</font>
												</h5>

											</td>
											<td colspan="9"><textarea name="sellerDetailed.dishes"
													rows="3" class="form-control" placeholder="限定10个,多个用';'号隔开">${detailed.sellerDetailed.dishes}</textarea>
											</td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>商家环境图片:</h5> <!-- <h5><font color="red">（限定10个,多个用";"号隔开）</font></h5>  -->
											</td>
											<td colspan="9" align="left"><input type="hidden"	id="sellerPicsNum" value="${fn:length(detailed.sellerPics)}">
												<button class="btn btn-danger" type="button" id="addPic" style="float : left">
													<i class="icon-plus"></i></button> <br />
												<div id="sellerPicTemp"	style="display: none;float : left;text-align: center;">
													<button class="btn btn-warning removebtn" type="button"><i class="icon-remove"></i></button>
													<input name="picid" type="hidden" /> 
													<input name="picurl" type="hidden" /> 
													<input name="bewrite" type="text"	class="form-control" placeholder="图片描述" style="width:100px" />
													<div class="pic"></div>
												</div>
												<div id="sellerPics" style="float : left;">
													<c:forEach items="${detailed.sellerPics}"	varStatus="status" var="pic">
														<div style="float : left;text-align: center;">
															<button class="btn btn-warning removebtn" type="button">
																<i class="icon-remove"></i></button>
															<input name="sellerPics[${status.index }].picid" type="hidden" value="${pic.picid}" /> 
															<input	name="sellerPics[${status.index }].picurl" type="hidden" value="${pic.picurl}" id="sellerPicUrl${status.index}" />
															<input name="sellerPics[${status.index }].bewrite" type="text" value="${pic.bewrite}" class="form-control" placeholder="图片描述" style="width:100px" />
															<div class="pic"></div>
														</div>
													</c:forEach>
												</div></td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>商家介绍:</h5>

												<h5>
													<font color="red">（商家介绍最多300字）</font>
												</h5>
											</td>
											<td colspan="9"><textarea
													name="sellerDetailed.introduce" rows="10"
													class="form-control" placeholder="商家介绍最多300字">${detailed.sellerDetailed.introduce}</textarea>
											</td>
										</tr>

										<tr>
											<td colspan="10" align="left"><spn>
												<h5>推广设置信息</h5>
												</spn>
												<hr></td>
										</tr>

										<tr>
											<td colspan="10">
												<div>
													<table>
														<tr>
															<td><h5>摇一摇：</h5>
															<td>
															<td><h5>推广时间：</h5>
															<td>
															<td colspan="1"><input type="hidden" id="selleridid0" name="extensionSets['yaoyiyao'].sellerId"	value="${selleridList.sellerid}" /> 
															<input	type="hidden" id="extensionType0"	name="extensionSets['yaoyiyao'].extensionType" value="1" /> 
															<input	type="hidden" id="id0" name="extensionSets['yaoyiyao'].id"	value="${detailed.extensionSets.yaoyiyao.id}" /> 
															<input	type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSets['yaoyiyao'].dateStart" placeholder="开始日期"	style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.yaoyiyao.dateStart}" pattern="yyyy-MM-dd"/>">
															<label style="float: left;">&nbsp;--&nbsp;</label>
															<input	type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd" name="extensionSets['yaoyiyao'].dateEnd" placeholder="结束日期"	style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.yaoyiyao.dateEnd}" pattern="yyyy-MM-dd"/>">
															<td>
															<td><h5>排序：</h5>
															<td>
															<td class="sellerContentCss"><input type="text"	class="form-control" placeholder="排序级别(数字越大越靠前)"	name="extensionSets['yaoyiyao'].sort"	value="${detailed.extensionSets.yaoyiyao.sort}"></td>
															<td><h5>添加到营销列表：</h5>
															<td>
															<td style="text-align: left;">
																<h5>
																	<label><input	name="extensionSets['yaoyiyao'].isAddMarketingList" value="1" type="radio" ${detailed.extensionSets.yaoyiyao.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;
																	<span	style="font-size: 12px;">是</span></label>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<label>
																	<input	name="extensionSets['yaoyiyao'].isAddMarketingList" value="0"	type="radio" ${detailed.extensionSets.yaoyiyao.isAddMarketingList==0?'checked':''}>&nbsp;&nbsp;
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
															<input type="hidden" id="extensionType1" name="extensionSets['orderPromotion'].extensionType"	value="2" /> 
															<input type="hidden" id="id1"	name="extensionSets['orderPromotion'].id"	value="${detailed.extensionSets.orderPromotion.id}" /> 
															<input type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSets['orderPromotion'].dateStart" placeholder="开始日期"	style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.orderPromotion.dateStart}" pattern="yyyy-MM-dd"/>">
															<label style="float: left;">&nbsp;--&nbsp;</label> 
															<input	type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"
																name="extensionSets['orderPromotion'].dateEnd" placeholder="结束日期"
																style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.orderPromotion.dateEnd}" pattern="yyyy-MM-dd"/>">
															<td>
															<td><h5>排序：</h5>
															<td>
															<td class="sellerContentCss"><input type="text" class="form-control" placeholder="排序级别(数字越大越靠前)" name="extensionSets['orderPromotion'].sort" value="${detailed.extensionSets.orderPromotion.sort}"></td>
															<td><h5>添加到营销列表：</h5>
															<td>
															<td style="text-align: left;">
																<h5>
																	<label><input
																		name="extensionSets['orderPromotion'].isAddMarketingList" value="1"
																		type="radio"
																		${detailed.extensionSets.orderPromotion.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;<span
																		style="font-size: 12px;">是</span></label>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<label><input
																		name="extensionSets['orderPromotion'].isAddMarketingList" value="0"
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
															<td colspan="1"><input type="hidden" id="extensionType2" name="extensionSets['listPromotion'].extensionType"	value="3" /> 
															<input type="hidden" id="id2"	name="extensionSets['listPromotion'].id"	value="${detailed.extensionSets.listPromotion.id}" /> 
															<input	type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSets['listPromotion'].dateStart" placeholder="开始日期" style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.listPromotion.dateStart}" pattern="yyyy-MM-dd"/>">
															<label style="float: left;">&nbsp;--&nbsp;</label> 
															<input	type="text" readonly="readonly" class="form-control form-datetime"	data-date-format="yyyy-mm-dd"	name="extensionSets['listPromotion'].dateEnd" placeholder="结束日期"	style="width:46%;float:left"
																value="<fmt:formatDate value="${detailed.extensionSets.listPromotion.dateEnd}" pattern="yyyy-MM-dd"/>">
															<td>
															<td><h5>排序：</h5>
															<td>
															<td class="sellerContentCss"><input type="text"	class="form-control" placeholder="排序级别(数字越大越靠前)" name="extensionSets['listPromotion'].sort"	value="${detailed.extensionSets.listPromotion.sort}"></td>
															<td><h5>添加到营销列表：</h5>
															<td>
															<td style="text-align: left;">
																<h5>
																	<label><input	name="extensionSets['listPromotion'].isAddMarketingList" value="1"	type="radio"	${detailed.extensionSets.listPromotion.isAddMarketingList==1?'checked':''}>&nbsp;&nbsp;
																	<span	style="font-size: 12px;">是</span></label>
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	<label>
																	<input	name="extensionSets['listPromotion'].isAddMarketingList" value="0"	type="radio" ${detailed.extensionSets.listPromotion.isAddMarketingList==0?'checked':''}>&nbsp;&nbsp;
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
											<td class="sellerTitleCss">
												<h5>商家提示:</h5>
												<h5>
													<font color="red">（商家提示介绍最多300字）</font>
												</h5>
											</td>
											<td colspan="9"><textarea name="sellerDetailed.tips"
													rows="10" class="form-control">${detailed.sellerDetailed.tips}</textarea>
											</td>
										</tr>
										<tr>
											<td class="sellerTitleCss">
												<h5>商家优惠:</h5>
												<h5>
													<font color="red">（商家优惠介绍最多300字）</font>
												</h5>
											</td>
											<td colspan="9"><textarea name="sellerDetailed.rule"
													rows="10" class="form-control">${detailed.sellerDetailed.rule}</textarea>
											</td>
										</tr>
										<tr>
											<td colspan="3" align="left"><span
												style="position:relative;top:49px;"><h5>商家等级信息：</h5></span></td>
											<td colspan="6"></td>
										</tr>
										<tr height="1px">
											<td colspan="12"><hr></td>
										</tr>


										<tr>
											<td colspan="12">
												<table>
													<tr>
														<td class="sellerTitleCss">
															<h5>
																人均消费<font color="red">(元)</font>:
															</h5>
														</td>
														<td class="sellerContentCss"><input type="text"
															class="form-control" placeholder="人均消费"
															name="sellerDetailed.consume"
															value="<fmt:formatNumber value='${detailed.sellerDetailed.consume}' pattern='00.00'/>">
															<input type="hidden" name="sellerDetailed.isPay" value="0">
														</td>
														<td class="sellerTitleCss">
															<h5>是否连锁:</h5>
														</td>
														<td class="sellerContentCss"><select
															class="form-control" id="isChain"
															name="sellerDetailed.isChain"
															value="${detailed.sellerDetailed.isChain}">
																<option value="">请选择</option>
																<option value="1"
																	<c:if test="${detailed.sellerDetailed.isChain==1}">selected</c:if>>是</option>
																<option value="0"
																	<c:if test="${detailed.sellerDetailed.isChain==0}">selected</c:if>>否</option>
														</select></td>
														<td class="sellerTitleCss">
															<h5>商家等级:</h5>
														</td>
														<td class="sellerContentCss">
														<select class="form-control"  name ="sellerDetailed.sellerGrade">
											                <option value = "1" <c:if test="${detailed.sellerDetailed.sellerGrade==1}">selected</c:if>>A&nbsp;级</option> 
											                <option value = "3" <c:if test="${detailed.sellerDetailed.sellerGrade==3}">selected</c:if>>B&nbsp;级</option>
											                <option value = "5" <c:if test="${detailed.sellerDetailed.sellerGrade==5}">selected</c:if><c:if test="${empty detailed.sellerDetailed.sellerGrade}">selected</c:if>>C&nbsp;级</option>
											             </select>
														</td>
													</tr>
													<tr>
														<td class="sellerTitleCss">
															<h5>级别调整原因:</h5>
														</td>
														<td class="sellerContentCss" colspan="5"><textarea
																name="sellerDetailed.adjustReason" class="form-control">${detailed.sellerDetailed.adjustReason}</textarea>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td rowspan="2"
									style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br> <br> <br> <br> <br> <br>
									<br> <br>
									<button class="btn btn-danger" type="submit"
										id="sellerDetailedUpdate">
										<i class="icon-save"></i>&nbsp;保存
									</button>&nbsp; <!-- <button class="btn btn-warning" type="reset" id="backDetailedId"><i class="icon-reply"></i>&nbsp;返回</button>		 -->
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/common/IDCard.js"></script>
	<script src="<%=path%>/js/businessman/editSeller.js"></script>
	<script src="<%=path%>/js/businessman/commonAgio.js"></script>
</body>
</html>
