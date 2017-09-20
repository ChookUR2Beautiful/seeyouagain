var allStartImages;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	allStartImages = $('#allStartImages').page({
		url : 'startImageManagerment/startImageSet/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:30,
		paramForm : 'searchStartImageForm'
	});
	
	inserTitle(' > 启动图管理 > <a href="startImageManagerment/startImageSet/init.jhtml" target="right">启动图列表</a>','allStartImageSpan',true);
	
	$("input[data-bus=reset]").click(function(){

		$("#ld").find("select").trigger("chosen:updated");

	});
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条启动图&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchStartImageForm").serialize());
	updateAddBtnHref("#addStartImageSetBto", callbackParam);
	obj.find('div').eq(0).scrollTablel({
		//tableClass :"table-bordered table-striped info",
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
			title : "客户端类型",// 标题
			name : "type",
			//sort : "up",
			width : 100,// 宽度
			//leng : 3,//显示长度
			type:"stirng",//数据类型		
			customMethod : function(value, data) {
				var type = "-";
				var typeTemp = data.type;
				if(1 == typeTemp){
					type = "商户版";
				}else if(2 == typeTemp){
					type = "寻蜜鸟用户版";
				}else{
					type = "合作商版";
				}
				return type;
			}
		},{
			title : "位置",// 标题
			name : "showLocal",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var status = "-";// 位置: 1-启动页, 2-直播间
				var showLocal = data.showLocal;
				if(1 == showLocal){
					status = "启动页";
				}else{
					status = "直播间";
				}
				return status;
			}		
		},{
			title : "状态",// 标题
			name : "status",
			//sort : "up",
			width : 100,// 宽度
			type:"stirng",//数据类型
			customMethod : function(value, data) {
				var status = "-";
				var astatus = data.status;
				if(1 == astatus){
					status = "停用";
				}else{
					status = "启用";
				}
				return status;
			}
		},{
			title : "类型",// 标题
			name : "showType",
			//sort : "up",
			width : 100,// 宽度
			//leng : 8,//显示长度
			type:"stirng",//数据类型,//数据类型
			customMethod : function(value, data) {
					var status = "-";
					var showType = data.showType;
					// 类型: 1-图片, 2-视频
					if(1 == showType){
						status = "图片";
					}else{
						status = "视频";
					}
					return status;
			}
		},{
			title : "图片",// 标题
			name : "pic",
			//sort : "up",
			width : 200,// 宽度
			//leng : 8,//显示长度
			type:"string",//数据类型
			customMethod : function(value, data) {
				//return imgRoot + data.pic;
				var showType = data.showType;
				if(1 == showType){
					return '<img style="width:50px;height:50px;" src="'+imgRoot+data.pic+'"/>';
				}else{
					var videoUrl = data.videoUrl;
					return  '<a href="'+videoUrl+'" target="right">查看</a>';
				}
			
			}
		},{
			title : "地区",// 标题
			name : "provinceName",
			//sort : "up",
			width : 200,// 宽度
			type:"string",//数据类型		
			customMethod : function(value, data) {
				var provinceName = data.provinceName == undefined ? "" : data.provinceName;
				var cityName = data.cityName == undefined ? "" : data.cityName;
				
				var description = data.provinceName == undefined ? "全国" : provinceName + '-' + cityName;
				return description;
			}
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["check", "update"],// 不需要选择checkbox处理的权限
			width : 100,// 宽度
			// 当前列的中元素
			cols : [{
				title : "查看",// 标题
				customMethod : function(value, data) {
					var astatus = data.status;
					if(1 == astatus){
						return "<a href='javascript:;' onclick='updateStatus(" + data.id + ", 0)'>启用</a>"
					}else{
						return "<a href='javascript:;' onclick='updateStatus(" + data.id + ", 1)'>停用</a>"
					}
				},
				linkInfoName :  "href",  /*"modal"*/
				linkInfo : {
					
					modal : {
						url : "startImageManagerment/startImageSet/check.jhtml",// url
						position:"60px",// 模态框显示位置
						width:"800px"
					},
					param : ["id"],// 参数
					permission : "check"// 列权限
				}
			
			},{
				title : "修改",// 标题
				linkInfoName : "href",  /*"modal"*/
				linkInfo : {
					href : "startImageManagerment/startImageSet/update/init.jhtml",
					param : ["id"],
					permission : "update"
				}
			}] 
		}
	},permissions);
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

function updateStatus(id, status) {
	showSmConfirmWindow(function() {
//		debugger
		$.post("startImageManagerment/startImageSet/updateStatus.jhtml", {
			"id" : id,
			"status": status
		}, function(data, status) {
			if (status == "success") {
				showSmReslutWindow(data.success, data.msg);
				setTimeout(function() {
					allStartImages.reload();
				}, 1000);
			}
			else {
				window.messager.warning(data.msg);
			}
		});
	}, "确定要更改吗？");
	
	
	/*showSmConfirmWindow(function(){
		$.ajax({
			type:"POST",
			url:"startImageManagerment/startImageSet/updateStatus.jhtml",
			data:{id:id, status:status},
			dataType:"json",
			success:function(data){
				if(data.success) {
					setTimeout(function() {
						allStartImages.reload();
					}, 1000);
				}
				showSmReslutWindow(data.success,data.msg);
			},
			error:function(data){
				showSmReslutWindow(data.success, data.msg);
			}
		});
		
	}, "确定修改吗？");*/

}


