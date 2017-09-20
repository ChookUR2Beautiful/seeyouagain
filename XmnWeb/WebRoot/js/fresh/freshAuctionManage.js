var recordList1;//全部活动
var recordList2;//未开始活动
var recordList3;//进行中活动
var recordList4;//已结束活动
var recordList5;//活动流拍
var initListUrl = "freshAuction/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 积分超市 > <a href="freshAuction/manage/init.jhtml" target="right">竞拍活动管理</a>',
			'userSpan', true);
	
	liveDateInit();
	
	//加载页面数据
	pageInit();

});

/**
 * 加载页面数据
 */
function pageInit(){
	//加载全部
	recordList1 = $("#recordList1").page({
		url : initListUrl,
		success : success1,
		pageBtnNum : 10,
		paramForm : 'searchForm1',
		param : {
			activityType : "5"
		}
	});
	
	//加载初始列表
	recordList2 = $("#recordList2").page({
		url : initListUrl,
		success : success2,
		pageBtnNum : 10,
		paramForm : 'searchForm2',
		param : {
			activityType : "5"
		}
	});
	
	//加载预告列表
	recordList3 = $("#recordList3").page({
		url : initListUrl,
		success : success3,
		pageBtnNum : 10,
		paramForm : 'searchForm3',
		param : {
			activityType : "5"
		}
	});
	
	//加载直播列表
	recordList4 = $("#recordList4").page({
		url : initListUrl,
		success : success4,
		pageBtnNum : 10,
		paramForm : 'searchForm4',
		param : {
			activityType : "5"
		}
	});
	
	//加载历史活动列表
	recordList5 = $("#recordList5").page({
		url : initListUrl,
		success : success5,
		pageBtnNum : 10,
		paramForm : 'searchForm5',
		param : {
			activityType : "5"
		}
	});
	
}

/**
 * 加载全部通告
 * @param data
 * @param obj
 */
function success1(data, obj) {
	var formId = "searchForm1", title = "活动列表", subtitle = "个活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle 
			+'</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
						title : "立即结束",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/terminate.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
							var value="";
					        if(data.state==0 && data.proceedStatus==2){
					            value = "<a href='javascript:confirmTerminate(" + data.id + ")'>" + "立即结束" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "freshAuction/manage/update/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					},
					customMethod : function(value, data){
							var value = "";
							if(data.state==0 && data.proceedStatus==1){
								value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
							}
				            return value;
				    }
				 },
		         {

					title : "编辑",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "freshAuction/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					},
					customMethod : function(value, data){
						var value="";
						var url="freshAuction/manage/update/init.jhtml?id="+data.id;
				        if(data.state==0 && data.proceedStatus==1){
				            value = "<a href='" + url + "'>" + "编辑" + "</a>";
				        }
				        return value;
				    }
				} ,{
					title : "关联订单",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "freshAuction/manage/freshBill/list.jhtml",// url
						param : ["id"],// 参数
						permission : "freshBillList"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var url="fresh/activityOrder/init.jhtml?activityId="+data.id+"&activityType=02";
				        if(data.state==0 && data.proceedStatus==3){
				            value = "<a href='" + url + "'>" + "关联订单" + "</a>";
				        }
				        return value;
				    }
				 }
			]
		},
		cols : [ 
       {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "活动标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "开始时间",
			name : "beginTimeStr",
			type : "string",
			width : 150
		},{
			title : "结束时间",
			name : "endTimeStr",
			type : "string",
			width : 150
		} , {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 150
		}, {title : "起步价",
			name : "startPrice",
			type : "string",
			width : 100
		}, {
			title : "当前最高金额",
			name : "maxPrice",
			type : "string",
			width : 120
		},  {
			title : "参与人数",
			name : "peopleNum",
			type : "string",
			width : 100
		},{
			title : "获奖者",
			name : "winner",
			type : "string",
			width : 180
		},{
			title : "活动状态",
			name : "proceedStatusStr",
			type : "string",
			width : 180
		}, {
			title : "购买列表",
			name : "--",
			type : "string",
			isLink : true,
			width : 150,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "freshAuction/manage/bidding/list/init.jhtml",
				},
				param : ["id"],
				permission : "biddingList",
			},
			customMethod : function(value, data) {
				return $(value).html("查看详情");
			}
		}]
	}, permissions);
}

