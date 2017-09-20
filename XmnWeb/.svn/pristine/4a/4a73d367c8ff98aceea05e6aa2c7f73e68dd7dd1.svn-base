var imgRoot = $("#fastfdsHttp").val();
var videos = new Array();
var addUrl = "vstarLiverContent/range/edit.jhtml";
var fileArr = new Array();
var contentKey = true;
var anchorImageChooser=new $.zui.ModalTrigger({
		title:'主播相册',
	type : 'ajax',
	width:'800px',
	position:'10px',//距顶部的偏移
	url : 'anchorBusiness/manage/anchorImage/anchorImageChooser.jhtml',
	toggle : 'modal'
});
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


//初始化日期
$('#beginTime').datetimepicker({
  format:'yyyy-mm-dd hh:ii',
  startDate: new Date(),
  language:  'zh-CN',
  //weekStart: 1,
  //todayBtn:  1,
  autoclose: 1,
  todayHighlight: 1,
  startView: 2,
  //minView:2,
  forceParse: 0,
  //showMeridian: 1
}).on("changeDate",function(ev){
  var transferdate=transferDate($("#beginTime").val());//转时间日期
  $('#endTime').datetimepicker('remove');
  $('#endTime').datetimepicker({
      format:'yyyy-mm-dd hh:ii',
      language:  'zh-CN',
      //minView:2,
      autoclose: 1,
      'startDate':transferdate
  }).on("changeDate",function(ev){
      var enddate=$("#endTime").val();
      setEndTime(enddate);
  });
});
$('#endTime').datetimepicker({
  format:'yyyy-mm-dd hh:ii',
  startDate: new Date(),
  language:  'zh-CN',
  minView:2,
  autoclose: 1
}).on("changeDate",function(ev){
  var enddate=$("#endTime").val();
  setEndTime(enddate);
});
function setEndTime(enddate){
  $('#beginTime').datetimepicker('remove');
      $('#beginTime').datetimepicker({
          format:'yyyy-mm-dd hh:ii',
          language:  'zh-CN',
          autoclose: 1,
          todayHighlight: 1,
          startView: 2,
          forceParse: 0,
          'endDate':transferDate(enddate)
  });
}
//将时间字符串转为date
function transferDate(data){
  var start_time=data;
  var newTime= start_time.replace(/-/g,"-");
  var transferdate = new Date(newTime);
  return transferdate;
}
function transferTime(str){
  var newstr=str.replace(/-/g,'-');
  var newdate=new Date(newstr);
  var time=newdate.getTime();
  return time;
}


$("#zhiboCoverDiv").on("click",function(){
	debugger
	if($(this).next().find("li").length>=5){
		showWarningWindow("warning","最多添加5张图片!",9999);
		return false;
	}
	$("#datas .active").removeClass("active");
	$(this).next().addClass("active");
	var anchorId=$("#anchorId").val();
	if(anchorId==undefined || anchorId==""){
		showWarningWindow("warning","请选择主播!",9999);
	}else{
		debugger
		anchorImageChooser.options.url='anchorBusiness/manage/anchorImage/anchorImageChooser.jhtml?id=' + anchorId;
		anchorImageChooser.show();
	}
});


$("#ensure").click(function(){
	debugger;
	var ids =recordList.getIds();
	if(!ids){
		showWarningWindow("warning","请选择一条记录",9999);
		return ;
	}else{
		var idArray=ids.split(',');
		if(idArray.length>5){
			showWarningWindow("warning","最多可以选择5条记录",9999);
			return ;
		}
	}
	
	loadImg(ids);
	anchorImageChooser.close();
});

function loadImg(ids){
	$.post("anchorBusiness/manage/anchorImage/getByIds.jhtml",{"ids":ids},function(data,status){
		if(status=='success'){
			$.each(data.data,function(i,item){
				$("#datas .active").append("<li><img src='"+item.imgRoot+obj+"' /><em class='icon-remove delete-img'></em></li>");
			})
		}
		
	});
	
}

var  options={
		deleteActionOnDone:function(file, doRemoveFile){
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
	        if(responseObject.response.indexOf('ok')) {
	        	var result=JSON.parse(responseObject.response);
	        	file.contentId=result.id;
	        	fileArr.push(result.id);
	        }else{
	        	 return '上传失败。服务器返回了一个错误：' + responseObject.response;
	        }
	    }
		
	}



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
						
						if(item.payType==0){
							content+="<td>免费</td>";
						}else{
							if(item.payType==1){
								content+="<td>"+item.amount+"鸟币</td>";
							}else if(item.payType==2){
								content+="<td>"+item.amount+"余额</td>";
							}
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
		foreshowUrl : {
			required : true
		},
		sortVal : {
			required : true,
			range:[0,999]
		}
	},
	messages : {
	}
}, save);

function returnfun(){
	$('#prompt').hide();
	if($("#is_pay:checked").length){
		if(!$("[name='amount']").val()){
			showWarningWindow("warning", "请输入金额!", 9999);
			return;
		}
		if(!$("[name='zbalance']").val()){
			showWarningWindow("warning", "请输鸟币入金额!", 9999);
			return;
		}
	}
	if (!$("#coverImg").val()) {
		showWarningWindow("warning", "请选择封面图片!", 9999);
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
	var imgs=$("#datas").find("ul").find("img");
	if(!imgs.length){
		showWarningWindow("warning", "请选择主播相册!", 9999);
		return;
	}
	var srcArr=new Array();
	$.each(imgs,function(i,item){
		srcArr.push($(item).attr("value"));
	});
	data.anchorImage=srcArr.toString();
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

function save() {
	$('#prompt').show()
	 window.setTimeout(returnfun,300)
	 return;
}

//删除标签
$("#datas").on("click","em.delete-img",function(){
	var tagId=$(this).parent().attr("name");
	var spans=$(this).parent().parent().next().find("span");
	for(var i=0;i<spans.length;i++){
		if(spans.eq(i).text()==tagId){
			spans.eq(i).remove();
		}
	}
	$(this).parent().remove();
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
