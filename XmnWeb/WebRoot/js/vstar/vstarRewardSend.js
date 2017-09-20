var searchChosen = undefined;
var $members = $("[group='members']");
var $specific = $("[group='specific']");
$(function(){
    // 初始化input选取
    $("#ctype-default").click();
    $("#userType-default").click();
    $("[defaultSelect]").change();
    
    //初始化选择用户
    selectUsersInit();
    
    
    $('#selectUsers').chosen({
        no_results_text: '没有找到',    // 当检索时没有找到匹配项时显示的提示文本
        disable_search_threshold: 10, // 10 个以下的选择项则不显示检索框
        search_contains: true         // 从任意位置开始检索
    });
    
    var rewardType=$("input[name='ctype']:checked").val();
    pageInit(rewardType);
    
});

/**
 * 加载页面数据
 */
function pageInit(rewardType){
//	debugger;
	enrollList1 = $('#enrollList1').page({
		url : 'vstarRewardSend/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 10,//每页条数
		paramForm : 'searchForm',
		param :{"rewardType":rewardType}
	});
	
}

/**
 * 查询最新10条奖励信息成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "searchForm", title = "奖励列表", subtitle = "条信息";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : false,
		// 操作列
		handleCols : {
			
		},
		cols : [ {
			title : "获奖名单",
			name : "nname",
			type : "string",
			width : 100
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 100
		} ,{
			title : "奖励类型",
			name : "rewardTypeStr",
			type : "string",
			width : 100
		},{
			title : "获得奖励",
			name : "rewardComment",
			type : "string",
			width : 100
		},{
			title : "获奖时间",
			name : "createTimeStr",
			type : "string",
			width : 100
		}]
	}, permissions);
}

/**
 * 初始化选择用户
 */
function selectUsersInit(){
//	debugger;
	var userItems=$("#selectUsers").attr("initValue");
	var users=eval(userItems);
	//系统推送优惠券,用户及手机号码字符串。用户之间以逗号分隔，UID和phone之间以:;分隔(606872:;:;11800000005:;11800000005, 606873:;:;11800000006:;11800000006)
	$.each( users, function(i, object){
		var uid=object["uid"];
		var phone=object["phone"];
		var nname=object["nname"]==undefined?"":object["nname"];
		var item=uid+":;:;"+phone+":;"+nname;
		 $("#selectUsers").append("<option value='"+item+"' selected>"+phone+"</option>");
	});
}


// 为添加优惠劵按钮绑定事件
$("#button-addCoupon").click(function(){
	debugger;
    var cid = $("#cid").val();
    var typeName = $("input[name='ctype']:checked").attr("typeName");
    var couponName = $("#cid_chosen .chosen-single").text();
    var useNum = $("#useNum").val();
    var flag = $("#couponListTbody td[cid='"+cid+"']").length == 0;
    if(cid && couponName && useNum>0 && flag){
        // 拼装一行数据
        var tr = $("<tr couponLine>" 
        			+"<td>"+typeName+"</td>" 
        			+"<td style='display:none;' cid='"+cid+"'>"+cid+"</td>" 
        			+"<td>"+couponName+"</td>" 
        			+"<td useNum='"+useNum+"'>"+useNum +"</td>" 
        			+"<td><span class='btn btn-default' id='button-addCoupon' onclick='deleteLine(this)' style='padding-top: 0px; padding-bottom: 0px;'>删除</span></td>" 
        			+"</tr>");
        $("#couponListTbody").append(tr);
    }
});

// 发送添加请求
$("#ensure").click(function(){
//	debugger;
    // 获取参数
    var ctype = $("input[name='ctype']:checked").val();
    var coupons = spliceCoupons();
    var userType = $("input[name='userType']:checked").val();
    var levelSelect = $("#level-select").val();
    var selectUsers = $("#selectUsers").val().join(",");
    var getWay=$("#getWay").val();
    var birdCoin=$("#birdCoin").val();
    if(ctype!=6){
    	birdCoin=birdCoin==''||birdCoin==undefined?"0":birdCoin;
    }
    

    // 校验参数, 成功发送请求
    if(validateCoupon({ ctype: ctype, coupons: coupons, userType: userType, levelSelect: levelSelect, selectUsers: selectUsers})) {
        $.post("VstarPlayerRank/manage/reward/count-add.jhtml",
            {
                ctype: ctype,
                coupons: coupons,
                userType: userType,
                levelSelect: levelSelect,
                selectUsers: selectUsers,
                "getWay" : getWay,
                "birdCoin": birdCoin
            },
            function (result) {
            	var tips="";
            	if(ctype==6){
            		tips="所选用户较多时生成时间较长, 请勿重复提交!<br/>是否继续?";
            	}else{
            		tips="共计发放优惠劵["+result.data+"]张, 数量较大时生成时间较长, 请勿重复提交!<br/>是否继续?";
            	}
            	
                showSmConfirmWindow(function(){
                    $.post("coupon/couponissue/systemPush/add.jhtml",
                        {
                            ctype: ctype,
                            coupons: coupons,
                            userType: userType,
                            levelSelect: levelSelect,
                            selectUsers: selectUsers,
                            "getWay" : getWay,
                            "birdCoin": birdCoin
                        },
                        function (result) {
                            if(result.success) {
                                showSmReslutWindow(result.success, result.msg);
                                setTimeout(function () {
                                    window.history.back();
                                }, 1000);

                            }else {
                                showWarningWindow("warning","添加失败!",9999);
                            }

                        }
                    );

                },tips);
            }
        );
    }
});


