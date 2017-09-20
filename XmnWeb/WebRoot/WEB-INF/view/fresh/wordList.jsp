<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>活动</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
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
			<form class="form-horizontal" role="form" method="post" id="searchForm">
				<input type="hidden" id="status" name="proceedStatus"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>关键字来源:</h5></td>
							<td style="width:18%;">
								<select class="form-control" style = "width:90%;" name="type">
									<option value="">--请选择--</option>
									<option value="4">真实搜索</option>
									<option value="2">手动添加</option>
								</select>
							</td>
							<td style="width:5%;"><h5>关键词</h5></td>
							<td style="width:18%;">
									<input type="text" class="form-control"  name="word" style="width:90%;"/>
								</td>
					  		<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
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
					<c:if test="${null!=btnAu['fresh/word/add']}">
						<button  class="btn btn-success" data-type="ajax" data-toggle="modal" data-title="添加关键字"  data-url="fresh/word/edit/init.jhtml" ><span class="icon-plus"></span>&nbsp;添加关键字</button>
					</c:if>
					<c:if test="${null!=btnAu['fresh/word/edit/init']}">
						<button  class="btn btn-primary" data-type="ajax" data-toggle="modal" data-title="修改默认搜索"  data-url="fresh/word/edit/init.jhtml?type=3" ><span class="icon-edit"></span>&nbsp;修改默认搜索</button>
					</c:if>
			</div>
			<div id="wordList"></div>
		</div>
	</div>
	</body>
	<div id="hidden_modal"></div>
	<script type="text/json" id="permissions">
{
	edit:'${ btnAu['fresh/word/edit/init']}',
	add:'${ btnAu['fresh/word/add']}',
	delete:'${btnAu['fresh/word/delete']}'
}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
		<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/js/fresh/wordList.js"></script>
	<!-- 引入时间插件 -->
  
</html>
