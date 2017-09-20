var recordList;
var initListUrl = "liveSalary/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
		' > 直播频道 > <a href="liveSalary/init.jhtml" target="right">主播工资管理</a> ',
		'userSpan', true);

	initAnchorId();

	//加载页面数据
	pageInit();

	//日期控件初始化
	liveDateInit();

	//导出
	$("#export").click(function() {
		var path = "liveSalary/export.jhtml";
		showSmConfirmWindow(function() {
			var data = $("#searchForm").serializeArray();
			data = jsonFromt(data);
			myPost(path, data);
		}, "确定要导出吗？");
	});

});

//初始化默认主播下拉框
function initAnchorId() {
	//	debugger;
	$("#anchorId").chosenObject({
		hideValue : "id",
		showValue : "nickname",
		url : "anchor/manage/initAnchorId.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
}

/**
 * 加载页面数据
 */
function pageInit() {
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "searchForm",
		title = "共",
		subtitle = "个主播";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
		+ '<font>共计【'
		+ data.total
		+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
	+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作", // 标题
			queryPermission : [ "update", "delete" ], // 不需要选择checkbox处理的权限
			width : 150, // 宽度
			// 当前列的中元素
			cols : [
				{
					title : "修改工资", // 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveSalary/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改工资"
						},
						param : [ "id" ],
						permission : "update"
					},
					customMethod : function(value, data) {
						if(data.status==0){
							return  value;
						}
						return '';
					}
				},
				{
					title : "确认工资", // 标题
					linkInfoName : "href",
					linkInfo : {
						param : [ "id" ],
						permission : "update2"
					},
					customMethod : function(value, data) {
						return data.status==0?"<a style='cursor:pointer;' href='javascript:;' onclick='confirmSalary("+data.id+")'>确认工资</a>":'';
					}
					
				}

			]
		},
		cols : [ {
			title : "主播类型",
			name : "signTypeStr",
			type : "string",
			width : 100
		}, {
			title : "主播信息",
			name : "name",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;float:left;" src="' + imgRoot + data.avatar + '"/><div>' + data.name + "(" + data.levelName + ")</div><div>" + data.phone + "</div>";
			}
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				if (value == 0) {
					return "未发放"
				} else if (value == 1) {
					return "已发放";
				} else {
					return value;
				}
			}
		}, {
			title : "有效场次",
			name : "bout",
			type : "string",
			width : 100
		}
		, {
			title : "完成率",
			name : "percent",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				return data.percent*100+"%";
			}
		}
		, {
			title : "账户鸟蛋",
			name : "balance",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				return value+'<div><a style="cursor:pointer;" href="liveGivedGift/manage/salary/init.jhtml?anchorId='+data.anchorId+'">查看送礼明细</a></div>';
			}
		}, {
			title : "级别薪酬",
			name : "levelIncome",
			type : "string",
			width : 100
		}, {
			title : "浮动绩效",
			name : "floatPerformance",
			type : "string",
			width : 100
		}, {
			title : "收入上限",
			name : "topIncome",
			type : "string",
			width : 100
		}, {
			title : "系统计算工资",
			name : "baseSalary",
			type : "string",
			width : 100
		}, {
			title : "实际发放工资",
			name : "afterSalary",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				if (data.afterSalary) {
					return data.afterSalary + "(" + (data.isTaxes ? '已税' : '未税') + ")";
				} else {
					return value;
				}
			}
		}, {
			title : "统计日期",
			name : "beginDate",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				return data.beginDateStr + "至" + data.endDateStr;
			}
		} ]
	}, permissions);
}


/**
 * 直播日期控件初始化
 */
function liveDateInit() {
	$('.form_datetime').datetimepicker({
		format : 'yyyymm',
		weekStart : 1,
		autoclose : true,
		startView : 3,
		minView : 3,
		forceParse : false,
		language : 'zh-CN'
	});
}

function confirmSalary(id){
	showSmConfirmWindow(function (){	
		 $.ajax({
			 url : "liveSalary/confirmSalary.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					recordList.reload();
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"<div style='font-size: 20px;font-weight: bold;'>确认工资无误，点击后只修改状态类型，还需要让财务操作发放工资操作</div>");
}


$("#manual").on("click",function(){
	showSmConfirmWindow(function (){
		 $.ajax({
	         url : "liveSalary/init/list/manual.jhtml",
	         type : "post",
	         dataType : "json",
	         beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
	         success : function(result) {
	        	 $('#prompt').hide();
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			recordList.reload();
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"生成工资条会扣除主播相应鸟蛋,如主播鸟蛋不足,则生成失败,确定要生成吗？");
});