/**
 * 校验参数
 */
function validateCoupon(data){
//	debugger;
	var ctype=data.ctype;//奖励类型
	
    if(!data.coupons && ctype!=6){
        // 未选取优惠劵
        showWarningWindow("warning","未选取优惠劵!",9999);
        return false;
    }

    if(data.userType==0 && !data.levelSelect){
        //未选取会员等级
        showWarningWindow("warning","未选取会员等级!",9999);
        return false;
    }
    if(data.userType== 1 && !data.selectUsers){
        // 没有选取用户
        showWarningWindow("warning","未选取用户!",9999);
        return false;
    }
    
    
    var result=birdCoinBlur($("#birdCoin"));
    if(!result && ctype==6){
    	// 输入鸟币金额不合法
        showWarningWindow("warning","请输入大于0小于1000000的正数(最多保留两位小数)!",9999);
        return false;
    }
    
    return true;
}
/**
 * 删除元素
 * @param elem
 */
function deleteLine(elem){
    $(elem).parent().parent().remove();
}

/**
 * 拼接已选取的优惠劵 为json字符串
 * @returns {string}
 */
function spliceCoupons() {
    var value= "";
    $.each($("tr[couponline]"), function (index, context) {
        var cid = $(context).find("td[cid]").attr("cid");
        var useNum = $(context).find("td[useNum]").attr("useNum");
        if(index == 0){
            value += "[";
        }
        value += "{\"cid\":" + cid + ",\"useNum\":" + useNum + "}";
        if (index + 1 == $("tr[couponline]").length) {
            value += "]";
        } else {
            value += ",";
        }
    });
    return value;
}

/** 切换优惠劵类型*/
function onchangeCtype(input) {
	var rewardType=$(input).val();
	
	if(rewardType=='6'){
		$(".coupon-info").css('display','none');
		$(".bird-info").css('display','block');
	}else{
		
		var $cid=$("#cid");
	    var $cidDiv = $("#cidDiv");
	    $cidDiv.empty().append($cid);
	    $("#cid").chosenObject({
	        hideValue : "value",
	        showValue : "cname",
	        filterVal : $(input).val(),
	        url : "coupon/couponissue/queryYouHuiQuan.jhtml",
	        isChosen:true,//是否支持模糊查询
	        isCode:true,//是否显示编号
	        isHistorical:false,//是否使用历史已加载数据
	        width:"100%"
	    });

	    $("#couponListTbody").html("");
	    
	    $(".coupon-info").css('display','block');
	    $(".bird-info").css('display','none');
	}
    
	pageInit(rewardType);

}

/** 切换用户类型 */
function onchangeUserType(input){
    var userType = $(input).val();

    // 选取"按会员类型"
    if(userType == 0){
        $("#specific").find("input").val("");// 清空表单数据
        $members.show();
        $specific.hide();

    }
    // 选取按具体用户
    else if(userType == 1){
        $members.hide();
        $specific.show();
        $("#members").find("input").val("");// 清空表单数据
    }
}


/**
 * 鸟币金额失去焦点，校验方法
 * @param num
 */
function birdCoinBlur(num){
	debugger;
	var reg=new RegExp("^(0|[1-9][0-9]*)(\.[0-9]{1,2})?$");
	var rangeValidate = 0<$(num).val() && $(num).val()<1000000;
	if(!reg.test($(num).val()) || !rangeValidate){
		submitDataError($(num),"需输入大于0小于1000000正数(最多保留两位小数)");
		return false;
	}else{
	submitDatasuccess($(num));
	    return true;
	}
}


