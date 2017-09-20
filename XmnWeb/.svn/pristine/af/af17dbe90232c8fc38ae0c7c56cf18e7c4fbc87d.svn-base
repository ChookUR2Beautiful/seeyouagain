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
		<input type="hidden" name="uid" value="${anchorInfo.uid }" /> <input
			type="hidden" name="nickname" value="${anchorInfo.nickname }" />
		<table width="100%">
			<tbody>
				<tr>
					<td style="width:30%;"><h5>主播名称:</h5></td>
					<td><input type="text" class="form-control"
						disabled="disabled" style="width:90%;" value="${anchorInfo.nickname }"></td>
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
<script src="<%=path%>/js/live_anchor/anchorCard/addAnchorCardInit.js"></script>
<script type="text/javascript">
	
</script>
