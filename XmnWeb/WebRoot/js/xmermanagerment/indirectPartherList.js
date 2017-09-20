var indirectPartnerList;
$(document).ready(function() {
	indirectPartnerList = $('#indirectPartnerList').page({
		url : 'xmer/manage/directPartner/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm'
	});
	
	inserTitle('> 寻蜜客管理 > <a href="xmer/manage/init.jhtml" target="right">寻蜜客成员管理</a> > 寻蜜客直接伙伴 >寻蜜客间接伙伴','indirectPartnerList',true);
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
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条寻蜜客成员记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
			checkable : false,
	    	identifier : "",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "寻蜜客编号",// 标题
					name : "uid",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型					
			},{
				title : "寻蜜客姓名",// 标题
				name : "name",
				width : 150,// 宽度
				type:"string"//数据类型
				
		},{
			title : "手机号",// 标题
			name : "phoneid",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},{
			title : "签约日期",// 标题
			name : "sdateStr",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["check"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
							title : "查看",// 标题
							linkInfoName : "modal",
							linkInfo : {
								modal : {
									url : "xmer/manage/check.jhtml",// url
									position:"60px",// 模态框显示位置
									width:"800px"
								},
								param : ["uid"],// 参数
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