//var groupEditKey = null;
var chooseUrlBtn;
var chooseDediBtn;
var formId = "editForm";
var ISTYPE;
var jsonTextInit;
var urlReg=new RegExp(/^((https|http|ftp|rtsp|mms)?:\/\/)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/); 
inserTitle(' > <span>首页模块列表</span>', 'moduleList', false);

$(function(){
	/**
	 * banner图初始化
	 */
	bannerInit();
	iconInit();	
	activityInit();
	dediInit();
	$("#product_form").find("#activityChoose").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "fresh/activity/getActivityChoose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%",
		inherit_select_classes : true
	});
	$("#product_form").find("#killChoose").chosenObject({
		hideValue : "id",
		showValue : "title",
		url : "fresh/kill/getKillChoose.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%",
		inherit_select_classes : true
	});
	$("#dediProduct").chosenObject({
		hideValue : "codeId",
		showValue : "pname",
		url : "fresh/activity/getProduct.jhtml",
		isChosen : true, //是否支持模糊查询
		isCode : true, //是否显示编号
		isHistorical : false, //是否使用历史已加载数据
		width : "100%"
	});
	brandChooseInit();
	
	urlChoose();
});

function bannerInit() {
	for (var i = 0; i < $("div[id^='picUrlImg']").size(); i++) {
		$("#picUrlImg" + i).uploadImg({
			urlId : "picUrl" + i,
			showImg : $('#picUrl' + i).val()
		});
	}
}

function iconInit() {
	for (var i = 0; i < $("div[id^='iconUrlImg']").size(); i++) {
		$("#iconUrlImg" + i).uploadImg({
			urlId : "iconUrl" + i,
			showImg : $('#iconUrl' + i).val()
		});
	}
}

function activityInit() {
	for (var i = 0; i < $("div[id^='activityUrlImg']").size(); i++) {
		$("#activityUrlImg" + i).uploadImg({
			urlId : "activityUrl" + i,
			showImg : $('#activityUrl' + i).val()
		});
	}
}

function dediInit(){
	var dediUrls=$("#dedi_border").find("[name^='dediUrl']");
	var activityIds=$("#dedi_border").find("[name^='activityId']");
	$.each(dediUrls,function(i,item){
		var _item=$(item);
		_item.attr("id","dediUrl"+i);
		var iconUrlImg = _item.next().attr("id", "dediUrlImg" + (i + 1));
		iconUrlImg.uploadImg({
			urlId : _item.attr("id"),
			showImg : _item.val()
		});
		
	});
	$.each(activityIds,function(i,item){
		var _item=$(item);
		_item.attr("id","activityId"+i);
		_item.chosenObject({
			hideValue : "id",
			showValue : "title",
			url : "fresh/activity/getActivityChoose.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%",
			inherit_select_classes : true
		});
	});
}

function brandChooseInit(){
	var brandChoose=$("#brand_border").find("[name^='brandId']");
	$.each(brandChoose,function(i,item){
		var _item = $(item);
		_item.chosenObject({
			hideValue : "id",
			showValue : "name",
			url : "fresh/manage/getBrand.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%"
		});
	});
}



