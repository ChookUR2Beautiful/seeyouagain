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
<title>添加合作商信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<jsp:include page="../common.jsp"></jsp:include>
<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script> 
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/js/business_cooperation/addJoint.js?v=1.0.1"></script>
<script src="<%=path%>/js/common/IDCard.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script> 
<script type="text/javascript">
    alist = '${areaList}'; 
</script>

<style type="text/css">
td {
	border-bottom: none!important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary" style="border-bottom: 0px;">
			    <input type="hidden" id="isType" value="${isType}">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body">
				<form  id="editJointForm" role="form">
				    <input type="hidden"  id ="jointid" name="jointid" value="${joint.jointid}">
			   		<input type="hidden"  id="staffid" name="staffid" value="${staff.staffid}">
			   		<input type="hidden"  name="status" value="1">
			   		<input type="hidden"  name="jointToken" value="${jointToken}">
						<table class="table" style="text-align: center;" >
						<tr>
							<td class="sellerTitleCss">
								<h5>企业名称:</h5> 
							</td>
							<td class="sellerContentCss">
								 <input type="text" class="form-control" name="corporate"  value="${joint.corporate}">
							</td>
							
							<td class="sellerTitleCss">
									<h5>法人姓名：</h5> 
							</td>
							<td class="sellerContentCss">
								  <input type="text" class="form-control" name="legalperson"   value="${joint.legalperson}">
							</td>
							<td class="sellerTitleCss">
									<h5>身份证号：</h5> 
							</td>
							<td class="sellerContentCss">
								   <input  type="text" class="form-control" name="idnumber"  value="${joint.idnumber}">
						   </td>
						</tr>
						<tr>
							<td>
								<h5>合作商联系手机:</h5> 
							</td>
							<td>
							     <input type="hidden" class="form-control" name="oldphoneid"  value="${joint.phoneid}">
								 <input type="text" class="form-control" name="phoneid"  value="${joint.phoneid}">
							</td>
							
							<td >
									<h5>经办人：</h5> 
							</td>
							<td>
								  <input type="text" class="form-control" name="applicant"   value="${joint.applicant}">
							</td>
							<td>
									<h5>档案编号：</h5> 
							</td>
							<td >
								   <input  type="text" class="form-control" name="number"  value="${joint.number}">
						   </td>
						</tr>
						
						<tr>
							<td>
								<h5>公司地址:</h5> 
							</td>
							<td>
								 <input type="text" class="form-control" name="address"  value="${joint.address}">
							</td>
							
							<td >
									<h5>所属行业：</h5> 
							</td>
							<td>
								<div class="input-group" id="tradeSelect" style="width : 100%" initValue="${joint.industry}"></div>
								  <%-- <input type="text" class="form-control" id="industry" name="industry"   value="${joint.industry}"> --%>
							</td>
							<td>
									<h5>已启动商圈数：</h5> 
							</td>
							<td>
								   <input  type="text" class="form-control" name="startnum"  value="${joint.startnum}">
						   </td>
						</tr>
						<tr>
							<td><h5>saas总套数:</h5></td>
							<td>
								<input type="text" class="form-control" id="saasnum" name="saasnum" placeholder="请输入saas总套数" value="${joint.saasnum}">
							</td>
							<td><h5>sass折扣:</h5></td>
							<td>
								<input type="text" class="form-control" id="saasagio" name="saasagio" placeholder="请输入sass折扣(如:0.75)" value="${joint.saasagio}">
							</td>
							<td><h5>签约总额:</h5></td>
							<td>
								<input type="text" class="form-control" id="saasamount" name="saasamount" placeholder="请输入签约总额:" value="${joint.saasamount}">
							</td>
						</tr>
						<tr>
							<td>
									<h5>执照编号：</h5> 
							</td>
							<td >
								   <input  type="text" class="form-control" name="license"  value="${joint.license}">
						   </td>
						   
						   <td>
								<h5>员工分账比例:</h5> 
							</td>
							<td>
								 <input type="text" class="form-control" name="percentage" placeholder="请输入0-0.99之间的小数（保留2位）" value="${joint.percentage}">
							</td>
						</tr>
						<tr>
							<td >
									<h5>区域:</h5>
									
							</td>
							
							<td colspan="3" style="text-align: left;">
								<div class="input-group" id="areaInfo" style="width: 80%;float:left;" initValue="${joint.area}"></div>
								<h5><font style="color: red;float:left;">(区域可多选)</font></h5>
							</td>
						</tr>
						<tr>
							<td><h5>身份证附件：</h5></td>
							<td><input type="hidden" class="form-control" id="idnumberurl" name="idnumberurl"  value="${joint.idnumberurl}">
							<div id="idnumberurlImg"></div>
						   </td>
						   <td><h5>营业执照附件：</h5></td>
							<td><input type="hidden" class="form-control" id="licenseurl" name="licenseurl"  value="${joint.licenseurl}">
							<div id="licenseurlImg"></div>
						   </td>
						</tr>
					</table>
					<hr>
					<!-- 合作商家经纬度信息=============================================================================== -->
					<input type="hidden" name="jointLandmarkToken" value="${jointLandmarkToken}">
					<input type="hidden" name="jointid" value="${jointLandmarkList.jointid}">
					<input type="hidden" name="lid" value="${jointLandmarkList.lid}"/>
					<table class="table" style="text-align: center;">
						<tr>
							<td class="sellerTitleCss">
								<h5>经度:</h5>
							</td>
							<td class="sellerContentCss">
								<input type="text" name="longitude" class="form-control" value="${jointLandmarkList.longitude}" style="width: 92%;">
							</td>
							<td align="left">
								<h5 style="color: red;"><font>请使用高德地图经度（范围 : 73.240&lt;经度&lt;135.150）</font></h5>
							</td>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td class="sellerTitleCss">
								<h5>纬度:</h5>
							</td>
							<td class="sellerContentCss">
								<input type="text" name="latitude" class="form-control" value="${jointLandmarkList.latitude}" style="width: 92%;">
							</td>
							<td align="left">
								<h5 style="color: red;"><font>请使用高德地图纬度（范围 : 3.3120&lt;纬度&lt;53.1980）</font></h5>
							</td>
							<td colspan="3"></td>
						</tr>
					</table>
					<hr>
					<!-- 帐号信息=============================================================================== -->
					<table class="table" style="text-align: center;">
						<tr>
							<td class="sellerTitleCss">
								<h5>登录账号:</h5> 
							</td>
							<td class="sellerContentCss">
								 <input type="hidden" class="form-control" name="oldId"  value="${staff.phoneid}">
								 <input type="text" class="form-control" name="account"  value="${staff.phoneid}">
							</td>
							
							<td class="sellerTitleCss">
									<h5>原始登录密码：</h5> 
							</td>
							<td class="sellerContentCss">
								  <input type="password" class="form-control" name="password">
							</td>
							<td class="sellerTitleCss">
									<h5>姓名：</h5> 
							</td>
							<td class="sellerContentCss">
								  <input type="text" class="form-control" name="fullname"   value="${staff.fullname}">
							</td>
						</tr>
						<tr>
							<td>
								<h5>昵称:</h5> 
							</td>
							<td>
								 <input type="text" class="form-control" name="nickname"  value="${staff.nickname}">
							</td>
							
							<td >
									<h5>绑定手机IMEI：</h5> 
							</td>
							<td>
								  <input type="text" class="form-control" name="imei"   value="${staff.imei}">
							</td>
							<td><h5>性别:</h5></td> 
	 						<td style="text-align: center;">
	 							<input  name="sex" value="1" type="radio" ${staff.sex==1?"checked":""} <c:if test="${empty staff.sex}">checked</c:if> ><span style="font-size: 12px;width: 60px;">男</span>
	 							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 							<input name="sex" value="2" type="radio" ${staff.sex==2?"checked":""} ><span style="font-size: 12px;">女</span>
	 						</td>
						</tr>
						
						<!-- headurl,头像URL -->
						<tr>
							<td><h5>头像：</h5></td>
							<td><input type="hidden" class="form-control" id="headurl" name="headurl"  value="${staff.headurl}">
							<div id="headurlImg"></div>
						   </td>
						</tr>
						<tr>
							<td style="width:80px;" >
									<h5>备注：</h5> 
							</td>
							<td style="width:150px;"  colspan = "9">
									<textarea name="remarks" rows="3" class="form-control" placeholder="备注">${joint.remarks}</textarea>
							</td>
						</tr>
						</table>
						<!-- 帐号信息=============================================================================== -->
						
						<div align="center">
								<button class="btn btn-danger" type="submit" id="saveJoint" ><i class="icon-save"></i>&nbsp;保存</button>&nbsp;&nbsp;
								<button class="btn btn-warning" type="button" onclick="$(getOtherIframe('title')).find('#backButton').trigger('click');"><i class="icon-reply"></i>&nbsp;取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
  	
  	<script type="text/javascript">
	
	var valiinfo={"editJointForm":{rules:{
			corporate:{
				required:true,
				rangelength:[2,20]
			},
			legalperson:{
				required:true,
				rangelength:[2,20]
			},
			idnumber:{
				/* required:true, */
				isIdCardNo:true
			},
			phoneid:{
				required:true,
				digits:true,
				phoneNumbers:true,
				checkphoneids:true
			},
			applicant:{
				maxlength:100
			},
			number:{
				maxlength:50
			},
			address:{
				maxlength:300
			},
			industry:{
				number:true
			},
			startnum:{
				number:true,
				maxlength:12
			},
			license:{
				/* required:true, */
				maxlength:50
			},
			percentage:{
				/* required:true,  */
				number:true,
				accountBy:[0,1]
			},
			status:{
				required:true
			},
			
			//管理员信息校验
			account:{
				required:true,
				number:true,
				phoneNumbers:true,
				checkAccount:true
			},
			password:{
				required:true,
				rangelength:[6,20]
			},
			fullname:{
				required:true,
				rangelength:[2,20]
			},
			nickname:{
				maxlength:50
			},
			imei:{
				maxlength:30
			},
			sex:{
				required:true
			},
			//管理员信息校验
			remarks:{
				maxlength:300
			},
			saasnum:{
				required:true,
				number:true
			},
			saasagio:{
				required:true,
				number:true,
				accountBy:[0,1]
			},
			saasamount:{
				required:true,
				number:true
			},
			longitude:{
				required:true,
				number:true,
				landmarkCheck:[73.240,135.150]
			},
			latitude:{
				required:true,
				number:true,
				landmarkCheck:[3.3120,53.1980]
			}
		},messages:{
			corporate:{
				required:"不能为空",
				rangelength:"长度为2-20个字符"
			},
			legalperson:{
				required:"不能为空",
				rangelength:"长度为2-20个字符"
			},
			idnumber:{
				/* required:"不能为空", */
				isIdCardNo:"请输入正确的身份证号码，长度为8位到20位!"
			},
			phoneid:{
				required:"不能为空",
				digits:"请输入整数",
				phoneNumber:"请输入正确的手机号！",
				checkphoneids:"合作商联系手机已存在！"			
			},
			applicant:{
				maxlength:"长度最大为100字符"
			},
			number:{
				number:"商圈数为数字",
				maxlength:"最大长度12位"
			},
			address:{
				maxlength:"最多长度300字符!"
			},
			industry:{
				number:"行业为数字"
			},
			startnum:{
				number:"输入值为数字",
				maxlength:"最大长度12位"
			},
			license:{
				/* required:"不能为空", */
				maxlength:"最大长度50字符"
			},
			percentage:{
				/* required:"不能为空", */
				number:"输入值为数字",
				accountBy:"员工分账比例数为0-0.99之间的小数"
			},
			status:{
				required:"请选择一个状态!"
			},
			
			//管理员信息校验
			account:{
				required:"不能为空",
				number:"帐号为手机号应为数字",
				phoneNumbers:"帐号必须为手机号!",
				checkAccount:"帐号已存在！"
			},
			password:{
				required:"不能为空",
				rangelength:"长度6-20位"
			},
			fullname:{
				required:"不能为空",
				rangelength:"长度2-20位"
			},
			nickname:{
				maxlength:"最大长度50"
			},
			imei:{
				maxlength:"最大长度30"
			},
			sex:{
				required:"请选择性别"
			},
			//管理员信息校验
			
			remarks:{
				maxlength:"最大长度300字符!"
			},
			saasnum:{
				required:"不能为空",
				number:"请输入数字类型"
			},
			saasagio:{
				required:"不能为空",
				number:"请输入数字类型",
				accountBy:"签约折扣数为0-0.99之间的小数"
			},
			saasamount:{
				required:"不能为空",
				number:"请输入数字类型"
			},
			longitude:{
				required:"合作商家经度未填写",
				number:"地标请输入数字类型"
			},
			latitude:{
				required:"合作商家纬度未填写",
				number:"地标请输入数字类型"
			}
		}},
	};

	$.validator.addMethod("accountBy",function(value,element,params){
	 		if(value.length > 4){
	 			return  false;
	 		}
	 		if(value >= params[0] && value < params[1]){
	 			return true;
	 		}else{
	 			return false;
	 		}
	 },"员工分账比例数是[0,1)的2位小数");
	 
	var formId=["editJointForm"];
	var formHandle={
			"editJointForm":saveJoint
	};
	function idCard(idCard){
		var hk = /^[a-zA-Z]{1,2}\d{6}\([0-9a-zA-Z]\)$/.test(idCard);//香港身份证
		var tw = /^[a-zA-Z][0-9]{9}$/.test(idCard);//台湾身份证
		var mo = /^[1|5|7][0-9]{6}\([0-9Aa]\)/.test(idCard);//澳门身份证
		var mainland = idCardNoUtil.checkIdCardNo(idCard);//大陆身份证
		return mainland||hk||tw||mo;
	}
	$(function(){
		 /*
		 	验证身份证合法性
		 	
		 	新规则：身份证输入规则：8<=长度<=20  只能录入大小写英文，数字，()
		 */
		 $.validator.addMethod("isIdCardNo", function(value, element) {
			  return (this.optional(element) ||idCard(value)); 
			}, "请正确输入您的身份证号码,长度为8位到20位");
		
			/*
			 * 校验必须为手机号11位
			 */
			$.validator.addMethod("phoneNumbers", function(value, element) {
				    var result = true;
			        if(value.length!=11)
			        {
			        	result =  false;
			        }
			     /*   var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
			        if(!myreg.test(value))
			        {
			        	result = false;
			        }*/
			        return result;    
			}, "请输入正确的手机号码,长度为11位");	
			 /*
			  * 验证手机唯一性
			 */
			 $.validator.addMethod("checkphoneids", function(value, element) {
				   var result = true;
			        // 设置同步
			        $.ajaxSetup({
			            async: false
			        });
			        var param = {
			        		phoneid: value
			        };
			        var oldphoneid = $("input[name=oldphoneid]").val();
			        if(oldphoneid != value){
			        	$.post("business_cooperation/joint/init/checkPhoneid.jhtml", param, function(data){
				        	if(data <= 0){
				        		result = true;
				        	}else{
				        		result = false;
				        	}
				        });
		        	}else{
		        		result = true;
		        	}
			        // 恢复异步
			        $.ajaxSetup({
			            async: true
			        });
			        return result;    
				}, "合作商联系手机已存在！");
			 
			 /*
			  * 验证帐号唯一
			 */
			 $.validator.addMethod("checkAccount", function(value, element) {
				   var result = true;
			        // 设置同步
			        $.ajaxSetup({
			            async: false
			        });
			        var param = {
			        		phoneid: value
			        };
			        var oldphoneid = $("input[name=oldId]").val();
			        if(oldphoneid != value){
			        	$.post("business_cooperation/staff/init/checkPhone.jhtml", param, function(data){
				        	if(data <= 0){
				        		result = true;
				        	}else{
				        		result = false;
				        	}
				        });
		        	}else{
		        		result = true;
		        	}
			        // 恢复异步
			        $.ajaxSetup({
			            async: true
			        });
			        return result;    
				}, "登录账号已存在！");
		
		$.validator.addMethod("landmarkCheck", function(value, element,params) {
			 var len = value.length;
			 if(len > 16){
				 return false;
			 }else if((value <= params[0]) || (value >= params[1]) ){
				 return false;
			 }else {
				 var indexOf = value.indexOf(".");
				 if(indexOf>0){
					 var numStr = value.substr(indexOf+1);
					 return !(numStr.length > 12);
				 }
				 return true;
			 }  
			}, "请输入有效的经纬度!");
		
			 
		for(var i=0;i<formId.length;i++){
			validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
		}
	});	
	function formSubmit(form){
		return formHandle[form];
	}
	</script>
</body>
</html>
