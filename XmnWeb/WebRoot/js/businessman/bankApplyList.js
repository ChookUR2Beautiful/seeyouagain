var bankApplyList;
$(document).ready(function() {
	bankApplyList = $('#bankApplyList').page({
		url : 'businessman/bankApply/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchBankApplyForm'
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
	inserTitle(' > 商家管理 > <span><a href="businessman/bankApply/init.jhtml" target="right">商家银行卡信息管理</a>','bankApplyList',true);
/*
	$("#export").click(function(){
		$form = $("#sellerFromId").attr("action","businessman/seller/export.jhtml");
		$form[0].submit();
	});*/
	
});

function queryBumen(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    var data = {handletype:parms};
	$.ajax({
        type: "POST",
        url : 'businessman/bankApply/init/list.jhtml',
        data: data,
        dataType: "json",
        success: function(result){
        	$("#handletype").val(parms);
        	bankApplyList.reload();
         }
    });
	$(object).attr("class", "btn btn-success");
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;&nbsp;商家银行卡信息管理</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchBillForm").serialize());
	obj.find('div').eq(0).scrollTablel({
	    	//checkable :false,
	    	//identifier : "bid",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "申请类型",// 标题
					name : "applytypeText",
					//sort : "up",
					width : 150,// 宽度
					leng : 3,//显示长度
					type:"stirng",//数据类型
					
			},{
				title : "商家编号",// 标题
				name : "aid",
				width : 150,// 宽度
				type:"stirng"//数据类型
				
		},{
			title : "开户姓名",// 标题
			name : "fullname",
			//sort : "up",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "开户行名称",// 标题
			name : "bank",
			//sort : "up",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},{
			title : "所属省",// 标题
			name : "location",
			width : 150,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "所属市",// 标题
			name : "cityname",
			//sort : "up",
			width : 160,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "证件类型",// 标题
			name : "idtypeText",
			width : 150,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},
		{
			title : "证件号码",// 标题
			name : "idcard",
			//sort : "up",
			width : 200,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},
		{
			title : "银行卡号",// 标题
			name : "bankid",
			//sort : "up",
			width : 200,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
			
		},
		{
			title : "银行卡类型",// 标题
			name : "typeText",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "公/私",// 标题
			name : "ispublic",
			//sort : "up",
			width : 100,// 宽度
			type:"string",//数据类型
			customMethod : function(value,data){
				if(data.ispublic==0){
					return "私";
				}
				if(data.ispublic==1){
					return "公";
				}
				return "-";
			}
		},
		{
			title : "用途",// 标题
			name : "modifitypeText",
			//sort : "up",
			width : 200,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "手机预留号",// 标题
			name : "bankphone",
			//sort : "up",
			width : 200,// 宽度
			type:"number"//数据类型
		}
		,
		{
			title : "处理状态",// 标题
			name : "handletypeText",
			//sort : "up",
			width : 200,// 宽度
			type:"string"//数据类型
			
		},
		{
			title : "申请时间",// 标题
			name : "bdate",
			//sort : "up",
			width : 160,// 宽度
			type:"string"//数据类型
		},
		{
			title : "补充材料",// 标题
			name : "supplementaryInformation",
			width : 200,// 宽度
			leng : 8,//显示长度
			type:"string",//数据类型
			isLink : true,// 表示当前列是否是超链接 true:是 false：不是
			// 只有当isLink为true时 才有效
			link : {
				required : true,
				linkInfoName : "modal", // href , modal ,method
				linkInfo : {
					modal : {
						url : "businessman/bankApply/seeSupplementaryInformation.jhtml",// url
						position:"300px",// 模态框显示位置
						width:"960px",
						title : "补充资料"
					}
				},
				param : ["appid"]// 参数
			}
		},
		{
			title : "申请原因",// 标题
			name : "remark",
			width : 200,// 宽度
			type:"string"//数据类型
		},
		{
			title : "申请不通过原因",// 标题
			name : "handleremark",
			width : 200,// 宽度
			leng : 8,//显示长度
			type:"string",//数据类型
			
		},
		{
			title : "开户行名称缩写",// 标题
			name : "abbrev",
			width : 200,// 宽度
			type:"string"//数据类型
		}
		
		],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["up","notup"],// 不需要选择checkbox处理的权限
				width : 150,// 宽度
				// 当前列的中元素
				cols : [{
					title : "通过",// 标题
					linkInfoName : "method",
					linkInfo : {
						method : "updatetg",
						param : ["appid"],// 参数
						permission : "up"// 列权限
					},
					customMethod : function(value,data){
						if(data.handletype==1){
							return value;
						}
						return "-";
					}
				},
				{
					title : "不通过",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "businessman/bankApply/updateNot/init.jhtml",// url
							position:"300px",// 模态框显示位置
							width:"960px",
							title : "补充资料"
						},
						param : ["appid"],// 参数
						permission : "notup"// 列权限
					},
					customMethod : function(value,data){
						if(data.handletype==1){
							return value;
						}
						return "-";	
					}
				}] 
	}},permissions);
		
	
