var youhuiquanList;
$(document).ready(function() {
	//优惠券活动
	youhuiquanList = $('#youhuiquan').page({
		url : 'marketingManagement/activityManagement/youhuiquan/init/list.jhtml',
		success : youhuiquanSuccess,
		pageBtnNum : 10,
		paramForm : 'youhuiquanForm',
		param:{
			type:"6"
		}
	});
	limitedDate({form:"#youhuiquanForm",startDateName:"sDateBegin",endDateName:"eDateEnd",format:'yyyy-mm-dd hh:ii'});
	
	$("#youhuiquanactivityManager").on("click",function(){
		var aids= new Array();
		var aids=youhuiquanList.getIds().split(",");
		if(!youhuiquanList.getIds()){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		if(aids.length>1){
			showWarningWindow("warning","请选择一条记录！");
			return;
		}
		var aid = youhuiquanList.getIds();
		$("#aid").val(aid);
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#youhuiquanForm").serialize());
		$("#addForm").get(0).action="marketingManagement/activityManagement/youhuiquan/activityManagerSeller/init.jhtml?doType=youhuiquan"+callbackParam;
	    $("#addForm").get(0).submit();
	});
	
});

function youhuiquanSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">优惠券活动列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	
	
	if(permissions.yhqtjsj=='true'){html.push('<th style="width:5%;">选择</th>');}
	var b = permissions.yhqcheck=='true' || permissions.yhqupdate=='true';
	if(b){
		html.push('<th>操作</th>');
	}
	html.push('<th>活动编号</th>');
	html.push('<th>活动名称</th>');
	html.push('<th>开始日期</th>');
	html.push('<th>结束日期</th>');
	html.push('<th>开始时间</th>');
	html.push('<th>结束时间</th>');
	html.push('<th>活动描述</th>');
/*	html.push('<th style="width:10%;">与其他折扣补贴活动互斥</th>');
*/	html.push('<th>参与商家</th>');
	html.push('<th>参与用户</th>');
	html.push('<th>订单总数</th>');
	html.push('<th>已送金额</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
		if(permissions.yhqtjsj=='true'){
			html.push('<th><input type="checkbox" val=' + data.content[i].aid + '  /></th>'); 
		}
		if(b){
			html.push('<td>');
			if(permissions.yhqupdate=='true'){
			
				html.push('<a href="javascript:viod(); " data-type="ajax"  data-width="60%" data-url="marketingManagement/activityManagement/youhuiquan/update/init.jhtml?aid='+data.content[i].aid +'&&isRelationSeller='+data.content[i].isRelationSeller+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
		    }
			if(permissions.yhqcheck=='true'){
				
				html.push('<a href="javascript:viod(); " data-type="ajax" data-width="50%" data-url="marketingManagement/activityManagement/youhuiquan/check/init.jhtml?aid='+data.content[i].aid +'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
			}
			html.push('</td>');
		}
			var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#youhuiquanForm").serialize());
			html.push('<td>' + (undefined == data.content[i].aid ? "-" : data.content[i].aid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].aname ? "-" : data.content[i].aname) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startDate ? "-" : data.content[i].startDate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endDate ? "-" : data.content[i].endDate) + '</td>');		
			html.push('<td>' + (undefined == data.content[i].startTimeText ? "-" : data.content[i].startTimeText) + '</td>');
			html.push('<td>' + (undefined == data.content[i].endTimeText ? "-" : data.content[i].endTimeText) + '</td>');
			 var eescriptions=(undefined == data.content[i].eescription ? "-" : data.content[i].eescription);
			html.push('<td title ="'+eescriptions+'">' + substr(eescriptions) + '</td>');
			//与其他折扣补贴活动互斥，0不互斥，1互斥
			/*html.push('<td>' + (undefined == data.content[i].repelName ? "-" : data.content[i].repelName) + '</td>');*/
			var isRelateNum=(undefined == data.content[i].isRelationSeller ? "0" : data.content[i].isRelationSeller);
			if(permissions.yhqcysj=='true'){
				if(isRelateNum==0){
					html.push('<td>'+isRelateNum+'</td>');
				}else{
					html.push('<td><a id="SendoutAmount" href="marketingManagement/activityManagement/youhuiquan/initSellerRelateNum/init.jhtml?aid='+data.content[i].aid + callbackParam+'">'+isRelateNum+' </a>&nbsp;&nbsp;</td>');
				}
			}else{
				html.push('<td>'+isRelateNum+'</td>');
			}
			
			html.push('<td>' + (undefined == data.content[i].participateNum ? "-" : data.content[i].participateNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].billNum ? "-" : data.content[i].billNum) + '</td>');
			var StrgiveMoneyCount=(undefined == data.content[i].giveMoneyCount ? "0" : data.content[i].giveMoneyCount);
			if(permissions.yhqzjje=='true'){
				if(StrgiveMoneyCount==0){
					html.push('<td>'+StrgiveMoneyCount+'</td>');
				}else{
					html.push('<td><a id="SendoutAmount" href="marketingManagement/activityManagement/youhuiquan/activityPrize/init.jhtml?aid='+data.content[i].aid + callbackParam+'">￥'+StrgiveMoneyCount+' </a>&nbsp;&nbsp;</td>');
				}
			}else{
				html.push('<td>'+StrgiveMoneyCount+'</td>');
			}
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



