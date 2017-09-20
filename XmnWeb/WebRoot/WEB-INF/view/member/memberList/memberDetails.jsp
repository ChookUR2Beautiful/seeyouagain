<%@ page pageEncoding="utf-8" import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="table table-form " style="border-left: 1px">
		<tbody>
			<tr>
				<td >
					<table width="100%" class="borderBottom table table-form " >
							<tbody>
									<tr >
										<th  class="w-100px">头像</th>
										<td ><!-- class="img-circle" 图片显示圆的 -->
											<c:if test="${!empty member.avatar }"><img src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP%>${ member.avatar }" width="100px" height="100px" style="border:2px solid  #ddd;"></c:if>
											<c:if test="${empty member.avatar }"><img src="resources/web/images/logo.png" width="100px" height="100px" style="background: none repeat scroll 0 0 #cc3636;border:2px solid #ddd;"></c:if>
										</td>
									</tr>
									<tr >
										<th  class="w-100px">用户昵称</th>
										<td >${member.nname}</td>
									</tr>
									<tr>
										<th class="w-100px">手机号码</th>
										<td>${member.phone}</td>
									</tr>
									<tr>
										<th>性别</th>
										<td>
										 	<c:if test="${member.sex==1 }">男</c:if> 
											<c:if test="${member.sex==2 }">女</c:if> 
								 			<c:if test="${member.sex==0 }">未知</c:if>
										</td>
									</tr>
									
									<tr>
							          	<th>个性签名</th>
							          	<td colspan="2">
							          		${member.sign}
										</td>
						        	</tr>
						        	<%-- <tr>
										<th class="w-100px">工作地址</th>
										<td>${member.address}</td>
									</tr> --%>
									<!-- <tr>
							          	<th>兴趣</th>
							          	<td colspan="2">
							          		兴趣
										</td>
						        	</tr>
						        	<tr>
							          	<th>消费情况</th>
							          	<td >
							          		<a>消费情况</a>
										</td>
						        	</tr>
						        	<tr>
							          	<th>返利账单总额</th>
							          	<td >
							          		<a>￥200</a>
										</td>
						        	</tr>
						        	<tr>
							          	<th>可提现</th>
							          	<td >
							          		￥200&nbsp;
							          		<a>查看提现记录</a>
										</td>
						        	</tr> -->
						        	<tr>
										<th class="w-100px">状态</th>
										<td>	<c:if test="${member.status==1 }">正常</c:if>
												<c:if test="${member.status==2 }">锁定</c:if>
												<c:if test="${member.status==3 }">注销</c:if> 
												<c:if test="${member.status==4 }">黑名单</c:if> 
										
									</tr>
									<tr>
										<th class="w-100px">注册城市</th>
										<td><c:if test="${!empty member.regcity}">${member.regcity}</c:if><c:if test="${empty member.regcity}">无</c:if></td>
										
									</tr>
									<tr>
										<th class="w-100px">注册区域</th>
										<td><c:if test="${!empty member.regarea}">${member.regarea}</c:if><c:if test="${empty member.regarea}">无</c:if></td>
										
									</tr>
									<tr>
										<th class="w-100px">所属商家</th>
										<td> <c:if test="${!empty member.genusname}">${member.genusname}</c:if><c:if test="${empty member.genusname}">无</c:if></td>
									</tr>
							</tbody>
					</table>
				</td>
				
				
				<!-- <td style="margin:0 auto;border-left: 2px solid #e5e5e5; font-size: 12px">
						&nbsp;&nbsp;&nbsp;&nbsp;<label >用户操作日志：</label>
						<br>
						<table class="table table-hover table-striped tablesorter table-data" style="font-size: 12px;overflow-x: hidden;overflow-y: auto;">
						<thead>
							<tr class="text-center">
									<td class="text-important">时间</td>
									<td class="text-important">操作</td>
									<td class="text-important">停留时间</td>
							</tr>
						</thead>
						<tbody>
							<tr class="text-center">
								<td>
									<label >2014-12-12</label>
								</td>
								<td><label >买东西</label></td>
								<td><label >3s</label></td>
							</tr>
						</tbody>
					</table>
					
				</td>	 -->	
			</tr>
		</tbody>
		<tfoot>
			
			<tr class="text-center">
				<td colspan="2" >
					<button data-dismiss="modal" class="btn btn-default" type="reset"><span class="icon-remove"></span>  取  消  </button>
					<!-- <input type="button" class="btn" data-dismiss="modal"	 value="取消" > -->
				</td>
			</tr>
		</tfoot>
	</table>



