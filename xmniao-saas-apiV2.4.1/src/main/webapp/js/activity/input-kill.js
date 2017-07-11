$(function(){
	
	router()
	checkStepOneStaus();

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
        step2Init();
    }else if(hash == '#step3'){
        $('#step1').hide();
        $('#step2').hide();
        $('#step3').show();
        $('body').scrollTop(0);
        step3Init();
    }
}
    
    function step1FormCheck(){
		var awardProportion = $("#secKillAmount").val();
		var beginDate = $("#beginDate").val();
		var endDate =$("#endDate").val();
		var name = $("#activityName").val();
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		if(!(awardProportion&&(parseFloat(awardProportion)<=5000&&parseFloat(awardProportion)>0))){
			Tips.show('请填写0~5000元以内的金额');
			return false;
		}else if(!(beginDate)){
			Tips.show('请正确填写开始日期');
			return false;
		}else if(!(endDate)){
			Tips.show('请正确填写结束日期');
			return false;
		}else if(!(beginTime)){
			Tips.show('请正确填写开始时间');
			return false;
		}else if(!(endTime&&endTime.replace(":","")>beginTime.replace(":",""))){
			Tips.show('请正确填写结束时间');
			return false;
		}else if(!name){
			Tips.show('请正确填写活动名称');	
			return false;
		}
		
		return true;
	}
    
    $("#step1submit").on("click",function(){
    	$("#secKillAmount").trigger("click");
    		if(step1FormCheck()){
    			window.location.hash = 'step2';
    		}
    });
    
    $("#step1").find("input[type='text']").bind('keyup',function(){
	       checkStepOneStaus();
	    });
	    
	function checkStepOneStaus(){
		var awardProportion = $("#secKillAmount").val();
		var beginDate = $("#beginDate").val();
		var endDate =$("#endDate").val();
		var name = $("#activityName").val();
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		if(!awardProportion){
			$("#step1submit").addClass("links-disabled");
			return false;
		}else if(!(beginDate)){
			$("#step1submit").addClass("links-disabled");
			return false;
		}else if(!(endDate)){
			$("#step1submit").addClass("links-disabled");
			return false;
		}else if(!(beginTime)){
			$("#step1submit").addClass("links-disabled");
			return false;
		}else if(!(endTime&&endTime.replace(":","")>beginTime.replace(":",""))){
			$("#step1submit").addClass("links-disabled");
			return false;
		}else if(!name){
			$("#step1submit").addClass("links-disabled");
			return false;
		}
		$("#step1submit").removeClass("links-disabled");
		return true;
	}
	
	$("#list_award").on("click", function() {
		setPrizeDrawNumber();
		$("#formid").attr("action", path+"/h5/activity/kill/list_award_kill").submit();
	});
	
	$("input[name=limitNumber]").on("click", function() {
		if ($(this).val() == 1) {
			$("#item-content").attr("class", "item-content");

		}
	});
	
	function setPrizeDrawNumber() {
		if ($("input[name=limitNumber][checked=checked]").val() == 1) {
			$("input[name=prizeDrawNumber]").val(1);
		}
		else {
			$("input[name=prizeDrawNumber]").val($("#prizeDrawNumber").val());
		}
	}
	
	function step2FormCheck(){
		var limitNumber = $("#limitNumber").val();
		var awaryCount = $("#awaryCount").val();
		if(!(awaryCount&&awaryCount>0)){
			Tips.show('请选择奖品');
			return false;
		}else if(!limitNumber){
			Tips.show('请正确填写是否限制每人领取一次');
			return false;
		}
		return true;
	}
	
	$("#step2submit").on("click", function() {
		setPrizeDrawNumber();
		if(step2FormCheck()){
			window.location.hash = '#step3';
		}
	});

	$("#submitButton").on("click", function() {
		if(!key){
        	return;
        }
		submitSetTime();
		if(step1FormCheck()&&step2FormCheck()&&checkAwardEndDate()){
			$("#formid").attr("action", path+"/h5/activity/kill/save").submit();
		}
	});

	window.onhashchange = router;
	
	step2Init=function(){
		$("#prizeDrawNumber").val("￥"+$("#secKillAmount").val());
		
	}
	
	step3Init=function(){
		$("#step3name").text($("#activityName").val());
		$("#step3limitNumber").text($("#limitNumber").val()==1?"1次":"无限制");
		$("#step3secKillAmount").text($("#secKillAmount").val());
		$("#step3date").text($("#beginDate").val()+"至"+$("#endDate").val());
	}
});
