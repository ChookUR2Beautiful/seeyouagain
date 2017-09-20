<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<title>添加连锁店信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/web/css/chosenCity.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}
</style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
				<div class="panel-body" align="center">
					<form id="editPostageTemplaceForm" style="height:100%">
						<input type="hidden" name="sellerSubsidyToken"
							value="${sellerSubsidyToken}"> <input type="hidden"
							id="isType" name="isType" value="${isType}"/> <input 
							type="hidden" id="sex" value="${bxmer.sex}"/>
						<table class="layoutTable" style="width:100%;"cellpadding="10px">
						<tr>
						<td style="width:11%;" align="right">模板名称:</td>
						<td>
						<input type="text" class="form-control" id="title" name="title" value="${postageTemplate.title}" style = "width:16%;"/>
						</td>
						</tr>
						<tr>
						<td style="width:11%;" align="right">运送方式:</td>
						<td>
						默认运费&nbsp;
									<input class="form-control" style="width:5.4%;" name="postageRuleList[0].baseWeight" placeholder="输入整数" value="${postageTemplate.tPostageRule.baseWeight}"/>kg内,&nbsp;
						            <input class="form-control" style="width:5.4%;" name="postageRuleList[0].baseFee" value="${postageTemplate.tPostageRule.baseFee}"/>元;每增加&nbsp;
						            <input class="form-control" style="width:5.4%;" name="postageRuleList[0].extraWeight" placeholder="输入整数" value="${postageTemplate.tPostageRule.extraWeight}"/>kg内,增加&nbsp;
						            <input class="form-control" style="width:5.4%;" name="postageRuleList[0].extraFee" value="${postageTemplate.tPostageRule.extraFee}"/>元;
						            <input class="form-control" type="hidden" name="postageRuleList[0].tid" value="${postageTemplate.tid}"/>
									<input class="form-control" type="hidden" name="postageRuleList[0].id" value="${postageTemplate.tPostageRule.id}"/>
									<input class="form-control" type="hidden" name="postageRuleList[0].area" value="${postageTemplate.tPostageRule.area}"/>
						</td>
						</tr>
						<tr>
							<td style="width:11%;" align="right"></td>
							<td style="width:100%;">
							<div id="citypostageDIV" style="display: none;">
								<table class="postageRuleTable" style="width:80%;" cellpadding="10px" cellspacing="2">
								<tbody style="border: 1px solid;text-align:center;">
									<tr id="ptTitle"></tr>
									<tr class="cityPostageGroup" style="position: relative;">
									</tr>
									
									<c:if test="${postageTemplate.postageRuleSize > 1}">
										<tr class="tempptTitle">
											<td style="width:25%;border: 1px solid;"><h5>运送到</h5></td>
											<td style="width:15%;border: 1px solid;"><h5>首重</h5></td>
											<td style="width:15%;border: 1px solid;"><h5>运费</h5></td>
											<td style="width:15%;border: 1px solid;"><h5>续重</h5></td>
											<td style="width:15%;border: 1px solid;"><h5>续运费</h5></td>
											<td style="width:15%;border: 1px solid;"><h5>操作</h5></td>
										</tr>
									  	<c:forEach var="postlst" items="${postageTemplate.postageRuleList}" varStatus="status" begin="1">
											<tr class="cityPostageGroup" style="position: relative;">
												<input class="form-control" type="hidden" name="postageRuleList[${status.count}].tid" value="${postageTemplate.tid}"/>
												<input class="form-control" type="hidden" name="postageRuleList[${status.count}].id" value="${postlst.id}"/>
												<input type="hidden" class="postlstAreaR" name="postageRuleList[${status.count}].area_r" value="${postlst.area_r}"/>
												<td style="width:200px;border: 1px solid;padding:5px 5px;box-sizing: border-box;">
												<span style="float:left;font-size:13px;">${postlst.area_r}</span>
												<a href="javascript:;" style="font-size:13px;" class="addbtn">编辑</a>
												<input class="form-control postaddress" type="hidden" name="postageRuleList[${status.count}].area" value="${postlst.area}"/>
												</td>
												<td style="width:15%;border: 1px solid;"><input name="postageRuleList[${status.count}].baseWeight" id="" class="form-control" style="width:80%;" placeholder="输入整数" value="${postlst.baseWeight}"/></td>
												<td style="width:15%;border: 1px solid;"><input name="postageRuleList[${status.count}].baseFee" id="" class="form-control" style="width:80%;" value="${postlst.baseFee}"/></td>
												<td style="width:15%;border: 1px solid;"><input name="postageRuleList[${status.count}].extraWeight" id="" class="form-control"style="width:80%;" placeholder="输入整数" value="${postlst.extraWeight}"/></td>
												<td style="width:15%;border: 1px solid;"><input name="postageRuleList[${status.count}].extraFee" id="" class="form-control" style="width:80%;" value="${postlst.extraFee}"/></td>
												<td style="width:15%;border: 1px solid;"><a href="javascript:;" style="font-size:13px;" class="deletecitypostage">删除</a></td>
										    </tr>
									    </c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						</td>
						</tr>
						<tr>
						<td style="width:11%;" align="right"></td>
						<td colspan="4">
							<a href="javascript:;" style="font-size:13px;" class="citypostage">为指定地区城市设置运费</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						</tr>
						<tr>
						<td style="width:11%;" align="right"><input id="freeCondition" type="checkbox" value="" />&nbsp;&nbsp;指定条件地区包邮:</td>
						<td>
						<div id="freeConditionDIV" style="display:none;width: 80%;">
							<table class="postageFreeRuleTable" style="width:76%;" cellpadding="10px" cellspacing="2">
							<tbody style="border: 1px solid;text-align:center;">
								<tr>
									<td style="width:30%;border: 1px solid;"><h5>运送到</h5></td>
									<td style="width:46%;border: 1px solid;"><h5>包邮条件</h5></td>
									<td style="width:24%;border: 1px solid;"><h5>操作</h5></td>
								</tr>
								<c:if test="${empty postageTemplate.postageFreeRuleList}">
									<tr class="postageFreeRuleGroup" style="position:relative;">
									<td style="width:30%;border: 1px solid;padding:5px 5px;box-sizing: border-box;">
									<span style="float:left;font-size:13px;">未添加地区</span>
									<a href="javascript:;" style="font-size:13px;float: right;" class="chanceseladdress">编辑</a>
									<input class="form-control postaddress" type="hidden" name="postageFreeRuleList[0].area"/>
									</td>
									<td style="width:46%;border: 1px solid;">满&nbsp;<input name="postageFreeRuleList[0].amount" id="" class="form-control" style="width:29.4%;" value=""/>元以上,&nbsp;<input name="postageFreeRuleList[0].weight" id="" class="form-control" style="width:29.4%;" placeholder="输入整数" value=""/>kg内包邮</td>
									<td style="width:24%;border: 1px solid;">
									<span ><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;
									<span ><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i></span>
									</td>
								    </tr>
								</c:if>
								<c:if test="${!empty postageTemplate.postageFreeRuleList}">
								<c:forEach var="postfreelst" items="${postageTemplate.postageFreeRuleList}" varStatus="status">
								<input class="form-control" type="hidden" name="postageFreeRuleList[${status.index}].tid" value="${postageTemplate.tid}"/>
								<input class="form-control" type="hidden" name="postageFreeRuleList[${status.index}].id" value="${postfreelst.id}"/>
								<input type="hidden" class="postfreelstAreaF" name="postageFreeRuleList[${status.index}].area_f" value="${postfreelst.area_f}"/>
								<tr class="postageFreeRuleGroup" style="position:relative;">
									<td style="width:30%;border: 1px solid;padding:5px 5px;box-sizing: border-box;">
									<span style="float:left;font-size:13px;">${postfreelst.area_f}</span>
									<a href="javascript:;" style="font-size:13px;float: right;" class="chanceseladdress">编辑</a>
									<input class="form-control postaddress" type="hidden" name="postageFreeRuleList[${status.index}].area" value="${postfreelst.area}"/>
									</td>
									<td style="width:46%;border: 1px solid;">满&nbsp;<input name="postageFreeRuleList[${status.index}].amount" id="" class="form-control" style="width:29.4%;" value="${postfreelst.amount}"/>元以上,&nbsp;<input name="postageFreeRuleList[${status.index}].weight" id="" class="form-control" style="width:29.4%;" placeholder="输入整数" value="${postfreelst.weight}"/>kg内包邮</td>
									<td style="width:24%;border: 1px solid;">
									<span ><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;
									<span ><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i></span>
									</td>
								</tr>
								</c:forEach>
								</c:if>
							</tbody>
						</table>
						</div>
						</td>
						</tr>
						</table>
						<hr>
						<div align="center">
							<button class="btn btn-danger" type="submit">
								<i class="icon-save"></i>&nbsp;保 存
							</button>
							&nbsp;&nbsp;
							<button class="btn btn-warning" type="button" id="cancelId">
								<i class="icon-reply"></i>&nbsp;取消
							</button>
						</div>
					</form>
				</div>
			</div>
			
			<!-- 指定条件地区包邮临时行 -->
			<div class="hidden" id="postageFreeRuleTemp">
				<table class="freeRuleTempTable">
					<tr class="postageFreeRuleGroup " data="datacount" style="position:relative;">
						<td style="width:30%;border: 1px solid;padding:5px 5px;box-sizing: border-box;"><span style="float:left;font-size:13px;">未添加地区</span><a href="javascript:;" style="font-size:13px;float: right;" class="chanceseladdress">编辑</a><input class="form-control postaddress" type="hidden" name="postageFreeRuleList[index].area" value=""/></td>
						<td style="width:46%;border: 1px solid;">满&nbsp;<input name="postageFreeRuleList[index].amount" id="" class="form-control" style="width:29.4%;"/>元以上,&nbsp;<input name="postageFreeRuleList[index].weight" id="" placeholder="输入整数" class="form-control" style="width:29.4%;"/>kg内包邮</td>
						<td style="width:24%;border: 1px solid;">
						<span ><i class="icon icon-plus glyphicon glyphicon-plus"	style="cursor:pointer"></i></span>&nbsp;&nbsp;&nbsp;&nbsp;
						<span ><i class="icon icon-minus glyphicon glyphicon-minus" style="cursor:pointer"></i></span>
						</td>
					</tr>
				</table>
			</div>
			<!-- 指定地区城市设置运费临时行行的表头 -->
			<div class="hidden" id="citypostageTempTitle">
				<table class="citypostageTempTableTitle">
					<tr class="tempptTitle">
						<td style="width:25%;border: 1px solid;"><h5>运送到</h5></td>
						<td style="width:15%;border: 1px solid;"><h5>首重</h5></td>
						<td style="width:15%;border: 1px solid;"><h5>运费</h5></td>
						<td style="width:15%;border: 1px solid;"><h5>续重</h5></td>
						<td style="width:15%;border: 1px solid;"><h5>续运费</h5></td>
						<td style="width:15%;border: 1px solid;"><h5>操作</h5></td>
					</tr>
				</table>
			</div>
			<!-- 指定地区城市设置运费临时行 -->
			<div class="hidden" id="citypostageTemp">
				<table class="citypostageTempTable">
					<tr class="cityPostageGroup" style="position: relative;">
						<td style="width:200px;border: 1px solid;padding:5px 5px;box-sizing: border-box;"><span style="float:left;font-size:13px;">未添加地区</span><a href="javascript:;" style="font-size:13px;" class="addbtn">编辑</a><input class="form-control postaddress" type="hidden" name="postageRuleList[subscript].area" value=""/></td>
						<td style="width:15%;border: 1px solid;"><input name="postageRuleList[subscript].baseWeight" id="" class="form-control" style="width:80%;" placeholder="输入整数" value="1"/></td>
						<td style="width:15%;border: 1px solid;"><input name="postageRuleList[subscript].baseFee" id="" class="form-control" style="width:80%;"/></td>
						<td style="width:15%;border: 1px solid;"><input name="postageRuleList[subscript].extraWeight" id="" class="form-control"style="width:80%;" placeholder="输入整数" value="1"/></td>
						<td style="width:15%;border: 1px solid;"><input name="postageRuleList[subscript].extraFee" id="" class="form-control" style="width:80%;"/></td>
						<td style="width:15%;border: 1px solid;"><a href="javascript:;" style="font-size:13px;" class="deletecitypostage">删除</a></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}'
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/searchChosen3.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/fresh/order/addAndUpdatePostageTemplate.js"></script>
	<script src="<%=path%>/js/fresh/chosenCity.js"></script>
</body>
</html>
