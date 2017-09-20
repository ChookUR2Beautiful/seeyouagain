var divideList;
var withdrawList;
var hszList;
var modalTrigger = null;
$(document).ready(function() {
	
	if(permissions.tzlb=='true'){
		//帖子列表
	 divideList = $('#divide').page({
		url : 'user_terminal/tPost/post/init.jhtml',
		success : divideSuccess,
		pageBtnNum : 10,
		paramForm : 'divideForm'
	});
	}
	
	if(permissions.jblb=='true'){
			//举报列表
		withdrawList = $('#withdraw').page({
			url : 'user_terminal/tPost/report/init.jhtml',
			success : withdrawSuccess,
			pageBtnNum : 10,
			paramForm : 'withdrawForm'
		});
	}
	if(permissions.hszlb=='true'){
			//回收站
			 hszList = $('#hsz').page({
				url : 'user_terminal/tPost/recycle/init.jhtml',
				success : hszSuccess,
				pageBtnNum : 10,
				paramForm : 'hszForm'
			});
	}

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
	
	inserTitle(' > 用户端管理 > <a href="user_terminal/tPost/init.jhtml" target="right">寻蜜客圈子管理</a>','disputeOrder',true);
});

/*
 * 帖子列表
 */
function divideSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">帖子列表</caption>');

		html.push('<thead>');
		html.push('<tr>');
		var b = permissions.tzlbxq==''&&permissions.tzlbsc=='';
		if(!b){
			html.push('<th style="width:20px;"><input type="checkbox" /></th>');
			html.push('<th style="width:200px;">操作</th>');
		}
	html.push('<th >用户名(昵称)</th>');
	html.push('<th >帖子内容</th>');
	html.push('<th >评论数</th>');
	html.push('<th >点赞数</th>');
	html.push('<th >发布时间</th>');	
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
if(null != data && data.content.length > 0)
	{
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		if(!b){
		      html.push('<th><input type="checkbox" val="' + data.content[i].tid + '"/></th>');
			 html.push("<td>");
			if(permissions.tzlbxq=='true'){
				 html.push('<a href="javascript:" onclick="openTpost('+data.content[i].tid+","+1+');">查看</a>&nbsp;&nbsp;');
		    }
		    if(permissions.tzlbsc=='true'){
				 html.push('<a href="javascript:remove('+data.content[i].tid+')">删除</a>');
		    }
		    html.push("</td>");
		    
		/*	 html.push('<td><a href="javascript:" onclick="openTpost('+data.content[i].tid+","+1+');">详情</a>' +
					'&nbsp;&nbsp;<a href="javascript:remove('+data.content[i].tid+')">删除</a></td>');		*/					
			
		}
		
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
			var contents=(undefined == data.content[i].content ? "-" : data.content[i].content) ;
		html.push('<td title ="'+contents+'">' + substr(contents) + '</td>');
		html.push('<td>' + (undefined == data.content[i].count ? "-" : data.content[i].count) + '</td>');
		html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');		
		html.push('</tr>');
	 }
	}else
	{
		html.push('<tr>');
		html.push('<td colspan="20">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

//删除（修改帖子列表状态0——》1）
 $('#updates').click(function() {	
		remove(divideList.getIds());
 });

/**
 * 删除（批量更改状态0——》1）
 */
function remove(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}	
	showSmConfirmWindow(function() {					
			var  data = tid;			
			var arryIds = {'tids' : data,'status' : 1};
			$.ajax({
		        type: "POST",
		        url: "user_terminal/tPost/post/updatePostStatus.jhtml",
		        data: arryIds,
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	divideList.reload();
		        	hszList.reload();		        	
		        	withdrawList.reload();
		         }
		    });
		},"你确定要删除？");
}

