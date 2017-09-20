<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>总店列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="splitShopFromId">
			<input type = "hidden" id = "sellerid" name = "sellerid" value = "${sellerid}" >
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:40px;"><h5>&nbsp;&nbsp;商家名称:</h5></td>
							<td style="width:160px;">
							<input type="text" class="form-control"  name="sellername"  style="width:90%"></td>
							
							<td style="width:40px;"><h5>&nbsp;&nbsp; 加入时间:</h5></td>							
							<td style="width:170px;">
							<input type="text" name ="signdateStart" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:43%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="signdateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:43%;float:left">
							</td>
							<td style="width:40px;"><h5>&nbsp;&nbsp;所属区域：</h5></td>
							<td style="width:160px;"><input type="text" class="form-control"  name="title"  style="width:90%"></td>
											
							<td style="width:40px;"><h5>&nbsp;&nbsp;&nbsp;状态：</h5></td>
								<td style="width:160px;">
										<select class="form-control" name ="status" style = "width:90%;">
										    <option value = "">请选择</option>
							                <!-- <option value = "0">未验证</option> -->
							                <option value = "1">审核中</option>
							                <option value = "2">未通过</option>
							                <option value = "3">已签约</option>
							                <option value = "4">未签约</option>
							             </select>
								</td>
							<!-- <td style="width:60px;"><h5>&nbsp;&nbsp; 销售额:</h5></td>
							<td style="width:180px;">
							<input type="text" class="form-control"   style="width:180px;float:left">
						    <label style="float: left;">&nbsp;--&nbsp;</label>
						    <div></div>
							<input type="text" class="form-control"   style="width:180px;float:left">
							</td> -->
							<!-- <td style="width:60px;"><h5>&nbsp;&nbsp;账号：</h5></td>
							<td style="width:180px;"><input type="text" class="form-control"  name="nname"  style="width:180px"></td> -->
													
						</tr>
						<tr style="height: 20px"></tr>
						<tr>
							<td colspan="8" style="text-align: right; ">
								<div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<!-- <button type="button" class="btn btn-success" id="passId"></span>批量通过
				</button> 
				<button type="button" class="btn btn-danger" id="notPassId">
					</span>批量不通过
				</button>  -->
				
			</div>
			<div id="splitShopDiv"></div>
		</div>
	</div>
	</div>
	<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<%-- <script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
	<script src="<%=path%>/resources/zui/js/zui.js"></script>--%>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<%-- <script src="<%=path%>/resources/page/page.js"></script>  --%>
	<script src="<%=path%>/js/businessman/splitShop.js"></script>
</body>
</html>
