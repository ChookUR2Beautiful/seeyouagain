<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>添加通告</title>

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
<body>
<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
					<div class="panel-body">
						<form id="editFrom" role="form" class="form-horizontal">
							<c:if test="${!empty gift}">
								<input type="hidden" name="id" value="${gift.id}">
							</c:if>
							<input type="hidden" name="giftEditToken" value="${giftEditToken }">
							<div class="form-group">
								<label class="col-md-4 control-label">类型：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="giftKind" value="1" type="radio" ${gift.giftKind==1?"checked":""} ><span style="font-size: 12px;">普通礼物</span>
									<input name="giftKind" value="2" type="radio" ${gift.giftKind==2?"checked":""} ><span style="font-size: 12px;">商品礼物</span>
									<input name="giftKind" value="3" type="radio" ${gift.giftKind==3?"checked":""} ><span style="font-size: 12px;">套餐礼物</span>
								</div>
							</div>
							<!-- 普通礼物信息begin -->
							<div class="form-group base-info"  style="display:none;">
								<label class="col-md-4 control-label">普通礼物类型：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="giftType" value="1" type="radio" ${gift.giftType==1?"checked":""} ><span style="font-size: 12px;">积分礼物</span>
									<input name="giftType" value="2" type="radio" ${gift.giftType==2?"checked":""} ><span style="font-size: 12px;">鸟豆礼物</span>
									<input name="giftType" value="3" type="radio" ${gift.giftType==3?"checked":""} ><span style="font-size: 12px;">积分卡</span>
								</div>
							</div>
							<div class="form-group base-info"  style="display:none;">
								<label class="col-md-4 control-label">参与免费赠送：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="freeStatus" value="1" type="radio" ${gift.freeStatus==1?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="freeStatus" value="0" type="radio" ${gift.freeStatus==0?"checked":""} ><span style="font-size: 12px;">否</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label" id="giftPriceLabel">礼物价格：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="giftPrice" id="giftPrice"
										value="${gift.giftPrice}">
								</div>
							</div>
							<!-- 普通礼物信息end -->
							
							<!-- 商品礼物信息begin -->
							<div class="form-group product-info" style="display:none;">
								<label class="col-md-4 control-label">关联商品：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<select class="form-control" id="codeid" name="codeid">
									</select>
									<!-- 商品名称 -->
									<input type="hidden" id="pname">
									<!-- 商品售价 -->
									<input type="hidden" id="salePrice">
								</div>
							</div>
							
							<div class="form-group product-info" style="display:none;">
								<label class="col-md-4 control-label">商品规格：<span
									style="color:red;">*</span></label>
								<div class="col-md-7">
									<select class="form-control" id="group">
									</select>
								</div>
								<button type="button" class="btn btn-success" id="addProBtn" >
										 添加
								</button>
							</div>
							
							<div class="form-group product-info" style="display:none;">
								<label class="col-md-4 control-label"></label>
								<div class="col-md-7">
									<table class="table table-bordered table-hover" id="productTable">
									  <thead>
									    <tr>
									      <th>关联商品</th>
									      <th>鸟币</th>
									      <th>操作</th>
									    </tr>
									  </thead>
									  <tbody>
										  <c:if test="${!empty gift.giftDetailList }">
											  <c:forEach items="gift.giftDetailList" var="giftDetail" varStatus="status">
											  	<tr>
											      <td>${giftDetail.pname }</td>
											      <td>${giftDetail.birdCoin }</td>
											      <td><a href="javascript:;" onclick="deletePro(this)">删除</a></td>
											      <input type="hidden" name="giftDetailList[${status.index }].codeid">
											      <input type="hidden" name="giftDetailList[${status.index }].pvIds">
											      <input type="hidden" name="giftDetailList[${status.index }].pvValue">
											    </tr>
											  </c:forEach>
									      </c:if>
									  </tbody>
									</table>
								</div>
							</div>
							
							<div class="form-group product-info"  style="display:none;">
								<label class="col-md-4 control-label">默认运费：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="freight" id="freight"
										value="${gift.freight}">
								</div>
							</div>
							<!-- 商品礼物信息 end -->
							
							<!-- 套餐礼物信息 begin -->
							<div class="form-group combo-info" style="display:none;">
								<label class="col-md-4 control-label">商户分类：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<div class="input-group" id="tradeSelect"
										style="width : 100%" initValue="${gift.genre}">
									</div>
								</div>
							</div>
							<!-- 套餐礼物信息 end  -->
							
							<div class="form-group">
								<label class="col-md-4 control-label">礼物名称：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="giftName" id="giftName"
										value="${gift.giftName}">
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-md-4 control-label">获得经验：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="giftExperience" id="giftExperience"
										value="${gift.giftExperience}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">排序值：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="text" class="form-control" name="sortVal" id="sortVal" placeholder="1-99之间整数，值越小排序越靠前"
										value="${gift.sortVal}">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label">发送广播：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="isRadio" value="1" type="radio" ${gift.isRadio==1?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isRadio" value="0" type="radio" ${gift.isRadio==0?"checked":""} ><span style="font-size: 12px;">否</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">礼物可连击：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input name="isSeries" value="002" type="radio" ${gift.isSeries=="002"?"checked":""} ><span style="font-size: 12px;">是</span>
									<input name="isSeries" value="001" type="radio" ${gift.isSeries=="001"?"checked":""} ><span style="font-size: 12px;">否</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">IM消息优先级：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<select class="form-control" name="msgPriority">
									<option value="normal" ${gift.msgPriority=="normal"?"selected":""}>正常</option>
									<option value="high" ${gift.msgPriority=="high"?"selected":""}>高</option>
								</select>
								</div>
							</div>
							<div class="form-group" id="zhiboCoverDiv">
								<label class="col-md-4 control-label">礼物图片：<span style="color:red;">*</span></label>
								<div class="col-md-7">
									<input type="hidden" class="form-control" name="giftAvatar" id="giftAvatar"
										value="${gift.giftAvatar}">
										<div id="giftAvatarImg"></div>
								</div>
							</div>
							<div class="form-group">
								<div class="text-center" style="padding:10px 0 10px 0;">
									<button type="submit" class="btn btn-success">
										<span class="icon-ok"></span> 保 存
									</button>
									&nbsp;&nbsp;
									&nbsp;&nbsp;
									<a class="btn btn-warning" href="liveGift/manage/init.jhtml?"><span class="icon-remove"></span>&nbsp;取消</a>
								</div>
							</div>
						</form>
					</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/live_anchor/giftEdit.js?v=1.0.11"></script>
</body>
</html>
