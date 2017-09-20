var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var jsonTextInit;
var anchorImageChooser;
var tagIds=[];
$(function() {
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			zhiboTitle : {
				required : true
			},
			zhiboAddress : {
				required : true
			},
			sequenceNo  :{
				required :true,
				digits:true,
				range:[1,999]
			},
			zhiboPlaybackUrl :{
				required: true
			},
			telphones :{
				required:true,
				telRule :true
			},
			orderNum :{
				required :true,
				digits:true,
				range:[1,20]
			},
			customShareTitle:{
				required:true,
				maxlength:255
			},
			customShareDesc:{
				required:true,
				maxlength:255
			}
		},
		messages:{
			store:{
				sequenceNo:"请输入直播推荐排序",
				digits:"排序必须为数字类型",
				range:"推荐排序须设定为1-999之间的整数"
			},
			zhiboPlaybackUrl :{
				required :"请填写回放地址"
			},
			telphones :{
				required: "请填写手机号码",
				telRule : "多个手机号以英文逗号分隔"
			},
			orderNum:{
				required:"请输入接单人数",
				digits:"接单人数必须为数字类型",
				range:"接单人数须设定为1-20之间的整数"
			},
		}
	}, liveRecordSave);
	
	
	//初始化日期控件
	initDate();
	
	//初始化主播下拉框
	initAnchorId();
	
	//初始化商家下拉框
	initSellerid();
	
	//指定观众手机号码初始化
	telphonesInit();
	
	//直播类型初始化
	liveTopicInit();
	
	//初始化直播标签分类
	liveRecordClassifyIdInit();
	
	//初始化已保存标签数组
	initTagsId();
	
	
	//编辑控制
	editControl();
	
	//是否自定义分享描述
	initCustomShareSet();
	
	//是否配置机器人
	initRobotSet(0);
	
});


/**
 * 保存通告信息
 */
