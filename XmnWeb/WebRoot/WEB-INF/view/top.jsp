<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">       
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<style type="text/css">
	 body { margin:0; font-size:12px; font-family:Verdana, Geneva, sans-serif; background:#EEE;overflow: hidden;}
	 .logo {height: 86px; background:#CC3636; overflow: hidden;} 
	 .logo img { width: 72px; height: 70px; float: left; padding: 6px 10px 0 14px;}
	 .logo h1 { float: left; font-size: 18px; color: #FFF; padding: 43px 0 0;}
	 .fms { padding:30px 20px 0 0;overflow:hidden;}
	 .fms .a { display:block; height:18px; line-height:18px; float:right;  text-indent:24px; color:#34251A;}
	 .fms a:hover { color:#000;}
	 .fms .welcome { height:20px; line-height:20px; float:right; color:#AD7858; padding:0 30px 0 0; background:url(resources/web/images/ico.png) 0 0 no-repeat; text-indent:28px;}
	 .fms .welcome span { font-weight:bolder; color:#34251A;}
	</style>
  </head>
 
  <body style="background-color:#DDB1B1;min-width:1024px;">
	 <div class="logo" style="width:220px;float:left;">
	 	<img src="resources/web/images/logo.png" alt="寻蜜鸟分账管理系统" /><h1>业务管理系统</h1>
	 </div>
	 <div style="width:800px;float:right;" class="fms">
	 	<div class="fms"><a href="logout.jhtml" class="a" style="background: url(resources/web/images/ico.png) 0 -100px no-repeat;" target="_parent">退出</a><a class="a" href="javascript:;" id="pasw" style="padding-right: 35px;" ><li class="icon-edit" style="height:18px;line-height:18px;"></li>修改密码</a><div class="welcome">欢迎您登录，当前版本为【XmnWebV3.0.8】<span>${currentUs.name}</span> </div></div>
	 </div>
	 <div id="divForm" style="display: none;">
	 	<div class="modal fade" id="currentPasForm">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">修改密码</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form"  id="currentchangePasswordForm">
								<table class="table table-form">
									<tbody>
										<tr>
											<th style="font-size:12px;border-bottom: none !important;"><h5>&nbsp;&nbsp;新密码:</h5></th>	
											<th colspan="2" style="font-size:12px;	border-bottom: none !important;">
												<input type="password" class="form-control" id="password" name="password" >
											</th>
										</tr>
							 			<tr>
							 					<th style="font-size:12px;	border-bottom: none !important;"><h5>&nbsp;&nbsp;确认密码:</h5></th>
							 					<th colspan="2" style="font-size:12px;	border-bottom: none !important;">
							 						<input type="password" class="form-control" name="repassword"  >
							 					</th>
							 					
							 			</tr>
							 			<tr>
							 					<th style="border-bottom: none !important;"></th>
							 					<th  style="font-size:12px;	border-bottom: none !important;"> 
							 						<button type="submit" class="btn btn-success"  ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
													
							 					</th>
							 					<th  style="font-size:12px;	border-bottom: none !important;"> 
							 						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
							 					</th>
							 			</tr>	
							 		</tbody>
						 	</table>
						 	 <div id="msg" style="color: #fff;display: none;text-align: center;margin: 18px 0;padding: 20px;text-shadow: 0 1px 1px rgba(0, 0, 0, 0.15);"></div>
						 </form>
					</div>
				</div>
			</div>
		</div>
	 </div>
	<script src="<%=path%>/resources/zui/lib/jquery/jquery.js"></script>
	<script src="<%=path%>/resources/zui/js/zui.js"></script>
	<script src="<%=path%>/resources/web/js/popoverUtil.js"></script>
	<script src="<%=path%>/resources/web/js/util.js"></script>
	<script type="text/javascript" src="<%=path%>/js/system_settings/currentchangePassword.js"></script>
</body>
</html>
