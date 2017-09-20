var imgRoot = $("#fastfdsHttp").val();
initbrandId();

$(function(){
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

//微信群二维码图片
$("#activityImg").uploadImg({
	urlId : "img",
	showImg : $('#img').val()
});

//初始化日期
$('#begin_time').datetimepicker({
    format:'yyyy-mm-dd hh:ii',
    startDate: new Date(),
    language:  'zh-CN',
    //weekStart: 1,
    //todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    //minView:2,
    forceParse: 0,
    //showMeridian: 1
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
	var codeId=$(this).val();
	if(!codeId){
		return;
	}
	$.post("fresh/manage/getSaleGroupList.jhtml",{"codeId":codeId},function(data,status){
		if(status=='success'){
			var group=$("#group");
			group.html('');
			productInfo=data.productInfo;
			if(data.saleGroupList.length==0){
				var item={
						"stock":productInfo.store,
						"amount":0,
						"codeId":codeId
				}
				if(!pvIds&&$("#activityId").val()){
					item.stock=item.stock*1+$("#boutNum").val()*1;
				}
				$("<option>").text("通用规格").val($.toJSON(item)).appendTo(group);
			}else{
				$.each(data.saleGroupList,function(i,item){
					var option=$("<option>").text(item.pvValues).val($.toJSON(item));
					if(item.pvIds==pvIds&&$("#activityId").val()){
						option.attr("selected","selected");
						item.stock=item.stock*1+$("#boutNum").val()*1;
					}
					option.val($.toJSON(item));
					option.appendTo(group);
				});
			}
			group.trigger("change");
		}
	});

});

$("#group").on("change",function(){
	var ids=$(this).val();
	var group=$.parseJSON(ids);
	$("#breviary").attr("src",imgRoot+productInfo.breviary);
	$("#goodsName").text(productInfo.pname);
	$("#pvValue").val(group.pvValues);
	$("#price").text('￥'+(productInfo.price+group.amount*1)+" + "+productInfo.integral+"积分");
	$("#store").text(group.stock);
	$("#basePrice").val(productInfo.price+group.amount*1+productInfo.integral)
	if((group.pvIds!=pvIds&&group.pvIds)||group.codeId!=chooseCodeId){
		chooseCodeId=group.codeId;
		$("#point").val(Math.round(productInfo.price+group.amount*1+productInfo.integral));
		$("#boutNum").val(group.stock);
		$("#title").val(productInfo.pname);
	}
	$("#product_div").show();
});


$("#boutNum").bind("keyup change",function(){
	var pvIds=$("#group").val();
	var group=$.parseJSON(pvIds);
	if($(this).val()>group.stock){
		$(this).val(group.stock);
	}
});

validate("editFrom",{
	rules : {
		codeId:{
			required : true
		},
		title : {
			required : true
		},
		point : {
			required : true
		},
		pointPrice:{
			required : true
		},
		boutNum:{
			required : true,
			digits:true,
			range : [1,1000000000]
		}
	},
	messages:{
		boutNum:{
			range : "期数不正确"
		}
	}
},save);

function save(){

	if(!$("#begin_time").val()){
		showWarningWindow("warning","请输入活动开始时间!",9999);
		return;
	}
	if(!$("#end_time").val()){
		showWarningWindow("warning","请输入活动结束时间!",9999);
		return;
	}
	var codeId=$("#brandId").val();
	if(!codeId){
		showWarningWindow("warning","请选择商品!",9999);
		return;
	}
	var data = $('#editFrom').serializeArray();
	data=jsonFromt(data);
	var pvIds=$("#group").val();
	var group=$.parseJSON(pvIds);
	data.pvIds=group.pvIds;
	$.ajax({
		type : 'post',
		url : "fresh/indiana/add.jhtml",
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href = "fresh/indiana/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}

