<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form action="${action }" id="memberForm" class="form-horizontal" method="post">
	<c:if test="${!empty member.uid}">	
		<input type="hidden" name="uid" value="${member.uid}">
	</c:if>
	<c:if test="${!empty member.uname}">	
		<input type="hidden" name="model" value="${member.uname}">
	</c:if>
	<c:if test="${!empty member.model}">	
		<input type="hidden" name="model" value="${member.model}">
	</c:if>
	<c:if test="${!empty member.brand}">	
		<input type="hidden" name="brand" value="${member.brand}">
	</c:if>
	<c:if test="${!empty member.last_model}">	
		<input type="hidden" name="last_model" value="${member.last_model}">
	</c:if>
	<c:if test="${!empty member.last_brand}">	
		<input type="hidden" name="last_brand" value="${member.last_brand}">
	</c:if>
	<c:if test="${!empty member.genusname}">	
		<input type="hidden" name="genusname" value="${member.genusname}">
	</c:if>
	<c:if test="${!empty member.city}">	
		<input type="hidden" name="city" value="${member.city}">
	</c:if>
	<c:if test="${!empty member.region}">	
		<input type="hidden" name="region" value="${member.region}">
	</c:if>
	<table width="100%" class="borderBottom table table-form " >
		<tbody>
			<tr >
				<th  class="w-100px">用户昵称:</th>
				<td colspan="2"><input type="text" class="form-control" placeholder="用户昵称" value="${member.nname}"  name="nname" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"></td>
			</tr>
			
			<tr >
				<th  class="w-100px">用户密码:</th>
				<td colspan="2">
			 		<input type="hidden" class="form-control"  name="oldpassword"  value = "${member.password}">
				 <!-- 编辑页面前台显示密码：****** -->
					<c:if test="${not empty member.password}">
				 		<input type="password" class="form-control"  name="password" value="******" readonly="readonly">
					</c:if>
					<c:if test="${empty member.password}">
						 <input type="password" class="form-control"  name="password">
					</c:if>
				</td>	
			</tr>
			<tr>
				<th class="w-100px">手机号码:</th>
				<td colspan="2">
						<c:if test="${empty member.phone}">
							<input type="text" class="form-control" placeholder="手机号码"   name="phone">
						</c:if>
						<c:if test="${!empty member.phone}">
						<input type="text" class="form-control"  name="phone" value="${member.phone}" readonly="readonly">								
						</c:if>
					</td>
			</tr>
			<tr>
				<th>性别:</th>
				<td colspan="2">
				 <select class="form-control"  name="sex" >
								<option value="">请选择</option>
								<option  <c:if test="${member.sex==1 }">selected="selected"</c:if> value="1">男</option>
								<option  <c:if test="${member.sex==2 }">selected="selected"</c:if> value="2">女</option>
								<option  <c:if test="${member.sex==0 }">selected="selected"</c:if> value="0">未知</option>
							</select>
				</td>
			</tr>
			<tr>
				   	<td class = "sellerTitleCss">
						<h5>注册区域:</h5> 
					</td>
					<td class = "sellerContentCss" style="text-align: left" colspan = "2">
						<div class="input-group" id="ld" style="width:100%" <c:choose>
									    <c:when test="${!empty member.regareaId}">
									      initValue=" ${member.regareaId}"
									    </c:when>
									
									    <c:when test="${empty member.regareaId && !empty member.regcityId}">
									    	initValue=" ${member.regcityId}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${member.province}"
									    </c:otherwise>
									 </c:choose>></div>
					</td>
			</tr>
			<tr>
				<td ><h5>商圈:</h5></td>
				<td class = "sellerTitleCss" style="text-align: left;" colspan="2">
					<select class="form-control"  id="regzoneid" name ="regzoneid" style = "width:100%;" initValue="${member.regzoneid}">
						<option value="">请先选择区域再选择商圈</option>
			        </select>
				</td>
			</tr>
			 	<tr>
				<th class="w-100px">用户类型:</th>
				<c:if test="${!empty member.usertype}">
					<td><input type="radio"  value="1" <c:if test="${member.usertype==1 }">checked="checked"</c:if> name=usertype>普通会员</td>
					<td><input type="radio"  value="2" <c:if test="${member.usertype==2 }">checked="checked"</c:if> name="usertype">寻蜜客</td>
				</c:if>	
				<c:if test="${empty member.usertype}">
					<td><input type="radio"  value="1" checked="checked" name=usertype>普通会员</td>
					<td><input type="radio"  value="2"  name="usertype">寻蜜客</td>
				</c:if>		
				<input type="hidden"  value="${member.usertype}"  name="baseusertype" />
			</tr>
			<tr>
				<th class="w-100px">头像:</th>
				<td colspan="2">
					<div id="tx"></div>
					<input type="hidden" value="${member.avatar}" class="form-control" placeholder="头像" value="" id="avatar"  name="avatar"  >
					</td>
			</tr>
			<tr>
	          	<th>个性签名:</th>
	          	<td colspan="2">
	          		<textarea  rows="2" placeholder="个性签名"  class="form-control" name="sign" >${member.sign}</textarea>
				</td>
        	</tr>
        	<%-- <tr>
				<th class="w-100px">工作地址:</th>
				<td colspan="2"><textarea  rows="2" placeholder="工作地址" class="form-control"  name="address">${member.address}</textarea></td>
			</tr> --%>
		<!-- 	<tr>
	          	<th>兴趣</th>
	          	<td colspan="2">
	          		<textarea  rows="2" placeholder="兴趣" class="form-control"></textarea>
				</td>
        	</tr> -->
       
			<c:if test="${!empty member.uid}">
	        	<tr>
					<th class="w-100px">状态:</th>
					<td colspan="2">
						<select class="form-control"  name="status">
						
								<option  value="">请选择</option>
								<option <c:if test="${member.status==1 }">selected="selected"</c:if> value="1">正常</option>
								<option <c:if test="${member.status==2 }">selected="selected"</c:if> value="2">锁定</option>
								<option <c:if test="${member.status==3 }">selected="selected"</c:if> value="3">注销</option>
								<option <c:if test="${member.status==4 }">selected="selected"</c:if> value="4">黑名单</option>
						</select>
					</td>
					
				</tr>
			</c:if>
		
		</tbody>
		<tfoot>
			
			<tr >
				<th style="text-align: center;" colspan="3"> 
	 						<button  class="btn btn-success" type="submit"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button data-dismiss="modal" class="btn btn-default" type="reset"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 					
				<!-- <th></th>
				<th >
					<input type="submit" data-loading="稍候..." value="保存"  class="btn btn-primary radius" > 
					
				</th>
				<th><input type="button" class="btn btn-danger radius" data-dismiss="modal" value="取消"  name="block"></th> -->
			</tr>
		</tfoot>
	</table>