/**
 * 初始状态
 * @param data
 * @param obj
 */
function success2(data, obj) {
	var formId = "searchForm2", title = "活动列表", subtitle = "个活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
					 {
							title : "立即结束",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "freshAuction/manage/update/terminate.jhtml",// url
								param : ["id"],// 参数
								permission : "update"// 列权限
							},
							customMethod : function(value, data){
								var value="";
						        if(data.state==0 && data.proceedStatus==2){
						            value = "<a href='javascript:confirmTerminate(" + data.id + ")'>" + "立即结束" + "</a>";
						        }
						        return value;
						    }
					 },
					 {
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/delete.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
								var value = "";
								if(data.state==0 && data.proceedStatus==1){
									value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
								}
					            return value;
					    }
					 },
			         {

						title : "编辑",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
							var value="";
							var url="freshAuction/manage/update/init.jhtml?id="+data.id;
					        if(data.state==0 && data.proceedStatus==1){
					            value = "<a href='" + url + "'>" + "编辑" + "</a>";
					        }
					        return value;
					    }
					} ,{
						title : "关联订单",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/freshBill/list.jhtml",// url
							param : ["id"],// 参数
							permission : "freshBillList"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var url="fresh/activityOrder/init.jhtml?activityId="+data.id+"&activityType=02";
					        if(data.state==0 && data.proceedStatus==3){
					            value = "<a href='" + url + "'>" + "关联订单" + "</a>";
					        }
					        return value;
					    }
					 }
				]
		},
		cols : [ 
       {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "活动标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "开始时间",
			name : "beginTimeStr",
			type : "string",
			width : 150
		},{
			title : "结束时间",
			name : "endTimeStr",
			type : "string",
			width : 150
		} , {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 150
		}, {title : "起步价",
			name : "startPrice",
			type : "string",
			width : 100
		}, {
			title : "当前最高金额",
			name : "maxPrice",
			type : "string",
			width : 120
		},  {
			title : "参与人数",
			name : "peopleNum",
			type : "string",
			width : 100
		},{
			title : "获奖者",
			name : "winner",
			type : "string",
			width : 180
		},{
			title : "活动状态",
			name : "proceedStatusStr",
			type : "string",
			width : 180
		}, {
			title : "购买列表",
			name : "--",
			type : "string",
			isLink : true,
			width : 150,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "freshAuction/manage/bidding/list/init.jhtml",
				},
				param : ["id"],
				permission : "biddingList",
			},
			customMethod : function(value, data) {
				return $(value).html("查看详情");
			}
		} ]
	}, permissions);
}


/**
 * 预告列表
 * @param data
 * @param obj
 */
