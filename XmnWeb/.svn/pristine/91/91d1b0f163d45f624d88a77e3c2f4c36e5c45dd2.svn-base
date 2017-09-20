var pageDiv;
$(document)
		.ready(
				function() {

					pageDiv = $('#celebrityOrderDiv').page({
						url : 'billmanagerment/celebraty/list.jhtml',
						success : success,
						pageBtnNum : 10,
						pageSize : 10,
						paramForm : 'celebrityOrderForm'
					});

					inserTitle(
							' > 订单管理> <a href="billmanagerment/celebraty/init.jhtml" target="right"> 食评列表</a>',
							'celebrityOrderDiv', true);

					$("input[data-bus=reset]").click(function() {
						$(".form-control").attr("value", "");
						$("#ld").find("select").trigger("chosen:updated");
					});

					$('.form-datetime').datetimepicker({
						weekStart : 1,
						todayBtn : 1,
						autoclose : 1,
						todayHighlight : 1,
						startView : 2,
						minView: 2,
						forceParse : 0,
						showMeridian : 1,
						format : 'yyyy-mm-dd'
					});

				});

function queryBM(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    $("#status").val(parms);
    pageDiv.reload();
	$(object).attr("class", "btn btn-success");
	
	
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'
			+ data.total + '】条记录&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#searchBillForm").serialize());
	obj
			.find('div')
			.eq(0)
			.scrollTablel(
					{
						tableClass : "table-bordered table-striped info",
						callbackParam : callbackParam,
						caption : captionInfo,
						// 数据
						data : data.content,
						// 数据行
						cols : [ {
							title : "订单编号",// 标题
							name : "orderNo",
							// sort : "up",
							width : 200,// 宽度
							leng : 200,// 显示长度
							type : "string",// 数据类型
						}, {
							title : "商家信息",// 标题
							name : "sellername",
							width : 250,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								return data.sellername+"-"+data.fullname+"("+data.phoneid+")</br>要求到店时间："+data.arrivalTime
							}
						},{
							title : "食评名嘴",// 标题
							name : "name",
							// sort : "up",
							width : 200,// 宽度
							leng : 200,// 显示长度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								return data.name+"("+data.phone+")</br>";
							}
						},{
							title : "下单时间",// 标题
							name : "createTime",
							// sort : "up",
							width : 150,// 宽度
							type : "string"// 数据类型
						},{
							title : "订单状态",// 标题
							name : "status",
							// sort : "up",
							width : 120,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if(value == 1){
									return "接单";
								}else if(value == 2){
									return "品尝美食";
								}else if(value == 3){
									return "撰写食评";
								}else if(value == 4){
									return "等待发布";
								}else if(value == 5){
									return "发布成功";
								}else if(value == 6){
									return "订单取消";
								}else{
									return "-";
								}
							}
						},{
							title : "支付状态",// 标题
							name : "payStatus",
							// sort : "up",
							width : 120,// 宽度
							type : "string",// 数据类型
							customMethod : function(value, data) {
								if(value == 1){
									return "未支付";
								}else if(value == 2){
									return "已支付";
								}else if(value == 3){
									return "取消支付";
								}else if(value == 4){
									return "支付失败";
								}else if(value == 5){
									return "已退款";
								}else{
									return "-";
								}
							}
						}, {
							title : "订单总额",// 标题
							name : "price",
							// sort : "up",
							width : 100,// 宽度
							type : "string"// 数据类型
						}],
						// 操作列
						handleCols : {
							title : "操作",// 标题
							queryPermission : [ "update"],// 不需要选择checkbox处理的权限
							width : 120,// 宽度
							// 当前列的中元素
							cols : [
									{
										title : "更改状态",// 标题
										linkInfoName : "href",
										linkInfo : {
											param : [ "orderNo" ],// 参数
											permission : "update"// 列权限
										},
										customMethod : function(value, data) {
											return "<a href='"+context+"/billmanagerment/celebraty/update/init.jhtml'>更改状态</a>";
										}
									}]}
					}, permissions);
}


/**
 * 转换from表单
 */
function jsonFromt(data) {
	var json = {};
	for (var i = 0; i < data.length; i++) {
		json[data[i].name] = data[i].value;
	}
	return json;
}
