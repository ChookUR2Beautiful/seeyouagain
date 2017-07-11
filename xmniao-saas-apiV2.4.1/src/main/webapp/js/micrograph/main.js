/**
 * Created by Administrator on 2016/11/14.
 */
var imageDom;
function uploadCallback(json){
		loading.hide();
		if(json==null){
			Tips.show("网络不稳定,请稍后再试!");
		}else if(json==""){
			return;
		}else if(json){
			var data=eval('(' + json + ')'); 
			if(data.state!="0"){
				Tips.show(data.info);
			}else{
				 imageDom.attr("src",data.result.url);
			}
		}
		imageDom=null;
	}
(function() {
	//初始化拖拽元素函数
	function initDrag(id) {
		var box = document.getElementById(id);
		var block = box.querySelectorAll(".drag-node");
		var oW,
			oH;
		// 获取节点
		//var block = document.getElementById("block");
		for (var i = 0; i < block.length; i++) {
			// 绑定touchstart事件
			block[i].addEventListener("touchstart", function(e) {
				//                    console.log(e);
				var touches = e.touches[0];
				oW = touches.clientX - this.offsetLeft;
				oH = touches.clientY - this.offsetTop;
				//阻止页面的滑动默认事件
				document.addEventListener("touchmove", defaultEvent, false);
			}, false);

			block[i].addEventListener("touchmove", function(e) {
				e.preventDefault();
				var touches = e.touches[0];
				var oLeft = touches.clientX - oW;
				var oTop = touches.clientY - oH;
				//限制移动范围
				if (oLeft < 0) {
					oLeft = 0;
				}
				else if (oLeft > document.documentElement.clientWidth - this.offsetWidth) {
					oLeft = (document.documentElement.clientWidth - this.offsetWidth);
				}
				if (oTop < 0) {
					oTop = 0;
				}
				else if (oTop > document.documentElement.clientHeight - this.offsetHeight) {
					oTop = (document.documentElement.clientHeight - this.offsetHeight);
				}
				this.style.left = oLeft + "px";
				this.style.top = oTop + "px";
			}, false);

			block[i].addEventListener("touchend", function() {
				document.removeEventListener("touchmove", defaultEvent, false);

			}, false);
		}
		function defaultEvent(e) {
			e.preventDefault();


		}
	}

	//图片点击事件
	$(".editor-bg img").on("click", function() {
		var _this = $(this);
		if (_this.hasClass("change-img")) {
			return;
		}
		$(".editor-bg img").removeClass("active-img");
		_this.addClass("active-img");
		if(isIOS()){
			_this.parent().find("#changeBtn-" + _this.attr("id")).trigger("click");
		}else{
			loading.show();
			imageDom=_this;
			var path=tempContextUrl+"h5/micrograph/upload";
			nativeUpload.startImageSelector(path);
				return;
		}
		
	});
	
	
	
	//初始化拖拽元素
	//initDrag("box");0

	//底部页面选择滑动初始化
	slidePage();
	//关闭图片编辑导航
	// $(".close-btn").on("click",function () {
	//     $(".editorImg-nav").slideUp();
	// });
	// //删除图片
	// $("#deleteBtn").on("click",function () {
	//     $(".active-img").remove();
	// });
	//点击调用input  file调手机端摄像头和相册
	// $(".changeImg").on("click",function () {
	//     $("#changeBtn").click();
	// });
	//替换原图

	function checkImage(imgPath) {
		var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
		if (strExtension.compare('jpg') || strExtension.compare('gif') 
			||strExtension .compare('png')  || strExtension .compare('bmp') ) {
			return false;
		}
		Tips.show("请选择图片文件");
		return true;
		
	}	

	String.prototype.compare = function(str) {
		//不区分大小写
		if (this.toLowerCase() == str.toLowerCase()) {
			return true; // 正确
		}
		else {
			return false; // 错误
		}
	}

	$(document).on("change", "input[type='file']", function() {
		var filename = $(this).val();
		//  $(".active-img").attr("src",filename);
		if (isIOS()&&checkImage(filename)) {	//ios时验证图片格式
			return;
		}
		;
		$.ajaxFileUpload({
			url : basePath + "/h5/micrograph/upload", //用于文件上传的服务器端请求地址  
			secureuri : false, //一般设置为false  
			fileElementId : $(this).attr("id"), //文件上传控件的id属性  
			dataType : 'json', //返回值类型 一般设置为json  
			success : function(data, status) //服务器成功响应处理函数  
			{
				if (status == "success" || data.state == 0) {
					$(".active-img").attr("src", data.result.url);
				}

			},
			error : function(data, status, e) //服务器响应失败处理函数  
			{
				Tips.show("网络不稳定,请稍后再试!");
				loading.hide();
			}
		})

		/*$.ajax({
            type: "POST",
            url: basePath+"/api/v1/image/upload",
            data: { image: filename },
            cache: false,
            success: function(data) {
                alert("上传成功");
                
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
            }
        });*/

	/*var $file = $(this);
	var fileObj = $file[0];
	var windowURL = window.URL || window.webkitURL;
	var dataURL;
	var $img = $(".active-img");

	if(fileObj && fileObj.files && fileObj.files[0]){
	    dataURL = windowURL.createObjectURL(fileObj.files[0]);
	    $img.attr('src',dataURL);
	}else{
	    dataURL = $file.val();
	    var imgObj = document.getElementsByClassName("active-img")[0];
	    // 两个坑:
	    // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
	    // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
	    imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
	    imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

	}*/
	});
	//禁止上下拉动页面
	document.addEventListener('touchmove', function(event) {
		if (event.target.type == 'range') return;
		event.preventDefault();
	})
	$("#thumblist").width($("#thumblist li").length * 89 + 20);
	//点击上一页下一页
	// $(".pageChange-btn span").on("click",function () {
	//     if(!$(this).hasClass("active")){
	//         $(this).addClass("active").siblings().removeClass("active");
	//     }
	// });
	// //点击上一页
	// $(".pageChange-btn span.prev-page").on("click",function () {
	//     var num=$(".editor-bg").index($(".active"));
	//     if(num!=0){
	//         $(".active").removeClass("active");
	//         $(".editor-bg").eq(num-1).addClass("active");
	//     }
	// });
	//点击下一页
	// $(".pageChange-btn span.next-page").on("click",function () {
	//     var num=$(".editor-bg").index($(".active"));
	//     if(num!=$(".editor-bg").length-1){
	//         $(".active").removeClass("active");
	//         $(".editor-bg").eq(num+1).addClass("active");
	//     }
	// });
	//点击出现页面选择
	// $(".page-btn").on("click",function () {
	//     $("#moveid").slideDown();
	//     $("#moveid li").eq($(".active").index()).addClass("tb-selected").siblings().removeClass("tb-selected");
	// });
	// $(document).on("touchstart",function(e){
	//     var target = $(e.target);
	//     if(target.closest("#moveid").length == 0&&target.closest(".page-btn").length == 0){
	//         $("#moveid").slideUp();
	//     }
	//     if(target.closest(".editor-bg img").length == 0&&target.closest(".editorImg-nav").length == 0){
	//         $(".editorImg-nav").slideUp();
	//     }
	// });
	//点击页面缩略图
	$("#thumblist li").on("click", function() {
		$(this).addClass("tb-selected").siblings().removeClass("tb-selected");
		var index = $(this).index();
		mySwiper.slideTo(index, 1000, false);

	});
	function slidePage() {
		var isdrag = false;
		var tx,
			x;
		document.getElementById("moveid").addEventListener('touchend', function() {
			sdrag = false;
		});
		document.getElementById("moveid").addEventListener('touchstart', selectmouse);
		document.getElementById("moveid").addEventListener('touchmove', movemouse);
	}
	function movemouse(e) {
		e.preventDefault();
		if (isdrag) {
			var n = tx + e.touches[0].pageX - x;
			var m = $("#thumblist").width() - $(window).width() + 5;
			if (n > 0 || n < -m) {
				return true;
			}
			$("#moveid").css("left", n);
			return false;
		}
	}
	function selectmouse(e) {
		isdrag = true;
		tx = parseInt(document.getElementById("moveid").style.left + 0);
		x = e.touches[0].pageX;
		return false;
	}
})();