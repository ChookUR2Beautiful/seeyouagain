$(function(){
	// 行业类别
	$("#tradeSelect").tradeLd({
//		isChosen : true,
		showConfig : [{name:"category",tipTitle:"请选择",width:'48%'},{name:"genre",tipTitle:"请选择",width:'49%'}]
	});
	
	$("#img_high_upload").uploadImg({  //显示图片上传事默认显示的样式图
		urlId : "img_high",
		showImg : $("#img_high").val()
	});
	$("#img_middle_upload").uploadImg({  //显示图片上传事默认显示的样式图
		urlId : "img_middle",
		showImg : $("#img_middle").val()
	});
	$("#img_low_upload").uploadImg({  //显示图片上传事默认显示的样式图
		urlId : "img_low",
		showImg : $("#img_low").val()
	});
	
	startValidate();
 });

 /**
  * 表单验证
  */
function startValidate(){
 	validate("nagivateEditForm",{
			rules:{
				title:{
					required:true,
					maxlength:4
				},
				category:{
					required:true
				},
				genre:{
					required:true
				}
			},
			messages:{
				title:{
					required:"导航标题不能为空!",
					maxlength:"导航标题最大长度为4字符!"
				},
				category:{
					required:"一级类别未填写"
				},
				genre:{
					required:"二级类别未填写"
				}
			}
		},formAjax);
 }
	 
/**
 * Ajax 请求
 */		
function formAjax(){
		var url ;
		if($('#isType').val() ==  'add'){
			//inserTitle(' > 用户端管理  > <a href="user_terminal/navigate/init.jhtml" target="right">分类导航管理</a>','oneLevelNavigate',true);
			url = 'user_terminal/navigate/add.jhtml' + '?t=' + Math.random();
		}else{
			url = 'user_terminal/navigate/update.jhtml' + '?t=' + Math.random();
		}
		
		var data = jsonFromt($('#nagivateEditForm').serializeArray());
		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				// 添加成功后跳转到列表页面
				var url = contextPath +'/user_terminal/navigate/init.jhtml';
				setTimeout(function() {
					location.href = url;
				}, 1000);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
		
//		var data = jsonFromt($('#nagivateEditForm').serializeArray());
//		data = jsonFromt(data);
//		if(!valiImgData('#nagivateEditForm',data)){
//			$.post(url,data,function(result){
//				if(result.success){
//					showSmReslutWindow(result.success, result.msg);
//				//	var callbackParam="&isBackButton=true&callbackParam="+getParam("callbackParam",window.location.search.substr(1));
//					//var url = contextPath + "/businessman/seller/update/init.jhtml?sellerid="+ result.data+callbackParam;
//					var url = contextPath +'/user_terminal/navigate/init.jhtml';
//					setTimeout(function(){
//	        			location.href =url;
//	        		}, 1000);
//				}else{
//					window.messager.warning(result.msg);
//				}
//			},"json");
//		}
}