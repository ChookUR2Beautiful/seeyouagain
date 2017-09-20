<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商户列表</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
<style type="text/css">
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

.data table.info tr td {
	text-align: center;
	font-size: 14px
}

.data table,info {
	word-break: break-all;
	word-wrap: break-word;
}

.data table.info tr th {
	text-align: center;
	font-size: 14px;
	line-height: 15px;
}

.data table.info tr td a {
	color: #CC3333;
}

.data table.info tr td a:hover {
	color: #CC3333;
	text-decoration: underline;
}
</style>

</head>
<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="sellerFromId">
			<!-- 0不是连锁店 -->
			<input type="hidden" name="ismultiple" value="0"/>
			<input type="hidden" id="wxapiUrl" name="wxapiUrl" value="${wxapiUrl}"/>
			
				<table style="width:100%;">
					<tbody>
						<tr>
								<td><h5>商家编号：</h5></td>
							<td>
								<input type="text" class="form-control"  name="sellerid" value="${param.sellerid}" style="width:90%">
							</td>
							<td style="width:100px;"><h5>签约时间：</h5></td>							
							<td style="width:400px;">
								<input type="text" name ="signdateStart" value="${param.signdateStart}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="signdateEnd" value="${param.signdateEnd}" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							<td style="width:100px;"><h5>商家手机号：</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control"  name="phoneid"  value="${param.phoneid}" style="width:75%">
							</td>								
						</tr>
						<tr>
							<td style="width:100px;"><h5>商家名称：</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control"  name="sellername"  value="${param.sellername}" style="width:90%">
							</td>
							<td><h5>区域查询：</h5></td>
							<td>
								<div class="input-group" id="ld" 
									<c:choose>
									    <c:when test="${!empty param.area}">
									      initValue=" ${param.area}"
									    </c:when>
									
									    <c:when test="${empty param.area && !empty param.city}">
									    	initValue=" ${param.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${param.province}"
									    </c:otherwise>
									 </c:choose>
								  style="width:91%">
								</div>
							</td>
							<td style="width:100px;"><h5>审核状态：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="status" value="${param.status}" style = "width:75%;">
									    <option value = "">--请选择--</option>
						                <!-- <option value = "0">未验证</option> -->
						                <option value = "1" ${param.status==1?"selected":""}>审核中</option>
						                <option value = "2" ${param.status==2?"selected":""}>未通过</option>
						                <option value = "3" ${param.status==3?"selected":""}>已签约</option>
						                <option value = "4" ${param.status==4?"selected":""}>未签约</option>
						                <option value = "5" ${param.status==5?"selected":""}>暂停合作</option>
						                <option value = "6" ${param.status==6?"selected":""}>注销合作</option>
						             </select>
						   </td>	
						</tr>	
							
						<tr>
							<td style="width:100px;"><h5>商家等级：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="sellerGrade" style = "width:90%;">
									    <option value = "">--请选择--</option>
						                <option value = "1" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==1?"selected":""}</c:if>>A&nbsp;&nbsp;级</option> 
						                <option value = "2" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==2?"selected":""}</c:if>>B+级</option>
						                <option value = "3" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==3?"selected":""}</c:if>>B&nbsp;&nbsp;级</option>
						                <option value = "4" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==4?"selected":""}</c:if>>C+级</option> 
						                <option value = "5" <c:if test="${!empty param.sellerGrade}">${param.sellerGrade==5?"selected":""}</c:if>>C&nbsp;&nbsp;级</option>
						             </select>
						   </td>
							
							<td><h5>商圈查询：</h5></td>
							<td>
									<select class="form-control"  id="zoneid" name ="zoneid" initValue="${param.zoneid}" style = "width:88%;">
						            </select>
							</td>
							<td style="width:100px;"><h5>上线状态：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="isonline" style = "width:75%;">
									    <option value = "">--请选择--</option>
						                <option value = "0" <c:if test="${!empty param.isonline}">${param.isonline==0?"selected":""}</c:if>>未上线</option> 
						                <option value = "1" <c:if test="${!empty param.isonline}">${param.isonline==1?"selected":""}</c:if>>已上线</option>
						                <option value = "3" <c:if test="${!empty param.isonline}">${param.isonline==3?"selected":""}</c:if>>已下线</option>
						             </select>
						   </td>
						</tr>	
						<tr>
						    <td><h5>归属合作商：</h5></td>
							<td>
									<select class="form-control" id="jointid" name ="jointid" initValue="${param.jointid}" >
							    	</select>
						   </td>		
							
							<td><h5>行业查询：</h5></td>
							<td>
									<div class="input-group" id="tradeSelect" style = "width:91.5%;"
									 <c:choose>
									    <c:when test="${!empty param.genre}">
									      initValue="${param.genre}"
									    </c:when>
									    <c:otherwise>  
									    	initValue="${param.category}"
									    </c:otherwise>
									 </c:choose>
									 ></div>
							</td>	
						   <td style="width:100px;"><h5>是否开启首次：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name ="isFirst" style = "width:75%;">
										<option value = "">--请选择--</option>
						                <option value = "0" <c:if test="${!empty param.isFirst}">${param.isFirst==0?"selected":""}</c:if>>否</option>
						                <option value = "1" <c:if test="${!empty param.isFirst}">${param.isFirst==1?"selected":""}</c:if>>是</option> 
						             </select>
						   </td>
						</tr>
						<tr>
							<td style="width:100px;"><h5>分账模式：</h5></td>
							<td style="width:400px;">
								<select class="form-control" tabindex="2" name ="ledgerMode" style = "width:90%;">
									<option value = "">--请选择--</option>
					                <option value = "0" <c:if test="${!empty param.ledgerMode}">${param.ledgerMode==0?"selected":""}</c:if>>正常分账模式</option>
					                <option value = "2" <c:if test="${!empty param.ledgerMode}">${param.ledgerMode==2?"selected":""}</c:if>>仅商家参与分账模式</option> 
					             </select>
						   </td>
							<td colspan="2"></td>
							<td colspan="2">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page"/>
				</c:if>
			</form>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['businessman/seller/add']}"><a type="button" id="addSellerBto" class="btn btn-success"  href="businessman/seller/add/init.jhtml?isType=add" ><span class="icon-plus"></span>&nbsp;添加</a></c:if>
				<c:if test="${!empty  btnAu['businessman/seller/beachOnLine']}">
				   <!--  <button type="button" class="btn btn-warning" id="readyOnline" ><span class="icon-hand-up"></span>&nbsp;预上线</button> -->
					<button type="button" class="btn btn-info" id="beachOnLine" ><span class="icon-hand-up"></span>&nbsp;上线</button>
					<!-- <button type="button" class="btn btn-danger" id="beachDownLine" ><span class="icon-hand-down"></span>&nbsp;下线</button> -->
				</c:if>
				<c:if test="${!empty btnAu['businessman/seller/downLine']}"><button type="button" class="btn btn-danger" id="beachDownLine" ><span class="icon-hand-down"></span>&nbsp;下线</button></c:if>
				
				<button type="button" class="btn btn-danger" onclick="sellerOption(1,1)" ><span class="icon icon-off"></span>&nbsp;公开商户</button>
				<button type="button" class="btn btn-danger" onclick="sellerOption(1,0)" ><span class="icon icon-off"></span>&nbsp;不公开商户</button>
				<button type="button" class="btn btn-danger" onclick="sellerOption(2,1)" ><span class="icon icon-off"></span>&nbsp;参与分红</button>
				<button type="button" class="btn btn-danger" onclick="sellerOption(2,0)" ><span class="icon icon-off"></span>&nbsp;不参与分红</button>
				<button type="button" class="btn btn-danger" onclick="sellerOption(3,1)" ><span class="icon icon-off"></span>&nbsp;设为付费商家</button>
				<button type="button" class="btn btn-danger" onclick="sellerOption(3,0)" ><span class="icon icon-off"></span>&nbsp;非付费商家</button>
				
				<%-- <c:if test="${!empty btnAu['businessman/sellerMarketing/addBatch']}"><button type="button" class="btn btn-success" title="批量设置营销活动" data-type="ajax" data-position="100px" data-height="560px"   data-width="560px" data-url="businessman/sellerMarketing/addBatch/init.jhtml"  data-toggle="modal"><span class="icon-plus"></span>&nbsp;营销活动</button></c:if> --%>
				<%-- <c:if test="${!empty btnAu['businessman/seller/updateFlatAgioBatch'] }"><button type="button" class="btn btn-success" id="batchSetFlatAgio" title="批量设置平台补贴折扣"><span class="icon-plus"></span>&nbsp;平台补贴折扣</button></c:if> --%>
				<%-- <c:if test="${!empty btnAu['businessman/sellerDetailed/updateIsFirstBatch'] }"><button type="button" class="btn btn-success" id="batchIsFirst" title="批量设置是否开启首次"><span class="icon-plus"></span>&nbsp;是否开启首次</button></c:if> --%>
				<c:if test="${!empty  btnAu['businessman/seller/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>	  		
			</div>
			<div id="sellerInfoDiv"></div>
		</div>																																				
	</div>
	<script type="text/javascript">   contextPath = '${pageContext.request.contextPath}' </script>
	<script type="text/json" id="permissions">
           {islock:'${ btnAu['businessman/seller/islock']}',xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',
            zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }',
           order:'${btnAu['businessman/seller/initOrder'] }',yx:'${btnAu['businessman/sellerMarketing/init'] }',
           wallet:'${btnAu['businessman/seller/viewWallet'] }',sx:'${btnAu['businessman/seller/beachOnLine']}',
           xx:'${btnAu['businessman/seller/downLine']}',setFlatAgio:'${btnAu['businessman/seller/updateFlatAgioBatch']}',
		   dr:'${btnAu['businessman/expert/init']}',mt:'${btnAu['businessman/seller/toMaterielPage']}',
           isFirst:'${btnAu['businessman/sellerDetailed/updateIsFirstBatch']}',foodClass:'${btnAu['businessman/seller/food/class/init']}',
           food:'${btnAu['businessman/seller/food/init']}',bdcard:'${btnAu['businessman/seller/bindCardInit/init']}',
		   resetPassW:'${btnAu['businessman/seller/resetDepositPassW']}',init:'${btnAu['businessman/seller/init/list']}'}</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/page/page.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/js/businessman/model/sellerModel.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/businessman/seller.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var vali=
			{
				rules:{
					sellerid:{
						digits:true,
						range:[1,2147483647]
					}
				},
				messages:{
					sellerid:{
						digits:"商家编号只能是数字",
						range:"最大值为2147483647"
					}
				}
			};
		validate("sellerFromId",vali);
	});
	</script>
	
</body>
</html>
