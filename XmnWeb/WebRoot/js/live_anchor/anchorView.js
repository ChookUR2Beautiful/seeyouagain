var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			nickname : {
				required : true,
				nameRule : [1,12]
			},
			name :{
				required :true,
				nameRule : [1,12]
			},
			phone : {
				required : true,
				remote:{
					type : 'post',
					url : 'anchor/manage/checkAccount.jhtml' + '?t=' + Math.random(),
					dataType : 'json',
					data:{
						account: function() {
				            return $("#phone").val();
				        }
					}
				},
				phoneRule : true,
				isConflict:true
			},
			avatar : {
				required : true
			},
			idcard : {
				required : true
			},
			weixin : {
				required : true
			},
			birthday : {
				required : true
			},
			ledgerRatio : {
				required : true,
				range : [ 0, 1]
			},
			saleCouponRatio : {
				required : true,
				range : [ 0, 1]
			},
			useCouponRation : {
				required : true,
				range : [ 0, 1]
			},
			introduceMv : {
				required : true,
				maxlength : 200
			},
			sortVal : {
				required : true,
				digits:true,//只能输入整数
				range : [ 0, 999]
			}
		},
		messages:{
			nickname :{
				required : "昵称不能为空！",
				nameRule: "请输入1-12位字母,数字,下划线,中文(中文占两位)"
			},
			name :{
				required : "真实姓名不能为空！",
				nameRule: "请输入1-12位字母,数字,下划线,中文(中文占两位)"
			},
			phone : {
				required : "手机号码不能为空！",
				remote : "该手机号已被注册！",
				phoneRule : "请输入正确的手机号！"
			},
			ledgerRatio:{
				required:"送礼分成不能为空!",
				range:"分账比例取值为0-1之间"
			},
			saleCouponRatio:{
				required:"售出粉丝券分成不能为空!",
				range:"分账比例取值为0-1之间"
			},
			useCouponRation:{
				required:"用户使用粉丝券分成不能为空!",
				range:"分账比例取值为0-1之间"
			},
			introduceMv : {
				required : "主播介绍视频不能为空!",
				maxlength : "最多输入200个字符"
			},
			sortVal : {
				required : "主播排序不能为空",
				digits:"只能输入整数",//只能输入整数
				range : "请输入1-999之间的整数"
			}
		}
	}, anchorSave);
	
	//初始化主播等级下拉框
	liveLevelIdInit();
	
	//初始化签约类型
	signTypeInit();
	
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
	
	//设置截止时间
	$('.form_datetime').datetimepicker("setEndDate", new Date());
	
	// 头像
	$("#avatarImg").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
	// 微信群二维码图片
	$("#wechatPicImg").uploadImg({
		urlId : "wechatPic",
		showImg : $('#wechatPic').val()
	});
	
	var isUpdate = $("#id").val() == null || $("#id").val() =="" ? false : true;
	if(isUpdate){
		$('input[name=password]').val("000000");
		$('input[name=password]').attr("readonly", true);
		$('#phone').attr('disabled',true);
	}
	
	
	//修改手机号
	var chagneAccount = true;
	
	$("#phone").change(function(){
		initAccount();
		chagneAccount = true;
	});
	
	/*
	 * 绑定现有会员账号，
	 */
	$(".binding").click(function() {
		$('#sm_confirm_window').modal('hide');
		updateAccountText(true);
	});
	/*
	 * 不绑定现有会员账号 
	 */
	$(".unbinding").click(function() {
		$('#sm_confirm_window').modal('hide');
		updateAccountText(false);
	});
	
	/**
	 * 取消设为主播
	 */
	$(".viewer_cancel").click(function(){
		$('#viewer_confirm_window').modal('hide');
		updateViewerText(false);
	});
	
	/**
	 * 设为主播
	 */
	$(".viewer_confirm").click(function() {
		$('#viewer_confirm_window').modal('hide');
		updateViewerText(true);
	});
	
	/**
	 * 判断该手机号是否已注册寻蜜鸟会员账号，有则进行绑定
	 */
	$.validator.addMethod("isConflict", function(value, element) {
		
		
		if(!chagneAccount){
			return true;
		}
	    // 设置同步
	    $.ajaxSetup({
	        async: false
	    });
	    var param = {
	    	phone: value
	    };
	    $.post("anchor/manage/init/isConflict.jhtml", param, function(data){
	        if(data.role=="1"){
	        	updateAccountText(false);
	        	chagneAccount = false;
	        	//寻蜜鸟会员账号,模态框信息
	        	$('#usr_nname').text(data.liverInfo.nickname);//会员昵称
	        	$('#phoneNum').text(data.liverInfo.phone);//会员手机号
	        	
	        	//绑定主播模态框信息
	        	bindAnchorInfo(data);
	        	$('#sm_confirm_window').modal();
	        }else if(data.role=="2"){
	        	updateViewerText(false);
	        	chagneAccount = false;
	        	//寻蜜鸟直播观众账号,模态框信息
	        	$('#viewer_usr_nname').text(data.liverInfo.nickname);//会员昵称
	        	$('#viewer_phoneNum').text(data.liverInfo.phone);//会员手机号
	        	
	        	//绑定主播模态框信息
	        	bindAnchorInfo(data);
	        	$('#viewer_confirm_window').modal();
	        }
	    });
	    // 恢复异步
	    $.ajaxSetup({
	        async: true
	    });
	    return true;    
	}, "该帐号已存在寻蜜鸟会员");
	
			
});

