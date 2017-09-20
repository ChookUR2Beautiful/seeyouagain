var advertisingList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	advertisingList = $('#advertisingList').page({
		url : 'business_cooperation/advertising/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		param : {type : "3"},
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(advertisingList.getIds());
	});
	
	inserTitle(' > 合作商管理 > <a href="business_cooperation/advertising/init.jhtml" target="right">广告轮播图管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","business_cooperation/advertising/export.jhtml");
		$form[0].submit();
	});
	
	// 区域
	var ld = $("#ld").areaLd({
		isChosen : true,
		width : "100%"
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
		var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个广告&nbsp;</font></caption>';
	

		obj.find('div').eq(0).scrollTablel({
		caption : captionInfo,
		// 数据
		data : data.content,
		// 数据行
		cols : [ {
			title : "广告图片",// 标题
			name : "adbpicUrl",
			width : 180,// 宽度
			type : "string",// 数据类型
			customMethod:function(value,data){
				return '<img style="width:50px;height:50px;" src="'+value+'"/>';
			}
		}, {
			title : "广告文本",// 标题
			name : "content",
			width : 250,// 宽度
			type : "string"// 数据类型
		}, {
			title : "广告链接",// 标题
			name : "adburl",
			width : 200,// 宽度
			type : "string",// 数据类型
			customMethod:function(value,data){
				return '<a href='+value+' target="_blank">广告连接</a>';
			}
		}, {
			title : "排序",// 标题
			name : "sort",
			width : 80,// 宽度
			type : "string"// 数据类型
		}, {
			title : "上线状态",// 标题
			name : "isshowText",
			width : 150,// 宽度
			type : "string"// 数据类型
		}, {
			title : "区域",// 标题
			name : "areaText",
			width : 180,// 宽度
			type : "string"// 数据类型
		}, {
			title : "类型",// 标题
			name : "typeText",
			width : 150,// 宽度
			type : "string"// 数据类型
		}, {
			title : "备注",// 标题
			name : "remarks",
			width : 200,// 宽度
			type : "string"// 数据类型
		}],
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ {
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
							url:'business_cooperation/advertising/update/init.jhtml',
							position:"60px",// 模态框显示位置
							width:"70%"
						},
					param : [ "id" ],// 参数
					permission : "update"// 列权限
				}
			} ]
		}
	}, permissions);
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
			url : 'business_cooperation/advertising/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					advertisingList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

