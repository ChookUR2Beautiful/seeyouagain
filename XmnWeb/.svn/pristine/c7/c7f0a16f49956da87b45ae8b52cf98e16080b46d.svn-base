<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商户列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">

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
input[type='radio'],input[type='checkbox']{
	    margin: 0 5px 0 10px;
}
.submit{text-align: left;}
.addnewModal label{
	    width: 112px;
    text-align: right;
    margin-right: 10px;
}
.updateModal label{
	    width: 140px;
    text-align: left;
    margin-left: 10px;
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
			<form class="form-horizontal" role="form"  id="userForm">
				<input type="hidden" name="cid" value="${userCoupon.cid}">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;店铺名称:</h5></td>
							<td style="width:19%;">
								<input type="text" class="form-control"  id="sellerName" name="sellerName"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;所属名称:</h5></td>
							<td style="width:19%;">
								<input type="text" class="form-control"  id=phone name="phone"  style="width:90%">
							</td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;储值卡类型:</h5></td>
							<td style="width:18%;"><select style="width:90%" 
									class="form-control" id="sellerType" name="sellerType">
										<option value="">全部</option>
										<option value="1">单店</option>
										<option value="2">连锁店</option>
										<option value="3">区域代理</option>
								</select></td>
							<td colspan="2" style="width:50%;">
								<div class="submit">&nbsp;&nbsp;
								<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
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
			<c:if test="${!empty btnAu['businessman/valueCard/add']}">
			<a type="button" id="addSellerCard" class="btn btn-success"  onclick="addValueCardModal();"><span class="icon-plus"></span>&nbsp;设置储值卡</a>
			</c:if>
			</div>
			<div id="userFormDiv"></div>
		</div>
	</div>
	</div>
	
	<!-- 新增摸态框 -->
	<div class="modal fade" id="addValueCardModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title" id="addModalLabel">设置储值卡</h4>
				</div>
				<div class="modal-body addnewModal">
					<form class="form-horizontal" role="form" id="updateChoiceSortForm">
						<div class="form-group">
							<p>
								&nbsp;&nbsp;<label style="font-size:14px">选择类型:</label>
								<input type="radio" name="sellerType" value="1">&nbsp;&nbsp;<span style="font-size:14px">单店</span>
								<input type="radio" name="sellerType" value="2">&nbsp;&nbsp;<span style="font-size:14px">连锁店</span>
								<input type="radio" name="sellerType" value="3">&nbsp;&nbsp;<span style="font-size:14px">区域代理</span>
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:15px">选择商户:</label>
								<span id="par" style="width:50%;">
								<select class="form-control" id="sellerid" name="sellerid" style="width:50%;"></select>
								<input type="hidden" id="sellerName" name="sellerName">
								</span>
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:15px">下级充值获得:</label>
								<input type="text" class="form-control" id="referrerRatio" name="referrerRatio" style="width:50%">
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:15px">下下级充值获得:</label>
								<input type="text" class="form-control" id="parentReferrerRatio" name="parentReferrerRatio" style="width:50%">
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:14px">累计限额:</label>
								<input type="radio" name="limitRecharge" value="500000"><span style="font-size:14px">50万</span>
								<input type="radio" name="limitRecharge" value="1000000"><span style="font-size:14px">100万</span>
							</p>
							<div id="rechargeValue"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button id="addValueCard" type="button" class="btn btn-default" onclick="addValueCard();">开通储值卡</button>
				</div>
				<div id="scrollTablel"></div>
			</div>
		</div>
     </div>
     
     <!-- 修改模态框 -->
     <div class="modal fade" id="updateValueCardModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title" id="updateModalLabel">修改储值卡</h4>
				</div>
				<div class="modal-body updateModal">
					<form class="form-horizontal" role="form" id="updateChoiceSortForm">
						<div class="form-group">
						<input type="hidden" id="updateSellerid" name="updateSellerid">
							<p>
								&nbsp;&nbsp;<label style="font-size:14px">选择类型:</label>
								<label id="typeText"></label>
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:15px">下级充值获得:</label>
								<label id="fText" ></label>
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:15px">下下级充值获得:</label>
								<label id="sText"></label>
							</p>
							<p>
								&nbsp;&nbsp;<label style="font-size:14px">累计限额:</label>
								<input type="radio" name="updateLimitRecharge" value="500000"><span style="font-size:14px">50万</span>
								<input type="radio" name="updateLimitRecharge" value="1000000"><span style="font-size:14px">100万</span>
							</p>
							<div id="updateRechargeValue"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button id="updateValueCard" type="button" class="btn btn-default" onclick="updateValueCard();">修改储值卡</button>
				</div>
				<div id="scrollTablel"></div>
			</div>
		</div>
     </div>
     	<!-- 释放储值卡-->
	<div class="modal fade" id="freeValueCardModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 34%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title" id="freeModalLabel">释放储值卡</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="freeSellerCard">
						<div class="form-group">
							<div>
								&nbsp;&nbsp;<label style="font-size:15px">&nbsp;&nbsp;释放商户名称:</label>
								<span id="name" style="font-size:15px"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="font-size:15px" >释放储值卡金额:</label>
								<span id="totalRecharge" style="font-size:15px">&nbsp;&nbsp;</span>
								<input type="hidden" id = "freeSellerid" name="freeSellerid"/>
								<input type="hidden" id = "freeSellertype" name="freeSellertype"/>
							</div>
							<div>
								<label style="color: red">*释放后，当前商户将取消储值卡功能，并已充值的会员未使用的储值卡额度，将会进行释放到公共额度中</label>
							</div>
							<div>
								  &nbsp;&nbsp;<label style="font-size:16px"><span>验证码：</span><input type="text" height="15px" name="ucaptcha"/><a href="javascript:;"> &nbsp;&nbsp;<img id="Kaptcha" src="Kaptcha.jpg" alt="看不清？换一张！" title="看不清？换一张！" ></a></label>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button id="addValueCard" type="button" class="btn btn-default" onclick="freeValueCard();">确定释放</button>
				</div>
			</div>
		</div>
     </div>
     <script type="text/json" id="permissions">
		{close:'${btnAu['businessman/valueCard/close'] }',add:'${btnAu['businessman/valueCard/add'] }',childList:'${btnAu['businessman/valueCard/childSellerInit']}',recordList:'${btnAu['businessman/rechargeDetail/init']}',limit:'${btnAu['businessman/valueCard/limit'] }'}
    </script>
     <script type="text/javascript">
     contextPath = '${pageContext.request.contextPath}';
     </script>
 	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/businessman/valueCard/valueCardList.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
</body>
</html>
