/**
 * Created by Administrator on 2016/12/2.
 */


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