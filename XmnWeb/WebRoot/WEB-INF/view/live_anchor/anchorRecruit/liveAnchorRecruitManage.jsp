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
<title>主播招募管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet"> 

<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">

<!-- 图片弹出样式 -->
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.css?v=1.0.5" />
<link rel="stylesheet" href="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7" />

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

<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchFromId">
				<table style="width: 100%;">
					<tbody>
						<tr>
							<td style="width: 6%;"><h5>主播昵称:</h5></td>
							<td style="width: 15% !important;"><input type="text"
								class="form-control" value="${param.name}" name="name"
								style="width: 80%">
							</td>
							<td style="width: 6%;"><h5>主播手机号:</h5></td>
							<td style="width: 15% !important;"><input type="text"
								class="form-control" value="${param.phone}" name="phone"
								style="width: 85%">
							</td>
							<%-- <td style="width:80px;"><h5>主播类型：</h5></td>
							<td style="width:180px;">
									<select class="form-control" tabindex="2" name ="liveType" value="${param.liveType}" style = "width:85%;">
									    <option value = "">全部</option>
						                <option value = "1" ${param.status==1?"selected":""}>签约主播</option>
						                <option value = "2" ${param.status==2?"selected":""}>兼职主播</option>
						                <option value = "3" ${param.status==3?"selected":""}>普通主播(商户)</option>
						                <option value = "4" ${param.status==3?"selected":""}>普通主播(用户)</option>
						                <option value = "5" ${param.status==3?"selected":""}>测试主播</option>
						             </select>
						   </td> --%>	
                           <td style="width:60px;"><h5>状态：</h5></td>
						   <td style="width:180px;">
									<select class="form-control" tabindex="2" name ="recommendStatus" value="${param.recommendStatus}" style = "width:85%;">
									    <option value = "">全部</option>
						                <option value = "1" ${param.status==1?"selected":""}>待审核</option>
						                <option value = "2" ${param.status==2?"selected":""}>已通过</option>
						                <option value = "3" ${param.status==3?"selected":""}>已拒绝</option>
						            </select>
						   </td>							   
							
						   <td colspan="2" style="text-align: right; width:140px;">
								<div class="submit" >
									<input class="submit radius-3" type="button" value="查询全部"  data-bus = 'query' style="width:180px; flaot:left;" /> 
									<input class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset'  style="width:180px; flaot:left;" />  
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
			<div class="btn-group" style="margin-bottom: 5px;"></div>
			<div id="anchorRecruitInfoList"></div>
		</div>
	</div>	
	
	<!-- modal start -->
	<div class="modal fade" id="viewAnchorRecruitModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">个人信息</h4>
				</div>
				<div class="modal-body example" >  <!-- style="height: 460px;" -->
					<form class="form-horizontal" id="recommendSpecialForm">
					   <input type="hidden" name="uid" id="uid" />
					   <div class="form-group">
							<label class="col-md-3 control-label">真实姓名: </label>
							<div class="col-md-8">
								<h5 id="name"></h5>
							</div>
					   </div>
                       <div class="form-group">
							<label class="col-md-3 control-label">性别： </label>
							<div class="col-sm-8">
								<h5 id="sex"></h5>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">主播手机号：&nbsp;&nbsp;</label>
							<div class="col-sm-8">  <!-- class="col-lg-9 col-xs-9" -->
							     <h5 id="phone"></h5>
							</div>
						</div>
						<!-- <hr/> -->
                        <div class="form-group">
							<label class="col-md-3 control-label">出生日期： </label>
							<div class="col-sm-8">
								<span id="birthDay"></span>
							</div>
						</div>						
                        <div class="form-group">
							<label class="col-md-3 control-label">表演经验： </label>
							<div class="col-sm-8">
								<span id="performExperience"></span>
							</div>
						</div>	
                        <div class="form-group">
							<label class="col-md-3 control-label">经验简述： </label>
							<div class="col-sm-8">
								<span id="experienceResume"></span>
							</div>
						</div>													
                        <div class="form-group">
							<label class="col-md-3 control-label">爱好或特长： </label>
							<div class="col-sm-8">
								<span id="interests"></span>
							</div>
						</div>	
                        <div class="form-group">
							<label class="col-md-3 control-label">风格标签： </label>
							<div class="col-sm-8">
								<span id="styleLabel"></span>
							</div>
						</div>		
						<hr/>	
                        <div class="form-group">
							<label class="col-md-3 control-label">日常照片： </label>
							<div class="col-sm-8">
								<span id="liveAnchorImageUrl"></span>
							</div>
						</div>											
												
						<div class="modal-footer">
							<!-- <button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button> -->
							<c:if test="${null!=btnAu['liveAnchorRecruit/manage/approve'] && btnAu['liveAnchorRecruit/manage/approve']}"><button type="button" class="btn btn-primary" id="liverPassedBtn" >通过</button></c:if>
							<c:if test="${null!=btnAu['liveAnchorRecruit/manage/approve'] && btnAu['liveAnchorRecruit/manage/approve']}"><button type="button" class="btn btn-primary" id="liverRefusedBtn" >拒绝</button></c:if>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 

    <div class="modal fade" id="passedModal">
		<div class="modal-dialog" style="width:500px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">审核提示</h4>
				</div>
				<div class="modal-body example" style="height: 160px;">
					<form class="form-horizontal" id="passedForm">
						<div class="form-group">
						   <div class="modal-body" style="text-align:center;"><strong>是否通过审核，通过后开通主播权限</strong></div>
				        </div>
						<div class="modal-footer">
						    <button type="button" class="btn btn-primary" id="passedSubmit">开通</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">否</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 
    
     <div class="modal fade" id="refuseModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">审核提示</h4>
				</div>
				<div class="modal-body example" style="height: 260px; ">
					<form class="form-horizontal" id="refuseForm">
						<div class="form-group">
						    <div class="modal-body" style="text-align:center;" ><strong>是否拒绝主播申请</strong></div>
							<div style="text-align:center; width:400px; height: 120px; margin: 0 auto;/*水平居中*/"> 
								<textarea id="refuseReason" name="notice" rows="5" style="width:100%;">${params.refuseReason}</textarea>
							</div> 
						</div>
						<div class="modal-footer" >
							<button type="button" class="btn btn-default"
								data-dismiss="modal">否</button>
							<button type="button" class="btn btn-primary" id="refuseSubmit">是</button>
						</div>
					</form>
				</div>

			</div>
		</div>
    </div> 
	
	<script type="text/json" id="permissions">{
	  view:'${ btnAu['liveAnchorRecruit/manage/list']}',
	  approve:'${ btnAu['liveAnchorRecruit/manage/approve']}'
	}
	</script>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	
	<!--图片弹出  -->
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/jquery.fancybox.js?v=2.1.5"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712//source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>	
	<script type="text/javascript" src="<%=path%>/ux/fancyBox-v2.1.5/fancyapps-fancyBox-18d1712/lib/jquery.mousewheel-3.0.6.pack.js"></script> 
		
	<!-- 图片缩放 -->   
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/shiftzoom.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/geodata.js"></script>
	<script type="text/javascript" src="<%=path%>/ux/shiftzoom/cvi_tip_lib.js"></script>
	
	<script src="<%=path%>/js/live_anchor/anchorRecruit/liveAnchorRecruitManage.js"></script>
	
	<script type="text/javascript">
		$(function() {
			$('.fancybox').fancybox();
		});
	</script>
</body>
</html>		