$(function(){
router();	
step1checkStepOneStaus();
	
function router(){
    var hash = window.location.hash;
    if(hash == '' || hash == '#step1'){
        $('#step1').show();
        $('#step2').hide();
        $('#step3').hide();
        $('body').scrollTop(0);
    }else if(hash == '#step2'){
        $('#step1').hide();
        $('#step2').show();
        $('#step3').hide();
        $('body').scrollTop(0);
    }else if(hash == '#step3'){
        $('#step1').hide();
        $('#step2').hide();
        $('#step3').show();
        $('body').scrollTop(0);
        step3Init();
    }
}

function step1checkStepOneStaus(){
	var awardProportion = $("#awardProportion").val();
	var beginDate = $("#beginDate").val();
	var endDate =$("#endDate").val();
	var name = $("#activityName").val();
	if(!(awardProportion&&(parseFloat(awardProportion)<=100&&parseFloat(awardProportion)>=0))){
		$("#step1submit").addClass("links-disabled");
		return false;
	}else if(!(beginDate)){
		$("#step1submit").addClass("links-disabled");
		return false;
	}else if(!(endDate)){
		$("#step1submit").addClass("links-disabled");
		return false;
	}else if(!name){
		$("#step1submit").addClass("links-disabled");
		return false;
	}
	$("#step1submit").removeClass("links-disabled");
	return true;
}


$("#step1").find("input[type='text']").bind('keyup',function(){
	step1checkStepOneStaus();
 });

$("#step1submit").on("click",function(){
	$("#awardProportion").trigger("click");
	if(step1FormCheck()){
		window.location.hash = '#step2';
	}
});

function setPrizeDrawNumber(){
	if($("input[name=limitNumber][checked=checked]").val()==1){
		$("input[name=prizeDrawNumber]").val(1);
	}else{
		$("input[name=prizeDrawNumber]").val($("#prizeDrawNumber").val());
	}
}

$("#list_award").on("click",function(){
	setPrizeDrawNumber();
	$("#formid").attr("action",path+"/h5/activity/roullete/list_award_roullete").submit();
});

$("input[name=limitNumber]").on("click",function(){
	if($(this).val()==1){
		$("#item-content").attr("class","item-content");
	
	}
});

function step1FormCheck() {
	var awardProportion = $("#awardProportion").val();
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var name = $("#activityName").val();
	if (!(awardProportion && (parseFloat(awardProportion) <= 100 && parseFloat(awardProportion) >= 0))) {
		Tips.show('请正确填写获得几率');
		return false;
	}
	else if (!(beginDate)) {
		Tips.show('请正确填写开始日期');
		return false;
	}
	else if (!(endDate)) {
		Tips.show('请正确填写结束日期');
		return false;
	}
	else if (!name) {
		Tips.show('请正确填写活动名称');
		return false;
	}
	return true;
}

function step2FormCheck(){
	var prizeDrawNumber = $("#prizeDrawNumber").val();
	var limitNumber = $("#limitNumber").val();
	var awaryCount = $("#awaryCount").val();
	var setCondition =$("#setCondition").val();
	if(!(awaryCount&&awaryCount>0)){
		Tips.show('请选择奖品');
		return false;
	}else if(!(prizeDrawNumber&&prizeDrawNumber>=0)){
		Tips.show('请正确填写每人可抽取次数');
		return false;
	}else if(!setCondition){
		Tips.show('请正确填写重设次数');
		return false;
	}else if(!limitNumber){
		Tips.show('请正确填写是否限制每人领取一次');
		return false;
	}
	return true;
}

$("#step2submit").on("click",function(){
	setPrizeDrawNumber();
	if(step2FormCheck()){
		window.location.hash = '#step3';
	}
});

window.onhashchange = router;

$("#submitButton").on("click", function() {
	if(!key){
    	return;
    }
	submitSetTime();
	if(step1FormCheck()&&step2FormCheck()&&checkAwardEndDate()){
		$("#formid").attr("action", path+"/h5/activity/roullete/save").submit();
	}
});

step3Init = function(){
	$("#step3name").text($("#activityName").val());
	$("#step3prizeDrawNumber").text($("#prizeDrawNumber").val());
	$("#step3limitNumber").text($("#limitNumber").val()==1?"1次":"无限制");
	var setCondition=$("#setCondition").val();
	var setConditionToString="";
	if(setCondition==0){
		setConditionToString="不重设";
	}else if(setCondition==1){
		setConditionToString="分享获得";
	}else if(setCondition==2){
	setConditionToString="每天重置";
	}else if(setCondition==3){
	setConditionToString="消费获得";
	}
	$("#step3setConditionToString").text(setConditionToString);
	$("#step3date").text($("#beginDate").val()+"至"+$("#endDate").val());
}

})