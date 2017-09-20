var enrollList1;//全部选手
var imgRoot = $("#fastfdsHttp").val();


$(document).ready(function() {
	inserTitle('> 会员管理> <a href="memberWallet/manage/init.jhtml" target="right">钱包管理</a>','enrollConfirm',true);
	
	pageInit();
	
});

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'memberWallet/manage/init/list.jhtml',
		success : success1,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 10,//每页条数
		paramForm : 'searchForm1',
		param :{}
	});
	
}

/**
 * 查询全部选手信息成功回调函数
 * @param data
 * @param obj
 */
function success1(data, obj) {
	var formId = "searchForm1", title = "会员钱包列表", subtitle = "条信息";
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
		checkable : false,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","detail","delete"],// 不需要选择checkbox处理的权限
			width : 200,// 宽度,
			cols: [
					{
						title : "&nbsp;&nbsp;&nbsp;&nbsp;修改用户钱包",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "memberWallet/manage/update/init.jhtml",
								position : "",
								width : "auto",
								title : "修改会员钱包信息"
							},
							param : [ "uid" ],
							permission : "update"
						}
					} ,
					{
						title : "修改直播钱包",// 标题
						linkInfoName : "modal",
						linkInfo : {
							modal : {
								url : "memberWallet/manage/update-live/init.jhtml",
								position : "",
								width : "auto",
								title : "修改直播钱包信息"
							},
							param : [ "uid" ],
							permission : "update"
						},
						customMethod:function(value,data){
							debugger;
							console.log(data);
							if(data.liveZbalanceLock !=undefined){
								return value;
							}else{
								return "";
							}
						}
					} 
			]
		},
		cols : [ {
			title : "用户编号",
			name : "uid",
			type : "string",
			width : 80
		},{
			title : "用户昵称",
			name : "nname",
			type : "string",
			width : 100
		},{
			title : "佣金锁定状态",
			name : "zbalanceLock",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				var result="-";
				if(value=="true"){
					result="已锁定";
				}else if(value=="false"){
					result="未锁定";
				}
				return result;
			}
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 100
		} ,{
			title : "钱包余额",
			name : "amount",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				return value;
			}
		},{
			title : "分账余额",
			name : "balance",
			type : "string",
			width : 100
		},{
			title : "佣金余额",
			name : "commision",
			type : "string",
			width : 100
		},{
			title : "赠送余额",
			name : "zbalance",
			type : "string",
			width : 100
		},{
			title :"是否锁定鸟币",
			name :"liveZbalanceLock",
			type :"string",
			width:100,
			customMethod:function(value,data){
				var result="-";
				if(value=="true"){
					result="是";
				}
				if(value=="false"){
					result="否";
				}
				return result;
			}
		},{
			title : "鸟蛋余额",
			name : "liveBalance",
			type : "string",
			width : 100
		},{
			title : "鸟豆余额",
			name : "liveCommision",
			type : "string",
			width : 100
		},{
			title : "鸟币余额",
			name : "liveZbalance",
			type : "string",
			width : 100
		},{
			title : "鸟蛋转出总额",
			name : "liveTurnEggOut",
			type : "string",
			width : 100
		}]
	}, permissions);
}



/**
 * 更新报名审核状态
 * @param id
 * @param status
 * @param telphones
 */
function update(id,status){
	 $.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"status":status},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 更新报名限制状态
 * @param id
 * @param confining
 */
function confine(id,confining){
	$.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"confining":confining},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 试播审核
 */
 function confirmPilot(id,status){
	 if(id==undefined || id == '' || status== undefined || status == ''){
		 window.messager.warning("报名信息不完整");
	 }
	 
	 var tips="是否通过申请";
	 if(status=="7"){
		 tips="是否通过申请";
	 }else if(status=="8"){
		 tips="是否拒绝申请";
	 }
	 
	 showSmConfirmWindow(function (){
		 $.ajax({
		        url : "VstarEnroll/manage/update.jhtml",
		        type : "post",
		        dataType : "json",
		        data:{"id":id,"status":status},
		        success : function(result) {
		       	 if (result.success) {
		    			showSmReslutWindow(result.success, result.msg);
		    			setTimeout(function() {
		    				pageInit();
		    			}, 1000);
		    		} else {
		    			window.messager.warning(result.msg);
		    		}
		        }
		    });
	 },tips);
 }

 
//初始化赛区下拉框
 function initDivisionId(){
 	debugger;
 	$("#divisionId").chosenObject({
 		hideValue : "id",
 		showValue : "divisionName",
 		url : "division/initDivisionId.jhtml",
 		isChosen:true,//是否支持模糊查询
 		isCode:true,//是否显示编号
 		isHistorical:false,//是否使用历史已加载数据
 		width:"85%"
 	});
 }
