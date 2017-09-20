var anchorList;
var initListUrl = "sunshineProfit/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 黄金庄园 > <a href="sunshineProfit/manage/init.jhtml" target="right">阳光收益管理</a>',
			'userSpan', true);
	anchorList = $("#anchorList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	
	$("#dates").on("click", ".icon-plus", function(event) {
		if ($(this).parents(".col-md-10").find(".input-group")) {
			addStoreProfitData(event);
		}
	});
	
	//导出
	$("#exportAnchor").click(function(){
		/*var size=getCurrentDataSize();
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}*/
		
		var path="sunshineProfit/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "searchForm";
	var title = "阳光收益列表", subtitle = "个阳光收益";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 100
		},{
			title : "会员昵称",
			name : "nickname",
			type : "string",
			width : 120
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 120
		}, {
			title : "类型",
			name : "channel",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				var result="";
				var channel = data.channel;  //庄园状态 1:启用 2:停用
				//17 推荐奖励 10 园又赠送 15 仓库存储收益
				if(channel == 17){//收支渠道
					result="推荐奖励";
				}else if(channel == 10){
					result="园友赠送";
				}else if(channel == 15){
					result="仓库存储收益";
				}else{
					result="其他";
				}
				
				return result;
			}
		},{
			title : "获得阳光（束）",
			name : "num",
			type : "string",
			width : 100
		}, {
			title : "来源用户",
			name : "giveName",
			type : "string",
			width : 100
		}, {
			title : "获得时间",
			name : "createTime",
			type : "string",
			width : 80
		} ]
	}, permissions);
}

 
 /**
  * 获取当前查询记录数
  */
 /*function getCurrentDataSize(){
	 var formId = "searchForm";
	 var total=0;
	// 设置同步
    $.ajaxSetup({
        async: false
    });
	 
	 $.ajax({
		 url : "sunshineProfit/manage/init/getCurrentDataSize.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 total=result;
		 }
	 });
	 
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
    
    return total;
 }*/
 
 
 //推荐奖励是否与下级消耗能量挂钩
 $("input[name='isSpendEnergy']").change(function() {
	if ($(this).is(':checked')) {
	    $('input[name=spendEnergyNumber]').removeAttr("readonly"); //去除input元素的readonly属性
	} else {
		$("input[name='spendEnergyNumber']").val("");  //直属下级消耗 .html
	    $("input[name='spendEnergyNumber']").attr("readonly", "true"); //去除input元素的readonly属性
	}
});
 
 
 //阳光渠道管理
 function sunshineChannelView() {
	// 初始化值清空栏位
	$("#sunshineChannelModal :input").each(function() {
		$(this).val("");
	});
	
	//加载数据
	var url='sunshineProfit/manage/list/viewDetail.jhtml';
	$.ajax({
		type : 'post',
		url : url,
		data :[id],
		dataType : 'json',
		success : viewSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#sunshineChannelModal').modal('hide');
		}
	});
}


function viewSuccess(data) {
	if (data.data){//获取到数据
		$("#sunshineChannelModal").find('input[name=id]').val(data.data.id);
		
		$("input[name='recommendNumber']").val(data.data.recommendNumber);  //推荐奖励获得阳光
    	/*var isSpendEnergy = data.data.isSpendEnergy;  // 推荐奖励是否与下级消耗能量挂钩
		if (isSpendEnergy == 1){
		    $("input[name='isSpendEnergy']").prop("checked",true);//勾选　
		    
		    $('input[name=spendEnergyNumber]').removeAttr("readonly");//去除input元素的readonly属性
			$("input[name='spendEnergyNumber']").val(data.data.spendEnergyNumber);  //直属下级消耗 .html
		}else{
		    $("input[name='isSpendEnergy']").prop("checked",false);//去掉勾选　
		    
		    $("input[name='spendEnergyNumber']").val("");  //直属下级消耗 .html
		    $("input[name='spendEnergyNumber']").attr("readonly", "true");//去除input元素的readonly属性
		    
		}*/
		$("input[name='handselNumber']").val(data.data.handselNumber);  //园友赠送阳光
	}
	
	//显示模态框数据
	$('#sunshineChannelModal').modal();
	
	// 点击关闭遮罩层
	$(".close-shade").on("click", function() {
		$(".shade-box,.shade-content").hide();
	});
}


$("#editSunshineChannelSubmit").on("click", function() {
	var id = $("#sunshineChannelModal").find("[name=id]").val();
	
	var recommendNumber = $("#sunshineChannelModal").find("[name=recommendNumber]").val();  //推荐获取奖励
	if (recommendNumber < 0) {
		showWarningWindow("warning", "请正确填写推荐奖励获得阳光!", 9999);
		return;
	}
	/*var isSpendEnergy = 0;  //推荐奖励是否与下级消耗能量挂钩
	var spendEnergyNumber = $("#sunshineChannelModal").find("[name=spendEnergyNumber]").val();  //推荐奖励需下级消耗的能量值
	if($("input[name='isSpendEnergy']").is(':checked')) {
		isSpendEnergy = 1;
		if (spendEnergyNumber < 0) {
			showWarningWindow("warning", "请正确填写能量邀请人获得推荐奖励!", 9999);
			return;
		}	
	}*/
	var handselNumber = $("#sunshineChannelModal").find("[name=handselNumber]").val();  //园友赠送阳光
	
	var data = {
		"id" : id,
		"recommendNumber" : recommendNumber,
		"handselNumber" : handselNumber
	};
	
    //保存阳光渠道管理数据
	$.ajax({
		url : "sunshineProfit/manage/updateSunshineProfit.jhtml",
		type : "post",
		dataType : "json",
		data : data,
		success : function(result) {
			if (result.success) {
				showSmReslutWindow(result.success, result.msg);
				setTimeout(function() {
					pageInit();
				}, 1000);
			}
		}
	});
 
	//隐藏模态框
	$('#sunshineChannelModal').modal('hide');
});