function addModule(moduleId) {
	if (moduleId != "dedi_module") {
		var module = $("#panel").find("#" + moduleId);
		if (module.size() > 0) {
			showWarningWindow("warning", "该模块已存在!", 9999);
			return;
		}
	}
	if (moduleId == "banner_module") {
		var module = $("#" + moduleId).clone(true);
		var picUrls = module.find("[name^='picUrl']");
		$.each(picUrls, function(i, item) {
			var picUrl = $(item).attr("id", "picUrl" + (i + 1)).attr("name", "picUrl" + (i + 1));
			var picUrlImg = $(item).next().attr("id", "picUrlImg" + (i + 1));
			picUrlImg.uploadImg({
				urlId : picUrl.attr("id"),
				showImg : picUrl.val()
			});
		});
		module.appendTo("#banner_border").show();
	}
	else if (moduleId == "icon_module") {
		var module = $("#" + moduleId).clone(true);
		var iconUrls = module.find("[name^='iconUrl']");
		$.each(iconUrls, function(i, item) {
			var iconUrl = $(item).attr("id", "iconUrl" + (i + 1));
			var iconUrlImg = $(item).next().attr("id", "iconUrlImg" + (i + 1));
			iconUrlImg.uploadImg({
				urlId : iconUrl.attr("id"),
				showImg : iconUrl.val()
			});
		});
		module.appendTo("#icon_border").show();
	}else if(moduleId =='activity_module'){
		var module = $("#" + moduleId).clone(true);
		var activityUrls = module.find("[name^='activityUrl']");
		$.each(activityUrls, function(i, item) {
			var activityUrl = $(item).attr("id", "activityUrl" + (i + 1));
			var activityUrlImg = $(item).next().attr("id", "activityUrlImg" + (i + 1));
			activityUrlImg.uploadImg({
				urlId : activityUrl.attr("id"),
				showImg : activityUrl.val()
			});
		});
		var jumpType=module.find("[name^=JumpType]");
		$.each(jumpType,function(i,item){
			$(item).attr("name","activity"+$(item).attr("name"))
		});
		module.appendTo("#activity_border").show();
	}
	else if (moduleId == "product_module") {
		var module = $("#" + moduleId).clone(true);
		module.find("#productType_activity").attr("checked", "checked");
		module.find("#activityChoose").chosenObject({
			hideValue : "id",
			showValue : "title",
			url : "fresh/activity/getActivityChoose.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%",
			inherit_select_classes : true
		});
		module.find("#killChoose").chosenObject({
			hideValue : "id",
			showValue : "title",
			url : "fresh/kill/getKillChoose.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%",
			inherit_select_classes : true
		});
		module.appendTo("#product_border").show();
	}
	else if (moduleId == "dedi_module") {
		var modules = $("#panel").find("[id^=dedi_module]");
		var num = modules.size() + 1;
		var module = $("#copyModule").find("#" + moduleId).clone(true);
		console.log(module);
		var dediUrl = module.find("[name='dediUrl']").attr("id",dediUrl+num);
		var dediUrlImg = dediUrl.next().attr("id", "dediUrlImg" + num);
		dediUrlImg.uploadImg({
			urlId : dediUrl.attr("id"),
			showImg : dediUrl.val()
		});
		module.find("#product_activity").attr("checked", "checked");
		module.find("[name='activityId']").attr("id", "dedi_activityChoose" + num).chosenObject({
			hideValue : "id",
			showValue : "title",
			url : "fresh/activity/getActivityChoose.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%",
			inherit_select_classes : true
		});
		module.find("[name='dedi_product_choose']").attr("id", "dedi_brand" + num).chosenObject({
			hideValue : "codeId",
			showValue : "pname",
			url : "fresh/activity/getProduct.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%"
		});
		module.find("tbody").attr("id", "tbody" + num);
		var activityProductTypes=module.find("[name^='activityProductType']");
		$.each(activityProductTypes,function(i,item){
			$(item).attr("id","activityProductType"+(num-1));
		});
		module.appendTo("#dedi_border").show();
	}
	else if (moduleId == "brand_module") {
		var module = $("#" + moduleId).clone(true);
		var brandSelects = module.find("[name=brandId]");
		$.each(brandSelects, function(i, item) {
			var _item = $(item);
			_item.attr("id", "brandId" + i);
			_item.chosenObject({
				hideValue : "id",
				showValue : "name",
				url : "fresh/manage/getBrand.jhtml",
				isChosen : true, //是否支持模糊查询
				isCode : true, //是否显示编号
				isHistorical : false, //是否使用历史已加载数据
				width : "100%"
			});
		});
		module.appendTo("#brand_border").show();
	}
	else if(moduleId=="boutique_module"){
		$("#" + moduleId).clone(true).appendTo("#boutique_border").show();
	}
}

