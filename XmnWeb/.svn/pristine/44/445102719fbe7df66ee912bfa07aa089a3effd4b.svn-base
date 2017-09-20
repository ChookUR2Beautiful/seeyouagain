$(function() {
	// 表单验证
	recruitStationValidate();
	
	// 省市
	var ld = $("#ld").cityLd({
		isChosen : false
	});
	
	// 初始化验证年龄的方法
	checkAge();
	
});

/**
 * 表单验证
 */
function recruitStationValidate() {
	validate("editRecruitStationForm", {
		rules : {
			stationName : {
				required : true
			},
			nums : {
				required : true,
				digits : true,
				range : [ 0, 2147483647]
			},
			sellerName : {
				required : true
			},
			contact : {
				required : true
			},
			phone : {
				required : true,
				maxlength : 20
			},
			ageMin : {
				required : true,
				digits : true
			},
			ageMax : {
				required : true,
				digits : true,
				ageLegal : true
			},
			province : {
				required : true
			},
			city : {
				required : true
			}
		},
		messages : {
			stationName : {
				required : "招聘岗位不能为空!"
			},
			nums : {
				required : "招聘人数不能为空！",
				digits : "招聘人数只能为整数!",
				range : "取值为1-2147483647!"
			},
			sellerName : {
				required : "商家名称不能为空!"
			},
			contact : {
				required : "联系人不能为空!"
			},
			phone : {
				required : "联系电话不能为空",
				maxlength : 20
			},
			ageMin : {
				required : true,
				digits : "年龄只能为整数！",
			},
			ageMax : {
				required : true,
				digits : "年龄只能为整数！",
			},
			province : {
				required : "工作省份不能为空！"
			},
			city : {
				required : "工作城市不能为空！"
			}
		}
	}, formAjax);
}

/**
 * Ajax 请求
 */
function formAjax() {
	var url = 'jobmanage/recruitStation/update.jhtml' + '?t=' + Math.random();
	var data = jsonFromt($('#editRecruitStationForm').serializeArray());
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			if (data.success) {
				showSmReslutWindow(data.success, data.msg);
				var url = contextPath + "/jobmanage/recruitStation/init.jhtml";
				setTimeout(function(){
        			location.href =url;
        		}, 1000);
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			showSmReslutWindow(false, errorThrown);
		}
	});
}

/**
 * 修改岗位：
 */
//弹出模态框
function showPosition(){
	$('#stationModal').modal('show');
}

function asdStationName(_this){
	$('#stationModal .btn').removeClass('listactive');
	$(_this).addClass('listactive');
}

/**
 * 保存岗位
 */
function confirmBtn(){
	var stationNames = $("#stationModal .btn");
	var activeList = '';
	stationNames.each(function(){
		if($(this).hasClass('listactive')){
			activeList = $(this).val();
		}
	});
	
	if(activeList == ''){
		alert('当前没有选择岗位，请选择。');
	}
	
	$('#stationselect').empty().append('<input class="form-control" value="'+ activeList +'" name="stationName" onfocus="showPosition();" style="width: 540px"/>');
	$('#stationModal').modal('hide');//关闭模态框
}

/**
 * 修改岗位要求：
 * 点击修改时，默认选中原来已经包含的岗位要求
 */
$('#editbtn a').click(function(){
	
	var btnList = $('#stationRequireModal .btn-group button');
	
	$('input[name="stationval"]').each(function(){
		var _this = this;
		btnList.each(function(){
			
			if($(this).html() == $(_this).val()){
				
				$(this).addClass('listactive');
			}
		})
	});
});
//

/**
 * 岗位要求选择
 */
function asdStationRequire(_this){
	// $('#stationRequireModal .btn').removeClass('listactive');
	if($(_this).hasClass('listactive')){
		$(_this).removeClass('listactive');
	}else{
		$(_this).addClass('listactive');
	}	
}

/**
 * 保存岗位要求
 */
function confirmRequireBtn(){
	var activeList = '';
	activeList = $("#stationRequireModal .listactive");
	
	var nativeList = $('#stationRequireContent input');
	
	/**
	 * 获取原来的岗位要求的个数
	 */
	var idNo = 0;
	var idInput = $("#stationRequireContent input[type='hidden']");
	$(idInput).each(function(ind,obj){
		idNo ++;
	});
	
	$(activeList).each(function(index,stationRequire){
		var isActive = 0;// 标记，用来确保不重复添加原来已经包含的岗位要求
		var _this = this;
		nativeList.each(function(){
			if($(this).val() == $(_this).val()){
				isActive = 1;
			}
		});
		var sr = $(stationRequire).text();
		var idv = $(stationRequire).val();
		if(!isActive){
			var _html = '<div style="display:inline-block;vertical-align: middle;">';
			_html += '<input type="text" name="stationval" class="form-control" readonly="readonly" value="';
			_html +=  sr + '" style="width: 100px;text-align: center;background-color: white;border-radius :4px;"/>';
			_html += '<input type="hidden" name="tagIds['+ (idNo++) +']" value="' + idv + '"/></div>';
			$("#editbtn").before(_html);
		}
	});
	
	/*
	 * 遍历模态框中的岗位和原有的岗位要求进行对比，如果没有被选中，则删除页面中显示的
	 */
	$("#stationRequireModal .listbtn").each(function(){
		var _this = this;
		if(!$(_this).hasClass('listactive')){
			
			$('input[name="stationval"]').each(function(){
				if($(this).val() == $(_this).html()){
					$(this).parent().remove();
				}
			});
		}
	});
	$('#stationRequireModal').modal('hide');//关闭模态框
}
//

/**
 * 验证最小年龄不能大于最大年龄
 */ 
function checkAge(){
	$.validator.addMethod("ageLegal", function(value, element) {
		var ageMinVal = $("#aminId").val();
		var ageMaxVal = $("#amaxId").val();
		var result = true;
		if(ageMinVal != "" && ageMaxVal != ""){
			if (ageMinVal >= ageMaxVal) {
				result = false;
				return result;
			}
		}
		return result;
	}, "最大年龄过小!");
}


