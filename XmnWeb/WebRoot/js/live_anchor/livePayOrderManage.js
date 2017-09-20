var livePayOrderList;
var initListUrl = "livePayOrder/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="livePayOrder/manage/init.jhtml" target="right">充值记录</a>',
			'userSpan', true);
	livePayOrderList = $("#livePayOrderList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#export").click(function(){
		var path="livePayOrder/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
});

function success(data, obj) {
	var formId = "shareForm", title = "充值列表", subtitle = "条充值记录";
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
 			queryPermission : [ "update"],// 不需要选择checkbox处理的权限
 			width : 120,// 宽度
 			// 当前列的中元素
 			cols : [ //推荐状态, recommendStatus:1 审核中 2 审核通过 3 审核被拒
 				 {
 					title : "通过",// 标题
 					linkInfoName : "href",
 					linkInfo : {
 						href : "livePayOrder/manage/update.jhtml",// url
 						param : ["id"],// 参数
 						permission : "update"// 列权限
 					},
 					customMethod : function(value, data){
 						var value = '';
 						if (data.objectOriented == 4){//营业厅会员
	 						var payment = data.payment;
	 						if (payment == '9000' || payment == '18000' || payment == '36000'|| payment == '108000')
	 						  value = "<a href='javascript: excitationProjectView("+data.id+")'>" + "编辑" + "</a>";
 						}
 				        return value;
 				    }
 				 }
 			]
 		},
		cols : [ {
			title : "订单编号",
			name : "orderNo",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return value;
			}
		}, {
			title : "用户手机号",
			name : "phone",
			type : "string",
			width : 150
		}, {
			title : "昵称",
			name : "nname",
			type : "string",
			width : 150
		},{
			title : "充值时间",
			name : "payTimeStr",
			type : "string",
			width : 150
		}, {
			title : "充值金额",
			name : "payment",
			type : "string",
			width : 150
		} , {
			title : "兑换鸟豆",
			name : "realCoin",
			type : "string",
			width : 150
		} , {
			title : "支付方式",
			name : "payTypeStr",
			type : "string",
			width : 150
		} , {
			title : "支付状态",
			name : "payStateStr",
			type : "string",
			width : 150
		} , {
			title : "充值渠道",
			name : "objectOrientedStr",
			type : "string",
			width : 150
		}]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "livePayOrder/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 livePayOrderList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 批量删除
  */
 $('#delete').click(function(){
 	var ids = livePayOrderList.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'livePayOrder/manage/delete.jhtml' + '?t=' + Math.random(),
 			data : 'ids=' + ids,
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					livePayOrderList.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 

 $("select[name='excitationProject']").on('change', function() {
	var excitationProject = $("select[name='excitationProject']").val();
	if (excitationProject == 'A' ) {
		$('input[name=curPeriodExcitation]').removeAttr("readonly");// 去除input元素的readonly属性
	} else {
		$("input[name='curPeriodExcitation']").val('');
		$("input[name='curPeriodExcitation']").attr("readonly", "true");// 去除input元素的readonly属性
	}
});

 /* 编辑奖励方案*/
 function excitationProjectView(id) {
	// 初始化值清空栏位
	$("#excitationProjectModal :input").each(function() {
		$(this).val("");
	});
	$("select[name='excitationProject']").val('');
	//加载数据 ?id='+id
	var url='livePayOrder/manage/list/viewDetail.jhtml?id='+id;
	$.ajax({
		type : 'post',
		url : url,
		data :[id, orderNo],
		dataType : 'json',
		success : viewSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#excitationProjectModal').modal('hide');
		}
	});
}


function viewSuccess(data) {
	if (data.data){//获取到数据
		$("#excitationProjectModal").find('input[name=id]').val(data.data.id);
		$("#excitationProjectModal").find('input[name=orderNo]').val(data.data.orderNo);  //订单编号
		
		$("#excitationProjectModal :input[name='nname']").val(data.data.nname);  //会员昵称
		$("#excitationProjectModal :input[name='phone']").val(data.data.phone);  //会员手机号码
		//会员等级
		var ledgerLevel = data.data.ledgerLevelDesc == undefined ? '' : data.data.ledgerLevelDesc ;
		
		var excitationProject = data.data.excitationProject == undefined ? '' : data.data.excitationProject;
		var periodExcitation = data.data.periodExcitation == undefined ? '' : data.data.periodExcitation;
		var curPeriodExcitation = data.data.curPeriodExcitation == undefined ? '' :data.data.curPeriodExcitation;
		if (excitationProject && excitationProject != ''){
			if (!ledgerLevel == ''){
				ledgerLevel +=  '-'+excitationProject+'返还模式';
			}else{
				ledgerLevel +=  excitationProject+'返还模式';
			}
			if (periodExcitation != null && curPeriodExcitation!= null)
				ledgerLevel += '-'+data.data.curPeriodExcitation +'期/'+data.data.periodExcitation+'期';
		}
		$("#excitationProjectModal :input[name='ledgerLevel']").val(ledgerLevel);  //会员手机号码
		$("input[name='curPeriodExcitation']").val(curPeriodExcitation == null ? '' : curPeriodExcitation);
		
		var excitationProject = data.data.excitationProject;
		$("select[name='excitationProject']").val(excitationProject);  //奖励方案
		
		if (excitationProject == undefined ||  excitationProject == ''){
			$('select[name=excitationProject]').removeAttr("disabled");// 去除input元素的readonly属性
			$('input[name=curPeriodExcitation]').removeAttr("readonly");// 去除input元素的readonly属性
		}else{
			$('select[name=excitationProject]').attr("disabled", "true");// 去除input元素的readonly属性
			$("input[name='curPeriodExcitation']").attr("readonly", "true");// 去除input元素的readonly属性
		}

	}
	
	//显示模态框数据
	$('#excitationProjectModal').modal();
	
	// 点击关闭遮罩层
	$(".close-shade").on("click", function() {
		$(".shade-box,.shade-content").hide();
	});
}

/*保存V客充值渠道奖励方案*/
$("#editExcitationProjectSubmit").on("click", function() {
	var id = $("#excitationProjectModal").find("[name=id]").val();
	var orderNo = $("#excitationProjectModal").find("[name=orderNo]").val();
	
	var excitationProject = $("#excitationProjectModal").find("[name=excitationProject]").val();  //返还模式
	var curPeriodExcitation = $("#excitationProjectModal").find("[name=curPeriodExcitation]").val();  //
	if (excitationProject == 'A'){
		if (curPeriodExcitation < 0 || curPeriodExcitation > 12) {
			showWarningWindow("warning", "请正确填写已返还期数!", 9999);
			return;
		}else{
			var reg = /^\d+$/;
			if (!reg.test(curPeriodExcitation)) {
				submitDataError($("#excitationProjectModal :input[name='curPeriodExcitation']"), "请输入整数数值!");
//				showWarningWindow('warning', "请输入整数数值！");
				return false;
			}
		}
	}else{
		if (curPeriodExcitation != '' ) {
			showWarningWindow("warning", "请清空返还期数!", 9999);
			return;
		}
	}
	var data = {
		"id" : id,
		"orderNo" : orderNo,
		"excitationProject" : excitationProject,
		"curPeriodExcitation" : curPeriodExcitation
	};
	
    //保存数据
	$.ajax({
		url : "livePayOrder/manage/updateExcitationProject.jhtml",
		type : "post",
		dataType : "json",
		data : data,
		success : function(result) {
			if (result.success) {
				showSmReslutWindow(result.success, result.msg);
				setTimeout(function() {
					pageInit();
				}, 1000);
			}
		}
	});
 
	//隐藏模态框
	$('#excitationProjectModal').modal('hide');
});