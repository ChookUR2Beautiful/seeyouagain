$(function(){
	startImageValidate();
	$("#adbpicUpload").uploadImg({  //显示图片上传事默认显示的样式图
		urlId : "pic",
		showImg : $("#pic").val()
	});
/*	$("#adbpicLowUpload").uploadImg({  //低分辨率启动图
		urlId : "picLow",
		showImg : $("#picLow").val()
	});
	$("#adbpicMiddleUpload").uploadImg({  //中分辨率启动图片
		urlId : "picMiddle",
		showImg : $("#picMiddle").val()
	});*/
	
	//指定区域
	initAreaListData();
	
	//初始化显示
	initShowType();
 });


/**
 * 初始化联动信息
 * 
 * @param {}
 *            areaInfo
 */
function initAreaListData() {
	if($('#isType').val() ==  'add'){
		$("div[name='area']").areaLd({
	//	$("#area").areaLd({
			isChosen : true,
			separator : ",",
			showConfig : [ {
				name : "provinceId",
				tipTitle : "--省--"
			}, {
				name : "cityId",
				tipTitle : "--市--"
			} ]
		});
	} else {
		$("#updateArea").each(function(index, item) {
			var ld = $(this).areaLd({
				isChosen : true,
				separator : ",",
				showConfig : [ {
					name : "provinceId",
					tipTitle : "--省--"
				}, {
					name : "cityId",
					tipTitle : "--市--"
				} ]
			});
		});

	}
}

//初始化　位置：启动页, 直播间, 类型: 图片->广告图片,视频链接
function initShowType() {
	var showLocal = $("input[name='showLocal']:checked").val();
    if (showLocal == undefined){
    	$("input[type=radio][name=showLocal][value='1']").prop("checked",'checked');
    }
    
	var showType = $("input[name='showType']:checked").val();
    if (showType == undefined){
    	$("input[type=radio][name=showType][value='1']").prop("checked",'checked');
    }
	
	$("input[name='showLocal']").trigger("change");
	$("input[name='showType']").trigger("change");
}


$("input[name='showLocal']").on("change", function(){  
	//位置：启动页, 直播间
	var showLocal = $("input[name='showLocal']:checked").val();
	switch (parseInt(showLocal)) {
	case 1:  //直播间
		$("input[name=liveBegin]").prop("checked",false);
	    $("input[name=liveEnd]").prop("checked",false);
	    $("input[name=liveIn]").removeAttr("checked");
		$("#liveShowInfo").hide();
		break;	
	case 2:  //直播间
		$("#liveShowInfo").show();
		break;		
	default://启动页
		$("input[type=checkbox][name=liveBegin]").prop("checked",false);
	    $("input[type=checkbox][name=liveEnd]").prop("checked",false);
	    $("input[type=checkbox][name=liveIn]").prop("checked",false);
	    $("input[name=intervals]").val("");
	    
        $("#liveShowInfo").hide();
		break;
	}
	
});

$("input[name='showType']").on("change", function(){
	var showType = $("input[name='showType']:checked").val();
	switch (parseInt(showType)) {  // 类型: 图片->广告图片,视频链接
	case 1:  //图片
        $("#picUrlInfo").show();
        $("#videoUrlInfo").hide();
        $("input[name=videoUrl]").val("");
		break;	
	case 2: //视频
        $("#videoUrlInfo").show();
        $("#picUrlInfo").hide();
        $("input[name=pic]").val('');
		break;
	default:
		$("#picUrlInfo").show();
        $("#videoUrlInfo").hide();
        $("input[name=videoUrl]").val('');
		break;
	}
	
});


 
 /**
	 * 表单验证
	 */
function startImageValidate(){
 	validate("saveOrUpdateStartImageForm",{
			rules:{
				type:{
						required:true
				},
				atype:{
					required:true,
				},
				index:{
					required:true,
				 	digits:true,
				 	range:[0,2147483647]
				},
//				startUrl:{
//					required:true
//				},
				pic:{
					required:true
				},
				remarks:{
					maxlength:100
				},
				videoUrl:{
					maxlength:800
				},
				status : {
					required:true
				}
			},
			messages:{
				type:{
					required:"客户端类型不能为空!"
				},
				index:{
					required:"排序值不能为空！",
				 	digits:"排序值只能为整数!",
				 	range:"取值为1-2147483647!"
				},
//				startUrl:{
//					required:"启动图链接不能为空!"
//				},
				pic:{
					required:"启动图片不能为空!"
				},
				atype:{
					required:"设备类型不能为空!"
				},
				remarks:{
					maxlength:"备注的最大长度为50字符!"
				},
				videoUrl:{
					maxlength:"视频链接的最大长度为800字符!"
				},				
				status : {
					required:"请选择一种状态!"
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
				url = 'startImageManagerment/startImageSet/add.jhtml' + '?t=' + Math.random();
			}else{
				url = 'startImageManagerment/startImageSet/update.jhtml' + '?t=' + Math.random();
			}
			
			var data = jsonFromt($('#saveOrUpdateStartImageForm').serializeArray());
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
//					$('#triggerModal').modal('hide');
					if (data.success) {
						showSmReslutWindow(data.success, data.msg);
						setTimeout(function() {
							window.location.href="startImageManagerment/startImageSet/init.jhtml";
						}, 1000);
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
	}
	
	/**
	 * 校验区域是否填写
	 * @returns {Boolean}
	 */
	function checkCities(){
		var reuslt = true;
		var selectAray = [ "province", "rangecontent"];
		$.each(selectAray,function(index,name){
			$("#cities").find('select[name$="'+name+'"]').each(function(){
				var val = $(this).val();
				if(val==""){
					setErrorMark($(this),"#cities",name,'#editShareForm',true);
					reuslt = false;
				}
			});
			
		});
		return reuslt;
	}