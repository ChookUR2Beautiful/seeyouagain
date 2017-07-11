window.location.hash='#step1';
router();



/*页面路由*/
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

function step2Init(){
    var totalMoney = parseFloat($('#totalmoney').val());
    $("#stp2-totalMoney").html('￥'+totalMoney);
}

function step3Init(){
    /*抵用金额*/
    var totalMoney = $('#totalmoney').val();
    /*活动开始日期*/
    var activityStartDate = $('#activity-starttime').attr('initTime');
    /*活动结束日期*/
    var activityEndDate = $('#activity-endtime').attr('initTime');
    /*活动名称*/
    var activityName = $('#activity-name').val();

    /*使用条件*/
    var offCondition = parseFloat($('#offset-condition').val()).toFixed(2);
    /*红包个数*/
    var offNum = parseInt($('#offser-num').val());
    /*消费满输入框*/
    var fullMoney = parseFloat($('#fullconsum-money').val());
    /*是否选择消费满模式*/
    var getConditionText = '';
    var isFullConsum = $('#xfmhd-radio').prop('checked');
    var isUnlimited = $('#wxz-radio').prop('checked');
    var isNewUserbanding = $('#xyhbd-radio').prop('checked');
    if(isFullConsum) getConditionText = '消费满'+fullMoney+'元获得';
    else if(isUnlimited) getConditionText = '无限制';
    else if(isNewUserbanding) getConditionText = '新用户绑定获得';
    

    $('#stp3-activityname').html(activityName);
    $('#stp3-totalmoney').html('￥'+parseFloat(totalMoney));
    $('#stp3-date').html( activityStartDate + '至' + activityEndDate);

    $('#stp3-useCondition').html('满'+offCondition+'可用');
    $('#stp3-offnum').html(offNum+'张');
    $('#stp3-getcondition').html(getConditionText);


    
}


