var videoList;
var sellerAskList;
$(document).ready(function() {
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
	$('#videoInfoId').click(function(){
		videoReady();
		$('#videoId').show();
		$('#sellerAskId').hide();
		sellerAskList = "";
	});
	$('#sellerAskInfoId').click(function(){
		sellerAskReady();
		$('#videoId').hide();
		$('#sellerAskId').show();
		videoList ="";
	});
	$('#videoInfoId').trigger("click");
	
	$("#exportVideo").click(function(){
		$form = $("#searchForm").attr("action","business_cooperation/video/export.jhtml");
		$form[0].submit();
	});
	
	$("#exportAsk").click(function(){
		$form = $("#searchSellerAskForm").attr("action","business_cooperation/sellerAsk/export.jhtml");
		$form[0].submit();
	});
	
	inserTitle(' > 合作商管理 > <a href="business_cooperation/video/init.jhtml" target="right">产品介绍管理</a>','userSpan',true);

});
/**
 * 初始化视频信息
 */
function videoReady(){
	videoList = $('#videoList').page({
		url : 'business_cooperation/video/init/list.jhtml',
		success : successVideo,
		pageBtnNum : 10,
		paramForm : 'searchForm'
	});

	$('#deleteVideo').click(function() {
		removeVideo(videoList.getIds());
	});
}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successVideo(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个视频&nbsp;</font></caption>';
	

	obj.find('div').eq(0).scrollTablel({
	caption : captionInfo,
	// 数据
	data : data.content,
	// 数据行
	cols : [{
		title : "视频名称",// 标题
		name : "videoname",
		width : 180,// 宽度
		type : "string"// 数据类型
	}, {
		title : "视频格式",// 标题
		name : "format",
		width : 80,// 宽度
		type : "string"
	}, {
		title : "简介",// 标题
		name : "introduce",
		width : 380,// 宽度
		type : "string"// 数据类型
	}, {
		title : "时长",// 标题
		name : "duration",
		width : 80,// 宽度
		type : "string"// 数据类型
	}, {
		title : "状态",// 标题
		name : "status",
		width : 180,// 宽度
		type : "string",// 数据类型
		customMethod : function(v,d){
			var status = "-";
			if(v == 0)
			{
				status ="停用";
			}
			else if(v == 1)
			{
				status ="启用";
			}
			else
			{
				status = "-";
			}
			return status;
		}
	}, {
		title : "下载次数",// 标题
		name : "number",
		width : 200,// 宽度
		type : "string"// 数据类型
	}, {
		title : "视频大小",// 标题
		name : "size",
		width : 200,// 宽度
		type : "string"// 数据类型
	}],
	// 操作列
	handleCols : {
		title : "操作",// 标题
		queryPermission : ["updateVideo", "playVideo" ],// 不需要选择checkbox处理的权限
		width : 150,// 宽度
		// 当前列的中元素
		cols : [ {
			title : "修改",// 标题
			linkInfoName : "modal",
			linkInfo : {
				modal : {
					url : "business_cooperation/video/update/init.jhtml",
					position:"300px",// 模态框显示位置
					width:"600px"
				},
				param : [ "vid" ],// 参数
				permission : "updateVideo"// 列权限
			}
		},{
			title : "下载",// 标题
			linkInfoName : "href",
			linkInfo : {
				href : "-",
				permission : "playVideo"// 列权限
			},
			customMethod : function(v,d){
				var url = $("#fastfdsHttp").val()+d.vurl;
				return $(v).attr({"href":url,"target":"_blank"})[0].outerHTML;
			}
		}]
	}
}, permissions);
	
	
	
	/*var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">视频讲解</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandleVideo = permissions && (permissions.updateVideo  || permissions.playVideo);
	if(hasHandleVideo){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('<th style="width:150px;">视频名称</th>');
	html.push('<th style="width:80px;">视频格式</th>');
	html.push('<th style="width:200px;">简介</th>');
	html.push('<th style="width:100px;">时长</th>');
	html.push('<th style="width:80px;">状态</th>');
	html.push('<th style="width:80px;">下载次数</th>');
	html.push('<th style="width:80px;">视频大小</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandleVideo){
				html.push('<th><input type="checkbox" val=' + data.content[i].vid + '  /></th>');
				html.push('<td>');
				if(permissions.updateVideo){
					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="business_cooperation/video/update/init.jhtml?vid='+data.content[i].vid+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				if(permissions.delVideo){
					html.push('<a href="javascript:removeVideo('+data.content[i].vid+')">删除</a>&nbsp;&nbsp;');
				}
				if(permissions.playVideo){
//					html.push('<a href="javascript:viod(); " data-type="ajax"   data-url="business_cooperation/video/playVideo/init.jhtml?vurl='+data.content[i].vurl+'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
					html.push('<a href="'+$("#fastfdsHttp").val()+data.content[i].vurl+'" target="_blank">下载</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			var videoname = (undefined == data.content[i].videoname ? "-" : data.content[i].videoname);
			html.push('<td title="'+videoname+'">' + substr(videoname,16) + '</td>');
			html.push('<td>' + (undefined == data.content[i].format ? "-" : data.content[i].format) + '</td>');
			var introduce = (undefined == data.content[i].introduce ? "-" : data.content[i].introduce);
			html.push('<td title="'+introduce+'">' + substr(introduce,8) + '</td>');
			html.push('<td>' + (undefined == data.content[i].duration ? "-" : data.content[i].duration) + '</td>');
			var status = "-";
			if(data.content[i].status == 0)
			{
				status ="停用";
			}
			else if(data.content[i].status == 1)
			{
				status ="启用";
			}
			else
			{
				status = "-";
			}
			html.push('<td>' + status+ '</td>');
			html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
			html.push('<td>' + (undefined == data.content[i].size ? "-" : data.content[i].size) + '</td>');
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
function removeVideo(vid) {
	if(!vid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/video/delete.jhtml' + '?t=' + Math.random(),
			data : 'vid=' + vid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					videoList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


/**
 * 初始化商家问卷信息
 */
function sellerAskReady(){
	sellerAskList = $('#sellerAskList').page({
		url : 'business_cooperation/sellerAsk/init/list.jhtml',
		success : successSellerAsk,
		pageBtnNum : 10,
		paramForm : 'searchSellerAskForm'
	});

	$('#delete').click(function() {
		remove(sellerAskList.getIds());
	});
	
	

}

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function successSellerAsk(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info"  id = "sellerAskGridListId">');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">产品问答</caption>');
	html.push('<thead>');
	html.push('<tr>');
	var hasHandleAsk = permissions && (permissions.updateAsk || permissions.delAsk || permissions.viewAsk);
	if(hasHandleAsk){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('<th style="width:20%;">问题名称</th>');
	html.push('<th style="width:40%;">问题内容</th>');
	html.push('<th style="width:20%;">提问时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
	if(null != data && data.content.length > 0){
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(hasHandleAsk){
				html.push('<th><input type="checkbox" val=' + data.content[i].aid + '  /></th>');
				html.push('<td>');
				if(permissions.updateAsk){
					html.push('<a href="javascript: " data-type="ajax"   data-url="business_cooperation/sellerAsk/update/init.jhtml?aid='+data.content[i].aid+'"  data-toggle="modal" >修改</a>&nbsp;&nbsp;');
				}
				if(permissions.delAsk){
					html.push('<a href="javascript:remove('+data.content[i].aid+')">删除</a>&nbsp;&nbsp;');
				}
				if(permissions.viewAsk){
					html.push('<a href="javascript:" data-type="ajax" data-url="business_cooperation/sellerAsk/view/init.jhtml?aid='+data.content[i].aid+'"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
				}
				html.push('</td>');
			}
			var askname = (undefined == data.content[i].askname ? "-" : data.content[i].askname);
			html.push('<td title="'+askname+'">' + substr(askname,20) + '</td>');
			var content = (undefined == data.content[i].content ? "-" : data.content[i].content);
			html.push('<td title="'+content+'">' + substr(content,28) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');
			html.push('</tr>');
		}
	}else{
		html.push('<tr>');
		html.push('<td colspan="100">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
	/*var id = "sellerAskGridListId";
	subChar(id,20);*/
}

function subChar(gridId,length){
	var object = $("#"+gridId).find("td");
	$.each(object,function(i,o){
		var node = o.childNodes;
		if(node.length==1){
			if(node[0].data != "-"){
				if(node[0].data.length > length){
					node[0].data = node[0].data.substring(0,length) +"...";
				}
			}
		}
	});
}

/**
 * 删除
 */
function remove(aid) {
	if(!aid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'business_cooperation/sellerAsk/delete.jhtml' + '?t=' + Math.random(),
			data : 'aid=' + aid,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					sellerAskList.reload();
				}

				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
			}
		});
	});
}


