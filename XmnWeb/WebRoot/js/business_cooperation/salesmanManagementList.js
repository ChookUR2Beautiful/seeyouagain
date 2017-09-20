var salesmanList;
$(document).ready(function() {
	salesmanList = $('#salesmanList').page({
	        url : 'business_cooperation/salesmanManagement/init/list.jhtml',
	        success : success,
	        pageBtnNum :10,
	        pageSize:15,
	        paramForm : 'searchForm'
	    });
	 inserTitle(' > 合作商管理 > <a href="business_cooperation/salesmanManagement/init.jhtml" target="right">合作商业务管员管理</a>','userSpan',true);
});
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success(data, obj) {
	
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个合作商业务员记录&nbsp;</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm").serialize());
	updateAddBtnHref("#addJoint",callbackParam);
	
	obj.find('div').eq(0).scrollTablel({
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
			title : "合作商编号",// 标题
			name : "jointid",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "公司名称",// 标题
			name : "corporate",
			width : 250,// 宽度
			type:"string"//数据类型
		},{
			title : "账号类型",// 标题
			name : "category",
			width : 180,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
	                if(data.category==1){
	                 return "普通员工";
	                }else if(data.category==2){ 
	                 return "管理员";
	                }else{
	                 return "-";	
	                }
	         }
		},{
			title : "业务员编号",// 标题
			name : "phoneid",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "业务员名称",// 标题
			name : "fullname",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "区域",// 标题
			name : "areaTitle",
			width : 180,// 宽度
			type:"string"//数据类型
		},{
			title : "账号状态",// 标题
			name : "status",
			width : 180,// 宽度
			type:"string",//数据类型
			customMethod : function(value, data) {
                if(data.status==0){
                 return "启用";
                }else if(data.status==1){ 
                 return "停用";
                }else{
                 return "-";	
                }
         }
		},{
			title : "加入时间",// 标题
			name : "sdateStr",
			width : 180,// 宽度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["update","start"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
					title : "修改",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "business_cooperation/salesmanManagement/update/init.jhtml",// url
						param : ["staffid"],// 参数
						permission : "update"// 列权限
					}
				},
				{
					title : "启用",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "business_cooperation/salesmanManagement/update/staff.jhtml",// url
						param : ["status"],// 参数
						permission : "start"// 列权限
					},
					customMethod : function(value, data){
                        if(data.status==0){
                            var value1 = "<a href='javascript:update(\""+data.staffid+"\",\""+1+"\")'>" + "停用" + "</a>";
                            return value1;
                        }else{
                            var value2 = "<a href='javascript:update(\""+data.staffid+"\",\""+0+"\")'>" + "启用" + "</a>";
                            return value2;
                        }
                    }
				
				}]
	}},permissions);
	}
/**
 * 启用或者停用操作（修改）
 */
 function update(staffid,status){
	 $.ajax({
         url : "business_cooperation/salesmanManagement/start.jhtml",
         type : "post",
         dataType : "json",
         data:'staffid=' + staffid + '&status='+ status,
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				 salesmanList.reload();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }

