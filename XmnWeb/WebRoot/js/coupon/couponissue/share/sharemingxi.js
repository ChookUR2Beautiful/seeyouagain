/**
 * 
 */

var sharemingxiList;
$(function() {
	inserTitle(
			' >优惠券管理 > <span><a href="coupon/couponissue/init.jhtml.jhtml" target="right">优惠券发放管理</a> >分享发放明细',
			'couponList', true);
	initDates();
	sharemingxiList = $('#sharemingxiList').page({
		url : 'coupon/couponissue/share/mingxi/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'sharemingxiForm',
		param : {
			activityType : "1"
		}
	});
});

function initDates() {
	startDateAndEndDate("getTimeStart", "getTimeEnd");
	startDateAndEndDate("dateIssueStart", "dateIssueEnd");

}

function startDateAndEndDate(startDateFormName, endDateFormName) {
	$('input[name="' + startDateFormName + '"]').datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		// startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function() {
				$("input[name='" + endDateFormName + "']").datetimepicker(
						"setStartDate",
						$("input[name='" + startDateFormName + "']").val());
			});
	$('input[name="' + endDateFormName + '"]').datetimepicker({
		autoclose : true,
		format : 'yyyy-mm-dd hh:ii',
		// startDate : new Date(),
		todayBtn : true,
		minuteStep : 1
	}).on(
			"changeDate",
			function() {
				$("input[name='" + startDateFormName + "']").datetimepicker(
						"setEndDate",
						$("input[name='" + endDateFormName + "']").val());
			});
}

function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;优惠码列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total + '】个优惠码&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#sharemingxiForm").serialize());
	// updateAddBtnHref("#addShare", callbackParam);
	obj.find('div').eq(0).scrollTablel({
		identifier : "issueId",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// 操作列
		/*
		 * handleCols : { },
		 */
		cols : [ {
			title : "优惠券SN码",
			name : "serial",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "优惠券编号",
			name : "cid",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "优惠券名称",
			name : "cname",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "获取状态",
			name : "getStatusText",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "使用状态",
			name : "userStatus",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "发行时间",
			name : "dateIssue",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "获取方式",
			name : "getWayText",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "领取时间",
			name : "getTime",
			type : "string",
			// width : 400,
			leng : 8
		}, {
			title : "使用时间",
			name : "userTime",
			type : "string",
			// width : 400,
			leng : 8
		} ]
	}, permissions);
}