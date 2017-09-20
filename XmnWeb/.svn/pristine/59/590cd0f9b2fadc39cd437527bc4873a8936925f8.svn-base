var ISTYPE;
var sellerid = $("#sellerid").val();
var sellername = $("#sellername").val();
ISTYPE = $("#isType").val();
$(document).ready(function() {
	$("#sellername").attr("readonly", true);
	if(ISTYPE=="add"){
		$("#znum").attr("readonly", true);
	}else{
		$("#znum").attr("readonly", false);
	}
    //验证提交添加或修改的数据
    initValidator();
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
    var url = contextPath + '/businessman/expert/sellerlabel/init.jhtml?sellerid='+sellerid+'&sellername='+sellername;
    location.href = url;
}
/**
 * 验证并且提交添加或修改的数据
 */
var formId = [ "addCommentLabelForm" ];
function initValidator() {
    for (var i = 0; i < formId.length; i++) {
        validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
    }
}
/**
 * 验证方法
 */
var valiinfo = {
    "addCommentLabelForm" : {
        rules : {
        	labelname : {
                required : true,
                remote:{
	                        url:"businessman/expert/sellerlabel/vaillabelname.jhtml?sellerid="+sellerid,//后台处理程序
	                        type:"post",                        //数据发送方式
	                        dataType:"json", //接受数据格式  
	                        data:{labelname:function(){return $("#labelname").val();},istype:ISTYPE}
	                    }
                },
            znum : {
                required :  function(){
                    if(ISTYPE=="add"){
                        return false;
                    }
                    return true;
                },
                digits : function(){
                    if(ISTYPE=="add"){
                        return false;
                    }
                    return true;
                }
            }
        },
        messages : {
        	labelname : {
                required : "标签名称不能为空！",
                remote : "该商家标签已存在！"
            },
            znum : {
            	 digits : "请输入数字！"
            }
       }

    }
};
/**
 * 商家标签保存修改
 */
var UpdateSavaExpertComment = function() {
    var url;
    if (ISTYPE == "add") {
        url = "businessman/expert/sellerlabel/add.jhtml";
    } else {
        url = "businessman/expert/sellerlabel/update.jhtml";
    }
    var data = $('#addCommentLabelForm').serializeArray();
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
        "addCommentLabelForm" : UpdateSavaExpertComment
};
function formSubmit(form) {
    return formHandle[form];
}

