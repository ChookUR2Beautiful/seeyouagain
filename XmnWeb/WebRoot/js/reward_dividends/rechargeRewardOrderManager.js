/**
 * Created by Joney on 2017/6/2.
 */
initOrderList();

$('.form-datetime').datetimepicker({
    weekStart : 1,
    todayBtn : 1,
    autoclose : 1,
    todayHighlight : 1,
    startView : 2,
    forceParse : 0,
    showMeridian : 1,
    format : 'yyyy-mm-dd hh:ii:ss'
});

/** 初始化订单列表 */
function initOrderList() {
    $("#div-orderList").page({
        url: 'order/list.jhtml',
        success: loadOrderList,
        pageBtnNum: 10,
        paramForm: 'searchParam',
        method: 'GET'
    });
}


/** 加载订单列表
 * @param data
 * @param obj
 */
function loadOrderList(data, obj) {
    var callbackParam = "isBackButton=true&callbackParam=" + getFormParam($("#searchFromId").serialize());
    obj.find('div').eq(0).scrollTablel({
        identifier: "id",
        callbackParam: callbackParam,
        data: data.content,
        checkable: false,
        //操作列
        cols: [
            {title:"操作", name:"id", type:"string",width:60,
                customMethod : function(value,data){
                    var cla = data.status == 1 ? "btn btn-sm btn-info " : "btn btn-sm disabled";
                    return "<button class='"+cla+"' onclick='confirmOrder("+value+")'>确认订单</button>"
                }
            },
            { title: "编号", name: "id", type: "string", width: 40},
            { title: "订单编号", name: "orderNo", type: "string", width: 130},
            {title: "手机号码", name: "phone", type: "string", width: 90},
            {title: "地址", name: "rid", type: "string", width: 50,
                customMethod: function (rid,data) {
//                            showAddress(rid);
                    var cla = data.status != 0 ? "btn btn-sm btn-info " : "btn btn-sm disabled";
                    return "<button class='"+cla+"' onclick='showAddress("+rid+")' data-position='fit' data-toggle='modal' data-target='#address'>查看地址</button>"
                }
            },
            { title: "下单时间", name: "createTime", type: "string", width: 100,
                customMethod: function (value) { return convertTimestamp(value) }
            },
            { title: "订单状态", name: "status", type: "string", width: 90,
                customMethod: function (value) { return convertOrderStatus(value); }
            }
        ]
    },  permissions);
}

/** 转换订单状态为说明文字 */
function convertOrderStatus(status) {
    switch (status) {
        case 0:
            return "初始下单";
        case 1:
            return "用户已确认";
        case 2:
            return "系统确认";
        case 3:
            return "系统取消";
        default:
            return status;
    }
}

/** 转换时间戳 */
function convertTimestamp(timestamp) {
    return new Date(timestamp).format("yyyy-MM-dd hh:mm:ss")
}

/** 系统确认订单, 改变订单状态 */
function confirmOrder(orderId){

    showSmConfirmWindow(function(){
        $.post("order/confirm.jhtml",{orderId:orderId,status:2},function(){
            initOrderList();
        });
    },"是否确认订单");

}

/** 弹出地址信息 */
function showAddress(rid){
    $.get("order/address.jhtml",{rid:rid},function(data){
        $("#address-name").html(data.username);
        $("#address-phone").html(data.phoneid);
        $("#address-detail").html(data.province+data.city+data.areaname+data.address);
    })

}

