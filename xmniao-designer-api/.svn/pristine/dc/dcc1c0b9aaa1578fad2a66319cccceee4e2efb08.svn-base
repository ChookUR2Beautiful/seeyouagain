/**
 * Created by Administrator on 2016-10-11.
 */

/*页面翻页*/
var isLoading = 0;//进行中
var isLoading2 = 0;//已结束

/*当前页数*/
var currentPage =1;//进行中
var currentPage2 = 1;//已结束
/*每页显示条数*/
var pageSize = 5;

/*总条数*/
var totalCountIng = $("#totalCountIng").val(); //进行中总条数
var totalCountEnd =$("#totalCountEnd").val();//已结束总条数

var totalPageIng=Math.ceil(totalCountIng / pageSize); //进行中总页数
var totalPageEnd=Math.ceil(totalCountEnd / pageSize); //已结束总页数

var jinxing =basePath+"/h5/redpacket/list_by?status=1,2,3";
var jiesu =basePath+"/h5/redpacket/list_by?status=0";

window.onscroll = onScrollEvent;
document.addEventListener('touchmove', onScrollEvent , false);
var hasData = 1;

function onScrollEvent(){
    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
    var slideActive = $(".container-wrap .slide-tab-wrap span.active a").attr("data-slide");
    var slideContent = $(".container-wrap .swiper-container div.swiper-slide-active .content-slide-wrap");
    var slideContentSize = $(".container-wrap .swiper-container div.swiper-slide-active .slide-item").size();
    if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 80 ){
        if(slideActive=='jinxing' && isLoading ==0){
        	isLoading=1;
            if(totalPageIng > currentPage){
                loadData(slideActive,jinxing,slideContent,currentPage);
            }else{
            	if(totalCountIng>0){
            		slideContent.append('<div class="end">已经到底了</div>');
            	}
            }
        }else if(slideActive=='jiesu' && isLoading2 ==0){
        	isLoading2=1;
            if(totalPageEnd > currentPage2){
                loadData(slideActive,jiesu,slideContent,currentPage2);
            }else{
            	if(totalCountEnd>0){
            		slideContent.append('<div class="end">已经到底了</div>');
            	}
            }
        }
    }
}

function loadData(state,addressUrl,contentBox,currentPage){
    $.ajax({
        type: "POST",
        url: addressUrl,
        data: {
        	"sellerid" : sellerid
        },
        dataType: "json",
        success: function(response){
        	if(response.result.code==0 && typeof(response.result.data)!='undefined'){
        		$(response.result.data).each(function (index,domEle){
        			var sellerid=domEle.sellerid;
        			var redpacketId=domEle.redpacketId;
        			var redpacketType=domEle.redpacketType;
        			var status=domEle.status;
        			
        			var redpacketName=domEle.redpacketName;
        			var recordNumber=domEle.recordNumber;
        			if(typeof(recordNumber)=='undefined' || recordNumber==null){
        				recordNumber=0;
        			}
        			var views=domEle.views;
        			if(typeof(views)=='undefined' || views==null){
        				views=0;
        			}
        			var totalAmount=domEle.totalAmount;
        			var chargeBalance=domEle.chargeBalance;
        			var beginDate=domEle.beginDate;
        			var endDate=domEle.endDate;
        			
        			var _html ='<div class="slide-item">';
    	            _html+='<div class="slide-item">';
    	            _html+='<a class="item-links" href="javascript:void(0)" onclick="detailData('+sellerid+','+redpacketId+','+redpacketType+','+status+')">';
    	            _html+='<div class="item-head">';
    	            _html+='<span class="tit-desc">';
    	            if(status==0){
    	            	switch (redpacketType) {
	    					case 0:
	    						 _html+='<i class="icon-wrap icon-xiang-2"></i>';// 分享引流红包
	    						break;
	    					case 1:
	    						_html+='<i class="icon-wrap icon-xian-2"></i>';// 限时到店红包
	    						break;
	    					case 2:
	    						_html+='<i class="icon-wrap icon-jian-2"></i>';// 消费满赠红包
	    						break;
	    					case 3:
	    						_html+='<i class="icon-wrap icon-tui-2"></i>';// 推荐消费红包
	    						break;
	    					case 4:
	    						_html+='<i class="icon-wrap icon-chou-2"></i>';// 普通抽獎红包
	    						break;
	    					default:
	    						break;
    				     }
   	                 }else if(status==1){
   	                	switch (redpacketType) {
	    					case 0:
	    						 _html+='<i class="icon-wrap icon-xiang"></i>';// 分享引流红包
	    						break;
	    					case 1:
	    						_html+='<i class="icon-wrap icon-xian"></i>';// 限时到店红包
	    						break;
	    					case 2:
	    						_html+='<i class="icon-wrap icon-jian"></i>';// 消费满赠红包
	    						break;
	    					case 3:
	    						_html+='<i class="icon-wrap icon-tui"></i>';// 推荐消费红包
	    						break;
	    					case 4:
	    						_html+='<i class="icon-wrap icon-chou"></i>';// 普通抽獎红包
	    						break;
	    					default:
	    						break;
					     }
   	                 }
    	            _html+=redpacketName+'</span>';
    	            _html+='</div>';
    	            _html+='<div class="item-content">';
    	            _html+='<span class="content-size">';
    	            _html+='<b>'+recordNumber+'</b>';
    	            _html+='<em>领取个数</em>';
    	            _html+='</span>';
    	            _html+='<span class="content-time">';
    	            _html+='<b>'+views+'</b>';
    	            _html+='<em>浏览次数</em>';
    	            _html+='</span>';
    	            _html+='</div>';
    	            _html+='<div class="item-floor">';
    	            _html+='<span class="floor-limit">';
    	            _html+='<em class="limit-total">红包总额：￥'+totalAmount+'</em>';
    	            _html+='<em class="limit-sum">剩余金额：￥'+chargeBalance+'</em>';
    	            _html+='</span>';
    	            _html+='<span class="floor-time">';
    	            _html+='<em class="time-date">'+beginDate+'-'+endDate+'</em>';
    	            _html+='<div class="more-links"><i></i><i></i><i></i></div>';
    	            _html+='</span>';
    	            _html+='</div>';
    	            _html+='</a>';
    	            _html+=' <a class="icon-wrap share-links" href="javascript:void(0)"></a>';
    	            _html+='</div>';
    	            contentBox.append(_html);
    	            if(state=='jinxing'){
      	                currentPage++;
      	                isLoading = 0;
      	            }else if(state=='jiesu'){
      	                currentPage2++;
      	                isLoading2 = 0;
      	            }
        		});
        	}
        }
    });
}