function liveRecordSave() {
	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveRecord/manage/addOrderRecord" + suffix;
	} else {// 修改操作
		url = "liveRecord/manage/updateOrderRecord" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	//将标签及图片数组转化为字符串
	convertData();
	
	var result=validateCustomer(isAdd);
	if(!result){//自定义校验不通过
		return ;
	}
	
	
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
					var targetUrl=$("#targetUrl").val();
					console.log(targetUrl);
					var url = contextPath +'/'+targetUrl;
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
 * 将标签，图片数组信息转化为字符串数据
 */
function convertData(){
	
	if(tagIds.length>0){
		$("#tagIds").val(tagIds.join(";"));
	}
	
	
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


/**
 * 初始化直播标签数组
 */
function initTagsId(){
	var tagIdVals=$("#tagIds").val();
	if(tagIdVals){
		tagIds=tagIdVals.split(";");
//		console.log("tagIds="+tagIds);
	}
	
}


//初始化主播下拉框
function initAnchorId(){
//	debugger;
	$("#anchorId_").chosenObject({
		hideValue : "id",
		showValue : "nickname",
		url : "anchor/manage/initAnchorId.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//选择主播后，修改主播昵称隐藏域的值
$('body').on("click",'#anchorId__chosen .chosen-results li',function(){
//	debugger;
	var anchorId =  $("#anchorId_").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "anchor/manage/getAnchorById.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":anchorId},
		success : function(data){
			if(data != null){
				/*$("#uid").val(data.uid);*/
				$("#nname").val(data.nickname);
				$("#sex").val(data.sex);
				
			}
		}
	});
});


//初始化商家下拉框
function initSellerid(){
	$("#sellerid").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/getSellerIdAndName.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//选择商家后，修改商家名称 (to fix 编辑无法触发,暂时后台同步数据)
$('body').on("click",'#sellerid_chosen .chosen-results li',function(){
//	debugger;
	var sellerid =  $("#sellerid").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "businessman/seller/getSellerLandmarkInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"sellerid":sellerid},
		success : function(data){
			if(data != null){
				$("#sellername").val(data.sellername);
				$("#sellerAlias").val(data.sellername);
				$("#longitude").val(data.longitude);
				$("#latitude").val(data.latitude);
				$("#zhiboAddress").val(data.address);
			}
		}
	});
});



/**
 * 绑定"是否指定观众"单击事件
 */
$("input[name='isAppoint']").on("change",function(){
	telphonesInit();
});

/**
 * 指定观众手机号码显示初始化
 */
function telphonesInit(){
	var isAppoint = $("input[name='isAppoint']:checked").val();
	if (isAppoint == 1) {
		$("#telphonesDiv").css("display","block");
	} else {
		$("#telphonesDiv").css("display","none");
		$("#telphones").val('');
	}
}

/**
 * 绑定"直播类型,1商家、2活动"change事件
 */
$("input[name='liveTopic']").on("change",function(){
	liveTopicInit();
});

/**
 * 商家别名 / 活动主题初始化
 * 
 */
function liveTopicInit(){
	var liveTopic = $("input[name='liveTopic']:checked").val();
	if (liveTopic == 1) {
		$("#sellerDiv").css("display","block");
		$("#sellerAliasLabel").text("商家别名：");
	} else {
		$("#sellerDiv").css("display","none");
		$("#sellerAliasLabel").text("活动主题：");
	}
}

/**
 * 初始化直播标签分类
 */
function liveRecordClassifyIdInit(){
	$("#classifyId").chosenObject({
		hideValue : "id",
		showValue : "classifyName",
		url : "businessman/classify/liveRecordClassifyInit.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"40%"
	});
}

//选择分类后，初始化标签
$('body').on("click",'#classifyId_chosen .chosen-results li',function(){
	//初始化联动标签
	liveRecordTagInit();
});

/**
 * 初始化直播标签
 */
function liveRecordTagInit(){
	var classifyId=$("#classifyId").val();
	$("#tagId_chosen,#tagId").remove();
	$("#addTagBtn").before('<select class="form-control" id="tagId" style="width:45%;"></select>');
	$("#tagId").chosenObject({
		hideValue : "id",
		showValue : "tagName",
		url : "businessman/classify/liveRecordTagInit.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		filterVal:classifyId,////过滤的值 (classifyId=1)
		isHistorical:false,//是否使用历史已加载数据
		width:"40%"
	});
	
	// 当原始select中的选项发生变化时通知chosen更新选项列表
	$('#tagId').trigger('chosen:updated');
}


/**
 * 自定义校验方法
 */
function validateCustomer(isAdd){
//	debugger;
	var result=true;
	var anchorId=$("#anchorId_").val()||$("#anchorId_").attr("initValue");
	
	if(!isAdd){
		if(anchorId == null || anchorId==""){
			showWarningWindow("warning","请选择主播!",9999);
			result=false;
			return ;
		}
	}
	
	
	var planStartDate=$("#planStartDate").val();
	if(planStartDate == null || planStartDate==""){
		showWarningWindow("warning","请选择直播计划开始时间!",9999);
		result=false;
		return ;
	}
	
	var planEndDate=$("#planEndDate").val();
	if(planEndDate == null || planEndDate==""){
		showWarningWindow("warning","请选择直播计划结束时间!",9999);
		result=false;
		return ;
	}
	
	var liveTopic=$("input[name='liveTopic']:checked").val();
	if(liveTopic==1){
		var sellerid=$("#sellerid").val()||$("#sellerid").attr("initValue");
		if(sellerid == null || sellerid==""){
			showWarningWindow("warning","请选择商铺!",9999);
			result=false;
			return ;
		}
	}
	
	if(!isAdd){
		var zhiboCover=$("#datas .pic-url-list").val();
		if(zhiboCover == null || zhiboCover==""){
			showWarningWindow("warning","请上传封面!",9999);
			result=false;
			return ;
		}
	}
	
	//校验机器人设置输入值
	var isRobotInit=$("input[name='isRobotInit']:checked").val();
	if(isRobotInit==1){
		var reg1 = new RegExp("^\\d+$");
		var robotSetMixNums=$("input[name='robotSetMixNums']").val();
		var robotSetMaxNums =$("input[name='robotSetMaxNums']").val();
		
		if(!(undefined ==robotSetMixNums || reg1.test(robotSetMixNums))){ 
			submitDataError("input[name='robotSetMixNums']","随机增加机器人最小值需为大于等于0的整数");
			result=false;
			return false;
		}
		var compare=parseFloat(robotSetMaxNums)>=parseFloat(robotSetMixNums);
		if(!(undefined ==robotSetMaxNums || (reg1.test(robotSetMaxNums)&&compare ))){ 
			submitDataError("input[name='robotSetMaxNums']","随机增加机器人最大值必须大于等于最小值");
			result=false;
			return false;
		}
		
		var robotMinNums=$("input[name='robotMinNums']").val();//初始机器人
		var robotMaxNums =$("input[name='robotMaxNums']").val();//最高上限
		
		if(!(undefined ==robotMinNums || reg1.test(robotMinNums))){ 
			submitDataError("input[name='robotMinNums']","初始机器人需为大于等于0的整数");
			result=false;
			return false;
		}
		var compare=parseFloat(robotMaxNums)>=parseFloat(robotMinNums);
		if(!(undefined ==robotMaxNums || (reg1.test(robotMaxNums)&&compare ))){ 
			submitDataError("input[name='robotMaxNums']","最高上限机器人必须大于等于初始机器人");
			result=false;
			return false;
		}
		
		var multiple =$("input[name='multiple']").val();//机器人显示倍数
		var rangeResult=multiple>=1 && multiple<=100000;
		if(!(reg1.test(multiple) && rangeResult )){ 
			submitDataError("input[name='multiple']","机器人显示倍数需为1-100000之间的整数");
			result=false;
			return false;
		}
		
	}
	
	
	return result;
}

/**
 * 编辑控制，1,3,5 状态的通告只允许修改直播推荐排序
 * 直播类型 -1 初始 0 预告 1 正在直播  2暂停直播 3 回放  4 历史通告 5 结束直播
 */
function editControl(){
//	debugger;
	var zhiboType=$("#zhiboTypeEdit").val();
	var setSellerSequNo=$("#setSellerSequNo").val();
	if(zhiboType==1||zhiboType==3||zhiboType==4||zhiboType==5){
		$("#zhoboTitle").attr("disabled",true);
		$("#planStartDate").attr("disabled",true);
		$("#planEndDate").attr("disabled",true);
		$("#zhiboAddress").attr("disabled",true);
		
		$("#anchorDiv").css("display","none");
		$("#liveTopicDiv").css("display","none");
		$("#sellerDiv").css("display","none");
//		$("#zhiboCoverDiv").css("display","none");
		$("#sequenceNo").attr("disabled",false);
		$("#liveRecordTagDiv").css("display","none");
		if(setSellerSequNo=='Y'){
			$("#sellerSequNoDiv").css("display","block");
			$("#sequenceNoDiv").css("display","none");
		}
		
		//隐藏直播券信息
		$("input[name='haveCoupon']").attr("disabled",true);
		$(".on-off").css('display','none');
	}
	
	//3,4,5状态的通告才可编辑回放地址
	if(zhiboType==3||zhiboType==4||zhiboType==5){
		$("#zhiboPlaybackUrlDiv").css("display","block");
	}
	
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if(!isAdd){
		$("#passwordDiv").css("display","none");
	}
}


/**
 * 手机号校验(只做长度校验)
 */
$.validator.addMethod("telRule", function(value, element) {
	var phone =  /^\d{11}(,\d{11})*$/;//多个手机号已逗号分隔
	return this.optional(element) || (phone.test(value));
}, "请输入正确的手机号！");



/**
 * 绑定"上传封面图"单击事件
 */
$(".upload-btn").on("click",function(){
	if($(this).next().find("li").length>=5){
		showWarningWindow("warning","最多添加5张图片!",9999);
		return false;
	}
	$("#datas .active").removeClass("active");
	$(this).next().addClass("active");
	var addImgId="zhiboCover";
	var anchorId=$("#anchorId_").val();
	if(anchorId==undefined || anchorId==""){
		showWarningWindow("warning","请选择主播!",9999);
	}else{
		anchorImageChooser = new ModalTrigger({
 			title:'主播相册',
			type : 'ajax',
			width:'800px',
			position:'10px',//距顶部的偏移
			url : 'anchorBusiness/manage/anchorImage/anchorImageChooser.jhtml?id=' + anchorId+"&addImgId="+addImgId ,
			toggle : 'modal'
		});
		anchorImageChooser.show();
	}
});

//删除图片
$("#datas .img-list").on("click","em",function(){
	$(this).parent().remove();
});


/**
 * 绑定确认添加标签按钮click事件
 */

$("#addTagBtn").on("click",function(){
	var tagText=$("#tagId_chosen").find(".chosen-single span").text();
	var tagId=$("#tagId").val();
	if(tagId){
		if(tagIds.length<2){
			for(var i=0;i<tagIds.length;i++){
				if(tagId==tagIds[i]){
					showWarningWindow("warning","该标签已添加!",9999);
					return false;
				}
			}
			tagIds.push(tagId);
			$("#box").append("<span name='"+tagId+"'>"+tagText+"<em class='icon-remove'></em></span>");
		}else{
			showWarningWindow("warning","最多添加2个标签!",9999);
		}
	}else{
		showWarningWindow("warning","请选择标签!",9999);
	}
	
});

//删除标签
$("#box").on("click","em",function(){
	for(var i=0;i<tagIds.length;i++){
		if($(this).parent().attr("name")==tagIds[i]){
			tagIds.splice($.inArray(tagIds[i], tagIds), 1);
		}
	}
	$(this).parent().remove();
});


//初始化日期控件
function initDate(){
	
	
	$('input[name="planStartDate"]').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
		endDate: $("input[name='planEndDate']").val()
	}).on("changeDate",function() {
			$("input[name='planEndDate']").datetimepicker("setStartDate",$("input[name='planStartDate']").val());
		});
	
	$('input[name="planEndDate').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate: $("input[name='planStartDate']").val()
	}).on( "changeDate", function() {
				$("input[name='planStartDate']").datetimepicker("setEndDate", $("input[name='planEndDate']").val());
			});
	
	$('.form_datetime').datetimepicker({
		weekStart : 0,
		todayBtn : 0,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 0,
		minuteStep :30,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii',
		startDate : new Date(),
	});
	
};

