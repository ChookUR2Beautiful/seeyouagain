var allBillList;
$(document).ready(function() {
	allBillList = $('#transportFeeList').page({
		url : 'transportFee/manage/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
		paramForm : 'searchForm'
	});
	
	inserTitle(' > 运费管理 > <a href="transportFee/manage/init.jhtml" target="right">运费模板列表</a>','transportFeeList',true);
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","materialOrder/manage/export.jhtml");
		$form[0].submit();
	});
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html=[];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">运费模板列表<font style="float:right;">共计【'+data.total+'】条记录&nbsp;</font></caption>');
	if(null != data && data.content.length>0){
 		for(var i = 0;i<data.content.length;i++){
 	html.push('<thead>');
 	html.push('<tr>');
 	html.push('<th colspan="5"><p style="text-align:left;float: left;">'+data.content[i].templateName+'('+(undefined ==data.content[i].supplierName?'-':data.content[i].supplierName)+')</p><div style="float:right;">');
 	if(authorityEdit !=''&&authorityEdit=='true'){
 	html.push('<a href="javascript:edit('+data.content[i].id+')" style="text-align:right">编辑</a>');
 	}else{
 		html.push('<a href="javascript:;" style="text-align:right;color:gray">编辑</a>')
 	}
 	if(authorityDelete !=''&& authorityDelete =='true'){
 		html.push('|<a href="javascript:remove('+data.content[i].id+')" style="text-align:right">删除</a>')
 	}else{
 		html.push('|<a href="javascript:;" style="text-align:right;color:gray">删除</a>')
 	}
 	html.push('</div></th>');
 	html.push('</tr>');
 	html.push('<tr>');
 	html.push('<th>可配送至</th>');
 	html.push('<th>'+(data.content[i].type=='001'?'首件（个）':'首重（kg）')+'</th>');
 	html.push('<th>运费（元）</th>');
 	html.push('<th>'+(data.content[i].type=='001'?'续件（个）':'续重（kg）')+'</th>');
 	html.push('<th>续费（元）</th>');
 	html.push('</tr>');
 	html.push('</thead>');
 	
 	html.push('<tbody>');
 	html.push('</tbody>');
 			if(data.content[i].conditions.length>0){
 				for(var j=0;j<data.content[i].conditions.length;j++){
 					html.push('<tr>');
 					html.push('<td style="width: 20%;">'+data.content[i].conditions[j].deliveryCity+'</td>');
 					html.push('<td style="width: 10%;">'+data.content[i].conditions[j].firstNums+'</td>');
 					html.push('<td style="width: 15%;">'+data.content[i].conditions[j].firstItem+'</td>');
 					html.push('<td style="width: 15%;">'+data.content[i].conditions[j].continueNums+'</td>');
 					html.push('<td style="width: 15%;">'+data.content[i].conditions[j].continueItem+'</td>');
 					html.push('</tr>');
 				}
 			}else{
 				html.push('<tr><td colspan="5">暂无数据</td></tr>');
 			}
 		}
 	}else{
 		html.push('<tr><td colspan="5">暂无数据</td></tr>');
 	}
 	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 编辑
 * @param id
 * @returns
 */
function edit(id){
	window.location.href=contextPath+"/transportFee/manage/editView.jhtml?id="+id+"&t="+Math.random();
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

function remove(id){
	showSmConfirmWindow(function (){
	$.ajax({
		type : 'post',
		url : 'transportFee/manage/deleteTemplate.jhtml' + '?t=' + Math.random(),
		data : {
			'id':id			
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
			if (data.success) {
				allBillList.reload();
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
	 },"确定删除吗？");
}


function recoveryAjax(bid){
	$.ajax({
		type : 'post',
		url : 'billmanagerment/refundBillPending/recoveryOrder.jhtml' + '?t=' + Math.random(),
		data : {
			'bid':bid			
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
			if (data.success) {
				allBillList.reload();
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
}
function formAjax(id,bid,money){
		$.ajax({
			type : 'post',
			url : 'billmanagerment/refundBillPending/confirm.jhtml' + '?t=' + Math.random(),
			data : {
				'id':id,
				'bid':bid,
				'money':money
				//'remarks':remarks				
			},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {			 				
	          	$('#prompt').hide();
	          	if (data.success) {
					allBillList.reload();
			    }	
				showSmReslutWindow(data.success, data.msg);
				
				//支付宝打开页面（response不为空才打开）
				if(null!=data.data.response && ""!=data.data.response.trim()){
					var body = window.document.body;
					var response = $.parseHTML(data.data.response);
					$.each(response,function(){
						if(this.nodeName.toLowerCase()=="form"){
							$(body).append($(this));
							var id = $(this).attr("id");
							$(this).attr("target","tkwindow");
							var $form = $(body).find($(this));
							if($form.length>0){
								$form.submit();
								$form.remove();
							} 
							return;
						}
					}); 
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
}
var win;
function openWind(){
	if(win){
		win.close();
	}
	win = window.open('','tkwindow'); 
}
