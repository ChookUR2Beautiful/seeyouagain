<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>精选产品列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" id="searchHotSaleForm">
				<input type="hidden" name="pid" value="${pid}"/> 
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>产品名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="pname" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>商品名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="goodsName" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>产品编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="codeId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>产品状态:</h5></td>
							<td style="width:24%;">
								<select class="form-control" name="pstatus" style = "width:83%;">
									<option value="">请选择</option>
									<option value="0" ${bill.status==0?"selected":""}>待上线</option>
									<option value="1" ${bill.status==1?"selected":""}>已上线</option>
									<option value="2" ${bill.status==2?"selected":""}>已售罄</option>
									<option value="3" ${bill.status==3?"selected":""}>已下线</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>供货方电话:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="tel" style = "width:90%;" placeholder=""/></td>
						  	<td style="width:5%;"><h5>产品售价:</h5></td>
						 	<td style="width:18%;">
							  <input type="text" class="form-control"  name="minPrice"  style="width:39%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  name="maxPrice"  style="width:39%;float:left"/>
							</td>	
							<td style="width:5%;"><h5>产品分类:</h5></td>
						 	<td style="width:18%">
								<div class="container">
							        <div class="dropdown">
							            <select  role="button" data-toggle="dropdown" class="btn form-control" data-target="#" name="classa" >
							               <option  value="" style="display:none;" >请选择</option>
							            	<option id="dLabel"  value="" style="display:none;" ></option>
							            </select>
							            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
							            	<li><a href="javascript:;" id="" onclick="confirmType(this)">请选择</a></li>
							            	<c:forEach items="${freshTypes}" var="freshType">
											 <li class="dropdown-submenu">`
							                    <a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${freshType.id}">${freshType.name}</a>
							                    <c:if test="${freshType.childs!=null}">
							                    <ul class="dropdown-menu">
							                    <c:forEach items="${freshType.childs}" var="type">
							                        <li><a tabindex="-1" href="javascript:;" onclick="confirmType(this)" id="${type.id}">${type.name}</a></li>
							                       </c:forEach>
							                    </ul>
							                    </c:if>
							                </li>
											</c:forEach>
							            </ul>
							        </div>
    							</div>

							</td>	
						 <%-- 	<td style="width:18%;">
						 	<div class="input-group" id="ptypeld" style="width:90%" 
								 <c:choose>
								    <c:when test="${!empty freshInfo.secondary}">
								        initValue="${freshInfo.secondary}"
								    </c:when>
								    <c:otherwise>  
								    	initValue="${freshInfo.classa}"
								    </c:otherwise>
								 </c:choose>
								>
							</div>
							
							</td> --%>
							<%-- <td style="width:5%;"><h5>销售城市</h5></td>
							<td style="width:24%;">
							<div class="input-group" id="ld" style="width:83%" 
									<c:choose>
									    <c:when test="${!empty freshInfo.city}">
									    	initValue=" ${freshInfo.city}"
									    </c:when>  
									    <c:otherwise>  
									    	initValue=" ${freshInfo.province}"
									    </c:otherwise>
									 </c:choose>
								 >
								</div>
							</td> --%>
						</tr>
						<tr>
							<td style="width:5%;"></td>
							<td style="width:18%;"></td>
						  	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
						 	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;">
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
				<c:if test="${!empty btnAu['fresh/hotsalemanage/cancelhotsale'] && btnAu['fresh/hotsalemanage/cancelhotsale']}">
					<button type="button" class="btn btn-default" id="cancelHotSale"><span class="icon-remove-sign"></span>&nbsp;取消精选
				</button> </c:if>
			</div>
			<div id="hotSaleList"></div>
		</div>
	</div>
	<!-- 修改热卖产品排序模态框 start -->
	<div class="modal fade" id="updateChoiceSortModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 24%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改精选产品排序</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="updateChoiceSortForm">
						<div class="form-group">
							<input type="hidden" id="choicePid"> 
							<label for="courierNumber" class="col-sm-2 control-label">排序：</label>
							<div class="col-sm-10">
								<input id="choiceSortId" name="choiceSort" type="text" style="width:80%;" class="form-control" placeholder="输入非负整数数值">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="updateChoiceSortConfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
	<!-- 修改热卖产品排序模态框 end -->
	<script type="text/json" id="permissions">{init:'${ btnAu['fresh/hotsalemanage/init']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/fresh/hotSaleList.js"></script>
	<script type="text/javascript">
		function confirmType(item){
			$("#dLabel").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
		}
	</script>
  </body>
</html>
