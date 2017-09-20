$(document).ready(function() {
	if( $('#isType').val() == "add"){
		inserTitle(' > 用户端管理 > <a href="user_terminal/register_gift/init.jhtml" target="right">注册礼物管理</a> > <a href="user_terminal/register_gift/add/init.jhtml" target="right">新增礼物</a>','userSpan',true);
	}
	if( $('#isType').val() == "update"){
		inserTitle(' > 用户端管理 > <a href="user_terminal/register_gift/init.jhtml" target="right">注册礼物管理</a> > <a href="user_terminal/register_gift/update/init.jhtml?id='+ $("#id").val() +'" target="right">编辑礼物</a>','userSpan',true);
	}

	changeGiftType();
	validateSubmit();
	
	// 返回
	$("#backId").on("click", function() {
		muBack();
	});
});

/**
 * 验证及提交消费优惠劵表单
 */
function validateSubmit() {
	validate("editGiftForm", {
		rules : {
			'quota' : {
				required : true
			},
			'coupon.denomination' : {
				required : true,
				digits : true
			},
			'coupon.condition' : {
				required : true,
				gt : 0,
				max : 99999
			},
			'coupon.dayNum' : {
				required : true,
				integer : true,
				gt : 0,
				max : 99999
			},
			'num' : {
				required : true,
				integer : true
			}
		},
	}, function() {
		var data = jsonFromt($('#editGiftForm').serializeArray());
		var url;
		if($('#isType').val()=="add"){
			url = "user_terminal/register_gift/add.jhtml";
		}else{
			url = "user_terminal/register_gift/update.jhtml";
		}
		
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				// 添加成功后跳转到列表页面
				var url = contextPath + '/user_terminal/register_gift/init.jhtml';
				setTimeout(function() {
					location.href = url;
				}, 1000);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	});
}

function changeGiftType(){
	console.info("changeGiftType");
	var type = $('#giftType').val();
	console.info(type);
	if(type==2){
		$('.integral').hide();
		$('.coupon').show();
		if($("input[name='conditionRadio']:checked").val() == 0){
			console.info("conditionInputReadOnly");
			conditionInputReadOnly();
		}
	}else{
		$('.integral').show();
		$('.coupon').hide();
	}
}
/**
 * 有效日期,选中：自定义天数
 */
function swichtimeYes() {
	 console.log("有效日期,选择自定义天数");

}
function conditionNo(){
	 console.log("有效日期,选择自定义天数2");
}
/**
 * 消费优惠劵--使用条件变为不可编辑
 */
function conditionInputReadOnly() {
	$("input[name='coupon.condition']").val(0).attr("value", 0)
			.attr("readOnly", true);
}

/**
 * 消费优惠劵--使用条件变为可编辑
 */
function conditionInputRead() {
	$("input[name='coupon.condition']").attr("readOnly", false);
}

function isCoupon() {
	console.log("isCoupon");
	if($('#giftType').val==2){
		return true;
	}else{
		return false;
	}
}
function isIntegral() {
	console.log("isIntegral");
	if($('#giftType').val==1){
		return true;
	}else{
		return false;
	}
}
function muBack() {
	var url = contextPath + '/user_terminal/register_gift/init.jhtml';
	location.href = url;
}