<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body>
<div>

 <div  class=".table-bordered">
   <div class=".table-bordered"><h5>提现状态</h5></div>
   <div class=".table-bordered">${withdrawalsProcess.submitdate}&nbsp;&nbsp;提交资料</td></div>
    <c:if test="${!empty withdrawalsProcess.disposedate}">
     <div class=".table-bordered">
     ${withdrawalsProcess.disposedate}&nbsp;&nbsp;处理中</td></tr>
     </div>
     </c:if>
     <c:if test="${!empty withdrawalsProcess.receivedate}">
     <div class=".table-bordered">
     ${withdrawalsProcess.receivedate}&nbsp;&nbsp;已到账</td></tr>
     </div>
     </c:if>
     <c:if test="${withdrawalsProcess.state eq '4'}">
     <div class=".table-bordered">
     ${withdrawalsProcess.faildate}&nbsp;&nbsp;打款失败</td></tr>
     </div>
     </c:if>
 </div>
 <hr>
 <div class=".table-bordered">
  <div class=".table-bordered"><h5>提现资料</h5></div>
  <div class=".table-bordered">提现金额：${withdrawalsDetails.money}</div>
  <div class=".table-bordered">
           账户：${withdrawalsDetails.accountname}
           </br>
           银行卡号：${withdrawalsDetails.account}     
  </div>
  <div class=".table-bordered">公司名称：${withdrawalsDetails.username}</div>
  <div class=".table-bordered">发票号：${withdrawalsDetails.invoice}</div>
  <div class=".table-bordered">快递（发票寄出）</div>
  <div class=".table-bordered">快递公司：${withdrawalsDetails.express}</div>
  <div class=".table-bordered">快递号：${withdrawalsDetails.expressid}</div>
  <hr>
  <div class=".table-bordered">
       请在每月10号上传发票与寄出发票
       寄出地址：广东省广州市天河区黄埔大道西683号广东农信大厦18楼（寻蜜鸟网络技术有限公司）   财务部收
  </div>
  <hr>
 </div>
 <center><button type="reset" class="btn btn-default" data-dismiss="modal"><span class="icon-remove"></span>关闭</button></center>
</div>
</body>
</html>
