<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>市集管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link  href="<%=path%>/css/cloud_design/goodPage.css" rel="stylesheet"/> 
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">出售</a></li>
		<li class=""><a href="#tab2" data-toggle="tab">求购</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab1" class="tab-pane in active">
			<div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="searchFormSell">
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:7%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
										<td style="width:18%;">
											<input type="text" class="form-control" name="nname" value="" style = "width:85%;"/>
										</td>
										<td style="width:7%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
										<td style="width:18%;">
											<input type="text" class="form-control" name="phone" value="" style = "width:58%;"/>
										</td>
									   <td colspan="2" style="width:29%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
											</div>
									   </td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
			    <div class="panel-body data">
					<div id="sellList" class="good-table-wrap"></div>
				</div>
	        </div>
		</div>
        <!--  tab2 -->
		<div id="tab2" class="tab-pane">
		    <div class="panel">
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="searchFormBuy">
						<%-- <input type="hidden" id="uidViewJunior" name="uidViewJunior" value="${uidViewJunior}"> --%>
						<div class="form-group">
							<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:7%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
										<td style="width:18%;">
											<input type="text" class="form-control" name="nname" value="" style = "width:85%;"/>
										</td>
										<td style="width:7%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
										<td style="width:18%;">
											<input type="text" class="form-control" name="phone" value="" style = "width:58%;"/>
										</td>
									   <td colspan="2" style="width:29%;">
											<div class="submit" style="text-align: left;">
												<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
												<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
											</div>
									   </td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
			    <div class="panel-body data">
					<div id="buyList" class="good-table-wrap"></div>
				</div>
	        </div>
		</div>
	</div>

	<!-- modal start -->
	
    <div class="modal fade" id="chatRecordModal">
		<div class="modal-dialog" style="width: 1060px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息回复</h4>
				</div>
				<!-- style="height: 260px;" -->
				<div class="modal-body example" >
					<form class="form-horizontal" id="storeProfitForm">
						<!-- 公用开始 -->
						<div class="form-group">
							<div class="col-sm-offset-1 col-sm-10">
								<div id="dynamicList" class="good-table-wrap"></div>
							</div>
						</div>
						
						<!-- 结束 -->
						<div class="modal-footer">
							<!-- <button type="button" class="btn btn-primary" id="editStoreProfitSubmit">保存</button> -->
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 
	<!-- modal end -->
	
	<script type="text/json" id="permissions">{
		delete:'${ btnAu['manorMarketTrans/manage/delete']}'  }
	</script>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>

	<script src="<%=path%>/js/golden_manor/manorMarketTransManage.js?v=1.0.5"></script>
</body>
</html>
