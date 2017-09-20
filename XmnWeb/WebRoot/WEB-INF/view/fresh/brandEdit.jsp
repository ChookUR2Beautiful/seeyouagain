<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty freshBrand}">
			<input type="hidden" name="id" value="${freshBrand.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">品牌名：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="text" class="form-control" name="name" id="name"
					value="${freshBrand.name}">
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">所属分类：<span style="color:red;">*</span></label>
			<div class="col-md-7">
			<div class="dropdown">
					            <select role="button" data-toggle="dropdown" class="btn form-control" data-target="#" name="typeId">
					           		<option  value="" style="display:none;" ></option>
					           		<option id="addType"  value="" style="display:none;" ></option>
					            </select>
					            <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu" >
					            	<c:forEach items="${freshTypes}" var="freshType">
									 <li class="dropdown-submenu form-control">
					                    <a tabindex="-1" href="javascript:;" onclick="confirmAddType(this)" id="${freshType.id}">${freshType.name}</a>
					                    <c:if test="${freshType.childs!=null}">
					                    <ul class="dropdown-menu">
					                    <c:forEach items="${freshType.childs}" var="type">
					                        <li><a tabindex="-1" href="javascript:;" onclick="confirmAddType(this)" id="${type.id}">${type.name}</a></li>
					                       </c:forEach>
					                    </ul>
					                    </c:if>
					                </li>
									</c:forEach>
					            </ul>
					        </div>
				<%-- <select class="form-control"
								name="typeId" style="width:90%">
									<option value="">选择分类</option>
									<c:forEach items="${freshTypes}" var="freshType1" >
										<c:choose>
											<c:when test="${freshType1.id==freshBrand.typeId}">
												<option value="${freshType1.id}" selected="selected">${freshType1.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${freshType1.id}">${freshType1.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select> --%>
			</div>
		</div>
		<div class="form-group" id="zhiboCoverDiv">
			<label class="col-md-4 control-label">品牌LOGO：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input type="hidden" class="form-control" name="logo" id="logo"
					value="${freshBrand.logo}">
					<div id="picUrlImg"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">品牌说明：<span style="color:red;">*</span></label>
			<div class="col-lg-7 col-xs-5">	
		                            <textarea class="form-control" rows="3" name="remark">${freshBrand.remark }</textarea>
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
	<script type="text/javascript">
		var  brandTypeId= "${freshBrand.typeId}";
		if(brandTypeId){
			var freshType= $("#"+brandTypeId);
			if(freshType.attr("id")){
				$("#addType").text(freshType.text()).val(freshType.attr("id")).attr("selected","selected");
			}
		}
		function confirmAddType(item){
			$("#addType").text($(item).text()).val($(item).attr("id")).attr("selected","selected");
		}
		$("#picUrlImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	</script>
	<script type="text/javascript" src="<%=path%>/js/fresh/brandEdit.js">
<!--

//-->
</script>
