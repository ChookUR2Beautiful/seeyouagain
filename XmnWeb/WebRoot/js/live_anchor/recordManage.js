var recordList1;//全部通告
var recordList2;//初始通告
var recordList3;//预告
var recordList4;//直播
var recordList5;//历史通告
var recordList6;//回放
var initListUrl = "liveRecord/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveRecord/manage/init.jhtml" target="right">通告管理</a>',
			'userSpan', true);
	
	liveDateInit();
	
	//加载页面数据
	pageInit();
	
	//批量设为预告
	setAdvanceBatch();
	
	//批量取消预告
	cancelAdvanceBatch();
	
	//设置置顶
	setStickBatch();
	
	//取消置顶
	cancelStickBatch();
	
	//批量修改基础信息
	updateBaseInfoBatch();
	
	//合并自定义直播
	mergeRecord();
	
	//导出
	$("#export").click(function(){
		var path="liveRecord/manage/export.jhtml";
		$form = $("#searchForm1").attr("action",path);
		$form[0].submit();
	});	
	

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
	
	//加载历史通告列表
	recordList5 = $("#recordList5").page({
		url : initListUrl,
		success : success5,
		pageBtnNum : 10,
		paramForm : 'searchForm5',
		param : {
			activityType : "5"
		}
	});
	
	//回放列表
	recordList6 = $("#recordList6").page({
		url : initListUrl,
		success : success6,
		pageBtnNum : 10,
		paramForm : 'searchForm6',
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
	var formId = "searchForm1", title = "通告列表", subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+'&nbsp;&nbsp;&nbsp;&nbsp;直播时长&nbsp;&nbsp;【'+data.titleInfo.hoursFromSecond
			+'】</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 260,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消预告",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , -1 ,telphones
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==0){
				        	params += ",\""+ '-1' + "\"";
				        	if(data.telphones){
				        		params += ",\"" +data.telphones + "\"";
				        	}
				            value = "<a href='javascript:setAdvance(" + params + ")'>" + "取消预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
						title : "设为预告",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveRecord/manage/setAdvance.jhtml",// url
							param : ["id"],// 参数
							permission : undefined// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var params="";//params= id , -1 ,telphones
							params="\"" + data.id +"\"" ;
					        if(data.zhiboType==-1){
					        	params += ",\""+ '0' + "\"";
					        	if(data.telphones){
					        		params += ",\"" +data.telphones + "\"";
					        	}
					            value = "<a href='javascript:setAdvance(" + params + ")'>" + "设为预告" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
							var value="";
							if(data.zhiboType != 1){
								value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
							}
				            return value;
				    }
				 },
		         {

					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id","appointAnchor" ],
						permission : "update"
					},
					customMethod : function(value, data) {
						try {
							if(data.zhiboType == -1){
								value = value.replace("appointAnchor=", "appointAnchor=Y");
								return $(value).html("指派主播")[0].outerHTML;
							}else{
								value = value.replace("appointAnchor=", "");
								return $(value).html("修改")[0].outerHTML;
							}
						} catch (e) {
							console.log(e);
						}
					}
					
				} ,{
					title : "设置粉丝券",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance/advanceDetail.jhtml",// url
						param : ["id"],// 参数
						permission : undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var url="liveRecord/manage/setAdvance/advanceDetail.jhtml?id="+data.id;
				        if(data.zhiboType=='-1'||data.zhiboType=='0'){
				            value = "<a href='" + url + "'>" + "设置粉丝券" + "</a>";
				        }
				        return value;
				    }
				 }
				
			]
		},
		cols : [ 
       {
			title : "状态",
			name : "zhiboTypeStr",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		},{
			title : "商家 / 主题",
			name : "sellerAlias",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				if(value!="-"){
					result=value;
				}else{
					result=data.sellername;
				}
				return result;
			}
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		}, {title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 100
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result=value;
				if(data.anchorId==null || data.anchorId==undefined){
					result="待接单";
				}
				return result;
			}
		}/*, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 100
		}*/ ]
	}, permissions);
}

/**
 * 初始状态
 * @param data
 * @param obj
 */
