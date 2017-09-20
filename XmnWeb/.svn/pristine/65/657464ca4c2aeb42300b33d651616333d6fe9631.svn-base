var sellerList;
var pageSize=15;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	sellerList = $('#sellerList').page({
		url : 'businessman/unsignedSeller/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:pageSize,
		paramForm : 'brandForm'
	});
	
	inserTitle(' > 商家管理  > <a href="businessman/unsignedSeller/init.jhtml" target="right">点评商户管理</a>','sellerList',true);
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
			//数据
			data:data.content,
			 //数据行
			cols:[{
				title : "上线状态",// 标题
				name : "isonlineStr",
				//sort : "up",
				width : 120,// 宽度
				type:"int",//数据类型		
		},{
			title : "商家编号",// 标题
			name : "unsignedSellerid",
			//sort : "up",
			width : 220,// 宽度
			type:"string"//数据类型
		}, {
			title : "商户名称",
			name : "sellername",
			type : "string",
			width : 150			
		}, {
			title : "所在区域",
			name : "businessTitle",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return value;
			}
		}, {
			title : "详细地址",
			name : "address",
			type : "string",
			width : 150
		}, {
			title : "联系电话",
			name : "tel",
			type : "string",
			width : 150
		}, {
			title : "人均",
			name : "consume",
			type : "string",
			width : 150
		}, {
			title : "相册",
			name : "media",
			type : "string",
			width : 150
		}, {
			title : "点评",
			name : "comment",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				if(data.comment){
					return "<a href='businessman/experience/comment/init.jhtml?sellerType=2&sellerid="+data.unsignedSellerid+"'  >"+data.comment+"</a>";
				}else{
					return value;
				}
			}
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				width : 80,// 宽度
				queryPermission : ["edit","delete"],// 不需要选择checkbox处理的权限
				// 当前列的中元素
				cols : [{
					title : "编辑", // 标题
					linkInfoName : "href",
					linkInfo : {
						href : "businessman/unsignedSeller/updateState.jhtml", // url
						param : [ "unsignedSellerid" ], // 参数
						permission : "edit" // 列权限
					},
					customMethod : function(value, data) {
						if (data.isonline == 0 || data.isonline==3) {
							return "<a href='javascript:;' onclick='update_fun(" + data.unsignedSellerid + ","+1+")'>启用</a>"
						}else if(data.isonline == 1){
							return "<a href='javascript:;' onclick='update_fun(" + data.unsignedSellerid + ","+3+")'>停用</a>"
						}
						else {
							return '';
						}
					}
				}] 
	}},permissions);
	
}

function update_fun(unsignedSellerid,type){
	$.ajax({
		type : 'post',
		url : "businessman/unsignedSeller/updateState.jhtml",
		data : {"unsignedSellerid":unsignedSellerid,"type":type},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			sellerList.reload();
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	
}




