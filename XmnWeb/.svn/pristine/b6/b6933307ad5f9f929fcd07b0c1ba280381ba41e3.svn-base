var searchChosen = undefined;
var $members = $("[group='members']");
var $specific = $("[group='specific']");
$(function(){
    // 初始化input选取
    $("#ctype-default").click();
    $("#userType-default").click();
    $("[defaultSelect]").change();

    // 初始化优惠劵选取列表
    if (!searchChosen) {
        searchChosen = $("#selectUsers").searchChosen({
            url : "user_terminal/appPush/init/choseMember/init.jhtml",
            separator : ","
        });
    };

    // 为添加优惠劵按钮绑定事件
    $("#button-addCoupon").click(function(){
        var cid = $("#cid").val();

        var useNum = $("#useNum").val();
        // 判断优惠劵是否已添加
        var flag = $("#couponListTbody td[cid='"+cid+"']").length == 0
        if(cid  && useNum>0 && flag){
            // 查询优惠劵信息并添加一行优惠劵
            $.get("coupon/couponissue/queryCoupon.jhtml?cid="+cid,function(couponInfo){
                var tr = $("<tr couponLine>" +
                    "<td cid='"+cid+"'>"+cid+"</td>" +
                    "<td>"+couponInfo.cname+"</td>" +
                    "<td>"+couponInfo.denomination+"</td>" +
                    "<td useNum='"+useNum+"'>"+useNum+"</td>" +
                    "<td >"+useNum*couponInfo.denomination+"</td>" +
                    "<td><span class='btn btn-default' id='button-addCoupon' onclick='deleteLine(this)' style='padding-top: 0px; padding-bottom: 0px;'>删除</span></td>" +
                    "</tr>")
            $("#couponListTbody").append(tr);
            });
        }
    });


    // 发送添加请求
    $("#ensure").click(function(){
        console.log("提交请求!");
        // 获取参数
        var ctype = $("input[name='ctype']:checked").val();
        var coupons = spliceCoupons();
        var userType = $("input[name='userType']:checked").val();
        var levelSelect = $("#level-select").val();
        var selectUsers = $("#selectUsers").val();
        var getWay=$("#getWay").val();

        // 校验参数, 成功发送请求
        if(validateCoupon({ ctype: ctype, coupons: coupons, userType: userType, levelSelect: levelSelect, selectUsers: selectUsers})) {
            $.post("coupon/couponissue/systemPush/count-add.jhtml",
                {
                    ctype: ctype,
                    coupons: coupons,
                    userType: userType,
                    levelSelect: levelSelect,
                    selectUsers: selectUsers,
                    "getWay":getWay
                },
                function (result) {

                    showSmConfirmWindow(function(){
                        $.post("coupon/couponissue/systemPush/add.jhtml",
                            {
                                ctype: ctype,
                                coupons: coupons,
                                userType: userType,
                                levelSelect: levelSelect,
                                selectUsers: selectUsers,
                                "getWay":getWay
                            },
                            function (result) {
                                if(result.success) {
                                    showSmReslutWindow(result.success, result.msg);
                                    setTimeout(function () {
                                        window.history.back();
                                    }, 1000)
                                }else {
                                    showWarningWindow("warning","添加失败!",9999);
                                }
                            }
                        )

                    },"共计发放优惠劵["+result.data+"]张, 数量较大时生成时间较长, 请勿重复提交!<br/>是否继续?")
                }
            );
            // $('#ensure').unbind("click");
        }
    });


    $("#import-users").change(function(){
        // 清空已选取的用户
        $("#selectUsers").val("");

        // 导入的用户信息
        var importUsers = $("#import-users").val();
        if(importUsers){
            var requestParam = {users : importUsers};
            // 发送用户匹配数据
            $.post("coupon/couponissue/matching-users.jhtml",requestParam,function(resultData){
                if (resultData.state == 0) {
                    $matchedUserList = $("#matched-user-list").empty();
                    $unmatchedUserList = $("#unmatched-user-list").empty();


                    matchedUsers = resultData.context.matchedUsers;
                    unmatchedUsers = resultData.context.unmatchedUsers;

                    $("#matchedUserNum").remove();
                    $("#user-list-btn").remove();
                    $("#matching-users").parent()
                        .append($("<span id ='matchedUserNum'>匹配成功: "+matchedUsers.length+"  匹配失败: "+unmatchedUsers.length+"</span>"))
                    $("#matching-users").parent().append($("<input type='button' class='btn' id='user-list-btn' value='用户列表' onclick='submitUserList()'>"));
                    var selectUsersValue = "" ;
                    $.each(matchedUsers,function(index,data){
                        selectUsersValue += (data.uid+":;:;"+data.uname+":;,");
                    });
                    $("#selectUsers").val(selectUsersValue);



                } else {
                    showWarningWindow("warning",resultData.message,9999)
                }

            });
        }else {
            showWarningWindow("warning","没有导入用户!",9999);
        }

    });
})

