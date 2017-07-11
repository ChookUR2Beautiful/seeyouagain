<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员红包</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css" />
<link rel="stylesheet" href="${ctx}/css/component.css" />
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pullupload.js"></script>
<script type="text/javascript" src="${ctx}/js/component.js"></script>
<script type="text/javascript">
	//领取明细
	function detailRecord(redpacketId,redpacketRecordId,userId) {
		window.location.href = "${ctx}/h5/redpacket/detail_record?redpacketId="+ redpacketId +"&redpacketRecordId="+redpacketRecordId+ "&userId=" + userId;
	};
</script>
<style>
	.page-loading{
		width: 100%;
		height: 30px;
		background-size: 37%;
		background-image: url(${ctx}/imgs/loadingimg.gif);
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
<body class="bg-color-01">
	<div class="container-wrap" style="padding:0">
		<div class="datadetails-info-module">
			<div class="datadetails-info-wrap">
				<div class="getdetails-con">
					<span class="getdetails-coll">
					<strong>
					 <em>￥${requestScope.realSpending}</em>
					 </strong>
					 <font>总支出</font></span> <span class="getdetails-coll bag-size"><strong>
							<c:choose>
								<c:when test="${requestScope.recordNumber > 0 }">${requestScope.recordNumber}</c:when>
								<c:otherwise>0</c:otherwise>
							</c:choose> <em>个</em> 
					 </strong> <font>领取红包个数</font></span>
				</div>

			</div>
		</div>

		<div class="list-default-module fill-list-block" id="list-box">
			<!-- <c:forEach items="${requestScope.data}" var="data">
				<c:if test="${!empty data}">
					<a class="list-item item-icon-left" href="javascript:void(0)" onclick="detailRecord(${data.redpacketId},${data.redpacketRecord.id},${data.userId})">
						<c:choose>
							<c:when test="${empty data.avatar}">
								<i class="icon-wrap icon-crown" ></i>
							</c:when>
							<c:otherwise>
								<i class="icon-wrap icon-crown" style="background-image: url(${data.avatar});"></i>
							</c:otherwise>
						</c:choose>

						<span class="item-coll"> <strong>${data.nickname}</strong>
							<font>${data.recordTime}</font>
					        </span> <span class="item-coll"> <strong>￥${data.redpacketRecord.denomination}</strong>
							<font> 
							    <c:choose>
									<c:when test="${data.redpacketType eq 0 }">
										  分享引流红包
								    </c:when>
									<c:when test="${data.redpacketType eq 1 }">
										   限时到店红包
								    </c:when>
									<c:when test="${data.redpacketType eq 2 }">
										  消费满赠红包
								    </c:when>
									<c:when test="${data.redpacketType eq 3 }">
										  推荐消费红包
								    </c:when>
									<c:when test="${data.redpacketType eq 4 }">
										  普通抽奖红包
								    </c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
						    </font>
					</span>
					</a>
				</c:if>
			</c:forEach> -->
			
		</div>
		<!-- <c:if test="${requestScope.recordNumber eq 0 }">
              <div class="emptydata">
					<i></i>
					<p>您当前没相关数据</p>
			  </div>
	   </c:if>	 -->
	</div>

	<script>
		$(function(){
			new pullUpload({
				url: '${ctx}/h5/redpacket/paged_list_record',
				params: {
					pageNum: 0,
					pageSize: 20,
					redpacketId: "${requestScope.redpacketId}"
				},
				initdata: true,
				beforeSend: function(){
					$('#list-box').append('<div class="page-loading"></div>');
				},
				success: function(pullupload,data){
					$('.page-loading').remove();
					if(data.state == 0){
						/*成功*/
						var res = data.result.recordList;
						var _html = '';
						var totalCount = data.result.recordCount;
						if(totalCount){
							var localPage = pullupload.params.pageNum;
							var totalPage = Math.ceil(parseInt(totalCount) / pullupload.params.pageSize) - 1;
							
							
							for(var i in res){
								_html += '<a class="list-item item-icon-left" href="javascript:void(0)">';
								if(res[i].avatar){
									_html += '<i class="icon-wrap icon-crown" style="background-image: url('+res[i].avatar+');"></i>';
								}else{
									_html += '<i class="icon-wrap icon-crown" ></i>';
								}
								_html += '<span class="item-coll"> <strong>'+res[i].nname+'</strong>';
								_html += '<font>'+res[i].recordTime+'</font></span>';
								_html += '<span class="item-coll"> <strong>￥'+parseFloat(res[i].denomination).toFixed(2)+'</strong>';
								if(res[i].redpType == 0){
									_html += '<font>分享引流红包</font>';
								}else if(res[i].redpType == 1){
									_html += '<font>限时到店红包</font>';
								}else if(res[i].redpType == 2){
									_html += '<font>消费满赠红包</font>';
								}else if(res[i].redpType == 3){
									_html += '<font>推荐消费红包</font>';
								}else if(res[i].redpType == 4){
									_html += '<font>普通抽奖红包</font>';
								}else{
									_html += '<font></font>';
								}
								_html += '</span></a>';
							}

							if(totalPage <= localPage){
								pullupload.end = true;
								_html += '<div class="page-end">已经到底了</div>';
							}

							$('#list-box').append(_html);
							pullupload.params.pageNum ++;
							
							
						}else{
							pullupload.end = true;
							$('#list-box').append('<div class="emptydata"><i></i><p>您当前没相关数据</p></div>');
						}
						
					}else{
						Tips.show('服务器错误，请刷新重试');
					}
					
				}
			});
		});
	</script>
</body>
</html>