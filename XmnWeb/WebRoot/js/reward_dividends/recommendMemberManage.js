var pageDiv, vkeProfitDiv;

$(document).ready(function() {
	//标题
	inserTitle(' > 打赏分红> <span><a href="recommendMember/manage/init.jhtml" target="right">V客会员管理</a>', 'sellerList',true);
	
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/recommendMember/manage/init.jhtml';
			location.href =url;
		}
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
		});
	});
	
	pageDiv = $("#rechargeRewardInfoList").page({
		url : 'recommendMember/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchFromId'
	});
	
	//导出
	$("#exportVkeMember").click(function(){
		/*var formId="searchFromId";
		var url="recommendMember/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>5000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}*/
		var path="recommendMember/manage/export.jhtml";
		$form = $("#searchFromId").attr("action",path);
		$form[0].submit();
	});	
	
	
	/*保存对应关联关系*/
	$("#addRelationSubmit").click(function(){
		saveRelationship();
	});
	
	//加载关联等级
	initRankId();
});

initSelectDataList();

function initSelectDataList(){
	//加载V客会员列表
	initVkeId();
	//加载商家列表
	initSellerId();
	//加载主播列表
	initLiveId();
}

//初始化关联等级下拉框
function initRankId(){
//	debugger;
	var rankType=2;
	$("#ledgerLevel").chosenObject({
		hideValue : "id",
		showValue : "rankName",
		url : "liveFansRank/manage/getFansRanks.jhtml",
		filterVal:rankType,////过滤的值 (filterVal=type)
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

/**
 * 加载累计充值总额
 */
function loadRechargeTotal(){
	$.ajax({
		 url : "recommendMember/manage/init/countLivePrivilegeInfo.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#searchFromId').serializeArray()),
		 success : function(result) {
			 if (result.success) {
				 var data=result.data;
				 var content='';
					//加载统计区间表单数据
				 if(data){
					 content +="<tr>"
//		                 + "       <td>"+data.data.startTime+"至"+data.data.endTime+"</td>"				//统计时间区间
		                 + "       <td>"+data.vkeCount+"</td>"  	//V客总数
		                 + "       <td>"+data.vkeLevel1+"</td>"		//一星V客数
		                 + "       <td>"+data.vkeLevel2+"</td>"	 	//二星V客
		                 + "       <td>"+data.vkeLevel3+"</td>"  	//三星V客
		                 + "       <td>"+data.vkeLevel5+"</td>"  	//五星V客
		                 + "       <td>"+data.vkeLive+"</td>"		//累计推荐主播
		                 + "       <td>"+data.liveProft+"</td>"		//获得主播收益
		                 + "       <td>"+data.vkeSeller+"</td>"		//累计签约商户
		                 + "       <td>"+data.sellerProft+"</td>"	//获得商户流水
		                 + "</tr>" ;
				 }else{
					 content +="<tr ><td colspan='6'>暂无数据</td></tr>";
				 }
			     $("#vkeProfitTotal").html(content);
			 } else {
				 showSmReslutWindow(result.success, result.msg);
			 }
		 }
	 });
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;V客会员管理列表  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个会员&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchFromId").serialize());
	updateAddBtnHref("#addRechargeRewardBto", callbackParam);
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data: data.content, 
		caption : captionInfo,
		checkable : false,
		cols:[{
			title : "用户编号",
			name : "uid",
			type : "string",
			width : 80
		},{
			title : "用户昵称",
			name : "nickname",
			type : "string",
			width : 80
		},{
			title : "真实姓名",
			name : "name",
			type : "string",
			width : 80			
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 80		
		},{
			title : "会员等级",
			name : "rankName",
			type : "string",
			width : 100			
		},{
			title : "鸟币限制",
			name : "limitBalance",
			type : "string",
			width : 80	
		},{
			title : "返还模式",
			name : "excitationProjectStr",
			type : "string",
			width : 80	
		},{
			title : "上级",
			name : "superiorStr",
			type : "string",
			width : 80		
		},{
			title : "间接上级",
			name : "topLevelStr",
			type : "string",
			width : 80				
		},{
			title : "主播推荐名额",
			name : "totalRecommendLive",
			type : "string",
			width : 80,
			customMethod : function(value, data){
				var recommendLive = data.recommendLive == undefined ? 0 : data.recommendLive ;
				var totalRecommendLive = data.totalRecommendLiveStr == undefined ? 0 : data.totalRecommendLiveStr;
	            var value = "<a href='javascript:;' onclick='anchorView("+data.uid+")'>" + recommendLive + '/' + totalRecommendLive + "</a>";
	            return value;
	        }
		},{
			title : "获得主播收益",
			name : "liveProfitAmount",
			type : "string",
			width : 80
		},{
			title : "商户推荐名额",
			name : "totalRecommendSeller",
			type : "string",
			width : 80,
			customMethod : function(value, data){
				var recommendSeller =  data.recommendSeller == undefined ? 0 : data.recommendSeller ;
				var totalRecommendSeller = data.totalRecommendSellerStr == undefined ? 0 : data.totalRecommendSellerStr;
	            var value = "<a href='javascript:void(0);' onclick='sellerView("+data.uid+")' >" + recommendSeller + '/' + totalRecommendSeller + "</a>";
	            return value;
	        }
		},{
			title : "获得商户收益",
			name : "sellerProfitAmount",
			type : "string",
			width : 80
		}]
	}, permissions);
	
	loadRechargeTotal();
}

/*查看推荐的主播明细*/
function anchorView(uid){
	var url='recommendMember/manage/list/viewLiveDetail.jhtml';
	$.ajax({
		type : 'post',
		url : url,
		data :{uid:uid},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : viewLiveSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#recommendLiveListModal').modal('hide');
		}
	});
}

