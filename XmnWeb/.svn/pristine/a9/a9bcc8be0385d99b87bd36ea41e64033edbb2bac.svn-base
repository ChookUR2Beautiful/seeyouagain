<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

				<form class="borderBottom table table-form" action="coupon/couponissue/youhuimaexport.jhtml"  role="form" name="dcForm" id="dcForm">
				<table style="width:100%;">
<tbody>
										<tr>
											<td style="width:8%;"><h5>活动编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" id="issue"  name="IssueId" style="width:90%;" /></td>
											<td style="width:7%;"><h5>获取状态:</h5></td>
											<td style="width:20%;"><select class="form-control"
												name="getStatus" style="width:90%;float:left">
													<option value="" selected="selected">全部</option>
													<option value="0">未获取</option>
													<option value="1">已获取</option>
													<option value="2">已锁定</option>
											</select></td>
											<td style="width:7%;"><h5>领取时间:</h5></td>
											<td style="width:32%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="getTimeStart" value="${param.getTimeStart}" readonly="readonly">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="getTimeEnd" value="${param.getTimeEnd}" readonly="readonly">
												</div>
											</td>
										</tr>
										<tr>
											<td style="width:8%;"><h5>优惠券编号:</h5></td>
											<td style="width:25%;"><input type="text"
												class="form-control" id="cidid" name="cid" style="width:90%;" /></td>
 
											<td style="width:7%;"><h5>使用状态:</h5></td>
											<td style="width:20%;"><select class="form-control"
												name="userStatus" style="width:90%;float:left">
													<option value="" selected="selected">全部</option>
													<option value="0">未使用</option>
													<option value="2">已使用</option>
													<option value="1">已锁定</option>
											</select></td> 
											<td style="width:7%;"><h5>发行时间:</h5></td>
											<td style="width:32%;">
												<div class="input-group" style="width:90%;float:left;">
													<input type="text" class="form-control form-datetime"
														name="dateIssueStart" value="${param.dateIssueStart}" readonly="readonly">
													<span class="input-group-addon fix-border">--</span> <input
														type="text" class="form-control form-datetime"
														name="dateIssueEnd" value="${param.dateIssueEnd}" readonly="readonly">
												</div>
											</td>
										</tr>
									</tbody>
					  <tfoot>
			      		<tr>
				          	<td colspan="6" class="text-center">
					            	<button class="btn btn-default" type="submit" id="submit"><i class="icon-download-alt" data-loading="稍候..."></i>&nbsp;导 出</button>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="reset" class="btn btn-warning" data-dismiss="modal"><i class="icon-remove"></i>&nbsp;取 消 </button>
							</td>
				        </tr>
			      </tfoot>
				</table>
				<script type="text/javascript">
			$(function(){
				$(".form-datetime").datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				showMeridian : 1,
				minuteStep : 1,
				format : 'yyyy-mm-dd hh:ii:ss'
		        });

				     $("#submit").click(function(){
				        if(!issueIdIsNum()){
				            return false;
				        };
				        if(!cidIsNum()){
				            return false;
				        };
				        var $issueId=$("#issue");
				        var issueIdval=$.trim($issueId.val());
				        var flag=true;
		                if(issueIdval==""){
		                submitDataError($issueId,"必填");
		                flag= false;
		                }
		                return flag;
				     })
				     function issueIdIsNum(){
				       var $issueId=$("#issue");
				        var isnum=isNaN($issueId.val())
				        if(isnum){
				        $("#issue").val("");
				        submitDataError($issueId,"请输入数字！");
				        return false;
				        }
				        return true;
				     }
				     
				      function cidIsNum(){
				       var $cidid=$("#cidid");
				       if($.trim($cidid.val())!=""){
				        var isnn=isNaN($cidid.val())
				        if(isnn){
				          $("#cidid").val("");
				          submitDataError($cidid,"请输入数字！");
				          return false;
				          }else{
				          return true;
				          }
				        }
				        return true;
				     }
				     
				 }) 
				 
				</script>
			</form>
