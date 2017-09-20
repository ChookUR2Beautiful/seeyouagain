var enrollList1;//已报名
var enrollList2;//报名通过
var enrollList3;//实名审核
var enrollList4;//试播审核
var enrollList5;//试播通过
var enrollList6;//限制中
var enrollList7;//被拒绝

var imgRoot = $("#fastfdsHttp").val();


$(document).ready(function() {
	inserTitle('> 新食尚大赛> <a href="VstarEnroll/manage/init.jhtml" target="right">报名审核管理</a>','enrollConfirm',true);
	
	//加载区域控件
	$("#ld1").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld2").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld3").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld4").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld5").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld6").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	$("#ld7").areaLd({
		showConfig : [{name:"provinceId",tipTitle:"--省--"},{name:"cityId",tipTitle:"--市--"},{name:"areaId",tipTitle:"--区--"}],
		isChosen : true
	});
	
	pageInit();
	
	bindChangePhoto();
	
	//通过
	putaway();
	
	//拒绝
	removeOffshelf();
	
});

/**
 * 绑定图片切换事件
 */
function bindChangePhoto(){
	$(".tab-content").on("click",".list-box img",function(){
	var src=$(this).attr("src");
	$(this).parent().prev().css("background","url("+src+")").css("background-repeat","no-repeat").css("background-position","center").css("background-size","100%");
}); 
}

/**
 * 加载页面数据
 */
function pageInit(){
	enrollList1 = $('#enrollList1').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success1,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm1',
		param :{}
	});
	
	enrollList2 = $('#enrollList2').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success2,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm2',
		param :{}
	});
	
	//实名认证待审核
	enrollList3 = $('#enrollList3').page({
		url : 'VstarRealName/manage/init/list.jhtml',
		success : success3,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm3',
		param :{}
	});
	
	enrollList4 = $('#enrollList4').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success4,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm4',
		param :{}
	});
	
	enrollList5 = $('#enrollList5').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success5,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm5',
		param :{}
	});
	
	enrollList6 = $('#enrollList6').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success6,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm6',
		param :{}
	});
	
	enrollList7 = $('#enrollList7').page({
		url : 'VstarEnroll/manage/init/list.jhtml',
		success : success7,
		pageBtnNum : 10,//默认翻页按钮数量
		pageSize : 20,//每页条数
		paramForm : 'searchForm7',
		param :{}
	});
}

/**
 * 加载报名审核数据
 * 
 * @param data
 * @param obj
 */
function success1(data, obj) {
	$("#col1").text(data.total);
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			var imgList=data.content[i].enrollImgList;
			html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
	        html.push('<div class="img-list">');
	        for(var j=0;j<imgList.length;j++){
	        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
	        }
	        
	        html.push('</div>');
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
//	        		+'<span>点赞数：'+(undefined == data.content[i].likeCount ? "0" : data.content[i].likeCount)+'</span></p>');
	        +'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        if(permissions.update == 'true'&& data.content[i].status==1 ){
	        	html.push('<a class="pass-btn" href="javascript:update('+data.content[i].id+',2)">通过</a>');
	        	html.push('<a href="javascript:;" data-type="ajax" data-toggle="modal" data-url="VstarEnroll/manage/update/init.jhtml?status=3&id='+data.content[i].id
						+'" data-width="auto" data-position="fit" data-title="审核提示">拒绝</a>');  
	        }
	        
	        if(permissions.update == 'true'&& data.content[i].status==2 ){
				html.push('<a class="restrict-btn" href="javascript:confine('+data.content[i].id+',1)">限制</a>');
			}
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 加载试播审核数据
 * 
 * @param data
 * @param obj
 */
function success2(data, obj) {
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			try {
				var imgList=data.content[i].enrollImgList;
				html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
		        html.push('<div class="img-list">');
		        for(var j=0;j<imgList.length;j++){
		        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
		        }
		        
		        html.push('</div>');
			} catch (e) {
				console.log(e);
			}
			
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
//	        		+'<span>点赞数：'+(undefined == data.content[i].likeCount ? "0" : data.content[i].likeCount)+'</span></p>');
	        		+'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        if(permissions.update == 'true'&& data.content[i].status==5 ){
	        	html.push('<a class="pass-btn" href="javascript:confirmPilot('+data.content[i].id+',7)">通过</a>');
	        	html.push('<a  href="javascript:confirmPilot('+data.content[i].id+',8)">不通过</a>');
	        }
	        
	        if(permissions.update == 'true'&& data.content[i].status==2 ){
//				html.push('<a class="restrict-btn" href="javascript:confine('+data.content[i].id+',1)">限制</a>');
	        	html.push('<a class="restrict-btn" href="javascript:;" data-type="ajax" data-toggle="modal" data-url="VstarEnroll/manage/update/init.jhtml?confining=1&id='+data.content[i].id
						+'" data-width="auto" data-position="fit" data-title="限制提示">限制</a>'); 
			}
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 查询实名审核信息成功回调函数
 * @param data
 * @param obj
 */