</form>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script type="text/javascript">
	$("#tx").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
	//区域
	$("#ld").areaLd({
		isChosen : false,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"regcity",tipTitle:"--市--"},{name:"regarea",tipTitle:"--区--"}],
		lastChange : function($dom){
			if($dom.val()){
				if($dom.val()){
					$.ajax({
						url : "common/business/BusinessList.jhtml?areaId="+($dom.val()),
						dataType:"json",
						method : "get", 
						success:function(data){
							var businessV = $("#regzoneid").attr("initValue");
							$("#regzoneid").empty().append('<option value="">请选择商圈</option>');
							for(var i=0;i<data.length;i++){
								$("#regzoneid").append('<option value="'+data[i].bid+'" '+(data[i].bid==businessV?'selected':'')+'>'+data[i].title+'</option>');
							}
						}
					});
				}
			}
		}
	});
	/**
	  * 校验必须为手机号
	 */
	 $.validator.addMethod("phoneNumber", function(value, element) {
		    var result = true;
	        if(value.length!=11)
	        {
	        	result =  false;
	        }
	        return result;    
	}, "请输入正确的手机号码！");
	
	var valiinfo={
			rules:{
				nname:{
					//required:true,
					rangelength:[1,20]
				},
				password:{
					required:true,
					rangelength:[6,16]
				},
				phone:{
					required:true,
					phoneNumber:true,
					digits:true,
					checkPhone:true
				},
				sex:{
					required:true
				},
				status:{
					required:true
				}
			},
			messages:{
				nname:{
					 //required:"用户昵称未填写", 
					 rangelength:"有效范围1~20个字符"
				},
				password:{
					 required:"用户密码未填写", 
					 rangelength:"有效范围6~16个字符"
				},
				phone:{
					required:"手机号码未填写",
					digits:"电话号码必须是数字",
					phoneNumber:"请输入正确的手机号！"
					
				},
				 sex:{
					required:"用户性别未选择"
				},
				status:{
					required:"用户状态未选择"
				}
			}
	}
	
 /*
 *	验证帐号唯一性，异步检验
 */
 $.validator.addMethod("checkPhone", function(value, element) {
	 var result = true;
	 if(value.length>0){
		
			 // 设置同步
		        $.ajaxSetup({
		            async: false
		        });
		        var param = {
		        	phone: value
		        };
		        $.post("member/memberList/init/checkPhone.jhtml", param, function(data){
		            result = data;
		        }); 
	 }
     return result;
        
	}, "该号码已被使用");
	
	validate("memberForm",valiinfo,formSubmit);
	
	</script>