function success2(data, obj) {
	var formId = "searchForm2", title = "通告列表", subtitle = "个通告";
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
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消预告",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , -1 ,telphones
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==0){
				        	params += ",\""+ '-1' + "\"";
				        	if(data.telphones){
				        		params += ",\"" +data.telphones + "\"";
				        	}
				            value = "<a href='javascript:setAdvance(" + params + ")'>" + "取消预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
						title : "设为预告",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveRecord/manage/setAdvance.jhtml",// url
							param : ["id"],// 参数
							permission : "setAdvance-delete"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var params="";//params= id , -1 ,telphones
							params="\"" + data.id +"\"" ;
					        if(data.zhiboType==-1){
					        	params += ",\""+ '0' + "\"";
					        	if(data.telphones){
					        		params += ",\"" +data.telphones + "\"";
					        	}
					            value = "<a href='javascript:setAdvance(" + params + ")'>" + "设为预告" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType != 1){
							value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
						}
			            return value;
					}
				 },
		         {
					title : "指派主播",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id","appointAnchor" ],
						permission : "update"
					},
					customMethod : function(value, data) {
						try {
							if(data.zhiboType == -1){
								value = value.replace("appointAnchor=", "appointAnchor=Y");
								return $(value).html("指派主播")[0].outerHTML;
							}else{
								return $(value).html("修改")[0].outerHTML;
							}
						} catch (e) {
							console.log(e);
						}
					}
				} 
				
			]
		},
		cols : [ 
	        {
	 			title : "状态",
	 			name : "zhiboTypeStr",
	 			type : "string",
	 			width : 150
	 		}, {
	 			title : "标题",
	 			name : "zhiboTitle",
	 			type : "string",
	 			width : 180
	 		}, {
	 			title : "直播时间",
	 			name : "planStartDateStr",
	 			type : "string",
	 			width : 150
	 		}, {
				title : "直播时长",
				name : "zhiboDurationStr",
				type : "string",
				width : 150
			},{
	 			title : "商家 / 主题",
	 			name : "sellerAlias",
	 			type : "string",
	 			width : 150,
				customMethod : function(value, data) {
					var result;
					if(value!="-"){
						result=value;
					}else{
						result=data.sellername;
					}
					return result;
				}
	 		} , {
	 			title : "地点",
	 			name : "zhiboAddress",
	 			type : "string",
	 			width : 150
	 		}, {title : "推荐排序",
	 			name : "sequenceNo",
	 			type : "string",
	 			width : 100
	 		}, {
	 			title : "主播",
	 			name : "nname",
	 			type : "string",
	 			width : 150,
	 			customMethod:function(value,data){
					var result=value;
					if(data.anchorId==null || data.anchorId==undefined){
						result="待接单";
					}
					return result;
				}
	 		}/*, {
	 			title : "关注人数",
	 			name : "concernedNums",
	 			type : "string",
	 			width : 100
	 		} */]
	}, permissions);
}


/**
 * 预告列表
 * @param data
 * @param obj
 */
function success3(data, obj) {
	var formId = "searchForm3", title = "通告列表", subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+'&nbsp;&nbsp;&nbsp;&nbsp;直播时长&nbsp;&nbsp;【'+data.titleInfo.hoursFromSecond
			+'】</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 280,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消预告",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : "setAdvance-delete"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , -1 ,telphones
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==0){
				        	params += ",\""+ '-1' + "\"";
				        	if(data.telphones){
				        		params += ",\"" +data.telphones + "\"";
				        	}
				            value = "<a href='javascript:setAdvance(" + params + ")'>" + "取消预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
						title : "设为预告",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveRecord/manage/setAdvance.jhtml",// url
							param : ["id"],// 参数
							permission : "setAdvance-delete"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var params="";//params= id , -1 ,telphones
							params="\"" + data.id +"\"" ;
					        if(data.zhiboType==-1){
					        	params += ",\""+ '0' + "\"";
					        	if(data.telphones){
					        		params += ",\"" +data.telphones + "\"";
					        	}
					            value = "<a href='javascript:setAdvance(" + params + ")'>" + "设为预告" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType != 1){
							value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
						}
			            return value;
					}
				 },
		         {

					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
					
				} ,{
					title : "设置粉丝券",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance/advanceDetail.jhtml",// url
						param : ["id"],// 参数
						permission :undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var url="liveRecord/manage/setAdvance/advanceDetail.jhtml?id="+data.id;
				        if(data.zhiboType=='-1'||data.zhiboType=='0'){
				            value = "<a href='" + url + "'>" + "设置粉丝券" + "</a>";
				        }
				        return value;
				    }
				 }
				
			]
		},
		cols : [ 
       {
			title : "状态",
			name : "zhiboTypeStr",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
//				debugger;
				var result=value==undefined?"":value;
				if(data.stick==1){
					result+="【置顶】";
				}
				return result;
			}
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		},{
			title : "商家 / 主题",
			name : "sellerAlias",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				if(value!="-"){
					result=value;
				}else{
					result=data.sellername;
				}
				return result;
			}
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		}, {title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 100
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result=value;
				if(data.anchorId==null || data.anchorId==undefined){
					result="待接单";
				}
				return result;
			}
		}/*, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 100
		}*/ ]
	}, permissions);
}

