<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="utf-8">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" role="form" id="addCardForm">
		<input type="hidden" name="sellerid" value="${param.sellerid }" /> <input
			type="hidden" name="sellername" value="${param.sellername }" />
		<table width="100%">
			<tbody>
				<tr>
					<td style="width:30%;"><h5>商家名称:</h5></td>
					<td><input type="text" class="form-control"
						disabled="disabled" style="width:90%;" value="${param.sellername}"></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>证件类型:</h5></td>
					<td><select class="form-control" id="idtype" name="idtype"
						style="width:90%;">
							<option value="">请选择</option>
							<option value="1" ${param.idtype==1?"selected":""}>身份证</option>
							<option value="2" ${param.idtype==2?"selected":""}>护照</option>
							<option value="3" ${param.idtype==3?"selected":""}>驾驶证</option>
					</select></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>证件号码:</h5></td>
					<td><input type="text" class="form-control" name="identity"
						style="width:90%;"></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>银行卡类型:</h5></td>
					<td><select class="form-control" name="cardType"
						style="width:90%;">
							<option value="">请选择</option>
							<option value="1" ${param.cardType==1?"selected":""}>借记卡</option>
							<option value="2" ${param.cardType==2?"selected":""}>信用卡</option>
					</select></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>银行卡号:</h5></td>
					<td><input type="text" class="form-control" name="cardId"
						style="width:90%;"></td>
					</td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>开户行名称:</h5></td>
					<td><select id="bank" name="bank" class="form-control"
						style="width:90%;">
							<option value="">--请选择--</option>
					</select> <input type="hidden" class="form-control" id="abbrev"
						name="abbrev"> <!-- 开户行名称缩写 --></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>支行名称:</h5></td>
					<td><input type="text" class="form-control" name="bankName"
						style="width:90%;"></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>银行所在区域:</h5></td>
					<td colspan="1">
						<div class="input-group" id="areaId" style="width: 90%"></div>
					</td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>持卡人姓名:</h5></td>
					<td><input type="text" class="form-control"
						name="cardUserName" style="width:90%;"></td>
				</tr>

				<tr>
					<td style="width:30%;"><h5>银行预留手机号:</h5></td>
					<td><input type="text" class="form-control" name="cardPhone"
						style="width:90%;"></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>银行卡应用类型:</h5></td>
					<td><select class="form-control" id="ispublic" name="ispublic"
						style="width:90%;">
							<option value="">请选择</option>
							<option value="0" ${param.ispublic==0?"selected":""}>对私</option>
							<option value="1" ${param.ispublic==1?"selected":""}>对公</option>
					</select></td>
				</tr>
				<tr>
					<td style="width:30%;"><h5>银行卡提现用途:</h5></td>
					<td><select class="form-control" name="cardPurpose"
						style="width:90%;">
							<option value="">请选择</option>
							<option value="1" ${param.cardPurpose==1?"selected":""}>营业收入</option>
							<option value="2" ${param.cardPurpose==2?"selected":""}>佣金</option>
					</select></td>
				</tr>
				<tr>
					<td style="height:10px;"></td>
				</tr>
				<th colspan="2" style="text-align: center;">
					<button type="submit" class="btn btn-success" id="ensure">
						<span class="icon-ok"></span> 保 存
					</button> &nbsp;&nbsp;
					<button type="reset" class="btn btn-default" data-dismiss="modal">
						<span class="icon-remove"></span> 取 消
					</button>
				</th>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/js/common/IDCard.js"></script>
