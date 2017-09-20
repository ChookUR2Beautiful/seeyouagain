$(function() {
	inserTitle(
			'> 商家管理 > <span><a href="businessman/seller/init.jhtml" target="right">商家信息管理</a><span> ><span>修改菜品</span>',
			'', true);
	$("#fid").chosenObject(
			{
				id : "fid",
				hideValue : "fid",
				showValue : "className",
				url : "businessman/seller/food/class/init/list.jhtml?sellerId="
						+ sellerId,
				isChosen : true,
				width : "100%"
			});
	$("#foodBigPic").uploadImg({
		urlId : "picUrl",
		showImg : $('#picUrl').val()
	});

	validate("foodUpdateForm", {
		rules : {
			foodName : {
				required : true
			},
			cprice : {
				required : true
			}
		},
		messages : {
			foodName : {
				required : "请输入菜品名称！"
			},
			cprice : {
				required : "请输入菜品价格！",
			}
		}
	}, formAjax);
});
function formAjax() {
	var data = jsonFromt($('#foodUpdateForm').serializeArray());
	$
			.ajax({
				type : 'post',
				url : "businessman/seller/food/update.jhtml" + "?t="
						+ Math.random(),
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide')
					showSmReslutWindow(data.success, data.msg);
					// 添加成功后跳转到列表页面
					var url = contextPath
							+ '/businessman/seller/food/init.jhtml?sellerid=${food.sellerId}';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
}