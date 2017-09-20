<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<title>会员列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet"> 
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
	<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
		<style>
		.submit{text-align:left;}
	</style>
  </head>
  <body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
	<ul id="myTab" class="nav nav-tabs">
		<c:if test="${btnAu['memberblactlist/memberList/init/ordinarylist']}">
			<li
				<c:if test="${btnAu['memberblactlist/memberList/init/ordinarylist']}">class="active"</c:if>>
				<a href="#tab1" data-toggle="tab">普通会员</a>
			</li>
		</c:if> 
		<c:if test="${btnAu['memberblactlist/memberList/init/xunmikelist']}">
			<li
				<c:if test="${empty btnAu['memberblactlist/memberList/init/ordinarylist'] && btnAu['memberblactlist/memberList/init/xunmikelist']}"> class="active" </c:if>>
				<a href="#tab2" data-toggle="tab">寻蜜客会员</a>
			</li>
		</c:if> 
	</ul>
	<div class="tab-content">
	<c:if test="${ btnAu['memberblactlist/memberList/init/ordinarylist']}">
	<div id="tab1" class="tab-pane <c:if test="${ btnAu['memberblactlist/memberList/init/ordinarylist']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="blackOrdinarysearchForm">
			    <input type="hidden" name="usertype" value="1">
			    <input type="hidden" name="status" value="4">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;用户编号:</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="uid" style="width:78%;"></td>	
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;手机号码:</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="phone" style="width:77%;"></td>	
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;注册时间:</h5></td>
							<td style="width:400px;">
							<input type="text" name ="staregtime" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36%;float:left">
							<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="endregtime"   placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:36%;float:left"> 
							</td>
											
							
						</tr>					
						<tr>
						<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;用户昵称:</h5></td>
						<td style="width:400px;"><input type="text" class="form-control"  name="nname" style="width:78%;"></td>	
						<td style="width:100px"><h5>&nbsp;&nbsp;&nbsp;注册区域:</h5></td>
						   <td style="width:400px">
							    <div class="input-group" id="ysqy" style="width:78%;">
								</div>							
						   </td> 
                       	<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;所属商家:</h5></td>
						   <td style="width:400px;"><input type="text" class="form-control"  name="genusname" style="width:78%;"></td>	 
							
						</tr>
						<tr>
						<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;注册来源:</h5></td>
							<td style="width:400px;">
								<select class="form-control" name="regtype" style="width:78%;">
									<option value="">请选择</option>
									<option value="1">旅游众筹网站</option>
									<option value="2">寻蜜鸟网站</option>
									<option value="3">400客服电话</option>
									<option value="4">旅游众筹安卓客户端</option>
									<option value="5">旅游众筹IOS客户端</option>
									<option value="6">寻蜜鸟安卓客户端</option>
									<option value="7">寻蜜鸟IOS客户端</option>
									<!-- <option value="8">商家安卓客户端</option>
									<option value="9">商家IOS客户端</option> -->
								</select>
							</td>
							<td></td>	<td></td>
							<td  colspan="2">
								<div class="submit" style="text-align: left; ">&nbsp;&nbsp;
								<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>	
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel">
		<div class="panel-body data">
			<%-- <div id="blackordinarylistDiv" request-init ="${blackordinarylist}"> --%>
			<div id="blackordinarylistDiv" >
			</div>
		</div>
	 </div>
	</div>
	</c:if>
	<c:if test="${ btnAu['memberblactlist/memberList/init/xunmikelist']}">
	<div id="tab2" class="tab-pane <c:if test="${ empty btnAu['memberblactlist/memberList/init/ordinarylist'] && btnAu['memberblactlist/memberList/init/xunmikelist']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="xunmikesearchForm">
			    <input type="hidden" name="usertype" value="2">
			    <input type="hidden" name="status" value="4">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;用户编号:</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="uid" style="width:78%;"></td>	
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;手机号码:</h5></td>
							<td style="width:400px;"><input type="text" class="form-control"  name="phone" style="width:78%;"></td>	
							<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;注册时间:</h5></td>
							<td style="width:400px;">
							<input type="text" name ="staregtime" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:36%;float:left">
							<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="endregtime"   placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:36%;float:left"> 
							</td>
											
							
						</tr>					
						<tr>
						<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;用户昵称:</h5></td>
						<td style="width:400px;"><input type="text" class="form-control"  name="nname" style="width:78%;"></td>	
						<td style="width:100px"><h5>&nbsp;&nbsp;&nbsp;注册区域:</h5></td>
						   <td style="width:400px">
							    <div class="input-group" id="ysqyx" style="width:78%;">
								</div>							
						   </td> 
                       	<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;所属商家:</h5></td>
						   <td style="width:400px;"><input type="text" class="form-control"  name="genusname" style="width:78%;"></td>	 
							
						</tr>
						<tr>
						<td style="width:100px;"><h5>&nbsp;&nbsp;&nbsp;注册来源:</h5></td>
							<td style="width:400px;">
								<select class="form-control" name="regtype" style="width:78%;">
									<option value="">请选择</option>
									<option value="1">旅游众筹网站</option>
									<option value="2">寻蜜鸟网站</option>
									<option value="3">400客服电话</option>
									<option value="4">旅游众筹安卓客户端</option>
									<option value="5">旅游众筹IOS客户端</option>
									<option value="6">寻蜜鸟安卓客户端</option>
									<option value="7">寻蜜鸟IOS客户端</option>
									<option value="8">商家安卓客户端</option>
									<option value="9">商家IOS客户端</option>
								</select>
							</td>
							<td></td>	<td></td>
							<td  colspan="2">
								<div class="submit" style="text-align: left; ">&nbsp;&nbsp;
								<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>	
						</tr>
					</tbody>
					<!-- <tbody>
						<tr>
							<td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;用户编号:</h5></td>
							<td style="width:200px;"><input type="text" class="form-control"  name="uid" ></td>	
							<td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;手机号码:</h5></td>
							<td style="width:200px;"><input type="text" class="form-control"  name="phone" ></td>	
							<td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;注册来源:</h5></td>
							<td style="width:200px;">
								<select class="form-control" name="regtype">
									<option value="">请选择</option>
									<option value="1">旅游众筹网站</option>
									<option value="2">寻蜜鸟网站</option>
									<option value="3">400客服电话</option>
									<option value="4">旅游众筹安卓客户端</option>
									<option value="5">旅游众筹IOS客户端</option>
									<option value="6">寻蜜鸟安卓客户端</option>
									<option value="7">寻蜜鸟IOS客户端</option>
									<option value="8">商家安卓客户端</option>
									<option value="9">商家IOS客户端</option>
								</select>
							</td>					
							<td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;注册时间:</h5></td>
							<td style="width:480px;">
							<input type="text" name ="staregtime" placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:42%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="endregtime"   placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:42%;float:left"> 
							</td>
						</tr>					
						<tr>
						   <td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;用户昵称:</h5></td>
						   <td style="width:200px;"><input type="text" class="form-control"  name="nname" ></td>	
						   <td style="width:70px"><h5>&nbsp;&nbsp;&nbsp;注册区域:</h5></td>
						   <td style="width:200px">
							    <div class="input-group" id="ysqyx" style="width: 99%;"></div>							
						   </td> 
                       	   <td style="width:70px;"><h5>&nbsp;&nbsp;&nbsp;所属商家:</h5></td>
						   <td style="width:200px;"><input type="text" class="form-control"  name="genusname" ></td>	 
						   <td  colspan="8" style="text-align: right; ">
								<div class="submit">&nbsp;&nbsp;
								<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
						   </td>	
						</tr>
					</tbody> -->
				</table>
			</form>
		</div>
	</div>
	
	<div class="panel">
		<div class="panel-body data">
			<%-- <div id="blackxunmikelistDiv" request-init ="${blackxunmikelist}"> --%>
				<div id="blackxunmikelistDiv" >
			</div>
		</div>
	 </div>
	</div>
	</c:if>
	</div>
		
	<%--<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script> --%>
	<script type="text/json" id="permissions">
     {detail:'${ btnAu['memberblactlist/memberList/ordinary/details']}'
      ,statusUpdate:'${btnAu['memberblactlist/memberList/ordinary/update'] }'
      ,xedetail:'${ btnAu['memberblactlist/memberList/xunmike/details']}'
     ,xestatusUpdate:'${btnAu['memberblactlist/memberList/xunmike/update'] }'}
   </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    <script src="<%=path%>/ux/js/scrollTablel.js"></script>
    <c:if test="${ btnAu['memberblactlist/memberList/init/ordinarylist']}">
	<script src="<%=path%>/js/member/memberList/blackordinarylist.js"></script>
	</c:if>
 	<c:if test="${ btnAu['memberblactlist/memberList/init/xunmikelist']}">
	<script src="<%=path%>/js/member/memberList/blackxunmikelist.js"></script>
	</c:if>
	<script type="text/javascript">
		inserTitle(' > 会员管理 > <span><a href="memberblactlist/memberList/init.jhtml" target="right">黑名单</a>','memberList',true);
		
		function upstatus(uid,status,updateW) {
		var URL;
			if(updateW=="ordinary"){
			URL='memberblactlist/memberList/ordinary/update.jhtml' + '?t=' + Math.random();
			}else{
			URL='memberblactlist/memberList/xunmike/update.jhtml' + '?t=' + Math.random();
			}
			showSmConfirmWindow(function(){
				formAjax(uid,status,URL,updateW);
			}, "确定恢复到列表？");		
		}
		
		function formAjax(uid,status,URL,updateW){
			$.ajax({
				type : 'post',
				url : URL,
				data : {
					'uid':uid,
					'status':status
				},
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {			 				
		          	$('#prompt').hide();	
					showSmReslutWindow(data.success, data.msg);	
					if(updateW=="ordinary"){
					setTimeout(function(){$("#blackOrdinarysearchForm").get(0).submit();}, 2000);
					}else{
					setTimeout(function(){$("#xunmikesearchForm").get(0).submit();}, 2000);
					}
					
					
				}
			})
		}
	</script>
</body>
</html>
