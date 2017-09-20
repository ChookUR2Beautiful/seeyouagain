var imgRoot = $("#fastfdsHttp").val();
var formId = "materialTemplateForm";
var ISTYPE;
var jsonTextInit;
var ifram_num=0;
var dragresize;
$(function () {
		
		//初始化dragresize
		dragresizeInit();
        //start
        
        //end
        
        ISTYPE = $("#isType").val();
    	if(ISTYPE == "add"){
    		inserTitle(' > <span>添加页面信息</span>','materialEdit',false);
    	}else{
    		inserTitle(' > <span>编辑页面信息</span>','materialEdit',false);
    		
    		//加载模板基础信息
    		loadCarousel();
    		
    		//加载模板元数据信息
    		loadCarouselSource();
    	}
    	
    	backGroundImgInit();
    	
    	//表单初始化数据
    	jsonTextInit();
    	
        //表单校验
    	validate(formId, {
    		rules : {
    			remark:{
    				required:true,
    				maxlength:40
    			},
    			goodimgWidth:{
    				required:true,
    				digits:true,
    				range:[200,900000]
    			},
    			goodimgHeight:{
    				required:true,
    				digits:true,
    				range:[200,100000]
    			}
    		},
    		messages:{
    			remark:{
    				required:"请输入模板名称",
    				maxlength:"模板名称最多200个字符"
    			},
    			goodimgWidth:{
    				required:"请输入背景宽度",
    				digits:"请填写数字",
    				range:"宽度必须为200-900000之间的整数"
    			},
    			goodimgHeight:{
    				required:"请输入背景高度",
    				digits:"请填写数字",
    				range:"高度必须为200-900000之间的整数"
    			}
    		}
    	}, save);

    });


/**
 * 保存模板页面信息
 */
function save() {
	var jsonData=jsonFromt($('#' + formId).serializeArray());
	console.log(jsonData);
	var templateId=$("#templateId").val();
	var url;
	var suffix = ".jhtml";
	var isAdd = ISTYPE == "add" ? true:false;
	if (isAdd) {// 添加操作
		url = "saasPage/manage/add" + suffix;
	} else {// 修改操作
		url = "saasPage/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/saasTemplate/manage/update/init.jhtml?id='+templateId;
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$('#prompt').hide();
				$('#triggerModal').modal('hide');
			}
		});
	} else {
		showWarningWindow("warning", "没做任何修改！");
	}
}

/**
 * 背景图初始化
 */
function backGroundImgInit(){
	$("#backgroundImageDiv").uploadImg({
		urlId : "backgroundImage",
		showImg : $('#backgroundImage').val(),
		callback: printRelativePath
	});
}

/**
 * 框架图初始化
 */
function picUrlDivInit(picUrlId){
	$("#picUrlImg"+picUrlId).uploadImg({
		urlId : "picUrl"+picUrlId,
		showImg : $('#picUrl'+picUrlId).val()
	});
}

/**
 * 回调函数
 * @param printRelativePath
 */
function printRelativePath(printRelativePath){
	$(".good-template-info-picimg-wrap").css("background-image","url("+imgRoot+printRelativePath+")");
}

/**
 * 表单数据初始化
 */
function jsonTextInit(){
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
}

/**
 * 绑定取消按钮click事件
 */
$("#cancelBtn").on('click',function(){
	var url = contextPath + '/saasTemplate/manage/update/init.jhtml';
	var templateId=$("#templateId").val();
	var params='';
	if(templateId){
		params='?id='+templateId;
	}
	location.href = url+params;
});

/**
 * 自定义校验方法
 */
function validateCustomer(){
	var result=true;
	var backgroundImage=$("#backgroundImage").val();
	if(backgroundImage == null || backgroundImage==""){
		showWarningWindow("warning","请上传页面背景!",9999);
		rsult=false;
		return ;
	}
	
	return result;
}

/**
 * 加载物料模板基础信息
 */
