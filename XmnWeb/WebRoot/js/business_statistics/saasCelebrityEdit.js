var formId = "editForm";
var jsonTextInit;
$(function(){
	
	if($("#id").val()){
		inserTitle(' > <span>编辑角色','saasCelebrityEdit',false);
	}else{
		inserTitle(' > <span>添加角色','saasCelebrityEdit',false);
	}
	
	// 头像
	$("#avatarImg").uploadImg({
		urlId : "avatar",
		showImg : $('#avatar').val()
	});
	
	$("#areaLdId").areaLd({
		isChosen : true,
		showConfig : [{name:"province",tipTitle:"--省--"},{name:"city",tipTitle:"--市--"},{name:"area",tipTitle:"--区--"}]
	});
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//表单校验
	validate(formId, {
		rules : {
			name : {
				required : true
			},
			reviewPrice:{
				required : true
			},
			reviewNum:{
				required : true,
				digits:true
			},
			province:{
				required:true
			},
			city:{
				required:true
			},
			area:{
				required:true
			},
			address : {
				required : true,
				maxlength:200
			},
			phone : {
				required : true
			},
			email :{
				required :true
			},
			describe:{
				required :true
			}
		},
		messages:{
			name:{
				required:"请输入姓名"
			},
			reviewPrice:{
				required : "请输入食评费用"
			},
			reviewNum:{
				required : "请输入食评次数",
				digits:"请输入正整数"
			},
			province:{
				required:"省未填写"
			},
			city:{
				required:"市未填写"
			},
			area:{
				required:"区未填写"
			},
			address:{
				required:"请输入联系地址",
				maxlength:"最多输入200个字符"
			},
			phone :{
				required:"请输入联系方式"
			},
			email:{
				required:"请输入联系邮箱"
			},
			describe:{
				required :"请输入简介"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "saasRole/manage/add" + suffix;
	} else {// 修改操作
		url = "saasRole/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/saasRole/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;

	return result;
}