function upModule(item) {
	var module = $(item).parents(".border");
	var lastModule = module.prev();
	if (lastModule.size() > 0 && lastModule.hasClass("border")) {
		module.insertBefore(lastModule);
	}
}

function downModule(item) {
	var module = $(item).parents(".border");
	var lastModule = module.next();
	if (lastModule.size() > 0 && lastModule.hasClass("border")) {
		lastModule.insertBefore(module);
	}
}

function deleteModule(item,id,type){
	if(!id&&!type){
		$(item).parents(".border").remove();
	}else{
		
		if(type){
			var url="fresh/module/deleteBanner.jhtml";
			if(type==-1){
				url="fresh/module/deleteBrand.jhtml";
			}
			showSmConfirmWindow(function() {
				$.post(url, {
					"typeId" : typeId,"type":type
				}, function(data, status) {
					if (status == "success") {
						showSmReslutWindow(data.success, data.msg);
						if(data.success){
							$(item).parents(".border").remove();
						}
					}
					else {
						window.messager.warning(data.msg);
					}
				})
			}, "确定要此模块吗？");
		}else{
			showSmConfirmWindow(function() {
				$.post("fresh/module/deleteModule.jhtml", {
					"id":id
				}, function(data, status) {
					if (status == "success") {
						showSmReslutWindow(data.success, data.msg);
						if(data.success){
							$(item).parents(".border").remove();
						}
					}
					else {
						window.messager.warning(data.msg);
					}
				})
			}, "确定要此模块吗？");
		}
	}
}

function deleteBanner(item) {
	var module = $(item).parents(".border");
	if (module.find(".form-group").size() <= 2) {
		return;
	}
	var group = module.find(".form-group:last");
	var textGroup = group.prev();
	var hr = textGroup.prev("hr");
	group.remove();
	textGroup.remove();
	hr.remove();
}

function addBanner(item) {
	var module = $(item).parents(".border");
	if (module.find("[name^='picUrl']").size() >= 5) {
		return;
	}
	var group = module.find(".form-group:last");
	var textGroup = group.prev();
	var num = textGroup.find("[name^='picUrl']").attr("name").replace("picUrl", "");
	var newGroup = textGroup.clone(true);
	var picUrl = newGroup.find("[name^='picUrl']").attr("name", "picUrl" + num * 1 + 1);
	picUrl.attr("id", "picUrl" + num * 1 + 1);
	var picUrlImg = newGroup.find("[id^='picUrlImg']").attr("id", "picUrlImg" + num * 1 + 1);
	picUrlImg.uploadImg({
		urlId : picUrl.attr("id"),
		showImg : picUrl.val()
	});
	$("<hr>").appendTo(module.children());
	newGroup.appendTo(module.children());
	var newGroup2 = group.clone(true);
	newGroup2.find("[name^='bannerUrl']").attr("name", "bannerUrl" + num * 1 + 1);
	newGroup2.find("[name^='picUrl']").attr("name", "picUrl" + num * 1 + 1);
	newGroup2.find("[id^='picUrl']").attr("id", "picUrl" + num * 1 + 1);
	newGroup2.appendTo(module.children());
}

function addBrand(item) {
	for (i = 0; i < 4; i++) {
		var module = $(item).parents(".border");
		if (module.find(".form-group").size() >= 8) {
			return;
		}
		var group = $("#brand_group").clone(true);
		group.attr("id","");
		var brand = group.find("[name^=brandId]");
		var num = module.find(".form-group").size();
		;
		brand.attr("id", "brandId" + num);
		brand.chosenObject({
			hideValue : "id",
			showValue : "name",
			url : "fresh/manage/getBrand.jhtml",
			isChosen : true, //是否支持模糊查询
			isCode : true, //是否显示编号
			isHistorical : false, //是否使用历史已加载数据
			width : "100%"
		});
		group.appendTo(module.children()).show();
	}
}

