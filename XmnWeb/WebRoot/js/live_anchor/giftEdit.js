var dataCount = 0;//当前添加抵商品序号
var dataSize =10;//最多可添加商品数量
var formId = "editFrom";
var imgRoot = $("#fastfdsHttp").val();
var loaded=false;//已加载商品信息标志

var jsonTextInit;
$(function() {
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	validate(formId, {
		rules : {
			giftKind:{
				required:true
			},
			giftName : {
				required : true
			},
			giftType:{
				required:true
			},
			giftPrice : {
				required : true
			},
			giftExperience  :{
				required :true,
				digits:true,
				range:[1,99999]
			},
			sortVal  :{
				required :true,
				digits:true,
				range:[1,99999]
			},
			freeStatus:{
				required:true
			},
			isRadio:{
				required:true
			},
			isSeries:{
				required:true
			}
		},
		messages:{
			giftExperience:{
				required:"请输入获得经验",
				digits:"获得经验必须为数字类型",
				range:"获得经验须设定为1-99999之间的整数"
			},
			sortVal:{
				required:"请输入礼物排序值",
				digits:"礼物排序值必须为数字类型",
				range:"礼物排序值须设定为1-99之间的整数"
			}
		}
	}, liveGiftSave);
	
	//礼物类型初始化
	giftKindInit();
	
	//初始化商品下拉框
	initCodeId();
	
	
	// 行业类别
	$("#tradeSelect").tradeLd({
		isChosen : true,
		showConfig : [{name:"category",tipTitle:"全部",width:'50%'},{name:"genre",tipTitle:"全部",width:'50%'}]
	});
	
	// 礼物图片
	$("#giftAvatarImg").uploadImg({
		urlId : "giftAvatar",
		showImg : $('#giftAvatar').val()
	});
});




/**
 * 保存礼物信息
 */
function liveGiftSave() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	if (isAdd) {// 添加操作
		url = "liveGift/manage/add" + suffix;
	} else {// 修改操作
		url = "liveGift/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	var data=jsonFromt($('#' + formId).serializeArray());
	console.log(data);
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					var url = contextPath +'/liveGift/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		$('#prompt').hide();
		$('#triggerModal').modal('hide');
		showSmReslutWindow(false, "没做任何修改！");
	}
}

/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;
	
	var zhiboCover=$("#giftAvatar").val();
	if(zhiboCover == null || zhiboCover==""){
		showWarningWindow("warning","请上传礼物图片!",9999);
		rsult=false;
		return rsult;
	}
	
	var giftKind=$("input[name='giftKind']:checked").val();
	if(giftKind==2){
		var productNum=currentDataSize();
		if(productNum<=0){
			showWarningWindow("warning","请添加商品!",9999);
			rsult=false;
			return rsult;
		}
	}
	
	return result;
}


