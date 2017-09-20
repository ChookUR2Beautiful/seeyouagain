<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>活动管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  <form id="addForm" action="" method="post">
   <input type="hidden" id="aid" name="aid">
  </form>
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
  		<c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">
	      <li <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">class="active"</c:if> >
	        <a href="#tab1" data-toggle="tab">满赠活动(金额)</a>
	      </li>
      	</c:if>
      	<c:if test="${ btnAu['marketingManagement/activityManagement/discount/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && btnAu['marketingManagement/activityManagement/discount/init']}"> class="active" </c:if> >
	        <a href="#tab2" data-toggle="tab">折扣补贴</a>
	      </li>
      </c:if>
      
      <!-- 佣金补贴活动 -->
      <c:if test="${ btnAu['marketingManagement/activityManagement/commission/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && btnAu['marketingManagement/activityManagement/commission/init']}"> class="active" </c:if> >
	        <a href="#tab4" data-toggle="tab">佣金补贴</a>
	      </li>
      </c:if>
      
      <c:if test="${ btnAu['marketingManagement/activityManagement/scratchCard/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && btnAu['marketingManagement/activityManagement/scratchCard/init']}"> class="active" </c:if> >
	       <a href="#tab3" data-toggle="tab">刮刮卡</a>
	      </li>
      </c:if>
      <c:if test="${ btnAu['marketingManagement/activityManagement/youhuiquan/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && btnAu['marketingManagement/activityManagement/scratchCard/init'] && btnAu['marketingManagement/activityManagement/youhuiquan/init']}"> class="active" </c:if> >
	       <a href="#tab5" data-toggle="tab">优惠券活动</a>
	      </li>
      </c:if>
      <c:if test="${ btnAu['marketingManagement/activityManagement/manzengjf/init']}">
	      <li <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && empty btnAu['marketingManagement/activityManagement/scratchCard/init'] && empty btnAu['marketingManagement/activityManagement/youhuiquan/init']&&btnAu['marketingManagement/activityManagement/manzeng/init']}">class="active"</c:if> >
	        <a href="#tab6" data-toggle="tab">满赠活动(积分)</a>
	      </li>
      </c:if>	
    </ul>
    <div class="tab-content">
    
    <!-- 满赠 -->
    <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">
	    <div id="tab1" class="tab-pane <c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}">in active</c:if>">
	  	<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form"  id="manzengForm">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
								<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
								
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
								<td style="width:18%;"><input type="text" class="form-control"  name="aname"   style="width:90%"></td>
								
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
								<td style="width:18%;">
									<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm"  class="form-control form-datetime"style="width:45%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:45%;float:left">
								</td>
								
								<td colspan="2">
									<div class="submit" >
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['marketingManagement/activityManagement/manzeng/add']}">
				<button type="button" class="btn btn-success"  data-toggle="modal" data-width="60%" data-url="marketingManagement/activityManagement/manzeng/add/init.jhtml?type=2">
				<span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>
				<c:if test="${!empty btnAu['marketingManagement/activityManagement/manzeng/activityManagerSeller/init']}">
				<button type="button" class="btn btn-success" onclick="mzActivityManager();"  title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
				</c:if>
				</div>
				<div id="manzeng"></div>
			</div>
		</div>
		</div>
	</c:if>
	
	<!-- 折扣 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/discount/init']}">
	<div id="tab2" class="tab-pane <c:if test="${  empty btnAu['marketingManagement/activityManagement/manzeng/init'] && btnAu['marketingManagement/activityManagement/discount/init']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="discountForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aname"  style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
							<td style="width:18%;">
								<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							
							<td colspan="2">
								<div class="submit">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/discount/add']}"><button type="button" class="btn btn-success" id="addDiscountBto" data-type="ajax"  data-url="marketingManagement/activityManagement/discount/add/init.jhtml"   data-width="800px" data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
			</c:if>
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/discount/activityManagerSeller/init']}">
				<button type="button" class="btn btn-success" onclick="activitySubsidy();"  title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
			</c:if>
			</div>
			<div id="discount"></div>
		</div>
	</div>
	</div>
	</c:if>
	
	
	<!-- 佣金补贴活动 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/commission/init']}">
	<div id="tab4" class="tab-pane <c:if test="${  empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && btnAu['marketingManagement/activityManagement/commission/init']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="commissionForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aname"  style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
							<td style="width:18%;">
								<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							
							<td colspan="2">
								<div class="submit">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/commission/add']}"><button type="button" class="btn btn-success" id="addDiscountBto" data-type="ajax"  data-url="marketingManagement/activityManagement/commission/add/init.jhtml"   data-width="800px" data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
			</c:if>
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/commission/activityManagerSeller/init']}">
				<button type="button" class="btn btn-success" onclick="commissionActivitySubsidy();"  title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
			</c:if>
			</div>
			<div id="commission"></div>			
		</div>
	</div>
	</div>
	</c:if>
	
	
	<%--刮刮乐活动tab --%>
	<c:if test="${ btnAu['marketingManagement/activityManagement/scratchCard/init']}">
	 <div id="tab3" class="tab-pane <c:if test="${  empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && btnAu['marketingManagement/activityManagement/scratchCard/init']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="guagualeForm">
				
				<input type="hidden" name="type" value="6" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aname"  style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
							<td style="width:18%;">
								<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							
							<td colspan="2">
								<div class="submit">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/scratchCard/add']}"><button type="button" class="btn btn-success"  data-width="60%" data-type="ajax"  data-url="marketingManagement/activityManagement/scratchCard/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
			</c:if>
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/scratchCard/activityManagerSeller/init']}">
			<button type="button" class="btn btn-success" id="guaguakaactivityManager" title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
			</c:if>
			</div>
			<div id="guaguale"></div>
		</div>
	</div>
	</div>
	</c:if>
	
	<%--优惠券活动tab --%>
	<c:if test="${ btnAu['marketingManagement/activityManagement/youhuiquan/init']}">
	 <div id="tab5" class="tab-pane <c:if test="${  empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && btnAu['marketingManagement/activityManagement/scratchCard/init'] && btnAu['marketingManagement/activityManagement/youhuiquan/init']}">in active</c:if>">
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form"  id="youhuiquanForm">
				
				<input type="hidden" name="type" value="6" />
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="aname"  style="width:90%"></td>
							
							<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
							<td style="width:18%;">
								<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime"style="width:42%;float:left">
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:42%;float:left">
							</td>
							
							<td colspan="2">
								<div class="submit">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/youhuiquan/add']}"><button type="button" class="btn btn-success"  data-width="60%" data-type="ajax"  data-url="marketingManagement/activityManagement/youhuiquan/add/init.jhtml"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
			</c:if>
			<c:if test="${!empty btnAu['marketingManagement/activityManagement/youhuiquan/activityManagerSeller/init']}">
			<button type="button" class="btn btn-success" id="youhuiquanactivityManager" title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
			</c:if>
			</div>
			<div id="youhuiquan"></div>
		</div>
	</div>
	</div>
	</c:if>
	<!-- 满赠积分页签 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/manzengjf/init']}">
	    <div id="tab6" class="tab-pane <c:if test="${ empty btnAu['marketingManagement/activityManagement/manzeng/init'] && empty btnAu['marketingManagement/activityManagement/discount/init'] && empty btnAu['marketingManagement/activityManagement/commission/init'] && empty btnAu['marketingManagement/activityManagement/scratchCard/init'] && empty btnAu['marketingManagement/activityManagement/youhuiquan/init']&&btnAu['marketingManagement/activityManagement/manzengjf/init']}">in active</c:if>">
	  	<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form"  id="manzengjfForm">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动编号：</h5></td>
								<td style="width:18%;"><input type="text" class="form-control"  name="aid"   style="width:90%"></td>
								
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动名称：</h5></td>
								<td style="width:18%;"><input type="text" class="form-control"  name="aname"   style="width:90%"></td>
								
								<td style="width:6%;"><h5>&nbsp;&nbsp;活动开始时间:</h5></td>							
								<td style="width:18%;">
									<input type="text" name ="sDateBegin" placeholder="yyyy-MM-dd hh:mm"  class="form-control form-datetime"style="width:45%;float:left">
									<label style="float: left;">&nbsp;--&nbsp;</label>
									<input type="text" name ="eDateEnd" placeholder="yyyy-MM-dd hh:mm" class="form-control form-datetime" style="width:45%;float:left">
								</td>
								
								<td colspan="2">
									<div class="submit" >
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body data">
				<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${!empty btnAu['marketingManagement/activityManagement/manzengjf/add']}">
				<button type="button" class="btn btn-success"  data-toggle="modal" data-width="60%" data-url="marketingManagement/activityManagement/manzeng/add/init.jhtml?type=7">
				<span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>
				<!--<c:if test="${!empty btnAu['marketingManagement/activityManagement/manzeng/activityManagerSeller/init']}">
				<button type="button" class="btn btn-success" onclick="mzjfActivityManager();"  title="商家与活动关联"><span class="icon-plus"></span>&nbsp;添加商家</button>
				</c:if>-->
				</div>
				<div id="manzengjf"></div>
			</div>
		</div>
		</div>
	</c:if>
	
	</div>
	<!-- 修改、查看、添加商家、参与商家 --> <!--买赠已中奖金额 、折扣已补贴金额、刮刮卡已中奖金额 -->
	<script type="text/json" id="permissions">
	{mzupdate:'${ btnAu['marketingManagement/activityManagement/manzeng/update']}',
	mzcheck:'${btnAu['marketingManagement/activityManagement/manzeng/check'] }',
	mztjsj:'${btnAu['marketingManagement/activityManagement/manzeng/activityManagerSeller/init']}',
	mzcysj:'${btnAu['marketingManagement/activityManagement/manzeng/initSellerRelateNum/init']}',
	mzzjje:'${btnAu['marketingManagement/activityManagement/manzeng/activityPrize/init']}',
    
    yhqupdate:'${ btnAu['marketingManagement/activityManagement/youhuiquan/update']}',
	yhqcheck:'${btnAu['marketingManagement/activityManagement/youhuiquan/check'] }',
	yhqtjsj:'${btnAu['marketingManagement/activityManagement/youhuiquan/activityManagerSeller/init']}',
	yhqcysj:'${btnAu['marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/init']}',
	yhqzjje:'${btnAu['marketingManagement/activityManagement/youhuiquan/activityPrize/init']}',

	zkupdate:'${ btnAu['marketingManagement/activityManagement/discount/update']}',
	zkcheck:'${btnAu['marketingManagement/activityManagement/discount/discountActivityDetails'] }',
	zktjsj:'${btnAu['marketingManagement/activityManagement/discount/activityManagerSeller/init']}',
	zkcysj:'${btnAu['marketingManagement/activityManagement/discount/discountParticipatingMerchants/init']}',
	zkbtje:'${btnAu['marketingManagement/activityManagement/discount/giveMoney/init']}',
	
	ggkupdate:'${ btnAu['marketingManagement/activityManagement/scratchCard/update']}',
	ggkcheck:'${btnAu['marketingManagement/activityManagement/scratchCard/check'] }',
	ggktjsj:'${btnAu['marketingManagement/activityManagement/scratchCard/activityManagerSeller/init']}',
	ggkcysj:'${btnAu['marketingManagement/activityManagement/scratchCard/initSellerRelateNum/init']}',
	ggkzjje:'${btnAu['marketingManagement/activityManagement/scratchCard/activityPrize/init']}',

	commissionupdate:'${ btnAu['marketingManagement/activityManagement/commission/update']}',
	commissioncheck:'${btnAu['marketingManagement/activityManagement/commission/commissionActionDetail'] }',
	commissiontjsj:'${btnAu['marketingManagement/activityManagement/commission/activityManagerSeller/init']}',
	commissioncysj:'${btnAu['marketingManagement/activityManagement/commission/commissionParticiptingMerchants/init']}',
	commissionkbtje:'${btnAu['marketingManagement/activityManagement/commission/activityPrize/init']}'}

	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/grid.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script type="text/javascript">inserTitle(' > 营销活动管理  > <a href="marketingManagement/activityManagement/init.jhtml" target="right">活动管理</a>','activityManagement',true);</script>
	<%-- <script src="<%=path%>/js/marketingmanagement/activityManagermentList.js"></script> --%>
	<!-- 满赠金额 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/manzeng/init']}"><script src="<%=path%>/js/marketingmanagement/mzActivityManagerment.js"></script></c:if>
	<!-- 满赠积分 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/manzengjf/init']}"><script src="<%=path%>/js/marketingmanagement/mzjfActivityManagerment.js"></script></c:if>
	<!-- 折扣 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/discount/init']}"><script src="<%=path%>/js/marketingmanagement/zkActivityManagerment.js"></script></c:if>
	<!-- 佣金 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/commission/init']}"><script src="<%=path%>/js/marketingmanagement/commissionActivityManagerment.js"></script></c:if>
	<!-- 刮刮卡 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/scratchCard/init']}"><script src="<%=path%>/js/marketingmanagement/ggkActivityManagerment.js"></script></c:if>
	<!-- 优惠券活动 -->
	<c:if test="${ btnAu['marketingManagement/activityManagement/youhuiquan/init']}"><script src="<%=path%>/js/marketingmanagement/yhqActivityManagerment.js"></script></c:if>
  </body>
</html>