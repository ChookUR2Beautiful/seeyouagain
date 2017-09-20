var secondLevelNavigate;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	//一级导航
	secondLevelNavigate = $('#secondLevelNavigate').page({
		url : 'user_terminal/navigate/secondLevel/init/list.jhtml',
		success : secondLevelNavigateSuccess,
		pageBtnNum : 10,
		param:{
			type:"2"
		}
	});
});

function secondLevelNavigateSuccess(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条二级分类导航信息&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
		identifier : "nId",
		tableClass :"table-bordered table-striped info",
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
			title : "导航位置",// 标题
			name : "order",
			width : 100,// 宽度
			leng : 3,//显示长度
			type:"stirng"//数据类型		
		},{
			title : "导航图片(高清)",// 标题
			name : "img_high",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"string",//数据类型
			customMethod : function(value, data) {
				return '<img style="width:50px;height:50px;" src="'+imgRoot+data.img_high+'"/>';
			}
		},{
			title : "导航标题",// 标题
			name : "title",
			width : 150,// 宽度
			type:"stirng"//数据类型
		},{
			title : "跳转分类",// 标题
			name : "category_genre",
			width : 150,// 宽度
			type:"string"//数据类型
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["secondLevelNavigateUpdate", "secondLevelNavigateDetail", "secondLevelNavigateSort"],// 不需要选择checkbox处理的权限
//			width : 50,// 宽度
			// 当前列的中元素
			cols : [{
				title : "下移",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["nId"],
					permission : "secondLevelNavigateSort"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:updateSecondLevelOrder('+data.nId+', '+data.order+', '+1+');">下移</a>';
				}
			},{
				title : "上移",// 标题
				linkInfoName : null,
				width : 50,
				linkInfo : {
					modal : {
						url : null,
						position : null,
						width : null
					},
					param : ["nId"],
					permission : "secondLevelNavigateSort"
				},
				customMethod : function(value, data) {
					return '<a href="javascript:updateSecondLevelOrder('+data.nId+', '+data.order+', '+0+');">上移</a>';
				}
			},{
				title : "修改",// 标题
				linkInfoName : "href",
				width : 10,
				linkInfo : {
					href : "user_terminal/navigate/secondLevel/update/init.jhtml",
					param : ["nId"],
					permission : "secondLevelNavigateUpdate"
				}
			},{
				title : "查看",// 标题
				linkInfoName : "href",
				width : 10,
				linkInfo : {
					href : "user_terminal/navigate/secondLevel/detail.jhtml",
					param : ["nId"],
					permission : "secondLevelNavigateDetail"
				}
			}] 
		}
	},permissions);
}

//修改分类导航位置
function updateSecondLevelOrder(nId, order, flag) {
	showSmConfirmWindow(function(){
		formAjaxSecondLevel(nId, order, flag);
	}, "确定更改分类导航位置？");		
}

function formAjaxSecondLevel(nId, order, flag){
	$.ajax({
		type : 'post',
		url : 'user_terminal/navigate/secondLevel/sort.jhtml' + '?t=' + Math.random(),
		data : {
			'nId':nId,
			'flag':flag,
			'order':order
		},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
			try {
				if (secondLevelNavigate != undefined) {
					secondLevelNavigate.reload();
				}
			} catch (err) {
				console.log(err);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$('#prompt').hide();
			showSmReslutWindow(data.success, data.msg);
		}
	})
}