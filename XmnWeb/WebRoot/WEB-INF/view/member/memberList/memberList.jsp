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
  </head>
  
  <body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<input type="hidden" id="path" value="<%=path%>" />
		<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="searchForm">
				<table style="width:100%;">
				
					<tbody>
						<tr>
							<td><h5>&nbsp;&nbsp;用户编号:</h5></td>
							<td>
								<input type="text" class="form-control" name="uid" value="" style="width:78%">
							</td>
							<td style="width:100px;"><h5>&nbsp;&nbsp;手机号码:</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control" name="phone" value="" style="width:77%">
							</td>
							<td><h5>&nbsp;&nbsp;所属商家：</h5></td>
							<td>	
								<input type="text" class="form-control" name="genusname" value="" style="width:76%">
							</td>
							
						</tr>
						
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;用户昵称:</h5></td>
							<td style="width:400px !important;">
								<input type="text" class="form-control" name="nname" value="" style="width:78%">
							</td>
							<td><h5>&nbsp;&nbsp;注册区域：</h5></td>
							<td>
									<div class="input-group" id="ysqy" style="width:78%;" >
							</td>
							
							<td style="width:100px;"><h5>&nbsp;&nbsp;会员类型:</h5></td>							
							<td style="width:400px;">
								<select class="form-control" name="usertype" style="width:76%;">
									<option value="">请选择</option>
									<option value="1">普通会员</option>
									<option value="2">寻蜜客</option>
								</select>
							</td>
							
						</tr>
						
						
						<tr>
							<td style="width:100px;"><h5>&nbsp;&nbsp;注册时间：</h5></td>
							<td>
								<input type="text" name="staregtime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:36%;float:left">
								<label style="width:6%;float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name="endregtime" value="" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:36%;float:left">
						    </td>
						    
						    <td style="width:100px;"><h5>&nbsp;&nbsp;注册来源：</h5></td>
							<td style="width:400px;">
									<select class="form-control" tabindex="2" name="regtype" style="width:77%;">
									    <option value="">请选择</option>
										<option value="1">旅游众筹网站</option>
										<option value="2">寻蜜鸟网站</option>
										<option value="3">400客服电话</option>
										<option value="4">旅游众筹安卓客户端</option>
										<option value="5">旅游众筹IOS客户端</option>
										<option value="6">寻蜜鸟安卓客户端</option>
										<option value="7">寻蜜鸟IOS客户端</option>
						             </select>
						   </td>
						    
						    <td style="width:100px;"><h5>&nbsp;&nbsp;状态：</h5></td>
							<td style="width:400px !important;">
								<select class="form-control" tabindex="2" name="status" style="width:76%;">
									   <option value="">请选择</option>
										<option value="1">正常</option>
										<option value="2">锁定</option>
										<option value="3">注销</option>
										<option value="4">黑名单</option>
						             </select>
							
							</td>
						    
							
						</tr>
						<tr>
						<td colspan="4"></td>
							<td colspan="2">
								<div class="submit" style="text-align: left; ">&nbsp;&nbsp;<input class="submit radius-3" type="button" value="查询全部" data-bus="query">
								<input class="reset radius-3" type="reset" value="重置全部" data-bus="reset">
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
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['member/memberList/add'] }"><button type="button" class="btn btn-success"  data-type="ajax" data-url="member/memberList/add/init.jhtml" data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button></c:if>
				<%-- <c:if test="${!empty btnAu['member/memberList/export'] }"> --%>
					<c:if test="${!empty btnAu['member/memberList/export'] }"><button type="button" class="btn btn-default"  data-type="ajax" data-url="member/memberList/export/init.jhtml" data-pisition=''  data-title='会员数据导出'  data-width="1000px"   data-toggle="modal" ><span class="icon-download-alt"></span>&nbsp;导出</button></c:if>
				<%-- </c:if> --%>
			</div>
			<%-- <div id="memberListDiv" request-init ="${requestInit}">
				
			</div> --%>
			<div id="memberListDiv">
				
			</div>
		</div>
	</div>
	<%--<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script> --%>
	
	<script type="text/json" id="permissions">{detail:'${ btnAu['member/memberList/details']}',update:'${btnAu['member/memberList/update']}',statusUpdate:'${btnAu['member/memberList/status/update']}',wallet:'${btnAu['member/memberList/viewWallet']}',resetPassW:'${btnAu['businessman/seller/resetDepositPassW']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>//resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
    <script src="<%=path%>/resources/upload/upload.js"></script> 
    
    <script src="<%=path%>/ux/js/scrollTablel.js"></script>
     <script src="<%=path%>/js/member/memberList/memberList.js?v=1.0.1"></script>
    
	<!--  <script type="text/javascript">
		inserTitle(' > 会员管理 > <span><a href="member/memberList/init.jhtml" target="right">会员列表</a>','memberList',true);
		var mem = $('#memberListDiv');
		var div;
		$(function(){
			var url  = [$(mem).attr("request-init"),".jhtml"].join("");
			div = $(mem).page({
				url : url,
				dataType:"html",
				success : handle,
				pageBtnNum : 10,
				pageSize:15,
				paramForm : 'searchForm'
			});
			
			$("body").on("submit",".modal-body form",function(event){
				var target = event.target;
				$.ajax({
					url : $(target).attr("action"),
					type : $(target).attr("method"),
					data :jsonFromt($(target).serializeArray()),
					cache:false
				}).done(function ( data ) {
					$('#triggerModal').modal('hide');
					showSmReslutWindow(data.success, data.msg);
					if(data.success){
						div.reload();
					}
					
				});
				return false;
			});
			$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				minView :2,
				maxView :3,
				autoclose: true,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd'
			});
			
			//导出数据    
			$("#export").click(function(){
				var pageSize = $("#memberTable").attr("data-total");
				$form = $("#searchForm").attr("action","member/memberList/export.jhtml?pageSize="+pageSize);
				var $input = $("<input type='hidden' name='pageSize' value='"+pageSize+"'>");
				$form.append($input);
				$form[0].submit();
				$input.remove();
			});
			
		var ld = $("#ysqy").areaLd({
		isChosen : true,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"},{name:"region",tipTitle:"--区--"}]
	    });
	    		//合作商(搜索查询)
	$("#jointid").chosenObject({
		id : "jointid",
		hideValue : "jointid",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		limit : -1,
		isChosen:true
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ysqy").find("select").trigger("chosen:updated");
		$("#jointid").trigger("chosen:updated");
	});
		});  
		
		function handle(data){
			var table  =$(mem).find("table");
			if(table.length==0){
				$(mem).prepend(data);
				table  =$(mem).find("table");
			}else{
				$(table).html(data);
			} 
		}
		  
		function formSubmit(){
			var form =$("body").find(".modal-body form");
			var action = $(form).attr("action");
			var method = $(form).attr("method");
			$.ajax({
				url : action,
				type : method,
				data :jsonFromt($(form).serializeArray()),
				cache:false
			}).done(function ( data ) {
				$('#triggerModal').modal('hide');
				showSmReslutWindow(data.success, data.msg);
				if(data.success){
					div.reload();
				}
				
			});
			return false;
		}
		/* 	$("body").on("click","button.edit,button.add",function(evnt){
				setTimeout(function(){
					$("#tx").uploadImg({
						urlId : "avatar",
						showImg : $('#avatar').val()
					});
				},300);
			}) */
		/* 	var div = $("#memberListDiv");
			var url  = [$(div).attr("request-init"),".jhtml"].join("");
			$.ajax({
				url : url,
				cache:false
			}).done(function ( data ) {
				  		console.info(data);
					}); */
		/* 	$(div).on("click","table thead input[type=checkbox]",function(event){
				var checked = event.target.checked;
				$(div).find("table tbody input[type=checkbox]").prop("checked",checked);
			}); */
		
	/* 	var tpl={
				table:"<table class='table table-datatable table-hover table-striped tablesorter table-data'></table>",
				thead :"<thead><tr class='text-center'><td class='text-center text-important'>操作</td><td class='text-center text-important'>用户昵称</td><td class='text-center text-important'>手机号码</td><td class='text-center text-important'>性别</td><td class='text-center text-important'>注册时间</td><td class='text-center text-important'>状态</td><td class='text-center text-important'>类型</td><td class='text-center text-important'>所属商家</td><td class='text-center text-important'>城市</td><td class='text-center text-important'>所属区域</td></tr></thead>",
				tbody :"<tbody></tdody>",
				tr:"<tr class='text-center'><td><button type='button' class='btn btn-primary radius edit'  title='编辑' data-type='ajax' data-url='member/memberList/update/init.jhtml?uid={uid}' data-toggle='modal'><i class='icon-edit'></i>&nbsp;编辑</button>&nbsp;&nbsp;<button type='button' class='btn btn-info radius' title='明细' data-type='ajax' data-url='member/memberList/details.jhtml?uid={uid}' data-toggle='modal'><i class='icon-search'></i>&nbsp;明细</button></td><td>{nname}</td><td>{phone}</td><td>{sex}</td><td>{regtime}</td><td>{status}</td><td>{usertype}</td><td>{genusname}</td><td>{city}</td><td>{region}</td></tr>"
		}
	 */
		
				/* var tr = new Array();
				var key;
				var tpltable = tpl.table;
				$.each(result,function(i,obj){
					var tpltr = tpl.tr;
					for(key in obj){
						tpltr = trCombination(tpltr,key,obj[key],true);
					}
					tr.push(tpltr);
				});
				
				var table  =$(mem).find("table");
				if(table.length==0){
					$(mem).prepend(tpltable);
					table  =$(mem).find("table");
				}else{
					$(table).html(tpltable);
				}
				$(table).append(tpl.thead);
				$(table).append(tpl.tbody);
				$(mem).find("tbody").html(tr.join(""));
			} }*/
		
		
	/* 	function trCombination(tr,replaceEment,replaceValue,replaceAll){
			var replaceEment ="{"+replaceEment+"}";
			if(replaceAll){
				replaceEment = new RegExp(replaceEment,'g');
			}
			return tr.replace(replaceEment,replaceValue);
		}
		
		
		function tplCombination(data){
			
		} */
		function upstatus(uid,status) {
			    
			showSmConfirmWindow(function(){
				formAjax(uid,status);
			}, "确定加入黑名单？");		
		}
		
		function formAjax(uid,status){
		console.info(status);
			$.ajax({
				type : 'post',
				url : 'member/memberList/status/update.jhtml' + '?t=' + Math.random(),
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
		        	setTimeout(function(){$("#searchForm").get(0).submit();}, 2000);
					
				}
			})
			
			
		}
	</script>  -->
	
<!-- 	<script type="text/javascript">
	function formSubmit(){
		var form =$("body").find(".modal-body form");
		var action = $(form).attr("action");
		var method = $(form).attr("method");
		var memberListDiv = $("#memberListDiv");
		$.ajax({
			url : action,
			type : method,
			data :jsonFromt($(form).serializeArray()),
			cache:false
		}).done(function ( data ) {
			$('#triggerModal').modal('hide');
			showSmReslutWindow(data.success, data.msg);
			if(data.success){
				//div.reload();
				memberListDiv.reload();
			}
			
		});
		return false;
	}
	</script> -->
	
</body>
</html>
