var picSerUrl = $("#picSerUrl").val();
var bannerList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	bannerList = $('#bannerList').page({
		url : 'user_terminal/banner/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm'
	});
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/banner/init.jhtml" target="right">导航图管理</a>','allbillSpan',true);
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var picTitle;
	var typeTitle;
	var contentTitle;
	var sortTiltle;
	if(data.bannerStyle == 0){
		picTitle = 180;
		typeTitle = 180;
		contentTitle = 180;
		sortTiltle = null;
	}else if(data.bannerStyle == 1){
		sortTiltle = 180;
		picTitle = 180;
		typeTitle = 180;
		contentTitle = 180;
	}
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条导航图记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
			checkable : $("#checkbox").val(),
	    	identifier : "id",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			// 数据
			data:data.content, 
			 // 数据行
			cols:[{
					title : "导航图编号",// 标题
					name : "id",
					// sort : "up",
					width : 100,// 宽度
					leng : 3,// 显示长度
					type:"number",// 数据类型
			},{
				title : "排序",// 标题
				name : "sort",
				// sort : "up",
				width : 100,// 宽度
				leng : 3,// 显示长度
				type:"number",// 数据类型
		},{
				title : "导航图位置信息",// 标题
				name : "positionStr",
				width : 150,// 宽度
				type:"string"// 数据类型
				
		},{
			title : "展示风格",// 标题
			name : "bannerStyleStr",
			// sort : "up",
			width : 110,// 宽度
			type:"string"// 数据类型
			
		},{
			title : "是否重点",// 标题
			name : "isEmphasis",
			// sort : "up",
			width : 110,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data){
				return value==1?"是":"否";
			}
		},
	/*
	 * { title : "导航图片1",// 标题 name : "picurl1", //sort : "up", width : 120,//
	 * 宽度 leng : 8,//显示长度 type:"string",//数据类型 customMethod : function(value,
	 * data) { if(data.picurl1!=null){ return '<img
	 * style="width:50px;height:50px;" src='+picSerUrl+data.picurl1+'>'; }else{
	 * return "-"; } } },{ title : "导航图1内容类型",// 标题 name : "typeStr1", //sort :
	 * "up", width : 120,// 宽度 leng : 8,//显示长度 type:"string"//数据类型 },{ title :
	 * "导航图1内容",// 标题 name : "content1", //sort : "up", width : 120,// 宽度 leng :
	 * 8,//显示长度 type:"string"//数据类型 },{ title : "导航图1排序",// 标题 name : "sort1",
	 * //sort : "up", width : 120,// 宽度 leng : 8,//显示长度 type:"string"//数据类型 },{
	 * title : "导航图片2",// 标题 name : "picurl2", //sort : "up", width : 120,// 宽度
	 * leng : 8,//显示长度 type:"string",//数据类型 customMethod : function(value, data) {
	 * if(data.picurl1!=null && data.bannerStyle == 1){ return '<img
	 * style="width:50px;height:50px;" src='+picSerUrl+data.picurl2+'>'; }else{
	 * return "-"; } } },{ title : "导航图2内容类型",// 标题 name : "typeStr2", //sort :
	 * "up", width : 120,// 宽度 leng : 8,//显示长度 type:"string"//数据类型 },{ title :
	 * "导航图2内容",// 标题 name : "content2", //sort : "up", width : 120,// 宽度 leng :
	 * 8,//显示长度 type:"string"//数据类型 },{ title : "导航图2排序",// 标题 name : "sort2",
	 * //sort : "up", width : 120,// 宽度 leng : 8,//显示长度 type:"string"//数据类型 },
	 */
		{
			title : "广告图片",// 标题
			name : "picurl1",
			// sort : "up",
			width : 150,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data){
				if(data.bannerStyle==1){
					return '<img style="width:50px;height:50px;" src="'+imgRoot+data.picurl1 + '"/><img style="width:50px;height:50px;" src="'+imgRoot+data.picurl2 + '"/>';
				}else{
					return '<img style="width:50px;height:50px;" src="'+imgRoot+value + '"/>';
				}
			}
		},{
			title : "上线状态",// 标题
			name : "statusStr",
			width : 150,// 宽度
			type:"string"// 数据类型
		},{
			title : "全国",// 标题
			name : "isAll",
			width : 200,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data){
				var isAll = data.isAll;
				var result ="-";
				if(0 == isAll){
					result = "否";
				}else if(1 == isAll){
					result = "是";
				}
				return result;
			}
		},{
			title : "省市",// 标题
			name : "province",
			width : 200,// 宽度
			type:"string",// 数据类型
			customMethod : function(value, data) {
				var province = data.provinceStr;
				var city = data.cityStr;
				var result = "-";
				if(province != null){
					result = province;
				}
				if(city != null){
					result +="-"+city;
				}
				return result;
			}
		},{
			title : "导航图描述",// 标题
			name : "remarks",
			width : 200,// 宽度
			type:"string"// 数据类型
			
		},{
			title : "开始时间",// 标题
			name : "beginTimeStr",
			// sort : "up",
			width : 200,// 宽度
			type:"stirng"// 数据类型
			
		},{
			title : "结束时间",// 标题
			name : "endTimeStr",
			// sort : "up",
			width : 200,// 宽度
			type:"stirng"// 数据类型
			
		},{
			title : "创建时间",// 标题
			name : "createTimeStr",
			// sort : "up",
			width : 200,// 宽度
			type:"stirng"// 数据类型
			
		},{
			title : "更新时间",// 标题
			name : "updateTimeStr",
			// sort : "up",
			width : 200,// 宽度
			type:"string"// 数据类型
			
		}],
			// 操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["update","check"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
							title : "修改",// 标题
							linkInfoName : "href",
							linkInfo : {
							href : "user_terminal/banner/update/init.jhtml",// url
							param : ["id"],// 参数
							permission : "update"// 列权限
						    }
				       },
						 {
							title : "查看",// 标题
							linkInfoName : "modal",
							linkInfo : {
								modal : {
									url : "user_terminal/banner/check.jhtml",// url
									position:"60px",// 模态框显示位置
									width:"800px"
								},
								param : ["id"],// 参数
								permission : "check"// 列权限
						    }
					      }
				      ]
	}},permissions);
}
function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

/**
 * 日期插件
 */
$(function() {
	var today = new Date();
	var todaystr = addDate(today, 0);
	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var val = d.getFullYear() + "-" + month + "-" + day;
		return val;
	}
});

/**
 * 批量删除
 */
$('#delete').click(function(){
	var ids = bannerList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/banner/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					bannerList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
});

	 
	