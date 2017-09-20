<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<form class="form-horizontal" role="form" id="editAllianceAccountForm">
		<input type="hidden" class="form-control"  name="aid"  value="${account.aid}">
		<table class="borderBottom table table-form ">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;登录帐号:</h5></th>	
					<th colspan="2">
						<input type="text" class="form-control"  name="account"  value="${account.account}">
						<input type="hidden" id="oldAccount"  value="${account.account}">
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;帐号昵称:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="nname"  value="${account.nname}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;真实姓名:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="fullname"  value="${account.fullname}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;联系人手机:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="phone"  value="${account.phone}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;登录密码:</h5></th>	
					<th colspan="2"><input type="password" class="form-control"  name="password"  value="******"></th>
				</tr>
				
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;状态:</h5></th>
					<th >
						<select class="form-control" name="status">
							<option <c:if test="${account.status==0}">selected="selected"</c:if> value="0">启用</option>
							<option <c:if test="${account.status==1}">selected="selected"</c:if> value="1">不启用</option>
						</select>
					</th>
					
				</tr>
 				<tr>
	 					<th colspan="3" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
<script type="text/javascript">
$(document).ready(function() {
	
/*
 	验证帐号唯一性，异步检验
 */
 $.validator.addMethod("checkAccount", function(value, element) {
	  var result = true;
		if($('#oldAccount').val() == value){
			 return result;
	 	}
        // 设置同步
        $.ajaxSetup({
            async: false
        });
        var param = {
        	account: value
        };
        $.post("businessman/allianceShop/init/checkAccount.jhtml", param, function(data){
            result = data;
        });
	  
       return result;    
	}, "帐号已存在");
	 
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
	 
	validate("editAllianceAccountForm",{
		rules:{
			account:{
				required:true,
				checkAccount:true,
				rangelength:[6,20]
			},
			nname:{
				rangelength:[2,20]
			},
			fullname:{
				rangelength:[2,20]
			},
			phone:{
				required:true,
				number:true,
				phoneNumber:true
			},
			password:{
				required:true,
				rangelength:[6,16]
			},
			status:{
				required:true
			}
		},
		messages:{
			account:{
				required:"账号未填写!",
				checkAccount:"帐号已存在!" ,
				rangelength:"账号真实长度为  6-20个字符!"
			},
			nname:{
				rangelength:"账号昵称真实长度为  2-20个字符!"
			},
			fullname:{
				rangelength:"真实姓名有效长度2-20个字符!"
			},
			phone:{
				required:"手机号未填写",
				number:"无效号码",
				phoneNumber:" 请输入正确的手机号！"
			},
			password:{
				required:"密码未填写!",
				rangelength:"密码有效长度为6-16个字符!"
			},
			status:{
				required:"状态未选择!"	
			}
		}},formAjax);
}); 

function formAjax(){
	var url = 'businessman/allianceShop/allianceAccount/update.jhtml' + '?t=' + Math.random();
	$.ajax({
		type : 'post',
		url : url,
		data : jsonFromt($('#editAllianceAccountForm').serializeArray()),
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			 $('#triggerModal').modal('hide');
			if (data.success) {
				allianceAccountList.reload();
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}
</script>

