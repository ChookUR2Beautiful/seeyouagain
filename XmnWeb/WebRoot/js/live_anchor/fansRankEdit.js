var formId = "editForm";
var jsonTextInit;
$(function(){
	
	if($("#id").val()){
		inserTitle(' > <span>编辑级别','fansRankEdit',false);
	}else{
		inserTitle(' > <span>添加级别','fansRankEdit',false);
	}
	
	//加载上一级
	initParentId();
	
	//初始化上一级别显示状态 
	initParentIdShow();
	
	//初始化编辑状态
	initEditControl();
	
	// 级别图片
	$("#picUrlImg").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//表单校验
	validate(formId, {
		rules : {
			rankType : {
				required : true
			},
			rankName : {
				required : true,
				maxlength:255
			},
			rewardLowest:{
				required : true,
				digits:true,
				range:[0,1000000000]
			},
			rewardHighest:{
				required : true,
				digits:true,
				range:[0,1000000000]
			},
			referrerRatio:{
				required:true,
				digits:true,
				range:[0,100]
			},
			parentReferrerRatio:{
				required:true,
				digits:true,
				range:[0,100]
			},
			sendBean:{
				required:true
			},
			referrerReward : {
				required : true,
				digits:true,
				range:[0,100]
			},
			consumeRatio : {
				required : true,
				digits:true,
				range:[0,100]
			},
			redPacketLowest : {
				required : true
			},
			redPacketHighest : {
				required : true
			}
		},
		messages:{
			rankName:{
				required:"请输入级别名称",
				maxlength:"请输入1-255个字符"
			},
			rewardLowest:{
				required : "请输入最低充值金额",
				digits:"请输入0至1000000000之间的整数",
				range:"请输入0至1000000000之间的整数"
			},
			rewardHighest:{
				required :"请输入最高充值金额",
				digits:"请输入0至1000000000之间的整数",
				range:"请输入0至1000000000之间的整数"
			},
			referrerRatio:{
				required:"请填写直属推荐人充值分佣比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			parentReferrerRatio:{
				required:"请填写直属推荐人上级充值分佣比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			sendBean:{
				required:"请选择打赏送鸟币"
			},
			referrerReward:{
				required:"请填写推荐奖励比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			},
			consumeRatio :{
				required:"请填写鸟币消费抵消比例",
				digits:"请填写1至100之间的整数",
				range:"请填写1至100之间的整数"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveFansRank/manage/add" + suffix;
	} else {// 修改操作
		url = "liveFansRank/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
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
				if (data.success) {
					var url = contextPath +'/liveFansRank/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	var count=getCount();
	var parentId=$("#parentId").val();
	
	if(isAdd && count>0 && (parentId==null || parentId=='')){
		showWarningWindow("warning","请选择上一级别!",9999);
		result=false;
		return result;
	}
	
	var picUrl=$("#picUrl").val();
	if(picUrl == null || picUrl==""){
		showWarningWindow("warning","请上传级别图片!",9999);
		rsult=false;
		return rsult;
	}
	
	return result;
}


//初始化上一级别
function initParentId(){
	var rankType=$("input[name='rankType']:checked").val();
	$("#parentId").chosenObject({
		hideValue : "id",
		showValue : "rankName",
		url : "liveFansRank/manage/getFansRanks.jhtml",
		filterVal:rankType,
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	$('#parentId').trigger('chosen:updated');
}

/**
 * 初始化上一级别显示状态
 */
function initParentIdShow(){
	var id=$("#id").val();
	if(id){
		$("#parentIdTr").css('display','none');
	}
}

/**
 * 初始编辑控制
 */
function initEditControl(){
	var id=$("#id").val();
	if(id){
		$("#rankName").attr('readonly','readonly');
		$("input[name='rankType']").attr('disabled',true);
	}
}


/**
 * 获取当前粉丝级别数量
 * @returns {Number}
 */
function getCount(){
	var count=0;
	var rankType=$("input[name='rankType']:checked").val();
	var url=contextPath +'/liveFansRank/manage/init/list.jhtml';
	$.ajax({
		type : 'post',
		url : url,
		data : {"rankType":rankType},
		dataType : 'json',
		async:false,
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			count=data.total;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
	
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
	
	return count;
}

/**
 * 绑定级别类型change事件
 */
$("input[name='rankType']").change(function(){
	initParentId();
});
