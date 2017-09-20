<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<title></title>
<style>
body {
	margin: 0;
	font-size: 12px;
	font-family: 'Microsoft Yahei', '微软雅黑';
	overflow-y: auto;
	padding: 10px 30px;
}

th {
	font-size: 12px;
}
</style>
</head>
<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="shareForm">
			   <table style="width:100%;">
					<tbody>
						<tr>
						  <td style="width:5%;"><h5>分享编号:</h5></td>
						  <td style="width:25%;"><input type="text" class="form-control"  name="sid" value="${param.sid}" style = "width:90%;"></td>
						  <td style="width:5%;"><h5>分享链接:</h5></td>
						  <td style="width:25%;"><input type="text" class="form-control"  name="link" value="${param.link}" style = "width:90%;"></td>
						  <td style="width:5%;"></td>
						  <td style="width:25%;"></td>	
						</tr>
						<tr>
						  <td style="width:5%;"><h5>分享标题:</h5></td>
						  <td style="width:25%;"><input type="text" class="form-control"  name="title" value="${param.sid}" style = "width:90%;"></td>
						  <td style="width:5%;"><h5>分享范围:</h5></td>
						  <td style="width:25%;">
						  <select name="range" class="form-control" style = "width:90%;">
							<option value="">--请选择--</option>
							<option value="1"<c:if test="${!empty param.range}">${param.range==1?"selected":""}</c:if>>全国</option>
							<option value="2"<c:if test="${!empty param.range}">${param.range==2?"selected":""}</c:if>>指定城市</option>
							<option value="3"<c:if test="${!empty param.range}">${param.range==3?"selected":""}</c:if>>指定商家</option>
						</select>
						  </td>
						  <td colspan="2" style="width:30%;">
						  <div class="submit submit-sp" style="float:left; margin-left: 10px;">
						    <input class="submit radius-3" type="button" value="查询全部" data-bus='query' /> 
						    <input type="reset" class="reset radius-3" value="重置全部" data-bus='reset' />
					        </div>
						  </td>	
						
						</tr>
						
					</tbody>
				</table>	
				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>

	<div class="panel">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['user_terminal/share/add']}">
					<a type="button" id="addShareBto" class="btn btn-success"
						href="user_terminal/share/add/init.jhtml?isType=add"><span
						class="icon-plus"></span>&nbsp;添加</a>
				</c:if>
			</div>
			<div id="shareList"></div>
		</div>
	</div>
<script type="text/javascript">
	contextPath = '${pageContext.request.contextPath}'
</script>
<script type="text/json" id="permissions">{list:'${ btnAu['user_terminal/share/init/list']}',update:'${ btnAu['user_terminal/share/update']}'}</script>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/page/page.js"></script>
<script src="<%=path%>/ux/js/grid.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
<script src="<%=path%>/ux/js/scrollTablel.js"></script>
<script type="text/javascript">
var shareList;
$(function() {
	// 区域
	/* var ld = $("#ld").areaLd({
		isChosen : true,
		showConfig : [ {
			name : "province",
			tipTitle : "--省--"
		}, {
			name : "city",
			tipTitle : "--市--"
		} ]
	}); */
	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/user_terminal/share/init.jhtml';
			location.href = url;
		}
		//只要重置按钮一按下，就立即执行清空chosen的内容
		setTimeout(function() {
			$("#ld").find("select").trigger("chosen:updated");
		}, 0);
	});
	$(".form-date").datetimepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		minView : "month",
		pickerPosition : "bottom-left"
	});
	inserTitle(
			' >用户端管理> <span><a href="user_terminal/share/init.jhtml" target="right">分享信息管理</a>',
			'shareList', true);
	shareList = $('#shareList').page({
		url : 'user_terminal/share/init/list.jhtml',
		pageBtnNum : 10,
		paramForm : 'shareForm',
		success : success
		}
	);
});
function success(data, obj) {
			var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">分享信息列表<font style="float:right;">共计【'+data.total+'】条分享信息&nbsp;</font></caption>';
			//var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条分享信息&nbsp;</font></caption>';
			var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#shareForm").serialize());
			updateAddBtnHref("#addShareBto", "&"+callbackParam);
			obj.find('div').eq(0).scrollTablel({
			    	//checkable :false,
			    	//identifier : "bid",
			    	tableClass :"table-bordered table-striped info",
			    	callbackParam : callbackParam,
			    	caption : captionInfo,
					//数据
					data:data.content, 
					 //数据行
					cols:[{
						title : "分享信息编号",// 标题
						name : "sid",
						//sort : "up",
						width : 150,// 宽度
						leng : 8,//显示长度
						type:"number",//数据类型
						
				},{
						title : "分享标题",// 标题
						name : "title",
						width : 150,// 宽度
						leng : 8,//显示长度
						type:"stirng",//数据类型
						
				},{
						title : "分享链接",// 标题
						name : "link",
						width : 150,// 宽度
						leng : 8,//显示长度
						type:"stirng",//数据类型
						
				},{
						title : "分享范围",// 标题
						name : "range",
						width : 150,// 宽度
						leng : 8,//显示长度
						type:"stirng",//数据类型
						customMethod : function(value,data) {
								var range = "-";
								if (value == 1) {
									range = "全国";
								} else if (value == 2) {
									range = "指定城市";
								} else if (value == 3) {
									range = "指定商家";
								}
								return range;
							}
						
				}],
					//操作列
					handleCols : {
						title : "操作",// 标题
						queryPermission : ["update"],// 不需要选择checkbox处理的权限
						width : 150,// 宽度
						cols : [{
							title : "修改",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "user_terminal/share/update/init.jhtml",
								param : ["sid"],// 参数
								permission : "update"// 列权限
							}
						}] 
			}},permissions)
			};
</script>
</body>

</html>