var searchChosen = undefined;
var dateCount = 0, cityCount = 0, dateSize = 10, citySize = 10;
var sellersDiv = '<label class="col-md-2 control-label">选择商家：</label><div class="col-md-8"><label id="checkids"></label><textarea style="display: block;" id="sellerids" rows="5" name="shareRange[0].rangecontent" class="col-md-8"></textarea></div>';
var searchChooseURL="marketingManagement/activityManagement/init/choseSeller/init.jhtml",url;

/**
 * 初始化验证方法
 */
function initValidator(){
	 
	 /**
	  * 校验价钱
	 */
	 $.validator.addMethod("url", function(value, element) {
		 if(value){
			 var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
			        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			        + "|" // 允许IP和DOMAIN（域名）
			        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			        + "[a-z]{2,6})" // first level domain- .com or .museum
			        + "(:[0-9]{1,4})?" // 端口- :80
			        + "((/?)|" // a slash isn't required if there is no file name
			        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
			        var re=new RegExp(strRegex);
			        //re.test()
			        if (re.test(str_url)){
			            return (true);
			        }else{
			            return (false);
			        }
		 }
	}, "请输入正确链接！");
	 

	for(var i=0;i<formId.length;i++){
		validate(formId[i],valiinfo[formId[i]],formSubmit(formId[i]));
	}
};	