function success3(data, obj) {
	$("#col3").text(data.total);
	var formId = "searchForm1", title = "实名审核列表", subtitle = "条信息";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="
			+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		checkable : true,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update","delete" ],// 不需要选择checkbox处理的权限
			width : 120,// 宽度
			// 当前列的中元素
			cols : [ 
				 {
					title : "拒绝",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "VstarRealName/manage/update.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					},
					customMethod : function(value, data){
							var result="";
							if(data.status==4){
								result = "<a href='javascript:realNameUpdate("+data.id+",6"+")'>" + "拒绝" + "</a>";
							}
							return result;
				    }
				 }, {
						title : "通过",// 标题
						linkInfoName : "href",
						linkInfo : {
							href : "VstarRealName/manage/update/init.jhtml",
							param : [ "id" ],
							permission : "update"
						},
						customMethod : function(value, data){
							var result="";
							if(data.status==4){
								result = "<a href='javascript:realNameUpdate("+data.id+",5"+")'>" + "通过" + "</a>";
							}
							return result;
						}
					 } 
			]
		},
		cols : [ {
			title : "报名编号",
			name : "id",
			type : "string",
			width : 70
		},{
			title : "身份证正面",
			name : "imgUrl201",
			type : "string",
			width : 220,
			customMethod : function(value, data) {
				var html='';
				html+='<div class="good-table-imgmodule">'
					+    '<img src="'+imgRoot+value+'"  class="" style=""/>'
		          
		            +    '<div class="good-table-img-price">'
		            +    '   <span>'+data.identityName+'</span><span>身份证号：<em>'+ data.identityNumber +'</em> </span>' 
		            +    '</div>'
		            +'</div>';
				return html;
			}
		},{
			title : "身份证反面",
			name : "imgUrl202",
			type : "string",
			width : 210,
			customMethod : function(value, data) {
				return '<img src="'+imgRoot+value + '"/>';
			}
		},{
			title : "持证照",
			name : "imgUrl203",
			type : "string",
			width : 210,
			customMethod : function(value, data) {
				return '<img src="'+imgRoot+value + '"/>';
			}
		},{
			title : "报名照片",
			name : "imgUrl101",
			type : "string",
			width : 210,
			customMethod : function(value, data) {
				return '<img src="'+imgRoot+value + '"/>';
			}
		},{
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 90
		},{
			title : "状态",
			name : "statusText",
			type : "string",
			width : 100
		} ]
	}, permissions);
}

/**
 * 加载限制中数据
 * 
 * @param data
 * @param obj
 */
