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
<title>用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datatable/datatable.css" rel="stylesheet">

</head>
<body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-heading">
   			 <strong><i class="icon-wrench"></i> 区域权限设置</strong>
    	</div>
		<div class="text-center">
              <span class="text-muted"><h5>为角色名称:  ${tr.roleName} 的角色分配区域权限</h5></span>
         </div>
		<div class="panel-body">
		
		
		
		
		<ul class="nav nav-primary">
					<c:forEach var="parent" items="${parentAuthorityAreaList}" varStatus="parentInfo">
						<li class="<c:if test="${parentInfo.first}">active</c:if>"><a data-toggle="tab" href="#${parent.authorityId}">${parent.authorityName}</a></li>
					</c:forEach>
		</ul>
		
		<div class="tab-content" style="margin-top: 50px">
					<c:forEach var="parenta" items="${parentAuthorityAreaList}" varStatus="info">
						<div id="${parenta.authorityId}"
							class="tab-pane <c:if test="${info.first}">active</c:if>">
								<div class="list">
									<section class="items items-hover"> 
											<div class="item-content">
												<table class="table table-hover table-striped tablesorter table-data borderBottom" style="margin-top: 10px;">
													<c:if test="${fn:length(subAuthorityArea[parenta.authorityId])>6}">
														<tr>
															<c:forEach var="list" items="${subAuthorityArea[parenta.authorityId]}" varStatus="info">
																<td style="width: 200px;">
																	<button class="btn radius"  data-url="system_settings/dataAuthority/init/authorityAreaInfo.jhtml?roleId=${roleId }&authorityId=${list.authorityId}"  >
																		${list.authorityName }
																	</button>
																</td>	
																<c:if test="${info.count>=6&&info.count%6==0&&info.count<fn:length(subAuthorityArea[parent.authorityId])}">
																	</tr><tr>
																</c:if>
															</c:forEach>
														</tr>
													</c:if>
													<c:if test="${fn:length(subAuthorityArea[parenta.authorityId])<=6}">
														<tr>
															<c:forEach var="list" items="${subAuthorityArea[parenta.authorityId]}">
																	<td style="width: 200px;">
																		
																		
																			<button class="btn radius"  data-url="system_settings/dataAuthority/init/authorityAreaInfo.jhtml?roleId=${roleId }&authorityId=${list.authorityId}"  >
																				${list.authorityName }
																			</button>
																		
																</td>	
															</c:forEach>
														</tr>
													</c:if>
												</table>
												
											</div>

									 </section>
								</div>
						</div>

					</c:forEach>
				</div>
		
		
		
		
		
		
		
		
		
		
		
		<%-- <div class="comments-list" style="border-left:none;">
			<c:forEach var="parent" items="${parentAuthorityAreaList}">
				<div class="comment">
					<!-- <a class="avatar" href="###"><i class="icon-user icon-border icon-2x icon-muted"></i></a> -->
					<div class="content">
						<span class="text-muted">
							<h4>
								${parent.authorityName}
								&nbsp;&nbsp;
						
								&nbsp;&nbsp;
								 <span class="label">&nbsp;&nbsp;${fn:length(subAuthorityArea[parent.authorityId])}&nbsp;个方法
									&nbsp;&nbsp;&nbsp;<i class="icon-chevron-down"></i>
								</span>
							</h4>

						</span>


					</div>
					<div class="comments-list" style="border-left:none;">
						<div class="comments">
							<div class="content">
							<table>
								<c:if test="${fn:length(subAuthorityArea[parent.authorityId])>6}">
									<tr>
										<c:forEach var="list" items="${subAuthorityArea[parent.authorityId]}" varStatus="info">
											<td style="width: 200px;">
												<button class="btn radius"  data-url="system_settings/dataAuthority/init/authorityAreaInfo.jhtml?roleId=${roleId }&authorityId=${list.authorityId}"  >
													${list.authorityName }
												</button>
											</td>	
											<c:if test="${info.count>=6&&info.count%6==0&&info.count<fn:length(subAuthorityArea[parent.authorityId])}">
												</tr><tr>
											</c:if>
										</c:forEach>
									</tr>
								</c:if>
								<c:if test="${fn:length(subAuthorityArea[parent.authorityId])<=6}">
									<tr>
										<c:forEach var="list" items="${subAuthorityArea[parent.authorityId]}">
												<td style="width: 200px;">
													
													
														<button class="btn radius"  data-url="system_settings/dataAuthority/init/authorityAreaInfo.jhtml?roleId=${roleId }&authorityId=${list.authorityId}"  >
															${list.authorityName }
														</button>
													
											</td>	
										</c:forEach>
									</tr>
								</c:if>
							</table>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>	 --%>	
	
		<div class="text-center" id="text"><h5>点击方法按钮即可在下方显示相应区域权限</h5></div>
		<div class="tab-content" style="margin-top: 50px" id="formDiv"></div>
			
		</div>
		
	</div>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datatable/datatable.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script >
	
	$(function(){
		var $panel = $("div.panel");
		$panel.on("click","button.radius",function(event){
			var url = $(this).attr("data-url");
			if(url && url.length>0){
				$.ajax({
					dataType:"html",
					url : url,
				}).done(function(data){
					$("#text").hide();
					$("#formDiv").html(data);
				}).fail(function(data){
					window.messager.warning("操作失败!");
					$("#text").show();
					$("#formDiv").empty();
				});
				return false;;
			}
		});
		$panel.on("click","li",function(){
			$("#text").show();
			$("#formDiv").empty();
		});
	});
	

	
	
	 
	
	</script>
</body>
</html>