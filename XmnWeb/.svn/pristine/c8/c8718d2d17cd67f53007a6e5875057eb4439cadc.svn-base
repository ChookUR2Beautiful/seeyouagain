/**
 * 
 */
var updateURL = "businessman/brandSeller/update/init.jhtml";
var deleteURL = "javascript:remove("
var brandSellerModel = {
	title : "品牌店列表",
	totalTitle : "品牌店",
	form : "",// 查询表单的id
	checkbox : true,
	backButton : true,
	addBtn : "addbtn",
	handleColumn : {
		title : "操作",
		name : "brandId",
		handlePermissions : [ "del" ],// 需要用到checkbox
		queryPermissions : [ "update", "del" ],// 不需要用到checkbox
		column : [ {
			title : "修改",
			href : updateURL,
			param : [ "brandId" ],
			permissions : "update"
		}, {
			title : "删除",
			href : deleteURL,
			param : [ "brandId" ],
			permissions : "del"
		} ]
	},
	columns : [ {
		title : "品牌店编号",
		name : "brandId",
	}, {
		title : "品牌店名称",
		name : "brandName",
	}, {
		title : "品牌店图片",
		name : "picUrl",
	}, {
		title : "品牌店图片描述",
		name : "bewrite",
	}, {
		title : "返利",
		name : "rebate",
	}, {
		title : "开始时间",
		name : "dateStart",
	}, {
		title : "结束时间",
		name : "dateEnd",
	}, {
		title : "排序",
		name : "sort",
	} ],
	permissions : permissions
}