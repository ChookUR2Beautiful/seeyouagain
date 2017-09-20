<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="${action }" id="categoryForm" class="form-horizontal" method="post">
<c:if test="${!empty category }">
	<input type="hidden"  value="${category.tid}"  name="tid" >
</c:if>
	<table width="100%" class="borderBottom table table-form " >
		<tbody>
			<c:if test="${empty category }">
				<tr>
					<th class="w-100px">上级分类:</th>
					<td colspan="2">
							 <select class="form-control"  name="pid">
									<option value="">请选择</option>
									<optgroup label="父分类">
										<option value="0">父分类</option>
									</optgroup>
									<optgroup label="子分类">
										<c:forEach var="l" items="${list}">
										<option value="${l.tid}"><li class="group-result" >${l.tradename}</li></option>
									</c:forEach>
									</optgroup>
									
								</select>
						</td>
				</tr>
			</c:if>
			<tr>
				<th>分类名称:</th>
				<td colspan="2">
				 <input type="text" class="form-control" placeholder="分类名称"  value="${category.tradename }" name="tradename">	
				</td>
			</tr>
			<tr>
				<th>分类排序:</th>
				<td colspan="2">
				 <input type="text" class="form-control" placeholder="分类排序"  value="${category.orderNum }" name="orderNum">	
				</td>
			</tr>
			
			<tr>
				<th class="w-100px">是否热点:</th>
				<c:if test="${!empty category}">
					<td><input type="radio"  value="0" <c:if test="${category.hot==0 }">checked="checked"</c:if> name="hot">否</td>
					<td><input type="radio"  value="1" <c:if test="${category.hot==1 }">checked="checked"</c:if> name="hot">是</td>
				</c:if>	
				<c:if test="${empty category}">
					<td><input type="radio"  value="0" checked="checked" name="hot">否</td>
					<td><input type="radio"  value="1"  name="hot">是</td>
				</c:if>	
			</tr>
			<tr>
				<th class="w-100px">跳转类型:</th>
				<td colspan="2">
						 <select class="form-control" id="type" name="type">
								<option value="1" <c:if test="${category.type==1 }">selected="selected"</c:if> >wap</option>
								<option value="2" <c:if test="${category.type==2 }">selected="selected"</c:if> >app</option>
							</select>
				</td>
			</tr>
				<tr>
				<th class="w-100px">跳转url:</th>
				<td colspan="2" id="urlTd">
						 
				</td>
			</tr>
		
			<tr>
				<th class="w-100px">首页显示(大图):</th>
				<td colspan="2">
					<div id="bigImg"></div>
					<input type="hidden"  value="${category.showBigImg }" id="showBigImg"  name="showBigImg"  >
					</td>
			</tr>
			<tr>
				<th class="w-100px">首页显示(小图):</th>
				<td colspan="2">
					<div id="smallImg"></div>
					<input type="hidden"  value="${category.showSmallImg }" id="showSmallImg"  name="showSmallImg"  >
					</td>
			</tr>
		</tbody>
		<tfoot>
			
			<tr >
				<th style="text-align: center;" colspan="3"> 
 						<button  class="btn btn-success" type="submit"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
						<button data-dismiss="modal" class="btn btn-default" type="reset"><span class="icon-remove"></span>  取  消  </button>
	 			</th>
				<!-- <th > <input type="submit" data-loading="稍候..." value="保存"  class="btn btn-primary radius" ></th>
				<th >
					
				</th>
				<th >
					<input type="button" class="btn btn-danger radius"  data-dismiss="modal" value="取消" >
				</th> -->
			</tr>
		</tfoot>
	</table>
</form>
	<script type="text/javascript">
	$("#bigImg").uploadImg({
		urlId : "showBigImg",
		showImg : $('#showBigImg').val()
	});
	
	$("#smallImg").uploadImg({
		urlId : "showSmallImg",
		showImg : $('#showSmallImg').val()
	});
	var valiinfo={
		rules:{
			pid:{
				required:true
			},
			tradename:{
				required:true,
				maxlength:200
			},
			orderNum:{
				digits:true
			},
			type:{
				required:true
			},
			website:{
				required:true,
				url:true
			},
			redirectType:{
				required:true
			}
		},
		messages:{
			pid:{
				 required:"未选择模块"
			},
			tradename:{
				required:"分类名称未填写",
				maxlength:"最多长度200字符"
			},
			orderNum:{
				digits:"分类排序只能为数字"
			},
			type:{
				required:"跳转类型未选择!"
			},
			website:{
				required:"跳转地址未填写!",
				url:"无效的链接地址!"
			},
			redirectType:{
				required:"跳转地址未选择!"
			}
		}
	}
	
	$(function(){
		typeVal();
		initValue();
		$("#type").on("change",function(){
			typeVal();
		});
		validate("categoryForm",valiinfo,formSubmit); 
	});
	
	function typeVal(){
		var type= $("#type").val();
		if(type=="1"){
			createInput();
		}else if(type=="2"){
			createSelect();
		}
	}
	
	function initValue(){
		var type= $("#type").val();
		var urlValue = '${category.url}';
		console.info(urlValue);
		if(type=="1"){
			$("#categoryForm").find("input[name=website]").val(urlValue);
		}else if(type=="2"){
			$("#categoryForm").find("select[name=redirectType]").children().each(function(){
				var val = $(this).val();
				if(val==urlValue){
					 this.selected=true;
					 return;
				}
			});
		}
	}
	
	function createInput(){
		$("#urlTd").empty();
		$("#urlTd").append("<input type='text'  class='form-control' placeholder='跳转url' name='website' >");
	}
	function createSelect(){
		$("#urlTd").empty();
		var $sel = $("<select class='form-control'  name='redirectType'>");
		var $opt1 = $("<option value=''>请选择</option>");
		var $opt2 = $("<option value='seller' >商户列表页</option>");
		var $opt3 = $("<option value='grow'>成长记列表</option>");
		$sel.append($opt1);
		$sel.append($opt2);
		$sel.append($opt3);
		$("#urlTd").append($sel);
		
	}
	 
	
	</script>

	
