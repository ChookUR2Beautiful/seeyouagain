var pageDiv;
$(document).ready(function() {
	inserTitle(' > 商家管理 > <a href="businessman/sellerPending/init.jhtml" target="right">商家信息待审核</a>','sellerApplayList',true);
    
	//行业类别
	$("#tradeSelect").tradeLd({
		isChosen : true,
		showConfig : [{name:"category",tipTitle:"请选择",width:'48%'},{name:"genre",tipTitle:"请选择",width:'49%'}]
	});
	
	//商圈
	$("#zoneid").chosenObject({
		id : "zoneid",
		hideValue : "bid",
		showValue : "title",
		url : "common/business/businessInfo.jhtml",
		isChosen:true
	});
	
	//合作商
	$("#jointid").chosenObject({
		id : "jointid",
		hideValue : "jointid",
		showValue : "corporate",
		url : "business_cooperation/joint/jointList.jhtml",
		isChosen:true
	});
	
		
	//批量通过
	$('#passId').click(function(){
		if(!pageDiv.getIds()){
			showWarningWindow("warning","请至少选择一条记录！");
			return;
		}
		
		if(!pageDiv.validateChose("sellerGrade", "1,2,3,4,5", "商家等级未设置，不能审核通过！")){
			console.log(pageDiv.get());
			return;
		}
		
		showSmConfirmWindow(function() {
			var  data = pageDiv.getIds();
			
			var arryIds = {'ids' : data,'status' : 3};
			$.ajax({
		        type: "POST",
		        url: "businessman/sellerPending/beachStatus.jhtml",
		        data: arryIds,
				beforeSend : function(XMLHttpRequest) {
					$('#prompt').show();
				},
		        success: function(result){
		        	$('#prompt').hide();
		        	showSmReslutWindow(result.success, result.msg);
		        	pageDiv.reload();
		         },
		 		error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
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
			url : 'businessman/sellerPending/updateSellerStatus/sellerState.jhtml?ids=' + data ,
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
	
	$("#export").click(function(){
		$form = $("#sellerPendingFromId").attr("action","businessman/sellerPending/export.jhtml");
		$form[0].submit();
	});
	
	var ld = $("#ld").areaLd({
		//url : "businessman/sellerPending/init/ld.jhtml",
		isChosen : true
	});
	
	//重置
	//点击两次才能清空
	//解决方案：https://github.com/harvesthq/chosen/issues/2293
	$("input[data-bus=reset]").click(function(){
		if(location.href.indexOf("?") > 0){
			var url = contextPath + '/businessman/sellerPending/init.jhtml';
			location.href =url;
		}
		//只要重置按钮一按下，就立即执行清空chosen的内容
		setTimeout(function(){
			$("#ld").find("select").trigger("chosen:updated");
			$("#tradeSelect").find("select").trigger("chosen:updated");
			$("#zoneid").trigger("chosen:updated");
			$("#jointid").trigger("chosen:updated");
		},0);
	});
	
	pageDiv = $('#sellerInPendingfoDiv').page({
		url : 'businessman/sellerPending/init/list.jhtml',
		success : successScroll,
		pageBtnNum : 10,
		pageSize : 15,//每也条数
		param : {status : 1},
		paramForm : 'sellerPendingFromId'
	});
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
	        url: "businessman/sellerPending/beachDeleteSeller.jhtml",
	        data: data,
	        success: function(result){
	        	showSmReslutWindow(result.success, result.msg);
	        	pageDiv.reload();
	         }
	    });
	});
}



/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家信息待审核<font style="float:right;">共计【'+data.total+'】个商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	var flag = permissions.update=="" && permissions.view ==""
	if(!flag){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:200px;">操作</th>');
	}
	
	html.push('<th style="width:100px;">商家编号</th>');
	html.push('<th style="width:250px;">加入时间</th>');
	html.push('<th style="width:150px;">商家名称</th>');
	html.push('<th style="width:150px;">商家等级</th>');
	html.push('<th style="width:200px;">商家手机号</th>');
	html.push('<th style="width:50px;">行业类别</th>');
	html.push('<th style="width:150px;">连锁店</th>');
	//html.push('<th style="width:100px;">平台补贴</th>');	
	html.push('<th style="width:100px;">地址</th>');	
	html.push('<th style="width:100px;">咨询电话</th>');
	html.push('<th style="width:100px;">区域</th>');
	html.push('<th style="width:100px;">商圈</th>');
	html.push('<th style="width:100px;">归属合作商</th>');
	html.push('<th style="width:70px;">归属业务员</th>');
	html.push('<th style="width:70px;">状态</th>');		
	html.push('<th style="width:100px;">意见</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#sellerPendingFromId").serialize());
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(!flag){
					html.push('<th><input type="checkbox" val='+data.content[i].sellerid+'  /></th>');
					html.push('<td>');
					//修改权限
					if(permissions.update=='true'){
						html.push('<a href="businessman/sellerPending/update/init.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'&viewType=editSellerPending">修改</a>');
					}
					
					//查看权限
					if(permissions.view=='true'){
						html.push('&nbsp;&nbsp;<a href="businessman/sellerPending/getInit.jhtml?sellerid='+data.content[i].sellerid+callbackParam+'&viewType=viewSellerPending">查看</a>');
					}
					html.push('</td>');
				
				}
				
				//html.push('<td>'+data.content[i].sellerid+'</td>');
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				//时间
				var signdate = data.content[i].signdate?data.content[i].signdate:'-';
				html.push('<td>'+signdate+'</td>');
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				sellername = substr(sellername,8);
				html.push('<td class="text-ellipsis" title ="'+data.content[i].sellername+'">'+sellername+'</td>');	
				var sellerGradeStr = "-";
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
				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				//所属行业
				var typeName =   (undefined == data.content[i].typename ? "" : data.content[i].typename) + "--" +  (undefined == data.content[i].tradename ? "" : data.content[i].tradename);
				html.push('<td class="text-ellipsis">'+typeName+'</td>');	
				
				//连锁店
				var lssellername = data.content[i].lssellername?data.content[i].lssellername:'-';
				html.push('<td class="text-ellipsis" title ="'+lssellername+'">'+ substr(lssellername,4)+'</td>');
				//平台补贴
				/*var flatAgio = data.content[i].flatAgio?data.content[i].flatAgio:"0";
				html.push('<td class="text-ellipsis">'+accMul(flatAgio,100)+" %"+'</td>');*/
				
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
				//商圈
				var btitle = data.content[i].btitle?data.content[i].btitle:'-';
				html.push('<td class="text-ellipsis">'+btitle+'</td>');
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
		html.push('<td colspan="21">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

function successScroll(data, obj) {
	var checkable = permissions.updateSellerStatus=="true";
	var formId = "sellerPendingFromId",title = "商家信息待审核",subtitle="个商家";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+title+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】'+subtitle+'&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#"+formId).serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "sellerid",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		checkable : checkable,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["update", "view"],// 不需要选择checkbox处理的权限
			width : 120,// 宽度
			// 当前列的中元素
			cols : [{
				title : "查看",// 标题
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerPending/getInit.jhtml",
					param : ["sellerid"],
					permission : "view"
				},
				customMethod : function(value, data){
					value = $(value);
					value.attr("href",$(value).attr("href")+"&viewType=viewSellerPending");
					value = value[0].outerHTML;
					return value;
				}
			},{
				title : "修改",// 标题
				linkInfoName : "href",
				linkInfo : {
					href : "businessman/sellerPending/update/init.jhtml",
					param : ["sellerid"],
					permission : "update"
				},
				customMethod : function(value, data){
					value = $(value);
					value.attr("href",$(value).attr("href")+"&viewType=editSellerPending");
					value = value[0].outerHTML;
					return value;
				}
			},] 
		},
		cols:[{
			title : "商家名称",
			name : "sellername",
			type : "string",
			width : 120,
			customMethod : function(value,data){
				return value + "<br/>("+data.sellerid+")";
			}
		},{
			title: "折扣",
			name : "baseagio",
			type : "int",
			width : 60,
            customMethod : function(value){
				return value * 100 + "%";
			}
		},{
			title : "营业执照",
        	name : "licenseurl",
        	type : "string",
        	width : 80,
        	customMethod : function(value){
				var imageprefix = $('#fastfdsHttp').val();
                return '<button type="button" class="btn btn-primary" data-custom="<img src='+imageprefix+value+'>" data-toggle="modal">查看</button>';
			}


        // <button type="button" class="btn btn-primary" data-iframe="partial/iframe-modal.html" data-toggle="modal">iframe对话框</button>

		},{
			title: "商户联系人",
			name : "fullname",
			type : "string",
			width : 160,
            customMethod : function(value,data){
				return value + "<br/>("+data.phoneid+")";
			}
		},{
            title : "区域",
            name : "title",
            type : "string",
            width : 110,
        },{
			title : "地址",
			name : "address",
			type : "string",
			width : 150
		},{
			title : "推荐人类型",
			name : "saasTypeTexe",
			type : "string",
			width : 90
		},{
			title : "推荐人",
			name : "xmerName",
			type : "string",
			width : 180,
            customMethod : function(value,data){
				if(value || data.uid)
					return value + "<br/>("+data.uid+")"
				else
					return "--";
			}
		},{
			title : "状态",
			name : "statusText",
			type : "string",
			width : 80
		}]},permissions);
}
