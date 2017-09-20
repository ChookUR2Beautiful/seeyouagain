<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"	rel="stylesheet">
<!-- <link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> -->
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">

	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	 <script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	

<!-- 	<script src="<%=path%>/resources/web/js/jquery-1.11.1.min.js"></script> -->
	<script src="<%=path%>/ux/js/jquery.validate.js"></script>
	<script src="<%=path%>/ux/js/jquery.validate.additional-methods.js"></script>
	<script src="<%=path%>/ux/js/jquery.validate.messages_zh.js"></script>
	<script src="<%=path%>/resources/web/js/popoverUtil.js"></script>
	<script src="<%=path%>/resources/web/js/util.js"></script>
	 
	<script src="<%=path%>/js/businessman/vipCard/addVipCard.js"></script> 
<script type="text/javascript">
function retailBlur(retail){
	if($(retail).val()==''){
		submitDataError($(retail),"到账金额不能为空");
		return;
	}else{
	submitDatasuccess($(retail));
	}
}
function priceBlur(price){
	if($(price).val()==''){
		submitDataError($(price),"充值金额不能为空");
		return;
	}else{
	submitDatasuccess($(price));
	}
}

</script>
	
<style type="text/css">
	body,table,tbody,td,th,tr,div,ul,li,h1,h2,h3,h4,h5{
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
		padding:40px 0;
	}
	ul li a{
		font-size:16px;
	}
	.warn{
		color:red;
		font-weight:bold;
	}
</style>

</head>