function success4(data, obj) {
	$("#col4").text(data.total);
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			try {
				var imgList=data.content[i].enrollImgList;
				html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
		        html.push('<div class="img-list">');
		        for(var j=0;j<imgList.length;j++){
		        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
		        }
		        
		        html.push('</div>');
				
			} catch (e) {
				console.log(e);
			}
			
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
	        		+'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        
	        //参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝
	        if(permissions.update == 'true'&& (data.content[i].status==2 || data.content[i].status==5 ) && data.content[i].playStatus==0){
	        	html.push('<a class="pass-btn" href="javascript:confirmPilot('+data.content[i].id+','+data.content[i].status+',1)">通过</a>');
	        	html.push('<a  href="javascript:confirmPilot('+data.content[i].id+',8)">不通过</a>');
	        }
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 加载试播通过数据
 * 
 * @param data
 * @param obj
 */
function success5(data, obj) {
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			var imgList=data.content[i].enrollImgList;
			html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
	        html.push('<div class="img-list">');
	        for(var j=0;j<imgList.length;j++){
	        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
	        }
	        
	        html.push('</div>');
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
	        		+'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        
	        if(permissions.update == 'true'&& data.content[i].playStatus==1 ){
				html.push('<a class="restrict-btn" href="javascript:confine('+data.content[i].id+',1)">限制</a>');
			}
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 加载限制中数据
 * 
 * @param data
 * @param obj
 */
function success6(data, obj) {
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			var imgList=data.content[i].enrollImgList;
			html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
	        html.push('<div class="img-list">');
	        for(var j=0;j<imgList.length;j++){
	        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
	        }
	        
	        html.push('</div>');
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
//	        		+'<span>点赞数：'+(undefined == data.content[i].likeCount ? "0" : data.content[i].likeCount)+'</span></p>');
	        		+'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        
	        if(permissions.update == 'true'&& data.content[i].confining==1 ){
				
				html.push('<a class="restrict-btn" href="javascript:confine('+data.content[i].id+',0)">恢复</a>&nbsp;&nbsp;');
			}
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	obj.find('div').eq(0).html(html.join(''));
}

/**
 * 加载限制中数据
 * 
 * @param data
 * @param obj
 */
function success7(data, obj) {
	var html = [];
	
	html.push('<div class="list-box">');
	
	if(null != data && data.content.length > 0)
	{
		for(var i = 0; i < data.content.length; i++){
			html.push('<div class="box">');
			html.push('<p class="num">选手编号：'+(undefined == data.content[i].id ? "-" : data.content[i].id)+'</p>');
			var imgList=data.content[i].enrollImgList;
			html.push('<div style="background: url('+imgRoot+imgList[0].imgUrl+');background-repeat: no-repeat;background-position: center;background-size: 100%;" class="img-box"></div>');
	        html.push('<div class="img-list">');
	        for(var j=0;j<imgList.length;j++){
	        	html.push('<img src="'+imgRoot+imgList[j].imgUrl+'">');
	        }
	        
	        html.push('</div>');
	        html.push('<p class="name">姓名：'+(undefined == data.content[i].nname ? "-" : data.content[i].nname)
	        		+'<span>城市：'+(undefined == data.content[i].areaText ? "-" : data.content[i].areaText)+'</span></p>');
	        html.push('<p class="phone">手机号码：'+(undefined == data.content[i].phone ? "-" : data.content[i].phone)
//	        		+'<span>点赞数：'+(undefined == data.content[i].likeCount ? "0" : data.content[i].likeCount)+'</span></p>');
	        		+'<span>'+data.content[i].statusText +'</span></p>');
	        html.push('<div class="btn-box">');
	        
	        if(permissions.update == 'true'&& data.content[i].confining==1 ){
				
				html.push('<a class="restrict-btn" href="javascript:confine('+data.content[i].id+',0)">恢复</a>&nbsp;&nbsp;');
			}
	        
	        html.push('</div>');
	        html.push('</div>');
	        
		}
	}
	else
	{
		html.push('<p class="nodata-hint">暂无数据</>');
	}
	
	html.push('</div>');
	
	obj.find('div').eq(0).html(html.join(''));
}


/**
 * 更新报名审核状态
 * @param id
 * @param status
 * @param telphones
 */
function update(id,status){
	 $.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"status":status},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 更新报名限制状态
 * @param id
 * @param confining
 */
function confine(id,confining){
	$.ajax({
        url : "VstarEnroll/manage/update.jhtml",
        type : "post",
        dataType : "json",
        data:{"id":id,"confining":confining},
        success : function(result) {
       	 if (result.success) {
    			showSmReslutWindow(result.success, result.msg);
    			setTimeout(function() {
    				pageInit();
    			}, 1000);
    		} else {
    			window.messager.warning(result.msg);
    		}
        }
    });
}

/**
 * 试播审核
 */
 function confirmPilot(id,status,playStatus){
	 if(id==undefined || id == '' || status== undefined || status == ''){
		 window.messager.warning("报名信息不完整");
	 }
	 
	 var tips="是否通过复选审核，通过后选手获得主播试播资格";
	 if(status=="7"){
		 tips="是否通过复选审核，通过后选手获得主播试播资格";
	 }else if(status=="8"){
		 tips="是否拒绝申请";
	 }
	 
	 showSmConfirmWindow(function (){
		 $.ajax({
		        url : "VstarEnroll/manage/update.jhtml",
		        type : "post",
		        dataType : "json",
		        data:{"id":id,"status":status,"playStatus":playStatus},
		        success : function(result) {
		       	 if (result.success) {
		    			showSmReslutWindow(result.success, result.msg);
		    			setTimeout(function() {
		    				pageInit();
		    			}, 1000);
		    		} else {
		    			window.messager.warning(result.msg);
		    		}
		        }
		    });
	 },tips);
 }
 
 /**
  * 更新实名审核状态
  * @param id
  * @param status
  * @param telphones
  */
 function realNameUpdate(id,status){
 	 $.ajax({
         url : "VstarRealName/manage/update.jhtml",
         type : "post",
         dataType : "json",
         data:{"id":id,"status":status},
         success : function(result) {
        	 if (result.success) {
     			showSmReslutWindow(result.success, result.msg);
     			setTimeout(function() {
     				pageInit();
     			}, 1000);
     		} else {
     			window.messager.warning(result.msg);
     		}
         }
     });
 }
 

 /**
  * 批量通过
  */	
 function putaway(){
 	$("#putaway").click(function(){
 		console.log(enrollList3.getIds());
 		if(!enrollList3.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!enrollList3.validateChose("status", "4", "非待审核数据不可执行此操作")){
 			return;
 		}
 		var data = {ids:enrollList3.getIds(),status:'5'};
 		updateBatch(data,"你确定要通过选中信息？");
 	});
 }

 /**
  * 批量拒绝
  */	
 function removeOffshelf(){
 	$("#removeOffshelf").click(function(){
 		console.log(enrollList3.getIds());
 		if(!enrollList3.getIds()){
 			showWarningWindow("warning","请至少选择一条记录！");
 			return;
 		}
 		if(!enrollList3.validateChose("status", "4", "非待审核数据不可执行此操作")){
 			return;
 		}
 		var data = {ids:enrollList3.getIds(),status:'6'};
 		updateBatch(data,"你确定要拒绝选中信息？");
 	});
 }

 /**
 * 批量更新报名审核状态
 * @param data
 * @param title
 */
 function updateBatch(data,title){
 	showSmConfirmWindow(function() {
 					$.ajax({
 				        type: "POST",
 				        url: "VstarRealName/manage/updateRealNameBatch.jhtml",
 				        data: data,
 				        dataType: "json",
 				        success: function(result){
 							showSmReslutWindow(result.success, result.msg);
 							pageInit();
 				         }
 				    });
 			},title);
 }

