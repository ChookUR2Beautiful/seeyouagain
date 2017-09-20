<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>黄金庄园-关系管理</title>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width">
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path%>/resources/treant-js/css/zui.css"/>
    <link rel="stylesheet" href="<%=path%>/resources/treant-js/Treant.css">
    <%--<link rel="stylesheet" href="<%=path%>/resources/treant-js/collapsable.css">--%>
    <link rel="stylesheet" href="<%=path%>/resources/treant-js/vendor/perfect-scrollbar/perfect-scrollbar.css">
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
    <style>
        .modal-dialog {
            margin-top :200px!important;
        }
    </style>
</head>
<body>

<div class="navbar navbar-fixed-top " style="background-color: white">
        <div class="container-fluid">
            <form class="form-inline" id="query-relation-from" target="nm_iframe">
                <div class="col-sm-1"></div>
                <div class="col-sm-2">
                    <%--<input>--%>
                    <label for="userNum" class="inline">用户: </label>
                    <input type="text" class="form-control" id="userNum">
                </div>
                <div class="col-sm-1">
        <span class="switch switch-inline">
            <input type="checkbox" id="switch-uid-uname">
            <label for="switch-uid-uname">用户帐号</label>
        </span>
                </div>
                <div class="col-sm-1">
        <span class="switch">
            <input type="checkbox" id="switch-self-parent">
            <label for="switch-self-parent">查询本人</label>
        </span>
                </div>
                <div class="col-sm-1">
        <span class="switch">
            <input type="checkbox" id="switch-flower-users">
            <label for="switch-flower-users">查询花朵链</label>
        </span>
                </div>
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-primary">查询</button>
                </div>
 			<div class="col-sm-1">
                    <button type="button" data-type="ajax"
										data-url="manorMember/manage/usrChain/edit/init.jhtml"
										data-toggle="modal" data-width="950px"  class="btn btn-primary">移动关系链</button>
                </div>
            </form>
            
            
    </div>
    <hr/>
</div>
<div class="container-fluid" style="padding-top: 60px">
    <div id="collapsable-example"></div>
</div>
<jsp:include page="../common.jsp"></jsp:include>
<script src="<%=path%>/resources/treant-js/vendor/raphael.js"></script>
<script src="<%=path%>/resources/treant-js/Treant.js"></script>

<%-- <script src="<%=path%>/resources/treant-js/vendor/jquery.min.js"></script> --%>
<script src="<%=path%>/resources/treant-js/vendor/jquery.easing.js"></script>
<%-- <script src="<%=path%>/resources/treant-js/js/zui.js"></script> --%>
<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>