/**
 * 正在直播
 * @param data
 * @param obj
 */
function success4(data, obj) {
	var formId = "searchForm4", title = "通告列表", subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+'&nbsp;&nbsp;&nbsp;&nbsp;直播时长&nbsp;&nbsp;【'+data.titleInfo.hoursFromSecond
			+'】</font></caption>';
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
			width : 260,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消预告",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : "setAdvance"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , -1 ,telphones
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==0){
				        	params += ",\""+ '-1' + "\"";
				        	if(data.telphones){
				        		params += ",\"" +data.telphones + "\"";
				        	}
				            value = "<a href='javascript:setAdvance(" + params + ")'>" + "取消预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
						title : "设为预告",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveRecord/manage/setAdvance.jhtml",// url
							param : ["id"],// 参数
							permission : "setAdvance"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var params="";//params= id , -1 ,telphones
							params="\"" + data.id +"\"" ;
					        if(data.zhiboType==-1){
					        	params += ",\""+ '0' + "\"";
					        	if(data.telphones){
					        		params += ",\"" +data.telphones + "\"";
					        	}
					            value = "<a href='javascript:setAdvance(" + params + ")'>" + "设为预告" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType != 1){
							value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
						}
			            return value;
					}
				 },
		         {

					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
					
				} ,{
					title : "设置粉丝券",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance/advanceDetail.jhtml",// url
						param : ["id"],// 参数
						permission : undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var url="liveRecord/manage/setAdvance/advanceDetail.jhtml?id="+data.id;
				        if(data.zhiboType=='-1'||data.zhiboType=='0'){
				            value = "<a href='" + url + "'>" + "设置粉丝券" + "</a>";
				        }
				        return value;
				    }
				 }
				
			]
		},
		cols : [ 
       {
			title : "状态",
			name : "zhiboTypeStr",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		},{
			title : "商家 / 主题",
			name : "sellerAlias",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				if(value!="-"){
					result=value;
				}else{
					result=data.sellername;
				}
				return result;
			}
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		}, {title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 100
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result=value;
				if(data.anchorId==null || data.anchorId==undefined){
					result="待接单";
				}
				return result;
			}
		}/*, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 100
		}*/ ]
	}, permissions);
}

/**
 * 历史通告列表
 * @param data
 * @param obj
 */
