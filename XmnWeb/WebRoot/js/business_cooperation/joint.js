var jointList;
$(document).ready(function() {

	//区域
	var ld = $("#ld").areaLd({
		isChosen : true
	});
	
	$('#delete').click(function() {
		remove(jointList.getIds());
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
	inserTitle(' > 合作商管理 > <a href="business_cooperation/joint/init.jhtml" target="right">合作商信息管理</a>','userSpan',true);

	$("#export").click(function(){
		$form = $("#searchForm").attr("action","business_cooperation/joint/export.jhtml");
		$form[0].submit();
	});
	
	jointList = $('#jointList').page({
		url : 'business_cooperation/joint/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});
	
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});
});
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个合作商&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	updateAddBtnHref("#addJoint",callbackParam);
	
	obj.find('div').eq(0).scrollTablel({
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
			title : "合作商编号",// 标题
			name : "jointid",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "公司名称",// 标题
			name : "corporate",
			width : 250,// 宽度
			type:"string"//数据类型
		},{
			title : "法人姓名",// 标题
			name : "legalperson",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "合作商联系手机",// 标题
			name : "phoneid",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "登录账号",// 标题
			name : "loginAccount",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "商家总数",// 标题
			name : "sellerNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "未签约商家",// 标题
			name : "noSignNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "未通过商家",// 标题
			name : "willonlineNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "上线商家",// 标题
			name : "noPassNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "活跃商家",// 标题
			name : "activeSellerNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "沉睡商家",// 标题
			name : "sleepSellerNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "签约商家",// 标题
			name : "agreeNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "商圈",// 标题
			name : "areaNum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "已启动商圈",// 标题
			name : "startnum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "城市",// 标题
			name : "cityTitle",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "区域",// 标题
			name : "areaTitle",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "状态",// 标题
			name : "statusText",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "钱包",// 标题
			name : "jointid",
			width : 200,// 宽度
			leng : 8,//显示长度
			type :"string",//数据类型
			isLink : true,// 表示当前列是否是超链接 true:是 false：不是
			// 只有当isLink为true时 才有效
			link : {
				linkInfoName : "modal", // href , modal ,method
				linkInfo : {
					modal : {
						url : "business_cooperation/joint/getWallet.jhtml",// url
						position:"300px",// 模态框显示位置
						width:"600px",
						title : "钱包信息"
					}
				},
				param : ["jointid"],// 参数
				permission : "getWallet"// 单列权限
			},
			customMethod : function(value, data) {
				return $(value).html("钱包")[0].outerHTML;
			}
		},{
			title : "地址",// 标题
			name : "address",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "开通时间",// 标题
			name : "sdate",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "saas总套数",// 标题
			name : "saasnum",
			width : 120,// 宽度
			type:"string"//数据类型
		},{
			title : "库存",// 标题
			name : "stocknum",
			width : 120,// 宽度
			type:"string"//数据类型
		},{
			title : "sass折扣",// 标题
			name : "saasagio",
			width : 120,// 宽度
			type:"string"//数据类型
		},{
			title : "签约总额",// 标题
			name : "saasamount",
			width : 120,// 宽度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["update"],// 不需要选择checkbox处理的权限
				width : 150,// 宽度
				// 当前列的中元素
				cols : [{
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : 'business_cooperation/joint/update/init.jhtml',
						param : ["jointid"],// 参数
						permission : "update"// 列权限
					}
				}] 
	}},permissions);
	
	
	/*var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">合作商</caption>');
	html.push('<thead>');
	html.push('<tr class="text-center">');
	var hasHandle = permissions && (permissions.update);
	if(hasHandle){
		html.push('<th><input type="checkbox" /></th>');
		html.push('<th>操作</th>');
	}

	html.push('<th>商家总数</th>');
	html.push('<th>未签约商家</th>');
	html.push('<th>未通过商家</th>');
	html.push('<th>上线商家</th>');
	html.push('<th>活跃商家</th>');
	html.push('<th>沉睡商家</th>');
	html.push('<th>签约商家</th>');
	html.push('<th>商圈</th>');
	html.push('<th>已启动商圈</th>');
	html.push('<th>城市</th>');
	html.push('<th>区域</th>');
	html.push('<th>状态</th>');
	if(permissions.getWallet){
		html.push('<th>钱包</th>');
	}
	html.push('<th>地址</th>');
	html.push('<th>开通时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		var callbackParam="&isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
		updateAddBtnHref("#addJoint",callbackParam);
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandle){
				html.push('<th><input type="checkbox" val=' + data.content[i].jointid + '  /></th>');
				if(permissions.update){
					html.push('<td><a href="business_cooperation/joint/update/init.jhtml?jointid='+data.content[i].jointid+callbackParam+'">修改</a></td>');
				}
				if(permissions.del){
					html.push('<a href="javascript:remove('+data.content[i].jointid+')">删除</a>&nbsp;&nbsp;');
				}
			}
			html.push('<td>'+data.content[i].jointid+'</td>');
			var corporate = (undefined == data.content[i].corporate ? "-" : data.content[i].corporate);
			html.push('<td title="'+corporate+'">' + substr(corporate,6) + '</td>');
			html.push('<td>' + (undefined == data.content[i].legalperson ? "-" : data.content[i].legalperson) + '</td>');
			html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].loginAccount ? "-" : data.content[i].loginAccount) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sellerNum ? "-" : data.content[i].sellerNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].noSignNum ? "-" : data.content[i].noSignNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].willonlineNum ? "-" : data.content[i].willonlineNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].noPassNum ? "-" : data.content[i].noPassNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].activeSellerNum ? "-" : data.content[i].activeSellerNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sleepSellerNum ? "-" : data.content[i].sleepSellerNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].agreeNum ? "-" : data.content[i].agreeNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].areaNum ? "-" : data.content[i].areaNum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].startnum ? "-" : data.content[i].startnum) + '</td>');
			html.push('<td>' + (undefined == data.content[i].cityTitle ? "-" : data.content[i].cityTitle) + '</td>');
			var areaTitle = (undefined == data.content[i].areaTitle ? "-" : data.content[i].areaTitle);
			html.push('<td title="'+areaTitle+'">' + substr(areaTitle,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].statusText ? "-" : data.content[i].statusText) + '</td>');
			if(permissions.getWallet){
				html.push('<td><a data-type="ajax" data-url="business_cooperation/joint/getWallet.jhtml?jointid='+data.content[i].jointid+'" data-toggle="modal" href="javascript:">钱包</a>&nbsp;&nbsp;</td>');
			}
			var address = (undefined == data.content[i].address ? "-" : data.content[i].address);
			html.push('<td title="'+address+'">' + substr(address,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));*/
}

/**
 * 删除
 */
/*function remove(jointid) {
	if(!jointid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/joint/delete.jhtml' + '?t=' + Math.random(),
			data : 'jointid=' + jointid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					jointList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