//根据tid查找（查看详情）
function openTpost(tid,type){
	var urls="";	
	if(type==1)     {urls="user_terminal/tPost/tPostComment/post/init.jhtml?tid=";}
	else if(type==2){urls="user_terminal/tPost/tPostComment/report/init.jhtml?tid=";}
	else if(type==3){urls="user_terminal/tPost/tPostComment/recycle/init.jhtml?tid=";}	
	modalTrigger = new ModalTrigger({
		type : "ajax",
		//url : "user_terminal/tPost/getPostBytid/init.jhtml?tid=" + tid,
		url:urls+ tid,
		width : 1024,
		toggle : "modal"
	});
	modalTrigger.show();
}

//==========================================举报列表================================================
/*
 * 举报列表
 */
function withdrawSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">举报列表</caption>');
	
		html.push('<thead>');
		html.push('<tr>');
		var b = permissions.jblbxq==''&&permissions.jblbsc==''&&permissions.jblbdh=='';
		if(!b){
			html.push('<th style="width:20px;"><input type="checkbox" /></th>');
			html.push('<th style="width:200px;">操作</th>');
		}
		html.push('<th >用户名(昵称)</th>');
		html.push('<th >帖子内容</th>');
		html.push('<th >举报原因</th>');
		html.push('<th >发布时间</th>');	
		html.push('</tr>');
		html.push('</thead>');
		html.push('<tbody>');
if(null != data && data.content.length > 0)
	{		
		for (var i = 0; i < data.content.length; i++) {
			html.push('<tr>');
			if(!b){
				html.push('<th><input type="checkbox" val="' + data.content[i].tid + '"/></th>');
				html.push("<td>");
				if(permissions.jblbxq=='true'){
					html.push('<a href="javascript:" onclick="openTpost('+data.content[i].tid+","+2+');">查看</a>&nbsp;&nbsp;');
			      }
			    if(permissions.jblbsc=='true'){
					html.push('<a href="javascript:removejblb('+data.content[i].tid+')">删除</a>&nbsp;&nbsp;');
			      }
			    if(permissions.jblbdh=='true'){
					html.push('<a href="javascript:removejbhome('+data.content[i].tid+')">打回</a>');
			      }
			      html.push("</td>");
				/*html.push('<td><a href="javascript:" onclick="openTpost('+data.content[i].tid+","+2+');">详情</a>&nbsp;&nbsp;' +
							   '<a href="javascript:removejblb('+data.content[i].tid+')">删除</a>&nbsp;&nbsp;' +
						       '<a href="javascript:removejbhome('+data.content[i].tid+')">打回</a></td>');	*/									
			}
			
			html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
				var contentss=(undefined == data.content[i].content ? "-" : data.content[i].content);
			html.push('<td title ="'+contentss+'">' + substr(contentss)  + '</td>');
			html.push('<td>' + (undefined == data.content[i].reason ? "-" : data.content[i].reason) + '</td>');
			html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');		
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
}

//删除（修改帖子列表状态2——》1）
 $('#updatesOne').click(function() {	
		removejblb(withdrawList.getIds());
 });

/**
 * 删除（批量更改状态2——》1）
 */
function removejblb(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}	
	showSmConfirmWindow(function() {					
			var  data = tid;			
			var arryIds = {'tids' : data,'status' : 1};
			$.ajax({
		        type: "POST",
		        url: "user_terminal/tPost/report/updatePostStatusByjb.jhtml",
		        data: arryIds,
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	withdrawList.reload();
		        	hszList.reload();
		        	divideList.reload();		        	
		         }
		    });
		},"你确定要删除？");
}

//打回（修改帖子列表状态2——》0）
 $('#updatesZero').click(function() {	
		removejbhome(withdrawList.getIds());
 });

/**
 * 打回（更改状态2——》0）
 */
function removejbhome(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}	
	showSmConfirmWindow(function() {					
			var  data = tid;			
			var arryIds = {'tids' : data,'status' : 0};
			$.ajax({
		        type: "POST",
		        url: "user_terminal/tPost/report/updatePostStatusByHome.jhtml",
		        data: arryIds,
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	withdrawList.reload();
		        	hszList.reload();
		        	divideList.reload();		        	
		         }
		    });
		},"你确定要打回？");
}


