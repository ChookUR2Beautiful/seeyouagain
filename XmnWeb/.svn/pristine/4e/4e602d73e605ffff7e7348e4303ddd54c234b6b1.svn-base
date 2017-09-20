var expertCommentList;
var sellerid = $("#sellerid").val();
var sellername = $("#sellername").val();
var imgRoot = $("#fastfdsHttp").val();
$(document).ready(function() {
	expertCommentList = $('#commentLabelList').page({
	        url : 'businessman/expert/sellerlabel/list.jhtml',
	        success : success,
	        pageBtnNum :10,
	        pageSize:15,
	        paramForm : 'searchForm'
	    });
	 inserTitle(' > 商家管理 > <a href="businessman/seller/init.jhtml" target="right">商家信息管理</a> > <a href="businessman/expert/init.jhtml?sellerid='+sellerid+'&sellername='+sellername+'" target="right">达人评论管理</a> > <a href="businessman/expert/sellerlabel/init.jhtml?sellerid='+sellerid+'&sellername='+sellername+'" target="right">商家标签管理</a>','userSpan',true);
//	 labelNum();//查询商家标签数
});
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;商家【'+sellername+'】共计【'+data.total+'】个标签记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&sellerid="+sellerid+"&sellername="+sellername+"&callbackParam="+getFormParam($("#searchForm").serialize());
	updateAddBtnHref("#addCommentLabel",callbackParam);
	
	obj.find('div').eq(0).scrollTablel({
    	callbackParam : callbackParam,
    	caption : captionInfo,
		//数据
		data:data.content, 
		 //数据行
		cols:[{
				title : "标签ID",
				name : "id",
			    width : 180,// 宽度
			    type:"string"//数据类型
		},{
			title : "商家ID",
			name : "sellerid",
			width : 180,// 宽度
			type:"string"//数据类型
//			customMethod : function(value, data) {
//				var sellername = $("#sellername").val();
//                if(null != sellername && '' != sellername){
//                 return sellername;
//                }else{
//                 return "-";	
//                }
//         }
		},{
			title : "标签名称",// 标题
			name : "labelname",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "点赞数",// 标题
			name : "znum",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "创建时间",// 标题
			name : "sdateStr",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "更新时间",// 标题
			name : "udateStr",
			width : 180,// 宽度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				handlePermissions : [ "del" ],// 需要用到checkbox
				queryPermission : ["update","del"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
							title : "修改",// 标题
							linkInfoName : "href",
							linkInfo : {
								href : "businessman/expert/sellerlabel/update/init.jhtml",// url
								param : ["id"],// 参数
								permission : "update"// 列权限
							}
				        },{					
						    title : "删除",// 标题
		                    linkInfoName : "href",
		                    linkInfo : {
		                        href : "businessman/expert/sellerlabel/delete.jhtml",// url
		                        param : ["id"],// 参数
		                        permission : "del"// 列权限
		                    },
		                    customMethod : function(value, data){
		                            var value1 = '<a href="javascript:remove('+data.id+')">删除</a>&nbsp;&nbsp;';//"<a href='javascript:remove(\""+data.id+"\")'>" + "删除" + "</a>";
		                            return value1;
		                    }
				      }]
	    }},permissions);
	}
/**
 * 删除商家标签
 * @param id
 */
function remove(id) {
	if(!id){
		showWarningWindow("warning","请至少选择一条记录！");
		return;
	}
	
	showSmConfirmWindow(function() {
		$.ajax({
			type : 'post',
			url : 'businessman/expert/sellerlabel/delete.jhtml',
			data : 'id=' + id,
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				$('#prompt').hide();

				if (data.success) {
					expertCommentList.reload();
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
 * 查询商家标签数
 */
//function labelNum(){
//	var url = "businessman/expert/init/labelnum.jhtml";
//	var paramdata = $('#searchForm').serializeArray();
//    //form转成json
//	paramdata = jsonFromt(paramdata);
//    //post提交请求
//    $.post(url, paramdata, function(data) {
//    	$("#lableCount").html(data.labelNum);
//    }, "json");
//}

