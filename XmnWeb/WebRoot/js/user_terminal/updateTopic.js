var picNum = $("#topicPicsNum").val() | 0;
var commentList;

$(document).ready(
		function() {
			/* 将修改页面中的评论列表移除，功能添加到查看页面
			 * 1.删除列表展示，去除获取评论列表js代码
			 * 2.删除评论js代码从本文件中删除
			 * 3.查看回复功能js代码从本文件中删除
			if ($('#isType').val() == 'update') {
				$("#commentlist").show();
			}

			commentList = $('#topicCommentList').page({
				url : 'user_terminal/topic/init/commentList.jhtml',
				success : success,
				pageBtnNum : 10,
				param : {
					topicId : $('#topic_Id').val(),
					type : 0
				},
				paramForm : 'searchForm'
			});
			*/

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

			// 返回
			$("#backId").on("click", function() {
				muBack();
			});
			
			startValidate();
			$("#addPic").click(
					function() {
						if ($("#topicPics>div").length > 8) {
							showWarningWindow("warning", "最多只能添加9张图片！");
						} else {

							var temp = $("#topicPicTemp").clone(true)
									.removeAttr("id").show();
							temp.find("input[name='topicImg']").attr("name",
									"topicImgList[" + picNum + "].img").attr(
									"id", "img" + picNum);
							temp.find("input[name='topicId']").attr("name",
									"topicImgList[" + picNum + "].topicId");
							temp.find("input[name='imgId']").attr("name",
									"topicImgList[" + picNum + "].id");
							temp.find(".pic").uploadImg({
								size : 150,
								urlId : "img" + picNum
							});
							temp.find(".removebtn").click(function() {
								$(this).parent().remove();
							});

							$("#topicPics").append(temp);

							picNum++;

						}
					});

			/**
			 * 返回
			 */
			function muBack() {
				var url = contextPath + '/user_terminal/topic/init.jhtml';
				location.href = url;
			}

			//图片初始化
			$("#topicPics>div").each(function(i, n) {
				$(this).find(".pic").uploadImg({
					size : 150,
					urlId : "topicImg" + i
				});
				$(this).find(".removebtn").click(function() {
					$(this).parent().remove();
				});
			});
			if( $('#isType').val() == "add"){
				inserTitle(' > 用户端管理 > <a href="user_terminal/topic/init.jhtml" target="right">成长记列表</a> > <a href="user_terminal/topic/init.jhtml" target="right">新增成长记</a>','userSpan',true);
			}
			if( $('#isType').val() == "update"){
				inserTitle(' > 用户端管理 > <a href="user_terminal/topic/init.jhtml" target="right">成长记列表</a> > <a href="user_terminal/topic/update/init.jhtml?id='+ $("#topic_Id").val() +'" target="right">修改成长记</a>','userSpan',true);
			}
		});

/*------------------------分隔线-------------------------------------- 	 */
function startValidate() {
	validate("editTopicForm", {
		rules : {
			"topic.content" : {
				required : true
			}
		},
		messages : {
			"topic.content" : {
				required : "话题内容不为空！"
			}
		}
	}, formAjax);
}

function formAjax() {
	var url;
	var data;
	data = jsonFromt($('#editTopicForm').serializeArray());
	var topiccontent = $('#topicContent').val();
	;

	if (topiccontent != "" && topiccontent != null) {
		if ($('#isType').val() == 'add') {
			url = 'user_terminal/topic/add.jhtml' + '?t=' + Math.random();
		} else {
			url = 'user_terminal/topic/update.jhtml' + '?t=' + Math.random();
		}

		$.ajax({
			type : 'post',
			url : url,
			data : data,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
				if (data.success) {
					/* if($('#isType').val() ==  'add'){
						topicList.reset();
					}else{
						topicList.reload();
					} */
					showSmReslutWindow(data.success, data.msg);
					var url = contextPath + '/user_terminal/topic/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);

				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	} else {
		alert("话题内容不能为空，发表失败！");
	}

}
/*
	删除评论
 */
/*
function removeComm(id) {
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'user_terminal/topic/deleteComment.jhtml' + '?t='
					+ Math.random(),
			data : 'commentId=' + id,
			dataType : 'json',
			success : function(data) {
				 console.info($("#comment-info"));
				console.info($("#comment-info").find("#tr-"+id)); 
				 $("#comment-info").find("#tr-"+id).remove(); 
				//topicCommentList.reload();
				commentList.reload();
			},
		});

	});
	
}
//评论列表分页
function success(data, obj) {
	var html = [];
	html.push('<table class="table table-hover table-bordered table-striped info" id="comment-info">');
	html.push('<caption style="background:#EED8D8; text-align:center; color:#703636; font-size:16px; line-height:40px;">评论列表</caption>');
	html.push('<thead>');
	html.push('<tr>');
	html.push('<th style="width:80px;">评论者昵称</th>');
	html.push('<th style="width:300px;">评论内容</th>');
	html.push('<th style="width:150px;">评论时间</th>');
	if (permissions.delComm == 'true') {
		html.push('<th style="width:80px;">操作</th>');
	}
	html.push('</tr>');
	html.push('</thead>');
	html.push('<tbody');
	if (null != data && data.content.length > 0) {
		for (var i = 0; i < data.content.length; i++) {

			html.push('<tr>');
			//话题内容  话题创建时间  话题状态  评论数量  点赞数量  排序数
			//html.push('<th><input type="hidden" val=' + data.content[i].id + '/></th>');

			//话题内容  话题创建时间  话题状态  评论数量  点赞数量  排序数

			html.push('<td>'
					+ (undefined == data.content[i].nname ? "-"
							: data.content[i].nname) + '</td>')
			html.push('<td>'
					+ (undefined == data.content[i].content ? "-"
							: data.content[i].content) + '</td>');
			html.push('<td>'
					+ (undefined == data.content[i].time ? "-"
							: data.content[i].time) + '</td>');
			if (permissions.delComm == 'true') {
				html.push('<td>');
				html.push('<input type="hidden" id = "commentId" val='
						+ data.content[i].id
						+ '/><a href="javascript:removeComm('
						+ data.content[i].id + ')">删除</a>&nbsp;&nbsp;');
				html
						.push('<a  data-type="ajax" data-width="950px"  data-url="user_terminal/topic/reply/init.jhtml?pid='
								+ data.content[i].id
								+ '&replyDetail=2"  data-toggle="modal" >查看回复</a>&nbsp;&nbsp;');
				html.push('</td>');

			}
			html.push('</tr>');
		}
	} else {
		html.push('<tr>');
		html.push('<td colspan="20" align="center"">暂无数据</td>');
		html.push('</tr>');
	}
	html.push('</tbody>');
	html.push('</table>');
	obj.find('div').eq(0).html(html.join(''));
}

*/