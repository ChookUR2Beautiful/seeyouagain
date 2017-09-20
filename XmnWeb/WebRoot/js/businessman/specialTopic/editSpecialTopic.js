var ISTYPE
var formId=["specialTopicForm"];

$(document).ready(function() {
	ISTYPE = $("#isType").val();
	var id = $('#id').val();
	if(ISTYPE == "add"){
		inserTitle(' > 添加专题信息','addSpecialTopic', false);
//		inserTitle(' > <span><a href="businessman/specialTopic/add/init.jhtml?sellerid='+id+'&isType=add" target="right">添加专题信息</a>','addSpecialTopic', true);
	}else{
		inserTitle(' > 编辑专题信息','addSpecialTopic', false);
	}
	
	/**
	 * 返回
	 */
	 $("#backId").on("click",function(){
		 muBack();
	 });
	 
	// 清除ckEditor实例
	if (CKEDITOR.instances['content']) {
		CKEDITOR.instances['content'].destroy(true);
	}
	// 初始化富文本编辑器
	$("textarea#content").ckeditor({

	});
	
	$('#topicType').trigger("change");
	
});

//初始化下拉框选择
initSelectDataList();


//*************************初始化下拉选择信息**************************
function initSelectDataList(){
	//商家信息
	initsellerId();
	//连锁店信息
	initmultipId();
	//区域代理
	initallianceId();
	
	//直播信息
	initliveId();
	//预告信息
	initbeforliveId();
	
	//粉丝信息
	initfansCouponId();
	//套餐信息
	initsellerPackageId();
	//产品信息
	initproductInfoId();
}


