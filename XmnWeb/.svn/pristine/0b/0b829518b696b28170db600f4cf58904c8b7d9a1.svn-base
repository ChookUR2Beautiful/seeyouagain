var imgRoot = $("#fastfdsHttp").val();
var groups = new Array();
var groupEditKey;
initbrandId();
initdatetime();
//初始化商品下拉框
function initbrandId() {
	var productInfoChoose=$("[name^=productInfoChoose]");
	$.each(productInfoChoose,function(i,item){
		$(item).chosenObject({
			hideValue : "pid",
			showValue : "pname",
			url : "fresh/activity/getProduct.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%"
		});
	});
}

$("#label").chosenObject({
	hideValue : "id",
	showValue : "labelName",
	url : "freshLabel/manage/getLabelChoose.jhtml?type=1",
	isChosen : true, //是否支持模糊查询
	isCode : true, //是否显示编号
	isHistorical : false, //是否使用历史已加载数据
	width : "100%"
});


function initdatetime(){
	var len=$("#bout_list").find(".bout_modul").length;
	if(len==0){return;}
	for(i=1;i<=len;i++){
		$("[name=beginDate"+i+"]").datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			language : 'zh-CN',
			//weekStart: 1,
			//todayBtn:  1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			//minView:2,
			forceParse : 0,
		//showMeridian: 1
		});
		$('[name=endDate'+i+"]").datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			language : 'zh-CN',
			//minView : 2,
			autoclose : 1
		});
	}
}

$("#activityImg").uploadImg({
	urlId : "img",
	showImg : $('#img').val()
});

$("[name=orderLimit]").on("click",function(){
	if($("[name=orderLimit]:checked").val()==1){
		$("#orderLimitSpan").show();
	}else{
		$("#orderLimitSpan").hide();
	}
});

//初始化日期
$('#begin_time').datetimepicker({
	format : 'yyyy-mm-dd hh:ii',
	startDate : new Date(),
	language : 'zh-CN',
	//weekStart: 1,
	//todayBtn:  1,
	autoclose : 1,
	todayHighlight : 1,
	startView : 2,
	//minView:2,
	forceParse : 0,
//showMeridian: 1
}).on("changeDate", function(ev) {
	var transferdate = transferDate($("#begin_time").val()); //转时间日期
	$('#end_time').datetimepicker('remove');
	$('#end_time').datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		language : 'zh-CN',
		//minView:2,
		autoclose : 1,
		'startDate' : transferdate
	}).on("changeDate", function(ev) {
		var enddate = $("#end_time").val();
		setEndTime(enddate);
	});
});
$('#end_time').datetimepicker({
	format : 'yyyy-mm-dd hh:ii',
	startDate : new Date(),
	language : 'zh-CN',
	//minView : 2,
	autoclose : 1,
	forceParse : 0,
	startView : 2,
	todayHighlight : 1,
}).on("changeDate", function(ev) {
	var enddate = $("#end_time").val();
	setEndTime(enddate);
});
function setEndTime(enddate) {
	$('#begin_time').datetimepicker('remove');
	$('#begin_time').datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		language : 'zh-CN',
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		'endDate' : transferDate(enddate)
	});
}
//将时间字符串转为date
function transferDate(data) {
	var start_time = data;
	var newTime = start_time.replace(/-/g, "-");
	var transferdate = new Date(newTime);
	return transferdate;
}
function transferTime(str) {
	var newstr = str.replace(/-/g, '-');
	var newdate = new Date(newstr);
	var time = newdate.getTime();
	return time;
}

function formSubmit() {
	alert();
}
$("[name^=productInfo]").on("change", function() {
	var pid = $(this).val();
	if ($("tr[id=" + pid + "]").size()) {
		return;
	}
	groupEditKey=$(this).parents("[id^=bout_modul]").find("[name=kill_tbody]");
	var but = $("<button>").attr("data-type", "ajax").attr("data-url", "fresh/activity/group/init.jhtml?pid=" + pid).attr("data-toggle", "modal").attr("style", "display:none;");
	$("body").append(but);
	but.trigger("click");
});

