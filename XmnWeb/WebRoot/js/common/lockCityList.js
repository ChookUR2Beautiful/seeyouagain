var isLockCityList;
$(document).ready(function() {
	inserTitle(' > 基础数据管理 > <a href="common/cityManagerment/init.jhtml" target="right">城市管理</a>','citySpan',true);
	isLockCityList = $('#isLockCityList').page({
		url : 'common/cityManagerment/lock/list.jhtml',
		success : successLockCity,
		pageBtnNum : 10,
		paramForm : 'searchFormLock',
		param:{
			status:"1"
		}
	});
	
	//区域搜索中查询
	var ld = $("#areaLdIdLock").areaLd({
		isChosen : true,
		showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"careaId",tipTitle:"--市--"}]
	});
	//重置选择查询条件
	$("input[data-bus=reset]").click(function(){		
		$("#areaLdIdLock").find("select").trigger("chosen:updated");
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successLockCity(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;<font>共计【'+data.total+'】个关闭城市信息&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFormLock").serialize());
	obj.find('div').eq(0).scrollTablel({
		callbackParam : callbackParam,
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
					param : ["careaId"],
					permission : "open"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:open('+data.careaId+', '+0+');">开通</a>';
				}
			}] 
		},
		cols:[{
			title : "省编号",
			name : "pareaId",
			type : "string",
			width : 150
		},{
			title : "省份",
			name : "ptitle",
			type : "string",
			width : 160
		},{
			title : "市编号",
			name : "careaId",
			type : "string",
			width : 150
		},{
			title : "城市",
			name : "ctitle",
			type : "string",
			width : 150
		}]
	},permissions);
}

//开通城市
function open(areaId, status) {
	showSmConfirmWindow(function(){
		formAjaxOpen(areaId, status);
	}, "确定开通该城市？");		
}

function formAjaxOpen(areaId, status){
	$.ajax({
		type : 'post',
		url : 'common/cityManagerment/updateStatus.jhtml' + '?t=' + Math.random(),
		data : {
			'areaId':areaId,
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
				if (isLockCityList != undefined) {
					isLockCityList.reload();
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