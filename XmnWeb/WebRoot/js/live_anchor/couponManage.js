var couponList;
var initListUrl = "liveCoupon/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 直播频道管理 > <a href="liveCoupon/manage/init.jhtml" target="right">直播粉丝券管理</a>',
			'userSpan', true);
	
	//加载页面数据
	pageInit();
	
	//初始化日期组件
	initDates();
	
	//上架
	putaway();
	
	//下架
	removeOffshelf();
	
	//设为推荐
	setRecommend();
	
	//取消推荐
	cancelRecommend();

});

/**
 * 加载页面数据
 */
function pageInit(){
	couponList = $("#couponList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
}

/**
 * 查询礼物成功回调函数
 * @param data
 * @param obj
 */
function success(data, obj) {
	var formId = "shareForm", title = "直播粉丝券", subtitle = "个粉丝券";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 150,// 宽度
			// 当前列的中元素
			cols : [ 
				
				 {
					title : "删除",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveCoupon/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "delete"// 列权限
					},
					customMethod : function(value, data){
				            var value = "<a href='javascript:confirmDelete("+data.cid+")'>" + "删除" + "</a>";
				            return value;
				    }
				 },
		         {
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "liveCoupon/manage/update/init.jhtml",
						param : [ "cid" ],
						permission : "update"
					}
				} 
				
			]
		},
		cols : [ {
			title : "券名",
			name : "cname",
			type : "string",
			width : 250
		}/*{
			title : "粉丝券编号",
			name : "cid",
			type : "string",
			width : 100
		}, {
			title : "使用状态",
			name : "useStatus",
			type : "string",
			width : 100,
			customMethod: function(value,data){
				switch (value) {
				case '001':
					value='未使用';
					break;
				default:
					value='使用中';
					break;
				}
				return value;
			}
		}*/, {
			title : "推荐",
			name : "isRecom",
			type : "string",
			width : 100,
			customMethod: function(value,data){
				switch (value) {
				case 1:
					value='推荐';
					break;
				default:
					value='未推荐';
					break;
				}
				return value;
			}
		}, {
			title : "状态",
			name : "status",
			type : "string",
			width : 100,
			customMethod: function(value,data){
				switch (value) {
				case 1:
					value='出售中';
					break;
				case 2:
					value='下架';
					break;
				case 3:
					value='售罄';
					break;
				default:
					value='出售中';
					break;
				}
				return value;
			}
		}, {
			title : "价格",
			name : "denomination",
			type : "string",
			width : 150
		}, {
			title : "赠送抵用券面值",
			name : "vouchers",
			type : "string",
			width : 180,
			customMethod : function(value, data) {
				if(undefined==data.vouchers){
					return "-";
				}else{
					var result;
					try{
						result=data.vouchers.split(';').join('<br>');
					}catch(e){
						console.log(e);
						result = "-";
					}
					return result;
				}
			},
		}, {
			title : "销售时间",
			name : "-",
			type : "string",
			width : 250,
			customMethod: function(value,data){
				var result='-';
				if(data.saleStartTimeStr){
					result=data.saleStartTimeStr;
				}
				if(data.saleEndTimeStr){
					result+= ' 至 '+data.saleEndTimeStr;
				}
				return result;
			}
		}, {
			title : "使用时间",
			name : "-",
			type : "string",
			width : 250,
			customMethod: function(value,data){
				var result='-';
				if(data.startDateStr){
					result=data.startDateStr;
				}
				if(data.endDateStr){
					result+= ' 至 '+data.endDateStr;
				}
				return result;
			}
		}
		]
	}, permissions);
}

