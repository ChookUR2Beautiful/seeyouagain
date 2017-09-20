var pageDiv;
$(document).ready(function() {
	var sellerid = $('#sellerid').val();
	pageDiv = $('#splitShopDiv').page({
		url : 'businessman/seller/init/splitShop/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'splitShopFromId',
		param : {sellerid:sellerid}
	});
	//删除
	$('#delete').click(function(){
		if(!pageDiv.getIds()){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}

		showSmConfirmWindow(function() {
			var  data = pageDiv.getIds();
			var arryIds = {'ids' : data};
			$.ajax({
		        type: "POST",
		        url: "businessman/seller/init/splitShop.jhtml",
		        data: arryIds,
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	pageDiv.reload();
		         }
		    });
		});
	});
		
	//批量通过
	$('#passId').click(function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		showSmConfirmWindow(function() {
		var  data = pageDiv.getIds();
		var arryIds = {'ids' : data,'status' : 3};
		$.ajax({
	        type: "POST",
	        url: "businessman/seller/updateSellerStatus.jhtml",
	        data: arryIds,
	        success: function(result){
	        	showSmReslutWindow(result.success, result.msg);
	        	pageDiv.reload();
	         }
	    });
		},"你确定执行批量通过？");
	});
	
	//批量不通过
	$('#notPassId').click(function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		var  data = pageDiv.getIds();
		var modalTrigger = new ModalTrigger({
			type : 'ajax',
			url : 'businessman/seller/sellerState.jhtml?ids=' + data ,
			toggle : 'modal'
		});
		modalTrigger.show();
	});
		
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
	inserTitle(' ><a href="businessman/seller/init/splitShop.jhtml?sellerid='+sellerid+'" target="right">总店列表</a>','splitShopList',false);
});

/**
 * 单个删除数据
 * @param id
 */
function deleteSeller(id){
	showSmConfirmWindow(function() {
	var data = {'ids' : id};
	$.ajax({
        type: "POST",
        url: "businessman/seller/beachDeleteSeller.jhtml",
        data: data,
        success: function(result){
        	showSmReslutWindow(result.success, result.msg);
        	pageDiv.reload();
         }
    });
	});
}

/**
 * 转换from表单
 */
function jsonFromt(data){
	var json = {};
	for(var i=0; i<data.length; i++){
		json[data[i].name] = data[i].value;
	}
	return json;
}


