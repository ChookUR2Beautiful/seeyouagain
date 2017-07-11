function pullUpload(options)
{
  this.url = options.url;
  this.isloading = 0;
  this.end = 0;
  this.success = options.success;
  this.params = options.params;
  this.initData = options.initdata;
  this.beforeSend = options.beforeSend;

  this.init();
}

pullUpload.prototype.init = function(){
  var _this = this;
  window.onscroll = function(){
    _this.onScrollEvent(_this);
  };
  document.addEventListener('touchmove', function(){
    _this.onScrollEvent(_this);
  } , false);
  console.log(_this.initData);
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
    _this.isloading == 0 && _this.loadData(_this);
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





