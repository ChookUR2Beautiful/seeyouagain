<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>选择创建红包类型</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css?v=1.0" />
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</head>
<body class="packer-body">
	<div class="container-wrap" style="height: 100%;">
		<div class="swiper-container marketing-slide" id="marketing-domule">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="swiper-contentbox">
						<div class="marke-img-wrap"
							style="background-image: url(${ctx}/imgs/redpacket/fenxiang-ad2x.png);"></div>
						<div class="marke-info-text">
                            <img src="${ctx}/imgs/redpacket/share_text1.png">
                        </div>
						<div class="marke-info-wrap">
							<p>无限制领取红包，领取后分享至朋友圈，引来下线用户，即可领取二次裂变红包，只允许二级裂变！</p>
						</div>
						<div class="marke-btn-wrap">
							
						</div>
						<div class="marke-links-wrap">
							<a class="check-links" href="javascript:void(0)">查看使用效果</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="swiper-contentbox">
						<div class="marke-img-wrap"
							style="background-image: url(${ctx}/imgs/redpacket/xianshi-ad@2x.png);"></div>
						<div class="marke-info-text">
                            <img src="${ctx}/imgs/redpacket/share_text2.png">
                        </div>
						<div class="marke-info-wrap">
							<p>用户需要按照商户限定时间到指定地点，打开用户端才可以领取商户红包！</p>
						</div>
						<div class="marke-btn-wrap">
						</div>
						<div class="marke-links-wrap">
							<a class="check-links" href="javascript:void(0)">查看使用效果</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="swiper-contentbox">
						<div class="marke-img-wrap"
							style="background-image: url(${ctx}/imgs/redpacket/xiaofei-ad@2x.png);"></div>
						<div class="marke-info-text">
                            <img src="${ctx}/imgs/redpacket/share_text3.png">
                        </div>
						<div class="marke-info-wrap">
							<p>用户消费满所限定的金额后，即可获赠红包！</p>
						</div>
						<div class="marke-btn-wrap">
						</div>
						<div class="marke-links-wrap">
							<a class="check-links" href="javascript:void(0)">查看使用效果</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="swiper-contentbox">
						<div class="marke-img-wrap"
							style="background-image: url(${ctx}/imgs/redpacket/tuijin-ad@2x.png);"></div>
						<div class="marke-info-text">
                            <img src="${ctx}/imgs/redpacket/share_text4.png">
                        </div>
						<div class="marke-info-wrap">
							<p>推荐好友到商户处消费，消费结账时填写推荐人的手机，则推荐人可获得红包！</p>
						</div>
						<div class="marke-btn-wrap">
						</div>
						<div class="marke-links-wrap">
							<a class="check-links" href="javascript:void(0)">查看使用效果</a>
						</div>
					</div>
				</div>
				<div class="swiper-slide">
					<div class="swiper-contentbox">
						<div class="marke-img-wrap"
							style="background-image: url(${ctx}/imgs/redpacket/choujiang-ad@2x.png);"></div>
						<div class="marke-info-text">
                            <img src="${ctx}/imgs/redpacket/share_text5.png">
                        </div>
						<div class="marke-info-wrap">
							<p>针对所有用户，商户限定红包总金额、个数及随机范围值，用户领取红包后由系统分配金额！</p>
						</div>
						<div class="marke-btn-wrap">
						</div>
						<div class="marke-links-wrap">
							<a class="check-links" href="javascript:void(0)">查看使用效果</a>
						</div>
					</div>
				</div>
			</div>
			<!-- 如果需要分页器 -->
			<div class="swiper-pagination"></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>
<script type="text/javascript">
	var sellerid="${requestScope.sellerid}"; //商家id
    var redpacketType=0; //红包类型(默认为 0： 分享引流红包)
    
	$(document).ready(function(){
		setFunction(sellerid,redpacketType);
		var mySwiper = new Swiper('.swiper-container', {
			// 如果需要分页器
			pagination : '.swiper-pagination',
			slidesPerView : 'auto',
			centeredSlides : true,
			paginationClickable : true,
			onSlideChangeEnd:function(swiper){
				//左右滑动事件
				redpacketType=swiper.activeIndex;
				setFunction(sellerid,redpacketType);
            }
		});

		var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;
		var clientWidth = document.body.clientWidth || document.documentElement.clientWidth || window.innerWidth;
		var widthPecent = 0.83;
		var curWidth = clientWidth * widthPecent * 0.88;
		var imgProportion = 600 / 1000;
		var imgHeight = curWidth / imgProportion;
		var initPadding = (clientHeight - imgHeight) / 2;

		// var textMarTop = 240 * imgHeight / 1000;
		// $('.marke-info-wrap').css('marginTop', textMarTop + 'px');
		$('.swiper-contentbox').css('paddingTop', initPadding);
		$('.marke-btn-wrap').css({'position':'absolute','width': '100%','left': '0',bottom:(initPadding + 23)+'px'});
        $('.marke-links-wrap').css({'position':'absolute','width': '100%','left': '0',bottom:(initPadding + 3)+'px'});
        /*20161111*/
        if( parseInt(clientWidth) / parseInt(clientHeight) > 0.70 ){
            $('.marke-info-text').css('marginTop','16px');
        }
        /*20161111 END*/
	});
    
	//根据商家id,红包类型检查是否存在数据
	function checkIsExistData(sellerid,redpacketType) {
		var flag = false;
		$.ajax({
			url : '${ctx}/h5/redpacket/list_by',
			type : "post",
			dataType : "json",
			async : false,
			data : {
				"sellerid" : sellerid,
				"redpacketType" : redpacketType
 			},
			success : function(response) {
				if(response.result.code==0 && typeof(response.result.data)!='undefined'){
					var data=response.result.data;
					$(data).each(function(index,element){
						var status=element.status;
						if(status==1 || status==2 || status==3){
							flag=true;
						}
					});
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		})
		return flag;
	};
    
    
    //设置
    function setFunction(sellerid,redpacketType){
    	if (checkIsExistData(sellerid,redpacketType)) {
			$(".swiper-slide .swiper-contentbox .marke-btn-wrap").html("<a class='links-btn' href='javascript:void(0)' onclick='inBusiness(this)' attrid='"+ redpacketType +"'>正在运营中</a>");
		} else {
			$(".swiper-slide .swiper-contentbox .marke-btn-wrap").html("<a class='links-btn' href='javascript:void(0)' onclick='startCreate(this)' attrid='"+ redpacketType +"'>开始创建</a>");
		}
    }

	//开始创建事件
	function startCreate(_this) {
		window.location.href = "${ctx}/h5/redpacket/input?sellerid="+sellerid+"&redpacketType=" + $(_this).attr('attrid');
	};

	//正在运营事件
	function inBusiness(_this) {
		window.location.href = "${ctx}/h5/redpacket/detail?sellerid="+sellerid+"&redpacketType="+$(_this).attr('attrid');
	};
</script>
</html>