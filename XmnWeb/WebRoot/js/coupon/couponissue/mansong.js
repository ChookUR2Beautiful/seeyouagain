var mansongList;
$(document).ready(function() {
	//满赠活动
	mansongList = $('#mansong').page({
		url : 'coupon/couponissue/mansong/init/list.jhtml',
		success : mansongSuccess,
		pageBtnNum : 10,
		paramForm : 'mansongForm',
		param:{
			activityType:"2"
		}
	});
	/*limitedDate({form:"#mansongForm",startDateName:"sDateBegin",endDateName:"eDateEnd",format:'yyyy-mm-dd hh:ii'});*/
	$("#addmansong").click(function(){
	      var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#mansongForm").serialize());
	      $("#addmansong").get(0).href="coupon/couponissue/mansong/add/init.jhtml?"+callbackParam;
	      return true;
	})
});

function mansongSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">满就送列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th >操作</th>');
	html.push('<th >活动编号</th>');
	html.push('<th >活动名称</th>');
	html.push('<th >满就送</th>');
	html.push('<th >满送频率</th>');
	html.push('<th >限制条件</th>');
	html.push('<th >活动开始时间</th>');
	html.push('<th >活动结束时间</th>');
	html.push('<th >总发行量</th>');
	html.push('<th >状态</th>');
	html.push('<th >备注</th>');
	html.push('<th >优惠券类型</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{  var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#mansongForm").serialize());
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<td>');
			if(permissions.mansongupdate){
				
				html.push('<a href="coupon/couponissue/mansong/update/init.jhtml?issueId='+data.content[i].issueId+callbackParam+'">修改</a>');
				html.push(" ");
			}
			if(data.content[i].status==0){
				html.push('<a href="javascript:void(updateStatusMangSong('+data.content[i].issueId+','+1+'));">启动</a>');
			}else if(data.content[i].status==1){
				html.push('<a href="javascript:void(updateStatusMangSong('+data.content[i].issueId+','+0+'));">停止</a>');
			}else{
				html.push('<a href="javascript:void(publishMangSong('+data.content[i].issueId+','+data.content[i].activityType+'));">发行</a>');
				
			}
			html.push('</td>')
			html.push('<td>' + (undefined == data.content[i].issueId ? "-" : data.content[i].issueId) + '</td>');
			html.push('<td>' + (undefined == data.content[i].activityName ? "-" : data.content[i].activityName) + '</td>');
			var td = '<a href="javascript:viod();" data-type="ajax"   data-url="coupon/couponissue/mansong/youhuiquan/list.jhtml?issueId='+data.content[i].issueId+'"  data-toggle="modal" data-title="满就送优惠券详细信息" data-width="850px" data-backdrop="static">查看</a>'
			html.push('<td>' + td+ '</td>');

			var rateText="-"
			if(undefined!=data.content[i].rate){
				if(data.content[i].rate==1){
					rateText="首单";
				}
                if(data.content[i].rate==2){
                	rateText="首满";
				}
                if(data.content[i].rate==3){
                	rateText="每次";
                }
			}
			html.push('<td>' + rateText+ '</td>');
			html.push('<td>' + (undefined == data.content[i].limitingCondition ? "-" : data.content[i].limitingCondition) + '</td>');
			html.push('<td>' + (undefined == data.content[i].dateStart ? "-" : data.content[i].dateStart) + '</td>');
			html.push('<td>' + (undefined == data.content[i].dateEnd ? "-" : data.content[i].dateEnd) + '</td>');
			if(undefined == data.content[i].issueVolume){
				html.push('<td>' +  "-" + '</td>');
			}else{
				if(data.content[i].issueVolume==0){
				html.push('<td>' + "0" + '</td>');	
				}else{
						html.push('<td><a href="coupon/couponissue/mansongmingxi/initlist.jhtml?issueId='+data.content[i].issueId+callbackParam+'">'+data.content[i].issueVolume+'</a></td>');	
				}
			}
			var statusText="";
			if(!(undefined == data.content[i].status)){
				 if(data.content[i].status==0){
					   statusText="已停止"
				   }else if(data.content[i].status==1){
					   statusText="已启动"
				   }else{
						statusText="待启动"
				   }
			}else{
			    statusText="-";
			}
			html.push('<td>' + statusText + '</td>');
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
function publishMangSong(issueId,activityType){
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
				mansongList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}, "确认发行？发行后活动自动启动！");
}

/**
 * 更新状态
 * @param issueId
 * @param status
 */
function updateStatusMangSong(issueId,status){
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
				mansongList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	},title);
}
