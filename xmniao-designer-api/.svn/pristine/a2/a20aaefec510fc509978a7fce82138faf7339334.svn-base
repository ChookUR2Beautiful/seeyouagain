/**
 * 滚动翻页 （自定义实现此方法）
 *
 */

 /*添加css*/

 /*



 */

console.log('ag');
var isLoading = 0; 
window.onscroll = onScrollEvent;
document.addEventListener('touchmove', onScrollEvent , false);
/*初始页码*/
var page = 1;

function onScrollEvent(){
    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
    
    if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 20){
        isLoading == 0 && loadData(false);
    }
}

window.onload = initScroll;
function initScroll(){
    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
    
    parseInt(clientHeight) < parseInt(scrollHeight - 20) && $('#thelist .contentbox').append('<div class="nowloading"><i></i></div>');
}
/**
 * 滚动翻页 （自定义实现此方法）
 * 
 */

 /* PageCount 总页数 */


function loadData(isRest){
	isLoading = 1;
	page++;
    $.ajax({
        method: "Get",
        url: "ajaxGetAnchor",
		 data:{"pageNo":page},
        dataType:"json",
        success: function (data) {
        	if(data.state==100){
        		$.each(data.response.anchorList,function(i,v){
        			var _html = '';
            		_html += '<a class="user-item" href="toViewMaxImage?id='+v.id+'&height='+v.height+'&weight='+v.weight+'&name='+v.name+'&three_dimensional='+v.three_dimensional+'&self_comment='+v.self_comment+'">';
            		_html += '<div class="user-content item-thumbnail-left"> <img class="item-img" src="'+v.avatar +'">';
            		_html += '<div class="item-title"><em class="user-fals-num">粉丝量'+v.concerned_nums+'</em>'+v.name+'</div>';
            		_html += '<ul class="item-info">';
            		_html += '<li><em>身高：</em>'+v.height+'cm</li>';
            		_html += '<li><em>体重：</em>'+v.weight+'kg</li>';
            		_html += '<li><em>三围：</em>'+v.three_dimensional+'</li>';
            		_html += '</ul>';
            		_html += '<p class="item-desc">'+v.self_comment+'</p></div>';
            		_html += '</a>';
            		$('.live-user-list').append(_html); 
        		});
        		isLoading = 0;       
        	}else{
        		
        		$('.nowloading').remove();
        		
                var _html = '<div class="end">已经到底了!</div>';
                $('.live-user-list').append(_html);

                
        	}
        },
        error:function(data){
            console.log('数据出错')
        }

    })
	
	
	
	

}