function deleteBrand(item) {
	var module = $(item).parents(".border");
	if (module.find(".form-group").size() <= 4) {
		return;
	}
	for (i = 0; i < 4; i++) {
		var group = module.find(".form-group:last");
		group.remove();
	}
}

function urlChoose(){
	$("[name='url_choose']").on("click",function(){
		var val=$(this).val();
		if(val==1){
			$("#url_choose_product").show();
			$("#url_choose_activity").hide();
		}else if(val==2){
			$("#url_choose_product").hide();
			$("#url_choose_activity").show();
		}
	});
}

$("#brand").chosenObject({
	hideValue : "codeId",
	showValue : "pname",
	url : "fresh/activity/getProduct.jhtml",
	isChosen : true, //是否支持模糊查询
	isCode : true, //是否显示编号
	isHistorical : false, //是否使用历史已加载数据
	width : "100%",
	inherit_select_classes : true
});


$("#kill").chosenObject({
	hideValue : "id",
	showValue : "title",
	url : "fresh/kill/getKillChoose.jhtml",
	isChosen : true, //是否支持模糊查询
	isCode : true, //是否显示编号
	isHistorical : false, //是否使用历史已加载数据
	width : "100%",
	inherit_select_classes : true
});





$(".chooseUrlBtn").on("click", function() {
	chooseUrlBtn = $(this);
});
$(".chooseDediBtn").on("click", function() {
	chooseDediBtn = $(this);
});

$("#chooseSubmit").on("click", function() {
	var val=$("[name='url_choose']:checked").val();
	var text;
	if(val==2){
	   text = $("#kill_chosen").find("span").text();
	   chooseUrlBtn.parent().prev("input").attr("jumptype",6);
	}else if(val==1){
		text = $("#brand_chosen").find("span").text();
		chooseUrlBtn.parent().prev("input").attr("jumptype",1);
	}
	if(text=='--请选择--'||!text){
		return;
	}
	chooseUrlBtn.parent().prev("input").val(text);
	chooseUrlBtn = null;
	$('#myModal').modal('hide');
});

$("#dediModalSubmit").on("click", function() {
	var text = $("#dediProduct_chosen").find("span").text();
	var codeId=$("#dediProduct").val();
	var sort=$("#dediSort").val();
	var tr=$("<tr id="+codeId+">").append($("<td>").text(codeId)).append($("<td>").text(text))
	.append($("<td>").text(sort));
	var productJson={
			"codeId":codeId,
			"sort": sort
	}
	tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">').html('&nbsp;&nbsp;<a href="javascript:;" onclick="deleteGroup(this)">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(productJson)));
	chooseDediBtn.parents((".border")).find("[name^='tbody']").append(tr);
	chooseDediBtn = null;
	$('#dediModal').modal('hide');
});

$("#productType_choice").on("click", function() {
	$("#activityChoose_form").hide();
	$("#killChoose_form").hide();
});
$("#productType_jixuan").on("click", function() {
	$("#activityChoose_form").hide();
	$("#killChoose_form").hide();
});
$("#productType_activity").on("click", function() {
	$("#activityChoose_form").show();
	$("#killChoose_form").hide();
});
$("#productType_kill").on("click", function() {
	$("#killChoose_form").show();
	$("#activityChoose_form").hide();
});

$("[name^='activityProductType']").on("click", function() {
	if($(this).val()==2){
		$(this).parent().parent().next("#activity_product").show();
		$(this).parents(".border").find("[name='activity_choose']").hide();
	}else if($(this).val()==1){
		$(this).parent().parent().next("#activity_product").hide();
		$(this).parents(".border").find("[name='activity_choose']").show();
	}
});