$("#add_bout").on("click", function() {
	var len = $("#bout_list").find("[id^=bout_modul]").length + 1 * 1;
	var lastModul=$("#bout_list").find("[id^=bout_modul]").eq(len-2);
	var bout = $("#bout_modul").clone(true);
	bout.attr("id","bout_modul"+len).addClass("bout_modul");
	var productChoose=bout.find("[name=productInfo]")
	.attr("name","productInfoChoose"+len).chosenObject({
		hideValue : "pid",
		showValue : "pname",
		url : "fresh/activity/getProduct.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	var beginDate = bout.find("[name=beginDate]");
	beginDate.attr("name", "beginDate" + len)
	var endDate = bout.find("[name=endDate]");
	endDate.attr("name", "endDate" + len);
	var lastendDate;
	if(lastModul.length>0){
		lastendDate=transferDate($("[name=endDate"+(len-1)+"]").val());
	}
	var laseBeginDate=$("[name=beginDate"+(len-1)+"]");
	var laseEndDate=$("[name=endDate"+(len-1)+"]");
	//初始化日期
	beginDate.datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		startDate : lastModul.length>0?lastendDate:new Date(),
		language : 'zh-CN',
		//weekStart: 1,
		//todayBtn:  1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		//minView:2,
		forceParse : 0,
	//showMeridian: 1
	}).on("changeDate", function(ev) {
		var nextBeginDate=$("[name=beginDate"+(len*1+1)+"]");
		var nextEndDate=$("[name=endDate"+(len*1+1)+"]");
		var transferdate = transferDate(beginDate.val()); //转时间日期
		endDate.datetimepicker('remove');
		endDate.datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			language : 'zh-CN',
			//minView:2,
			autoclose : 1,
			'startDate' : transferdate,
			endDate: nextBeginDate.length>0?transferDate(nextBeginDate.val()):transferDate("2027-1-1")
		}).on("changeDate", function(ev) {
			var enddate = endDate.val();
			var lastBeginDate=$("[name=beginDate"+(len*1-1)+"]");
			var lastEndDate=$("[name=endDate"+(len*1-1)+"]");
			beginDate.datetimepicker('remove');
			beginDate.datetimepicker({
				format : 'yyyy-mm-dd hh:ii',
				language : 'zh-CN',
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				startDate: lastEndDate.length>0?transferDate(lastEndDate.val()):new Date(),
				'endDate' : transferDate(enddate)
			});
		});
		if(laseEndDate.length){
			laseEndDate.datetimepicker('remove');
			laseEndDate.datetimepicker({
				format : 'yyyy-mm-dd hh:ii',
				language : 'zh-CN',
				//minView:2,
				autoclose : 1,
				'startDate' : transferDate(laseBeginDate.val()),
				endDate:  transferDate(beginDate.val())
			}).on("changeDate", function(ev) {
				var enddate = laseEndDate.val();
				laseBeginDate.datetimepicker('remove');
				laseBeginDate.datetimepicker({
					format : 'yyyy-mm-dd hh:ii',
					language : 'zh-CN',
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					forceParse : 0,
					'endDate' : transferDate(enddate)
				});
				beginDate.datetimepicker('remove');
				beginDate.datetimepicker({
					format : 'yyyy-mm-dd hh:ii',
					startDate : lastModul.length>0?lastendDate:new Date(),
					language : 'zh-CN',
					//weekStart: 1,
					//todayBtn:  1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					//minView:2,
					forceParse : 0,
					'endDate' : transferDate(endDate.val())
				//showMeridian: 1
				})
				
			});
		}
		
	});
	endDate.datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		startDate : lastModul.length>0?lastendDate:new Date(),
		language : 'zh-CN',
		//minView : 2,
		autoclose : 1
	}).on("changeDate", function(ev) {
		var enddate =endDate.val();
		beginDate.datetimepicker('remove');
		beginDate.datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
			language : 'zh-CN',
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			'endDate' : transferDate(enddate)
		});
		var nextBeginDate=$("[name=beginDate"+(len*1+1)+"]");
		var nextEndDate=$("[name=endDate"+(len*1+1)+"]");
		if(nextBeginDate.length){
			nextBeginDate.datetimepicker('remove');
			console.log(nextEndDate.val());
			console.log(endDate.val());
			nextBeginDate.datetimepicker({
				format : 'yyyy-mm-dd hh:ii',
				language : 'zh-CN',
				autoclose : 1,
				todayHighlight : 1,
				startView : 2,
				forceParse : 0,
				'startDate': transferDate(endDate.val()),
				'endDate' : transferDate(nextEndDate.val())
			});
			
			
			nextBeginDate.datetimepicker({
				format : 'yyyy-mm-dd hh:ii',
				language : 'zh-CN',
				//minView:2,
				autoclose : 1,
				'startDate' : transferDate(endDate.val()),
				endDate: transferDate(nextEndDate.val())
			})
		}
	});
	bout.appendTo("#bout_list").show();
});

$("[name=delete_modul_bit]").on("click", function() {
	var modul=$(this).parents("[id^=bout_modul]");
	if($("#bout_list").find(".bout_modul").length!=modul.attr("id").charAt(modul.attr("id").length-1)){
		showWarningWindow("warning", "请从最后一场起删除", 9999);
		return;
	}else{
		modul.remove();
	}
});

function deleteGroup(pid) {
	$("#" + pid).remove();
}

