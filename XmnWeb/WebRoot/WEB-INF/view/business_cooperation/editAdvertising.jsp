<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<form class="form-horizontal" role="form" id="editAdvertisingForm">
	<input type="hidden"  name="id" value="${advertising.id}">
	<input type="hidden"   id = "isType" value="${isType}">
	<table width="100%">
		<tbody>
			<tr>
				<th style="width:100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>	
				<td colspan="2">
					<div id="adbpicUpload"></div><small style="color:red;">最优图片尺寸：160*640</small>
					<input type="hidden" class="form-control" id="adbpic" name="adbpic"  value="${advertising.adbpic}">
				</td>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;广告文本：</h5></th>	
				<th colspan="2"><input type="text" class="form-control" name="content"  value="${advertising.content}"></th>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;广告链接：</h5></th>	
				<th colspan="2"><input type="text" class="form-control"  name="adburl"  value="${advertising.adburl}"></th>
			</tr>
			
			
			
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
				<th colspan="2"><input type="text" class="form-control"  name="sort"  value="${advertising.sort}"></th>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;上线状态：</h5></th>	
				<th colspan="2">
					<select class="form-control"  name="isshow">
						<option value="">请选择</option>
						<option value="0" ${advertising.isshow==0?"selected":""}>已上线</option>
						<option value="2" ${advertising.isshow==2?"selected":""}>已下线</option>
					</select>
				</th>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;区域：</h5></th>	
				<td colspan="2">
					<table width="100%" id="areaTable">
						<tr>
							<td><h5>&nbsp;&nbsp;全国：<input type="radio" name="isall" id="allArea" value="1" <c:if test="${advertising.isall==1}">checked="checked"</c:if>></h5></td>
							<td><h5>&nbsp;&nbsp;特定区域：<input type="radio" name="isall" id="specifyArea" value="0"  <c:if test="${(!empty advertising.area || !empty advertising.city) && advertising.isall==0}">checked="checked"</c:if>></h5></td>
							<td></td>
						</tr>
						<c:if test="${(!empty advertising.area || !empty advertising.city) && advertising.isall==0}">
							<tr>
								<td colspan="3">
									<div class="input-group" id="areaInfo" style="width: 80%;float:left;" initValue="${!empty advertising.area ? advertising.area : advertising.city}"></div>
									<h5><font style="color: red;float:left;">(区域可多选)</font></h5>
								</td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;类型：</h5></th>	
				<th colspan="2">
					<select class="form-control"  name="type" readonly>
						<option value="3" selected>合作商客户端</option>
					</select>
				</th>
			</tr>
			<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;备注：</h5></th>	
				<th colspan="2">			
					<textarea  name="remarks" cols="20" rows="3" class="form-control">${advertising.remarks}</textarea>
				</th>
			</tr>
			
			<tr>
				<th colspan="3" style="text-align: center;"> 
					<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
					<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
				</th>
			</tr>
 		</tbody>
 	</table>
</form>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/js/business_cooperation/addOrUpdateAdvertising.js"></script>

