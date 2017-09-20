<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'editAdvertising' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/zui/lib/chosen/chosen.css"
	rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css"
	rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
</head>

<body>
	<form class="form-horizontal" role="form" id="editAdvertisingForm">
		<input type="hidden" name="id" value="${advertising.id}"> <input
			type="hidden" id="isType" value="${isType}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:100px;"><h5>&nbsp;&nbsp;广告图片：</h5></th>
					<td colspan="2">
						<div id="adbpicUpload"></div> <small style="color:red;">最优图片尺寸：160*640</small>
						<input type="hidden" class="form-control" id="adbpic"
						name="adbpic" value="${advertising.adbpic}">
					</td>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告文本：</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						name="content" value="${advertising.content}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;广告链接：</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						name="adburl" value="${advertising.adburl}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;排序：</h5></th>
					<th colspan="2"><input type="text" class="form-control"
						name="sort" value="${advertising.sort}"></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;上线状态：</h5></th>
					<th colspan="2"><select class="form-control" name="isshow">
							<option value="">请选择</option>
							<option value="0" ${advertising.isshow==0?"selected":""}>已上线</option>
							<option value="2" ${advertising.isshow==2?"selected":""}>已下线</option>
					</select></th>
				</tr>
				<tr>
					<th><h5>&nbsp;&nbsp;区域：</h5></th>
					<td colspan="3">
						<table width="100%" id="areaTable">
							<tr>
								<td><h5>
										&nbsp;&nbsp;全国：<input type="radio" name="isall" id="allArea"
											value="1"
											<c:if test="${advertising.isall==1}">checked="checked"</c:if>>
									</h5></td>
								<td><h5>
										&nbsp;&nbsp;特定区域：<input type="radio" name="isall"
											id="specifyArea" value="0"
											<c:if test="${advertising.isall==0}">checked="checked"</c:if>>
									</h5></td>
								<td></td>
							</tr>
							<c:if test="${advertising.isall == 0}">
								<tr>
									<td colspan="3">
										<div class="input-group" id="areaInfo"
											style="width: 80%;float:left;"
											initValue="${advertising.areaInitValue}"></div>
										<div style="width: 20%;float:left;">
											<font style="color: red;float:left;">(区域可多选)</font>
										</div>
									</td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;类型：</h5></th>
					<th colspan="2"><select class="form-control" name="type"
						readonly>
							<option value="2" selected>商户客户端</option>
					</select></th>
				</tr>
				<tr>
					<th style="width:70px;"><h5>&nbsp;&nbsp;备注：</h5></th>
					<th colspan="2"><textarea name="remarks" cols="20" rows="3"
							class="form-control">${advertising.remarks}</textarea></th>
				</tr>

				<tr>
					<th colspan="3" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure">
							<span class="icon-ok"></span> 保 存
						</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
					</th>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
