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
    <title>版本</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">  
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">  
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr>
						<!-- 	<td style="width:50px;"><h5>&nbsp;&nbsp;id</h5></td>
							<td style="width:120px;"><input type="text" class="form-control"  name="id"  style="width:120px;"></td>
							 -->
							<td style="width:6%;"><h5>&nbsp;&nbsp;应用类型:</h5></td>
							<td style="width:16%;">
								<select class="form-control" name="apptype" style = "width:90%;">
									<option value="" >全部应用类型</option>
									<option value="1" >经销商版</option>
									<option value="2" >商户版</option>
									<option value="3" >用户版</option>
									<option value="4" >商户版(演示)</option>	
									<option value="5" >用户版(演示)</option>	
									<option value="6" >其他 </option>
								</select>
							</td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;版本类型:</h5></td>
							<td style="width:16%;">
								<select class="form-control" name="vtype" style = "width:90%;">
									<option value="" >全部版本类型</option>
									<option value="1" >Android</option>
									<option value="2" >Ios</option>
									<option value="3" >Wp</option>
									<option value="4" >其他</option>																			
								</select>
							</td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;发布时间:</h5></td>							
							<td style="width:18%;">
								<input type="text" name ="sdateTappVersionStarts" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="sdateTappVersionEnd" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							<td colspan="2">
								<div class="submit">
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
		
		<div class="btn-group">
		<c:if test="${!empty btnAu['common/appVersion/add'] }">
			<button data-toggle="dropdown" class="btn  btn-success dropdown-toggle" type="button" id="btnGroupDrop">
              	<span class="icon-plus"></span>
              	添加版本
                <span class="caret"></span>
              </button>
              <ul role="menu" class="dropdown-menu" aria-labelledby="btnGroupDrop">
                <li><a  data-type="ajax"   data-url="common/appVersion/add/init.jhtml?type=1"  data-toggle="modal" ><i title="android" class="icon-android"></i>&nbsp;&nbsp;Android</a></li>
                <li><a  data-type="ajax"   data-url="common/appVersion/add/init.jhtml?type=2"  data-toggle="modal" ><i title="apple" class="icon-apple"></i>&nbsp;&nbsp;IOS</a></li>
                
                <li><a   data-type="ajax"   data-url="common/appVersion/add/init.jhtml?type=3"  data-toggle="modal" ><i title="windows" class="icon-windows"></i>&nbsp;&nbsp;WP</a></li>
                <li><a   data-type="ajax"   data-url="common/appVersion/add/init.jhtml?type=4"  data-toggle="modal" ><i title="tablet" class="icon-tablet"></i>&nbsp;&nbsp;其他</a></li>
              </ul>
             </c:if>
            <%--  <c:if test="${!empty btnAu['common/appVersion/delete'] }">
              	<button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
              </c:if> --%>
          </div>
			<div id="appVersionList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{xg:'${btnAu['common/appVersion/update']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
 	<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
    <script src="<%=path%>/resources/upload/upload.js"></script>  
    <script src="<%=path%>/js/common/appVersion.js"></script> 

  </body>
</html>
