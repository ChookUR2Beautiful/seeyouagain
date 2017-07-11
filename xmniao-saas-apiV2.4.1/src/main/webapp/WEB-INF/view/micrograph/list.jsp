<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" class="gray">
<head>
<meta charset="UTF-8">
<title>微图助力</title>
<meta name="renderer" content="webkit">
<meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/main2.css">
<link rel="stylesheet" href="${ctx}/css/component.css">
<style type="text/css">
.page-loading {
	width: 100%;
	height: 30px;
	background-size: 37%;
	background-image: url(${ctx}/imgs/loadingimg.gif);
	background-repeat: no-repeat;
	background-position: center;
}

.page-end {
	text-align: center;
	font-size: 12px;
	padding: 5px 0;
	color: #999;
}
</style>
</head>
<body class="template">
	<section id="draw1">
		<div class="nav-box">
		
		<div class="title-box bg-fff">
			<p class="sort">
				<span class="">综合排序</span>
			</p>
			<p class="type">
				<span>适用类型</span>
			</p>
			<p class="search">
				<span>搜索</span>
			</p>
		</div>
		<div>
			<ul class="tab-box sort-box" style="display: none;">
				<li ${serialType==null||serialType==1?'class="active"':''}
					onclick="setSerialType(1)">综合排序</li>
				<li ${serialType==3?'class="active"':''} onclick="setSerialType(3)">使用次数从低到高</li>
				<li ${serialType==2?'class="active"':''} onclick="setSerialType(2)">使用次数从高到低</li>
			</ul>
			<ul class="tab-box type-box" style="display: none;">
				<li ${tag==null?'class="active"':''} onclick="setTag('')">全部</li>
				<c:forEach items="${tags}" var="tag1">
					<li onclick="setTag(${tag1.id})"
						${tag1.id==tag?'class="active"':''}>${tag1.name}</li>
				</c:forEach>
			</ul>
		
			
			
		</div>
	</div>
	<ul class="list-box" id="lsit-box">
		
	</ul>
	</section>
	<section id="draw2">
	<div class="search-box">
				<div class="input-box">
					<input type="text" placeholder="请输入搜索的商品" id="search_input" />
				</div>
				<div class="history-box" ${fn:length(micrographSearch)==0?'style="display:none;"':''}>
					<p>
						历史记录<span class="clear-btn" id="clear_btn">清空</span>
					</p>
					<ul>
						<c:forEach items="${micrographSearch}" var="search">
							<li onclick='search(this)'>${search.title}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="data-box" style="display:none;">
					<ul class="search-list">
						
					</ul>
				</div>
				<div class="nodata-box" style="display:none;">
					<img src="${ctx}/imgs/activityRank/search_list_null.png" />
					<p>
						哎呦，没有找到相关数据，<br />换个词再试试吧！
					</p>
				</div>
			</div>
	
	</section>
	
	<div class="shade-box"></div>
	<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/component.js"></script>
	<script type="text/javascript" src="${ctx}/js/util.js"></script>
	<script>
		var serialType = "${serialType}" //排序类型
		var searchName = "${searchName}"; //搜索内容
		var tag = "${tag}"; //分类
		/*当前页数*/
		var currentPage = 1;
		/*内容状态,没有内容后状态为0*/
		var currentPageStatus = 1;
		/*每页显示条数*/
		var pageSize = 10;
		
		router();
		/*页面路由*/
		function router(){
		    var hash = window.location.hash;
		    if(hash == '' || hash == '#draw1'){
		        $('#draw1').show();
		        $('#draw2').hide();
		    }else if(hash == '#draw2'){
		        $('#draw1').hide();
		        $('#draw2').show();
		    }
		}
		
		$(function() { 
			loadData();
			$(".sort-box li,.type-box li").on("click", function() {
				$(this).addClass("active").siblings().removeClass("active");
			});
			
			 $(document).bind("click",function(e){
			 e.stopPropagation();
                var target = $(e.target);
                if(target.closest(".nav-box").length == 0){
                    $(".shade-box").hide();
                    $(".sort-box").slideUp();
                    $(".type-box").slideUp();
                }
            })
            $(".title-box p").on("click",function () {
                $(this).siblings().removeClass("active");
                $(this).toggleClass("active");
                $(".shade-box").toggle();
				switch ($(this).index()) {
				case 0:
					$(".sort-box").slideToggle();
					$(".type-box").hide();
					break;
				case 1:
					$(".type-box").slideToggle();
					$(".sort-box").hide();
					break;
				case 2:
					$(".tab-box").slideUp();
					$(".search-box").show();
					$(".shade-box").hide();
					window.location.hash='#draw2';
				default:
					break;
				}
			});
	
	
			$(this).ajaxStart(function() {
				$("#draw1").append('<div class="page-loading"></div>');
			});
			$(this).ajaxSuccess(function() {
				$("#draw1").find(".page-loading").remove();
			});
	
	
			$(window).scroll(function() {
				// 当滚动到最底部以上100像素时， 加载新内容
				if ($(document).height() - $(this).scrollTop() - $(this).height() < 1) {
					if (currentPageStatus) {
						currentPageStatus = 0;
						loadData();
					}
				}
			});
	
			function loadData() {
				var param = {
					"pageSize" : pageSize,
					"pageIndex" : currentPage,
					"serialType" : serialType,
					"searchName" : searchName,
					"tag" : tag
				};
				$.post("${ctx}/h5/micrograph/list", param, function(data, status) {
					if (status == "success") {
						if (data.state == 0) {
							$.each(data.result, function(i, item) {
								var html = '<li><img src="${imageHost}' + item.coverImage + '" onclick="imgOn('+item.id+')" /><p>' + item.title + '</p><span>'+item.soldTimes+'人制造</span></li>'
								$("#lsit-box").append(html);
							});
							if ((currentPage == 1 && data.result.length == 0) || data.result == null) {
								if (searchName&&!serialType) {
									$(".history-box").hide();
									$(".tab-box").slideUp();
									$(".search-box").show();
									$(".shade-box").hide();
									$(".nodata-box").show();
								}
								var html = '<div class="emptydata">';
								html += '<i></i>';
								html += '<p>您当前没相关数据</p>';
								html += '</div>';
								$("body").append(html);
								currentPageStatus = 0;
							}
							else {
								currentPage++;
								currentPageStatus = 1;
								if (data.result.length < pageSize) {
									$("body").append('<div class="load-more"><span>没有更多</span></div>');
									currentPageStatus = 0;
								}
							}
						}
					}
				}, "json");
	
			}
	
			var searchKey = 1;
			$("#search_input").on("keyup", function(data, status) {
				if (event.keyCode == "13") {
				//回车执行查询
					window.location.href = "${ctx}/h5/micrograph/list_init?searchName=" + $(this).val();
				}
				$(".nodata-box").hide();
				var name = $(this).val();
				if ($.trim(name)) {
					if (searchKey) {
					window.setTimeout(function() {
						var seName=$("#search_input").val()
						if(!$.trim(seName)){
							return;
						}
						$.post("${ctx}/h5/micrograph/search_like", "name=" + seName, function(data, status) {
							if (status == "success") {
								if (data.state == 0) {
									$(".history-box").hide();
									var searchList = $(".data-box").show();
									searchList.find(".search-list").html("");	
									$.each(data.result, function(i, item) {
										searchList.find(".search-list").append("<li onclick='search(this)'>" + item.title + "</li>");
									});
								}
							}
						});
					
					},1000);
					}
					searchKey = 0;
					window.setTimeout(function() {
						searchKey = 1;
					}, 1000);
				}
				else {
					$(".history-box").show();
					$(".data-box").hide();
				}
			});
			
			$("#clear_btn").on("click",function(){
				var dialogObject = {};
				dialogObject.title='提示';
		        dialogObject.content = '确定要清空吗?';
		        dialogObject.secondBtnEvent=function(){
						$(".history-box").html("");
						$.post("${ctx}/h5/micrograph/clear_search","");
		       }
		       var dialogWrap = new dialog(dialogObject)
			});
			
			
			 window.onhashchange = router;
			 
		});
	
		function search(item) {
			var _this = $(item);
			$("#search_input").val(_this.text());
			window.location.href = "${ctx}/h5/micrograph/list_init?searchName=" + _this.text();
		}
	
		function setTag(tagId) {
			if (tagId == tag) {
				return;
			}
			window.location.href = "${ctx}/h5/micrograph/list_init?tag=" + tagId + "&serialType=" + serialType+"&searchName="+searchName;
		}
	
		function setSerialType(serialType) {
			if (serialType == this.serialType) {
				return;
			}
			window.location.href = "${ctx}/h5/micrograph/list_init?serialType=" + serialType + "&tag=" + tag+"&searchName="+searchName;
		}
		
		function imgOn(id){
			window.location.href ="${ctx}/h5/micrograph/input?id="+id;
		}
	</script>
</body>
</html>