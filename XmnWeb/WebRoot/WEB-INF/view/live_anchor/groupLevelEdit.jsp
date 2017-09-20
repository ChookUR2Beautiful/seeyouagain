<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty groupLevel}">
			<input type="hidden" name="id" id="id" value="${groupLevel.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">上一级别：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<select class="form-control" id="lastLevelId"  name="lastLevelId"
												initValue="${groupLevel.lastLevelId}" style="width:98%;"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label">级别名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="levelName" id="levelName"
					value="${groupLevel.levelName}">
			</div>
		</div>
		
		<div class="form-group" >
			<label class="col-md-4 control-label">级别图片：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="levelPic" id="levelPic"
					value="${groupLevel.levelPic}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">月度业绩：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<div class="input-group">
								       <input type="text" class="form-control" name="minPerformance" id="minPerformance" value="${groupLevel.minPerformance}">
								        <span class="input-group-addon fix-border"><i class="icon icon-minus"></i></span>
								       <input type="text" class="form-control" name="maxPerformance" id="maxPerformance" value="${groupLevel.maxPerformance}">
							        </div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">奖励比例：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<input type="text" class="form-control" name="awardScale" id="awardScale"
					value="${groupLevel.awardScale}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">统计周期：<span style="color:red;">*</span></label>
			<div class="col-lg-7 col-xs-5">	
		                            <select class="form-control" rows="3" name="period" id="period">
		                            	 <option value="1" ${groupLevel.period==1?'checked="checked"':''}>一周</option>
		                            	 <option value="2"  ${groupLevel.period==2?'checked="checked"':''}>一个月</option>
		                            	 <option value="3"  ${groupLevel.period==3?'checked="checked"':''}>一季度</option>
		                            	 <option value="4"  ${groupLevel.period==4?'checked="checked"':''}>一年</option>
		                            </select>
		                        </div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="<%=path%>/js/live_anchor/groupLevelEdit.js">

</script>
