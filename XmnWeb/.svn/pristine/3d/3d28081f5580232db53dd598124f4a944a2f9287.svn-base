var imgRoot = $("#fastfdsHttp").val();
var formId="editForm";

$(function(){
	initbrandId();
	$("#brandId").trigger("change");
});


//初始化商品下拉框
function initbrandId(){
	$("#brandId").chosenObject({
		hideValue : "codeId",
		showValue : "pname",
		url : "fresh/activity/getProduct.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


//初始化日期
$('#begin_time').datetimepicker({
    format:'yyyy-mm-dd hh:ii',
    startDate: new Date(),
    language:  'zh-CN',
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
}).on("changeDate",function(ev){
    var transferdate=transferDate($("#begin_time").val());//转时间日期
    $('#end_time').datetimepicker('remove');
    $('#end_time').datetimepicker({
        format:'yyyy-mm-dd hh:ii',
        language:  'zh-CN',
        //minView:2,
        autoclose: 1,
        'startDate':transferdate
    }).on("changeDate",function(ev){
        var enddate=$("#end_time").val();
        setEndTime(enddate);
    });
});


$('#end_time').datetimepicker({
    format:'yyyy-mm-dd hh:ii',
    startDate: new Date(),
    language:  'zh-CN',
    minView:2,
    autoclose: 1
}).on("changeDate",function(ev){
    var enddate=$("#end_time").val();
    setEndTime(enddate);
});


function setEndTime(enddate){
    $('#begin_time').datetimepicker('remove');
        $('#begin_time').datetimepicker({
            format:'yyyy-mm-dd hh:ii',
            language:  'zh-CN',
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            'endDate':transferDate(enddate)
    });
}
//将时间字符串转为date
function transferDate(data){
    var start_time=data;
    var newTime= start_time.replace(/-/g,"-");
    var transferdate = new Date(newTime);
    return transferdate;
}
function transferTime(str){
    var newstr=str.replace(/-/g,'-');
    var newdate=new Date(newstr);
    var time=newdate.getTime();
    return time;
}


var productInfo;
$("#brandId").on("change",function(){
//	debugger;
	var codeId=$(this).val();
	if(codeId){
		$.post("fresh/manage/getSaleGroupList.jhtml",{"codeId":codeId},function(data,status){
			if(status=='success'){
				var group=$("#group");
				group.html('');
				productInfo=data.productInfo;
				if(data.saleGroupList.length==0){
					var item={
							"pvValues":"通用规格",
							"stock":productInfo.store,
							"amount":0
					};
					$("<option>").text("通用规格").val($.toJSON(item)).appendTo(group);
				}else{
					$.each(data.saleGroupList,function(i,item){
						$("<option>").text(item.pvValues).val($.toJSON(item)).appendTo(group);
					});
				}
				group.trigger("change");
			}
		});
	}
});

$("#group").on("change",function(){
	var pvIds=$(this).val();
	var group=$.parseJSON(pvIds);
	$("#breviary").attr("src",imgRoot+productInfo.breviary);
	$("#goodsName").text(productInfo.pname);
	$("#pvValue").val(group.pvValues);
	$("#price").text('￥'+(productInfo.price+group.amount*1)+" + "+productInfo.integral+"积分");
	$("#store").val(group.stock);
	$("#title").val(productInfo.pname);
	$("#place_sum").text(productInfo.price+group.amount*1+productInfo.integral);
	$("#basePrice").val(productInfo.price+group.amount*1+productInfo.integral);
	$("#product_div").show();
});


validate(formId,{
	rules : {
		title : {
			required : true
		},
		startPrice : {
			required : true,
			number :true
		},
		range:{
			required : true,
			number :true
		},
		insurancePrice:{
			required : true,
			number :true
		}
	},
	messages:{
		startPrice : {
			number :"请输入数字"
		},
		range:{
			number :"请输入数字"
		},
		insurancePrice:{
			number :"请输入数字"
		}
	}
},save);

function save(){
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "freshAuction/manage/add" + suffix;
	} else {// 修改操作
		url = "freshAuction/manage/update" + suffix;
	}
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	var data = $('#'+formId).serializeArray();
	data = jsonFromt(data);
	var pvIds=$("#group").val();
	var group=$.parseJSON(pvIds);
	data.pvIds=group.pvIds;
	
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
			showSmReslutWindow(data.success, data.msg);
			window.location.href="freshAuction/manage/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}

/**
 * 自定义校验
 */
function validateCustomer(){
	var result=true;
	if(!$("#begin_time").val()){
		showWarningWindow("warning","请输入活动开始时间!",9999);
		return;
	}
	if(!$("#end_time").val()){
		showWarningWindow("warning","请输入活动结束时间!",9999);
		return;
	}
	
	var brandId=$("#brandId").val();
	if(!brandId){
		showWarningWindow("warning","请选择商品!",9999);
		return;
	}
	
	var store= $("#store").val();
	if(parseInt(store)<=0){
		showWarningWindow("warning","商品库存不足!",9999);
		return;
	}
	
	return result;
}

