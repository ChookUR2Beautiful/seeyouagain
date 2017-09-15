if (typeof(xmnplayer) === "undefined") {
	
	var xmnplayer = {};
	xmnplayer.setup = function(options){
		console.log(options);
		this.pid = options.pid;
		this.video = null;
		this.file = options.file;
		this.postUrl = options.postUrl;
		this.onPause = options.onPause;
		this.onPlay = options.onPlay;
		this.onError = options.onError;
		this.playing = false;
		this.pause = false;
		this.isremove = false;
		this.initPlayer();
	}

	xmnplayer.initPlayer = function(){
		

	}

	xmnplayer.addEvent = function(video){
		var _this = this;
		video.addEventListener('play',function(){
			$.isFunction(_this.onPlay) && _this.onPlay();
     	},false);

     	video.addEventListener('pause',function(){
			$.isFunction(_this.onPause) && _this.onPause();
     	},false);

     	video.addEventListener('error',function(){
			$.isFunction(_this.onError) && _this.onError();
     	},false);
	}

	xmnplayer.play = function(){
		if($('#'+this.pid).find('video').length == 0 && !this.isremove){
			var clientHeight = document.body.clientHeight || document.documentElement.clientHeight || window.innerHeight;
			var clientWidth = document.body.clientWidth || document.documentElement.clientWidth || window.innerWidth;
			var video = document.createElement("video");
			video.src = this.file;
			video.width = parseInt(clientWidth);
	        video.height = parseInt(clientHeight) - 50;
	        // video.setAttribute("webkit-playsinline","");
	        // video.setAttribute("x5-video-player-type","h5");
	        // video.setAttribute("x5-video-player-fullscreen","false");

	        var div = document.getElementById(this.pid);
	        if(!!div){
	        	div.appendChild(video);
	        }
	        this.video = video;
//	        this.addEvent(video);
		}
		
		if(!this.isremove) this.video.play();
	}
	xmnplayer.stopPlay = function(){
		this.video.pause();
	}
	xmnplayer.remove = function(){
		this.isremove = 1;
		$('#'+this.pid).find('video').remove();
	}

}