function success5(data, obj) {
	var formId = "searchForm5", title = "通告列表", subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+'&nbsp;&nbsp;&nbsp;&nbsp;直播时长&nbsp;&nbsp;【'+data.titleInfo.hoursFromSecond
			+'】</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		tableClass :"table-bordered table-striped info",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		 checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 260,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消预告",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance.jhtml",// url
						param : ["id"],// 参数
						permission : "setAdvance"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , -1 ,telphones
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==0){
				        	params += ",\""+ '-1' + "\"";
				        	if(data.telphones){
				        		params += ",\"" +data.telphones + "\"";
				        	}
				            value = "<a href='javascript:setAdvance(" + params + ")'>" + "取消预告" + "</a>";
				        }
				        return value;
				    }
				 },
				 {
						title : "设为预告",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "liveRecord/manage/setAdvance.jhtml",// url
							param : ["id"],// 参数
							permission : "setAdvance"// 列权限
						},
						customMethod : function(value, data){
							var value="";
							var params="";//params= id , -1 ,telphones
							params="\"" + data.id +"\"" ;
					        if(data.zhiboType==-1){
					        	params += ",\""+ '0' + "\"";
					        	if(data.telphones){
					        		params += ",\"" +data.telphones + "\"";
					        	}
					            value = "<a href='javascript:setAdvance(" + params + ")'>" + "设为预告" + "</a>";
					        }
					        return value;
					    }
				 },
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : false// 列权限
					},
					customMethod : function(value, data){
						var value="";
						if(data.zhiboType != 1){
							value = "<a href='javascript:confirmDelete("+data.id+")'>" + "删除" + "</a>";
						}
			            return value;
					}
				 },
		         {

					title : "添加回放",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
					
				} ,{
					title : "设置粉丝券",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/setAdvance/advanceDetail.jhtml",// url
						param : ["id"],// 参数
						permission : undefined// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var url="liveRecord/manage/setAdvance/advanceDetail.jhtml?id="+data.id;
				        if(data.zhiboType=='-1'||data.zhiboType=='0'){
				            value = "<a href='" + url + "'>" + "设置粉丝券" + "</a>";
				        }
				        return value;
				    }
				 }
				
			]
		},
		cols : [ 
       {
			title : "状态",
			name : "zhiboTypeStr",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		},{
			title : "商家 / 主题",
			name : "sellerAlias",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				if(value!="-"){
					result=value;
				}else{
					result=data.sellername;
				}
				return result;
			}
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		}, {title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 100
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result=value;
				if(data.anchorId==null || data.anchorId==undefined){
					result="待接单";
				}
				return result;
			}
		}/*, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 100
		} */]
	}, permissions);
}


/**
 * 加载回放列表
 * @param data
 * @param obj
 */
function success6(data, obj) {
	var formId = "searchForm1", title = "通告列表", subtitle = "个通告";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;'
			+'&nbsp;&nbsp;&nbsp;&nbsp;直播时长&nbsp;&nbsp;【'+data.titleInfo.hoursFromSecond
			+'】</font></caption>';
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
			width : 260,// 宽度
			// 当前列的中元素
			cols : [ 
				{
					title : "取消回放",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/cancelPlayBack.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					},
					customMethod : function(value, data){
						var value="";
						var params="";//params= id , 4 
						params="\"" + data.id +"\"" ;
				        if(data.zhiboType==3){
				        	params += ",\""+ '4' + "\"";
				            value = "<a href='javascript:cancelPlayBack(" + params + ")'>" + "取消回放" + "</a>";
				        }
				        return value;
				    }
				 },
				 
		         {

					title : "修改回放",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveRecord/manage/update/init.jhtml",
						param : [ "id" ],
						permission : "update"
					}
					
				} 
			]
		},
		cols : [ 
       {
			title : "状态",
			name : "zhiboTypeStr",
			type : "string",
			width : 150
		}, {
			title : "标题",
			name : "zhiboTitle",
			type : "string",
			width : 180
		}, {
			title : "直播时间",
			name : "planStartDateStr",
			type : "string",
			width : 150
		}, {
			title : "直播时长",
			name : "zhiboDurationStr",
			type : "string",
			width : 150
		},{
			title : "商家 / 主题",
			name : "sellerAlias",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result;
				if(value!="-"){
					result=value;
				}else{
					result=data.sellername;
				}
				return result;
			}
		} , {
			title : "地点",
			name : "zhiboAddress",
			type : "string",
			width : 150
		}, {title : "推荐排序",
			name : "sequenceNo",
			type : "string",
			width : 100
		}, {
			title : "主播",
			name : "nname",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result=value;
				if(data.anchorId==null || data.anchorId==undefined){
					result="待接单";
				}
				return result;
			}
		}/*, {
			title : "关注人数",
			name : "concernedNums",
			type : "string",
			width : 100
		}*/ ]
	}, permissions);
}


/**
 * 批量设为预告
 */	
function setAdvanceBatch(){
	$("#setAdvanceBatch").click(function(){
//		debugger;
		if(!recordList2.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!recordList2.validateChose("zhiboType", "-1", "数据不符合条件，只有通告状态才可操作设为预告功能")){
			return;
		}
		var data = {ids:recordList2.getIds(),zhiboType:'0'};
		updateZhiboTypeBatch(data,"你确定要将选中的通告设为预告？");
	});
}

/**
 * 批量取消预告
 */
