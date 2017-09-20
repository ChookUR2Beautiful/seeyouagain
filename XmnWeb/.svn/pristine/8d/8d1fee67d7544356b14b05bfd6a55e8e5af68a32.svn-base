<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>二级分类管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	
  </head>
  
  <body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
		<div class="panel">
	<div class="panel-body">
			<form id="searchForm" method="post" role="form" class="form-horizontal">
				<input type="hidden" id="pid" name="pid" value="${pid}">
			   <table style="width:100%;">
					<tbody>
						<tr>
						  <td style="width:5%;"><h5>分类编号:</h5></td>
						  <td style="width:25%;"><input type="text" style="width:90%;" value="" name="tid" class="form-control"></td>
						  <td style="width:5%;"><h5>分类名称:</h5></td>
						  <td style="width:25%;">
						 	<input type="text" style="width:90%;" value="" name="tradename" class="form-control">
						  </td>
						  <td style="width:30%;" colspan="2">
						  <div style="float:left; margin-left: 10px;" class="submit submit-sp">
						    <input type="button" data-bus="query" value="查询全部" class="submit radius-3"> 
						    <input type="reset" data-bus="reset" value="重置全部" class="reset radius-3">
					        </div>
						  </td>	
						
						</tr>
						
					</tbody>
				</table>	
				
			<input type="hidden" value="1" name="page"></form>
		</div>
		</div>
    <div class="panel">
    	<!-- 城市信息展示 -->
		<div class="panel-body">
			<div id="twoTraderList"></div>
		</div>
	</div>
	<%-- '${btnAu['user_terminal/searchTags/setDisplaySearchTag'] }' --%>
	<script type="text/json" id="permissions">{
 		twoTraderMgt :'${btnAu['user_terminal/sellerTrader/twoTrader/SellerMgt/init'] }'
	}</script>
   <jsp:include page="../../common.jsp"></jsp:include>
     <script src="<%=path%>/ux/js/scrollTablel.js"></script>
   <script type="text/javascript">
   		var twoTraderList;	
		$(function(){ 
			//var pid = $("#pid").val();
			twoTraderList = $('#twoTraderList').page({
				url : baseURI+"/user_terminal/sellerTrader/twoTrader/init/list.jhtml",
				success : success,
				pageBtnNum : 10,
				paramForm : 'searchForm',
				error:function(){
					showSmReslutWindow(false, "请求失败");
				}
			});
			inserTitle(' > 用户端管理 > <a href="user_terminal/sellerTrader/init.jhtml" target="right">商家分类管理</a> > <a href="user_terminal/sellerTrader/twoTrader/init.jhtml?tid='+$("#pid").val()+'" target="right">商家二级分类管理</a>','sellerTrader',true);
		});
		
		function success(data, obj){
			var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;二级分类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个分类&nbsp;</font></caption>';
			obj.find('div').eq(0).scrollTablel({
				caption : captionInfo,
				data:data.content, 
				cols:[{
					title : "编号",
					name : "tid",
					type : "string",
					width : 150
				},{
					title : "分类名称",
					name : "tradename",
					type : "string",
					width : 150
				},{
					title : "商家数量",
					name : "number",
					type : "string",
					width : 180
				},{
					title : "排序",
					name : "orderNum",
					type : "string",
					width : 150
				}],//操作列
				handleCols : {
					title : "操作",// 标题
					queryPermission : ["twoTraderMgt"],// 不需要选择checkbox处理的权限
					width : 150,// 宽度
					cols : [{
						title : "管理",// 标题
						linkInfoName : "href",
						linkInfo : {
							href :"user_terminal/sellerTrader/twoTrader/SellerMgt/init.jhtml",
							param : ["tid"],
							customParam : ["&pid="+$("#pid").val()],
							permission : "twoTraderMgt"
						},
						customMethod : function(value, data) {
							return data.number==0 ? "-": $(value).attr("data-title",data.tradename+">商家管理")[0].outerHTML;
						}
					}] 
				}},permissions);
		}
		
   </script>
  </body>
</html>
