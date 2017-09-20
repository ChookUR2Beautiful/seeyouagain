var lockBussinessInArea;
$(document).ready(function() {
	//inserTitle(' > 基础数据管理 > <a href="common/cityManagerment/init.jhtml" target="right">城市管理</a>','citySpan',true);
	var areaId = $("#areaIdTag").val();
	lockBussinessInArea = $('#lockBussinessInArea').page({
		url : 'common/area/bussiness/lock/list.jhtml',
		success : successLockBussinessInArea,
		pageBtnNum : 10,
		paramForm : 'searchFormLockArea',
		param:{
			status : "0",
			areaId : areaId 
		}
	});
	
	//区域搜索中查询
//	var ld = $("#areaLdIdLock").areaLd({
//		isChosen : true,
//		showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"careaId",tipTitle:"--市--"}]
//	});
	//重置选择查询条件
//	$("input[data-bus=reset]").click(function(){		
//		$("#areaLdIdLock").find("select").trigger("chosen:updated");
//	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successLockBussinessInArea(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;<font>共计【'+data.total+'】个关闭商圈信息&nbsp;</font></caption>';
	//var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFormLock").serialize());
	obj.find('div').eq(0).scrollTablel({
		//callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["open"],// 不需要选择checkbox处理的权限
			// 当前列的中元素
			cols : [{
				title : "开通",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["bid"],
					permission : "open"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:open('+data.bid+', '+1+');">开通</a>';
				}
			}] 
		},
		cols:[{
			title : "城市",
			name : "ctitle",
			type : "string",
			width : 150
		},{
			title : "区域",
			name : "atitle",
			type : "string",
			width : 160
		},{
			title : "商圈",
			name : "btitle",
			type : "string",
			width : 150
		}]
	},permissions);
}

//开通城市
function open(bid, status) {
	showSmConfirmWindow(function(){
		formAjaxOpen(bid, status);
	}, "确定开通该商圈？");		
}

function formAjaxOpen(bid, status){
	$.ajax({
		type : 'post',
		url : 'common/area/bussiness/updateBussinessStatus.jhtml' + '?t=' + Math.random(),
		data : {
			'bid':bid,
			'status':status
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			try {
				if (lockBussinessInArea != undefined) {
					lockBussinessInArea.reload();
				}
			} catch (err) {
				console.log(err);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
		}
	});
}