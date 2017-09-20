var pageDiv;
var pageDiv1;
$(document).ready(function() {
	//标题
	inserTitle(' > 直播频道> <span><a href="liveLevel/manage/init.jhtml" target="right">主播等级管理</a>', 'sellerList',true);
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/liveLevel/manage/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});
	
	pageDiv = $("#liveLevelInfoList").page({
		url : 'liveLevel/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchFromId'
	});

	
});


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;全部主播等级 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个主播等级&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFromId").serialize());
	updateAddBtnHref("#addLiveLevelBto", callbackParam);

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data: data.content, 
		caption : captionInfo,
		checkable : false,
		//操作列
		handleCols : {
			title : "操作",// 标题
//			handlePermission : ["sj","xj","tj"],//需要用到checkbox
			queryPermission : [ "xg"],// 不需要选择checkbox处理的权限
			//width : 240,// 宽度
			cols : [ // 当前列的中元素
            {
				title : "修改",// 标题
				linkInfoName : "href",
				width : 30,
				linkInfo : {
					href : "liveLevel/manage/update/init.jhtml",
					param : ["id"],
					permission : "xg"
				}
			}] 
		},
		cols:[{
			title : "级别名称",
			name : "levelName",
			type : "string",
			width : 180
		},{
			title : "级别薪酬",
			name : "levelIncome",
			type : "string",
			width : 120
		},{
			title : "浮动绩效",
			name : "floatPerformance",
			type : "string",
			width : 120
		},{
			title : "收入上限",
			name : "topIncome",
			type : "string",
			width : 120		
		},{
			title : "完成率100%场次",
			name : "percentComplete",
			type : "string",
			width : 150		
		},{
			title : "完成率80%场次",
			name : "percentComplete80",
			type : "string",
			width : 120				
		},{
			title : "完成率60%场次",
			name : "percentComplete60",
			type : "string",
			width : 120		
		},{
			title : "完成率40%场次",
			name : "percentComplete40",
			type : "string",
			width : 120		
/*		},{
			title : "有效时长",
			name : "liveHours",
			type : "string",
			width : 300					
		},{
			title : "送礼分成",
			name : "giftAllot",
			type : "string",
			sort : "up",
			leng : 8,			
			isLink : false,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "liveLevel/manage/init.jhtml"
				},
				param : ["sellerid","sellerGrade","isonline"],
				permission : "yx"
			},
			width : 300,
			customMethod : function(value, data) {
				var giftAllot = data.giftAllot == undefined ? "-" : data.giftAllot +'%';
				return giftAllot;
			}*/
		}]
	}, permissions);
	
}


