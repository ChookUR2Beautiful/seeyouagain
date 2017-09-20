<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>已开放区域商家信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">

<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: Verdana, Geneva, sans-serif;
	overflow-y: auto;
	padding: 10px 30px;
}
.data table.info tr td {
	font-size: 12px
}

.data table,info{
	word-break:break-all; 
	word-wrap:break-word;
}

.data table.info tr th {
	font-size: 12px;
	line-height: 15px;
}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(widgets/page/image/load-b.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="panel">
		<div class="panel-body">
				<form class="form-horizontal" role="form" id = "manageDetailsFromId">
				<div>1111${id}</div>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td width="70"><h5>加入时间:</h5></td>
							<td width="360">
								<input type="text" name ="signdateStart" placeholder="选择或者输入一个日期+时间：yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:150px;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="signdateEnd" placeholder="选择或者输入一个日期+时间：yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:150px;float:left">
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td colspan="2">
								<button  type="button" class="btn btn-primary" style="float:right" id="manageDetailsSearch">搜索</button>
							</td>
							<td colspan="2"></td>
							<td >
								<button type="reset" class="btn btn-primary" style="float:left">重置</button>
							</td> 
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	
	<div>
		<div class="btn-group">
                <button class="btn btn-default" onclick="changePage(1);">折扣</button>
                <button class="btn btn-default" onclick="changePage(2);">会员</button>
                <button class="btn btn-default" onclick="changePage(3);">订单</button>
                <button class="btn btn-default" onclick="changePage(4);">销售额</button>
                <button class="btn btn-default" onclick="changePage(5);">营业额</button>
                <button class="btn btn-default" onclick="changePage(6);">佣金</button>
                <button class="btn btn-default" onclick="changePage(7);">已提现</button>
         </div>
		<div class="panel-body data">
			<!-- 折扣 -->
			<div id="manageDetailsDiv" style = "display: none;"></div>
			<!-- 会员 -->
			<div id="memberDiv" style = "display: none;" ></div>
			<!-- 订单 -->
			<div id="orderDiv" style = "display: none;" ></div>
			<!-- 销售额 -->
			<div id="salesVolumeDiv" style = "display: none;"></div>
			<!-- 营业额-->
			<div id="turnoverDiv" style = "display: none;" ></div>
			<!-- 佣金-->
			<div id="commissionDiv" style = "display: none;" ></div>
			<!-- 已提现 -->
			<div id="drawMoneyDiv" style = "display: none;" ></div>
		</div>
	</div>
	<script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
	<script src="<%=path%>/resources/zui/js/zui.js"></script>
	<script src="<%=path%>/resources/zui/js/zui.lite.min.js"></script>
	<script src="<%=path%>/resources/zui/js/zui.lite.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/js/businessman/manageDetails.js"></script>
</body>
</html>
