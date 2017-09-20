function checkNum(maxNum,item){
	var num=$(item).val();
	if(num>maxNum){
		$(item).val(maxNum);
	}else if(num<0||!num){
		$(item).val(0);
	}
}

$("#submit").on("click",function(){
//	var data = $('#editGroupFrom').serializeArray();
	var pid=$("#pid").val();
	var cash=$("#cash").val();
	var integral=$("#integral").val();
	var codeId=$("#codeId").val();
	var sort=$("#sort").val();
	var pname=$("#pname").val();
	var maxStore=$("#maxStore").val();
	var store=$("#store").val()?$("#store").val():0;
	var tableList=$(".tableList");
	var arr=new Array();
	$.each(tableList,function(i,item){
		var _item=$(item);
		var sort=_item.find("input[name$='sort']").val();
		var pvIds=_item.find("input[name$='pvIds']").val();
		var stock=_item.find("input[name$='stock']").val();
		var amount=_item.find("input[name$='amount']").val();
		var codeId=_item.find("input[name$='codeId']").val();
		var maxAmount=_item.find("input[name$='maxAmount']").val();
		var maxStock=_item.find("input[name$='maxStock']").val();
		var pvValues=_item.find("input[name*='pvValues']");
		var pvValuesArr=new Array();
		$.each(pvValues,function(i,item){
			var p_item=$(item);
			pvValuesArr.push(p_item.val());
		});
		var obj=new Object();
		obj.sort=sort;
		obj.pvIds=pvIds;
		obj.stock=stock;
		obj.amount=amount;
		obj.codeId=codeId;
		obj.pvValues=pvValuesArr;
		obj.maxStock=maxStock;
		arr.push(obj);
		store=stock*1+store*1;
	});
	var map={
		"cash":cash,
		"type":"edit",
		"integral":integral,
		"codeId":codeId,
		"sort":sort,
		"saleGroupList":arr	,
		"maxStore":maxStore,
		"store":store
	};
	if($("#type").val()=="edit"){
		var tds=$("tr[id="+pid+"]").find("td");
		tds.eq(2).text(integral+"积分+"+cash)
		tds.eq(3).text(store);
		tds.eq(4).text(sort);
		$("tr[id="+pid+"]").find("input[type=hidden]").val($.toJSON(map));
		$('#triggerModal').modal('hide');
	}else{
		var tr=$("<tr id="+pid+">").append($("<td>").text(codeId)).append($("<td>").text(pname)).append($("<td>").text(integral+"积分+"+cash))
		.append($("<td>").text(store)).append($("<td>").text(sort))
		
		if($("#productTB").size()){
			//活动添加页面
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">').html('<a href="javascript:;" onclick="editGroup('+pid+')">编辑</a>&nbsp;&nbsp;<a href="javascript:;" onclick="deleteGroup('+pid+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			$("#productTB").append(tr);
		}else{
			//首页手动添加商品
			tr.append($('<td data-row="0" data-index="0" data-flex="false" data-type="string" style="width: 130px; height: 59px;">').html('<a href="javascript:;" onclick="editGroup('+pid+')">编辑</a>&nbsp;&nbsp;<a href="javascript:;" onclick="deleteGroup('+pid+')">删除</a>')).append($("<input>").attr("type","hidden").val($.toJSON(map)));
			groupEditKey.append(tr);
		}
		$('#triggerModal').modal('hide');
	}
});