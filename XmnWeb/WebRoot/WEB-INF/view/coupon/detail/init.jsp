<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=path%>/resources/zui-1.7.0/css/zui.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=path%>/resources/zui-1.7.0/lib/datatable/zui.datatable.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="<%=path%>/resources/paginator/css/bootstrapv3.css">

</head>
<body>


<div class="panel">
    <div class="panel-heading">
        <div class="row">
            <label for="uid" class="col-xs-1 text-right">用户编号:</label>
            <div class="col-xs-1">
                <input type="number" class="form-control" name="uid" id="uid">
            </div>

            <label for="uname" class="col-xs-1 text-right">优惠劵名称:</label>
            <div class="col-xs-2">
                <input type="text" class="form-control" name="uname" id="uname">
            </div>

            <label for="phone" class="col-xs-1 text-right">手机号码:</label>
            <div class="col-xs-2">
                <input type="text" class="form-control" name="phone" id="phone">
            </div>
            <label class="col-xs-1 text-right">优惠劵类型:</label>
            <div class="col-xs-1">
                <select name="ctype" class="form-control col-xs-2" id="ctype">
                    <option value="" >--请选择--</option>
                    <option value="0">消费优惠劵</option>
                    <option value="1">商城优惠劵</option>
                    <option value="5">平台通用优惠劵</option>
                </select>
            </div>

            <div class="col-xs-2 pull-right ">
                <input type="button" class="btn btn-info" value="查询" onclick="loadCouponList()">
                <input type="button" class="btn btn-info" value="重置" onclick="clearParams()">
            </div>
        </div>

    </div>
    <div class="panel-body">
        <div id="coupon-table" class="datatable show-scroll-slide scrolled-out" style="width: auto"></div>
        <div class="pull-right"> <ul id='pagination'></ul> </div>
    </div>
</div>


</body>



<script type="text/javascript" src="<%=path%>/resources/zui-1.7.0/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/zui-1.7.0/js/zui.js"></script>
<script type="text/javascript" src="<%=path%>/resources/paginator/src/bootstrap-paginator.js"></script>
<script type="text/javascript" src="<%=path%>/resources/zui-1.7.0/lib/datatable/zui.datatable.js"></script>
<script type="text/javascript" src="<%=path%>/js/coupon/detail/init.js"></script>
</html>