/*$("[name='dedi_product_choose']").on("change", function() {
	var _this = $(this);
	var pid = _this.val();
	var selectId = _this.attr("id");
	var num = selectId.charAt(selectId.length - 1);
	console.log(num);
	if ($("#tbody" + num).find("tr[id=" + pid + "]").size()) {
		return;
	}
	groupEditKey = $("#tbody" + num);
	var but = $("<button>").attr("data-type", "ajax").attr("data-url", "fresh/activity/group/init.jhtml?pid=" + pid).attr("data-toggle", "modal").attr("style", "display:none;");
	$("body").append(but);
	but.trigger("click");
});*/


function saveModule(item){
	console.log("调用保存方法");
	var module
	if($(item).get(0).tagName=='FORM'){
		module=$(item).find(".border");
	}else{
		module = $(item).parents(".border");
	}
	var moduleId=module.attr("id");
	var success=false;
	if(moduleId=="banner_module"){
		var bannerUrls=module.find("[name^=bannerUrl]");
		var imageUrls=module.find("[name^=picUrl]");
		$.each(imageUrls,function(i,item){
			if(!$(item).val()){
				showWarningWindow("warning","请上传banner图片!",9999);
				success=true;
				return false;
			}
		});
		$.each(bannerUrls,function(i,item){
			var jumpUrl=$(item).val();
			if(jumpUrl&&jumpUrl.indexOf('[')!=0){
				if(!jumpUrl.match(urlReg)){
					showWarningWindow("warning","请正确填写http开头链接或选择商品链接",9999);
					success=true;
					return false;
				}
			}
		});
		if(success){
			return;
		}
		var arr=new Array();
		for(i=0;i<bannerUrls.size();i++){
			var bannerUrl=bannerUrls.eq(i).val();
			var imageUrl=imageUrls.eq(i).val();
			var jumpType=bannerUrls.eq(i).attr("jumpType");
			var freshImage={"imageUrl":imageUrl,"jumpUrl":bannerUrl,"typeId":typeId,"type":1,"jumpType":jumpType}
			arr.push(freshImage);
		}
		var json=$.toJSON(arr);
		console.log(json);
		$.ajax({
			type : 'post',
			url : "fresh/module/saveFreshImage.jhtml",
			data : {"json":json},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				window.messager.warning(data.msg);
			}
		});
	}else if(moduleId=="icon_module"){
		var iconUrls=module.find("[name^='iconUrl']");
		$.each(iconUrls,function(i,item){
			if(!$(item).val()){
				showWarningWindow("warning","请上传图标图片!",9999);
				success=true;
				return false;
			}
		});
		var iconBackUrls=module.find("[name^='iconBackUrl']");
		$.each(iconBackUrls,function(i,item){
			var jumpUrl=$(item).val();
			if(!jumpUrl){
				showWarningWindow("warning","请输入链接!",9999);
				success=true;
				return false;
			}else if(jumpUrl.indexOf('[')!=0){
				if(!jumpUrl.match(urlReg)){
					showWarningWindow("warning","请正确填写http开头链接或选择商品链接",9999);
					success=true;
					return false;
				}
			}
		});
		var iconTitles=module.find("[name^='iconTitle']");
		$.each(iconTitles,function(i,item){
			if(!$(item).val()){
				showWarningWindow("warning","请输入标题!",9999);	
				success=true;
				return false;
			}
		});
		if(success){
			return;
		}
		var arr=new Array();
		for(i=0;i<iconUrls.size();i++){
			var iconUrl=iconUrls.eq(i).val();
			var iconBackUrl=iconBackUrls.eq(i).val();
			var jumpType=iconBackUrls.eq(i).attr("jumpType");
			var iconTitle=iconTitles.eq(i).val();
			var freshImage={"imageUrl":iconUrl,"jumpUrl":iconBackUrl,"title":iconTitle,"typeId":typeId,"type":2,"jumpType":jumpType}
			arr.push(freshImage);
		}
		var json=$.toJSON(arr);
		$.ajax({
			type : 'post',
			url : "fresh/module/saveFreshImage.jhtml",
			data : {"json":json},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				window.messager.warning(data.msg);
			}
		});
	}else if(moduleId=='activity_module'){
		var activityUrls=module.find("[name^='activityUrl']");
		$.each(activityUrls,function(i,item){
			if(!$(item).val()){
				showWarningWindow("warning","请上传图标图片!",9999);
				success=true;
				return false;
			}
		});
		var activityJumpTypes=module.find("[name^='activityJumpType']:checked");
		var activityTitles=module.find("[name^='activityTitle']");
		$.each(activityTitles,function(i,item){
			if(!$(item).val()){
				showWarningWindow("warning","请输入标题!",9999);	
				success=true;
				return false;
			}
		});
		if(success){
			return;
		}
		var arr=new Array();
		for(i=0;i<activityUrls.size();i++){
			var activityUrl=activityUrls.eq(i).val();
			var activityTitle=activityTitles.eq(i).val();
			var jumpType=activityJumpTypes.eq(i).val();
			var freshImage={"imageUrl":activityUrl,"title":activityTitle,"typeId":typeId,"type":3,"jumpType":jumpType}
			arr.push(freshImage);
		}
		var json=$.toJSON(arr);
		$.ajax({
			type : 'post',
			url : "fresh/module/saveFreshImage.jhtml",
			data : {"json":json},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				window.messager.warning(data.msg);
			}
		});
	}
	else if(moduleId=="product_module"){
		 var data = $('#product_form').serializeArray();
		 data = jsonFromt(data);
		 var radio=module.find('input:radio:checked').val();
		 if(radio=="4"){
			var choose =  module.find("#activityChoose");
			if(!choose.val()){
				showWarningWindow("warning","请选择活动!",9999);
				return;
			}
			data.activityId=choose.val();
		 }else if(radio=='3'){
			 var choose =  module.find("#killChoose");
			 if(!choose.val()){
					showWarningWindow("warning","请选择活动!",9999);
					return;
				}
			 data.activityId=choose.val();
		 }else{
			 data.activityId=''; 
		 }
		 $.ajax({
				type : 'post',
				url : "fresh/module/saveModule.jhtml",
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					if(data.data){
						if(module.find("input[name='id']").size()==0){
							$("<input/>").attr("type","hidden").attr("name","id").val(data.data).appendTo(module);
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(data) {
					$('#prompt').hide();
					window.messager.warning(data.msg);
				}
			});
	}else if(moduleId=="brand_module"){
		var brands=module.find("[name^=brandId]");
		$.each(brands,function(i,item){
			var _item=$(item);
			if(!_item.val()){
				showWarningWindow("warning","请选择品牌!",9999);
				success=true;
				return;
			}
		});
		if(success){
			return;
		}
		var arr=new Array();
		for(i=0;i<brands.size();i++){
			var brandId=brands.eq(i).val();
			var hotBrand={"brandId":brandId,"typeId":typeId}
			arr.push(hotBrand);
		}
		var json=$.toJSON(arr);
		$.ajax({
			type : 'post',
			url : "fresh/module/saveHotBrand.jhtml",
			data : {"json":json},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				window.messager.warning(data.msg);
			}
		});
	}else if(moduleId=="boutique_module"){
		var data = $('#boutique_form').serializeArray();
		 data = jsonFromt(data);
		 $.ajax({
				type : 'post',
				url : "fresh/module/saveModule.jhtml",
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					if(data.data){
						if(module.find("input[name='id']").size()==0){
							$("<input/>").attr("type","hidden").attr("name","id").val(data.data).appendTo(module);
						}
					}
					showSmReslutWindow(data.success, data.msg);
				},
				error : function(data) {
					$('#prompt').hide();
					window.messager.warning(data.msg);
				}
			});
	}else if(moduleId=="dedi_module"){
		var moduleName=module.find("input[name='moduleName']").val();
		if(!moduleName){
			showWarningWindow("warning","请选输入模块名称!",9999);
			return;
		}
		var dediUrl=module.find("input[name='dediUrl']").val();
		if(!dediUrl){
			showWarningWindow("warning","请输入图片!",9999);
			return;
		}
		var jumpUrl=module.find("input[name='jumpUrl']").val();
		if(!jumpUrl){
			showWarningWindow("warning","请输入链接!",9999);
			return;
		}
		var activityId=module.find("[name='activityId']").val();
		
		var activityProductType=module.find('input:radio:checked').val();
		var showNum=module.find("[name='showNum']").val();
		if(!showNum){
			showWarningWindow("warning","请输入显示数量!",9999);
			return;
		}
		if(showNum<2||showNum>999999999){
			showWarningWindow("warning","请输入2-999999999范围的数量!",9999);
			return;
		}
		var sort =module.find("[name='sort']").val();
		var id = module.find("[name='id']").val();
		var modulePojo={
				"moduleName":moduleName,
				"imageUrl":dediUrl,
				"jumpUrl":jumpUrl,
				"activityId":activityId,
				"activityProductType":activityProductType,
				"typeId":typeId,
				"showNum":showNum,
				"sort":sort,
				"id":id
		}
		if(activityProductType==2){
			var hiddens=module.find("[name='tbody']").find("input[type=hidden]");
			if(hiddens.length==0){
				showWarningWindow("warning","请选择商品!",9999);
				return;
			}
			var json=new Array()
			$.each(hiddens,function(i,item){
				json.push($(item).val());
			});
			var obj={
					"json":json
			}
			modulePojo.activityId='';
			modulePojo.ProductJsonString=$.toJSON(obj);
			console.log(modulePojo.id);
		}else{
			if(!activityId){
				showWarningWindow("warning","请选择活动!",9999);
				return;
			}
		}
		$.ajax({
			type : 'post',
			url : "fresh/module/saveDediModule.jhtml",
			data : modulePojo,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				if(module.find("input[name='id']").size()==0){
					$("<input/>").attr("type","hidden").attr("name","id").val(data.data).appendTo(module.children("div").get(0));
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(data) {
				$('#prompt').hide();
				window.messager.warning(data.msg);
			}
		});
	}
		
}



