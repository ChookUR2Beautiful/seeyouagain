<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>商店兑换管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">

</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" id="uidViewJunior" name="uidViewJunior" value="${uidViewJunior}">
				<div class="form-group">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:7%;"><h5>&nbsp;&nbsp;会员昵称：</h5></td>
								<td style="width:18%;">
									<input type="text" class="form-control" name="nickname" value="" style = "width:80%;"/>
								</td>
								<td style="width:7%;"><h5>&nbsp;&nbsp;会员手机号：</h5></td>
								<td style="width:18%;">
									<input type="text" class="form-control" name="phone" value="" style = "width:60%;"/>
								</td>
								<td style="width:7%;"><h5>类型：</h5></td>
								<td style="width:18%;">
									<select class="form-control" tabindex="2" name ="channel" style = "width:80%;">
									    <option value = "">--全部--</option>
						                <option value = "34" >兑换蜜罐获取鸟币</option>
						                <option value = "35" >兑换鸟币购买花朵</option>
						             </select>
							    </td>
								
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query'  style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset'  style="width:43%;flaot:left;margin:0 2%;"/>
									</div>
								</td>
							</tr>

						</tbody>
					</table>
					
				</div>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<%-- <c:if test="${btnAu['liveMember/manage/export']}">
					<button type="button" id="purchaseManageBto" class="btn btn-success" data-position="100px" data-width="auto"
						onclick="javascript:purchaseManageView();"><span class="icon-folder-open-alt"></span>&nbsp;购买管理
					</button>
				</c:if> --%>
				<c:if test="${btnAu['liveMember/manage/export']}">
						<button type="button" id="exchangeManageBto" class="btn btn-success" data-position="100px" data-width="auto" onclick="javascript:exchangeManageView();">
						<span class="icon-folder-open-alt"></span>&nbsp;兑换管理</button>
				</c:if>		
				<c:if test="${btnAu['liveMember/manage/export']}">
						<button type="button" id="exportAnchor" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>		
			</div>
			<div id="anchorList" class="good-table-wrap"></div>
		</div>
	</div>
	
	<!-- modal 窗口部分开始 -->
	<div class="modal fade" id="exchangeManageModal">
		<div class="modal-dialog" style="width: 820px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">兑换管理</h4>
				</div>
				<div class="modal-body example" >
					<form class="form-horizontal" id="exchangeForm">
					    <input type="hidden" name="id">
						<!--  开始  -->
                        <div class="form-group" id="dates">
							<label class="col-md-1 control-label"></label>
							<div class="col-md-10">
								<div class="input-group">
								    <span class="input-group-addon">花朵数量:</span>
									<input type="number" name="flowers" placeholder="花朵数量" id="flowers" class="form-control form-date" /> 
									<span class="input-group-addon">生命周期</span>
									<input type="number" name="lifeCycle" placeholder="生命周期" id="lifeCycle" class="form-control form-date" /> 
								    <span class="input-group-addon">购买价格</span>
								    <input type="text" name="coin" placeholder="购买价格" id="coin" class="form-control form-date" /> 
									<span class="input-group-addon "><i class="icon icon-plus" style="cursor: pointer" >添加</i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
                            <label class="col-md-1 control-label"></label>
							<div class="col-md-10">
							    <div class="input-group">
								    <span class="input-group-addon">套餐描述:</span>
								    <textarea id="content" name="content" rows="3" placeholder="最多输入32个汉字"  maxlength="64" style="width:100%;"></textarea>
								</div>
							</div>
						</div>
						<div class="form-group" id="tableDates">
						    <label class="col-md-1 control-label"></label>
							<div class="col-md-10">
								<table class="table table-bordered" data-page="${page}" data-total="${total}" class="propertyTable">
									<thead style="background-color: beige;">
										<tr class="text-center">
											<th class="text-center"><div class="header">花朵数量（朵）</div></th>
											<th class="text-center"><div class="header">生命周期（月）</div></th>
											<th class="text-center"><div class="header">购买价格（币）</div></th>
											<th class="text-center"><div class="header">描述</div></th>
											<th class="text-center"><div class="header">操作</div></th>
										</tr>
									</thead>
									<tbody id="exchangeTB">

									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">1滴花蜜等于鸟币： <span
								style="color: red;">*</span></label>
							<div class="col-md-8">
								<div class="input-group">
									<input type="text" name="rate" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">出售花蜜： <span
								style="color: red;">*</span></label>
							<div class="col-md-8">
								<div class="input-group">
								    <span class="input-group-addon">满</span>
									<input type="text" name="sellPotLimit" class="form-control" /><span
										class="input-group-addon">滴, 即可出售</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">一罐花蜜： <span
								style="color: red;">*</span></label>
							<div class="col-md-8">
								<div class="input-group">
								    <span class="input-group-addon">满</span>
									<input type="text" name="potHoney" class="form-control" /><span
										class="input-group-addon">滴, 存满一罐</span>
								</div>
							</div>
						</div>
									
						<!-- <div class="form-group">
							<div class="col-sm-offset-3 col-sm-8">
								<div class="alert alert-warning">
									<strong>提示：</strong> 1滴花蜜=1鸟币&nbsp;（例如：100滴等于1罐）
								</div>
							</div>
						</div>	 -->
						<!-- <hr/> -->

						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="editExchangeManageSubmit">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<div class="modal fade" id="purchaseManageModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">购买管理:</h4>
				</div>
				<div class="modal-body example">
					<form class="form-horizontal" id="purchaseManageForm">	
						<div class="form-group">
						    <input name="isSpendEnergy" type="checkbox" /><label class="col-md-3 control-label" >花苗售价：<span
								style="color: red;">*</span></label>	
							<div class="col-md-6">
								<div class="input-group">
								     <span class="input-group-addon">阳光兑换</span>
									 <input type="text" name="spendEnergyNumber"  class="form-control" readonly="true" />
								</div>
							</div>
						</div>
					    <div class="form-group">
						    <input name="isSpendEnergy" type="checkbox" /><label class="col-md-3 control-label" >肥料售价：<span
								style="color: red;">*</span></label>	
							<div class="col-md-6">
								<div class="input-group">
								     <span class="input-group-addon">阳光兑换</span>
									 <input type="text" name="spendEnergyNumber"  class="form-control" readonly="true" />
								</div>
							</div>
						</div>
										
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="editPurchaseManageSubmit">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- modal 窗口部分结束 -->
	
	<script type="text/json" id="permissions">{
			add:'${ btnAu['liveMember/manage/add']}'
		}
	</script>
	
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/resources/web/js/jquery.json-2.4.js"></script>
	
	<script src="<%=path%>/js/golden_manor/shopExchangeManage.js?v=1.0.5"></script>
</body>
</html>
