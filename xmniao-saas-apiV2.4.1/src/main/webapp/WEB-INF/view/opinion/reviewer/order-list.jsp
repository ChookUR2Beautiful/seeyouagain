<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>名嘴食评</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/marketingRank.css"/>
    <link rel="stylesheet" href="${ctx}/css/component.css">

    <style type="text/css">
        .end{
            font-size: 12px;
            text-align: center;
            padding:2px 0;
            margin:3px 0;
        }
        .page-loading{
            width: 100%;
            height: 30px;
            background-size: 37%;
            background-image: url(/imgs/loadingimg.gif);
            background-repeat: no-repeat;
            background-position: center;
        }
        .page-end{
            text-align: center;
            font-size: 12px;
            padding: 5px 0;
            color: #999;
        }
    </style>
</head>
<body class="bg-color-01 padd-fill-tb">
    <div class="container-wrap container-wrap-padding">
        <div class="slide-tab-wrap slide-tab-fixed">
           <span class="active"><a href="javascript:;">进行中</a></span>
           <span><a href="javascript:;">已发布</a></span>
        </div>
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" id="menberbag-underway">
                    <div class="content-slide-wrap user-review-list" id="list-box-1"></div>
                    <div class="page-end" style="display: none;">已经到底了</div>
                </div>
                <div class="swiper-slide">
                    <div class="content-slide-wrap user-review-list" id="list-box-2"></div>
                    <div class="page-end" style="display: none;">已经到底了</div>
                </div>
            </div>
        </div>

    </div>
    <div class="floor-module">
        <a class="floor-links" href="${ctx}/h5/opinion/reviewer/list">发起任务</a>
    </div>

</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/opinion/pullupload.js"></script>
<script type="text/javascript" src="${ctx}/js/opinion/reviewer-order-input.js"></script>
<script type="text/javascript">
    $(function(){
        var num=1;var num2=1;
        //返回角度
        function GetSlideAngle(dx, dy) {
            return Math.atan2(dy, dx) * 180 / Math.PI;
        }

//根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
        function GetSlideDirection(startX, startY, endX, endY) {
            var dy = startY - endY;
            var dx = endX - startX;
            var result = 0;

            //如果滑动距离太短
            if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
                return result;
            }

            var angle = GetSlideAngle(dx, dy);
            if (angle >= -45 && angle < 45) {
                result = 4;
            } else if (angle >= 45 && angle < 135) {
                result = 1;
            } else if (angle >= -135 && angle < -45) {
                result = 2;
            }
            else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
                result = 3;
            }

            return result;
        }

