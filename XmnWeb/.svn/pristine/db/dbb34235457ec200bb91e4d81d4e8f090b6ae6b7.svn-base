var openBussinessInArea;
$(document).ready(function() {
	//inserTitle(' > 基础数据管理 > <a href="common/area/init.jhtml" target="right">区域管理</a>','citySpan',true);
	var areaId = $("#areaIdTag").val();
	openBussinessInArea = $('#openBussinessInArea').page({
		url : 'common/area/bussiness/open/list.jhtml',
		success : successOpenBussinessInArea,
		pageBtnNum : 10,
		paramForm : 'searchFormOpenArea',
		param:{
			status : "1",
			areaId : areaId 
		}
	});

	//区域搜索中查询
//	var ld = $("#areaLdIdOpen").areaLd({
//		isChosen : true,
//		showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"careaId",tipTitle:"--市--"}]
//	});
	//重置选择查询条件
//	$("input[data-bus=reset]").click(function(){		
//		$("#areaLdIdOpen").find("select").trigger("chosen:updated");
//	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successOpenBussinessInArea(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;<font>共计【'+data.total+'】个开通商圈信息&nbsp;</font></caption>';
	//var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFormOpen").serialize());
	obj.find('div').eq(0).scrollTablel({
		//callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["lock"],// 不需要选择checkbox处理的权限
			// 当前列的中元素
			cols : [{
				title : "关闭",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["bid"],
					permission : "lock"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:lock('+data.bid+', '+0+');">关闭</a>';
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

//关闭城市
function lock(bid, status) {
	showSmConfirmWindow(function(){
		formAjaxLock(bid, status);
	}, "确定关闭该商圈？");		
}

function formAjaxLock(bid, status){
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
				if (openBussinessInArea != undefined) {
					openBussinessInArea.reload();
				}
			} catch (err) {
				console.log(err);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
		}
	})
}