var recruitStationList;
$(document).ready(function() {
		
	inserTitle(' > 招聘频道 > <span><a href="jobmanage/recruitStation/init.jhtml" target="right">岗位管理</a>','recruitStationList',true);

	$("#export").click(function(){
		$form = $("#recruitStationFormId").attr("action","jobmanage/recruitStation/export.jhtml");
		$form[0].submit();
	});
	
	recruitStationList = $('#recruitStationList').page({
		url : 'jobmanage/recruitStation/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'recruitStationFormId'
	});
	
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/jobmanage/recruitStation/init.jhtml';
			location.href =url;
		}
	});
	
	// 省市
	var ld = $("#searld").cityLd({
		isChosen : false
	});
	
	// 清空选择的城市
	$("input[data-bus=reset]").click(function(){
		$("#searld").find("select").trigger("chosen:updated");
	});	
	
});

/**
 * 根据状态（status）条件搜索
 * @param data
 */
function queryStatus(object,status){
	var buttons = $("button.status");
	for(i=0;i<buttons.length;i++){
		$(buttons[i]).removeClass("btn-success").addClass("btn-default");
	}
	$(object).removeClass("btn-default").addClass("btn-success");
	$("input[name='status']").val(status);
	recruitStationList.reload();
}

/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}

/**
 * 构建表格
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;岗位信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】个岗位&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#recruitStationFormId").serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "recruitStationId",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		cols:[{
			title : "岗位名称",
			name : "stationName",
			type : "string",
			width : 200
		},{
			title : "发布时间",
			name : "updateDate",
			type : "date",
			width : 200
		},{
			title : "商家ID",
			name : "sellerId",
			type : "string",
			width : 100
		},{
			title : "店铺名称",
			name : "sellerName",
			type : "string",
			width : 200
		},{
			title : "联系人",
			name : "contact",
			type : "string",
			width : 120,
		},{
			title : "联系电话",
			name : "phone",
			type : "string",
			width : 150
		},{
			title : "招聘人数",
			name : "strNums",
			type : "int",
			width : 100
		},{
			title : "薪资要求",
			name : "salary",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(data.salary==0){
					return "面议";
				} 
				if(data.salary==1){
					return "3000以下";
				} 
				if(data.salary==2){
					return "3000-5000";
				}
				if(data.salary==3){
					return "5000-8000";
				}
				if(data.salary==4){
					return "8000-10000";
				}
				if(data.salary==5){
					return "10000以上";
				}
				return "-";
			}
		},{
			title : "年龄",
			name : "age",
			type : "string",
			width : 100,
		},{
			title : "工作城市",
			name : "cityName",
			type : "string",
			width : 100,
		},{
			title : "工作经验",
			name : "experie",
			type : "string",
			width : 120,
			customMethod : function(value, data) {
				if(data.experie==0){
					return "不限";
				}
				if(data.experie==1){
					return "1年以下";
				} 
				if(data.experie==2){
					return "1-3年";
				} 
				if(data.experie==3){
					return "3-5年";
				}
				if(data.experie==4){
					return "5-10年";
				}
				if(data.experie==5){
					return "10年以上";
				}
				return "-";
			}
		},{
			title : "学历",
			name : "degrees",
			type : "string",
			width : 100,
			customMethod : function(value, data) {
				if(data.degrees==0){
					return "小学";
				}
				if(data.degrees==1){
					return "初中";
				} 
				if(data.degrees==2){
					return "高中";
				} 
				if(data.degrees==3){
					return "大专";
				}
				if(data.degrees==4){
					return "本科以上";
				}
				return "-";
			}
		},{
			title : "岗位要求",
			name : "stationRequire",
			type : "string",
			width : 200,
			customMethod : function(value, data) {
				var data = data.stationRequire;
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
		}],
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["update","delete"],
			width : 130,// 宽度
			// 当前列的中元素
			cols : [{
						title : "删除",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "jobmanage/recruitStation/delete.jhtml",// url
							param : ["recruitStationId"],// 参数
							permission : "delete"// 列权限
						},
						customMethod : function(value, data){
	                        if((data.status==0)||(data.status==2)){
	                            var value1 = "<a href='javascript:confirmDelete(\""+data.recruitStationId+"\")'>" + "删除" + "</a>";
	                            return value1;
	                        }else{
	                        	var value2 = '<a href="javascript:;" disabled="disabled" style="color:#CDCDCD;"></a>';
								return value2;
	                        }
	                    }
					},{
							title : "编辑",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "jobmanage/recruitStation/update/init.jhtml",// url
								param : ["recruitStationId"],// 参数
								permission : "update"// 列权限
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

function slidedown(_this){
	if($(_this).parent().find('.data-box .datalist').css('display') == 'block'){
		$(_this).parent().find('.data-box .datalist').slideUp();	
	}else{
		$(_this).parent().find('.data-box .datalist').slideDown();
	}
}

/**
 * 删除操作
 */
 function confirmDelete(recruitStationId){
	 showSmConfirmWindow(function (){
		 $.ajax({
	         url : "jobmanage/recruitStation/delete.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'recruitStationId=' + recruitStationId,
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				recruitStationList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	 },"确定要删除吗？");
	 
 }
 
//清空选择的学历
$("input[data-bus=reset]").click(function(){
	if($("#degId").find("option") != ""){
		$("#degId").find("option").Uploader;
	}
});	
