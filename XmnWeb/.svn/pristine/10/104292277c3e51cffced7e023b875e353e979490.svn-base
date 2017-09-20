var anchorList;
var initListUrl = "liveMember/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 打赏分红 > <a href="liveMember/manage/init.jhtml" target="right">直播会员管理</a>',
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
		var size=getCurrentDataSize();
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}
		
		var path="liveMember/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "searchForm";
	var title = "会员列表", subtitle = "个会员。（备注：该统计信息仅统计2017.01.05起的相关数据）";
	var uidViewJunior=$("#uidViewJunior").val();
	if(uidViewJunior){
		title="["+uidViewJunior+"]"+"下级会员列表，充值总金额"+"【"+data.titleInfo.juniorAmount+"】";
	}
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
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","detail","delete"],// 不需要选择checkbox处理的权限
			width : 250,// 宽度
			// 当前列的中元素
			cols : [     
                 {
					title : "钱包记录",// 标题
					linkInfoName : "href",
//						width : 30,
					linkInfo : {
						href : "liveMember/manage/viewLivePurseInfo.jhtml",
						param : ["uid"],
						permission : "purseInfo"
					}
				 },{
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveMember/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete(\""+data.id+"\",\""+data.uid+"\")'>" + "删除" + "</a>";
				            return value;
				    }
				 },{
					title : "查看下级",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveMember/manage/viewJuniorInfo.jhtml",
						param : ["uid"],
						permission : "viewJuniorInfo"
					},
				} ,
				{
					title : "修改",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveMember/manage/update/init.jhtml",
							position : "",
							width : "auto",
							title : "修改直播会员信息"
						},
						param : [ "id" ],
						permission : "update"
					}
				} ,
				/*{
					title : "绑定上级",// 标题
					linkInfoName : "modal",
					linkInfo : {
						modal : {
							url : "liveMember/manage/bindSuperiorInfo/init.jhtml",
							position : "",
							width : "auto",
							title : "绑定上级信息"
						},
						param : [ "id" ],
						permission : "bindSuperiorInfo"
					}
				} */ 
			]
		},
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 120
		},{
			title : "渠道类型",
			name : "userDescription",
			type : "string",
			width : 150,
			customMethod:function(value,data){
				var result="";
				if(value){
					result=value.replace(/,/g,"<br/>");//替换所有逗号
				}
				return result;
			}
		},{
			title : "会员昵称",
			name : "nickname",
			type : "string",
			width : 120
		},{
			title : "真实姓名",
			name : "name",
			type : "string",
			width : 120
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 120
		}, {
			title : "上上级",
			name : "topLevelStr",
			type : "string",
			width : 120
		},{
			title : "上级",
			name : "superiorStr",
			type : "string",
			width : 120
		},{
			title : "间接上级",
			name : "indirectUidAndName",
			type : "string",
			width : 150
		}, {
			title : "充值总金额",
			name : "orderAmount",
			type : "string",
			width : 120
		}, {
			title : "总打赏鸟豆",
			name : "comsumAmount",
			type : "string",
			width : 120
		}, {
			title : "会员红包奖励权限",
			name : "redPacketAuthorityStr",
			type : "string",
			width : 150
		}, {
			title : "鸟币消费余额限制",
			name : "restrictiveStr",
			type : "string",
			width : 150
		}, {
			title : "鸟币限制最低余额",
			name : "limitBalance",
			type : "string",
			width : 150
		}, {
			title : "鸟豆余额",
			name : "commision",
			type : "string",
			width : 200
		}, {
			title : "鸟币余额",
			name : "zbalance",
			type : "string",
			width : 200
		}, {
			title : "总可返打赏面额",
			name : "comsumLedger",
			type : "string",
			width : 150
		} , {
			title : "待返的奖励面额",
			name : "expectedPriviledgeLedger",
			type : "string",
			width : 200
		} , {
			title : "已返打赏面额",
			name : "currentConsumeLedger",
			type : "string",
			width : 200
		}, {
			title : "已返分红红包金额",
			name : "currentDividendLedger",
			type : "string",
			width : 200
		}, {
			title : "已返推荐面额",
			name : "currentRecommendLedger",
			type : "string",
			width : 200
		} /*, {
			title : "推荐人类型",
			name : "referrerType",
			type : "string",
			width : 150,
			customMethod : function(value, data) {
				var result='';
				switch (value) {
				case '001':
					result='企业推荐人';
					break;
				case '002':
					result='普通推荐人';
					break;
				default:
					result='-';
					break;
				}
				
				return result;
			}
		} , {
			title : "级别名称",
			name : "fansRankName",
			type : "string",
			width : 150
		}, {
			title : "企业级",
			name : "enterpriseNname",
			type : "string",
			width : 180
		}, {
			title : "上上级",
			name : "topLevelStr",
			type : "string",
			width : 120
		}*/ ]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(id,uid){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveMember/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id,uid:uid},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 anchorList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 /**
  * 获取当前查询记录数
  */
 function getCurrentDataSize(){
	 var formId = "searchForm";
	 var total=0;
	// 设置同步
    $.ajaxSetup({
        async: false
    });
	 
	 $.ajax({
		 url : "liveMember/manage/init/getCurrentDataSize.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 total=result;
		 }
	 });
	 
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
    
    return total;
 }