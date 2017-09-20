var youhuimaList;
$(document).ready(function() {
	// 优惠码列表
	youhuimaList = $('#youhuima').page({
		url : 'coupon/couponissue/youhuima/init/list.jhtml',
		success : function(data, obj) {
			obj.find('div').eq(0).html($.renderGridView({
				title : "优惠码列表",
				totalTitle : "优惠码",
				form : "youhuimaForm",
				columns : [ {
					title : "优惠券SN码",
					name : "serial"
				},{
					title : "优惠券编号",
					name : "cid"
				}, {
					title : "优惠券名称",
					name : "cname"
				},{
					title : "活动编号",
					name : "issueId"
				},{
					title : "活动名称",
					name : "activityName"
				},{
					title : "用户编号",
					name : "uid"
				},{
					title : "获取状态",
					name : "getStatusText"
				}, {
					title : "使用状态",
					name : "userStatusText"
				}, {
					title : "发行时间",
					name : "dateIssue",
				}, {
					title : "获取方式",
					name : "getWayText",
				}, {
					title : "领取人手机号",
					name : "phone",
				}, {
					title : "获取时间",
					name : "getTime",
				}, {
					title : "使用时间",
					name : "userTime",
				},{
					title : "优惠券类型",
					name : "ctypeStr",
				}
				]
			}, data));
		},
		pageBtnNum : 10,
		paramForm : 'youhuimaForm',
		param : {
			activityType : "1"
		}
	});

	$("#publish").on("click", function() {
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : 'coupon/couponissue/youhuima/add/init.jhtml',
			toggle : 'modal'
		});
		modalTrigger.show();
	});
});
