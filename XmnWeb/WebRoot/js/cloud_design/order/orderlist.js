var allBillList;
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	allBillList = $('#materialOrderList').page({
		url : 'materialOrder/manage/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		pageSize:10,
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
	
	inserTitle(' > 雏鸟云设计 > <a href="materialOrder/manage/init.jhtml" target="right">订单管理</a>','allBillList',true);
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","materialOrder/manage/export.jhtml");
		$form[0].submit();
	});
});
function queryBM(object,parms){
    var tags = document.getElementsByName("bumen") ;
    for(i=0;i<tags.length;i++){
    	$(tags[i]).attr("class", "btn btn-default");
    }
    $("#status").val(parms);
	allBillList.reload();
	$(object).attr("class", "btn btn-success");
	
	
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html=[];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
 	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家物料订单<font style="float:right;">共计【'+data.total+'】条订单&nbsp;</font></caption>');
 	
 	html.push('<thead>');
 	html.push('<tr>');
 	html.push('<th>商品</th>');
 	html.push('<th>单价/数量</th>');
 	html.push('<th>买家</th>');
 	html.push('<th>下单时间</th>');
 	html.push('<th>订单状态</th>');
 	html.push('<th>实付金额</th>');
 	html.push('<th>售后</th>');
 	html.push('</tr>');
 	html.push('</thead>');
 	
 	html.push('<tbody>');
 	html.push('</tbody>');
 	
 	if(null != data && data.content.length>0){
 		for(var i = 0;i<data.content.length;i++){
 			html.push('<tr><td colspan="7" style="text-align: left;background-color:#E7E4CD;">');
 			html.push('<p style="display:inline-block;">订单编号：'+(undefined == data.content[i].orderNo?'-':data.content[i].orderNo)+'</p>');
 			html.push('<p style="margin-left: 350px;display:inline-block;">供应商：'+(undefined == data.content[i].name?'-':data.content[i].name)+'</p>');
 			html.push('<div style="float:right;"><a href="materialOrder/manage/init/view.jhtml?orderNo='+data.content[i].orderNo+'&isBackButton=true&callbackParam='+'&t='+Math.random()+'">查看订单详情</a>|');
 			html.push('<a  href="javascript:;" style="color:gray;"'+'>填写物流单</a>|');
 			html.push('<a href="javascript:;" style="color:gray;"'+'>操作售后</a></div>');
 			html.push('</td></tr>');
 			html.push('<tr colspan="2">');
 			
 			if(data.content[i].type == 001){
 				
 				html.push('<td style="width: 20%;"><div><img src="'+imgRoot+data.content[i].url+'" style="width:60px;float:left;" />'
 	 					+'<p style="padding-left:70px;text-align:left;">'+(undefined == data.content[i].title ? "-" : data.content[i].title)+'</p>'
 	 					+'<p style="padding-left:70px;text-align:left;">'+(undefined == data.content[i].remark ? "-" : data.content[i].remark)
 	 					+'<a href="javaScript:;" onclick="shopView(\''+data.content[i].orderNo+'\',\''+data.content[i].type+'\')">【查看商品】'
 	 					+'</a>'+'</p>'+ '</div></td>');
 				
 				html.push('<td style="width: 10%;">' + (undefined == data.content[i].orderAmount ? "-" : data.content[i].salePrice) +'</br>('+data.content[i].nums+'件)'+'</td>');
 			}else{
 				html.push('<td style="width: 20%;"><div><p style="text-align:left;">'+(undefined == data.content[i].title ? "-" : data.content[i].title)+'</p>'
 	 					+'<p style="text-align:left;">'+(undefined == data.content[i].remark ? "-" : data.content[i].remark)
 	 					+'<a href="javaScript:;" onclick="shopView(\''+data.content[i].orderNo+'\',\''+data.content[i].type+'\')">【定制详情】'
 	 					+'</a>'+'</p>'+ '</div></td>');
 				
 				html.push('<td style="width: 10%;">-</td>');
 			}
 				
 			html.push('<td style="width: 15%;">' + (undefined == data.content[i].consignee ? "-" : data.content[i].consignee) + '</br>'+data.content[i].mobile+'</td>');
 			html.push('<td style="width: 15%;">' + (undefined == data.content[i].createTimeStr ? "-" : data.content[i].createTimeStr) + '</td>');
 			if(undefined != data.content[i].status){
 				switch(data.content[i].status){
 				case 1:
 					html.push('<td style="width: 10%;">待付款</td>');
 					break;
 				case 2:
 					html.push('<td style="width: 10%;">待确定</td>');
 					break;
 				case 3:
 					html.push('<td style="width: 10%;">待发货</td>');
 					break;
 				case 4:
 					html.push('<td style="width: 10%;">已发货</td>');
 					break;
 				case 5:
 					html.push('<td style="width: 10%;">已完成</td>');
 					break;
 				case 6:
 					html.push('<td style="width: 10%;">已关闭</td>');
 					break;
 				case 9:
 					html.push('<td style="width: 10%;">已关闭</td>');
 					break;
 				case 7:
 					html.push('<td style="width: 10%;">售后</td>');
 					break;
 				default:
 					html.push('<td style="width: 10%;">-</td>');
 					break;
 				}
 			}else{
 				html.push('<td>-</td>');
 			}
 			html.push('<td style="width: 15%;">' + (undefined == data.content[i].orderAmount ? "-" : data.content[i].orderAmount+data.content[i].freight)+(data.content[i].type == 001?'</br>(含运费：'+data.content[i].freight+'元)':'')+'</td>');
 			html.push('<td>售后</td>');
 			html.push('</tr>');
 		}
 	}else{
 		html.push('<tr><td colspan="21">暂无数据</td></tr>')
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

function shopView(orderNo,type) {
			var url='materialOrder/manage/init/shopView.jhtml?orderNo='+orderNo+'&type='+type+ '&t=' + Math.random();
			$.ajax({
				type : 'post',
				url : url,
				data :[orderNo,type],
				dataType : 'json',
				success : function(data) {
					if(data.data.type == '001'){
						$("#group").html(data.data.group);
						$("#num").html(data.data.nums+"份");
						$("#price").html("￥"+data.data.amount+"(含运费"+data.data.freight+"元)");
						$("#title").html(data.data.title);
						$("#createTime").html(data.data.createTime);
						
						var html=[];
						
						for(var i=0;i<data.data.mgroupSize;i++){
							for(var j=0;j<data.data.mgroup[i].picSize;j++){
								
								if(data.data.mgroup[i].pics[j].type=='001'){
									html.push("<li><img width='90px' src='"+imgRoot+data.data.mgroup[i].pics[j].picUrl+"'/></li>");
								}
							}
						}
					
						$("#commonUrl").html(html);
						
						$(".shade-box,#modle1").show();
					}else{
						$("#budget").html(data.data.budget);
						$("#mainColor").html(data.data.mainColor);
						$("#secColor").html(data.data.secColor);
						$("#materialType").html(data.data.materialType);
						$("#finishTime").html(data.data.finishTime);
						$("#remarks").html(data.data.remark);
						
						var html=[];
						for(var i=0;i<data.data.urlSize;i++){
							html.push("<li><img width='90px' src='"+imgRoot+data.data.customizeUrls[i]+"' /></li>");
						}
						$("#customizeUrls").html(html);
						
						$(".shade-box,#modle2").show();
					}
					
					 //点击关闭遮罩层
				    $(".close-shade").on("click",function () {
				        $(".shade-box,.shade-content").hide();
				    });
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#prompt').hide();
					$('#triggerModal').modal('hide');
				}
			});
}
