<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

</head>

<body>
	<form class="form-horizontal" role="form" id="editAppPushForm" style=" width :580px;overflow-y:auto; ">
		<input type="hidden"  name="sellerid" value="${param.sellerid}">
		<input type="hidden"  name="id" value="${sellerAgio.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden"  name="sdate" value="<fmt:formatDate value="${sellerAgio.sdate}" pattern="yyyy-MM-dd HH:mm"/>">
		<input type="hidden"  name="selleridid" value="${sellerAgio.sellerid}">
		<input type="hidden"  name="aid" value="${sellerAgio.aid}">
		<table width="100%">
			<tbody>
				
				<tr>
					<th style="width:120px;"><h5>&nbsp;&nbsp;折扣类型:</h5></th>	
					<th colspan="2">
						<select class="form-control" name ="type" readonly="readonly" >
			                <option value = "1"${sellerAgio.type==1?"selected":""}>常规折扣</option>
			             </select>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;折扣状态:</h5></th>	
					<th colspan="2">
							<select class="form-control" name ="status">
							    <option value = "">--请选择--</option>
				                <option value = "1"${sellerAgio.status==1?"selected":""}>启用</option>
				                <option value = "2"${sellerAgio.status==2?"selected":""}>关闭</option>
				            </select>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;折扣（%）:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="baseagio"  value="${sellerAgio.baseagio}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;营业收入（%）:</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  readonly="readonly" name="income"  value="${sellerAgio.income}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;商户占比（%）:</h5></th>	
					<th colspan="2"><input type="text" class="form-control" readonly="readonly" name="sledger"  value="${sellerAgio.sledger}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;用户占比（%）:</h5></th>	
					<th colspan="2"><input type="text" class="form-control" readonly="readonly" name="yledger"  value="${sellerAgio.yledger}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;平台占比（%）:</h5></th>
					<th colspan="2"><input type="text" class="form-control" readonly="readonly" name="pledger"  value="${sellerAgio.pledger}"></th>
				</tr>
				
				<%-- <tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<th colspan="2">
						<input type="text" id="discountstdate" name ="stdate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="float:left" value="<fmt:formatDate value="${sellerAgio.stdate}" pattern="yyyy-MM-dd HH:mm"/>">
					</th>
				</tr>
				<tr>	
					<th style="width:90px;"><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<th colspan="2">
						<input type="text" id="discountendate" name ="endate" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="float:left" value="<fmt:formatDate value="${sellerAgio.endate}" pattern="yyyy-MM-dd HH:mm"/>" >
					</th>	
				</tr> --%>
				
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;备注:</h5></th>
					<th colspan="2"><textarea name="remarks" rows="3" class="form-control" placeholder="备注">${sellerAgio.remarks}</textarea>					
				</tr>
 				<tr>
 					<th colspan="3" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保存 </button>
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
 					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script type="text/javascript">
		$(document).ready(function() {
			$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd hh:ii'	
			});
			startValidate();
			
			//获取折扣信息
			getAgio();
		});
		
	function startValidate(){
			validate("editAppPushForm",{
				rules:{
					type:{
						required:true,
					},
					status:{
						required:true,
					},
					baseagio:{
						required:true,
						number:true,
						accountBy:[0,100],
					}
				},
				messages:{
					type:{
						required:"请选择折扣类型",
					},
					status:{
						required:"请选择折扣状态",
					},
					baseagio:{
						required:"请填写折扣数",
						number:"请输入合理数字",
						accountBy:"请输入0-100之间的数",
					}
				}},formAjax);
			 
			$.validator.addMethod("accountBy",function(value,element,params){
		 		if(value.length > 5){
		 			return  false;
		 		}
		 		if(value >= params[0] && value <= params[1]){
		 			return true;
		 		}else{
		 			return false;
		 		}
		 	},"请输入0-100之间的数");
			 
		}
	
		function formAjax(){
			
			var data = $('#editAppPushForm').serializeArray();
			data = jsonFromt(data);
			
			var url ;
			if($('#isType').val() ==  'add'){
				url = 'businessman/sellerAgio/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'businessman/sellerAgio/update.jhtml' + '?t=' + Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : jsonFromt($('#editAppPushForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					 $('#triggerModal').modal('hide');
					if (data.success) {
						if($('#isType').val() ==  'add'){
							orderinvoiceList.reset();
						}else{
							orderinvoiceList.reload();
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
		}
		
	function databj(sdate,enddate){
		var s = dateParse(sdate).getTime(); 
		var e = dateParse(enddate).getTime();
		return e>s;
	}
	</script>
</body>
</html>
