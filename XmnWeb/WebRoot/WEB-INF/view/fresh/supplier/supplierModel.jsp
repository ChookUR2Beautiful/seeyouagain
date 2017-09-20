<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<form action="${action}" id="supplierForm" class="form-horizontal"
	method="post">
	<c:if test="${!empty supplier.supplierId}">
		<input type="hidden" name="supplierId" value="${supplier.supplierId}">
	</c:if>
	<c:if test="${!empty supplier.status}">
		<input type="hidden" name="status" value="${supplier.status}">
	</c:if>
	<table width="100%" class="borderBottom table table-form">
		<tbody>
			<tr>
				<td class="w-100px" width="20%;">
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;供应商名称<font color="red">*</font>&nbsp;:
					</h5>
				</td>
				<td><input type="text" class="form-control" placeholder="供应商名称"
					name="supplierName" value="${supplier.supplierName}"></td>
			</tr>
			<tr>
				<td class="w-100px" width="20%;">
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;联系人<font color="red">*</font>&nbsp;:
					</h5>
				</td>
				<td><input type="text" class="form-control" placeholder="联系人"
					name="contacts" value="${supplier.contacts}"></td>
			</tr>
			<tr>
				<td class="w-100px" width="20%;">
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;联系电话<font color="red">*</font>&nbsp;:</h5>
				</td>
				<td>
					<input type="text" class="form-control" placeholder="手机（13688889999）或固话（07623661129）" name="phone" value="${supplier.phone}">
					<input type="hidden" class="form-control" name="oldphone" value="${supplier.phone}"/>
				</td>
			</tr>
			<tr>
				<td class="w-100px" width="20%;">
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;地址<font color="red">*</font>&nbsp;:
					</h5>
				</td>
				<td><input type="text" class="form-control" placeholder="地址"
					name="address" value="${supplier.address}"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th style="text-align: center;" colspan="2">
					<button class="btn btn-success" type="submit">
						<span class="icon-ok"></span>保 存
					</button>&nbsp;&nbsp;
					<button data-dismiss="modal" class="btn btn-default" type="reset">
						<span class="icon-remove"></span> 取 消
					</button>
				</th>
			</tr>
		</tfoot>
	</table>
</form>
<script type="text/javascript">
	contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript">
	/**
	 * 校验必须为手机号 
	 */
	$.validator.addMethod("phoneNumber", function(value, element) {
		var result = true;
		//var reg1 = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		//var reg2 = /^(0[0-9]{2,3})?([2-9][0-9]{6,7})+([0-9]{1,4})?$/;
		if (value.length < 7 || value.length > 18) {
			result = false;
		}
		return result;
	}, "请输入正确的电话号码！");
	
	 /*
	  * 验证手机唯一性
	 */
	 $.validator.addMethod("checkphones", function(value, element) {
		   var result = true;
	        // 设置同步
	        $.ajaxSetup({
	            async: false
	        });
	        var param = {
	        		phone: value
	        };
	        var oldphone = $("input[name=oldphone]").val();
	        if(oldphone != value){
	        	$.post("fresh/supplier/checkPhone.jhtml", param, function(data){
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
		}, "供应商联系手机已存在！");

	var valiinfo = {
		rules : {
			supplierName : {
				required : true,
				rangelength : [ 1, 50 ]
			},
			contacts : {
				required : true,
				rangelength : [ 1, 50 ]
			},
			phone : {
				required : true,
				digits : true,
				phoneNumber : true,
				checkphones:true
			},
			address : {
				required : true
			}
		},
		messages : {
			supplierName : {
				required : "供应商名称未填写！",
				rangelength : "有效范围1~50个字符"
			},
			contacts : {
				required : "联系人未填写！",
				rangelength : "有效范围1~50个字符"
			},
			phone : {
				required : "手机号码未填写",
				digits : "电话号码必须是数字",
				phoneNumber : "请输入正确的联系电话！",
				checkphones:"供应商联系手机已存在！"
			},
			address : {
				required : "地址未填写！"
			}
		}
	};

	validate("supplierForm", valiinfo, formSubmit);
	
</script>

