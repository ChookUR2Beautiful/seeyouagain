<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="table table-hover table-bordered table-striped info"
	data-page="${page}" data-total="${total}">
	<caption
		style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">合作商提现列表</caption>
	<thead>
		<tr class='text-center'>
		    <th>合作商账号</th>
			<th>合作商昵称</th>
			<th>操作</th>
			<!-- <th>类型</th> -->
			<th>账户余额</th>
			<th>提现金额</th>
			<th>提现时间</th>
			<th>提现状态</th>
			<th>提现类型</th>
			<th>提现来源</th>
			<th>提现账号</th>
			<th>发票号</th>
			<th>快递公司名称</th>
			<th>快递单号</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<c:if
			test="${!empty result &&!empty result.data&& fn:length(result.data)>0}">
			<c:forEach var="list" items="${result.data}">
				<tr class="text-center">
				
				    <td>${list.loginAccount }</td>
					<td>${list.username }</td>
				    <td><a  title="合作商提现详情" data-type="ajax" data-position="100px" data-height="580px"   data-width="360px" data-url="business_cooperation/getMoneyJoint/businessDetails.jhtml?id=${list.id }" data-toggle="modal">查看</a></td>
					
					<%-- <td><c:if test="${list.usertype==1 }">商家</c:if><c:if test="${list.usertype==2 }">普通会员</c:if><c:if test="${list.usertype==3 }">寻蜜客</c:if><c:if test="${list.usertype==4 }">合作商提现</c:if> </td> --%>
					<td>${list.balance}</td>
					<td>${list.money}</td>
					<td>${list.date}</td>
					<td><c:if test="${list.state==1}">处理中</c:if> 
					    <c:if test="${list.state==2}">已到帐</c:if>
					    <c:if test="${list.state==3}">打款失败</c:if>
					    <c:if test="${list.state==4}">已写回钱包</c:if>
					</td>
					<td><c:if test="${list.type==1}">银行卡</c:if> 
					    <c:if test="${list.type==2}">第三方支付账户</c:if>
					</td>
					<td><c:if test="${list.source==1}">从返利提现</c:if> 
					    <c:if test="${list.source==2}">从佣金提现</c:if>
					</td>
					<td>${list.account}</td>
					<td>${list.invoice}</td>
					<td>${list.express}</td>
					<td>${list.expressid}</td>
					<td>${list.opinion}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if
			test="${empty result||empty result.data || fn:length(result.data)==0}">
			<tr class="text-center">
				<td colspan="14">暂无数据</td>
			</tr>
		</c:if>
	</tbody>
</table>
