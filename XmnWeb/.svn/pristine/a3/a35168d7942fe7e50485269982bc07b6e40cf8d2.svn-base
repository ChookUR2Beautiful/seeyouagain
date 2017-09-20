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
    <title>成长记列表</title>
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
			<form class="form-horizontal" role="form" method="post"  id="searchForm">
				<table style="width:100%;">
					<tbody>
						<tr >
							<td style="width:5%;"><h5>&nbsp;&nbsp;话题内容:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="content" value="${param.content }" style="width:90%"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;是否显示:</h5></td>
							<td style="width:15%;">
								 <select class="form-control"  name="status" style="width:90%"> <!-- 发送状态|0=待发送|1=已发送 -->
								        <c:if test="${!empty param.status }">
								        	<option value="" >请选择</option>
											<option value="0" <c:if test="${param.status ==0}">selected="selected"</c:if> >不显示</option>
											<option value="1" <c:if test="${param.status ==1}">selected="selected"</c:if>>显示</option>
										</c:if>
										<c:if test="${empty param.status }">
								        	<option value="" selected="selected">请选择</option>
											<option value="0">不显示</option>
											<option value="1">显示</option>
										</c:if>											
								 </select>
							</td>
							<td style="width:5%;" nowrap="nowrap"><h5>&nbsp;&nbsp;话题建立时间:</h5></td>		
							<td style="width:20%;">
								<input type="text" name ="edateStart" placeholder="yyyy-MM-dd hh:mm" value="${param.edateStart}" class="form-control form-datetime"style="width:40%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="edateEnd" placeholder="yyyy-MM-dd hh:mm" value="${param.edateEnd}" class="form-control form-datetime" style="width:40%;float:left">
							</td>
							<td  style="width:30%;" >
								<div class="submit submit-sp" style="float:left; margin-left: 10px;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/topic/add']}"><a type="button" class="btn btn-success" id="addTopicBtn"  href="user_terminal/topic/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
				<%--<c:if test="${!empty btnAu['user_terminal/topic/delete']}"><button type="button" class="btn btn-danger" id="delete"><span class="icon-remove"></span>&nbsp;删除</button>
				</c:if> --%>
				<!-- <button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button> -->
				
			</div>
			<div id="topicList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{update:'${ btnAu['user_terminal/topic/update']}'<%--,delTopic:'${btnAu['user_terminal/topic/delete'] }'--%>,check:'${btnAu['user_terminal/topic/check'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/user_terminal/model/topicModel.js"></script>
	<script src="<%=path%>/js/user_terminal/topic.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		
		limitedDate({form:"#searchForm",startDateName:"edateStart",endDateName:"edateEnd"});
		
	});
	</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
  </body>
</html>
