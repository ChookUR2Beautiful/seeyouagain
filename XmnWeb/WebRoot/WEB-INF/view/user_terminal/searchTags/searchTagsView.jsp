<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${!empty tags}">
	<c:forEach var="tag" items="${tags}">
		<div class="board-item ">${tag.hotWord}
			<c:if test="${!empty btnAu['user_terminal/searchTags/delete']}">
				<a href="javascript:;" class="text-danger" title="删除搜索标签" data-hid="${tag.hid}">&nbsp;&nbsp;<i class="icon-trash"></i></a>
			</c:if>
		</div>
	</c:forEach>
	<c:if test="${fn:length(tags)==6 && !empty btnAu['user_terminal/searchTags/add']} ">
		<div class=" board-item disable-drop" style="display: none;"><button class="btn btn-block btn-danger disabled"   disabled="disabled" title="添加搜索标签" type="button" ><i class="icon-plus"></i></button></div>
	</c:if>
</c:if>
<c:if test="${(empty tags || fn:length(tags)<6) && !empty btnAu['user_terminal/searchTags/add']}">
	<div class=" board-item disable-drop"><button class="btn btn-block btn-danger"  title="添加搜索标签" type="button" ><i class="icon-plus"></i></button></div>
</c:if>

