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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
</head>

<body>
	<form class="form-horizontal" role="form" id="editAdvertisingForm">
		<input type="hidden"  name="id" value="${advertising.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>	
					<th>
						<div style="width:180px;">
							<input type="hidden" class="form-control" id="adbpic" name="adbpic"  value="${advertising.adbpic}">
							<div id="adbpicUpload"></div>
							<div style="width:180px;float:left;"><small style="color:red;">高分辨率图片(640*160)</small></div>
						</div>
					</th>
					<th>
						<div style="width:180px;">
							<input type="hidden" class="form-control" id="pic_middle" name="picMiddle"  value="${advertising.picMiddle}">
							<div id="picMiddleUpload"></div>
							<div style="width:180px;float:left;"><small style="color:red;">中分辨率图片(640*160)</small></div>
					</th>
					<th>
						<div style="width:180px;">
							<input type="hidden" class="form-control" id="pic_low" name="picLow"  value="${advertising.picLow}">
							<div id="picLowUpload"></div>
							<div style="width:180px;float:left;"><small style="color:red;">低分辨率图片(640*160)</small></div>
						</div>
					</th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;分享图片：</h5></th>	
					<th colspan="3">
						<div style="width:180px;">
							<input type="hidden" class="form-control" id="share_img" name="share_img"  value="${advertising.share_img}">
							<div id="shareImgUpload"></div>
							<div style="width:180px;float:left;"><small style="color:red;">最优图片分辨率(640*160)</small></div>
						</div>
						
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告文本：</h5></th>	
					<th colspan="3"><input type="text" class="form-control" name="content"  value="${advertising.content}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告链接：</h5></th>	
					<th colspan="3"><input type="text" class="form-control"  name="adburl"  value="${advertising.adburl}"></th>
				</tr>
				
				<tr>
				<th style="width:70px;"><h5>&nbsp;&nbsp;区域：</h5></th>	
				<td colspan="3">
					<table width="100%" id="areaTable">
						<tr>
							<td><label><h5>&nbsp;&nbsp;全国：<input type="radio" name="isall" id="allArea" value="1" <c:if test="${advertising.isall==1}">checked="checked"</c:if>></h5></label></td>
							<td><label><h5>&nbsp;&nbsp;特定区域：<input type="radio" name="isall" id="specifyArea" value="0"  <c:if test="${(!empty advertising.area || !empty advertising.city) && advertising.isall==0}">checked="checked"</c:if>></h5></label></td>
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
					<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
					<th colspan="3"><input type="text" class="form-control"  name="sort"  value="${advertising.sort}"></th>
				</tr>
				<%-- <tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;是否显示：</h5></th>	
					<th colspan="3">
						<select class="form-control"  name="isshow">
							<option value="">请选择</option>
							<option value="0" ${advertising.isshow==0?"selected":""}>显示</option>
							<option value="1" ${advertising.isshow==1?"selected":""}>不显示</option>
						</select>
					</th>
				</tr> --%>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;类型：</h5></th>	
					<th colspan="3">
						<select class="form-control"  name="type">
							<option value="">请选择</option>
							<option value="1" ${advertising.type==1?"selected":""}>寻蜜鸟客户端美食</option>
							<%-- <option value="2" ${advertising.type==2?"selected":""}>商户客户端</option>
							<option value="3" ${advertising.type==3?"selected":""}>合作商客户端</option> --%>
							<option value="4" ${advertising.type==4?"selected":""}>寻蜜客圈子广告</option>
							<option value="6" ${advertising.type==6?"selected":""}>微信商城生鲜</option>
							<option value="7" ${advertising.type==7?"selected":""}>微信商城附近美食</option>
							<option value="8" ${advertising.type==8?"selected":""}>寻蜜鸟客户端生鲜</option>
							<option value="9" ${advertising.type==9?"selected":""}>寻蜜客主页</option>
						</select>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;开始时间：</h5></th>	
					<th colspan="3">
						<input type="text" class="form-control form-datetime" readOnly="readonly" name="startTime" value="<fmt:formatDate value="${advertising.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;结束时间：</h5></th>	
					<th colspan="3">
						<input type="text" class="form-control form-datetime" readOnly="readonly" name="endTime" value="<fmt:formatDate value="${advertising.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享标题：</h5></th>	
					<th colspan="3">
						<input type="text"  name="shareTitle" placeholder="30字以内" class="form-control" maxlength="30" value="${advertising.shareTitle}" />
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享描述：</h5></th>	
					<th colspan="3">
						<input type="text"  name="share_cont" placeholder="40字以内" class="form-control" maxlength="40" value="${advertising.share_cont}" />
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享链接：</h5></th>	
					<th colspan="3">
						<input type="text"  name="shareUrl" class="form-control" value="${advertising.shareUrl}" />
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;上线状态：</h5></th>	
					<th colspan="3">
						<label style="font-size:13px;"><input type="radio" name="isshow" value="1" <c:if test="${empty advertising.isshow or advertising.isshow == 1}">checked</c:if>/>待上线</label>
						<label style="font-size:13px;"><input type="radio" name="isshow" value="0" <c:if test="${advertising.isshow == 0}">checked</c:if>/>已上线</label>
						<label style="font-size:13px;"><input type="radio" name="isshow" value="2" <c:if test="${advertising.isshow == 2}">checked</c:if>/>已下线</label>
					</th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;备注：</h5></th>	
					<th colspan="3">
			<%-- 		<input type="text" class="form-control"  name="remarks"  value="${advertising.remarks}"> --%>					
					<textarea  name="remarks" cols="20" rows="3" class="form-control">${advertising.remarks}</textarea>
					</th>
				</tr>
				
 				<tr>
	 					<th colspan="4" style="text-align: center;"> 
	 						<button type="submit" class="btn btn-success"  id="ensure"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
							<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
	 					</th>
	 				</tr>
	 			</tbody>
	 		</table>
	 </form>
	 <script src="<%=path%>/resources/webuploader/webuploader.js"></script> 
	 <script
		src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/resources/upload/upload.js"></script>
	 <script src="<%=path%>/js/user_terminal/addOrUpdateAdvertising.js"></script>
</body>
</html>
