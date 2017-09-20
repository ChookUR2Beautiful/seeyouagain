var bannerAdvertisingList;
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(function() {
	
	bannerAdvertisingList = $('#bannerAdvertisingList').page({
		url : 'user_terminal/bannerAdvertising/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(bannerAdvertisingList.getIds());
	});
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/bannerAdvertising/init.jhtml" target="right">活动banner</a>','userSpan',true);

/*	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/advertising/export.jhtml");
		$form[0].submit();
	});*/
	// 区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});

function queryBanner(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    var data = {status:parms};
    $("#isshow").val(parms);
    bannerAdvertisingList.reload();
	$(object).attr("class", "btn btn-success");
}


function success(data, obj) {
	var formId = "searchForm",title = "banner活动列表",subtitle="个活动banner";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+title+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】'+subtitle+'&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#"+formId).serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["update"],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			cols : [{
				title : "上线",// 标题
				linkInfoName : "method",
				linkInfo : {
					method :"bannerIsOnline",
					param : ["id"],// 参数
					customParam:["0"],
					permission : "online"// 单列权限
				},
				customMethod : function(value, data) {
                 var isshow=data.isshow;
                 if(isshow==1||isshow==2){
                	 return value+"&nbsp;&nbsp;";
                 }else{
                	return "";
                 }
				}
			},{
				title : "下线",// 标题
				linkInfoName : "method",
				linkInfo : {
					method :"bannerIsOffline",
					param : ["id"],// 参数
					customParam:["2"],
					permission : "offline"// 单列权限
				},
				customMethod : function(value, data) {
	                 var isshow=data.isshow;
	                 if(isshow==0){
	                	 return "&nbsp;&nbsp;"+value;
	                 }else{
	                	return "";
	                	
	                 }
					}
			},{
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "user_terminal/bannerAdvertising/update/init.jhtml",// url
						position : "",// 模态框显示位置
						width : "50%", // 模态框宽度
						title : "修改活动banner" //模态框标题
					},
					param : ["id"],
					permission : "update"
				}
			}] 
		},
		cols:[{
			title : "广告图片",
			name : "adbpic",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "低分辨率广告图片",
			name : "picLow",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "中分辨率广告图片",
			name : "picMiddle",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "分享图片",
			name : "share_img",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		},{
			title : "广告标题",
			name : "content",
			type : "string",
			width : 150
		},{
			title : "副标题",
			name : "subtitle",
			type : "string",
			width : 150
		},{
			title : "广告链接",
			name : "adburl",
			type : "string",
			width : 180
		},{
			title : "分享标题",
			name : "shareTitle",
			type : "string",
			width : 180
		},
		{
			title : "排序",
			name : "sort",
			type : "string",
			width : 150
		},{
			title : "状态",
			name : "isshowText",
			type : "string",
			width : 150
		},{
			title : "区域",
			name : "areaText",
			type : "string",
			width : 200
		},{
			title : "分享文案",
			name : "share_cont",
			type : "string",
			width : 400
		},{
			title : "备注",
			name : "remarks",
			type : "string",
			width : 400
		}]},permissions);
}




/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/advertising/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					bannerAdvertisingList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}
/**
 * 恢复订单
 * @param {} id
 * @param {} bid
 * @param {} money
 * @param {} remarks
 */		
function bannerIsOnline(id,isshow) {
		showSmConfirmWindow(function(){
			var URL='user_terminal/bannerAdvertising/updateBannerStatusOnLine.jhtml' + '?t=' + Math.random();
			bannerIsOnlineAjax(id,isshow,URL);
		}, "确定上线吗？");
}
function bannerIsOffline(id,isshow) {
	    showSmConfirmWindow(function(){
		   var URL='user_terminal/bannerAdvertising/updateBannerStatusOffLine.jhtml' + '?t=' + Math.random();
		   bannerIsOnlineAjax(id,isshow,URL);
	   }, "确定下线吗？");
}
function  bannerIsOnlineAjax(id,isshow,URL){
	$.ajax({
		type : 'post',
		url : URL,
		data : {
			'id':id,
			"isshow":isshow
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {			 				
          	$('#prompt').hide();
			if (data.success) {
				bannerAdvertisingList.reload();
		    }			
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
		}
	});
}
