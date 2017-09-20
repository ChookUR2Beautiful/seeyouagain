<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="table table-hover table-bordered table-striped info" data-page="${page}" data-total="${total}">
<!-- 	    <thead>
	      <tr class="text-center">
	        <th class="text-center"> <div class="headerSortUp">消费满：</div></th>
	        <th class="text-center"><div class="header">就送：</div></th>
	       <th class="text-center"><div class="header">商家名称</div></th>
	       <th class="text-center"><div class="header">签约时间</div></th>
	        <th class="text-center" ><div class="header">签约商家</div></th>

	      </tr>
	    </thead> -->
	    <tbody>
	    <c:if test="${!empty tCouponIssueRefs &&fn:length(tCouponIssueRefs)>0}">
	    <c:forEach var="l" items="${tCouponIssueRefs}"  varStatus="status" >
		       <tr class="text-center">
		        <td>${status.index+1}</td><td>消费满【${l.amount}】元</td>
		        <td>就送优惠券【${l.cname}】【编号${l.cid}】</td>
                <td>【${l.num}】张</td>
                <td>发行量【${l.issueVolume}】张</td>
		      </tr>
		     </c:forEach>
		     </c:if>
		     <c:if test="${empty tCouponIssueRefs || fn:length(tCouponIssueRefs)==0}">
		     <tr class="text-center"> <td colspan="4">暂无数据</td></tr>
		     </c:if>
	     </tbody>
  </table>