var activityOrderList;
$(document).ready(function() {
	activityOrderList = $('#activityOrderList').page({
		url : 'fresh/activityOrder/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:15,
		paramForm : 'searchForm'
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
	
	inserTitle(' > 积分超市 > <a href="fresh/activityOrder/init.jhtml" target="right">活动订单</a>','activityOrderList',true);
	
});
function queryBM(object,parms){
    var tags = document.getElementsByName("bumen");
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    $("#state").val(parms);
    activityOrderList.reload();
	$(object).attr("class", "btn btn-success");
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {

	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】条订单&nbsp;</font></caption>';
	obj.find('div').eq(0).scrollTablel({
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
				title : "活动类型",// 标题
				name : "activityType",
				width : 100,// 宽度
				type:"stirng",//数据类型
				customMethod : function(value, data) {
					if(value == 1){
						return "一元夺宝";
					}else if(value == 2){
						return "竞拍";
					}else{
						return "-";
					}
				}
				
		},{
			title : "关联活动",// 标题
			name : "activityName",
			//sort : "up",
			width : 120,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "商品",// 标题
			name : "productName",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},{
			title : "单价",// 标题
			name : "productPrice",
			width : 80,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "数量",// 标题
			name : "productNum",
			//sort : "up",
			width : 80,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "收件人",// 标题
			name : "receivingName",
			//sort : "up",
			width : 120,// 宽度
			type:"stirng"//数据类型
			
		},{
			title : "实收款",// 标题
			name : "amountReceived",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"stirng"//数据类型
			
		},
		{
			title : "订单状态",// 标题
			name : "state",
			width : 100,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
			customMethod : function(value, data) {//01待付款 02待发货 03已发货 04已完成 05已关闭
				if(value == 1){
					return "待付款 ";
				}else if(value == 2){
					return "待发货";
				}else if(value == 3){
					return "已发货";
				}else if(value == 4){
					return "已完成";
				}else if(value == 5){
					return "已关闭";
				}else{
					return "-";
				}
			}
		},
		{
			title : "下单时间",// 标题
			name : "createTime",
			width : 100,// 宽度
			type:"number"//数据类型
		}],
			//操作列
		handleCols : {
				title : "操作",// 标题
				queryPermission : ["view"],// 不需要选择checkbox处理的权限
				width : 100,// 宽度
				// 当前列的中元素
				cols : [{
					title : "查看",// 标题
					linkInfoName : "href",
					linkInfo : {
						href :"fresh/activityOrder/view.jhtml",
						param : ["id"],// 参数（退款金额 = 点单金额要减去减免金额）
						permission : "view"// 单列权限
					}
				}] 
	}},permissions);
}


/**
 * 导出订单
 */
$("#export").click(function(){
	$("#exportModal").modal();
});

/**
 * 确认导出
 */
$("#exportConfirm").click(function() {
//	var state = $("#state").val();
	var sdate = $("#exportSdate").val();
	var edate = $("#exportEdate").val();
	if((null != sdate && "" != sdate)||(null != edate && "" != edate)){
				$("#exportModal").modal('hide');
				$form = $("#exporsubform").attr("action","fresh/activityOrder/export.jhtml");
				$form[0].submit();
	}
	
})

