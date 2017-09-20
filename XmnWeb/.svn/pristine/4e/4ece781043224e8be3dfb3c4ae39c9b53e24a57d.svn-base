<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</head>

<body>
	<form class="form-horizontal" role="form" id="editSellerMarketingForm">
		<input type="hidden" id="isType" value="${isType}">
		<p><div  id="MutexHint" style="height:100px;position: relative;top:-20px;left:20px;">
		<font><b>特别提示：
</br>1、同种活动商家不能重复参加。
</br>2、A级商家不能参加教育基金或消费补贴活动。
</br>3、消费补贴活动与教育基金活动，只能二选一。
</br>4、若商家参加教育基金活动，则消费补贴活动自动退出。
</br>5、若商家参加消费补贴活动，则教育基金活动自动退出。
</br>6、预上线商家不能参与教育基金活动。</b>

</font>
		</div></p>
<div style="height:20px;"></div>


		<table width="100%">
			<tbody>
				<tr>
					<th style="width:20%;"><h5>&nbsp;&nbsp;营销活动:</h5></th>
					<td>
						<select class="form-control" id="activityType" name="activityType" style="width:100%" ></select>
					</td>
				</tr>
				<tr>
					<th style="width:20%;"><h5>&nbsp;&nbsp;活动时间:</h5></th>
					<td>
						<input type="text" id="startDate" placeholder="活动开始时间" class="form-control" style="width:47%;float:left" readonly>
						<label style="float: left;">&nbsp;--&nbsp;</label>
						<input type="text" id="endDate" placeholder="活动结束时间" class="form-control" style="width:47%;float:left" readonly>
					</td>
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;是否参加:</h5></th>
					<td>
						<select class="form-control" name="isattend">
							<option value="0" ${sellerMarketing.isattend==0? "selected" : "" }>参加营销</option>
							<option value="1" ${sellerMarketing.isattend==1? "selected" : "" }>不参加营销</option>
						</select>
					</td>
				</tr>
				<%-- <tr>
					<th ><h5>&nbsp;&nbsp;开始时间:</h5></th>
					<td><input type="text" class="form-control form-datetime"  name="sdate"  value="<fmt:formatDate value="${sellerMarketing.sdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
				</tr>
				<tr>
					<th ><h5>&nbsp;&nbsp;结束时间:</h5></th>
					<td><input type="text" class="form-control form-datetime"  name="edate"  value="<fmt:formatDate value="${sellerMarketing.edate}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
				</tr> --%>
				<tr>
					<th ><h5>&nbsp;&nbsp;选择商家:</h5></th>
					<td><div style="overflow-y:auto;height:100"><textarea class="form-control"   name="sellerids"></textarea></div></td>
				</tr>
				<tr><td height="10px;"></td></tr>
 				<tr>
 					<th colspan="2" style="text-align: center;"> 
 						<button type="submit" class="btn btn-success" id="ensure"><span class="icon-ok"></span>  保  存  </button>
 						&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>  取  消  </button>
					</th>
 				</tr>
	 			</tbody>
	 		</table>
	 		        <p></p>
	 				<div id="tishi" style="overflow-y:auto;height:80"></div>
	 </form>
	 <script src="<%=path%>/ux/js/searchChosen.js"></script>
	 <script type="text/javascript">
	 $(document).ready(function() {
			validate("editSellerMarketingForm",{
				rules:{
					activityType:{
						required:true
					},
			  sellerids:{
						required:true
					}
				},
				messages:{
					activityType:{
						required: "不能为空"
					},
			  sellerids:{
						required: "不能为空"
					}
				}},formAjax);
			$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd hh:ii'
			}).on('changeDate', function(ev){
				$(this).blur();
			});
			setTimeout(function(){
				$("#activityType").chosenObject({
					hideValue : "type",
					showValue : "aname",
					url : "marketingManagement/activitymanagement/getActivitys.jhtml",
					isChosen:true
				}).on("chosen:hiding_dropdown", function (event){
					getActivity(event.target.value);
				});
				getActivity($("#activityType").val());
				
			 searchChosen = $(":input[name='sellerids']").searchChosen({
					url : "businessman/sellerMarketing/addBatch/init/choseSeller/init.jhtml?t=",
					divId : "choseSeller"
				}); 
			}, 500);	
		}); 
		 function getActivity(activityType){
			 if(activityType){
				 $.ajax({
					url : "marketingManagement/activitymanagement/getActivityType.jhtml?type=" + activityType,
					type : "get",
					dataType : 'json',
					success : function(data) {
						$("#startDate").val(data.startDate);
						$("#endDate").val(data.endDate);
					}
				});
			 }else{
				$("#startDate").val("");
				$("#endDate").val("");
			 } 
		 }
	 
		function formAjax(){
			var data = jsonFromt($('#editSellerMarketingForm').serializeArray());
/* 			if(!(checkData(data.aid, "#aid_chosen", "不能为空") &&
				checkData(data.sellerids, "#choseSeller", "不能为空") &&
				checkData(dateCompare(data.sdate, $("#startDate").val()) >= 0, "input[name='sdate']", "不小于活动开始时间") &&
				checkData(dateCompare(data.edate, data.sdate) >= 0, "input[name='edate']", "不小于开始时间") &&
				checkData(dateCompare($("#endDate").val(), data.edate) >= 0, "input[name='edate']", "不大于活动结束时间"))){
				return false;
			} */
			if(!(checkData(data.activityType, "#activityType_chosen", "不能为空")&&checkData(data.sellerids, "#choseSeller", "不能为空"))){
			    return false;
			}
			var url;
			if($('#isType').val() ==  'add'){
				url = 'businessman/sellerMarketing/addBatch.jhtml' + '?t=' + Math.random();
			}else{
				url = 'businessman/sellerMarketing/updateBatch.jhtml' + '?t=' + Math.random();
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
				    $('#prompt').hide();
				    if(undefined!=data.data){
				    if(data.data!=null||data.data!=""||data.data!=""){
					   $("#tishi").html("<font color=red>"+data.data+"</font>");
					   return;
					}
					}
					 $('#triggerModal').modal('hide');
					if (data.success) {
						pageDiv.reset();
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