//===========================================回收站================================================

function hszSuccess(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" >');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">回收站</caption>');
	
	html.push('<thead>');
	html.push('<tr>');
	var b = permissions.hszxq==''&&permissions.hszdh==''&&permissions.hszsc=='';
	if(!b){
		html.push('<th style="width:20px;"><input type="checkbox" /></th>');
		html.push('<th style="width:200px;">操作</th>');
	}
	html.push('<th >用户名(昵称)</th>');
	html.push('<th >帖子内容</th>');
	html.push('<th >评论数</th>');
	html.push('<th >点赞数</th>');	
	html.push('<th >发布时间</th>');
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody>');
if(null != data && data.content.length > 0)
	{	
	for (var i = 0; i < data.content.length; i++) {
		html.push('<tr>');
		
		if(!b){
			
			html.push('<th><input type="checkbox" val="' + data.content[i].tid + '"/></th>');	
			html.push("<td>");
		if(permissions.hszxq=='true'){
		    html.push('<a href="javascript:" onclick="openTpost('+data.content[i].tid+","+3+');">查看</a>&nbsp;&nbsp;');
		}
		if(permissions.hszsc=='true'){
			 html.push('<a href="javascript:removetrue('+data.content[i].tid+')">彻底删除</a>&nbsp;&nbsp;');
		}
		if(permissions.hszdh=='true'){
			 html.push('<a href="javascript:updateOne('+data.content[i].tid+')">恢复</a>');
		}
		html.push("</td>");
			/*html.push('<td><a href="javascript:" onclick="openTpost('+data.content[i].tid+","+3+');">详情</a>&nbsp;&nbsp;' +
						  '<a href="javascript:removetrue('+data.content[i].tid+')">彻底删除</a>&nbsp;&nbsp;' +
						  '<a href="javascript:updateOne('+data.content[i].tid+')">恢复</a></td>');	*/									
		}
		
		html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname) + '</td>');
		  var contentsss=(undefined == data.content[i].content ? "-" : data.content[i].content);
		html.push('<td title ="'+contentsss+'">' + substr(contentsss) + '</td>');
		html.push('<td>' + (undefined == data.content[i].count ? "-" : data.content[i].count) + '</td>');
		html.push('<td>' + (undefined == data.content[i].number ? "-" : data.content[i].number) + '</td>');
		html.push('<td>' + (undefined == data.content[i].sdate ? "-" : data.content[i].sdate) + '</td>');	
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
}

// 删除（彻底删除）
 $('#delete').click(function() {	
		removetrue(hszList.getIds());
 });

/**
 * 删除（彻底删除）
 */
function removetrue(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}	
	showSmConfirmWindow(function() {					
			var  data = tid;			
			var arryIds = {'tids' : tid};
			$.ajax({
		       /* type: "POST",
		        url: "user_terminal/tPost/delete.jhtml",
		        data: arryIds,*/
				type : 'post',
				url : 'user_terminal/tPost/recycle/delete.jhtml',
				data :  arryIds,
				beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	hszList.reload();		        	
		        	divideList.reload();
		        	withdrawList.reload();
		         }
		    });
		},"你确定要彻底删除？");
}


//恢复（恢复把状态改成0）
 $('#updateSzero').click(function() {	
		updateOne(hszList.getIds());
 });

/**
 * 恢复（恢复把状态改成0）
 */
function updateOne(tid) {
	if(!tid){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}	
	showSmConfirmWindow(function() {					
			var  data = tid;			
			var arryIds = {'tids' : data,'status' : 0};
			$.ajax({
		        type: "POST",
		        url: "user_terminal/tPost/recycle/updatePostStatusToOne.jhtml",
		        data: arryIds,
		        success: function(result){
		        	showSmReslutWindow(result.success, result.msg);
		        	hszList.reload();
		        	divideList.reload();
		        	withdrawList.reload();
		         }
		    });
		},"你确定要恢复？");
}

