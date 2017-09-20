var abnormalList;
$(document).ready(function() {
	abnormalList = $('#abnormalList').page({
		url : 'user_terminal/abnormal_seller/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(abnormalList.getIds());
	});
	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'	
	});
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/abnormal_seller/init.jhtml" target="right">举报商家管理</a>','userSpan',true);

	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/user_terminal/abnormal_seller/init.jhtml';
			location.href = url;
		}
	});
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/abnormal_seller/export.jhtml");
		$form[0].submit();
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	obj.find('div').eq(0).html($.renderGridView(abnormalModel,data));
}


/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/abnormal_seller/delete.jhtml' + '?t=' + Math.random(),
			data : {"id":id},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					topicList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

var abnormalModel = {
		title : "举报商家列表",
		totalTitle:"举报记录",
		form : "searchForm",
		checkbox : true,
		backButton : true,
		addBtn : "addAbnormalBtn",
		handleColumn :{
					title : "操作",
					name : "id",
					queryPermissions :["delete","update"],
					column : [{
								title : "处理",
								method : "dealPass",
								param : ["id"],
								permissions : "update",
								customIsShowMethod: function(value, data){
			                        if(data.status==0){
			                           	return true;
			                        }else{
										return false;
			                        }
								}
							},{
								title : "虚假举报",
								method : "dealUnable",
								param : ["id"],
								permissions : "update",
								customIsShowMethod: function(value, data){
			                        if(data.status==0){
			                           	return true;
			                        }else{
										return false;
			                        }
			                    }
							}, {
								title : "删除",
								method : "removeAbnormal",
								param : ["id"],
								permissions : "delete",
							}]
		},
		columns : [{
					title : "举报类型",
					name : "type",
					width : "10%",
					customMethod : function(value,data) {
						var type = "-";
						if (value == 1) {
							type = "商家基础信息有误";
						} else if (value == 2) {
							type = " 电话空号";
						} else if(value == 3){
							type = "地址错误";
						} else if(value == 4){
							type = "无法支付";
						}
						return type;
					}
				}, {
					title : "被举报商家编号",
					name : "sellerid",
					width : "10%"
				}, {
					title : "被举报商家",
					name : "sellername",
					width : "5%"
				}, {
					title : "举报会员编号",
					name : "uid",
					width : "5%"
				}, {
					title : "举报会员",
					name : "uname",
					width : "5%"
				}, {
					title : "举报会员联系方式",
					name : "phone",
					width : "5%"
				}, {
					title : "举报时间",
					name : "sdate",
					width : "5%"
				}, {
					title : "处理结果",
					name : "status",
					width : "5%",
					customMethod : function(value,data) {
						var status = "-";
						if (value == 0) {
							status = "待处理";
						} else if (value == 1) {
							status = "已处理";
						} else if(value == 2){
							status = "虚假举报";
						}
						return status;
					}
				}, {
					title : "处理时间",
					name : "edate",
					width : "5%"
				}],
		permissions : permissions
	}

/**
 * 修改申请
 * @param {} id
 * @param {} bid
 * @param {} money
 * @param {} remarks
 */		
function dealPass(id) {
	dealAbnormal(id,1);
}
function dealUnable(id) {
	dealAbnormal(id,2);
}
function dealAbnormal(id,status) {
	showSmConfirmWindow(function(){
		 $.ajax({
	         url : "user_terminal/abnormal_seller/update.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'id=' + id + '&status='+ status+"&t="+Math.random(),
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				abnormalList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	}, "你确定执行操作吗？");
}

function removeAbnormal(id){
	showSmConfirmWindow(function(){
		 $.ajax({
	         url : "user_terminal/abnormal_seller/delete.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'id=' + id + '&status='+ status+"&t="+Math.random(),
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			setTimeout(function() {
	     				abnormalList.reload();
	     			}, 3000);
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	}, "你确定删除吗？");
}