<script>

    $("#query-relation-from").submit(function () {
        var data = new Object();

        // 结构图配置
        chart_config = new Object();
        chart_config.chart = {
            container: "#collapsable-example",
//                animateOnInit: true,
//            rootOrientation: "WEST",
             scrollbar : "resize",
            node: {collapsable: true},
            siblingSeparation : 1,
            subTeeSeparation : 100,
            animation: {
//                nodeAnimation: "easeOutBounce",
                nodeSpeed: 300,
//                connectorsAnimation: "bounce",
                connectorsSpeed: 300
            }
            , connectors: {type: "curve"       /* 连接线样式*/}
        };

        data.queryParent = $("#switch-self-parent").prop("checked") ? true : false;

        var ulr;
        if ($("#switch-flower-users").prop("checked")) {
            // 查询用户链
            ulr = "manor/relation/users.jhtml";
        } else {
            // 查询花朵链
            ulr = "manor/relation/flower.jhtml";
        }

        if ($("#switch-uid-uname").prop("checked")) {
            // 使用 uid 查询
            data.uid = $("#userNum").val();
        } else {
            // 使用 用户名查
            data.uname = $("#userNum").val();
        }

        // 查询关系链
        $.post(ulr, data, function (result) {

            if (result.state == 0) {
                chart_config.nodeStructure = result.context.nodeStructure;
                $("#collapsable-example").empty().attr("style","");

                treant = new Treant(chart_config, function () {
                    // 绑定节点点击事件
                    $(".node-name").click(function (event) {
                        event.stopPropagation();
                        var id = $(this).parent().attr("id");
                        console.log(id);
                        if(id.length > 10){
                            // 节点 branch_id
                            $.get("manor/relation/branch/info.jhtml?branchId="+id,function(result){
                                if(result.state==0){
                                    $("#branch-info-nname").text(result.context.user.nname);
                                    $("#branch-info-uname").text(result.context.user.uname);
                                    $("#branch-info-uid").text(result.context.user.uid);
                                    var locationInfo;
                                    switch (result.context.branch.location){
                                        case 0: locationInfo = "左"; break;
                                        case 1: locationInfo = "中"; break;
                                        case 2: locationInfo = "右"; break;
                                    }
                                    $("#branch-info-location").text(locationInfo);
                                    $("#branch-info-all-flower").text(result.context.allFlower);
                                    $("#branch-info-self-flower").text(result.context.selfFlower);
                                    $("#branch-info-self-seeding").text(result.context.selfSeedling);
                                    $("#branch-info-self-system-give").text(result.context.selfSystemGive);

                                    $("#flower-info").modal();

                                }else {
                                    $("#modal-content").text(result.message);
                                    $("#myModal").modal();
                                }
                            });


                        }else if (id){
                            // 用户id

                        }

//                        $('html, body').animate({
//                            scrollTop: $(".node:last").offset().top-120,
//                            scrollLeft : $(".node:last").offset().left
//                        }, 500);
                    });
                });

            } else {
                $("#modal-content").text(result.message);
                $("#myModal").modal();
            }
            $('html, body').animate({
                scrollTop: $(".node:last").offset().top-120,
                scrollLeft : $(".node:last").offset().left
            }, 500);
        });


        return true;
    })


    /*开关label 改变事件*/
    $("#switch-uid-uname").change(function () {
        if ($(this).is(':checked')) {
            $(this).next().text("用户编号")
        } else {
            $(this).next().text("用户帐号")
        }
    })
    $("#switch-self-parent").change(function () {
        if ($(this).is(':checked')) {
            $(this).next().text("查询上级")
        } else {
            $(this).next().text("查询本人")
        }
    });
    $("#switch-flower-users").change(function () {
        if ($(this).is(':checked')) {
            $(this).next().text("查询用户链")
        } else {
            $(this).next().text("查询花朵链")
        }
    })
</script>



<div class="modal fade" id="myModal"> <div class="modal-dialog"> <div class="modal-content" > <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
                    <h4 class="modal-title">出错了!</h4>
                </div>
                <div class="modal-body"> <h4><p id="modal-content">没有查询到信息</p></h4> </div>
</div> </div> </div> </div>


<%--花朵信息弹出框--%>
<div class="modal modal-for-page fade in" id="flower-info">
    <div class="modal-dialog" style="margin-top: 253px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
                <h4 class="modal-title">花朵信息</h4>
            </div>
            <div class="modal-body">
                <div class="row"><div class="col-sm-2"><p>用户名:</p></div><div class="col-sm-3" id="branch-info-nname">                  </div> </div>
                <div class="row"><div class="col-sm-2"><p>用户帐号:</p></div><div class="col-sm-3" id="branch-info-uname">                  </div> </div>
                <div class="row"><div class="col-sm-2"><p>用户编号:</p></div><div class="col-sm-3" id="branch-info-uid">                 </div> </div>
                <div class="row"><div class="col-sm-2"><p>节点位置:</p></div>
                    <div class="col-sm-1" id="branch-info-location"></div>
                    <%--<div class="col-sm-2"><button class="btn-sm btn-info">开通节点</button></div>--%>
                    <%--<div class="col-sm-2"><button class="btn-sm btn-warning">摆放园友</button> </div>--%>
                </div>
                <div class="row"><div class="col-sm-2"><p>节点花朵数:</p></div><div class="col-sm-3" id="branch-info-all-flower">                </div> </div>
                <div class="row"><div class="col-sm-2"><p>自种花朵数:</p></div><div class="col-sm-3" id="branch-info-self-flower">                </div> </div>
                <div class="row"><div class="col-sm-2"><p>自种种子数:</p></div><div class="col-sm-3" id="branch-info-self-seeding">                 </div> </div>
                <div class="row"><div class="col-sm-2"><p>独享花朵数:</p></div><div class="col-sm-3" id="branch-info-self-system-give">                 </div> </div>
            </div>
        </div>
    </div>
</div>

<iframe id="id_iframe" name="nm_iframe" style="display:none;"></iframe>
</body>
</html>