/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	//用于查询分店的id
	var fartherSellerId = $('#sellerid').val();
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">总店列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var b = permissions.xg==''&&permissions.ck=='';
	if(!b){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:130px;">操作</th>');
	}
	
	
	//html.push('<th style="width:100px;">ID</th>');
	html.push('<th style="width:150px;">加入时间</th>');
	html.push('<th style="width:150px;">名称</th>');
	html.push('<th style="width:50px;">行业类别</th>');
	//html.push('<th >分店</th>');
	
	
	if(permissions.zh=='true'){
		html.push('<th style="width:50px;">账号</th>');
	}
	if(permissions.zk=='true'){
		html.push('<th style="width:50px;">折扣</th>');
	}
	
	html.push('<th style="width:150px;">地址</th>');	
	html.push('<th style="width:100px;">咨询电话</th>');
	html.push('<th style="width:100px;">区域</th>');
	
	/*html.push('<th >经营情报</th>');*/
	html.push('<th style="width:100px;">归属合作商</th>');
	html.push('<th style="width:70px;">归属业务员</th>');
	html.push('<th style="width:70px;">状态</th>');		
	html.push('<th style="width:150px;">意见</th>');
	//html.push('<th style="width:80px;">操作员</th>');	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			//html.push('<td><a href="businessman/seller/updateSellerByid.jhtml?sellerid='+data.content[i].sellerid+'">修改</a>&nbsp;/&nbsp;<a href="#">删除</a></td>');
			
			if(!b){
				html.push('<th><input type="checkbox" val='+data.content[i].sellerid+'  /></th>');
				html.push('<td>');
				if(permissions.xg=='true'){
					html.push('<a href="businessman/seller/update/init.jhtml?sellerid='+data.content[i].sellerid+'">修改</a>&nbsp;&nbsp;');
				}
				if(permissions.ck=='true'){
					html.push('<a href="businessman/seller/getInit.jhtml?sellerid='+data.content[i].sellerid+'&editType=3&fartherSellerId='+fartherSellerId+'">查看</a>');
				}
				html.push('</td>');
				
			}
			
			//html.push('<td>'+data.content[i].sellerid+'</td>');
			//时间
			var signdate = data.content[i].signdate?data.content[i].signdate:'-';
			html.push('<td>'+signdate+'</td>');
			var sellername = data.content[i].sellername?data.content[i].sellername:'-';
			sellername = substr(sellername,8);
			html.push('<td class="text-ellipsis" title ="'+data.content[i].sellername+'">'+sellername+'</td>');	
			var typeName =   (undefined == data.content[i].typename ? "" : data.content[i].typename) + "--" +  (undefined == data.content[i].tradename ? "" : data.content[i].tradename);
			//所属行业
			html.push('<td class="text-ellipsis">'+typeName+'</td>');	
			//总店 html.push('<td class="text-ellipsis"><a href="http://www.baidu.com">查看</a></td>');
			
			//已签约才可以查看帐号和折扣
			if(data.content[i].status == 3)
			{
				//账号
				if(permissions.zh=='true'){
					html.push('<td class="text-ellipsis"><a href="businessman/sellerAccount/init.jhtml?sellerid='+data.content[i].sellerid+'">查看</a></td>');
				}
				//折扣
				if(permissions.zk=='true'){
					html.push('<td class="text-ellipsis"><a href="businessman/sellerAgio/init.jhtml?sellerid='+data.content[i].sellerid+'&fartherSellerId='+fartherSellerId+'">查看</a></td>');
				}
			}else
			{
				//账号
				if(permissions.zh=='true'){
					html.push('<td>-</td>');
				}
				//折扣
				if(permissions.zk=='true'){
					html.push('<td>-</td>');
				}
			}
			//地址
			var address = data.content[i].address?data.content[i].address:'-';
			address = substr(address,8);
			html.push('<td class="text-ellipsis" title ="'+data.content[i].address+'">'+address+'</td>');
			//咨询电话
			var tel = data.content[i].tel?data.content[i].tel:'-';
			html.push('<td class="text-ellipsis">'+tel+'</td>');
			//区域
			var title = data.content[i].title?data.content[i].title:'-';
			html.push('<td class="text-ellipsis">'+title+'</td>');
			
			//经营情报
			/*html.push('<td class="text-ellipsis"><a href="businessman/seller/manageDetails.jhtml?sellerid='+data.content[i].sellerid+'">查看</a></td>');*/
			//归属合作商
			var corporate = data.content[i].corporate?data.content[i].corporate:'-';
			corporate = substr(corporate,8);
			html.push('<td class="text-ellipsis"  title ="'+data.content[i].corporate+'">'+corporate+'</td>');
			//归属业务员
			var salesman = data.content[i].salesman?data.content[i].salesman:'-';
			html.push('<td class="text-ellipsis">'+salesman+'</td>');
			
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
			
			var examineinfo = data.content[i].examineinfo?data.content[i].examineinfo:'-';
			examineinfo = substr(examineinfo,8);
			html.push('<td class="text-ellipsis" title ="'+data.content[i].examineinfo+'">'+examineinfo+'</td>');	//意见
			//html.push('<td class="text-ellipsis">'+sellername+'</td>');	//操作员
			html.push('</tr>');
		}
}
else
{
	html.push('<tr>');
	html.push('<td colspan="20">暂无数据</td>');
	html.push('</tr>');
}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}