var isOpenCityList;
$(document).ready(function() {
	inserTitle(' > 基础数据管理 > <a href="common/cityManagerment/init.jhtml" target="right">城市管理</a>','citySpan',true);
	isOpenCityList = $('#isOpenCityList').page({
		url : 'common/cityManagerment/open/list.jhtml',
		success : successOpenCity,
		pageBtnNum : 10,
		paramForm : 'searchFormOpen',
		param:{
			status:"0"
		}
	});
	
	//区域搜索中查询
	var ld = $("#areaLdIdOpen").areaLd({
		isChosen : true,
		showConfig : [{name:"pareaId",tipTitle:"--省--"},{name:"careaId",tipTitle:"--市--"}]
	});
	//重置选择查询条件
	$("input[data-bus=reset]").click(function(){		
		$("#areaLdIdOpen").find("select").trigger("chosen:updated");
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successOpenCity(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;<font>共计【'+data.total+'】个开通城市信息&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFormOpen").serialize());
	obj.find('div').eq(0).scrollTablel({
		callbackParam : callbackParam,
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
					param : ["careaId"],
					permission : "lock"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:lock('+data.careaId+', '+1+');">关闭</a>';
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
		},{
			title : "开通数量",
			name : "openNum",
			type : "string",
			width : 150
		},{
			title : "关闭数量",
			name : "lockNum",
			type : "string",
			width : 150
		}]
	},permissions);
}

//关闭城市
function lock(areaId, status) {
	showSmConfirmWindow(function(){
		formAjaxLock(areaId, status);
	}, "确定关闭该城市？");		
}

function formAjaxLock(areaId, status){
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
				if (isOpenCityList != undefined) {
					isOpenCityList.reload();
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