//滑动处理
        var startX, startY;
        document.addEventListener('touchstart', function (ev) {
            startX = ev.touches[0].pageX;
            startY = ev.touches[0].pageY;
        }, false);
        document.addEventListener('touchend', function (ev) {
            var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
            var endX, endY;
            endX = ev.changedTouches[0].pageX;
            endY = ev.changedTouches[0].pageY;
            var direction = GetSlideDirection(startX, startY, endX, endY);
            switch (direction) {
                case 0:

                    break;
                case 1:

                    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
                    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
                    if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 50){
                        /*有滑动条情况*/
                        //$('#list-box-order-1').append('<div class="page-loading"></div>');
                        var index=$(".slide-tab-wrap span.active").index();
                        if(index==0){
                            if($("#list-box-1 .emptydata").html()==undefined){
                                download(num);
                                num++;
                            }
                        }else {
                            if($("#list-box-2 .emptydata").html()==undefined){
                                download2(num2);
                                num2++;
                            }

                        }
                        
                    }
                    
                    //alert("向上");
                    break;
                case 2:
                    //alert("向下");
                    
                    if(scrollTop==0){
                        var index=$(".slide-tab-wrap span.active").index();
                        console.log(index);
                        if(index==0){
                            $("#list-box-1").html("");
                            $("#list-box-1").next().hide();
                            download(0);
                            num=1;
                            //console.log(num);
                            // 初始化页面
                        }else {
                            $("#list-box-2").html("");
                            $("#list-box-2").next().hide();
                            download2(0);
                            num2=1;
                        }
                    }
                    break;
                case 3:
                    //alert("向左");
                    break;
                case 4:
                    //alert("向右");
                    break;
                default:
            }
        }, false);
        var tabIndex = '' ;
        $(".slide-tab-wrap span").each(function(index,ele){
            if ( $(this).hasClass("active" )) {
                tabIndex = $(this).index();
                $(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
                $(".swiper-container .swiper-wrapper .swiper-slide").eq(tabIndex).show()
                return false;
            }
        })
        $(".slide-tab-wrap span").click(function(){
            var conIndex= $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            $(".swiper-container .swiper-wrapper .swiper-slide").siblings().hide();
            $(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).show();
        })


        // 初始化页面
        download(0);
        download2(0);
    
    })
    function download(num){
        $('#list-box-1').append('<div class="page-loading"></div>');
        $.post("${ctx}/h5/opinion/reviewer/order/list",
                {
                    pageNum: num,
                    pageSize: 4,
                },
                function(data){
                    $('.page-loading').remove();
                    if(data.state == 0){
                        /*成功*/
                        var res = data.result.orderList;
                        var _html = '';
                        var totalCount = data.result.orderCount;
                        if(totalCount){
                            for(var i in res){
                                // 设置性别图标
                                var sexClass = "icon-wrap icon-age-man";
                                if(res[i].celebrity.sex == 2){
                                    sexClass = "icon-wrap icon-age-woman";
                                }
                                var status = res[i].status;
                                // TODO apend _html
                                _html += '<div class="slide-item">'
                                _html += '    <div class="item-links">'
                                _html += '        <div class="item-content item-pad-60">'
                                _html += '            <img class="item-content-img" src="'+res[i].celebrity.avatar+'" alt="#">'
                                _html += '            <div class="item-content-wrap">'
                                _html += '                <div class="item-content-tit"><span class="item-content-money">￥'+(res[i].price).toFixed(2)+'</span><span class="item-content-name">'+res[i].celebrity.name+'<i class="'+sexClass+'"></i></span></div>'
                                _html += '                <div class="item-content-text col-sm-2"><span class="item-content-cost">食评费用</span><span class="item-content-desc">要求面谈时间：'+res[i].arrivalTime+'</span></div>'
                                _html += '            </div>'
                                _html += '        </div>'
                                _html += '        <div class="item-floor">'
                                _html += '            <div class="item-floor-clumn">'

                                var hightStyle = "color:#365dc9";

                                switch (status){
                                    case 1:
                                        _html += '                当前进度：<span style="'+hightStyle+'">接单</span> - <span>到店面谈</span> - <span>网红拍照</span> - <span>等待发布</span> - <span>发布成功</span>'
                                        break;
                                    case 2:
                                        _html += '                当前进度：<span >接单</span> - <span style="'+hightStyle+'">到店面谈</span> - <span>网红拍照</span> - <span>等待发布</span> - <span>发布成功</span>'
                                        break;
                                    case 3:
                                        _html += '                当前进度：<span >接单</span> - <span >到店面谈</span> - <span style="'+hightStyle+'">网红拍照</span> - <span>等待发布</span> - <span>发布成功</span>'
                                        break;
                                    case 4:
                                        _html += '                当前进度：<span >接单</span> - <span>到店面谈</span> - <span >网红拍照</span> - <span style="'+hightStyle+'">等待发布</span> - <span>发布成功</span>'
                                        break;
                                    case 5:
                                        _html += '                当前进度：<span >接单</span> - <span>到店面谈</span> - <span>网红拍照</span> - <span >等待发布</span> - <span style="'+hightStyle+'">发布成功</span>'
                                        break;
                                }

                                _html += '            </div>'
                                _html += '        </div>'
                                _html += '    </div>'
                                _html += '</div>'
                            }
                            
                            if(data.result.orderList<4){
                                //_html += '<div class="page-end">已经到底了</div>';
                                $('#list-box-1').next().show();

                            }else{
                                $('#list-box-1').append(_html);
                            }
                        }else{
//                            pullupload.end = true;
                            $('#list-box-1').append('<div class="emptydata"><i></i><p>您当前没相关数据</p></div>');
                        }

                    }else{
                        Tips.show('服务器错误，请刷新重试');
                    }

                }
        );
    }
    function download2(num){
        $('#list-box-2').append('<div class="page-loading"></div>');
        $.post("${ctx}/h5/opinion/reviewer/order/published_list",
                {
                    pageNum: num,
                    pageSize: 2,
                },
                function(data){
                    $('.page-loading').remove();
                    if(data.state == 0){
                        /*成功*/
                        var res = data.result.ariticleList;
                        var _html = '';
//                        var totalCount = data.result.ariticleCount;
                        var totalCount = data.result.ariticleCount;
                        if(totalCount){
//                            var localPage = pullupload.params.pageNum;
//                            var totalPage = Math.ceil(parseInt(totalCount) / pullupload.params.pageSize) - 1;

                            for(var i in res){
                                // TODO

                                // 设置性别图标
                                var sexClass = "icon-wrap icon-age-man";
                                if(res[i].celebrity.sex == 2){
                                    sexClass = "icon-wrap icon-age-woman";
                                }

                                _html += '<div class="slide-item">'
                                _html += '    <a href="#" class="item-links">'
                                _html += '        <div class="item-content item-pad-60">'
                                _html += '            <img class="item-content-img" src="'+res[i].celebrity.avatar+'" alt="#">'
                                _html += '            <div class="item-content-wrap">'
                                _html += '                <div class="item-content-tit">'
                                _html += '                    <span class="item-content-viewer-wrap">'+res[i].views+'<em class="viewer-count">人在看</em></span>'
                                _html += '                    <span class="item-content-name">'+res[i].celebrity.name+'<i class="'+sexClass+'"></i></span>'
                                _html += '                </div>'
                                _html += '                <div class="item-content-text col-sm-2"><span class="item-content-address">'+res[i].zoneName+'</span>'
                                _html += '                    <span class="item-content-desc">'+res[i].createTime+'</span>'
                                _html += '                </div>'
                                _html += '            </div>'
                                _html += '        </div>'
                                _html += '        <div class="item-content-picimg-wrap">'
                                _html += '            <a href="${ctx}/h5/opinion/reviewer/article?articleId='+res[i].id+'">'
                                _html += '            <img src="'+res[i].image+'" alt="">'
                                _html += '            </a>'
                                _html += '            <div class="item-content-picimg-wrap-text"><span>'+res[i].sellerName+'</span></div>'
                                _html += '        </div>'
                                _html += '        <div class="item-content-picimg-desc">'+res[i].name+'</div>'
                                _html += '    </a>'
                                _html += '</div>'
                            }
                            if(data.result.ariticleList<4){
                                    //_html += '<div class="page-end">已经到底了</div>';
                                $('#list-box-2').next().show();
                                //$('#list-box-2').append(_html);
                            }else{
                                $('#list-box-2').append(_html);
                            }
                        }else{
                            $('#list-box-2').append('<div class="emptydata"><i></i><p>您当前没相关数据</p></div>');
                        }

                    }else{
                        Tips.show('服务器错误，请刷新重试');
                    }

                }
        );
    }
</script>
</html>