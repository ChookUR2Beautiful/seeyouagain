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
    
    <title>商家分类管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
	
  </head>
  
  <body style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<input type="hidden" id="category" value="${pid}">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
   <div class="panel">
   	<div class="panel-body">
   		<form id="sellerTraderForm">
   			<input type="hidden" id="area" name="area" value="${tid}">
   			<div class="row">
		   		<div class="col-xs-1"><h5>商家编号:</h5></div> 
		   		<div class="col-xs-2">
		   			<input class="form-control" name="sellerid" type="text">
		   		</div>
		   		<div class="col-xs-1"><h5>商家名称:</h5></div> 
		   		<div class="col-xs-2">
		   			<input class="form-control" name="sellername" type="text">
		   		</div>
		   		<div class="col-xs-2 submit">
					<input type="button" data-bus="query" value="查询全部" class="submit radius-3">
				</div>
				<div class="col-xs-1 submit">
					<input type="reset" data-bus="reset" value="重置全部" class="reset radius-3">
				</div>
	   		</div>
   		</form>
   	</div>
   </div>
   
    <div class="panel">
		<div class="panel-body">
			<div style="margin-bottom: 5px;" class="btn-group" id="btnGroup">
	            <button  class="btn btn-success" type="button" handle="remove" ref="user_terminal/sellerTrader/twoTrader/SellerMgt/init/addSeller/init/list.jhtml">已添加</button>&nbsp;&nbsp;	  
				<button  class="btn btn-default" type="button" handle="add" ref="user_terminal/sellerTrader/twoTrader/SellerMgt/init/noAddSeller/init/list.jhtml">未添加</button>
			</div>
			<div id="sellerTraderList"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{
		sellerAdd :'${btnAu['user_terminal/sellerTrader/twoTrader/SellerMgt/add'] }',
		sellerRemove :'${btnAu['user_terminal/sellerTrader/twoTrader/SellerMgt/remove'] }'
	}</script>
   <jsp:include page="../../common.jsp"></jsp:include>
     <script src="<%=path%>/ux/js/scrollTablel.js"></script>
	 <script type="text/javascript">
	 	$(function(){
	 		
	 		var btnGroup = $("#btnGroup");
	 		init(btnGroup.find("button.btn-success"));
	 		btnGroup.on("click","button",function(event){
	 			var $form = $("#sellerTraderForm");
	 			reset($form );
	 			var $that =  $(this);
	 			var $currentBtn = btnGroup.find("button.btn-success");
	 			if(!$that.is($currentBtn)){
	 				$that.removeClass("btn-default").addClass("btn-success");
	 				$currentBtn.removeClass("btn-success").addClass("btn-default");
	 				init($that,$form);
	 			}
	 		});
	 		inserTitle(' > 用户端管理 > <a href="user_terminal/sellerTrader/init.jhtml" target="right">商家分类管理</a> > <a href="user_terminal/sellerTrader/twoTrader/init.jhtml?tid='+$("#category").val()+'" target="right">商家二级分类管理</a> > 二级分类商家管理','sellerTraderSellerMgt',true);
	 	});
	 	
	 	function reset(form){
	 		$(':input',form)
			 .not(':button, :submit, :reset, :hidden')
			 .val('')
			 .removeAttr('checked')
			 .removeAttr('selected');
	 	}
	 	
	 	function init($that,form){
	 		var $sellerTraderList = $("#sellerTraderList");
	 		$(":input",form).off();
	 		$sellerTraderList.off();
	 		var page = $sellerTraderList.page({
 				url : baseURI+$that.attr("ref"),
				success : success,
				pageBtnNum : 10,
				paramForm : 'sellerTraderForm',
				error:function(){
					showSmReslutWindow(false, "请求失败");
				}
	 		});
	 	}
	 	
	 	
	 	function getHandleCol(handleName){
	 		return  handleName =="remove" ? 
	 		 {
				title : "操作",// 标题
				queryPermission : ["sellerRemove"],// 不需要选择checkbox处理的权限
				width : 150,// 宽度
				cols : [{
					title : "移除",// 标题
					linkInfoName : "method",
					linkInfo : {
						method :"remove",
						param : ["sellerid"],// 参数
						permission : "sellerRemove"// 单列权限
					}
				}]}:{
					title : "操作",// 标题
					queryPermission : ["sellerAdd"],// 不需要选择checkbox处理的权限
					width : 150,// 宽度
					cols : [{
						title : "添加",// 标题
						linkInfoName : "method",
						linkInfo : {
							method :"add",
							param : ["sellerid"],// 参数
							permission : "sellerAdd"// 单列权限
						}
					}]};
	 	}
	 	
	 	function success(data, obj){
	 		var table =obj.find('div').eq(0);
	 		var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;商家信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个商家&nbsp;</font></caption>';
	 		var handleCols  =  getHandleCol($("#btnGroup").find("button.btn-success").attr("handle"));
	 		table.scrollTablel({
		    	caption : captionInfo,
				//数据
				data:data.content, 
				 //数据行
				cols:[{
					title : "商家编号",
					name : "sellerid",
					type : "string",
					width : 100
				},{
					title : "商家名称",
					name : "sellername",
					type : "string",
					width : 100
				},{
					title : "所属连锁店",
					name : "lssellername",
					type : "string",
					width : 100
				},
				{
					title : "所属分类",
					name : "hyText",
					type : "string",
					width : 100
				}] ,
				//操作列
			handleCols : handleCols },permissions);
	 
	 	}  
	 	
	 	function add(sellerId){
	 		request(sellerId,"/user_terminal/sellerTrader/twoTrader/SellerMgt/add.jhtml","确定添加分类到该商家?");
	 	}
	 	
		function remove(sellerId,url,title){
			request(sellerId,"/user_terminal/sellerTrader/twoTrader/SellerMgt/remove.jhtml","确定在当前分类中删除该商家?");
	 	}
		
		function request(sellerId,url,title){
			showSmConfirmWindow(function(){
	 			$.post(baseURI+url,{
					"sellerid"  : sellerId,
					"genre" :  $("#area").val(),
					"category" : $("#category").val()
				}, 'json'
				).done(function(data){
					if(data.success){
						init($("#btnGroup").find("button.btn-success"));
					}
					showSmReslutWindow(data.success, data.msg);
			 	}).fail(function(){
				  	$('#prompt').hide();
					$('#triggerModal').modal('hide');
					showSmReslutWindow(false, "请求失败");
			  });
			},title);	
		}
	 </script>
	 </body>