var sellerAdvertisingList;
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 商家管理 > <a href="businessman/advertising/init.jhtml" target="right">广告轮番图管理</a>',
			'userSpan', true);
	sellerAdvertisingList = $("#sellerAdvertisingList").page({
		url : 'businessman/advertising/init/list.jhtml',
		pageBtnNum : 10,
		paramForm : 'searchForm',
		success : successScroll
	});
	//批量删除
	$('#delete').click(function() {
		remove(sellerAdvertisingList.getIds());
	});
	
	//导出excel
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","businessman/advertising/export.jhtml");
		$form[0].submit();
	});
	
	// 区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});
	//重置,清空区域选择
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});

function successScroll(data, obj) {
	var formId = "searchForm",title = "广告轮播图列表",subtitle="个广告轮播";
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
				title : "修改",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "businessman/advertising/update/init.jhtml",// url
						position : "",// 模态框显示位置
						width : "50%", // 模态框宽度
						title : "修改广告轮播图" //模态框标题
					},
					param : ["id"],
					permission : "update"
				}
			},] 
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
			title : "广告文本",
			name : "content",
			type : "string",
			width : 150
		},{
			title : "广告链接",
			name : "adburl",
			type : "string",
			width : 180
		},{
			title : "排序",
			name : "sort",
			type : "string",
			width : 150
		},{
			title : "上线状态",
			name : "isshowText",
			type : "string",
			width : 150
		},{
			title : "类型",
			name : "typeText",
			type : "string",
			width : 150
		},{
			title : "区域",
			name : "areaText",
			type : "string",
			width : 200
		},{
			title : "备注",
			name : "remarks",
			type : "string",
			width : 400
		}]},permissions);
}

function success(data, obj) {
	var html = [];
	html
			.push('<table class="table table-hover table-bordered table-striped info" >');
	html
			.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">广告轮播</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if (!isEmptyObject(permissions)) {
//		html.push('<th><input type="checkbox" /></th>');
		html.push('<th>操作</th>');
	}

	html.push('<th>广告图片</th>');
	html.push('<th>广告文本</th>');
	html.push('<th>广告链接</th>');
	html.push('<th>排序</th>');
	html.push('<th>是否显示</th>');
	html.push('<th>类型</th>');
	html.push('<th>区域</th>');
	html.push('<th>备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if (null != data && data.content.length > 0) {
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');

			if (!isEmptyObject(permissions)) {
//				html.push('<th><input type="checkbox" val='
//						+ data.content[i].id + '  /></th>');
				html.push('<td>');
				if (permissions.update == 'true') {
					html
							.push('<a href="javascript:viod(); " data-type="ajax"   data-url="businessman/advertising/update/init.jhtml?id='
									+ data.content[i].id
									+ '"  data-toggle="modal" >修改</a>&nbsp;');
				}
				/*if (permissions.del == 'true') {
					html.push('&nbsp;<a href="javascript:remove('
							+ data.content[i].id + ')">删除</a>');
				}*/
				html.push('</td>');
			}

			html.push('<td>'
					+ (undefined == data.content[i].adbpic ? "-"
							: '<img style="width:50px;height:50px;" src="'
									+ imgRoot + data.content[i].adbpic + '"/>')
					+ '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].content ? "-"
							: data.content[i].content) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].adburl ? "-"
							: data.content[i].adburl) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].sort ? "-"
							: data.content[i].sort) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].isshowText ? "-"
							: data.content[i].isshowText) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].typeText ? "-"
							: data.content[i].typeText) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].areaText ? "-"
							: data.content[i].areaText) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].remarks ? "-"
							: data.content[i].remarks) + '</td>');
			html.push('</tr>');
		}
	} else {
		html.push('<tr>');
		html.push('<td colspan="10">暂无数据</td>');
		html.push('</tr>');
	}

	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
function remove(id) {
	if (!id) {
		showWarningWindow("warning", "请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : "POST",
			url : "businessman/advertising/delete.jhtml" + "?t="
					+ Math.random(),
			data : "id=" + id,
			dataType : "json",
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerAdvertisingList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}