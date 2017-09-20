$(document).ready(function() {
	inserTitle(' > 套餐明细信息', 'sellerPackageDetail', false);
});




function sellerPackageOption(flag, status){
	var ids = $("#id").val();
	/*if(!pageDiv.getIds()){
		showWarningWindow("warning", "请至少选择一条套餐记录！");
		return;
	}*/
	if(flag == 1 && status == 1){
		if($("#status").val() == 3){
			return;
		}
	}
	showSmConfirmWindow(function(){
		$.ajax({
			type:"POST",
			url:"sellerPackage/manage/beachOnLine/updateStatusOption.jhtml",
			data:{ids:ids,flag:flag,state:status},
			dataType:"json",
			success:function(data){
				if(data.success) {
					setTimeout(function() {
//						pageDiv.reload();
						window.location.href="sellerPackage/manage/init.jhtml";
					}, 1000);
				}
				showSmReslutWindow(data.success,data.msg);
			},
			error:function(data){
				showSmReslutWindow(data.success, data.msg);
			}
		});
		
	}, "确定修改吗？")
}



//导出
$("#handelOnLine").click(function(){
	sellerPackageOption(1, 1);
});	

//2.下架
$("#handelDownLine").click(function(){
	sellerPackageOption(1, 2);
});