/*	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家银行卡信息管理</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var b = permissions.notup==''&&permissions.up=='';
	if(!b){
			//html.push('<th style="width:20px;"><input type="checkbox" /></th>');
			html.push('<th style="width:130px;">操作</th>');
	}
	html.push('<th style="width:80px;">申请类型</th>');   
	html.push('<th style="width:80px;">商家编号</th>');
	html.push('<th style="width:100px;">开户姓名</th>');
	html.push('<th style="width:100px;">开户行名称</th>');
	html.push('<th style="width:100px;">支行名称</th>');
	html.push('<th style="width:70px;">所属省</th>');	
	html.push('<th style="width:70px;">所属市</th>');
	html.push('<th style="width:80px;">证件类型</th>');
	html.push('<th style="width:100px;">证件号码</th>');
	html.push('<th style="width:100px;">银行卡号</th>');
	html.push('<th style="width:100px;">银行卡类型</th>');
	html.push('<th style="width:80px;">用途</th>');
	html.push('<th style="width:100px;">手机预留号</th>');	
	html.push('<th style="width:80px;">处理状态</th>');
	html.push('<th style="width:100px;">申请时间</th>');
	html.push('<th style="width:80px;">补充材料</th>');
	html.push('<th style="width:100px;">申请原因</th>');	
	html.push('<th style="width:100px;">申请不通过原因</th>');
	html.push('<th style="width:70px;">开户行名称缩写</th>');	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				if(!b){
					//html.push('<th><input type="checkbox" val='+data.content[i].appid+'  /></th>');
					    html.push('<td>');
					if(permissions.up=='true'&&data.content[i].handletype==1){
						html.push('&nbsp;&nbsp;<a href="javascript:updatetg('+data.content[i].appid+')">通过</a>&nbsp;&nbsp;');
					}
					if(permissions.notup=='true'&&data.content[i].handletype==1){
						html.push('<a href="javascript:viod();" data-title="是否要拒绝商家的此次银行卡信息修改？" data-type="ajax"   data-url="businessman/bankApply/updateNot/init.jhtml?appid='+data.content[i].appid+'"  data-toggle="modal" >不通过</a>');
					}
			    html.push('</td>');	
				}	
				html.push('<td>' + (undefined == data.content[i].applytypeText ? "-" : data.content[i].applytypeText) + '</td>');
				html.push('<td>' + (undefined == data.content[i].aid ? "-" : data.content[i].aid) + '</td>');
				html.push('<td>' + (undefined == data.content[i].fullname ? "-" : data.content[i].fullname) + '</td>');
				html.push('<td>' + (undefined == data.content[i].bank ? "-" : data.content[i].bank) + '</td>');
					var bankname=(undefined == data.content[i].bankname ? "-" : data.content[i].bankname);
				html.push('<td title ="'+bankname+'">' + substr(bankname) + '</td>');
				html.push('<td>' + (undefined == data.content[i].location ? "-" : data.content[i].location) + '</td>');
				html.push('<td>' + (undefined == data.content[i].cityname ? "-" : data.content[i].cityname) + '</td>');				
					var idtype=data.content[i].idtype;
					var idtypes="";
					if(idtype==1){		 idtypes="身份证";
					}else if(idtype==2){ idtypes="护照";
					}else if(idtype==3){ idtypes="驾驶证";}
				html.push('<td>' + (undefined == idtypes ? "-" : idtypes) + '</td>');
				html.push('<td>' + (undefined == data.content[i].idcard ? "-" : data.content[i].idcard) + '</td>');
				html.push('<td>' + (undefined == data.content[i].bankid ? "-" : data.content[i].bankid) + '</td>');
					var type=data.content[i].type;
					var types="";
					if(type==1){		types="借记卡(储蓄卡)";
					}else if(type==2){  types="信用卡";}
				html.push('<td>' + (undefined == types ? "-" : types) + '</td>');	
				var modifitype=data.content[i].modifitype;
				var modifitypes="";
				if(modifitype==1){		modifitypes="营业收入";
				}else if(modifitype==2){  modifitypes="佣金收益";}
				html.push('<td>' + (undefined == modifitypes ? "-" : modifitypes) + '</td>');	
				html.push('<td>' + (undefined == data.content[i].bankphone ? "-" : data.content[i].bankphone) + '</td>');				
				    var handletype=data.content[i].handletype;
					var handletypes="";
					if(handletype==0){			handletypes="已处理";
					}else if(handletype==1){	handletypes="处理中";
					}else if(handletype==2){	handletypes="不通过";}
				html.push('<td>' + (undefined == handletypes ? "-" : handletypes) + '</td>');
				html.push('<td>' + (undefined == data.content[i].bdate ? "-" : data.content[i].bdate) + '</td>');
				html.push('<td><a href="javascript:void();" data-title="补充资料" data-type="ajax" data-position="" data-width="960px" data-height="700px"data-url="businessman/bankApply/seeSupplementaryInformation.jhtml?appid='+data.content[i].appid+'" data-toggle="modal">查看</a>&nbsp;&nbsp;</td>');
					var remark=(undefined == data.content[i].remark ? "-" : data.content[i].remark);
				html.push('<td title ="'+remark+'">' + substr(remark) + '</td>');
					var  handleremark=(undefined == data.content[i].handleremark ? "-" : data.content[i].handleremark);
				html.push('<td title ="'+handleremark+'">' + substr(handleremark) + '</td>');
				html.push('<td>' + (undefined == data.content[i].abbrev ? "-" : data.content[i].abbrev) + '</td>');
				
			//	html.push('<td>' + (undefined == data.content[i].modifitype ? "-" : data.content[i].modifitype) + '</td>');//1营业收入银行卡  2 佣金收益银行卡
			//	html.push('<td>' + (undefined == data.content[i].abbrev ? "-" : data.content[i].abbrev) + '</td>');							
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
*/}

/**
 * 通过
 * @param {} appid
 */
function updatetg(appid) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/bankApply/update.jhtml' + '?t=' + Math.random(),
			data : {
				'appid':appid,
				'handletype':0	
			},
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {			 				
	          	$('#prompt').hide();
				if (data.success) {
					bankApplyList.reload();
			    }			
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	},"你确定执行通过？");
}


