var brandBranchSellerList;
$(function() {
	inserTitle(
			' > 用户端管理 > <a href="user_terminal/brandSeller/init.jhtml" target="right">品牌店管理</a> > 分店管理',
			'brandSeller', true);

	brandBranchSellerList = $("#brandBranchSellerList").page({
		url : 'user_terminal/brandSeller/branchSeller/init/list.jhtml',
		success : successScroll,
		pageBtnNum : 10,
		paramForm : 'brandSellerForm'
	});

	// 重置,清空区域选择
	$("input[data-bus=reset]").click(function() {
		$("#ld").find("select").trigger("chosen:updated");
		// resetIsBrandButtons();
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
	$("input[name='brandId']").val(status);
	brandBranchSellerList.reload();
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

function bindBrandSeller(sellerid, brandId, type) {
	var url, msg;
	if (type == "add") {
		url = "user_terminal/brandSeller/branchSeller/add.jhtml";
		msg = "添加分店";
	} else {
		url = "user_terminal/brandSeller/branchSeller/delete.jhtml";
		msg = "移除分店";
	}
	showSmConfirmWindow(function() {
		$.ajax({
			method : "POST",
			url : url,
			data : {
				"sellerid" : sellerid,
				"brandId" : brandId
			},
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
				brandBranchSellerList.reload();
			},
		});
	}, msg);
}

function isAddedSeller() {
	return !$("input[name='brandId']").val() == "";
}

function successScroll(data, obj) {
	// var checkable = permissions.updateSellerStatus=="true";
	var formId = "brandSellerForm", title = "未添加为分店的商家列表", subtitle = "个商家";
	if (isAddedSeller()) {
		title = "已添加为分店的商家列表"
	}
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
							queryPermission : [ "addBranchSeller", "delBranchSeller" ],// 不需要选择checkbox处理的权限
							width : 180,// 宽度
							// 当前列的中元素
							cols : [ {
								title : $("input[name='brandId']").val() != "" ? "移除"
										: "添加",// 标题
								linkInfoName : "method",
								linkInfo : {
									method : "bindBrandSeller",
									param : [ "sellerid" ],
									customParam : [
											brandId,
											$("input[name='brandId']").val() != "" ? "delete"
													: "add" ],
									permission : $("input[name='brandId']")
											.val() != "" ? "delBranchSeller"
											: "addBranchSeller"// "update"
								}
							} ]
						},
						cols : [ {
							title : "商家编号",
							name : "sellerid",
							type : "string",
							width : 100
						}, {
							title : "商家名称",
							name : "sellername",
							type : "string",
							width : 150
						}, {
							title : "所属连锁店",
							name : "lssellername",
							type : "string",
							width : 200
						}, {
							title : "所属品牌店",
							name : "brandName",
							type : "string",
							width : 200
						} ]
					}, permissions);
}

function successByGrid(data, obj) {
	console.log(obj);
	obj.find('div').eq(0).html($.renderGridView(brandSellerModel, data));
}