function editGroup(pid) {
 	var json = $("#" + pid).find("input[type=hidden]").val();
	var but = $("<button>").attr("data-type", "ajax").attr("data-url", "fresh/activity/group/init.jhtml?json=" + json + "&pid=" + pid).attr("data-toggle", "modal").attr("style", "display:none;");
	$("body").append(but);
	but.trigger("click");
}

validate("editFrom", {
	rules : {
		title : {
			required : true
		}
	},
	messages : {
		
	}
}, save);

function save() {
	if (!$("#begin_time").val()) {
		showWarningWindow("warning", "请输入活动开始时间!", 9999);
		return;
	}else{
		//判断总开始时间必须小于第一场开始时间
		try{
			var beginDate = $("#bout_list").find(".bout_modul:first").find("[name^=beginDate]").val();;
			if(new Date($("#begin_time").val()).getTime()>new Date(beginDate).getTime()){
				showWarningWindow("warning", "活动开始时间必须小于第一场次开始时间!", 9999);
				return;
			}
		}catch(err){
			console.log(err);
			showWarningWindow("warning", "活动开始时间必须小于第一场次开始时间!", 9999);
			return;
		}
	}
	if (!$("#end_time").val()) {
		showWarningWindow("warning", "请输入活动结束时间!", 9999);
		return;
	}else{
		//判断总结束时间必须大于最后场结束时间
		try{
			var endDate = $("#bout_list").find(".bout_modul:last").find("[name^=endDate]").val();;
			if(new Date($("#end_time").val()).getTime()<new Date(endDate).getTime()){
				showWarningWindow("warning", "活动结束时间必须大于最后场次结束时间!", 9999);
				return;
			}
		}catch(err){
			console.log(err);
			showWarningWindow("warning", "活动结束时间必须大于最后场次结束时间!", 9999);
			return;
		}
	}
	if (!$("#label").val()) {
		showWarningWindow("warning", "请选择商品标签!", 9999);
		return;
	}
	if (!$("#img").val()) {
		showWarningWindow("warning", "请上传活动图片!", 9999);
		return;
	}
	if($("[name=orderLimit]:checked").val()==1){
		if(!$("#orderLimit").val()){
			showWarningWindow("warning", "请填写限制购买数!", 9999);
			return;
		}
		if($("#orderLimit").val()<1){
			showWarningWindow("warning", "请填写正确的限制购买数!", 9999);
			return;
		}
	}else{
		$("#orderLimit").val('');
	}
	
	var moduls=$("#bout_list").find("[id^=bout_modul]");
	if(moduls.length<=0){
		showWarningWindow("warning", "请添加场次!", 9999);
		return;
	}
	var boutList=new Array();
	var key=false;
	$.each(moduls,function(i,item){
		var _item=$(item);
		var beginDate=_item.find("[name^=beginDate]").val();
		if(!beginDate){
			showWarningWindow("warning", "请输入第场"+i*1+1+"的开始时间!", 9999);
			key=true
			return;
		}
		var endDate=_item.find("[name^=endDate]").val();
		if(!endDate){
			showWarningWindow("warning", "请输入第场"+i*1+1+"的结束时间!", 9999);
			key=true
			return true;
		}
		var hiddens=_item.find("input[type=hidden]");
		if (hiddens.length == 0) {
			showWarningWindow("warning", "请选择商品!", 9999);
			key=true
			return true;
		}
		var boutModuls=$("#bout_list").find(".bout_modul");
		for(i=0;i<boutModuls.length;i++){
			var _item=boutModuls.eq(i);
			if(i!=0){
				if(new Date(_item.find("[name^=beginDate]").val()).getTime()<new Date(boutModuls.eq(i-1).find("[name^=endDate]").val()).getTime()){
					showWarningWindow("warning", "第"+(i*1+1)+"场开始时间不能大于上一场结束时间", 9999);
					key=true;
					return;
				}
			}
			if(new Date(_item.find("[name^=endDate]").val()).getTime()<new Date(_item.find("[name^=beginDate]").val()).getTime()){
				showWarningWindow("warning", "第"+(i*1+1)+"场结束时间不能大于时间", 9999);
				key=true;
				return;
			}
		}
		var json = new Array()
		$.each(hiddens, function(i, item) {
			json.push($(item).val());
		});
		var bout={
				"beginTime":beginDate,
				"endTime":endDate,
				"productJson":json
		}
		boutList.push(bout);
	})
	if(key){
		return;
	}
	var data = $('#editFrom').serializeArray();
	data = jsonFromt(data);
	var json={
			"json":boutList
	}
	var boutListJson=$.toJSON(json);
	data.boutListJson = boutListJson;
	$.ajax({
		type : 'post',
		url : "fresh/kill/add.jhtml",
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href = "fresh/kill/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}