var pageDiv;
$(document).ready(function() {
	if(type==1){
		window.parent.searchChosen = window.parent.includeSellerids;
	}
	if(type==2){
		window.parent.searchChosen = window.parent.excludeSellerids;
	}
	pageDiv = $('#sellerInfoDiv').page({
		url : $("#listUrl").val(),
		success : success,
		pageSize : 10,//每页条数
		pageBtnNum : 3,
		paramForm : 'sellerFromId'
	});
	
	//行业类别
	$("#tradeSelect").tradeLd({
		isChosen : false,
		showConfig : [{name:"category",tipTitle:"请选择"},{name:"genre",tipTitle:"请选择"}]
	});
	
	//区域
	var ld = $("#ld").areaLd({
		isChosen : false
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
	/**
	 * 重置
	 */
	$("input[data-bus=reset]").click(function(){
		$("#ld").find("select").trigger("chosen:updated");
	});	
});


function checkSome(obj){
		$("input:checkbox").each(function(){
			this.checked=obj.checked;
		});
	   $("input:checkbox").each(function(){
		  if(undefined!=$(this).attr("sellername")){
			   window.parent.searchChosen.chose($(this).attr("sellerid"),$(this).attr("sellername"),this);  
		   }
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
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">商家列表<font style="float:right;">共计【'+data.total+'】个商家&nbsp;</font></caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:6%;text-align:center;"><input type="checkbox" id="allCheck"  onclick="checkSome(this);" ></input></th>');
	html.push('<th style="width:10%;">商家编号</th>');
	html.push('<th style="width:18%;">商家名称</th>');
	html.push('<th style="width:10%;">商家等级</th>');
	html.push('<th style="width:10%;">商家手机号</th>');
	html.push('<th style="width:10%;">所属行业</th>');
	html.push('<th style="width:15%;">区域</th>');
	html.push('<th style="width:15%;">审核状态</th>');
	html.push('<th style="width:15%;">上线状态</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data && data.content.length > 0)
	{
			for (var i = 0; i < data.content.length; i++) {
				html.push('<tr>');
				html.push('<td style="text-align:center;">');
				html.push('<input  type="checkbox" items="oneCheck" name="'+data.content[i].sellerid+'"   sellerid="'+data.content[i].sellerid+'" sellername="'+data.content[i].sellername+'"  onclick=\'javascript:window.parent.searchChosen.chose("'+data.content[i].sellerid+'","'+data.content[i].sellername+'",this);\'></input>&nbsp;&nbsp;');
				html.push('</td>');
				
				html.push('<td>' + (undefined == data.content[i].sellerid ? "-" : data.content[i].sellerid) + '</td>');
				
				//商家名称
				var sellername = data.content[i].sellername?data.content[i].sellername:'-';
				html.push('<td class="text-ellipsis" name="sellername" initValue="'+sellername+'" title ="'+sellername+'">'+substr(sellername,10)+'</td>');
				//商家等级
				html.push('<td>' + (data.content[i].sellerGradeStr.length<1 ? "-" : data.content[i].sellerGradeStr) + '</td>');
				
				html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
				//所属行业
				var typeName =   (undefined == data.content[i].typename ? "" : data.content[i].typename) + "-" +  (undefined == data.content[i].tradename ? "" : data.content[i].tradename);
				html.push('<td class="text-ellipsis" title ="'+typeName+'">'+substr(typeName,6)+'</td>');
				//区域
				var title = data.content[i].ctitle + '-' + data.content[i].atitle;
				html.push('<td class="text-ellipsis" title ="'+title+'">'+ substr(title,10)+'</td>');
				
				//审核状态  默认  0未验证  1审核中  2未通过  3已签约  4未签约 5暂停合作
				var status="";
				var StrsSatus = data.content[i].status;
				if(StrsSatus==0){status="未验证";}
				else if(StrsSatus==1){status="审核中";}
				else if(StrsSatus==2){status="未通过";}
				else if(StrsSatus==3){status="已签约";}
				else if(StrsSatus==3){status="未签约";}
				else if(StrsSatus==3){status="暂停合作";}
				
				html.push('<td class="text-ellipsis" title ="'+status+'">'+ status +'</td>');
				
				//上线状态  0：未上线 1：已上线 2:预上线 3：已下线 默认为：0
				var isonline = "";
				var StrIsonline = data.content[i].isonline;
				if(StrIsonline==0){isonline="未上线";}
				else if(StrIsonline==1){isonline="已上线";}
				else if(StrIsonline==2){isonline="预上线";}
				else if(StrIsonline==3){isonline="已下线";}
				
				html.push('<td class="text-ellipsis" title ="'+isonline+'">'+ isonline +'</td>');
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
	window.parent.searchChosen.choseload();
	
}
    $("#allCancel").click(function(){
    	console.log(window.parent);
	  window.parent.searchChosen.allCancel();
	})
