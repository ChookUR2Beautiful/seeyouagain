var pageDiv;
$(document).ready(function() {
	//加载title
	inserTitle(' > 专题关联内容信息','addSpecialTopicBto',false); 
	pageDiv = $('#specialTopicRelationDiv').page({
		url : 'businessman/specialTopic/viewRelation/init.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'specialTopicRelationFromId'
	});
});



function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp; #{title} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个#{subtitle} &nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#specialTopicRelationFromId").serialize());
//	debugger;
	updateAddBtnHref("#addSellerPackageBto", callbackParam);
	var topicType = $('#topicType').val(); //1.商户 2.连锁店 3.区域代理 4.主播 5.预告 6.粉丝券 7.套餐 8.商品
	switch (parseInt(topicType)) { //必须转成int型
	case 1:
		captionInfo = captionInfo.replace("#{title}", "关联商家").replace("#{subtitle}", "商家"); 
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,
			cols : [ {
				title : "商家编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "商家名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "商家地址",
				name : "relationAddress",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 2:
		// 关联连锁店
		captionInfo = captionInfo.replace("#{title}", "关联连锁店").replace("#{subtitle}", "连锁店"); 
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,
			cols : [ {
				title : "区域代理编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "商家名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "商家地址",
				name : "relationAddress",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 3:
		//关联区域代理
		captionInfo = captionInfo.replace("#{title}", "关联区域代理").replace("#{subtitle}", "区域代理");
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,

			cols : [ {
				title : "连锁店编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "商家名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "连锁门店数量",
				name : "relationNum",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;	
	case 4:
		//关联主播
		captionInfo = captionInfo.replace("#{title}", "关联主播").replace("#{subtitle}", "主播");
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,

			cols : [ {
				title : "主播编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "主播名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "类型",
				name : "relationName",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 5:
		//关联预告 
		captionInfo = captionInfo.replace("#{title}", "关联预告").replace("#{subtitle}", "预告");
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,

			cols : [ {
				title : "通告编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "通告标题",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "关联商户",
				name : "relationName",
				type : "string",
				width : 150
			}, {
				title : "关联主播",
				name : "relationAddress",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 6:
		//关联粉丝劵
		captionInfo = captionInfo.replace("#{title}", "关联粉丝劵").replace("#{subtitle}", "粉丝劵"); 
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,

			cols : [ {
				title : "粉丝劵编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "粉丝劵名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "商家地址",
				name : "relationName",
				type : "string",
				width : 150
			}, {
				title : "关联通告",
				name : "relationStore",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 7:
		//关联套餐
		captionInfo = captionInfo.replace("#{title}", "关联套餐").replace("#{subtitle}", "套餐");
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,
			cols : [ {
				title : "套餐编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "套餐名称",
				name : "subName",
				type : "string",
				width : 150
			}, {
				title : "关联商户",
				name : "relationName",
				type : "string",
				width : 150
			}

			]
		}, permissions);
		break;
	case 8:
		//关联商品
		captionInfo = captionInfo.replace("#{title}", "关联商品").replace("#{subtitle}", "商品");
		obj.find('div').eq(0).scrollTablel({
			identifier : "id",
			callbackParam : callbackParam,
			data : data.content,
			caption : captionInfo,
			checkable : false,

			cols : [ {
				title : "商品编号",
				name : "subId",
				type : "string",
				width : 150
			}, {
				title : "商品名称",
				name : "subName",
				type : "string",
				width : 150
			}

			]
		}, permissions);	
		break;
	default:
		result = '-';
		break;
	};
	
}
