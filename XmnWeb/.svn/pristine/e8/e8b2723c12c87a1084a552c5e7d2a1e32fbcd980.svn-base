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
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
td {
	border-bottom: none !important;
}
</style>

 <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;;
        }
        li{
            list-style: none;
        }
        .box{
            font-size: 14px;
            color: #333;
            padding: 15px;
       
        }
        .box>ul li{
            margin-top: 10px;
        }
        .box ul li label{
            display: inline-block;
            width: 80px;
        }
        .box ul li input[type="text"]{
            height: 30px;
            border: 1px solid #ddd;
            text-indent: 8px;
            vertical-align: middle;
            width: 30%;
            border-radius:5px;
        }
        .box ul li p select{
        	width: 300px;
            padding: 5px;
            border-radius:5px;
        }
        .box ul li p{
            display: inline-block;
            width: 80%;
        }
        .box ul li p span{
            margin: 0 10px;
        }
        .box ul li .address-select{
            display: inline-block;
            vertical-align: text-top;
            overflow: hidden;
        }
        .box ul li .address-select>div{
            float: left;
            width: 300px;
            height: 200px;
            border: 1px solid #ddd;
        }
        .box .address-select>div.btn-box{
            width: 30px;
            margin: 0 10px;
            border: 0;
            padding: 0;
            padding-top: 60px;
        }
        .box  .address-select>div.btn-box span{
            display: block;
            margin-top: 10px;
            cursor: pointer;
        }
        .box .address-select p{
            height: 30px;
		    line-height: 30px;
		    margin: 0;
		    display: block;
		    width: 100%;
		    text-align: center;
		    border-bottom: 1px solid #ddd;
        }
        .select-left ul li{
            padding: 5px;
            float: left;
            margin-right: 10px;
        }
        .select-left ul li.active{
            background: #00a2ec;
            color: #fff;
        }
        .select-right ul li.active{
            background: #00a2ec;
            color: #fff;
        }
        .box .address-select ul{
            overflow: auto;
            height: 170px;
        }
        .box input[type="radio"]{
            margin-right: 5px;
            vertical-align: middle;
        }
        .tab-box input{
            height: 30px;
            margin-left: 60px;
            margin-top: 10px;
            text-indent: 5px;
            width: 30%;
            border-radius:5px;
            border: 1px solid #ddd;
        }
    </style>
