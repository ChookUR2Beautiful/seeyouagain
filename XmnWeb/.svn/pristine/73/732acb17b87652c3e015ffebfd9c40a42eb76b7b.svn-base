 var commentList;
 $(document).ready(function() {
	
	// 返回
	 $("#backId").on("click",function(){
		 muBack();
	 });
	
	 commentList = $('#topicCommentList').page({
			url : 'user_terminal/topic/init/commentList.jhtml',
			success : success,
			pageBtnNum : 10,
			param : {topicId : $('#topicId').val(),type:$('#commentType').val()},
			
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
	 var tpl = "<tr><td style='width:70px;'>{nname}</td><td style='width:300px;'>{content}</td><td style='width:180px;'>{time}</td></tr>"
		 $('#ensure').click(function(){
			 	var topicComment = $('#topicComment').val();
			 	var comment = $.trim(topicComment);
				
				if(comment!=null && comment != ""){
			 		$.ajax({
						type : 'post',
						url : 'user_terminal/topic/check/addComment.jhtml' + '?t=' + Math.random(),
						data : jsonFromt($('#checkTopicForm').serializeArray()),
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								var tpltemp =tpl;
								var param = data.data;
								for(var key in param){
									var replaceEment ="{"+key+"}";
									tpltemp = tpltemp.replace(replaceEment,param[key]);
								}
								$("#commentInfo").append(tpltemp);
								$("#topicComment").val("");
								showSmReslutWindow(data.success, "回复成功！");
								commentList.reload();
							}
						}
			 		});
				 }else{
					 alert("回复内容不能为空！");
				 }
					
			 });
	 inserTitle(' > 用户端管理 > <a href="user_terminal/topic/init.jhtml" target="right">成长记列表</a> > <a href="user_terminal/topic/check/init.jhtml?id='+ $("#topicId").val() +'" target="right">查看话题</a>','userSpan',true);
 });
				 
/* 				 
	------------------------------以下是js调用的方法---------------------------------------------------------------			
 */				 
/*
 * 	返回
 */
function muBack(){
	var url = contextPath + '/user_terminal/topic/init.jhtml';
						location.href =url;
				}
 				
/*
         删除评论
*/
/*
 * 删除回复
*/

function removeComm(id) {
		showSmConfirmWindow(function() {
			$.ajax({
				type : 'post',
				url : 'user_terminal/topic/deleteComment.jhtml' + '?t='
						+ Math.random(),
				data : 'commentId=' + id,
				dataType : 'json',
				success : function(data) {
					showSmReslutWindow(data.success, "删除成功！");
					commentList.reload();
					//commentList.reload();
					//showSmReslutWindow(data.success, "删除成功！");
					
				},
			});

		});
	}
function isShow(id,status) {
		$.ajax({
			type : 'post',
			url : 'user_terminal/topic/deleteComment/isShowComm.jhtml' + '?t='
					+ Math.random(),
			data : {"commentId":id,"status":status},
			dataType : 'json',
			success : function(data) {
				commentList.reload();
				//commentList.reload();
				showSmReslutWindow(true, "操作成功！");
				
			},
		});
}

/*
 *  分页显示数据
  */
 function success(data, obj) {
	 var html = [];
		html.push('<table class="table table-hover table-bordered table-striped info" >');
		html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">评论列表</caption>');
		html.push('<thead>');
		html.push('<tr>');
		html.push('<th style="width:8%; ">评论者昵称</th>');
		html.push('<th style="width:45%; ">评论内容</th>');
		html.push('<th style="width:15%; ">评论时间</th>');
		html.push('<th style="width:10%; ">评论是否显示</th>');
		if(!isEmptyObject(permissions)){
			html.push('<th style="width:13% ;">操作</th>');
			
		}
		html.push('</tr>');
		html.push('</thead>');
		html.push('<tbody');
		if(null != data && data.content.length > 0)
		{
			for (var i = 0; i < data.content.length; i++) {
				var status = null;
				if(data.content[i].status==1){
					status = "显示";
				}
				if(data.content[i].status==0){
					status = "不显示";
				}
				html.push('<tr>');
				html.push('<td>' + (undefined == data.content[i].nname ? "-" : data.content[i].nname)+ '</td>')
				html.push('<td>' + (undefined == data.content[i].content ? "-" : data.content[i].content) +'</td>');
				html.push('<td>' + (undefined == data.content[i].time ? "-" : data.content[i].time) +'</td>');
				html.push('<td>' + (undefined == status ? "-" : status) +'</td>');
				if(!isEmptyObject(permissions)){
					var isShowStatus = null;
					isShowStatus = data.content[i].status;
					html.push('<td>');
					html.push('<input type="hidden" id = "commentId" val=' + data.content[i].id + '/>');
					if (permissions.delComm == 'true')
 					{
/*						html.push('<a href="javascript:removeComm('	+ data.content[i].id + ')">删除</a>&nbsp;&nbsp;');
*/						html.push('<a href="javascript:isShow('	+ data.content[i].id +","+ data.content[i].status+ ')" >'+(isShowStatus==0 ? '显示' : '不显示')+'</a>&nbsp;&nbsp;');
 					}	
					if(permissions.reply == 'true')
					{
						html.push('<a href="javascript:" data-type="ajax" data-width="950px"  data-url="user_terminal/topic/reply/init.jhtml?pid='+data.content[i].id +'&replyDetail=0"  data-toggle="modal" >回复</a>&nbsp;&nbsp;');
						html.push('<a href="javascript:" data-type="ajax" data-width="950px"  data-url="user_terminal/topic/reply/init.jhtml?pid='+data.content[i].id +'&replyDetail=1"  data-toggle="modal" >查看</a>&nbsp;&nbsp;');
						
					}
						
					html.push('</td>');
					
				}
				
				html.push('</tr>');
			}
		}
		else
		{
			html.push('<tr>');
			html.push('<td colspan="20" align="center"">暂无数据</td>');
			html.push('</tr>');
		}
		html.push('</tbody>');
		html.push('</table>');
		obj.find('div').eq(0).html(html.join(''));
	
}
