window.location.hash='#step1';
router();

var everySwitch = new sassSwitch({
        ele: '#every-switch',
        open: true
});
var switchStatus = true;
$("#every-switch").bind('switchchange',function(){
	switchStatus = everySwitch.getSwitchStatus();
});

/*页面路由*/
function router(){
    var hash = window.location.hash;
    if(hash == '' || hash == '#step1'){
        $('#step1').show();
        $('#step2').hide();
        $('#step3').hide();

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

function step2Init(){
    var totalNum = parseInt($('#totalNum').val());
    var giftName = $('#gift-name').val();
    $("#stp2-totalNum").html(totalNum+"&nbsp;张");
    $("#stp2-giftname").html(giftName);
}

function step3Init(){
    /*抵用金额*/
    var totalNum = $('#totalNum').val();
    /*活动开始日期*/
    var activityStartDate = $('#activity-starttime').attr('initTime');
    /*活动结束日期*/
    var activityEndDate = $('#activity-endtime').attr('initTime');
    /*赠品券名称*/
    var giftName = $('#gift-name').val();

    /*使用条件*/
    var payCondition = parseFloat($('#paycondition').val()).toFixed(2);
    
    
    /*描述*/
    var descript = $('#gift-descript').val();    
    /*领取条件*/
    var getCondition = '';

    if($('#wxz-radio').prop('checked')) getCondition = '无限制';
    else if($('#xfmhd-radio').prop('checked')) getCondition = '消费满获得';
    else if($('#xyhbd-radio').prop('checked')) getCondition = '新用户绑定获得';

    $('#stp3-giftname').html(giftName);
    $('#stp3-totalnum').html(parseInt(totalNum) + "&nbsp;张");
    $('#stp3-date').html( activityStartDate + '至' + activityEndDate);

    $('#stp3-descript').html(descript);
    $('#stp3-getcondition').html(getCondition);

    var limitCondition = everySwitch.getSwitchStatus();
    var limitText = '';
    if(limitCondition) limitText = '每人领取一次';
    else limitText = '无限制';

    $('#stp3-limitcondition').html(limitText);
    /**/
    
}


$(function(){
    window.onhashchange = router;
    
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
                    $(_this)
                    .attr('initTime',year + '年' + month + '月' + day + '日')
                    .html(month + '月' + day + '日');
                    datePicker.destoryDatePicker();

                    checkStepOneStaus();
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
                    $(_this)
                    .attr('initTime',year + '年' + month + '月' + day + '日')
                    .html(month + '月' + day + '日');
                    datePicker.destoryDatePicker();
                    checkStepOneStaus();
                } 
            }
        });
    });


    $('#gift-name').bind('keyup',function(){
        checkStepOneStaus();
    });
    $('#totalNum').bind('keyup',function(){
        checkStepOneStaus();
    });



    function checkStepOneStaus(){
        /*抵用金额*/
        var totalNum = parseInt($('#totalNum').val());
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动名称*/
        var giftName = $('#gift-name').val();

        if(!isNaN(totalNum) && totalNum > 0 && activityStartDate && activityEndDate && giftName != ''){
            $('#gesteptwo').removeClass('links-disabled');
        }else{
            $('#gesteptwo').addClass('links-disabled');
        }
    }
    /*下一步按钮*/
    $('#gesteptwo').bind('click',function(){
        if(!$(this).hasClass('links-disabled')){
            window.location.hash = 'step2';
            router();
        }else{
            /*抵用数量*/
            var totalNum = parseFloat($('#totalNum').val());
            /*活动开始日期*/
            var activityStartDate = $('#activity-starttime').attr('initTime');
            /*活动结束日期*/
            var activityEndDate = $('#activity-endtime').attr('initTime');
            /*活动名称*/
            var giftName = $('#gift-name').val();

            if(isNaN(totalNum) || totalNum <= 0){
                Tips.show('请正确填写赠品券数量');
            }else if(!activityStartDate){
                Tips.show('请选择活动开始日期');
            }else if(!activityEndDate){
                Tips.show('请选择活动结束日期');
            }else if(giftName == ''){
                Tips.show('请填写赠品券名称');
            }
        }
    });

    /*step2提交*/
    $('#step2submit').bind('click',function(){
    	var awardCondition = $("input:radio:checked").attr("condition");
    	if(awardCondition=='1'){
    		var payCondition = parseFloat($('#paycondition').val());
            if(isNaN(payCondition) || payCondition <= 0 || payCondition >= 1000 ){
                Tips.show('请输入正确的支付满金额');
            }else{
                window.location.hash = 'step3';
                router();
            }
    	}else{
            window.location.hash = 'step3';
            router();
        }
    });
    $('#div-consumeAndPay').hide();
    /*领取条件*/
    $('.reset-radio-input').bind('change',function(){
        if($(this).attr('id') == 'xfmhd-radio') 
        	$('#div-consumeAndPay').show();
        else $('#div-consumeAndPay').hide();
    });
    
    var key = 1;
    function submitSetTime(){
   	 key = 0;
   	 window.setTimeout(function(){
   			key=1;
   	 },1000); 
    }
    /*提交创建赠品券*/
    $('#submitform').click(function(){
    	if(!key){
        	return;
        }
    	submitSetTime();
    	var sellerId = $("#sellerId").val();
        /*抵用数量*/
        var totalNum = parseFloat($('#totalNum').val());
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动名称*/
        var giftName = $('#gift-name').val();
        /*活动描述*/
        var description = $('#gift-descript').val();
        console.info(description);

        if(isNaN(totalNum) || totalNum <= 0){
            Tips.show('请正确填写赠品券数量');
            return;
        }else if(!activityStartDate){
            Tips.show('请选择活动开始日期');
            return;
        }else if(!activityEndDate){
            Tips.show('请选择活动结束日期');
            return;
        }else if(giftName == ''){
            Tips.show('请填写赠品券名称');
            return;
        }
        //领取条件
        var awardCondition = $("input:radio:checked").attr("condition");
    	/*支付满条件金额*/
        var payCondition = $('#paycondition').val();

        //活动页面创建奖品参数标示
        var adlistUrl = $("#adlistUrl").val();
        //拼接请求参数
        var obj = new Object();
        obj.cname = giftName;
        obj.startDate = activityStartDate;
        obj.endDate = activityEndDate;
        obj.limitAmount = "0";
        obj.awardCondition = awardCondition;
        if(awardCondition=='1'){
        	obj.limitAmount = payCondition;
        }
        obj.limitNumber = (switchStatus?"1":"0");
        obj.sendNum = $('#totalNum').val();
        obj.status = 0;//初始化启用状态
        if(adlistUrl!=null&&adlistUrl!=""){
        	obj.status = 1;
		}
        obj.description = description;
        /*数据提交*/
        $.ajax({
        	type:"post",
        	url: basePath + '/h5/coupon/save_gift',
        	data:obj,
        	dataType:"json",
        	success:function(data){
        		var adlistUrl = $("#adlistUrl").val();
        		if(data.state==0){
        			if(adlistUrl==null||adlistUrl==""){
        				if(data.result.condition==0){
        					//跳转到PHP
        					window.location=data.result.url;
        				}else{
        					window.location=basePath + '/h5/coupon/select?couponType=4';
        				}
            		}else{
            			//跳转至活动奖品列表
            			window.location=basePath + adlistUrl+"?awardId="+data.result.cid+"&awardType=4";
            			
            		}
        		}else{
        			Tips.show('创建赠品券失败！');
        		}
        	}
        });
    });
});



