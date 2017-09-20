<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
<style type="text/css">
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
	
	#u10 {
	    width: 100%;   
	    height: 56px;
	    line-height: 56px;
	    font-size: 18px;
	    text-align:center;
	    background-color: rgba(25, 158, 216, 1);
	}
	
	Style Attribute {
   		visibility: visible;
	}
</style>
</head>

<body style="overflow-x: auto;overflow-y: auto;background:#EEE"
	class="doc-views with-navbar">
	<div class="panel" style="width: 60%">
	<div id="u10" class="text" style="visibility: visible;">
		<p>
			<span>岗位编辑</span>
		</p>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form" id="editRecruitStationForm">
			<input type="hidden" name="recruitStationId" value="${recruitStation.recruitStationId}">
			<div class="form-group" style="margin-top:100px;">
				<label class="col-md-2 control-label">招聘岗位：</label>
				<div class="col-md-3" id="stationselect">
					<input class="form-control" value="${recruitStation.stationName}" name="stationName" onfocus="showPosition();" style="width: 540px"/>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">招聘人数：</label>
				<div class="col-md-3">
					<div class="input-group" style="width: 540px">
						<input type="text" class="form-control" name="nums"  value="${recruitStation.nums}"/>
						<span class="input-group-addon fix-border" style="background-color: white;border: hidden;">人</span>
					</div>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">薪资要求：</label>
				<div class="col-md-3">
					<div class="input-group">
						<select class="form-control" tabindex="2" name ="salary" style="width: 540px">
							<option value = "0" <c:if test="${recruitStation.salary==0}">selected</c:if>>面议</option>
						    <option value = "1" <c:if test="${recruitStation.salary==1}">selected</c:if>>3000以下</option>
						    <option value = "2" <c:if test="${recruitStation.salary==2}">selected</c:if>>3000-5000</option>
						    <option value = "3" <c:if test="${recruitStation.salary==3}">selected</c:if>>5000-8000</option>
						    <option value = "4" <c:if test="${recruitStation.salary==4}">selected</c:if>>8000-10000</option>
						    <option value = "5" <c:if test="${recruitStation.salary==5}">selected</c:if>>10000以上</option>
					    </select>
					</div>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">年龄：</label>
				<div class="col-md-3">
					<div class="input-group" id="ageContent">
						<select class="form-control" tabindex="2" id="aminId" name ="ageMin" style="width: 110px">
							<option value = "18" <c:if test="${recruitStation.ageMin==18}">selected</c:if>>18</option>
						    <option value = "20" <c:if test="${recruitStation.ageMin==20}">selected</c:if>>20</option>
						    <option value = "25" <c:if test="${recruitStation.ageMin==25}">selected</c:if>>25</option>
						    <option value = "30" <c:if test="${recruitStation.ageMin==30}">selected</c:if>>30</option>
						    <option value = "35" <c:if test="${recruitStation.ageMin==35}">selected</c:if>>35</option>
						    <option value = "40" <c:if test="${recruitStation.ageMin==40}">selected</c:if>>40</option>
						    <option value = "45" <c:if test="${recruitStation.ageMin==45}">selected</c:if>>45</option>
						    <option value = "50" <c:if test="${recruitStation.ageMin==50}">selected</c:if>>50</option>
						    <option value = "55" <c:if test="${recruitStation.ageMin==55}">selected</c:if>>55</option>
						    <option value = "60" <c:if test="${recruitStation.ageMin==60}">selected</c:if>>60</option>
					    </select>
						<span class="input-group-addon fix-border" style="background-color: white;border: hidden;">--</span>
						<select class="form-control" tabindex="2" id="amaxId" name="ageMax" style="width: 110px">
							<option value = "18" <c:if test="${recruitStation.ageMax==18}">selected</c:if>>18</option>
						    <option value = "20" <c:if test="${recruitStation.ageMax==20}">selected</c:if>>20</option>
						    <option value = "25" <c:if test="${recruitStation.ageMax==25}">selected</c:if>>25</option>
						    <option value = "30" <c:if test="${recruitStation.ageMax==30}">selected</c:if>>30</option>
						    <option value = "35" <c:if test="${recruitStation.ageMax==35}">selected</c:if>>35</option>
						    <option value = "40" <c:if test="${recruitStation.ageMax==40}">selected</c:if>>40</option>
						    <option value = "45" <c:if test="${recruitStation.ageMax==45}">selected</c:if>>45</option>
						    <option value = "50" <c:if test="${recruitStation.ageMax==50}">selected</c:if>>50</option>
						    <option value = "55" <c:if test="${recruitStation.ageMax==55}">selected</c:if>>55</option>
						    <option value = "60" <c:if test="${recruitStation.ageMax==60}">selected</c:if>>60</option>
						</select>
						<span class="input-group-addon" style="background-color: white;color: red;border: hidden;" id="ageMsg"></span>
					</div>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">工作省市：</label>
				<div class="col-md-3" style="width: 280px;">
					<div class="input-group" id="ld" style="width:100%" 
						<c:choose>
						    <c:when test="${!empty recruitStation.city}">
						    	initValue="${recruitStation.city}"
						    </c:when>  
						    <c:otherwise>
						    	initValue="${recruitStation.province}"
						    </c:otherwise>
						 </c:choose>
					 >
					</div>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">工作经验：</label>
				<div class="col-md-3">
					<div class="input-group">
						<select class="form-control" tabindex="2" name ="experie" style="width: 540px">
							<option value = "0" <c:if test="${recruitStation.experie==0}">selected</c:if>>不限</option>
						    <option value = "1" <c:if test="${recruitStation.experie==1}">selected</c:if>>1年以下</option>
						    <option value = "2" <c:if test="${recruitStation.experie==2}">selected</c:if>>1-3年</option>
						    <option value = "3" <c:if test="${recruitStation.experie==3}">selected</c:if>>3-5年</option>
						    <option value = "4" <c:if test="${recruitStation.experie==4}">selected</c:if>>5-10年</option>
						    <option value = "5" <c:if test="${recruitStation.experie==5}">selected</c:if>>10年以上</option>
					    </select>
					</div>
				</div>
			</div>		
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">学历：</label>
				<div class="col-md-3">
					<div class="input-group" style="width: 540px">
						<select class="form-control" tabindex="2" name ="degrees">
							<option value = "0" <c:if test="${recruitStation.degrees==0}">selected</c:if>>小学</option>
						    <option value = "1" <c:if test="${recruitStation.degrees==1}">selected</c:if>>初中</option>
						    <option value = "2" <c:if test="${recruitStation.degrees==2}">selected</c:if>>高中</option>
						    <option value = "3" <c:if test="${recruitStation.degrees==3}">selected</c:if>>大专</option>
						    <option value = "4" <c:if test="${recruitStation.degrees==4}">selected</c:if>>本科以上</option>
					    </select>
					</div>
				</div>
			</div>
			<div class="form-group" style="margin-top: 28px;">
				<label class="col-md-2 control-label">岗位要求：</label>
				<div class="col-md-10">
					<div class="input-group" id="stationRequireContent">
						<c:forEach items="${recruitStation.stationRequireList}" var="sr" varStatus="vs">
							<div style="display:inline-block;vertical-align: middle;">
			         			<input type="text" name="stationval" class="form-control" readonly="readonly" value="${sr.name}" style="width: 100px;text-align: center;background-color: white;border-radius:4px;"/>
			         			<input type="hidden" name="tagIds[${vs.index}]" value="${sr.id}"/>
		         			</div>
		         		</c:forEach>
		         		<span class="input-group-addon" id="editbtn" style="display: inline-block;vertical-align: middle;background-color: white;border: hidden;text-align: left">
							<a href="javascript:;" style="color: red;font-size: 14" data-toggle="modal" data-target="#stationRequireModal">修改</a>
						</span>
						
					</div>
				</div>
			</div>
			<div class="col-md-12 text-center" style="margin-top: 70px;">
				<button id="ensure" type="submit" class="btn btn-success">
					<i class="icon-save"></i>&nbsp;保存
				</button>
				&nbsp;&nbsp;
				<button onclick="window.history.back();" id="concel" type="button"
					class="btn btn-default">
					<i class="icon-reply"></i>&nbsp;取消
				</button>
			</div>
		 </form>
	</div>
	
	<!-- 岗位选择（模态框） -->
	<div class="modal fade" id="stationModal" data-position="100px">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <div class="modal-title">选择岗位</div>
		         </div>
				<div class="modal-body">
					<div class="btn-group">  
			         	<c:forEach items="${stationTagNameList}" var="stn">
			         		<button onclick="asdStationName(this);" class="btn listbtn" value="${stn.name}" style="margin: 5px;width: 130px;">${stn.name}</button>
			         	</c:forEach>
					</div>
				</div>
				 <div class="modal-footer">
		         	<button type="submit" class="btn btn-success" onclick="confirmBtn();"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
					<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
		         </div>
			</div>
		</div>
	</div>
	
	<!-- 岗位要求选择（模态框） -->
	<div class="modal fade" id="stationRequireModal" data-position="100px">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <div class="modal-title">选择岗位要求</div>
		         </div>
				<div class="modal-body">
					<div class="btn-group">
			         	<c:forEach items="${stationTagRequireList}" var="str">
			         		<button onclick="asdStationRequire(this);" class="btn listbtn" name="tagIds" value="${str.id}" style="margin: 5px;width: 130px;border-radius:4px;">${str.name}</button>
			         	</c:forEach>
					</div>
				</div>
				<div class="modal-footer">
		         	<button type="submit" class="btn btn-success" onclick="confirmRequireBtn();"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
					<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
		        </div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		contextPath = '${pageContext.request.contextPath}';
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/ld2.js"></script>
	<script src="<%=path%>/js/jobmanage/editRecruitStation.js"></script>
</body>
</html>
