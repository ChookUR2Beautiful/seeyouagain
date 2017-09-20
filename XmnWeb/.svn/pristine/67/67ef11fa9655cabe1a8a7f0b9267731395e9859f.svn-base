var timerModule = null;
var isPlay = 0;
$(function () {

   startPlay(); // 初始化
    $(".horse-info-module").on('mouseenter', function (e) {
        e.stopPropagation();
        stopPlay();
        return false;
    });
    $(".horse-info-module").on('mouseleave', function (e) {
        e.stopPropagation();
        startPlay();
        return false;
    });

    /**
     * 发送消息
     */
    $(".add-usre-module").on('click','.add-send',function(){
      var _that = $(this);
      var liveRecordId=$("#liveRecordId").val();
      var phone =  $(this).parents(".add-user-item").attr("data-id");//手机号码
      var text =   $(this).parents(".add-user-content").find('.user-content-ingput').val();//消息
      
      var nowTime = new Date().getTime();
      var clickTime = $(this).attr("sendTime");
      if( clickTime != 'undefined' && (nowTime - clickTime < 2000)){
    	  showWarningWindow("warning","操作过于频繁，请稍后再试.",9999);
          return false;
       }else{
          $(this).attr("sendTime",nowTime);
       } 
      
        if(text==""){
        	showWarningWindow("warning","发送内容不能为空",9999);
        }else{
        	 $.ajax({
        	        type: "POST",
        	        url: 'liveVideo/manage/enterChatRoom/sendChatMsg.jhtml' + '?t=' + Math.random(),
        	        data:{"liveRecordId":liveRecordId,"phone":phone,"text":text},
        	        dataType: "json",
        	        success: function(data){
        	        	_that.parents(".add-user-content").find('.user-content-ingput').val('');
        	        }
        	    });
        }
    });
    
    /**
     * 删除输入框
     */
    $(".add-usre-module").on('click','.add-detele',function(){
        $(this).parents(".add-user-item").remove();
        var userModuleHeight = $(".add-usre-module").height();
        $(".horse-info-module .horse-info-position").css("height",userModuleHeight);
    });
    
    
    //添加用户事件 end
    $('#myModal').modal('hide');
    $('#myModal').on('hidden.bs.modal', function (e) {
        $(".modal-input-module").val("");
    });
    
    $('#addUserList').on('click', function (e) {
        var iphoneVal =$(".modal-input-module").val();
        if(iphoneVal==""){
            showWarningWindow("warning","请输入手机号码",9999);
        }else{
        	$('#myModal').modal('hide');
            addUserHtml();
            $(".modal-input-module").val("");
        }

    });


});
/*计时添加内容 start*/
function startPlay() {
    isPlay = 1;
//    timerModule = setTimeout(loadMessage, 5000);
    timerModule = setInterval(loadMessage, 5000);
}
function stopPlay() {
    isPlay = 0;
    clearTimeout(timerModule);
}

/**
 * 加载服务器消息,每5秒执行一次
 */
function loadMessage() {
	var liveRecordId=$("#liveRecordId").val();
	var maxId=$("#maxId").val();
    $.ajax({
        type: "POST",
        url: "liveVideo/manage/enterChatRoom/loadMessage.jhtml" + '?t=' + Math.random(),
        data:{"liveRecordId":liveRecordId,"maxId":maxId},
        dataType: "json",
        success: function(data){
        	if(data.success){
        		 var content = '';
        		//加载消息列表
				 $.each(data.data, function (n, obj) {  
					 content +='<div class="horse-info-item">'
							 +		'<div class="horse-info-content"><span class="horse-info-name">'+obj.nname+'</span><span class="horse-info-text">'+obj.messagerTxt+'</span></div>'
							 + '</div>';
		          });
                 $(".horse-info-wrap").append(content);
                 if(data.data[0]){
                	 $("#maxId").val(data.data[0].id);
                	 console.log(data.data[0].id);
                 }
        	}
            slideTop();
            /*if (isPlay == 1) {
                timerModule = setTimeout(loadMessage, 5000);
            }*/
        }
    });
}


function slideTop() {
    var containtHeight = $(".horse-info-position").height();
    var containtScrollHeight = $(".horse-info-wrap").height();
    if (containtScrollHeight > containtHeight || containtScrollHeight == containtHeight) {
        $(".horse-info-wrap").css({
            'transition':'transform .5s',
            '-webkit-transition':'transform .5s',
            'transform':'translateY(-'+(containtScrollHeight-containtHeight)+'px)',
            '-webkit-transform':'translateY(-'+(containtScrollHeight-containtHeight)+'px)'
        });
    }
}

/*计时添加内容 end*/
/*添加用户 start*/
function addUserHtml() {
	 var phone =$(".modal-input-module").val();
    $.ajax({
        type: "POST",
        url: 'liveVideo/manage/checkAccount.jhtml' + '?t=' + Math.random(),
        data:{"phone":phone},
        dataType: "json",
        success: function(res){
        	if(res.success){
        		var html = '';
                html += '<div class="add-user-item" data-id="'+res.data.phone+'">';
                html += '<div class="add-user-name">用户昵称：'+res.data.nickname+'</div>';
                html += '<div class="add-user-content">';
                html += '<div class="user-content-btn">';
                html += '<span class="add-send btn-item" sendTime="">发送</span>';
                html += '<span class="add-detele btn-item">删除</span>';
                html += '</div>';
                html += '<input type="text" class="user-content-ingput">';
                html += '</div>';
                html += '</div>';
                $(".add-user-list").prepend(html);
                var userModuleHeight = $(".add-usre-module").height();
                $(".horse-info-module .horse-info-position").css("height",userModuleHeight);
        	}else{
        		 showWarningWindow("warning",res.msg,9999);
        	}
            
        }
    });
}
/*添加用户 end*/