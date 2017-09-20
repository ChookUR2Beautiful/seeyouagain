<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="borderBottom table table-form areaInbusinesstable" data-page="${page}" data-total="${total}">
	    <thead>
	      <tr class="text-center">
	        <th class="text-center"> <div class="headerSortUp">员工姓名</div></th>
	        <th class="text-center"><div class="header">商家编号</div></th>
	       <th class="text-center"><div class="header">商家名称</div></th>
	       <th class="text-center" ><div class="header">商家状态</div></th>

	      </tr>
	    </thead>
	    <tbody>
	    <c:if test="${!empty tstaffRanking &&fn:length(tstaffRanking)>0}">
	    <c:forEach var="l" items="${tstaffRanking}">
		       <tr class="text-center">
		        <td>${l.fullname }</td>
		        <td>${l.sellerid }</td>
                <td>${l.sellername }</td>
                <td>${l.xstatus}</td>
		      </tr>
		     </c:forEach>
		     </c:if>
		     <c:if test="${empty tstaffRanking || fn:length(tstaffRanking)==0}">
		     <tr class="text-center"> <td colspan="4">暂无数据</td></tr>
		     </c:if>
	     </tbody>
  </table>