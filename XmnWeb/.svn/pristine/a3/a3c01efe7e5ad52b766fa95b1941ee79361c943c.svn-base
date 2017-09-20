var formId = "editForm";
var ISTYPE;
var jsonTextInit;
var vstarImgList;
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(function() {
	ISTYPE = $("#isType").val();
	if(ISTYPE == "add"){
		inserTitle(' > <span>添加图片素材</span>','add',false);
		$("#addTips").css('display','block');
	}else{
		inserTitle(' > <span>编辑图片素材</span>','edit',false);
		$("#toAddBtn").css('display','inline-block');
		$("#imgsDiv").css('display','block');
		pageInit();
	}
	
	vstarDictIdInit();
	
	// 封面图片
	$("#coverImgDiv").uploadImg({
		urlId : "coverImg",
		showImg : $('#coverImg').val()
	});
	
	//分享图标
	$("#shareImgDiv").uploadImg({
		urlId : "shareImg",
		showImg : $('#shareImg').val()
	});
	
	//表单初始化数据
	jsonTextInit();
	
	//表单校验
	validate(formId, {
		rules : {
			title : {
				required : true,
				maxlength: 200
			},
			viceTitle : {
				required : true,
				maxlength: 200
			},
			shareTitle:{
				required:true,
				maxlength: 200
			},
			shareDescription:{
				required:true,
				maxlength: 200
			},
			activityUrl:{
				required:true,
				maxlength: 200
			},
			sortVal:{
				required:true,
				digits:true,
				range:[0,999]   
			}
		},
		messages:{
			title:{
				required:"请输入教学标题",
				maxlength:"最多填写200个字符"
			},
			viceTitle : {
				required:"请输入副标题",
				maxlength:"最多填写200个字符"
			},
			shareTitle:{
				required:"请输入分享标题",
				maxlength:"最多填写200个字符"
			},
			shareDescription:{
				required:"请输入分享描述",
				maxlength:"最多填写200个字符"
			},
			activityUrl:{
				required:"请输入活动链接",
				maxlength:"最多填写200个字符"
			}
		}
	}, save);
	
});

/**
 * 保存物料规格信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = ISTYPE == "add" ? true:false;
	if (isAdd) {// 添加操作
		url = "VstarContent/manage/add" + suffix;
	} else {// 修改操作
		url = "VstarContent/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	
	var valiImg = valiImgData("#"+formId,jsonFromt($("#" + formId).serializeArray()));
	if(valiImg){
		return false;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
				//提交后禁用提交按钮，防止重复提交表单
				$('#submitBtn').attr("disabled","disabled");
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/VstarContent/manage/init.jhtml';
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
		showWarningWindow("warning", "没做任何修改！");
	}
}


/**
 * 表单数据初始化
 */
function jsonTextInit(){
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
}

/**
 * 初始化教学分类
 */
function vstarDictIdInit(){
	$("#vstarDictId").chosenObject({
		hideValue : "id",
		showValue : "name",
		url : "VstarContent/manage/vstarDictIdInit.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}


/**
 * 批量添加图片
 * id:t_vstar_content 主键
 * imgUrls:图片路径,多个以";"分隔
 */
function addImageBatch(id,imgUrls){
	if(imgUrls!=null && imgUrls!=""){
		$.ajax({
			type : 'post',
			url : "VstarContent/manage/add/addImageBatch.jhtml",
			data : {"id":id,"imgUrls":imgUrls},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
				//提交后禁用提交按钮，防止重复提交表单
//				$('#submitBtn').attr("disabled","disabled");
			},
			success : function(data) {
				if (data.success) {
					//
					pageInit();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	}
}

function pageInit(){
	vstarImgList = $('#vstarImgList').page({
		url : 'VstarContent/manage/init/vstarImglist.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize : 8,//每页条数
		paramForm : 'editForm',
		param:{"limit":8}
	});
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	debugger;
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num" data-id="'+data.content[i].id+'">编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			html.push('<div style="background: url('+imgRoot+data.content[i].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"><img src="'+contextPath+'/images/vstar/check_icon.png" class="hide"/></div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	
	obj.find('div').eq(0).html(html.join(''));
}

//选择图片
$("#vstarImgList").on('click','.img-box',function(){
	if($(this).children().hasClass('hide')){
		$(this).children().removeClass('hide');
	}else{
		$(this).children().addClass('hide');
	}
});

//全选
$("#checkAll").click(function(){
	var url=$(this).parent().next().find(".img-box").children();
	if(url.hasClass("hide")){
		url.removeClass("hide");
	}else{
		url.addClass("hide");
	}
});

function getCheckIds(){
	var ids=[];
	$(".list-box>div").each(function(){
		var _this=$(this).find('p');
		if(!_this.next().children().hasClass("hide")){
			var id=_this.attr("data-id");
			ids.push(id);
		}
	});
	
	return ids.join(',');
}

/**
 * 绑定删除事件
 */
$('#delete').click(function(){
	var ids=getCheckIds();
	if(!ids){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	deleteImg(ids);
});

/**
 * 删除图片素材-图片
 * @param ids
 */
function deleteImg(ids){
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'VstarContent/manage/update/deleteImgs.jhtml' + '?t=' + Math.random(),
			data : 'ids=' + ids,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					vstarImgList.reset();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}
