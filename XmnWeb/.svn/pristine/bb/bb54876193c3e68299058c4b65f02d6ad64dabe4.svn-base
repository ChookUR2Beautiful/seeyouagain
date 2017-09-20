// 初始化Web Uploader
//文件使用webUploader插件
//插件地址http://fex.baidu.com/webuploader/
//使用方法都在网站文档里面了
var BASE_URL = '/XmnWeb/';

$.fn.uploadImg = function(param){
	var $thisDom = $(this).css("width","110px");
	
	var defaults = {
		runtimeOrder : "html5,flash",
		editable : true,
		picker : ".picker",
		showImg : "",
		emptyImg : "././resources/upload/add.png",
		urlId : "",
		breviaryId:"",
		auto : true,
		pickerName : "选择图片",
		fileNumLimit : 1,
		fileVal : "filedata",
		width : 0,//限制上传图片宽度
		height : 0,//限制上传图片高度
		size : 0,//限制图片大小（kb）
		server : BASE_URL + 'uploadFile.jhtml?sltflag='+$("#sltflag").val(),
		callback : null,
		filterMsg : "上传格式（gif,jpg,jpeg,bmp,png,ico）",//文件不符合格式是显示提示文本
		accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png,ico',
	        mimeTypes: 'image/*'
	    },//限制图片格式
		multiple: false
	}
	
	var opts = $.extend(defaults, param);
	if(opts.urlId && !opts.showImg){
		opts.showImg = $("#" + opts.urlId).val();
	}
	
	var html = [];
	
	html.push('<div class="uploader" style="float:left;">');
	html.push('<p class="title"></p>');
	html.push('<p class="imgWrap picker"><img class="img-thumbnail" style="width:100px;height:100px;" src="'+(opts.showImg ? $('#fastfdsHttp').val()+opts.showImg : opts.emptyImg)+'"/></p>');
	html.push('<p class="progress"><span></span></p>');
	html.push('<div class="file-panel">');
	if(opts.editable){
		html.push('<span class="glyphicon glyphicon-remove icon-remove-sign" style="color: rgb(212, 106, 64);" title="删除"></span>');
//		html.push('<span class="icon-undo"  title="还原"></span>');
	}
	html.push('</div>');
	html.push('</div>');
	$(this).html(html.join(''));
	
	var uploader = WebUploader.create({
		
		runtimeOrder : opts.runtimeOrder,
		
		auto : opts.auto,
	    // swf文件路径
	    swf: BASE_URL + 'resources/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: opts.server,
	    
	   /* //是否分片上传
	    chunked:true,
	    //分片大小
	    chunkSize: 524288000,*/
	    
	    //限制最多一张图片
//	    fileNumLimit : opts.fileNumLimit,

	    // 只允许选择图片文件。
	    accept : opts.accept,
	    
	    
	    fileVal : opts.fileVal
	});
	
	uploader.on( 'fileQueued', function( file ) {
		$thisDom.popover('hide');
		var files = uploader.getFiles();
		for(var i = 0;i < files.length - opts.fileNumLimit; i++){
			uploader.removeFile(files[i]);
		}
		if(opts.size){
			if(file.size > opts.size * 1024){
				uploader.removeFile(file);
				showPopover($thisDom, "图片大小不能超过" + opts.size + "KB");
			}
		}else{
			// 创建缩略图
		    // 如果为非图片文件，可以不用调用此方法。
		    uploader.makeThumb( file, function( error, src ) {
		    	//验证图片宽度高度
		        if(!((opts.width == 0 || file._info.width <= opts.width) && (opts.height == 0 ||file._info.height <= opts.height))){
		        	uploader.removeFile(file);
		        	showPopover($thisDom,"图片尺寸不超过" + opts.width + "*" + opts.height);
		        }else{
		        	if ( error ) {
			        	$('#' + thumbId).replaceWith('<span>不能预览</span>');
			            return;
			        }
			        $thisDom.find('img').attr( 'src', src );
		        }
		    });
		}
		
		
	    
	});
	
	uploader.on("error", function(type){
		if(type == 'Q_TYPE_DENIED'){
			showPopover($thisDom,opts.filterMsg);
		}
	});
	
	uploader.on("uploadProgress", function(file, percentage){
		var progessBar = $thisDom.find('.progress span');
		progessBar.show().css( 'width', percentage * 100 + '%' );
		$("#pro1").val(percentage);
	});
	
	uploader.on("uploadSuccess", function(file, response){
		var progessBar = $thisDom.find('.progress span').hide();
		var res = eval(response);
//		console.info(res);
		if(res.status == 1){
			var relativePath = res.relativePath;
			$thisDom.find('img').attr( 'src', $('#fastfdsHttp').val()+relativePath );
			$thisDom.attr("upload",true);
//			console.info(opts);
			if(opts.urlId){
				var urldom = $("#" + opts.urlId);
				if(urldom){
					urldom.val(res.relativePath);
				}
			}
			if(opts.breviaryId){
				var breviarydom = $("#" + opts.breviaryId);
				if(urldom){
					breviarydom.val(res.relativeBreviaryPath);
				}
			}
			if(opts.callback){
				opts.callback(relativePath);
			}
		}
	});
	
	
	$thisDom.find(".icon-remove-sign").click(function(){
		$thisDom.find('img').attr('src',opts.emptyImg);
		$("#" + opts.urlId).val("");
		$("#" + opts.breviaryId).val("");
		uploader.reset();
	});
	
	$thisDom.find(".icon-undo").click(function(){
		if(opts.showImg){
			$thisDom.find('img').attr('src',opts.showImg);
			$("#" + opts.urlId).val(opts.showImg);
		}
	});
	
	setTimeout(function(){
		//如果可编辑，则添加选择图片按钮
		if(opts.editable){
			uploader.addButton({
		    	id : $thisDom.find(opts.picker),
		    	multiple : opts.multiple
			});
		}
	},500);
	return uploader;
}