$(function(){
    window.onhashchange = router;
    var switchStatus = true;
    var sw = new sassSwitch({
        ele: '#sass-switch',
        open: switchStatus
    });
    $("#sass-switch").bind('switchchange',function(){
    	switchStatus = sw.getSwitchStatus();
    	});
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


    $('#activity-name').bind('keyup',function(){
        checkStepOneStaus();
    });
    $('#totalmoney').bind('keyup',function(){
        checkStepOneStaus();
    });



    function checkStepOneStaus(){
        /*抵用金额*/
        var totalMoney = parseFloat($('#totalmoney').val());
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动名称*/
        var activityName = $('#activity-name').val();
        
        if(!isNaN(totalMoney) && totalMoney > 0 && activityStartDate && activityEndDate && activityName != ''){
            $('#gesteptwo').removeClass('links-disabled');
        }else{
            $('#gesteptwo').addClass('links-disabled');
        }
    }
    /*下一步按钮*/
    $('#gesteptwo').bind('click',function(){
            /*抵用金额*/
            var totalMoney = parseFloat($('#totalmoney').val());
            /*活动开始日期*/
            var activityStartDate = $('#activity-starttime').attr('initTime');
            /*活动结束日期*/
            var activityEndDate = $('#activity-endtime').attr('initTime');
            /*活动名称*/
            var activityName = $('#activity-name').val();

            if(isNaN(totalMoney) || totalMoney <= 0||totalMoney>5000){
                Tips.show('请填写5000以内的抵用券金额');
            }else if(!activityStartDate){
                Tips.show('请选择活动开始日期');
            }else if(!activityEndDate){
                Tips.show('请选择活动结束日期');
            }else if(activityName == ''){
                Tips.show('请填写抵用券名称');
            }else{
            	window.location.hash = 'step2';
                router();
            }
        
    });

    /*step2提交*/
    $('#step2submit').bind('click',function(){
        /*使用条件*/
        var offCondition = parseFloat($('#offset-condition').val());
        /*红包个数*/
        var offNum = parseInt($('#offser-num').val());
        /*是否选择消费满模式*/
        var isFullConsum = $('#xfmhd-radio').prop('checked');
        /*消费满输入框*/
        var fullMoney = parseFloat($('#fullconsum-money').val());
        var total=$("#totalmoney").val();
        if(isNaN(offCondition) || offCondition > 5000 || offCondition <= 0){
            Tips.show('请输入正确的使用条件金额');
        }else if(isNaN(offNum) || offNum <= 0){
            Tips.show('请输入正确的数量');
        }else if(isFullConsum && (isNaN(fullMoney) || fullMoney > 5000 || fullMoney <= 0)){
            Tips.show('请输入正确的消费满金额');
        }else{
            if(offCondition>total){
                window.location.hash = 'step3';
                router();
            }else {
                Tips.show('使用条件需大于抵用金额');
            }

        }
    });

    /*领取条件*/
    $('.reset-radio-input').bind('change',function(){
        if($(this).attr('id') == 'xfmhd-radio') $('#fullconsum').show();
        else $('#fullconsum').hide();
    });

    var key = 1;
    function submitSetTime(){
   	 key = 0;
   	 window.setTimeout(function(){
   			key=1;
   	 },1000); 
    }
    /*提交*/
    $('#submitform').bind('click',function(){
    	/*防止按钮连击重复创建*/
    	if(!key){
        	return;
        }
    	submitSetTime();
        var hash = window.location.hash;
        /*抵用金额*/
        var totalMoney = parseFloat($('#totalmoney').val());
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动名称*/
        var activityName = $('#activity-name').val();

        /*step2提交*/
       /*使用条件*/
        var offCondition = parseFloat($('#offset-condition').val());
        /*现金券个数*/
        var offNum = parseInt($('#offser-num').val());
        /*是否选择消费满模式*/
        var isFullConsum = $('#xfmhd-radio').prop('checked');
        /*消费满输入框*/
        var fullMoney = parseFloat($('#fullconsum-money').val());

        /*数据校验*/
        if(isNaN(totalMoney) || totalMoney <= 0){
            Tips.show('请正确填写抵用券金额');
            return;
        }else if(!activityStartDate){
            Tips.show('请选择活动开始日期');
            return;
        }else if(!activityEndDate){
            Tips.show('请选择活动结束日期');
            return;
        }else if(activityName == ''){
            Tips.show('请填写抵用券名称');
            return;
        }

        if(isNaN(offCondition) || offCondition > 5000 || offCondition <= 0){
            Tips.show('请输入正确的使用条件金额');
            return;
        }else if(isNaN(offNum) || offNum <= 0){
            Tips.show('请输入正确的数量');
            return;
        }else if(isFullConsum && (isNaN(fullMoney) || fullMoney > 5000 || fullMoney <= 0)){
            Tips.show('请输入正确的消费满金额');
            return;
        }
        
        //领取条件
        var awardCondition = $("input:radio:checked").attr("condition");

        //活动页面创建奖品参数标示
        var adlistUrl = $("#adlistUrl").val();
        //拼接请求参数
        var obj = new Object();
        obj.cname = activityName;
        obj.startDate = activityStartDate;
        obj.endDate = activityEndDate;
        obj.denomination = $('#totalmoney').val();
        obj.limitAmount = "0"
        obj.awardCondition = awardCondition;
        if(awardCondition=='1'){
        	obj.limitAmount = $('#fullconsum-money').val();//消费满获取金额
        }
        obj.conditions = $('#offset-condition').val();
        obj.limitNumber = (switchStatus?"1":"0");
        obj.sendNum = $('#offser-num').val();
        obj.description = 0;
        if(adlistUrl!=null&&adlistUrl!=""){
        	obj.status = 1;
		}
        /*数据提交*/
        $.ajax({
        	type:"post",
        	url: basePath + '/h5/coupon/save_cash',
        	data:obj,
        	dataType:"json",
        	success:function(data){
        		var adlistUrl = $("#adlistUrl").val();
        		if(data.state==0){
        			if(adlistUrl==null||adlistUrl==""){
        				//无限制的活动
        				if(data.result.condition==0){
        					//跳转到PHP
        					window.location=data.result.url;
        				}else{
        					window.location=basePath + '/h5/coupon/select?couponType=3';
        				}
            		}else{
            			//跳转至活动奖品列表
            			window.location=basePath + adlistUrl+"?awardId="+data.result.cid+"&awardType=3";
            		}
        		}else{
        			Tips.show('创建现金抵用券失败！');
        		} 
        	}
        });
    });
    
    

    


});