function viewLiveSuccess(data) {
	$('#prompt').hide();
	//添加优惠券信息 $("#areaTable tr:last").after(tr);
	$("#liveListInfoTB").html("");  //初始化表格信息
	if (data.data != null) {
		for (var i = 0; i < data.data.length; i++) {
			var nickname = data.data[i].nickname == undefined ? "" : data.data[i].nickname; // 主播名称
			var phone = data.data[i].phone == undefined  ? "" : data.data[i].phone ; // 主播手机
			var liveTotalProfit = data.data[i].liveTotalProfit == undefined ? 0 : data.data[i].liveTotalProfit; // 主播累计收益
			var fromLiveProfit = data.data[i].fromLiveProfit == undefined ? 0 : data.data[i].fromLiveProfit; // 获得主播收益
			var recommendStatus = data.data[i].recommendStatus == undefined ? "" : data.data[i].recommendStatus; // 推荐状态
			var tr = $("<tr class='text-center'>").append($("<td>").text(nickname)).append($("<td>").text(phone)).append($("<td>").text(liveTotalProfit)).append($("<td>").text(fromLiveProfit));
			if (recommendStatus == 2) //审核通过的
			    tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">').html('<a href="javascript:update(\''+data.data[i].uid+'\', \''+data.data[i].uid+'\', 0,\''+data.data[i].nickname+'\')" onclick="">放弃</a>'));
			else
				tr.append($("<td>"));
			$("#liveListInfoTB").append(tr);
		}
	}
	$('#recommendLiveListModal').modal('show');  //'show'
}

/*查看推荐的商家明细*/
function sellerView(uid){
	var url='recommendMember/manage/list/viewShopDetail.jhtml';
	$.ajax({
		type : 'post',
		url : url,
		data :{uid:uid},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : viewSellerSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#rewardRecordListModal').modal('hide');
		}
	});
}

function viewSellerSuccess(data) {
	$('#prompt').hide();
	$("#sellerListInfoTB").html("");  //初始化表格信息
	if (data.data != null) {
		for (var i = 0; i < data.data.length; i++) {
			var sellername = data.data[i].sellername; // 商户名称
			var fullname = data.data[i].fullname == undefined ? "" : data.data[i].fullname; // 负责人
			var phoneid = data.data[i].phoneid == undefined ? "" : "("+ data.data[i].phoneid +")"; // 负责人电话
			var sellerTotalProfit = data.data[i].sellerTotalProfit; // 流水累计收益
			var fromSellerProfit = data.data[i].fromSellerProfit; // 获得商户收益
			var status = data.data[i].status; // 推荐状态

			var tr = $("<tr class='text-center'>").append($("<td>").text(sellername)).append($("<td>").text(fullname+phoneid)).append($("<td>").text(sellerTotalProfit)).append($("<td>").text(fromSellerProfit));
			if (status == 1) //订单状态: 默认0 0 未支付(存入草稿) 1 (支付成功） 2 (已作废)
			    tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">').html('<a href="javascript:update(\''+data.data[i].sellerid+'\',\''+data.data[i].uid+'\', 1,\''+data.data[i].sellername+'\')" onclick="">放弃</a>'));
			else
				tr.append($("<td>"));
			$("#sellerListInfoTB").append(tr);
		}
	}
	$('#recommendSellerListModal').modal('show');  //'show'
}

