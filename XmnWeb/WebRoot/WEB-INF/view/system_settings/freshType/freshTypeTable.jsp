<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div  class="main categorytable" 
	data-page="${page}" data-total="${total}">
	<div class="comments">
		<header>
			<c:if test="${!empty btnAu['fresh/category/add']}"><a class="btn btn-info radius" data-type="ajax" data-position="100px" data-position="100px" data-url="fresh/category/add/init.jhtml" data-toggle="modal">
					<span class="icon-plus"></span>&nbsp; 添加分类
				</a>
			</c:if>
			<c:if test="${!empty btnAu['fresh/category/delete']}">
			<button type="button" class="btn btn-danger" onclick="delete_fun()" style="width: 80px;">
							<span class="icon-remove"></span>&nbsp;删除
						</button>
			</c:if>
		</header>
		<div class="comments-list" style="border-left:none;">
			<c:forEach var="parent" items="${parent}">
				<div class="comment">
					<div class="content">
						<span class="text-muted">
							<h4>
							<c:if test="${!empty btnAu['fresh/category/edit']}">
								<div class="checkbox">
								  <label>
								    <input type="checkbox" name="parent" value="${parent.id}"><button class="btn  radius" data-type="ajax" data-position="100px" data-url="fresh/category/add/init.jhtml?id=${parent.id }" data-toggle="modal">
										${parent.name}
									</button>
									&nbsp;&nbsp;
								  </label>
								</div>
							</c:if>
							<c:if test="${empty btnAu['fresh/category/edit']}">
								<div class="checkbox">
								  <label>
								    <input type="checkbox" name="parent" value="${parent.id}"><button class="btn radius">
									${parent.name}
								</button>
								&nbsp;
								  </label>
								</div>
								
							</c:if>
								&nbsp;&nbsp;
								 <span class="label">&nbsp;&nbsp;${fn:length(submap[parent.id])}&nbsp;个模块
									&nbsp;&nbsp;&nbsp;<i class="icon-chevron-down"></i>
								</span>
							</h4>

						</span>


					</div>
					<div class="comments-list" style="border-left:none;">
						<div class="comments">
							<div class="content">
							<table>
								<c:if test="${fn:length(submap[parent.id])>6}">
									<tr>
										<c:forEach var="list" items="${submap[parent.id]}" varStatus="info">
											<td style="width: 200px;">
												<c:if test="${!empty btnAu['fresh/category/edit']}">
													<div class="checkbox">
													  <label>
													    <input type="checkbox" name="child" value="${list.id}">
													    <button class="btn radius" data-type="ajax" data-position="100px" data-url="fresh/category/add/init.jhtml?id=${list.id }" data-toggle="modal">
														${list.name }
													</button>
													  </label>
													</div>
												</c:if>
												<c:if test="${empty btnAu['fresh/category/edit']}">
													<c:if test="${!empty btnAu['fresh/category/edit']}">
													<div class="checkbox">
													  <label>
													    <input type="checkbox" name="child" value="${list.id}">
													  <button class="btn radius">
														${list.name }
													</button>
													  </label>
													</div>
												</c:if>
												</c:if>
											</td>	
											<c:if test="${info.count>=6&&info.count%6==0&&info.count<fn:length(submap[parent.id])}">
												<tr></tr>
											</c:if>
										</c:forEach>
									</tr>
								</c:if>
								<c:if test="${fn:length(submap[parent.id])<=6}">
									<tr>
										<c:forEach var="list" items="${submap[parent.id]}">
												<td style="width: 200px;">
													
													<c:if test="${!empty btnAu['fresh/category/edit']}">
														<div class="checkbox">
													  <label>
													    <input type="checkbox" name="child" value="${list.id}">
													 <button class="btn radius" data-type="ajax" data-position="100px" data-url="fresh/category/add/init.jhtml?id=${list.id }" data-toggle="modal">
															${list.name }
														</button>
													  </label>
													</div>
													</c:if>
													<c:if test="${empty btnAu['fresh/category/edit']}">
														<div class="checkbox">
													  <label>
													    <input type="checkbox" name="child" value="${list.id}">
														<button class="btn radius">
																${list.name }
														</button>
													  </label>
													</div>
													</c:if>
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