<script type="text/javascript">
	$(function() {
		var areaId = $("#areaId");
		if (areaId.length > 0) {
			areaId.areaLd({
				//isChosen : true,
				commonChange : function($dom, level) {

				},
				showConfig : [ {
					name : "provinceId",
					tipTitle : "--省--"
				}, {
					name : "citynameId",
					tipTitle : "--市--"
				} ]
			});
		}
	});

	$(document).ready(
			function() {
				jQuery.validator.addMethod("isMobile",
						function(value, element) {//电话号码自定义校验方式
							var length = value.length;
							var mobile = /^(1\d{10})$/;
							return this.optional(element)
									|| (length == 11 && mobile.test(value));
						}, "请正确填写您的手机号码");
				jQuery.validator.addMethod("isCardtypeId", function(value,
						element) {//证件号码自定义校验方式
					var cardtypeId = /^(\w{9,20})$/;
					return this.optional(element) || (cardtypeId.test(value)) || idCardNoUtil.checkAllIdCardNo(value);
				}, "请正确填写证件号码");
				validate("addCardForm", {
					rules : {
						cardId : {//银行卡号
							required : true,
							digits : true,
							minlength : 12,
							maxlength : 19
						},
						cardType : {//银行卡类型
							required : true
						},
						cardUserName : {//持卡人姓名
							required : true,
							maxlength : 30
						},
						bankName : {//发卡行名称
							required : true,
							maxlength : 50
						},
						cardPhone : {//银行预留手机号
							required : function() {
								if ($("#ispublic").val() == 0) {
									return true;
								} else {
									return false;
								}
							},
							isMobile : function() {
								if ($("#ispublic").val() == 0) {
									return true;
								} else {
									return false;
								}
							}
						},
						cardPurpose : {//银行卡提现用途
							required : true
						},
						ispublic : {//银行卡应用类型
							required : true
						},
						idtype : {//证件类型
							required : function() {
								if ($("#ispublic").val() == 0) {
									return true;
								} else {
									return false;
								}

							}
						},
						identity : {//证件号码
							required : function() {
								if ($("#ispublic").val() == 0) {
									return true;
								} else {
									return false;
								}
							},
							isCardtypeId : function() {
								if ($("#ispublic").val() == 0) {
									return true;
								} else {
									return false;
								}
							}
						},
						bank : {//开户行名称
							required : true,
							maxlength : 30
						},
						abbrev : {//开户行名称缩写
							required : true,
							maxlength : 20
						},
						provinceId : {//银行所属省
							required : true
						},
						citynameId : {//银行所属市
							required : true
						}
					},
					messages : {
						cardId : {
							required : "银行卡号不能为空!",
							digits : "请输入正整数",
							minlength : "银行卡号不能小于12位数字!",
							maxlength : "银行卡号不能超过19位数字!"
						},
						cardType : {
							required : "银行卡类型不能为空!"
						},
						cardUserName : {
							required : "持卡人姓名不能为空!",
							maxlength : "持卡人姓名不能超过30个字符"
						},
						bankName : {
							required : "发卡行名称不能为空!",
							maxlength : "发卡行名称不能超过50个字符！"
						},
						cardPhone : {
							required : "银行预留手机号不能为空!",
							isMobile : "请输入正确的手机号码"
						},
						cardPurpose : {
							required : "银行卡提现用途不能为空!"
						},
						ispublic : {
							required : "银行卡应用类型不能为空!"
						},
						idtype : {
							required : "证件类型不能为空!"
						},
						identity : {
							required : "证件号码不能为空!",
							isCardtypeId : "请正确填写9-20位字母或数字的证件号！"
						},
						bank : {
							required : "开户行名称不能为空!",
							maxlength : "开户行名称不能超过30个字符"
						},
						abbrev : {
							required : "开户行名称缩写不能为空!",
							maxlength : "开户行名称缩写不能超过20个字符"
						},
						provinceId : {
							required : "银行所属省不能为空!"
						},
						citynameId : {
							required : "银行所属市不能为空!"
						}
					}
				}, formAjax);

			});

	function formAjax() {
		var url = 'businessman/seller/addCard.jhtml?t=' + Math.random();
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#addCardForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					cardList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});

	}
	$.ajax({
		type : 'post',
		url : 'common/tbanks/getTBanks.jhtml',
		dataType : 'json',
		success : function(data) {
			$("#bank").append(data.data);
		}
	});

	$(function() {
		$("#bank").change(function() {
			$("#abbrev").val($("#bank").val());
		});
	})
</script>
