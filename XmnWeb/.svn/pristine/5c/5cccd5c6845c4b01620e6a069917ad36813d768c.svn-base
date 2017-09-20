var anchorList;
var initListUrl = "bursRelationChain/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="bursRelationChain/manage/init.jhtml" target="right">关系链管理</a>',
			'userSpan', true);
	anchorList = $("#anchorList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#exportAnchor").click(function(){
		var formId="searchForm";
		var url="bursRelationChain/manage/init/getCurrentSize.jhtml";
		var size=getCurrentSize(formId,url);
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}
		var path="bursRelationChain/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	
	
});

function success(data, obj) {
	var formId = "shareForm", title = "关系链列表", subtitle = "条记录。";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
//		checkable : false,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "bindSuperiorInfo"],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [     
				{
					title : "绑定上级",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "bursRelationChain/manage/bindSuperiorInfo/init.jhtml",
//							url : "liveMember/manage/bindSuperiorInfo/init.jhtml",
							position : "",
							width : "auto",
							title : "绑定上级信息"
						},
						param : [ "id" ],
						permission : "bindSuperiorInfo"
					}
				}  
			]
		},
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 120
		},{
			title : "渠道类型",
			name : "objectOrientedStr",
			type : "string",
			width : 120,
			customMethod:function(value,data){
				var result="";
				if(value){
					result=value.replace(/,/g,"<br/>");//替换所有逗号
				}
				return result;
			}
		},{
			title : "会员昵称",
			name : "nname",
			type : "string",
			width : 120
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 120
		},{
			title : "上级",
			name : "superiorName",
			type : "string",
			width : 120,
			customMethod : function(value,data){
//				debugger;
				var result="";
				if(data.objectOriented ==2 && data.uidRelationChainLevel ==1){
					result=data.sellerInfo;
				}else{
					result=data.superiorInfo;
				}
				return result;
			}
		},{
			title : "间接上级",
			name : "indirectUidAndName",
			type : "string",
			width : 120
		},{
			title : "会员层级",
			name : "uidRelationChainLevel",
			type : "string",
			width : 120
		}  ]
	}, permissions);
}
