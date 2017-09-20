var imgRoot = $("#fastfdsHttp").val();
var videos = new Array();
var addUrl = "vstarLiverContent/main/edit.jhtml";
var fileArr = new Array();
// 清除ckEditor实例
if (CKEDITOR.instances['content']) {
	CKEDITOR.instances['content'].destroy(true);
}
// 初始化富文本编辑器
$("#content").ckeditor({

});


$("#coverImgImg").uploadImg({
	urlId : "coverImg",
	showImg : $('#coverImg').val()
});


$("#anchorId").chosenObject({
	hideValue : "id",
	showValue : "nickname",
	url : "anchor/manage/initAnchorId.jhtml",
	isChosen:true,//是否支持模糊查询
	isCode:true,//是否显示编号
	isHistorical:false,//是否使用历史已加载数据
	width:"80%"
});

$("#is_pay").on("click",function(){
	if($("#is_pay:checked").length){
		$("#pay_model").show();
	}else{
		$("#pay_model").hide();
		$("#pay_model").find("input[type='text']").val('');
		$("#pay_model").find("input[type='number']").val('');
		$("#pay_model").find("input[type='radio']").prop("checked", false);
		$("#pay_model").find("input[type='checkbox']").prop("checked", false);
	}
});

var  options={
	deleteActionOnDone:function(file, doRemoveFile){
		debugger
		var key=false;
		$.ajax({
			url : "vstarLiverContent/fileUpload/deleteId.jhtml",
			type : "post",
			dataType : "json",
			async:false, 
			data : {
				id : file.contentId
			},
			success : function(data) {
				debugger
				if(data.success){
					fileArr.remove(file.contentId);
					key= true;
				}else{
					key= false;
				}
			}
		});
		return key;
	},
	deleteConfirm:'确认删除文件?此操作不需要保存,立即生效!',
	/*filters : {
		// 只允许上传图片或图标（.ico）
		mime_types : [
			{
				title : '文档',
				extensions : 'pdf,ppt,doc,xls'
			},
			{
				title : '文件',
				extensions : 'zip,rar,doc'
			}
		],
		// 不允许上传重复文件
		prevent_duplicates : true,
	},*/
	responseHandler: function(responseObject, file) {
		debugger
        if(responseObject.response.indexOf('ok')) {
        	var result=JSON.parse(responseObject.response);
        	file.contentId=result.id;
        	fileArr.push(result.id);
        }else{
        	 return '上传失败。服务器返回了一个错误：' + responseObject.response;
        }
    }
	
}
//$('#uploaderExample2').uploader(options);



function loadVideo(data) {
	$.ajax({
		url : "vstarLiverContent/video/edit/getvideosByIds.jhtml",
		type : "post",
		dataType : "json",
		data : {
			ids : videos.toString()
		},
		success : function(result) {
			if (result.success) {
				$("#videoList").html("");
				var content = '';
				//加载统计区间表单数据
				$.each(result.data, function(i, item) {
					content += "<tr id='" + item.id + "'>"
						+ "       <td>" + item.title + "</td>"
						+ "       <td>" + item.videoUrl + "</td>"
						+ "       <td>" + item.duration + "</td>"
						debugger
						if(item.zbalance&&item.amount){
							content+="<td>"+item.zbalance+"鸟币/"+item.amount+"元</td>";
						}else{
							content+="<td>免费</td>";
						}
						
						if(item.experienceTime){
							content+="<td>"+item.experienceTime+"分钟</td>";
						}else{
							content+="<td></td>";
						}
						content+= '<td><a  data-type="ajax" data-url="vstarLiverContent/init/video.jhtml?id=' + item.id + '" data-toggle="modal">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="delectvideo(' + item.id + ')" >删除</a></td>';
					+"</tr>" ;
				})
				$("#videoList").html(content);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}

function delectvideo(id) {
	$.ajax({
		url : "vstarLiverContent/video/edit/deleteVideo.jhtml",
		type : "post",
		dataType : "json",
		data : {
			id : id
		},
		success : function(result) {
			if (result.success) {
				$("#" + id).remove();
				videos.remove(id);
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
}


Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

validate("editFrom", {
	rules : {
		title : {
			required : true
		},
		viceTitle : {
			required : true
		},
		sortVal : {
			required : true,
			range:[0,999]
		},
		zbalance: {
			required : true
		},
		amount: {
			required : true
		}
	},
	messages : {
	}
}, save);

function save() {
	if (!$("#coverImg").val()) {
		showWarningWindow("warning", "请选择封面图片!", 9999);
		return;
	}
	if (!videos.length) {
		showWarningWindow("warning", "请添加教学视频!", 9999);
		return;
	}
	if (!fileArr.length) {
			showWarningWindow("warning", "请添加教学附件!", 9999);
			return;
	}
	
	var data = $('#editFrom').serializeArray();
	data = jsonFromt(data);
	if(!data.anchorId){
		showWarningWindow("warning", "请选择主播!", 9999);
		return;
	}
	data.fileIds = fileArr.toString();
	data.videoIds = videos.toString();
	data.contentText = $("#content").val();
	if(!data.contentText){
		showWarningWindow("warning", "请输入教学内容!", 9999);
		return;
	}
	$.ajax({
		type : 'post',
		url : addUrl,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			//$('#prompt').show();
		},
		success : function(data) {
			$('#prompt').hide();
			if (data.success) {
				window.location.href = "vstarLiverContent/init.jhtml";
			} else {
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function() {
			window.messager.warning(data.msg);
		}
	});
	
	
}


