<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

				<form class="borderBottom table table-form" action="member/memberList/export.jhtml"  role="form"  id="dcForm">
				<table style="width:100%;">
					<tbody>
						<tr>	
							<td style="width:50px;"><h5>&nbsp;&nbsp;&nbsp;注册来源:</h5></td>
							<td style="width:180px;">
								<select class="form-control" name="regtype">
									<option value="">请选择</option>
									<option value="1">旅游众筹网站</option>
									<option value="2">寻蜜鸟网站</option>
									<option value="3">400客服电话</option>
									<option value="4">旅游众筹安卓客户端</option>
									<option value="5">旅游众筹IOS客户端</option>
									<option value="6">寻蜜鸟安卓客户端</option>
									<option value="7">寻蜜鸟IOS客户端</option>
								</select>
							</td>
							
													
							<td style="width:50px;"><h5>&nbsp;&nbsp;&nbsp;注册月份:</h5></td>
							<td style="width:230px;">
							<input type="text" name ="month" placeholder="yyyy-mm" class="form-control form-datetime">
							</td>
						</tr>
												
						<tr style="height: 20px"></tr>
						<tr>
							
						<td style="width:80px"><h5>&nbsp;&nbsp;&nbsp;注册区域:</h5></td>
						   <td style="width:160px">
							    <div class="input-group" id="dcqy" style="width: 99%;">
								</div>							
							</td> 
                       		<td style="width:80px;"><h5>&nbsp;&nbsp;&nbsp;所属商家:</h5></td>
						   	<td style="width:130px;"><input type="text" class="form-control"  name="genusname" ></td>	
						</tr>
						
					</tbody>
					  <tfoot>
			      		<tr>
				          	<td colspan="4" class="text-center">
					            	<button class="btn btn-default" type="submit" ><i class="icon-download-alt" data-loading="稍候..."></i>&nbsp;导 出</button>
									<button type="reset" class="btn btn-warning" data-dismiss="modal"><i class="icon-remove"></i>&nbsp;取 消 </button>
							</td>
				        </tr>
			      </tfoot>
				</table>
			</form>
		<script type="text/javascript">
		$(function(){
			var ld = $("#dcqy").areaLd({
					isChosen : true,
					showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"},{name:"region",tipTitle:"--区--"}]
			  	 });
			$(".form-datetime").datetimepicker({
				weekStart : 1,
				autoclose : 1,
				todayHighlight : 0,
				forceParse : 0,
				startView : 3,
				minView :3,
				maxView :3,
				format : 'yyyy-mm'
			});
			var valiinfo={
					rules:{
						month:{
							required:true
						}
					},
					messages:{
						month:{
							required:"注册月份未选择"
						}
					}
				}
			validate("dcForm",valiinfo,formSubmit);
		});
		function formSubmit(){
			var pageSize = $("#memberTable").attr("data-total");
			$form = $("#dcForm");
			var $input = $("<input type='hidden' name='pageSize' value='"+pageSize+"'>");
			$form.append($input);
			$form[0].submit();
			$input.remove();
			$('#triggerModal').modal('hide');
		}
		
		</script>
