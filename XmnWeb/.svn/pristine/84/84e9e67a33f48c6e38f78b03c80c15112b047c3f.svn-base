var appVersionList;
$(document).ready(function() {
	inserTitle(' > 系统管理 > <a href="common/appVersion/init.jhtml" target="right">版本管理</a>','userSpan',true);
	appVersionList = $('#appVersionList').page({
		url : 'common/appVersion/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(appVersionList.getIds());
	});
	
	$('.form-datetime').datetimepicker({
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				minView :2,
				maxView :3,
				autoclose: true,
				forceParse : 0,
				showMeridian : 1,
				format : 'yyyy-mm-dd'
		});
	
	//营业执照正面
	$("#urldivs").uploadFile({
		urlId : "urlids"
	});

});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var html = [];
	var Ispermissions=isEmptyObject(permissions);
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">版本</caption>');
	html.push('<thead>');
	html.push('<tr>');
	if(!Ispermissions){
		if(null != data &&data.content.length!=0)
		/*html.push('<th style="width:20px;"><input type="checkbox" /></th>');*/
		html.push('<th style="width:80px;">操作</th>');
	}
	

	
	/*html.push('<th style="width:100px;">id</th>');*/
	html.push('<th style="width:100px;">应用类型</th>');//1=经销商版|2=商户版|3=用户版|4=商户版(演示)|5=用户版(演示)|6=其他
	html.push('<th style="width:100px;">版本类型</th>');//1=Android|2=Ios|3=Wp|4=其他
	html.push('<th style="width:100px;">更新版本号</th>');
	html.push('<th style="width:100px;">内部版本号</th>');
	html.push('<th style="width:100px;">版本状态</th>');//1=已发布|2=停止更新
	html.push('<th style="width:100px;">URL地址</th>');
	html.push('<th style="width:100px;">激活数量</th>');
	html.push('<th style="width:100px;">发布时间</th>');
	html.push('<th style="width:100px;">更新内容</th>');	
	html.push('<th style="width:100px;">强制升级</th>');	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if(null != data &&data.content.length!=0){
		for (var i = 0; i < data.content.length; i++) {
			//1=经销商版|2=商户版|3=用户版|4=商户版(演示)|5=用户版(演示)|6=其他
			var apptype=data.content[i].apptype;
			var apptype1="";
			if(apptype==1){apptype1="经销商版";
			}else if(apptype==2){apptype1="商户版";
			}else if(apptype==3){apptype1="用户版";
			}else if(apptype==4){apptype1="商户版(演示)";
			}else if(apptype==5){apptype1="用户版(演示)";
			}else if(apptype==6){apptype1="其他";
			}else{apptype1="-";
			}
			
			//1=Android|2=Ios|3=Wp|4=其他
			var vtype= data.content[i].vtype;
			var vtype1="";
			if(vtype==1){vtype1="Android";
			}else if(vtype==2){vtype1="Ios";
			}else if(vtype==3){vtype1="Wp";
			}else if(vtype==4){vtype1="其他";
			}else{vtype1="-";}
			
			//1=已发布|2=停止更新
			var status= data.content[i].status;
			var status1="";
			if(status==1){status1="已发布";
			}else if(status==2){status1="停止更新";
			}else{status1="-";}
			
			//拼接地址
			var ips=$("#fastfdsHttp").val();
			var urls=data.content[i].url;
			var ipurl = vtype==2?urls:(ips+urls);
			
			html.push('<tr>');
				if(!Ispermissions){
					/*html.push('<th><input type="checkbox" val=' + data.content[i].id + '  /></th>');*/
						html.push('<td>');
						if(permissions.xg=='true'){
							html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="common/appVersion/update/init.jhtml?id='+data.content[i].id+'"  data-toggle="modal" >修改</a>');
						}
					/*	if(permissions.sc=='true'){
							html.push('<a href="javascript:remove('+data.content[i].id+')">删除</a>');
						}*/
					html.push('</td>');
				}
			
			/*html.push('<td>' + (undefined == data.content[i].id ? "-" : data.content[i].id) + '</td>');*/
			 
			html.push('<td>' + (apptype1) + '</td>');
			html.push('<td>' + (vtype1) + '</td>');
			html.push('<td>' + (undefined == data.content[i].version ? "-" : data.content[i].version) + '</td>');
			html.push('<td>' + (undefined == data.content[i].inside ? "-" : data.content[i].inside) + '</td>');
			html.push('<td>' + (status1) + '</td>');
			html.push('<td>' + (undefined == data.content[i].url ? "-" :  '<a  href='+ipurl +' target=_blank>'+urls+'</a>') + '</td>');
			html.push('<td>' + (undefined == data.content[i].activeNo ? "-" : data.content[i].activeNo) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) + '</td>');
			html.push('<td>' + (0 == data.content[i].mustUpdate ? "否":"是") + '</td>');
			
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 删除
 */
/*function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'common/appVersion/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					appVersionList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}*/