//初始化商家下拉框
function initsellerId(){
	$("#sellerId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化连锁店下拉框
function initmultipId(){
	$("#multipId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/multipShop/init/multipShopList.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化区域代理下拉框
function initallianceId(){
	$("#allianceId").chosenObject({
		hideValue : "id",
		showValue : "allianceName",
		url : "businessman/allianceShop/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化直播下拉框
function initliveId(){
	$("#liveId").chosenObject({
		hideValue : "id",
		showValue : "nickname",
		url : "anchor/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化预告下拉框
function initbeforliveId(){
	$("#beforliveId").chosenObject({
		hideValue : "id",
		showValue : "zhiboTitle",
		url : "liveRecord/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化粉丝券下拉框
function initfansCouponId(){
	$("#fansCouponId").chosenObject({
		hideValue : "cid",
		showValue : "cname",
		url : "liveCoupon/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化套餐下拉框
function initsellerPackageId(){
	$("#sellerPackageId").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "sellerPackage/manage/init/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

//初始化商品下拉框
function initproductInfoId(){
	$("#productInfoId").chosenObject({
		hideValue : "codeId",
		showValue : "pname",
		url : "fresh/manage/list.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


//微信群二维码图片
$("#activityImg").uploadImg({
	urlId : "picUrl",
	showImg : $('#picUrl').val()
});

/*商家*/
$("#sellerId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "businessman/seller/init/list.jhtml";
	var seller = {
			sellerid:pid};
	$.ajax({
		type : 'post',
		url : url,
		data : seller,
		dataType : 'json',
		success : function(data) {
			var sellerid= undefined == data.content[0].sellerid ? "-" : data.content[0].sellerid;
			var sellername= undefined ==data.content[0].sellername ? "-" :data.content[0].sellername;
			var address= undefined ==data.content[0].address ? "-" :data.content[0].address;
		    var map={
		    		"type":"edit",
					"subId":sellerid,
					"subName":sellername,
					"address":address
				};
			var tr=$("<tr id="+sellerid+">").append($("<td>").text(sellerid)).append($("<td>").text(sellername)).append($("<td>").text(address))
			
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+sellerid+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#sellerTB").append(tr);
		}
	});
});

/*连锁店*/
$("#multipId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "businessman/seller/init/list.jhtml";
	var seller = {
			ismultiple:1,
			sellerid:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : seller,
		dataType : 'json',
		success : function(data) {
			var sellerid=data.content[0].sellerid;
			var sellername=data.content[0].sellername;
			var subShopNum=data.content[0].subShopNum;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":sellerid,
					"subName":sellername,
					"subShopNum":subShopNum
//					"sort":sort
				};
			var tr=$("<tr id="+sellerid+">").append($("<td>").text(sellerid)).append($("<td>").text(sellername)).append($("<td>").text(subShopNum))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+sellerid+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			//活动添加页面
			$("#multipTB").append(tr);
		}
	});
});

/*区域代理*/
$("#allianceId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "businessman/allianceShop/init/list.jhtml";
	var alliance = {
			id:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : alliance,
		dataType : 'json',
		success : function(data) {
			var id=data.content[0].id;
			var allianceName=data.content[0].allianceName;
			var subShopNum=data.content[0].subShopNum;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":id,
					"subName":allianceName,
					"subShopNum":subShopNum
//					"sort":sort
				};
			var tr=$("<tr id="+id+">").append($("<td>").text(id)).append($("<td>").text(allianceName)).append($("<td>").text(subShopNum))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+id+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#allianceTB").append(tr);
		}
	});
});


/*主播*/
$("#liveId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "anchor/manage/init/list.jhtml";
	var multip = {
			id:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : multip,
		dataType : 'json',
		success : function(data) {
			var id=data.content[0].id;
			var nickname=data.content[0].nickname;
			var phone=data.content[0].phone;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":id,
					"subName":nickname,
					"phone":phone
//					"sort":sort
				};
			var tr=$("<tr id="+id+">").append($("<td>").text(id)).append($("<td>").text(nickname)).append($("<td>").text(phone))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+id+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#liveTB").append(tr);
		}
	});
});


/*预告*/
$("#beforliveId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "liveRecord/manage/init/list.jhtml";
	var multip = {
			id:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : multip,
		dataType : 'json',
		success : function(data) {
			var id=data.content[0].id;
			var zhiboTitle=data.content[0].zhiboTitle;
			var sellername=data.content[0].sellername;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":id,
					"subName":zhiboTitle,
					"sellername":sellername
//					"sort":sort
				};
			var tr=$("<tr id="+id+">").append($("<td>").text(id)).append($("<td>").text(zhiboTitle)).append($("<td>").text(sellername))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+id+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#beforeliveTB").append(tr);
		}
	});
});


/*粉丝劵 */
$("#fansCouponId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "liveCoupon/manage/init/list.jhtml";
	var fansCoupon = {
			cid:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : fansCoupon,
		dataType : 'json',
		success : function(data) {
			var cid=data.content[0].cid;
			var cname=data.content[0].cname;
			var sellername=data.content[0].sellername;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":cid,
					"subName":cname,
					"sellername":sellername
//					"sort":sort
				};
			var tr=$("<tr id="+cid+">").append($("<td>").text(cid)).append($("<td>").text(cname)).append($("<td>").text(sellername))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+cid+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#fansCouponTB").append(tr);
		}
	});
});

/*套餐 */
$("#sellerPackageId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "sellerPackage/manage/init/list.jhtml";
	var sellerPackage = {
			id:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : sellerPackage,
		dataType : 'json',
		success : function(data) {
			var id=data.content[0].id;
			var title=data.content[0].title;
			var sellername=data.content[0].sellername;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":id,
					"subName":title,
					"sellername":sellername
//					"sort":sort
				};
			var tr=$("<tr id="+id+">").append($("<td>").text(id)).append($("<td>").text(title)).append($("<td>").text(sellername))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+id+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#sellerPackageTB").append(tr);
		}
	});
});


/*商品 */
$("#productInfoId").on("change",function(){
	var pid=$(this).val();
	if($("tr[id="+pid+"]").size()){
		return;
	}
	var url;
	url = "fresh/manage/list.jhtml";
	var productInfo = {
			codeId:pid
		};
	$.ajax({
		type : 'post',
		url : url,
		data : productInfo,
		dataType : 'json',
		success : function(data) {
			var codeId=data.content[0].codeId;
			var pname=data.content[0].pname;
			var store=data.content[0].store;
//			var sort=$("#sort").val();
		    var map={
		    		"type":"edit",
					"subId":codeId,
					"subName":pname,
					"subShopNum":store
//					"sort":sort
				};
			var tr=$("<tr id="+codeId+">").append($("<td>").text(codeId)).append($("<td>").text(pname)).append($("<td>").text(store))
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					.html('<a href="javascript:;" onclick="deleteGroup('+codeId+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			
			//活动添加页面
			$("#productInfoTB").append(tr);
		}
	});
});




function deleteGroup(pid){
	$("#"+pid).remove();
}


//直播分账开关
$("#topicType").on("change", function(){
	$(this).val()==1 ? $("#sellerPanel").show(): $("#sellerPanel").hide();
	$(this).val()==2 ? $("#multipPanel").show(): $("#multipPanel").hide();
	$(this).val()==3 ? $("#alliancePanel").show(): $("#alliancePanel").hide();
	$(this).val()==4 ? $("#livePanel").show(): $("#livePanel").hide();
	
	$(this).val()==5 ? $("#beforlivePanel").show(): $("#beforlivePanel").hide();
	$(this).val()==6 ? $("#fansCouponPanel").show(): $("#fansCouponPanel").hide();
	$(this).val()==7 ? $("#sellerPackagePanel").show(): $("#sellerPackagePanel").hide();
	$(this).val()==8 ? $("#productInfoPanel").show(): $("#productInfoPanel").hide();
	
});

/**=
 * 初始化验证方法
 */
validate("specialTopicForm",{
	rules : {
		title : {
			required : true
		},
		description : {
			required : true
		},
		img : {
			required : true
		}
	},
	messages:{
		typeId:{
			required:"请输入专题标题",
		},
		remark:{
			required:"请输入专题描述",
		},
		remark:{
			required:"请输入专题BANNER",
		}
	}
},save);


var hiddens;

function save(){
	getRelationInfo();
	/*if(hiddens.length==0){
		showWarningWindow("warning", "请选择关联商户!", 9999);
		return;
	}*/
//	debugger;
	var url;
	if(ISTYPE == "add"){
		url = "businessman/specialTopic/add.jhtml";
	}else{
		url = "businessman/specialTopic/update.jhtml";
	}
	
	var json = new Array()
	$.each(hiddens,function(i,item){
		json.push($(item).val());
	});
	var obj={json:json}
	json=$.toJSON(obj);
	
	var data = $('#specialTopicForm').serializeArray();
	data = jsonFromt(data);
	data.productJson=json;
	
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			window.location.href="businessman/specialTopic/init.jhtml";
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
}


function getRelationInfo(){
	var type = $("#topicType").val();
	
	if(1 == type){
		hiddens=$("#sellerTB").find("input[type=hidden]");
	}else if(2 == type){
		hiddens=$("#multipTB").find("input[type=hidden]");
	}else if(3 == type){
		hiddens=$("#allianceTB").find("input[type=hidden]");
	}else if(4 == type){
		hiddens=$("#liveTB").find("input[type=hidden]");
	}else if(5 == type){
		hiddens=$("#beforeliveTB").find("input[type=hidden]");	
	}else if(6 == type){
		hiddens=$("#fansCouponTB").find("input[type=hidden]");		
	}else if(7 == type){
		hiddens=$("#sellerPackageTB").find("input[type=hidden]");		
	}else if(8 == type){
		hiddens=$("#productInfoTB").find("input[type=hidden]");				
	}else{
		
	}
	
}



