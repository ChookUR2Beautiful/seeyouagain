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
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
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
							<td style="width:5%;"><h5>用户昵称:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="nname" style="width:90%;"></td>	
													
							<td style="width:5%;"><h5>手机号:</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="phone" style="width:90%;"></td>
							
							<td style="width:5%;"></td>
							<td style="width:25%;" style="width:90%;"></td>	
							
							</tr>
							<tr>
													
							<td style="width:5%;"><h5>注册时间:</h5></td>
							<td style="width:25%;">
							<input type="text" name ="staregtime" placeholder="yyyy-MM-dd" class="form-control form-datetime"style="width:42%;float:left">
							<label style="float: left;">&nbsp;--&nbsp;</label>
							<input type="text" name ="endregtime"   placeholder="yyyy-MM-dd" class="form-control form-datetime" style="width:42%;float:left"> 
							</td>
							<td style="width:5%;"><h5>注册来源:</h5></td>
							<td style="width:25%;">
								<select class="form-control" name="regtype" style="width:90%;">
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
							<td  colspan="12" style="text-align: right;width:25%; ">
								<div class="submit"><input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:42%;"/>
								<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:42%;"/>
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
			<div class="row">
				<div class="col-md-1"><h5>已选择用户:</h5></div>
				<div class="col-md-11"><div id="choseDatas" class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="width:100%"><ul class="chosen-choices"></ul></div></div>
			</div>
			<div id="memberListDiv" request-init="${requestInit}">
			</div>
		</div>
	</div>
	<div class="panel" style="padding-top: 10px;padding-bottom : 10px;">
		<div class="return" align="center">
			<button class="btn btn-success closeChosen" type="button" ><i class="icon-ok-sign"></i>&nbsp;保存</button>&nbsp;&nbsp;
			<button class="btn btn-default closeChosen" id="allCancel"><span class="icon-reply"></span>&nbsp;取消</button>
		</div>
	</div>
	<%--<script type="text/json" id="permissions">{xg:'${ btnAu['businessman/seller/update']}',ck:'${btnAu['businessman/seller/getInit'] }',zh:'${btnAu['businessman/sellerAccount/init'] }',zk:'${btnAu['businessman/sellerAgio/init'] }'}</script> --%>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">
		var mem = $('#memberListDiv');
		var div;
		$(function(){
			var url  = [$(mem).attr("request-init"),".jhtml"].join("");
			div = $(mem).page({
				url : url,
				dataType:"html",
				success : handle,
				pageBtnNum : 10,
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
		});
		$("#allCancel").click(function(){
	    window.parent.searchChosen.allCancel();
	   })
		function handle(data){
			var table  =$(mem).find("table");
			if(table.length==0){
				$(mem).prepend(data);
				table  =$(mem).find("table");
			}else{
				$(table).html(data);
			}
			window.parent.searchChosen.choseload();
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
		function checkSome(obj){
			$("input:checkbox").each(function(){
				this.checked=obj.checked;
			});
		   $("input:checkbox").each(function(){
			  if(undefined!=$(this).attr("sellername")){
				   window.parent.searchChosen.memberChose($(this).attr("sellerid"),$(this).attr("sellername"),this);  
			   }
		   });
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
	</script>
	
</body>
</html>
