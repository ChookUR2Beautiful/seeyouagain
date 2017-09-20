var formId = "editForm";
var jsonTextInit;
$(function(){
	
	inserTitle(' > <span>预告详情','advanceDetail',false);
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//初始化直播券单选框
	initLiveCoupon();
	
	//初始化提供粉丝券设置,保存后此项不可变更。注意：必须初始化直播券单选框后执行
	initHaveCouponSet();
	
	//初始化抵用券信息
	initHaveFree();
	
	//初始化日期控件
	initDates();
	
	//表单校验
	validate(formId, {
		rules : {
			haveCoupon :{
				required:true
			},
			cid :{
				required:true
			},
			cname : {
				required : true
			},
			denomination:{
				required : true,
				//测试环境去掉校验
				/*digits:true,
				range:[1,100000000]*/
			},
			sendNum:{
				required : true,
				digits:true
			},
			validityDesc : {
				required : true
			},
			introduce : {
				required : true
			},
			rule :{
				required :true
			},
			dayNum:{
				required :true
			}
		},
		messages:{
			haveCoupon :{
				required:"请选择"
			},
			cname:{
				required:"请输入券名"
			},
			denomination:{
				required : "请输入价格",
				digits:"请输入正整数",
				range:"请输入正整数"
			},
			sendNum:{
				required : "请输入粉丝券数量",
				digits:"请输入整数"
			},
			validityDesc:{
				required:"请输入粉丝券描述"
			},
			introduce :{
				required:"请输入预告介绍"
			},
			rule:{
				required:"请输入使用须知"
			},
			dayNum:{
				required :"请选择使用时间"
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
	var isAdd = $("#" + formId).find("input[name='anchorCid']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveRecord/manage/setAdvance/addAnchorConpon" + suffix;
	} else {// 查看操作
		url = "liveRecord/manage/setAdvance/updateAnchorConpon" + suffix;
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
		loading.show();
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
					var url = contextPath +'/liveRecord/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}else{
					loading.hide();
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
//	debugger;
	var result=true;
	
	var haveCoupon=$("input[name='haveCoupon']:checked").val();
	var cid=$("#cid").val();
	if(haveCoupon==1 && (cid=='' || cid ==undefined)){
		showWarningWindow('warning', "请选择粉丝券！");
		result=false;
		return;
	}
	
	var startDate=$("#startDate").val();
	if(haveCoupon==1 && (startDate == null || startDate=="")){
		showWarningWindow("warning","请选择使用开始时间!",9999);
		rsult=false;
		return ;
	}
	
	var endDate=$("#endDate").val();
	if(haveCoupon==1 && (endDate == null || endDate=="")){
		showWarningWindow("warning","请选择使用结束时间!",9999);
		rsult=false;
		return ;
	}
	
	var validateResult=sendNumValidate();
	if(!validateResult){
		result=validateResult;
	}
	return result;
}



/**
 * 初始化赠送抵用券 
 */

function initHaveFree(haveFree,voucherList){
//	debugger;
	if(haveFree=='1'){//有抵用券
		$("input[name='haveFree']")[0].checked=true;
		//加载抵用券列表详情
		loadVouchers(voucherList);
		$("#voucherTr").css("display","table-row");
		
	}else{//无抵用券
		$("input[name='haveFree']")[1].checked=true;
		$("#voucherTr").css("display","none");
	}
	
}


//=====================2016-11-01==============================
/**
 * 初始化直播券下拉框
 */
function initLiveCoupon(){
	$("#cid").chosenObject({
		hideValue : "cid",
		showValue : "cname",
		url : "liveRecord/manage/setAdvance/initLiveCoupon.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


/**
 * 选择直播券后,修改对应信息
 */
$('body').on("click",'#cid_chosen .chosen-results li',function(){
//	debugger;
	loadAdvanceCouponInfo();
});


/**
 * 加载预告直播券信息
 */
function loadAdvanceCouponInfo(){
	var cid =  $("#cid").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "liveRecord/manage/setAdvance/getLiveCouponInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"cid":cid},
		success : function(data){
			if(data != null){
				$("#denomination").val(data.denomination);
				$("#originalPrice").val(data.originalPrice);
				//粉丝券数量 
				var isAdd= $("#isAdd").val();
				if(isAdd=='true'){
					$("#sendNum").val(data.defaultMaxNum);
					$("#introduce").val(data.introduce);
				}
				$("#validityDesc").val(data.validityDesc);
				$("#rule").val(data.rule);
				//初始化赠送预售抵用券情况
				initHaveFree(data.haveFree,data.voucherList);
			}
		}
	});
}


/**
 * 加载抵用券列表详情
 */
function loadVouchers(data){
	var content='';
	//加载活动列表
	 $.each(data, function (n, obj) {  
		 content +="<div class='input-group'>"
				 + "	<input  type='text'	 readonly='readonly' class='form-control' style='width:100px;' value="+ obj.denomination +" /> "
				 + "	<span	class='input-group-addon'>元 &nbsp;&nbsp;&nbsp;&nbsp;满</span>  "
				 + "	<input  type='text'	 readonly='readonly' class='form-control' style='width:100px;' value="+ obj.condition +" /> "
				 + "	<span	class='input-group-addon'>元可用</span> "
				 + "</div>";
      });  
    $("#plandiv").html(content);
}

/**
 * 提供直播粉丝券改变触发
 */
$("input[name='haveCoupon']").change(function(){
//	debugger;
	var haveCoupon=$("input[name='haveCoupon']:checked").val();
	if(haveCoupon=='1'){
		$(".on-off").css('display','table-row');
	}else{
		$(".on-off").css('display','none');
	}
});


/**
 * datetimepicker 转化
 */
function initDates() {
	$("input.form-datetime").datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		todayBtn : false,
		minuteStep : 1,
		startDate : new Date(),
	});
	
	$('input[name="startDate"]').datetimepicker({
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
		endDate: $("input[name='endDate']").val()
	}).on("changeDate",function() {
			$("input[name='endDate']").datetimepicker("setStartDate",$("input[name='startDate']").val());
		});
	
	$('input[name="endDate').datetimepicker({
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
		startDate: $("input[name='startDate']").val()
	}).on( "changeDate", function() {
				$("input[name='startDate']").datetimepicker("setEndDate", $("input[name='endDate']").val());
			});
}

/**
 * 提供粉丝券设置
 */
function initHaveCouponSet(){
//	debugger;
	var haveCoupon=$("input[name='haveCoupon']:checked").val();
	if(haveCoupon=='0'||haveCoupon=='1'){
//		$("input[name='haveCoupon']").click();
		if(haveCoupon=='1'){
			$(".on-off").css('display','table-row');
		}else{
			$(".on-off").css('display','none');
		}
		$("input[name='haveCoupon']").attr("disabled",true);
		loadAdvanceCouponInfo();
	}
}

/**
 * 粉丝券减少数量不能少于当前库存
 */
function sendNumValidate(){
	var result=true;
	var currentSendNum=$("#currentSendNum").val();//变更前发行量
	var sendNum=$("#sendNum").val();//变更后发行量
	var stock=$("#stock").val()==''?0:$("#stock").val();//库存
	var stockChange=sendNum-currentSendNum;
	var afterStock=parseInt(stock)+parseInt(stockChange);
	if(afterStock<0){
		submitDataError("input[name='sendNum']","减少数量"+(-stockChange)+"不能大于当前库存"+stock);
		result=false;
	}else{
		$("#stockChange").val(stockChange);
	}
	return result;
}