/**
 * 主播手机号校验(只做长度校验)
 */
$.validator.addMethod("phoneRule", function(value, element) {
	var length = value.length;
	var phone = /^[0-9]{11}$/;
	return this.optional(element) || (length == 11 && phone.test(value));
}, "请输入正确的手机号！");

/**
 * 主播昵称长度校验,可包括字母，数字，下划线，中文(最多12个字节，一个中文占两个字节)
 */
$.validator.addMethod("nameRule", function(value, element ,param) {
//	debugger;
	var length = value.length;
	var name = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
	for(var i = 0; i < value.length; i++){
		if(value.charCodeAt(i) > 127){
			length++;
		}
	}
	return this.optional(element) || ( length >= param[0] && length <= param[1] && name.test(value) );
}, "请输入字母，数字，下划线，中文(最多12位，中文占两位)！");


/*
 * 初始化绑定提示信息，默认是不冲突的
 */
function initAccount(){
	$('#conflictMsg').text("");
	$('#isBinding').val("N");
	$('#ensure').attr("disabled",false);
	$('.conflictTip').hide();
	
	$('.viewer_conflictTip').hide();
}

/*
 * 更新绑定提示信息
 */
function updateAccountText(isBinding){
//	debugger;
	if(isBinding){
		$('#isBinding').val("Y");
		$('#conflictMsg').text("手机号已注册寻蜜鸟账号，已绑定！(保存后生效)");
		$('#password').val("......");//密码占位符，显示用，无实际意义
		$('#password').attr("readonly","readonly");
		$('#ensure').attr("disabled",false);
	}else{
		$('#isBinding').val("N");
		$('#conflictMsg').text("手机号已注册寻蜜鸟账号，请绑定后保存!");
		$('#ensure').attr("disabled",true);
	}
	$('.conflictTip').show();
}

/*
 * 更新设为主播提示信息
 */
function updateViewerText(isConfirm){
//	debugger;
	if(isConfirm){
		$('#viewer_conflictMsg').text("手机号已注册直播观众账号，已设为主播！(保存后生效)");
		$('#password').val("......");//密码占位符，显示用，无实际意义
		$('#password').attr("readonly","readonly");
		$('#ensure').attr("disabled",false);
	}else{
		$('#viewer_conflictMsg').text("手机号已注册直播观众账号，请设为主播后保存!");
		$('#ensure').attr("disabled",true);
	}
	$('.viewer_conflictTip').show();
}

/**
 * 保存主播信息
 */
function anchorSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var anchorId=$("#id").val();
	var isAdd = anchorId ==null || anchorId =="" ? true : false;
	if (isAdd) {// 添加操作
		url = "anchor/manage/add" + suffix;
	} else {// 修改操作
		url = "anchor/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	//将图片数组转化为字符串
	convertData();
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					/*if (isAdd) {
						anchorList.reset();
					} else {
						anchorList.reload();
					}*/
					var url = contextPath +'/anchor/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 绑定主播信息
 * @param data
 */
function bindAnchorInfo(data){
//	debugger;
	// 0 无寻蜜鸟会员和直播观众信息  1 存在寻蜜鸟会员信息，不存在直播观众信息  2存在会员和直播观众信息
	if(data.role=="1"){
		$("#uid").val(data.liverInfo.uid);
	}else if(data.role=="2"){
		$("#id").val(data.viewer.id);
		$("#uid").val(data.viewer.uid);
		$("#utype").val("1");
		$("#changeToAnchor").val("Y");
	}
	
	$('#nickname').val(data.liverInfo.nickname);//绑定主播昵称
	
	$('#name').val(data.liverInfo.name);//绑定主播真实姓名 
	
	//绑定主播性别
	if(data.liverInfo.sex==1){
		$('input[name=sex]:eq(0)').attr("checked","checked");
	}else{
		$('input[name=sex]:eq(1)').attr("checked","checked");
	}
	
	$('#avatar').val(data.liverInfo.avatar);//绑定主播头像
	$("#avatarImg").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
	$('#password').val("......");//设置显示密码
	$('#password').attr("readonly","readonly");
	
	$('#idcard').val(data.liverInfo.idcard);//绑定主播身份证号
//	$('#idcard').attr("readonly","readonly");
	
	$('#birthday').val(data.liverInfo.birthday);//出生日期
	$('#birthday').attr("readonly","readonly");
	
	$('#bursPassword').val(data.liverInfo.password);//绑定会员密码
}

/**
 * 初始化主播等级下拉框
 */
function liveLevelIdInit(){
	$("#levelId").chosenObject({
		hideValue : "id",
		showValue : "levelName",
		url : "liveLevel/manage/initAnchorLevelId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
}

//选择主播等级后，修改主播送礼分成的值
$('body').on("click",'#levelId_chosen .chosen-results li',function(){
//	debugger;
	var levelId =  $("#levelId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "anchor/manage/update/init/getAnchorLevelInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":levelId},
		success : function(data){
			if(data != null){
				console.log(new Date()+"  data="+data.giftAllot);
				if(data.giftAllot){
					var ledgerRatio=parseFloat(data.giftAllot/100).toFixed(4);
					$("#ledgerRatio").val(ledgerRatio);
				}
				
			}
		}
	});
});

/*
 * 绑定签约类型change事件
 * 
 */
$("#signType").change(function(){
	signTypeInit();
});

/**
 * 签约类型初始化,0 兼职主播，1 签约主播，2测试账号
 */
function signTypeInit(){
	var signType=$("#signType").val();
	if(signType==0){
		$("#rootRole").css("display","inline");
	}else{
		$("#rootRole").css("display","none");
		$("#rootRole").find("option[value='0']").attr("selected",true);
	}
}


/**
 * 绑定添加相册click事件 
 */
$("#addAnchorImage").click(function(){
	var modalTrigger = new ModalTrigger({
		title:'添加主播相册',
		type : 'ajax',
		url : 'anchor/manage/anchorImageAddBatch/init.jhtml' ,
		width:'1000px',
		toggle : 'modal'
	});
	modalTrigger.show();
});

//删除图片
$("#datas .img-list").on("click","em",function(){
	$(this).parent().remove();
});

/**
 * 图片数组信息转化为字符串数据
 */
function convertData(){
	var picUrls=[];
	$("#datas .img-list img").each(function(index){
		var srcTemp=$(this).attr("src");
		srcTemp=srcTemp.replace(imgRoot,"");
		picUrls.push(srcTemp);
	});
	
	if(picUrls.length>0){
		$("#datas .pic-url-list").val(picUrls.join(";"));
	}
}