function success3(data, obj) {
	var formId = "searchForm3", title = "活动列表", subtitle = "个活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle 
			+'</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
					 {
							title : "立即结束",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "freshAuction/manage/update/terminate.jhtml",// url
								param : ["id"],// 参数
								permission : "update"// 列权限
							},
							customMethod : function(value, data){
								var value="";
						        if(data.state==0 && data.proceedStatus==2){
						            value = "<a href='javascript:confirmTerminate(" + data.id + ")'>" + "立即结束" + "</a>";
						        }
						        return value;
						    }
					 },
					 {
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/delete.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
								var value = "";
								if(data.state==0 && data.proceedStatus==1){
									value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
								}
					            return value;
					    }
					 },
			         {

						title : "编辑",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
							var value="";
							var url="freshAuction/manage/update/init.jhtml?id="+data.id;
					        if(data.state==0 && data.proceedStatus==1){
					            value = "<a href='" + url + "'>" + "编辑" + "</a>";
					        }
					        return value;
					    }
					},{
						title : "关联订单",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/freshBill/list.jhtml",// url
							param : ["id"],// 参数
							permission : "freshBillList"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var url="fresh/activityOrder/init.jhtml?activityId="+data.id+"&activityType=02";
					        if(data.state==0 && data.proceedStatus==3){
					            value = "<a href='" + url + "'>" + "关联订单" + "</a>";
					        }
					        return value;
					    }
					 }
				]
		},
		cols : [ 
       {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "活动标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "开始时间",
			name : "beginTimeStr",
			type : "string",
			width : 150
		},{
			title : "结束时间",
			name : "endTimeStr",
			type : "string",
			width : 150
		} , {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 150
		}, {title : "起步价",
			name : "startPrice",
			type : "string",
			width : 100
		}, {
			title : "当前最高金额",
			name : "maxPrice",
			type : "string",
			width : 120
		},  {
			title : "参与人数",
			name : "peopleNum",
			type : "string",
			width : 100
		},{
			title : "获奖者",
			name : "winner",
			type : "string",
			width : 180
		},{
			title : "活动状态",
			name : "proceedStatusStr",
			type : "string",
			width : 180
		}, {
			title : "购买列表",
			name : "--",
			type : "string",
			isLink : true,
			width : 150,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "freshAuction/manage/bidding/list/init.jhtml",
				},
				param : ["id"],
				permission : "biddingList",
			},
			customMethod : function(value, data) {
				return $(value).html("查看详情");
			}
		} ]
	}, permissions);
}

/**
 * 正在直播
 * @param data
 * @param obj
 */
function success4(data, obj) {
	var formId = "searchForm4", title = "活动列表", subtitle = "个活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle 
			+'</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
					 {
							title : "立即结束",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "freshAuction/manage/update/terminate.jhtml",// url
								param : ["id"],// 参数
								permission : "update"// 列权限
							},
							customMethod : function(value, data){
								var value="";
						        if(data.state==0 && data.proceedStatus==2){
						            value = "<a href='javascript:confirmTerminate(" + data.id + ")'>" + "立即结束" + "</a>";
						        }
						        return value;
						    }
					 },
					 {
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/delete.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
								var value = "";
								if(data.state==0 && data.proceedStatus==1){
									value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
								}
					            return value;
					    }
					 },
			         {

						title : "编辑",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
							var value="";
							var url="freshAuction/manage/update/init.jhtml?id="+data.id;
					        if(data.state==0 && data.proceedStatus==1){
					            value = "<a href='" + url + "'>" + "编辑" + "</a>";
					        }
					        return value;
					    }
					} ,{
						title : "关联订单",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/freshBill/list.jhtml",// url
							param : ["id"],// 参数
							permission : "freshBillList"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var url="fresh/activityOrder/init.jhtml?activityId="+data.id+"&activityType=02";
					        if(data.state==0 && data.proceedStatus==3){
					            value = "<a href='" + url + "'>" + "关联订单" + "</a>";
					        }
					        return value;
					    }
					 }
				]
		},
		cols : [ 
       {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "活动标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "开始时间",
			name : "beginTimeStr",
			type : "string",
			width : 150
		},{
			title : "结束时间",
			name : "endTimeStr",
			type : "string",
			width : 150
		} , {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 150
		}, {title : "起步价",
			name : "startPrice",
			type : "string",
			width : 100
		}, {
			title : "当前最高金额",
			name : "maxPrice",
			type : "string",
			width : 120
		},  {
			title : "参与人数",
			name : "peopleNum",
			type : "string",
			width : 100
		},{
			title : "获奖者",
			name : "winner",
			type : "string",
			width : 180
		},{
			title : "活动状态",
			name : "proceedStatusStr",
			type : "string",
			width : 180
		}, {
			title : "购买列表",
			name : "--",
			type : "string",
			isLink : true,
			width : 150,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "freshAuction/manage/bidding/list/init.jhtml",
				},
				param : ["id"],
				permission : "biddingList",
			},
			customMethod : function(value, data) {
				return $(value).html("查看详情");
			}
		}]
	}, permissions);
}

/**
 * 历史活动列表
 * @param data
 * @param obj
 */
