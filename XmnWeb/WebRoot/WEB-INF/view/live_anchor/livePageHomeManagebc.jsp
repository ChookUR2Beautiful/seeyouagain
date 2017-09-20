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
<title>首页推荐管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 

<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">预告推荐</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">直播推荐</a></li>
		<li class=""><a href="#tab3" data-toggle="tab">回放推荐</a></li>
		<li class=""><a href="#tab4" data-toggle="tab">精彩视频推荐</a></li>
		<li class=""><a href="#tab5" data-toggle="tab">好看推荐</a></li>
	</ul>
	
	<div class="tab-content">
		<input type="hidden" id="operationType" name="operationType" value="${operationType }">
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="advanceSearchForm">
						<!-- 直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 无回放 -->
						<input type="hidden" id="zhiboType" name="zhiboType" value="0">
						<input type="hidden" id="recommended" name="recommended" value="1">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
										<td style="width:5%;"><h5>商家:</h5></td>
										<td style="width:30%;"><input type="text" class="form-control"  name="sellername" style = "width:66%;"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播日期:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control form_datetime"  name="liveDate" style = "width:85%;"></td>
										<td style="width:5%;"><h5></h5></td>							
										<td style="width:25%;"> </td>	
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
		
						<c:if test="${!empty param.page}">
							<input type="hidden" value="${param.page}" name="page"/>
						</c:if>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['livePageHome/manage/add'] && btnAu['livePageHome/manage/add']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="添加预告推荐" data-url="livePageHome/manage/add/init.jhtml?zhiboType=0"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;添加推荐
							</button>
						</c:if>
					</div>
					<div id="advanceList"></div>
				</div>
			</div>
		</div>
		
		<div id="tab2" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="underwaySearchForm">
						<!-- -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播 -->
					<input type="hidden" id="zhiboType" name="zhiboType" value="1">
					<input type="hidden" id="recommended" name="recommended" value="1">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
										<td style="width:5%;"><h5>商家:</h5></td>
										<td style="width:30%;"><input type="text" class="form-control"  name="sellername" style = "width:66%;"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播日期:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control form_datetime"  name="liveDate" style = "width:85%;"></td>
										<td style="width:5%;"><h5></h5></td>							
										<td style="width:25%;"> </td>	
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
		
						<c:if test="${!empty param.page}">
							<input type="hidden" value="${param.page}" name="page"/>
						</c:if>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['livePageHome/manage/add'] && btnAu['livePageHome/manage/add']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="添加直播推荐" data-url="livePageHome/manage/add/init.jhtml?zhiboType=1"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;添加推荐
							</button>
						</c:if>
					</div>
					<div id="underwayList"></div>
				</div>
			</div>
		</div>
		
		<div id="tab3" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="playbackSearchForm">
						<!-- -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4历史通告 5结束直播 -->
					<input type="hidden" id="zhiboType" name="zhiboType" value="3">
					<input type="hidden" id="recommended" name="recommended" value="1">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播编号:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
										<td style="width:5%;"><h5>商家:</h5></td>
										<td style="width:30%;"><input type="text" class="form-control"  name="sellername" style = "width:66%;"></td>
									</tr>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;直播日期:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control form_datetime"  name="liveDate" style = "width:85%;"></td>
										<td style="width:5%;"><h5></h5></td>							
										<td style="width:25%;"> </td>	
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
		
						<c:if test="${!empty param.page}">
							<input type="hidden" value="${param.page}" name="page"/>
						</c:if>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['livePageHome/manage/add'] && btnAu['livePageHome/manage/add']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="添加回放推荐" data-url="livePageHome/manage/add/init.jhtml?zhiboType=3"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;添加推荐
							</button>
						</c:if>
					</div>
					<div id="playbackList"></div>
				</div>
			</div>
		</div>
		
		<div id="tab4" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="anchorVideoSearchForm">
					<!-- <input type="hidden" id="recommended" name="recommended" value="1"> -->
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;标题:</h5></td>							
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										<td style="width:5%;"><h5>&nbsp;&nbsp;主播昵称:</h5></td>
										<td style="width:25%;"><input type="text" class="form-control"  name="anchorName" style = "width:85%;"></td>
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
		
						<c:if test="${!empty param.page}">
							<input type="hidden" value="${param.page}" name="page"/>
						</c:if>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['livePageHome/manage/add'] && btnAu['livePageHome/manage/add']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="添加预告推荐" data-url="livePageHome/manage/addAnchorVideo/init.jhtml"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;添加推荐
							</button>
						</c:if>
					</div>
					<div id="anchorVideoList"></div>
				</div>
			</div>
		</div>
		
		<!-- 好看推荐 -->
        <div id="tab5" class="tab-pane">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="searchSpecialForm">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:5%;"><h5>&nbsp;&nbsp;专题名称:</h5></td>							
										<td style="width:25%;"><input type="text" class="form-control"  name="title" style = "width:85%;"></td>
										
										<td colspan="2" style="width:35%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
		
						<%-- <c:if test="${!empty param.page}">
							<input type="hidden" value="${param.page}" name="page"/>
						</c:if> --%>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${null!=btnAu['livePageHome/manage/add'] && btnAu['livePageHome/manage/add']}">
							<a type="button" style="float:left" id="addSpecial_btn"
							   class="btn btn-success" data-position="100px" data-toggle="modal"
							   data-target="#editSpecialModal"><span class="icon-plus"></span>&nbsp;添加专题</a>
						</c:if>
					</div>
					<div id="recommendSpecialList"></div>
				</div>
			</div>
		</div>		
	</div>
    
    <div class="modal fade" id="editSpecialModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加专题推荐</h4>
				</div>
				<div class="modal-body example" style="height: 230px;">
					<form class="form-horizontal" id="recommendSpecialForm">
					   <%-- <input type="hidden" id="isType" name="isType" value="${isType}"/> --%> 
					   <%--  <input type="hidden" id="id" name="id" value="${isType}"/>  --%>
                       <div class="form-group">
							<!-- <label for="exampleInputPassword5" class="col-sm-3">添加专题推荐：<em class="em-tips-font">*</em></label> -->
							<label class="col-md-3 control-label">添加专题推荐： <span
						           style="color:red;">*</span></label>
							<div class="col-sm-8">
								<select class="form-control" id="special_choose"
									name="special_choose" style="width:41%;float:left"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">排序：&nbsp;&nbsp;</label>
							<div class="col-lg-9 col-xs-9">
								<input type="number" class="form-control" id="homeSort"
									name="homeSort" style="width:100px;">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">地区：&nbsp;&nbsp;</label>
							<div class="col-lg-9 col-xs-9">
								<div class="input-group" id="ld" style="width:83%"
									<c:choose>
									    <c:when test="${!empty specialInfo.city}">
									    	initValue=" ${specialInfo.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${specialInfo.province}"
									    </c:otherwise>
									 </c:choose>>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" id="specialModalSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 

    <div class="modal fade" id="editSpecialSortModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">排序:</h4>
				</div>
				<div class="modal-body example" style="height: 150px;">
					<form class="form-horizontal" id="specialSortForm">
						<div class="form-group">
							<label for="exampleInputPassword5" class="col-sm-2">修改排序</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" id="editSort_inp"
									name="editSort_inp" style="width:150px;">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary" id="editSortSubmit">保存</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 
	
	<script type="text/json" id="permissions">{
	  add:'${ btnAu['livePageHome/manage/add']}',
	  update:'${ btnAu['livePageHome/manage/update']}',
	  delete:'${ btnAu['livePageHome/manage/delete']}'
	}
	</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	
	<script src="<%=path%>/js/live_anchor/livePageHomeManagebc.js"></script>
</body>
</html>
