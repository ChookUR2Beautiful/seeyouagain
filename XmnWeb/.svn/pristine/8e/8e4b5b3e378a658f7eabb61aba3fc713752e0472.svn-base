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
    <title>生鲜列表</title>
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
  	<input type="hidden" id="checkbox" value="${btnAu['user_terminal/banner/delete']}" />
  	<input type="hidden" id="picSerUrl" value="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>" />
  	
  	<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab1" data-toggle="tab">常规寻蜜客</a>
			</li>
			<li>
				<a href="#tab2" data-toggle="tab">(EC)脉客寻蜜客</a>
			</li>
			<li>
				<a href="#tab3" data-toggle="tab">V客寻蜜客</a>
			</li>
			<li>
				<a href="#tab4" data-toggle="tab">主播(接受V客赠送)寻蜜客</a>
			</li>
	</ul>
  	
  	<div class="tab-content">
  	

	<%--常规购买 --%>
	<div class="tab-pane active" id="tab1"><%--常规寻蜜客 --%>
	  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchForm1">
				<input type="hidden" class="form-control" id="objectOriented" name="objectOriented"  value="5" style = "width:90%;"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>寻蜜客编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="uid" name="uid" style = "width:90%;"/></td>
							<td colspan="2" style="width:5%;"></td>
							<td style="width:5%;"><h5>寻蜜客姓名:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" id="name" name="name" style = "width:90%;"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" id="phoneid" name="phoneid" style = "width:90%;"/>
							</td>
							<td  colspan="2" style="width:5%;"><h5></h5></td>
						 	<td style="width:5%;"><h5>加入时间:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control"  id="sdate" name="sdate" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'edate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="edate" name="edate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['xmer/manage/export']}"><button type="button" id="export1" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="xmerList1"></div>
		</div>
	</div>
	</div>
	<div class="tab-pane" id="tab2"><%--脉客寻蜜客 --%>
	  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchForm2">
				<input type="hidden" class="form-control" id="objectOriented" name="objectOriented" value="8" style = "width:90%;"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>寻蜜客编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="uid" name="uid" style = "width:90%;"/></td>
							<td colspan="2" style="width:5%;"></td>
							<td style="width:5%;"><h5>寻蜜客姓名:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="nname" style = "width:90%;"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="phone" style = "width:90%;"/>
							</td>
							<td  colspan="2" style="width:5%;"><h5></h5></td>
						 	<td style="width:5%;"><h5></h5></td>
							<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['xmer/manage/export']}"><button type="button" id="export2" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="xmerList2"></div>
		</div>
	</div>
	</div>
	<div class="tab-pane" id="tab3"><%--V客寻蜜客 --%>
	  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchForm3">
				<input type="hidden" class="form-control" id="objectOriented" name="objectOriented" value="7" style = "width:90%;"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>寻蜜客编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="uid" name="uid" style = "width:90%;"/></td>
							<td colspan="2" style="width:5%;"></td>
							<td style="width:5%;"><h5>寻蜜客姓名:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="nname" style = "width:90%;"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="phone" style = "width:90%;"/>
							</td>
							<td  colspan="2" style="width:5%;"><h5></h5></td>
						 	<td style="width:5%;"><h5></h5></td>
							<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['xmer/manage/export']}"><button type="button" id="export3" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="xmerList3"></div>
		</div>
	</div>
	</div>
	
	<div class="tab-pane" id="tab4"><%--主播寻蜜客 --%>
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchForm4">
				<input type="hidden" class="form-control" id="objectOriented" name="objectOriented" value="6" style = "width:90%;"/>
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>寻蜜客编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="uid" name="uid" style = "width:90%;"/></td>
							<td colspan="2" style="width:5%;"></td>
							<td style="width:5%;"><h5>寻蜜客姓名:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="nname" style = "width:90%;"/>
							</td>
							<td style="width:5%;"></td>
							<td style="width:18%;">
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:18%;">
							<input type="text" class="form-control" name="phone" style = "width:90%;"/>
							</td>
							<td  colspan="2" style="width:5%;"><h5></h5></td>
						 	<td style="width:5%;"><h5></h5></td>
							<td style="width:18%;">
							</td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['xmer/manage/export']}"><button type="button" id="export4" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="xmerList4"></div>
		</div>
	</div>
	</div>
	</div>
 
     <!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">
{update:'${btnAu['xmer/manage/update']}',check:'${btnAu['xmer/manage/check']}',wallet:'${btnAu['xmer/finance/walletRecord']}',xmer:'${btnAu['xmer/finance/sumMoney/init']}',
dp:'${btnAu['xmer/manage/directPartner']}'}
</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/xmermanagerment/ecxmerList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/verxmerList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/versendxmerList.js"></script>
	<script src="<%=path%>/js/xmermanagerment/xmerList.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>