function cancelAdvanceBatch(){
	$("#cancelAdvanceBatch").click(function(){
		if(!recordList3.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!recordList3.validateChose("zhiboType", "0", "数据不符合条件，只有预告才可操作取消预告")){
			return;
		}
		var data = {ids:recordList3.getIds(),zhiboType:'-1'};
		updateZhiboTypeBatch(data,"你确定要将选中的通告取消预告？");
	});
}

/**
 * 批量设置预告置顶
 */	
function setStickBatch(){
	$("#setStickBatch").click(function(){
		if(!recordList3.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!recordList3.validateChose("zhiboType", "0", "数据不符合条件，只有预告才可设为置顶")){
			return;
		}
		var data = {ids:recordList3.getIds(),stick:'1'};
		updateZhiboTypeBatch(data,"你确定要将选中的预告设为置顶？");
	});
}

/**
 * 批量取消预告置顶
 */	
function cancelStickBatch(){
	$("#cancelStickBatch").click(function(){
		if(!recordList3.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		if(!recordList3.validateChose("zhiboType", "0", "数据不符合条件，只有预告才可设为置顶")){
			return;
		}
		var data = {ids:recordList3.getIds(),stick:'0'};
		updateZhiboTypeBatch(data,"你确定要将选中的预告取消置顶？");
	});
}

/**
 * 合并自定义直播		<br/>
 * 1、主播名必须一致	<br/>
 * 2、状态为结束直播 	<br/>
 */
function mergeRecord(){
	$("#mergeRecord").click(function(){
		var ids=recordList5.getValue("id");
		if(ids.length<=1){
			showWarningWindow("warning","请至少选择两条记录！");
			return;
		}else{
			var anchorIds=recordList5.getValue("anchorId");
			var anchorId=anchorIds[0];
			if(!recordList5.validateChoseStrict("anchorId", anchorId+"", "数据不符合条件，只能合并同一主播的通告")){
				return;
			}
		}
		
		if(!recordList5.validateChoseStrict("liveStartType", "1", "数据不符合条件，只有自定义通告才可合并")){
			return;
		}
		
		if(!recordList5.validateChoseStrict("zhiboType", "4,5", "数据不符合条件，只有历史通告才可合并")){
			return;
		}
		
		
		
		showSmConfirmWindow(function() {
				var ids=recordList5.getIds();
			
				$.ajax({
		        type: "POST",
		        url: "liveRecord/manage/mergeRecord.jhtml",
		        data: {"ids":ids},
		        dataType: "json",
			        success: function(result){
						showSmReslutWindow(result.success, result.msg);
						pageInit();
			         }
			    });
		},"确定合并通告？");
	});
}

/**
 * 批量修改通告基础信息，需满足以下条件:<br/>
 * 1、所有通告类型一致<br/>
 * 2、商家或活动主题一致<br/>
 * 3、直播计划开始时间一致<br/>
 * 4、zhiboType需为通告或预告
 */
function updateBaseInfoBatch(){
	$("#updateBaseInfoBatch").click(function(){
//		debugger;
 		console.log(recordList1.getIds());
 		var ids=recordList1.getIds();
 		if(!recordList1.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		
 		if(!recordList1.validateChoseStrict("zhiboType", "0", "数据不符合条件，只有预告状态才可批量修改")){
			return;
		}
 		
 		var liveTopics=recordList1.getValue("liveTopic");
 		var baseLiveTopic=liveTopics[0];
 		for(var i=0;i<liveTopics.length;i++){
 			if(liveTopics[i]!=baseLiveTopic){
 				showWarningWindow("warning","所选通告的直播类型不一致！");
 	 			return;
 			}
 		}
 		
 		var sellerids=recordList1.getValue("sellerid");
 		var baseSellerid=sellerids[0];
 		for(var i=0;i<sellerids.length;i++){
 			if(sellerids[i]!=baseSellerid){
 				showWarningWindow("warning","所选通告的商家不一致！");
 	 			return;
 			}
 		}
 		
 		var planStartDates=recordList1.getValue("planStartDate");
 		var baseplanStartDate=planStartDates[0];
 		for(var i=0;i<planStartDates.length;i++){
 			if(planStartDates[i]!=baseplanStartDate){
 				showWarningWindow("warning","所选通告的直播时间不一致！");
 	 			return;
 			}
 		}
 		
 		var sellerAlias=recordList1.getValue("sellerAlias");
 		var baseSellerAlia=sellerAlias[0];
 		for(var i=0;i<sellerAlias.length;i++){
 			if(sellerAlias[i]!=baseSellerAlia){
 				showWarningWindow("warning","所选通告的主题不一致！");
 	 			return;
 			}
 		}
 		
 		var modalTrigger = new ModalTrigger({
 			title:'批量修改通告基础信息',
			type : 'ajax',
			url : 'liveRecord/manage/updateBaseInfoBatch/init.jhtml?ids=' + ids ,
			toggle : 'modal'
		});
		modalTrigger.show();
 		
 	});
}

/**
 * 批量更新通告直播类型或预告置顶状态
 * @param data
 * @param title
 */
 function updateZhiboTypeBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
				        type: "POST",
				        url: "liveRecord/manage/updateZhiboTypeBatch.jhtml",
				        data: data,
				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							pageInit();
 				         }
 				    });
 			},title);
 }


