<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
<head>
    <meta charset="utf-8"/>
    <title>编辑模板</title>
    <meta name="renderer" content="webkit">
    <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta name="x5-fullscreen" content="true">
    <meta name="full-screen" content="yes">
    <link rel="stylesheet" href="${ctx}/css/main2.css">
    <link rel="stylesheet" href="${ctx}/css/swiper.css">
    <link rel="stylesheet" href="${ctx}/css/component.css">
</head>
<body style="height: 100%;">
    <div class="swiper-container" style="height: 100%;">
        <div class="swiper-wrapper" style="height: 100%;">
        <c:forEach items="${micrographPage}" var="page" varStatus="status">
        	<div class="swiper-slide" style="height: 80%;">
        	<div  id="${page.id}" class="editor-bg ${status.index==0?'active':''}" style="background: url('${imageHost}/${page.backgroundImage}') no-repeat;background-size: 100% 100%;">
           <c:forEach items="${page.micrographModule}" var="module" >
             <c:choose>
            	<c:when test="${module.type==0}">
            		<textarea maxlength="${module.fontNum}" class="drag-node" id="${module.id}" contenteditable="true" style="left: ${module.leftStyle}%;top: ${module.top}%;width:${module.width}%;height:${module.height}%;font-size:${module.fontSize}px;" onkeyup="checkSize(${module.fontNum},this)">${module.remark}</textarea>
            	</c:when>
				<c:otherwise>
					<input id="changeBtn-${module.id}"  type="file" name="file" accept="image/*;capture=camera"  style="opacity:0">
					 <img src="${imageHost}${module.image}" id="${module.id}" class="drag-node " style="left: ${module.leftStyle}%;top:${module.top}%;width:${module.width}%;height:${module.height}%;" />
				</c:otherwise>          	
             </c:choose>
        </c:forEach>    
        </div>
        </div>
        </c:forEach>
        </div>
    </div>
<div class="swiper-pagination"></div>
<div id="moveid">
    <ul id="thumblist">
       <c:forEach items="${micrographPage}" var="page" varStatus="status">
        	<li  ${status.index==0?'class="tb-selected"':''}  >
            <img src="${imageHost}/${page.backgroundImage}" />
        	</li>
        </c:forEach>
    </ul>
</div>

	<div class="footer-nav editorImg-nav"  id="show_preview">
    <a href="javascript:;" class="refresh-btn" id="refresh">刷新</a>
    <a href="javascript:;" class="preview-btn" id="preview_btn">预览</a>
</div>
    <div class="footer-nav editorImg-nav" style="display: none" id="show_sharl">
        <a href="javascript:;" class="refresh-btn" id="hide_preview_btn">返回编辑</a>
        <a href="javascript:;" class="preview-btn" id="submit_bit">分享</a>
    </div>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/micrograph/main.js"></script>
	<script type="text/javascript" src="${ctx}/js/baseUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/component.js"></script>
    <script>
    	var imageHost= "${imageHost}";
    	var tempContextUrl="${tempContextUrl}";
    	$("#preview_btn").on("click",function(){
    		$("#moveid").hide();
    			$("#show_preview").hide();
    		$("#show_sharl").show();
    		$(".editor-bg img").addClass("change-img");
    		$("textarea").attr("readonly","readonly");
    		$("title").text("预览");
    		$(".swiper-slide").css("height","93.5%");
    	});
    	
    	$("#hide_preview_btn").on("click",function(){
    		$("#moveid").show();
    		$("#show_preview").show();
    		$("#show_sharl").hide();
    		$(".editor-bg img").removeClass("change-img");
    		$("textarea").removeAttr("readonly");
    		$("title").text("编辑模板");
    		$(".swiper-slide").css("height","80%");
    	});
    	
    	$("#refresh").on("click",function(){
    		window.location.reload();
    	});
    
        var mySwiper = new Swiper('.swiper-container', {
            //可选选项，自动滑动
            pagination : '.swiper-pagination',
            paginationClickable :true,
            normalizeSlideIndex:false,
              onSlideChangeEnd: function(swiper){
                //var index=swiper.activeIndex;
                $("#thumblist li").eq(swiper.activeIndex).addClass("tb-selected").siblings().removeClass("tb-selected");
                if((swiper.activeIndex+1)*89>$(window).width()){
                    var n=(swiper.activeIndex+1)*89-$(window).width();
                    $("#moveid").css("left", -n-10+"px");
                }else{
                    $("#moveid").css("left", "0px");
                }

                //console.log(swiper.activeIndex);
            }
        })
        
        $("#submit_bit").on("click",function(){
          var backgroundImage = $(".editor-bg");
          var back_list=new Array();
          $.each(backgroundImage,function(){
          	 var back_id=$(this).attr("id");
          	 var imgs=$(this).find("img");
          	 var inputs=$(this).find("textarea[class='drag-node']");
          	 $.each(imgs,function(){
          	 	var src=$(this).attr("src");
          	 	src=src.replace(imageHost,"");
          	 	var model= newModel(back_id,$(this).attr("id"),1,null,src);
          	 	back_list.push(model);
          	 }); 
          	 $.each(inputs,function(){
          		var model=newModel(back_id,$(this).attr("id"),0,$(this).val(),null);
          		back_list.push(model);
          	 });
          });
          var obj=new Object();
          $.post("${ctx}/h5/micrograph/save",{'model':JSON.stringify(back_list)},function(data,status){
          		if (status == "success") {
          			if (data.state == 0) {
          				obj.id=data.result.id;
          				obj.value='';
          				share(obj,null,basePath+"/h5/micrograph/share");
          			}else{
          				Tips.show(data.info);
          			}
          		}
          });
        });
        
        
        
        function newModel(pageId,id,type,content,image){ //use factory
			var obj=new Object();
			obj.pageId=pageId;
			obj.id=id;
			obj.type=type;
			obj.content=content;
			obj.image=image;
			return obj;
		} 
		
		function checkSize(i,item){
			var obj=$(item);
			var text=obj.text();
			var l = obj.text().length;
            if(l>i){
            	obj.text(text);
       		 }
		}
		
		$(this).ajaxStart(function() {
				loading.show();
			});
			$(this).ajaxSuccess(function() {
				loading.hide();
			});
		
    </script>
</body>
</html>