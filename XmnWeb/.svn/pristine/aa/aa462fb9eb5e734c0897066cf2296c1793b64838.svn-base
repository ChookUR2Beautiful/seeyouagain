<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
<link href="<%=path%>/resources/webuploader/webuploader.css" rel="stylesheet">
<link href="<%=path%>/resources/upload/upload.css" rel="stylesheet">
<link
	href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css"
	rel="stylesheet">
	
<link rel="stylesheet" href="<%=path%>/resources/css/jedate.css"/><link>
<link rel="stylesheet" href="<%=path%>/resources/css/main.css"/><link>

</head>
<body>
<form id ="orderForm" method="post">
<input type="hidden" name="orderNo" value="${materialOrder.orderNo}">
<input type="hidden" name="type" value="${materialOrder.type}">
<div class="content">
    <div class="flow-box bg-fff">
        <div class="flow-stage">
            <ul>
                <li><span class="round">1</span><span>待付款</span><span class="line"></span></li>
                <li><span class="round">2</span><span>待确认</span><span class="line"></span></li>
                <li><span class="round">3</span><span>待发货</span><span class="line"></span></li>
                <li><span class="round">4</span><span>已发货</span><span class="line"></span></li>
                <li><span class="round">5</span><span>已完成</span><span class="line"></span></li>
                <li><span class="round">6</span><span>已关闭</span><span class="line"></span></li>
                <li><span class="round">7</span><span>售后</span></li>
            </ul>
        </div>
        <div class="order-information">
            <ul>
                <li>
                    <label>订单编号：</label>
                    <span>${materialOrder.orderNo}</span>
                </li>
                <li>
                    <label>订单类型：</label>
                    <span>${materialOrder.typeStr }</span>
                </li>
               <c:if test="${materialOrder.status != 1}">
               	<li>
                    <label>支付单号：</label>
                    <span><c:if test="${materialOrder.tradeNo != undefined}">${materialOrder.tradeNo}</c:if><c:if test="${materialOrder.tradeNo == undefinded}">-</c:if></span>
                </li>
                 <li>
                    <label>支付方式：</label>
                    <span>${materialOrder.payType}</span>
                </li>
               </c:if>
                </ul>
                <ul>
                 <li>
                    <label>买家：</label>
                    <span>${materialOrder.userName}</span>
                </li>
                <li>
                    <label>收货信息：</label>
                    <input type="text" id="address"/><a href="javascritp:void(0);" id="copy" onclick="copyAddress();">【复制信息】</a>
                </li>
                </ul>
                <c:if test="${materialOrder.status == 4 or materialOrder.status == 5 or materialOrder.status == 6 or materialOrder.status == 7}">
                <ul>
                <li>
                    <label>快递公司：</label>
                    <span>${materialOrder.postCompany}</span>
                </li>
                <li>
                    <label>快递单号：</label>
                    <span>${materialOrder.courierNumber}</span>
                </li>
                <li>
                    <label>供应商：</label>
                    <span>${materialOrder.name}</span>
                </li>
            	</ul>
                </c:if>
        </div>
        <!--待付款-->
        <c:if test="${!empty btnAu['materialOrder/manage/update']}">
       <c:if test="${materialOrder.status == 1}">
        <div class="price-box">
            订单价格<input type="number" placeholder="请输入订单的修改价格" name="orderAmount"/>  
        <div class="back-box" style="display: inline-block;vertical-align: middle;"><a type="button" href="javascript:;" onclick="changePrice();">&nbsp;确定价格</a></div>
        </div>
       </c:if>
       </c:if>
        <!--待确认-->
       <c:if test="${materialOrder.status == 2}">
        <div class="customization-box table-list">
            <ul>
                <li>
                    <p>订单编号</p>
                    <p>${materialOrder.orderNo}</p>
                </li>
                <li>
                    <p>设计师选择</p>
                   <select name="designer" style="width:200px ">
                        <c:forEach items="${suppliers}" var="supplier">
                        <option value="${supplier.supplierId}" <c:if test="${supplier.supplierId == materialOrder.designer}">selected</c:if>>${ supplier.name}</option>
                        </c:forEach>
                    </select>
                </li>
                	<li>
                	<p>上传初稿</p>
					<div  ImgValidate="true">
					<input type="hidden" id="starturl" name = "startUrl" value = "${materialOrder.startUrl}"/>		
					<div id="startdivs"></div>
					</div>
                </li>
                	<li>
                    <p>上传终稿</p>
                    <div  ImgValidate="true">
					<input type="hidden" id="endurl" name = "endUrl" value = "${materialOrder.endUrl}"/>		
					<div id="enddivs"></div>
					</div>
                </li>
            </ul>
             <c:if test="${!empty btnAu['materialOrder/manage/update']}">
            <div class="back-box">
                <a type="button" href="javascript:;" onclick="updateSaleStatus();">下一步</a><a type="button" href="javascript:;" onclick="saveMaterialPic();" style="margin-right:15px;">保存</a>
            </div>
            </c:if>
        </div>
       </c:if>
        <!--待发货-->
       <c:if test="${materialOrder.status == 3}">
        <div class="shipments-box table-list">
            <ul>
                <li>
                    <p>订单编号</p>
                    <p>${materialOrder.orderNo}</p>
                </li>
                <li>
                    <p>快递公司</p>
                    <input type="text" placeholder="快递公司填写" name="postCompany"/>
                </li>
                <li>
                    <p>快递单号</p>
                    <input type="text"  placeholder="快递单号填写" name="courierNumber"/>
                </li>
                <li>
                    <p>供应商</p>
                    <select name="description" style="width:200px ">
                        <c:forEach items="${suppliers}" var="supplier">
                        <option value="${supplier.supplierId}" <c:if test="${supplier.supplierId == materialOrder.supplierId}">selected</c:if>>${ supplier.name}</option>
                        </c:forEach>
                    </select>
                </li>
            </ul>
             <c:if test="${!empty btnAu['materialOrder/manage/update']}">
                <div class="back-box"><a type="button" href="javascript:;" onclick="deliver();">下一步</a></div>
               </c:if> 
        </div>
       </c:if>
        <!--已关闭-->
         <c:if test="${!empty btnAu['materialOrder/manage/update']}">
        <c:if test="${materialOrder.status == 6}">
        <div class="delete-order">
            <span onclick="remove('${materialOrder.orderNo}')">删除订单</span>
        </div>
        </c:if>
        </c:if>
        <!--售后-->
        <c:if test="${materialOrder.status == 7}">
        <div class="sales-service">
            <ul>
                <li>
                    <p>售后状态</p>
                    <p>
                        <input type="radio" name="state" id="state1" value="0" <c:if test="${afterSale.state==0}">checked</c:if>/><label for="state1">未处理</label>
                        <input type="radio" name="state"  id="state2" value="1" <c:if test="${afterSale.state==1}">checked</c:if>/><label for="state2">已处理</label>
                        <input type="radio" name="state"  id="state3" value="2" <c:if test="${afterSale.state==2}">checked</c:if>/><label for="state3">已解决</label>
                    </p>
                </li>
                <li>
                    <p>处理类型</p>
                    <p>
                        <input type="radio" name="dealType" id="type" value="0" <c:if test="${afterSale.dealType==0}">checked</c:if> /><label for="type">无需退款</label>
                        <input type="radio" name="dealType"  id="type2" value="1" <c:if test="${afterSale.dealType==1}">checked</c:if> /><label for="type2">部分退款</label>
                        <input type="radio" name="dealType"  id="type3" value="2" <c:if test="${afterSale.dealType==2}">checked</c:if> /><label for="type3">全额退款</label>
                    </p>
                </li>
                <li>退款金额<input type="amount" name="amount" placeholder="￥ 0.00" value="${afterSale.amount}"/></li>
            </ul>
            <div class="plan-box">
                <p>进度跟踪</p>
                <textarea placeholder="请填写订单跟踪状态" name="plan">${afterSale.plan}</textarea>
            </div>
        </div>
         <c:if test="${!empty btnAu['materialOrder/manage/update']}">
        <div class="back-box"><a type="button" href="javascript:;" onclick="updateAfterSale();">确定</a></div>
        </c:if>
        </c:if>
    </div>
    <div class="list-box">
        <div class="order-list">
            <ul class="list-title">
                <li style="width: 25%;">商品</li>
                <li style="width: 10%;">单价/数量</li>
                <li style="width: 15%;">买家</li>
                <li style="width: 15%;">下单时间</li>
                <li style="width: 10%;">订单状态</li>
                <li style="width: 15%;">实付金额</li>
                <li style="width: 10%;">售后</li>
            </ul>
            <div class="order">
              <c:if test="${materialOrder.type == 001}">
                <ul class="order-title">
                    <li style="width: 25%;">订单编号：${materialOrder.orderNo}</li>
                    <li>供应商：${materialOrder.name}</li>
                    <li class="handle">
                        <a color="gray">查看订单详情</a>
                        <a color="gray">填写物流单</a>
                        <c:if test="${materialOrder.status != 5}">
                        <a color="gray">操作售后</a>
                        </c:if>
                        <c:if test="${materialOrder.status == 5}">
                         <c:if test="${!empty btnAu['materialOrder/manage/update']}">
                        <a href="javascript:addAfterSale();" style="color:blue">操作售后</a>
                        </c:if>
                         <c:if test="${empty btnAu['materialOrder/manage/update']}">
                         <a href="javascript:;" style="color:blue">操作售后</a>
                         </c:if>
                        </c:if>
                    </li>
                </ul>
                <ul class="order-content">
                    <li style="width: 25%;padding-top: 8px;">
                        <div>
                            <img src="" id="logo"/>
                            <p>${materialOrder.title}</p>
                            <p>${materialOrder.remark}<span class="examine-btn"><c:if test="${materialOrder.type == 001}">【查看商品】</c:if><c:if test="${materialOrder.type == 002}">【查看商品】</c:if></span></p>
                        </div>
                    </li>
                    <li style="width: 10%;"><span>${materialOrder.salePrice}<br />（${materialOrder.nums}件）</span></li>
                    <li style="width: 15%;"><span>${materialOrder.consignee}<br />${materialOrder.mobile}</span></li>
                    <li style="width: 15%;"><span>${materialOrder.createTimeStr}</span></li>
                    <li style="width: 10%;"><span>
                    <c:if test="${materialOrder.status == 1}">待付款</c:if>
                    <c:if test="${materialOrder.status == 2}">待确定</c:if>
                    <c:if test="${materialOrder.status == 3}">待发货</c:if>
                    <c:if test="${materialOrder.status == 4}">已发货</c:if>
                    <c:if test="${materialOrder.status == 5}">已完成</c:if>
                    <c:if test="${materialOrder.status == 6 or materialOrder.status == 9}">已关闭</c:if>
                    <c:if test="${materialOrder.status == 7}">售后</c:if>
                    </span><i>&nbsp;</i></li>
                    <li style="width: 15%;"><span>${materialOrder.freight+materialOrder.orderAmount}<br />（含运费：${materialOrder.freight}）</span><i>&nbsp;</i></li>
                    <li style="width: 10%;"><span>售后</span><i>&nbsp;</i></li>
                </ul>
              </c:if>
               <c:if test="${materialOrder.type == 002}">
                <ul class="order-title">
                    <li style="width: 25%;">订单编号：${materialOrder.orderNo}</li>
                    <li>供应商：-</li>
                    <li class="handle">
                        <a color="gray">查看订单详情</a>
                        <a color="gray">填写物流单</a>
                        <c:if test="${materialOrder.status != 5}">
                        <a href="javascript:;" color="gray">操作售后</a>
                        </c:if>
                        <c:if test="${materialOrder.status == 5}">
                        <a href="javascript:addAfterSale();" style="color:blue">操作售后</a>
                        </c:if>
                    </li>
                </ul>
                <ul class="order-content">
                    <li style="width: 25%;padding-top: 8px;">
                        <div>
                            <p>${materialOrder.title}</p>
                            <p>${materialOrder.remark}<span class="examine-btn">【定制详情】</span></p>
                        </div>
                    </li>
                    <li style="width: 10%;"><span>-</span></li>
                    <li style="width: 15%;"><span>${materialOrder.consignee}<br />${materialOrder.mobile}</span></li>
                    <li style="width: 15%;"><span>${materialOrder.createTimeStr}</span></li>
                    <li style="width: 10%;"><span>
                    <c:if test="${materialOrder.status == 1}">待付款</c:if>
                    <c:if test="${materialOrder.status == 2}">待确定</c:if>
                    <c:if test="${materialOrder.status == 3}">待发货</c:if>
                    <c:if test="${materialOrder.status == 4}">已发货</c:if>
                    <c:if test="${materialOrder.status == 5}">已完成</c:if>
                    <c:if test="${materialOrder.status == 6 or materialOrder.status == 9}">已关闭</c:if>
                    <c:if test="${materialOrder.status == 7}">售后</c:if>
                    </span><i>&nbsp;</i></li>
                    <li style="width: 15%;"><span>${materialOrder.orderAmount}</span><i>&nbsp;</i></li>
                    <li style="width: 10%;"><span>售后</span><i>&nbsp;</i></li>
                </ul>
               </c:if>
            </div>
        </div>
    </div>

