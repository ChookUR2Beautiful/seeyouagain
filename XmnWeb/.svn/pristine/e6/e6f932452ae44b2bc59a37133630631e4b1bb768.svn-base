var yaoyiyaomingxiList;
$(document).ready(function() {
	// 优惠码列表
	yaoyiyaomingxiList = $('#yaoyiyaomingxiList').page({
		url : 'coupon/couponissue/yaoyiyaomingxi/init/list.jhtml',
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
				}, {
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
					title : "领取时间",
					name : "getTime",
				}, {
					title : "使用时间",
					name : "userTime",
				} ]
			}, data));
		},
		pageBtnNum : 10,
		paramForm : 'youhuimaForm',
		param : {
			activityType : "1"
		}
	});
});
