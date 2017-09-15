function showtips(c){
	this.i = 1;
}

showtips.prototype.show = function(content){
	$('body').append('<div class="tipsmodle"><p>'+content+'</p></div>');
	var twidth = $('.tipsmodle').css('width');
	$('.tipsmodle').css({'marginLeft' : - parseInt(twidth) / 2 + "px", left : "50%"}).show();
	this.showAction();
}

showtips.prototype.showAction = function(){	
	var _this = this;
	$('.tipsmodle').animate({opacity : "0.9" , bottom : "25%" },100,"linear",function(){
		var tips_index = this;
		setTimeout(function(){
			_this.destoryTips(tips_index);
		},2000);
	});
}

showtips.prototype.destoryTips = function(_this){	

	$(_this).animate({opacity : "0" , bottom : "30%"},100,"linear",function(){
		$(_this).remove();
	});
	
}	


var loading = {
	show : function(){
		var loadingHtml = '<div class="index-showok-model"></div><div class="index-showok"><i class="loadingimg"></i><p class="loadingp">请稍候</p></div>';
		if($('.index-showok-model').length == 0 ){
			$('body').append(loadingHtml);
			$('.index-showok').animate({opacity : "0.6" },500,"linear");
		}
	},
	hide : function(){
		$('.index-showok').animate({opacity : "0" },500,"linear",function(){
			$('.index-showok-model').remove();
			$('.index-showok').remove();
		});
	}
}


function confirmDiag(options){
	this.title = options.title || '提醒';
	this.content = options.content || '请输入提示内容';
	this.confirmFun = options.confirmFun;
	this.init();
}

confirmDiag.prototype.init = function(){
	var diaHtml = '<div class="confirmdiagmodal"></div><div class="confirmdiag-box"><h3>'+this.title+'</h3><p class="confirmdiag-tips">'+this.content+'</p><a class="confirmdiag-btn" href="javascript:;" data-ajax="false">确定</a></div>';
	$('body').append(diaHtml);
	var _this = this;
	$('.confirmdiag-box .confirmdiag-btn').bind('click',function(){
		$('.confirmdiagmodal').remove();
		$('.confirmdiag-box').remove();
		$.isFunction(_this.confirmFun) && _this.confirmFun();
	});
}


function dialogWithbtn(options){
	this.title = options.title || '温馨提示';
	this.content = options.content || '请输入提示内容';
	this.confirmTxt = options.confirmTxt || '确认';
	this.cancelFun = options.cancelFun;
	this.confirmFun = options.confirmFun;
	this.init();
}

dialogWithbtn.prototype.init = function(){
	var diaHtml = '<div class="confirmdiagmodal"></div><div class="dialogwithbtn-box"><h3>'+this.title+'</h3><p class="dialogwithbtn-tips">'+this.content+'</p><div class="dialogwithbtn-btnbox"><a href="javascript:;" class="dialogwithbtn-cancel" data-ajax="false">取消</a><a href="javascript:;" class="dialogwithbtn-confirm" data-ajax="false">'+this.confirmTxt+'</a></div></div>';
	$('body').append(diaHtml);
	var _this = this;
	$('.dialogwithbtn-box .dialogwithbtn-cancel').bind('click',function(){
		$.isFunction(_this.cancelFun) && _this.cancelFun();
		$('.confirmdiagmodal').remove();
		$('.dialogwithbtn-box').remove();
	});

	$('.dialogwithbtn-box .dialogwithbtn-confirm').bind('click',function(){
		
		var vcode = $('#validevcode').val();
		$.isFunction(_this.confirmFun) && _this.confirmFun(vcode);
		
	});



}




