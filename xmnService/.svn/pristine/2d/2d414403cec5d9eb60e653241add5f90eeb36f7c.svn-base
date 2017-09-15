<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家活动列表</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/activity.css"/><link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/commom.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/showtips.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/show.js"></script>
	
</head>
<body>

	  <ul class="coupons-list">
       <%--  <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_huodong@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_huodong@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_hongbao@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_huodong@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li>
        <li>
            <img src="${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png" alt="bg" />
            <p class="list-title">分享引流红包</p>
            <p class="list-time">活动时间：2016.11.22-2016.11.23</p>
            <span class="right-arrow"></span>
        </li> --%>
    </ul>
</body>
	<script>
	 $(function(){
		 var tips = new showtips();
		 
		 var url="${pageContext.request.contextPath}/seller/activity/list";
		 $.getJSON(url,{"appversion":"${params.appversion}", "systemversion": "${params.apiversion}","apiversion":"${params.apiversion}","sellerid":"${params.sellerid}"},function(data){
			 console.log(data.response.activityList);
			 if(data.state==100){
				 var html="";
				$.each(data.response.activityList,function(i,v){
					if(v.type==0)
					{
						html+="<a href='${url}/activitys/red_packets?id="+v.id+"&codeType=99&code=${params.sessiontoken}&lat=${param.lat}&lon=${param.lon}'><li><img src='${pageContext.request.contextPath}/images/icon_hongbao@2x.png' alt='bg' />";
						html+="<p class='list-title'>"+v.cname+"</p>";
						html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
						html+="<span class='right-arrow'></span>";
						html+="</li></a>";
					}else
					{
						if(v.type==1)
						{
							html+="<a href='${url}/activitys/coupons?s_id="+v.sellerid+"&codeType=99&code=${params.sessiontoken}&c_type="+v.flag+"'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
							html+="<p class='list-title'>"+v.cname+"</p>";
							html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
							html+="<span class='right-arrow'></span>";
							html+="</li></a>";	
						}else
						{
							if(v.type==2)
							{
								html+="<a href='${url}/activitys/freetry?act_id="+v.id+"&code=${params.sessiontoken}&codeType=99'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
								html+="<p class='list-title'>"+v.cname+"</p>";
								html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
								html+="<span class='right-arrow'></span>";
								html+="</li></a>";
							}else{
								if(v.type==3)
								{
									html+="<a href='${url}/activitys/roullete?act_id="+v.id+"&code=${params.sessiontoken}&codeType=99'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
									html+="<p class='list-title'>"+v.cname+"</p>";
									html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
									html+="<span class='right-arrow'></span>";
									html+="</li></a>";
								}else{
									if(v.type==4)
									{
										html+="<a href='${url}/activitys/fcouspoints?act_id="+v.id+"&code=${params.sessiontoken}&codeType=99'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
										html+="<p class='list-title'>集点活动</p>";
										html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
										html+="<span class='right-arrow'></span>";
										html+="</li></a>";
									}else
									{
										if(v.type==5)
										{
											html+="<a href='${killUrl}?act_id="+v.id+"&code=${params.sessiontoken}&codeType=99&title="+v.cname+"'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
											html+="<p class='list-title'>"+v.cname+"</p>";
											html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
											html+="<span class='right-arrow'></span>";
											html+="</li></a>";
										}else
										{
											html+="<a href='${url}/activitys/coupons/reduction?re_id="+v.id+"&code=${params.sessiontoken}&codeType=99'><li><img src='${pageContext.request.contextPath}/images/icon_xianjinquan@2x.png' alt='bg' />";
											html+="<p class='list-title'>"+v.cname+"</p>";
											html+="<p class='list-time'>活动时间："+v.sdate.replace(/-/gm,'.')+"-"+v.edate.replace(/-/gm,'.')+"</p>";
											html+="<span class='right-arrow'></span>";
											html+="</li></a>";
										}
									}
									
								}
							}
						}
					}
				})
				$(".coupons-list").append(html);
			 }else{
				 tips.show("获取商家活动列表失败！");
			 }
		 });
		 
	 });
	</script>
</html>