var pageDiv;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	pageDiv = $('#floatAdvertInfoDiv').page({
		url : 'floatAdvert/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'floatAdvertFromId'
	});
	
	//标题
	inserTitle(' > 直播频道> <span><a href="floatAdvert/manage/init.jhtml" target="right">悬浮广告管理</a>','sellerList',true);
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/floatAdvert/manage/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
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
	
});


function success(data, obj) {
	var formId = "floatAdvertFromId", title = "悬浮广告列表", subtitle = "个悬浮广告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+ title+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+ data.total+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());
	
	updateAddBtnHref("#addFloatAdvertBto", callbackParam);	
	
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete","up","down" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "上移",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/up.jhtml",// url
						param : ["id"],// 参数
						permission : "up"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType==1){//直播才显示上移，下移操作
							value = "<a href='javascript:upOrDown(\""+data.id+"\",\"" + "up" + "\")'>" + "上移" + "</a>";
						}
			            return value;
				    }
				} ,{
					title : "下移",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveSeller/manage/down.jhtml",// url
						param : ["id"],// 参数
						permission : "down"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType==1){
							value = "<a href='javascript:upOrDown(\""+data.id+"\",\"" + "down" + "\")'>" + "下移" + "</a>";
						}
			            return value;
				    }
				} ,
		        {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						/*
						 * linkInfoName : "modal",
						 * modal : {
							url : "floatAdvert/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改悬浮广告"
						},*/
						href : "floatAdvert/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "粉丝券",
			name : "cname",
			type : "string",
			width : 150
		}, {
			title : "投放主播",
			name : "nickname",
			type : "string",
			width : 150
		}, {
			title : "广告时间",
			name : "planStartDateStr",
			type : "string",
			width : 220,
			customMethod : function(value, data) {//销售时间
				var saleStartTime = data.startTime?data.startTime:'-';
				var saleEndTime = data.endTime? data.endTime:'-';
				return saleStartTime+'-'+saleEndTime;
			}
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				switch (value) {
				case 1:
					result = "上线";
					break;
				case 2:
					result = "未开始";
					break;
				case 3:
					result = "已结束";
					break;
				default:
					result = "已下线";
					break;
				}
				return result;
			}
		}, {
			title : "封面",
			name : "picUrl",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		} ]
	}, permissions);
	
	
}



/**
 * 批量删除
 */
$('#deleteBto').click(function(){
	var ids = pageDiv.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'floatAdvert/manage/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					pageDiv.reset();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
});


function floatAdvertOption(status){
	var ids = pageDiv.getIds();
	if(!pageDiv.getIds()){
		showWarningWindow("warning", "请至少选择一条套餐记录！");
		return;
	}
	
	if(status == 2){
		if(!pageDiv.validateChose("status", "3, 1", "套餐已经售罄不能上架")){
			return;
		}
	}
	
	showSmConfirmWindow(function(){
		$.ajax({
			type:"POST",
			url:"floatAdvert/manage/beachOnLine/updateStatusOption.jhtml",
			data:{ids:ids, state:status},
			dataType:"json",
			success:function(data){
				if(data.success) {
					setTimeout(function() {
						pageDiv.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success,data.msg);
			},
			error:function(data){
				showSmReslutWindow(data.success, data.msg);
			}
		});
		
	}, "确定修改吗？")
}

/**
 * 
 * @param id
 * @param operationType
 */
 function upOrDown(id,operationType){
	 $.ajax({
         url : "liveSeller/manage/upOrDown.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"operationType":operationType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			showWarningWindow("warning", result.msg);
     		}
         }
     });
 }

//导出
$("#beachOnLine").click(function(){
	floatAdvertOption(1);
});	

//2.下架
$("#beachDownLine").click(function(){
	floatAdvertOption(4);
});	

