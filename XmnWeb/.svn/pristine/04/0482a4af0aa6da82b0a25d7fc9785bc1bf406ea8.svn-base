	
	$("#cityIds").multipleChosen({
		hideValue : "areaId",
		showValue : "btitle",
		url : "division/getCitys.jhtml?id="+($("#id").length?$("#id").val():''),
		isChosen:true,//是否支持模糊查询
		isCode:false,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		isMultiple : true,//是否支持多选
		width:"100%"
	});
	
	validate("editFrom",{
		rules : {
			divisionName : {
				required : true
			},
			cityIds:{
				required : true
			}
			
		},
		messages:{
		}
	},save);
	
	function save(){
		var data = $('#editFrom').serializeArray();
		data = jsonFromt(data);
		var cityIds= $("#cityIds").val();
		if(cityIds){
			var arr=new Array();
			$.each(cityIds,function(i,item){
				var sp=item.split('_');
				arr.push(sp[0]);
			});
			data.cityIds=arr.toString();
		}else{
			showWarningWindow("warning","请选择城市!",9999);
			return;
		}
		$.ajax({
			type : 'post',
			url : "division/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				divisionList.reload();
				showSmReslutWindow(data.success, data.msg);
			},
			error : function() {
				window.messager.warning(data.msg);
			}
		});
	}
