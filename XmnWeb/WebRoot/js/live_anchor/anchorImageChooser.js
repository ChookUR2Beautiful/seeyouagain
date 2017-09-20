var recordList;
var initListUrl = "anchorBusiness/manage/anchorImage/anchorImageInit/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="anchor/manage/init.jhtml" target="right">主播管理</a> >主播相册',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
});

/**
 * 加载页面数据
 */
function pageInit(){
	recordList = $("#recordList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
}

function success(data, obj) {
	var formId = "shareForm", title = "主播相册列表", subtitle = "个图片";
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
		checkable : true,
		// 操作列
		handleCols : {
			
		},
		cols : [ {
			title : "图片编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "排序值",
			name : "sort",
			type : "string",
			width : 180
		} ,{
			title : "图片",
			name : "anchorImage",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
			}
		}, {
			title : "文字",
			name : "imageComment",
			type : "string",
			width : 180
		} ]
	}, permissions);
}

/**
 * 绑定确定按钮点击事件
 */
$("#ensure").click(function(){
	debugger;
	var ids =recordList.getIds();
	if(!ids){
		showWarningWindow("warning","请选择一条记录",9999);
		return ;
	}else{
		var idArray=ids.split(',');
		if(idArray.length>5){
			showWarningWindow("warning","最多可以选择5条记录",9999);
			return ;
		}
	}
	
	if($("#contentKey").length){
		$.post("anchorBusiness/manage/anchorImage/getByIds.jhtml",{"ids":ids},function(data,status){
			if(status=='success'){
				$.each(data.data,function(i,item){
					$("#datas .active").append("<li><img src='"+imgRoot+item.anchorImage+"' value='"+item.anchorImage+"' /><em class='icon-remove delete-img'></em></li>");
				})
			}
			
		});
	}else{
		//
		var anchorImage=recordList.getValue('anchorImage');
		$.each(anchorImage,function(n,obj){
			$("#datas .active").append("<li><img src='"+imgRoot+obj+"' /><em class='icon-remove delete-img'></em></li>");
		});
	}
	anchorImageChooser.close();
});


 