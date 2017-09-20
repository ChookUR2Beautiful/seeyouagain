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
    <title>商家账号</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
  	<c:if test="${!empty btnAu['user_terminal/tPost/post/init']}">
      <li class="active">
        <a href="#tab1" data-toggle="tab">帖子列表</a>
      </li>
     </c:if>
     <c:if test="${!empty  btnAu['user_terminal/tPost/report/init']}">
      <li class="">
        <a href="#tab2" data-toggle="tab">举报列表</a>
      </li>
     </c:if>
     <c:if test="${!empty  btnAu['user_terminal/tPost/recycle/init'] }">  
      <li class="">
        <a href="#tab3" data-toggle="tab">回收站</a>
      </li>
     </c:if> 
    </ul>
    <div class="tab-content">
    <div id="tab1" class="tab-pane in active">
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="divideForm">
				<table style="width:100%;">
					<tbody>
						<tr><input type="hidden" name="type" value="divide"/>					
							<td style="width:5%;"><h5>&nbsp;&nbsp;帖子内容:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="content" style = "width:80%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="nname" style = "width:80%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;发布时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="lbsdateStart" style="width:35%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="lbsdateEnd" style="width:35%;float:left">
							</td>
							<td>
								<div class="submit" style="float:left; margin-left: 10px;">
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
			<c:if test="${null!=btnAu['user_terminal/tPost/post/updatePostStatus'] && btnAu['user_terminal/tPost/post/updatePostStatus']}">
			<button type="button" class="btn btn-warning" id="updates"><span class="icon-trash"></span>&nbsp;删除</button>	
			</c:if>		
			</div>
			<div id="divide">1</div>
		</div>
	</div>
	</div>
	<div id="tab2" class="tab-pane">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="withdrawForm">
				<table style="width:100%;">
					<tbody>
						<tr><input type="hidden" name="type" value="withdraw"/>	
						  	<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="nname" style = "width:80%;"></td> 					
							<td style="width:5%;"><h5>&nbsp;&nbsp;举报原因:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="reason" style = "width:80%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;发布时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="jbsdateStart" style="width:35%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="jbsdateEnd" style="width:35%;float:left">
							</td>
							<td>
								<div class="submit" style="float:left; margin-left: 10px;">
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
			<c:if test="${null!=btnAu['user_terminal/tPost/report/updatePostStatusByjb'] && btnAu['user_terminal/tPost/report/updatePostStatusByjb']}">
			<button type="button" class="btn btn-warning" id="updatesOne"><span class="icon-trash"></span>&nbsp;删除</button>
			</c:if>
			<c:if test="${null!=btnAu['user_terminal/tPost/report/updatePostStatusByHome'] && btnAu['user_terminal/tPost/report/updatePostStatusByHome']}">	
			<button type="button" class="btn btn-success" id="updatesZero"><span class="icon-reply-all"></span>&nbsp;打回</button>
			</c:if>			
			</div>
			<div id="withdraw">2</div>
			
		</div>
	</div>
	</div>

	<div id="tab3" class="tab-pane">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="hszForm">
				<table style="width:100%;">
					<tbody>
						<tr><input type="hidden" name="type" value="hsz"/>					
							<td style="width:5%;"><h5>&nbsp;&nbsp;帖子内容:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="content" style = "width:80%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:15%;"><input type="text" class="form-control"  name="nname" style = "width:80%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;发布时间:</h5></td>							
							<td style="width:25%;">
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="scsdateStart" style="width:35%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime"  name="scsdateEnd" style="width:35%;float:left">
							</td>
							<td>
								<div class="submit" style="float:left; margin-left: 10px;">
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
			<c:if test="${null!=btnAu['user_terminal/tPost/recycle/delete'] && btnAu['user_terminal/tPost/recycle/delete']}">	
			<button type="button" class="btn btn-danger" id="delete"><span class="icon-trash"></span>&nbsp;彻底删除</button>	
			</c:if>
			<c:if test="${null!=btnAu['user_terminal/tPost/recycle/updatePostStatusToOne'] && btnAu['user_terminal/tPost/recycle/updatePostStatusToOne']}">	
			<button type="button" class="btn btn-success" id="updateSzero"><span class="icon-reply-all"></span>&nbsp;恢复</button>	
			</c:if>		
			</div>
			<div id="hsz">3</div>
			
		</div>
	</div>
	</div>
	
	
	</div>
<script type="text/json" id="permissions">{tzlb:'${btnAu['user_terminal/tPost/post/init']}',tzlbxq:'${btnAu['user_terminal/tPost/tPostComment/post/init']}',tzlbsc:'${btnAu['user_terminal/tPost/post/updatePostStatus'] }',
										   jblb:'${btnAu['user_terminal/tPost/report/init']}',jblbxq:'${btnAu['user_terminal/tPost/tPostComment/report/init'] }',jblbsc :'${btnAu['user_terminal/tPost/report/updatePostStatusByjb']}',jblbdh :'${btnAu['user_terminal/tPost/report/updatePostStatusByHome'] }',
										   hszlb:'${btnAu['user_terminal/tPost/recycle/init']}',hszxq :'${btnAu['user_terminal/tPost/tPostComment/recycle/init'] }', hszsc :'${btnAu['user_terminal/tPost/recycle/delete'] }', hszdh :'${btnAu['user_terminal/tPost/recycle/updatePostStatusToOne'] }'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/user_terminal/post.js"></script>
  </body>
</html>