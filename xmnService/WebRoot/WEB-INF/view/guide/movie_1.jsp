<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>寻蜜鸟创客计划分享</title>
       <title>寻蜜鸟创客计划分享</title>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!"> 
    <meta name="format-detection" content="telephone=no"> 
    <meta name="google" value="notranslate"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
    <meta http-equiv="Cache-Control" content="no-transform"> 
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="<%=basePath %>css/normalize.css">
    <link rel="stylesheet" href="<%=basePath %>css/xmk-lead.css">
    <link rel="stylesheet" href="<%=basePath %>css/showtips.css">
</head>
<body>
	<div id="id_video_container" style="width:100%;height:468px;"></div><script src="http://qzonestyle.gtimg.cn/open/qcloud/video/h5/h5connect.js"></script>
 <script type="text/javascript">
            (function(){
               var option ={"auto_play":"0","file_id":"14651978969265660355","app_id":"1252387534","width":640,"height":468};
               /*调用播放器进行播放*/
               new qcVideo.Player(
                       /*代码中的id_video_container将会作为播放器放置的容器使用,可自行替换*/
                       "id_video_container",
                       option
                   );
             })()
 </script>
</body>
</html>