//初始化商品下拉框
function initCodeId(){
	$("#codeid").chosenObject({
		hideValue : "codeId",
		showValue : "pname",
		url : "fresh/activity/getProduct.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

var productInfo;
//绑定选择商品点击事件
$("body").on("click","#codeid_chosen .chosen-results li",function(){
	var codeId=$("#codeid").val();
	if(codeId){
		$.post("fresh/manage/getSaleGroupList.jhtml",{"codeId":codeId},function(data,status){
			if(status=='success'){
				var group=$("#group");
				group.html('');
				productInfo=data.productInfo;
				$("#pname").val(productInfo.pname);
				$("#salePrice").val(productInfo.integral+productInfo.cash);
				if(data.saleGroupList.length==0){
					var item={
							"pvValues":"通用规格",
							"stock":productInfo.store,
							"amount":0
					};
					$("<option>").text("通用规格").val($.toJSON(item)).appendTo(group);
				}else{
					$.each(data.saleGroupList,function(i,item){
						$("<option>").text(item.pvValues).val($.toJSON(item)).appendTo(group);
					});
				}
			}
		});
	}
});

/**
 * 删除当前行
 */
function deletePro(obj){
	$(obj).parent().parent().remove();  
	dataCount--;
};

/**
 * 绑定添加按钮点击事件
 */
$("body").on("click","#addProBtn",function(){
//	debugger;
	var codeid=$("#codeid").val();//商品编号
	var pname=$("#pname").val();//商品名称
	var salePrice=$("#salePrice").val();//商品售价
	var group=$("#group").val();//商品规格信息
	if(group==null){
		showWarningWindow("warning","请选择商品规格!",9999);
		return ;
	}
	
	if(dataCount==dataSize){
		showWarningWindow("warning","最多添加"+dataSize+"种商品!",9999);
		return ;
	}
	
	var groupVal=$.parseJSON(group);
	
	var pnameDetail=pname;
	if(groupVal.pvValues instanceof Array){
		pnameDetail+="-"+groupVal.pvValues.join(',');
	}
	var birdCoin=parseFloat(salePrice)+parseFloat(groupVal.amount);
	
	var pvIds="";
	if(groupVal.pvIds !=undefined){
		pvIds=groupVal.pvIds;
	}
	
	
	var pvValue="";
	if(groupVal.pvValues instanceof Array){
		pvValue=groupVal.pvValues.join(',');
	}else{
		pvValue="通用规格";
	}
	
	var rowId=codeid+","+pvIds;
	var exist=$("#productTable tbody").find("tr[id='"+rowId+"']").size();
	if(exist){
		showWarningWindow("warning","该规格商品已添加!",9999);
		return ;
	}
	
	var content="";
	content +=  "<tr id='"+rowId+"'>"
			+	    "<td>"+pnameDetail+"</td>"
			+	    "<td>"+birdCoin+"</td>"
			+	    "<td><a href='javascript:;' onclick='deletePro(this)'>删除</a></td>"
			+	    "<input type='hidden' name='giftDetailList["+dataCount+"].codeid' value='"+codeid+"'>"
			+	    "<input type='hidden' name='giftDetailList["+dataCount+"].pvIds'  value='"+pvIds+"'>"
			+	    "<input type='hidden' name='giftDetailList["+dataCount+"].pvValue' value='"+pvValue+"'>"
			+   "</tr>";
	$("#productTable tbody").append(content);
	dataCount++;
});


/**
 * 返回当前商品数量
 */
function currentDataSize(){
	return $("#productTable tbody").find("tr").size();
};

/**
 * 绑定礼物类型单击事件
 */
$("input[name='giftKind']").on('click',function(){
	 giftKindInit();
});


function giftKindInit(){
	var isAdd = $("#" + formId).find("input[name='id']").length == 0 ? true : false;
	var giftKind=$("input[name='giftKind']:checked").val();
	//普通礼物
	if(giftKind==1){
		$(".base-info").css("display","block");
		$("#giftPriceLabel").html("礼物价格：<span style='color:red;'>*</span>");
	}else{
		$(".base-info").css("display","none");
		$("input[name='giftKid']").val("");
		$("input[name='freeStatus'][value='0']").prop("checked","checked");
	}
	
	//商品礼物
	if(giftKind==2){
//		debugger;
		$(".product-info").css("display","block");
		$("#giftPriceLabel").html("显示鸟币：<span style='color:red;'>*</span>");
		if(!isAdd && !loaded){
			 // 设置同步
		    $.ajaxSetup({
		        async: false
		    });
		    
			loadProductInfo();
			
			 // 恢复异步
		    $.ajaxSetup({
		        async: true
		    });
		}
		dataCount=currentDataSize();
	}else{
		$(".product-info").css("display","none");
		$("#freight").val(0);
	}
	
	//套餐礼物
	if(giftKind==3){
		$(".combo-info").css("display","block");
		$("#giftPriceLabel").html("显示鸟币：<span style='color:red;'>*</span>");
	}else{
		$(".combo-info").css("display","none");
		$("select[name='category']").val("");
		$("select[name='genre']").val("");
		$("#tradeSelect").find("select").trigger("chosen:updated");
	}
}

/**
 * 加载商品信息
 */
function loadProductInfo(){
	var url="liveGift/manage/update/loadProductInfo.jhtml";
	var id=$("input[name='id']").val();
	$.ajax({
		type : 'post',
		url : url,
		data : {"id":id},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
//			console.log(data);
			
			$.each(data,function(index,obj){
				var rowId=obj.codeid+","+obj.pvIds;
				var pnameDetail=obj.pname+"+"+obj.pvValue;
				var birdCoin= obj.birdCoin;
				var codeid=obj.codeid;
				var pvIds=obj.pvIds;
				var pvValue=obj.pvValue;
				var content="";
				content +=  "<tr id='"+rowId+"'>"
						+	    "<td>"+pnameDetail+"</td>"
						+	    "<td>"+birdCoin+"</td>"
						+	    "<td><a href='javascript:;' onclick='deletePro(this)'>删除</a></td>"
						+	    "<input type='hidden' name='giftDetailList["+index+"].codeid' value='"+codeid+"'>"
						+	    "<input type='hidden' name='giftDetailList["+index+"].pvIds'  value='"+pvIds+"'>"
						+	    "<input type='hidden' name='giftDetailList["+index+"].pvValue' value='"+pvValue+"'>"
						+   "</tr>";
				$("#productTable tbody").append(content);
			});
			
			loaded=true;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			$('#triggerModal').modal('hide');
		}
	});
}