function update(paramId, uid, type, name) {
	var title, url = 'recommendMember/manage/update.jhtml';
	if (type == 0){  //放弃主播
		title = "是否放弃当前“"+ name +"”主播，放弃后V客返还一个名额";
	}else{
		title = "是否放弃当前“"+ name +"”商户，放弃后V客返还一个名额";
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : url,
			data : {'id':paramId, 'type':type, 'uid':uid},
//			data : [paramId, type],
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {			 				
	          	$('#prompt').hide();
				if (data.success) {
					pageDiv.reload();
					//type 0-主播, 1商户
					if (type == 0){
						$('#recommendLiveListModal').modal('hide');
					}else{
						$('#recommendSellerListModal').modal('hide');  //'show'
					}
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	}, title);
}

//添加关系

//初始化V客会员列表
function initVkeId(){
//	debugger;
	var vkeIdSelectDiv = $("#vkeIdSelect").html('');
	vkeIdSelectDiv.html('<select class="form-control" name="vkeId" id="vkeId" style="width: 70%; float: left;" ></select>');
	$("#vkeId").chosenObject({
		hideValue : "uid",
		showValue : "showValue",//输入查询值
		showType:"multiple",//选项显示形式
		showParams:["nname","phone"],//showType为multiple时生效,phone|nickname
		url : "bursRelationChain/manage/init/list.jhtml?objectOriented=4&t=new Date()",
		filterVal:null,
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	$("#vkeId").bind("change",function(){
		loadLimitNumberInfo();
	});
}

function dealRelationDivShowInit(){
	initVkeId();
	var dealType=$("input[name='dealType']:checked").val();
	//1 主播，2 商户
	if(dealType == 1){
		$("#liveDiv").css("display","block");
		$("#sellerDiv").css("display","none");
		initLiveId();
	}else if(dealType == 2){
		$("#sellerDiv").css("display","block");
		$("#liveDiv").css("display","none");
		initSellerId();
	}

	$("#limitNumber").val(0);
}

//初始化商家下拉框
function initSellerId(){
	var status=3;
	var sellerIdSelectDiv= $("#sellerIdSelect").html('');
	sellerIdSelectDiv.html('<select class="form-control" name="sellerId" id="sellerId" style="width: 70%; float: left;" ></select>');	
	$("#sellerId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/init/list.jhtml?t=new Date()",
		filterVal:status,////过滤的值 (filterVal=type)
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	$("#sellerId").bind("change",function(){
		 loadSellerForVkeInfo();
	});
}

//初始化直播下拉框
function initLiveId(){
	var liveIdSelectDiv = $("#liveIdSelect").html('');
	liveIdSelectDiv.html('<select class="form-control" name="liveId" style="width: 70%; float: left;" ></select>');
	$("#addRelationModal").find("[name=liveId]").chosenObject({
		hideValue : "uid",
		showValue : "nickname",
		url : "anchor/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
	
	$("#liveId").bind("change", function(){
		 loadLiveForVkeInfo();
	});
}


/**
 * 加载V客推荐主播名额
 * @param uid
 */
function loadLimitNumberInfo(){
//	debugger;
	var dealType=$("input[name='dealType']:checked").val();
	var vkeUid =  $("#vkeId").find("option:selected").val();
	if(vkeUid){
		$.ajax({
			type: "POST",
			url : "recommendMember/manage/init/leaveUseQuota.jhtml?t=new Date()",
			dataType : "json",
			data: {"vkeUid":vkeUid, "type": dealType},
			success : function(data){
				if(data != null){
					//V客推荐名额
					var leaveUseQuota=data.leaveUseQuota;
					$("#limitNumber").val(leaveUseQuota);
//					$("#superiorDiv").css("display","block");
				}else{
					$("#limitNumber").val(0);
				}
			}
		});
	}
};


/**
 * 加载主播推荐的V客
 * @param uid
 */
function loadLiveForVkeInfo(){
//	debugger;
	var liveUid=$("#liveId").find("option:selected").val();//上级uid
	if(liveUid){
		$.ajax({
			type: "POST",
			url : "recommendMember/manage/init/getVkeNameByLive.jhtml?t=new Date()",
			dataType : "json",
			data: {"liveUid":liveUid},
			success : function(data){
				if(data != null){
					//上级显示手机号
					var vkeName=data.vkeName;
					$("input[name='vkeNameForLive']").val(vkeName);
//					$("#superiorDiv").css("display","block");
				}else{
					$("input[name='vkeNameForLive']").val("无推荐人");
				}
			}
		});
	}
};

/**
 * 加载商家对应的V客
 * @param uid
 */
function loadSellerForVkeInfo(){
//	debugger;
	var superiorUid=$("#superiorUid").val();//上级uid
	if(superiorUid){
		$.ajax({
			type: "POST",
			url : "recommendMember/manage/init/getVkeNameBySeller.jhtml?t=new Date()",
			dataType : "json",
			data: {"uid":superiorUid},
			success : function(data){
				if(data != null){
					//上级显示手机号
					var vkeName=data.vkeName;
					$("input[name='vkeNameForSeller']").val(vkeName);
//					$("#superiorDiv").css("display","block");
				}
			}
		});
	}
};


/**
 * 保存V客关联信息
 */
function saveRelationship() {
//	debugger;
	var suffix = ".jhtml";
	
	var vkeUid =  $("#vkeId").find("option:selected").val();
	var relationId = 0;
	var dealType=$("input[name='dealType']:checked").val();
	if (dealType == 1 ){
		relationId =  $("#liveId").find("option:selected").val();
	}else if (dealType == 2 ){
		relationId =  $("#sellerId").find("option:selected").val();
	}
	var result= validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	var url = "recommendMember/manage/addRelationship" + suffix;
	$.ajax({
		type : 'post',
		url : url,
		data :  {"type":dealType, "vkeUid": vkeUid, "relationId": relationId},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			$('#addRelationModal').modal('hide');
			if (data.success) {
				 setTimeout(function() {
					pageDiv.reset();
				}, 2000);
			}
			showSmReslutWindow(data.success, data.msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	
	var limitNumber = $('#limitNumber').val();
	if(limitNumber==0 || limitNumber < 0){
		showWarningWindow("warning","V客名额为0不能绑定!",9999);
		result=false;
		return result;
	}
	var dealType=$("input[name='dealType']:checked").val();
	if (dealType == 1 ){//主播
		var anchorId = $('#liveId').find("option:selected").val();
		if(anchorId == null || anchorId==""){
			showWarningWindow("warning","请选择主播!",9999);
			result=false;
			return result;
		}
		var vkeNameForLive = $("input[name='vkeNameForLive']").val();
		if (vkeNameForLive != ""){
			showWarningWindow("warning","V客不能绑定该主播!",9999);
			result=false;
			return result;
		}
	}else if (dealType == 2 ){//商家
		var sellerId=$("#sellerId").find("option:selected").val();
		if(sellerId == null || sellerId==""){
			showWarningWindow("warning","请选择店铺!",9999);
			result=false;
			return result;
		}
		var vkeNameForSeller = $("input[name='vkeNameForSeller']").val();
		if (vkeNameForSeller != ""){
			showWarningWindow("warning","V客不能绑定该主播!",9999);
			result=false;
			return result;
		}
	}
	return result;
}

$("input:radio[name='dealType']").eq(0).prop("checked",'checked');  //.attr

$("input[name='dealType']").change(function(){
	dealRelationDivShowInit();
});

// 新增新人推荐
function editVkeRelationship() {
	$("input:radio[name='dealType']").eq(0).prop("checked",'checked');  //.attr
	$("#limitNumber").val(0);
	initSelectDataList();
	
//	$("#editFreshmanModal").find("[class='modal-title']").html("添加推荐视频");	
	//显示窗口
	$('#addRelationModal').modal('show');
}



