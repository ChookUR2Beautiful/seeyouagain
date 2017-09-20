<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>
<link rel="stylesheet" href="<%=path%>/resources/css/jedate.css"/><link>
<link rel="stylesheet" href="<%=path%>/resources/css/main.css"/><link>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm"
				method="post">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%"><h5>关键字：</h5></td>
							<td style="width:10%"><select class="form-control"
								style="width:90%" id="option" onchange="setName()">
									<option value="1">订单号</option>
									<option value="2">收货人姓名</option>
									<option value="3">收货人手机</option>
							</select></td>
							<td style="width:10%"><input type="text" id="selArea"
								class="form-control" style="width:90%" name="orderNo">
							</td>
							<td style="width:5%"><h5>下单时间：</h5></td>
							<td style="width:17%;">
								<input type="text" name ="createTime" placeholder="yyyy-MM-dd HH:mm" class="form-control form-datetime"style="width:41%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="createTimeEnd" placeholder="yyyy-MM-dd HH:mm" class="form-control form-datetime" style="width:42%;float:left"></td>
							</td>	
						<tr>
							<td style="width:5%"><h5>订单类型：</h5></td>
							<td style="width:10%"><select class="form-control"
								name="type" style="width:90%">
									<option value="">全部订单</option>
									<option value="001">平面物料</option>
									<option value="002">专人定制</option>
							</select></td>
							<td style="width:5%"><h5>订单状态：</h5></td>
							<td style="width:10%"><select class="form-control" id = "status"
								name="status" style="width:90%">
									<option value="">全&nbsp;&nbsp;部</option>
									<option value="1">待付款</option>
									<option value="2">待确定</option>
									<option value="3">待发货</option>
									<option value="4">已发货</option>
									<option value="5">已完成</option>
									<option value="6">已关闭</option>
									<option value="7">售后</option>
							</select></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button" value="查询全部"
										data-bus='query' /> <input class="reset radius-3"
										type="reset" value="重置全部" data-bus='reset' />
								</div>
							</td>
						</tr>
						<tr>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-success"
					onclick="queryBM(this,'');" name="bumen">全&nbsp;部</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'1');" name="bumen">待协商/待付款</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'2');" name="bumen">待确定</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'3');" name="bumen">待发货</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'4');" name="bumen">已发货</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'5');" name="bumen">已完成</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'6');" name="bumen">已关闭</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="queryBM(this,'7');" name="bumen">售&nbsp;&nbsp;后</button>
				<c:if
					test="${!empty btnAu['materialOrder/manage/export']}">
					<button type="button" id="export" class="btn btn-default">
						<span class="icon-download-alt"></span>&nbsp;excel导出
					</button>
				</c:if>
			</div>
			<div id="materialOrderList"></div>
		</div>
		<!--弹出层-->
<div class="shade-box"></div>
<div class="shade-content" id="modle1">
    <div class="content-box">
        <span class="close-shade">×</span>
        <div class="customization-box">
         <h2>商品详情</h2>
            <ul class="introduction-list">
                <li><label>规格：</label><span id="group"></span></li>
                <li><label>印刷数量：</label><span id="num"></span></li>
                <li><label>订单金额：</label><span id="price"></span></li>
                <li><label>商品名称：</label><span id="title"></span></li>
            </ul>
            <ul class="img-box" id="commonUrl">
            </ul>
            <ul>
            <li><label>下单时间：</label><span id="createTime"></span></li>
            </ul>
        </div>
    </div>
</div>

<div class="shade-box"></div>
<div class="shade-content" id="modle2">
    <div class="content-box">
        <span class="close-shade">×</span>
        <div class="customization-box">
           <h2>定制详情</h2>
            <ul>
                <li><label>预算：</label><span id="budget"></span></li>
                <li><label>主色调：</label><span id="mainColor"></span></li>
                <li><label>副色调：</label><span id="secColor"></span></li>
                <li><label>类型：</label><span id="materialType"></span></li>
                <li><label>需求描述：</label><span id="remarks"></span></li>
             </ul>
             <ul class="img-box" id="customizeUrls">
            </ul>
             <ul>
                <li><label>要求完成日期：</label><span id="finishTime"></span></li>
            </ul>
        </div>
    </div>
</div>
	</div>
	<script type="text/javascript">
	function setName(){
		var option = $("#option").val();
		if(option == 1){
			$("#selArea").attr("name","orderNo");
		}
		if(option == 2){
			$("#selArea").attr("name","consignee");
		}
		if(option == 3){
			$("#selArea").attr("name","mobile");
		}
	}
/* 	$(function(){
		//点击查看商品
        $(".examine-btn").on("click",function () {
            $(".shade-box,.shade-content").show();
        });
	}) */
	

	</script>
	<script type="text/json" id="permissions">{orderExport:'${btnAu['materialOrder/manage/export']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/js/cloud_design/order/orderlist.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
	<script src="<%=path%>/resources/datapicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>
