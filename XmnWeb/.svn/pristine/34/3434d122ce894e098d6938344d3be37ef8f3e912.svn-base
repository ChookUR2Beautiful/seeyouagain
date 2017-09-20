var pageDiv;
$(document).ready(function() {

	pageDiv = $('#redPackageDiv').page({
		url : 'businessman/redPackage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
		paramForm : 'redPackageForm'
	});
	
	inserTitle(' > 商户会员红包 > <a href="businessman/redPackage/init.jhtml" target="right">所有红包</a>','redPackageDiv',true);
	
	$("input[data-bus=reset]").click(function(){
		$(".form-control").attr("value","");
		$("#ld").find("select").trigger("chosen:updated");
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView: 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd'
	});


});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			// 数据
			data:data.content, 
			 // 数据行
			cols:[{
					title : "活动商户",// 标题
					name : "sellerName",
					// sort : "up",
					width : 200,// 宽度
					leng : 200,// 显示长度
					type:"string",// 数据类型
			},{
				title : "活动名称",// 标题
				name : "redpacketName",
				width : 250,// 宽度
				type:"string"// 数据类型
				
		},{
			title : "红包类型",// 标题
			name : "redpacketType",
			// sort : "up",
			width : 160,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
					return "分享引流红包";
				}else if(value == 1){
					return "限时到店红包";
				}else if(value == 2){
					return "消费满赠红包";
				}else if(value ==3){
					return "推荐消费红包";
				}else if(value == 4){
					return "普通抽奖红包";
				}else{
					return "-";
				}
			}
			
		},
		{
			title : "状态",// 标题
			name : "status",
			// sort : "up",
			width : 100,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				if(value == 0){
						return "已结束";
					}else if(value == 1){
						return "已激活";
					}else if(value == 2){
						return "活动占用";
					}else if(value ==3){
						return "进行中";
					}else{
						return "未支付";
					}
				}
		},{
			title : "红包总金额",// 标题
			name : "totalAmount",
			// sort : "up",
			width : 120,// 宽度
			type:"string"// 数据类型
		},{
			title : "已领取个数",// 标题
			name : "getRedpacketNumber",
			width : 120,// 宽度
			type:"number"// 数据类型
		},{
			title : "已领取金额",// 标题
			name : "getRedpacket",
			width : 120,// 宽度
			type:"string"// 数据类型
			
		},{
			title : "剩余金额",// 标题
			name : "leftRedpacket",
			// sort : "up",
			width : 120,// 宽度
			type:"stirng"// 数据类型
			
		},{
			title : "绑定会员",// 标题
			name : "lockVip",
			// sort : "up",
			width : 120,// 宽度
			type:"string"// 数据类型
			
		},{
			title : "消费会员",// 标题
			name : "totalVip",
			width : 120,// 宽度
			leng : 8,// 显示长度
			type:"number"// 数据类型
			
		},
		{
			title : "有效日期",// 标题
			name : "beginDate",
			// sort : "up",
			width : 250,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				return data.beginDateStr+"至"+data.endDateStr;
			}
			
		}],
			// 操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["list","update"],// 不需要选择checkbox处理的权限
				width : 200,// 宽度
				// 当前列的中元素
				cols : [{
					title : "终止",// 标题
					linkInfoName : "href",
					linkInfo : {
						permission : "update"// 列权限
				    },
				    customMethod : function(value, data){
                        if(data.status == 3){
                            var value1 = "<a href='javascript:shutDown(\""+data.id+"\")'>" + "终止活动" + "</a>";
                            return value1;
                        }else{
                        	return "";
                        }
                    }
			      },{
						title : "领取明细",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "businessman/redPackage/init/detail/init.jhtml",// url
							param : ["id"], // 参数
							permission : "list"// 列权限
						}
					}
					]
	}},permissions);
}

/**
 * 终止进行中的红包活动
 */
 function shutDown(id){
	 showSmConfirmWindow(function(){
		 $.ajax({
 			type : 'post',
 			url : 'businessman/redPackage/update.jhtml' + '?t=' + Math.random(),
 			data :{'id':id},
 			dataType : 'json',
 			success : function(data){
 				if (data.success) {
						setTimeout(function(){
							pageDiv.reload();
		        		}, 1000);
				    }			
					showSmReslutWindow(data.success, data.msg);
 			},
 			error : function() {
 				window.messager.warning(data.msg);
 			}
 		});
	 },"确定终止吗？")
}
 
/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}
