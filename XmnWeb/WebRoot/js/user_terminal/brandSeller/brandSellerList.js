var brandSellerList;
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 用户端管理 > <span><a href="user_terminal/brandSeller/init.jhtml" target="right">品牌店管理</a>',
			'brandSeller', true);

	brandSellerList = $("#brandSellerList").page({
		url : 'user_terminal/brandSeller/init/list.jhtml',
		success : successScroll,
		// success : successByGrid,
		pageBtnNum : 10,
		paramForm : 'brandSellerForm'
	});

	$('#delete').click(function() {
		console.log(brandSellerList.getIds());
		remove(brandSellerList.getIds());
	});

	$("#export").click(
			function() {
				$form = $("#brandSellerForm").attr("action",
						"user_terminal/brandSeller/export.jhtml");
				$form[0].submit();
			});

	// 区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});

	// 重置,清空区域选择
	$("input[data-bus=reset]").click(function() {
		$("#ld").find("select").trigger("chosen:updated");
//		resetIsBrandButtons();
	});

});

/**
 * 重置时，设置第一个按钮为选中
 */
function resetIsBrandButtons() {
	var buttons = $("button.status");
	for (i = 0; i < buttons.length; i++) {
		$(buttons[i]).removeClass("btn-success").addClass("btn-default");
	}
	$(buttons).first().removeClass("btn-default").addClass("btn-success");
}
/**
 * 改变筛选状态选中时显示按钮
 * 
 * @param object
 * @param status
 */
function queryStatus(object, status) {
	var buttons = $("button.status");
	for (i = 0; i < buttons.length; i++) {
		$(buttons[i]).removeClass("btn-success").addClass("btn-default");
	}
	$(object).removeClass("btn-default").addClass("btn-success");
	$("input[name='isBrand']").val(status);
	brandSellerList.reload();
}

/**
 * 更新品牌店的状态
 * 
 * @param id
 * @param status
 */
function updateStatus(id, status) {
	var url, msg;
	if (status == 1) {// 上线
		url = "user_terminal/brandSeller/online.jhtml";
		msg = "确认要上线？"
	}
	if (status == 2) {// 下线
		url = "user_terminal/brandSeller/offline.jhtml";
		msg = "确认要下线？"
	}
	showSmConfirmWindow(function() {
		$.ajax({
			method : "POST",
			url : url,
			data : {
				"brandId" : id
			},
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				brandSellerList.reload();
			},
		});
	}, msg);
}
function successScroll(data, obj) {
	// var checkable = permissions.updateSellerStatus=="true";
	var formId = "brandSellerForm", title = "品牌店列表", subtitle = "个品牌店";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	updateAddBtnHref("#addbtn", callbackParam);

	obj
			.find('div')
			.eq(0)
			.scrollTablel(
					{
						identifier : "brandId",
						callbackParam : callbackParam,
						data : data.content,
						caption : captionInfo,
						// checkable : checkable,
						// 操作列
						handleCols : {
							title : "操作",// 标题
							queryPermission : [ "update", "online", "offline" ,'branchSellerList'],// 不需要选择checkbox处理的权限
							width : 180,// 宽度
							// 当前列的中元素
							cols : [
									{
										title : "下线",// 标题
										linkInfoName : "method",
										linkInfo : {
											method : "updateStatus",
											param : [ "brandId" ],
											customParam : [ "2" ],
											permission : "offline"
										},
										customMethod : function(value, data) {
											var result = '<a href="javascript:void(0)" class="hidden"></a>';
											if (data.isBrand != undefined
													&& data.isBrand == 1) {
												result = value;
											}
											return result;
										}
									},
									{
										title : "上线",// 标题
										linkInfoName : "method",
										linkInfo : {
											method : "updateStatus",
											param : [ "brandId" ],
											customParam : [ "1" ],
											permission : "online"
										},
										customMethod : function(value, data) {
											var result = '<a href="javascript:void(0)" class="hidden"></a>';
											if (data.isBrand != undefined
													&& (data.isBrand == 0 || data.isBrand == 2)) {
												result = value;
											}
											return result;
										}
									},
									{
										title : "修改",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "user_terminal/brandSeller/update/init.jhtml",
											param : [ "brandId" ],
											permission : "update"
										}

									/*
									 * , customMethod : function(value, data){
									 * value = $(value);
									 * value.attr("href",$(value).attr("href")+"&viewType=editSellerPending");
									 * value = value[0].outerHTML; return value; }
									 */
									},
									{
										title : "分店管理",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "user_terminal/brandSeller/branchSeller/init.jhtml",
											param : [ "brandId" ],
											permission : "branchSellerList"
										}
									} ]
						},
						cols : [
								{
									title : "品牌店编号",
									name : "brandId",
									type : "string",
									width : 100
								},
								{
									title : "品牌店名称",
									name : "brandName",
									type : "string",
									width : 150
								},
								{
									title : "非新用户满返活动描述",
									name : "activCont",
									type : "string",
									width : 200
								},
								{
									title : "新用户满返活动描述",
									name : "activNewUser",
									type : "string",
									width : 200
								},
								{
									title : "品牌店图片描述",
									name : "bewrite",
									type : "string",
									width : 150
								},
								{
									title : "品牌店图片",
									name : "picUrl",
									type : "string",
									width : 150,
									customMethod : function(value, data) {
										return '<img style="width:50px;height:50px;" src="'
												+ imgRoot + value + '"/>';
									}
								}, {
									title : "是否折上折",
									name : "agioAgio",
									type : "string",
									width : 90,
									customMethod : function(value, date) {
										var agio = date.agioAgio;
										if (agio == 0) {
											return "否";
										} else if (agio == 1) {
											return "是";
										}
										return "-"

									}
								}, {
									title : "返利",
									name : "rebatePercentage",
									type : "string",
									width : 100,
									customMethod : function(value, data) {
										return value + "%";
									}
								}, {
									title : "开始时间",
									name : "dateStart",
									type : "string",
									width : 160
								}, {
									title : "结束时间",
									name : "dateEnd",
									type : "string",
									width : 160
								}, {
									title : "排序",
									name : "sort",
									type : "string",
									width : 80
								}, {
									title : "全单折扣",
									name : "agioPercentText",
									type : "string",
									width : 100
								}, {
									title : "上线状态",
									name : "isBrandText",
									type : "string",
									width : 80
								}, {
									title : "区域",
									name : "areaText",
									type : "string",
									width : 400
								} ]
					}, permissions);
}

