<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="borderBottom table table-form areaInbusinesstable" data-page="${page}" data-total="${total}">
	    <thead>
	      <tr class="text-center">
	        <th class="text-center"> <div class="headerSortUp">省份</div></th>
	        <th class="text-center"><div class="header">城市</div></th>
	        <th class="text-center" ><div class="header">区域</div></th>
	        <th class="text-center"><div class="header">商圈</div></th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:if test="${!empty area &&fn:length(area)>0}">
	    <c:forEach var="l" items="${area}">
		       <tr class="text-center">
		        <td>${l.ptitle }</td>
		        <td>${l.ctitle }</td>
		        <td>${l.atitle }</td>
		        <td>${l.btitle }</td>
		      </tr>
		     </c:forEach>
		     </c:if>
		     <c:if test="${empty area || fn:length(area)==0}">
		     <tr class="text-center"> <td colspan="4">暂无数据</td></tr>
		     </c:if>
	     </tbody>
  </table>