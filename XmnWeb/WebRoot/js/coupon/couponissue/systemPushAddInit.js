var searchChosen = undefined;
$(function() {
	if (!searchChosen) {
		searchChosen = $("#object").searchChosen({
			url : "user_terminal/appPush/init/choseMember/init.jhtml",
			separator : ","
		});
	}
	
	//初始化优惠券下拉框
	initCoupon();
	
	validate("addUsersForm", {
		rules : {
			sendNum:{
				required : true,
				digits:true,//只能输入整数
				range : [ 0, 1000]
			},
			userIds : {
				required : true
			}
		},
		messages : {
			userIds : {
				required : "不能为空!"
			}
		}
	}, formAjax);
	
	
});
function formAjax() {
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	var data = jsonFromt($('#addUsersForm').serializeArray());
//	console.log(data);
	if (!checkData(data.userIds, searchChosen.next(), "不能为空")) {
		return false;
	}
	showSmConfirmWindow(function() {
		var url;
		url = 'coupon/couponissue/systemPush/add.jhtml' + '?t='
				+ Math.random();
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if (data.success) {
					showSmReslutWindow(data.success, data.msg);
					setTimeout(function() {
						window.history.back();
					}, 1000);
				} else {
					showSmReslutWindow(data.success, data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	}, "请确认信息是否正确！");
}


/**
 * 初始化粉丝券下拉框
 */
function initCoupon(){
	var ctype=$("input[name='ctype']:checked").val();
//	console.log(ctype);
	$("#cid").chosenObject({
		hideValue : "value",
		showValue : "cname",
		filterVal : ctype,
		url : "coupon/couponissue/queryYouHuiQuan.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

/**
 * 绑定"优惠券类型"单击事件
 */
$("input[name='ctype']").on("change",function(){
	var $cid=$("#cid");
	var $cidDiv=$("#cidDiv").html("");
//	$('<select class="form-control" id="cid" name="cid"  initValue="" style="width:100%;"> </select>').appendTo($cidDiv);
	$cid.appendTo($cidDiv);
	initCoupon();
	$('#cid').trigger('chosen:updated');
});


/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	
	var cid=$("#cid").val();
	if(cid == null || cid==""){
		showWarningWindow("warning","请选择优惠券!",9999);
		rsult=false;
		return rsult;
	}
	
	return result;
}