/* ********************仓库储存收益管理*********** */
var cid = 0;
var hiddens;

function storeProfitView() {
	// 初始化值清空栏位
	$("#storeProfitModal :input").each(function() {
		$(this).val("");
	});
	$("#storeProfitTB").html("");
	//加载数据
	var url='sunshineProfit/manage/list/viewStoreProfit.jhtml';
	$.ajax({
		type : 'post',
		url : url,
		data :[id],
		dataType : 'json',
		success : storeSuccess,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#storeProfitModal').modal('hide');
		}
	});
}

function storeSuccess(data) {
	if (data.data){//获取到数据
       //循环仓库储存收益信息
		for(var i = 0;i<data.data.length;i++){
			cid ++ ;
			//加载
			var baseNumber = data.data[i].baseNumber;
			var profit = data.data[i].profit;
			var expectProfit = baseNumber * (profit/ 100);
			var map = {
					"baseNumber" : baseNumber,
					"profit" : profit
			};
			var tr = $("<tr id=" + cid + " class='text-center' >").append($("<td>").text(baseNumber)).append($("<td>").text(profit)).append($("<td>").text(expectProfit));
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
					  .html('<a href="javascript:;" onclick="deleteGroup(' + cid + ')">删除</a>')).append($("<input >").attr("type", "hidden").attr("name", "storeProfitInfo").val($.toJSON(map)));
			//添加仓库储存收益信息 $("#areaTable tr:last").after(tr);
			$("#storeProfitTB").append(tr);
		}
	}
	
	// 显示模态框数据
	$('#storeProfitModal').modal();
	
	// 点击关闭遮罩层
	$(".close-shade").on("click", function() {
		$(".shade-box,.shade-content").hide();
	});
}

//保存阳光渠道管理
$("#editStoreProfitSubmit").on("click", function() {
	//获取表格中数据
	getRelationInfo();
	if(hiddens.length==0){
		showWarningWindow("warning", "请添加仓库储存收益信息!", 9999);
		return;
	}
	//转换成数组
	var json = new Array();
	$.each(hiddens, function(i,item){
		json.push($(item).val());
	});
	//转换成json串
	var obj={json:json};
	json=$.toJSON(obj);
	
	var data = {
		"sunshineProfitJson" : json
	};

   //保存数据
	$.ajax({
		url : "sunshineProfit/manage/updateStoreProfit.jhtml",
		type : "post",
		dataType : "json",
		data : data,
		success : function(result) {
			if (result.success) {
				showSmReslutWindow(result.success, result.msg);
				setTimeout(function() {
					pageInit();
				}, 1000);
			}
		}
	});

	//隐藏模态框
	$('#storeProfitModal').modal('hide');
});

function addStoreProfitData(event) {
	// t = event.target;
	var baseNumber = $("input[name='baseNumber']").val();
	if (!baseNumber) {
		showWarningWindow('warning', "请输入仓库存放数量！");
		return false;
	} else {
		var reg = /^\d+$/;
		if (!reg.test(baseNumber)) {
			submitDataError($("input[name='baseNumber']"),
					"请输入整数数值!");
			return false;
		}
	}
	var profit = $("input[name='profit']").val();
	if (!profit) {
		submitDataError($("input[name='profit']"), "请输入收益比例!");
		return false;
	}
	if (profit > 1) {
		submitDataError($("input[name='profit']"), "请输入收益比例(0-1)!");
		return false;
	}
	
    var reg = new RegExp("^[0-9]+(.[0-9]{2})?$", "g");
    if (!reg.test(profit)) {
    	submitDataError($("input[name='profit']"), "请输入一个数字，最多只能有两位小数！");
	    return false;
    }

	var expectProfit = (baseNumber * (profit/ 100)).toFixed(2);
	var map = {
		"baseNumber" : baseNumber,
		"profit":profit
	};
	var tr = $("<tr id=" + cid + " class='text-center' >").append($("<td>").text(baseNumber)).append($("<td>").text(profit)).append($("<td>").text(expectProfit));
	tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">')
			  .html('<a href="javascript:;" onclick="deleteGroup(' + cid + ')">删除</a>')).append($("<input >").attr("type", "hidden").attr("name", "storeProfitInfo").val($.toJSON(map)));
	
	//添加仓库储存收益信息 $("#areaTable tr:last").after(tr);
	$("#storeProfitTB").append(tr);

	cid++;
}

function deleteGroup(pid){
	$("#"+pid).remove();
	cid--;
}

function getRelationInfo(){
	hiddens=$("#storeProfitTB").find("input[type=hidden][name=storeProfitInfo]");
}

