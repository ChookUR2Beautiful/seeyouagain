
var div;
$(function(){
	var allBillList = $('#allBillList');
	var url  = [$(allBillList).attr("request-init"),".jhtml"].join("");
	div = $(allBillList).page({
		url : url,
		success : handle,
		pageBtnNum : 10,
		paramForm : 'searchBillForm'
	});
	
	limitedDate({form:"#searchBillForm",startDateName:"zdateStart",endDateName:"zdateEnd",format:'yyyy-mm-dd hh:ii'});
	//导出数据    
/*	$("#export").click(function(){
		var pageSize = $("#memberTable").attr("data-total");
		$form = $("#searchForm").attr("action","member/memberList/export.jhtml?pageSize="+pageSize);
		var $input = $("<input type='hidden' name='pageSize' value='"+pageSize+"'>");
		$form.append($input);
		$form[0].submit();
		$input.remove();
	});*/
});

function handle(data, obj){
	var model = {
	title : "已送出金额",
	columns : [{
				title : "订单号",
				name : "bid",
				width : "120px"
			}, {
				title : "订单金额",
				name : "money",
				width : "50px",
			}, {
				title : "支付方式",
				name : "paytypeText",
				width : "50px",
			},  {
				title : "折扣",
				name : "baseagio",
				customMethod : function(value, data) {
					return (value ? Number(value)*100 +"%": "-");
				},
				width : "50px",
			}, {
				title : "用户昵称",
				name : "nname",
				width : "50px",
			},{
				title : "用户手机",
				name : "phoneid",
				width : "50px",
			},{
				title : "参与商家",
				name : "genusname",
				width : "50px",
			},{
				title : "支付时间",
				name : "zdate",
				width : "50px",
			},{
				title : "赠送金额",
				name : "ratioMoney",
				width : "50px"
			}]
	}
	obj.find('div').eq(0).html($.renderGridView(model,data));
	
}