function success5(data, obj) {
	var formId = "searchForm5", title = "活动列表", subtitle = "个活动";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle 
			+'</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
					 {
							title : "立即结束",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "freshAuction/manage/update/terminate.jhtml",// url
								param : ["id"],// 参数
								permission : "update"// 列权限
							},
							customMethod : function(value, data){
								var value="";
						        if(data.state==0 && data.proceedStatus==2){
						            value = "<a href='javascript:confirmTerminate(" + data.id + ")'>" + "立即结束" + "</a>";
						        }
						        return value;
						    }
					 },
					 {
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/delete.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						},
						customMethod : function(value, data){
								var value = "";
								if(data.state==0 && data.proceedStatus==1){
									value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
								}
					            return value;
					    }
					 },
			         {

						title : "编辑",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
							var value="";
							var url="freshAuction/manage/update/init.jhtml?id="+data.id;
					        if(data.state==0 && data.proceedStatus==1){
					            value = "<a href='" + url + "'>" + "编辑" + "</a>";
					        }
					        return value;
					    }
					} ,{
						title : "关联订单",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "freshAuction/manage/freshBill/list.jhtml",// url
							param : ["id"],// 参数
							permission : "freshBillList"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var url="fresh/activityOrder/init.jhtml?activityId="+data.id+"&activityType='02'";
					        if(data.state==0 && data.proceedStatus==3){
					            value = "<a href='" + url + "'>" + "关联订单" + "</a>";
					        }
					        return value;
					    }
					 }
				]
		},
		cols : [ 
       {
			title : "活动编号",
			name : "id",
			type : "string",
			width : 150
		}, {
			title : "活动标题",
			name : "title",
			type : "string",
			width : 180
		}, {
			title : "开始时间",
			name : "beginTimeStr",
			type : "string",
			width : 150
		},{
			title : "结束时间",
			name : "endTimeStr",
			type : "string",
			width : 150
		} , {
			title : "关联商品",
			name : "pname",
			type : "string",
			width : 150
		}, {title : "起步价",
			name : "startPrice",
			type : "string",
			width : 100
		}, {
			title : "当前最高金额",
			name : "maxPrice",
			type : "string",
			width : 120
		},  {
			title : "参与人数",
			name : "peopleNum",
			type : "string",
			width : 100
		},{
			title : "获奖者",
			name : "winner",
			type : "string",
			width : 180
		},{
			title : "活动状态",
			name : "proceedStatusStr",
			type : "string",
			width : 180
		}, {
			title : "购买列表",
			name : "--",
			type : "string",
			isLink : true,
			width : 150,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "freshAuction/manage/bidding/list/init.jhtml",
				},
				param : ["id"],
				permission : "biddingList",
			},
			customMethod : function(value, data) {
				return $(value).html("查看详情");
			}
		} ]
	}, permissions);
}



/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "freshAuction/manage/update/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 pageInit();
				 } else {
					 showSmReslutWindow(result.success, result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
/**
 * 立即结束
 * @param id
 */
 function confirmTerminate(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
	         url : "freshAuction/manage/update/terminate.jhtml",
	         type : "post",
	         dataType : "json",
	         data:{"id":id},
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				pageInit();
	     			}, 1000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定要立即结束吗？");
 }
 
 
 
 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 
	//限定查询日期
 	limitedDate({
 		form:"#searchForm1",
 		startDateName:"queryStartTime",
 		endDateName:"queryEndTime",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 	//限定查询日期
 	limitedDate({
 		form:"#searchForm2",
 		startDateName:"queryStartTime",
 		endDateName:"queryEndTime",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 	//限定查询日期
 	limitedDate({
 		form:"#searchForm3",
 		startDateName:"queryStartTime",
 		endDateName:"queryEndTime",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 	//限定查询日期
 	limitedDate({
 		form:"#searchForm4",
 		startDateName:"queryStartTime",
 		endDateName:"queryEndTime",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 	//限定查询日期
 	limitedDate({
 		form:"#searchForm5",
 		startDateName:"queryStartTime",
 		endDateName:"queryEndTime",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 }
 