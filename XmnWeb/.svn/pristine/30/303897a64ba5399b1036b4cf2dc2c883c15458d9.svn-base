<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<style type="text/css">
		body {margin:0; font-size:12px; font-family:Verdana, Geneva, sans-serif; background:#EEE;}
		h1,h2,h3 { display:block; margin:0; padding:0; font-size:24px; line-height:24px; font-weight:normal;}
		img { border:0;}
		dl,dt,dd,ul,li { display: block; margin: 0; padding: 0; list-style-type: none;}
		a { text-decoration: none;}
		*:focus { outline: none; }
		.wrap { width:1000px; margin:0 auto;}
		.clear { clear:both;}
		.fms { font-family:'Microsoft Yahei', '微软雅黑';}
		#lefts { width: 220px; height:100%; background:#371A1A;position: fixed;}
		#lefts .logo {height: 86px; background:#CC3636; overflow: hidden;} 
		#lefts .logo img { width: 72px; height: 70px; float: left; padding: 6px 10px 0 14px;}
		#lefts .logo h1 { float: left; font-size: 18px; color: #FFF; padding: 52px 0 0;}
		#lefts dl.list { padding:0 0 1px;}
		#lefts dl.list dt {height: 50px; line-height: 50px; font-size: 16px; background:#4E2E2E; color:#A38F8F; overflow:hidden;}
		#lefts dl.list dt i { display:block; float:left; width:60px; height:50px;}
		#lefts dl dt i.i1 { background:url(resources/web/images/lefts.png) 15px 15px no-repeat;}
		#lefts dl dt i.i2 { background:url(resources/web/images/lefts.png) 18px -88px no-repeat;}
		#lefts dl dt i.i3 { background:url(resources/web/images/lefts.png) 15px -184px no-repeat;}
		#lefts dl dt i.i4 { background:url(resources/web/images/lefts.png) 15px -288px no-repeat;}
		#lefts dl.on dt i.i1 { background:url(resources/web/images/lefts.png) -85px 15px no-repeat;}
		#lefts dl.on dt i.i2 { background:url(resources/web/images/lefts.png) -82px -88px no-repeat;}
		#lefts dl.on dt i.i3 { background:url(resources/web/images/lefts.png) -85px -184px no-repeat;}
		#lefts dl.on dt i.i4 { background:url(resources/web/images/lefts.png) -85px -288px no-repeat;}
		#lefts dl.on dt {color:#F27C7C;}
		#lefts dl dt span { display:block; float:left; width:143px; background:url(resources/web/images/ico.png) 130px -380px no-repeat;}
		#lefts dl.on dt span { background:url(resources/web/images/ico.png) 130px -430px no-repeat;}
		#lefts dl.list dd { display:none;}
		#lefts dl.on dd { display:block;}
		#lefts dl.list dd a { display: block;color: #8F95A3;text-indent: 30px; line-height: 38px;}
		#lefts dl.list dd a:hover,
		#lefts dl.list dd a.this { color:#F27C7C;}
	</style>
  </head>
  <body >
	<div id="lefts" class="fms" style="overflow-x: hidden; overflow-y: auto;">
		<c:forEach var="parentlist" items="${leftShow[0]}" varStatus="parentInfo">
		<dl class="list list-1 <c:if test="${parentInfo.first}">on</c:if>">
			<dt><i class="i1"></i><span>${parentlist.authorityName}</span></dt>
			<c:forEach var="list" items="${leftShow[parentlist.id]}">
				<dd ><a href="${list.authorityUrl}.jhtml" target="right">${list.authorityName}</a></dd>
			</c:forEach>
		</dl>
		</c:forEach> 
		
	<!-- 	<div id="lefts" class="fms">
	 <dl class="list list-1 on">
			<dt><i class="i1"></i><span>商家管理</span></dt>
			<dd><a href="businessman/sellerDetailed/init.jhtml" target="right">点赞管理</a></dd>
			<dd><a href="businessman/comment/seller/init.jhtml" target="right">评论管理</a></dd>
			<dd><a href="businessman/appPush/init.jhtml" target="right">营销信息推送</a></dd>
			<dd><a href="businessman/sellerPending/init.jhtml" target="right">商家信息待审核</a></dd>
			 <dd><a href="businessman/sellerBlack/init.jhtml" target="right">商家黑名单管理</a></dd> 
		 	<dd><a href="businessman/sellerApply/init.jhtml" target="right">商家信息修改申请</a></dd>
			<dd><a href="businessman/orderinvoice/init.jhtml" target="right" >申请发票商家</a></dd>
			<dd><a href="businessman/seller/init.jhtml" target="right">全部商家信息</a></dd>
			 <dd><a href="businessman/agioRecord/init.jhtml" target="right">折扣设置记录</a></dd>
			<dd><a href="businessman/loginRecord/init.jhtml" target="right">商家帐号登录记录</a></dd> 
			 <dd><a href="http://www.baidu.com" target="right">提现商家信息</a></dd> 
		</dl>
		<dl class="list list-1">
			<dt><i class="i1"></i><span>合作商管理</span></dt>
			<dd><a href="common/advertising/init.jhtml" target="right">广告轮番图管理</a></dd>
			<dd><a href="business_cooperation/video/init.jhtml" target="right">产品介绍管理</a></dd>
			<dd><a href="business_cooperation/subject/init.jhtml" target="right">话题交流管理</a></dd>
			
		 	<dd><a href="common/systemInfo/init.jhtml" target="right">系统信息发布管理</a></dd>
			<dd><a href="common/systemMsg/init.jhtml" target="right">推送消息管理</a></dd>
			<dd><a href="business_cooperation/joint/init.jhtml" target="right" >合作商列表</a></dd>
			 <dd><a href="businessman/seller/init.jhtml" target="right">合作商提现管理</a></dd>
		</dl> 
		<dl class="list list-1">
			<dt><i class="i1"></i><span>订单管理</span></dt>
			<dd><a href="billmanagerment/allbill/init.jhtml" target="right">所有订单</a></dd>
			<dd><a href="billmanagerment/refund/init.jhtml" target="right">商户申诉订单</a></dd>
			<dd><a href="billmanagerment/refundhistory/init.jhtml" target="right">申诉处理历史</a></dd>
		</dl> 
		<dl class="list list-1">
			<dt><i class="i1"></i><span>系统设置</span></dt>
			<dd><a href="system_settings/user/init.jhtml" target="right">用户管理</a></dd>
			<dd><a href="system_settings/role/init.jhtml" target="right">角色管理</a></dd>
			<dd><a href="common/area/init.jhtml" target="right">区域管理</a></dd>
			<dd><a href="http://www.baidu.com" target="right">团队管理</a></dd>
			<dd><a href="http://www.baidu.com" target="right">短信内容描述</a></dd>
			<dd><a href="common/appVersion/init.jhtml" target="right" >版本管理</a></dd>
			<dd><a href="system_settings/log/init.jhtml" target="right">日志管理</a></dd> 
		</dl>    -->
	</div>
	<script type="text/javascript" src="<%=path %>/resources/web/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
		  $(".doit input,.submit input").hover(function(){
			  $(this).addClass("inputon")
			},function(){
				$(this).removeClass("inputon")
			});
			var s_width = $(".show").width();
			$(".admin").width(s_width);
			$(".crumbs").width(s_width);
		});
		$("#lefts dl dt").click(function (){
			if($(this).parent("dl").hasClass("on")){
				$(this).parent("dl").removeClass("on");
			}else{
				$(this).parent("dl").addClass("on").siblings().removeClass("on");
			}
		})
	</script>
	</body>
</html>
