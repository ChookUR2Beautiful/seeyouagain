<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.xmniao.xmn.core.util.FastfdsConstant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>详情</title>
    <link rel="stylesheet" href="css/businessman/main.css"/><link>

    <script>
//        var mySwiper = new Swiper('.swiper-container', {
//            onSlideChangeEnd: function(swiper){
//                console.log(swiper.activeIndex);
//                $("#activeSlide").text(swiper.activeIndex+1);
//            }
//        });
    </script>
</head>
<body id="remarkDetailCtrl" style="padding-bottom: 1rem;">
	<input type="hidden" id="commentId" value="${detail.id}">
    <section id="remarkCtrl">
        <div class="list">
            <div class="flex">
                <div class="flex-fixed left-box">
                    <img src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP %>/${detail.avatar}" >
                    <img src="images/businessman/grade_02.png" class="icon">
                </div>
                <div class="flex-auto">
                    <p class="name">${detail.liverName}<span class="fl-r">${detail.createTimeRelativeFormat}</span></p>
                    <p class="address">${detail.zoneName}<span class="fl-r">${detail.reviewStateStr}</span></p>
                    <p class="text">${detail.content}</p>
                    <div class="video-box">
                        <!--<div class="lay" ng-click="openSlide(remark);"></div>-->
                        <ul>
                        	<c:forEach items="${detail.commentMedias}" var="medias" varStatus="status">
                        		<li>
                                	<${medias.videoType==1?'img':'video'} src="<%=FastfdsConstant.FILE_UPLOAD_FASTDFS_HTTP %>/${medias.mediaUrl}" ${status.index==0?'class="box" style="width:100%;"':''} controls="controls" />
                            	</li>
                        	</c:forEach>
                        </ul>
                        <!--<span>1/<em ng-bind="remark.imgList.length"></em></span>-->
                    </div>
                     <p class="text">地址: ${detail.address}</p>
                     <p class="text">电话: ${detail.phone}</p>
                </div>
            </div>
        </div>
    </section>
	
    <div class="big-img-box" ng-if="bigShow===true">
        <img src="images/businessman/close_big_img_box.png" class="close-btn" ng-click="closeSlide();" >
        <span class="serial-number"><em id="activeSlide">1</em>/<em ng-bind="slideList.length"></em></span>
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide" ng-repeat="slide in slideList" repeat-finish>
                    <video ng-src="{{slide.src}}" ng-if="slide.type===1"></video>
                    <img ng-src="{{slide.src}}" ng-if="slide.type===2" >
                </div>
            </div>
        </div>
    </div>
    <c:if test="${detail.reviewState != 1 && detail.reviewState != 3}">
    <div  style="text-align: left;width:100%;">
									<button  class="btn btn-lg" type="button" 
										data-target="#queryYes" data-toggle="modal" style="width:43%;flaot:left;margin:0 2%;" >通过</button>
									<input class="btn btn-lg" type="button" value="拒绝"
										data-target="#queryNo" data-toggle="modal" style="width:43%;flaot:left;margin:0 2%;" />
								</div>
</c:if>
<div class="modal fade" id="queryYes">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title">审核提示</h4>
      </div>
      <div class="modal-body">
        <p>是否通过审核，通过后前端将会显示点评</p>
      </div>
      <div class="modal-footer" style="text-align: center;">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" id="queryYesClick">确认</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="queryNo">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title">审核提示</h4>
      </div>
      <div class="modal-body">
        <p>是否拒绝商户点评原因</p>
        <textarea class="form-control" rows="" cols="" id="note"></textarea>
      </div>
      <div class="modal-footer" style="text-align: center;">
        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
        <button type="button" class="btn btn-primary" id="queryNoClick" >是</button>
      </div>
    </div>
  </div>
</div>

</body>
<script type="text/javascript">
		 $("#queryNoClick").on("click",function(){
		 	if(!$("#note").val()){
			showWarningWindow("warning", "请输入拒绝原因!", 9999);
			return;
		}
	 	 var commentId= $("#commentId").val();
	 	 var note=$("#note").val();
	 	 $.ajax({
		url : "businessman/experience/comment/updateReviewState.jhtml",
		type : "post",
		dataType : "json",
		data: {"id":commentId,"refuseRemark":note,"reviewState":2},
		success : function(result) {
			if (result.success) {
				$('#queryNo').modal('hide');
				$('#triggerModal').modal('hide');
				commentList.reload();
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
	 
		
	 });
	 $("#queryYesClick").on("click",function(){
	 	var commentId= $("#commentId").val();
	 	      $.ajax({
		url : "businessman/experience/comment/updateReviewState.jhtml",
		type : "post",
		dataType : "json",
		data: {"id":commentId,"reviewState":1},
		success : function(result) {
			if (result.success) {
				$('#queryYes').modal('hide');
				$('#triggerModal').modal('hide');
				commentList.reload();
			} else {
				showSmReslutWindow(result.success, result.msg);
			}
		}
	});
			
	 });
</script>

</html>