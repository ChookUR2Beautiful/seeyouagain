var userlist;
$(function() {
	inserTitle(
			' > 优惠券管理  > <a href="coupon/couponissue/init.jhtml" target="right">优惠券发放管理</a> > 短信发放优惠券 > 发送用户',
			'activityManagement', true);
	userlist = $("#userlist").page({
		url : "coupon/couponissue/sendshortmessage/userlist.jhtml",
		param : {
			issueId : issueId
		},
		success : function(data, obj) {
			obj.find('div').eq(0).html($.renderGridView({
				title : "短信发送优惠券 - 发送用户",
				totalTitle : "用户",
				form : "searchForm",
				checkbox : true,
				backButton : true,
				handleColumn : {
					title : "操作",
					name : "cdid",
					handlePermissions : [ "sendmessage" ], // 需要用到checkbox
					queryPermissions : [],// 不需要用到checkbox
					column : [ /*
								 * { title : "发送", href :
								 * "businessman/seller/update/init.jhtml", param : [
								 * "cdid" ], permissions : "sendmessage" }
								 */]
				},
				columns : [ {
					title : "用户编号",
					name : "uid"
				}, {
					title : "手机号码",
					name : "phone"
				}, {
					title : "优惠劵名称",
					name : "cname"
				}, {
					title : "优惠券SN码",
					name : "serial"
				}, {
					title : "优惠劵面额",
					name : "denomination"
				}, {
					title : "获取状态",
					name : "getStatusText"
				}, {
					title : "发送状态",
					name : "sendStatusText"
				} ],
				permissions : permissions
			}, data));
		},
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$("#sendMessageBtn").on("click", function() {
		if (!userlist.getIds()) {
			showWarningWindow("warning", "请选择一条记录！");
			return;
		}
		sendMessage(issueId, userlist.getIds());
	})
});

function sendMessage(issueId, cdids) {
	showSmConfirmWindow(function() {
		data = {
			'issueId' : issueId,
			'cdids' : cdids
		}
		$.ajax({
			type : 'post',
			url : "coupon/couponissue/sendshortmessage/sendMessage.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				if (data.success) {
					userlist.reload();
				} else {
					$('#prompt').hide();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}, "是否发送短信?");
}