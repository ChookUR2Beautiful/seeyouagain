<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>食评名嘴</title>
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
        <div class="slide-tab-wrap slide-tab-fixed" >
           <span class="active"><a href="javascript:;">全部名嘴</a></span>
           <span><a href="javascript:;">名嘴排行</a></span>
        </div>
        <div class="swiper-container">
            <div class="swiper-wrapper" id="menberbag-underway">
                <div class="swiper-slide" >
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
        <a class="floor-links" href="javascript:;" onclick="sub()" >我要食评</a>
    </div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/opinion/pullupload.js"></script>
<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/opinion/reviewer-order-input.js"></script>
<script type="text/javascript">
    function sub(){
        var reviewerId = $("#menberbag-underway input:checked").attr("reviewerid");
        if(reviewerId){
            window.location.href = '${ctx}/h5/opinion/reviewer/order/submit?reviewerId='+reviewerId;
        }else{
            Tips.show("请选择名嘴!");
        }

    }
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
                        //$('#list-box-1').append('<div class="page-loading"></div>');
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
                            $('#list-box-1').next().hide();
                            download(0);
                            num=1;
                            //console.log(num);
                            // 初始化页面
                        }else {
                            $("#list-box-2").html("");
                            $('#list-box-2').next().hide();
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
            // if(conIndex == 1){
            //     $("#list-box-1").hide();
            //     $("#list-box-2").show();
            //     load.initdata=false;
            //     load2.initdata=true;
            // }else{
            //     $("#list-box-1").show();
            //     $("#list-box-2").hide();
            //     load.initdata=true;
            //     load2.initdata=false;
            // }
            $(".swiper-container .swiper-wrapper .swiper-slide").hide();
            $(".swiper-container .swiper-wrapper .swiper-slide").eq(conIndex).show();
        })
        download(0);
        download2(0);
        //loadList();
        //loadlist2();
        //load.initdata=true;
    })
    function download(num){
        $('#list-box-1').append('<div class="page-loading"></div>');
        $.post("${ctx}/h5/opinion/reviewer/list",
                {
                    pageNum: num,
                    pageSize: 4,
                    order :1
                },
                function(data){
                    $('.page-loading').remove();
                    if(data.state == 0){
                        /*成功*/
                        var res = data.result.reviewerList;
                        var _html = '';
                        var totalCount = data.result.count;
//                        var totalCount = 0;
                        if(totalCount){
                            for(var i in res){
                                var sexClass = "icon-wrap icon-age-man";
                                if(res[i].sex == 2){
                                    sexClass = "icon-wrap icon-age-woman";
                                }
                                _html += '<div class="slide-item">'
                                _html += '    <a href="${ctx}/h5/opinion/reviewer/detail?reviewerId='+res[i].id+'" class="item-links">'
                                _html += '        <div class="item-content item-pad-60">'
                                _html += '            <img class="item-content-img" src="${imgHost}'+res[i].avatar+'" alt="#">'
                                _html += '            <div class="item-content-wrap">'
                                _html += '                <div class="item-content-tit"><span class="item-content-name">'+res[i].name+'<i class="'+sexClass+'"></i></span></div>'
                                _html += '                <div class="item-content-text"><span class="item-content-desc">'+res[i].describe.substr(0,37)+'....</span></div>'
                                _html += '            </div>'
                                _html += '        </div>'
                                _html += '        <div class="item-floor col-sm-2">'
                                _html += '                <div class="item-floor-clumn">'
                                _html += '                    <strong>￥'+(res[i].reviewPrice).toFixed(2)+'</strong>'
                                _html += '                    <span>食评费用</span>'
                                _html += '                </div>'
                                _html += '                <div class="item-floor-clumn">'
                                _html += '                    <strong>'+res[i].orderNum+'</strong>'
                                _html += '                    <span>食评次数</span>'
                                _html += '                </div>'
                                _html += '        </div>'
                                _html += '    </a>'
                                _html += '    <div class="item-posit-radio">'
                                _html += '        <input name="diyongradio" type="radio" class="reset-radio-input" reviewerId="'+res[i].id+'" id="markeingRadio-1-'+res[i].id+'">'
                                _html += '        <label class="reset-radio" for="markeingRadio-1-'+res[i].id+'"></label>'
                                _html += '    </div>'
                                _html += '</div>'
                            }

                            if(data.result.reviewerList<4){
                                //_html += '<div class="page-end">已经到底了</div>';
                                $('#list-box-1').next().show();
                            }else{
                                $('#list-box-1').append(_html);
                            }
                        }else{
                            $('#list-box-1').append('<div class="emptydata"><i></i><p>您当前没相关数据</p></div>');
                        }

                    }else{
                        Tips.show('服务器错误，请刷新重试');
                    }
                }
        );
    }
    function download2(num2){
        $('#list-box-2').append('<div class="page-loading"></div>');
        $.post("${ctx}/h5/opinion/reviewer/list",
                {
                    pageNum: num2,
                    pageSize: 4,
                    order :2
                },
                function(data){
                    $('.page-loading').remove();
                    if(data.state == 0){
                        /*成功*/
                        var res = data.result.reviewerList;
                        var _html = '';
                        var totalCount = data.result.count;
                        if(totalCount){
                            for(var i in res){
                                var sexClass = "icon-wrap icon-age-man";
                                if(res[i].sex == 2){
                                    sexClass = "icon-wrap icon-age-woman";
                                }
                                _html += '<div class="slide-item">'
                                _html += '    <a href="${ctx}/h5/opinion/reviewer/detail?reviewerId='+res[i].id+'" class="item-links">'
                                _html += '        <div class="item-content item-pad-60">'
                                _html += '            <img class="item-content-img" src="${imgHost}'+res[i].avatar+'" alt="#">'
                                _html += '            <div class="item-content-wrap">'
                                _html += '                <div class="item-content-tit"><span class="item-content-name">'+res[i].name+'<i class="'+sexClass+'"></i></span></div>'
                                _html += '                <div class="item-content-text"><span class="item-content-desc">'+res[i].describe.substr(0,37)+'....</span></div>'
                                _html += '            </div>'
                                _html += '        </div>'
                                _html += '        <div class="item-floor col-sm-2">'
                                _html += '                <div class="item-floor-clumn">'
                                _html += '                    <strong>￥'+(res[i].reviewPrice).toFixed(2)+'</strong>'
                                _html += '                    <span>食评费用</span>'
                                _html += '                </div>'
                                _html += '                <div class="item-floor-clumn">'
                                _html += '                    <strong>'+res[i].orderNum+'</strong>'
                                _html += '                    <span>食评次数</span>'
                                _html += '                </div>'
                                _html += '        </div>'
                                _html += '    </a>'
                                _html += '    <div class="item-posit-radio">'
                                _html += '        <input name="diyongradio" type="radio" class="reset-radio-input" reviewerId="'+res[i].id+'" id="markeingRadio-2-'+res[i].id+'">'
                                _html += '        <label class="reset-radio" for="markeingRadio-2-'+res[i].id+'"></label>'
                                _html += '    </div>'
                                _html += '</div>'
                            }

                            if(data.result.reviewerList<4){
                                //_html += '<div class="page-end">已经到底了</div>';
                                $('#list-box-2').next().show();;
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