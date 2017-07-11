/**
 * Created by Administrator on 2016-09-29.
 */
router();
var limit_size;
var tabsSwiper;

/*页面路由*/
function router(){
    var hash = window.location.hash;
    if(hash == '' || hash == '#draw1'){
        $('#draw1').show();
        $('#draw2').hide();
    }else if(hash == '#draw2'){
        $('#draw1').hide();
        $('#draw2').show();
        step2Init();
    }
}


function step2Init(){
		var fullPrice=$("#full_price").val();
		$("#jizan-list-1-1").text('支付满￥'+fullPrice+'可集一个点');
		var isSuposition=$("#is_suposition").val();
		$("#jizan-list-off").text(isSuposition==1?'是':'否');
		$("#jizan-list-2-1").text($("#awardName").val());
		var beginDate=$("#beginDate").val();
		var endDate = $("#endDate").val();
		$("#jizan-list-3-1").text(beginDate+'-'+endDate);
		$("#draw2_select").html("");
		for(i=1;i<=pointsNumber;i++){
			if(i%pointsNumber==0){
				$("#draw2_select").append(' <li class="count-activity"><span>'+i+'</span><i class="set-count-icon"></i></li>');
			}else{
				$("#draw2_select").append("<li><span>"+i+"</span></li>")
			}
		}
}

function checkForm(){
	if(!pointsNumber){
		Tips.show("请填写点数");
		return false;
	}
	var fullPrice=$("#full_price").val();
	if(!fullPrice){
		Tips.show("请填写支付满金额");
		return false;
	}
    var beginDate=$("#beginDate").val();
    if(!beginDate){
    	Tips.show("请填写开始日期");
    	return false;
    }
    var endDate = $("#endDate").val();
    if(!endDate){
    	Tips.show("请填写结束日期");
    	return false;
    }
    var awardId = $("#awardId").val();
    if(!awardId){
    	Tips.show("请选择奖品");
    	return false;
    }
    return true;
}

$("#step2-2submit").on("click",function(){
	if(checkForm()){
		window.location.hash='#draw2';
	}
});

/*设置奖品*/

$("#draw2-1").bind('click',function(){
	var fullPrice=$("#full_price").val();
    var isSuposition=$("#is_suposition").val();
    var beginDate=$("#beginDate").val();
    var endDate = $("#endDate").val();
    var awardId = $("#awardId").val();
    var awardType = $("#awardType").val();
    var obj={"pointsNumber":pointsNumber,"fullPrice":fullPrice,"isSuposition":isSuposition,"beginDate":beginDate,"endDate":endDate,"awardId":awardId,"awardType":awardType};
    myPost(basePath+"/h5/activity/fcouspoints/list_award",obj);
    
})

$("li[name='points_but']").on("click",function(){
	$("li[name='points_but']").removeClass("count-activity");
	$(this).addClass("count-activity");
	pointsNumber=$(this).find("span").text();
});

$("#every-switch").bind('switchchange',function(){
	switchStatus = everySwitch.getSwitchStatus();
	$("#is_suposition").val(switchStatus?1:0);
});


$(function(){
	$.each($("li[name='points_but']"),function(){
		if($(this).find("span").text()==pointsNumber){
			$(this).addClass("count-activity");
		}
	})
	
	var beginDate=new Date(Date.parse($("#beginDate").val().replace(/-/g, "/")));
	var endDate=new Date(Date.parse($("#endDate").val().replace(/-/g, "/")));
	$('#activity-starttime').attr("initTime",beginDate.getFullYear() + '年' + (parseInt(beginDate.getMonth())+parseInt(1)) + '月' + beginDate.getDate() + '日')
	$('#activity-endtime').attr("initTime",endDate.getFullYear() + '年' + (parseInt(endDate.getMonth())+parseInt(1))+ '月' + endDate.getDate() + '日')
	
	$('#activity-starttime').bind('click',function(){
        var _this = this;
        var endTime = $('#activity-endtime').attr('initTime');
        var startTime = $(_this).attr('initTime');
        var initTime;
        if(startTime) initTime = startTime;
        else initTime = endTime;
        new datePicker({
            initTime: initTime,
            successDestory: false,
            compareTime: endTime,
            operation: '<=',
            success: function(year,month,day,datePicker,compareStatus){
                if(!compareStatus) Tips.show('请选择正确的时间');
                else{
                	$("#beginDate").val(year + '-' + month + '-' + day);
                    $(_this)
                            .attr('initTime',year + '年' + month + '月' + day + '日')
                            .html(year+'.'+month + '.' + (day<10?"0"+day:day));
                    datePicker.destoryDatePicker();
                }
            }
        });
    });


    $('#activity-endtime').bind('click',function(){
        var _this = this;
        var startTime = $('#activity-starttime').attr('initTime');
        var endTime = $(_this).attr('initTime');
        var initTime;
        if(endTime) initTime = endTime;
        else initTime = startTime;
        new datePicker({
            initTime: initTime,
            compareTime: startTime,
            operation: '>=',
            successDestory: false,
            success: function(year,month,day,datePicker,compareStatus){
                if(!compareStatus) Tips.show('请选择正确的时间');
                else{
                	$("#endDate").val(year + '-' + month + '-' + day);
                    $(_this)
                            .attr('initTime',year + '年' + month + '月' + day + '日')
                            .html(year+'.'+month + '.' + (day<10?"0"+day:day));
                    datePicker.destoryDatePicker();
                }
            }
        });
    });
    $('#activity-name').bind('keyup',function(){
        checkStepOneStaus();
    });
    $('#oddsNubmer').bind('keyup',function(){
        checkStepOneStaus();
    });
    
   
    
      
    window.onhashchange = router;
  
    var key = 1;
    function submitSetTime(){
   	 key = 0;
   	 window.setTimeout(function(){
   			key=1;
   	 },1000); 
    }
    
    $("#step2InitSubmit").on("click",function(){
    	if(!key){
        	return;
        }
    	submitSetTime();
    	if(checkForm()){
    		var fullPrice=$("#full_price").val();
    	    var isSuposition=$("#is_suposition").val();
    	    var beginDate=$("#beginDate").val();
    	    var endDate = $("#endDate").val();
    	    var awardId = $("#awardId").val();
    	    var awardEndDate =$("#couponEndDate").val()+" 23:59:00";
    	    if(new Date(Date.parse(awardEndDate.replace(/-/g, "/"))).getTime()<new Date(Date.parse(endDate.replace(/-/g, "/")))){
				Tips.show("活动时间有更改,请重新选择奖品");
				return;
			}
    	    var obj={"pointsNumber":pointsNumber,"fullPrice":fullPrice,"isSuposition":isSuposition,"beginDate":beginDate,"endDate":endDate,"awardId":awardId};
    	    myPost(basePath+"/h5/activity/fcouspoints/save", obj)
    	}
    });
    
    
    
});	



