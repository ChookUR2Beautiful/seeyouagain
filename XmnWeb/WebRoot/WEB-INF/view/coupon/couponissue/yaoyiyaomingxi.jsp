<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>活动管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="youhuimaForm">
						<input type="hidden" name="issueId" value="${param.issueId}">
							<table style="width:100%;">
								<tbody>
									<tr>
									    <td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;优惠券编号:</h5></td>
										<td style="width:25%;"><input type="text"
											class="form-control" name="cid" style="width:90%;" /></td>

										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;使用状态:</h5></td>
										<td style="width:25%;"><select class="form-control"
											name="userStatus" style="width:90%;float:left">
												<option value="" selected="selected">全部</option>
												<option value="0">未使用</option>
												<option value="2">已使用</option>
												<option value="1">已锁定</option>
										</select></td>
										
										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;领取时间:</h5></td>
										<td style="width:25%;"><input type="text"
											class="form-control form-datetime" name="getTimeStart"
											style="width:44%;float:left"> <label
											style="float: left;">&nbsp;--&nbsp;</label> <input
											type="text" class="form-control form-datetime"
											name="getTimeEnd" style="width:44%;float:left"></td>
									</tr>
									<tr>
										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;优惠券名称:</h5></td>
										<td style="width:25%;"><input type="text"
											class="form-control" name="cname" style="width:90%;" /></td>

										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;获取状态:</h5></td>
										<td style="width:25%;"><select class="form-control"
											name="getStatus" style="width:90%;float:left">
												<option value="" selected="selected">全部</option>
												<option value="0">未获取</option>
												<option value="1">已获取</option>
												<option value="2">已锁定</option>

										</select></td>
										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发行时间:</h5></td>
										<td style="width:25%;"><input type="text"
											class="form-control form-datetime" name="dateIssueStart"
											style="width:44%;float:left"> <label
											style="float: left;">&nbsp;--&nbsp;</label> <input
											type="text" class="form-control form-datetime"
											name="dateIssueEnd" style="width:44%;float:left"></td>
									</tr>
									<tr>
										<td style="width:8%;"><h5>&nbsp;&nbsp;&nbsp;&nbsp;优惠券SN码:</h5></td>
										<td style="width:25%;"><input type="text"
											class="form-control" name="serial" style="width:90%;" /></td>
										
										<td colspan="2"></td>
										
										<td colspan="2"><div class="submit" style="margin-right: 70px;">
												<input class="submit radius-3" type="button" value="查询全部"
													data-bus='query' /><input class="reset radius-3"
													type="reset" value="重置全部" data-bus='reset' />
											</div></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
		<div class="panel panel-default">
			<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
				</div>
				<div id="yaoyiyaomingxiList"></div>
				
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">
      {yaoyiyaoupdate:'${ btnAu['coupon/couponissue/yaoyiyao/update/init']}',mansongupdate:'${ btnAu['coupon/couponissue/mansong/update/init']}',sendshortmessageupdate:'${ btnAu['coupon/couponissue/sendshortmessage/update/init']}'}
   </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
	inserTitle(' > 优惠券管理  > <a href="coupon/couponissue/yaoyiyaomingxi/init.jhtml" target="right">摇一摇明细</a>','activityManagement',true);
		$(".form-datetime").datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii:ss'
	  });
	</script>
	<script src="<%=path%>/js/coupon/couponissue/yaoyiyaomingxi.js"></script>
  </body>
</html>