//删除标签
$("#datas").on("click","em.remove-tag",function(){
	var tagId=$(this).parent().attr("name");
	var spans=$(this).parent().parent().next().find("span");
	for(var i=0;i<spans.length;i++){
		if(spans.eq(i).text()==tagId){
			spans.eq(i).remove();
		}
	}
	$(this).parent().remove();
});


/**
 * 是否自定义分享描述设置
 */
function initCustomShareSet(){
//	debugger;
	var customShare=$("input[name='isCustomShare']:checked").val();
	if(customShare==undefined){
//		customShare=1;//未设置，默认不提供
		$("input[name='isCustomShare']")[1].checked=true;
	}
	customShareChange();
}

/**
 * 绑定"是否自定义分享描述"单击事件
 */
$("input[name='isCustomShare']").on("change",function(){
	customShareChange();
});

/**
 * 是否自定义分享描述初始化
 */
function customShareChange(){
	var isCustomShare = $("input[name='isCustomShare']:checked").val();
	if (isCustomShare == 1) {
		$("#customShareTitleInfo").css("display","block");
		$("#customShareDescInfo").css("display","block");
	} else {
		$("#customShareTitleInfo").css("display","none");
		$("#customShareDescInfo").css("display","none");
//		$("input[name='customShareTitle']").val('');
//		$("input[name='customShareDesc']").val('');
	}
}

