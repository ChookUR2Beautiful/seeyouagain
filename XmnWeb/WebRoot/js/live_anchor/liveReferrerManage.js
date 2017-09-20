var anchorList;
var initListUrl = "liveReferrer/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveReferrer/manage/init.jhtml" target="right">企业级推荐人管理</a>',
			'userSpan', true);
	anchorList = $("#anchorList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	liveDateInit();
	
	//导出
	$("#exportAnchor").click(function(){
		var path="liveReferrer/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "shareForm", title = "企业级推荐人列表", subtitle = "个会员。";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","detail","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveReferrer/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\",\""+data.uid+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
				{
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveReferrer/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "设置推荐人类型"
						},
						param : [ "id" ],
						permission : "update"
					}
				} 
			]
		},
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 150
		},{
			title : "会员昵称",
			name : "nickname",
			type : "string",
			width : 150
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 150
		}, {
			title : "推荐人类型",
			name : "referrerType",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case '001':
					result='企业推荐人';
					break;
				case '002':
					result='普通推荐人';
					break;
				default:
					result='-';
					break;
				}
				
				return result;
			}
		},  {
			title : "下线人数",
			name : "juniors",
			type : "string",
			width : 180
		}, {
			title : "下线累计充值",
			name : "juniorRecharge",
			type : "string",
			width : 180,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "livePayOrder/manage/init.jhtml"
				},
				param : ["uid"],
				permission : "rechargeDetail"
			},
			customMethod : function(value, data) {
//				debugger;
				if(undefined==data.juniorRecharge){
					return "-";
				}else{
					var result;
					try{
						result = $(value).length>0?data.juniorRecharge+"<br>"+$(value).html("充值详情")[0].outerHTML:"-";
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		},{
			title : "下线累计打赏",
			name : "juniorReward",
			type : "string",
			width : 180,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "liveGivedGift/manage/init.jhtml"
				},
				param : ["uid"],
				permission : "rechargeDetail"
			},
			customMethod : function(value, data) {
//				debugger;
				if(undefined==data.juniorReward){
					return "-";
				}else{
					var result;
					try{
						result = $(value).length>0?data.juniorReward+"<br>"+$(value).html("打赏详情")[0].outerHTML:"-";
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}, {
			title : "下线消费累计金额",
			name : "juniorConsume",
			type : "string",
			width : 200,
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "liveCouponOrder/manage/init.jhtml"
				},
				param : ["uid"],
				permission : "rechargeDetail"
			},
			customMethod : function(value, data) {
				if(undefined==data.juniorConsume){
					return "-";
				}else{
					var result;
					try{
						result = $(value).length>0?data.juniorConsume+"<br>"+$(value).html("粉丝券详情")[0].outerHTML:"-";
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			}
		}  ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id,uid){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveReferrer/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id,uid:uid},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 anchorList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"将删除该主播的所有通告，确定要删除吗？");
 }
 
 /**
  * 日期控件初始化
  */
 function liveDateInit(){
	 $('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});
	 
	 
	 $('input[name="startTime"]').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate : new Date(),
			endDate: $("input[name='endTime']").val()
		}).on("changeDate",function() {
				$("input[name='endTime']").datetimepicker("setStartDate",$("input[name='startTime']").val());
			});
		
		$('input[name="endTime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 0,
			minuteStep :30,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd',
			startDate: $("input[name='startTime']").val()
		}).on( "changeDate", function() {
					$("input[name='startTime']").datetimepicker("setEndDate", $("input[name='endTime']").val());
				});
 }