validate("product_form",{
	rules : {
		moduleName : {
			required : true
		},
		showNum : {
			required : true,
			digits:true,
			range:[2,999999999]
		}
	},
	messages:{
		moduleName:{
			required:"请输入模块名字",
		},
		showNum : {
		required : "请输入数量",
		digits:"请输入2至999999999之间的整数",
		range:"请输入2至999999999之间的整数"
	}
	}
},saveModule);


validate("boutique_form",{
	rules : {
		moduleName : {
			required : true
		},
		showNum : {
			required : true,
			digits:true,
			range:[2,999999999]
		}
	},
	messages:{
		moduleName:{
			required:"请输入模块名字",
		},
		showNum : {
		required : "请输入数量",
		digits:"请输入2至999999999之间的整数",
		range:"请输入2至999999999之间的整数"
	}
	}
},saveModule);

function deleteGroup(item){
	var _item=$(item);
	_item.parents("tr").remove();
}

function loadingType(typeId){
	window.location.href="fresh/module/init.jhtml?type="+typeId;
}


function IsURL (str_url) { 
	var strRegex = '^((https|http|ftp|rtsp|mms)?://)'
	+ '?(([0-9a-z_!~*\'().&=+$%-]+: )?[0-9a-z_!~*\'().&=+$%-]+@)?' //ftp的user@ 
	+ '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184 
	+ '|' // 允许IP和DOMAIN（域名） 
	+ '([0-9a-z_!~*\'()-]+.)*' // 域名- www. 
	+ '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名 
	+ '[a-z]{2,6})' // first level domain- .com or .museum 
	+ '(:[0-9]{1,4})?' // 端口- :80 
	+ '((/?)|' // a slash isn't required if there is no file name 
	+ '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$'; 
	var re=new RegExp(strRegex); 
	//re.test() 
	if (re.test(str_url)) { 
	return (true); 
	} else { 
	return (false); 
	} 
	}