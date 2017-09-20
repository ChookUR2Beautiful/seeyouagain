var ISTYPE;
ISTYPE = $("#isType").val();
$(document).ready(function() {
	if (ISTYPE == "add") {
		inserTitle(' >添加寻蜜客成员资料','bannerList', false);
	}else{
		inserTitle(' >修改寻蜜客成员资料','bannerList', false);
		
		$("#uid").attr("readonly", true);
		if($("#sex").val()==1){
			$("input[name='sex']").get(0).checked=true; 
		}else{
			$("input[name='sex']").get(1).checked=true; 
		}
	}
    //验证提交添加或修改的数据
    initValidator();
	//初始化上传图片
	initPic();
});
/**
 * 初始化上传图片
 */
function initPic() {
	//导航图
	$("#pic").uploadImg({
		urlId : "picURL",
		showImg : $('#picURL').val()
	});
}
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
    var url = contextPath + '/xmer/manage/init.jhtml';
    location.href = url;
}
/**
 * 验证并且提交添加或修改的数据
 */
var formId = [ "editXmerForm" ];
function initValidator() {
    for (var i = 0; i < formId.length; i++) {
        validate(formId[i], valiinfo[formId[i]], formSubmit(formId[i]));
    }
}
/**
 * 验证方法
 */
var valiinfo = {
    "editXmerForm" : {
        rules : {
        	name : {
                required : true
            },
            phoneid : {
                required : true
            },
            sdate : {
                required : true
            },
            age : {
                required : true
            },
            email : {
            	required : true
            },
            weixinid : {
            	required : true
            },
            achievement : {
            	required : true
            }
        },
        messages : {
        	name : {
                required : "姓名不能为空"
            },
            phoneid : {
                required : "手机号不能为空"
            },
            sdate : {
                required : "加入时间不能为空"
            },
            age : {
                required : "年龄不能为空"
            },
            email : {
            	required : "邮箱不能为空"
            },
            weixinid : {
            	required : "微信号不能为空"
            },
            achievement : {
            	required : "成就不能为空"
            }
        }

    }
};
/**
 * 寻蜜客资料保存修改
 */
var UpdateSavaXmer = function() {
    var url;
    if (ISTYPE == "add") {
        url = "xmer/manage/add.jhtml";
    } else {
        url = "xmer/manage/update.jhtml";
    }
    var data = $('#editXmerForm').serializeArray();
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
        "editXmerForm" : UpdateSavaXmer
};
function formSubmit(form) {
    return formHandle[form];
}
/**
 * 时间
 */
$(function() {
	var today = new Date();
	var todaystr = addDate(today, 0);
	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var val = d.getFullYear() + "-" + month + "-" + day;
		return val;
	}
});
