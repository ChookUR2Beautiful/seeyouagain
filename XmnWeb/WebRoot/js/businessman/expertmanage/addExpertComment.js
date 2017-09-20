var ISTYPE;
var sellerid = $("#sellerid").val();
var sellername = $("#sellername").val();
ISTYPE = $("#isType").val();
$(document).ready(function() {
	$("#sellername").attr("readonly", true);
    //验证提交添加或修改的数据
    initValidator();
});
/**
 * 达人头像
 */
$("#expertpic").uploadImg({
	urlId : "url",
	showImg : $('#url').val()
});
/**
* 触发取消
 */
  $("#cancelId").on("click", function() {
              muBack();
   });
/**
 * 取消方法
 */
function muBack() {
    var url = contextPath + '/businessman/expert/init.jhtml?sellerid='+sellerid+'&sellername='+sellername;
    location.href = url;
}
/**
 * 验证并且提交添加或修改的数据
 */
var formId = [ "addExpertCommentForm" ];
function initValidator() {
    for (var i = 0; i < formId.length; i++) {
        validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
    }
}
/**
 * 验证方法
 */
var valiinfo = {
    "addExpertCommentForm" : {
        rules : {
            name : {
                required : true
            },
            content : {
                required : true
            },
            experttitle : {
                required : true
            }
        },
        messages : {
        	name : {
                required : "达人姓名不能为空！"
            },
            content : {
                required : "评论内容不能为空！"
            },
            experttitle : {
                required : "达人头衔不能为空！"
            }
       }

    }
};
/**
 * 合作商业务员保存修改
 */
var UpdateSavaExpertComment = function() {
    var url;
    if (ISTYPE == "add") {
        url = "businessman/expert/add.jhtml";
    } else {
        url = "businessman/expert/update.jhtml";
    }
    var data = $('#addExpertCommentForm').serializeArray();
    //form转成json
    data = jsonFromt(data);
    //post提交请求
    $.post(url, data, function(result) {
        if (result.success) {
            showSmReslutWindow(result.success, result.msg);
            setTimeout(function() {
                muBack();
            }, 1000);
        } else {
            window.messager.warning(result.msg);
        }
    }, "json");
}
/**
 * 转换from表单为json数据格式
 */
function jsonFromt(data) {
    var json = {};
    for (var i = 0; i < data.length; i++) {
        json[data[i].name] = data[i].value;
    }
    return json;
}

/**
 * 提交表单方法
 */
var formHandle = {
        "addExpertCommentForm" : UpdateSavaExpertComment
};
function formSubmit(form) {
    return formHandle[form];
}

