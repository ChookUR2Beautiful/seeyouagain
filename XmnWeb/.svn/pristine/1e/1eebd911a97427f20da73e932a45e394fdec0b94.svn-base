var imgRoot = $("#fastfdsHttp").val();
var groups=new Array();
initbrandId();
//初始化商品下拉框
function initbrandId(){
	$("#brandId").chosenObject({
		hideValue : "pid",
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

$("#label").chosenObject({
	hideValue : "id",
	showValue : "labelName",
	url : "freshLabel/manage/getLabelChoose.jhtml?type=1",
	isChosen : true, //是否支持模糊查询
	isCode : true, //是否显示编号
	isHistorical : false, //是否使用历史已加载数据
	width : "100%"
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

function formSubmit() {
	alert();
}
$("#brandId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var but=$("<button>").attr("data-type","ajax").attr("data-url","fresh/activity/group/init.jhtml?pid="+pid).attr("data-toggle","modal").attr("style","display:none;");
	$("body").append(but);
	but.trigger("click");
});

function deleteGroup(pid){
	$("#"+pid).remove();
}

function editGroup(pid){
	var json=$("#"+pid).find("input[type=hidden]").val();
	var but=$("<button>").attr("data-type","ajax").attr("data-url","fresh/activity/group/init.jhtml?json="+json+"&pid="+pid).attr("data-toggle","modal").attr("style","display:none;");
	$("body").append(but);
	but.trigger("click");
}

validate("editFrom",{
	rules : {
		title : {
			required : true
		},
		remark : {
			required : true
		}
	},
	messages:{
		typeId:{
			required:"请输入标题",
		},
		remark:{
			required:"请输入活动规划",
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
	if (!$("#label").val()) {
		showWarningWindow("warning", "请选择商品标签!", 9999);
		return;
	}
	if(!$("#img").val()){
		showWarningWindow("warning","请上传活动图片!",9999);
		return;
	}
	var hiddens=$("#productTB").find("input[type=hidden]");
	if(hiddens.length==0){
		showWarningWindow("warning","请选择商品!",9999);
		return;
	}
	var json=new Array()
	$.each(hiddens,function(i,item){
		json.push($(item).val());
	});
	var obj={json:json}
	json=$.toJSON(obj);
	var data = $('#editFrom').serializeArray();
	data = jsonFromt(data);
	data.productJson=json;
	$.ajax({
		type : 'post',
		url : "fresh/activity/add.jhtml",
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href="fresh/activity/list/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}

