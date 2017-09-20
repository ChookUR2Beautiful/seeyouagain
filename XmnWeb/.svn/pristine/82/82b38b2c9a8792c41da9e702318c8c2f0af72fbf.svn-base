<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>报名审核管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/>
	<style type="text/css">
		*{margin:0;padding:0;}
		.list-box{
			font-size: 0;
			overflow: auto;
		    max-height: 480px;
		    margin-bottom: 20px;
			
		}
		.list-box .box{
			width: 320px;
		    display: inline-block;
		    position: relative;
		    margin-right:15px;
		    margin-bottom:15px;
		    border: 1px solid #d6d6d6;
    		padding: 10px;
    		height: 530px;
    		vertical-align: top;
		}
		.list-box .box .img-box{
			height: 410px;
    		width: 100%;
		}
		.list-box .box .img-list{
			position: absolute;
		    top: 342px;
		    left: 0;
		    width: 100%;
		}
		.list-box .box .img-list img{
			width: 50px;
    		height: 50px;
		    margin-left: 10px;
		}
		.list-box .box p{
			margin:0;
			font-size: 14px;
		    overflow: hidden;
		    margin-top: 10px;
		}
		.list-box .box p.num{
			position: absolute;
		    width: 100%;
		    height: 30px;
		    line-height: 30px;
		    text-align: center;
		    left: 0;
		    top: 0;
		    background: rgba(0,0,0,0.6);
		    color: #fff;
		    margin: 0;
		}
		.list-box .box p span{
			float: right;
		}
		.list-box .box .btn-box{
			font-size:0;
		}
		.list-box .box .btn-box a{
			display: inline-block;
			width:141px;
			height:35px;
			line-height:35px;
			text-align:center;
			font-size: 14px;
			border: 1px solid #999;
    		color: #333;
    		cursor: pointer;
		}
		.list-box .box .btn-box a.pass-btn{
			margin-right:15px;
		}
		.list-box .box .btn-box a.restrict-btn{
			width:100%;
		}
		.list-box p.nodata-hint{
			font-size: 16px;
		    text-align: center;
		    height: 100px;
		    line-height: 100px;
		}
		
		.good-table-imgmodule{
			padding:0;
			height: auto;
		}
		.totalTips{
			position: absolute;
		    top: -10px;
		    right: -10px;
		    background: #ff0303;
		    border-radius: 50%;
		    padding: 3px 6px;
		    font-size: 12px;
		    color: #fff;
		    z-index: 10;
		}
		
	</style>
	
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	
	<!-- 导航栏 -->
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">报名审核</a><span class="totalTips" id="col1">0</span></li>
		<li class=""><a href="#tab2" data-toggle="tab">报名通过</a></li>
		<li class=""><a href="#tab4" data-toggle="tab">试播审核</a><span class="totalTips" id="col4">0</span></li>
		<li class=""><a href="#tab5" data-toggle="tab">试播通过</a></li>
		<li class=""><a href="#tab3" data-toggle="tab">实名审核</a><span class="totalTips" id="col3">0</span></li>
		<li class=""><a href="#tab6" data-toggle="tab">限制中</a></li>
		<li class=""><a href="#tab7" data-toggle="tab">被拒绝</a></li>
		<div class="btn-group" style="margin-left: 5px;">
						<c:if
							test="${null!=btnAu['VstarEnroll/manage/auditSet'] && btnAu['VstarEnroll/manage/auditSet']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="设置" data-url="VstarEnroll/manage/auditSet/init.jhtml"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;设置
							</button>
						</c:if>
		</div>
	</ul>
	
	<div class="tab-content">
		<!-- 报名审核star -->
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm1">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝  -->
						<!-- <input type="hidden"  name="statusParam" value="1,2,3,4"> -->
						<input type="hidden"  name="status" value="1">
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="0">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld1"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<%-- <div class="btn-group" style="margin-bottom: 5px;">
						<c:if
							test="${null!=btnAu['VstarEnroll/manage/auditSet'] && btnAu['VstarEnroll/manage/auditSet']}">
							<button type="button" class="btn btn-success" data-type="ajax"
								data-title="设置" data-url="VstarEnroll/manage/auditSet/init.jhtml"
								data-toggle="modal" data-width="auto">
								<span class="icon-plus"></span>&nbsp;设置
							</button>
						</c:if>
					</div> --%>
					<div id="enrollList1"></div>
				</div>
			</div>
		</div>
		<!-- 报名审核end -->
		
		<!-- 报名通过star -->
		<div id="tab2" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm2">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<!-- <input type="hidden"  name="statusParam" value="5,7,8"> -->
						<input type="hidden"  name="status" value="2">
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="0">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld2"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="enrollList2"></div>
				</div>
			</div>
		</div>
		<!-- 报名通过end -->
		
		<!-- 实名待审核start -->
		<div id="tab3" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm3">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<!-- <input type="hidden"  name="statusParam" value="5,7,8"> -->
						<input type="hidden"  name="status" value="4">
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="0">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld3"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div class="btn-group" style="margin-bottom: 5px;">
						<c:if test="${!empty btnAu['VstarRealName/manage/update'] && btnAu['VstarRealName/manage/update']}">
							<button type="button" class="btn btn-info" id="putaway" ><span class="icon-hand-up"></span>&nbsp;批量通过</button>
						</c:if>
						<c:if test="${!empty btnAu['VstarRealName/manage/update'] && btnAu['VstarRealName/manage/update']}">
							<button type="button" class="btn btn-danger" id="removeOffshelf" ><span class="icon-hand-down"></span>&nbsp;批量拒绝</button>
						</c:if>
					</div>
					
					<div id="enrollList3" class="good-table-wrap"></div>
				</div>
			</div>
		</div>
		<!-- 实名待审核end -->
		
		<!-- 试播审核start -->
		<div id="tab4" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm4">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<input type="hidden"  name="statusParam" value="2,5">
						<!-- <input type="hidden"  name="status" value="5"> -->
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="0">
						<!-- 直播权限状态，0未开通，1已开通。默认0 -->
						<input type="hidden"  name="playStatus" value="0">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld4"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="enrollList4"></div>
				</div>
			</div>
		</div>
		<!-- 试播审核end -->
		
		<!-- 试播通过start -->
		<div id="tab5" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm5">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<!-- <input type="hidden"  name="status" value="7"> -->
						<input type="hidden"  name="playStatus" value="1">
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="0">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld5"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="enrollList5"></div>
				</div>
			</div>
		</div>
		<!-- 试播通过end -->
		
		<!-- 限制中start -->
		<div id="tab6" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm6">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="1">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld6"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="enrollList6"></div>
				</div>
			</div>
		</div>
		<!-- 限制中end -->
		
		
		<!-- 被拒绝start -->
		<div id="tab7" class="tab-pane">
			<div class="panel">
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="searchForm7">
						<!-- 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝 -->
						<input type="hidden"  name="statusParam" value="3,6,8">
						<!-- 受限制的 -->
						<input type="hidden"  name="confining" value="">
							<div class="form-group">
									<table style="width:100%;">
										<tbody>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手编号：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="id" style = "width:85%;"></td>
												<td style="width:5%;"><h5>&nbsp;&nbsp;选手名称：</h5></td>
												<td style="width:25%;"><input type="text" class="form-control"  name="nname" style = "width:85%;"></td>
												<td style="width:5%;"><h5>手机号码：</h5></td>
												<td style="width:30%;"><input type="text" class="form-control"  name="phone" style = "width:66%;"></td>
											</tr>
											<tr>
												<td style="width:5%;"><h5>&nbsp;&nbsp;区域：</h5></td>
												<td style="width:25%;">
													<div class="input-group" id="ld7"  style="width:88%">
													</div>
												</td>
												<td style="width:5%;"><h5></h5></td>
												<td style="width:25%;">
												</td>
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
								<input type="hidden" value="${param.page}" name="page" />
							</c:if>
						</form>
					</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body data">
					<div id="enrollList7"></div>
				</div>
			</div>
		</div>
		<!-- 被拒绝end -->
		
	</div>
	
	
	<script type="text/json" id="permissions">
		{update:'${ btnAu['VstarEnroll/manage/update']}'}
    </script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/vstar/vstarEnrollManage.js"></script>
  </body>
</html>