$(function() {
	$("#cities").on("click",".icon-plus",function() {
		if ($(this).parents(".col-md-5").find(".input-group").size() < citySize) {
			cityCount++;
			$(this).parents(".input-group").after($(".cityTemp").html()).next().find(".ld").each(function(){
				$(this).areaLd({
					isChosen : true,
					separator : ",",
					showConfig : [
							{
								name : "shareRange["+ cityCount + "].province",
								tipTitle : "--省--"
							},
							{
								name : "shareRange[" + cityCount + "].rangecontent",
								tipTitle : "--市--"
							} ],
					lastChange:function(){
						if($('input[name="range"]:checked').val()==3){
							rebuildChooseSeller();
						}
					}
				});
			});
		}
	});
	$("#cities").on("click",".icon-minus",function() {
		if ($(this).parents(".col-md-5").find(".input-group").size() > 1) {
			$(this).parents(".input-group").remove();
			if($('input[name="range"]:checked').val()==3){
				rebuildChooseSeller();
			}
		}
	});

	validate("editShareForm", {
		rules:{
			title:{
				required:true
			},
			link:{
				required : true,
				url:true
			}
		},
		messages:{
			title:{
				required:"分享信息必填"
			},
			link:{
				required:"分享链接必填",
				url:"分享链接格式不正确"
			}
		}
	}, formAjax);
	
	if(type == "add"){
		inserTitle(
				' >用户端管理 > <span><a href="user_terminal/share/init.jhtml" target="right">分享信息管理</a> >添加分享信息',
				'sharList', true);
		
		//rebuildChooseSeller();
		$("#allSeller,#cities,#sellers").hide();
		
		//是全国通用的则区域和商家的信息清空
		$("#allArea").on("change", function() {
			//清空区域信息
			$("#cities").find(".col-md-5").empty();
			//清空商家控件
			$("#sellers").empty();
			$("#allSeller").empty();
		});
		
		//指定区域
		$("#specifyArea").on("change", function() {
			$("#sellers").empty();
			$("#cities").show();
			$("#cities").find(".col-md-5").html($(".cityTemp").html());
			$("#cities").find(".ld").areaLd({
				isChosen : true,
				separator : ",",
				showConfig : [ {
					name : "shareRange[" + 0 + "].province",
					tipTitle : "--省--"
				}, {
					name : "shareRange[" + 0 + "].rangecontent",
					tipTitle : "--市--"
				} ],
				lastChange:function(){
					if($('input[name="range"]:checked').val()==3){
						rebuildChooseSeller();
					}
				}
			});
		});
		//【指定商家】
		$("#specifySeller").on("change", function() {
			//清空区域信息
			$("#cities").find(".col-md-5").empty();
			$("#sellers").show();
			$("#sellers").html(sellersDiv);
			if($('input[name="range"]:checked').val()==3){
				rebuildChooseSeller();
			}
		});
		
	}else{
		
		//设置标的值
//		cityCount = $("#cities").find(".input-group").size()-1;
		cityCount = $("#cities").find(".input-group").size();
		inserTitle(
				' >用户端管理 > <span><a href="user_terminal/share/init.jhtml" target="right">分享信息管理</a> >修改分享信息',
				'sharList', true);
		//是全国通用的则区域和商家的信息清空
		$("#allArea").on("change", function() {
			//$("#allSeller,#cities,#sellers").show();
			//清空区域信息
			$("#cities").find(".col-md-5").empty();
			//清空商家控件
			$("#sellers").empty();
			$("#allSeller").empty();
		});
		//指定区域
		$("#specifyArea").on("change", function() {
			$("#sellers").empty();
			$("#cities").show();
			$("#cities").find(".col-md-5").html($(".cityTemp").html());
			$("#cities").find(".ld").areaLd({
				isChosen : true,
				separator : ",",
				showConfig : [ {
					name : "shareRange[" + 0 + "].province",
					tipTitle : "--省--"
				}, {
					name : "shareRange[" + 0 + "].rangecontent",
					tipTitle : "--市--"
				} ],
			});
		});
		//【不是全部商家】
		$("#specifySeller").on("change", function() {
			//清空区域信息
			$("#cities").find(".col-md-5").empty();
			if($('input[name="range"]:checked').val()==3){
				rebuildChooseSeller();
			}
		});
		
		if($("#allSeller").html()){
			$("#allSeller").find(':radio[value="1"]').on("change",function(){
				$("#sellers").empty();
				//$("#cities").find(".col-md-5").empty();
			});
			//【不是全部商家】
			$("#range").find(':radio[value="3"]').on("change",function(){
				rebuildChooseSeller();
				$("#cities").find(".col-md-5").html($(".cityTemp").html());
				$("#cities").find(".ld").areaLd({
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "shareRange[" + 0 + "].province",
						tipTitle : "--省--"
					}, {
						name : "shareRange[" + 0 + "].rangecontent",
						tipTitle : "--市--"
					} ],
					lastChange:function(){
						if($('input[name="range"]:checked').val()==3){
							rebuildChooseSeller();
						}
					}
				});
			});
		}
		
		if($("#cities").html()){
			$(".updateld").each(function(index, item) {
				var ld = $(this).areaLd({
					isChosen : true,
					separator : ",",
					showConfig : [ {
						name : "shareRange[" + index + "].province",
						tipTitle : "--省--"
					}, {
						name : "shareRange[" + index + "].rangecontent",
						tipTitle : "--市--"
					} ],
					lastChange:function(){
						if($('input[name="range"]:checked').val()==0){
							rebuildChooseSeller();
						}
					}
				});
			});
		}
		if($.trim($("#sellers").html()).length>0){
			console.info($("#sellers").html());
			searchChosen=null;	
			$("#sellers").empty();
			$("#sellers").html(sellersDiv);
			var sellerids = $('input[name="shareRange[0].rangecontent"]:checked').val();
			searchChosen = $("#sellerids").searchChosen( {
				url : searchChooseURL+getrangecontentids(),
				initUrl:"user_terminal/share/update/getSellers.jhtml?sid="+$("#sid").val(),
				initId:"sellerid",
				initTitle:"sellername"
			});
		}
	}
});

/**
 * 重新构建选择商家控件
 */
function rebuildChooseSeller(){
	searchChosen=null;
	//清空原有的
	$("#sellers").empty();
	$("#sellers").html(sellersDiv);
	searchChosen = $("#sellerids").searchChosen( {
		url : searchChooseURL+getrangecontentids()
	});
}


function formAjax() {
	var allArea = $("[name=range]:checked").val();  //获取用户添加分享信息时分享范围选择的是全国（1）还是指定城市（2）或者是指定商家（3）
	if(allArea == 1){  //全国，不需要检验城市和商家
		doAddOrUpdate();
	}else if(allArea == 2){  //指定城市
		if(checkCities()){  //只需要城市检验
			doAddOrUpdate();
		}
	}else{  //指定商家
		if(!checkids()){//只需要商家校验
			return false;
		}
		doAddOrUpdate();
	}
}

