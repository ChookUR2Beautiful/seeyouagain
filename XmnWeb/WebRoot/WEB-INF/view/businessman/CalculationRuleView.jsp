<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
 <div style=" overflow-y:auto;  overflow-x:auto; height:600px;font-size: 5em;font-weight: 10;"><h5>
	  <div><h5>特约商户分类标准:</h5></div>
       <ol>
       <li><h5>此标准执行截止日期：2015年3月10日-2015年6月30日。</h5></li>
       <li><div><h5>具体执行标准详见下图：</h5></div>
       <div>
        <table border="1px">
            <tr>
            <th style="text-align:center">类别</th>
            <th style="text-align:center">商户类型</th>
            <th style="text-align:center">行业分类</th>
          <!--   <th style="text-align:center">折扣要求X</th> -->
            <th style="text-align:center">签约标准</th>
            </tr>
            <tr>
            <td>A店</td>
            <td>
            <span>
            <p>美食餐饮</p>
            <p>休闲娱乐</p> 
            </span>
            </td>
            <td>
            <span>
            <ul>
            <li>连锁餐饮</li>
            <li>品牌餐饮</li>
            <li>品牌餐饮</li>
            <li>大型KTV</li>
            <li>水疗会所</li>
            <li>电影院</li>
            <li>大型酒吧</li>
            </ul>
            </span>
            </td>
         <!--    <td>X≤9折</td> -->
            <td><span>
            <ol>
            <li>连锁类、品牌类;</li>
            <li>营业面积达到200M²以上;</li>
            <li>服务员数量达到20人以上；</li>
            <li>人均消费达到30元以上；</li>
            <li>单店月度流水量达到30万元以上。</li>
            </ol>
            </span></td>
            </tr>
            <tr>
            <td>B店</td>
            <td>
            <span>
            <p>美食餐饮</p>
            <p>休闲娱乐</p>
            <p>生活服务</p>   
            </span>
            </td>
            <td>
            <span>
            <ul>
            <li>美食</li>
            <li>KTV</li>
            <li>电影院</li>
            <li>大型KTV</li>
            <li>足疗按摩</li>
            <li>水疗会所</li>
            <li>酒店</li>
            <li>咖啡酒吧</li>
            <li>品牌连锁服装店</li>
            </ul>
            </span>
            </td>
        <!--     <td>X≤8.5折</td> -->
            <td><span>
            <ol>
            <li>营业面积达到100M²以上;</li>
            <li>服务员数量达到10人以上；</li>
            <li>人均消费达到30元以上；</li>
            <li>单店月度流水量达到15万元以上。</li>
            </ol>
            </span></td>
            </tr>
            <tr>
            <td>C店</td>
            <td>
            <span>
            <p>美食餐饮</p>
            <p>休闲娱乐</p>
            <p>生活服务</p>   
            </span>
            </td>
            <td>
            <span>
            <ul>
            <li>美食</li>
            <li>美发美甲</li>
            <li>足疗按摩</li>
            <li>家居饰品</li>
            <li>鲜花礼品</li>
            <li>汽车美容</li>
            <li>数码配件</li>
            <li>购物食品店</li>
            <li>棋牌室</li>
            <li>球馆球场</li>
            <li>桌游电玩</li>
            <li>咖啡酒吧</li>
            </ul>
            </span>
            </td>
       <!--      <td>X≤8.5折</td> -->
            <td><span>
            <ol>
            <li>营业面积达到20M²以上;</li>
            <li>服务员数量达到5人以上；</li>
            <li>人均消费达到20元以上；</li>
            <li>单店月度流水量达到5万元以上。</li>
            </ol>
            </span></td>
            </tr>
        </table>
       </div>
       </li>
       <ul>
       <div style="position:relative;left:-60px;"><font color="red"><h5>特殊说明:</h5></font></div>
       <li>
          <h5><font color="red">B店特殊说明：B店签约商家中美食类、KTV、电影类为B+店，其它为B店。</font></h5>
       </li>
       <li>
          <h5><font color="red">C店特殊说明：C店签约商家中美食类为C+店，其它为C店。</font></h5>
       </li>
       </ul>
      </ol>
      </h5>
  </div>    
</body>
</html>
