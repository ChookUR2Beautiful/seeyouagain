/**
 * 分享发放优惠券
 */
var shareList;
$(document).ready(function() {
	$("#shareAreaInfo").areaLd({
		isChosen : true,
		// isMultiple : true,// 区域是否支持多选（在isChosen为true时），
		separator : ",",
		showConfig : [ {
			name : "province",
			tipTitle : "--省--",
		}, {
			name : "city",
			tipTitle : "--市--",
		}, {
			name : "area",
			tipTitle : "--区--",
		} ]
	});
	shareList = $('#shareList').page({
		url : 'coupon/couponissue/share/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'shareForm',
		param : {
			activityType : "5"
		}
	});
	$("input[data-bus=reset]").click(function() {
		$("#shareAreaInfo").find("select").trigger("chosen:updated");
	});
});

function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;分享发放优惠列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total + '】个分享发放优惠&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#shareForm").serialize());
	updateAddBtnHref("#addShare", callbackParam);
	obj
			.find('div')
			.eq(0)
			.scrollTablel(
					{
						identifier : "issueId",
						callbackParam : callbackParam,
						data : data.content,
						caption : captionInfo,
						// 操作列
						handleCols : {
							title : "操作",// 标题
							// handlePermission : ["sx","xx"],// 需要用到checkbox
							queryPermission : [ "shareUpdate", "shareView" ],// 不需要选择checkbox处理的权限
							width : 150,// 宽度
							// 当前列的中元素
							cols : [
									{
										title : "启动状态",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "coupon/couponissue/share/view.jhtml",
											param : [ "issueId" ],
											permission : "shareView"
										},
										customMethod : function(value, data) {
											switch (data.status) {
											case 0:
												return '<a href="javascript:updateStatuShare('
														+ data.issueId
														+ ','
														+ 1 + ');">启动</a>';
											case 1:
												return '<a href="javascript:updateStatuShare('
														+ data.issueId
														+ ','
														+ 0 + ');">停止</a>';
											case 2:
												var result = "";
												if (data.isGenerate == 0) {
													result = '<a href="javascript:publishShare('
															+ data.issueId
															+ ","
															+ data.activityType
															+ ');">发行</a>'
												}
												return result;
											}
										}
									},
									{
										title : "修改",// 标题
										linkInfoName : "href",
										linkInfo : {
											href : "coupon/couponissue/share/update/init.jhtml",
											param : [ "issueId" ],
											permission : "shareUpdate"
										}
									},
									{
										title : "查看",// 标题
										linkInfoName : "modal",
										linkInfo : {
											modal : {
												url : "coupon/couponissue/share/view.jhtml",
												position : "",// 模态框显示位置
												width : "800",
												title : "查看分享发放优惠" // 模态框标题
											},
											param : [ "issueId" ],
											permission : "shareView"
										}
									} ]
						},
						cols : [
								{
									title : "消费金额",
									name : "amount",
									type : "string",
									leng : 8
								},
								{
									title : "次数限制",
									name : "maxTimes",
									type : "string",
									leng : 8
								},
								{
									title : "活动区域",
									name : "areaText",
									type : "string",
									leng : 8
								},
								{
									title : "针对用户",
									name : "rate",
									type : "string",
									leng : 8,
									customMethod : function(value, data) {
										switch (data.rate) {
										case 1:
											return "新用户";
											break;
										/*
										 * case 2: return "首满"; break;
										 */
										case 3:
											return "全部用户";
											break;
										}
									}
								},
								{
									title : "订单后刮刮卡发行量",
									name : "issueVolumeOrder",
									type : "string",
									leng : 8,
									customMethod : function(value, data) {
										if (data.isGenerate == 1) {
											var url = "coupon/couponissue/share/mingxi/init.jhtml?issueId="
													+ data.issueId + "&type=1";
											return '<a href="' + url + '&'
													+ callbackParam + '">'
													+ data.issueVolumeOrder
													+ '</a>';
										} else {
											return data.issueVolumeOrder;
										}
									}
								},
								{
									title : "分享刮刮卡发行量",
									name : "issueVolumeShare",
									type : "string",
									leng : 8,
									customMethod : function(value, data) {
										if (data.isGenerate == 1) {
											var url = "coupon/couponissue/share/mingxi/init.jhtml?issueId="
													+ data.issueId + "&type=2";
											return '<a href="' + url + '&'
													+ callbackParam + '">'
													+ data.issueVolumeShare
													+ '</a>';
										} else {
											return data.issueVolumeShare;
										}

									}
								}/*
									 * , { title : "分享刮刮卡", name :
									 * "examineinfo", type : "string", // width :
									 * 400, leng : 8 }
									 */, {
									title : "活动开始时间",
									name : "dateStart",
									type : "string",
									leng : 8
								}, {
									title : "活动结束时间",
									name : "dateEnd",
									type : "string",
									leng : 8
								}, {
									title : "状态",
									name : "status",
									type : "string",
									leng : 8,
									customMethod : function(value, data) {
										switch (data.status) {
										case 0:
											return "停止";
											break;
										case 1:
											return "启动";
											break;
										case 2:
											return "待启动";
											break;
										}
									}
								},{
									title:"优惠券类型",
									name:"ctypeStr",
									type:"string"
								}
								]
					}, permissions);
}

/**
 * 发行优惠券
 * 
 * @param issueId
 * @param activityType
 */
function publishShare(issueId, activityType) {
	showSmConfirmWindow(function() {
		data = {
			'issueId' : issueId,
			'activityType' : activityType
		}
		$.ajax({
			type : 'post',
			url : "coupon/couponissue/youhuima/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				shareList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}, "确认发行？发行后活动自动启动！");
}

/**
 * 更新状态
 * 
 * @param issueId
 * @param status
 */
function updateStatuShare(issueId, status) {
	var title;
	if (status === 1) {
		title = "确定要启动？";
	}
	if (status === 0) {
		title = "确定要停止？";
	}
	showSmConfirmWindow(function() {
		data = {
			'issueId' : issueId,
			'status' : status
		}
		$.ajax({
			type : 'post',
			url : "coupon/couponissue/updateCouponIssueStatus.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				shareList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}, title);
}