/**
 * 删除操作
 */
 function confirmDelete(cid){
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "liveCoupon/manage/delete.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{"cid":cid},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 couponList.reload();
					 }, 2000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要删除吗？");
 }
 
 
 /**
  * 初始化日期控件
  */
 function initDates(){
 	//限定销售日期
 	limitedDate({
 		form:"#searchForm",
 		startDateName:"saleStartDate",
 		endDateName:"saleEndDate",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 	//限定使用日期
 	limitedDate({
 		form:"#searchForm",
 		startDateName:"startDate",
 		endDateName:"endDate",
 		overlap:true,
 		format : 'yyyy-mm-dd hh:ii',
 		minuteStep:1,
 		minView : 0,
 	});
 	
 }
 
 /**
  * 批量上线
  */	
 function putaway(){
 	$("#putaway").click(function(){
 		debugger;
 		console.log(couponList.getValue('cid'));
 		if(couponList.getValue('cid')==undefined || couponList.getValue('cid').length==0){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!couponList.validateChose("status", "2,3", "粉丝券已上架")){
 			return;
 		}
 		var data = {cids:couponList.getValue('cid').join(','),status:'1'};
 		updateStatusBatch(data,"你确定要上架选中的粉丝券？");
 	});
 }
 
 /**
  * 批量下线
  */	
 function removeOffshelf(){
 	$("#removeOffshelf").click(function(){
 		console.log(couponList.getValue('cid').join(','));
 		if(couponList.getValue('cid')==undefined || couponList.getValue('cid').length==0){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!couponList.validateChose("status", "1,3", "粉丝券已下架不能再次下架")){
 			return;
 		}
 		var data = {cids:couponList.getValue('cid').join(','),status:'2'};
 		updateStatusBatch(data,"你确定要下架选中的粉丝券？");
 	});
 }
 
 
 /**
  * 批量更新粉丝券状态
  * @param data
  * @param title
  */
  function updateStatusBatch(data,title){
  	showSmConfirmWindow(function() {
  					$.ajax({
				        type: "POST",
				        url: "liveCoupon/manage/updateStatusBatch.jhtml",
				        data: data,
				        dataType: "json",
  				        success: function(result){
  							showSmReslutWindow(result.success, result.msg);
  							couponList.reload();
  				         }
  				    });
  			},title);
  }
  
  
  /**
   * 设为推荐
   */	
  function setRecommend(){
  	$("#setRecommend").click(function(){
  		console.log(couponList.getValue('cid'));
  		if(couponList.getValue('cid')==undefined || couponList.getValue('cid').length==0){
  			showWarningWindow("warning","请至少选择一条记录！");
  			return;
  		}
  		if(!couponList.validateChose("isRecom", "0", "粉丝券已推荐不可再次推荐")){
  			return;
  		}
  		if(!couponList.validateChose("status", "1,3", "粉丝券已下架不能推荐")){
  			return;
  		}
  		var data = {cids:couponList.getValue('cid').join(','),isRecom:'1'};
  		updateRecomBatch(data,"你确定要推荐选中的粉丝券？");
  	});
  }
  
  /**
   * 取消推荐
   */	
  function cancelRecommend(){
  	$("#cancelRecommend").click(function(){
  		console.log(couponList.getValue('cid'));
  		if(couponList.getValue('cid')==undefined || couponList.getValue('cid').length==0){
  			showWarningWindow("warning","请至少选择一条记录！");
  			return;
  		}
  		if(!couponList.validateChose("isRecom", "1", "未推荐的粉丝券不可取消推荐")){
  			return;
  		}
  		var data = {cids:couponList.getValue('cid').join(','),isRecom:'0'};
  		updateRecomBatch(data,"你确定要取消推荐选中的粉丝券？");
  	});
  }
  
  /**
   * 批量更新粉丝券状态
   * @param data
   * @param title
   */
   function updateRecomBatch(data,title){
   	showSmConfirmWindow(function() {
   					$.ajax({
 				        type: "POST",
 				        url: "liveCoupon/manage/updateRecomBatch.jhtml",
 				        data: data,
 				        dataType: "json",
   				        success: function(result){
   							showSmReslutWindow(result.success, result.msg);
   							couponList.reload();
   				         }
   				    });
   			},title);
   }
