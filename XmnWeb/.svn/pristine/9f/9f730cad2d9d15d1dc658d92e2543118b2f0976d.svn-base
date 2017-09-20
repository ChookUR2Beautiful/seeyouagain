var levelList;
var pageSize=15;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	levelList = $('#levelList').page({
		url : 'groupLevel/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:pageSize,
		paramForm : 'brandForm'
	});
	
	inserTitle(' > 打赏分红 > <a href="groupLevel/init.jhtml" target="right">团队等级管理</a>','brandList',true);
	
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个品牌&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add",callbackParam);
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content,
			 //数据行
			cols:[{
				title : "级别",// 标题
				name : "level",
				//sort : "up",
				width : 120,// 宽度
				type:"int",//数据类型		
		},{
			title : "名称",// 标题
			name : "levelName",
			//sort : "up",
			width : 220,// 宽度
			type:"string"//数据类型
		}, {
			title : "月度业绩",
			name : "minPerformance",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return data.minPerformance+"~"+data.maxPerformance;
			}
		}, {
			title : "奖励比例",
			name : "awardScale",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return data.awardScale+"%";
			}
		}, {
			title : "统计周期",
			name : "periodStr",
			type : "string",
			width : 150
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				width : 80,// 宽度
				queryPermission : ["edit","delete"],// 不需要选择checkbox处理的权限
				// 当前列的中元素
				cols : [{
					title : "编辑",// 标题
					linkInfoName : "modal",
					width : 20,
					linkInfo : {
						modal : {
							url : "groupLevel/update/init.jhtml",
							position : "100px",
							width : "600px"
						},
						param : ["id"],
						permission : "edit"
					}
				}] 
	}},permissions);
	
}






