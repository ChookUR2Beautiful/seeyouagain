<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>岗位列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/web/css/jquery.validate.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="recruitStationFormId">
				<input style="display:none;" id="status" name="status" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>岗位名称：</h5></td>
							<td style="width:18%">
								<input type="text" class="form-control"  name="stationName"  value="${param.stationName}" style="width:89%">
							</td>
							<td style="width:5%;"><h5>商家ID：</h5></td>
							<td style="width:23%">
								<input type="text" class="form-control"  name="sellerId"  value="${param.sellerId}" style="width:92.5%">
							</td>
							<td style="width:5%;"><h5>工作城市：</h5></td>
							<td style="width:23%">
								<div class="input-group" id="searld" style="width:91%" 
									<c:choose>
									    <c:when test="${!empty recruitStation.city}">
									    	initValue=" ${param.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${param.province}"
									    </c:otherwise>
									 </c:choose>
								 >
								</div>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>店铺名称：</h5></td>
							<td style="width:18%">
								<input type="text" class="form-control"  name="sellerName"  value="${param.sellerName}" style="width:89%">
							</td>
							<td style="width:5%;"><h5>联系人：</h5></td>
							<td style="width:23%">
								<input type="text" class="form-control"  name="contact"  value="${param.contact}" style="width:92.5%">
							</td>
							<td style="width:5%;"><h5>学历：</h5></td>
							<td style="width:23%">
								<select class="form-control" tabindex="2" name ="degrees" style="width: 89%" id="degId">
									<option value="">-请选择-</option>
									<option value = "0">小学</option>
								    <option value = "1">初中</option>
								    <option value = "2">高中</option>
								    <option value = "3">大专</option>
								    <option value = "4">本科以上</option>
							    </select>
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>发布时间:</h5></td>
							<td style="width:23%;">
								<input type="text" class="form-control" id="sdate" placeholder="yyyy-MM-dd hh:mm" name="sdate" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'edate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd HH:mm'})" style="width:42%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  	<input type="text" class="form-control" id="edate" placeholder="yyyy-MM-dd hh:mm" name="edate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm'})" style="width:42%;float:left"/>
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query';"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset';"/>
								</div>
							</td>
							<td colspan="4"></td>	
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
			<div class="btn-group" style="margin-bottom: 5px;">
				<button type="button" class="btn btn-default status" onclick="queryStatus(this,0);">&nbsp;已发布</button>
				<button type="button" class="btn btn-default status" onclick="queryStatus(this,1);">&nbsp;已删除</button>
				<c:if test="${btnAu['jobmanage/recruitStation/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="recruitStationList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{update:'${btnAu['jobmanage/recruitStation/update']}',delete:'${btnAu['jobmanage/recruitStation/delete']}'}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/jobmanage/recruitStationList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
    <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>