/**
 * 删除操作
 */
 function confirmDelete(id){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveRecord/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:'id=' + id,
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
						var url = contextPath +'/liveRecord/manage/init.jhtml';
						setTimeout(function() {
							location.href = url;
						}, 1000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 /**
  * 预告设置
  * @param id
  * @param zhiboType
  */
 function setAdvance(id,zhiboType,telphones){
	 $.ajax({
         url : "liveRecord/manage/setAdvance.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"zhiboType":zhiboType,"telphones":telphones},
         success : function(result) {
//        	 debugger;
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
 }
 
 /**
  * 取消回放
  * @param id
  * @param zhiboType
  */
 function cancelPlayBack(id,zhiboType){
	 $.ajax({
         url : "liveRecord/manage/update/cancelPlayBack.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"zhiboType":zhiboType},
         success : function(result) {
//        	 debugger;
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
 }
 
 /**
  * 批量删除
  */
 $('#deleteBatch').click(function(){
//	debugger;
 	var ids = recordList1.getIds();
 	if(!ids){
 		showWarningWindow("warning","请至少选择一条记录！");
 		return;
 	}
 	
 	if(!recordList1.validateChoseStrict("zhiboType", "-1,0,2,3,4,5", "数据不符合条件，直播状态的通告不可删除")){
		return;
	}
 	showSmConfirmWindow(function() {
 		$.ajax({
 			type : 'post',
 			url : 'liveRecord/manage/deleteBatch.jhtml' + '?t=' + Math.random(),
 			data : {'ids':ids},
 			dataType : 'json',
 			beforeSend : function(XMLHttpRequest) {
 				$('#prompt').show();
 			},
 			success : function(data) {
 				$('#prompt').hide();

 				if (data.success) {
 					recordList1.reset();
 				}

 				showSmReslutWindow(data.success, data.msg);
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				$('#prompt').hide();
 			}
 		});
 	});
 });
 
/**
 * 
 * @param id
 * @param operationType
 */
 function upOrDown(id,operationType){
	 $.ajax({
         url : "liveRecord/manage/upOrDown.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"operationType":operationType},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				recordList.reload();
     			}, 1000);
     		} else {
     			showWarningWindow("warning", result.msg);
     		}
         }
     });
 }
 
 
 /**
  * 直播日期控件初始化
  */
 function liveDateInit(){
	 /*$('.form_datetime').datetimepicker({
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			showMeridian : 1,
			format : 'yyyy-mm-dd'
		});*/
	 
	//限定直播日期
	limitedDate({
		form:"#searchForm1",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	//限定直播日期
	limitedDate({
		form:"#searchForm2",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	//限定直播日期
	limitedDate({
		form:"#searchForm3",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	//限定直播日期
	limitedDate({
		form:"#searchForm4",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	//限定直播日期
	limitedDate({
		form:"#searchForm5",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
	//限定直播日期
	limitedDate({
		form:"#searchForm6",
		startDateName:"startTime",
		endDateName:"endTime",
		overlap:true,
		format : 'yyyy-mm-dd hh:ii',
		minuteStep:1,
		minView : 0,
	});
 }
 
 /**
  * 绑定活动状态tab页主播签约情况change事件
  */
 $(".tab-pane").find("select[name='signType']").change(function(){
	 var signType=$(this).val();
		if(signType==0){
			$(this).next().css("display","inline");
		}else{
			$(this).next().css("display","none");
			$(this).next().attr("selected",true);
		}
	
 });
 