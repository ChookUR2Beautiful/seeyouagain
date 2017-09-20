<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty catehomeMustbuy}">
			<input type="hidden" name="id" id="id" value="${catehomeMustbuy.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">类型：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input name="type" value="1" type="radio" ${catehomeMustbuy.type==1?"checked":""} ><span style="font-size: 12px;">精选</span>
									&nbsp;&nbsp;
									<input name="type" value="2" type="radio" ${catehomeMustbuy.type==2?"checked":""} ><span style="font-size: 12px;">潮玩</span>
									&nbsp;&nbsp;
									<input name="type" value="3" type="radio" ${catehomeMustbuy.type==3?"checked":""} ><span style="font-size: 12px;">送礼</span>
									&nbsp;&nbsp;
									<input name="type" value="4" type="radio" ${catehomeMustbuy.type==4?"checked":""} ><span style="font-size: 12px;">海淘</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">商品：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<div class="dropdown">
					<select role="button" data-toggle="dropdown"
						class="btn form-control" data-target="#" name="typeId">
						<option value="" style="display:none;">请选择</option>
						<option id="dLabel" value="" style="display:none;"></option>
					</select>
					<ul class="dropdown-menu multi-level" role="menu"
						aria-labelledby="dropdownMenu">
						<li><a href="javascript:;" id="" onclick="confirmType(this)">请选择</a></li>
						<c:forEach items="${freshTypes}" var="freshType">
							<li class="dropdown-submenu"> <a tabindex="-1"
								href="javascript:;" onclick="confirmType(this)"
								id="${freshType.id}">${freshType.name}</a> <c:if
									test="${freshType.childs != null&&fn:length(freshType.childs)>0}">
									<ul class="dropdown-menu">
										<c:forEach items="${freshType.childs}" var="type">
											<li><a tabindex="-1" href="javascript:;"
												onclick="confirmType(this)" id="${type.id}">${type.name}</a></li>
										</c:forEach>
									</ul>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-7" >
				<select class="form-control" id="product" >
					<option>==请选择商品分类==</option>
				</select>
			</div>
		</div>
			
		<div class="form-group">
			<label class="col-md-4 control-label">排序：<span style="color:red;">*</span></label>
			<div class="col-md-7" >
				<input type="number" class="form-control" name="sort" id="sort" min="0"
					value="${catehomeActivity.sort}">
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
	<script type="text/javascript" src="<%=path%>/js/businessman/cateMustbuyEdit.js"/>
	<script type="text/javascript">
	var brandTypeId = "${productInfo.classa}";
	if (brandTypeId) {
		var freshType = $("#" + brandTypeId);
		if (freshType.attr("id")) {
			$("#dLabel").text(freshType.text()).val(freshType.attr("id")).attr("selected", "selected");
		}
	}
	function confirmType(item) {
		$("#dLabel").text($(item).text()).val($(item).attr("id")).attr("selected", "selected");
		$.ajax({
			type : 'post',
			url : "fresh/manage/getProductInfoByType.jhtml",
			data : {"typeId":$(item).attr("id")},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data) {
				var product =$("#product").html("");
				$.each(data,function(i,item){
					$("<option/>").text(item.pname).appendTo(product);
				})
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
</script>
