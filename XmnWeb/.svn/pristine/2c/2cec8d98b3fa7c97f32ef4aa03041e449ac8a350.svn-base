<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
</head>

<body>
	<form class="form-horizontal" role="form" id="editIsFirstForm">
		<div class="example">
			<div class="panel panel-primary">
		           <div class="panel-body">
		           <div class="panel" >
		           <div><h5>子店信息：</h5></div>
			     <table >
					<tr>
					 <td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;子店编号：</h5></td>
					 <td style="width:200px;"><input type="text" class="form-control" id="sellerid" name="sellerid" value="${sellerDetailed.sellerid}" readonly="readonly"></td>
					 <td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;子店名称：</h5></td>
					 <td style="width:200px;"><input type="text" class="form-control" name="sellername" value="${sellerDetailed.sellername}" readonly="readonly"></td>
					</tr>
					<tr>
					<td style="width:100px;"><h5>连锁店编号：</h5></td>
					 <td style="width:200px;"><input type="text" class="form-control" id="fatherid" name="fatherid" value="${sellerDetailed.fatherid}" readonly="readonly"></td>
					 <td style="width:100px;"><h5>所属连锁店：</h5></td>
					 <td style="width:200px;"><input type="text" class="form-control"  name="lssellername" value="${sellerDetailed.lssellername}" readonly="readonly"></td>
			  		</tr>
              </table>
            </div>
         <div class="panel">
		<div><h5>将子店以下功能授权给连锁店操作：</h5></div>
		 <table>
		 <tr>
		  <td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业收入提现:&nbsp;&nbsp;<input type="radio" name="operatingOut" id="operatingOutC" value="0" ${sellerDetailed.operatingOut==0?'checked':''}><label for="operatingOutC">关闭</label></radio>&nbsp;&nbsp;
		 <input type="radio" name="operatingOut" id="operatingOutO" value="1" ${sellerDetailed.operatingOut==1?'checked':''}><label for="operatingOutO">开启</label></radio></h5>
		 </td>
		 <td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返利收入提现:&nbsp;&nbsp;<input type="radio" name="rebateOut" id="rebateOutC" value="0" ${sellerDetailed.rebateOut==0?'checked':''}><label for="rebateOutC">关闭</label></radio>&nbsp;&nbsp;
		 <input type="radio" name="rebateOut" id="rebateOutO" value="1" ${sellerDetailed.rebateOut==1?'checked':''}><label for="rebateOutO">开启</radio></h5>
		 </td>
		 </tr>
		 <tr>
		 <td><h5>基本信息和资料修改:&nbsp;&nbsp;<input type="radio" name="isShopinfo" id="isShopinfoC" value="0" ${sellerDetailed.isShopinfo==0?'checked':''}><label for="isShopinfoC">关闭</label></radio>&nbsp;&nbsp;
		 <input type="radio" name="isShopinfo" id="isShopinfoO" value="1" ${sellerDetailed.isShopinfo==1?'checked':''}><label for="isShopinfoO">开启</label></radio></h5>
		 </td>
		 <td><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;折扣修改:&nbsp;&nbsp;<input type="radio"  name="isDiscount" id="isDiscountC" value="0" ${sellerDetailed.isDiscount==0?'checked':''}><label for="isDiscountC">关闭</label></radio>&nbsp;&nbsp;
		 <input type="radio" name="isDiscount" value="1" id="isDiscountO" ${sellerDetailed.isDiscount==1?'checked':''}><label for="isDiscountO">开启</label></radio></h5>
		 </td>
		 </tr>
		 </table>
		</div>
		<div class="panel" style="padding-top: 10px;padding-bottom : 10px;">
			<div class="text-center">
				<button type="submit" class="btn btn-success" id="ensure">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
		</div>
		</div>
	</form>
</body>
<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
<script type="text/javascript">
	$(function() {
		validate("editIsFirstForm", {}, formAjax);
	});
	function formAjax() {
		$.ajax({
			type : 'post',
			url : "businessman/sellerDetailed/authorized.jhtml" + "?t="
					+ Math.random(),
			data : jsonFromt($('#editIsFirstForm').serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
			    $('#prompt').hide();
			    $('#triggerModal').modal('hide');
				showSmReslutWindow(data.success, data.msg);
				sellerAccountList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	}
</script>
</html>
