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
	<!-- <div align="center">
	 <a class="btn btn-warning" href = "businessman/seller/init.jhtml" type="button" id="sellerReturnId"><i class="icon-remove-sign"></i>&nbsp;返回</a>		 
	</div> -->
	<!-- 编辑页面类型 1：商家列表页面；2：商家待审核页面；3：分店页面 -->
	<input type = "hidden" id ="editType" value = "${param.editType}">
	<!-- 用于查询分店列表的id -->
	<input type = "hidden" id ="fartherSellerId" value = "${param.fartherSellerId}">
	<dir></dir>
	<div id="main" style="min-height: 903px;">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form action=""  role="form">
					<table class="table" style="text-align: center;">
					<input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
				 <tr><td>
						<table class="table" style="text-align: center;">
						<tr>
							<td class = "sellerTitleCss">
									<h5>商家名称:</h5> 
							</td>
							<td class="sellerContentCss">
								 <input disabled="disabled" type="text" name = "sellername" class="form-control"  value = "${selleridList.sellername}"> 
							</td>
							<td class = "sellerTitleCss">
									<h5>地址:</h5> 
							</td>
							<td colspan="3">
								  <input disabled="disabled" type="text" class="form-control" name="address"  value="${selleridList.address}">
							</td>
						</tr>
						
						<tr>
							<td class = "sellerTitleCss">
									<h5>区域:</h5> 
							</td>
							<td class="sellerContentCss">
								<div class="input-group" style="width:100%;" id="areaSelect" initValue="${selleridList.area}"></div>
							</td>
							
							<td class = "sellerTitleCss">
								<h5>商圈:</h5> 
							</td>
							<td class="sellerContentCss">
								<select class="form-control" disabled id="zoneid" name="zoneid" initValue="${selleridList.zoneid}">
									<option value="">暂无数据</option>
								</select>
							</td>
							
							<td class = "sellerTitleCss"><h5>经营行业：</h5></td>
							<td class="sellerContentCss">
								<input type="hidden" name="typename" value="${selleridList.typename}"/>
								<input type="hidden" name="tradename" value="${selleridList.tradename}"/>
								<div class="input-group" id="tradeSelect" style="width : 100%" initValue="${selleridList.genre}"></div>
							</td>
							
						</tr>
						
						<tr>
							<td><h5>所属合作商:</h5></td>
							<td style="text-align: left">
							    <select class="form-control" id="jointid" name="jointid" style="width:100%;" initValue="${selleridList.jointid}" disabled="disabled">
							    	<option value="">请先选择区域</option>
							    </select>
							</td>
							<td><h5>所属业务员:</h5></td>
							<td style="text-align: left">
							    <select class="form-control" id="staffid" name="staffid" style="width:100%;" initValue="${selleridList.staffid}" disabled="disabled">
							    	<option value="">请先选择合作商</option>
							    </select>
							</td>
							<td >
									<h5>连锁店:</h5> 
						   </td>
						   <td style="text-align: left">
							       <select class="form-control" id="fatherid" name ="fatherid" styl ="width:100%;" initValue="${selleridList.fatherid}" disabled="disabled">
							       </select>
							       <input type="hidden" class="form-control" name="lssellername" value="${selleridList.lssellername}">
						    </td>
						</tr>
						
						<tr>
							<td>
									<h5>联系人号码:</h5> 
							</td>
							<td >
								 <input disabled="disabled" type="text" class="form-control" name="phoneid"  value="${selleridList.phoneid}">
							</td>
							
							<td>
								<h5>联系电话:</h5> 
							</td>
							<td>
								 <input disabled="disabled" type="text" class="form-control" name="tel"  placeholder="联系电话" value="${selleridList.tel}">
							</td>
							
							<td>
									<h5>法人姓名:</h5> 
							</td>
							<td>
								  <input disabled="disabled" type="text" class="form-control" name="fullname"  value="${selleridList.fullname}">
							</td>
						</tr>
						<tr>
							<td>
									<h5>有效开始日期:</h5> 
							</td>
							<td>								 
								   <input disabled="disabled" type="text" class="form-control form-datetime" title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>" name="svalidity"  value="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>">
							</td>
							<td>
								  <h5>有效结束日期:</h5> 
							</td>
							<td>								 
								  <input disabled="disabled" type="text" class="form-control form-datetime" title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>" name="evalidity"  value="<fmt:formatDate value="${selleridList.evalidity}" pattern="yyyy-MM-dd HH:mm"/>">
							</td>
							
							
							<td>
									<h5>营业执照:</h5> 
							</td>
							<td>
								   <input disabled="disabled" type="text" class="form-control" name="licenseid"  value="${selleridList.licenseid}">
							</td>
						</tr>
						
						<tr>
						        <td><h5>&nbsp;&nbsp;是否折上折：</h5></td> 
		 						<td style="text-align: center;">
		 							<h5>
			 							<input disabled="disabled" name="agioAgio" value="1"type="radio" ><span style="font-size: 12px;" >是</span>
			 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 							<input disabled="disabled" name="agioAgio" value="0" type="radio" checked="checked"><span style="font-size: 12px;">否</span>
		 							</h5>
		 						</td>
						   
							<td><h5>&nbsp;&nbsp;总部帮忙签约</h5></td> <%-- type="radio" <c:if test="${selleridList.give==1}">checked</c:if> --%>
	 						<td style="text-align: center;">
	 							<input disabled="disabled" name="give" value="1"type="radio" ${selleridList.give==1?'checked':''} ><span style="font-size: 12px;width: 60px;">是</span>
	 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 							<input disabled="disabled" name="give" value="0" type="radio" ${selleridList.give==0?'checked':''} ><span style="font-size: 12px;">否</span>
	 						</td>
	 						
							<td><h5>&nbsp;&nbsp;是否是美食星势力商户</h5></td> 
	 						<td style="text-align: center;">
	 							<input  name="isforce" value="1" type="radio" ${selleridList.isforce==1?'checked':''} disabled="disabled" ><span style="font-size: 12px;width: 60px;">是</span>
	 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 							<input name="isforce" value="0" type="radio" ${selleridList.isforce==0?'checked':''} disabled="disabled"><span style="font-size: 12px;">否</span>
	 						</td>
						</tr>
					
						<tr>
							<td >
									<h5>折扣（%）:</h5> 
						    </td>
							<td >
								    <input type="hidden" class="form-control"  name="aid"  value="${sellerAgio.aid}">
								   <input type="text" class="form-control" readonly="readonly" placeholder="折扣数为0.01-0.99之间的小数" name="baseagio"  value="${sellerAgio.baseagio}">
						    </td> 
						    
						    <td>
									<h5>平台占比（%）:</h5> 
						    </td>
							<td>
								   <input type="text" class="form-control" readonly="readonly" name="pledger"  value="${sellerAgio.pledger}">
						    </td> 
						    
						    <td>
									<h5>用户占比（%）:</h5> 
						    </td>
							<td>
								   <input type="text" class="form-control" readonly="readonly" name="yledger"  value="${sellerAgio.yledger}">
						    </td> 
						    <%-- <td >
									<h5>平台补贴（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control"  name="flatAgio"  value="${selleridList.flatAgio}">
						    </td>  --%>
						    
						</tr>
						<!-- 折扣信息=================================================================================================== -->
						<tr>
						    <td >
									<h5>营业收入（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control"  readonly="readonly" name="income"  value="${sellerAgio.income}">
						    </td> 
						    
						    <td >
									<h5>商户占比（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control" readonly="readonly" name="sledger"  value="${sellerAgio.sledger}">
						    </td>
						    <td><h5>营业时间：</h5></td>
						    <td>
						    	<input type="text" class="form-control form-time" name="sdate1" style="width:45%;float:left;" value="${selleridList.sdate1}" disabled/>
						    	<span style="float:left;">&nbsp;~&nbsp;</span>
						    	<input type="text" class="form-control form-time" name="sdate2" style="width:45%;float:left;" value="${selleridList.sdate2}" disabled/>
						    </td>
						</tr>
						
						<tr>
						   <td>
									<h5>连盟店:</h5> 
						   </td>
						   <td style="text-align: left">
							       <select class="form-control" id="allianceId" name ="allianceId" styl ="width:100%;" initValue="${selleridList.allianceId}" disabled="disabled">
							       </select>
						   </td>
						</tr>
						<!-- 折扣信息=================================================================================================== -->
												
						<tr>
						<td >
									<h5>审批说明:</h5> 
							</td>
							<td colspan = "10">
									<textarea disabled="disabled" name="examineinfo" rows="3" class="form-control">${selleridList.examineinfo}</textarea>
							</td>
						</tr>
						
						<tr>
						<td >
									<h5>备注:</h5>
							</td>
							<td colspan = "10">
									<textarea disabled="disabled" name="remarks" rows="3" class="form-control" >${selleridList.remarks}</textarea>
							</td>
						</tr>

						<tr>		
							<td ><h5>商家LOGO:</h5> </td>							
							<td >	
								<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"  rel="slide" title="商家LOGO" class="fancybox"  id="logo">
									<img alt="商家LOGO"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.url}"   style="width: 100px;height: 100px;">			
								</a>
							</td> 
									
							<td><h5>营业执照正本附件:</h5> </td>							
							<td>	
								<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licenseurl}"  rel="slide" title="营业执照正本附件" class="fancybox"  id="yyzzzm">
									<img alt="营业执照正本附件"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licenseurl}"   style="width: 100px;height: 100px;">			
								</a>

							</td> 
						
						    <td><h5>营业执照副本附件:</h5> </td>
							<td>
							   <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" rel="slide" title="营业执照副本附件"  class="fancybox"  id="yyzzfm">
							  	  <img alt="营业执照副本附件" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.licensefurl}" style="width: 100px;height: 100px;">   
							   </a>
							</td> 				
						</tr>
						
						<tr>
							<td>
								<h5>商家图片:</h5> 
							</td>
							<td >	
								<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.picUrl}"  rel="slide" title="商家图片" class="fancybox"  id="sellerPic">
									<img alt="商家图片"  tabIndex="-3" src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.picUrl}"   style="width: 100px;height: 100px;">			
								</a>
							</td> 
						</tr>
	
						<tr>
							<td><h5>身份证附件正面:</h5> 
							</td>
							<td>
							   <a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityzurl}" rel="slide" title="身份证附件正面"  class="fancybox" id="sfzzm" >	 
									 <img alt="身份证附件正面" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityzurl}" style="width: 100px;height: 100px;">							
							   </a>
							</td>
							
							<td>
									<h5>身份证附件反面:</h5> 
							</td>
							<td >
								<a  href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityfurl}" rel="slide" title="身份证附件反面" class="fancybox" id="sfzfm" >	 
								    <img alt="身份证附件反面" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identityfurl}" style="width: 100px;height: 100px;">													 
							    </a>
							</td> 
							
							<td>
								<h5>手持身份证正面照:</h5> 
							</td>
							<td >
								<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identitynurl}" rel="slide" title="手持身份证正面照" class="fancybox" id="scsfzzmz" >	 
								    <img alt="手持身份证正面照" class="image_gall"src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.identitynurl}" style="width: 100px;height: 100px;">													 
							    </a>
							</td> 
						</tr>
						
						<tr>
								<td>
									<h5>商户协议URL:</h5> 
								</td>
								<td text-align: left;" >
	 							 	<input  type="hidden" class="form-control" name="agreement"   value="${selleridList.agreement}"> 
	 							 	<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${selleridList.agreement}" target="_Blank" style="text-align: left;">${selleridList.agreement}</a>							
								</td>
						</tr>
				
						</table>
						</td>											
						</tr></table>
					</form>
				</div>
			</div>
		</div>
		
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">商家经纬度信息</div>
				<div class="panel-body">
					<form action="" id = "sellerLandmarkFromId">
								<table class="table" style="text-align: center;">
										<tr>
											<td class = "sellerTitleCss">
													<h5>经度:</h5> 
											</td>
											<td class="sellerContentCss">
											<input type="hidden"  name="sellerid" value="${selleridList.sellerid}"/>
											<input type="hidden"  name="lid" id="lid" value="${sellerLandmarkList.lid}"/>
												 <input type="text" disabled="disabled" name = "longitude" class="form-control"  placeholder="经度" value = "${sellerLandmarkList.longitude}"> 
											</td>
											<td class = "sellerTitleCss">
													<h5>纬度:</h5> 
											</td>
											<td class="sellerContentCss">
												  <input type="text" disabled="disabled" name = "latitude" class="form-control"  placeholder="纬度" value = "${sellerLandmarkList.latitude}">
											</td>
											<td></td>							
										</tr>
									</table>
					 </form>
				</div>
			</div>
		</div>
		
		
		<div class="example" >
			<div class="panel panel-primary">
				<div class="panel-heading">详细信息</div>
				<div class="panel-body">
					<form action="">					
					<table class="table" style="text-align: center;">
				<tr>
					<td>
					<table class="table" style="text-align: center;">
						<tr>
							<td class = "sellerTitleCss">
									<h5>人均消费(元):</h5> 
							</td>
							<td class="sellerContentCss">
								  <input disabled="disabled" type="text" class="form-control"  name="consume" value="${detailed.sellerDetailed.consume}">								
							   
							</td>
													
							<td class = "sellerTitleCss">
									<h5>WIFI:</h5> 
							</td>
							<td class="sellerContentCss">
							<select class="form-control" name="isparking" value="${detailed.sellerDetailed.iswifi}" disabled="disabled">
								<c:if test="${detailed.sellerDetailed.iswifi==0}">
									<option value="0" selected="selected">没有</option>
								</c:if>
								<c:if test="${detailed.sellerDetailed.iswifi==1}">
									<option value="1" selected="selected">免费提供</option>
								</c:if>
								<c:if test="${detailed.sellerDetailed.iswifi==2}">
									<option value="2" selected="selected">有偿提供</option>
								</c:if>
					            </select>
							</td>
							
							<td class = "sellerTitleCss">
									<h5>停车位:</h5> 
							</td>
							<td class="sellerContentCss">
								<select class="form-control" name="isparking" value="${detailed.sellerDetailed.isparking}" disabled="disabled">
								<c:if test="${detailed.sellerDetailed.isparking==0}">
									<option value="0" selected="selected">没有</option>
								</c:if>
								<c:if test="${detailed.sellerDetailed.isparking==1}">
									<option value="1" selected="selected">免费提供</option>
								</c:if>
								<c:if test="${detailed.sellerDetailed.isparking==2}">
									<option value="2" selected="selected">有偿提供</option>
								</c:if>
					            </select>
							</td>
												
						</tr>
						
						<tr>
							<td class = "sellerTitleCss">
									<h5>参考地标:</h5> 
							</td>
							<td class="sellerContentCss" colspan = "9">
								 <input disabled="disabled" type="text" class="form-control" name="landmark" value="${detailed.sellerDetailed.landmark}">
							</td>
						</tr>
												
						
						<tr>
							<td class = "sellerTitleCss">
									<h5>商家推荐:</h5> 
									
									<h5><font color="red">限定10个,多个用";"号隔开</font></h5> 
							</td>
							<td colspan="6"  >
									<textarea disabled="disabled" name="dishes" cols="20" rows="3" class="form-control" >${detailed.sellerDetailed.dishes}</textarea>
							</td>
						</tr>
						
						<tr>
							<td class="sellerTitleCss">
									<h5>商家环境图片:</h5> 
							</td>
							
							<td colspan="9" align="left">
								<div id="sellerPics" style="float : left;">
								<c:forEach items="${detailed.sellerPics}" varStatus="status" var="pic">
									<div style="float : left;text-align: center;margin: 5px">
										<span style="width:100px; font-size:15px;" title="${pic.bewrite}">${fn:substring(pic.bewrite,0,5)}</span><br/>
										<a href="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${pic.picurl}" rel="slide" title="商家环境图片"  class="fancybox" id="sfzzm" >	 
										    <img alt="商家环境图片" class="image_gall"  src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${pic.picurl}" style="width: 100px;height: 100px;">							
						  				</a>
									</div>
								</c:forEach>
								</div>
							</td>
						</tr>
						
						<tr>
							<td class = "sellerTitleCss">
									<h5>商家介绍:</h5> 
									
									<h5><font color="red">商家介绍最多300字</font></h5> 
							</td>
							<td  colspan="6" >
									<textarea disabled="disabled" name="introduce" cols="20" rows="6" class="form-control" >${detailed.sellerDetailed.introduce}</textarea>
							</td>
						</tr>			
						</table>
						</td>
						</tr>
						</table>
						
					</form>
				</div>							
			</div>
				<div align="center">
				  <input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
				 <button class="btn btn-success" type="button" id="auditYes" ><i class="icon-ok"></i>&nbsp;审核通过</button>&nbsp;
				 <button class="btn btn-danger" type="button" id="auditNo"><i class="icon-remove-sign"></i>&nbsp;审核不通过</button>		 
				</div>

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
			
			var sellerLogoId='${selleridList.url}';//正面执照电子版URL
			if( sellerLogoId==""){
				$("#logo").hide();				
			}else{
				$("#logo").show();		
			} 
		 
	});    
  </script>
   <script src="<%=path%>/js/businessman/sellerPendingView.js"></script> 
   
</body>
</html>
