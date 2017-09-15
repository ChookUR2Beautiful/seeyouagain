<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath%>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath%>css/grade-20160906.css"/>
</head>
<body class="live-fill grad-bg-col">
    <div class="main-container">
        <div class="grade-banner-module">
            <div class="grade-logo-wrap">
                <div class="grad-logo-img"><span>${data.rankNo }</span></div>

            </div>
            <div class="grad-suffer-wrap">当前经验值：<strong>${data.current_expe}</strong> ，离升级还差：<strong>${data.nextLevleExpe }</strong></div>
            <div class="grad-progress-wrap"><span class="progress-subname">lV.${data.rankNo }</span><span class="progress-content"><div class="progress-space"><span id="progress-time" class="progress-vader" style="width:${data.vader}"></span></div>
            </span><span class="progress-subname">lV.${data.rankNo +1}</span></div>
    </div>
    <div class="grade-info-module">
        <h2 class="grade-subtitle"><strong>如何升级</strong></h2>
        <div class="grade-info-content">
            <div class="grade-info-item">
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_pay@2x.png" alt=""/></i>消费</div>
                <p class="info-subtext">10经验/鸟币</p></div>
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_look@2x.png" alt=""/></i>观看</div>
                <p class="info-subtext">9经验/5分钟</p></div>
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_play@2x.png" alt=""/></i>开播</div>
                <p class="info-subtext">18经验/5分钟</p></div>
            </div>
            <div class="grade-info-item">
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_share@2x.png" alt=""/></i>分享</div>
                <p class="info-subtext">5经验/次</p></div>
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_news@2x.png" alt=""/></i>私信主播</div>
                <p class="info-subtext">2000经验/次</p></div>
                <div class="grade-info-item-col"><div class="info-subhead"><i class="icon-wrap"><img src="<%=basePath%>images/grade_barrage@2x.png" alt=""/></i>弹幕</div>
                <p class="info-subtext">20经验/次</p></div>
            </div>
        </div>
        <div class="grade-info-desc">有效经验计算：看直播4小时/天，开播8小时/天，分享3次/天， 私信主播无上限，弹幕无上限，送礼无上限。</div>
    </div>
    <div class="exclusive-icon-module">
        <h2 class="grade-subtitle"><a class="links" href="<%=basePath%>exclusiveicon"><i class="icon-wrap icon-img icon-arrow-left"></i>不同等级不同图标，等级越高图标越特殊</a><strong>专属图标</strong></h2>
        <div class="exclusive-icon-wrap">
            <div class="exclusive-icon-content">
                
                <div class="excluseive-item-row"><div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon1@2x.png" alt="" /></div>
                    <div class="excluseive-desc">排名靠前 </div>
                    <!-- <div class="excluseive-hinge">（2级解锁）</div> -->
                </div>
                <div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon2@2x.png" alt="" /></div>
                    <div class="excluseive-desc">弹幕框特效</div>
                    <!-- <div class="excluseive-hinge">（15级解锁）</div> -->
                </div>
                <div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon3@2x.png" alt="" /></div>
                    <div class="excluseive-desc">私信主播</div>
                    <!-- <div class="excluseive-hinge">（20级解锁）</div> -->
                </div></div>
                <div class="excluseive-item-row"><div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon4@2x.png" alt="" /></div>
                    <div class="excluseive-desc">聊天框字体</div>
                    <!-- <div class="excluseive-hinge">（25级解锁）</div> -->
                </div>
                <div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon5@2x.png" alt="" /></div>
                    <div class="excluseive-desc">升级飞屏特效</div>
                    <!-- <div class="excluseive-hinge">（28级解锁）</div> -->
                </div>
                <div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon6@2x.png" alt="" /></div>
                    <div class="excluseive-desc"> 头像金边闪耀</div>
                    <!-- <div class="excluseive-hinge">（30级解锁）</div> -->
                </div></div>
                <div class="excluseive-item-row"><div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon7@2x.png" alt="" /></div>
                    <div class="excluseive-desc">小土豪进房特效</div>
                    <!-- <div class="excluseive-hinge">（32级解锁）</div> -->
                </div>
                <div class="exclusive-item">
                    <div class="excluseive-img"><img src="<%=basePath%>images/exclusive_icon8@2x.png" alt="" /></div>
                    <div class="excluseive-desc">中土豪进房特效</div>
                    <!-- <div class="excluseive-hinge">（35级解锁）</div> -->
                </div></div>
                <!--<div class="exclusive-item">-->
                    <!--<div class="excluseive-img"><img src="<%=basePath%>images/grades-img-10.png" alt="" /></div>-->
                    <!--<div class="excluseive-desc">土豪发礼物专属位</div>-->
                    <!--&lt;!&ndash; <div class="excluseive-hinge">（30级解锁）</div> &ndash;&gt;-->
                <!--</div>-->
                <!--<div class="exclusive-item">-->
                    <!--<div class="excluseive-img"><img src="<%=basePath%>images/grades-img-11.png" alt="" /></div>-->
                    <!--<div class="excluseive-desc">升级飞屏特效 </div>-->
                    <!--&lt;!&ndash; <div class="excluseive-hinge">（32级解锁）</div> &ndash;&gt;-->
                <!--</div>-->
                <!--<div class="exclusive-item">-->
                    <!--<div class="excluseive-img"><img src="<%=basePath%>images/grades-img-12.png" alt="" /></div>-->
                    <!--<div class="excluseive-desc">头像金边闪耀 </div>-->
                    <!--&lt;!&ndash; <div class="excluseive-hinge">（35级解锁）</div> &ndash;&gt;-->
                <!--</div>-->
                <!--<div class="exclusive-item">-->
                    <!--<div class="excluseive-img">-->
                        <!--<img src="<%=basePath%>images/grades-img-01.png" alt="" />-->
                        <!--<p class="excluseive-text">999级</p>-->
                    <!--</div>-->
                    <!--<div class="excluseive-desc">未asggagq解锁状态</div>-->
                <!--</div>-->
            </div>
        </div>
    </div>
    </div>
</body>
</html>