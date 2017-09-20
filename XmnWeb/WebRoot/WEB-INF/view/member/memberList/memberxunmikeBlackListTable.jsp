<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table  class="table table-hover table-bordered table-striped info"  id="memberTable" data-page="${memberPage}" data-total="${memberTotal}">
					<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;" >
						寻蜜客会员黑名单列表
						<font style="float:right;">共计【${memberTotal}】个会员&nbsp;</font>
					</caption>
					<thead>
					<tr >
					<c:if test="${btnAu['memberblactlist/memberList/xunmike/details']||btnAu['memberblactlist/memberList/xunmike/update']}">
					<th >操作</th>
					</c:if>
					<th >用户编号</th>
					<th >用户昵称</th>
					<th >手机号码</th>
					<th >性别</th>
					<th>注册时间</th>
					<th >状态</th>
					<th >类型</th>
					<th>注册来源</th>
					<th>注册手机型号</th>
					<th>注册手机品牌</th>
					<th>登录手机型号</th>
					<th>登录手机品牌</th>
					<th >所属商家</th>
					<th>注册区域</th>
					</tr></thead>
					<tbody>
						<c:if test="${fn:length(member)>0}">
						<c:forEach var="list" items="${member}">
							<tr >
							    <td>
							    <c:if test="${btnAu['memberblactlist/memberList/xunmike/details']}">
								 <a  href="javascript:viod();" title='详情' data-type='ajax' data-url='memberblactlist/memberList/xunmike/details.jhtml?uid=${list.uid}' data-toggle='modal'>&nbsp;查看</a>
								 </c:if>	
								 <c:if test="${btnAu['memberblactlist/memberList/xunmike/update']}">
								 <c:if test="${list.status==4}"><a href="javascript:function viod();" onclick='upstatus(${list.uid},"1","xunmike")'>&nbsp;恢复到列表</a></c:if>							
								 </c:if>
								</td>
								<td>${list.uid }</td>
								<td>${list.nname }</td>
								<td>${list.phone }</td>
								<td><c:if test="${list.sex==0 }">未知</c:if><c:if test="${list.sex==1 }">男</c:if> <c:if test="${list.sex==2 }">女</c:if>  </td>
								<td>${list.regtime}</td>
								<td><c:if test="${list.status==1 }">正常</c:if><c:if test="${list.status==2 }">锁定</c:if> <c:if test="${list.status==3 }">注销</c:if><c:if test="${list.status==4 }">黑名单</c:if></td>
								<td><c:if test="${list.usertype==1 }">普通会员</c:if><c:if test="${list.usertype==2 }">寻蜜客</c:if> </td>
								<td>
									<c:if test="${list.regtype==1 }">旅游众筹网站</c:if>
									<c:if test="${list.regtype==2 }">寻蜜鸟网站</c:if> 
									<c:if test="${list.regtype==3 }">400客服电话</c:if>
									<c:if test="${list.regtype==4 }">旅游众筹安卓客户端</c:if> 
									<c:if test="${list.regtype==5 }">旅游众筹IOS客户端</c:if>
									<c:if test="${list.regtype==6 }">寻蜜鸟安卓客户端</c:if> 
									<c:if test="${list.regtype==7 }">寻蜜鸟IOS客户端</c:if>
									<c:if test="${list.regtype==8 }">商家安卓客户端</c:if>
									<c:if test="${list.regtype==9 }">商家IOS客户端</c:if> 
								</td>
								<td>${list.model }</td>
								<td>${list.brand }</td>
								<td>${list.last_model }</td>
								<td>${list.last_brand }</td>
								<td>${list.genusname }</td>
								<td>${list.regcity}-${list.regarea}</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(member)==0}">
							<tr ><td colspan="16">暂无数据</td></tr>
						</c:if>
					</tbody>
				</table>