</head>
<body
	style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="main">
		<div>
			<div class="panel panel-primary">
				<div class="panel-heading"><c:if test="${isType eq 'add'}">添加运费模板</c:if><c:if test="${! isType eq 'add'}">编辑运费模板</c:if></div>
				<div class="panel-body">
					<form id="postTemplateForm">
					<input type="hidden" name="deliveryNo" id="deliveryNo">
					<input type="hidden" name="id" value="${postTemplate.id }">
					<input type="hidden" name="type" value="${postTemplate.type }">
					<input type="hidden" name="cityStr" value="${postTemplate.cityStr }">
					<input type="hidden" name="deliveryCity" id="deliveryCity">
					<input type="hidden" name="isType" id="isType" value="${isType}">
						 <div class="box">
        <ul>
           <li>
               <label>模板名称</label>
               <p>
                   <input type="text" placeholder="请输入模板名称" name="templateName" value="${postTemplate.templateName}"/>
                    <label>&nbsp;&nbsp;关联供应商</label>
                   <select name="supplierId" id="supplierId" disabled="disabled">
                   		<option value="">=======请选择======</option>
                       <c:forEach items="${suppliers}" var="supplier">
										<option value="${supplier.supplierId}" <c:if test="${postTemplate.supplierId == supplier.supplierId}">selected</c:if> >${supplier.name}</option>
						</c:forEach>
                   </select>
                   <input type="hidden" name="supplierName"/>
               </p>
           </li>
            <li >
                <label>配送至</label>
                <div class="address-select">
                    <div class="select-left">
                        <p>配送地点</p>
                        <ul>
						
                        </ul>
                    </div>
                    <div class="btn-box">
                        <span class="shift-left-btn">左移</span>
                        <span class="shift-right-btn">右移</span>
                    </div>
                    <div class="select-right">
                        <p>待选地点</p>
                        <ul>
                        <c:forEach items="${areas}" var="area">
                        	<li value="${area.area_id}">${area.title}</li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
            </li>
            <li>
                <label>计价方式</label>
                <input type="radio" name="method" id="piece" value="001" checked class="active"/><label for="piece">按件数</label>
                <input type="radio" name="method" id="weight" value="002"/><label for="weight">按重量</label>
            </li>
        </ul>
        <div class="tab-box">
            <p class="first"><label>首件</label><input type="text" placeholder="1件" name="firstNums"/><input type="text" placeholder="8.00" name="firstItem"/></p>
            <p class="second"><label>续件</label><input type="text" placeholder="1件" name="continueNums"/><input type="text" placeholder="8.00" name="continueItem"/></p>
        </div>
        <div align="center">
			<a type="button" class="btn btn-danger"  href="javascript:add();" ><span class="icon-plus"></span>&nbsp;添加</a>
        </div>
    </div>
						<hr>
						<table class="table" id ="conditions">
							<tr>
							<th>可配送至</th>
							<th>首件（个）</th>
							<th>运费（元）</th>
							<th>续件（个）</th>
							<th>续费（元）</th>
							<th>操作</th>
							</tr>
							<tbody>
							<c:if test="${postTemplate == null or fn:length(postTemplate.conditions) <= 0}">
							<tr id = "zanw">
							<td colspan="6" align="center">暂无数据</td>
							</tr>
							</c:if>
							<c:if test="${fn:length(postTemplate.conditions) > 0}">
							<c:forEach items="${postTemplate.conditions}" var="condition" varStatus="va">
							<tr>
							<td class="city-name" value="${condition.deliveryNo}">${condition.deliveryCity}</td>
							<td>${condition.firstNums}</td>
							<td>${condition.firstItem}</td>
							<td>${condition.continueNums}</td>
							<td>${condition.continueItem}</td>
							<td><a href="javascript:void(0)" class='deleteLine'>删除</a></td>
							<input type="hidden" name="conditions[${va.index }].deliveryCity" value="${condition.deliveryCity}">
							<input type="hidden" name="conditions[${va.index }].firstNums" value="${condition.firstNums}">
							<input type="hidden" name="conditions[${va.index }].firstItem" value="${condition.firstItem}">
							<input type="hidden" name="conditions[${va.index }].continueNums" value="${condition.continueNums}">
							<input type="hidden" name="conditions[${va.index }].continueItem" value="${condition.continueItem}">
							<input type="hidden" name="conditions[${va.index }].deliveryNo" value="${condition.deliveryNo}">
							</tr>
							</c:forEach>
							</c:if>
							</tbody>
						</table>
						<hr>
						<table class="table" style="text-align: center;">
							<div align="center">
								<a type="button" class="btn btn-success"  href="javascript:addTemplate();" ><span class="icon-save"></span>&nbsp;保存</a>
								&nbsp;&nbsp;
								<a type="button" class="btn btn-success"  href="javascript:goBack();" ><span class="icon-reply"></span>&nbsp;取消</a>
							</div>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">{add:'${ btnAu['transportFee/manage/add']}',delete:'${btnAu['transportFee/manage/delete']}',edit:'${ btnAu['transportFee/manage/edit']}'}</script>
	<script type="text/javascript">
		var i = ${fn:length(postTemplate.conditions)};
		contextPath = '${pageContext.request.contextPath}';
	</script>
	
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/js/cloud_design/postTemplate/editPostTemplate.js"/>
	<script src="<%=path%>/ux/js/searchChosen.js"></script>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
</body>
</html>
