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
/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function waiterConfigsuccess(data, obj) {

    var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;共计【'+data.total+'】个合作商业务员记录&nbsp;</font></caption>';
    var callbackParam = "&isBackButton=true&callbackParam="	+ getFormParam($("#WaiterConfigForm").serialize());
    
    updateAddBtnHref("#addWaiterConfig",callbackParam);

    obj.find('div').eq(0).scrollTablel({
            callbackParam : callbackParam,
            caption : captionInfo,
            //数据
            data:data.content, 
             //数据行
            cols:[{
            title : "商家编号",// 标题
            name : "sellerId",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "商家名称",// 标题
            name : "sellername",
            width : 250,// 宽度
            type:"string"//数据类型
        },{
            title : "订单起始金额",// 标题
            name : "startMoney",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "订单结束金额",// 标题
            name : "endMoney",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "奖励金额",// 标题
            name : "money",
            width : 180,// 宽度
            type:"string"//数据类型
        },{
            title : "配置状态",// 标题
            name : "status",
            width : 180,// 宽度
            type:"string",//数据类型
            customMethod : function(value, data) {
                if(data.status==0){
                 return "已开启";
                }else if(data.status==1){ 
                 return "已关闭";
                }else{
                 return "-";    
                }
         }
        }],
            //操作列
            handleCols : {
                title : "操作",// 标题
                queryPermission : ["update"],// 不需要选择checkbox处理的权限
                width : 130,// 宽度
                // 当前列的中元素
                cols : [{
                    title : "修改",// 标题
                    linkInfoName : "href",
                    linkInfo : {
                        href : "businessman/sellerSubsidy/waiterConfig/update/init.jhtml",// url
                        param : ["id"],// 参数
                        permission : "update"// 列权限
                    }
                }]
    }},permissions);
    }
