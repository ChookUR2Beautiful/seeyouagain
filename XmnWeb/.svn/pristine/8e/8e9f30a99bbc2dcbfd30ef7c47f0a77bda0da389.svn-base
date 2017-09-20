var sellerSpreadList;
$(document).ready(function() {

	sellerSpreadList = $('#sellerSpreadInfoDiv').page({
            url : 'businessman/sellerSubsidy/manager.jhtml',
            success : sellerSpreadSuccess,
            pageBtnNum :10,
            pageSize:15,
            paramForm : 'SellerSpreadForm'
        });
     inserTitle(' > 商家管理 > <a href="businessman/sellerSubsidy/init.jhtml" target="right">商家服务员推广</a>','userSpan',true);

     $('#export').click(function(){
    	$form = $("#SellerSpreadForm").attr("action","businessman/sellerSubsidy/spread/export.jhtml");
 		$form[0].submit();
     });
	$(".form-datetime2").val(initMonth());
	$(".form-datetime2").datetimepicker({
		weekStart : 1,
		autoclose : 1,
		todayHighlight : 0,
		forceParse : 0,
		startView : 3,
		minView :3,
		maxView :3,
		format : 'yyyy-mm'
	});
	var valiexport={
			rules:{
				month:{
					required:true
				}
			},
			messages:{
				month:{
					required:"导出月份未选择"
				}
			}
		}
	validate("dcForm",valiexport,exportSubmit);	
});
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function sellerSpreadSuccess(data, obj) {

    var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个合作商业务员记录&nbsp;</font></caption>';
    var callbackParam = "&isBackButton=true&callbackParam="	+ getFormParam($("#SellerSpreadForm").serialize());
    
    updateAddBtnHref("#addSread",callbackParam);

    obj.find('div').eq(0).scrollTablel({
            callbackParam : callbackParam,
            caption : captionInfo,
            //数据
            data:data.content, 
             //数据行
            cols:[{
            title : "商家编号",// 标题
            name : "sellerid",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "商家名称",// 标题
            name : "sellername",
            width : 250,// 宽度
            type:"string"//数据类型
        },{
            title : "服务员ID",// 标题
            name : "aid",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "服务员账号",// 标题
            name : "account",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "服务员姓名",// 标题
            name : "fullname",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "服务员寻蜜鸟会员编号",// 标题
            name : "uid",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "推广二维码",// 标题
            name : "url",
            width : 180,// 宽度
            type:"string",//数据类型
            customMethod : function(value, data) {
				if(data!=null){
					return "<a href=\"javascript:seeCode('"+data.sellerid+","+data.sellername+","+data.aid+"')\">" + "查看二维码" + "</a>";
				}else{ 
				 return "-";
				}
			}
        },{
            title : "推广状态",// 标题
            name : "tgStatus",
            width : 180,// 宽度
            type:"string",//数据类型
            customMethod : function(value, data) {
                if(data.tgStatus==0){
                 return "正常";
                }else if(data.tgStatus==1){ 
                 return "停止";
                }else{
                 return "-";    
                }
         }
        }],
            //操作列
            handleCols : {
                title : "操作",// 标题
                queryPermission : ["start"],// 不需要选择checkbox处理的权限
                width : 130,// 宽度
                // 当前列的中元素
                cols : [{
                    title : "修改",// 标题
                    linkInfoName : "href",
                    linkInfo : {
                        href : "businessman/sellerSubsidy/spread/start",// url
                        param : ["id"],// 参数
                        permission : "start"// 列权限
                    },
                    customMethod : function(value, data){
                        if(data.tgStatus==0){
                            var value1 = "<a href=\"javascript:updateTgStauts('"+1+","+data.id+"')\">" + "停止" + "</a>";
                            return value1;
                        }else{
                            var value2 = "<a href=\"javascript:updateTgStauts('"+0+","+data.id+"')\">" + "开启" + "</a>";
                            return value2;
                        }
                    }
                }]
    }},permissions);
    }
/**
 * 查看二维码
 * @param 
 */
function seeCode(barcode){
	var codeStr = barcode;
	var result = codeStr.split(",");
	var sellerid = result[0];
	var sellername = result[1];
	var aid = result[2];
	var data = 'sellerid=' + sellerid + '&sellername='+ encodeURIComponent(sellername) + '&aid='+ aid;
//	$.ajax({
//		 url : "businessman/sellerSubsidy/base64ToEncrypt.jhtml",
//		 type : "post",
//		 dataType : "json",
//		 data:
//		 success : function(data) {
		 var base = $("base").attr("href");//获取base标签的href属性值 
		 showCodeWindow('<img width="512" alt="二维码" src="'+base+'/getBigMatrix.jhtml?'+data+'">');
//		 }
//	});
}
/**
 * 弹出二维码模态框
 */
function showCodeWindow(content) {
	$('#lg_edit_window').find('.modal-title').html('推广二维码');
	$('#lg_edit_window').find('.modal-body').html('<div class="content" align="center">' + content + '</div>');
	$('#lg_edit_window').modal();
//	setTimeout(function(){
//		$('#sm_edit_window').modal('hide');
//	}, 2000);
}
/**
 * 弹出操作提示结果
 */
function showSmReslutWindow(isflag, content) {
	$('#sm_reslut_window').find('.modal-title').html('操作提示');
	if (isflag) {
		$('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon alert-success"> <i class="icon-ok"></i> <div class="content">' + content + '</div></div>');
	} else {
		$('#sm_reslut_window').find('.modal-body').html('<div class="alert with-icon  alert-danger"><i class="icon-remove-sign"></i> <div class="content">' + content + '</div></div>');
	}
	$('#sm_reslut_window').modal();
	setTimeout(function(){
		$('#sm_reslut_window').modal('hide');
	}, 2000);
}
/**
 * 修改推广状态
 */
function updateTgStauts(statusParam){
	var result = statusParam.split(",");
	var tgStatus = result[0];
	var id = result[1];
	$.ajax({
		 url : "businessman/sellerSubsidy/spread/start.jhtml",
		 type : "post",
		 dataType : "json",
		 data:'tgStatus=' + tgStatus +"&id="+id,
		 success : function(result) {
			 if (result.success) {
					showSmReslutWindow(result.success, result.msg);
					setTimeout(function() {
						sellerSpreadList.reload();
					}, 2000);
				} else {
					window.messager.warning(result.msg);
			}
		 }
	});
}

function initMonth(){
	return new Date().format("yyyy-MM");
}
function exportSubmit(){
	$form = $("#dcForm");
	$form[0].submit();
	$('#exportModal').modal('hide');
	
}

var waiterConfigList;
$(document).ready(function() {
	waiterConfigList = $('#waiterConfigInfoDiv').page({
            url : 'businessman/sellerSubsidy/config.jhtml',
            success : waiterConfigsuccess,
            pageBtnNum :10,
            pageSize:15,
            paramForm : 'WaiterConfigForm'
        });
     inserTitle(' > 商家管理 > <a href="businessman/sellerSubsidy/init.jhtml" target="right">商家服务员推广</a>','userSpan',true);
});
