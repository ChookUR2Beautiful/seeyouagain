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

<title>添加主播</title>

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
	#filePicker .box{
		width:100%  !important;
	}
	
	#datas .img-list li em{
		position: absolute;
		right: -15px;
		top: 0;
		color: #c13535;
		cursor: pointer;
	}
	
	.img-list{
		display:inline-block;
	}
	
	#datas .img-list{
		font-size:0;
	}
	
	#datas .img-list li{
		position:relative;
		display:inline-block;
		margin-right:20px;
	}
	
	.img-list li img{
		width:100px;
		height:100px;
	}
</style>
</head>
<body >
<div id="main">
		<div class="example">
			<div class="panel panel-primary">
				<div class="panel-heading">基本信息</div>
					<div class="panel-body">
						<form id="editFrom" role="form" class="form-horizontal">
							<!-- 主播会员ID -->
							<input type="hidden" id="uid" name="uid" value="${anchor.uid}">
							<input type="hidden" id="isBinding" name = "isBinding" value="N">
							<!--  0 无寻蜜鸟会员和直播观众信息  1 存在寻蜜鸟会员信息，不存在直播观众信息  2存在会员和直播观众信息 -->
							<input type="hidden" id="role" name = "role" value="0">
							
							<input type="hidden" id="id" name="id" value="${anchor.id}">
							<input type="hidden" id="utype" name="utype" value="${anchor.utype}">
							<input type="hidden" id="changeToAnchor" name="changeToAnchor" value="">
							<div class="form-group">
								<label class="col-md-2 control-label">主播昵称：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.nickname}
								</div>
								<label class="col-md-2 control-label">主播等级：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									<select class="form-control" id="levelId" name="levelId" initValue="${anchor.levelId }" disabled="disabled"></select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">真实姓名：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.name}
								</div>
								<label class="col-md-2 control-label">主播排序：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.sortVal == null? 0:anchor.sortVal}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">主播手机号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.phone }
								</div>
								<label class="col-md-2 control-label">主播来源：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.anchorType }
								</div>
							</div>
							
							<!-- 绑定提示信息 begin -->
							<div class="form-group conflictTip" style="display:none;">
								<label class="col-md-7 control-label" id="conflictMsg"  style="color:#F00; font-size:12px;"></label>
								<div class="col-md-3">
									<a id="reloadSelect"  style="color:#00F; font-size:12px;" data-toggle="modal" href="#sm_confirm_window">点我重新绑定</a>
								</div>
							</div>
							<!-- 绑定提示信息 end -->
							
							<!-- 设为主播提示信息 begin -->
							<div class="form-group viewer_conflictTip" style="display:none;">
								<label class="col-md-7 control-label" id="viewer_conflictMsg"  style="color:#F00; font-size:12px;"></label>
								<div class="col-md-3">
									<a id="viewer_reloadSelect"  style="color:#00F; font-size:12px;" data-toggle="modal" href="#viewer_confirm_window">点我重新设置</a>
								</div>
							</div>
							<!-- 设为主播提示信息 end -->
							
							<div class="form-group">
								<label class="col-md-2 control-label">性别：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.sex==1?"男":"女"}
								</div>
								<label class="col-md-2 control-label">收入上限：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${topIncome}
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">出生日期：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.birthday}
								</div>
								<label class="col-md-2 control-label">身份证号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.idcard}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">微信号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.weixin}
								</div>
								
								<label class="col-md-2 control-label">送礼分成：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.ledgerRatio}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">售出粉丝券分成：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.saleCouponRatio}
								</div>
								<label class="col-md-2 control-label">使用粉丝券分成：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.useCouponRation}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">鸟蛋收入总额：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.birdEggTotal}
								</div>
								<label class="col-md-2 control-label">鸟蛋余额：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.balance}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">送出礼物数：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.giveGiftsNums}
								</div>
								
								<label class="col-md-2 control-label">粉丝数量：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.concernedNums}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">主播编号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.id}
								</div>
								
								<label class="col-md-2 control-label">关注：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.concernNums}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">主播房间号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.anchorRoomNo}
								</div>
								
								<label class="col-md-2 control-label">主播介绍视频地址：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.introduceMv}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">商家可查看：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.sellerLookStatus==1?"是":"否"}
								</div>
								
								<label class="col-md-2 control-label">内部账号：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									${anchor.isInside==1?"是":"否"}
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">主播头像：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									<input type="hidden" class="form-control" id="avatar" name="avatar"  value="${anchor.avatar}">
												<div id="avatarImg"></div>
								</div>
								<label class="col-md-2 control-label">群二维码：<span style="color:red;">*</span></label>
								<div class="col-md-3">
									<input type="hidden" class="form-control" id="wechatPic" name="wechatPic"  value="${anchor.wechatPic}">
												<div id="wechatPicImg"></div>
								</div>
							</div>
							
							<hr>
							<h4>主播相册</h4>
							
							<div class="form-group">
								<div class="col-md-7" id="datas">
									<div style="position:relative;margin-top: 10px;">
										<ul class="img-list">
											<c:forEach items="${anchorImageList }" var="item">
												<li><img src='${item.anchorImageStr}' /></li>
											</c:forEach>
										</ul>
										<input type="hidden" class="pic-url-list" name="picUrls"  value="">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="text-center" style="padding:10px 0 10px 0;">
									<a class="btn btn-warning" href="anchor/manage/init.jhtml?"><span class="icon icon-reply"></span>&nbsp;返回</a>
								</div>
							</div>
						</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 绑定寻蜜鸟会员账号操作提示 begin -->
	<div class="modal fade" id="sm_confirm_window" style="z-index: 9999;" data-position="100px">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		     	 <div class="alert with-icon  alert-warning">
					<i class="icon-warning-sign"></i> 
					<div class="content">
						<p>该手机号已有对应的寻蜜鸟会员账号，操作人员请联系主播，确认该寻蜜鸟会员账号是否归该主播所有，并进行绑定！</p>
					</div>
				</div>
				<div>
					<h4>对应会员账号信息</h4>
					<div class="content">
						<table class="table">
							<tr>
								<td width="12%"><h5>会员昵称:</h5></h5></td>
								<td width="24%"><h5 id="usr_nname"></h5></td>
							</tr>
							<tr>
								<td width="12%"><h5>手机号码:</h5></h5></td>
								<td width="24%"><h5 id="phoneNum"></h5></td>
							</tr>
						</table>
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default unbinding" >不 绑 定</button>&nbsp;&nbsp;
		        <button type="button" class="btn btn-primary binding" >绑    定</button>
		      </div>
			</div>
		</div>
	</div>
	<!-- 绑定寻蜜鸟会员账号操作提示 end -->
	
	<!-- 将观众设为主播操作提示 begin -->
	<div class="modal fade" id="viewer_confirm_window" style="z-index: 9999;" data-position="100px">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		     	 <div class="alert with-icon  alert-warning">
					<i class="icon-warning-sign"></i> 
					<div class="content">
						<p>该手机号已有对应的寻蜜鸟直播观众账号，操作人员请联系主播，确认该寻蜜鸟直播观众账号是否归该主播所有！</p>
					</div>
				</div>
				<div>
					<h4>直播观众账号信息</h4>
					<div class="content">
						<table class="table">
							<tr>
								<td width="12%"><h5>观众昵称:</h5></h5></td>
								<td width="24%"><h5 id="viewer_usr_nname"></h5></td>
							</tr>
							<tr>
								<td width="12%"><h5>手机号码:</h5></h5></td>
								<td width="24%"><h5 id="viewer_phoneNum"></h5></td>
							</tr>
						</table>
					</div>
				</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default viewer_cancel" >取 消</button>&nbsp;&nbsp;
		        <button type="button" class="btn btn-primary viewer_confirm" >设为主播</button>
		      </div>
			</div>
		</div>
	</div>
	<!-- 将观众设为主播操作提示 begin end -->
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>					
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/live_anchor/anchorView.js?v=1.0.4"></script>
</body>
</html>
