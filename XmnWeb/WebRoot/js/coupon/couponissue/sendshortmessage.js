var sendshortmessageList;
$(document).ready(function() {
	//满赠活动
	sendshortmessageList = $('#sendshortmessage').page({
		url : 'coupon/couponissue/sendshortmessage/init/list.jhtml',
		success : sendshortmessageSuccess,
		pageBtnNum : 10,
		paramForm : 'sendshortmessageForm',
		param:{
			activityType:"3"
		}
	});
	var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sendshortmessageForm").serialize());
	$("#addsendshortmessage").click(function(){
	    
	      $("#addsendshortmessage").get(0).href="coupon/couponissue/sendshortmessage/add/init.jhtml?"+callbackParam;
	      return true;
	});
	
	$("#addUsers").on("click",function(){
		if(!sendshortmessageList.getIds()){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		if(sendshortmessageList.getIds().split(",").length>1){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		$("#addUsers").get(0).href="coupon/couponissue/sendshortmessage/addUsers/init.jhtml?issueId="+sendshortmessageList.getIds()+callbackParam;
	});
});

function sendshortmessageSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">短信发送列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th >选择</th>');
	html.push('<th style="width:10%;">操作</th>');
	html.push('<th >活动编号</th>');
	html.push('<th >活动名称</th>');
	html.push('<th >短信内容</th>');
	html.push('<th >优惠券</th>');
	html.push('<th >发送用户</th>');
	html.push('<th >发送状态</th>');
	html.push('<th >发送时间</th>');
	/*	html.push('<th style="width:10%;">发送成功数</th>');
	html.push('<th style="width:10%;">发送失败数</th>');*/
	html.push('<th >备注</th>');
	html.push('<th >优惠券类型</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<th><input type="checkbox" val=' + data.content[i].issueId + '  /></th>');
			html.push('<td>');
			var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sendshortmessageForm").serialize());
			if(permissions.sendshortmessageupdate){
				
				html.push('<a href="coupon/couponissue/sendshortmessage/update/init.jhtml?issueId='+data.content[i].issueId+'&status='+data.content[i].status+callbackParam+'">修改</a>');
				html.push(" ");
			}
			if(permissions.importUsers && data.content[i].sendStatus==0){
				
				html.push('&nbsp;&nbsp;<a data-toggle="modal" data-url="coupon/couponissue/sendshortmessage/importUsers/init.jhtml?issueId='+data.content[i].issueId+callbackParam+'" data-type="ajax" href="coupon/couponissue/sendshortmessage/update/init.jhtml?issueId='+data.content[i].issueId+'&status='+data.content[i].status+callbackParam+'">导入用户</a>');
				html.push(" ");
			}
			if(data.content[i].status==0&&data.content[i].sendStatus==0){
				html.push('&nbsp;&nbsp;<a href="javascript:void(updateStatusMessage('+data.content[i].issueId+','+1+'));">启动</a>');
			}else if(data.content[i].status==1&&data.content[i].sendStatus==0){
				html.push('&nbsp;&nbsp;<a href="javascript:void(updateStatusMessage('+data.content[i].issueId+','+0+'));">停止</a>');
			}
			if(data.content[i].sendStatus==0){
				html.push('&nbsp;&nbsp;<a href="javascript:sendMessage('+data.content[i].issueId+','+data.content[i].usernum+');">发送</a>');
			}
			html.push('</td>')
			html.push('<td>' + (undefined == data.content[i].issueId ? "-" : data.content[i].issueId) + '</td>');
			html.push('<td>' + (undefined == data.content[i].activityName ? "-" : data.content[i].activityName) + '</td>');
			html.push('<td>' + (undefined == data.content[i].message ? "-" : data.content[i].message) + '</td>');
			html.push('<td>' + (undefined == data.content[i].cname ? "-" : data.content[i].cname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].usernum ? "-" : '<a href="coupon/couponissue/sendshortmessage/userlist/init.jhtml?issueId='+data.content[i].issueId+callbackParam+'">'+data.content[i].usernum+'</a>') + '</td>');
			html.push('<td>' + (undefined == data.content[i].sendStatus ? "-" :data.content[i].sendStatus==0?"待发送":"已发送" ) + '</td>');
			html.push('<td>' + (undefined == data.content[i].dateSend ? "-" : data.content[i].dateSend) + '</td>');
			html.push('<td>' + (undefined == data.content[i].remark ? "-" : data.content[i].remark) + '</td>');
			html.push('<td>' + (data.content[i].ctypeStr) + '</td>');
			html.push('</tr>');
		}
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}
/**
 * 发行优惠券
 * @param issueId
 * @param activityType
 */
function publishMessage(issueId,activityType){
	showSmConfirmWindow(function() {
		data = {'issueId':issueId,'activityType':activityType}
		$.ajax({
			type : 'post',
			url : "coupon/couponissue/youhuima/add.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				sendshortmessageList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}, "确定要发行？");
}

/**
 * 更新状态
 * @param issueId
 * @param status
 */
function updateStatusMessage(issueId,status){
	var title;
	if(status===1){
		title="确定要启动？";
	}
	if(status===0){
		title="确定要停止？";
	}
	showSmConfirmWindow(function() {
		data = {'issueId':issueId,'status':status}
		$.ajax({
			type : 'post',
			url : "coupon/couponissue/updateCouponIssueStatus.jhtml",
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				showSmReslutWindow(data.success, data.msg);
				sendshortmessageList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	},title);
}

function sendMessage(issueId,usernum) {
	if(usernum<1){
		showWarningWindow("warning","请先添加用户后再发送！");
	}else{
	showSmConfirmWindow(function() {
		data = {
			'issueId' : issueId,
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
					sendshortmessageList.reload();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	 }, "是否发送短信?");
	}
}

