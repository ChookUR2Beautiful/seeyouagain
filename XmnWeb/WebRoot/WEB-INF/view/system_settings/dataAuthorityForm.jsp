<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:if test="${!empty roleId && !empty authorityId}">
<div class="text-center"><h5>为  ${fname} 模块下的  ${mname} 方法设置区域权限</h5></div>
<form  id="dataAuthorityForm" action="system_settings/dataAuthority/init/save.jhtml" method="post" >
				<input type="hidden" name="roleId"   value="${roleId }">
				<input type="hidden" name="authorityId"   value="${authorityId }">
				 <table class="borderBottom table table-form">
			       <tbody>
							<tr>
								<td style="width: 25%"></td>
								<td ><h3>选择地区 : <a href="javascript:;" class="btn btn-mini add"><i class="icon-plus"></i></a></h3></td>
								<td style="width: 25%"></td>
							</tr>
							<c:if test="${!empty roleArea}">
									<input type="hidden" name="id"   value="${roleArea.id}">
									<c:forTokens var="area" items="${roleArea.area}" delims=",">
										<tr class="text-center">
											<td style="width: 25%;"></td>
											<td><div style="width: 100%;" class="input-group ldDiv" initValue="${area}"></div></td>
											<td style="width: 25%;" class="text-left text-middle"><h4><a href="javascript:;" class="btn btn-mini remove"><li class="icon-remove"></li></a></h4></td>
										</tr>
									</c:forTokens>
							</c:if>
							
			      </tbody>
			      <tfoot>
			      		<tr>
				         <td style="width: 25%"></td>
				          <td>
				             <button class="btn btn-danger" type="submit" ><i class="icon-ok" data-loading="稍候..."></i>&nbsp;保存</button>
								<a href="system_settings/role/init.jhtml" class="btn btn-warning"  ><i class="icon-remove"></i>&nbsp;取消</a> 
				          </td>
				         <td style="width: 25%"></td>
				        </tr>
			      </tfoot>
		     </table>
			</form>
			<script type="text/javascript" src="<%=path%>/js/system_settings/dataAuthority.js"></script>
			
			</c:if>
			<c:if test="${empty roleId || empty authorityId}">
				<div class="text-center"><h5>无效的数据,请重新选择!</h5></div>
			</c:if>