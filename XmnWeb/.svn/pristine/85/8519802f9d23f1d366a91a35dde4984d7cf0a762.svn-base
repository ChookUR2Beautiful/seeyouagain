var picSerUrl = $("#picSerUrl").val();
var userCVList;
$(document).ready(function() {
	userCVList = $('#userCVList').page({
		url : 'usercv/manage/init/list.jhtml',
		success : success,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm'
	});
	
	inserTitle(' > 招聘频道 > <a href="usercv/manage/init.jhtml" target="right">用户简历管理</a>','userCVList',true);
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
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条用户简历记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	obj.find('div').eq(0).scrollTablel({
			checkable : $("#checkbox").val(),
	    	identifier : "id",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "创建时间",// 标题
					name : "sdateStr",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"string",//数据类型
			},{
				title : "头像",// 标题
				name : "headpic",
				//sort : "up",
				width : 120,// 宽度
				leng : 8,//显示长度
				type:"string",//数据类型
				customMethod : function(value, data) {
					if(data.headpic !=null){
						return '<img style="width:50px;height:50px;" src='+picSerUrl+data.headpic+'>';
					}else{
					 return "-";
					}
				}
				
		},{
			title : "姓名",// 标题
			name : "name",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},{
			title : "性别",// 标题
			name : "sexStr",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "年龄",// 标题
			name : "age",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "联系电话",// 标题
			name : "phoneid",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "最高学历",// 标题
			name : "degrees",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "工作经验",
			name : "experie",
			type : "string",
			width : 120
		},{
			title : "薪资要求",// 标题
			name : "salary",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "工作城市",// 标题
			name : "workCity",
			type : "string",
			width : 120,
			
		},{
			title : "我要找",// 标题
			name : "iwant",
			type : "string",
			width : 160,
			customMethod : function(value, data) {
				var data = data.iwant
				if(data == ''){
					return '-';
				}
				var dataList = data.split(';');
				var listHtml = '';
				var firstChild = '';
				for(var i = 0 ; i < dataList.length - 1 ; i++){
					listHtml += '<li>'+dataList[i]+'</li>';
					if(i == 0){
						firstChild = dataList[i];
					}
				}
				var _html = '<a href="javascript:;" class="alink" onclick="slidedown(this);"><span>'+firstChild+'</span><i class="allow-right"><i></a>';
				var listBox = '<div class="data-box"><ul class="datalist">'+listHtml+'</ul></div>';
				return _html+listBox;
			}
		},{
			title : "我做过",// 标题
			name : "doing",
			type : "string",
			width : 160,
			customMethod : function(value, data) {
				var data = data.doing
				if(data == ''){
					return '-';
				}
				var dataList = data.split(';');
				var listHtml = '';
				var firstChild = '';
				for(var i = 0 ; i < dataList.length - 1 ; i++){
					listHtml += '<li>'+dataList[i]+'</li>';
					if(i == 0){
						firstChild = dataList[i];
					}
				}
				var _html = '<a href="javascript:;" class="alink" onclick="slidedown(this);"><span>'+firstChild+'</span><i class="allow-right"><i></a>';
				var listBox = '<div class="data-box"><ul class="datalist">'+listHtml+'</ul></div>';
				return _html+listBox;
			}
		},{
			title : "培训经验",// 标题
			name : "experience",
			type : "string",
			width : 160,
			customMethod : function(value, data) {
				var data = data.experience
				if(data == ''){
					return '-';
				}
				var dataList = data.split(';');
				var listHtml = '';
				var firstChild = '';
				for(var i = 0 ; i < dataList.length - 1 ; i++){
					listHtml += '<li>'+dataList[i]+'</li>';
					if(i == 0){
						firstChild = dataList[i];
					}
				}
				var _html = '<a href="javascript:;" class="alink" onclick="slidedown(this);"><span>'+firstChild+'</span><i class="allow-right"><i></a>';
				var listBox = '<div class="data-box"><ul class="datalist">'+listHtml+'</ul></div>';
				return _html+listBox;
			}
		},{
			title : "自我评价",// 标题
			name : "selfAssessment",
			type : "string",
			width : 160,
			customMethod : function(value, data) {
				var data = data.selfAssessment
				if(data == ''){
					return '-';
				}
				var dataList = data.split(';');
				var listHtml = '';
				var firstChild = '';
				for(var i = 0 ; i < dataList.length - 1 ; i++){
					listHtml += '<li>'+dataList[i]+'</li>';
					if(i == 0){
						firstChild = dataList[i];
					}
				}
				var _html = '<a href="javascript:;" class="alink" onclick="slidedown(this);"><span>'+firstChild+'</span><i class="allow-right"><i></a>';
				var listBox = '<div class="data-box"><ul class="datalist">'+listHtml+'</ul></div>';
				return _html+listBox;
			}
		}]},permissions);
}
function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

function slidedown(_this){
	if($(_this).parent().find('.data-box .datalist').css('display') == 'block'){
		$(_this).parent().find('.data-box .datalist').slideUp();	
	}else{
		$(_this).parent().find('.data-box .datalist').slideDown();
	}
	
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
	
$("#export").click(function(){
		var url = "usercv/manage/export.jhtml";
		$form = $("#searchForm").attr("action",url);
		$form[0].submit();
	});

/**
 * 批量删除
 */
$('#delete').click(function(){
	var ids = userCVList.getIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'usercv/manage/delete.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					userCVList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
});
