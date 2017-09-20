<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>连锁店列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
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
.submit{text-align: left;}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
		
			<form class="form-horizontal" role="form"  id="sellerPackageFromId">
			  <input type="hidden" name="ismultiple"  value="1"/>
				<table style="width:100%;">
					<tbody>
						<tr>
						    <td style="width:6%;"><h5>搜索套餐名称:</h5></td>
							<td style="width:20% !important;">
							    <input type="text" class="form-control"  value="${param.title}" name="title"  style="width:80%">
							</td>
							
							<td style="width:100px;"><h5>套餐状态：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="status" value="${param.status}" style = "width:75%;">
									    <option value = "">--请选择--</option>
						                <!-- <option value = "0">未验证</option> -->
						                <option value = "1" ${param.status==1?"selected":""}>出售中</option>
						                <option value = "2" ${param.status==2?"selected":""}>下架</option>
						                <option value = "3" ${param.status==3?"selected":""}>售罄</option>
						             </select>
						   </td>	
				           <td style="width:5%;"><h5>销售时间:</h5></td>
                           <td style="width:30% ;">
								<input type="text" name ="saleStartTime" value="${param.saleStartTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:32%;float:left" >
						        <label style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</label>
						        <input type="text" name ="saleEndTime" value="${param.saleEndTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:32%;float:left">
						   </td>
						</tr>
						
						<tr>
						   <td style="width:6%;"><h5>关联的店铺：</h5></td>
						   <td style="width:400px;">
                               <select class="form-control" id="sellerId" name="sellerid" 
										initValue="${param.sellerid}" style="width:41%;float:left"></select>
						    </td>	
				            <td style="width:5%;"><h5>使用时间:</h5></td>
                            <td style="width:30% ;">
                                <input type="text" name ="useStartTime" value="${param.useStartTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:35%;float:left" >
						        <label style="float: left;">&nbsp;&nbsp;-&nbsp;&nbsp;</label>
						        <input type="text" name ="useEndTime" value="${param.useEndTime}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:35%;float:left">
						    </td>
						    <td colspan="2" style="text-align: right; ">
								<div class="submit">
								   <input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								   <input  class="reset radius-3" type="reset" value="重置全部"  data-bus = 'reset' />
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
		        <!-- -->
				<c:if test="${null!=btnAu['sellerPackage/manage/add'] && btnAu['sellerPackage/manage/add']}">
				      <a type="button"  class="btn btn-primary"  id="addSellerPackageBto" href="sellerPackage/manage/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a></c:if>
			    <c:if test="${null!=btnAu['sellerPackage/manage/onLine'] && btnAu['sellerPackage/manage/onLine']}"><button type="button" class="btn btn-success" id="beachOnLine" ><span class="icon-hand-up"></span>&nbsp;&nbsp;上架&nbsp;&nbsp;</button></c:if>
				<c:if test="${null!=btnAu['sellerPackage/manage/downLine'] && btnAu['sellerPackage/manage/downLine']}"><button type="button" class="btn btn-danger" id="beachDownLine" ><span class="icon-hand-down"></span>&nbsp;&nbsp;下架&nbsp;&nbsp;</button> </c:if>
			    <c:if test="${null!=btnAu['sellerPackage/manage/recommend'] && btnAu['sellerPackage/manage/recommend']}"><button type="button" class="btn btn-warning" id="beachRecommended" ><span class="icon icon-off"></span>&nbsp;&nbsp;设置重点推荐&nbsp;&nbsp;</button> </c:if>
			</div>
			<div id="sellerPackageInfoDiv"></div>
		</div>																																				
	</div>
	
	<script type="text/json" id="permissions">
		{xg:'${ btnAu['sellerPackage/manage/update'] }',
         del:'${btnAu['sellerPackage/manage/delete'] }',
         view:'${btnAu['sellerPackage/manage/view']}',

         sj:'${btnAu['sellerPackage/manage/onLine']}',
         xj:'${btnAu['sellerPackage/manage/downLine']}',
         tj:'${btnAu['sellerPackage/manage/recommend']}'}
    </script>
    
    
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/sellerPackage/sellerPackageList.js?v=1.0.5"></script>
	
</body>
</html>