<script src="<%=path%>/ux/js/ld2.js"></script>
<script type="text/javascript">
	$.validator.addMethod("isURL", function(value, element) {
		var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
		/*  + '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@
		 + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
		 + '|' // 允许IP和DOMAIN（域名）
		 + '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
		 + '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
		 + '[a-z]{2,6})' // first level domain- .com or .museum
		 + '(:[0-9]{1,4})?' // 端口- :80
		 + '((/?)|' // a slash isn't required if there is no file name 
		 + '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'*/;
		var re = new RegExp(strRegex);
		return (re.test(value));
	}, "请输入正确的链接");

	$(function() {
		$("#adbpicUpload").uploadImg({
			urlId : "adbpic",
			showImg : $("#adbpic").val()
		});
		validate("editAdvertisingForm", {
			rules : {
				content : {
					required : true
				},
				adburl : {
					required : true,
					maxlength : 100,
					isURL : true
				},
				sort : {
					required : true,
					digits : true,
					range : [ 0, 2147483647 ]
				},
				isshow : {
					required : true
				},
				type : {
					required : true
				},
				remarks : {
					required : true,
					maxlength : 100
				},
				isall : {
					required : true
				}
			},
			messages : {
				content : {
					required : "广告文本不能为空"
				},
				adburl : {
					required : "广告链接不能为空!",
					maxlength : "链接最大长度为100字符!",
					isURL : "请输入正确的URL格式"
				},
				sort : {
					required : "排序值不能为空！",
					digits : "排序值只能为整数!",
					range : "取值为1-2147483647!"
				},
				isshow : {
					required : "请选择是否显示!"
				},
				type : {
					required : "请选择类型!"
				},
				remarks : {
					required : "请输入备注!",
					maxlength : "备注的最大长度为100字符!"
				},
				isall : {
					required:"请选择一种区域类型!"
				}
			}
		}, formAjax);

		var initAreaInfo = areaInfoHandler(function(areaInfo) {
			initAreaLd(areaInfo);
			return areaInfo.attr("initValue");
		});

		$("#specifyArea").on("click", {
			"initAreaInfo" : initAreaInfo
		}, function(event) {
			if ($(this).is(":checked")) {
				removeAreaInfo();
				createAreaInfo(event.data.initAreaInfo);
				initAreaLd($("#areaInfo"));
			}
		});
		$("#allArea").on("click", function() {
			removeAreaInfo();
		});
	});

	function formAjax() {
		var success = areaInfoHandler(function(areaInfo){
			var selectAray = [ "province", "city"];
			return checkSelect('#editAdvertisingForm', "#areaInfo", selectAray, true);
		});
		if(success==null || success){
			var data = jsonFromt($('#editAdvertisingForm').serializeArray());
			if (data) {
				var areaInfo = $("#areaInfo");
				if(areaInfo.length>0){
					var areas = $("#areaInfo").find("select[name='area']").val()!=null?$("#areaInfo").find("select[name='area']").val().toString():null;
					data.area = areas;
				}
			}
			$.ajax({
						type : 'post',
						url : $("#isType").val() == 'add' ? 'businessman/advertising/add.jhtml'
								+ '?t=' + Math.random()
								: 'businessman/advertising/update.jhtml' + '?t='
										+ Math.random(),
						data : data,
						dataType : 'json',
						beforeSend : function(XMLHttpRequest) {
							$('#prompt').show();
						},
						success : function(data) {
							$('#prompt').hide();
							$('#triggerModal').modal('hide');
							if (data.success) {
								if ($('#isType').val() == 'add') {
									sellerAdvertisingList.reset();
								} else {
									sellerAdvertisingList.reload();
								}
							}
							showSmReslutWindow(data.success, data.msg);
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$('#prompt').hide();
							$('#triggerModal').modal('hide');
						}
					});
		}
	}

	/**
	 * 初始化联动信息
	 * 
	 * @param {}
	 *            areaInfo
	 */
	function initAreaLd(areaInfo) {
		$(areaInfo).areaLd({
			isChosen : true,
			isMultiple : true,// 区域是否支持多选（在isChosen为true时），
			separator : ",",
			showConfig : [ {
				name : "province",
				tipTitle : "--省--" ,
				width:"20%" 
			}, {
				name : "city",
				tipTitle : "--市--" ,
				width:"20%" 
			}, {
				name : "area",
				tipTitle : "" ,
				width:"55%" 
			} ]
		});
	}

	/**
	 * 区域处理
	 * 
	 * @param {}
	 *            fn
	 */
	function areaInfoHandler(fn) {
		var areaInfo = $("#areaInfo");
		if (areaInfo.length > 0) {
			return fn.call(this, areaInfo);
		}
		return null;
	}

	function removeAreaInfo() {
		var tr = $("#areaTable tr");
		if (tr.length == 2) {
			$(tr[1]).remove();
		}
	}

	function createAreaInfo(initAreaInfo) {
		var div = $("<div>").addClass("input-group").attr("id", "areaInfo")
				.css({
					"width" : "80%",
					"float" : "left"
				});
		if (initAreaInfo) {
			div.attr("initValue", initAreaInfo);
		}
		var h5 = $("<h5>");
		var font = $("<font>").css({
			"color" : "red",
			"float" : "left"
		}).text("(区域可多选)").appendTo(h5);
		var td = $("<td>").attr("colspan", 3).append(div, h5);
		var tr = $("<tr>").append(td);
		$("#areaTable tr:last").after(tr);
	}
</script>
</html>