/** 保存已匹配用户 */
function saveImportUsers(){
    var selectUsersValue = "" ;
    $.each(matchedUsers,function(index,data){
        selectUsersValue += (data.uid+":;:;"+data.uname+":;,");
    });
    $("#selectUsers").val(selectUsersValue);
    $("#close-import-users-box").click();
}

function submitUserList(){
    var importUsers = $("#import-users").val();
    $("#users").val(importUsers);
    window.open("coupon/couponissue/userList.jhtml","user-list-win","_blank");
    $("#user-list-from").submit();
}


/**
 * 校验参数
 */
function validateCoupon(data){
    if(!data.coupons){
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

    if(data.userType== 2 && !data.selectUsers){
        // 没有选取用户
        showWarningWindow("warning","未匹配用户!",9999);
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
            value += "]"
        } else {
            value += ",";
        }
    })
    return value;
}


/**
 * 切换用户等级类型
 * @param select
 */
function onchangeLevelType(select){
    var levelType = $(select).val();
    console.log("切换用户等级类型 value="+levelType);
    var $levelSelect = $("#level-select").empty();
    $.get("coupon/couponissue/user-level.jhtml?rankType="+levelType,function(result){
        $.each(result,function (index,context) {
            var option = $("<option value='"+context.id+"'>"+context.rankName+"</option>");
            $levelSelect.append(option);
        })
    });

}
/** 切换优惠劵类型*/
function onchangeCtype(input) {
    console.log("改变优惠劵类型:" + $(input).attr("typeName"));

    var $cid=$("#cid");
    var $cidDiv = $("#cidDiv");
    $cidDiv.empty().append($cid);
    $("#cid").chosenObject({
        // id : "value",
        hideValue : "value",
        showValue : "cname",
        // filterVal : $(input).val(),
        url : "coupon/couponissue/queryYouHuiQuan.jhtml",
        showParams : ['value'],
        isChosen:true,//是否支持模糊查询
        isCode:true,//是否显示编号
        isHistorical:false,//是否使用历史已加载数据
        width:"100%"
    });

    $("#couponListTbody").html("");
    // $('#cid').trigger('chosen:updated');

}


/** 切换用户类型 */
function onchangeUserType(input){
    console.log("用户类型:" + $(input).attr("typeName"));
    var userType = $(input).val();

    $("#specific").find("input").val("");// 清空表单数据
    $("#members").find("input").val("");// 清空表单数据

    // 选取"按会员类型"
    if(userType == 0){
        $members.show();
    }else {
        $members.hide();
    }
    // 选取按具体用户
    if(userType == 1){
        $specific.show();
    }else{
        $specific.hide();
    }
    // 按具体用户
    if(userType == 2){
        $("#import-users").show().val("");
        $("#selectUsers").siblings(".chosen-container").hide();
        $("#searchChosen").hide();
        $("#matching-users").show();
    }else {
        $("#matchedUserNum").remove();
        $("#import-users").hide();
        $("#selectUsers").siblings(".chosen-container").show();
        $("#searchChosen").show();
        $("#selectUsers").val("");
        $("#matching-users").hide();
    }
}


