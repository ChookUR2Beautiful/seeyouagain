<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.sheight{
   height:100px;
}
</style>
</head>
<body>
<div style="width:900px;height:530px;">
<div>
<table>
    <tr ><th width="300px" ><h5>企业有效证照:</h5></th><th width="300px"><h5>签约负责人身份证（正面）:</h5></th><th width="300px"><h5>签约负责人身份证（反面）:</h5></th></tr>
    <tr  style="text-align:center">
    <td><img name="license"  class="sheight" onMouseOver="moBig(this)"  title="企业有效证照" src="${bankApply.license}"></td>
    <td><img name="upidcard" class="sheight" onMouseOver="moBig(this)"  title="签约负责人身份证（正面）" src="${bankApply.upidcard}"></td>
    <td><img name="dwidcard" class="sheight" onMouseOver="moBig(this)"  title="签约负责人身份证（反面）" src="${bankApply.dwidcard}"></td>   
    </tr>
</table>
</div><hr/>
<div style="width:900px;height:380px;background-color:#EFEFEF;text-align: center;position: relative;left: 10px;">
<div ><img height="380px"  alt="" id="bigPic"src="${bankApply.upidcard}"></div>
</div>
</div>
<br/><br/>
<center><button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>关闭</button></center>
</body>
<script type="text/javascript">
var moBig=function (obj){//大图切换
      $("#bigPic").attr("src",obj.src).attr("title",obj.title);
}
</script>
</html>

