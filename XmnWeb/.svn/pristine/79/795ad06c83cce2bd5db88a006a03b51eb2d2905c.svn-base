<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table  class="table table-hover table-bordered table-striped info"  id="memberTable" data-page="${memberPage}" data-total="${memberTotal}">
					<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;" >
						会员列表
						<%-- <font style="float:right;">共计【${memberTotal}】个会员&nbsp;</font> --%>
					</caption>
					<thead><tr >
					<th >用户昵称</th>
					<th >手机号码</th>
					<th >性别</th>
					<th>注册时间</th>
					<th >状态</th>
					<th >所属商家</th>
					<th>所属区域</th>
					<th>注册区域</th>
					</tr></thead>
					<tbody>
						<c:if test="${fn:length(member)>0}">
						<c:forEach var="list" items="${member}">
							<tr >
								<%-- <c:if test="${!empty btnAu['member/memberList/update'] || !empty btnAu['member/memberList/details']}">
									<td>
										<c:if test="${!empty btnAu['member/memberList/update']}"><a href="javascript:viod();"  title='修改' data-type='ajax' data-url='member/memberList/update/init.jhtml?uid=${list.uid}' data-toggle='modal'>&nbsp;修改</a>&nbsp;&nbsp;</c:if>
										<c:if test="${!empty btnAu['member/memberList/details']}"><a  href="javascript:viod();" title='查看' data-type='ajax' data-url='member/memberList/details.jhtml?uid=${list.uid}' data-toggle='modal'>&nbsp;查看</a></c:if>									
									</td>
								</c:if> --%>
								<td>${list.nname }</td>
								<td>${list.phone }</td>
								<td><c:if test="${list.sex==0 }">未知</c:if><c:if test="${list.sex==1 }">男</c:if> <c:if test="${list.sex==2 }">女</c:if>  </td>
								<td>${list.regtime}</td>
								<td><c:if test="${list.status==1 }">正常</c:if><c:if test="${list.status==2 }">锁定</c:if> <c:if test="${list.status==3 }">注销</c:if></td>
								<!-- 新增查看钱包信息 -->
								<td>${list.genusname }</td>
								<td>${list.city }-${list.region}</td>
								<td>${list.regcity}-${list.regarea}</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(member)==0}">
							<tr ><td colspan="16">暂无数据</td></tr>
						</c:if>
					</tbody>
				</table>