function loadCarousel(){
	var id=$("#id").val();
	
	var url;
	var suffix = ".jhtml";
	url = "saasPage/manage/update/getBaseInfo" + suffix;
	$.ajax({
		type : 'post',
		url : url,
		async:false,
		data : {"id":id},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
				var pageInfo=data.data;
				$("#goodimgWidth").val(pageInfo.width);
				$("#goodimgHeight").val(pageInfo.height);
				$("#picUrl").val(pageInfo.backgroundImage);
				printRelativePath(pageInfo.backgroundImage);
				$("#btn-imgPreserve").click();
			}else{
				showSmReslutWindow(data.success, data.msg);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
	
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
}

/**
 * 加载物料模板元数据信息
 */
function loadCarouselSource(){
	var id=$("#id").val();
	
	var url;
	var suffix = ".jhtml";
	url = "saasPage/manage/update/getSourceInfo" + suffix;
	$.ajax({
		type : 'post',
		url : url,
		async:false,
		data : {"id":id},
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			
		},
		success : function(data) {
			if (data.success) {
//				var carouselSourceInfo=data.data;
//				console.log(carouselSourceInfo);
//				var ifram_num=0;
				var html='';
				//加载文案列表
				 $.each(data.data, function (n, obj) { 
					 //加载图片框架
					 if(obj.type=='1'){
						var showState=true;
						html='';
					    html+='<div class="good-template-info-item temlate-info-img " data-showState="'+showState+'" data-index="module-'+ifram_num+'">';
					    html+='<div class="reset-form-name">框架1：</div>';
					    html+='<div class="col-lg-2 col-xs-2">';
					    html+='<div id="picUrlImg'+ ifram_num +'"></div>';
					    html+='</div>';
					    html+='<div class="col-lg-10 col-xs-10">';
					    html+='<div class="col-lg-12 col-xs-12">';
					    html+='<button type="button" class="btn btn-danger btn-ifram-detele">删除</button>'+' ';
					    html+='<button type="button" class="btn btn-primary btn-picifram-submin">标记</button>';
					    html+='</div>';
					    html+='<div class="uploadings-desc">（建议尺寸：750*340px;格式：gif、png、jpg、大小：2Mb以内）</div>';
					    html+='</div>';
					    html+= '<input  class="input-picUrl" type="hidden" id="picUrl'+ ifram_num +'" name="source['+ifram_num+'].picUrl" value="'+ obj.image +'" >';
					    html+='<input  class="input-imgCoordinate" type="hidden" name="source['+ifram_num+'].imgCoordinate" value="'+ obj.imgCoordinate +'" >';
					    html+='<input  class="input-imgSize" type="hidden" name="source['+ifram_num+'].imgSize" value="' + obj.imgSize + '" >';
					    html+='<input  type="hidden" name="source['+ifram_num+'].type" value="1" >';
					    html+='</div>';
					       
					     $(".good-template-info-addmodule-bd").append(html);
					     $(".good-template-info-addmodule-bd .btn-picifram-submin").click();
					     /**
					      * 初始化图片组件
					      */
					     var picUrlId=ifram_num;
					     picUrlDivInit(picUrlId);
					     ifram_num++;
					     
					 }else if(obj.type=='0'){	//加载文字框架
						var showState=true;
						html='';
					    html+='<div class="good-template-info-item temlate-info-text" data-showState="'+showState+'" data-index="module-'+ifram_num+'">';
					    html+='<div class=" reset-form-name">框架2：</div>';
					    html+='<div class="col-lg-12 col-xs-12">';
					    html+='<div class="good-template-addinfo">';
					    html+='<div class="col-lg-8 col-xs-8 reset-fill-pad"><input type="text" class=" form-control" value="'+ obj.remark +'">';
					    html+='</div>';
					    html+='<div class="col-lg-4 col-xs-4">';
					    html+='<button type="button" class="btn btn-danger btn-ifram-detele">删除</button>'+' ';
					    html+='<button type="button" class="btn btn-primary btn-textifram-submin">标记</button>';
					    html+='</div>';

					    html+='</div>';
					    html+='<div class="good-template-select">';
					    html+='<div class="good-template-select-wrap col-lg-4 col-xs-4">';
					    html+='<span>字体大小</span>';
					    html+='<select class="form-control" id="source['+ifram_num+'].fontSize" name="source['+ifram_num+'].fontSize">';
					    var fontSizeOptions=setFontSize(obj.fontSize);
					    html+=fontSizeOptions;
					    html+='</select>';
					    html+='</div>';
					    html+='<div class="good-template-select-wrap col-lg-4 col-xs-4">';
					    html+='<span>字体数量</span>';
					    html+='<select class="form-control" name="source['+ifram_num+'].fontNum">';
					    var fontNumOption=setFontNum(obj.fontNum);
					    html+=fontNumOption;
					    html+='</select>';
					    html+='</div>';
					    html+='</div>';
					    html+='</div>';
					    html+= '<input type="hidden" class="input-fontText" name="source['+ifram_num+'].remark" value="'+ obj.remark +'" >';
					    html+='<input  class="input-fontCoordinate" type="hidden" name="source['+ifram_num+'].fontCoordinate" value="'+ obj.fontCoordinate +'" >';
					    html+='<input  class="input-imgSize" type="hidden" name="source['+ifram_num+'].imgSize" value="'+ obj.imgSize +'" >';
					    html+='<input  type="hidden" name="source['+ifram_num+'].type" value="0" >';
					    html+='</div>';
					    
					    $(".good-template-info-addmodule-bd").append(html);
					    $(".good-template-info-addmodule-bd .btn-textifram-submin").click();
					    ifram_num++;
					 }
				 	 
		          });  
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
	
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
}


//添加文案框架
$("#addtextframe").on('click',function(){
    
    var showState=true;
    var html='';
    html+='<div class="good-template-info-item temlate-info-text" data-showState="'+showState+'" data-index="module-'+ifram_num+'">';
    html+='<div class=" reset-form-name">框架2：</div>';
    html+='<div class="col-lg-12 col-xs-12">';
    html+='<div class="good-template-addinfo">';
    html+='<div class="col-lg-8 col-xs-8 reset-fill-pad"><input type="text" class=" form-control">';
    html+='</div>';
    html+='<div class="col-lg-4 col-xs-4">';
    html+='<button type="button" class="btn btn-danger btn-ifram-detele">删除</button>'+' ';
    html+='<button type="button" class="btn btn-primary btn-textifram-submin">标记</button>';
    html+='</div>';

    html+='</div>';
    html+='<div class="good-template-select">';
    html+='<div class="good-template-select-wrap col-lg-4 col-xs-4">';
    html+='<span>字体大小</span>';
    html+='<select class="form-control" name="source['+ifram_num+'].fontSize">';
    var fontSizeOption=setFontSize(null);
    html+=fontSizeOption;
    
    html+='</select>';
    html+='</div>';
    html+='<div class="good-template-select-wrap col-lg-4 col-xs-4">';
    html+='<span>字体数量</span>';
    html+='<select class="form-control" name="source['+ifram_num+'].fontNum">';
    var fontNumOption=setFontNum(null);
    html+=fontNumOption;
    html+='</select>';
    html+='</div>';
    html+='</div>';
    html+='</div>';
    html+= '<input type="hidden" class="input-fontText" name="source['+ifram_num+'].remark" value="" >'
    	+'<input  class="input-fontCoordinate" type="hidden" name="source['+ifram_num+'].fontCoordinate" value="" >'
    	+'<input  class="input-imgSize" type="hidden" name="source['+ifram_num+'].imgSize" value="" >'
    	+'<input  type="hidden" name="source['+ifram_num+'].type" value="0" >';
    html+='</div>';
    
    ifram_num++;
    $(".good-template-info-addmodule-bd").prepend(html);
});
//添加图片框架
$("#addimgframe").on('click',function(){
    var showState=true;
    var html= '';
    html+='<div class="good-template-info-item temlate-info-img " data-showState="'+showState+'" data-index="module-'+ifram_num+'">';
    html+='<div class="reset-form-name">框架1：</div>';
    html+='<div class="col-lg-2 col-xs-2">';
    html+='<div id="picUrlImg'+ ifram_num +'"></div>';
    html+='</div>';
    html+='<div class="col-lg-10 col-xs-10">';
    html+='<div class="col-lg-12 col-xs-12">';
    html+='<button type="button" class="btn btn-danger btn-ifram-detele">删除</button>'+' ';
    html+='<button type="button" class="btn btn-primary btn-picifram-submin">标记</button>';
    html+='</div>';
    html+='<div class="uploadings-desc">（建议尺寸：750*340px;格式：gif、png、jpg、大小：2Mb以内）</div>';
    html+='</div>';
    html+= '<input  class="input-picUrl" type="hidden" id="picUrl'+ ifram_num +'" name="source['+ifram_num+'].picUrl" value="" >'
    	+'<input  class="input-imgCoordinate" type="hidden" name="source['+ifram_num+'].imgCoordinate" value="" >'
    	+'<input  class="input-imgSize" type="hidden" name="source['+ifram_num+'].imgSize" value="" >'
    	+'<input  type="hidden" name="source['+ifram_num+'].type" value="1" >';
    html+='</div>';

    $(".good-template-info-addmodule-bd").prepend(html);
    
    /**
     * 初始化图片组件
     */
    var picUrlId=ifram_num;
    picUrlDivInit(picUrlId);
    
    ifram_num++;
});
//删除
$(".good-template-info-addmodule-bd ").on('click','.btn-ifram-detele',function(){
    var childModule = $(this).parents(".good-template-info-item").attr("data-index");
    $(".good-template-info-picimg-wrap .canvas-template-text,.good-template-info-picimg-wrap .canvas-template-picimg").filter(function(index){
        if( $(this).attr("data-ids")==childModule){
            $(this).remove();
        }
    });
    $(this).parents(".good-template-info-item").remove();
});
//图片标记
$(".good-template-info-addmodule-bd").on('click','.btn-picifram-submin',function(){
    var _thatParen = $(this).parents(".good-template-info-item");
    var showState = _thatParen.attr("data-showstate");
    var childModule = _thatParen.attr("data-index");
    var imgUrl = imgRoot+_thatParen.find(".input-picUrl").val();
    var posixVal = _thatParen.find(".input-imgCoordinate").val();
    var textSizeVal = _thatParen.find(".input-imgSize").val();
    var posix = {'x': 10, 'y': 10};
    if(posixVal){
    	posix.x=posixVal.split(',')[0];
    	posix.y=posixVal.split(',')[1];
    }
    var html='';
    html+='<div class="canvas-template-picimg drsMoveHandle alixixi" data-ids="'+childModule+'" style="z-index:'+ifram_num+'; top:'+posix.y+'px; left:'+posix.x+'px; width:'+textSizeVal.split(',')[0]+'px; height:'+textSizeVal.split(',')[1]+'px;"" data-index="'+ifram_num+'" >';
    html+='<img src="'+imgUrl+'"  />';
    html+='</div>';
    if(showState==='true'){
        $(".good-template-info-picimg-wrap").prepend(html);
        _thatParen.attr("data-showstate",false);
        _thatParen.find(".input-imgCoordinate").val(posix.x+','+posix.y);
        _thatParen.find(".input-imgSize").val(100+','+100);
    }

});
//文字标记
$(".good-template-info-addmodule-bd").on('click','.btn-textifram-submin',function(){
    var fontArray =[];
    var _thatParen = $(this).parents(".good-template-info-item");
    var showState = _thatParen.attr("data-showstate");
    var childModule = _thatParen.attr("data-index");
    var intputVal = _thatParen.find(".good-template-addinfo input[type='text']").val();
    var posixVal = _thatParen.find(".input-fontCoordinate").val();
    var textSizeVal = _thatParen.find(".input-imgSize").val();
    var posix = {'x': 10, 'y': 10};
    if(posixVal){
    	posix.x=posixVal.split(',')[0];
    	posix.y=posixVal.split(',')[1];
    }
    if(intputVal==''){
        alert('请输入文字');
        return false;
    }
    _thatParen.find(".good-template-select").filter(function(index){
        $(this).find("select option:selected").each(function(){
            var _that = $(this);
            fontArray.push(_that.val());

       });
    });
    var stringLeng =intputVal.stringIntercept(fontArray[1]);
    var html='';
    html+='<div class="canvas-template-text drsMoveHandle alixixi" data-ids="'+childModule+'" style="z-index:'+ifram_num+';top:'+posix.y+'px; left:'+posix.x+'px; font-size:'+fontArray[0]+'px; font-family:'+fontArray[2]+'; width:'+textSizeVal.split(',')[0]+'px; height:'+textSizeVal.split(',')[1]+'px;">';
    html+='<div class="canvas-template-text-content">'+stringLeng+'</div>';
    html+='</div>';
    _thatParen.find(".input-fontText").val(stringLeng);
    if(showState==='true'){
        
        _thatParen.attr("data-showstate",false);
        _thatParen.find(".input-fontCoordinate").val(posix.x+','+posix.y);
    	_thatParen.find(".input-imgSize").val(100+','+100);
        $(".good-template-info-picimg-wrap").prepend(html); 

    }else{
        $(".good-template-info-picimg-wrap .canvas-template-text").filter(function(index){
            if( $(this).attr("data-ids")==childModule){
                $(this).css({
                    "font-size":fontArray[0]+'px',
                    "font-family":fontArray[2]
                });
                $(this).children(".canvas-template-text-content").text(stringLeng);
            }
        });
    }
    
});

//限制图片宽高
$("#btn-imgPreserve").on('click',function(){
    var defaultWidth= 200,defaultHeight=200;
    var _curTagerWidth = parseInt($("#goodimgWidth").val());
    var _curTagerHeight = parseInt($("#goodimgHeight").val());
    if(_curTagerWidth>=defaultWidth && _curTagerHeight>=defaultHeight&&!isNaN(_curTagerWidth)&&!isNaN(_curTagerHeight)){
    	dragresize.maxLeft= _curTagerWidth-dragresize.minLeft;
    	dragresize.maxTop= _curTagerHeight-dragresize.minTop;
        $(".good-template-picimg-module").css({
            "paddingLeft":_curTagerWidth,
            "height":_curTagerHeight
        });
        $(".good-template-info-picimg").css({
            "height":_curTagerHeight,
            "width":_curTagerWidth
        });
        
        var imgSize=_curTagerWidth+'X'+_curTagerHeight;
        $("#imgSize").val(imgSize);
        $("#type").val('001');
        $("#imgType").val('003');
    }else{
        showWarningWindow("warning", '宽度不得少于'+defaultWidth+','+'高度不得小于'+defaultHeight,9999);
    }
});

//判断dom 是否有class
function domClass (elem,className) {
    var reg = new RegExp('(^|\\s+)' + className + '($|\\s+)');
    return reg.test(elem.className);
};
String.prototype.stringIntercept=function(n) {
    var r = /[^\x00-\xff]/g;
    if(this.replace(r, "mm").length <= n) return this;
    // n = n - 3;
    var m = Math.floor(n/2);
    for(var i=m; i<this.length; i++) {
        if(this.substr(0, i).replace(r, "mm").length>=n) {
            return this.substr(0, i); }
    } return this;
};

/**
 * dragresize插件初始化
 */
function dragresizeInit(){
	dragresize = new DragResize('dragresize',
            { minWidth:85, minHeight: 85, minLeft: 20, minTop: 20, maxLeft:400, maxTop: 600 });
    dragresize.isElement = function(elm)
    {
        if (elm.className && elm.className.indexOf('alixixi') > -1) return true;

    };
    dragresize.isHandle = function(elm)
    {
        if (elm.className && elm.className.indexOf('drsMoveHandle') > -1) return true;
    };
    dragresize.ondragfocus = function() { };
    dragresize.ondragstart = function(isResize) {

    };
    dragresize.ondragmove = function(isResize) {

    };
    dragresize.ondragend = function(isResize) {
        var posixX = this.element.style.left;
        var posixY = this.element.style.top;
        var eleWidth = this.element.style.width;
        var eleHeight = this.element.style.height;
        posixX = posixX.substring(0,posixX.length-2);
        posixY = posixY.substring(0,posixY.length-2);
        eleWidth = eleWidth.substring(0,eleWidth.length-2);
        eleHeight = eleHeight.substring(0,eleHeight.length-2);
        var moduleIds = this.element.getAttribute('data-ids');
        //var _that = this.element.get(0);
        var domState = domClass(this.element,'canvas-template-picimg');
//        console.log(domState);
        if(domState){
            $(".good-template-info-addmodule-bd .good-template-info-item").filter(function(){
                var childModule =$(this).attr("data-index");
                if( moduleIds==childModule){
                    $(this).find(".input-imgCoordinate").val(posixX+','+posixY);
                    $(this).find(".input-imgSize").val(eleWidth+','+eleHeight);
                }
            });
        }else{
            $(".good-template-info-addmodule-bd .good-template-info-item").filter(function(){
                var childModule =$(this).attr("data-index");
                if( moduleIds==childModule){
                    $(this).find(".input-fontCoordinate").val(posixX+','+posixY);
                    $(this).find(".input-imgSize").val(eleWidth+','+eleHeight);
                }
            });
        }


    };
    dragresize.ondragblur = function() { };
    dragresize.apply(document);
}

/**
 * 设置字体下拉框选项
 * @param fontSize
 */
function setFontSize(fontSize){
	var option="";
    var fontSizes=new Array("6","8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72");//字体数组
    for(var i in fontSizes ){
    	if(fontSize==fontSizes[i]){
    		option+='<option value="'+fontSizes[i]+'" selected >'+fontSizes[i]+'</option>';
	    }else{
	    	option+='<option value="'+fontSizes[i]+'">'+fontSizes[i]+'</option>';
	    }
    }
    return option;
}

/**
 * 设置显示字数
 * @param fontNum
 * @returns {String}
 */
function setFontNum(fontNum){
	var option="";
    var fontNums=new Array("13","14","15","16","17","18","19","20");//字数数组
    for(var i in fontNums ){
    	if(fontNum==fontNums[i]){
    		option+='<option value="'+fontNums[i]+'" selected >'+fontNums[i]+'</option>';
	    }else{
	    	option+='<option value="'+fontNums[i]+'">'+fontNums[i]+'</option>';
	    }
    }
    return option;
}
