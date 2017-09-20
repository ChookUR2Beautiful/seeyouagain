<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet"> 
</head>

<body>
	<form class="form-horizontal" role="form" id="editAdvertisingForm">
		<input type="hidden"  name="id" value="${advertising.id}">
		<input type="hidden"   id = "isType" value="${isType}">
		<input type="hidden" name="type" value="5">
		<table width="100%">
			<tbody>
				<tr>
					<tr>
						<th style="width:70px;"><h5>&nbsp;&nbsp;广告标题：</h5></th>	
						<th colspan="2"><input type="text" class="form-control" name="content"  value="${advertising.content}"></th>
					</tr>
					<tr>
						<th style="width:70px;"><h5>&nbsp;&nbsp;副标题：</h5></th>	
						<th colspan="2"><input type="text" class="form-control" name="subtitle"  value="${advertising.subtitle}"></th>
					</tr>
					<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>	
					<th colspan="2">
						<table>
					    <tr><td>
					    <div id="adbpicUpload"></div><small style="color:red;">最优图片分辨率(640*160)</small>
						<input type="hidden" class="form-control" id="adbpic" name="adbpic"  value="${advertising.adbpic}">
					    </td>
					    <td>
					    <div id="adbpicLowUpload"></div><small style="color:red;">低分辨广告图片(640*160)</small>
						<input type="hidden" class="form-control" id="picLow" name="picLow"  value="${advertising.picLow}">
					    </td>
					    <td>
					    <div id="adbpicMiddleUpload"></div><small style="color:red;">中分辨率广告图片(640*160)</small>
						<input type="hidden" class="form-control" id="picMiddle" name="picMiddle"  value="${advertising.picMiddle}">
					    </td>
					    </tr></table>
					</th>
				</tr>
				<tr>
						<th style="width:70px;"><h5>&nbsp;&nbsp;广告链接：</h5></th>	
						<th colspan="2"><input type="text" class="form-control"  name="adburl"  value="${advertising.adburl}"></th>
				</tr>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;分享图片：</h5></th>	
					<th colspan="2">
						<div id="shareImgUpload" ImgValidate="true"></div><small style="color:red;">最优图片分辨率(640*160)</small>
						<input type="hidden" class="form-control" id="share_img" name="share_img"  value="${advertising.share_img}">
					</th>
				</tr>
				
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
					<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="sort"  value="${advertising.sort}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享标题：</h5></th>	
					<th colspan="2"><input type="text" class="form-control" name="shareTitle"  value="${advertising.shareTitle}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享链接：</h5></th>	
					<th colspan="2"><input type="text" class="form-control"  name="shareUrl"  value="${advertising.shareUrl}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;分享描述：</h5></th>	
					<th colspan="2">
						<textarea  name="share_cont" cols="20" rows="3" class="form-control">${advertising.share_cont}</textarea>
					</th>
				</tr>
<%-- 				<tr>
				 <th style="width:70px;"><h5>&nbsp;&nbsp;状态：</h5></th>	
					<th colspan="2">
						<h5><label class="radio-inline"><input type="radio" name="isshow" value="0" <c:if test="${advertising.isshow == 0||(advertising.isshow!=1&&advertising.isshow!=2)}">checked="checked"</c:if> />待上线</label>
						<label class="radio-inline"><input type="radio" name="isshow" value="1" <c:if test="${advertising.isshow == 1}">checked="checked"</c:if> />已上线</label>
						<label class="radio-inline"><input type="radio" name="isshow" value="2" <c:if test="${advertising.isshow == 2}">checked="checked"</c:if> />已下线</label></h5>
					</th>
				</tr> --%>
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
	 <script src="<%=path%>/js/user_terminal/addOrUpdateBannerAdvertising.js"></script>
</body>
</html>
