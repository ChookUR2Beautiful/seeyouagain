<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
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
<title>添加商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">


<style type="text/css">
body,table,tbody,td,th,tr,div,ul,li,h1,h2,h4,h5{
	margin:0;
	padding:0;
	border:0;
	font-size:13px;
	line-height:1.53846;
}
body{
	padding:10px 30px;
	background-color:#EEE;
}
.content{
	width:auto;
	height:auto;
	border:2px;
}
td, th {
    padding: 10px;
}
td h5{ 
	font-weight:bold;
	text-align:right;
}
.myPanel{
	border:0;
	background-color:#FFF;
}
ul li a{
	font-size:16px;
}

.propertyTable{
	border:solid #ddd; border-width:1px 0px 0px 1px;
	cellpadding:1px;
	text-align:center;
}
.propertyTable td{
	background:#fff;
	border:solid #ddd; border-width:0px 1px 1px 0px;
}
.propertyTable caption{
	text-align:center;
}
.propertyTable thead {
	font-weight: 900;
}
.propertyTable tbody input{
	width: 90px;
	text-align: center;
}
input[type="number"]{-moz-appearance:textfield;}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button{
    -webkit-appearance: none !important;
    margin: 0;
}
</style>
</head>

<body>
	<div class="content">
		
		<div class="panel panel-default myPanel">
		<input type="hidden" id="pid" value="${freshInfo.pid}">
		<input type="hidden" id="codeId" value="${freshInfo.codeId}">
		<input type="hidden" id="pname" value="${freshInfo.pname}">
		<input type="hidden" id="type" value="${freshInfo.type}">
		<input type="hidden" id="maxStore" value="${freshInfo.maxStore==null?freshInfo.store:freshInfo.maxStore}">
			<form class="form-horizontal" role="form" id="editGroupFrom">
				<div id="myTabContent" class="tab-content panel-body">
					<div class="tab-pane fade in active" id="info">
									<div style="width: 50%; display: inline;" id="propertyTab">
										<div style="margin-top: 2px;">
										<table class="table table-borderless" >
											<td><h5>积分价<font color="red">*</font></h5></td>
											<td>
												<div class="input-group" style="width: 80%;">
													<input type="number" onkeyup="checkNum(10000000,this)"  onchange="checkNum(100000000,this)" class="form-control"   class="form-control" id="cash" placeholder="￥" name="cash" value="${freshInfo.cash}" 
														style="border-radius:4px;">
													<span class="input-group-addon fix-border" style="background-color: white;border: hidden;"> + </span>
													<input type="number" onkeyup="checkNum(10000000,this)"  onchange="checkNum(100000000,this)" class="form-control" class="form-control" id="integral" placeholder="积分" name="integral" value="${freshInfo.integral}" style="border-radius:4px;">
												</div>
											</td>
											<td><h5>排序<font color="red">*</font></h5></td>
											<td>
												<div class="input-group" style="width: 80%;">
													<input type="number"   class="form-control" id="sort" placeholder="排序" name="sort" value="${freshInfo.choiceSort}" style="border-radius:4px;" >
												</div>
											</td>
											<c:if test="${empty freshInfo.propertyList}">
												<td><h5>库存<font color="red">*</font></h5></td>
											<td>
												<div class="input-group" style="width: 80%;">
													<input type="number"   class="form-control" id="store" placeholder="商品库存" name="store" value="${freshInfo.store}" style="border-radius:4px;" onkeyup="checkNum(${freshInfo.maxStore==null?freshInfo.store:freshInfo.maxStore},this)" onchange="checkNum(${freshInfo.maxStore==null?freshInfo.store:freshInfo.maxStore},this)">
												</div>
											</td>
											</c:if>
										</table>
									
											<table class="propertyTable" class="propertyTable">
												<c:if test="${!empty freshInfo.propertyList}">
												<caption><font style="font-weight: 900;">产品属性参数表</font></caption>
												<thead>
												<tr>
													<c:forEach items="${freshInfo.propertyList}" var="saleProp" varStatus="spVs">
														<td>${saleProp.property}<%-- <input type="hidden" name="propertyList[${spVs.index}].id" value="${saleProp.id}"/> --%></td>
													</c:forEach>
													<td>加价金额</td>
													<td>库存</td>
													<td>排序</td>
												</tr>
												</thead>
												</c:if>
												<tbody>
												<c:if test="${!empty freshInfo.saleGroupList}">
													<c:forEach items="${freshInfo.saleGroupList}" var="saleGroup" varStatus="gvs">
														<tr class="tableList">
															<c:forEach items="${saleGroup.pvValues}" var="pvValue" varStatus="pvs">
																<td>${pvValue}<input name="saleGroupList[${gvs.index}].pvValues[${pvs.index}]" type="hidden" value="${pvValue}"/>
																</td>
															</c:forEach>
															<td><input type="number" class="form-control" name="saleGroupList[${gvs.index}].amount"  value="${saleGroup.amount}" style="height: 28px;" />
															</td>
															<td><input type="number" onkeyup="checkNum(${saleGroup.maxStock==null?saleGroup.stock:saleGroup.maxStock},this)" onchange="checkNum(${saleGroup.maxStock==null?saleGroup.stock:saleGroup.maxStock},this)" class="form-control" name="saleGroupList[${gvs.index}].stock" value="${saleGroup.stock}" style="height: 28px;"/>
																<input type="hidden" name="saleGroupList[${gvs.index}].maxStock"  value="${saleGroup.stock==null?saleGroup.maxStock:saleGroup.stock}"  />
															</td>
															<td><input type="number"  class="form-control" name="saleGroupList[${gvs.index}].sort" value="${saleGroup.sort}" style="height: 28px;" />
																<input name="saleGroupList[${gvs.index}].id" value="${saleGroup.id}" type="hidden" />
																<input name="saleGroupList[${gvs.index}].pvIds" value="${saleGroup.pvIds}" type="hidden" />
																<input name="saleGroupList[${gvs.index}].codeId" value="${saleGroup.codeId}" type="hidden" />
															</td>
														<tr>
													</c:forEach>
												</c:if>
												</tbody>
											</table>
											
										</div>
									</div>
							<div style="width: 15%; clear: both;"></div>
						</div>
				</div>
			</form>				
				<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="button" id="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
			
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=path%>/js/fresh/groupEdit.js"></script>
</html>
