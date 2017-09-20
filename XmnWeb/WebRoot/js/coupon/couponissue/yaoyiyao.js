var yaoyiyaoList;
$(document).ready(function() {
	//满赠活动
	yaoyiyaoList = $('#yaoyiyao').page({
		url : 'coupon/couponissue/yaoyiyao/init/list.jhtml',
		success : manzengSuccess,
		pageBtnNum : 10,
		paramForm : 'yaoyiyaoForm',
		param:{
			activityType:"1"
		} 
	});
	/*limitedDate({form:"#manzengForm",startDateName:"sDateBegin",endDateName:"eDateEnd",format:'yyyy-mm-dd hh:ii'});*/
	$("#addyaoyiyao").click(function(){
	      var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#yaoyiyaoForm").serialize());
	      $("#addyaoyiyao").get(0).href="coupon/couponissue/yaoyiyao/add/init.jhtml?"+callbackParam;
	      return true;
	})
});

function manzengSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">摇一摇列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th >操作</th>');
	html.push('<th >活动编号</th>');
	html.push('<th >活动名称</th>');
	html.push('<th >摇中概率</th>');
	html.push('<th >优惠券</th>');
	html.push('<th >总发行量</th>');
	html.push('<th >状态</th>');
	html.push('<th >限制条件</th>');
	html.push('<th >备注</th>');
	html.push('<th >优惠券类型</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{   var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#yaoyiyaoForm").serialize());
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			html.push('<td>');
			if(permissions.yaoyiyaoupdate){
				
				html.push('<a href="coupon/couponissue/yaoyiyao/update/init.jhtml?issueId='+data.content[i].issueId+callbackParam+'">修改</a>');
				html.push(" ");
			}
			if(data.content[i].status==1){
				html.push('<a href="javascript:void(updateStatusYaoYiYao('+data.content[i].issueId+','+0+'));">停止</a>');
			}else if(data.content[i].status==0){
				html.push('<a href="javascript:void(updateStatusYaoYiYao('+data.content[i].issueId+','+1+'));">启动</a>');
			}else{
				html.push('<a href="javascript:void(publishYaoYiYao('+data.content[i].issueId+','+data.content[i].activityType+'));">发行</a>');
			}
			html.push('</td>');
			html.push('<td>' + (undefined == data.content[i].issueId ? "-" : data.content[i].issueId) + '</td>');
			html.push('<td>' + (undefined == data.content[i].activityName ? "-" : data.content[i].activityName) + '</td>');
			html.push('<td>' + (undefined == data.content[i].hitRatioText ? "-" : data.content[i].hitRatioText) + '</td>');
			html.push('<td><a title="点击查看优惠券信息" href="coupon/couponissue/viewCoupon/viewCouponInit.jhtml?issueId='+data.content[i].issueId+callbackParam+'" >查看</a></td>');
			
/*			html.push('<td><a href="javascript:void();" data-title="优惠券信息" title="点击查看优惠券信息" data-type="ajax" data-width="60%" data-position="300px" data-url="coupon/couponissue/viewCoupon.jhtml?issueId='+data.content[i].issueId+'" >查看</a></td>');
*/			
			if(undefined == data.content[i].issueVolume){
				html.push('<td>' +  "-" + '</td>');
			}else{
				if(data.content[i].issueVolume==0){
				html.push('<td>' + "0" + '</td>');	
				}else{
						html.push('<td><a href="coupon/couponissue/yaoyiyaomingxi/initlist.jhtml?issueId='+data.content[i].issueId+callbackParam+'">'+data.content[i].issueVolume+'</a></td>');	
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
			html.push('<td>' + (undefined == data.content[i].limitingCondition ? "-" : data.content[i].limitingCondition) + '</td>');
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
function publishYaoYiYao(issueId,activityType){
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
				yaoyiyaoList.reload();
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
function updateStatusYaoYiYao(issueId,status){
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
				yaoyiyaoList.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	},title);
}
