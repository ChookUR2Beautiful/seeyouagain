var extensionSetList;
$(document).ready(function() {
	extensionSetList = $('#extensionSetList').page({
		url : 'marketingManagement/extensionSet/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:30,
		paramForm : 'searchForm'
	});

	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	inserTitle(' > 营销活动管理 > <a href="marketingManagement/extensionSet/init.jhtml" target="right"> 引流商家列表</a>','extensionSetSpan',true);
});
$(function(){
	//区域联动
	   $("#ld").areaLd({
			isChosen : true,
			showConfig : [ {
				name : "province",
				tipTitle : "--省--",
				width : '49%'
			}, {
				name : "city",
				tipTitle : "--市--",
				width : '49%'
			}]
		});
	 //重置
   $("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});

function queryBumen(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
   if(parms=="All"){
	   parms=""; 
   }
   var data = {extensionType:parms};
	$.ajax({
        type: "POST",
        url : 'marketingManagement/extensionSet/init/list.jhtml',
        data: data,
        dataType: "json",
        success: function(result){
           if(parms=="All"){
        	   $("#extensionType").val('');
       		   extensionSetList.reload();
           }else{
	            $("#extensionType").val(parms);
	        	extensionSetList.reload();
           }
         }
    });
	
	$("input[data-bus=reset]").click(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});

	$(object).attr("class", "btn btn-success");
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
/*function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">引流商家列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(!isEmptyObject(permissions)){
		html.push('<th>操作</th>');
	}
	html.push('<th >商家编号</th>');	
	html.push('<th >商家名称</th>');
	html.push('<th >商家帐号</th>');
	html.push('<th >推广类型</th>');
	html.push('<th >推广开始时间</th>');
	html.push('<th >推广结束时间</th>');
	html.push('<th >排序</th>');
	html.push('<th >商家地址</th>');
	html.push('<th >咨询电话</th>');
	html.push('<th >区域</th>');
	html.push('<th >归属合作商</th>');
	html.push('<th >是否开启首次</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
if(null != data && data.content.length > 0)
	{
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!isEmptyObject(permissions)){
			html.push('<td>');
			if(permissions.update=='true'){
			
				html.push('<a  href="javascript:void();" data-type="ajax" data-position="" data-width="560px" data-url="marketingManagement/extensionSet/update/init.jhtml?id='+data.content[i].id +'" data-toggle="modal" >修改&nbsp;&nbsp;</a>');
		    }
			if(permissions.del=='true'){
				
					html.push('<a href="javascript:remove('+data.content[i].id +')">删除</a>');
			}	
			html.push('</td>');
		}
		html.push('<td>' + (undefined == data.content[i].sellerId ? "-" : data.content[i].sellerId) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sellername ? "-" : data.content[i].sellername) + '</td>');
		html.push('<td>' + (undefined == data.content[i].account ? "-" : data.content[i].account) + '</td>');
		
		var type=data.content[i].extensionType;
		var types="";
		if(type==1){types="摇一摇";}else if(type==2){types="订单推广";}else if(type==3){types="列表推广";}else{types="";}
		html.push('<td>' + (undefined == types ? "-" : types) + '</td>');
		html.push('<td>' + (undefined == data.content[i].dateStart ? "-" : data.content[i].dateStart) + '</td>');
		html.push('<td>' + (undefined == data.content[i].dateEnd ? "-" : data.content[i].dateEnd) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sort ? "-" : data.content[i].sort) + '</td>');
		html.push('<td>' + (undefined == data.content[i].address ? "-" : data.content[i].address) + '</td>');
		html.push('<td>' + (undefined == data.content[i].tel ? "-" : data.content[i].tel) + '</td>');
		html.push('<td>' + (undefined == data.content[i].provinceName + "-"+ data.content[i].title ? "-" : data.content[i].provinceName + "-"+ data.content[i].title) + '</td>');
		html.push('<td>' + (undefined == data.content[i].corporate ? "-" : data.content[i].corporate) + '</td>');
		var Str=data.content[i].isFirst;
		var isFirst="";
		if(Str==1){isFirst="是";}else if(Str==0){isFirst="否";}
		html.push('<td>' + (undefined == isFirst ? "-" : isFirst) + '</td>');
		html.push('</tr>');
	}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="17">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}*/

function success(data, obj) {
	//var captionInfo = '<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">引流商家列表</caption>';
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;引流商家信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】条信息&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass : "table table-hover table-bordered table-striped info",
		//数据
		data:data.content, 
		caption : captionInfo,
		//数据行
		cols:[{
			title : "商家编号",
			name : "sellerId",
//			sort : "up",
			type : "string",
			width : 150
		},{
			title : "商家名称",
			name : "sellername",
			//sort : "up",
			type : "string",
			width : 150
		},{
			title : "商家账号",
			name : "account",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "推广类型",
			name : "extensionType",
			type : "string",
			customMethod : function(value, data) {
				var type = data.extensionType;
				var result;
				if(1 == type){
					result = "摇一摇";
				}else if(2 == type){
					result = "订单推广";
				}else if(3 == type){
					result = "列表推广";
				}else{
					result = "";
				}
				return result;
			},
			width : 150
		},{
			title : "推广开始时间",
			name : "dateStart",
			type : "string",
			width : 155,
			leng : 8
		},{
			title : "推广结束时间",
			name : "dateEnd",
			type : "string",
			width : 155,
			leng : 8
		},{
			title : "排序",
			name : "sort",
			type : "string",
			width : 150,
			leng : 8 
		},{
			title : "商家地址",
			name : "address",
			type : "string",
			width : 400,
			leng : 8
		},{
			title : "咨询电话",
			name : "tel",
			type : "string",
			width : 150,
			leng : 8
		},{
			title : "区域",
			name : "provinceName",
			type : "string",
			width : 150,
			leng : 8 
		},{
			title : "归属合作商",
			name : "corporate",
			type : "string",
			width : 300,
			leng : 8 
		},{
			title : "是否开启首次",
			name : "isFirst",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var first = data.isFirst;
				var result;
				if(1 == first){
					result = "是";
				}else{
					result = "否";
				}
				return result;
			},
			leng : 8
		}],
		//操作列
		handleCols : {
			title : "操作",  // 标题
			queryPermission : ["update", "del"],  // 不需要选择checkbox处理的权限
			width : 150,  // 宽度
			// 当前列的中元素
			cols : [{
				title : "删除",// 标题
				linkInfoName : null,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["id"],
					permission : "del"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:remove('+ data.id	+ ');">删除</a>';
				}
			},{
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "marketingManagement/extensionSet/update/init.jhtml",
						position : "200px",
						width : "800px"
					},
					param : ["id"],
					permission : "update"
				}
			}] 
	}}, permissions);
}

/**
 * 删除
 */
function remove(id) {
	if(!id){
	//	alert(id);
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/extensionSet/delete.jhtml' + '?id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					extensionSetList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