function success(data, obj) {

	// 不管是否有数据存在都要更新添加按钮的href的值
	// 添加页面，带返回按钮
	var callbackParam = "&isBackButton=true&callbackParam="
			+ getFormParam($("#brandSellerForm").serialize());
	// 更新添加按钮的URL
	updateAddBtnHref("#addbtn", callbackParam);

	var html = [];
	html
			.push('<table class="table table-hover table-bordered table-striped info" >');
	html
			.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">品牌店列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if (!isEmptyObject(permissions)) {
		/*
		 * html.push('<th><input type="checkbox" /></th>');
		 */html.push('<th>操作</th>');
	}

	html.push('<th>品牌店编号</th>');
	html.push('<th>品牌店名称</th>');
	// html.push('<th>品牌商家logo缩略图URL</th>');
	html.push('<th>品牌店图片</th>');
	html.push('<th>品牌店图片描述</th>');
	// html.push('<th>创建人</th>');
	// html.push('<th>创建时间</th>');
	// html.push('<th>修改人</th>');
	// html.push('<th>修改时间</th>');
	html.push('<th>是否生效</th>');
	html.push('<th>返利</th>');
	html.push('<th>开始时间</th>');
	html.push('<th>结束时间</th>');
	html.push('<th>排序</th>');
	// html.push('<th>是否全国</th>');
	// html.push('<th>城市</th>');
	html.push('<th>区域</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if (null != data && data.content.length > 0) {
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');

			if (!isEmptyObject(permissions)) {
				/*
				 * html.push('<th><input type="checkbox" val=' +
				 * data.content[i].brandId + ' /></th>');
				 */
				html.push('<td>');
				if (permissions.update == 'true') {
					html
							.push('<a href="user_terminal/brandSeller/update/init.jhtml?brandId='
									+ data.content[i].brandId
									+ callbackParam
									+ '">修改</a>&nbsp;');
				}
				/*
				 * if (permissions.del== 'true') { html.push('&nbsp;<a
				 * href="javascript:remove(' + data.content[i].brandId + ')">删除</a>'); }
				 */
				html.push('</td>');
			}

			html.push('<td>'
					+ (undefined == data.content[i].brandId ? "-"
							: data.content[i].brandId) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].brandName ? "-"
							: data.content[i].brandName) + '</td>');
			// html.push('<td>'
			// + (undefined == data.content[i].breviary ? "-"
			// : '<img style="width:50px;height:50px;" src="'
			// +imgRoot+data.content[i].breviary + '"/>')
			// +'</td>');
			html.push('<td>'
					+ (undefined == data.content[i].picUrl ? "-"
							: '<img style="width:50px;height:50px;" src="'
									+ imgRoot + data.content[i].picUrl + '"/>')
					+ '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].bewrite ? "-"
							: data.content[i].bewrite) + '</td>');
			// html.push('<td>'
			// + (undefined == data.content[i].creator ? "-"
			// : data.content[i].creator) + '</td>');
			// html.push('<td>'
			// + (undefined == data.content[i].dateCreated ? "-"
			// : data.content[i].dateCreated) + '</td>');
			// html.push('<td>'
			// + (undefined == data.content[i].updator ? "-"
			// : data.content[i].updator) + '</td>');
			// html.push('<td>'
			// + (undefined == data.content[i].dateUpdated ? "-"
			// : data.content[i].dateUpdated) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].isBrand ? "-"
							: data.content[i].isBrandText) + '</td>');
			html
					.push('<td>'
							+ (undefined == data.content[i].rebatePercentage ? "-"
									: data.content[i].rebatePercentage) + "%"
							+ '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].dateStart ? "-"
							: data.content[i].dateStart) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].dateEnd ? "-"
							: data.content[i].dateEnd) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].sort ? "-"
							: data.content[i].sort) + '</td>');
			/*
			 * html.push('<td>' + (undefined == data.content[i].isAllText ?
			 * "-" : data.content[i].isAllText) + '</td>');
			 */
			html.push('<td>'
					+ (undefined == data.content[i].areaText ? "-"
							: data.content[i].areaText) + '</td>');
			/*
			 * html.push('<td>' + (undefined == data.content[i].areaTitle ?
			 * "-" : data.content[i].areaTitle) + '</td>');
			 */
			html.push('</tr>');
		}
	} else {
		html.push('<tr>');
		html.push('<td colspan="12">暂无数据</td>');
		html.push('</tr>');
	}

	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

function successByGrid(data, obj) {
	console.log(obj);
	obj.find('div').eq(0).html($.renderGridView(brandSellerModel, data));
}

function remove(ids) {
	if (!ids) {
		showWarningWindow("warning", "请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/brandSeller/delete.jhtml' + '?t='
					+ Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					brandSellerList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}