<body>
	<div class="content">

		<div class="panel panel-default myPanel">

			<form class="form-horizontal" role="form" id="editVipCardInfo">
				<input type="hidden"   id = "isType" value="${isType}">
				<input type="hidden" id="fastfdsHttp"  value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" >
				<input type="hidden" name="id" value="${vipCardBean.id}">
				<input type="hidden" name="dstatus" value="0">
				<input type="hidden" name="sellerName" id="sellerName" value="${reqVipCardBean.sellerName}">
				<div>
					
					<div class="form-group" >
						<label for="cardLogo" class="col-sm-2 control-label" style="margin-top:70px;" >卡Logo：</label>
						<div class="col-sm-6">
						  <input type="hidden" class="form-control" id="sellerLogo" name="sellerLogo" value="${reqVipCardBean.sellerLogo}">
								<div id="logoUpload"></div>
						 </div>
					</div>
					<div class="form-group">
				      <label for="cardName" class="col-sm-2 control-label">卡名称：</label>
				      <div class="col-sm-6">
				         <input type="text" class="form-control" id="cardName" name="cardName" placeholder="必胜客至尊会员卡" value="${reqVipCardBean.cardName}">
				      </div>
				   </div>
					
					<c:if test="${!empty reqVipCardBean.sellerId}">
		               <input type="hidden" id="sellerId" name="sellerId" value="${reqVipCardBean.sellerId}">
					</c:if>
					<c:if test="${empty reqVipCardBean.sellerId}">
					   <div class="form-group">
							<label class="col-sm-2 control-label" for="sellerid" id="sID">适用商户：</label>
	  						<div class="col-sm-6">
								<select class="form-control" name="sellerId" id="sellerId">
									<option value="" >--请选择商户--</option>
								</select>
							</div>					
		               </div>
	                </c:if>
	                				   
	                <div class="form-group">
						<label for="childSeller" class="col-sm-2 control-label">适用门店：<c:if test="${isType=='add'}"><span class="warn">(请先选择适用商户)</span></c:if></label>
						<div class="col-sm-6" id="pollShop">
							<input type="text" class="form-control" name="childSeller" id="childSeller">
						</div>
				   </div>
									
				   <div class="form-group">
				      <label for="msellerName" class="col-sm-2 control-label">是否开启充值功能：</label>
				      <div class="col-sm-6">
				         <label>
						<input type="radio" name="isPay" id="ryes" value="0" ${reqVipCardBean.isPay==0?"checked":""} <c:if test="${empty reqVipCardBean.isPay}">checked</c:if>>
						是</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
						<input type="radio" name="isPay" id="rno" value="1" ${reqVipCardBean.isPay==1?"checked":""}  >
						否</label>
				      </div>
				   </div>
					<div class="form-group" id="dates">
						<label class="col-sm-2 control-label" for="plandiv">充值方案：</label>
						<div class="col-sm-6 plandiv" id="plandiv">
								<c:if test="${empty reqVipCardBean.planList}">
								<div class="input-group" >
									<span	class="input-group-addon">充值：</span> 
									<input  type="text"	name="planList[0].price" id="" class="form-control form-date issueVolume price" onblur="priceBlur(this)"/>
									<span	class="input-group-addon">到账：</span> 
									<input  type="text"	name="planList[0].retail" id="" class="form-control form-date issueVolume retail" onblur="retailBlur(this)"/>
									<span	class="input-group-addon inputRadio"><input  type="radio" name="defaultPlan" id="" class="issueDefault" value="0" checked="checked"}>默认</span>
									<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
									<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
									</span>
									</div>
								</c:if>
								<c:if test="${!empty reqVipCardBean.planList}">
									<c:forEach items="${reqVipCardBean.planList}" var="plan" varStatus="status">
									<div class="input-group" >
										<span	class="input-group-addon">充值：</span> 
										<input  type="text"	name="planList[${status.index}].price" id="" class="form-control form-date issueVolume price" onblur="priceBlur(this)" value="${plan.price}" >
										<span	class="input-group-addon">到账：</span> 
										<input  type="text"	name="planList[${status.index}].retail" id="" class="form-control form-date issueVolume retail" onblur="retailBlur(this)" value="${plan.retail}">
										<span	class="input-group-addon inputRadio"><input  type="radio" name="defaultPlan" id="" class="issueDefault" value="${status.index}" ${plan.isDefault==1?"checked":""}>默认</span>
										<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus" style="cursor:pointer"></i></span>
										<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
										</span>
										</div>
									</c:forEach>
								</c:if>
							</div>	
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">会员特权：</label>
						<div class="col-sm-6">
							<textarea class="form-control " rows="2" name="rights">${reqVipCardBean.rights}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">状态：</label>
						<div class="col-sm-6">
							<label>
							<input type="radio" name=cardStatus  value="1" ${reqVipCardBean.cardStatus==1?"checked":""} <c:if test="${empty reqVipCardBean.cardStatus}">checked</c:if>>
							上线</label>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<label>
							<input type="radio" name="cardStatus" value="2" ${reqVipCardBean.cardStatus==2?"checked":""}>
							下线</label>
						</div>
					</div>
					<div align="center">
						<table>
							<tr>
							<td width="160">
							<button class="btn btn-danger" type="submit" id="saveVipCard" ><span class="glyphicon glyphicon-floppy-disk"></span> &nbsp;保存</button>&nbsp;&nbsp;
							<td width="160">
							<a class="btn btn-warning" href="businessman/vipCard/listView.jhtml?"><span class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
							</td>
							</tr>
						</table>
					</div>
				</div>


			</form>
		</div>
	</div>
	
	<div class="hidden dateTemp">
		<div class="input-group">
			<span	class="input-group-addon">充值：</span> 
			<input  type="text"	name="planList[index].price" id="" class="form-control form-date issueVolume price" onblur="priceBlur(this)"/>
			<span	class="input-group-addon">到账：</span> 
			<input  type="text"	name="planList[index].retail" id="" class="form-control form-date issueVolume retail" onblur="retailBlur(this)"/>
			<span	class="input-group-addon inputRadio"><input  type="radio" name="defaultPlan" id="" class="issueDefault" value="index"/>默认</span>
			<span	class="input-group-addon"><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>
			<span class="input-group-addon"><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i>
			</span>
		</div>
	</div>
	
	
		
	<!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	
	
</body>
</html>
