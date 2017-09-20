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
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>添加商家信息</title>
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
	<!-- 用于查询分店列表的id -->
	<input type="hidden" id="fartherSellerId"
		value="${param.fartherSellerId}">
	<input type="hidden" id="isType" value="${isType}">	
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form id="sellerForm" role="form">
						<input type="hidden" name="sellerToken" value="${sellerToken}">
						<input type="hidden" id="selleridid" name="sellerid"
							value="${selleridList.sellerid}" /> <input type="hidden" id="isType"
							name="isType" value="${isType}">
						<table class="table" style="text-align: center;">
							<tr>
								<td class="sellerTitleCss">
									<h5>商家名称:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" name="sellername" placeholder="商家名称"
									value="${selleridList.sellername}"></td>
								<td class="sellerTitleCss">
									<h5>商家地址:</h5>
								</td>
								<td colspan="4"><input type="text" class="form-control"
									name="address" placeholder="地址" value="${selleridList.address}">
								</td>
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
								<td class="sellerContentCss"><select class="form-control"
									id="zoneid" name="zoneid" initValue="${selleridList.zoneid}">
										<option value="">请先选择区域再选择商圈</option>
								</select></td>
								<td class="sellerTitleCss"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属合作商:</h5></td>
								<td class="sellerContentCss" style="text-align: left"><select
									class="form-control" id="jointid" name="jointid"
									style="width:100%;" initValue="${selleridList.jointid}">
										<option value="">请先选择区域</option>
								</select></td>
							</tr>

							<tr>
								<%-- <td><h5>所属业务员:</h5></td>
								<td style="text-align: left"><select class="form-control"
									id="staffid" name="staffid" style="width:width:100%;"
									initValue="${selleridList.staffid}">
										<option value="">请先选择合作商</option>
								</select>
								</td> --%>
								<td class="sellerTitleCss">
									<h5>所属寻蜜客:</br><font color="red">(输入寻蜜客电话模糊搜索):</font></h5>
								</td>
							 	<td class="xmerContentCss">
									<label id="checkids"></label>
							 		<select class="form-control" id="uid" name="uid" initValue="${selleridList.uid}" style="width:100%;"></select>
								</td> 

								<td>
									<h5>&nbsp;&nbsp;&nbsp;&nbsp;联盟店:</h5>
								</td>
								<td style="text-align: left"><select class="form-control"
									id="allianceId" name="allianceId" style="width:100%;"
									initValue="${selleridList.allianceId}">
								</select></td>

								<td>
									<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;连锁店:</h5>
								</td>
								<td style="text-align: left"><select class="form-control"
									id="fatherid" name="fatherid" style="width:100%;"
									initValue="${selleridList.fatherid}">
								</select> <input type="hidden" class="form-control" name="lssellername"
									value="${selleridList.lssellername}"></td>
							</tr>
							<tr>
								<td class="sellerTitleCss"><h5>经营行业：</h5></td>
								
								<td class="sellerContentCss">
									<input type="hidden" name="typename" value="${selleridList.typename}" /> 
									<input type="hidden" name="tradename" value="${selleridList.tradename}" />
									<div class="input-group" id="tradeSelect" style="width : 100%" initValue="${selleridList.genre}"></div> 
						<%-- 			
									<div class="form-group" id="includeTrade">
										<label class="col-md-2 control-label"></label>
										<div class="col-md-5" style="width : 102%;">
											<div class="input-group">
												<div class="includeTradeSelect" ></div><div id="checkMsg"></div>
												<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> 
												<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
											</div>
										</div>
									</div>
									
									<div class="includeTradeSelectTemp hidden ">
										<div class="input-group">
											<div class="includeTradeSelect" style="width : 100%"></div>
											<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addIncludeTrade(this) ;"></i></span> 
											<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="removeIncludeTrade(this);"></i></span>
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
								<td nowrap="nowrap"><h5>美食星势力商户:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="isforce" value="1" type="radio"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isforce" value="0" type="radio" checked="checked"><span
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
									<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法人姓名:</h5>
								</td>
								<td colspan="2"><input type="text" class="form-control"
									name="fullname" placeholder="法人姓名"
									value="${selleridList.fullname}"></td>
							</tr>
							<tr>
								<td><h5>&nbsp;&nbsp;营业时间：</h5></td>
								<td><input type="text" class="form-control form-time"
									name="sdate1" readonly style="width:45%;float:left;"
									value="${selleridList.sdate1}" /> <span
									style="float:left;width:10%;">&nbsp;~&nbsp;</span> <input
									type="text" class="form-control form-time" name="sdate2"
									readonly style="width:45%;float:left;"
									value="${selleridList.sdate2}" /></td>

								<td>
									<h5>有效日期开始:</h5>
								</td>
								<td><input type="text" class="form-control form-datetime"
									readonly
									title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd"/>"
									name="svalidity" id="sjaddsvalidity" placeholder="开始日期"
									value="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>">
								</td>

								<td>
									<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有效日期结束:</h5>
								</td>
								<td><input type="text" class="form-control form-datetime"
									readonly name="evalidity" id="sjaddevalidity"
									placeholder="结束日期"
									value="<fmt:formatDate value="${selleridList.evalidity}" pattern="yyyy-MM-dd"/>">
								</td>
							</tr>
							<tr>

								<td><h5>是否折上折：</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="agioAgio" value="1" type="radio"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="agioAgio" value="0" type="radio"
											checked="checked"><span style="font-size: 12px;">否</span>
									</h5>
									<div id="agioInstructionDiv"></div>
								</td>

								<td><h5>总部帮忙签约：</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="give" value="1" type="radio"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="give" value="0" type="radio" checked="checked"><span
											style="font-size: 12px;">否</span>
									</h5>
								</td>

								<td nowrap="nowrap"><h5>是否扣取支付手续费:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="isfees" value="0" type="radio" checked="checked"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isfees" value="1" type="radio"><span
											style="font-size: 12px;">否</span>
									</h5>
								</td>
							</tr>
							
							<tr>
