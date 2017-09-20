var memberListDiv;
$(document).ready(function() {
	memberListDiv = $('#memberListDiv').page({
		url : 'member/memberList/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm'
	});
	
	var ld = $("#ysqy").areaLd({
		isChosen : true,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"},{name:"region",tipTitle:"--区--"}]
	});
	
	//合作商(搜索查询)
	$("#jointid").chosenObject({
		id : "jointid",
		hideValue : "jointid",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		limit : -1,
		isChosen:true
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ysqy").find("select").trigger("chosen:updated");
		$("#jointid").trigger("chosen:updated");
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		minView :2,
		maxView :3,
		autoclose: true,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd'
	});
		
	inserTitle(' > 会员管理 > <span><a href="member/memberList/init.jhtml" target="right">会员列表</a>','memberList',true);
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;会员列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个会员&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
    	tableClass :"table-bordered table-striped info",
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
				title : "用户编号",// 标题
				name : "uid",
				//sort : "up",
				width : 150,// 宽度
				leng : 3,//显示长度
				type:"stirng"//数据类型
		},{
			title : "用户呢称",// 标题
			name : "nname",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},{
			title : "手机号码",// 标题
			name : "phone",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "性别",// 标题
			name : "sex",
			//sort : "up",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var sexTemp = data.sex;
				var result = "-";
				if(1 == sexTemp){
					result = "男";
				}else if(2 == sexTemp){
					result = "女";
				}
				return result;
			}
		},{
			title : "注册时间",// 标题
			name : "regtime",
			width : 180,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "状态",// 标题
			name : "status",
			width : 150,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var statusTemp = data.status;
				var result = "-";
				if(1 == statusTemp){
					result = "正常";
				}else if(2 == statusTemp){
					result = "锁定";
				}else if(3 == statusTemp){
					result = "注销";
				}else if(4 == statusTemp){
					result = "黑名单"
				}
				return result;
			}
		},{
			title : "类型",// 标题
			name : "usertype",
			//sort : "up",
			width : 160,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var usertypeTemp = data.usertype;
				var result = "-";
				if(1 == usertypeTemp){
					result = "普通会员";
				}else if(2 == usertypeTemp){
					result = "寻蜜客";
				}
				return result;
			}
		},{
			title : "注册来源",// 标题
			name : "regtype",
			//sort : "up",
			width : 160,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var regtypeTemp = data.regtype;
				var result = "-";
				if(1 == regtypeTemp){
					result = "旅游众筹网站";
				}else if(2 == regtypeTemp){
					result = "寻蜜鸟网站";
				}else if(3 == regtypeTemp){
					result = "400客服电话";
				}else if(4 == regtypeTemp){
					result = "旅游众筹安卓客户端";
				}else if(5 == regtypeTemp){
					result = "旅游众筹IOS客户端";
				}else if(6 == regtypeTemp){
					result = "寻蜜鸟安卓客户端";
				}else if(7 == regtypeTemp){
					result = "寻蜜鸟IOS客户端";
				}else if(8 == regtypeTemp){
					result = "商家安卓客户端";
				}else if(9 == regtypeTemp){
					result = "商家IOS客户端";
				}
				return result;
			}
		},{
			title : "注册手机型号",// 标题
			name : "model",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
		},
		{
			title : "注册手机品牌",// 标题
			name : "brand",
			//sort : "up",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},
		{
			title : "登录手机型号",// 标题
			name : "last_model",
			//sort : "up",
			width : 200,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},
		{
			title : "登录手机品牌",// 标题
			name : "last_brand",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "等级",// 标题
			name : "rankNo",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "关注数量",// 标题
			name : "concernNums",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "送出",// 标题
			name : "giveGiftsNums",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "钱包余额",// 标题
			name : "amount",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "分账余额",// 标题
			name : "balance",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "佣金余额",// 标题
			name : "commision",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "赠送余额",// 标题
			name : "zbalance",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "积分余额",// 标题
			name : "integral",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
		},
		{
			title : "钱包状态",// 标题
			name : "walletStatus",
			//sort : "up",
			width : 150,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				var statusTemp = data.walletStatus;
				var result = "-";
				if(1 == statusTemp){
					result = "正常";
				}else if(2 == statusTemp){
					result = "锁定";
				}else if(3 == statusTemp){
					result = "注销";
				}
				return result;
			}
		},
		{
			title : "所属商家",// 标题
			name : "genusname",
			//sort : "up",
			width : 200,// 宽度
			type:"string"//数据类型
		},
		{
			title : "注册区域",// 标题
			name : "cdenom",
			//sort : "up",
			width : 200,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
				return data.regcity + "-" + data.regarea;
			}
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["detail", "update", "statusUpdate", "resetPassW"],// 不需要选择checkbox处理的权限
			//width : 200,// 宽度
			// 当前列的中元素
			cols : [{
				title : "重置提现密码",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["uid"],
					permission : "resetPassW"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:resetPassW('+data.uid+', '+1+');">重置提现密码</a>';
				}
			},{
				title : "加入黑名单",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["uid"],
					permission : "statusUpdate"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:upstatus('+data.uid+', '+4+');">加入黑名单</a>';
				}
			},
			{
				title : "查看",// 标题
				linkInfoName : "modal",
				width : 20,
				linkInfo : {
					modal : {
						url : "member/memberList/details.jhtml",// url
						position:"60px",// 模态框显示位置
						width:"800px"
					},
					param : ["uid"],// 参数
					permission : "detail"// 列权限
				}
			},
			{
				title : "修改",// 标题
				linkInfoName : "modal",
				width : 20,
				linkInfo : {
					modal : {
						url : "member/memberList/update/init.jhtml",
						position : "200px",
						width : "800px"
					},
					param : ["uid"],
					permission : "update"
				}
			}] 
	}},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

//修改会员信息
function formSubmit(){
	//alert("update");
	var form =$("body").find(".modal-body form");
	var action = $(form).attr("action");
	var method = $(form).attr("method");
	$.ajax({
		url : action + '?t=' + Math.random(),
		type : method,
		data :jsonFromt($(form).serializeArray()),
		dataType : 'json',
		cache:false
	}).done(function ( data ) {
		$('#triggerModal').modal('hide');
		if(data.success){
			memberListDiv.reload();
		}
		showSmReslutWindow(data.success, data.msg);		
	});
	return false;
}

//会员加入黑名单
function upstatus(uid,status) {
	showSmConfirmWindow(function(){
		formAjax(uid,status);
	}, "确定加入黑名单？");		
}

function formAjax(uid,status){
//console.info(status);
	$.ajax({
		type : 'post',
		url : 'member/memberList/status/update.jhtml' + '?t=' + Math.random(),
		data : {
			'uid':uid,
			'status':status
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
        	showSmReslutWindow(data.success, data.msg);
        	setTimeout(function(){$("#searchForm").get(0).submit();}, 2000);
			
		}
	})
}

//重置提现密码
function resetPassW(uid, userType){
	showSmConfirmWindow(function(){
		resetPassWord(uid,userType);
	}, "重置后需要在APP中重新设置，是否确认？");		
}

function resetPassWord(uid, userType){
	$.ajax({
		type : 'post',
		url : 'businessman/seller/resetDepositPassW.jhtml' + '?t=' + Math.random(),
		data : {
			'sellerid':uid,
			'userType':userType
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
        	showSmReslutWindow(data.success, data.msg);
        	setTimeout(function(){$("#searchForm").get(0).submit();}, 2000);
			
		}
	});
}