//进行添加或者更新分享信息的操作
function doAddOrUpdate(){
	var data = jsonFromt($('#editShareForm').serializeArray());
	var url;
	if(type="update"){
		url = "user_terminal/share/update.jhtml";
	}else{
		url = "user_terminal/share/add.jhtml";
	}
	$.ajax({
		type : 'post',
		url : url,
		data : data,
		dataType : 'json',
		beforeSend : function(XMLHttpRequest) {
			$('#prompt').show();
		},
		success : function(data) {
			showSmReslutWindow(data.success, data.msg);
			// 添加成功后跳转到列表页面
			var url = contextPath +'/user_terminal/share/init.jhtml';
			setTimeout(function() {
				location.href = url;
			}, 1000);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}

function checkids(){//商家名称校验
	 if($("#sellerids").val().length!=0){
		  return true;
	  }
	  submitDataError($("#checkids"),"请选择商家！");
	  return false;
	};

	/**
	 * 返回
	 */
	function muBack(){
		var url = contextPath + '/businessman/sellerSubsidy/init.jhtml';
			location.href =url;
	}

/**
 * 取得所有所选城市的id以逗号拼接的值
 * @returns
 */
function getrangecontentids(){
	var ids = [];
	$.each($('#cities').find('select[name$=".rangecontent"]'),function(index,value){
		ids.push($(value).val());
	});
	return "?rangecontentids="+ids.join(",");
}

function addShowAll(){
	$("#allArea").on("change", function() {
		$("#cities").find(".col-md-5").empty();
		cityCount = 0;//清零
		
		if($('input[name="allArea"]') && $('input[name="allArea"]').val()===1){
			url = searchChooseURL;
		}else{
			url = searchChooseURL+getrangecontentids();
		}
		searchChosen.destory();
		searchChosen = $("#sellerids").searchChosen( {
			url : url,
		});
	});

	$("#specifyArea").on("change", function() {
		$("#cities").find(".col-md-5").html($(".cityTemp").html());
		$("#cities").find(".ld").areaLd({
			isChosen : true,
			separator : ",",
			showConfig : [ {
				name : "shareRange[" + 0 + "].province",
				tipTitle : "--省--"
			}, {
				name : "shareRange[" + 0 + "].rangecontent",
				tipTitle : "--市--"
			} ],
			lastChange:function(){
				searchChosen.destory();
				if($('input[name="showAll"]') && $('input[name="showAll"]').val()===1){
					url = searchChooseURL;
				}else{
					url = searchChooseURL+getrangecontentids();
				}
				searchChosen = $("#sellerids").searchChosen( {
					url : url
				});
			}
		});
		if($('input[name="showAll"]') && $('input[name="showAll"]').val()===1){
			url = searchChooseURL;
		}else{
			url = searchChooseURL+getrangecontentids();
		}
		searchChosen.destory();
		searchChosen = $("#sellerids").searchChosen( {
			url : url,
		});
	});
}

function updateShowAll(){
	$("#allArea").on("change", function() {
		$("#cities").find(".col-md-5").toggle();
		searchChosen.destory();
		url = searchChooseURL;
		searchChosen = $("#sellerids").searchChosen( {
			url : url,
		});
	});
	
	$("#specifyArea").on("change", function() {
		$("#cities").find(".col-md-5").toggle();
		searchChosen.destory();
		url = searchChooseURL+getrangecontentids();
		searchChosen = $("#sellerids").searchChosen( {
			url : url
		});
	});
}

/**
 * 校验区域是否填写
 * @returns {Boolean}
 */
function checkCities(){
	var reuslt = true;
	var selectAray = [ "province", "rangecontent"];
	$.each(selectAray,function(index,name){
		$("#cities").find('select[name$="'+name+'"]').each(function(){
			var val = $(this).val();
			if(val==""){
				setErrorMark($(this),"#cities",name,'#editShareForm',true);
				reuslt = false;
			}
		});
		
	});
	return reuslt;
}