</div>
</form>
<!--弹出层-->
<div class="shade-box"></div>
<div class="shade-content" id="modle1">
    <div class="content-box">
        <span class="close-shade">×</span>
        <div class="customization-box">
         <h2>商品详情</h2>
            <ul class="introduction-list">
                <li><label>规格：</label><span id="group"></span></li>
                <li><label>印刷数量：</label><span id="num"></span></li>
                <li><label>订单金额：</label><span id="price"></span></li>
                <li><label>商品名称：</label><span id="title"></span></li>
            </ul>
            <ul class="img-box" id="commonUrl">
            </ul>
            <ul>
            <li><label>下单时间：</label><span id="createTime"></span></li>
            </ul>
        </div>
    </div>
</div>

<div class="shade-box"></div>
<div class="shade-content" id="modle2">
    <div class="content-box">
        <span class="close-shade">×</span>
        <div class="customization-box">
            <h2>定制详情</h2>
            <ul>
                <li><label>预算：</label><span id="budget"></span></li>
                <li><label>主色调：</label><span id="mainColor"></span></li>
                <li><label>副色调：</label><span id="secColor"></span></li>
                <li><label>类型：</label><span id="materialType"></span></li>
                <li><label>需求描述：</label><span id="remarks"></span></li>
             </ul>
             <ul class="img-box" id="customizeUrls">
            </ul>
             <ul>
                <li><label>要求完成日期：</label><span id="finishTime"></span></li>
            </ul>
        </div>
    </div>
