var userAdvertisingList;
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(function() {
	initDates();
	userAdvertisingList = $('#userAdvertisingList').page({
		url : 'user_terminal/advertising/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(userAdvertisingList.getIds());
	});
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/advertising/init.jhtml" target="right">广告轮番图管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/advertising/export.jhtml");
		$form[0].submit();
	});
	// 区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
		resetStatusButtons();
	});
});
function resetStatusButtons() {
	var buttons = $("button.status");
	for (i = 0; i < buttons.length; i++) {
		$(buttons[i]).removeClass("btn-success").addClass("btn-default");
	}
	$(buttons).first().removeClass("btn-default").addClass("btn-success");
}

function queryStatus(object,status){
	var buttons = $("button.status");
	for(i=0;i<buttons.length;i++){
		$(buttons[i]).removeClass("btn-success").addClass("btn-default");
	}
	$(object).removeClass("btn-default").addClass("btn-success");
	$("input[name='isshow']").val(status);
	userAdvertisingList.reload();
}

function updateStatus(id,status){
	var url,msg;
	if(status==1){//上线
		url = "user_terminal/advertising/online.jhtml";
		msg = "确认要上线？"
	}
	if(status==2){//下线
		url = "user_terminal/advertising/offline.jhtml";
		msg = "确认要下线？"
	}
	showSmConfirmWindow(function(){
		$.ajax({
			method: "POST",
			url : url,
			data : {"id" : id},
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				userAdvertisingList.reload();
			},
		});
	},msg);
}

function success(data, obj) {
	var formId = "searchForm",title = "广告轮播图列表",subtitle="个广告轮播";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+title+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】'+subtitle+'&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#"+formId).serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["update","offline","online"],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			cols : [{
				title : "下线",// 标题
				linkInfoName : "method",
				linkInfo : {
					method : "updateStatus",
					param : ["id"],
					customParam :["2"],
					permission : "offline"
				},
				customMethod : function(value, data){
					var result = '<a href="javascript:void(0)" class="hidden"></a>';
					if(data.isshow != undefined && data.isshow == 0){
						result = value;
					}
					return result;
				}
			},{
				title : "上线",// 标题
				linkInfoName : "method",
				linkInfo : {
					method : "updateStatus",
					param : ["id"],
					customParam :["1"],
					permission : "online"
				},
				customMethod : function(value, data){
					var result = '<a href="javascript:void(0)" class="hidden"></a>';
					if(data.isshow != undefined && (data.isshow == 1 || data.isshow == 2)){
						result = value;
					}
					return result;
				}
			},{
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "user_terminal/advertising/update/init.jhtml",// url
						position : "",// 模态框显示位置
						width : "800px", // 模态框宽度
						title : "修改广告轮播图" //模态框标题
					},
					param : ["id"],
					permission : "update"
				}
			}] 
		},
		cols:[{
			title : "广告图片",
			name : "adbpic",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "分享图片",
			name : "share_img",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "广告文本",
			name : "content",
			type : "string",
			width : 150
		},{
			title : "广告链接",
			name : "adburl",
			type : "string",
			width : 180
		},{
			title : "排序",
			name : "sort",
			type : "string",
			width : 150
		},{
			title : "上线状态",
			name : "isshowText",
			type : "string",
			width : 150
		},{
			title : "类型",
			name : "typeText",
			type : "string",
			width : 150
		},{
			title : "开始时间",
			name : "startTime",
			type : "string",
			width : 180
		},{
			title : "结束时间",
			name : "endTime",
			type : "string",
			width : 180
		},{
			title : "区域",
			name : "areaText",
			type : "string",
			width : 200
		},{
			title : "分享标题",
			name : "shareTitle",
			type : "string",
			width : 150
		},{
			title : "分享描述",
			name : "share_cont",
			type : "string",
			width : 150
		},{
			title : "分享链接",
			name : "shareUrl",
			type : "string",
			width : 150
		},{
			title : "备注",
			name : "remarks",
			type : "string",
			width : 400
		}]},permissions);
}




/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/advertising/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					userAdvertisingList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


/**
 * datetimepicker 转化
 */
function initDates() {
	$("input.form-datetime").datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii:ss',
		todayBtn : true,
		minuteStep : 1
	})
}
