router();
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
        step2Init();
    }else if(hash == '#step3'){
        $('#step1').hide();
        $('#step2').hide();
        $('#step3').show();
        step3Init();
    }
}

function step2Init(){
    var totalMoney = $('#totalmoney').val();
    $("#stp2-totalMoney").html('￥'+totalMoney);
}

function step3Init(){
    /*投入金额*/
    var totalMoney = $('#totalmoney').val();
    /*活动开始日期*/
    var activityStartDate = $('#activity-starttime').attr('initTime');
    /*活动结束日期*/
    var activityEndDate = $('#activity-endtime').attr('initTime');
    /*活动开始时间*/
    var activityStartTime = $('#activity-selstartTime').attr('initTime');
    /*活动结束时间*/
    var activityEndTime = $('#activity-selendTime').attr('initTime');
    /*活动名称*/
    var activityName = $('#activity-name').val();
    /*新用户金额*/
    var newUserMoney = $('#newusermoney').val();
    /*旧用户金额*/
    var oldUserMoney = $('#oldusermoney').val();
    /*分享红包比例*/
    var percent = $('#select-reward-box').attr('value');

    $('#stp3-activityname').html(activityName);
    $('#stp3-totalmoney').html('￥'+totalMoney);
    $('#stp3-newusermoney').html('￥'+newUserMoney);
    $('#stp3-oldusermoney').html('￥'+oldUserMoney);
    $('#stp3-sharepecent').html(percent);
    $('#stp3-time').html( activityStartTime +'-'+ activityEndTime );
    $('#stp3-date').html( activityStartDate + ' 至 ' + activityEndDate);
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
                    .val(month + '月' + day + '日');
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
                    .val(month + '月' + day + '日');
                    datePicker.destoryDatePicker();
                    checkStepOneStaus();
                } 
            }
        });
    });

    $('#activity-selstartTime').bind('click',function(){
        var _this = this;
        var startTime = $(_this).attr('initTime');
        var endTime = $('#activity-selendTime').attr('initTime');
        var initTime;
        if(startTime) initTime = startTime;
        else initTime = endTime;
        new timePicker({
            initTime: initTime,
            successDestory: false,
            compareTime: endTime,
            operation: '>=',
            success: function(hour,min,timepicker,compareStatus){
                // console.log(hour);
                if(!compareStatus) Tips.show('请选择正确的时间');
                else{
                    $(_this)
                    .attr('initTime',hour+":"+min)
                    .val(hour+":"+min);
                    timepicker.destoryTimePicker();
                    checkStepOneStaus();
                }
                
            }
        });
    });

    $('#activity-selendTime').bind('click',function(){
        var _this = this;
        var endTime = $(_this).attr('initTime');
        var startTime = $('#activity-selstartTime').attr('initTime');
        var initTime;
        if(endTime) initTime = endTime;
        else initTime = startTime;
        new timePicker({
            initTime: initTime,
            successDestory: false,
            compareTime: startTime,
            operation: '<=',
            success: function(hour,min,timepicker,compareStatus){
                if(!compareStatus) Tips.show('请选择正确的时间');
                else{
                    $(_this)
                    .attr('initTime',hour+":"+min)
                    .val(hour+":"+min);
                    timepicker.destoryTimePicker();
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
        /*投入金额*/
        var totalMoney = $('#totalmoney').val();
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动开始时间*/
        var activityStartTime = $('#activity-selstartTime').attr('initTime');
        /*活动结束时间*/
        var activityEndTime = $('#activity-selendTime').attr('initTime');
        /*活动名称*/
        var activityName = $('#activity-name').val();

        if(!isNaN(parseFloat(totalMoney)) && activityStartDate && activityEndDate && activityStartTime && activityEndTime && activityName != ''){
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
            /*投入金额*/
            var totalMoney = $('#totalmoney').val();
            /*活动开始日期*/
            var activityStartDate = $('#activity-starttime').attr('initTime');
            /*活动结束日期*/
            var activityEndDate = $('#activity-endtime').attr('initTime');
            /*活动开始时间*/
            var activityStartTime = $('#activity-selstartTime').attr('initTime');
            /*活动结束时间*/
            var activityEndTime = $('#activity-selendTime').attr('initTime');
            /*活动名称*/
            var activityName = $('#activity-name').val();

            if(isNaN(parseFloat(totalMoney))){
                Tips.show('请正确填写投入金额');
            }else if(!activityStartDate){
                Tips.show('请选择活动开始日期');
            }else if(!activityEndDate){
                Tips.show('请选择活动结束日期');
            }else if(!activityStartTime){
                Tips.show('请选择活动开始时间');
            }else if(!activityEndTime){
                Tips.show('请选择活动结束时间');
            }else if(activityName == ''){
                Tips.show('请填写活动名称');
            }
        }
    });

    /*选择红包奖励比例*/
    $('#select-reward-box').bind('click',function(){
        var _this = this;
        var value = $(this).attr('value');
        new selector({
            options: [
                {text: '5%',value: '5%'},
                {text: '10%',value: '10%'},
                {text: '15%',value: '15%'},
                {text: '20%',value: '20%'}
            ],
            initValue: value,
            success: function(res){
                console.log(res);
                $(_this).attr('value',res.value).html(res.text);
            }
        });
    });
    /*提交预览按钮事件*/
    $('#gostepthree').bind('click',function(){
        /*新用户金额*/
        var newUserMoney = $('#newusermoney').val();
        /*旧用户金额*/
        var oldUserMoney = $('#oldusermoney').val();
        /*分享红包比例*/
        var percent = $('#select-reward-box').attr('value');
        if(isNaN(parseFloat(newUserMoney)) || parseFloat(newUserMoney) > 100){
            Tips.show('请输入正确的新用户金额');
        }else if(isNaN(parseFloat(oldUserMoney)) || parseFloat(oldUserMoney) > 100){
            Tips.show('请输入正确的旧用户金额');
        }else if(typeof(percent) == 'undefined' || percent == ''){
            Tips.show('请选择分享红包奖励比例');
        }else{
            window.location.hash = 'step3';
            router();
        }
    });

    /*提交按钮*/
    $('#submitform').bind('click',function(){
        /*投入金额*/
        var totalMoney = $('#totalmoney').val();
        /*活动开始日期*/
        var activityStartDate = $('#activity-starttime').attr('initTime');
        /*活动结束日期*/
        var activityEndDate = $('#activity-endtime').attr('initTime');
        /*活动开始时间*/
        var activityStartTime = $('#activity-selstartTime').attr('initTime');
        /*活动结束时间*/
        var activityEndTime = $('#activity-selendTime').attr('initTime');
        /*活动名称*/
        var activityName = $('#activity-name').val();
        /*新用户金额*/
        var newUserMoney = $('#newusermoney').val();
        /*旧用户金额*/
        var oldUserMoney = $('#oldusermoney').val();
        /*分享红包比例*/
        var percent = $('#select-reward-box').attr('value');

        if(isNaN(parseFloat(totalMoney))){
            Tips.show('请正确填写投入金额');
        }else if(!activityStartDate){
            Tips.show('请选择活动开始日期');
        }else if(!activityEndDate){
            Tips.show('请选择活动结束日期');
        }else if(!activityStartTime){
            Tips.show('请选择活动开始时间');
        }else if(!activityEndTime){
            Tips.show('请选择活动结束时间');
        }else if(activityName == ''){
            Tips.show('请填写活动名称');
        }else if(isNaN(parseFloat(newUserMoney)) || parseFloat(newUserMoney) > 100){
            Tips.show('请输入正确的新用户金额');
        }else if(isNaN(parseFloat(oldUserMoney)) || parseFloat(oldUserMoney) > 100){
            Tips.show('请输入正确的旧用户金额');
        }else if(typeof(percent) == 'undefined' || percent == ''){
            Tips.show('请选择分享红包奖励比例');
        }else{
            //请求
            $.ajax({
                type: "GET",
                url: "test.json",
                data: {},
                dataType: "json",
                success: function(data){
                }
            });
        }

    });


});



