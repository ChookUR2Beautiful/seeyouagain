<%@ page pageEncoding = "UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table  class="table table-hover table-bordered table-striped info"  data-page="${memberPage}" data-total="${memberTotal}">
					<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;" >
						会员列表
						<font style="float:right;">共计【${memberTotal}】个会员&nbsp;</font>
					</caption>
					<thead><tr >
					<th ><input type="checkbox"  id="allCheck" onclick="checkSome(this);" ></th>
					<th >登陆账号</th>
					<th >用户昵称</th>
					<th >手机号码</th>
					<th >性别</th>
					<th>注册时间</th>
					<th >状态</th>
					<th >类型</th>
					<th>注册来源</th>
					<th >所属商家</th>
					<th>城市</th>
					<th>所属区域</th>
					</tr></thead>
					<tbody>
						<c:if test="${fn:length(member)>0}">
						<c:forEach var="list" items="${member}">
							<tr >
								<td>
								<input type="checkbox"  items="oneCheck" name="${list.uid}"     datas="${list.uid}:;${list.ios_token}:;${list.phone}:;${list.nname}" sellerid="${list.uid}"  sellername="${list.uname}"  onclick="javascript:window.parent.searchChosen.memberChose('${list.uid}','${list.uname}',this);"></input>
									<%-- <a href="javascript:window.parent.searchChosen.chose('${list.uid}:;${list.ios_token}:;${list.phone}:;${list.nname}', '${list.uname}',this);" >&nbsp;选择</a> --%>
								</td>
								<td>${list.uname }</td>
								<td>${list.nname }</td>
								<td>${list.phone }</td>
								<td><c:if test="${list.sex==0 }">未知</c:if><c:if test="${list.sex==1 }">男</c:if> <c:if test="${list.sex==2 }">女</c:if>  </td>
								<td>${list.regtime}</td>
								<td><c:if test="${list.status==1 }">正常</c:if><c:if test="${list.status==2 }">锁定</c:if> <c:if test="${list.status==3 }">注销</c:if></td>
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
								<td>${list.genusname }</td>
								<td>${list.city }</td>
								<td>${list.region }</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(member)==0}">
							<tr ><td colspan="15">暂无数据</td></tr>
						</c:if>
					</tbody>
				</table>
