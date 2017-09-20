<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>交易流水</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label">收货人手机：</label>
					<div class="col-md-3">
						<input type="text" class="form-control"  name="getUserPhone"  value="${param.getUserPhone}" style="width:100%">
					</div>
				
					<label class="col-md-1 control-label">查询区域：</label>
					<div class="col-md-3">
						<div class="input-group" id="ld" style="width:100%"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label">订&nbsp;购&nbsp;时&nbsp;间：</label>
					<div class="col-md-3">
						<input type="text" name ="ptimeStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:46.5%;float:left">
						<label style="float: left;">&nbsp;&nbsp;--&nbsp;&nbsp;</label>
						<input type="text" name ="ptimeEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:46.5%;float:left">
					</div>
					<label class="col-md-1 control-label">审批状态：</label>
					<div class="col-md-3">
						<select class="form-control" tabindex="2" name ="pstatus"  style = "width:100%;">
						    <option value = "">--请选择--</option>
			                <option value = "0">待支付</option>
			                <option value = "1">已支付</option>
			             </select>
					</div>
					<div class="submit">
						<input class="submit radius-3" type="button" value="查询全部" data-bus='query' /> 
							<input type="reset" class="reset radius-3" value="重置全部" data-bus='reset' />
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div class="panel">
		<div class="panel-body data">
			<c:if test="${null!=btnAu['marketingManagement/phoneBill/export'] && btnAu['marketingManagement/phoneBill/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>
			<div id="transactionFlowDiv" request-init ="${requestInit}">
				
			</div>
		</div>
	</div>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
		var trf= $('#transactionFlowDiv');
		$(function(){
		  
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
			
			var url  = [$(trf).attr("request-init"),".jhtml"].join("");
			$(trf).page({
				url : url,
				dataType:"html",
				success : handle,
				pageBtnNum : 10,
				paramForm : 'searchForm'
			});
			inserTitle(' > 营销管理  > <span><a href="marketingManagement/phoneBill/init.jhtml" target="right"> iPhone6活动统计</a>','transactionFlow',true);
			
			$("#export").click(function(){
				$form = $("#searchForm").attr("action","marketingManagement/phoneBill/export.jhtml");
				$form[0].submit();
			});
			
			//区域联动
			   $("#ld").areaLd({
					isChosen : true,
					showConfig : [ {
						name : "provinceId",
						tipTitle : "--省--",
						width : '50%'
					}, {
						name : "cityId",
						tipTitle : "--市--",
						width : '48%'
					}]
				});
			 //重置
		   $("input[data-bus=reset]").click(function(){
				$("#ld").find("select").trigger("chosen:updated");
			});
			
			
		});
		
		function handle(data){
			var table  =$(trf).find("#tableinfo");
			if(table.length==0){
				$(trf).prepend(data);
			}else{
				$(table).html(data);
			}
		}
		
	</script>
	
</body>
</html>
