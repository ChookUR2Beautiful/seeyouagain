<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<!-- <div align="center">
	 <a class="btn btn-warning" href = "businessman/seller/init.jhtml" type="button" id="sellerReturnId"><i class="icon-remove-sign"></i>&nbsp;返回</a>		 
	</div> -->
	<!-- 编辑页面类型 1：商家列表页面；2：商家待审核页面；3：分店页面 -->
	<input type = "hidden" id ="editType" value = "${param.editType}">
	<!-- 用于查询分店列表的id -->
	<input type = "hidden" id ="fartherSellerId" value = "${param.fartherSellerId}">
		<div id="main" style="min-height: 903px;">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
					<form  id="sellerForm" role="form">
					<input type="hidden" id="selleridid" name="sellerid" value="${selleridList.sellerid}"/>
					<table class="table" style="text-align: center;" width="100%">
					<tr><td>
						<table class="table" style="text-align: center;" >
						<tr>
							<td class = "sellerTitleCss">
									<h5>商家名称:</h5> 
							</td>
							<td class="sellerContentCss">
							     <input type="text" class="form-control" name="sellername"  placeholder="商家名称" value="${selleridList.sellername}"<c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
							<td class = "sellerTitleCss">
									<h5>地址:</h5> 
							</td>
							<td colspan="4">
								 <input type="text" class="form-control" name="address"  placeholder="地址" value="${selleridList.address}"<c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
						</tr>
						<tr>
							<td class = "sellerTitleCss">
									<h5>区域:</h5> 
							</td>
							<td class="sellerContentCss">
								<div class="input-group" style="width:100%;" id="areaSelect" initValue="${selleridList.area}"<c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
								</div>
							</td>
							<td class = "sellerTitleCss">
									<h5>商圈:</h5> 
							</td>
							<td class="sellerContentCss">
								<select class="form-control"  id="zoneid" name="zoneid" initValue="${selleridList.zoneid}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
									<option value="">请先选择区域再选择商圈</option>
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
							    <select class="form-control" id="jointid" name="jointid" style="width:100%;" initValue="${selleridList.jointid}">
							    	<option value="">请先选择区域</option>
							    </select>
							</td>
							<td><h5>所属业务员:</h5></td>
							<td style="text-align: left">
							    <select class="form-control" id="staffid" name="staffid" style="width:100%;" initValue="${selleridList.staffid}">
							    	<option value="">请先选择合作商</option>
							    </select>
							</td>
							<td >
									<h5>连锁店:</h5> 
						   </td>
						   <td style="text-align: left">
							       <select class="form-control" id="fatherid" name ="fatherid" style = "width:90%;" initValue="${selleridList.fatherid}">
							       </select>
							       <input type="hidden" class="form-control" name="lssellername"   value="${selleridList.lssellername}">
						    </td>
							
						</tr>
						<tr>
							<td>
								<h5>联系人手机:</h5> 
							</td>
							<td>
								 <input type="hidden" class="form-control" name="oldPhoneid"  placeholder="联系人手机" value="${selleridList.phoneid}" >
								 <input type="text" class="form-control" name="phoneid"  placeholder="联系人手机" value="${selleridList.phoneid}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
							
							<td>
								<h5>联系电话:</h5> 
							</td>
							<td>
								 <input type="text" class="form-control" name="tel"  placeholder="联系电话" value="${selleridList.tel}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
							
							<td style="width:100px;">
									<h5>法人姓名:</h5> 
							</td>
							<td colspan="2">
								<input type="text" class="form-control" name="fullname" placeholder="法人姓名" value="${selleridList.fullname}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td> 
						</tr>
						<tr>
							<td>
									<h5>有效日期开始:</h5> 
							</td>
							<td >
								  <input type="text" class="form-control form-datetime" title="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>" name="svalidity" id="sjaddsvalidity" placeholder="开始日期" value="<fmt:formatDate value="${selleridList.svalidity}" pattern="yyyy-MM-dd HH:mm"/>"<c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
							
							<td >
								   <h5>有效日期结束:</h5>
							</td>
							<td >
								 <input type="text" class="form-control form-datetime"   name="evalidity" id="sjaddevalidity" placeholder="结束日期" value="<fmt:formatDate value="${selleridList.evalidity}" pattern="yyyy-MM-dd HH:mm"/>"<c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
							
							 <td >
									<h5>营业执照:</h5> 
							</td>
							<td >
								  <input type="text" class="form-control" name="licenseid"  placeholder="营业执照" value="${selleridList.licenseid}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
							</td>
						</tr>
						
						<tr>
							<td><h5>&nbsp;&nbsp;是否折上折：</h5></td> 
	 						<td style="text-align: center;">
	 							<h5>
		 							<input  name="agioAgio" value="1"type="radio" ${selleridList.agioAgio==1?'checked':''}><span style="font-size: 12px;" >是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input name="agioAgio" value="0" type="radio" ${selleridList.agioAgio==0?'checked':''}><span style="font-size: 12px;">否</span>
	 							</h5>
	 						</td>
						   
						    <td><h5>&nbsp;&nbsp;总部帮忙签约：</h5></td> 
	 						<td style="text-align: center;">
	 							<h5>
		 							<input  name="give" value="1"type="radio" ${selleridList.give==1?'checked':''}><span style="font-size: 12px;" >是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input name="give" value="0" type="radio" ${selleridList.give==0?'checked':''}><span style="font-size: 12px;">否</span>
	 							</h5>
	 						</td>
	 						
	 						<td><h5>&nbsp;&nbsp;美食星势力商户：</h5></td> 
	 						<td style="text-align: center;">
	 							<h5>
		 							<input  name="isforce" value="1" type="radio" ${selleridList.isforce==1?'checked':''}><span style="font-size: 12px;">是</span>
		 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 							<input name="isforce" value="0" type="radio" ${selleridList.isforce==0?'checked':''}><span style="font-size: 12px;">否</span>
	 							</h5>
	 						</td> 
						</tr>
						
						<tr>
						<tr>
							<td >
									<h5>折扣（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control"  placeholder="请输入0-95之间的数" name="baseagio"  value="${sellerAgio.baseagio}" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>
						    </td> 
						    <td >
									<h5>平台占比（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control" readonly="readonly" name="pledger"  value="${sellerAgio.pledger}">
						    </td> 
						    
						    <td >
									<h5>用户占比（%）:</h5> 
						    </td>
							<td >
								   <input type="text" class="form-control" readonly="readonly" name="yledger"  value="${sellerAgio.yledger}">
						    </td>
						   <%--  <td >
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
						    	<input type="text" class="form-control form-time" name="sdate1" style="width:45%;float:left;" value="${selleridList.sdate1}" />
						    	<span style="float:left;">&nbsp;~&nbsp;</span>
						    	<input type="text" class="form-control form-time" name="sdate2" style="width:45%;float:left;" value="${selleridList.sdate2}" />
						    </td>
						    
						</tr>
						
						<tr>
						   <td>
									<h5>连盟店:</h5> 
						   </td>
						   <td style="text-align: left">
							       <select class="form-control" id="allianceId" name ="allianceId" styl ="width:100%;" initValue="${selleridList.allianceId}">
							       </select>
						   </td>
						</tr>
						
						<!-- ===================================================================================================== -->
						
						<tr>
						<td >
									<h5>备注:</h5>
							</td>
							<td colspan = "10">
									<textarea name="remarks" rows="3" class="form-control" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>>${selleridList.remarks}</textarea>
							</td>
						</tr>
						
						
						<tr>
							<td>
									<h5>商家LOGO:</h5> 
							</td>
							<td>
								   <div class="col-md-9">
									  <div id='sellerLogoId' ImgValidate="true"></div>
									  <input type="hidden" id="url" name = "url" value = "${selleridList.url}"/>		
								   </div>
							</td>
						
							<td >
									<h5>营业执照正本附件:</h5> 
							</td>
							<td  >
								   <div class="col-md-9">
									  <div id='sellerHead2'></div>
									  <input type="hidden" id="licenseurlid" name = "licenseurl" value = "${selleridList.licenseurl}"/>		
								   </div>
							</td>
							
							<td >
									<h5>营业执照副本附件:</h5> 
							</td>
							<td >
								   <div class="col-md-9">
									   <div id='sellerHead3'></div>
									    <input type="hidden" id="licensefurlid" name = "licensefurl" value = "${selleridList.licensefurl}"/>
								   </div>
							</td>
							
						</tr>
						
						<tr>
							<td >
									<h5>商家图片:</h5> 
							</td>
							<td>
								   <div class="col-md-9">
									  <div id='sellerPicId' ImgValidate="true"></div>
									  <input type="hidden" id="picUrl" name = "picUrl" value = "${selleridList.picUrl}"/>		
								   </div>
							</td>
						
							<td >
									<h5>身份证附件正面:</h5> 
							</td>
							<td >
								   <div class="col-md-9">
									  <div id='identityzurldivid'></div>
									  <input type="hidden" id="identityzurleditid" name = "identityzurl" value = "${selleridList.identityzurl}"/>		
								   </div>
							</td>
	
							<td >
									<h5>身份证附件反面:</h5> 
							</td>
							<td >
								   <div class="col-md-9">
									   <div id='licensefurldivid'></div>
									    <input type="hidden" id="licensefurleditid" name = "identityfurl" value = "${selleridList.identityfurl}"/>
								   </div>
							</td>	
						</tr>
						
						<tr>
								<td >
										<h5>商户协议URL:</h5> 
								</td>
								<td colspan="2">
									   <div class="col-md-9">
										   <div id='agreementdivid' ImgValidate="true"></div>
										    <input type="hidden" id="agreementids" name = "agreement" value = "${selleridList.agreement}"/>
									   </div>
								</td>
						</tr>
						
						
						</table>
						</td>
						<td>
						
							<td rowspan="4"  style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
								<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
								<button class="btn btn-danger" type="submit" id="UpdateSavaSeller" <c:if test="${selleridList.status == 3}">disabled="disabled"</c:if>><i class="icon-save"></i>&nbsp;保存</button>&nbsp;
								<button class="btn btn-warning" type="reset" id=""><i class="icon-reply"></i>&nbsp;重置</button>
											        
							  </td>
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
						<td>
							<table class="table" style="text-align: center;">
								<tr>
									<td class = "sellerTitleCss">
											<h5>经度:</h5> 
									</td>
									<td class="sellerContentCss">
									<input type="hidden"  name="sellerid" value="${selleridList.sellerid}"/>
									<input type="hidden"  name="lid" id="lid" value="${sellerLandmarkList.lid}"/>
										 <input type="text" name = "longitude" class="form-control"   value = "${sellerLandmarkList.longitude}"> 
									</td>
									<td class = "sellerTitleCss">
											<h5>纬度:</h5> 
									</td>
									<td class="sellerContentCss">
										  <input type="text" name = "latitude" class="form-control"   value = "${sellerLandmarkList.latitude}">
									</td>	
									<td></td>					
								</tr>
								</table>
							</td>
						<td rowspan="2"  style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
								<br>
								<button class="btn btn-danger" type="submit" ><i class="icon-save"></i>&nbsp;保存</button>&nbsp;
								<button class="btn btn-warning" type="reset" id=""><i class="icon-reply"></i>&nbsp;重置</button>		        
						</td>
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
			   <form id="sellerDetailedForm">
					<table class="table" style="text-align: center;">
				<tr>
					<td>
					<table class="table" style="text-align: center;">
						<tr>
							<td class="sellerTitleCss">
									<h5>人均消费 （元）:</h5> 
							</td>
							<td class="sellerContentCss">
								 <input type="hidden" id="selleridid" name="sellerDetailed.sellerid" value="${selleridList.sellerid}"/>
								  <input type="text" class="form-control"  placeholder="人均消费" name="sellerDetailed.consume" value="${detailed.sellerDetailed.consume}">
								  
							</td>
							<td class = "sellerTitleCss">
									<h5>WIFI:</h5> 
							</td>
							<td class="sellerContentCss">
								<select class="form-control" name="sellerDetailed.iswifi" value="${detailed.sellerDetailed.iswifi}">
									<option value="">请选择</option>
					                <option value="0" <c:if test="${detailed.sellerDetailed.iswifi==0}">selected</c:if>>没有</option>
					                <option value="1" <c:if test="${detailed.sellerDetailed.iswifi==1}">selected</c:if>>免费提供</option>
					                <option value="2" <c:if test="${detailed.sellerDetailed.iswifi==2}">selected</c:if>>有偿提供</option>
				                </select>
							</td>
							<td class = "sellerTitleCss">
									<h5>停车位:</h5> 
							</td>
							<td class="sellerContentCss">
								<select class="form-control" name="sellerDetailed.isparking" value="${detailed.sellerDetailed.isparking}">
									<option value="">请选择</option>
					                <option value="0" <c:if test="${detailed.sellerDetailed.isparking==0}">selected</c:if>>没有</option>
					                <option value="1" <c:if test="${detailed.sellerDetailed.isparking==1}">selected</c:if>>免费提供</option>
					                <option value="2" <c:if test="${detailed.sellerDetailed.isparking==2}">selected</c:if>>有偿提供</option>
				                </select>
							</td>
							
						</tr>
						
						<tr>
						<td class = "sellerTitleCss">
									<h5>参考地标:</h5> 
							</td>
							<td  colspan = "9">
								 <input type="text" class="form-control"  placeholder="参考地标" name="sellerDetailed.landmark" value="${detailed.sellerDetailed.landmark}">
							</td>
						</tr>
						
						<tr>
						
						<tr>
							<td class = "sellerTitleCss" >
									<h5>商家推荐:</h5> 
									<h5><font color="red">（限定10个,多个用";"号隔开）</font></h5> 
							</td>
							<td   colspan = "9">
									<textarea name="sellerDetailed.dishes" rows="3" class="form-control" placeholder="限定10个,多个用';'号隔开">${detailed.sellerDetailed.dishes}</textarea>
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
									<input name="bewrite" type="text" class="form-control" placeholder="图片描述" style="width:100px"/>
									<div class="pic"></div>
								</div>
								<div id="sellerPics" style="float : left;">
								<c:forEach items="${detailed.sellerPics}" varStatus="status" var="pic">
									<div style="float : left;text-align: center;">
										<button class="btn btn-warning removebtn" type="button" ><i class="icon-remove"></i></button>
										<input name="sellerPics[${status.index }].picid" type="hidden" value="${pic.picid}"/>
										<input name="sellerPics[${status.index }].picurl" type="hidden" value="${pic.picurl}" id="sellerPicUrl${status.index}"/>
										<input name="sellerPics[${status.index }].bewrite" type="text" value="${pic.bewrite}" class="form-control" placeholder="图片描述" style="width:100px"/>
										<div class="pic"></div>
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
									<textarea name="sellerDetailed.introduce" rows="10" class="form-control" placeholder="商家介绍最多300字">${detailed.sellerDetailed.introduce}</textarea>
							</td>
						</tr>
						
						</table>
						</td>
						<td rowspan="2"  style="text-align:center; margin:0 auto;width:200px;border-left: 2px solid #e5e5e5;">
								<br><br><br><br><br><br><br><br>
								<button  class="btn btn-danger" type="submit" id="sellerDetailedUpdate" ><i class="icon-save"></i>&nbsp;保存</button>&nbsp;
								<button class="btn btn-warning" type="reset" id="removeAccountInfo"><i class="icon-reply"></i>&nbsp;重置</button>		        
						</td>
						</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</div>
	
	
	<div tabindex="-1" class="modal fade in msg-model" data-position="100px" role="dialog" aria-hidden="true" aria-labelledby="mySmallModalLabel" style="display: none;">
		    <div class="modal-dialog modal-sm">
		      <div class="modal-content">
		        <div class="modal-header">
		          <button class="close" type="button" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
		          <h4 class="modal-title" id="mySmallModalLabel">系统提示</h4>
		        </div>
		        <div class="modal-body text-center" id="sysmsg">
		        </div>
		        <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal" id="close-modal-sm">关闭
		            </button>
		         </div>
		      </div>
		    </div>
  	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/js/businessman/editSellerPending.js"></script> 
    <script src="<%=path%>/js/common/IDCard.js"></script>
    <script src="<%=path%>/js/businessman/commonAgio.js"></script> 

</body>
</html>
