$(function(){
	var chooseUrl;
	$("#picUrlImg").uploadImg({
		urlId : "logoUrl",
		showImg : $('#logoUrl').val()
	});
	
	$("#activityLd").cityLd({
		isChosen : false 
	});
	
	$("#tagIds").multipleChosen({
		hideValue : "id",
		showValue : "tagName",
		url : "businessman/classify/liveRecordTagInit.jhtml?classifyType=1",
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
	
	validate("editFrom",{
		rules : {
			topicName : {
				required : true,
				rangelength:[1,5]
			},
			tagIds : {
				required : true
			},
			sort : {
				required : true
			}
			
		},
		messages:{
			topicName : {
				rangelength:"请输入5字以内"
			}
		}
	},save);
	
	function save(){
		if(!$("#logoUrl").val()){
			showWarningWindow("warning","请上传等级图片!",9999);
			return;
		}
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		data.provinceId=data.province;
		data.cityId=data.city;
		var tagIds= $("#tagIds").val();
		if(tagIds.length){
			var arr=new Array();
			$.each(tagIds,function(i,item){
				var sp=item.split('_');
				arr.push(sp[0]);
			});
			data.tagIds=arr.toString();
		}
		$.ajax({
			type : 'post',
			url : "businessman/recommend/addTopic.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				loadTopic();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
});