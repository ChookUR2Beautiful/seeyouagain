<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div  class="main categorytable" 
	data-page="${page}" data-total="${total}">
	<div class="comments">
		<header>
			<c:if test="${!empty btnAu['system_settings/category/add']}"><a class="btn btn-info radius" data-type="ajax" data-position="100px" data-position="100px" data-url="system_settings/category/add/init.jhtml" data-toggle="modal">
					<span class="icon-plus"></span>&nbsp; 添加菜单
				</a>
			</c:if>
		</header>
		<div class="comments-list" style="border-left:none;">
			<c:forEach var="parent" items="${parent}">
				<div class="comment">
					<!-- <a class="avatar" href="###"><i class="icon-user icon-border icon-2x icon-muted"></i></a> -->
					<div class="content">
						<span class="text-muted">
							<h4>
							<c:if test="${!empty btnAu['system_settings/category/update']}">
								<button class="btn  radius" data-type="ajax" data-position="100px" data-url="system_settings/category/update/init.jhtml?id=${parent.tid }" data-toggle="modal">
									${parent.tradename}
								</button>
								&nbsp;&nbsp;
							</c:if>
							<c:if test="${empty btnAu['system_settings/category/update']}">
								<button class="btn radius">
									${parent.tradename}
								</button>
								&nbsp;
							</c:if>
							<%-- 	<c:if test="${!empty btnAu['system_settings/category/delete'] && parent.isremove==0  }">
								<c:if test="${fn:length(submap[parent.tid])==0}">
									<button class="btn btn-mini btn-danger removeBtn" title="删除" data-removeId="${parent.tid}"><i class="icon-remove"></i> </button>&nbsp;
								</c:if>
							</c:if> --%>
								&nbsp;&nbsp;
								 <span class="label">&nbsp;&nbsp;${fn:length(submap[parent.tid])}&nbsp;个模块
									&nbsp;&nbsp;&nbsp;<i class="icon-chevron-down"></i>
								</span>
							</h4>

						</span>


					</div>
					<div class="comments-list" style="border-left:none;">
						<div class="comments">
							<div class="content">
							<table>
								<c:if test="${fn:length(submap[parent.tid])>6}">
									<tr>
										<c:forEach var="list" items="${submap[parent.tid]}" varStatus="info">
											<td style="width: 200px;">
												<c:if test="${!empty btnAu['system_settings/category/update']}">
													<button class="btn radius" data-type="ajax" data-position="100px" data-url="system_settings/category/update/init.jhtml?id=${list.tid }" data-toggle="modal">
														${list.tradename }
													</button>
												</c:if>
												<c:if test="${empty btnAu['system_settings/category/update']}">
													<button class="btn radius">
														${list.tradename }
													</button>
												</c:if>
												<%-- <c:if test="${!empty btnAu['system_settings/category/delete'] && list.isremove==0}">
													<button class="btn btn-mini btn-danger removeBtn" title="删除" data-removeId="${list.tid }"><i class="icon-remove"></i> </button>
												</c:if> --%>
											</td>	
											<c:if test="${info.count>=6&&info.count%6==0&&info.count<fn:length(submap[parent.tid])}">
												</tr><tr>
											</c:if>
										</c:forEach>
									</tr>
								</c:if>
								<c:if test="${fn:length(submap[parent.tid])<=6}">
									<tr>
										<c:forEach var="list" items="${submap[parent.tid]}">
												<td style="width: 200px;">
													
													<c:if test="${!empty btnAu['system_settings/category/update']}">
														<button class="btn radius" data-type="ajax" data-position="100px" data-url="system_settings/category/update/init.jhtml?id=${list.tid }" data-toggle="modal">
															${list.tradename }
														</button>
													</c:if>
													<c:if test="${empty btnAu['system_settings/category/update']}">
														<button class="btn radius">
															${list.tradename }
														</button>
													</c:if>
													<%-- <c:if test="${!empty btnAu['system_settings/category/delete'] && list.isremove==0}">
														<button class="btn btn-mini btn-danger removeBtn" title="删除" data-removeId="${list.tid }"><i class="icon-remove"></i> </button>
													</c:if> --%>
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
		</div>
	</div>
	<br>
	<br>
</div>


