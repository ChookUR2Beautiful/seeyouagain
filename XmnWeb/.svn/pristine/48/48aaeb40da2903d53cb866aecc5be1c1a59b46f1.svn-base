var userProposalList;
var imgRoot = $("#fastfdsHttp").val();

$(document).ready(function() {
	
	userProposalList = $('#userProposalList').page({
		url : 'user_terminal/userProposal/init/list.jhtml',
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	
	inserTitle(' > 用户端管理 > <a href="user_terminal/userProposal/init.jhtml" target="right">用户吐槽</a>','userSpan',true);

/*	$("#export").click(function(){
		$form = $("#searchForm").attr("action","user_terminal/advertising/export.jhtml");
		$form[0].submit();
	});*/
	// 区域


});
function success(data, obj) {
	var formId = "searchForm",title = "用户吐槽列表",subtitle="个用户吐槽";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'+title+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'+data.total+'】'+subtitle+'&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#"+formId).serialize());
	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data:data.content, 
		caption : captionInfo,
		//操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : ["update"],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			cols : [{
				title : "处理",// 标题
				linkInfoName : "modal",
				linkInfo : {
					modal : {
						url : "user_terminal/userProposal/update/init.jhtml",// url
						position : "",// 模态框显示位置
						width : "30%", // 模态框宽度
						title : "用户吐槽处理" //模态框标题
					},
					param : ["id","phoneid"],
					permission : "update"
				},
				customMethod : function(value, data) {
					var status=data.status;
					if(status==0){
						return value;
					}else{
						return '--';
					}
					
				}
			},] 
		},
		cols:[{
			title : "日期",
			name : "dateCreated",
			type : "string",
			width : 150
		},
		{
			title : "会员手机号/账号",
			name : "phoneid",
			type : "string",
			width : 150
		},{
			title : "吐槽对象",
			name : "objectText",
			type : "string",
			width : 150
		},{
			title : "吐槽内容",
			name : "content",
			type : "string",
			width : 300
		},{
			title : "处理状态",
			name : "statusText",
			type : "number",
			width : 180
		},
		{
			title : "类型",
			name : "typeText",
			type : "string",
			width : 150
		},{
			title : "处理结果",
			name : "result",
			type : "string",
			width : 300
		}]},permissions);
}




/**
 * 删除
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/advertising/delete.jhtml' + '?t=' + Math.random(),
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					userProposalList.reload();
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}

