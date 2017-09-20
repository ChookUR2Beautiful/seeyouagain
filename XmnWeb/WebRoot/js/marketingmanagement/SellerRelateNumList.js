var pageDiv;
$(document).ready(function() {
	var url  = [$("#sellerInPendingfoDiv").attr("request-init"),".jhtml"].join("");
/*	inserTitle(' > 活动管理 > <a href="'+url+'" target="right">参与活动商家</a>','sellerApplayList',true);
*/    

	
	$('.form-datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
	
	
	
	//重置
	//点击两次才能清空
	//解决方案：https://github.com/harvesthq/chosen/issues/2293
	$("input[data-bus=reset]").click(function(){
		/*if(location.href.indexOf("?") > 0){
			var tempurl = contextPath + "/"+url;
			location.href =tempurl;
		}*/
		//只要重置按钮一按下，就立即执行清空chosen的内容
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
			$("#tradeSelect").find("select").trigger("chosen:updated");
		},0);
	});
	
	pageDiv = $('#sellerInPendingfoDiv').page({
		url : url,
		success : success,
		pageBtnNum : 10,
		paramForm : 'sellerPendingFromId'
	});
});




/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">活动参与商家列表<font style="float:right;">共计【'+data.total+'】个参与商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(permissions.updateIsattend||permissions.exitActivity){
		html.push('<th>操作</th>');
		}
	html.push('<th>商家编号</th>');
	html.push('<th>商家名称</th>');
	/*html.push('<th>商家等级</th>');*/
	html.push('<th>商家手机号</th>');
	/*html.push('<th>行业类别</th>');
	html.push('<th>连锁店</th>');
	html.push('<th>地址</th>');	
	html.push('<th>区域</th>');
	html.push('<th>商圈</th>');
	html.push('<th>归属合作商</th>');
	*/html.push('<th>审核状态</th>');		
	html.push('<th>上线状态</th>');
	html.push('<th>参与状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerPendingFromId").serialize());
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(permissions.updateIsattend||permissions.exitActivity){
					html.push('<td>');
					
			   if(permissions.updateIsattend){
				if(data.content[i].isattend==0){
					html.push("<a href='javascript:#'  onclick='updateIsattend("+data.content[i].sellerMaketingId+','+data.content[i].isattend+','+data.content[i].sellerid+")'>暂停参与</a></br>");
				}else{
					html.push("<a href='javascript:#' style='color:#38b03f;' onclick='updateIsattend("+data.content[i].sellerMaketingId+','+data.content[i].isattend+','+data.content[i].sellerid+")'>重新参与</a></br>");
				}
			   }
				if(permissions.exitActivity){
				html.push("<a href='javascript:#'  onclick='exitActivity("+data.content[i].sellerMaketingId+','+data.content[i].sellerid+")'>退出活动<div>");
				  }  
				html.push('</td>');
				}
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				sellername = substr(sellername,8);
				html.push('<td class="text-ellipsis" title ="'+data.content[i].sellername+'">'+sellername+'</td>');	
				/*var sellerGradeStr = "-";
				if(data.content[i].sellerGrade == 1)
				{
					sellerGradeStr = "A 级";
				}
				else if(data.content[i].sellerGrade == 2)
				{
					sellerGradeStr = "B+级";
				}else if(data.content[i].sellerGrade == 3){
					sellerGradeStr = "B 级";
				}
				else if(data.content[i].sellerGrade == 4){
					sellerGradeStr = "C+级";
				}
				else if(data.content[i].sellerGrade == 5){
					sellerGradeStr = "C 级";
				}
				html.push('<td class="text-ellipsis" name="sellerGrade" initValue="'+data.content[i].sellerGrade+'">'+sellerGradeStr+'</td>');	//等级
*/				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				/*//所属行业
				var typeName =   (undefined == data.content[i].typename ? "" : data.content[i].typename) + "--" +  (undefined == data.content[i].tradename ? "" : data.content[i].tradename);
				html.push('<td class="text-ellipsis">'+typeName+'</td>');	
				
				//连锁店
				var lssellername = data.content[i].lssellername?data.content[i].lssellername:'-';
				html.push('<td class="text-ellipsis" title ="'+lssellername+'">'+ substr(lssellername,4)+'</td>');
				//平台补贴
				var flatAgio = data.content[i].flatAgio?data.content[i].flatAgio:"0";
				html.push('<td class="text-ellipsis">'+accMul(flatAgio,100)+" %"+'</td>');
				
				//地址
				var address = data.content[i].address?data.content[i].address:'-';
				address = substr(address,8);
				html.push('<td class="text-ellipsis" title ="'+data.content[i].address+'">'+address+'</td>');
				//区域
				var title = data.content[i].title?data.content[i].title:'-';
				html.push('<td class="text-ellipsis">'+title+'</td>');
				//商圈
				var btitle = data.content[i].btitle?data.content[i].btitle:'-';
				html.push('<td class="text-ellipsis">'+btitle+'</td>');
				//归属合作商
				var corporate = data.content[i].corporate?data.content[i].corporate:'-';
				corporate = substr(corporate,8);
				html.push('<td class="text-ellipsis"  title ="'+data.content[i].corporate+'">'+corporate+'</td>');*/
				
				//默认0未验证|1审核中|2未通过|3已签约|4未签约
				var status = "-";
				if(data.content[i].status == 1)
				{
					status = "审核中";
				}
				else if(data.content[i].status == 2)
				{
					status = "未通过";
				}
				else if(data.content[i].status == 3)
				{
					status = "已签约";
				}
				else if(data.content[i].status == 4)
				{
					status = "未签约";
				}
				else if(data.content[i].status == 0)
				{
					status = "未验证";
				}
				html.push('<td class="text-ellipsis" name="status" initValue="'+data.content[i].status+'">'+status+'</td>');	//状态
				
				
				// 0：未上线 1：已上线 2:预上线 3：已下线 默认为：0
				var Strisonline = "-";
				if(data.content[i].isonline == 3)
				{
					Strisonline = "已下线";
				}
				else if(data.content[i].isonline == 2)
				{
					Strisonline = "预上线";
				}
				else if(data.content[i].isonline == 1)
				{
					Strisonline = "已上线";
				}
				else if(data.content[i].isonline == 0)
				{
					Strisonline = "未上线";
				}
				html.push('<td class="text-ellipsis">'+Strisonline+'</td>');	//上线状态
				
				// 0：参加营销 1：不参加营销
				var StrisAttend = "-";
				if(data.content[i].isattend == 0)
				{
					StrisAttend = "已参与";
				}
				else if(data.content[i].isattend == 1)
				{
					StrisAttend = "已暂停";
				}
				html.push('<td class="text-ellipsis">'+StrisAttend+'</td>');	//上线状态
				html.push('</tr>');
			}
	}
	else
	{
		html.push('<tr>');
		html.push('<td colspan="21">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

function updateIsattend(sellerMaketingId,isattend,sellerid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/activityManagement/manzeng/initSellerRelateNum/updateSellerMarketingIsattend.jhtml' + '?t=' + Math.random(),
			data : 'id=' +sellerMaketingId+'&isattend='+isattend+'&sellerid='+sellerid+'&aid='+$('#aid').val(),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					pageDiv.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"请确认此操作！");
}
function exitActivity(sellerMaketingId,sellerid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'marketingManagement/activityManagement/manzeng/initSellerRelateNum/manzengExitActivity.jhtml' + '?t=' + Math.random(),
			data : 'id=' +sellerMaketingId+'&sellerid='+sellerid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					pageDiv.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"退出活动后，本记录会删除，是否确认？");
}
