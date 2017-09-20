<%--
  Created by IntelliJ IDEA.
  User: Joney
  Date: 2017/6/21
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  <title>黄金庄园花朵管理</title>

  <link rel="stylesheet" href="<%=path%>/resources/orgChart/css/style.css">
  <link rel="stylesheet" href="<%=path%>/resources/orgChart/css/jquery.orgchart.css">
  <%--<link rel="stylesheet" href="https://cdn.rawgit.com/FortAwesome/Font-Awesome/master/css/font-awesome.min.css">--%>

</head>
<body>


<label for="uid" >用户uid</label><input id="uid" name="uid" autocomplete="on"><input id="ensure" type="submit" value="查询花朵链(折叠)"><input id="ensureAll" type="submit" value="查询花朵链(展开)"></div></div>
<div id="chart-container"  class=".canvasContainer" style="height:100%;width: 100% ; overflow: visible;"></div>

</body>

<script type="text/javascript" src="<%=path%>/resources/orgChart/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/orgChart/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/orgChart/js/jquery.orgchart.js"></script>
<script>

    var ajaxURLs = {
        'children': '/orgchart/children/',
        'parent': function(nodeData){
            return '/orgchart/parent/'+nodeData.pid;
        },

        'siblings': function(nodeData) {
            return '/orgchart/siblings/' + nodeData.id;
        },
        'families': function(nodeData) {
            return '/orgchart/families/' + nodeData.id;
        }
    };

    $("#ensure").click(function(){
        var uid = $("#uid").val()
        $("#chart-container").empty();

        $('#chart-container').orgchart({
//          'data': 'manor/flower/init-node.jhtml?uid=606499'
            'data': 'manor/flower/all-node.jhtml?uid=' + uid
//          'data': 'manor/flower/all-node.jhtml?uid=606041'
            ,'nodeContent': "body"
            ,'nodeTitle': 'title'
            ,'zoom': true  // 可缩放
            ,'pan': true   // 可拖拽
            ,'toggleSiblingsResp': true // 节点可折叠/展开

            ,'createNode': function($node, data) {
                $node.click(function(){
                    $.get("manor/flower/node-info.jhtml?nodeId="+data.id,function(data){
                        var info = "";
                        info += "\nid:" + data.id;
                        info += "\n子节点数:" + (data.livedFlowers);
                        info += "\n过期子节点数:" + (data.perishedFlowers);
                        alert(info);

                    })


                })
            }
        });

        return false;
    })


    $("#ensureAll").click(function(){
        var uid = $("#uid").val()
        $("#chart-container").empty();

        $('#chart-container').orgchart({
            'data': 'manor/flower/all-node-expanded.jhtml?uid=' + uid
            ,'nodeContent': "body"
            ,'nodeTitle': 'title'
            ,'zoom': true  // 可缩放
            ,'pan': true   // 可拖拽
            ,'toggleSiblingsResp': true // 节点可折叠/展开
            ,'createNode': function($node, data) {
//                var secondMenuIcon = $('<i>', {
//                    click: function() {
////                        $(this).siblings('.second-menu').toggle();
//                    }
//                });
//                var secondMenu = '<div class="second-menu"><div></div></div>';
//                $node.append(secondMenuIcon).append(secondMenu);
                $node.click(function(){
                    $.get("manor/flower/node-info.jhtml?nodeId="+data.id,function(data){
                        var info = "";
                        info += "\nid:" + data.id;
                        info += "\n子节点数:" + (data.livedFlowers);
                        info += "\n过期子节点数:" + (data.perishedFlowers);
                        alert(info);

                    })


                })
            }
            ,'ajaxURL': ajaxURLs
        });

        return false;
    })


</script>
</html>