<!-- <script type="text/javascript">
/*
 *	验证帐号唯一性，异步检验
 */
 $.validator.addMethod("checkPhone", function(value, element) {
	 var result = true;
	 if(value.length>0){
		
			 // 设置同步
		        $.ajaxSetup({
		            async: false
		        });
		        var param = {
		        	phone: value
		        };
		        $.post("member/memberList/init/checkPhone.jhtml", param, function(data){
		            result = data;
		        }); 
	 }
     return result;
        
	}, "该号码已被使用");
	
 /**
  * 校验必须为手机号
 */
 $.validator.addMethod("phoneNumber", function(value, element) {
	    var result = true;
        if(value.length!=11)
        {
        	result =  false;
        }
        return result;    
}, "请输入正确的手机号码！");
 
	
$(document).ready(function() {
	$("#tx").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
	//区域
	$("#ld").areaLd({
		isChosen : false,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"regcity",tipTitle:"--市--"},{name:"regarea",tipTitle:"--区--"}],
		lastChange : function($dom){
			if($dom.val()){
				if($dom.val()){
					$.ajax({
						url : "common/business/BusinessList.jhtml?areaId="+($dom.val()),
						dataType:"json",
						method : "get", 
						success:function(data){
							var businessV = $("#regzoneid").attr("initValue");
							$("#regzoneid").empty().append('<option value="">请选择商圈</option>');
							for(var i=0;i<data.length;i++){
								$("#regzoneid").append('<option value="'+data[i].bid+'" '+(data[i].bid==businessV?'selected':'')+'>'+data[i].title+'</option>');
							}
						}
					});
				}
			}
		}
	});
	
	startValidate();
});

function startValidate(){
	validate("memberForm",{
		rules:{
			nname:{
				//required:true,
				rangelength:[1,20]
			},
			password:{
				required:true,
				rangelength:[6,16]
			},
			phone:{
				required:true,
				phoneNumber:true,
				digits:true,
				checkPhone:true
			},
			sex:{
				required:true
			},
			status:{
				required:true
			}
		},
		messages:{
			nname:{
				 //required:"用户昵称未填写", 
				 rangelength:"有效范围1~20个字符"
			},
			password:{
				 required:"用户密码未填写", 
				 rangelength:"有效范围6~16个字符"
			},
			phone:{
				required:"手机号码未填写",
				digits:"电话号码必须是数字",
				phoneNumber:"请输入正确的手机号！"
				
			},
			 sex:{
				required:"用户性别未选择"
			},
			status:{
				required:"用户状态未选择"
			}
		}
	},updateForm);
}

function updateForm(){
	var data = jsonFromt($('#memberForm').serializeArray());
	var url = 'member/memberList/update.jhtml' + '?t=' + Math.random();
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				memberListDiv.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}
</script> -->
	
