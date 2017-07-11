function pullUpload(options)
{
	this.url = options.url;
	this.isloading = 0;
	this.end = 0;
	this.success = options.success;
	this.params = options.params;
	this.initData = options.initdata;
	this.beforeSend = options.beforeSend;
	this.clear=false;
	this.init();
}
//返回角度
        function GetSlideAngle(dx, dy) {
            return Math.atan2(dy, dx) * 180 / Math.PI;
        }

//根据起点和终点返回方向 1：向上，2：向下，3：向左，4：向右,0：未滑动
        function GetSlideDirection(startX, startY, endX, endY) {
            var dy = startY - endY;
            var dx = endX - startX;
            var result = 0;

            //如果滑动距离太短
            if (Math.abs(dx) < 2 && Math.abs(dy) < 2) {
                return result;
            }

            var angle = GetSlideAngle(dx, dy);
            if (angle >= -45 && angle < 45) {
                result = 4;
            } else if (angle >= 45 && angle < 135) {
                result = 1;
            } else if (angle >= -135 && angle < -45) {
                result = 2;
            }
            else if ((angle >= 135 && angle <= 180) || (angle >= -180 && angle < -135)) {
                result = 3;
            }

            return result;
        }

//滑动处理
        var startX, startY;
        
pullUpload.prototype.init = function(){
	var _this = this;
	// window.onscroll = function(){
	// 	_this.onScrollEvent(_this);
	// };
	document.addEventListener('touchstart', function (ev) {
            startX = ev.touches[0].pageX;
            startY = ev.touches[0].pageY;
        }, false);
        document.addEventListener('touchmove', function (ev) {
            var endX, endY;
            endX = ev.changedTouches[0].pageX;
            endY = ev.changedTouches[0].pageY;
            var direction = GetSlideDirection(startX, startY, endX, endY);
            switch (direction) {
                case 0:

                    break;
                case 1:
                    //alert("向上");
                    _this.clear=false;
                    _this.onScrollEvent(_this);
                    break;
                case 2:

//                    alert("向下");
                    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                    if(scrollTop==0){
                    	_this.clear=true;
                       _this.params.pageNum=0;
                       _this.loadData(_this);
                    }
                    break;
                case 3:
                    //alert("向左");
                    break;
                case 4:
                    //alert("向右");
                    break;
                default:
            }
        }, false);
	// document.addEventListener('touchmove', function(){
	// 	_this.onScrollEvent(_this);
	// } , false);
	if(_this.initData) _this.loadData(_this);
}

pullUpload.prototype.getData = function(){
	this.loadData(this);
}

pullUpload.prototype.onScrollEvent = function(_this){
	if(_this.end) return;
	var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
    var scrollHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
    var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
    if(parseInt(scrollTop) > parseInt(scrollHeight) - parseInt(clientHeight) - 50){
    	/*有滑动条情况*/
    	if(_this.initdata){
				_this.isloading == 0 && _this.loadData(_this);
			}

    }
}

pullUpload.prototype.loadData = function(_this){
	
	_this.isloading = 1;
	$.isFunction(_this.beforeSend) && _this.beforeSend(_this);
	$.ajax({
        url:_this.url,
        method: "POST",
        data: _this.params,
        dataType: "json",
        success:function(data){
        	setTimeout(function(){
	        	_this.isloading = 0;
	        	$.isFunction(_this.success) && _this.success(_this,data);
        	},1000);
        	
        }
    });
}