/**
 * 初始化机器人配置
 */
function initRobotSet(index){
//	debugger;
	var isRobotInit=$("input[name='isRobotInit']:checked").val();
	if(isRobotInit==undefined){
		$("input[name='isRobotInit']")[1].checked=true;
	}else{
		robotConfChange(0);
	}
}

/**
 * 绑定"是否配置机器人"单击事件
 */
$("input[name='isRobotInit']").on("change",function(){
	robotConfChange(0);
});

/**
 * 机器人配置change事件
 * @param index
 */
function robotConfChange(index){
	debugger;
	var isRobotInit = $("input[name='isRobotInit']:checked").val();
	if (isRobotInit == 1) {  //显示
		$("[name='robotRangeDiv']").css("display","block");
		$("[name='robotConfDiv']").css("display","block");
		$("[name='robotMultipleDiv']").css("display","block");
		
		
	} else { //隐藏
		$("[name='robotRangeDiv']").css("display","none");
		$("[name='robotConfDiv']").css("display","none");
		$("[name='robotMultipleDiv']").css("display","none");
		
		$("[name='robotSetMixNums']").val(0);//单次最少新增机器人数量
		$("[name='robotSetMaxNums']").val(0);//单次最少新增机器人数量
		$("[name='robotMinNums']").val(0);//初始机器人
		$("[name='robotMaxNums']").val(0);//最高上限机器人
		$("[name='multiple']").val(1);//显示倍数
		
	}
}

/**
 * 机器人设置失去焦点，校验方法
 * @param num
 */
function robotNumBlur(num){
	debugger;
	var reg = new RegExp("^\\d+$");
	if(!reg.test($(num).val())){
		submitDataError($(num),"需输入大于等于0的整数");
		return;
	}else{
	submitDatasuccess($(num));
	}
}

