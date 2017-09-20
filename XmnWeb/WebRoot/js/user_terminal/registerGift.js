var giftList;
$(document).ready(function() {
	giftList = $('#giftList').page({
		url : 'user_terminal/register_gift/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
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
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/register_gift/init.jhtml" target="right">注册礼包管理</a>','userSpan',true);

	$("input[data-bus=reset]").click(function() {
		//清楚Select的option的select属性
		if (location.href.indexOf("?") > 0) {
			var url = contextPath + '/user_terminal/register_gift/init.jhtml';
			location.href = url;
		}
	});
	
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	obj.find('div').eq(0).html($.renderGridView(giftModel,data));
}



var giftModel = {
		title : "注册礼包列表",
		totalTitle:"注册礼物",
		form : "searchForm",
		checkbox : true,
		backButton : true,
		addBtn : "addGiftBtn",
		handleColumn :{
					title : "操作",
					name : "id",
					queryPermissions :["delete","update"],
					column : [{
								title : "修改",
								href : "user_terminal/register_gift/update/init.jhtml",
								param : ["id"],
								permissions : "update"
							}, {
								title : "删除",
								method : "remove",
								param : ["id"],
								permissions : "delete",
							}]
		},
		columns : [{
					title : "序号",
					name : "id",
					width : "10%"
					},{
					title : "礼物类型",
					name : "giftType",
					width : "10%",
					customMethod : function(value,data) {
						var type = "-";
						if (value == 1) {
							type = "积分";
						} else if (value == 2) {
							type = "通用优惠券";
						} else if (value == 3){
							type="鸟豆";
						}
						return type;
					}
				}, {
					title : "礼物标签",
					name : "giftStr",
					width : "60%"
				}],
		permissions : permissions
	};

function remove(id){
	showSmConfirmWindow(function(){
		 $.ajax({
	         url : "user_terminal/register_gift/delete.jhtml",
	         type : "post",
	         dataType : "json",
	         data:'id=' + id +"&t="+Math.random(),
	         success : function(result) {
	        	 if (result.success) {
	     			showSmReslutWindow(result.success, result.msg);
	     			giftList.reload();
	     		} else {
	     			window.messager.warning(result.msg);
	     		}
	         }
	     });
	}, "你确定删除吗？");
}