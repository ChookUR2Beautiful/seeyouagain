<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<form class="form-horizontal" name="editSellerMarketingForm"
		role="form" id="editSellerMarketingForm">
		<input type="hidden" id="isType" value="${isType}"> <input
			type="hidden" id="sellerGrade" name="sellerGrade"
			value="${param.sellerGrade}" /> <input type="hidden" id="isonline"
			name="isonline" value="${param.isonline}" /> <input type="hidden"
			name="id" value="${sellerMarketing.id}">
		<p>
		<div id="MutexHint"
			style="height:100px;position: relative;top:-20px;left:20px;">
			<font><b>特别提示： </br>1、同种活动商家不能重复参加。 </br>2、单个商家新增活动时，A等级商家不能参与 。
					</br>3、消费补贴活动与教育基金活动，只能二选一。 </br>4、若商家参加教育基金活动，则消费补贴活动自动退出。 </br>5、若商家参加消费补贴活动，则教育基金活动自动退出。
					 </br>6、预上线商家不能参与教育基金活动。
			</b></font>
		</div>
		</p>
		<p>
		<center>
			<div id="hint" style="height:30px;position: relative;top:10px;"></div>
		</center>
		<p>
			<input type="hidden" name="sellerid"
				value="${empty sellerMarketing.sellerid ? param.sellerid : sellerMarketing.sellerid}">
		<table width="100%">
			<tbody>
				<tr>
					<th style="width:20%;"><h5>&nbsp;&nbsp;营销活动:</h5></th>

					<c:if test="${isType=='add'}">
						<td><select class="form-control" id="activityType" name="activityType"
							style="width:100%" ></select></td>
					</c:if>
					<c:if test="${isType=='update'}">

						<th colspan="1"><select class="form-control"
							disabled="disabled" id="activityType" name="activityType" style="width:100%"
							initValue="${sellerMarketing.activityType}"></select></th>

					</c:if>

					<%-- <td>
						<select class="form-control" id="aid" name="aid" style="width:100%" initValue="${sellerMarketing.aid}"></select>
					</td> --%>
				</tr>
				<tr>
					<th style="width:20%;"><h5>&nbsp;&nbsp;活动时间:</h5></th>
					<td><input type="text" id="startDate" placeholder="活动开始时间"
						class="form-control" style="width:47%;float:left" readonly>
						<label style="float: left;">&nbsp;--&nbsp;</label> <input
						type="text" id="endDate" placeholder="活动结束时间" class="form-control"
						style="width:47%;float:left" readonly></td>
				</tr>
				<tr>
					<th><h5>&nbsp;&nbsp;是否参加:</h5></th>
					<td><select class="form-control" name="isattend">
							<option value="0"
								${sellerMarketing.isattend==0? "selected" : "" }>参加营销</option>
							<option value="1"
								${sellerMarketing.isattend==1? "selected" : "" }>不参加营销</option>
					</select></td>
				</tr>
				<tr>
					<td height="10px;"></td>
				</tr>
				<tr>
					<th colspan="2" style="text-align: center;">
						<button type="submit" class="btn btn-success" id="ensure">
							<span class="icon-ok"></span> 保 存
						</button> &nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal">
							<span class="icon-remove"></span> 取 消
						</button>
					</th>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var subflag = false;
		$(document).ready(function() {
							validate("editSellerMarketingForm", {
								rules : {
									activityType : {
										required : true
									}
								},
								messages : {
									activityType : {
										required : "不能为空"
									}
								}
							}, formAjax);

							$('.form-datetime').datetimepicker({
								weekStart : 1,
								todayBtn : 1,
								autoclose : 1,
								todayHighlight : 1,
								startView : 2,
								forceParse : 0,
								showMeridian : 1,
								format : 'yyyy-mm-dd hh:ii'
							}).on('changeDate', function(ev) {
								$(this).blur();
							});
                            var paramName;
                            if($("#isType").val()=="add"){
                                paramName="type"
                            }else{
                                paramName="activityType"
                            }
							setTimeout(function() {$("#activityType").chosenObject({//获取活动列表
															hideValue :"type",
															showValue : "aname",
															url : "marketingManagement/activitymanagement/getActivitys.jhtml",
															isChosen : true
														}).on("chosen:hiding_dropdown",function(event) {
															getActivity(event.target.value);
														});
										getActivity($("#activityType").val());
									}, 500);
							$("#activityType").change(function() {//3:教育；4：消费
												var sellerGrade = $("#sellerGrade").val();
												var activityType = $("#activityType").val();
												if ($("#isonline").val() != 1&& $("#isonline").val() != 2) {//判断是否上线、预上线
													$("#hint").html("<font color='red'>未上线商家不能参与活动</font>");
													return false;
												}
												if (sellerGrade==1) {//单个商家新增活动时，A店是不能参加
													$("#hint").html("<font color='red'>单个商家新增活动时，A等级商家不能参与</font>");
													subflag = false;
													return;
												} else {
													$("#hint").html("");
													subflag = true;
												} 
												if ($("#isonline").val() == 2&& activityType == 3) {//预上线商家不能参与教育基金活动
													$("#hint").html("<font color='red'>预上线商家不能参与教育基金活动</font>");
													subflag = false;
													return;
												} else {
													$("#hint").html("");
													subflag = true;
												}
												/* if (activityType == 3 || activityType == 4) {
													if (!(sellerGrade == 2 || sellerGrade == 3 || sellerGrade == 4 || sellerGrade == 5)) {
														$("#hint").html("<font color='red'>A级商家不能参加教育基金或消费补贴活动</font>");
														subflag = false;
														return;
													} else {
														$("#hint").html("");
														subflag = true;
														return;
													}
												} else {
													$("#hint").html("");
													subflag = true;
												} */
											});

							$("#ensure").click(function() {//提交前校验
												var type = $("#activityType").val().length;
												if (type == 0) {
													$("#hint").html("<font color='red'>营销活动不能为空</font>");
													return false;
												}
												if ($("#isonline").val() != 1&& $("#isonline").val() != 2) {
													$("#hint").html("<font color='red'>未上线商家不能参与活动</font>");
													return false;
												}
												if ($("#isType").val() == "add") {
													if (subflag) {
														return true;
													} else {
														return false;
													}
												} else {
													return true;
												}
											});
						});
		function getActivity(activityType) {//根据活动类型获取活动
			if (activityType) {
				$.ajax({
							url : "marketingManagement/activitymanagement/getActivityType.jhtml?type=" + activityType,
							type : "get",
							dataType : 'json',
							success : function(data) {
								$("#startDate").val(data.startDate);
								$("#endDate").val(data.endDate);
							}
						});
			} else {
				$("#startDate").val("");
				$("#endDate").val("");
			}

		}

		function formAjax() {
			var data = jsonFromt($('#editSellerMarketingForm').serializeArray());
			/* if(!(checkData(data.aid, "#aid_chosen", "不能为空") &&
				checkData(dateCompare(data.sdate, $("#startDate").val()) >= 0, "input[name='sdate']", "不小于活动开始时间") &&
				checkData(dateCompare(data.edate, data.sdate) >= 0, "input[name='edate']", "不小于开始时间") &&
				checkData(dateCompare($("#endDate").val(), data.edate) >= 0, "input[name='edate']", "不大于活动结束时间"))){
				return false;
			} */
			var url;
			if ($('#isType').val() == 'add') {
				url = 'businessman/sellerMarketing/add.jhtml' + '?t='
						+ Math.random();
			} else {
				url = 'businessman/sellerMarketing/update.jhtml' + '?t='
						+ Math.random();
			}
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					if (data.success) {
						$('#prompt').hide();
						if (undefined != data.data) {
							$("#hint").html("<font color='red'>" + data.data+ "</font>");
							return;
						}
						$('#triggerModal').modal('hide');
						if ($('#isType').val() == 'add') {
							sellerMarketingList.reset();
						} else {
							sellerMarketingList.reload();
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
	</script>
</body>

</html>