$.fn.uploadFile = function(param){
	var $thisDom = $(this);
	var defaults = {
		nameId : "",
		sizeId : "",
		urlId : "",
		breviaryId:"",
		runtimeOrder : "html5,flash",
		editable : true,
		picker : ".picker",
		showImg : "",
		auto : true,
		fileNumLimit : 1,
		fileVal : "filedata",
		width : 100,
		height : 100,
		server : BASE_URL + 'uploadFile.jhtml?sltflag='+$("#sltflag").val(),
		callback : null,
		filterMsg : "请选择正确格式的文件！",//文件选择错误的提示
		accept: {
	        title: 'File',
	        extensions: '*',
	        mimeTypes: '*'
	    },
		multiple: false,
		isImport:false//是否是导入  false:不是  true:是，  默认false
	}
	
	var opts = $.extend(defaults, param);
	var html = [];
	
	html.push('<div class="progress progress-striped active" style="width:200px;display: none;float:left">');
	html.push('<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 50%">');
	html.push('<span class="percent">50%</span></div></div>');
	html.push('<button class="btn btn-link url" type="button" style="display:none;float:left">文件下载</button>');
	html.push('<div class="picker" style="float:left;"><button type="button" class="btn btn-primary">选择文件</button></div>');
	html.push('<button type="button" class="btn btn-danger cancel" style="display: none;float:left;margin-left: 5px"><i class="icon-remove"></i></button>');
	$thisDom.html(html.join(''));
	
	if(opts.urlId && $("#" + opts.urlId).val()){
		$thisDom.find(".url").show().click(function(){
			window.open($('#fastfdsHttp').val() + $("#" + opts.urlId).val(), "_blank");
		});
	}
	
	
	var uploader = WebUploader.create({
		
		runtimeOrder : opts.runtimeOrder,
		
		auto : opts.auto,
	    // swf文件路径
	    swf: BASE_URL + 'resources/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: opts.server,
	    
	   /* //是否分片上传
	    chunked:true,
	    //分片大小
	    chunkSize: 524288000,*/
	    
//	    fileNumLimit : opts.fileNumLimit,

	    
	    accept: opts.accept,
	    
	    fileVal : opts.fileVal
	});
	
	// 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
		$thisDom.find(opts.picker).popover('hide');
		var files = uploader.getFiles();
		for(var i = 0;i < files.length - opts.fileNumLimit; i++){
			uploader.removeFile(files[i]);
		}
	});
	
	$thisDom.find(opts.picker).popover({
		content : opts.filterMsg,
		placement : "top",
		trigger : "focus"
	});
	
	uploader.on("error", function(type){
		if(type == 'Q_TYPE_DENIED'){
			$thisDom.find(opts.picker).popover('show');
		}
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on("uploadProgress", function(file, percentage){
		var percent = Math.round(percentage * 100) + '%';
		$thisDom.find('.progress').show().find(".progress-bar").css( 'width', percent );
		$thisDom.find(".percent").html(percent);
		$thisDom.find(".picker").hide();
		$thisDom.find(".cancel").show();
	});
	
	//文件上传成功
	uploader.on("uploadSuccess", function(file, response){
		$thisDom.find('.progress').hide();
		var res = eval(response);
		if(res.status == 1){
			if(!opts.isImport){
				var relativePath = res.relativePath;
				var url = $('#fastfdsHttp').val()+relativePath;
				$thisDom.find(".url").attr("title",file.name).html(substr(file.name,12)).show().click(function(){
					window.open(url, "_blank");
				});
				$thisDom.attr("upload",true);
				setValue(opts.urlId, relativePath);
				setValue(opts.breviaryId, relativeBreviaryPath);
				setValue(opts.nameId, file.name);
				setValue(opts.sizeId, file.size);
				
				if(opts.callback){
					opts.callback(relativePath);
				}
			}else{
				opts.callback.call(this,res);
			}
			
		}else{
			if(opts.isImport){
				opts.callback.call(this,res);
			}
		}
	});
	
	$thisDom.find(".cancel").click(function(){
		$thisDom.find('.url').hide();
		setValue(opts.urlId, "");
		setValue(opts.breviaryId, "");
		setValue(opts.nameId, "");
		setValue(opts.sizeId, "");
		$thisDom.find(".picker").show();
		$thisDom.find(".cancel").hide();
		$thisDom.find('.progress').hide();
		if(uploader.isInProgress()){
			uploader.stop(true);
		}
		uploader.reset();
	});
	
	setTimeout(function(){
		//如果可编辑，则添加选择图片按钮
		if(opts.editable){
			uploader.addButton({
		    	id : $thisDom.find(opts.picker),
		    	multiple : opts.multiple
		    });
		}
	}, 500);
	
	return uploader;
}
function setValue(domId,value){
	if(domId){
		var $dom = $("#"+domId);
		if($dom){
			$dom.val(value);
		}
	}
}
/**
 * 截取字符串
 * @param obj
 * @param length
 * @returns {String}
 */
function substr(obj,length){
	if(null == length){
		length = 8;
	}
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}