var commentList;
var pageSize = 15;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	var url=sellerType&&sellerid?"businessman/experience/comment/init/list.jhtml?sellerType="+sellerType+"&sellerid="+sellerid+"&":"businessman/experience/comment/init/list.jhtml";
	commentList = $('#commentList').page({
		url :url,
		success : success,
		pageBtnNum : 10,
		pageSize : pageSize,
		paramForm : 'brandForm'
	});

	inserTitle(' > 商户管理  > <a href="businessman/experience/comment/init.jhtml" target="right">点评管理</a>', 'commentList', true);


});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【' + data.total + '】条点评数据&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam=" + getFormParam($("#searchSupplierForm").serialize());
	updateAddBtnHref(".btn-add", callbackParam);
	obj.find('div').eq(0).scrollTablel({
		tableClass : "table-bordered table-striped info",
		callbackParam : callbackParam,
		caption : captionInfo,
		//数据
		data : data.content,
		//数据行
		cols : [ {
			title : "状态", // 标题
			name : "reviewStateStr",
			//sort : "up",
			width : 120, // 宽度
			type : "int", //数据类型		
		}, {
			title : "推荐状态", // 标题
			name : "isRecommendStr",
			//sort : "up",
			width : 100, // 宽度
			type : "string" //数据类型
		}, {
			title : "点评用户",
			name : "uid",
			type : "string",
			width : 250,
			customMethod : function(value, data) {
				var type;
				if (data.userType == 1 || data.userType == 2) {
					type = "【主播】";
				} else {
					type = "【用户】";
				}
				return type + data.liverName + "(" + data.liverPhone + ")"
			}
		}, {
			title : "点评商户",
			name : "sellername",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var type;
				if (data.sellerType == 1) {
					type = "【签约】";
				} else {
					type = "【非签约】";
				}
				return type + data.sellername
			}
		}, {
			title : "点评时间",
			name : "createTimeStr",
			type : "string",
			width : 150
		} ],
		//操作列
		handleCols : {
			title : "操作", // 标题
			width : 80, // 宽度
			queryPermission : [ "edit", "delete","reviewState","recommend" ], // 不需要选择checkbox处理的权限
			// 当前列的中元素
			cols : [ {
				title : "编辑", // 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					param : [ "id" ],
					permission : "reviewState"
				},
				customMethod : function(value, data) {
					if (data.reviewState == 0) {
						return '<a href="javascript:;"  onclick="updateReviewState(' + data.id + ',1)" >通过</a>'
					} else {
						return "";
					}
				}
			}, {
				title : "编辑", // 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					param : [ "id" ],
					permission : "reviewState"
				},
				customMethod : function(value, data) {
					if (data.reviewState == 0) {
						return '<a href="javascript:;"  onclick="updateReviewState(' + data.id + ',2)" >拒绝</a>'
					} else {
						return "";
					}
				}
			}, {
				title : "编辑", // 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					param : [ "id" ],
					permission : "reviewState"
				},
				customMethod : function(value, data) {
					if (data.reviewState == 1) {
						return '<a href="javascript:;"  onclick="updateReviewState(' + data.id + ',3)" >停用</a>'
					} else if (data.reviewState == 3) {
						return '<a href="javascript:;"  onclick="updateReviewState(' + data.id + ',1)" >启用</a>';
					} else {
						return '';
					}
				}
			}, {
				title : "编辑", // 标题
				linkInfoName : "href",
				width : 20,
				linkInfo : {
					param : [ "id" ],
					permission : "recommend"
				},
				customMethod : function(value, data) {
					if (data.reviewState == 1) {
						if (data.isRecommend == 0) {
							return '<a href="javascript:;"  onclick="updateIsRecommend(' + data.id + ',1)" >设为推荐</a>'
						} else if (data.isRecommend == 1) {
							return '<a href="javascript:;"  onclick="updateIsRecommend(' + data.id + ',0)" >取消推荐</a>';
						} else {
							return '';
						}
					}
					return "";
				}
			} , {
				title : "查看", // 标题
				linkInfoName : "modal",
				width : 20,
				linkInfo : {
					modal : {
						url : "businessman/experience/comment/init/experienceCommentDetail.jhtml",
						position : "100px",
						width : "400px"
					},
					param : [ "id" ],
					permission : "recommend"
				}
			} ]
		}
	}, permissions);

}

function updateIsRecommend(id,type){
	$.ajax({
		type : 'post',
		url : "businessman/experience/comment/updateIsRecommend.jhtml",
		data : {"id":id,"isRecommend":type},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			commentList.reload();
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}
function updateReviewState(id,type){
	var data={
			id:id,
			reviewState:type
	}
	if(type==1){
		showSmConfirmWindow(function() {
			updateState(data);
	 	},'是否通过审核,通过后前端将会显示点评');
	}else if(type==2){
		$("#experienceCommentId").val(id);
		$('#query').modal();
	}else{
		updateState(data);
	}
	
	
}


$("#queryClick").on("click",function(){
	if(!$("#refuseRemark").val()){
		showWarningWindow("warning", "请输入拒绝原因!", 9999);
		return;
	}
	var data={
			id:$("#experienceCommentId").val(),
			reviewState:2,
			refuseRemark:$("#refuseRemark").val()
	}
	updateState(data);
	$("#refuseRemark").val("");
});


function updateState(data){
	$.ajax({
		type : 'post',
		url : "businessman/experience/comment/updateReviewState.jhtml",
		data :data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#query').modal('hide');
			commentList.reload();
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}
