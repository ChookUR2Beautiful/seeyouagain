var sellerNoticeList;
$(document).ready(function() {
	sellerNoticeList = $('#sellerNoticeList').page({
		url : 'user_terminal/tsellernotice/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	inserTitle(' > 用户端管理 > <a href="user_terminal/tsellernotice/init.jhtml" target="right">商家须知</a>','userSpan',true);

});
function success(data, obj) {
	var formId = "searchForm",title = "商家须知列表",subtitle="商家须知信息";
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
			queryPermission : ["update"],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			cols : [{
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "user_terminal/tsellernotice/update/init.jhtml",// url
						position : "",// 模态框显示位置
						width : "50%", // 模态框宽度
						title : "修改活动banner" //模态框标题
					},
					param : ["id"],
					permission : "update"
				}
			}] 
		},
		cols:[{
			title : "商家须知编号",
			name : "id",
			type : "string",
			width : 150

		},{
			title : "商家须知",
			name : "remark",
			type : "string",
			width : 560,
			customMethod : function(value, data) {
				console.info(value.length);
				if(value.length>10){
					return value.substr(0, 10)+"..."; 
				}else{
					return value;
				}
				console.info("value:"+value);
			}
			
		},{
			title : "是否有效",
			name : "statusText",
			type : "Integer",
			width : 150
		}]},permissions);
}
