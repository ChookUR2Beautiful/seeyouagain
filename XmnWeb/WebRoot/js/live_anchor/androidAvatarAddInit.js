var formId = "androidAvatarForm";
var imgRoot = $("#fastfdsHttp").val();
var picNum = $("#robotAvatarsNum").val() | 0;
var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	validate(formId, {
		rules : {
			imageComment : {
				required : true
			}
		},
		message:{
			imageComment:{
				required:"请输入文字说明"
			}
		}
	}, anchorImageSave);
	
	// 页面图片
	$("#anchorImageDiv").uploadImg({
		urlId : "anchorImage",
		showImg : $('#anchorImage').val()
	});
	
	//机器人头像上传初始化
	androidAvatarInit();
	
});




/**
 * 保存礼物信息
 */
function anchorImageSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	url = "liveAndroid/manage/addAvatar" + suffix;
	var data = $('#' + formId).serializeArray();
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
			if (data.success) {
				setTimeout(function() {
					window.location.href = "liveAndroid/manage/init.jhtml";
				}, 2000);
			}
			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	var length=$("#robotAvatars>div").length;
	if(length == null || length==0){
		showWarningWindow("warning","请上传机器人头像 !",9999);
		rsult=false;
		return ;
	}
	
	return result;
}

/**
 * 机器人头像上传初始化
 */
function androidAvatarInit(){
	
	//增加头像图片
	$("#addPic").click(function(){
		if($("#robotAvatars>div").length > 9){
			showWarningWindow("warning","一次最多只能添加10张图片！");
		}else{
			var temp = $("#robotAvatarTemp").clone(true).removeAttr("id").show();
			temp.find("input[name='anchorImage']").attr("name", "robotAvatars[" + picNum + "].anchorImage").attr("id","anchorImage" + picNum);
			temp.find("input[name='id']").attr("name","robotAvatars[" + picNum + "].id");
			temp.find("input[name='imageType']").attr("name","robotAvatars[" + picNum + "].imageType");
			temp.find("input[name='createTime']").attr("name","robotAvatars[" + picNum + "].createTime").attr("value","");
			temp.find(".pic").uploadImg({
				urlId : "anchorImage" + picNum
			});
			temp.find(".removebtn").click(function(){
				$(this).parent().remove();
			});
			$("#robotAvatars").append(temp);
			picNum++;
		}
	});
	
	//图片初始化
	/*$("#sellerPics>div").each(function(i,n){
		$(this).find(".pic").uploadImg({
			urlId : "sellerPicUrl" + i
		});
		$(this).find(".removebtn").click(function(){
			$(this).parent().remove();
		});
	});*/
	
}