</div>
</div>
<div class="delete-hint">
    <p>确定要下架？<span>×</span></p>
    <div class="btn-box">
        <button class="cancel-btn">取消</button>
        <button class="confirm-btn">确定</button>
    </div>
</div>
<script type="text/json" id="permissions">{updateOrder:'${btnAu['materialOrder/manage/update']}'}</script>
<script type="text/javascript" src="<%=path%>/resources/zui/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/cloud_design/order/orderDetail.js"></script>
<script>
var imgRoot = $("#fastfdsHttp").val();
var str = "${materialOrder.consignee}"+","+"${materialOrder.mobile}"+","+"${materialOrder.deliveryAddress}";
   
    $(function () {
        //点击删除订单
        $(".delete-order button").on("click",function () {
            $(".shade-box,.delete-hint").show();
        });
        //点击关闭删除遮罩层
        $(".delete-hint button.cancel-btn,.delete-hint p span").on("click",function () {
            $(".shade-box,.delete-hint").hide();
        });
        $(".delete-hint button.confirm-btn").on("click",function () {
            $(".shade-box,.delete-hint").hide();
        });
        
        //点击查看商品
        $(".examine-btn").on("click",function () {
        	shopView($("input[name='orderNo']").val(),$("input[name='type']").val());
        });
        
        //点击关闭遮罩层
        $(".close-shade").on("click",function () {
            $(".shade-box,.shade-content").hide();
        });
        var imgRoot = $("#fastfdsHttp").val();
        $("#logo").attr("src",imgRoot+'${materialOrder.url}');
    })
    

   $(document).ready(function(){
	   var status = ${materialOrder.status};
	   status= status==9?6:status;
	   for(var i=0;i<status;i++){
		   $("ul li:eq("+i+")").attr("class","yes");
	   }

	   $("#address").val(str);
	   $("#address").width($("#address").val().length*14-80);
   });
    
    contextPath = '${pageContext.request.contextPath}';  
</script>
<jsp:include page="../../common.jsp"></jsp:include>
<script src="<%=path%>/resources/webuploader/webuploader.js"></script>
<script src="<%=path%>/resources/upload/upload.js"></script>
<script src="<%=path%>/ux/js/searchChosen.js"></script>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
</body>
</html>