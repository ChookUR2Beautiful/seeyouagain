$(document).ready(function() {
	
		$("#cid").chosenObject({
		id : "cid",
		hideValue : "value",
		showValue : "cname",
		url : "coupon/couponissue/queryYouHuiQuan.jhtml",
		isChosen:true
	   });
	});
	
		$(function() {
			validate("addCouponDetailForm", {
				rules : {
					cid : {
						required : true
					},
					totalVolume : {
						required : true,
						digits : true,
						min : 1
					}
				},
				messages : {
					cid : {
						required : "优惠券必选"
					},
					totalVolume : {
						required : "发行量必填",
						digits : "发行量必须是正整数",
						min : "发行量必须大于0"
					}
				}
			}, formAjax);
		});
		function formAjax() {
			var data = jsonFromt($('#addCouponDetailForm').serializeArray());
			var url = "coupon/couponissue/youhuima/add.jhtml";
			$.ajax({
				type : 'post',
				url : url,
				data : data,
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
				success : function(data) {
					showSmReslutWindow(data.success, data.msg);
					// 添加成功后跳转到列表页面
					var url =contextPath+ '/coupon/couponissue/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}