<%--
								<td><h5>&nbsp;&nbsp;订单基数：</h5></td>
								<td>
									<input type="text" class="form-control" name="order"
									placeholder="订单基数" value="${selleridList.order}">
								</td>

								<td><h5>&nbsp;&nbsp;节省钱基数：</h5></td>
								<td>
									<input type="text" class="form-control" name="saveMoney"
									placeholder="节省钱基数" value="${selleridList.saveMoney}">
								</td>
 --%>
 								<td><h5>&nbsp;&nbsp;商家标签：</h5></td>
								<td>
									<select class="form-control" tabindex="2" name ="label">
						                <option value = "0">无</option>
						                <option value = "1">推荐商家</option>
						                <option value = "2">省钱实惠</option>
						                <option value = "3">热门商家</option>
						             </select>
								</td>
								<td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否为KA商户:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="isKa" value="1" type="radio"><span
											style="font-size: 12px;">是</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="isKa" value="0" type="radio" checked="checked"><span
											style="font-size: 12px;">否</span>
									</h5>
								</td>
							</tr>
							<tr>
								<td><h5>每日商家提现限额:</h5></td>
								<td>
								<input  type="text" name = "dailyLimitWithdraw" class="form-control" value="50000.00"> 
								</td>
								<td><h5>SAAS交易总额:</h5></td>
								<td>
								<input  type="text" name = "totalLimitTurnover" class="form-control" value="3000000.00"> 
								</td>
								<td><h5>每日交易限额:</h5></td>
								<td>
								<input  type="text" name = "dailyLimitTurnover" class="form-control" value="50000.00"> 
								</td>
							</tr>
							<tr>
								<td><h5>是否公开商户:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "isPublic" value = "1" checked="checked"><span style="font-size: 12px;">是</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input  type="radio" name = "isPublic" value = "0" ><span style="font-size: 12px;">否</span> 
								</td>
								<td><h5>是否参与分红:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "joinDividend" value = "1" ><span style="font-size: 12px;">是</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<input  type="radio" name = "joinDividend" value = "0" checked="checked"><span style="font-size: 12px;">否</span> 
								</td>
								<td><h5>是否设为付费商家:</h5></td>
								<td style="text-align: left;">
								<input  type="radio" name = "isPaid" value = "1" checked="checked"><span style="font-size: 12px;">是</span> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input  type="radio" name = "isPaid" value = "0" ><span style="font-size: 12px;">否</span> 
								</td>
							</tr>
							<tr>
								<td><h5>营业执照名称：</h5></td>
								<td><input type="text" class="form-control" name="businessLicenseName"
										   placeholder="营业执照名称" value=""></td>
								<td>
							</tr>
						</table>
						<!-- 折扣信息=================================================================================================== -->
						<hr>
						<table class="table" style="text-align: center;" >
							<tr>
								<td><h5>分账模式:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="ledgerMode" value="0" type="radio" checked="checked"><span
											style="font-size: 12px;">正常分账模式</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="ledgerMode" value="2" type="radio"><span
											style="font-size: 12px;">仅商家参与分账模式</span>
									</h5>
								</td>
								<td class="sellerTitleCss">
									<h5>折扣（%）:</h5>
								</td>
								<td class="sellerContentCss"><input type="text"
									class="form-control" placeholder="请输入0-100之间的数" name="baseagio"
									value="${sellerAgio.baseagio}"></td>
								<%--<td class="sellerTitleCss">--%>
									<%--<h5>用户占比（%）:</h5>--%>
								<%--</td>--%>
								<%--<td class="sellerContentCss"><input type="text"--%>
									<%--class="form-control" readonly="readonly" name="yledger"--%>
									<%--value="${sellerAgio.yledger}"></td>--%>
							</tr>
							<%--<tr>--%>
								<%--<td class="sellerTitleCss">--%>
									<%--<h5>营业收入:</h5>--%>
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
									<%--class="form-control" readonly="readonly" name="ratio"--%>
									<%--value="${selleridList.ratio}"></td>--%>
								<%--<td class="sellerTitleCss">--%>
									<%--<h5>平台补贴（%）:</h5>--%>
								<%--</td>--%>
								<%--<td class="sellerContentCss"><input type="text"--%>
									<%--class="form-control" name="flatAgio" readonly="readonly"></td>	--%>
								<%--<td class="sellerTitleCss">--%>
									<%--<h5>平台扣款比例（%）:</h5>--%>
								<%--</td>--%>
								<%--<td class="sellerContentCss"><input type="text"--%>
									<%--class="form-control" name="debit"--%>
									<%--value="${selleridList.debit}"--%>
									<%--placeholder="不输入，默认为0"></td>--%>
							<%--</tr>--%>
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
										<input name="liveLedgerOperating" value="0" type="radio" ${selleridList.liveLedgerOperating==0?'checked':'' } checked><span
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
										<input name="liveLedger.ledgerStyle" value="0" type="radio" ${selleridList.liveLedger.ledgerStyle!=1?'checked':''}><span
											style="font-size: 12px;">自动分账</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="liveLedger.ledgerStyle" value="1" type="radio" ${selleridList.liveLedger.ledgerStyle==1?'checked':''}><span
											style="font-size: 12px;">手动分账</span>
									</h5>
								</td>
								<td class="width: 10%"><h5>直播分账模式:</h5></td>
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
								<td class="width: 10%">
									<h5>分账比例（%）:</h5>
								</td>
								<td class="width: 25%"><input type="text"
									class="form-control" placeholder="请输入0-100之间的数" name="liveLedger.ledgerRatio"
									value="${selleridList.liveLedger.ledgerRatio}"></td>
									
								<td class="width: 10%">
									<h5>有效时间:</h5>
								</td>
								<td  class="width: 25%">
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
						<!-- 鸟币支付开关=================================================================================================== -->
						<hr>
						<table class="table" style="text-align: center;" width="100%;">
							<tr>
								<td><h5>鸟币支付开关:</h5></td>
								<td style="text-align: left;">
									<h5>
										<input name="liveCoinPay" value="1" type="radio"><span
											style="font-size: 12px;">开启</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="liveCoinPay" value="0" type="radio" checked="checked"><span
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

								<td class="sellerTitleCss">
									<h5>营业执照正本附件:</h5>
								</td>
								<td>
									<div class="col-md-9">
										<div id='sellerHead2'></div>
										<input type="hidden" id="licenseurlid" name="licenseurl"
											value="${selleridList.licenseurl}" />
									</div>
								</td>

								<td class="sellerTitleCss">
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
											   value="" />
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
											name="identitynurl" value="${selleridList.identitynurl}"/>
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
											value="${selleridList.agreement}" />
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
						<div style="height:20px"></div>
						<div align="center">
							<button class="btn btn-danger" type="submit"
								id="UpdateSavaSeller">
								<i class="icon-save"></i>&nbsp;保存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button"
								onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/businessman/addSeller.js"></script>
	<script src="<%=path%>/js/businessman/commonAgio.js"></script>
	<script src="<%=path%>/js/common/IDCard.js"></script>
</body>
</html>
