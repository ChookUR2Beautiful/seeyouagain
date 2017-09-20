<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/chosenCity.css" rel="stylesheet">
<style type="text/css">
body,table,tbody,td,th,tr,div,ul,li,h1,h2,h4,h5{
	margin:0;
	padding:0;
	border:0;
	font-size:13px;
	line-height:1.53846;
}
body{
	padding:10px 30px;
	background-color:#EEE;
}
.content{
	width:auto;
	height:auto;
	border:2px;
}
td, th {
    padding: 10px;
}
td h5{ 
	font-weight:bold;
	text-align:right;
}
.myPanel{
	border:0;
	background-color:#FFF;
}
ul li a{
	font-size:16px;
}

.propertyTable{
	border:solid #ddd; border-width:1px 0px 0px 1px;
	cellpadding:1px;
	text-align:center;
}
.propertyTable td{
	background:#fff;
	border:solid #ddd; border-width:0px 1px 1px 0px;
}
.propertyTable caption{
	text-align:center;
}
.propertyTable thead {
	font-weight: 900;
}
.propertyTable tbody input{
	width: 90px;
	text-align: center;
}
</style>
</head>

<body>
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="content">

		<div class="panel panel-default myPanel">
			<!-- 
		   <div class="panel-heading myPanelHeading">
					<ul id="myTab" class="nav nav-pills nav-justified">
					   <li class="active">
							<a href="#info" data-toggle="tab">产品基本信息 </a>
					   </li>
					   <li >
							<a href="#detail" data-toggle="tab">产品详细信息</a>
					   </li>
					</ul>
		   </div>
		    -->
			<form class="form-horizontal" role="form" id="editFreshInfo">
				<input type="hidden" id="isType" value="${isType}">
				<input type="hidden" id="fastfdsHttp" value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>"/>
				<input type="hidden" name="codeId" value="${freshInfo.codeId}"> 
				<input type="hidden" name="pid" value="${freshInfo.pid}">
				<input type="hidden" name="dstatus" value="0"> 
				<input type="hidden" name="isIntegral" value="1">
				<div id="myTabContent" class="tab-content panel-body">
					<div class="tab-pane fade in active" id="info">
						<!-- 第一部分 -->
						<h3>产品基本信息*</h3>
						<hr>
						<table calss="table-col" width="100%;">
							<tbody>
								<tr>
									<td width="10%"><h5>产品类型<font color="red">*</font></h5></td>
									<td width="15%">
										<select class="form-control" name="saleType" id="saleType">
											<option value="0" <c:if test="${freshInfo.saleType==0}">selected</c:if>>线上</option>
											<option value="1" <c:if test="${freshInfo.saleType==1}">selected</c:if>>线下</option>
										</select>
									</td>
									<td width="10%"><h5>产品名称<font color="red">*</font></h5></td>
									<td width="15%">
										<input type="text" class="form-control" placeholder="产品名称" name="pname" value="${freshInfo.pname}">
									</td>
									<td width="10%"><h5>品牌名称</h5></td>
									<td width="15%">
										<select class="form-control" id="brandId" name="brandId"
										initValue="${freshInfo.brand_id}" style="width:100%;"></select>
										<%-- <input type="text" name="brandName" class="form-control" placeholder="品牌名称" value="${freshInfo.brandName}"> --%>
									</td>
									<td width="10%"><h5>产品分类<font color="red">*</font></h5></td>
									<td width="15%">
										<div class="dropdown">
							            <select  role="button" data-toggle="dropdown" class="form-control" style="weigth:50px" data-target="#" name="classa" >
							               <option  value="20" style="display:none;" >请选择</option>
							            	<option id="dLabel"  value="" style="display:none;" ></option>
							            </select>
							            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
							            	<li><a href="javascript:;" id="" onclick="confirmType(this)">请选择</a></li>
							            	<c:forEach items="${freshTypes}" var="freshType">
											 <li class="dropdown-submenu">`
							                    <a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${freshType.id}">${freshType.name}</a>
							                    <c:if test="${freshType.childs!=null}">
							                    <ul class="dropdown-menu">
							                    <c:forEach items="${freshType.childs}" var="type"> 
							                        <li><a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${type.id}">${type.name}</a></li>
							                       </c:forEach>
							                    </ul>
							                    </c:if>
							                </li>
											</c:forEach>
							            </ul>
    							</div>
									</td>
								</tr>
								<tr>
									<td><h5>产品原价<font color="red">*</font></h5></td>
									<td>
										<input type="text" class="form-control" placeholder="产品原价" name="price" value="${freshInfo.price}">
									</td>
									<td><h5>产品采购价<font color="red">*</font></h5></td>
									<td>
										<input type="text" class="form-control" placeholder="产品采购价" id="purchasePrice" name="purchasePrice" value="${freshInfo.purchasePrice}">
									</td>
									<td><h5>产品售价<font color="red">*</font></h5></td>
									<td>
										<input type="text" class="form-control" id="discount" placeholder="优惠后价格" name="discount" value="${freshInfo.discount}" readonly="readonly">
									</td>
									<td><h5>积分价<font color="red">*</font></h5></td>
									<td>
										<div class="input-group">
											<input type="text" class="form-control" id="cash" placeholder="￥" name="cash" value="${freshInfo.cash}" 
												style="border-radius:4px;">
											<span class="input-group-addon fix-border" style="background-color: white;border: hidden;"> + </span>
											<input type="text" class="form-control" id="integral" placeholder="积分" name="integral" value="${freshInfo.integral}" style="border-radius:4px;">
										</div>
									</td>
								</tr>
								<tr>
									<td><h5>商品库存<font color="red">*</font></h5></td>
									<td>
										<input type="number" class="form-control" placeholder="库存产品数" name="store" value="${freshInfo.store}">
									</td>
									<td><h5>产品重量</h5></td>
									<td>
										<input type="text" class="form-control" placeholder="请输入产品重量" name="weight" value="${freshInfo.weight}">
									</td>
									<td><h5>产品排序<font color="red">*</font></h5></td>
									<td>
										<input type="text" class="form-control" placeholder="请输入产品排序" name="sort" value="${freshInfo.sort}">
									</td>
									<td><h5>产品状态<font color="red">*</font></h5></td>
									<td>
										<select class="form-control" name="pstatus">
											<!--  <option value = "">--请选择--</option> -->
											<option value="0"
												<c:if test="${freshInfo.pstatus==0}">selected</c:if>
												<c:if test="${empty freshInfo.pstatus}">selected</c:if>>未上线</option>
											<option value="1"
												<c:if test="${freshInfo.pstatus==1}">selected</c:if>>已上线</option>
											<option value="2"
												<c:if test="${freshInfo.pstatus==2}">selected</c:if>>已售罄</option>
											<option value="3"
												<c:if test="${freshInfo.pstatus==3}">selected</c:if>>已下线</option>
										</select>
									</td>
								</tr>
								<tr>
									<td><h5>是否支持退款<font color="red">*</font></h5></td>
									<td>
										<select class="form-control" name="allowRefund">
											<option value="true" <c:if test="${freshInfo.allowRefund==true}">selected</c:if>>是</option>
											<option value="false" <c:if test="${freshInfo.allowRefund==false}">selected</c:if>>否</option>
										</select>
									</td>
									<td><h5>产品配送方式<font color="red">*</font></h5></td>
									<td>
										<select class="form-control" name="deliveryType">
											<option value="0" <c:if test="${freshInfo.deliveryType==0}">selected</c:if>>快递</option>
											<option value="1" <c:if test="${freshInfo.deliveryType==1}">selected</c:if>>虚拟商品</option>
											<option value="2" <c:if test="${freshInfo.deliveryType==2}">selected</c:if>>到店自提</option>
											<option value="3" <c:if test="${freshInfo.deliveryType==3}">selected</c:if>>兑换码</option>
										</select>
									</td>
									<td><h5>产品描述</h5></td>
									<td>
										<input type="text" class="form-control" placeholder="冬季进补  营养美味" name="salesInfo" value="${freshInfo.salesInfo}">
									</td>
									<td><h5>产品净重量</h5></td>
									<td>
										<input type="text" class="form-control" placeholder="产品净重" name="suttle" value="${freshInfo.suttle}">
									</td>
									<td colspan="2"><h5>&nbsp;</h5></td>
								</tr>
								<tr>
									<td><h5>快递计重(千克)<font color="red">*</font></h5></td>
									<td>
										<input type="text" class="form-control" placeholder="快递计重,单位(kg)" name="expWeight" value="${freshInfo.expWeight}">
									</td>
									<td><h5>缩略图<font color="red">&nbsp;&nbsp;(620*400)</font></h5></td>
									<td>
										<div>
											<input type="hidden" class="form-control" id="breviary" name="breviary" value="${freshInfo.breviary}">
											<div id="breviaryUpload" ImgValidate="true"></div>
										</div>
									</td>
									<td colspan="8"><h5>&nbsp;</h5></td>
								</tr>
							</tbody>
						</table>
						<!-- 第二部分 -->
						<h3>产品包装信息</h3>
						<hr>
						<table calss="table-col" width="100%;">
							<tbody>
								<tr>
									<td width="10%"><h5>保质期(天)<font color="red">*</font></h5></td>
									<td width="15%">
										<input type="text" name="quality" class="form-control" placeholder="保质期" value="${freshInfo.quality}">
									</td>
									<td width="10%"><h5>生产日期<font color="red">*</font></h5></td>
									<td width="15%">
										<input type="text" id="production" name="production" class="form-control form-datetime" onClick="WdatePicker()" placeholder="yyyy-MM-dd" value="${freshInfo.productionStr}">
									</td>
									<td width="10%"><h5>售卖方式</h5></td>
									<td width="15%">
										<input type="text" name="sellType" class="form-control" placeholder="单品/套装" value="${freshInfo.sellType}">
									</td>
									<td width="10%"><h5>包装方式</h5></td>
									<td width="15%">
										<input type="text" name="packing" class="form-control" placeholder="包装方式" value="${freshInfo.packing}">
									</td>
								</tr>
								<tr>
									<td><h5>加工方式</h5></td>
									<td>
										<input type="text" name="crafts" class="form-control" placeholder="-28度冷鲜" value="${freshInfo.crafts}">
									</td>
									<td width="10%"><h5>产品标准</h5></td>
									<td>
										<input type="text" class="form-control" placeholder="ISO9001" name="standard" value="${freshInfo.standard}">
									</td>
									<td><h5>份量</h5></td>
									<td>
										<input type="text" name="deal" class="form-control" placeholder="几个人的份量" value="${freshInfo.deal}">
									</td>
									<td><h5>配料信息</h5></td>
									<td>
										<input type="text" name="batching" class="form-control" placeholder="配料信息" value="${freshInfo.batching}">
									</td>
								</tr>
								<tr>
									<td><h5>是否精选</h5></td>
									<td>
										<div class="radio">
											<label> 
												<input type="radio" name="choice" value="1" ${freshInfo.choice==1?"checked":""}> 是
											</label> &nbsp;&nbsp;&nbsp;&nbsp; <label> 
											<input type="radio" name="choice" value="0" ${freshInfo.choice==0?"checked":""}
												<c:if test="${empty freshInfo.choice}">checked</c:if>>否
											</label>
										</div>
									</td>
									<td><h5>是否有添加剂</h5></td>
									<td>
										<div class="radio">
											<label> 
												<input type="radio" name="isAdditives" id="ryes" value="1" ${freshInfo.isAdditives==1?"checked":""}>是
											</label> &nbsp;&nbsp;&nbsp;&nbsp; <label> 
												<input type="radio" name="isAdditives" id="rno" value="0" ${freshInfo.isAdditives==0?"checked":""}
												<c:if test="${empty freshInfo.isAdditives}">checked</c:if>>否
											</label>
										</div>
									</td>
									<td><h5>产品编号</h5></td>
									<td>
										<input type="text" name="goodsSerial" class="form-control" placeholder="产品编号" value="${freshInfo.goodsSerial}">
									</td>
									<td><h5>条形码</h5></td>
									<td>
										<input type="text" name="barcode" class="form-control" placeholder="条形码" value="${freshInfo.barcode}">
									</td>
								</tr>
							</tbody>
						</table>
						<!-- 第三部分 -->
						<h3>产品供应商信息*</h3>
						<hr>
						<table calss="table-col" width="100%;">
							<tbody>
								<tr id="supplier">
									<td width="10%;"><h5>供应商名称<font color="red">*</font></h5></td>
									<td width="15%;">
										<div class="input-group">
											<label id="checkids"></label> 
											<select class="form-control" id="supplierId" name="supplierId" 
												initValue="${freshInfo.supplierId}" style="width:100%;"></select>
											<input type="hidden" id="supplierName" name="supplierName"/>
											<span class="input-group-addon fix-border" style="background-color: white;border: hidden;">
												<a href="<%=basePath%>fresh/supplier/init.jhtml" style="color: blue;font-size: 14;width: 2%;" id="editSupplier">编辑</a>
											</span>
										</div>
									</td>
									<td width="10%;"><h5>供应商联系人<font color="red">*</font></h5></td>
									<td width="15%;">
										<input type="text" name="contacts" class="form-control" placeholder="供应商联系人" value="${freshInfo.contacts}" readonly="readonly">
									</td>
									<td width="10%;"><h5>供应商联系电话<font color="red">*</font></h5></td>
									<td width="15%;">
										<input type="text" name="phone" class="form-control" placeholder="供应商联系电话" value="${freshInfo.supplierPhone}" readonly="readonly">
									</td>
									<td width="10%;"><h5>供应商地址</h5></td>
									<td width="15%;">
										<input type="text" name="address" class="form-control" placeholder="供应商地址" value="${freshInfo.supplierAddress}" readonly="readonly">
									</td>
								</tr>
								<tr>
									<td><h5>原产地</h5></td>
									<td>
										<input type="text" name="place" class="form-control" placeholder="例如：中国 云南" value="${freshInfo.place}">
									</td>
									<td><h5>生产厂家</h5></td>
									<td>
										<input type="text" name="cname" class="form-control" placeholder="江西省欢喜地农产品批发行" value="${freshInfo.cname}">
									</td>
									<td><h5>厂家地址</h5></td>
									<td>
										<input type="text" name="address" class="form-control" placeholder="" value="${freshInfo.address}">
									</td>
									<td><h5>厂家许可证编号</h5></td>
									<td>
										<input type="text" name="licenseId" class="form-control" placeholder="QS440618010001" value="${freshInfo.licenseId}">
									</td>
								</tr>
								<tr>
									<td><h5>厂家电话</h5></td>
									<td>
										<input type="text" name="tel" class="form-control" placeholder="" value="${freshInfo.tel}">
									</td>
									<td colspan="6"></td>
								</tr>
							</tbody>
						</table>
						<!-- 第四部分 -->
						<h3>产品销售、配送城市</h3>
						<hr>
						<table calss="table-col" width="100%;">
							<tbody>
								<tr>
									<td width="10%"><h5>配送城市</h5></td>
									<td width="15%;">
										<input type="hidden" value="${freshInfo.deliveryCity}" id="dc"/>
										<input type="hidden" value="${freshInfo.strDeliveryCity}" id = "sdc"/>
										<input type="hidden" value="${freshInfo.notDeliveryCity}" id = "ndc"/>
										<input type="hidden" value="${freshInfo.strNotDeliveryCity}" id = "sndc"/>
										<div class="input-group" style="text-align: left;">
											<select class="form-control" id="chooseDeliveryCity" style="border-radius:4px;width:250px;">
												<!-- <option value="0">--请选择--</option> -->
												<option value="1">支持配送城市</option>
												<option value="2">不支持配送城市</option>
											</select>
										</div>
									</td>
									<td style="position:relative;">
										<input type="hidden" name="deliveryCity" value="${freshInfo.deliveryCity}" id="deliveryCityId"/>
										<div class="deliveryCity">
											<input class="form-control" id="deliveryCity" style="border-radius:4px;width:400px;" value="${freshInfo.strDeliveryCity}"/>
										</div>
									</td>
									<td colspan="5"></td>
								</tr>
								<tr>
									<td width="10%"><h5>销售城市</h5></td>
									<td>
										<input type="hidden" value="${freshInfo.saleCity}" id="sc"/>
										<input type="hidden" value="${freshInfo.strSaleCity}" id="ssc"/>
										<input type="hidden" value="${freshInfo.notSaleCity}" id="nsc"/>
										<input type="hidden" value="${freshInfo.strNotSaleCity}" id="snsc"/>
										<select class="form-control" id="chooseSaleCity" style="border-radius:4px;width:250px;">
											<!-- <option value="0">--请选择--</option> -->
											<option value="1">支持销售城市</option>
											<option value="2">不支持销售城市</option>
										</select>
									</td>
									<td style="position:relative;">
										<input type="hidden" name="saleCity" value="${freshInfo.saleCity}" id="saleCityId"/>
										<div class="saleCity">
											<input class="form-control" id="saleCity" style="border-radius:4px;width:400px;" value="${freshInfo.strSaleCity}"/>
										</div>
									</td>
									<td colspan="5"></td>
								</tr>
								<tr>
									<td width="10%"><h5>运费设置</h5></td>
									<td width="15%;">
										<div class="input-group">
											<select class="form-control" name="expTid" style="border-radius:4px;width:250px;">
													<c:forEach items="${postageTemplateList}" var="postageTemplate">
														<option value="${postageTemplate.tid}" <c:if test="${freshInfo.expTid==postageTemplate.tid}">selected</c:if>>${postageTemplate.title}</option>
													</c:forEach>
											</select>
										</div>
									</td>
									<td>
										<a href="<%=basePath%>fresh/postagetemplate/init.jhtml" style="color: blue;font-size: 14;width: 4px;">&nbsp;&nbsp;编辑</a>
									</td>
									<td colspan="5"></td>
								</tr>
							</tbody>
						</table>
						<!-- 第五部分 -->
						<h3>产品属性信息</h3>
						<hr>
						<div width="100%;">
							<div style="width: 30%; margin-left: 6%; float: left;">
								<div id="propertyId">
									<div class="form-group" style="width: 5%;">
										<div class="col-md-8" style="width:100%; float: left;">
											<div class="input-group" style="height: 4%;">
												<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer;">添加分组</i></span>
												<span class="input-group-addon" style="background-color: white;" >
													<a style="color: red;cursor: pointer; font-weight: 900;" onclick = "generateTab();">重新生成表格</a>
												</span>
											</div>
										</div>
									</div>
								</div>
								<c:choose>
									<c:when test="${isType=='add'}">
										<div id="properties" style="margin-left: 8%;">
										</div>
									</c:when>
									<c:otherwise>
										<div id="properties" style="margin-left: 8%;">
											<c:forEach items="${freshInfo.propertyList}" var="saleProperty" varStatus="propVs">
											<div class="property" style="margin-top: 2%;">
												<div class="propertyName" style="margin-top: 1%;">
													<div class="input-group" style="width: 8%;float: left;">
														<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addPro(this);"></i></span>
														<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="minusPro(this);"></i></span>
													</div>
													<div style="float: left;">&nbsp;&nbsp;</div>
													<div style="float: left; width: 45%;">
														<input class="form-control" name="propertyList[${propVs.index}].property" value="${saleProperty.property}" style="width: 100%; height: 28px;">
														<input type="hidden" name="propertyList[${propVs.index}].id" value="${saleProperty.id}"/>
													</div>
													<div style="float: left;">&nbsp;&nbsp;</div>
													<div style="float: left; width: 15%;">
														<input class="form-control" name="propertyList[${propVs.index}].sort" value="${saleProperty.sort}" style="width: 100%; height: 28px;" onblur="generateTab();"/>
													</div>
													<div style="width: 1%; clear: both;"></div>
												</div>
												<div class="propertyValues" style="margin-left: 19%;">
													<c:forEach items="${saleProperty.propertyValueList}" var="salePropertyVal" varStatus="propValVs">
													<div class="propertyValue" style=" margin-top: 1%;">
														<div class="input-group" style="width: 5%; float: left;">
															<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="minusProVal(this);"></i></span>
															<span class="input-group-addon"></span>
														</div>
														<div>
															<input class="form-control" name="propertyList[${propVs.index}].propertyValueList[${propValVs.index}].value" value="${salePropertyVal.value}" style="width: 44%; height: 28px;" onblur="generateTab();">
															<input type="hidden" name="propertyList[${propVs.index}].propertyValueList[${propValVs.index}].id" value="${salePropertyVal.id}" />
															<input type="hidden" name="propertyList[${propVs.index}].propertyValueList[${propValVs.index}].propertyId" value="${saleProperty.id}" />
														</div>
													</div>
													</c:forEach>
												</div>
											</div>
											</c:forEach>
										</div>
									</c:otherwise>
								</c:choose>
								<div id="properties" style="margin-left: 8%;">
								</div>
							</div>
							<c:choose>
								<c:when test="${isType=='add'}">
									<div style="width: 50%; display: inline;" id="propertyTab">
										<div style="margin-top: 2px;">
											<table class="propertyTable" class="propertyTable">
												<caption></caption>
												<tbody></tbody>
											</table>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div style="width: 50%; display: inline;" id="propertyTab">
										<div style="margin-top: 2px;">
											<table class="propertyTable" class="propertyTable">
												<c:if test="${!empty freshInfo.propertyList}">
												<caption><font style="font-weight: 900;">产品属性参数表</font></caption>
												<thead>
												<tr>
													<c:forEach items="${freshInfo.propertyList}" var="saleProp" varStatus="spVs">
														<td>${saleProp.property}<input type="hidden" name="propertyList[${spVs.index}].id" value="${saleProp.id}"/></td>
													</c:forEach>
													<td>加价金额</td>
													<td>库存</td>
													<td>排序</td>
												</tr>
												</thead>
												</c:if>
												<tbody>
												<c:if test="${!empty freshInfo.saleGroupList}">
													<c:forEach items="${freshInfo.saleGroupList}" var="saleGroup" varStatus="gvs">
														<tr>
															<c:forEach items="${saleGroup.pvValues}" var="pvValue" varStatus="pvs">
																<td>${pvValue}<input name="saleGroupList[${gvs.index}].pvValues[${pvs.index}]" type="hidden" value="${pvValue}"/>
																</td>
															</c:forEach>
															<td><input class="form-control" name="saleGroupList[${gvs.index}].amount" value="${saleGroup.amount}" style="height: 28px;"/></td>
															<td><input class="form-control" name="saleGroupList[${gvs.index}].stock" value="${saleGroup.stock}" style="height: 28px;"/>
															</td>
															<td><input class="form-control" name="saleGroupList[${gvs.index}].sort" value="${saleGroup.sort}" style="height: 28px;" />
																<input name="saleGroupList[${gvs.index}].id" value="${saleGroup.id}" type="hidden" />
																<input name="saleGroupList[${gvs.index}].pvIds" value="${saleGroup.pvIds}" type="hidden" />
															</td>
														<tr>
													</c:forEach>
												</c:if>
												</tbody>
											</table>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
							
							<div style="width: 15%; clear: both;"></div>
						</div>
						<!-- 按钮，下一步 取消 -->
						<div align="center" style="margin-top: 5%;">
							<table>
								<tr>
									<td width="160">
										<button class="btn btn-danger" type="button" id="nextStep">
											<span class="glyphicon glyphicon-hand-right"></span>&nbsp;下一步
										</button>&nbsp;&nbsp;
									</td>
									<td width="160"></td>
									<td width="160">
										<!-- <button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button> -->
										<a class="btn btn-warning loadBack"  href="javascript:;"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
								</tr>
							</table>
						</div>
					</div>
					<div class="tab-pane fade" id="detail">
						<table class="tDetail" width="94%">
							<caption>
								<h3>产品详细信息</h3>
							</caption>
							<tbody>
								<tr >
									<td width="15%"><h5>产品图片</h5></td>
									<td width="17%"><input type="hidden" class="form-control"
										id="pic1" name="pic1" value="${freshInfo.pic1}">
										<div id="pic1Upload"></div></td>
									<td width="17%"><input type="hidden" class="form-control"
										id="pic2" name="pic2" value="${freshInfo.pic2}">
										<div id="pic2Upload"></div></td>
									<td width="17%"><input type="hidden" class="form-control"
										id="pic3" name="pic3" value="${freshInfo.pic3}">
										<div id="pic3Upload"></div></td>
									<td width="17%"><input type="hidden" class="form-control"
										id="pic4" name="pic4" value="${freshInfo.pic4}">
										<div id="pic4Upload"></div></td>
									<td width="17%"><input type="hidden" class="form-control"
										id="pic5" name="pic5" value="${freshInfo.pic5}">
										<div id="pic5Upload"></div></td>
								</tr>
								<tr>
									<td><h5>详情图片</h5></td>
									<td><input type="hidden" class="form-control" id="img1"
										name="img1" value="${freshInfo.img1}">
										<div id="img1Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img2"
										name="img2" value="${freshInfo.img2}">
										<div id="img2Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img3"
										name="img3" value="${freshInfo.img3}">
										<div id="img3Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img4"
										name="img4" value="${freshInfo.img4}">
										<div id="img4Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img5"
										name="img5" value="${freshInfo.img5}">
										<div id="img5Upload"></div></td>
								</tr>
								<tr>
									<td><h5>&nbsp;</h5></td>
									<td><input type="hidden" class="form-control" id="img6"
										name="img6" value="${freshInfo.img6}">
										<div id="img6Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img7"
										name="img7" value="${freshInfo.img7}">
										<div id="img7Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img8"
										name="img8" value="${freshInfo.img8}">
										<div id="img8Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img9"
										name="img9" value="${freshInfo.img9}">
										<div id="img9Upload"></div></td>
									<td><input type="hidden" class="form-control" id="img10"
										name="img10" value="${freshInfo.img10}">
										<div id="img10Upload"></div></td>
								</tr>

								<tr>
									<td><h5>配送描述</h5></td>
									<td colspan="5"><textarea class="form-control" rows="2"
											name="delivery" style="padding:6px;height:50px;">${freshInfo.delivery}</textarea>
									</td>
								</tr>
								<tr>
									<td><h5>包邮描述</h5></td>
									<td colspan="5"><textarea class="form-control" rows="2"
											name="postnote" style="padding:6px;height:50px;">${freshInfo.postnote}</textarea>
									</td>
								</tr>
								<tr>
									<td><h5>服务描述</h5></td>
									<td colspan="5"><textarea class="form-control" rows="2"
											name="servicenote" style="padding:6px;height:50px;">${freshInfo.servicenote}</textarea>
									</td>
								</tr>
								<tr>
									<td><h5>html5详情页面</h5></td>
									<td colspan="5">
										<div class="block-content collapse in">
											<textarea id="ckeditor_standard" class="ckeditor" name="html"
												width="">${freshInfo.html}</textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td><h5>备注</h5></td>
									<td colspan="5"><textarea class="form-control" rows="2"
											name="remarks" style="padding:6px;height:50px;">${freshInfo.remarks}</textarea>
									</td>
								</tr>
								<tr>
									<td><h5>排序大小</h5></td>
									<td colspan="2"><input type="text" name="picsort"
										class="form-control" value="${freshInfo.picsort}"></td>
									<td colspan="3"></td>
								</tr>
							</tbody>
						</table>
						<div align="center">
							<table>
								<tr>
									<td width="160">
										<button class="btn btn-danger" type="button" id="preStep">
											<span class="glyphicon glyphicon-hand-left"></span> &nbsp;上一步
										</button>&nbsp;&nbsp;
									</td>
									<td width="160">
										<button class="btn btn-danger" type="submit" id="saveJoint">
											<span class="glyphicon glyphicon-floppy-disk"></span>
											&nbsp;保存
										</button>&nbsp;&nbsp;
									</td>
									<td width="160">
										<!-- <button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button> -->
										<a class="btn btn-warning loadBack"  href="javascript:;"><span
											class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
									</td>
								</tr>
							</table>
						</div>

					</div>
				</div>
				<!-- 			<div align="center">
					<table>
						<tr>
							<td width="160">
							<button class="btn btn-danger" type="submit" id="saveJoint" ><span class="glyphicon glyphicon-floppy-disk"></span> &nbsp;保存</button>&nbsp;&nbsp;
							<td width="160">
							<!-- <button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button> -->
				<!--							<a class="btn btn-warning" href="fresh/manage.jhtml?"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
							</td>
						</tr>
					</table>
				</div>
 -->
			</form>
		</div>
	</div>
	<!-- 属性名 -->
	<div style="visibility: hidden;" id="propertyDivId">
		<div class="property" style="margin-top: 2%;">
			<div class="propertyName" style="margin-top: 1%;">
				<div class="input-group" style="width: 8%;float: left;">
					<span class="input-group-addon"><i class="icon icon-plus" style="cursor:pointer" onclick="addPro(this);"></i></span>
					<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="minusPro(this);"></i></span>
				</div>
				<div style="float: left;">&nbsp;&nbsp;</div>
				<div style="float: left; width: 45%;">
					<input class="form-control" name="" placeholder="属性名称:如颜色" style="width: 100%; height: 28px;">
				</div>
				<div style="float: left;">&nbsp;&nbsp;</div>
				<div style="float: left; width: 15%;">
					<input type="hidden" value=""/>
					<input class="form-control" name="" placeholder="排序值（数字）" style="width: 100%; height: 28px;" onblur="generateTab();" />
				</div>
				<div style="width: 1%; clear: both;"></div>
			</div>
			<div class="propertyValues" style="margin-left: 19%;">
			</div>
		</div>
	</div>
	<!-- 属性值 -->
	<div style="visibility: hidden;" id="propertyValueDivId">
		<div class="propertyValue" style=" margin-top: 1%;">
			<div class="input-group" style="width: 5%; float: left;">
				<span class="input-group-addon"><i class="icon icon-minus" style="cursor:pointer" onclick="minusProVal(this);"></i></span>
				<span class="input-group-addon"></span>
			</div>
			<div>
				<input class="form-control" name="" placeholder="属性值:如红色" style="width: 44%; height: 28px;" onblur="generateTab();">
			</div>
		</div>
	</div>
	<script type="text/javascript">contextPath = '${pageContext.request.contextPath}';</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/datapicker/WdatePicker.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/js/fresh/addfresh.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>

	<!-- ckeditor编辑器 -->
	<script src="<%=path%>/resources/ckeditor_4.5.6/ckeditor.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/config.js"></script>
	<script src="<%=path%>/resources/ckeditor_4.5.6/adapters/jquery.js"></script>
	
	<!-- 和配送城市的选择 -->
	<script src="<%=path%>/js/fresh/chosenCity.js"></script>
	<script type="text/javascript">
		var  classa= "${freshInfo.classa}";
		if(classa){
			var freshType= $("#"+classa);
			if(freshType.attr("id")){
				$("#dLabel").text(freshType.text()).val(freshType.attr("id")).attr("selected","selected");
			}
		}
		function confirmType(item){
			$("#dLabel").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
		}
		var freshPage="${freshPage}";
	</script>
</body>
</html>
