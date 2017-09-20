var subjectList;
var subjectReplyList;
var modalTrigger = null;
$(document).ready(function() {
	subjectList = $('#subjectList').page({
		url : 'business_cooperation/subject/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#delete').click(function() {
		remove(subjectList.getIds());
	});

	inserTitle(' > 合作商管理 > <a href="business_cooperation/subject/init.jhtml" target="right">话题交流管理</a>','userSpan',true);
	
	$("#export").click(function(){
		$form = $("#searchForm").attr("action","business_cooperation/subject/export.jhtml");
		$form[0].submit();
	});
	
	$('.form_datetime').datetimepicker({
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		forceParse : 0,
		showMeridian : 1,
		format : 'yyyy-mm-dd hh:ii'
	});
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个话题&nbsp;</font></caption>';
	

	obj.find('div').eq(0).scrollTablel({
	caption : captionInfo,
	// 数据
	data : data.content,
	// 数据行
	cols : [ {
		title : "员工姓名",// 标题
		name : "fullname",
		width : 180,// 宽度
		type : "string",// 数据类型
		isLink : true,// 表示当前列是否是超链接 true:是 false：不是
		// 只有当isLink为true时 才有效
		link : {
			required : true,
			linkInfoName : "method", // href , modal ,method
			linkInfo : {
				method : "userSubject"
			},
			param : ["staffid"]// 参数
		}
	}, {
		title : "手机号",// 标题
		name : "phoneid",
		width : 180,// 宽度
		type : "string"// 数据类型
	}, {
		title : "区域",// 标题
		name : "area",
		width : 200,// 宽度
		type : "string"
	}, {
		title : "话题内容",// 标题
		name : "content",
		width : 380,// 宽度
		type : "string"// 数据类型
	}, {
		title : "回复数量",// 标题
		name : "number",
		width : 80,// 宽度
		type : "string"// 数据类型
	}, {
		title : "提交时间",// 标题
		name : "sdate",
		width : 180,// 宽度
		type : "string"// 数据类型
	}, {
		title : "备注",// 标题
		name : "remarks",
		width : 200,// 宽度
		type : "string"// 数据类型
	}],
	// 操作列
	handleCols : {
		title : "操作",// 标题
		queryPermission : [ "htjlxq" ],// 不需要选择checkbox处理的权限
		width : 150,// 宽度
		// 当前列的中元素
		cols : [ {
			title : "查看",// 标题
			linkInfoName : "method",
			linkInfo : {
				method : "openSubject",
				param : [ "subjectid" ],// 参数
				permission : "htjlxq"// 列权限
			}
		} ]
	}
}, permissions);
	
	
	
	
	/*var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">话题</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var b =permissions.htjlxq=='';
	if(!b){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	
	html.push('<th style="width:100px;">员工姓名</th>');
	html.push('<th style="width:80px;">手机号</th>');
	html.push('<th style="width:80px;">区域</th>');
	html.push('<th style="width:100px;">话题内容</th>');
	html.push('<th style="width:100px;">回复数量</th>');
	html.push('<th style="width:100px;">提交时间</th>');
	html.push('<th style="width:100px;">备注</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>')
	if(null != data && data.content.length > 0){;
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(!b){
			 html.push('<th><input type="checkbox" val=' + data.content[i].subjectid + '  /></th>');
			 if(permissions.htjlxq=='true'){
			 	html.push('<td><a href="javascript:" onclick="openSubject('+data.content[i].subjectid+');">查看</a></td>');
			 }
			 if(permissions.htjlsc=='true'){
				 html.push('<a href="javascript:remove('+data.content[i].subjectid+')">删除</a>&nbsp;&nbsp;');
			 }
			}
			html.push('<td>' + (undefined == data.content[i].fullname ? "-" : '<a href="javascript:" onclick="userSubject('+data.content[i].staffid+');">' +data.content[i].fullname) + '</a></td>');
			html.push('<td>' + (undefined == data.content[i].phoneid ? "-" : data.content[i].phoneid) + '</td>');
			html.push('<td>' + (undefined == data.content[i].area ? "-" : data.content[i].area) + '</td>');
			var content = (undefined == data.content[i].content ? "-" : data.content[i].content);
			html.push('<td title="'+content+'">' + substr(content,20) + '</td>');
			html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('<td>' + (undefined == data.content[i].remarks ? "-" : data.content[i].remarks) + '</td>');
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="100">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));*/
}

/**
 * 删除
 */
function remove(subjectid) {
	if(!subjectid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/subject/delete.jhtml' + '?t=' + Math.random(),
			data : 'subjectid=' + subjectid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					subjectList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

function openSubject(subjectid){
	modalTrigger = new ModalTrigger({
		type : "ajax",
		url : "business_cooperation/subject/view/init.jhtml?subjectid=" + subjectid,
		width : 1024,
		toggle : "modal"
	});
	modalTrigger.show();
}


function userSubject(staffid1){
	subjectList = $('#subjectList').page({
		url : 'business_cooperation/subject/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		param : {staffid : staffid1},
		paramForm : 'searchForm'
	});
	if(modalTrigger){
		modalTrigger.close();
	}
	subjectReplyList = null;
}
